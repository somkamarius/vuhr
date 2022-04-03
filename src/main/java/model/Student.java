package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Student {

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.year = 1;
        this.studentGroup = 1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "SURNAME", nullable = false)
    private String surname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "YEAR", nullable = false)
    private int year;
    @Column(name = "STUDENT_GROUP", nullable = false)
    private int studentGroup;

    @ManyToMany
    @JoinTable(name = "subjects", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @Column(name="SUBJECT_ID")
    private List<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality studentSpeciality;
}
