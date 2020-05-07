package com.controller;

import com.dao.ClothesDAO;
import com.dao.CombinationDAO;
import com.dao.PrintDAO;
import com.model.Clothes;
import com.model.Combination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/combination")
public class CombinationController {
    @Autowired
    private CombinationDAO combinationDAO;
    @Autowired
    private ClothesDAO clothesDAO;
    @Autowired
    private PrintDAO printDAO;


    //Полный список одежды
    @RequestMapping(value = "/listCombinations", method = RequestMethod.GET)
    public ModelAndView listCombinations(ModelAndView model) {
        List<Combination> listCombinations = combinationDAO.combinationList();
        model.addObject("listCombinations", listCombinations);
        model.setViewName("clientViews/listCombinations");

        return model;
    }
}