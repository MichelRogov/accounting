package com.school.project.service.impl;

import com.school.project.model.entities.Attendance;
import com.school.project.repository.AttendanceRepository;
import com.school.project.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public Attendance create (Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public void update(Attendance attendance, Long id) {
        attendance.setId(id);
        attendanceRepository.saveAndFlush(attendance);
    }

    @Override
    public Attendance filterByLesson(Long id) {
        return null;
    }

    @Override
    public List<Attendance> filterByUser(Long id) {
        return null;
    }

    @Override
    public List<Attendance> filterByGroup(Long id) {
        return null;
    }
}
