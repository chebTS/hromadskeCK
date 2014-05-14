package com.hromadske.tv.ck.tasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.adapters.VideoAdapter;
import com.hromadske.tv.ck.entities.Video;

import static com.hromadske.tv.ck.utils.Constants.*;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Cheb on 15.05.2014.
 */
public class GetVideosTask extends BaseTask {
    private ArrayList<Video> videos;
    private ListView listView;
    private final String TAG = GetVideosTask.class.getName();
    public GetVideosTask(Context context, ProgressBar progressBar, ListView listView) {
        super(context, progressBar);
        this.listView = listView;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            HttpGet get = new HttpGet(VIDEOS_URL);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(get);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                String data= EntityUtils.toString(response.getEntity());
                videos = parseJSON(new JSONObject(data));
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, getClass().toString(), e);
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result){
            for(Video v:videos){
                Log.i(TAG," "+v.getTitle());
            }
            listView.setAdapter(new VideoAdapter(context, R.layout.item_video, videos));
        }else{
            Toast.makeText(context, "Connection trouble" , Toast.LENGTH_LONG).show();
        }
    }

    private ArrayList<Video> parseJSON(JSONObject jRoot) throws JSONException {
        ArrayList<Video> videoList = new ArrayList<Video>();
        Video video;
        JSONArray jsonArray = jRoot.getJSONObject("data").getJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            JSONObject thumbs = jsonObject.optJSONObject("thumbnail");
            JSONObject player = jsonObject.optJSONObject("player");
            video = new Video(
                    jsonObject.optString("id"),
                    jsonObject.optString("title"),
                    thumbs.optString("sqDefault"),
                    thumbs.optString("hqDefault"),
                    player.optString("mobile")
            );
            videoList.add(video);
        }
        return videoList;
    }
}
