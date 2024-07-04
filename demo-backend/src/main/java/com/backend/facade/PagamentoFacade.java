package com.backend.facade;

import com.backend.dto.PagamentoDTO;
import com.backend.producer.PagamentoRequestProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoFacade {

    @Autowired
    private PagamentoRequestProducer producer;

    public String solicitarPagamento(PagamentoDTO request) {
        try {
            producer.integrar(request);
        } catch (JsonProcessingException e) {
            return "ocorreu um erro ao solicitar pagemento..." + e.getMessage();
        }
        return "Pagamento aguardando confirmação!";
    }

    public void erroPagamento(String payload) {
        System.err.println("===RESPOSTA ERRO =====" + payload);
    }

    public void sucessoPagamento(String payload) {
        System.out.println("===RESPOSTA SUCESSO =====" + payload);
    }
}
