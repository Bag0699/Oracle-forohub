package com.bag.foro_hub.repository;

import com.bag.foro_hub.model.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TopicRepository extends JpaRepository<Topic, Long> {
  boolean existsByTitleAndMessage(String title, String message);

  boolean existsByTitleAndMessageAndIdNot(String title, String message, Long id);

  @Query(
"""
SELECT t FROM Topic t
WHERE t.course.id = :courseId
AND t.creationDate BETWEEN :startDate AND :endDate
""")
  Page<Topic> findByCourseAndYear(
      @Param("courseId") Long courseId,
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate,
      Pageable pageable);
}
