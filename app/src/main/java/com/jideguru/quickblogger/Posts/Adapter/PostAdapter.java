package com.jideguru.quickblogger.Posts.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jideguru.quickblogger.Posts.Models.PostObject;
import com.jideguru.quickblogger.Interface.ItemClickListener;
import com.jideguru.quickblogger.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import es.dmoral.toasty.Toasty;


class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView titleView, authorView, postsView;
//    public HtmlTextView contentView;
    public ImageView image;

    private ItemClickListener itemClickListener;

    public PostViewHolder(View itemView) {
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

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>{

    private PostObject postObject;
    private Context mContext;
    private LayoutInflater inflater;


    public PostAdapter(PostObject postObject, Context mContext) {
        this.postObject = postObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row_posts,parent,false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.titleView
                .setText(postObject
                        .getItems()
                        .get(position)
                        .getTitle());

//        holder.contentView
//                .setHtml(postObject
//                        .getItems()
//                        .get(position)
//                        .getContent(),
//                        new HtmlHttpImageGetter(holder.contentView));

        /*holder.contentView
                .setText(postObject
                        .getItems()
                        .get(position)
                        .getContent());*/

        String author = postObject
                .getItems()
                .get(position)
                .author
                .displayName;

        holder.authorView
                .setText("Posted by "+author);

        /*holder.postsView
                .setText(postObject
                        .getItems()
                        .get(position)
                        .getPosts()
                        .getTotalItems());*/

        try {
            String img = postObject.getItems().get(position).getContent();
            Document doc = Jsoup.parse(img);
            Element link = doc.select("img").first();
            String imgSrc = link.attr("src");

            Log.i("ImageURLS", imgSrc);
            Picasso.with(mContext)
                    .load(imgSrc)
                    .into(holder.image);
        }catch(Exception e){

            Toasty.error(mContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show();
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(postObject.getItems().get(position).getUrl()));
//                    mContext.startActivity(browserIntent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return postObject.items.size();
    }
}
