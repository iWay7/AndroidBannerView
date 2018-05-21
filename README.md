# AndroidBannerView
Android 无限循环轮播图。

### 本应用的示例

![image](https://github.com/iWay7/AndroidBannerView/blob/master/sample.gif)   

### 本示例基于 AndroidHelpers/BitmapCache 库，访问 https://github.com/iWay7/BitmapCache 添加依赖。

#### 使用方法：
##### 在布局文件中声明一个 BannerView 视图：
```
<site.iway.androidhelpers.BannerView
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="220dp" />
```

##### 然后给这个 BannerView 设置来源和自动循环时间等：
```
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
bannerView.setBitmapSources(bitmapSources); // 设定来源
bannerView.setAutoNextTime(3000); // 设定循环时间
bannerView.setBannerIndexChangedListener(...); // 设定监听器
```

##### BannerIndexChangedListener 回调 index 是原始的，可以根据以下方式计算真实数据：
```
private int computeBannerIndex(int rawIndex) {
    int offset = rawIndex % bitmapSources.length;
    if (rawIndex > 0)
        return offset;
    else if (rawIndex < 0)
        return bitmapSources.length + offset;
    else
        return 0;
}
```

##### 由于内部 index 是实时计算的，所以在布局之前完成查询当前 index 会产生错误，这种情况应单独判断：
```
if (bannerView.getWidth() > 0) {
    bannerIndex = bannerView.getCurrentIndex();
}
```