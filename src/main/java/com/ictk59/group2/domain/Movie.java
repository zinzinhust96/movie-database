package com.ictk59.group2.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movies_info")
public class Movie {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "director")
	private String director;
	
	@Column(name = "writer")
	private String writer;
	
	@Column(name = "casts")
	private String casts;
	
	@Column(name = "plot", columnDefinition = "TEXT")
	private String plot;
	
	@Column(name = "poster")
	private String poster;
	
	@Column(name = "rating")
	private String rating;
	
	@Column(name = "runtime")
	private String runtime;
	
	@ManyToMany( mappedBy = "movies")
	private Set<Actor> actors = new HashSet<Actor>();
	
	public Movie() {
		
	}

	public Movie(String title, String year, String country, String genre, String director, String writer, String casts, String plot, String poster,
			String rating, String runtime) {
		super();
		this.title = title;
		this.year = year;
		this.country = country;
		this.genre = genre;
		this.director = director;
		this.writer = writer;
		this.casts = casts;
		this.plot = plot;
		this.poster = poster;
		this.rating = rating;
		this.runtime = runtime;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCasts() {
		return casts;
	}

	public void setCasts(String casts) {
		this.casts = casts;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public Long getId() {
		return id;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", country=" + country + ", genre=" + genre
				+ ", director=" + director + ", writer=" + writer + ", casts=" + casts + ", plot=" + plot + ", poster="
				+ poster + ", rating=" + rating + ", runtime=" + runtime + "]";
	}
}
