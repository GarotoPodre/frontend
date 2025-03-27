package br.com.gama.leituras.frontend.controller;

import br.com.gama.leituras.frontend.model.LeituraDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class LeituraController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Método que obtém as leituras do backend
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/leituras")
    public String lista(Model model, HttpSession session){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            //Pega o token da sessão
            String jwt=(String) session.getAttribute("sessionToken");

            //Se existir (não for nulo), ou seja, já foi recebido através de alguma autenticação anterior
            if(jwt !=null){
                //adiciona o token ao cabeçalho da requisição
                headers.add("Authorization", "Bearer "+jwt);
            }else{
                //senão, redireciona para a tela de login, para aquisição de token
                return "redirect:/login";
            }

            HttpEntity<String>entity=new HttpEntity<>(headers);

            ResponseEntity<LeituraDto[]> response = restTemplate
                    .exchange("http://localhost:8080/leituras",
                            HttpMethod.GET,
                            entity,
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
                    "Problemas para obter os dados do backend :"+e.getMessage());
            return "leituras/lista";
        }
    }

    /**
     * Carrega o formulário de cadastro
     * @return
     */
    @GetMapping("/leituras/cadastro")
    public String carregaFormularioCadastro() {
        return "leituras/cadastro";
    }

    /**
     * Envia os dados lançados no formulário para o backend rest, para gravação
     * @param leitura
     * @param model
     * @param session
     * @return
     */
    @PostMapping("leituras/gravacao")
    public String gravar(LeituraDto leitura, Model model, HttpSession session){
        try {
            //Criando os cabeçalhos
            HttpHeaders headers=new HttpHeaders();
            String jwt=(String) session.getAttribute("sessionToken");

            if(jwt !=null){
                headers.add("Authorization", jwt);
            }

            headers.setContentType(MediaType.APPLICATION_JSON);

            //Criando a entidade de requisição com cabeçalho e body

            HttpEntity<LeituraDto> httpEntity = new HttpEntity<>(leitura, headers);
            ResponseEntity<LeituraDto> response = restTemplate
                    .exchange(
                            "http://localhost:8080/leituras",
                            HttpMethod.POST,
                            httpEntity,
                            LeituraDto.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/leituras";
            } else {
                model.addAttribute("mensagem",
                        "Erro ao cadastrar a leitura");

                return "redirect:/leituras/cadastro";
            }
        } catch (Exception e) {
            model.addAttribute("mensagem", "Problemas para fazer a gravação dos dados no backend :"+e.getMessage());
            return "redirect:/leituras/cadastro";
        }
    }

}
