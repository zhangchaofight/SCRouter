package com.example.scroutercompiler;

import com.example.scanno.annos.Router;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
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

@SuppressWarnings("unused")
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.example.scanno.annos.Router")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SCRouterProcessor extends AbstractProcessor{

    private Messager messager;
    private Filer filer;

    private Set<Element> annos;
    private Map<String, String> activityMap;

    private static final String packageName = "com.scanno.scrouterhelper";
    private static final String className = "SCRouterHelper";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
        note("init start-----------------");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        note("process start------------------");
        for (TypeElement element : set) {
            note(element.getQualifiedName().toString());
        }
        note("with router---------------");
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Router.class);
        for (Element element : elements) {
            note(element.getSimpleName().toString());
        }
        if (!collectAnno(elements)) {
            return false;
        }
        if (!validateAnno()) {
            return false;
        }
        note("map size : " + activityMap.size());
        for (String key : activityMap.keySet()) {
            note("print map : " + activityMap.get(key));
        }
        generateCode();
        return false;
    }

    private boolean collectAnno(Set<? extends Element> elements) {
        if (elements == null || elements.size() == 0) {
            note("did not find any annotation that needs processing");
            return false;
        }
        annos = new HashSet<>();
        for (Element element : elements) {
            if (element.getKind() == ElementKind.CLASS) {
                annos.add(element);
            }
        }
        return annos.isEmpty();
    }

    private boolean validateAnno() {
        activityMap = new HashMap<>();
        for (Element element : annos) {
            if (element.getModifiers().contains(Modifier.ABSTRACT)) {
                continue;
            }
            Router router = element.getAnnotation(Router.class);
            String path = router.path();
            if (validatePath(path)) {
                activityMap.put(path, ((TypeElement) element).getQualifiedName().toString());
            }
        }
        return activityMap.isEmpty();
    }

    private boolean validatePath(String path) {
        if (path == null || path.trim().length() == 0) {
            return false;
        }
        String[] arr = path.split("/");
        if (arr.length < 2 || arr.length > 3) {
            return false;
        }
        for (String s : arr) {
            if (s == null || s.trim().length() == 0) {
                return false;
            }
        }
        return true;
    }

    private void generateCode() {
        note("start generating code");

        MethodSpec getMethod = MethodSpec.methodBuilder("getMetaData")
                .addModifiers(Modifier.PUBLIC,Modifier.STATIC,Modifier.FINAL)
                .returns(String.class)
                .addStatement("return \"\"")
                .build();

        TypeSpec scHelper = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(getMethod)
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, scHelper)
                .build();

        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
            error("generate code fail!!!");
        }
    }

    private void note(String msg) {
        messager.printMessage(Diagnostic.Kind.NOTE, msg);
    }

    private void error(String msg) {
        messager.printMessage(Diagnostic.Kind.ERROR, msg);
    }
}