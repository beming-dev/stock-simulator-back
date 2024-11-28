package com.stock.stock_simulator.DTO;

public class KoreaStockPriceDto {
    private String rsym;
    private String zdiv;
    private String base;
    private String pvol;
    private String last;
    private String sign;
    private String diff;
    private String rate;
    private String tvol;
    private String tamt;
    private String tard;

    // Getters and Setters

    public String getRsym() {
        return rsym;
    }

    public void setRsym(String rsym) {
        this.rsym = rsym;
    }

    public String getZdiv() {
        return zdiv;
    }

    public void setZdiv(String zdiv) {
        this.zdiv = zdiv;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getPvol() {
        return pvol;
    }

    public void setPvol(String pvol) {
        this.pvol = pvol;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTvol() {
        return tvol;
    }

    public void setTvol(String tvol) {
        this.tvol = tvol;
    }

    public String getTamt() {
        return tamt;
    }

    public void setTamt(String tamt) {
        this.tamt = tamt;
    }

    public String getTard() {
        return tard;
    }

    public void setTard(String tard) {
        this.tard = tard;
    }

    @Override
    public String toString() {
        return "KoreaStockPriceDto{" +
                "rsym='" + rsym + '\'' +
                ", zdiv='" + zdiv + '\'' +
                ", base='" + base + '\'' +
                ", pvol='" + pvol + '\'' +
                ", last='" + last + '\'' +
                ", sign='" + sign + '\'' +
                ", diff='" + diff + '\'' +
                ", rate='" + rate + '\'' +
                ", tvol='" + tvol + '\'' +
                ", tamt='" + tamt + '\'' +
                ", tard='" + tard + '\'' +
                '}';
    }
}
