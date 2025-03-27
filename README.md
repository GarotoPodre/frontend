<h1>Fronted</h1>

<h1>Descrição do seu projeto</h1>
Meu portfólio começou com a aplicação 'batch', que cria um pipeline e grava os dados em um banco de dados da aplicação. 
Como o acesso direto aos dados não era recomendado, criei outra aplicação no portfólio, que se chama 'batchapi', que fornece acesso aos endpoints, 
mediante autenticação.
Por fim, era necessário um client para testar tudo isso, e é do que se trata essa aplicação.
Ela não tem nada de tão diferente, o mais legal que eu acho é que ela acessa o endpoint de autenticação da aplicação 'batchapi', forncendo credendiais de acesso, 
se tudo estiver ok, recebe um token JWT, e armazena isso na sessão. A cada requisição, é colocado esse token no cabeçalho da requisição, para que se possa acessar
os recursos, é uma coisa até trivial, mas sinceramente nunca havia feito, antes eu havia testado os endpoints via client android, então, pra quem tá procurando um exemplo
ofensivamente simples, tá aí. Eu ficaria feliz de ter achado algo assim, mas só achei exemplo com javascript... :P
Eu perdi um tempão com uma coisa simples, a requisição do token, armazenamento e recuperação do mesmo, não amo frontend, então não faço muito, por isso fiquei em dúvida 
de como fazer da melhor forma. A princípio, como disse, usei javascript, que não deu certo (aliás, quase nunca me dou bem com javascript :(, terrível isso) mas depois implementei com java 'puro'
mesmo.
<br/>
<h1>🧰Funcionalidades</h1>
- Permite inserção, leitura de leituras
- Permite autenticação no frontend
<h1>🚧Status do projeto</h1> 
<ul>
<li>endpoints para leitura[x]</li> 
<li>endpoints para autenticação[x]</li> 
</ul>

<h1>👨‍💻Tecnologias</h1>
<ul>
  <li>Springboot web mvc</li>
  <li>JWT</li>
</ul>


<h1> 🧑Autor</h1> 
<a href="mailto=welington_andrade@hotmail.com">Welington</a>
