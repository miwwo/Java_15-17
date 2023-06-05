package com.java.java_15.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String lastName;

    @Column(name = "subject")
    private String subject;

    // ЗАДАНИЕ 16
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        return "Teacher{" +
                "id =" + id +
                ", firstName ='" + firstName + '\'' +
                ", lastName ='" + lastName + '\'' +
                ", subject =" + subject +
                ", students =" + students +
                '}';
    }
}
