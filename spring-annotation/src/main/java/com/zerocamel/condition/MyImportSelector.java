package com.zerocamel.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import java.util.Set;

/**
 * @program: Spring
 * @description: ${利用ImportSelector导入类}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-06 22:22
 **/
public class MyImportSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //获取配置列下包含指定注解的方法
        Set<MethodMetadata> anImport = annotationMetadata.getAnnotatedMethods("org.springframework.context.annotation.Bean");
        //获取配置类所加的注解类型
        Set<String> types = annotationMetadata.getAnnotationTypes();
        //获取配置类名
        String className = annotationMetadata.getClassName();
        return new String[]{"com.zerocamel.bean.Blue"};
    }
}
