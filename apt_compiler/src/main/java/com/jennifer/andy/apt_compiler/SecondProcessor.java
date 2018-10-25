package com.jennifer.andy.apt_compiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Author:  andy.xwt
 * Date:    2018/10/25 15:29
 * Description:使用注解的方式替代getSupportedAnnotationTypes与getSupportedSourceVersion
 */

@SupportedAnnotationTypes("com.jennifer.andy.apt.annotation.Who")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SecondProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
