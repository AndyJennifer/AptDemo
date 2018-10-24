package com.jennifer.andy.apt_compiler;

import com.google.auto.service.AutoService;
import com.jennifer.andy.apt.annotation.Who;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;


/**
 * Author:  andy.xwt
 * Date:    2018/10/22 11:30
 * Description:
 */

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.jennifer.andy.apt.annotation.Who")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class MyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        simpleProcess(annotations, roundEnv);
        createFileByJavaPoet(annotations, roundEnv);
        return true;
    }

    private void simpleProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Who.class);
        log(annotations.toString());
        log(elements.toString());
        for (Element element : elements) {
            //如果当前元素类型是class类型
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;
                log(typeElement.getSimpleName().toString());
                Who annotation = typeElement.getAnnotation(Who.class);
                log(annotation.name() + "---->" + annotation.age());
            }
        }
    }

    /**
     * 手动创建源文件
     */
    private void createFileByHand() {

    }

    /**
     * 通过JavaPoet生成新的源文件
     */
    private void createFileByJavaPoet(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.jennifer.andy.aptdemo.domain", helloWorld)
                .build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 调用打印语句而已
     */
    private void log(String msg) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
    }


    //当然你可以不用重新该方法，可以使用SupportedAnnotationTypes注解，但是需要当前注解的全限定名称(包含包名）
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return ImmutableSet.of(Who.class.getName());
//    }

    //当然你可以不用重新该方法，可以使用SupportedSourceVersion注解，具体的注解查看SourceVersion枚举中的值
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.latestSupported();
//    }

}
