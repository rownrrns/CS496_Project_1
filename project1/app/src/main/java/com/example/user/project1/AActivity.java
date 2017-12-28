package com.example.user.project1;

import android.graphics.Canvas;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AActivity extends AppCompatActivity {
    private HashMap<String, String> map;
    private ArrayList<String> list;
    private TextView selected_item_textview;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;
    private String map_data;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putSerializable(map_data, AActivity.this.map);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        this.map = (HashMap<String, String>) savedInstanceState.getSerializable(map_data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);


        final ListView listview = (ListView)findViewById(R.id.listview);
        selected_item_textview = (TextView)findViewById(R.id.selected_item_textview);
        final TextView teltext = (TextView) findViewById(R.id.Tel_text);
        final EditText add_name = (EditText) findViewById(R.id.new_name);
        final EditText add_num = (EditText) findViewById(R.id.new_number);
        Button btn_add_window = (Button) findViewById(R.id.addwindow);
        final Button btn_add_num = (Button) findViewById(R.id.addnum);
        final Button btn_cancel = (Button) findViewById(R.id.cancel_btn);
        final Button btn_del = (Button) findViewById(R.id.del_btn);

        //버튼을 눌렀을 때 표시창이 변경되는 이벤트
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.addwindow:
                        teltext.setVisibility(View.GONE);
                        selected_item_textview.setVisibility(View.GONE);
                        add_name.setVisibility(View.VISIBLE);
                        add_num.setVisibility(View.VISIBLE);
                        btn_add_num.setVisibility(View.VISIBLE);
                        btn_cancel.setVisibility(View.VISIBLE);
                        btn_del.setVisibility(View.VISIBLE);
                        listview.clearChoices();
                        selected_item_textview.setText("Number");
                        listview.setAdapter(adapter2);
                        break;
                    case R.id.addnum:
                        String adding_name = add_name.getText().toString();
                        String adding_num = add_num.getText().toString();
                        if (!adding_name.matches("") && !adding_num.matches("")) {
                            teltext.setVisibility(View.VISIBLE);
                            selected_item_textview.setVisibility(View.VISIBLE);
                            add_name.setVisibility(View.GONE);
                            add_num.setVisibility(View.GONE);
                            btn_add_num.setVisibility(View.GONE);
                            btn_cancel.setVisibility(View.GONE);
                            btn_del.setVisibility(View.GONE);
                            com.example.user.project1.AActivity.this.map.put(adding_name, adding_num);
                            com.example.user.project1.AActivity.this.list.add(adding_name);
                            Collections.sort(list);
                            add_name.setText("");
                            add_num.setText("");
                            listview.clearChoices();
                            listview.setAdapter(adapter);
                        } else {
                            Toast.makeText(com.example.user.project1.AActivity.this, "이름 또는 전화번호를 채워 주세요", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.cancel_btn:
                        teltext.setVisibility(View.VISIBLE);
                        selected_item_textview.setVisibility(View.VISIBLE);
                        add_name.setVisibility(View.GONE);
                        add_num.setVisibility(View.GONE);
                        btn_add_num.setVisibility(View.GONE);
                        btn_cancel.setVisibility(View.GONE);
                        btn_del.setVisibility(View.GONE);
                        listview.clearChoices();
                        add_name.setText("");
                        add_num.setText("");
                        selected_item_textview.setText("Number");
                        listview.setAdapter(adapter);
                        break;
                    case R.id.del_btn:
                        SparseBooleanArray sb = listview.getCheckedItemPositions();
                        if(sb.size() != 0) {
                            for (int i = listview.getCount() - 1; i >=0 ; i--) {
                                if (sb.get(i)) {
                                    com.example.user.project1.AActivity.this.map.remove(list.get(i));
                                    com.example.user.project1.AActivity.this.list.remove(i);
                                }
                            }
                            listview.clearChoices();
                            com.example.user.project1.AActivity.this.adapter2.notifyDataSetChanged();
                        }
                        break;
                }
            }
        };
        btn_add_window.setOnClickListener(listener);
        btn_add_num.setOnClickListener(listener);
        btn_cancel.setOnClickListener(listener);
        btn_del.setOnClickListener(listener);

        //데이터를 저장하게 되는 리스트
        if(savedInstanceState == null) {
            this.map = new HashMap<String, String>();
            map.put("민준", "010-1111-1111");
            map.put("서준", "010-2222-2222");
            map.put("서연", "010-3333-3333");
            map.put("서현", "010-4444-4444");
            map.put("주원", "010-5555-5555");
            map.put("지우", "010-6666-6666");
            map.put("지우2", "010-7777-7777");
            map.put("예준", "010-8888-8888");
            map.put("도윤", "010-9999-9999");
            map.put("시우", "010-0000-0000");
            map.put("준서", "010-1357-9111");
            map.put("다은", "010-9876-5432");
            map.put("수아", "010-1234-5678");
            map.put("하은", "010-1123-5813");
            map.put("준우", "010-2468-1012");
            this.list = new ArrayList<String>(map.keySet());
            Collections.sort(list);
        } else {
            this.map = (HashMap<String, String>) savedInstanceState.getSerializable(map_data);
            this.list = new ArrayList<String>(map.keySet());
            Collections.sort(list);
        }


        //리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
         //       android.R.layout.simple_list_item_1, list);
        this.adapter = new ArrayAdapter<>( this,
                android.R.layout.simple_list_item_1, list);
        this.adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, list);

        //리스트뷰의 어댑터를 지정해준다.
        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        //리스트뷰의 아이템을 클릭시 해당 아이템의 문자열을 가져오기 위한 처리
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                //클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getItemAtPosition(position);

                //텍스트뷰에 출력
                selected_item_textview.setText(map.get(selected_item));
            }
        });
    }







        //리스트뷰에 보여질 아이템을 추가

}
