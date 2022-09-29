package com.edudemic.securityconfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edudemic.entities.Rol;
import com.edudemic.entities.Usuario;
import com.edudemic.repository.UsuarioRepository;


@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		Usuario user = userRepository.findByDni(dni);
		UserBuilder userBuilder = null;
		if(user != null) 
		{
			userBuilder =User.withUsername(dni);
			userBuilder.disabled(false);
			userBuilder.password(user.getPassword());
			userBuilder.authorities(new SimpleGrantedAuthority(user.getRol().getAuthority()));
			
		}else 
		{
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return userBuilder.build();
	}

}