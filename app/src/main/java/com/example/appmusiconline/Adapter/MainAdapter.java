//package com.example.appmusiconline.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager.widget.PagerAdapter;
//
//import com.example.appmusiconline.R;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//
//public class MainAdapter  extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
//
//    Context context ;
//    ArrayList<String> arr_string ;
//
//    public MainAdapter(Context context, ArrayList<String> arr_string) {
//        this.context = context;
//        this.arr_string = arr_string;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.dong_main , parent , false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//            holder.txtMain.setText(arr_string.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return arr_string.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        TextView txtMain ;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtMain = itemView.findViewById(R.id.txtMain);
//        }
//    }
//}
//
package com.example.appmusiconline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.viewpager.widget.PagerAdapter;

import com.example.appmusiconline.R;

import java.util.ArrayList;

public class MainAdapter extends PagerAdapter {
    Context context ;
    ArrayList<String> arr_string ;
    public MainAdapter(Context context, ArrayList<String> arr_string) {
        this.context = context;
        this.arr_string = arr_string;
    }
    @Override
    public int getCount() {
        return arr_string.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_main, null);
        TextView txtMain = (TextView) view.findViewById(R.id.txtMain);
        ImageView img = (ImageView) view.findViewById(R.id.imgSlash);
        txtMain.setText(arr_string.get(position));

        container.addView(view);
        return view;
    }
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

