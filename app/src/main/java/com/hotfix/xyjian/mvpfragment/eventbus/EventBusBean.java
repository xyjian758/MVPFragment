package com.hotfix.xyjian.mvpfragment.eventbus;


/**
 * @author Xyjian
 * @Description: TODO(){EventBus传递信息的实体类}
 * @date: 2018/4/20 9:05
 */
public class EventBusBean<T> {
    private int value;//根据此辨识是干什么的
    private T t;//若是传递实体类，用此方法
    private String stringValue;//字符串值
    private boolean booleanValue;//布尔值

    private EventBusBean() {
    }

    public EventBusBean(int index) {
        this.value = index;
    }

    public int getValue() {
        return this.value;
    }

    public EventBusBean setValue(int value) {
        this.value = value;
        return this;
    }

    public T getT() {
        return this.t;
    }

    public EventBusBean setT(T t) {
        this.t = t;
        return this;
    }
}
