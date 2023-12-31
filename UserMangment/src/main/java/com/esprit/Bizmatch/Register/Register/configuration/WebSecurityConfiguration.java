package com.esprit.Bizmatch.Register.Register.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService jwtService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/authen", "/registerNewUser").permitAll()
                //  .anyRequest().permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .antMatchers("/checkEmail").permitAll()
                .antMatchers("/resetPassword").permitAll()
                .antMatchers("/activate/{verificationToken}").permitAll()
                .antMatchers("/Claim/create").permitAll()
                .antMatchers("/Claim/all").permitAll()
                .antMatchers("/Claim/get/{id}").permitAll()
                .antMatchers("/Claim/update/{id}").permitAll()
                .antMatchers("/Claim/delete/{id}").permitAll()
                .antMatchers("/Claim/getbyname").permitAll()
                .antMatchers("/Claim/analyser-contenu").permitAll()
                .antMatchers("/Claim/calculer-pourcentage-sentiment").permitAll()
                .antMatchers("/Claim/analyser-contenu/{id}").permitAll()
                .antMatchers("/Claim/pourcentage-sentiment").permitAll()
                .antMatchers("/addroles/{roleName}/{userName}").permitAll()
                .antMatchers("/countFournisseur").permitAll()
                .antMatchers("/getallUser").permitAll()
                .antMatchers("/userss").permitAll()
                .antMatchers("/countentreprise").permitAll()
                .anyRequest().authenticated()

                /*

                 */
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
   /* @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Allow requests from Angular app
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow common HTTP methods
        configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
    }
}
