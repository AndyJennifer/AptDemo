package com.jennifer.andy.apt_compiler;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.jennifer.andy.apt.annotation.Who;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

/**
 * Author:  andy.xwt
 * Date:    2018/10/26 16:13
 * Description:通过ElementKind区分Element
 */
@AutoService(Processor.class)
public class ElementKindSpiltProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Who.class);
        for (Element element : elements) {
            if (element.getKind() == ElementKind.CLASS) {//如果元素是类

            } else if (element.getKind() == ElementKind.INTERFACE) {//如果当前元素是接口

            }
        }
        return false;
    }


    //当然你可以不用重新该方法，可以使用SupportedAnnotationTypes注解，但是需要当前注解的全限定名称(包含包名）
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(Who.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
