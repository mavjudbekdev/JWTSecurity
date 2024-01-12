package com.example.book.book.entity;

import com.example.book.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`book`")
public class Book {
    @Id
    private UUID id;
    private String name;
    private String author;
    private String deck;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
