/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function(){
    
    var msg = document.getElementById("mens").value;
    var elm = document.getElementById("mensagem");
    var inp = document.createElement("input");
    if(msg === "erro"){
        inp.setAttribute("value", "Não foi possível "+req.value+" o "+req.value+" !");
        inp.setAttribute("id", "erro");
        inp.setAttribute("readonly", "readonly");
        elm.appendChild(inp);
    }
    if(msg === "sucesso"){
        inp.setAttribute("value", req.value+" "+act.value+" com sucesso!");
        inp.setAttribute("id", "sucesso");
        inp.setAttribute("readonly", "readonly");
        elm.appendChild(inp);
    }
    else if(msg === "erro"){
        inp.setAttribute("value", "Não foi possível "+act.value+" o "+req.value+" !");
        inp.setAttribute("id", "erro");
        inp.setAttribute("readonly", "readonly");
        elm.appendChild(inp);
    }
    msg.value = "";
    act.value = "";
    req.value = "";
}
function somenteNumero(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla >= 33 && tecla <= 64 )
        return true;
    else
        return false;
}
function somenteLetras(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if (tecla > 47 && tecla < 58 || tecla === 48)
        return false;
    else
        return true;
}

function limpa_formulário_cep() {
//Limpa valores do formulário de cep.
    document.getElementById('rua').value = ("");
    document.getElementById('bairro').value = ("");
    document.getElementById('cidade').value = ("");
    document.getElementById('uf').value = ("");
}

function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
//Atualiza os campos com os valores.
        document.getElementById('rua').value = (conteudo.logradouro);
        document.getElementById('bairro').value = (conteudo.bairro);
        document.getElementById('cidade').value = (conteudo.localidade);
        document.getElementById('uf').value = (conteudo.uf);
        document.getElementById('ibge').value = (conteudo.ibge);
    } //end if.
    else {
//CEP não Encontrado.
        limpa_formulário_cep();
        alert("CEP não encontrado.");
    }
}

function pesquisacep(valor) {

//Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');
    //Verifica se campo cep possui valor informado.
    if (cep !== "") {

//Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;
        //Valida o formato do CEP.
        if (validacep.test(cep)) {

//Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('rua').value = "...";
            document.getElementById('bairro').value = "...";
            document.getElementById('cidade').value = "...";
            document.getElementById('uf').value = "...";
            //Cria um elemento javascript.
            var script = document.createElement('script');
            //Sincroniza com o callback.
            script.src = '//viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';
            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);
        } //end if.
        else {
//cep é inválido.
            limpa_formulário_cep();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
//cep sem valor, limpa formulário.
        limpa_formulário_cep();
    }
}
function limpa_formulário_cpf() {
    document.getElementById('CPF').value = ("");

}
function TestaCPF(n) {
    var Soma;
    var Resto;
    Soma = 0;
    if (n.length !== 11 ||
            n === "00000000000" ||
            n === "11111111111" ||
            n === "22222222222" ||
            n === "33333333333" ||
            n === "44444444444" ||
            n === "55555555555" ||
            n === "66666666666" ||
            n === "77777777777" ||
            n === "88888888888" ||
            n === "99999999999") {
        alert("CPF Invalido!");
        limpa_formulário_cpf();
        return false;
    }

    for (i = 1; i <= 9; i++)
        Soma = Soma + parseInt(n.substring(i - 1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto === 10) || (Resto === 11))
        Resto = 0;
    if (Resto !== parseInt(n.substring(9, 10))) {
        alert("CPF Invalido!");
        limpa_formulário_cpf();
        return false;
    }

    Soma = 0;
    for (i = 1; i <= 10; i++)
        Soma = Soma + parseInt(n.substring(i - 1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto === 10) || (Resto === 11))
        Resto = 0;
    if (Resto !== parseInt(n.substring(10, 11))) {
        alert("CPF Invalido!");
        limpa_formulário_cpf();
        return false;
    }
    return true;
}
function validarCNPJ(cnpj) {

    cnpj = cnpj.replace(/[^\d]+/g, '');

    if (cnpj == '' || cnpj.length != 14)
        return false;

    // Valida DVs
    tamanho = cnpj.length - 2;
    numeros = cnpj.substring(0, tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado !== digitos.charAt(0)){
        alert("CNPJ Invalido!");
        return false;
    }

    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado !== digitos.charAt(1))
        return false;

    return true;

}
function calcular() {
    var num1 = Number(document.getElementById("Aluno").value);
    var num2 = Number(document.getElementById("num2").value);
    var elemResult = document.getElementById("resultado");

    if (elemResult.textContent === undefined) {
       elemResult.textContent = "O resultado é " + String(num1 + num2) + ".";
    }
    else { // IE
       elemResult.innerText = "O resultado é " + String(num1 + num2) + ".";
    }
    /* Mudar no html para funcionar a função
     * <input type="text" id="num1" onblur="calcular();" />
<input type="text" id="num2" onblur="calcular();" />
<span id="resultado"></span>
     */
}