document.addEventListener('DOMContentLoaded', function() {
    // Capturando o formulário
    const formLogin = document.getElementById('formLogin');

    // Para evitar o comportamento padrão de submissão e poder capturar os dados com javascript
    formLogin.addEventListener('submit', (event) => {
        event.preventDefault();

        // Capturando os dados dos campos e colocando-os em variáveis
        const login = document.getElementById('login').value;
        const senha = document.getElementById('senha').value;

        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
//                'Content-Type': 'application/json',
//                'Accept': 'application/json'
                'Content-Type': 'text/plain;charset=UTF-8'
            },
            body: JSON.stringify({login, senha}) // converte em JSON
        })
            .then(response => {
                if (!response.ok){throw new Error('Falha no processo de login'); }
                return response.text(); // Recupera o token como texto
            })
            .then(jwt =>{
                localStorage.setItem('jwt', jwt);// armazena no local
                window.location.href = '/leituras'; // redireciona para a
                patch(error => {
                console.error('Error', error);
            })
            .catch(error => {
                alert("Erro no login: " + error.message);
            });
        return false;
    });
});