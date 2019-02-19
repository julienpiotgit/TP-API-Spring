package com.myaudiolibrary.apirest.controller;

import com.myaudiolibrary.apirest.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.myaudiolibrary.apirest.repository.ArtistRepository;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping("/{id}")
    public Artist findById(@PathVariable("id") Long id) {
        Artist a = artistRepository.findOne(id);
        if (a == null) {
            throw new EntityNotFoundException("L'artiste "+ id + " n'hesite pas donc ne peut être affiché.");
        } else {
            return a;
        }
    }

    @RequestMapping(params = {"name","page","size","sortProperty","sortDirection"})
    public Page<Artist> findByName(@RequestParam("name") String name,
                                   @RequestParam("page") Integer page,
                                   @RequestParam("size") Integer size,
                                   @RequestParam("sortProperty") String sortProperty,
                                   @RequestParam("sortDirection") String sortDirection)  {
        Pageable pageRequest = new PageRequest(page,size, Sort.Direction.fromString(sortDirection),sortProperty);
        Page<Artist> a = artistRepository.findByNameContainingIgnoreCase(name,pageRequest);
        if(artistRepository.findByName(name) == null){
            throw new EntityNotFoundException("Le nom " + name + " n'exite pas");
        } else{
            return a;
        }
        /*affiche l'erreur dans le mode console du navigateur*/
    }

    @RequestMapping(params = {"page","size","sortProperty","sortDirection"})
    public Page<Artist> listArtist(@RequestParam("page") Integer page,
                                      @RequestParam("size") Integer size,
                                      @RequestParam("sortProperty") String sortProperty,
                                      @RequestParam("sortDirection") String sortDirection) {
        PageRequest pageRequest = new PageRequest(page,size, Sort.Direction.fromString(sortDirection),sortProperty);
        return artistRepository.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public Artist createArtist(@RequestBody Artist artist){
        if(artistRepository.findByName(artist.getName()) == artistRepository.findByName(artist.getName())){
            throw new IllegalArgumentException("Le nom existe déjà");
        }/*else if (artistRepository.findByName(artist.getName()) == null){
            throw new IllegalArgumentException("Veuillez rentrer un nom d'artiste");
        }*/
        return artistRepository.save(artist);
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json",
            value = "/{id}")
    public Artist updateArtist(@PathVariable Long id, @RequestBody Artist artist){
        return artistRepository.save(artist);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Long id){
        if(artistRepository.findOne(id) == null){
            throw new EntityNotFoundException("L'artist " + id + " n'a pas été trouvé");
        }
        artistRepository.delete(id);
    }
}