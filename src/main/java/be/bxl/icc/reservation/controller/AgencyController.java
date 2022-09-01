package be.bxl.icc.reservation.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import be.bxl.icc.reservation.model.Agency;
import be.bxl.icc.reservation.model.AgencyService;
import be.bxl.icc.reservation.model.Artist;


@Controller
public class AgencyController {
	@Autowired
	AgencyService agencyService;

	@GetMapping("/agencies")
    public String index(Model model) {
		List<Agency> agencies = agencyService.getAll();

		model.addAttribute("agencies", agencies);
		model.addAttribute("title", "Liste des agences");
		
        return "agency/index";
    }
	
	@GetMapping("/agencies/{id}")
    public String show(Model model, @PathVariable("id") String id) {
	Agency agency = agencyService.getAgency(id);

	model.addAttribute("agency", agency);
	model.addAttribute("title", "Fiche d'une agence");
		
        return "agency/show";
    }
	
	@GetMapping("/agencies/{id}/edit")
	public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {
		Agency agency = agencyService.getAgency(id);

		model.addAttribute("agency", agency);
		
		//Générer le lien retour pour l'annulation
		String referrer = request.getHeader("Referer");

		if(referrer!=null && !referrer.equals("")) {
			model.addAttribute("back", referrer);
		} else {
			model.addAttribute("back", "/agencies/"+agency.getId());
		}
		
		return "agency/edit";
	}
	
	@PutMapping("/agencies/{id}/edit")
	public String update(@Valid @ModelAttribute("agency") Agency agency, BindingResult bindingResult, @PathVariable("id") String id, Model model) {
	    
		if (bindingResult.hasErrors()) {
			return "agency/edit";
		}
		
		Agency existing = agencyService.getAgency(id);
		
		if(existing==null) {
			return "agency/index";
		}
		
		Long indice = (long) Integer.parseInt(id);
		
		agency.setId(indice);
	    agencyService.updateAgency(agency.getId(), agency);
	    
		model.addAttribute("agency", agency);
	    
		return "redirect:/agencies/"+agency.getId();
	}

	@DeleteMapping("/agencies/{id}")
	public String delete(@PathVariable("id") String id, Model model) {
	    Agency existing = agencyService.getAgency(id);
		
	    if(existing!=null) {
		Long indice = (long) Integer.parseInt(id);
		
	    	agencyService.deleteAgency(indice);
	    }
	    	    
	    return "redirect:/agencies";
	}
	
	@GetMapping("/agencies/create")
	  public String create(Model model) {
	    model.addAttribute("agency", new Agency());
	    
	    return "agency/create";
	  }
	
	@PostMapping("/agencies/create")
	  public String store(@Valid @ModelAttribute("agency") Agency agency, BindingResult bindingResult, Model model) {

	    if (bindingResult.hasErrors()) {
	      return "agency/create";
	    }

	    agencyService.addAgency(agency);

	    return "redirect:/agencies/" + agency.getId();
	  }

}
