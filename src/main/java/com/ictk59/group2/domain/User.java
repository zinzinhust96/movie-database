package com.ictk59.group2.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name="users" )
public class User {

	@Id @GeneratedValue
	private Long id;
	
	@Column(name="username" ,unique=true, nullable=false )
	private String username;
	
	@Column(name="password", nullable=false )
	private String password;
	
	private String passwordConfirm;
	
	@Column(name="full_name")
	private String fullName;
	
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinTable( 
		name = "users_movies", 
		joinColumns = {@JoinColumn(name="user_id")}, 
		inverseJoinColumns = {@JoinColumn(name="movie_id")}  
	)
	private Set<Movie> watchlist = new HashSet<Movie>();
	
	public User() {}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Set<Movie> getWatchlist() {
		return watchlist;
	}

	public void setWatchlist(Set<Movie> watchlist) {
		this.watchlist = watchlist;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fullName=" + fullName + "]";
	}
	
}
