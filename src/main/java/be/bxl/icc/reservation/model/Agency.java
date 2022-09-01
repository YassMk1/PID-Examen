package be.bxl.icc.reservation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="agencies")
public class Agency {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		
	@NotEmpty(message = "The agency name must not be empty.")
	@Size(min=2, max=60, message = "The agency name  must be between 2 and 60 characters long.")
	private String name;
	

	@OneToMany(targetEntity=Artist.class, mappedBy="agency")
	private List<Artist> artists = new ArrayList<>();

	public Agency () {}
	
	public List<Artist> getArtists() {
		return artists;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
