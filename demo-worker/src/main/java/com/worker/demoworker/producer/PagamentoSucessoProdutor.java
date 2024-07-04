package com.worker.demoworker.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoSucessoProdutor {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void gerarResposta(String message) {
        amqpTemplate.convertAndSend
                ("pagamento-response-sucesso-exchange",
                        "pagamento-response-sucesso-rout-key",
                        message);
    }
}