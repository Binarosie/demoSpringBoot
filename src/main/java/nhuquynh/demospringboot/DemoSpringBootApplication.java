package nhuquynh.demospringboot;

import nhuquynh.demospringboot.configs.MySiteMeshFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBootApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<MySiteMeshFilter> siteMeshFilter() {
        FilterRegistrationBean<MySiteMeshFilter> filterRegistrationBean
                = new FilterRegistrationBean<MySiteMeshFilter>();
        filterRegistrationBean.setFilter(new MySiteMeshFilter()); //adding sitemesh file
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
