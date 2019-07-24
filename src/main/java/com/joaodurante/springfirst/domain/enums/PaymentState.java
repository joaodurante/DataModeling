package com.joaodurante.springfirst.domain.enums;

public enum PaymentState {
    PAID(0, "Paid"),
    PENDING(1, "Pending"),
    CANCELED(2, "Canceled");

    private int code;
    private String description;

    private PaymentState(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public static PaymentState toEnum(Integer code){
        if(code == null)
            return null;
        else{
            for(PaymentState i : PaymentState.values()){
                if(code.equals(i.getCode()))
                    return i;
            }
            throw new IllegalArgumentException("Invalid id: " + code);
        }
    }
}
