package com.secondhand.secondhand.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;




    public CustomUsernamePasswordAuthenticationFilter(String path, AuthenticationFailureHandler fail, AuthenticationSuccessHandler success) {

        setFilterProcessesUrl(path);
//        setAuthenticationFailureHandler(fail);
//        setAuthenticationSuccessHandler(success);


    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            BufferedReader reader = request.getReader();
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                System.out.println(line);
            }
            String parsedReq = sb.toString();
            if (parsedReq != null) {
                ObjectMapper mapper = new ObjectMapper();
                AuthReq authReq = mapper.readValue(parsedReq, AuthReq.class);
                return new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InternalAuthenticationServiceException("Failed to parse authentication request body");
        }
        return null;
    }



    public static class AuthReq {
        String email;
        String password;

        public String getEmail() {
            return email;
        }

        public AuthReq setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public AuthReq setPassword(String password) {
            this.password = password;
            return this;
        }
    }
}

