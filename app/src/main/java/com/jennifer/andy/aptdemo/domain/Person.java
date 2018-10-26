package com.jennifer.andy.aptdemo.domain;

import com.jennifer.andy.apt.annotation.Who;

@Who(name = "Andy", age = 18)
class Person {

    private String where;


    public void doSomething() {

    }

    @Who(name = "Andy2", age = 100)
    public void run() {
        int runTime;
    }

}
