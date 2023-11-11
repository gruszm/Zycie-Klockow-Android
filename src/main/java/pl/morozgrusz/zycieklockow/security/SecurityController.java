package pl.morozgrusz.zycieklockow.security;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.morozgrusz.zycieklockow.datatransferobjects.UserDTO;

@Controller
public class SecurityController
{
    @GetMapping("/register")
    public String getRegistrationForm(Model model)
    {
        UserDTO userDTO = new UserDTO();

        model.addAttribute("user_dto", userDTO);

        return "security/register";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(@Valid @ModelAttribute("user_dto") UserDTO userDTO,
                                      BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "security/register";
        }

        if (!userDTO.getPassword().equals(userDTO.getRepeatPassword()))
        {
            bindingResult.addError(new FieldError("user_dto", "repeatPassword",
                    "The passwords are not the same"));

            return "security/register";
        }

        return "redirect:/";
    }

    @GetMapping("/accessDenied")
    public String accessDenied()
    {
        return "security/access-denied";
    }
}
