package com.arun.springsecuritycoreclient.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author arun on 8/30/20
 */

@Getter
@Setter
public class Student {
    private Long id;
    private String name;
    private String standard;
    private Integer age;
}
