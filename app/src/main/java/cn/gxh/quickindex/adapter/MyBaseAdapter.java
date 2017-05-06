package cn.gxh.quickindex.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import cn.gxh.quickindex.holder.BaseHolder;

/**
 * Created by Administrator on 2016/8/4.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private final Context context;
    /** 列表显示的数据集合 */
    public List<T> listDatas;

    public MyBaseAdapter(Context context, List<T> listDatas) {
        this.context = context;
        this.listDatas = listDatas;
    }

    /**
     * 刷新数据显示
     * @param newDatas 要显示的新数据
     */
    public void setDatas(List<T> newDatas) {
        this.listDatas = newDatas;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;

        // 列表项对应的javabean
        T bean = (T) getItem(position);
        if (convertView == null) {
            // 创建holder对象
            holder = createViewHolder(context, parent, bean, position);
            // 初始化holder, 填充布局，查找子控件，settag等
            holder.init();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }

        // 刷新item子控件显示
        holder.refreshViews(bean, position);
        // 返回item布局
        return holder.getItemView();
    }

    /**
     * 创建holder对象
     *
     * @param context
     * @param parent
     * @param bean 列表项对应的javabean
     * @param position 列表项位置
     * @return
     */
    public abstract BaseHolder createViewHolder(
            Context context, ViewGroup parent, T bean, int position);

    @Override
    public int getCount() {
        return listDatas == null ? 0 : listDatas.size();
    }

    @Override
    public T getItem(int i) {
        return listDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 删除一个对象, 刷新界面
     * @param bean
     */
    public void remove(T bean) {
        listDatas.remove(bean);
        notifyDataSetChanged();
    };
}
















