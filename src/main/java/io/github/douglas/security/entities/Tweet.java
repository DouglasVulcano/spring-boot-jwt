package io.github.douglas.security.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String text;

    private User author;

    @CreationTimestamp 
    private Instant createdAt; 
}
