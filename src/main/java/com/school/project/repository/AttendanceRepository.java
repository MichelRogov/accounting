package com.school.project.repository;

import com.school.project.model.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> getAllByUserId (Long id);

    List<Attendance> getAllByLessonId (Long id);

}
