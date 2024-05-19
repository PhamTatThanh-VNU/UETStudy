package com.example.uetstudy.library.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment", uniqueConstraints = @UniqueConstraint(columnNames = "cmt_id"))
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_id")
    private Long cmtId;

    @Column(name = "cmt_content")
    private String cmtContent;


    @Column(name = "date_created")
    @CreationTimestamp
    private Timestamp dateCreated;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;



}
