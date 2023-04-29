package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Fund;
import net.javaguides.springboot.model.Admin;
import net.javaguides.springboot.repository.AdminRepository;
import net.javaguides.springboot.service.FundService;

import net.javaguides.springboot.Factory.FundFactory;

@Controller
public class FundController {

	@Autowired
	private FundService fundService;

	@Autowired
	private AdminRepository adminRepository;
	

	// login
	@GetMapping("/")
	public String viewLoginPage(Model model) {
		model.addAttribute("user", new Admin());
		return "login_form";
	}

	// process login
	@PostMapping("/process_login")
	public String ProcessLogin(Admin admin) {
		String email = admin.getEmail();
		String password = admin.getPassword();
		Admin result = adminRepository.validate(email, password);
		if (result==null){
			return "redirect:/";
		}
		else{
			return "redirect:/index";
		}
	}

	// display list of Funds
	@GetMapping("/index")
	public String viewHomePage(Model model) {
		return findPaginated(1, "name", "asc", model);		
	}
	// factory is used to create various objects by hiding the object creation logic 
	@GetMapping("/showNewFundForm")
	public String showNewFundForm(Model model) {
		// create model attribute to bind form data
		FundFactory fact = new FundFactory();
		Fund fund = fact.createFund("Fund");
		model.addAttribute("fund", fund);
		return "new_fund";
	}
	
	@PostMapping("/saveFund")
	public String saveFund(@ModelAttribute("fund") Fund fund) {
		// save fund to database
		fundService.saveFund(fund);
		return "redirect:/index";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Fund from the service
		Fund fund = fundService.getFundById(id);
		
		// set fund as a model attribute to pre-populate the form
		model.addAttribute("fund", fund);
		return "update_fund";
	}
	
	@GetMapping("/deleteFund/{id}")
	public String deleteFund(@PathVariable (value = "id") long id) {
		
		// call delete fund method 
		this.fundService.deleteFundById(id);
		return "redirect:/index";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Fund> page = fundService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Fund> listFunds = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listFunds", listFunds);
		return "index";
	}
}
