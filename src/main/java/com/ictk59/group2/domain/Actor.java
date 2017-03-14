package com.ictk59.group2.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "actors_info")
public class Actor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "biography", columnDefinition = "TEXT")
	private String biography;
	
	@Column(name = "birthday")
	private String birthday;
	
	@Column(name = "deathday")
	private String deathday;
	
	@Column(name = "place_of_birth")
	private String placeOfBirth;
	
	@Column(name = "profile_pic")
	private String profilePic;
	
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinTable( 
		name = "actors_by_movies", 
		joinColumns = {@JoinColumn(name="actor_id")}, 
		inverseJoinColumns = {@JoinColumn(name="movie_id")}  
	)
	private Set<Movie> movies = new HashSet<Movie>();

	public Actor() {

	}

	public Actor(String name, String aka, String biography, String birthday, String deathday, String placeOfBirth,
			String profilePic) {
		super();
		this.name = name;
		this.biography = biography;
		this.birthday = birthday;
		this.deathday = deathday;
		this.placeOfBirth = placeOfBirth;
		this.profilePic = profilePic;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDeathday() {
		return deathday;
	}

	public void setDeathday(String deathday) {
		this.deathday = deathday;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", biography=" + biography + ", birthday=" + birthday
				+ ", deathday=" + deathday + ", placeOfBirth=" + placeOfBirth + ", profilePic=" + profilePic
				+ ", movies=" + movies + "]";
	}

}
