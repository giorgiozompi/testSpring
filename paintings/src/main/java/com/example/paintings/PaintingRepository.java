package com.example.paintings;

import org.springframework.data.jpa.repository.JpaRepository;

// Questa è un'interfaccia che estende JpaRepository e definisce le operazioni CRUD per la classe Painting.
// JpaRepository fornisce metodi predefiniti per eseguire operazioni di persistenza sui dati, come salvataggio, ricerca e eliminazione.
public interface PaintingRepository extends JpaRepository<Painting, Long> {
}
