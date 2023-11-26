package edu.ucsb.cs156.mobility.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import edu.ucsb.cs156.mobility.entities.User;
import edu.ucsb.cs156.mobility.models.CurrentUser;

public abstract class CurrentUserService {
  public abstract User getUser();
  public abstract CurrentUser getCurrentUser();
  public abstract Collection<? extends GrantedAuthority> getRoles();

  public final boolean isLoggedIn() {
    return getUser() != null;
  }

}
