package com.controller;

import com.dao.ClothesDAO;
import com.model.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("/clothes")
public class ClothesController {

    @Autowired
    private ClothesDAO clothesDAO;


    //Полный список одежды
    @RequestMapping(value = {"/listClothes", "/"}, method = RequestMethod.GET)
    public ModelAndView listClothesClient(ModelAndView model){
        List<Clothes> listClothes = clothesDAO.clothesList();
        model.addObject("clothesType", clothesDAO.getClothesTypes());
        model.addObject("listClothes", listClothes);
        model.setViewName("clientViews/listClothes");

        return model;
    }


    //Список одежды по выбранному типу
    @RequestMapping(value = "/listClothesByType", method = RequestMethod.GET)
    public ModelAndView listClothesClientByType(HttpServletRequest request, ModelAndView model){
        String type = request.getParameter("type");
        List<Clothes> listClothesByType = clothesDAO.getClothesByType(type);
        model.addObject("clothesType", clothesDAO.getClothesTypes());
        model.addObject("listClothes", listClothesByType);
        model.setViewName("clientViews/listClothes");

        return model;
    }
}
