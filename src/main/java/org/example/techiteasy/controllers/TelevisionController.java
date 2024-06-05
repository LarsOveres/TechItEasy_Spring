package org.example.techiteasy.controllers;

import org.example.techiteasy.exceptions.RecordNotFoundException;
import org.example.techiteasy.models.Television;
import org.example.techiteasy.repositories.TelevisionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Notities:
@RestController zorgt ervoor dat Spring dit ziet als MVC Controller, zodat hij de HTTP-verzoeken af kan handelen binnen deze @RestController.
@RequestMapping laat je een specifieke url toewijzen waarop de methode of klasse moet reageren, @RequestMapping kan op elke methode toegepast worden (in dit bestand is /televisions de mapping).
@GetMapping verstuurd de inhoud van de methode wanneer dit pad naar de server aangeroepen wordt (in dit geval naar http://localhost:8080/televisions).
@PostMapping gebruik je om nieuwe data aan te maken en naar de server te versturen (meestal in JSON-formaat).
@PutMapping overschrijft gegevens uit de body, door er bijvoorbeeld "(/{id})" achter te zetten kun je de data aanpassen van de gegevens die op dat id opgeslagen zijn, alle datavelden moeten hiervoor ingevuld zijn bij het opsturen.
@DeleteMapping verwijderd data op de plek die je aangeeft met "(/{id})".
@PatchMapping word gebruikt om, in tegenstelling tot @PutMapping, maar 1 waarde uit de data te wijzigen. Bijvoorbeeld wanneer je een adres wijzigt.
*/


@RestController
public class TelevisionController {

    private final TelevisionRepository repository;

    TelevisionController(TelevisionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/televisions")
    List<Television> all() {
        return repository.findAll();
    }

    @PostMapping("/televisions")
    Television newTelevision(@RequestBody Television newTelevision) {
        return repository.save(newTelevision);
    }

    @GetMapping("/televisions/{id}")
    Television one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @PutMapping("/televisions/{id}")
    Television replaceTelevision(@RequestBody Television newTelevision, @PathVariable Long id) {
        return repository.findById(id)
                .map(television -> {
                    television.setType(newTelevision.getType());
                    television.setBrand(newTelevision.getBrand());
                    television.setPrice(newTelevision.getPrice());
                    television.setAvailableSize(newTelevision.getAvailableSize());
                    television.setRefreshRate(newTelevision.getRefreshRate());
                    television.setScreenType(newTelevision.getScreenType());
                    television.setScreenQuality(newTelevision.getScreenQuality());
                    television.setSmartTv(newTelevision.getSmartTv());
                    television.setWifi(newTelevision.getWifi());
                    television.setVoiceControl(newTelevision.getVoiceControl());
                    television.setHdr(newTelevision.getHdr());
                    television.setBluetooth(newTelevision.getBluetooth());
                    television.setAmbiLight(newTelevision.getAmbiLight());
                    television.setOriginalStock(newTelevision.getOriginalStock());
                    television.setSold(newTelevision.getSold());
                    return repository.save(television);
                })
                .orElseGet(() -> {
                    newTelevision.setId(id);
                    return repository.save(newTelevision);
                });
    }

    @DeleteMapping("television/{id}")
    void deleteTelevision(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
