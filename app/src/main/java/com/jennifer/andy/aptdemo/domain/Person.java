package com.jennifer.andy.aptdemo.domain;


import com.jennifer.andy.apt.annotation.Who;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Author:  andy.xwt
 * Date:    2018/10/12 17:26
 * Description:
 */
@Who(name = "AndyJenifer", age = 18)
class Person {


    @Where(country = "中国", province = "四川", city = "成都")
    private String where;

    @DoSomething("写博客")
    public void doSomething() {

    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Where {
        String country();

        String province();

        String city();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface DoSomething {
        String value();
    }


}
