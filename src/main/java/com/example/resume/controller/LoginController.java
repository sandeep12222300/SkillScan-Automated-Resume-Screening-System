package com.example.resume.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Value("${app.admin.username:admin}")
    private String adminUsername;

    @Value("${app.admin.password:admin123}")
    private String adminPassword;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html
    }

    // Handle login form submit
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {

        // SIMPLE AUTH (Environment configurable)
        if (adminUsername.equals(username) && adminPassword.equals(password)) {

            // store login info in session
            session.setAttribute("loggedInUser", username);

            return "redirect:/resume";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    // Resume screening page
    @GetMapping("/resume")
    public String resumePage(HttpSession session) {

        // simple session check
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        return "index"; // index.html
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
