package com.addy360.proxyserver.controllers;


import com.addy360.proxyserver.dtos.SearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping
public class HomeController {
    @GetMapping
    public String index(Model model) {
        SearchDto dto = new SearchDto();
        model.addAttribute("dto", dto);
        return "index";
    }

    @PostMapping
    public String url(@Valid SearchDto searchDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("dto",searchDto);
            return "index";
        }

        log.info("Search : {}", searchDto);
        return "page";

    }
}
