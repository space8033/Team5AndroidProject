package com.example.team5androidproject.dto;

import java.io.Serializable;

public class Receiver implements Serializable {
    private String receiverAddress;
    private String receiverZip;

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "receiverAddress='" + receiverAddress + '\'' +
                ", receiverZip='" + receiverZip + '\'' +
                '}';
    }
}
