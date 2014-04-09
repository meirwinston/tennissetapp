package com.tennissetapp.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class AppUserDetails implements UserDetails,CredentialsContainer {
	private static final long serialVersionUID = 1L;
	protected Long userAccountId;
	protected String username;
	protected String password;
	protected String email;
	protected boolean isEnabled = true;
	protected boolean isCredentialsNonExpired = true;
	protected boolean isAccountNonLocked = true;
	protected boolean isAccountNonExpired = true;
	private Collection<? extends GrantedAuthority> authorities;
	
	public AppUserDetails(){}
	
	public AppUserDetails(String username, String password, Long userAccountId,String email,Collection<? extends GrantedAuthority> authorities) {
		this.userAccountId = userAccountId;
		this.username = username;
		this.password = password;
		this.email = email;
		if(authorities != null){
			this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));;
		}
		
	}
	
	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	

	@Override
	public void eraseCredentials() {
		password = null;
	}
	
	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities =
            new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }


    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof AppUserDetails) {
            return email.equals(((AppUserDetails) rhs).email);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return email.hashCode();
    }
    
    
	@Override
	public String toString() {
		return "AppUserDetails [userAccountId=" + userAccountId + ", username="
				+ username + ", password=" + password + ", isEnabled="
				+ isEnabled + ", isCredentialsNonExpired="
				+ isCredentialsNonExpired + ", isAccountNonLocked="
				+ isAccountNonLocked + ", isAccountNonExpired="
				+ isAccountNonExpired + "]";
	}

}
