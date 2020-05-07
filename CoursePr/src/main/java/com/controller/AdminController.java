package com.controller;

import com.dao.*;
import com.model.Clothes;
import com.model.Image;
import com.model.Print;
import com.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("/admin")
public class AdminController {

    @Autowired
    private ClothesDAO clothesDAO;
    @Autowired
    private ImageDAO imageDAO;
    @Autowired
    private TemplateDAO templateDAO;
    @Autowired
    private PrintDAO printDAO;
    @Autowired
    private CombinationDAO combinationDAO;


    //Полный список одежды
    @RequestMapping(value = "/listClothesAdmin", method = RequestMethod.GET)
    public ModelAndView listClothes(ModelAndView model){
        List<Clothes> listClothes = clothesDAO.clothesList();
        model.addObject("listClothes", listClothes);
        model.setViewName("adminViews/listClothes");

        return model;
    }


    //Добавление одежды
    @RequestMapping(value = "/newClothes", method = RequestMethod.GET)
    public ModelAndView newClothes(ModelAndView model) {
        Clothes newClothes = new Clothes();
        model.addObject("clothes", newClothes);
        model.setViewName("adminViews/newClothes");
        return model;
    }

    //Редактирование одежды
    @RequestMapping(value = "/editClothes", method = RequestMethod.GET)
    public ModelAndView editClothes(HttpServletRequest request) {
        int clothesId = Integer.parseInt(request.getParameter("id"));
        Clothes clothes = clothesDAO.getClothesById(clothesId);
        ModelAndView model = new ModelAndView("adminViews/editClothes");
        model.addObject("clothes", clothes);

        return model;
    }

    //Удаление одежды
    @RequestMapping(value = "/deleteClothes", method = RequestMethod.GET)
    public ModelAndView deleteClothes(HttpServletRequest request) {
        int clothesId = Integer.parseInt(request.getParameter("id"));
        clothesDAO.delete(clothesId);
        return new ModelAndView("redirect:/listClothesAdmin");
    }


    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveClothes", method = RequestMethod.POST)
    public ModelAndView saveClothes(@ModelAttribute Clothes clothes) {
        clothesDAO.saveOrUpdate(clothes);
        return new ModelAndView("redirect:/listClothesAdmin");
    }



    //Полный список изображений
    @RequestMapping(value = "/listImagesAdmin", method = RequestMethod.GET)
    public ModelAndView listImages(ModelAndView model){
        List<Image> listImage = imageDAO.imageList();
        model.addObject("listImages", listImage);
        model.setViewName("adminViews/listImages");

        return model;
    }

    //Добавление нового изображения
    @RequestMapping(value = "/newImage", method = RequestMethod.GET)
    public ModelAndView newImage(ModelAndView model) {
        Image newImage = new Image();
        model.addObject("image", newImage);
        model.setViewName("adminViews/newImage");
        return model;
    }

    //Редактирование изображения
    @RequestMapping(value = "/editImage", method = RequestMethod.GET)
    public ModelAndView editImage(HttpServletRequest request) {
        int imageId = Integer.parseInt(request.getParameter("id"));
        Image image = imageDAO.getImageById(imageId);
        ModelAndView model = new ModelAndView("adminViews/editImage");
        model.addObject("image", image);

        return model;
    }

    //Удалить изображение
    @RequestMapping(value = "/deleteImage", method = RequestMethod.GET)
    public ModelAndView deleteImage(HttpServletRequest request) {
        int imageId = Integer.parseInt(request.getParameter("id"));
        imageDAO.delete(imageId);
        return new ModelAndView("redirect:/listImagesAdmin");
    }

    //Сохранить изменения для insert и update
    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    public ModelAndView saveImage(@ModelAttribute Image image) {
        imageDAO.saveOrUpdate(image);
        return new ModelAndView("redirect:/listImagesAdmin");
    }




    //Полный список шаблонов
    @RequestMapping(value = "/listTemplatesAdmin", method = RequestMethod.GET)
    public ModelAndView listTemplatesAdmin(ModelAndView model){
        List<Template> listTemplates = templateDAO.templateList();
        model.addObject("listTemplates", listTemplates);
        model.setViewName("adminViews/listTemplates");

        return model;
    }

    //Добавление нового шаблона
    @RequestMapping(value = "/newTemplate", method = RequestMethod.GET)
    public ModelAndView newTemplate(ModelAndView model) {
        Template newTemplate = new Template();
        model.addObject("template", newTemplate);
        model.setViewName("adminViews/newTemplate");
        return model;
    }

    //Редактирование шаблона
    @RequestMapping(value = "/ediTemplate", method = RequestMethod.GET)
    public ModelAndView editTemplate(HttpServletRequest request) {
        int templateID = Integer.parseInt(request.getParameter("id"));
        Template template = templateDAO.getTemplateById(templateID);
        ModelAndView model = new ModelAndView("adminViews/editTemplate");
        model.addObject("template", template);

        return model;
    }

    //Удалить шаблон
    @RequestMapping(value = "/deleteTemplate", method = RequestMethod.GET)
    public ModelAndView deleteTemplate(HttpServletRequest request) {
        int templateID = Integer.parseInt(request.getParameter("id"));
        templateDAO.delete(templateID);
        return new ModelAndView("redirect:/listTemplatesAdmin");
    }

    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveTemplate", method = RequestMethod.POST)
    public ModelAndView saveTemplate(@ModelAttribute Template template) {
        templateDAO.saveOrUpdate(template);
        return new ModelAndView("redirect:/listTemplateAdmin");
    }




    //Полный список принтов
    @RequestMapping(value = "/listPrintsAdmin", method = RequestMethod.GET)
    public ModelAndView listPrints(ModelAndView model){
        List<Print> listPrints = printDAO.printList();
        model.addObject("listPrints", listPrints);
        model.setViewName("adminViews/listPrints");

        return model;
    }

    //Добавление нового принта
    @RequestMapping(value = "/newPrint", method = RequestMethod.GET)
    public ModelAndView newPrint(ModelAndView model) {
        Print newPrint = new Print();
        model.addObject("print", newPrint);
        List<Template> listTemplates = templateDAO.templateList();
        model.addObject("listTemplates", listTemplates);
        List<Image> listImages = imageDAO.imageList();
        model.addObject("listImages", listImages);
        model.setViewName("adminViews/newPrint");
        return model;
    }


    //Редактирование принта
    @RequestMapping(value = "/editPrint", method = RequestMethod.GET)
    public ModelAndView editPrint(HttpServletRequest request) {
        int printID = Integer.parseInt(request.getParameter("id"));
        Print print = printDAO.getPrintById(printID);
        ModelAndView model = new ModelAndView("adminViews/editPrint");
        model.addObject("print", print);
        List<Template> listTemplates = templateDAO.templateList();
        model.addObject("listTemplates", listTemplates);
        List<Image> listImages = imageDAO.imageList();
        model.addObject("listImages", listImages);

        return model;
    }

    //Удалить принт
    @RequestMapping(value = "/deletePrint", method = RequestMethod.GET)
    public ModelAndView deletePrint(HttpServletRequest request) {
        int templateID = Integer.parseInt(request.getParameter("id"));
        templateDAO.delete(templateID);
        return new ModelAndView("redirect:/listTemplatesAdmin");
    }

    //Сохранение изменений для insert и update
    @RequestMapping(value = "/savePrint", method = RequestMethod.POST)
    public ModelAndView savePrint(@ModelAttribute Print print) {
        printDAO.saveOrUpdate(print);
        return new ModelAndView("redirect:/listPrintsAdmin");
    }
}
