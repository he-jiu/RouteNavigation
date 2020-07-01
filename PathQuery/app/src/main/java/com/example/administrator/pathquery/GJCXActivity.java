package com.example.administrator.pathquery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.pathquery.DAO.BusRepo;
import com.example.administrator.pathquery.DAO.PathQuery;
import com.example.administrator.pathquery.DAO.SQLiteHelper;
import com.example.administrator.pathquery.DAO.StationRepo;
import com.example.administrator.pathquery.DAO.TranceposRepo;
import com.example.administrator.pathquery.DAO.addBusStation;
import com.example.administrator.pathquery.Entity.Bus;
import com.example.administrator.pathquery.Entity.BusNumber;
import com.example.administrator.pathquery.Entity.Station;
import com.example.administrator.pathquery.Entity.Trancepos;
import com.example.administrator.pathquery.ListView.cccx_data;
import com.example.administrator.pathquery.ListView.czcx_data;
import com.example.administrator.pathquery.ListView.zzcx_data;
import com.example.administrator.pathquery.Utils.MyImageLoader;
import com.example.administrator.pathquery.Utils.cccxadapter;
import com.example.administrator.pathquery.Utils.czcxadapter;
import com.example.administrator.pathquery.Utils.zzcxadapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;


import java.util.ArrayList;
import java.util.List;



public class GJCXActivity extends Activity implements OnBannerListener
{
    String[][] msg;
    private Banner mBanner;
    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;
    private SQLiteHelper db;
    //计时器
    private long baseTimer;
    private int time=0;

    private int welcome2=0;//欢迎界面2
    private int home=1;//首页
    private int about=2;//关于
    private int help=3;//帮助
    //private int

    private ListView zzcx_lv;
    private List<zzcx_data> nList = new ArrayList<>();
    private zzcx_data datazz;
    private zzcxadapter adapterzz;


    private ListView cccx_lv;
    private List<cccx_data> mList = new ArrayList<>();
    private cccx_data datacc;
    private cccxadapter adaptercc;

    private ListView czcx_lv;
    private List<czcx_data> bList = new ArrayList<>();
    private czcx_data datacz;
    private czcxadapter adaptercz;

    private List<Bus> busList =new ArrayList<>();
    private List<BusNumber> busnumberList = new ArrayList<>();
    private List<Station> stationlist = new ArrayList<>();
    private List<Station> zzzstation = new ArrayList<>();

