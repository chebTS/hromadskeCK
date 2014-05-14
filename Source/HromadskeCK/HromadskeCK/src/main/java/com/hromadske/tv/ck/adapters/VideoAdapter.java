package com.hromadske.tv.ck.adapters;

import  static com.hromadske.tv.ck.utils.SystemUtils.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.entities.Video;

import java.util.List;

/**
 * Created by Cheb on 15.05.2014.
 */
public class VideoAdapter extends ArrayAdapter<Video>{
    private LayoutInflater inflater;
    public VideoAdapter(Context context, int resource, List<Video> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Video item = getItem(position);
        View view = inflater.inflate(R.layout.item_video, null);
        ((TextView)view.findViewById(R.id.txt_title)).setText(item.getTitle());
        IMAGELOADER.displayImage(item.getHqThumb(), (ImageView) view.findViewById(R.id.thumb));
        return view;
    }
}

