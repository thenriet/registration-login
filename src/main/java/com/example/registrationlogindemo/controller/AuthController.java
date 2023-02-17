package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.service.ProspectServiceImpl;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Prospect;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.form.ProspectForm;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {

    private UserService userService;
    
    @Autowired
   	private ProspectServiceImpl psi;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

	@GetMapping({"/", "/index"})
	public String index(Model model) {

		return "index";
	}

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    // Prospects part

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;
	
	@GetMapping({"/prospectList"})
	public String prospectList(Model model) {

		model.addAttribute("prospects", psi.fetchProspectList());

		return "prospectList";
	}

	@GetMapping({"/addProspect"})
	public String showAddProspectPage(Model model) {

		ProspectForm prospectForm = new ProspectForm();
		model.addAttribute("prospectForm", prospectForm);

		return "addProspect";
	}

	@PostMapping({"/addProspect"})
	public String saveProspect(Model model, //
			@ModelAttribute("prospectForm") ProspectForm prospectForm) {

		String name = prospectForm.getName();
		String siret = prospectForm.getSiret();
		String address = prospectForm.getAddress();
		String zip = prospectForm.getZip();
		String city = prospectForm.getCity();

		if (name != null && name.length() > 0 //
				&& siret != null && siret.length() > 0 //
				&& address != null && address.length() > 0 //
				&& zip != null && zip.length() > 0 //
				&& city != null && city.length() > 0) {
			Prospect newProspect = new Prospect(name, siret, address, zip, city);
			psi.saveProspect(newProspect);

			return "redirect:/prospectList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addProspect";
	}

	@GetMapping("/delete/{id}")
	public String deleteProspect(@PathVariable("id") long id, Model model) {
		psi.deleteProspectById(id);
	    return "redirect:/prospectList";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Optional<Prospect> prospect = psi.fetchProspect(id);	    
	    model.addAttribute("prospect", prospect);
	    return "/updateProspect";
	}
	
	@PostMapping("/update/{id}")
	public String updateProspect(@PathVariable("id") long id, Prospect prospect, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        prospect.setId(id);
	        return "updateProspect";
	    }
	   
	    psi.updateProspect(prospect, id);
	    return "redirect:/index";
	}
}
