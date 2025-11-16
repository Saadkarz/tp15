package banque_service.demo.dto;

import banque_service.demo.entities.TypeTransaction;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionRequest {
    private Long compteId;
    private double montant;
    private Date date;
    private TypeTransaction type;
}
