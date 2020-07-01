package com.example.administrator.pathquery.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.pathquery.R;
import com.example.administrator.pathquery.ListView.czcx_data;

import java.util.List;

public class czcxadapter extends BaseAdapter {
    private czcx_data data;
    private Context mContext;
    private List<czcx_data> nList;
    private LayoutInflater inflater;
    private int width, height;
    private WindowManager wm;


    public czcxadapter(Context mContext, List<czcx_data> nList){
        this.mContext=mContext;
        this.nList=nList;

        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    @Override
    public int getCount() {
        return nList.size();
    }

    @Override
    public Object getItem(int position) {
        return nList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.cxcxitem,null);


            viewHolder.czi_bus = convertView.findViewById(R.id.czi_bus);
            viewHolder.czi_start = convertView.findViewById(R.id.czi_start);
            viewHolder.czi_end = convertView.findViewById(R.id.czi_end);
            viewHolder.czi_lx = convertView.findViewById(R.id.czi_lx);

            //设置缓存
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = nList.get(position);

        //PicassoUtils.loadImageViewSize(data.getNewsImg(), width/3, 250, viewHolder.iv_news);
        viewHolder.czi_bus.setText(data.getBus());
        viewHolder.czi_start.setText(data.getStart());
        viewHolder.czi_end.setText(data.getEnd());
        viewHolder.czi_lx.setText(data.getLx());
        return convertView;

    }


    class ViewHolder{
        private TextView czi_bus;
        private TextView czi_start;
        private TextView czi_end;
        private TextView czi_lx;
    }
}