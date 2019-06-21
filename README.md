# 屏幕适配

屏幕适配常见方式
 
 * 布局适配
 
    - 避免写死控件尺寸，使用wrap_content , match_parent
    - LinerLayout      xxx:Layout_weight = "0.5"
    - RelativeLayout   xxx:Layout_centerInParent="true".....
    - ContraintLayout  xxx:Layout_contraintLeft_toLeftOf="true"...]
    - Percent-support-lib xxx:layout_withPercent="30%" Google的百分比布局容器（已过时）

 * 图片资源适配
 
   - .9图或者SVG图实现缩放
   - 备用位图匹配不同分辨率
   
 * 用户流程适配
   - 根据业务逻辑执行不同的跳转逻辑
   - 根据别名展示不同的页面 
  
* 屏幕适配自定义View
  
  - 假设UI设计师提供的图片是在720*1280上进行切图，我们应该考虑要做屏幕适配，假设要显示一个按钮，要展示在720*1280上，展示屏幕的一半，那他的宽度就是360，如果要是展示在1080*1920上，这个按钮的宽度必须是540才可以展示屏幕上一半，所以这可以用缩放比例进行适配 目标宽度（720）比上我们的真实屏幕宽度，目标高度（1280）比上我们真是高度，得到相应的比例
   
<image src="https://user-gold-cdn.xitu.io/2019/6/21/16b77d0d856babbb?w=1152&h=516&f=png&s=581018">


* 百分比布局适配

  - 自定义属性设置宽高各占父容器的百分比，拿到父容器的宽高乘以百分比得到控件view的宽高
 
<image src = "https://user-gold-cdn.xitu.io/2019/6/21/16b7820a146109cc?w=1164&h=578&f=png&s=496875">

* 修改像素密度 density，scaleDensity，densityDpi
  - density：表示像素密度 换句话说每一英寸像素的缩放比例 density = dpi / 160
  - scaleDenstiy: 表示字体的缩放比例，默认情况下和density是一致的
  - densityDpi：表示每一英寸共有多少个像素点 dpi=density*160

```
public class Density {

    private static final float  WIDTH = 320;//参考设备的宽，单位是dp 320 / 2 = 160

    private static float appDensity;//表示屏幕密度
    private static float appScaleDensity; //字体缩放比例，默认appDensity

    public static void setDensity(final Application application, Activity activity){
        //获取当前app的屏幕显示信息
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0){
            //初始化赋值操作
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;

            //添加字体变化监听回调
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体发生更改，重新对scaleDensity进行赋值
                    if (newConfig != null && newConfig.fontScale > 0){
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        //计算目标值density, scaleDensity, densityDpi
        float targetDensity = displayMetrics.widthPixels / WIDTH; // 1080 / 360 = 3.0
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);//屏幕上每一寸有160个像素点

        //替换Activity的density, scaleDensity, densityDpi
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;
    }

}
```

* 刘海屏 适配
 
  - 首先设置全屏模式
    ```
    requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
   
 - 刘海屏的几种模式
   
   ```
   WindowManager.LayoutParams params = window.getAttributes();
            /**
             *  * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT  全屏模式，内容下移，非全屏不受影响
             *  * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 允许内容去延伸进刘海区
             *  * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不允许内容延伸进刘海区
             */
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(params);
 - 追加沉浸式
   ```
            int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            int visibility = window.getDecorView().getSystemUiVisibility();
            visibility |= flags; //追加沉浸式设置
            window.getDecorView().setSystemUiVisibility(visibility);
 
 - 其他手机厂商（华为，小米，oppo，vivo）适配

    [华为厂商刘海适配地址](https://devcenter-test.huawei.com/consumer/cn/devservice/doc/50114)
    
    [小米厂商刘海适配地址](https://dev.mi.com/console/doc/detail?pId=1293)
   
    [Oppo厂商刘海适配地址](https://open.oppomobile.com/service/message/detail?id=61876)
  
    [Vivo厂商刘海适配地址](https://dev.vivo.com.cn/documentCenter/doc/103)
