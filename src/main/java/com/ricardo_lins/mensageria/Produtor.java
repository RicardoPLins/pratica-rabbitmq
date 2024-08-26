package com.ricardo_lins.mensageria;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Classe responsavel por enviar itens à fila
 */
public class Produtor {

    public static void main(String[] args) throws Exception {
        // Criacao de uma factory de conexao, responsavel por criar as conexoes
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // Localizacao do gestor da fila (Queue Manager)
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        String NOME_FILA = "filaOla";

        try (
                // Criacao de uma conexao
                Connection connection = connectionFactory.newConnection();
                // Criando um canal na conexao
                Channel channel = connection.createChannel()
        ) {
            // Declaracao da fila
            channel.queueDeclare(NOME_FILA, false, false, false, null);
            String mensagem = "Olá mundo, Sou Ricardo Pereira Lins!";
            // Publica uma mensagem na fila
            channel.basicPublish("", NOME_FILA, null, mensagem.getBytes());
            System.out.println("Enviei mensagem: " + mensagem);
        } catch (Exception e) {
            // Lida com qualquer exceção que possa ocorrer
            e.printStackTrace();
        }
    }
}
