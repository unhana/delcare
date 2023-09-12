package com.declare.dao.dataobject;

import com.declare.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Data
@NoArgsConstructor
@Entity
@Table(name = "declare_expense_item")
@Where(clause = "is_deleted = false")
public class DeclareExpenseItemEntity extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "declare_id", nullable = false)
    private Long declareId;

    @Column(name = "deposit", nullable = false, length = 32)
    private String deposit;

    @Column(name = "management_fee", nullable = false, length = 32)
    private String managementFee;

    @Column(name = "electric_fee", nullable = false, length = 64)
    private String electricFee;

    @Column(name = "electric_fee_detail", nullable = false, columnDefinition = "TEXT")
    private String electricFeeDetail;

    @Column(name = "temp_electric_fee", length = 64)
    private String tempElectricFee;

    @Column(name = "temp_electric_fee_detail", columnDefinition = "TEXT")
    private String tempElectricFeeDetail;

    @Column(name = "gas_fee", length = 64)
    private String gasFee;

    @Column(name = "gas_fee_detail", columnDefinition = "TEXT")
    private String gasFeeDetail;

    @Column(name = "water_fee", length = 32)
    private String waterFee;

    @Column(name = "network_fee", length = 64)
    private String networkFee;

    @Column(name = "network_fee_detail", columnDefinition = "TEXT")
    private String networkFeeDetail;
}
