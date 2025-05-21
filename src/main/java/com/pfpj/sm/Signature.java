package com.pfpj.sm;

import java.math.BigInteger;

public class Signature {
    private BigInteger r;
    private BigInteger s;

    public Signature() {
        this.r = BigInteger.ZERO;
        this.s = BigInteger.ZERO;
    }

    public Signature(BigInteger r, BigInteger s) {
        this.r = r;
        this.s = s;
    }

    public BigInteger getR() {
        return r;
    }

    public void setR(BigInteger r) {
        this.r = r;
    }

    public BigInteger getS() {
        return s;
    }

    public void setS(BigInteger s) {
        this.s = s;
    }
}
