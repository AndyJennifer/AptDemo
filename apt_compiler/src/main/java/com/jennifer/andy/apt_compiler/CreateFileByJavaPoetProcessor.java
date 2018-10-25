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
        return false;
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
}

