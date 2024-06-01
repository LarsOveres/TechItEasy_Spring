package org.example.techiteasy.controllers;

import org.example.techiteasy.models.Television;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
@RequestMapping("/televisions")
public class TelevisionController {

    private ArrayList<Television> televisions = new ArrayList<>();
    ;

    @GetMapping
    public ResponseEntity<ArrayList<Television>> getAllTelevisions() {
        return ResponseEntity.ok(televisions);
    }

    @PostMapping
    public ResponseEntity<Television> createTelevision(@RequestBody Television television) {
        televisions.add(television);
        return ResponseEntity.created(null).body(television);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody Television updatedTelevision) {
        if (id >= 0 && id < televisions.size()) {
            televisions.set(id, updatedTelevision);
            return ResponseEntity.ok(updatedTelevision);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
