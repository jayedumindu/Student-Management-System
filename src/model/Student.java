package model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String SId;
    private String NIC;
    private String SName;
    private String eMail;
    private String contact;
    private String Address;
}
