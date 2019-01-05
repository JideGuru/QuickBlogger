package com.jideguru.quickblogger.Blogs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jideguru.quickblogger.Blogs.Models.RootObject;
import com.jideguru.quickblogger.Interface.ItemClickListener;
import com.jideguru.quickblogger.MainScreen.MainActivity;
import com.jideguru.quickblogger.R;


/**
 * Created by jideguru on 10/09/2018.
 */


class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView nameView, descView, urlView, postsView;

    private ItemClickListener itemClickListener;

    public FeedViewHolder(View itemView) {
        super(itemView);

        nameView = (TextView) itemView.findViewById(R.id.blog_name);
        //contentView = (HtmlTextView) itemView.findViewById(R.id.row_content);
        descView = (TextView) itemView.findViewById(R.id.blog_desc);
        urlView = (TextView) itemView.findViewById(R.id.blog_url);
        postsView = (TextView) itemView.findViewById(R.id.blog_posts);


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

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{

    private RootObject rootObject;
    private Context mContext;
    private LayoutInflater inflater;


    public FeedAdapter(RootObject rootObject, Context mContext) {
        this.rootObject = rootObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row_blog,parent,false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.nameView
                .setText(rootObject
                        .getItems()
                        .get(position)
                        .getName());

        /*holder.contentView
                .setHtml(rootObject
                        .getItems()
                        .get(position)
                        .getContent(),
                        new HtmlHttpImageGetter(holder.contentView));*/

        holder.descView
                .setText(rootObject
                        .getItems()
                        .get(position)
                        .getDescription());

        holder.urlView
                .setText(rootObject
                        .getItems()
                        .get(position)
                        .getUrl());

        holder.postsView
                .setText(rootObject
                        .getItems()
                        .get(position)
                        .getPosts()
                        .getTotalItems());


        String postsLink = rootObject.getItems().get(position).getPosts().selfLink;
        Log.i("Selflink", postsLink);
        /*Intent pLink = new Intent(mContext, MainActivity.class);
        pLink.putExtra("postLink", postsLink);
        mContext.startActivity(pLink);*/


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                    Intent intent = new Intent (mContext, MainActivity.class);
                    intent.putExtra("blogId", rootObject.getItems().get(position).id);
                    // browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rootObject.getItems().get(position).getUrl()));
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return rootObject.items.size();
    }
}
