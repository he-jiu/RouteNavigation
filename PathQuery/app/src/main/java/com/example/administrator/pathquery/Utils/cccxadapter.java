package com.example.administrator.pathquery.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.pathquery.R;
import com.example.administrator.pathquery.ListView.cccx_data;

import java.util.List;

public class cccxadapter extends BaseAdapter {
    private cccx_data data;
    private Context mContext;
    private List<cccx_data> nList;
    private LayoutInflater inflater;
    private int width, height;
    private WindowManager wm;


    public cccxadapter(Context mContext, List<cccx_data> nList){
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
            convertView = inflater.inflate(R.layout.cccxitem,null);


            viewHolder.cci_stop = convertView.findViewById(R.id.cci_stop);
            viewHolder.cci_down = convertView.findViewById(R.id.cci_down);
            viewHolder.cci_up = convertView.findViewById(R.id.cci_up);

            //设置缓存
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = nList.get(position);

        //PicassoUtils.loadImageViewSize(data.getNewsImg(), width/3, 250, viewHolder.iv_news);
        viewHolder.cci_stop.setText(data.getStop());
        viewHolder.cci_down.setText(data.getDown());
        viewHolder.cci_up.setText(data.getUp());

        return convertView;

    }


    class ViewHolder{
        private TextView cci_stop;
        private TextView cci_down;
        private TextView cci_up;
    }
}