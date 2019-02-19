package com.myaudiolibrary.apirest.repository;

import com.myaudiolibrary.apirest.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album,Long> {

    Album findByTitle(String title);
}


/*@RestController
@RequestMapping(value = "/vehicule")
public class VehiculeController{
    @Autowired
    private VehiculeRepository vehiculerepository;

    @RequestMapping("/{id}")
    public Vehicule findById(@PathVariable("id) Long id){
        return vehiculerepository.findById(id);
    }
}*/