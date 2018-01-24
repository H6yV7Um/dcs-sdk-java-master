package com.baidu.duer.dcs.nim.model;

/**
 * Created by kenway on 17/7/20 10:08
 * Email : xiaokai090704@126.com
 */

public interface OnModelCallback<T> {

    void onModelSuccessed(T t);

    void onModelFailed(String failedMsg);

}
