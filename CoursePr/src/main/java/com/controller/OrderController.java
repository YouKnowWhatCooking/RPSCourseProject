package com.controller;

import com.dao.*;
import com.model.*;
import com.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("/order")
public class OrderController {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private OrderItemDAO orderItemDAO;
    @Autowired
    private ClothesDAO clothesDAO;
    @Autowired
    private PrintDAO printDAO;
    @Autowired
    private UsersDAO usersDAO;


    //Заполнение инормации о заказе
    @RequestMapping(value = "/myOrders", method = RequestMethod.GET)
    public ModelAndView myOrders(ModelAndView model) {
        //ТЕКУЩИЙ ЮЗЕР
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Users currentUser = usersDAO.findByUserName(username);

        List<Order> listOrders = orderDAO.ordersOfCurrentUser(currentUser.getId());
        model.addObject("listOrders", listOrders);
        model.setViewName("clientViews/myOrders");
        return model;
    }


    //Вывод содержимого заказа
    @RequestMapping(value = "/getOrderContent", method = RequestMethod.GET)
    public ModelAndView getOrderContent(HttpServletRequest request) {
        int orderID = Integer.parseInt(request.getParameter("id"));
        List<OrderItem> itemList = orderItemDAO.getContentByOrderID(orderID);
        ModelAndView model = new ModelAndView("clientViews/getOrderContent");
        model.addObject("itemList", itemList);

        return model;
    }
    //Вывод формы платежа
    @RequestMapping(value = "/paymentForm", method = RequestMethod.GET)
    public ModelAndView paymentForm(HttpServletRequest request) {

        if(request.getParameter("id") == null){
            Order order = orderDAO.getOrderById(orderDAO.getLastID());
            //ТЕКУЩИЙ ЮЗЕР
            String username;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }

            Users currentUser = usersDAO.findByUserName(username);
            order.setAddress(currentUser.getAddress());
            order.setEmail(currentUser.getEmail());
            ModelAndView model = new ModelAndView("clientViews/paymentForm");
            model.addObject("order", order);

            return model;
        } else {
            int orderID = Integer.parseInt(request.getParameter("id"));
            Order order = orderDAO.getOrderById(orderID);

            //ТЕКУЩИЙ ЮЗЕР
            String username;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }

            Users currentUser = usersDAO.findByUserName(username);

            order.setAddress(currentUser.getAddress());
            order.setEmail(currentUser.getEmail());
            ModelAndView model = new ModelAndView("clientViews/paymentForm");
            model.addObject("order", order);

            return model;
        }
    }

    //Типа оплата
    @RequestMapping(value = "/confirmPayment", method = RequestMethod.POST)
    public ModelAndView confirmPayment(@ModelAttribute Order order) {
        order.setStatus("Оплачен");
        orderDAO.saveOrUpdate(order);
        return new ModelAndView("redirect:/listClothes");
    }

    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public ModelAndView saveOrder(HttpServletRequest request, HttpSession session) {

        List<Item> cart = (List<Item>)session.getAttribute("cart");
        double cost = 0;
        for (Item item:cart) {
          cost+= item.getClothes().getPrice() +  item.getPrint().getTemplate().getPrice();
            if(!item.getPrint().getImage().getAuthor().equals(""))  {
                Users user = usersDAO.findByUserName(item.getPrint().getImage().getAuthor());
                double newBalance = user.getBalance() + cost*0.1;
                usersDAO.increaseBalance(user, newBalance);
            }
        }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        format.format(date); //Дата создания заказа


        //ТЕКУЩИЙ ЮЗЕР
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }

        Users currentUser = usersDAO.findByUserName(username);
        int clientID = currentUser.getId();


        String status = "Создан";
        Order order = new Order(format.format(date), clientID, status, null, null, cost);
        orderDAO.saveOrUpdate(order);

        int lastID = orderDAO.getLastID();
        for (Item item:cart) {
            OrderItem orderItem = new OrderItem(lastID, clothesDAO.getClothesById(item.getClothes().getId()), printDAO.getPrintById(item.getPrint().getId()), item.getQuantity());
            orderItemDAO.saveOrUpdate(orderItem);
        }
        return new ModelAndView("redirect:/paymentForm");
    }
    //Удалить заказ
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
    public ModelAndView deletePrint(HttpServletRequest request) {
        int orderID = Integer.parseInt(request.getParameter("id"));
        orderDAO.delete(orderID);
        return new ModelAndView("redirect:/myOrders");
    }
}
