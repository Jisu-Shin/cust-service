package com.cust_service.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cust extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cust_id")
    private Long id;

    private String name;

    @Column(length = 12)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private CustSmsConsentType smsConsentType;

    @Builder
    private Cust(String name, String phoneNumber, CustSmsConsentType smsConsentType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.smsConsentType = smsConsentType;
    }

    //==생성메서드==
    public static Cust createCust(String name, String phoneNumber, CustSmsConsentType smsConsentType){
        Cust cust = new Cust(name, phoneNumber, smsConsentType);
        return cust;
    }

    public void update(String phoneNumber, CustSmsConsentType type) {
        this.phoneNumber = phoneNumber;
        this.smsConsentType = type;
    }
}
