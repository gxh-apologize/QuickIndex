
#效果图：\<br>




#使用方法：\<br>
eclipse：可以下载下来添加进你的项目\<br>
AndroidStudio：\<br>
##Add it in your root build.gradle at the end of repositories：
	
	  allprojects {
			repositories {
				...
				maven { url 'https://jitpack.io' }
			}
		}
  
##Add the dependency：

	dependencies {
		compile 'com.github.gxh-apologize:QuickIndex:v1.0'
	}
  
#代码中：\<br>
  ##布局xml：
    
	    <?xml version="1.0" encoding="utf-8"?>
	    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
		xmlns:tools="http://schemas.android.com/tools"
		android:layout_width="match_parent"
		android:layout_height="match_parent">

		<ListView
		    android:id="@+id/lv_listview"
		    android:layout_width="match_parent"
		    android:layout_height="match_parent"/>

		<cn.gxh.quickindex.view.LetterBar
		    android:id="@+id/lb_main"
		    android:layout_width="30dp"
		    android:layout_height="match_parent"
		    android:background="#44000000"
		    android:layout_alignParentRight="true"/>

		<TextView
		    android:id="@+id/tv_selected_letter"
		    android:layout_width="80dp"
		    android:layout_height="80dp"
		    android:textSize="30sp"
		    android:visibility="gone"
		    android:background="@drawable/shape_tv_bg"
		    android:textColor="#fff"
		    android:layout_centerInParent="true"
		    android:text="A"
		    android:gravity="center"
		    />
	    </RelativeLayout>

  ##设置中间显示的提示框：\<br>
    
    	letterBar.setSelectedTextView(tvSelected);
  ##设置监听\<br>
   
   	letterBar.setOnLetterChangedListener(new LetterBar.OnLetterChangedListener() {
              @Override
              public void onLetterChange(int index, String letter) {
                  for (int i = 0; i < listDatas.size(); i++) {
                      Contact contact = listDatas.get(i);
                      if (letter.equals(contact.initLetter)) {
                          // 列表显示指定的位置
                          listView.setSelection(i);
                          return;
                      }
                  }
              }
          });
