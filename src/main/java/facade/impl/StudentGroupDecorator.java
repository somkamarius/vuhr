package facade.impl;

import model.Student;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;

@Decorator
public class StudentGroupDecorator implements StudentGroup {

    @Inject
    @Delegate
    @Any
    StudentGroup studentGroup;


    @Override
    public void addStudent(Student student, Long specialityId, List<Long> subjectIds) {
        studentGroup.addStudent(student, specialityId, subjectIds);

        if (student.getStudentGroup() > 5) {
            System.out.println("Student group should be in interval 1-5, not " + student.getStudentGroup());
        }
    }
}
