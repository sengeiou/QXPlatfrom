package love.qx.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossDomainConfig implements WebMvcConfigurer {
private CorsConfiguration buildConfig(){
    CorsConfiguration corsConfiguration = new CorsConfiguration();
//        设置属性
//        允许跨域请求的地址 *表示所有
    corsConfiguration.addAllowedOrigin("*");
//        允许跨域的请求头
    corsConfiguration.addAllowedHeader("*");
//        允许跨域的请求方法
    corsConfiguration.addAllowedMethod("*");
//        跨域请求的时候使用的是否是同一个session
    corsConfiguration.setAllowCredentials(true);
    return corsConfiguration;
}

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}
