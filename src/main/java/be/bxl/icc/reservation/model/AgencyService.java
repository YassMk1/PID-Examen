package be.bxl.icc.reservation.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {
	@Autowired
	private AgencyRepository agencyRepository;
		
	public List<Agency> getAll() {
		List<Agency> agencies = new ArrayList<>();
		
		agencyRepository.findAll().forEach(agencies::add);
		
		return agencies;
	}
	
	public Agency getAgency(String id) {
		int indice = Integer.parseInt(id);
		return agencyRepository.findById(indice);
	}

	public void addAgency(Agency agency) {
		agencyRepository.save(agency);
	}

	public void updateAgency(Long long1, Agency agency) {
		agencyRepository.save(agency);
	}

	public void deleteAgency(Long indice2) {
		Long indice = indice2;
		
		agencyRepository.deleteById(indice);
	}
	
}

