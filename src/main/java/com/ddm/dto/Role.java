package com.ddm.dto;

import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * Created by yunpeng.song on 6/15/2018.
 */

@Data
public class Role implements java.io.Serializable {
//https://docs.oracle.com/javaee/7/tutorial/bean-validation001.htm


    @Min(0)
    @Max(100)
    private int roleId;

    @NotNull(message = "Can't be null")
    @Size(min=2, max=16)
    private String roleName;

    @Email(message = "Should be email.")
    private String email;

    @NotNull
    private String description;


    private int type;

    @DecimalMin("1.11")
    @DecimalMax("3.60")
    private double rate;

    @Pattern(regexp="\\d{11}")
    private String phoneNumber;

    @NotNull(message = "Date can't be null")
    @Past(message = "Only past data allowed.")
    private Date createdDate;
    private int createdBy;

    private Date updatedDate;
    private int updatedBy;

}
