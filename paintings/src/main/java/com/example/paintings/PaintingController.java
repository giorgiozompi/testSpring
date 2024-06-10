package com.example.paintings;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
public class PaintingController {
    // L'annotazione @Controller indica che questa classe è un controller Spring MVC, che gestisce le richieste HTTP.
    
    @Autowired
    private PaintingRepository paintingRepository; // Il paintingRepository è una classe che gestisce l'accesso ai dati delle paintings nel database.
    // L'annotazione @Autowired indica a Spring di iniettare automaticamente una istanza di paintingRepository in questa classe.
    
    @Autowired
    private PaintingService paintingService; // Il paintingService è una classe che contiene la logica di business relativa alle paintings.
    // L'annotazione @Autowired indica a Spring di iniettare automaticamente una istanza di paintingService in questa classe.

    @GetMapping("/")
    public String home(Model model) {
        // Questo metodo gestisce le richieste HTTP GET alla radice del sito ("/").
        
        // Il parametro Model rappresenta il modello dati che viene passato alla vista.
        // La vista può utilizzare il modello per accedere ai dati e visualizzarli.
        
        // Recupera tutte le paintings presenti nel database utilizzando il paintingRepository.
        // Queste paintings vengono quindi aggiunte al modello con il nome "paintings".
        model.addAttribute("paintings", paintingRepository.findAll());
        
        // Restituisce una stringa che rappresenta il nome della vista da visualizzare all'utente.
        // In questo caso, restituisce "Home" per visualizzare la home page.
        return "Home";
    }

    @GetMapping("/aggiungi")
    public String mostraFormAggiungi(Model model) {
        // Questo metodo gestisce le richieste HTTP GET per visualizzare il form per aggiungere una nuova painting ("/aggiungi").
        
        // Aggiunge una nuova istanza vuota di painting al modello.
        // Questo permette all'utente di compilare i dettagli della nuova painting nel form.
        model.addAttribute("painting", new Painting());
        
        // Restituisce il nome della vista "Aggiungi" da visualizzare all'utente.
        return "Aggiungi";
    }

    @PostMapping("/aggiungi")
    public String aggiungipainting(@ModelAttribute Painting painting) {
        // Questo metodo gestisce le richieste HTTP POST per aggiungere una nuova painting ("/aggiungi").
        
        // Salva la nuova painting nel database utilizzando il paintingRepository.
        paintingRepository.save(painting);
        
        // Dopo aver aggiunto la painting, reindirizza l'utente alla pagina principale ("/").
        return "redirect:/";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Painting> updatepainting(@PathVariable Long id, @RequestBody Painting paintingDetails) {
        // Questo metodo gestisce le richieste HTTP PUT per aggiornare una painting esistente ("/{id}").
        
        // Cerca la painting nel database utilizzando il paintingService.
        Optional<Painting> paintingOptional = paintingService.findById(id);
        
        // Verifica se la painting è stata trovata.
        if (paintingOptional.isPresent()) {
            // Se la painting è stata trovata, ottieni l'oggetto painting.
            Painting painting = paintingOptional.get();
            
            // Aggiorna i dettagli della painting con quelli forniti nel corpo della richiesta.
            painting.setTitle(paintingDetails.getTitle());
            painting.setAuthor(paintingDetails.getAuthor());
            painting.setYear(paintingDetails.getYear());
            
            // Salva la painting aggiornata utilizzando il paintingService.
            return ResponseEntity.ok(paintingService.save(painting));
        } else {
            // Se la painting non è stata trovata, restituisci un errore 404.
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletepainting(@PathVariable Long id) {
        // Questo metodo gestisce le richieste HTTP DELETE per eliminare una painting esistente ("/{id}").
        
        // Cerca la painting nel database utilizzando il paintingService.
        Optional<Painting> painting = paintingService.findById(id);
        
        // Verifica se la painting è stata trovata.
        if (painting.isPresent()) {
            // Se la painting è stata trovata, elimina la painting utilizzando il paintingService.
            paintingService.deleteById(id);
            
            // Restituisci una risposta 204 (No Content) per indicare successo senza contenuto.
            return ResponseEntity.noContent().build();
        } else {
            // Se la painting non è stata trovata, restituisci un errore 404.
            return ResponseEntity.notFound().build();
        }
    }
}