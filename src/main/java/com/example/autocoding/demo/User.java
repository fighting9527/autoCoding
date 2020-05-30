package com.example.autocoding.demo;

/**
 * @author caoqi
 * 2020/5/29 3:05 PM
 */
@Table("USER")
public class User {
    @PrimaryKey
    @Column(value = "ID", type = "bigint")
    private Long id;
    @Column(value = "NAME", type = "varchar(20)")
    private String name;
    @Column(value = "GENDER", type = "tinyint")
    private int gender;
    @Column(value = "AGE", type = "tinyint")
    private int age;
    @Column(value = "PHONE", type = "varchar(30)")
    private String phone;
}
