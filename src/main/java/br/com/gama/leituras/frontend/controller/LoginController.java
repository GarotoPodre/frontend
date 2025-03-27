package br.com.gama.leituras.frontend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    private String token;

    @GetMapping("/login")
    public String carregaFormularioLogin() {

        return "leituras/login";
    }

    @PostMapping("/executaLogin")
    public String login(String login, String senha, HttpSession session) {
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = String.format("{\"login\":\"%s\", \"senha\":\"%s\"}", login, senha);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response=restTemplate.exchange(
                "http://localhost:8080/login",
                HttpMethod.POST,
                request,
                String.class
        );
        if (response.getStatusCode().is2xxSuccessful()) {
            this.token=response.getBody();
            session.setAttribute("sessionToken", token);
            System.out.println("Valor de toke recebido :"+token);
            return "redirect:/leituras";
        }else{
            throw new RuntimeException("Erro no processo de login");
        }
    }

    public String getToken() {
        return token;
    }

}
