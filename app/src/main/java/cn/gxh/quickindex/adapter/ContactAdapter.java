package cn.gxh.quickindex.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import cn.gxh.quickindex.bean.Contact;
import cn.gxh.quickindex.holder.BaseHolder;
import cn.gxh.quickindex.holder.ContactViewHolder;

/**
 * Created by GXH on 2017/5/6.
 */
public class ContactAdapter extends MyBaseAdapter<Contact> {
    public ContactAdapter(Context context, List<Contact> listDatas) {
        super(context, listDatas);
    }

    @Override
    public BaseHolder createViewHolder(Context context, ViewGroup parent, Contact bean, int position) {
        return new ContactViewHolder(context,parent,this,position,bean);
    }
}
