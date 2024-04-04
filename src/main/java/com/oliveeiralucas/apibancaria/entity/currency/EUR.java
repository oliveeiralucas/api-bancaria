package com.oliveeiralucas.apibancaria.entity.currency;

public class EUR extends ACurrency {
    @Override
    public String getCode() {
        return "EUR";
    }

    @Override
    public String getSymbol() {
        return "€";
    }
}
