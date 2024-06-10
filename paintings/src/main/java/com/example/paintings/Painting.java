package com.example.paintings;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Painting {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    private String title; 
    private String author; 
    private int year; 

    
   public String getAuthor() {
       return author;
   }public Long getId() {
       return id;
   }public String getTitle() {
       return title;
   }public int getYear() {
       return year;
   }public void setAuthor(String author) {
       this.author = author;
   }public void setId(Long id) {
       this.id = id;
   }public void setTitle(String title) {
       this.title = title;
   }public void setYear(int year) {
       this.year = year;
   }
}