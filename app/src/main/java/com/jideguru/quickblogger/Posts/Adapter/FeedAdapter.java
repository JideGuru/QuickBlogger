package com.jideguru.quickblogger.Posts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jideguru.quickblogger.Posts.Models.RootObject;
import com.jideguru.quickblogger.Interface.ItemClickListener;
import com.jideguru.quickblogger.R;


/**
 * Created by jideguru on 10/09/2018.
 */


class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView titleView, authorView, postsView;
//    public HtmlTextView contentView;
    public ImageView image;

    private ItemClickListener itemClickListener;

    public FeedViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.post_title);
//        contentView = (HtmlTextView) itemView.findViewById(R.id.post_content);
        //contentView = (TextView) itemView.findViewById(R.id.post_content);
        authorView = (TextView) itemView.findViewById(R.id.post_author);
        image = (ImageView) itemView.findViewById(R.id.post_thumbnail);
        //postsView = (TextView) itemView.findViewById(R.id.blog_posts);


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
        View itemView = inflater.inflate(R.layout.row_posts,parent,false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.titleView
                .setText(rootObject
                        .getItems()
                        .get(position)
                        .getTitle());

//        holder.contentView
//                .setHtml(rootObject
//                        .getItems()
//                        .get(position)
//                        .getContent(),
//                        new HtmlHttpImageGetter(holder.contentView));

        /*holder.contentView
                .setText(rootObject
                        .getItems()
                        .get(position)
                        .getContent());*/

        holder.authorView
                .setText(rootObject
                        .getItems()
                        .get(position)
                        .author
                        .displayName);

        /*holder.postsView
                .setText(rootObject
                        .getItems()
                        .get(position)
                        .getPosts()
                        .getTotalItems());*/

//        String img = rootObject.getItems().get(position).getContent();
//        Document doc = Jsoup.parse(img);
//        Element link = doc.select("img").first();
//        String imgSrc = link.attr("src");
//
//        Log.i("ImageURLS", imgSrc);
//        Picasso.with(mContext)
//                .load(imgSrc)
//                .into(holder.image);


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rootObject.getItems().get(position).getUrl()));
//                    mContext.startActivity(browserIntent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return rootObject.items.size();
    }
}
