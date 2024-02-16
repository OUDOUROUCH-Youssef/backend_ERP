package com.example.school.Controllers;

//import com.example.school.Entities.Eleve;
import com.example.school.Entities.Parent;
import com.example.school.Repositories.EleveRepository;
import com.example.school.Repositories.ParentRepository;
import com.example.school.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/parent")
public class ParentController {
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private ParentService parentService;
    @GetMapping("/all")
    public List<Parent> getParents(){
        return parentRepository.findAll();
    }

    @GetMapping("/{parentId}")
    public Parent getParentById(@PathVariable String parentId){
        return parentRepository.findById(parentId).get();
    }

    @PostMapping("/add")
    public Parent addParent(@RequestBody Parent parent){
        return parentRepository.save(parent);
    }

    @PutMapping("/parentId")
    public void updateParent(@RequestBody Parent parent, @PathVariable String parentId){
        parentRepository.findById(parentId).map(parent1 -> {
            parent1.setCIN(parent.getCIN());
            parent1.setLastName(parent.getLastName());
            parent1.setFirstName(parent.getFirstName());
            parent1.setAddress(parent.getAddress());
            parent1.setEmail(parent.getEmail());
            parent1.setDateInscription(parent.getDateInscription());
            parent1.setImage(parent.getImage());

            return parentRepository.save(parent1);
        });
    }

    @DeleteMapping("/parentId")
    public void deleteParent(@PathVariable String parentId){
        parentRepository.deleteById(parentId);
    }
}
