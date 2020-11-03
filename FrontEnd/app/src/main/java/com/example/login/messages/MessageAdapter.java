package com.example.login.messages;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> mData;
    private LayoutInflater mInflater;
    private String owner;


    /**
     * The data is passed into the constructor.
     *
     * @param context
     * @param data
     * @param owner
     */
    MessageAdapter(Context context, List<Message> data, String owner) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.owner = owner;
    }



    /**
     * It inflates the row layout from xml when needed.
     * @param parent
     * @param viewType
     * @return
     *  inflates the row layout from xml when needed.
     */

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.up_localmessages, parent, false);
        return new MessageAdapter.ViewHolder(view);
    }



    /**
     * It binds the data to the TextView in each row

     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {
        Message current = mData.get(position);
        String kk = current.getuNameReceiver();
        holder.user.setText(kk);
        String ms = current.getText();
        holder.messagePart.setText(ms);
        if (kk.equals(owner)) {
            holder.user.setGravity(Gravity.RIGHT);
            holder.messagePart.setGravity(Gravity.RIGHT);
        } else {
            holder.user.setGravity(Gravity.LEFT);
            holder.messagePart.setGravity(Gravity.LEFT);
        }
    }



    /**
     * Counts the total number of rows.
     * @return
     * the total number of rows.
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }




    /**
     * It stores and recycles views as they are scrolled off screen.
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user;
        TextView messagePart;

        ViewHolder(View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.uName);
            messagePart = itemView.findViewById(R.id.upMessage);
        }
    }
}
