package com.yeahbutstill.mvc.controllers;

import com.yeahbutstill.mvc.models.Partner;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PartnerController {

    @GetMapping(path = "/partner/current")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getCurrentPartner(@Valid Partner partner) {
        return partner.id() + " : " + partner.name();
    }

}
