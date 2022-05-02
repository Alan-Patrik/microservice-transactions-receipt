package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt;

import com.alanpatrik.bancosantander.transactionreceipt.enums.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "comprovante")
@EntityListeners(AuditingEntityListener.class)
public class TransactionReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo VALOR é obrigatório!")
    @Column(name = "valor")
    private double value;

    @NotNull(message = "Campo TIPO TRANSAÇÃO é obrigatório!")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transacao")
    private TransactionType transactionType;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime descriptionDate;

    @NotNull(message = "Campo CONTA DO REMETENTE é obrigatório!")
    @Column(name = "conta_remetente", columnDefinition = "MEDIUMTEXT")
    @Type(type = "org.hibernate.type.TextType")
    private String senderAccount;

    @NotNull(message = "Campo CONTA DO DESTINATÁRIO é obrigatório!")
    @Column(name = "conta_destinatario", columnDefinition = "MEDIUMTEXT")
    @Type(type = "org.hibernate.type.TextType")
    private String destinationAccount;
}
