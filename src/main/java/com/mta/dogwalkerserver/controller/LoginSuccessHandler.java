package com.mta.dogwalkerserver.controller;

import com.mta.dogwalkerserver.models.DogOwner;
import com.mta.dogwalkerserver.models.DogWalker;
import com.mta.dogwalkerserver.repo.DogOwnerRepo;
import com.mta.dogwalkerserver.repo.DogWalkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private DogOwnerRepo dogOwnerRepo;

    @Autowired
    private DogWalkerRepo dogWalkerRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        final GrantedAuthority dogWalkerAuthority = new SimpleGrantedAuthority(
                "DOG_WALKER_ROLE");
        final GrantedAuthority dogOwnerAuthority = new SimpleGrantedAuthority(
                "DOG_OWNER_ROLE");
        int dogWalkerId = 0;
        int dogOwnerId = 0;



        if (myUserDetails.getAuthorities().contains(dogWalkerAuthority))
        {
            DogWalker dogWalker = dogWalkerRepo.findByUserName(myUserDetails.getUsername());
            dogWalkerId = dogWalker.getId();
        }
        else if (myUserDetails.getAuthorities().contains(dogOwnerAuthority))
        {
            DogOwner dogOwner = dogOwnerRepo.findByUserName(myUserDetails.getUsername());
            dogOwnerId = dogOwner.getId();
        }

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        final RequestCache requestCache = new HttpSessionRequestCache(); //not clear what is this
        final SavedRequest savedRequest = requestCache.getRequest(request, response);//not clear what is this
        String redirectURL = super.determineTargetUrl(request, response);
        clearAuthenticationAttributes(request);//not clear what is this




        if (myUserDetails.getAuthorities().contains(dogWalkerAuthority)) //doesnt get here
        {
            redirectURL = "/api/DogWalker/id/" + dogWalkerId;
        }
        else if (myUserDetails.getAuthorities().contains(dogOwnerAuthority)) //doesnt get here
        {
            redirectURL = "/api/DogOwner/id/" + dogOwnerId;
        }

        redirectStrategy.sendRedirect(request, response, redirectURL);

    }
}
