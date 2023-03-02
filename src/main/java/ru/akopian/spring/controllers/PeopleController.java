package ru.akopian.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akopian.spring.dao.PersonDAO;
import ru.akopian.spring.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        //получаем всех людей из ДАО и передадим на представление
        model.addAttribute("people",personDAO.index());

        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        //Получаем одного человека по ид из ДАО и передадим на отображение
        model.addAttribute("person",personDAO.show(id));

        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute Person person){

        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute Person person){
        personDAO.save(person);

        return "redirect:/people";
    }
}
