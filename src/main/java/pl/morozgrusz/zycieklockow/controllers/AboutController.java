package pl.morozgrusz.zycieklockow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.morozgrusz.zycieklockow.configs.AppConfig;

@RestController
@RequestMapping("/api")
public class AboutController
{
    private AppConfig appConfig;

    @Autowired
    public AboutController(AppConfig appConfig)
    {
        this.appConfig = appConfig;
    }

    @GetMapping("/about")
    public String getAbout()
    {
        return appConfig.getAbout();
    }
}
