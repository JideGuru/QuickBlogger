package com.jideguru.quickblogger.Comments.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jideguru.quickblogger.Blogs.Models.BlogObject;
import com.jideguru.quickblogger.Comments.Models.CommentsObject;
import com.jideguru.quickblogger.Interface.ItemClickListener;
import com.jideguru.quickblogger.MainScreen.MainActivity;
import com.jideguru.quickblogger.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;


/**
 * Created by jideguru on 10/09/2018.
 */


class CommentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView nameView;
    public HtmlTextView contentView;

    private ItemClickListener itemClickListener;

    public CommentsViewHolder(View itemView) {
        super(itemView);

        nameView = (TextView) itemView.findViewById(R.id.comment_name);
        contentView = (HtmlTextView) itemView.findViewById(R.id.comment_content);


        //EVENT
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}

public class CommentsAdapter extends RecyclerView.Adapter<CommentsViewHolder>{

    private CommentsObject commentsObject;
    private Context mContext;
    private LayoutInflater inflater;


    public CommentsAdapter(CommentsObject commentsObject, Context mContext) {
        this.commentsObject = commentsObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row_comments,parent,false);
        return new CommentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {


        String user_name = commentsObject.items.get(position).author.displayName;
        String user_comment = commentsObject.items.get(position).content;
        holder.nameView.setText(user_name);
        holder.contentView.setHtml(user_comment);

//        holder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                if(!isLongClick){
//                    Intent intent = new Intent (mContext, MainActivity.class);
//
//
//                    mContext.startActivity(intent);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return commentsObject.items.size();
    }
}
