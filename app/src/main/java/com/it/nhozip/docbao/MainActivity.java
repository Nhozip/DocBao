package com.it.nhozip.docbao;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.roger.catloadinglibrary.CatLoadingView;

import java.util.ArrayList;
import java.util.logging.Handler;

import adapter.AdapterItem;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import model.ItemRSS;
import model.ItemTT;
import model.Page;
import rss.ReadRSS;
import utils.Loading;


public class MainActivity extends AppCompatActivity {
    private ReadRSS readRSS = new ReadRSS();
    private  ArrayList<ItemRSS> data = new ArrayList<>();
    private AdapterItem adapter;
    private RecyclerView recyclerView;
    private String link="http://vnexpress.net/rss/tin-moi-nhat.rss";
    private FloatingActionButton fABVnExpress,fABNgoiSao;
    private FloatingActionsMenu floatingActionsMenu;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<ItemTT> list=new ArrayList<>();
    private Toolbar mToolbar;
    public static String title;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private WaveSwipeRefreshLayout  mWaveSwipeRefreshLayout;
    private ArrayList<String> namePage=new ArrayList<>();
    private Page page=new Page();
    private ListView lv;

    private Dialog progress_spinner;
    private String s=null;

    private  ArrayList<ItemRSS> arrvnXpress = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        mDrawerToggle.syncState();



        if(isOnline()){
            new Delete().from(ItemTT.class).execute();


            progress_spinner=LoadingSpinner(this);
            progress_spinner.show();
            setTitle("VnExpress");
            initImageLoader(this);
            new GetListItem(this).execute();
            close();

            mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
                @Override public void onRefresh() {
                    // Do work to refresh the list here.
                    mWaveSwipeRefreshLayout.setRefreshing(false);
                    new GetListItem(getApplication()).execute();
                }
            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    link=list.get(position).getLink();
                    progress_spinner.show();
                    title=list.get(position).getName();
                    setTitle(list.get(position).getName());
                    mDrawerLayout.closeDrawers();
                    new GetListItem(getApplication()).execute();
                    close();
                }
            });
            new GetDataVnXpress(this).execute();

        }
        else{
            Toast.makeText(this,"Chưa kết nối Internet !",Toast.LENGTH_LONG).show();
            floatingActionsMenu.setVisibility(View.INVISIBLE);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(),"Chưa kết nối Internet !",Toast.LENGTH_LONG).show();
                    mDrawerLayout.closeDrawers();
                }
            });
        }

    }
    public static Dialog LoadingSpinner(Context mContext){
        Dialog pd = new Dialog(mContext, android.R.style.Theme_Black);
        View view = LayoutInflater.from(mContext).inflate(R.layout.aux_progress, null);
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pd.getWindow().setBackgroundDrawableResource(R.color.blue_semi_transparent);
        pd.setContentView(view);
        return pd;
    }
    public void close(){
        new CountDownTimer(2500,1000){

            @Override public void onTick(long millisUntilFinished) {

            }

            @Override public void onFinish() {
                progress_spinner.dismiss();
            }
        }.start();
    }

    private void init() {
        fABVnExpress = (FloatingActionButton) findViewById(R.id.fAbVnExpress);
        fABNgoiSao = (FloatingActionButton) findViewById(R.id.fAbNgoiSAo);
        floatingActionsMenu= (FloatingActionsMenu) findViewById(R.id.fAB);
        recyclerView= (RecyclerView) findViewById(R.id.rv);
        lv= (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.app_name,
                R.string.app_name
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        list=page.getVnExpress();
        for(int i=0;i<list.size();i++){
            namePage.add(list.get(i).getName());
        }
        arrayAdapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,namePage);
        lv.setAdapter(arrayAdapter);

    }

    public void onClickPAB(View v){
        switch (v.getId()){
            case R.id.fAbVnExpress:
                list=page.getVnExpress();
                setData();
                link="http://vnexpress.net/rss/thoi-su.rss";
                setTitle("VnExpress");
                break;

            case R.id.fAbNgoiSAo:
                link="http://ngoisao.net/rss/24h.rss";
                list=page.getNgoiSao();
                setData();
                setTitle("Ngôi Sao");
                break;
            case R.id.fAbVietNamNet:
                link="http://vietnamnet.vn/rss/cong-nghe.rss";
                list=page.getVietNamNet();
                setData();
                setTitle("VietNamNet");
                break;
            case R.id.fAbTienPhong:
                link="http://www.tienphong.vn/rss/xa-hoi-2.rss";
                list=page.getTienPhong();
                setData();
                setTitle("Tiền Phong");
                break;

        }
        progress_spinner.show();
        floatingActionsMenu.collapse();
        new GetListItem(getApplicationContext()).execute();
        close();
    }

    private Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }

    public void setData(){
        namePage.clear();
        for(int i=0;i<list.size();i++){
            namePage.add(list.get(i).getName());
        }
        arrayAdapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,namePage);
        lv.setAdapter(arrayAdapter);

    }



    class GetListItem extends AsyncTask<ArrayList<ItemRSS>, Integer, ArrayList<ItemRSS>> {

        Context context;

        public GetListItem(Context context) {
            this.context = context;

        }

        @Override
        protected ArrayList<ItemRSS> doInBackground(ArrayList<ItemRSS>... params) {
            data = readRSS.getData(link);
            s=readRSS.getDataFromURL("http://mapi.qlcl.edu.vn/finalscore/0941360227");
            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemRSS> itemRSSes) {
            super.onPostExecute(itemRSSes);

            recyclerView.setHasFixedSize(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new AdapterItem(context, data);
            recyclerView.setAdapter(adapter);
            Log.e("Data", itemRSSes.toString());
            Log.e("Data", s.toString());

        }
    }
    class GetDataVnXpress extends AsyncTask<ArrayList<ItemRSS>, Integer, ArrayList<ItemRSS>> {

        Context context;

        public GetDataVnXpress(Context context) {
            this.context = context;

        }

        @Override
        protected ArrayList<ItemRSS> doInBackground(ArrayList<ItemRSS>... params) {


            arrvnXpress=readRSS.getData("http://vnexpress.net/rss/tin-moi-nhat.rss");
            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemRSS> itemRSSes) {
            super.onPostExecute(itemRSSes);

            for(int i=0;i<arrvnXpress.size();i++){
                ItemTT itemTT=new ItemTT(arrvnXpress.get(i).get_title(),arrvnXpress.get(i).get_link());
                itemTT.save();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new GetDataVnXpress(this).execute();
    }

    public static void initImageLoader(Context context) {

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }


}

