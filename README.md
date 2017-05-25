## Tecnologias

Java 8  
Spring Boot 1.5.3  
Maven

## Executar o código

mvn clean install  
java -jar target/syonet-0.0.1-SNAPSHOT.jar

## Lógica do código

O cliente indica uma cidade e uma data para visita;  
O sistema verifica se já existe uma visita na semana para a cidade selecionada;  
Se existe, atribui o mesmo vendedor, senão, sorteia um entre os que ainda não têm visitas naquela cidade;  
Se todos os vendedores têm visitas agendadas, todos entram no sorteio.

## O que poderia ser diferente

Implementação de validações no cadastro, tanto no front-end como no back-end;  
Utilização de ferramentas de análise de código;  
Modularização do projeto;  
Melhorias na exibição das visitas, como filtros de busca e ordenação por colunas da tabela;  
Inclusão de um campo para seleção do estado (atualmente só são listadas cidades do RS).