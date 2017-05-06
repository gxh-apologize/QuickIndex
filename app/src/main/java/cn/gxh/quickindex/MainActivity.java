package cn.gxh.quickindex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.gxh.quickindex.adapter.ContactAdapter;
import cn.gxh.quickindex.bean.Contact;
import cn.gxh.quickindex.view.LetterBar;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private LetterBar letterBar;
    private TextView tvSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();

    }

    private void initEvent() {
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

        letterBar.setSelectedTextView(tvSelected);
    }

    private List<Contact> listDatas;
    private void initData() {
        listDatas = new ArrayList<>();
        for (String name : Constant.LIST_DATAS2) {
            Contact bean = new Contact(name);
            listDatas.add(bean);
        }
        // 排序操作
        Collections.sort(listDatas, new Comparator<Contact>() {

            @Override
            public int compare(Contact left, Contact right) {
                return left.pinyin.compareTo(right.pinyin);
            }
        });
        ContactAdapter adapter = new ContactAdapter(this, listDatas);
        listView.setAdapter(adapter);
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        letterBar = (LetterBar) findViewById(R.id.lb_main);
        listView = (ListView) findViewById(R.id.lv_listview);
        tvSelected = (TextView) findViewById(R.id.tv_selected_letter);
    }

}
