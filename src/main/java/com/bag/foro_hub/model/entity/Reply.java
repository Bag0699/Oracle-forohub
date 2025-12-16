package com.bag.foro_hub.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "replies")
public class Reply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String message;

  @Column(name = "creation_date")
  private LocalDate creationDate = LocalDate.now();

  @Column(name = "is_solution")
  private Boolean isSolution = false;

  @ManyToOne
  @JoinColumn(name = "topic_id")
  private Topic topic;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
