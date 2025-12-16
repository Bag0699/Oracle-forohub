package com.bag.foro_hub.model.entity;

import com.bag.foro_hub.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String message;

  @Column(name = "creation_date")
  private LocalDate creationDate = LocalDate.now();

  @Enumerated(EnumType.STRING)
  private Status status = Status.OPEN;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reply> replies = new ArrayList<>();
}
