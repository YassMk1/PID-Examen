package be.bxl.icc.reservation.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
    List<Artist> findByLastname(String lastname);

    List<Artist> findAll();

    Artist findById(long id);
    
	List<Artist> findByAgency(Agency agency);
}
