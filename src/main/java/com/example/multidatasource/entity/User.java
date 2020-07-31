package com.example.multidatasource.entity;

import lombok.Data;

/**
 * @author : dengchao
 */
@Data
public class User extends BaseDomain {

    private Integer id;

    private String name;

    private Integer age;

    private String email;
}
