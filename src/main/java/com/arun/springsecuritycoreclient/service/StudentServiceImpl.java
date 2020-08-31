package com.arun.springsecuritycoreclient.service;

import com.arun.springsecuritycoreclient.client.StudentHttpClient;
import com.arun.springsecuritycoreclient.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author arun on 8/30/20
 */

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentHttpClient studentHttpClient;

    public StudentServiceImpl(StudentHttpClient studentHttpClient) {
        this.studentHttpClient = studentHttpClient;
    }

    @Override
    public List<Student> getStudent(String name) {
        return studentHttpClient.getSpringSecurityCore(name);
    }
}
