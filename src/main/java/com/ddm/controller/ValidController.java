package com.ddm.controller;

import com.ddm.dto.Role;
import com.ddm.model.UserDomain;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by yunpeng.song on 6/15/2018.
 */

@Controller
@RequestMapping(value = "/user")
public class ValidController {
    private static Logger logger = LoggerFactory.getLogger(ValidController.class);

    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);

        //Print:
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            logger.info("==========Error:" + constraintViolation.getMessage());
            logger.info("==========Field:"+constraintViolation.getPropertyPath().toString());
        }

        if (constraintViolations.size() > 0) {
            throw new BuzException(422, String.format("Error parameter:%s", constraintViolations.iterator().next().getMessage()));
        }

    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @PostMapping("/check")
    public ResponseObj addUser(Role role) {
        try {
            validate(role);
        } catch (BuzException e) {
            return e;
        }
        return new ResponseVO(200, "OK");
    }

}
