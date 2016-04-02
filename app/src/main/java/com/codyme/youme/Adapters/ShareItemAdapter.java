package com.codyme.youme.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codyme.youme.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by cody on 2016/3/16.
 */
public class ShareItemAdapter extends ItemAdapter {
    private final String TAG = "ShareItemAdapter";

    public ShareItemAdapter(Context context) {
        super(context);
    }

    public View initItem (JSONObject info) {
        View item = mInflater.inflate(R.layout.item_share, null);

        /**
         * 加载用户头像
         */
        try {
            ((ImageView)item.findViewById(R.id.item_share_head))
                    .setImageBitmap(BitmapFactory.decodeStream(
                            mContext.getAssets().open(info.getString("head"))
                    ));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * 加载用户昵称
         */
        try {
            ((TextView)item.findViewById(R.id.item_share_nickname)).setText(info.getString("nickname"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * 加载本条分享的时间及地点
         */
        try {
            ((TextView)item.findViewById(R.id.item_share_location)).setText(
                    info.getString("time")+"    "+info.getString("location")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * 加载分享内容的文本部分
         */
        try {
            ((TextView)item.findViewById(R.id.item_share_text)).setText(info.getString("text"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * 加载分享内容的图片部分
         * 最多加载三张图片
         */
        JSONArray imageList = null;
        try {
            imageList = info.getJSONArray("images");
            if (imageList != null){
                item.findViewById(R.id.item_share_image_list).setVisibility(View.VISIBLE);
                ImageView image[] = new ImageView[3];
                image[0] = (ImageView) item.findViewById(R.id.item_share_image_1);
                image[1] = (ImageView) item.findViewById(R.id.item_share_image_2);
                image[2] = (ImageView) item.findViewById(R.id.item_share_image_3);
                for (int i = 0; i < imageList.length() && i < 3; i ++){
                    image[i].setImageBitmap(
                            BitmapFactory.decodeStream(
                                    mContext.getAssets().open(
                                            imageList.getJSONObject(i).getString("image")
                                    )
                            )
                    );
                    image[i].setVisibility(View.VISIBLE);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 加载点赞情况
         * 点赞数量大于0且小于等于3时加载全部点赞者昵称
         * 大于3时只显示前3者昵称
         */
        try {
            int thumb_up_count = info.getInt("thumb_up_count");

            if (thumb_up_count > 0){
                TextView thumb_ups = (TextView) item.findViewById(R.id.item_share_thumb_up);
                JSONArray thumb_up_list = info.getJSONArray("thumb_ups");
                String htmlString = "";

                for (int i = 0; i < thumb_up_list.length(); i ++){
                    htmlString = htmlString
                            + "<b><font size=\"3\" color=\"#2288dd\">"
                            + thumb_up_list.getJSONObject(i).getString("nickname") + "</font></b>";
                    if (i < thumb_up_list.length() - 1){
                        htmlString = htmlString + "<font size=\"3\" color=\"#000000\">、</font>";
                    }
                }
                if (thumb_up_count > 3){
                    htmlString = htmlString
                            + "<font size=\"3\" color=\"#000000\">等"
                            + thumb_up_count
                            + "人</font>";
                }
                htmlString = htmlString + "<font size=\"3\" color=\"#000000\">觉得很赞</font>";
                thumb_ups.setVisibility(View.VISIBLE);
                item.findViewById(R.id.item_share_thumb_up_divider).setVisibility(View.VISIBLE);
                thumb_ups.setText(Html.fromHtml(htmlString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * 加载回复内容
         */
        try {
            int comment_count = info.getInt("comment_count");
            if (comment_count > 0){
                TextView comments = (TextView) item.findViewById(R.id.item_share_comment);
                JSONArray comment_list = info.getJSONArray("comments");
                String htmlString = "";

                for (int i = 0; i < comment_list.length(); i ++){
                    JSONObject comment = comment_list.getJSONObject(i);
                    htmlString = htmlString
                            + "<b><font size=\"3\" color=\"#2288dd\">"
                            + comment.getString("nickname")
                            + "</font></b>";
                    if (!comment.get("to").equals("")){
                        htmlString = htmlString
                                + "<font size=\"3\" color=\"#000000\">回复</font>"
                                + "<b><font size=\"3\" color=\"#2288dd\">"
                                + comment.getString("to")
                                + "</font></b>";
                    }
                    htmlString = htmlString
                            + "<font size=\"3\" color=\"#000000\">："
                            + comment.getString("text")
                            + "</font>";
                    if (i < comment_list.length() - 1){
                        htmlString += "<br>";
                    }
                }
                if (comment_count > 5){
                    htmlString = htmlString
                            + "<br><font size=\"3\" color=\"#000000\">......共"
                            + comment_count
                            + "条评论</font>";
                }
                comments.setVisibility(View.VISIBLE);
                comments.setText(Html.fromHtml(htmlString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return item;
    }
}
