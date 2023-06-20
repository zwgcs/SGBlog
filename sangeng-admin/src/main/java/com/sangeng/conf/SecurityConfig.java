package com.sangeng.conf;


import com.sangeng.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/12 19:36
 * @Description
 * @Since version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //允许login接口可以匿名访问
                .antMatchers("/user/login").anonymous()
                .antMatchers("/user/logout").authenticated()
                .antMatchers("/user/getInfo").authenticated()
                .antMatchers("/user/getRouters").authenticated()
                .antMatchers("/content/tag").authenticated()
                .antMatchers("/content/tag/{tagId}").authenticated()
                .antMatchers("/content/tag/listAllTag").authenticated()
                .antMatchers("/content/category").authenticated()
                .antMatchers("/content/category/{categoryId}").authenticated()
                .antMatchers("/content/category/listAllCategory").authenticated()
                .antMatchers("/content/category/list").authenticated()
                .antMatchers("/content/article").authenticated()
                .antMatchers("/content/article/{articleId}").authenticated()
                .antMatchers("/content/article/list").authenticated()
                .antMatchers("/system/menu").authenticated()
                .antMatchers("/system/menu/list").authenticated()
                .antMatchers("/system/menu/{menuId}").authenticated()
                .antMatchers("/system/menu/treeselect").authenticated()
                .antMatchers("/system/role").authenticated()
                .antMatchers("/system/role/{roleId}").authenticated()
                .antMatchers("/system/role/changeStatus").authenticated()
                .antMatchers("system/role/listAllRole").authenticated()
                .antMatchers("/system/role/list").authenticated()
                .antMatchers("/system/menu/roleMenuTreeselect/{roleId}").authenticated()
                .antMatchers("/system/user").authenticated()
                .antMatchers("/changeStatus").authenticated()
                .antMatchers("/system/user/{userId}").authenticated()
                .antMatchers("/system/user/list").authenticated()
                .antMatchers("/system/user/changeStatus").authenticated()
                .antMatchers("/content/link").authenticated()
                .antMatchers("/content/link/list").authenticated()
                .antMatchers("/content/link/{linkId}").authenticated()
                .antMatchers("/upload").authenticated()
                //对其他
                .anyRequest().permitAll();

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);


        http.logout().disable();

        http.cors();
    }


    /**
     * 添加认证对象
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 认证时通过BCryptPasswordEncoder规则对密码进行校验
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 添加过滤器
     */

}
