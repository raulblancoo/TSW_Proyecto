package com.tsw.ComPay.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Definir el LocaleResolver para cambiar entre idiomas
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("es")); // Idioma por defecto (español)
        //resolver.setCookieName("language");
        //resolver.setCookieMaxAge(3600); // La cookie durará 1 hora
        return resolver;
    }

    // Definir el MessageSource para cargar los archivos de mensajes
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages", "messages_en" , "messages_es"); // tus archivos de propiedades
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    // Interceptor para cambiar el idioma según el parámetro "lang" en la URL
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang"); // El parámetro para cambiar el idioma
        return interceptor;
    }

    // Registrar el interceptor en la configuración de Spring
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}