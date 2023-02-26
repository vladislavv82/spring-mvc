package ru.akopian.spring.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//замена web.xml
public class MySpringMvcDispatcherSerlvetIntitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null; //не используем
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //посылаем запросы на Диспетчер Сервлет
    }
}
