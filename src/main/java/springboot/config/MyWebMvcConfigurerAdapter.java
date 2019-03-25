package springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springboot.Interceptor.MyInterceptor;

/**
 * 推荐添加自己的MVC配置的方式
 * WebMvcConfigurerAdapter已过时,实现WebMvcConfigurer或者继承WebMvcConfigurationSupport
 * */
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Bean
    public MyInterceptor getMyInterceptor(){
        //容器初始化时注入自定义拦截器bean,单例
        return new MyInterceptor();
    }

    /**
     * 配置静态资源访问路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/","classpath:/resources/","classpath:/static/","classpath:/public/");
        //访问外部目录
        //registry.addResourceHandler("/my/**").addResourceLocations("file:D:/my/");
        //super.addResourceHandlers(registry);
    }

    /**
     * 配置自定义拦截器
     * springboot 2.x会拦截静态资源
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //new一个拦截器对象会导致bean注入为null
        //registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/login","/logout","/static/**");
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**").excludePathPatterns("/auth/**","/error","/static/**");
        //super.addInterceptors(registry);
    }

    /**
     * 配置视图页面跳转
     * 以前要访问一个页面需要先创建个Controller控制类，再写方法跳转到页面
     * 在这里配置后就可直接访问http://localhost:8080/就跳转到login.html页面了
     * 或者使用forward转发映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        //registry.addViewController("/").setViewName("login");
        registry.addViewController("/").setViewName("forward:/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        //super.addViewControllers(registry);
    }

}
