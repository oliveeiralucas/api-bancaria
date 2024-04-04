package com.oliveeiralucas.apibancaria.entity.currency;

public class USD extends ACurrency {
        @Override
        public String getCode() {
            return "USD";
        }

        @Override
        public String getSymbol() {
            return "$";
        }
}
