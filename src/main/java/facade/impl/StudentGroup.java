package facade.impl;

import model.Student;

import java.util.List;

public interface StudentGroup {
    void addStudent(Student student, Long specialityId, List<Long> subjectIds);
}
