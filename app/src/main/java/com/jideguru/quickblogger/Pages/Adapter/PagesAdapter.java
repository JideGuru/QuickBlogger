package com.jideguru.quickblogger.Pages.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jideguru.quickblogger.Interface.ItemClickListener;
import com.jideguru.quickblogger.Pages.Models.PageObject;
import com.jideguru.quickblogger.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;



class PagesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView nameView;
    public HtmlTextView contentView;

    private ItemClickListener itemClickListener;

    public PagesViewHolder(View itemView) {
        super(itemView);

        nameView = (TextView) itemView.findViewById(R.id.page_name);
        contentView = (HtmlTextView) itemView.findViewById(R.id.page_content);


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

public class PagesAdapter extends RecyclerView.Adapter<PagesViewHolder>{

    private PageObject pageObject;
    private Context mContext;
    private LayoutInflater inflater;


    public PagesAdapter(PageObject pageObject, Context mContext) {
        this.pageObject = pageObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public PagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row_pages,parent,false);
        return new PagesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PagesViewHolder holder, int position) {


        String user_name = pageObject.items.get(position).title;
        String user_comment = pageObject.items.get(position).content;
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
        return pageObject.items.size();
    }
}
