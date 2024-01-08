package pl.morozgrusz.zycieklockow.configuration;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
public class CustomBinderControllerAdvice
{
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.registerCustomEditor(String.class,
                new PropertyEditorSupport()
                {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException
                    {
                        if (text != null)
                        {
                            String trimmed = text.trim();
                            setValue(trimmed.isEmpty() ? null : trimmed);
                        }
                        else
                        {
                            setValue(null);
                        }
                    }
                }
        );
    }
}
