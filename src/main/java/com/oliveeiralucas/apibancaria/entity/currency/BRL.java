package com.oliveeiralucas.apibancaria.entity.currency;

public class BRL extends ACurrency {
    @Override
    public String getCode() {
        return "BRL";
    }

    @Override
    public String getSymbol() {
        return "R$";
    }
}
