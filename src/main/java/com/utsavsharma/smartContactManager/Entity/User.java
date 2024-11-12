package com.utsavsharma.smartContactManager.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @file User.java
 *       Author: Utsav Sharma
 *       Date: 12-08-2024
 *       Time: 10:15:47
 */

@Getter
@Setter

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "UserInfo")
public class User implements UserDetails {
	@Id

	private String userId;
	@Column(name = "userName", nullable = false)
	private String Name;
	private String Password;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(length = 500)
	private String about;

	@Override
	public String toString() {
		return "User [userId=" + userId + ", Name=" + Name + ", Password=" + Password + ", email=" + email + ", about="
				+ about + ", ProfilePic=" + ProfilePic + ", phoneNumber=" + phoneNumber + ", enabled=" + enabled
				+ ", emailVerified=" + emailVerified + ", phoneVerified=" + phoneVerified + ", providers=" + providers
				+ ", providerUserID=" + providerUserID + ", date=" + date + "]";
	}

	@Column(length = 500)
	private String ProfilePic;
	private String phoneNumber;
	// information
	private boolean enabled;
	private boolean emailVerified;
	private boolean phoneVerified;

	private Providers providers;
	private String providerUserID;
	@CreationTimestamp
	private Date date;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@Builder.Default
	private List<Contact> contacts = new ArrayList<>();

	@Override
	@Transactional
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getUsername() {
		return this.email;
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
	public String getPassword() {
		return this.Password;
	}

}
