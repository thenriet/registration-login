package com.example.registrationlogindemo.service;


import java.util.List;

import com.example.registrationlogindemo.entity.Prospect;

  
public interface ProspectService {
    // save operation
    Prospect saveProspect(Prospect prospect);
  
    // read operation
    List<Prospect> fetchProspectList();
  
    // update operation
    Prospect updateProspect(Prospect prospect, long prospectId);
  
    // delete operation
    void deleteProspectById(long prospectId);
}
