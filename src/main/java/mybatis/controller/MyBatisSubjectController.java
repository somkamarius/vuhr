package mybatis.controller;

import lombok.Getter;
import lombok.Setter;
import mybatis.mapper.SubjectMapper;
import mybatis.model.Subject;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
@Getter
@Setter
public class MyBatisSubjectController implements Serializable {
    private static final long serialVersionUID = -8485790918752453661L;

    @Inject
    private SubjectMapper subjectMapper;

    private Subject subjectToAdd = new Subject();
    private List<Subject> availableSubjects;
    private Subject subjectToEdit = new Subject();

    @PostConstruct
    void init() {
        this.availableSubjects = subjectMapper.selectAll();
    }

    @Transactional
    public String updateSubject() {
        subjectMapper.updateByPrimaryKey(subjectToEdit);
        return "/myBatis/vuhr?faces-redirect=true";
    }

    @Transactional
    public String addNewSubject() {
        subjectMapper.insert(subjectToAdd);
        return "/myBatis/vuhr?faces-redirect=true";
    }
}