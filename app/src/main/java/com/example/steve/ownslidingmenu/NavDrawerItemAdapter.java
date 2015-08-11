package com.example.steve.ownslidingmenu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steve.ownslidingmenu.DataItem.Item;
import com.example.steve.ownslidingmenu.DataItem.SectionItem;

import java.util.ArrayList;

/**
 * Created by Steve on 8/9/2015.
 */
public class NavDrawerItemAdapter extends BaseAdapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private Context context;
    private ArrayList<Item> navDrawerItems;
    LayoutInflater mInflater;

    public NavDrawerItemAdapter(Context context, ArrayList<Item> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
        mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return navDrawerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        Item item = navDrawerItems.get(i);
        if(item != null){
            if(item.isSection()){
                SectionItem na = (SectionItem)item;
                v = mInflater.inflate(R.layout.section_listview,null);
                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);
                TextView tv = (TextView)v.findViewById(R.id.section_listview_text);
                tv.setText(na.getTitle());
            }else{
                NavDrawerItem nb = (NavDrawerItem)item;
                v = mInflater.inflate(R.layout.drawer_list_item,null);
                ImageView imageView = (ImageView) v.findViewById(R.id.icon);
                TextView title = (TextView) v.findViewById(R.id.title);
                imageView.setImageResource(nb.getIcon());
                title.setText(nb.getTitle());
            }
        }

        /*if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.drawer_list_item, null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView title = (TextView) view.findViewById(R.id.title);
        imageView.setImageResource(navDrawerItems.get(i).getIcon());
        title.setText(navDrawerItems.get(i).getTitle());
        return view;*/
        return v;
    }
}
/*public class NavDrawerItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerItemAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return navDrawerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

   // @Override
    public View getView1(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.drawer_list_item,null);
        }
        ImageView imgIcon = (ImageView)view.findViewById(R.id.icon);
        TextView title = (TextView)view.findViewById(R.id.title);
        imgIcon.setImageResource(navDrawerItems.get(i).getIcon());
        title.setText(navDrawerItems.get(i).getTitle());
        return view;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.drawer_list_item,null);
        }
        ImageView imageView = (ImageView)view.findViewById(R.id.icon);
        TextView title = (TextView)view.findViewById(R.id.title);
        imageView.setImageResource(navDrawerItems.get(i).getIcon());
        title.setText(navDrawerItems.get(i).getTitle());
        return view;
    }
}*/
