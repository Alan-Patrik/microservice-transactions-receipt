package com.alanpatrik.bancosantander.transactionreceipt.modules.client;

import com.alanpatrik.bancosantander.transactionreceipt.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.TransactionReceipt;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.TransactionReceiptMapper;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.TransactionReceiptRepository;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.TransactionReceiptServiceImpl;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.AccountDTO;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptRequestDTO;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionReceiptClientService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final KafkaTemplate<Object, TransactionDTO> kafkaTemplate;
    private final TransactionReceiptServiceImpl transactionReceiptService;
    private final TransactionReceiptRepository transactionReceiptRepository;
    private final TransactionReceiptMapper transactionReceiptMapper = TransactionReceiptMapper.INSTANCE;

    @KafkaListener(topics = "EnviarComprovante", groupId = "MicroServicoEnviarComprovante")
    private void execute(ConsumerRecord<String, TransactionDTO> consumerRecord) throws JsonProcessingException {
        log.info("transacão recebida {}", consumerRecord.value());

        TransactionDTO transactionDTO = consumerRecord.value();

        TransactionReceiptRequestDTO transactionReceiptRequestDTO = new TransactionReceiptRequestDTO();
        transactionReceiptRequestDTO.setValue(transactionDTO.getValue());
        transactionReceiptRequestDTO.setTransactionType(transactionDTO.getTransactionType());
        transactionReceiptRequestDTO.setDescriptionDate(transactionDTO.getDescriptionDate());
        transactionReceiptRequestDTO.setSenderAccount(transactionDTO.getSenderAccount());
        transactionReceiptRequestDTO.setDestinationAccount(transactionDTO.getDestinationAccount());

        TransactionReceiptResponseDTO transactionReceiptResponseDTO = transactionReceiptService.create(transactionReceiptRequestDTO);
        save(transactionReceiptMapper.toModel(transactionReceiptResponseDTO));
    }

    public void save(TransactionReceipt transactionReceipt) throws JsonProcessingException {
        TransactionReceipt transactionResponse = transactionReceiptRepository.save(transactionReceipt);
        TransactionDTO transactionDTO = transactionReceiptMapper.toDTO(transactionResponse);

        create("EnviarComprovanteDestinatario", transactionDTO);
    }

    public void create(String topic, TransactionDTO transactionDTO) {
        kafkaTemplate.send(topic, transactionDTO);

        log.info("comprovante enviado {}", transactionDTO);

        //AQUI ENVIARIA PARA UM OUTRO MICROSERVIÇO DE ENVIO DE EMAIL...
    }
}
