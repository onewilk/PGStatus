package it.wilk.pg.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sackcentury.shinebuttonlib.ShineButton;

import it.wilk.pg.PGApplication;
import it.wilk.pg.R;
import it.wilk.pg.model.StatusInfo;

/**
 * RecyclerView Adapter
 * Created by Mr.Wilk on 2016/08/12 0012.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private Context mContext;
    private boolean isOpenData;

    public MainAdapter(Context mContext, boolean isOpenData) {
        this.mContext = mContext;
        this.isOpenData = isOpenData;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mFlagIv;
        TextView mNameTv;
        TextView mStatusTv;
        ShineButton mFavBtn;

        MyViewHolder(View view) {
            super(view);
            mFlagIv = (ImageView) view.findViewById(R.id.flag_iv);
            mNameTv = (TextView) view.findViewById(R.id.name_tv);
            mStatusTv = (TextView) view.findViewById(R.id.status_tv);
            mFavBtn = (ShineButton) view.findViewById(R.id.fav_btn);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_status, parent,
                false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        position = isOpenData ? position : PGApplication.mResult.openNum + position;
        StatusInfo info = PGApplication.mResult.dataList.get(position);
        Glide.with(mContext).load(info.flagUrl).into(holder.mFlagIv);
        holder.mNameTv.setText(info.name);
        holder.mStatusTv.setText(info.isOpen ? "ONLINE" : "OFFLINE");
        holder.mStatusTv.setTextColor(info.isOpen ? Color.GREEN : Color.RED);
        holder.mFavBtn.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return isOpenData ? PGApplication.mResult.openNum : PGApplication.mResult.closeNum;
    }
}
