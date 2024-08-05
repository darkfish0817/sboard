package com.indielab.demo.sboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 2, max = 50, message = "제목은 2자 이상 50자 미만이어야 합니다")
    private String title;
    private String content;
    private String ins_usr;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;
}
