package com.zerocamel.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义扫描包过滤规则
 * @Author zeroCamel
 */
public class MyTypeFilter implements TypeFilter {

    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        // 获取注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        // 获取类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        // 获取类资源 类路径
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();
        System.out.println(className);
        if (className.contains("ee"))
        {
            return  true;
        }

        return false;
    }
}
