package cn.gxh.quickindex.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.gxh.quickindex.adapter.MyBaseAdapter;

/**
 * 封装适配器中的getView方法：填充布局，查找子控件，settag, 刷新子控件显示
 */
public abstract class BaseHolder <T> {

    public Context context;

    /** 指ListView */
    public  ViewGroup parent;

    public MyBaseAdapter adapter;

    /** 列表项位置 */
    public  int position;

    /** 列表项实体 */
    public  T bean;

    /** 列表项布局 */
    public View itemView;

    public LayoutInflater inflater;

    public BaseHolder(Context context, ViewGroup parent,
                      MyBaseAdapter adapter, int position, T bean) {
        this.context = context;
        this.parent = parent;
        this.adapter = adapter;
        this.position = position;
        this.bean = bean;
    }

    /**
     * 初始化holder, 填充布局，查找子控件等
     */
    public void init() {
        inflater = LayoutInflater.from(context);
        // 创建列表项布局
        itemView = createView(context, parent, position, bean);
        // 查找列表项子控件
        initView();
        // setTag操作
        itemView.setTag(this);
    }

    /**
     * 创建列表项布局视图
     * @param context
     * @param parent
     * @param bean 列表项对应的javabean
     * @param position 列表项位置
     * @return
     */
    public abstract View createView(Context context, ViewGroup parent, int position, T bean);

    /**
     * 查找列表项子控件
     */
    protected abstract void initView();

    /**
     * 更新javabean及位置，刷新子控件的显示
     *
     * @param bean
     * @param position
     */
    protected abstract void onRefreshViews(T bean, int position);

    /**
     * 刷新item子控件显示
     * @param bean
     * @param position
     */
    public void refreshViews(T bean, int position) {
        this.bean = bean;
        this.position = position;

        onRefreshViews(bean, position);
    }

    /**
     * 返回item布局
     * @return
     */
    public View getItemView() {
        return itemView;
    }
}
