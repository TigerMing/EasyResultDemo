# EasyResultDemo
哪里开始就哪里结束，让onActivityResult的处理更简单

借鉴Glide添加空Fragment监控生命周期的方式

支持
延迟处理
延迟返回
线程切换

例：
    EasyResult.with(MainActivity.this).callBack(new IEasyResultCallback() {
       @Override
       public void callBack(int requestCode, int resultCode, @Nullable Intent data) {
           Log.d("EasyResult","requestCode = " + requestCode + "----resultCode = " + resultCode + "----data = " + data.getStringExtra("Test"));
       }
    }).start(new Intent(MainActivity.this,TestActivity1.class),102);

在callBack处理onActivityResult回调的逻辑即可
目的是便于逻辑的跟踪处理

测试效果可看logcat的打印日志 tag：EasyResult
