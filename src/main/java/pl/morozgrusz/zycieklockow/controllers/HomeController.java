package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.morozgrusz.zycieklockow.configs.AppConfig;

@RestController
@RequestMapping("/api")
public class HomeController
{
    private AppConfig appConfig;

    @Autowired
    public HomeController(AppConfig appConfig)
    {
        this.appConfig = appConfig;
    }

    @GetMapping("/home")
    public String getHome()
    {
        return appConfig.getHome();
    }
}
