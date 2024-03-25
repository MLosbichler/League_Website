package bichla.league_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bichla.league_project.service.APIService;

@RestController
public class TestController {

    private final APIService apiService;

    @Autowired
    private TestController(APIService apiService) {
        this.apiService = apiService;
    }

    @RequestMapping("/api/test")
    public void test() {
        apiService.test();
    }
}
