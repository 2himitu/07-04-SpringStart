package com.mysite.sbb.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String title;
    private String body;
    private long userId;
}
