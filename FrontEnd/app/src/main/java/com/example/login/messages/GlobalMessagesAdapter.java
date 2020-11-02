package com.example.login.messages;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import java.util.List;

public class GlobalMessagesAdapter extends RecyclerView.Adapter<GlobalMessagesAdapter.ViewHolder> {
    private List<Message> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private String owner;

    // data is passed into the constructor
    GlobalMessagesAdapter(Context context, List<Message> data, String owner) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.owner = owner;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.up_messages, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message current = mData.get(position);
        String kk = current.getuNameReceiver();
        if (kk.equals(owner)) {
            kk = current.getuNameSender();
        }
        holder.user.setText(kk);
        String ms = current.getText();
        if (ms.length() > 40) {
            ms = ms.substring(0, 40) + "...";
        }
        holder.messagePart.setText(ms);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView user;
        TextView messagePart;

        ViewHolder(View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.uName);
            messagePart = itemView.findViewById(R.id.upMessage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Message getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}