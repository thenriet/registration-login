package com.example.registrationlogindemo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.registrationlogindemo.entity.Prospect;
import com.example.registrationlogindemo.repository.ProspectRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
  
@Service
public class ProspectServiceImpl implements ProspectService{
	
	@Autowired
    private ProspectRepository pr;

	@Override
	public Prospect saveProspect(Prospect prospect) {
		// TODO Auto-generated method stub
		return pr.save(prospect);
	}

	@Override
	public List<Prospect> fetchProspectList() {
		// TODO Auto-generated method stub
		return (List<Prospect>) pr.findAll();
	}
	
	public Optional<Prospect> fetchProspect(long id) {
		// TODO Auto-generated method stub
		return  pr.findById(id);
	}

	@Override
	public Prospect updateProspect(Prospect prospect, long prospectId) {
        Prospect proDB = pr.findById(prospectId).get();
  
        if (Objects.nonNull(prospect.getName()) && !"".equalsIgnoreCase(prospect.getName())) {
        	proDB.setName(prospect.getName());
        }
        
        if (Objects.nonNull(prospect.getSiret()) && !"".equalsIgnoreCase(prospect.getSiret())) {
        	proDB.setSiret(prospect.getSiret());
        }
        
  
        if (Objects.nonNull(prospect.getAddress()) && !"".equalsIgnoreCase(prospect.getAddress())) {
        	proDB.setAddress(prospect.getAddress());
        }
        
        if (Objects.nonNull(prospect.getZip()) && !"".equalsIgnoreCase(prospect.getZip())) {
        	proDB.setZip(prospect.getZip());
        }
        
        if (Objects.nonNull(prospect.getCity()) && !"".equalsIgnoreCase(prospect.getCity())) {
        	proDB.setCity(prospect.getCity());
        }
  
        return pr.save(proDB);
    }

	@Override
	public void deleteProspectById(long prospectId) {
		// TODO Auto-generated method stub
		pr.deleteById(prospectId);
	}
	
	public void updateScanner() {
		Scanner myObj = new Scanner(System.in);
		
		List<Prospect> prospects = this.fetchProspectList();
		System.out.println(prospects.toString());
		
		System.out.println("Enter id:");
		int id = myObj.nextInt();
		myObj.nextLine();
		
		System.out.println("Enter name:");
		String name = myObj.nextLine();

	    System.out.println("Enter siret:");
	    String siret = myObj.nextLine();
	    
	    System.out.println("Enter address:");
	    String address = myObj.nextLine();
	    
	    System.out.println("Enter zip:");
	    String zip = myObj.nextLine();
	    
	    System.out.println("Enter city:");
	    String city = myObj.nextLine();
	    
	    Prospect newProspect = new Prospect(name, siret, address, zip, city);
	    
	    this.updateProspect(newProspect, id);
		System.out.println("Updated list");
		myObj.close();
	}

}
