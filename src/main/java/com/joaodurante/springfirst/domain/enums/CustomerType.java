package com.joaodurante.springfirst.domain.enums;

public enum CustomerType {
    PHYSICALPERSON(0, "Physical person"),
    LEGALENTITY(1, "Legal entity");

    private int code;
    private String description;

    private CustomerType(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public static CustomerType toEnum(Integer code){
        if(code == null)
            return null;
        else{
            for(CustomerType i : CustomerType.values()){
                if(code.equals(i.getCode()))
                    return i;
            }
            throw new IllegalArgumentException("Invalid id: " + code);
        }
    }
}