    Handler handler=new Handler()//声明消息处理器
    {
        @Override
        public void handleMessage(Message msg)//重写方法
        {

            //欢迎界面跳转计时
            if(time <=6){
                if(0==GJCXActivity .this.baseTimer ){
                    GJCXActivity.this.baseTimer=SystemClock.elapsedRealtime();
                }

                int time=(int)((SystemClock.elapsedRealtime() -GJCXActivity .this.baseTimer)/1000);
                if(time == 3){
                    Message message1= new Message();
                    message1.what=welcome2 ;
                    sendMessage(message1);
                }
                if(time ==6){
                    Message message2=new Message();
                    message2.what=home;
                    sendMessage(message2);
                }
            }



            switch(msg.what)
            {
                case 0://进入欢迎界面2

                    setContentView(R.layout.welcome2);
                    handler.sendEmptyMessageDelayed(home,3000) ;
                    break;
                //setContentView(R.layout.welcome2);
                case 1://进入首页
                    goToMainMenu();

                   /* new Thread(){
                        public void run(){
                            //跳转关于界面
                            about_btn.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View view) {
                                    Message message3=new Message();
                                    message3 .what =about;
                                    handler.sendMessage(message3) ;

                                }
                            }) ;
                            help_btn.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View view) {
                                    Message message4= new Message();
                                    message4 .what =help;
                                    handler.sendMessage(message4) ;

                                }
                            }) ;

                        }

                    }.start();*/

                    break;

                case 2://进入关于界面
                    goToAboutView();
                    //curr=WhichView.ABOUT_VIEW;
                    break;
                case 3://进入帮助界面
                    goToHelpView();
                    //curr=WhichView.ABOUT_VIEW;
                    break;
                case 4://进入首页
                    goToMainMenu();
                    //setContentView(R.layout.activity_main);

                    break;
                case 5://进入首页
                    goToMainMenu();
                    //goToMainMenu();
                    break;
                case 6://进入关于界面
                    goToAboutView();
                    //curr=WhichView.ABOUT_VIEW;
                    break;
                case 7://进入帮助界面
                    goToHelpView();
                    //curr=WhichView.HELP_VIEW;
                    break;

            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome1);
        handler.sendEmptyMessageDelayed(welcome2,3000) ;
        db = new SQLiteHelper(this);
      //  iniTLisit();//初始化数组
        initBannerData();
        this.handler.sendEmptyMessage(0);						//发送消息进入欢迎界面
    }
    public void initBannerData(){
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.ad1);
        imagePath.add(R.drawable.ad2);
        imagePath.add(R.drawable.ad3);
        imagePath.add(R.drawable.ad4);
        imagePath.add(R.drawable.ad5);
        imageTitle.add("是兄弟就来砍我1");
        imageTitle.add("是兄弟就来砍我2");
        imageTitle.add("是兄弟就来砍我3");
        imageTitle.add("是兄弟就来砍我4");
        imageTitle.add("是兄弟就来砍我5");
    }
    private void initBannerView() {
        mMyImageLoader = new MyImageLoader();
        mBanner = findViewById(R.id.banner);
        //设置样式，里面有很多种样式可以自己都看看效果
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        mBanner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        mBanner.setImages(imagePath)
                //轮播图的监听
                .setOnBannerListener(this)
                //开始调用的方法，启动轮播图。
                .start();

    }
    public void initdhBar(){
        ImageButton dhzzcx=(ImageButton)findViewById(R.id.dhzz_button);
        ImageButton dhczcx=(ImageButton)findViewById(R.id.dhcz_button);
        ImageButton dhmenu=(ImageButton)findViewById(R.id.dhmenu_button);
        ImageButton dhcccx=(ImageButton)findViewById(R.id.dhcc_button);
        ImageButton dhtj=(ImageButton)findViewById((R.id.dhtj_button));
        ImageButton guanyu=(ImageButton)findViewById((R.id.gotogy));
        ImageButton help=(ImageButton)findViewById(R.id.gotohelp);
        dhzzcx.setScaleType(ImageView.ScaleType.FIT_CENTER);
        dhczcx.setScaleType(ImageView.ScaleType.FIT_CENTER);
        dhmenu.setScaleType(ImageView.ScaleType.FIT_CENTER);
        dhcccx.setScaleType(ImageView.ScaleType.FIT_CENTER);
        guanyu.setScaleType(ImageView.ScaleType.FIT_CENTER);
        dhtj.setScaleType(ImageView.ScaleType.FIT_CENTER);
        help.setScaleType(ImageView.ScaleType.FIT_CENTER);
        guanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAboutView();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHelpView();
            }
        });
        dhzzcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTozzcxView();
            }
        });
        dhczcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToczcccxView();
            }
        });
        dhmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainMenu();
            }
        });
        dhcccx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTocccxView();
            }
        });
        dhtj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToaddModleChooseView();
            }
        });
    }
    public void initDB(){
        db = new SQLiteHelper(this);
       // SQLiteStudioService.instance().start(this);

        //添加数据
        StationRepo sp=new StationRepo(this);
        Station s=new Station("bejing","bej");
        Station t=new Station("shanghai","shai");
        Station r=new Station("yujiang","yj");
        sp.insert(s);
        sp.insert(t);
        sp.insert(r);

        BusRepo bu=new BusRepo(this);
        Bus b=new Bus("111","s","bejing","shanghai");
        Bus c=new Bus("112","r","yujiang","shanghai");
        bu.insert(b);
        bu.insert(c);

        TranceposRepo tr=new TranceposRepo(this);
        Trancepos too=new Trancepos("111","bejing","5:10","5:20");
        Trancepos tooo=new Trancepos("112","shanghai","7:50","None");
        Trancepos toooo=new Trancepos("112","bejing","6:50","7:00");
        tr.insert(too);
        tr.insert(tooo);
        tr.insert(toooo);
    }
    /**
     * 轮播图的监听
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }


    public void goToMainMenu()//去主菜单
    {
        setContentView(R.layout.activity_main);
        initBannerView();
        initdhBar();

       /* Button zzcx=(Button)findViewById(R.id.zzcx);
        Button cccx=(Button)findViewById(R.id.cccx);
        Button czcx=(Button)findViewById(R.id.czcx);
        Button cctj=(Button)findViewById(R.id.cctj);
        Button cztj=(Button)findViewById(R.id.cztj);
        Button gxtj=(Button)findViewById(R.id.gxtj);
        zzcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTozzcxView();
            }
        });
        cccx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTocccxView();
            }
        });
        czcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToczcccxView();
            }
        });
        cctj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTocctjView();
            }
        });
        cztj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goTocztjView();
            }
        });
        gxtj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTogxtjView();
            }
        });*/

    }
    public void goTozzcxView()//去站站查询
    {
        setContentView(R.layout.zzcx);
        initdhBar();
        List<String> station=new ArrayList<String>();
        PathQuery pq=new PathQuery(this);
        ArrayList<Station> sta= pq.AllStation();
        for(int i=0;i<sta.size();i++){
            station.add(sta.get(i).stationName);
        }
        ImageButton query=(ImageButton)findViewById(R.id.queryButton);
        ImageButton back=(ImageButton)findViewById(R.id.backButton);
        query.setScaleType(ImageView.ScaleType.FIT_CENTER);
        back.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ImageButton dhzzcx=findViewById(R.id.dhzz_button);
        dhzzcx.setImageDrawable(getResources().getDrawable(R.drawable.zzquerybutton2));
        final AutoCompleteTextView start=(AutoCompleteTextView)findViewById(R.id.start);
        final AutoCompleteTextView mid=(AutoCompleteTextView)findViewById(R.id.mid);
        final AutoCompleteTextView end=(AutoCompleteTextView)findViewById(R.id.end);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(GJCXActivity.
                this, android.R.layout.simple_dropdown_item_1line, station);
        start.setAdapter(adapter);
        mid.setAdapter(adapter);
        end.setAdapter(adapter);
        final CheckBox zzz=(CheckBox)findViewById(R.id.zzzbox);
        initBannerView();

        start.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(start.getText().toString().equals("请输入起始站")){
                        start.setText("");
                    }
                }else{
                    if(start.getText().toString().equals("")){
                        start.setText("请输入起始站");
                    }
                }
            }
        });

        mid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(mid.getText().toString().equals("请输入中转站")){
                        mid.setText("");
                    }
                }else{
                    if(mid.getText().toString().equals("")){
                        mid.setText("请输入中转站");
                    }
                }
            }
        });

        end.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(end.getText().toString().equals("请输入终点站")){
                        end.setText("");
                    }
                }else{
                    if(end.getText().toString().equals("")){
                        end.setText("请输入终点站");
                    }
                }
            }
        });

        zzz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast toast=Toast.makeText(GJCXActivity.this,"checked",Toast.LENGTH_SHORT    );
                    toast.show();
                } else {
                    Toast toast=Toast.makeText(GJCXActivity.this,"no checked",Toast.LENGTH_SHORT    );
                    toast.show();
                }
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start_text=start.getText().toString();
                String mid_text=mid.getText().toString();
                String end_text=end.getText().toString();

                if(zzz.isChecked()){
                    //zzzcx
                }else{
                    gotozzcxitemview(start_text,end_text);
                }
                //此处条件为是否有查询结果
                if(true) {
                }else{
                    Toast toast=Toast.makeText(GJCXActivity.this,"没有对应的查询结果",Toast.LENGTH_SHORT    );
                    toast.show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainMenu();
            }
        });


    }
    public void goTocccxView()//去车次查询界面
    {
        setContentView(R.layout.cccx);
        initdhBar();
        ImageButton dhcccx=findViewById(R.id.dhcc_button);
        dhcccx.setImageDrawable(getResources().getDrawable(R.drawable.ccquerybutton2));
        final String[] checi=new String[]{"661","662","102","103","105","613","513",};
        ImageButton query=(ImageButton)findViewById(R.id.queryButton);
        ImageButton back=(ImageButton)findViewById(R.id.backButton);
        final AutoCompleteTextView checiT=(AutoCompleteTextView)findViewById(R.id.checi);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(GJCXActivity.
                this, android.R.layout.simple_dropdown_item_1line,checi);
        checiT.setAdapter(adapter);
        initBannerView();

        checiT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(checiT.getText().toString().equals("请输入车次")){
                        checiT.setText("");
                    }
                }else{
                    if(checiT.getText().toString().equals("")){
                        checiT.setText("请输入车次");
                    }
                }
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checi_text=checiT.getText().toString();
                gotocccxitemview(checi_text);
                //此处条件为是否有查询结果
                if(true) {
                }else{
                    Toast toast=Toast.makeText(GJCXActivity.this,"没有对应的查询结果",Toast.LENGTH_SHORT    );
                    toast.show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainMenu();
            }
        });
    }
    public void goToczcccxView()//去车站所有车次查询
    {
        setContentView(R.layout.czcccx);
        initdhBar();
        ImageButton dhczcx=findViewById(R.id.dhcz_button);
        dhczcx.setImageDrawable(getResources().getDrawable(R.drawable.czquerybutton2));
        final String[] station=new String[]{"北京","天津","北京2","北京3","天津2","北京4","北京5",};
        ImageButton query=(ImageButton)findViewById(R.id.queryButton);
        ImageButton back=(ImageButton)findViewById(R.id.backButton);
        final AutoCompleteTextView chezhan=(AutoCompleteTextView)findViewById(R.id.cz_input);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(GJCXActivity.
                this, android.R.layout.simple_dropdown_item_1line,station);
        chezhan.setAdapter(adapter);
        initBannerView();

        chezhan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(chezhan.getText().toString().equals("请输入车站")){
                        chezhan.setText("");
                    }
                }else{
                    if(chezhan.getText().toString().equals("")){
                        chezhan.setText("请输入车次");
                    }
                }
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chezhan_text=chezhan.getText().toString();
                gotoczcxitemview(chezhan_text);
                //此处条件为是否有查询结果
                if(true) {
                    goToListView(msg);
                }else{
                    Toast toast=Toast.makeText(GJCXActivity.this,"没有对应的查询结果",Toast.LENGTH_SHORT    );
                    toast.show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainMenu();
            }
        });

    }
    public void goTofjgnView()//去附加功能界面
    {

    }
    public void goToaddModleChooseView()//去选择添加界面
    {
        setContentView(R.layout.activity_add_model_choose);
        initdhBar();

        ImageButton BusAddButton = (ImageButton) findViewById(R.id.BusAdd);
        ImageButton StationAddButton = (ImageButton) findViewById(R.id.StationAdd);
        ImageButton BusStationRelationAddButton = (ImageButton) findViewById(R.id.BusStationRelationAdd);
        ImageButton BackButton = (ImageButton)findViewById(R.id.backButton);

        BackButton.setOnClickListener(new View.OnClickListener() {//返回主界面
            @Override
            public void onClick(View v) {
                //setContentView(R.layout);
            }
        });

        BusAddButton.setOnClickListener(new View.OnClickListener() {//添加车次
            @Override

            public void onClick(View v) {
                goTocctjView();
            }
        });

        StationAddButton.setOnClickListener(new View.OnClickListener() {//添加车站
            @Override
            public void onClick(View v) {
                goTocztjView();
            }
        });

        BusStationRelationAddButton.setOnClickListener(new View.OnClickListener() {//添加车次车站关系
            @Override
            public void onClick(View v) {
                goTogxtjView();
            }
        });
        BackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToMainMenu();
            }
        });

    }


    public void goTocctjView()//去车次添加界面
    {
        setContentView(R.layout.activity_bus_add);


        initBannerView();
        initdhBar();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        ImageButton BackButton =(ImageButton)findViewById(R.id.backButton);
        final EditText BusName=(EditText)findViewById(R.id.BusName);
        final EditText BusType=(EditText)findViewById(R.id.BusType);
        final EditText StartStation=(EditText)findViewById(R.id.StartStation);
        final EditText EndStation=(EditText)findViewById(R.id.EndStation);

        Button submitButton = (Button)findViewById(R.id.confirm);

        BackButton.setOnClickListener(new View.OnClickListener() {//跳回主界面
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_main);
                goToMainMenu();

            }

        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String TheBusName=BusName.getText().toString();
                String TheBusType=BusType.getText().toString();
                String TheStartStation=StartStation.getText().toString();
                String TheEndStation=EndStation.getText().toString();
                //System.out.println(TheBusMessage+" "+TheBusType+" "+TheStartStation+" "+TheEndStation);



                Bus Bus=new Bus(TheBusName,TheBusType,TheStartStation,TheEndStation);
                BusRepo BusRepo=new BusRepo(GJCXActivity.this);
                if (BusRepo.insert(Bus)){
                    alertDialog.setMessage("添加成功");
                }
                else{
                    alertDialog.setMessage("车站重复");
                }
                alertDialog.show();


            }
        });
    }


    public void goTocztjView()//去车站添加界面
    {

        setContentView(R.layout.activity_station_add);

        initBannerView();
        initdhBar();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        ImageButton BackButton =(ImageButton)findViewById(R.id.backButton);
        Button submit=(Button)findViewById(R.id.submit);

        final EditText StationName=(EditText)findViewById(R.id.StationName);
        final EditText StationAbbreviation=(EditText)findViewById(R.id.StationAbbreviation);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String StationNameString = StationName.getText().toString();
                String StationAbbreviationString = StationAbbreviation.getText().toString();

                Station station=new Station(StationNameString,StationAbbreviationString);
                StationRepo StationRepo=new StationRepo(GJCXActivity.this);
                if(StationRepo.insert(station)){
                    alertDialog.setMessage("添加成功");
                }
                else{
                    alertDialog.setMessage("车站重复");
                }
                alertDialog.show();

            }
        });


        BackButton.setOnClickListener(new View.OnClickListener() {//跳回主界面
            @Override
            public void onClick(View v) {
                goToMainMenu();
            }
        });

    }


    public void goTogxtjView()//去关系添加界面
    {
        setContentView(R.layout.activity_bus_station_relation_add);

        initBannerView();
        initdhBar();

        ImageButton BackButton =(ImageButton)findViewById(R.id.backButton);//返回按键


        initView();


        BackButton.setOnClickListener(new View.OnClickListener() {//返回
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_main);
                goToMainMenu();
            }

        });
    }

    public void goToAboutView(){
        setContentView(R.layout.about);
        initBannerView();
        initdhBar();
        ImageButton back=findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainMenu();
            }
        });
        ImageButton about_btn=findViewById(R.id.gotogy);
        about_btn .setImageDrawable(getResources() .getDrawable(R.drawable.aboutbutton2) );

    }

    public void goToHelpView(){
        setContentView(R.layout.help);
        initBannerView();
        initdhBar();
        ImageButton back=findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainMenu();
            }
        });
        ImageButton  help_btn=findViewById(R.id.gotohelp);
        help_btn.setImageDrawable(getResources() .getDrawable(R.drawable.helpbutton2) );

    }

    private void initView(){
        Button confirm=(Button)findViewById(R.id.confirm);//确认键
        final AutoCompleteTextView BusName=(AutoCompleteTextView)findViewById(R.id.BusName);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GJCXActivity.this,addBusStation.class);
                intent.putExtra("busName",BusName.getText().toString());
                startActivity(intent);


            }

        });
    }

    public void goToListView(String[][]mssg)//去ListView界面
    {

    }
    //某列车经过的所有车站。去经过车站界面
    public void goToPassStationView(String[][]mssg)
    {

    }

    public void gotozzcxitemview(String stopA,String stopB){

        setContentView(R.layout.zzcxist);
        findzzdata(stopA,stopB);

        findzzview();
        adapterzz=new zzcxadapter(this,nList);//适配器的nlist应该更新
        zzcx_lv.setAdapter(adapterzz);
    }

    public void findzzdata(String stopA,String stopB){
        PathQuery pq1=new PathQuery(this);
        //busnumberList.clear();
        busnumberList=pq1.SSQuery(stopA,stopB);
        if(busnumberList.isEmpty())
        {
            Toast toast=Toast.makeText(GJCXActivity.this,"无法直达",Toast.LENGTH_SHORT    );
            toast.show();
            zzzstation = queryMid(stopA,stopB);
            for(int i=0;i<zzzstation.size();i++)
            {
                findzzdata(stopA,zzzstation.get(i).stationName);
                findzzdata(zzzstation.get(i).stationName,stopB);
            }
        }
        else {
            for (int i = 0; i < busnumberList.size(); i++) {
                datazz = new zzcx_data();
                datazz.setBus(busnumberList.get(i).busName);
                datazz.setStart(busnumberList.get(i).busStartStation);
                datazz.setEnd(busnumberList.get(i).busEndStation);
                datazz.setLx("公交车");
                nList.add(datazz);
            }
        }
    }

    public ArrayList<Station> queryMid(String s1,String s2){
        PathQuery query=new PathQuery(this);
        ArrayList<Station> result=new ArrayList<>();
        if(result.size()!=0){
            //可以直达
            return result;
        }else{
            ArrayList<Station> stalst=query.AllStation();
            for (int i = 0; i < stalst.size(); i++) {
                ArrayList<BusNumber> buslst1=query.SSQuery(s1,stalst.get(i).stationName);
                ArrayList<BusNumber> buslst2=query.SSQuery(s2,stalst.get(i).stationName);
                if((buslst1.size()!=0)&&(buslst2.size()!=0)){
                    result.add(stalst.get(i));
                }
            }
        }
        return result;
    }

    private void findzzview()
    {
        zzcx_lv = (ListView) findViewById(R.id.zzcx_list);
        zzcx_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置跳
                datazz = new zzcx_data();
                datazz = nList.get(position);
                gotocccxitemview(datazz.getBus());
            }
        });
    }

    public void gotocccxitemview(String bus){

        setContentView(R.layout.cccxlist);
        findccdata(bus);
        findccview();

        adaptercc=new cccxadapter(this,mList);//适配器的mlist应该更新
        cccx_lv.setAdapter(adaptercc);
    }

    public void findccdata(String bus){
        PathQuery pq2 =new PathQuery(this);
        stationlist=pq2.BusNumberQuery2(bus);
        for(int i=0;i<stationlist.size();i++)
        {
            datacc = new cccx_data();
            datacc.setStop(stationlist.get(i).stationName);
            datacc.setUp("nice");
            datacc.setDown("try");
            mList.add(datacc);
        }
    }
    public void findccview()
    {
        cccx_lv=(ListView) findViewById(R.id.cccx_list);
        cccx_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置跳转
                datacc=new cccx_data();
                datacc=mList.get(position);
                gotoczcxitemview(datacc.getStop());


            }
        });
    }
    public void gotoczcxitemview(String stop){

        setContentView(R.layout.czcxlist);
        findczdata(stop);
        findczview();
        adaptercz=new czcxadapter(this,bList);//适配器的blist应该更新
        czcx_lv.setAdapter(adaptercz);
    }

    private void initczdata(){
        //data = new zzcx_data();
        for(int i=0;i<=5;i++ )
        {
            datacz = new czcx_data();
            datacz.setBus(String.valueOf(i));
            datacz.setStart("tj");
            datacz.setEnd("sjz");
            datacz.setLx("gjc");
            bList.add(datacz);
        }
    }
    public void findczdata(String stop)
    {
        PathQuery pq3=new PathQuery(this);
        busList=pq3.StationQuery(stop);

        for(int i=0;i<busList.size();i++)
        {
            datacz = new czcx_data();
            datacz.setBus(busList.get(i).busName);
            datacz.setStart(busList.get(i).busStartStation);
            datacz.setEnd(busList.get(i).busEndStation);
            datacz.setLx(busList.get(i).busType);
            bList.add(datacz);
        }


    }
    private void findczview()
    {
        czcx_lv = (ListView) findViewById(R.id.czcx_list);
        czcx_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置跳转
                datacz = new czcx_data();
                datacz=bList.get(position);

                gotocccxitemview(datacz.getBus());

            }
        });
    }

}
