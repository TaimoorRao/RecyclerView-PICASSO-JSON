package com.example.task14;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.squareup.picasso.Picasso;

import static com.example.task14.MainActivity.EXTRA_CREATOR;
import static com.example.task14.MainActivity.EXTRA_LIKES;
import static com.example.task14.MainActivity.EXTRA_URL;

public class DetailItemDialog extends AppCompatDialogFragment {

    private ImageView mImage;
    private TextView mTextCreator;
    private TextView mTextLikes;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.detailed_item_dialog,null);

        mImage = view.findViewById(R.id.image_view_detail);
        mTextCreator = view.findViewById(R.id.text_view_creator_detail);
        mTextLikes = view.findViewById(R.id.text_view_likes_detail);

        builder.setView(view)
                .setTitle("Image Detail")
                .setNegativeButton("back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        Bundle args = getArguments();
        String imageUrl = args.getString(EXTRA_URL);
        String creatorName = args.getString(EXTRA_CREATOR);
        int likeCount = args.getInt(EXTRA_LIKES, 0);
        Picasso.get().load(imageUrl).fit().centerInside().into(mImage);
        mTextCreator.setText(creatorName);
        mTextLikes.setText("Likes: " + likeCount);
        return builder.create();
    }
}
