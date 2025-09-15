package com.example.redisdemo.controller;

import com.example.redisdemo.model.Person;
import com.example.redisdemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public String save(@RequestBody Person person) {
        personService.save(person);
        return "Saved!";
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable String id) {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Person person) {
        person.setId(id);
        personService.update(person);
        return "Updated!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        personService.delete(id);
        return "Deleted!";
    }
}
