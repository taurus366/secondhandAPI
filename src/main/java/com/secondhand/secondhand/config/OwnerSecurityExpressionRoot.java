package com.secondhand.secondhand.config;

import com.secondhand.secondhand.model.entity.RoleEntity;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import com.secondhand.secondhand.service.UserService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class OwnerSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private UserService userService;
    private Object returnObject;
    private Object filterObject;

    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     */
    public OwnerSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

//    CUSTOM

    public boolean isAdmin() {
        Authentication authentication = getAuthentication();
//        String userEmail = currentUserEmail();

//        if (userEmail == null) {
//            return false;
//        }


//        return userService.isAdmin(userEmail);
        if (authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication
                    .getPrincipal())
                    .getAuthorities()
                    .stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_"+ RoleEnum.ADMINISTRATOR));
        }
        return false;
    }

    private String currentUserEmail() {






        Authentication authentication = getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails) {
            ((UserDetails) authentication.getPrincipal()).getAuthorities().forEach(System.out::println);
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null;
    }



//    CUSTOM

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

}
