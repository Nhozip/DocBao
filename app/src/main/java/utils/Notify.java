package utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.it.nhozip.docbao.MainActivity;
import com.it.nhozip.docbao.R;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Handler;

import model.ItemRSS;
import model.ItemTT;
import rss.ReadRSS;
import rss.XMLDOMParser;

/**
 * Created by Nhozip on 6/17/2016.
 */
public class Notify extends BroadcastReceiver {
    public static final int GET_DATA = 1;
    private static final int NO_DATA =2 ;
    private ArrayList<ItemRSS> dataNew = new ArrayList<>();
    private ReadRSS readRSS = new ReadRSS();
    private Context context;

//    android.os.Handler handler=new android.os.Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if(msg.arg1==GET_DATA){
//              //  Toast.makeText(context,"CÓ tin mới",Toast.LENGTH_LONG).show();
//                new GetDataVnXpress(context,handler).execute();
//            }else if (msg.arg1==NO_DATA){
//              //Toast.makeText(context,"no data",Toast.LENGTH_LONG).show();
//            }
//
//            super.handleMessage(msg);
//        }
//    };
    @Override
    public void onReceive(final Context context, Intent intent) {
        //this.context=context;
            if(isOnline(context)){
                new GetDataVnXpress(context).execute();

            }


    }

    private Boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }

    class GetDataVnXpress extends AsyncTask<ArrayList<ItemRSS>, Integer, ArrayList<ItemRSS>> {
        Context context;
       //android.os.Handler handler;
        public GetDataVnXpress(Context context) {
            this.context = context;
          //  this.handler=handler;

        }

        @Override
        protected ArrayList<ItemRSS> doInBackground(ArrayList<ItemRSS>... params) {
            dataNew = readRSS.getData("http://vnexpress.net/rss/tin-moi-nhat.rss");

            return dataNew;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemRSS> itemRSSes) {
            super.onPostExecute(itemRSSes);
            List<ItemTT> ls = new Select().from(ItemTT.class).execute();
            ArrayList<ItemTT> dataOld = new ArrayList<>();
            dataOld.clear();
            dataOld.addAll(ls);
            Log.e("dataOld", dataOld.toString());
             Log.e("dataNew", dataNew.toString());

//            if (!itemRSSes.isEmpty()){
//                Message ms=new Message();
//                ms.obj=dataNew.toString();
//                ms.arg1=GET_DATA;
//                handler.sendMessage(ms);
//            }else{
//                Message ms=new Message();
//                ms.obj="";
//                ms.arg1=NO_DATA;
//                handler.sendMessage(ms);
//            }

            if (dataNew.get(0).get_link().compareTo(dataOld.get(0).getLink()) != 0) {
                NotificationManager notificationService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context);
                notBuilder.setSmallIcon(R.mipmap.ic_launcher);

                notBuilder.setContentTitle("Có Tin mới");
                notBuilder.setContentText("Có tin mới từ vnXpress ....");

                Intent intent = new Intent(context, MainActivity.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(context, 1,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                notBuilder.setContentIntent(pendingIntent);
                notBuilder.setAutoCancel(true);
                Notification notification = notBuilder.build();

                notificationService.notify(1, notification);
            }
        }
    }
}
