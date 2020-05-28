package com.controller;

import com.dao.UsersDAO;
import com.model.Users;
import com.service.SecurityService;
import com.service.UserService;
import com.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new Users());

        return "clientViews/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Users userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        userForm.setRole("ROLE_USER");
        if (bindingResult.hasErrors()) {
            return "clientViews/registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getLogin(), userForm.getPassword());
        return "redirect:listClothes";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "clientViews/login";
    }



    @RequestMapping(value = {"/account"}, method = RequestMethod.GET)
    public ModelAndView account() {

        //ТЕКУЩИЙ ЮЗЕР
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Users currentUser = usersDAO.findByUserName(username);
        ModelAndView model = new ModelAndView("clientViews/account");
        model.addObject("currentUser", currentUser);
        return model;
    }
    //Редактирование профиля
    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("id"));
        Users user = usersDAO.getUserById(userID);
        ModelAndView model = new ModelAndView("clientViews/editProfile");
        model.addObject("user", user);

        return model;
    }
    //Сохранение изменений для insert и update
    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    public ModelAndView saveProfile(@ModelAttribute Users user) {
        usersDAO.saveOrUpdate(user);
        return new ModelAndView("redirect:account");
    }
}

