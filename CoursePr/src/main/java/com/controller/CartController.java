package com.controller;

import com.dao.*;
import com.model.*;
import com.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller("/cart")
public class CartController {

    @Autowired
    private ClothesDAO clothesDAO;

    @Autowired
    private TemplateDAO templateDAO;

    @Autowired
    private ImageDAO imageDAO;

    @Autowired
    private PrintDAO printDAO;

    @Autowired
    private CombinationDAO combinationDAO;


    //Отобразить корзину
    @RequestMapping(value = "/cartPage", method = RequestMethod.GET)
    public String viewCart(ModelAndView model, HttpServletRequest request, HttpSession session) {
        List<Item> cart = (List<Item>)session.getAttribute("cart");
        session.setAttribute("cart", cart);

        return "clientViews/cartPage";
    }

    //Добавить в корзину готовую комбинацию
    @RequestMapping(value = "/addCombinationToCart", method = RequestMethod.GET)
    public String addToCart(ModelAndView model, HttpServletRequest request, HttpSession session){
        int combinationId = Integer.parseInt(request.getParameter("id"));
        Combination combination = combinationDAO.getCombinationById(combinationId);
        if(session.getAttribute("cart") == null){
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(combination.getClothes(), combination.getPrint(), 1));
            session.setAttribute("cart", cart);
        } else{
            List<Item> cart = (List<Item>)session.getAttribute("cart");
            int index = exists(combination.getClothes().getId(), combination.getPrint().getTemplate().getId(),
                    combination.getPrint().getImage().getId(), session);
            if (index == -1){
                cart.add(new Item(combination.getClothes(), combination.getPrint(), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "clientViews/cartPage";
    }



    //Выбрать одежду
    @RequestMapping(value = "/chooseImage", method = RequestMethod.GET)
    public ModelAndView chooseImage(ModelAndView model, HttpServletRequest request, HttpSession session){
        List<Image> listImage = imageDAO.imageList();
        model.addObject("listImages", listImage);
        int clothesID = Integer.parseInt(request.getParameter("clothesID"));
        Clothes clothes = clothesDAO.getClothesById(clothesID);
        model.addObject("clothes", clothes);
        model.setViewName("clientViews/listImages");

        return model;
    }

    //Выбрать изображение
    @RequestMapping(value = "/chooseTemplate", method = RequestMethod.GET)
    public ModelAndView chooseTemplate(ModelAndView model, HttpServletRequest request, HttpSession session){
        int clothesID = Integer.parseInt(request.getParameter("clothesID"));
        int imageID = Integer.parseInt(request.getParameter("imageID"));
        Clothes clothes = clothesDAO.getClothesById(clothesID);
        Image image = imageDAO.getImageById(imageID);

        List<Template> listTemplates = templateDAO.templateList();
        model.addObject("listTemplates", listTemplates);

        model.addObject("clothes", clothes);
        model.addObject("image", image);

        model.setViewName("clientViews/listTemplates");

        return model;
    }



    //Добавить созданную пользователем комбинацию
    @RequestMapping(value = "/addCustomCombination", method = RequestMethod.GET)
    public String addCustomCombination(ModelAndView model, HttpServletRequest request, HttpSession session){
        Print savedPrint = null;
        int clothesID = Integer.parseInt(request.getParameter("clothesID"));
        int imageID = Integer.parseInt(request.getParameter("imageID"));
        int templateID = Integer.parseInt(request.getParameter("templateID"));
        Clothes clothes = clothesDAO.getClothesById(clothesID);
        Image image = imageDAO.getImageById(imageID);
        Template template = templateDAO.getTemplateById(templateID);


        int exPrint = printExists(templateID, imageID, printDAO.printList());
        if(exPrint != -1){
            savedPrint = printDAO.getPrintById(exPrint);
        } else {
            Print print = new Print();
            print.setImage(image);
            print.setTemplate(template);
            printDAO.saveOrUpdate(print);

            savedPrint = printDAO.getPrintById(printDAO.getLastID());
        }

        if(session.getAttribute("cart") == null){
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(clothes, savedPrint, 1));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>)session.getAttribute("cart");
            int index = exists(clothesID, templateID, imageID, session);
            if (index == -1){
                cart.add(new Item(clothes, savedPrint, 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "clientViews/cartPage";
    }


    //Удалить itemLine
    @RequestMapping(value = "/removeLine", method = RequestMethod.GET)
    public String removeLine(HttpServletRequest request, HttpSession session){
        int clothesID = Integer.parseInt(request.getParameter("clothesID"));
        int templateID = Integer.parseInt(request.getParameter("templateID"));
        int imageID = Integer.parseInt(request.getParameter("imageID"));

        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = exists(clothesID, templateID, imageID, session);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "clientViews/cartPage";
    }

    //Увеличить количество
    @RequestMapping(value = "/incQuantity", method = RequestMethod.GET)
    public String incQuantity(ModelAndView model, HttpServletRequest request, HttpSession session){
        int clothesID = Integer.parseInt(request.getParameter("clothesID"));
        int templateID = Integer.parseInt(request.getParameter("templateID"));
        int imageID = Integer.parseInt(request.getParameter("imageID"));

        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = exists(clothesID, templateID, imageID, session);
        cart.get(index).setQuantity(cart.get(index).getQuantity()+1);
        session.setAttribute("cart", cart);
        return "clientViews/cartPage";
    }

    //Уменьшить количество
    @RequestMapping(value = "/decQuantity", method = RequestMethod.GET)
    public String decQuantity(ModelAndView model, HttpServletRequest request, HttpSession session){
        int clothesID = Integer.parseInt(request.getParameter("clothesID"));
        int templateID = Integer.parseInt(request.getParameter("templateID"));
        int imageID = Integer.parseInt(request.getParameter("imageID"));

        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = exists(clothesID, templateID, imageID, session);
        cart.get(index).setQuantity(cart.get(index).getQuantity()-1);
        if(cart.get(index).getQuantity() == 0){
            removeLine(request, session);
            session.setAttribute("cart", cart);
            return "clientViews/cartPage";
        } else {
            session.setAttribute("cart", cart);
            return "clientViews/cartPage";
        }

    }


    //Проверка на существование принта
    static private int printExists(int templateID, int imageID, List<Print> listPrints){
        for(int i=0; i<listPrints.size(); i++)
            if ((listPrints.get(i).getImage().getId() == imageID)
                    & (listPrints.get(i).getTemplate().getId() == templateID)) return listPrints.get(i).getId();
        return -1;
}


    //Проверка на существование комбинации
    static private int exists(int clothesID, int templateID, int imageID, HttpSession session){
        List<Item> cart = (List<Item>)session.getAttribute("cart");
        for(int i=0; i<cart.size(); i++)
            if((cart.get(i).getClothes().getId() == clothesID) & (cart.get(i).getPrint().getImage().getId() == imageID)
                    & (cart.get(i).getPrint().getTemplate().getId() == templateID)) return i;
        return -1;
        }
    }
