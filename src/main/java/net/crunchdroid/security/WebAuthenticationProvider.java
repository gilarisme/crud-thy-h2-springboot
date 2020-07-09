package net.crunchdroid.security;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.crunchdroid.entity.User;
import net.crunchdroid.repo.UserRepo;

@Component
public class WebAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserRepo userRepo;

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        
        try {
        	Optional<User> user = Optional.ofNullable(userRepo.findByUserName(userName));
        	System.out.println(userName);
        	System.out.println(user.get().getUserName());
        	if (user.isPresent()) {
        		if (bCryptPasswordEncoder.matches(password, user.get().getUserPassword())) {
        			List<String> groups = new ArrayList<String>() {	
                    };
                    
                    List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
                    authorities.add(new SimpleGrantedAuthority(user.get().getRole().getRoleName()));
                    System.out.println(user.get().getRole().getRoleName());
                    System.out.println(authorities.get(0));
                    return new UsernamePasswordAuthenticationToken(user.get(), password, authorities);
				}
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
        
        throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
