package uz.pdp.task_2_6_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //--------- security----------//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated();
    }

    //----- bu passwordni ecodelash uchun kerak buladi--------//
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    ---------java mail sender--------------//
    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("");
        javaMailSender.setPassword("");
        Properties javaMailProperties = javaMailSender.getJavaMailProperties();
        javaMailProperties.put("mail.transport.protocol","smtp");
        javaMailProperties.put("mail.smtp.auth","true");
        javaMailProperties.put("mail.smtp.strattls.enable","true");
        javaMailProperties.put("mail.debug","true");
        return javaMailSender;
    }
}
