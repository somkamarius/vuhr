package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Subject.findAll", query = "select i from Subject as i"),
        @NamedQuery(name = "Subject.getById", query = "select i from Subject as i where i.id = :id"),
})
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "LECTURER", nullable = false)
    private String lecturer;
    @Column(name = "CREDIT_AMOUNT", nullable = false)
    private int creditAmount;

    @ManyToMany(mappedBy = "subjects")
    @Column(name = "STUDENT_ID")
    private List<Student> students;
}
