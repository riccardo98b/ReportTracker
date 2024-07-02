package com.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.model.Ruolo;
import com.model.Utente;



public class DatabaseUserDetails  implements UserDetails{
	private static final long serialVersionUID = 1L;

	private final Utente utente;

	private final Set<GrantedAuthority> authorities;

	public DatabaseUserDetails(Utente utente) {
		this.utente = utente;

		authorities = new HashSet<GrantedAuthority>();
		for (Ruolo ruolo : utente.getRuolo()) {
			authorities.add(new SimpleGrantedAuthority(ruolo.getNome()));
		}
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.utente.getPassword();
	}

	@Override
	public String getUsername() {
		return this.utente.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
