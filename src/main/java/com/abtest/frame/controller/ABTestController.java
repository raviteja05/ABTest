package com.abtest.frame.controller;

import com.abtest.frame.exception.VariantPercentageOutOfBoundsException;
import com.abtest.frame.service.ABTestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class ABTestController {

    @Resource
    private ABTestService abTestService;

    @PostMapping("/variants")
    public String getVariant(@RequestBody Map<String,Integer> variants){
        String variant=abTestService.getRandomVariant(variants);

        return variant;
    }
}
