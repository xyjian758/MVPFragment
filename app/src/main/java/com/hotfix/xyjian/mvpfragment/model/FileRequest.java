package com.hotfix.xyjian.mvpfragment.model;

import com.hotfix.xyjian.mvpfragment.retrofit.BaseBean;
import com.hotfix.xyjian.mvpfragment.retrofit.RetrofitClient;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;

import io.reactivex.functions.Consumer;

/**
 * Created by Xyjian on 2018/4/17.
 */

public class FileRequest {
    public static void uploadFile(Subscriber subscriber){
        File file=null;
        RetrofitClient.getInstance()
                .uploadFile("",file,"dfd", BaseBean.class)
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                    }
                })
                .subscribe(subscriber);

    }
}
