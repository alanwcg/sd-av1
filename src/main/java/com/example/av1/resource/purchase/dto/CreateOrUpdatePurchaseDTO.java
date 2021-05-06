package com.example.av1.resource.purchase.dto;

import com.example.av1.domain.Purchase;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class CreateOrUpdatePurchaseDTO {

    private Long id;

    @NotBlank(message = "Customer name is missing")
    private String customer;

    @NotNull(message = "Invalid purchase")
    private List<PurchaseProductDTO> products;

    @NotNull(message = "Purchase total is missing")
    private Double total;

    @NotBlank(message = "Credit card number is missing")
    private String creditCardNumber;

    @NotNull(message = "Credit card due date is missing")
    private LocalDate creditCardDueDate;

    @NotBlank(message = "Credit card cvn is missing")
    private String creditCardCVN;

    @NotNull(message = "Installments number is missing")
    private Integer installments;

    public static Purchase to(CreateOrUpdatePurchaseDTO dto) {
        if(Objects.isNull(dto)) {
            return null;
        }

        Purchase purchase = new Purchase();

        Optional.ofNullable(dto.getId())
                .ifPresent(purchase::setId);

        Optional.ofNullable(dto.getCustomer())
                .ifPresent(purchase::setCustomer);

        Optional.ofNullable(dto.getTotal())
                .ifPresent(purchase::setTotal);

        Optional.ofNullable(dto.getCreditCardNumber())
                .ifPresent(purchase::setCreditCardNumber);

        Optional.ofNullable(dto.getCreditCardDueDate())
                .ifPresent(purchase::setCreditCardDueDate);

        Optional.ofNullable(dto.getCreditCardCVN())
                .ifPresent(purchase::setCreditCardCVN);

        Optional.ofNullable(dto.getInstallments())
                .ifPresent(purchase::setInstallments);

        return purchase;
    }

    public static RequestTokenDTO toRequestTokenBody(CreateOrUpdatePurchaseDTO dto) {
        if(Objects.isNull(dto)) {
            return null;
        }

        RequestTokenDTO requestTokenBody = new RequestTokenDTO();

        Optional.ofNullable(dto.getCreditCardNumber())
                .ifPresent(requestTokenBody::setNumber);

        Optional.ofNullable(dto.getCreditCardDueDate())
                .ifPresent(requestTokenBody::setDueDate);

        Optional.ofNullable(dto.getCreditCardCVN())
                .ifPresent(requestTokenBody::setCvn);

        return requestTokenBody;
    }

    public static InvoiceDTO toInvoice(CreateOrUpdatePurchaseDTO dto) {
        if(Objects.isNull(dto)) {
            return null;
        }

        InvoiceDTO invoice = new InvoiceDTO();

        Optional.ofNullable(dto.getTotal())
                .ifPresent(invoice::setValue);

        Optional.ofNullable(dto.getInstallments())
                .ifPresent(invoice::setInstallments);

        return invoice;
    }

}
