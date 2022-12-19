package com.hhoa.vblog.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * WebMvc配置.
 */
@Configuration
@Slf4j
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
//
//
//    /**
//     *添加消息转化类.
//     *
//     * @param list HttpMessageConverter list
//     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {
//        MappingJackson2HttpMessageConverter jsonConverter =
//                new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = jsonConverter.getObjectMapper();
//        //序列换成json时,将所有的long变成string
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//        list.add(jsonConverter);
//    }
}
