package site.iway.androidbannerview;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import java.io.File;

import site.iway.androidhelpers.BannerView;
import site.iway.androidhelpers.BitmapSource;
import site.iway.androidhelpers.BitmapSourceURL;
import site.iway.androidhelpers.BitmapView;
import site.iway.androidhelpers.ViewProcessor;
import site.iway.javahelpers.Scale;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BitmapSource[] bitmapSources = new BitmapSource[5];
        for (int i = 0; i < bitmapSources.length; i++) {
            String url = "http://home.iway.site:8888/test/images/image%20(" + (i + 1) + ").jpg";
            bitmapSources[i] = new BitmapSourceURL(url, null);
        }

        BannerView bannerView = (BannerView) findViewById(R.id.banner);
        bannerView.initializeBitmapViews(new ViewProcessor() {
            @Override
            public void process(View view) {
                BitmapView bitmapView = (BitmapView) view;
                bitmapView.setScale(Scale.CenterCrop);
                bitmapView.setBackDrawable(new ColorDrawable(0xffc8c8c8));
                bitmapView.setUseDefaultFilter(true);
            }
        });
        bannerView.setBitmapSources(bitmapSources);
        bannerView.setAutoNextTime(3000);
        bannerView.setBannerIndexChangedListener(null);
    }
}