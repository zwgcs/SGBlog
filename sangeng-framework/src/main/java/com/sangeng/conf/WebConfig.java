package com.sangeng.conf;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 对Web进行一些配置
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/3/16 17:01
 * @Since version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
        // 设置可被访问的响应头
        //.exposedHeaders("jieiwi");
    }
    /**
     * 注册fastJsonHttpMessageConvert消息转换器对象
     * @return
     */
    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverters() {
        //1.使用FastJson消息转换器对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2.创建FastJson配置类对象
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //3.通过配置类对象设置属性
        //"PrettyFormat"特性可以使得输出的JSON格式具有良好的可读性，即每个属性都单独占据一行，
        // 而不是将整个JSON字符串打印在一行中。这样做可以让JSON数据更加易读，方便人工阅读和调试
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //设置日期格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //将Long类型数据转为字符串
        SerializeConfig.globalInstance.put(Long.class, ToStringSerializer.instance);
        //为FastJson添加序列化配置实例
        fastJsonConfig.setSerializeConfig(SerializeConfig.globalInstance);
        //将FastJson配置添加到fastJSON消息转换器中
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return converter;
    }

    /**
     * 配置消息转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //将fastJson消息转换器添加到消息转换器集合中
        converters.add(fastJsonHttpMessageConverters());
    }
}
