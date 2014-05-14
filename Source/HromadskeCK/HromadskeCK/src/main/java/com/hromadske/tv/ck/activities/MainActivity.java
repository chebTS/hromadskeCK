package com.hromadske.tv.ck.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.entities.Video;
import com.hromadske.tv.ck.tasks.GetVideosTask;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private ProgressBar progressBar;
    private ListView listView;
    private ShareActionProvider myShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        listView = (ListView)findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        (new GetVideosTask(this, progressBar, listView)).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        myShareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(shareItem);
        setShareIntent();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                startActivity(new Intent(this, HelpActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String videoId = ((Video)parent.getItemAtPosition(position)).getId();
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
            startActivity(intent);
        }catch (ActivityNotFoundException ex){
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+videoId));
            startActivity(intent);
        }
    }

    private void setShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text));
        myShareActionProvider.setShareIntent(intent);
    }
}
