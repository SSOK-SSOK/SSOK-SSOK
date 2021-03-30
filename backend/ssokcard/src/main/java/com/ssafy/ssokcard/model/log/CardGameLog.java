//package com.ssafy.ssokcard.model;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Builder
//@ToString
//@Setter
//@Getter
//@Entity
//public class CardGameLog {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @CreationTimestamp
//    private LocalDateTime date;
//
//    private int score;
//
//    @ManyToOne
//    @JoinColumn(name = "users_id")
//    private User user;
//}
