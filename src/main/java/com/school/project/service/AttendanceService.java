package com.school.project.service;

import com.school.project.model.entities.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AttendanceService {
    Attendance create (Attendance attendance);

    void update (Attendance attendance, Long id);

    Attendance filterByLesson (Long id);

    List<Attendance> filterByUser (Long id);

    List<Attendance> filterByGroup (Long id);

    Attendance getAttendnceById (long id);
}
