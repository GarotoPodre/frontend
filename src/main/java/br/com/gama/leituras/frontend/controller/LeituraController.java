package br.com.gama.leituras.frontend.controller;

import br.com.gama.leituras.frontend.model.LeituraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class LeituraController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/leituras")
    public String lista(Model model){
        try {
            ResponseEntity<LeituraDto[]> response = restTemplate
                    .exchange("http://localhost:8080/leituras",
                            HttpMethod.GET,
                            null, //Sem request body
                            LeituraDto[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                LeituraDto[] leiturasArray = response.getBody();
                List<LeituraDto> leituras = Arrays.asList(leiturasArray);
                model.addAttribute("lista", leituras);
                return "leituras/lista";
            }
            else {
                model.addAttribute("mensagem",
                        "dados não estão disponíveis no momento");
                return "leituras/lista";
            }
        } catch (RuntimeException e) {
            model.addAttribute("mensagem",
                    "Endpoint fora do ar");
            return "leituras/lista";
        }
    }

    @GetMapping("/leituras/cadastro")
    public String carregaFormularioCadastro() {
        return "leituras/cadastro";
    }
    @PostMapping("leituras/gravacao")
    public String gravar(LeituraDto leitura, Model model) {
        try {
            //Criando os cabeçalhos
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            //Criando a entidade de requisição com cabeçalho e body
            HttpEntity<LeituraDto> request = new HttpEntity<>(leitura, headers);

            ResponseEntity<LeituraDto> response = restTemplate
                    .exchange(
                            "http://localhost:8080/leituras",
                            HttpMethod.POST,
                            request,
                            LeituraDto.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/leituras";
            } else {
                model.addAttribute("mensagem",
                        "Erro ao cadastrar a leitura");

                return "redirect:/leituras/cadastro";
            }
        } catch (Exception e) {
            model.addAttribute("mensagem", e.getMessage());
            return "redirect:/leituras/cadastro";
        }
    }
}
