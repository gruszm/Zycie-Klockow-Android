package pl.morozgrusz.zycieklockow.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig
{
    private String home;
    private String about;

    public String getHome()
    {
        return home;
    }

    public void setHome(String home)
    {
        this.home = home;
    }

    public String getAbout()
    {
        return about;
    }

    public void setAbout(String about)
    {
        this.about = about;
    }
}
