package pl.morozgrusz.zycieklockow.security;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.morozgrusz.zycieklockow.datatransferobjects.UserDTO;
import pl.morozgrusz.zycieklockow.services.UserService;

@Controller
public class SecurityController
{
    private UserService userService;

    @Autowired
    public SecurityController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegistrationForm(Model model)
    {
        UserDTO userDTO = new UserDTO();

        model.addAttribute("user_dto", userDTO);

        return "security/register";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(@Valid @ModelAttribute("user_dto") UserDTO userDTO,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes)
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

        if (userService.findUserByEmail(userDTO.getEmail()) != null)
        {
            bindingResult.addError(new FieldError("user_dto", "email",
                    "A user with this email already exists"));

            return "security/register";
        }

        userService.save(userDTO);

        redirectAttributes.addAttribute("registered", "");

        return "redirect:/";
    }

    @GetMapping("/accessDenied")
    public String accessDenied()
    {
        return "security/access-denied";
    }
}
