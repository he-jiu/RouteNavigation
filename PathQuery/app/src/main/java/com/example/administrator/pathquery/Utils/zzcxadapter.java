package com.example.administrator.pathquery.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.pathquery.R;
import com.example.administrator.pathquery.ListView.zzcx_data;

import java.util.List;

public class zzcxadapter extends BaseAdapter {
    private zzcx_data data;
    private Context mContext;
    private List<zzcx_data> nList;
    private LayoutInflater inflater;
    private int width, height;
    private WindowManager wm;


    public zzcxadapter(Context mContext, List<zzcx_data> nList){
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
            convertView = inflater.inflate(R.layout.zzcxitem,null);


            viewHolder.zzi_bus = convertView.findViewById(R.id.zzi_bus);
            viewHolder.zzi_start = convertView.findViewById(R.id.zzi_start);
            viewHolder.zzi_end = convertView.findViewById(R.id.zzi_end);
            viewHolder.zzi_lx = convertView.findViewById(R.id.zzi_lx);

            //设置缓存
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = nList.get(position);

        //PicassoUtils.loadImageViewSize(data.getNewsImg(), width/3, 250, viewHolder.iv_news);
        viewHolder.zzi_bus.setText(data.getBus());
        viewHolder.zzi_start.setText(data.getStart());
        viewHolder.zzi_end.setText(data.getEnd());
        viewHolder.zzi_lx.setText(data.getLx());
        return convertView;

    }


    class ViewHolder{
        private TextView zzi_bus;
        private TextView zzi_start;
        private TextView zzi_end;
        private TextView zzi_lx;
    }
}
