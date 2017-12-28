package com.example.user.project1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;

public class BActivity extends AppCompatActivity {


    public int[] picture = {
            R.drawable.jj,
            R.drawable.kk,
            R.drawable.oo,
            R.drawable.rr,
            R.drawable.tt,
            R.drawable.aa,
            R.drawable.ss,
            R.drawable.pp,
            R.drawable.ii,
            R.drawable.ee,
            R.drawable.ll,
            R.drawable.mm
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        final GridView grid = (GridView) findViewById(R.id.grid);
        final ImageAdapter Adapter = new ImageAdapter(this);
        final ImageButton imagebutton = (ImageButton) findViewById(R.id.image);
        final Button gridview = (Button) findViewById(R.id.gridview);
        final Button hori = (Button)  findViewById(R.id.Hori);
        final Gallery gal = (Gallery) findViewById(R.id.gallery1);
        final ImageView image = (ImageView) findViewById(R.id.imageView);
        grid.setAdapter(Adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                grid.setVisibility(View.GONE);
                imagebutton.setVisibility(View.VISIBLE);
                imagebutton.setImageResource(picture[i]);
                imagebutton.setScaleType(ImageButton.ScaleType.FIT_CENTER);
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.image){
                    imagebutton.setVisibility(View.GONE);
                    grid.setVisibility(View.VISIBLE);
                }
            }
        };
        imagebutton.setOnClickListener(listener);

        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.gridview:
                        grid.setVisibility(View.VISIBLE);
                        gal.setVisibility(View.GONE);
                        image.setVisibility(View.GONE);
                        break;
                    case R.id.Hori:
                        grid.setVisibility(View.GONE);
                        gal.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        imagebutton.setVisibility(View.GONE);
                        break;
                }
            }
        };
        hori.setOnClickListener(listener2);
        gridview.setOnClickListener(listener2);

        gal.setAdapter(new ImageAdapter2(this));
        gal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                image.setImageResource(picture[i]);
                image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}

class ImageAdapter extends BaseAdapter {
    private Context context;

    int[] picture = {
            R.drawable.jj,
            R.drawable.kk,
            R.drawable.oo,
            R.drawable.rr,
            R.drawable.tt,
            R.drawable.aa,
            R.drawable.ss,
            R.drawable.pp,
            R.drawable.ii,
            R.drawable.ee,
            R.drawable.ll,
            R.drawable.mm
    };

    public ImageAdapter(Context c) {
        context = c;
    }

    public int getCount() {
        return picture.length;
    }

    public Object getItem(int position) {
        return picture[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(330,200));
            imageView.setAdjustViewBounds(false);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else{
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(picture[position]);

        return imageView;
    }

}

class ImageAdapter2 extends BaseAdapter {
    private Context context;

    public int[] picture = {
            R.drawable.jj,
            R.drawable.kk,
            R.drawable.oo,
            R.drawable.rr,
            R.drawable.tt,
            R.drawable.aa,
            R.drawable.ss,
            R.drawable.pp,
            R.drawable.ii,
            R.drawable.ee,
            R.drawable.ll,
            R.drawable.mm
    };

    public ImageAdapter2(Context c) {
        context = c;
    }

    public int getCount() {
        return picture.length;
    }

    public Object getItem(int position) {
        return picture[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
        } else{
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(picture[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new Gallery.LayoutParams(300,200));

        return imageView;
    }

}
