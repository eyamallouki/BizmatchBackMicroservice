package com.esprit.Bizmatch.partenariatMS.Configuration;

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
                .antMatchers("/recruteur/update/{idR}").permitAll()
                .antMatchers("/recruteur/add").permitAll()
                .antMatchers("/recruteur/getAll").permitAll()
                .antMatchers("/recruteur/delete/{idR}").permitAll()
                .antMatchers("/recruteur/get/{idR}").permitAll()
                .antMatchers("/upload-file").permitAll()
                .antMatchers("/entreprise").permitAll()
                .antMatchers("/entreprise/getAll").permitAll()
                .antMatchers("/entreprise/add").permitAll()
                .antMatchers("/entreprise/edit").permitAll()
                .antMatchers("/entreprise/delete/{id}").permitAll()
                .antMatchers("/entreprise/{id}").permitAll()
                .antMatchers("/entreprise/addavecImage").permitAll()
                .antMatchers("/entreprise/meilleurMatch/{id}").permitAll()
                .antMatchers("/entreprise/accepter").permitAll()
                .antMatchers("/entreprise//upload-file").permitAll()
                .antMatchers("/demandeAchat/update/{id}").permitAll()
                .antMatchers("/demandeAchat/add").permitAll()
                .antMatchers("/demandeAchat/getAll").permitAll()
                .antMatchers("/demandeAchat/delete/{id}").permitAll()
                .antMatchers("/demandeAchat/get/{id}").permitAll()
                .antMatchers("/demandeAchat/lastidpost").permitAll()
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
