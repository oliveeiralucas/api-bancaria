package com.oliveeiralucas.apibancaria.enums;

// Enumeração para representar os tipos de carteira
public enum WalletType {
    COMUM(1), LOJISTA(2);

    private int walletType; //armazena valor associado a cada tipo de carteira

    // Construtor privado para associar um valor a cada tipo de carteira
    WalletType(int walletType){
        this.walletType = walletType;
    }

    // Ob-tem o valor associado a um tipo de carteira
    public int getWalletType(){
        return walletType;
    }
}
