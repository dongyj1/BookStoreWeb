package com.adminportal.domain.security;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.adminportal.domain.User;

@Entity
public class PasswordResetToken {
	private static final int EXPIRATION = 60 * 24;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String token;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	private Date expireDate;
	
	public PasswordResetToken(){}
	
	public PasswordResetToken(final String token, final User user) {
		super();
		this.token = token;
		this.user = user;
		this.expireDate = calculateExpireDate(EXPIRATION);
	}
	
	private Date calculateExpireDate(final int expireTimeInMinite) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expireTimeInMinite);
		return new Date(cal.getTime().getTime());
	}
	public void updateToken(final String token) {
		this.token = token;
		this.expireDate = calculateExpireDate(EXPIRATION);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	@Override
	public String toString() {
		return "PasswordResetToken [id=" + id + ", token=" + token + ", user=" + user + ", expireDate=" + expireDate
				+ "]";
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public static int getExpiration() {
		return EXPIRATION;
	}
	
}
