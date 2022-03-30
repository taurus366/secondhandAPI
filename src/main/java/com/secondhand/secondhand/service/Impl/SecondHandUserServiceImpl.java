package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.AuthenticationFailedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecondHandUserServiceImpl implements UserDetailsService {

   private final UserRepository userRepository;

    public SecondHandUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email + " LOGGED _____________________2__________________________________________");

        UserEntity userEntity = userRepository
                .findByEmailGraph(email)
                .orElseThrow(() -> {
                   throw  new UsernameNotFoundException("User with that email " + email + " not found!");

                });
        System.out.println("test");
        System.out.println(userEntity.getPassword() + "dadasdasd");
        return mapToUserDetails(userEntity);
    }

    private UserDetails mapToUserDetails(UserEntity user){

        List<GrantedAuthority> authorities =
                user
                        .getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                        .collect(Collectors.toList());

        return new SecondHandUser(
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.getId()
        );

    }
}
