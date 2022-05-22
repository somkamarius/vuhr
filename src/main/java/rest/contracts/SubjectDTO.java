package rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubjectDTO {

    private String Name;
    private String Lecturer;
    private String Description;
    private int CreditAmount;
}