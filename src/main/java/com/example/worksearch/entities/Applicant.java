package com.example.worksearch.entities;

import com.example.worksearch.entities.enums.ContactType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    @Getter @Setter
    private String email;

    @Column(name = "first_name", nullable = false)
    @Getter @Setter
    private String firstName;

    @Column(name = "second_name", nullable = false)
    @Getter @Setter
    private String secondName;

    @Column(name = "patronymic") // У гражданина РФ может не быть отчетства, допускаем null как валидное значение
    @Getter @Setter
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @Getter @Setter
    private City city;

    @Column(name = "contact")
    @Setter
    private String contact;

    @Column(name = "contact_type")
    @Setter
    private ContactType contactType;

    public String getContact(){
        if (this.contact == null){
            return this.email;
        }
        return contact;
    }

    public ContactType getContactType(){
        if (this.contactType == null){
            return ContactType.EMAIL;
        }
        return this.contactType;
    }
}