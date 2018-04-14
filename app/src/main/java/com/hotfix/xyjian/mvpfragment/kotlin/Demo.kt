package com.hotfix.xyjian.mvpfragment.kotlin

import com.google.gson.Gson

/**
 * Created by Xyjian on 2018/4/3.
 */
class Customer public constructor(name: String){

    constructor(name: String,type: Int) : this(name) {
        Gson().fromJson()
    }

    var name:String="gdgd"
    var  dkjgkdj:String?=""
            get() = this.dkjgkdj;
            set(value) {

            }
}

class Box<T>(t:T){
    var value=t;
}

class Ddkkk{
    var  box:Box<Int> = Box(1);
}


