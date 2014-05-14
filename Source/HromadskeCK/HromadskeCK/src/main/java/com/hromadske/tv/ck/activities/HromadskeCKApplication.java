package com.hromadske.tv.ck.activities;

import  static com.hromadske.tv.ck.utils.SystemUtils.*;
import android.app.Application;
import android.graphics.Bitmap;

import com.hromadske.tv.ck.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Cheb on 15.05.2014.
 */
public class HromadskeCKApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader() {
        IMAGELOADER = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .showImageOnLoading(R.drawable.stub)
                .showImageOnFail(R.drawable.stub)
                .showImageForEmptyUri(R.drawable.stub)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.PNG, 75, null)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileCount(100)
                .defaultDisplayImageOptions(options)
                .build();

        IMAGELOADER.init(config);
    }
}
