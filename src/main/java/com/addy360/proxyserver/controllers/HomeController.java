package com.addy360.proxyserver.controllers;


import com.addy360.proxyserver.dtos.SearchDto;
import com.addy360.proxyserver.utils.PageRequester;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class HomeController {
    private final PageRequester pageRequester;

    @ResponseBody
    @PostMapping("/api/v1/offer")
    ResponseEntity<?> test(@RequestBody Map<String, String> data) {
        log.info("Data received : {}", data);
        return ResponseEntity.status(201).body("Request sent Successfully");
    }

    @ResponseBody
    @PostMapping("/api/sms-status/receive")
    ResponseEntity<?> receiveSmsStatusCallback(@RequestBody Map<String, Object> payload) {
        log.info("Received sms status callback : {}", payload);
        return ResponseEntity.ok("Callback received successfully");
    }

    @GetMapping
    public String index(Model model) {
        SearchDto dto = new SearchDto();
        model.addAttribute("dto", dto);
        return "index";
    }

    @PostMapping
    public String url(@Valid SearchDto searchDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("dto", searchDto);
            return "index";
        }

        log.info("Search : {}", searchDto);

        String document = pageRequester.loadDocument(searchDto.getUrl());
        model.addAttribute("document", document);
        return "page";

    }

    @GetMapping("/nida/{nin}")
    @CrossOrigin(origins = "**", originPatterns = "**")
    public ResponseEntity<?> fromUrl(@PathVariable String nin) {
        String baseUrl = "https:/ors.brela.go.tz/um/load/load_nida";
        String s = pageRequester.requestUrl(baseUrl + "/" + nin);
        return ResponseEntity.ok(s);
    }
}
