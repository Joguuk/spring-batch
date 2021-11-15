package me.jjozerg.baseballbatch.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subject;

//    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
//    private List<Student> students = new ArrayList<>();
//
//    @Builder
//    public Teacher(String name, String subject) {
//        this.name = name;
//        this.subject = subject;
//    }
//
//    public void addStudent(Student student){
//        students.add(student);
//        student.setTeacher(this);
//    }
}
