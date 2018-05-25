# AndroidBannerView
Android 无限循环轮播图。

### 本应用的示例

![image](https://github.com/iWay7/AndroidBannerView/blob/master/sample.gif)   

### 本示例基于 AndroidHelpers/BitmapCache 库，访问 https://github.com/iWay7/AndroidBitmapCache 添加依赖。

#### 使用方法：
##### 在布局文件中声明一个 BannerView 视图：
```
<site.iway.androidhelpers.BannerView
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="220dp"
    app:autoNextTime="3000"
    app:willPreloadNextPage="true"
    app:clickDetectRadius="5dp"/>
```

##### 然后给这个 BannerView 设置来源和一些属性等：
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
bannerView.setAutoNextTime(...); // 设定自动下一页时间，也可在布局文件中配置
bannerView.setClickDetectRadius(...); // 设置 click 事件触摸半径，以像素为单位，也可在布局文件中配置
bannerView.setWillPreloadNext(...); // 设置是否进行预加载下一页，也可在布局文件中配置
bannerView.addRequestDisallowInterceptTouchEventViewGroup(...); // 添加 RequestDisallowInterceptTouchEvent 视图，通常用于 Banner 在 ViewPager 或者 ScrollView 内部的的时候使用
bannerView.removeRequestDisallowInterceptTouchEventViewGroup(...); // 移除 RequestDisallowInterceptTouchEvent 视图
bannerView.clearRequestDisallowInterceptTouchEventViewGroups(...); // 清空 RequestDisallowInterceptTouchEvent 视图
bannerView.setBannerIndexChangedListener(...); // 设置自动切换页面时的回调
bannerView.getCurrentIndex(); // 获取原始的 Index 值，一般用不到
bannerView.getRealIndex(); // 获取正在展示的 BitmapSource 的 Index 值
```

##### 可以在布局二五年间中配置的属性：
```
app:autoNextTime 自动下一页的时间间隔
app:willPreloadNextPage 下一页是否预加载
app:clickDetectRadius 设置 click 事件触摸半径
```