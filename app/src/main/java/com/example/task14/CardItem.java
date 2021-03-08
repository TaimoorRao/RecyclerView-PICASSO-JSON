package com.example.task14;

public class CardItem {
    private String mImageURL;
    private String mCreator;
    private int mLikes;

    public CardItem(String imageURL, String creator, int likes) {
        this.mImageURL = imageURL;
        this.mCreator = creator;
        this.mLikes = likes;
    }

    public String getCreator() {
        return mCreator;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public int getLikes() {
        return mLikes;
    }
}
