package com.example.todolist.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        logger.error("Une erreur est survenue : ", e);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Une erreur est survenue : " + e.getMessage());
        mav.setViewName("error");
        return mav;
    }
}