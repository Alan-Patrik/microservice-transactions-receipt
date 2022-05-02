package com.alanpatrik.bancosantander.transactionreceipt.jms.serializer;

import com.alanpatrik.bancosantander.transactionreceipt.modules.client.TransactionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Objects;

@Slf4j
public class TransactionReceiptSerializer implements Serializer<TransactionDTO> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, TransactionDTO transactionDTO) {

        try {
            if (Objects.nonNull(transactionDTO)) {
                return mapper.registerModule(new JavaTimeModule()).writeValueAsBytes(transactionDTO);

            }
        } catch (JsonProcessingException exception) {
            log.error("Erro ao serializar objeto de transação", exception);
            throw new RuntimeException(exception);
        }

        return null;
    }
}
