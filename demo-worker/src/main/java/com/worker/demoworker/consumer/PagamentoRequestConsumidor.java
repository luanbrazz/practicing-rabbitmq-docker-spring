package com.worker.demoworker.consumer;

import com.worker.demoworker.producer.PagamentoErroProdutor;
import com.worker.demoworker.producer.PagamentoSucessoProdutor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PagamentoRequestConsumidor {

    @Autowired
    private PagamentoErroProdutor erroProdutor;

    @Autowired
    private PagamentoSucessoProdutor sucessoProdutor;

    @RabbitListener(queues = {"pagamento-request-queue"})
    public void receberMensagem(@Payload Message message){
        System.out.println(message);
        if (new Random().nextBoolean()) {
            sucessoProdutor.gerarResposta("Msg de sucesso: " + message);
        } else {
            erroProdutor.gerarResposta("msg de erro: " + message);
        }
    }
}
