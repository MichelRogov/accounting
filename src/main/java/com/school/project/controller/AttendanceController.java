package com.school.project.controller;

import com.school.project.dto.AttendanceDto;
import com.school.project.model.entities.Attendance;
import com.school.project.service.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/attendance")
    public ResponseEntity<AttendanceDto> create (@RequestBody AttendanceDto attendanceDto){
        return ResponseEntity.ok()
                .body(convertAttendanceToAttendanceDto(attendanceService
                .create(convertAttendanceDtoToAttendance(attendanceDto))));
    }

    @PutMapping("attendance/{id}")
    public void update (@RequestBody AttendanceDto attendanceDto, @PathVariable Long id){
        attendanceService.update(convertAttendanceDtoToAttendance (attendanceDto), id);
        ResponseEntity.ok().build();
    }

    private Attendance convertAttendanceDtoToAttendance (AttendanceDto attendanceDto){
        return modelMapper.map(attendanceDto, Attendance.class);
    }

    private AttendanceDto convertAttendanceToAttendanceDto (Attendance attendance){
        return modelMapper.map(attendance, AttendanceDto.class);
    }
}
