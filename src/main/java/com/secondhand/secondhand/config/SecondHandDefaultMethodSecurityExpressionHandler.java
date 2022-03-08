package com.secondhand.secondhand.config;

import com.secondhand.secondhand.service.UserService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class SecondHandDefaultMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final UserService userService;

    public SecondHandDefaultMethodSecurityExpressionHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        OwnerSecurityExpressionRoot ownerSecurityExpressionRoot = new OwnerSecurityExpressionRoot(authentication);

        ownerSecurityExpressionRoot.setUserService(userService);
        ownerSecurityExpressionRoot.setPermissionEvaluator(getPermissionEvaluator());
        ownerSecurityExpressionRoot.setTrustResolver(new AuthenticationTrustResolverImpl());
        ownerSecurityExpressionRoot.setRoleHierarchy(getRoleHierarchy());

        return ownerSecurityExpressionRoot;
    }
}
