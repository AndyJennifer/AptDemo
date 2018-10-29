package com.jennifer.andy.apt_compiler;

import com.google.auto.service.AutoService;
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
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Author:  andy.xwt
 * Date:    2018/10/25 15:38
 * Description: 通过JavaPoet生成java文件
 */

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.jennifer.andy.apt.annotation.Who")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CreateFileByJavaPoetProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        createFileByJavaPoet(set, roundEnvironment);
        return true;
    }


    /**
     * 通过JavaPoet生成新的源文件
     */
    private void createFileByJavaPoet(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //创建main方法
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)//设置可见性修饰符public static
                .returns(void.class)//设置返回值为void
                .addParameter(String[].class, "args")//添加参数类型为String数组，且参数名称为args
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")//添加语句
                .build();
        //创建类
        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)//将main方法添加到HelloWord类中
                .build();

        //创建文件，第一个参数是包名，第二个参数是相关类
        JavaFile javaFile = JavaFile.builder("com.jennifer.andy.aptdemo.domain", helloWorld)
                .build();

        try {
            //创建文件
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            log(e.getMessage());
        }

    }

    /**
     * 调用打印语句而已
     */
    private void log(String msg) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
    }

}

