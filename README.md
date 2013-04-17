# 南邮手机报

## 编译依赖

- 第三方库 ActionBarSherlock  需要自己下载，将library目录作为Android Libraray Project导入 <http://actionbarsherlock.com/>  为低版本的系统提供ActionBar导航，提供一致的UI界面
- android.support.v4，ActionBarSherlock库和Android SDK均包含    Android SDK自带辅助包
- 极光推送SDK，已集成到libs目录下 用的第三方推送，替换原来自己写的轮询更新功能，提高软件鲁棒性，以及更友好的后台网页界面

## 前台界面

- BaseActivity AboutActivity和ScreenSlideActivity的基类，定义菜单项
- StartActivity 软件开启的闪屏界面
- NewsListActivity 往期列表
- SreenSlideActivity 内容展示的滑动界面

## 后台更新
- DownloadThread 异步下载并在完成后推送通知
- MyRecevier 接受极光推送的BroadcastReceiver，接受到后台消息是解析JSON处理
- UnzipUtils 解压
- NetWorkUtils 检测网络情况

## NewsApplication
- 初始化极光推送

---
正式上线后AndroidManifest.xml的极光推送的Key会被删掉
---

