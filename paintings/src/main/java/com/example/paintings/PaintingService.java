package com.example.paintings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaintingService {
    // L'annotazione @Service indica che questa classe è un componente di servizio gestito da Spring.

    @Autowired
    private PaintingRepository paintingRepository; // Il paintingRepository è utilizzato per accedere ai dati delle ricette nel database.

    // Questo metodo restituisce tutte le ricette presenti nel database.
    public List<Painting> findAll() {
        return paintingRepository.findAll();
    }

    // Questo metodo restituisce una painting dal database in base al suo ID.
    public Optional<Painting> findById(Long id) {
        return paintingRepository.findById(id);
    }

    // Questo metodo salva una nuova painting nel database o aggiorna una painting esistente.
    public Painting save(Painting painting) {
        return paintingRepository.save(painting);
    }

    // Questo metodo elimina una painting dal database in base al suo ID.
    public void deleteById(Long id) {
        paintingRepository.deleteById(id);
    }
}