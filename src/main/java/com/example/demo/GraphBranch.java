package com.example.demo;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class GraphBranch implements Serializable {

    private String name;
    private Date endDate;
    private Date startDate;
}
