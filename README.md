# Pedidos App

## Descrição

Pedidos App é uma API REST desenvolvida em Java usando Spring Boot, MongoDB e RabbitMQ. 

A aplicação permite enviar pedidos á uma fila, listar pedidos por cliente, calcular o valor total dos pedidos e muito mais.

A aplicação assim que envia um pedido a fila, tem um consumer para processa-la e adiciona-la ao banco.

Essa aplicação utiliza Docker para facilitar a configuração e a execução dos serviços necessários.

## Pré-requisitos

- Docker e Docker Compose instalados.

## Configuração do Ambiente

### 1. Clone o Repositório

Clone o repositório para sua máquina local:

```sh
git clone https://github.com/GustavoGodoy/pedidosRabbitMQ.git
cd pedidosRabbitMQ
```

### 2. Executar a Aplicação

Use Docker Compose para iniciar todos os serviços:

```sh
docker-compose up -d
```

### 3. Uso da API

## Endpoints Disponíveis
- {URL}:8080/swagger-ui/index.html: Pagina do Swagger
- {URL}:15762 Central do RabbitMQ (Senhas estarão na configuraçoes de ambiente)


- POST /api/v1/send: Envia um novo pedido para a fila RabbitMQ.
- GET /api/v1/pedidos/{codigoPedido}/total: Gera o valor total de um pedido especifico.
- GET /api/v1/clientes/{codigoCliente}/pedidos/quantidade: Quantidade de pedidos feita por um cliente especifico.
- GET /api/v1/clientes/{codigoCliente}/pedidos: Historico de pedidos de um cliente

## Exemplo de JSON para Criar um Pedido

Acesse o Swagger: {URL}:8080/swagger-ui/index.html


```json
{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 2.50
    }
  ]
}
```

Usando o cURL
Você pode usar cURL para testar o envio de um pedido:

```sh
Copiar código
curl -X POST -H "Content-Type: application/json" -d '{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 2.50
    }
  ]
}' http://localhost:8080/api/v1/send
```

## Usando o Insomnia
- Abra o Insomnia.
- Crie uma nova requisição.
- Selecione o método POST.
- Coloque a URL: http://localhost:8080/api/v1/send.
- Vá para a aba Body.
- Selecione raw e JSON.
- Cole o JSON de exemplo no corpo da requisição.
- Envie a requisição.


### Considerações Finais

Esta aplicação está pronta para ser executada em qualquer ambiente que suporte Docker, facilitando o desenvolvimento, teste e deploy. Para contribuições ou dúvidas, sinta-se à vontade para abrir uma issue ou enviar um pull request no repositório.

Autor: Gustavo Godoy

GitHub: [Meu Perfil GitHub](https://github.com/GustavoGodoy/)

DockerHub: [Meu Perfil DockerHub](https://hub.docker.com/u/godoyq)




