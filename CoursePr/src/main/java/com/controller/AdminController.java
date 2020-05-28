package com.controller;

import com.dao.*;
import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller("/admin")
public class AdminController {

    @Autowired
    private ClothesDAO clothesDAO;
    @Autowired
    private ImageDAO imageDAO;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private TemplateDAO templateDAO;
    @Autowired
    private PrintDAO printDAO;
    @Autowired
    private CombinationDAO combinationDAO;
    @Autowired
    private OrderDAO orderDAO;


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
        return new ModelAndView("redirect:/listClothes");
    }


    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveClothes", method = RequestMethod.POST)
    public ModelAndView saveClothes(@ModelAttribute Clothes clothes) {
        clothesDAO.saveOrUpdate(clothes);
        return new ModelAndView("redirect:/listClothes");
    }


    //Полный список пользователей
    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public ModelAndView listUsers(ModelAndView model){
        List<Users> listUsers = usersDAO.usersList();
        model.addObject("usersRoles", usersDAO.getUsersRoles());
        model.addObject("listUsers", listUsers);
        model.setViewName("adminViews/listUsers");
        return model;
    }

    //Список пользователей по выбранной роли
    @RequestMapping(value = "/listUsersByRole", method = RequestMethod.GET)
    public ModelAndView listClothesClientByType(HttpServletRequest request, ModelAndView model){
        String role = request.getParameter("role");
        List<Users> listUsersByRole = usersDAO.getUsersByRole(role);
        model.addObject("usersRoles", usersDAO.getUsersRoles());
        model.addObject("listUsers", listUsersByRole);
        model.setViewName("adminViews/listUsers");

        return model;
    }


    //Редактирование пользователя
    @RequestMapping(value = "/editRole", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("id"));
        Users user = usersDAO.getUserById(userID);
        ModelAndView model = new ModelAndView("adminViews/editRole");
        model.addObject("user", user);

        return model;
    }

    //Удаление пользователя
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("id"));
        usersDAO.delete(userID);
        return new ModelAndView("redirect:/listUsers");
    }


    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute Users user) {
        usersDAO.saveOrUpdate(user);
        return new ModelAndView("redirect:/listUsers");
    }

    //Добавление нового изображения
    @RequestMapping(value = "/newImage", method = RequestMethod.GET)
    public ModelAndView newImage(ModelAndView model) {
        Image newImage = new Image();
        //ТЕКУЩИЙ ЮЗЕР
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Users currentUser = usersDAO.findByUserName(username);
        if (currentUser.getRole().equals("ROLE_USER")){
            newImage.setAuthor(currentUser.getLogin());
            newImage.setStatus("Under consideration");
        } else {
            newImage.setStatus("In usage");
        }
        model.addObject("image", newImage);
        model.setViewName("clientViews/newImage");
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

    //Утверждение изображения
    @RequestMapping(value = "/approveImage", method = RequestMethod.GET)
    public ModelAndView approveImage(HttpServletRequest request) {
        int imageId = Integer.parseInt(request.getParameter("imageID"));
        Image image = imageDAO.getImageById(imageId);
        image.setStatus("In usage");
        imageDAO.saveOrUpdate(image);
        return new ModelAndView("redirect:/listImages");
    }

    //Удалить изображение
    @RequestMapping(value = "/deleteImage", method = RequestMethod.GET)
    public ModelAndView deleteImage(HttpServletRequest request) {
        int imageId = Integer.parseInt(request.getParameter("id"));
        imageDAO.delete(imageId);
        return new ModelAndView("redirect:/listImages");
    }

    //Сохранить изменения для insert и update
    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    public ModelAndView saveImage(@ModelAttribute Image image) {
        imageDAO.saveOrUpdate(image);
        return new ModelAndView("redirect:/listImages");
    }
    //Полный список шаблонов
    @RequestMapping(value = "/listTemplates", method = RequestMethod.GET)
    public ModelAndView listTemplatesAdmin(ModelAndView model){
        List<Template> listTemplates = templateDAO.templateList();
        model.addObject("listTemplates", listTemplates);
        model.setViewName("clientViews/listTemplates");

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
    @RequestMapping(value = "/editTemplate", method = RequestMethod.GET)
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
        return new ModelAndView("redirect:/listTemplates");
    }

    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveTemplate", method = RequestMethod.POST)
    public ModelAndView saveTemplate(@ModelAttribute Template template) {
        templateDAO.saveOrUpdate(template);
        return new ModelAndView("redirect:/listTemplates");
    }


    //Полный список принтов
    @RequestMapping(value = "/listPrints", method = RequestMethod.GET)
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
        int printID = Integer.parseInt(request.getParameter("id"));
        printDAO.delete(printID);
        return new ModelAndView("redirect:/listPrints");
    }

    //Сохранение изменений для insert и update
    @RequestMapping(value = "/savePrint", method = RequestMethod.POST)
    public ModelAndView savePrint(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int imageID = Integer.parseInt(request.getParameter("image"));
        int templateID = Integer.parseInt(request.getParameter("template"));
        Print print = new Print(id, templateDAO.getTemplateById(templateID), imageDAO.getImageById(imageID) );
        printDAO.saveOrUpdate(print);
        return new ModelAndView("redirect:/listPrints");
    }

    //Добавление новой комбинации
    @RequestMapping(value = "/newCombination", method = RequestMethod.GET)
    public ModelAndView newCombination(ModelAndView model) {
        Combination newCombination = new Combination();
        model.addObject("combination", newCombination);
        List<Clothes> listClothes = clothesDAO.clothesList();
        model.addObject("listClothes", listClothes);
        List<Print> listPrints = printDAO.printList();
        model.addObject("listPrints", listPrints);
        model.setViewName("adminViews/newCombination");
        return model;
    }


    //Редактирование комбинации
    @RequestMapping(value = "/editCombination", method = RequestMethod.GET)
    public ModelAndView editCombination(HttpServletRequest request) {
        int combID = Integer.parseInt(request.getParameter("id"));
        Combination combination = combinationDAO.getCombinationById(combID);
        ModelAndView model = new ModelAndView("adminViews/editCombination");
        model.addObject("combination", combination);
        List<Clothes> listClothes = clothesDAO.clothesList();
        model.addObject("listClothes", listClothes);
        List<Print> listPrints = printDAO.printList();
        model.addObject("listPrints", listPrints);

        return model;
    }

    //Удалить комбинацию
    @RequestMapping(value = "/deleteCombination", method = RequestMethod.GET)
    public ModelAndView deleteCombination(HttpServletRequest request) {
        int combID = Integer.parseInt(request.getParameter("id"));
        combinationDAO.delete(combID);
        return new ModelAndView("redirect:/listCombinations");
    }

    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveCombination", method = RequestMethod.POST)
    public ModelAndView saveCombination(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int clothesID = Integer.parseInt(request.getParameter("clothes"));
        int printID = Integer.parseInt(request.getParameter("print"));
        String cmbImgLnk = request.getParameter("combinedImageLink");
        double price = clothesDAO.getClothesById(clothesID).getPrice() + printDAO.getPrintById(printID).getTemplate().getPrice();
        Combination combination = new Combination(id, clothesDAO.getClothesById(clothesID), printDAO.getPrintById(printID), price, cmbImgLnk);
        combinationDAO.saveOrUpdate(combination);
        return new ModelAndView("redirect:/listCombinations");
    }



    //Взять на печать
    @RequestMapping(value = "/takeOrder", method = RequestMethod.GET)
    public ModelAndView takeOrder(HttpServletRequest request) {
        int orderID = Integer.parseInt(request.getParameter("id"));
        Order order = orderDAO.getOrderById(orderID);
        order.setStatus("Принят службой печати");
        orderDAO.saveOrUpdate(order);
        return new ModelAndView("redirect:/listOrders");
    }


    //Выполнить заказ
    @RequestMapping(value = "/executeOrder", method = RequestMethod.GET)
    public ModelAndView executeOrder(HttpServletRequest request) {
        int orderID = Integer.parseInt(request.getParameter("id"));
        Order order = orderDAO.getOrderById(orderID);
        order.setStatus("Ожидает получения");
        orderDAO.saveOrUpdate(order);
        return new ModelAndView("redirect:/listOrders");
    }


    //Отпустить заказ
    @RequestMapping(value = "/releaseOrder", method = RequestMethod.GET)
    public ModelAndView releaseOrder(HttpServletRequest request) {
        int orderID = Integer.parseInt(request.getParameter("id"));
        Order order = orderDAO.getOrderById(orderID);
        order.setStatus("Заказ получен");
        orderDAO.saveOrUpdate(order);
        return new ModelAndView("redirect:/listOrders");
    }

    //Список заказов
    @RequestMapping(value = "/listOrders", method = RequestMethod.GET)
    public ModelAndView listOrdersAdmin(ModelAndView model){
        List<Order> listOrders = orderDAO.orderList();
        model.addObject("listOrders", listOrders);
        model.setViewName("adminViews/listOrders");

        return model;
    }

}
