package be.bxl.icc.reservation.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AgencyRepository extends CrudRepository<Agency, Long> {
	Agency findById(long id);
	
	List<Agency> findAll();

	List<Artist> findAllById(Agency agency);
	
	Agency findByName(String name);
}
