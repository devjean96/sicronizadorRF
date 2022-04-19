# sicronizadorRF
Desafio Sicronizador Sicredi x Receita Federal

# Problema
- Cenário de Negócio: \
Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e organiza as informações de 
contas para enviar ao Banco Central. Todas agencias e cooperativas enviam arquivos Excel à Retaguarda. Hoje o Sicredi 
já possiu mais de 4 milhões de contas ativas.
Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal, 
antes as 10:00 da manhã na abertura das agências.

- Requisito: \
Usar o "serviço da receita" (fake) para processamento automático do arquivo.

- Funcionalidades:
0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma 
nova coluna.
  
Formato CSV: \
agencia;conta;saldo;status \
0101;12225-6;100,00;A \
0101;12226-8;3200,50;A \
3202;40011-1;-35,12;I \
3202;54001-2;0,00;P \
3202;00321-2;34500,00;B \
...
  
 # Solução
  Conforme descrito no problema, era necessário utilizar uma ferramenta para processar um grande volume de dados. Assim foi escolhido o framework Spring Batch, 
  que disponibiliza uma arquitetura para processar dados de forma rápida e segura através de uma implementação de um job (tarefa). \
  O job no Spring Batch é basicamente uma máquina de estados com sequência de steps (etapas). \
  Nessa implementação a estratégia escolhida foi a de uma tarefa divida em pedaços (Reader, Processor, Writer) baseado em um chunk. \
  Também foram implementadas tolerâncias a falhas, como saltos de alguns registros caso aconteça erros no processamento e tentativas de reprocessamento.
  
 # Tecnologias Utilizadas
 <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="20" height="20"/> </a>
Java 11 \
<a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="20" height="20"/> </a>
Spring Batch (v2.6.6) \
<a href="https://maven.apache.org/" target="_blank" rel="noreferrer">  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apache/apache-original.svg" alt="spring" width="20" height="20"/>  </a>
Maven
  
  
           
          
