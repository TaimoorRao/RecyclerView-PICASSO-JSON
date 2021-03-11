package com.example.task14;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    public Context mContext;
    private TestJSON[] mCardList;

//    private OnItemClickListener mListener;
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }

    public CardAdapter(Context context, TestJSON[] cardList) {
        mContext = context;
        mCardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        TestJSON hit = mCardList[position];
//        CardItem currentItem = mCardList.get(position);

        String imageURL = hit.getAvatarUrl();
        String textCreator = hit.getLogin();
        String textLikes = hit.getType();

        holder.mTextCreator.setText(textCreator);
        holder.mTextLikes.setText("Type: " + textLikes);
        Picasso.get()
                .load(imageURL)
                .fit()
                .centerInside()
                .into(holder.mImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, hit.getLogin() + " was clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCardList.length;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImage;
        public TextView mTextCreator;
        public TextView mTextLikes;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_view);
            mTextCreator = itemView.findViewById(R.id.text_view_creator);
            mTextLikes = itemView.findViewById(R.id.text_view_likes);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAbsoluteAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position);
//                        }
//                    }
//                }
//            });
        }
    }
}
