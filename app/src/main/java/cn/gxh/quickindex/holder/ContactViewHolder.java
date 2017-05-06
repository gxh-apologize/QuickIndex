package cn.gxh.quickindex.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.gxh.quickindex.R;
import cn.gxh.quickindex.adapter.MyBaseAdapter;
import cn.gxh.quickindex.bean.Contact;

/**
 * Created by GXH on 2017/5/6.
 */
public class ContactViewHolder extends BaseHolder<Contact> {
    private TextView tvcatalog;
    private ImageView ivicon;
    private TextView tvname;

    public ContactViewHolder(Context context, ViewGroup parent, MyBaseAdapter adapter, int position, Contact bean) {
        super(context, parent, adapter, position, bean);
    }

    @Override
    public View createView(Context context, ViewGroup parent, int position, Contact bean) {
        View itemView = super.inflater.inflate(R.layout.item_contact, parent, false);
        return itemView;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        tvname = (TextView) itemView.findViewById(R.id.tv_name);
        ivicon = (ImageView) itemView.findViewById(R.id.iv_icon);
        tvcatalog = (TextView) itemView.findViewById(R.id.tv_catalog);

    }

    /**
     * 填充数据
     *
     * @param bean
     * @param position
     */
    @Override
    protected void onRefreshViews(Contact bean, int position) {
        if (position == 0) {
            tvcatalog.setText(bean.initLetter);
            tvcatalog.setVisibility(View.VISIBLE);
        }else{
            Contact refBean = (Contact) super.adapter.getItem(position - 1);
            if(refBean.initLetter.equals(bean.initLetter)){
                tvcatalog.setVisibility(View.GONE);
            }else{
                tvcatalog.setVisibility(View.VISIBLE);
                tvcatalog.setText(bean.initLetter);
            }
        }
        tvname.setText(bean.name);
    }
}
