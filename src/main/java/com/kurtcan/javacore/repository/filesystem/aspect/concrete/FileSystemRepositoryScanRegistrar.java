package com.kurtcan.javacore.repository.filesystem.aspect.concrete;

import com.kurtcan.javacore.repository.filesystem.aspect.annotations.FileSystemRepository;
import com.kurtcan.javacore.repository.filesystem.aspect.annotations.FileSystemRepositoryScan;
import com.kurtcan.javacore.repository.filesystem.concretes.FileSystemRepositoryImpl;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;

/**
 * <h2>Custom component scan.</h2>
 * <hr>
 * <a href="https://stackoverflow.com/questions/43538429/how-to-get-list-of-interfaces-from-componentscan-packages">stackoverflow reference</a>
 */
public class FileSystemRepositoryScanRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // Get the MyInterfaceScan annotation attributes
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(FileSystemRepositoryScan.class.getCanonicalName());

        if (annotationAttributes != null) {
            String[] basePackages = (String[]) annotationAttributes.get("value");

            if (basePackages.length == 0){
                // If value attribute is not set, fallback to the package of the annotated class
                basePackages = new String[]{((StandardAnnotationMetadata) metadata).getIntrospectedClass().getPackage().getName()};
            }

            // using these packages, scan for interface annotated with MyCustomBean
            ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false, environment){
                // Override isCandidateComponent to only scan for interface
                @Override
                protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                    AnnotationMetadata metadata = beanDefinition.getMetadata();
                    return metadata.isIndependent() && metadata.isInterface();
                }
            };
            provider.addIncludeFilter(new AnnotationTypeFilter(FileSystemRepository.class));

            // Scan all packages
            for (String basePackage : basePackages) {
                for (BeanDefinition beanDefinition : provider.findCandidateComponents(basePackage)) {

                    // Get class object from bean definition class name
                    Class i;
                    try {
                        i = Class.forName(beanDefinition.getBeanClassName());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Can not get class for bean '%s' %s".formatted(beanDefinition.getBeanClassName(), e));
                    }

                    // Generate class for collected beans with cglib
                    Enhancer enhancer = new Enhancer();
                    enhancer.setSuperclass(FileSystemRepositoryImpl.class);
                    enhancer.setInterfaces(new Class[]{i});
                    enhancer.setCallback(NoOp.INSTANCE);
                    Object o = enhancer.create();

                    // Register newly generated classes
                    RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(o.getClass());
                    registry.registerBeanDefinition(o.getClass().getSimpleName(), rootBeanDefinition);
                }
            }
        }
    }
}