package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionReceiptRepository extends JpaRepository<TransactionReceipt, Long> {
}
