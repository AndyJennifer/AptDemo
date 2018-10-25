package com.jennifer.andy.apt_compiler;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.jennifer.andy.apt.annotation.Who;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Author:  andy.xwt
 * Date:    2018/10/25 15:25
 * Description:
 * 第一种传统的方法处理注解，通过重写getSupportedAnnotationTypes与getSupportedSourceVersion
 * 如果使用注解的形式可以查看{@link SecondProcessor}
 */

@AutoService(Processor.class)
public class FirstProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }


    //当然你可以不用重新该方法，可以使用SupportedAnnotationTypes注解，但是需要当前注解的全限定名称(包含包名）
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(Who.class.getName());
    }

    //当然你可以不用重新该方法，可以使用SupportedSourceVersion注解，具体的注解查看SourceVersion枚举中的值
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
