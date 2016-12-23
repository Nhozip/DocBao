package adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.nhozip.docbao.GotoContent;
import com.it.nhozip.docbao.MainActivity;
import com.it.nhozip.docbao.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

import model.ItemRSS;


/**
 * Created by Nhozip on 6/14/2016.
 */
public  class AdapterItem extends RecyclerView.Adapter<AdapterItem.RecyclerViewHolder> {
    ArrayList<ItemRSS> _list;
    Context _context;


    public AdapterItem(Context context, ArrayList<ItemRSS> list) {
        this._context=context;
        this._list=list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_rss, parent, false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.txtTitle.setText(_list.get(position).get_title());
        holder.txtpubDay.setText(_list.get(position).get_pubdate());
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(_list.get(position).get_img(), holder.img);

    }

    @Override
    public int getItemCount() {
        return _list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle,txtpubDay;
        private ImageView img;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtTitle= (TextView) itemView.findViewById(R.id.tvTitle);
            txtpubDay= (TextView) itemView.findViewById(R.id.tvPub);
            img= (ImageView) itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(_context, GotoContent.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //LoadingSpinner(_context).show();
                    Bundle bundle=new Bundle();
                    bundle.putString("link",_list.get(getPosition()).get_link());
                    intent.putExtra("L",bundle);
                    _context.startActivity(intent);

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


}
