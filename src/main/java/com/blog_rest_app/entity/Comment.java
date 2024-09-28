package com.blog_rest_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "user")
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="content")
    private String content;


    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="post_id")
    @JsonIgnore
    private Post post;
}
