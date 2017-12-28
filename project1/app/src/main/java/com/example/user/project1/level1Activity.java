package com.example.user.project1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Random;


public class level1Activity extends AppCompatActivity {
    GameView gv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gv = new GameView(this);
        setContentView(gv);
    }

    //도형 정보
    class Shape {
        final static int RECT = 0;
        final static int CIRCLE = 1;
        final static int TRIANGLE = 2;

        int what;
        int color;
        Rect rt;
    }

    // 게임 뷰
    class GameView extends View {
        // 빈 화면
        final static int BLANK = 0;
        final static int PLAY = 1;
        //진행속도
        final static int DELAY = 1000;
        //현재상태
        int status;
        //생성 목록
        ArrayList<Shape> arShape = new ArrayList<Shape>();
        Random Rnd = new Random();
        Activity mParent;

        public GameView(Context context) {
            super(context);
            mParent = (Activity) context;
            // 빈 화면 시작 잠시 후 게임 시작
            status = BLANK;
            mHandler.sendEmptyMessageDelayed(0, DELAY);
        }

        public void onDraw(Canvas canvas) {
            //검은색 배경으로 지우고 빈화면이면 지우고 리턴
            canvas.drawColor(Color.BLACK);
            if (status == BLANK) {
                return;
            }

            //도형 목록을 순회하면서 도형 정보대로 출력
            int idx;
            for (idx = 0; idx < arShape.size(); idx++) {
                Paint Pnt = new Paint();
                Pnt.setAntiAlias(true);
                Pnt.setColor(arShape.get(idx).color);

                Rect rt = arShape.get(idx).rt;
                switch (arShape.get(idx).what) {
                    case Shape.RECT:
                        canvas.drawRect(rt, Pnt);
                        break;
                    case Shape.CIRCLE:
                        canvas.drawCircle(rt.left + rt.width() / 2, rt.top + rt.height() / 2, rt.width() / 2, Pnt);
                        break;
                    case Shape.TRIANGLE:
                        Path path = new Path();
                        path.moveTo(rt.left + rt.width() / 2, rt.top);
                        path.lineTo(rt.left, rt.bottom);
                        path.lineTo(rt.right, rt.bottom);
                        canvas.drawPath(path, Pnt);
                        break;
                }
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int sel;
                sel = FindShapeIdx((int)event.getX(), (int)event.getY());

                //빈바닥은 무시
                if (sel == -1) {
                    return true;
                }

                //마지막 추가된 도형을 제대로 찍으면 다음단계
                //빈화면 후 새도형
                if (sel == arShape.size() - 1 ) {
                    status = BLANK;
                    invalidate();
                    mHandler.sendEmptyMessageDelayed(0, DELAY);
                    //틀린 도형은 질문 후 게임 종료 or 리스타트
                } else{
                    new AlertDialog.Builder(getContext())
                            .setMessage("틀렸습니다.")
                            .setTitle("The End")
                            .setPositiveButton("New game", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    arShape.clear();
                                    status = BLANK;
                                    invalidate();
                                    mHandler.sendEmptyMessageDelayed(0, DELAY);
                                }
                            })
                            .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    mParent.finish();
                                }
                            })
                            .show();
                }
                return true;
            }
            return false;
        }

        //새로운 도형 목록에 추가
        void AddNewShape() {
            Shape shape = new Shape();
            int idx;
            boolean bFindIntersect;
            Rect rt = new Rect();

            //기존도형과 겹치지 않는 새 위치 서치
            for (; ; ) {
                //크기는 32, 48,64 중 하나 선택
                int Size = 64 + 32 * Rnd.nextInt(3);

                //위치 난수 선택
                rt.left = Rnd.nextInt(getWidth());
                rt.top = Rnd.nextInt(getHeight());
                rt.right = rt.left + Size;
                rt.bottom = rt.top + Size;

                //화면을 벗어나면 다시 서치
                if (rt.right > getWidth() || rt.bottom > getHeight()) {
                    continue;
                }

                //기존 도형과 겹치는지 조사
                bFindIntersect = false;
                for (idx = 0; idx < arShape.size(); idx ++) {
                    if (rt.intersect(arShape.get(idx).rt) == true) {
                        bFindIntersect = true;
                    }
                }

                //기존 도형과 안겹치면 종료
                if (bFindIntersect == false) {
                    break;
                }
            }
            //새로운 도형을 선택
            shape.what = Rnd.nextInt(3);

            switch (Rnd.nextInt(5)) {
                case 0:
                    shape.color = Color.WHITE;
                    break;
                case 1:
                    shape.color = Color.RED;
                    break;
                case 2:
                    shape.color = Color.GREEN;
                    break;
                case 3:
                    shape.color = Color.BLUE;
                    break;
                case 4:
                    shape.color = Color.YELLOW;
                    break;

            }

            shape.rt = rt;
            arShape.add(shape);
        }

        //x, y 위치의 도형의 번호를 지정 없으면 -1
        int FindShapeIdx(int x, int y) {
            int idx;

            for (idx = 0; idx < arShape.size(); idx ++){
                if (arShape.get(idx).rt.contains(x, y)) {
                    return idx;
                }
            }
            return -1;
        }

        //새 도형을 추가하고 화면을 다시 그림
        Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                AddNewShape();
                status = PLAY;
                invalidate();

                String title;
                title = "기억력게임 - " + arShape.size() + " 단계.중수";
                mParent.setTitle((title));
            }
        };
    }
}



