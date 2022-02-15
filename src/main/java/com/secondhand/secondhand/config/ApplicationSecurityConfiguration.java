package com.secondhand.secondhand.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;


    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, ObjectMapper objectMapper) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    @Bean
    public RequestBodyReaderAuthenticationFilter authenticationFilter() throws Exception {
        RequestBodyReaderAuthenticationFilter authenticationFilter
                = new RequestBodyReaderAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }




    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/**").authenticated()

                .and()
                .addFilterBefore(
                        authenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(this::logoutSuccessHandler)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
//                .headers().c
//                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("401"));
    }






    private void loginSuccessHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        UserDetails loggedInUser = userDetailsService.loadUserByUsername(authentication.getName());
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + authentication.getName()));
        response.setStatus(HttpStatus.OK.value());
        objectMapper.writeValue(response.getWriter(),request.getCookies());
//        objectMapper.writeValue(response.getWriter(),response.setHeader(););

    }

    private void loginFailureHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e) throws IOException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        objectMapper.writeValue(response.getWriter(), "Nopity nop!");
    }

    private void logoutSuccessHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        response.setStatus(HttpStatus.OK.value());
        objectMapper.writeValue(response.getWriter(), "Bye!");
    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//
//
//
////        http.authorizeRequests().antMatchers("/users/login", "/users/register").permitAll()
////                .antMatchers("/**").authenticated().and().authorizeRequests()
////                .anyRequest().hasAnyRole("ADMIN", "USER").and().headers().and()
////                .exceptionHandling().and().authorizeRequests()
//////                .antMatchers("/login**").permitAll()
////                .and()
////                .addFilterAt(new CustomUsernamePasswordAuthenticationFilter("/loginAction", failureHandler(), successHandler()), UsernamePasswordAuthenticationFilter.class)
////                .formLogin()
////                .loginPage("/login").loginProcessingUrl("/loginAction")
////                .permitAll().and().logout().logoutUrl("/logout")
////                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////                .logoutSuccessUrl("/login").permitAll()
////                .deleteCookies("JSESSIONID").invalidateHttpSession(true).and()
////                .exceptionHandling().and().cors().and().csrf().disable();
//
//
////        http
////                .csrf().disable()
////                .cors().configurationSource(corsConfigurationSource())
////                .and()
////
////
////                .authorizeRequests()
////                .antMatchers("/users/register").permitAll()
////                .antMatchers("/**").authenticated()
////
////                .and()
////                .httpBasic()
////                .and()
////                                .addFilterAt(new CustomUsernamePasswordAuthenticationFilter("/login",failureHandler(),successHandler()),UsernamePasswordAuthenticationFilter.class)
////
////        .formLogin()
////                .successHandler(new AppAuthenticationSuccessHandler());
////                .loginPage("/login")
////                .usernameParameter("email")
////                .passwordParameter("password");
//
////        http
////                .csrf().disable()
////                .httpBasic()
////                .and()
//////                .authorizeRequests()
//////                .and()
////
//////                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
////
//////               .cors().and()
//////               .csrf(csrf ->
//////                        csrf.csrfTokenRepository(csrfTokenRepository()))
////                //READ POSTMAN READ JSON
//////                .csrf().a
//////                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
////                // DISBALED CORS
////                .cors().configurationSource(corsConfigurationSource())
////                .and()
////                //                PERMISSION ALLOWED ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
////                .authorizeRequests()
////                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
////                .antMatchers("/users/login", "/users/register","users/test").permitAll()
////                .antMatchers("/admin/").hasRole(RoleEnum.ADMINISTRATOR.name())
////                .antMatchers("/**").authenticated()
////                //                PERMISSION ALLOWED ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
////                .and()
////
//////                .addFilterAt(new CustomUsernamePasswordAuthenticationFilter("/login",failureHandler(),successHandler()),UsernamePasswordAuthenticationFilter.class)
////                .formLogin()
//////                .and()
////
//////                .loginPage("/login").permitAll()
////                .failureHandler(failureHandler())
//////                .permitAll()
//////                .loginPage("/users/login")
////
////                .loginProcessingUrl("/login").permitAll()
////                .usernameParameter("email")
////                .passwordParameter("password")
//////                .successHandler(successHandler())
//////                .failureHandler(failureHandler())
////                .and()
//////                .addFilterBefore(CorsFilter(),UsernamePasswordAuthenticationFilter.class)
//////                .and()
////
////                .logout()
////                .logoutUrl("/users/logout")
////                .logoutSuccessHandler(logoutSuccessHandler())
////                .invalidateHttpSession(true)
////                .deleteCookies("JSESSIONID");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder);
//    }
//
//    private CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-XSRF-TOKEN");
//        return repository;
//    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

//    public static class SimpleCorsFilter extends OncePerRequestFilter {
//
//        @Override
//        public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//            Enumeration<String> headerNames = req.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                String headerName = headerNames.nextElement();
//                System.out.println("headerName " + headerName);
//                System.out.println("headerVal " + req.getHeader(headerName));
//            }
//            HttpServletResponse response = (HttpServletResponse) res;
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Range, Content-Disposition, Content-Type, Authorization");
//            chain.doFilter(req, res);
//        }
//    }
//
    @Bean
    CorsConfigurationSource corsConfigurationSource() {


        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("POST, GET, OPTIONS, DELETE"));
//        configuration.setAllowCredentials(true);
        //the below three lines will add the relevant CORS response headers
//        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
//        configuration.addExposedHeader("customHeader");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
//
//    public class CsrfHeaderFilter extends OncePerRequestFilter {
//        @Override
//        protected void doFilterInternal(HttpServletRequest request,
//                                        HttpServletResponse response, FilterChain filterChain)
//                throws ServletException, IOException {
//            CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
//                    .getName());
//            if (csrf != null) {
//                System.out.println(csrf.getToken());
//                Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//                String token = csrf.getToken();
//                if (cookie == null || token != null && !token.equals(cookie.getValue())) {
//                    cookie = new Cookie("XSRF-TOKEN", token);
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                }
//            }
//            filterChain.doFilter(request, response);
//        }
//    }
//
//    private AuthenticationSuccessHandler successHandler() {
//        return new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
//                                                HttpServletResponse httpServletResponse, Authentication authentication)
//                    throws IOException, ServletException {
//                httpServletResponse
//                        .getWriter()
//                        .append("Successful logged");
//                httpServletResponse
//                        .setStatus(HttpServletResponse.SC_OK);
//            }
//        };
//    }
//
//
//    private AuthenticationFailureHandler failureHandler() {
//        return new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
//                                                HttpServletResponse httpServletResponse, AuthenticationException e)
//                    throws IOException, ServletException {
//                System.out.println(e.getMessage());
//
//                httpServletResponse.getWriter().append("Authentication failure");
//                httpServletResponse.setStatus(401);
//            }
//        };
//
//
//    }
//
//    private LogoutSuccessHandler logoutSuccessHandler() {
//        return new LogoutSuccessHandler() {
//            @Override
//            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                response
//                        .getWriter()
//                        .append("Successful logged out!");
//                response
//                        .setStatus(HttpServletResponse.SC_ACCEPTED);
//            }
//        };
//    }
}

