# 引导页的指示器
      
***
##使用方法
	在工程的build.gradle中添加
	 repositories {
        jcenter()
    }
	
	然后添加依赖
	   compile'com.zhao.project:indicatorlibrary:1.0.0'

## xml文件中
	<com.zhao.indicatorlibrary.view.GuideInstructionView
        android:id="@+id/guide_view"
        android:layout_centerHorizontal="true"
        android:layout_alignParentBottom="true"
        android:layout_width="120dp"
        android:layout_height="80dp"
        app:piontNormalColor="#969696"
        app:pointChoseColor="#00f228"
        app:pointNumber="3"
        app:pointSize="5"
        app:viewPadding="20" />
##属性解释
	pointNumber   指示点的数量
	pointSize     指示点的大小 单位dp
    pointChoseColor  指示点选中状态的颜色
	piontNormalColor  指示点正常时的颜色
    viewPadding    view 的内边距
       
## 使用要求
	页面布局，必须包含viewpager,使用是先给viewpager添加addOnPageChangeListener事件
	在监听的onPageScrolled方法中：
	@Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mGuideInstructionView.setProgress(position, positionOffset);
    }

## 控件效果演示
  
![图片](ezgif.gif)
