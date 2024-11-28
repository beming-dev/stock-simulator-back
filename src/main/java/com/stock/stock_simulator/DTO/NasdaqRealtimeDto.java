package com.stock.stock_simulator.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "실시간 종목 데이터 DTO")
public class NasdaqRealtimeDto {

    @Schema(description = "실시간 종목코드", example = "AAPL", required = true)
    private String RSYM;

    @Schema(description = "종목코드", example = "APPL123456", required = true)
    private String SYMB;

    @Schema(description = "수수점 자리수", example = "2", required = true)
    private String ZDIV;

    @Schema(description = "현재 영업일자", example = "20231128", required = true)
    private String TYMD;

    @Schema(description = "현재 일자", example = "1128", required = true)
    private String XYMD;

    @Schema(description = "현재 시간", example = "153000", required = true)
    private String XHMS;

    @Schema(description = "한국 일자", example = "1128", required = true)
    private String KYMD;

    @Schema(description = "한국 시간", example = "153000", required = true)
    private String KHMS;

    @Schema(description = "시가", example = "1200", required = true)
    private String OPEN;

    @Schema(description = "고가", example = "1300", required = true)
    private String HIGH;

    @Schema(description = "저가", example = "1100", required = true)
    private String LOW;

    @Schema(description = "현재가", example = "1250", required = true)
    private String LAST;

    @Schema(description = "대비구분", example = "1", required = true)
    private String SIGN;

    @Schema(description = "전일대비", example = "50", required = true)
    private String DIFF;

    @Schema(description = "등락율", example = "4.2", required = true)
    private String RATE;

    @Schema(description = "매수호가", example = "1240", required = true)
    private String PBID;

    @Schema(description = "매도호가", example = "1260", required = true)
    private String PASK;

    @Schema(description = "매수잔량", example = "5000", required = true)
    private String VBID;

    @Schema(description = "매도잔량", example = "6000", required = true)
    private String VASK;

    @Schema(description = "체결량", example = "10000", required = true)
    private String EVOL;

    @Schema(description = "거래량", example = "15000", required = true)
    private String TVOL;

    @Schema(description = "거래대금", example = "18750000", required = true)
    private String TAMT;

    @Schema(description = "매도체결량", example = "8000", required = true)
    private String BIVL;

    @Schema(description = "매수체결량", example = "7000", required = true)
    private String ASVL;

    @Schema(description = "체결강도", example = "95", required = true)
    private String STRN;

    @Schema(description = "시장구분 (1: 장중, 2: 장전, 3: 장후)", example = "1", required = true)
    private String MTYP;

    public String getRSYM() {
        return RSYM;
    }

    public void setRSYM(String RSYM) {
        this.RSYM = RSYM;
    }

    public String getSYMB() {
        return SYMB;
    }

    public void setSYMB(String SYMB) {
        this.SYMB = SYMB;
    }

    public String getZDIV() {
        return ZDIV;
    }

    public void setZDIV(String ZDIV) {
        this.ZDIV = ZDIV;
    }

    public String getTYMD() {
        return TYMD;
    }

    public void setTYMD(String TYMD) {
        this.TYMD = TYMD;
    }

    public String getXYMD() {
        return XYMD;
    }

    public void setXYMD(String XYMD) {
        this.XYMD = XYMD;
    }

    public String getXHMS() {
        return XHMS;
    }

    public void setXHMS(String XHMS) {
        this.XHMS = XHMS;
    }

    public String getKYMD() {
        return KYMD;
    }

    public void setKYMD(String KYMD) {
        this.KYMD = KYMD;
    }

    public String getKHMS() {
        return KHMS;
    }

    public void setKHMS(String KHMS) {
        this.KHMS = KHMS;
    }

    public String getOPEN() {
        return OPEN;
    }

    public void setOPEN(String OPEN) {
        this.OPEN = OPEN;
    }

    public String getHIGH() {
        return HIGH;
    }

    public void setHIGH(String HIGH) {
        this.HIGH = HIGH;
    }

    public String getLOW() {
        return LOW;
    }

    public void setLOW(String LOW) {
        this.LOW = LOW;
    }

    public String getLAST() {
        return LAST;
    }

    public void setLAST(String LAST) {
        this.LAST = LAST;
    }

    public String getSIGN() {
        return SIGN;
    }

    public void setSIGN(String SIGN) {
        this.SIGN = SIGN;
    }

    public String getDIFF() {
        return DIFF;
    }

    public void setDIFF(String DIFF) {
        this.DIFF = DIFF;
    }

    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getPBID() {
        return PBID;
    }

    public void setPBID(String PBID) {
        this.PBID = PBID;
    }

    public String getPASK() {
        return PASK;
    }

    public void setPASK(String PASK) {
        this.PASK = PASK;
    }

    public String getVBID() {
        return VBID;
    }

    public void setVBID(String VBID) {
        this.VBID = VBID;
    }

    public String getVASK() {
        return VASK;
    }

    public void setVASK(String VASK) {
        this.VASK = VASK;
    }

    public String getEVOL() {
        return EVOL;
    }

    public void setEVOL(String EVOL) {
        this.EVOL = EVOL;
    }

    public String getTVOL() {
        return TVOL;
    }

    public void setTVOL(String TVOL) {
        this.TVOL = TVOL;
    }

    public String getTAMT() {
        return TAMT;
    }

    public void setTAMT(String TAMT) {
        this.TAMT = TAMT;
    }

    public String getBIVL() {
        return BIVL;
    }

    public void setBIVL(String BIVL) {
        this.BIVL = BIVL;
    }

    public String getASVL() {
        return ASVL;
    }

    public void setASVL(String ASVL) {
        this.ASVL = ASVL;
    }

    public String getSTRN() {
        return STRN;
    }

    public void setSTRN(String STRN) {
        this.STRN = STRN;
    }

    public String getMTYP() {
        return MTYP;
    }

    public void setMTYP(String MTYP) {
        this.MTYP = MTYP;
    }

    @Override
    public String toString() {
        return "NasdaqRealtimeDto{" +
                "RSYM='" + RSYM + '\'' +
                ", SYMB='" + SYMB + '\'' +
                ", ZDIV='" + ZDIV + '\'' +
                ", TYMD='" + TYMD + '\'' +
                ", XYMD='" + XYMD + '\'' +
                ", XHMS='" + XHMS + '\'' +
                ", KYMD='" + KYMD + '\'' +
                ", KHMS='" + KHMS + '\'' +
                ", OPEN='" + OPEN + '\'' +
                ", HIGH='" + HIGH + '\'' +
                ", LOW='" + LOW + '\'' +
                ", LAST='" + LAST + '\'' +
                ", SIGN='" + SIGN + '\'' +
                ", DIFF='" + DIFF + '\'' +
                ", RATE='" + RATE + '\'' +
                ", PBID='" + PBID + '\'' +
                ", PASK='" + PASK + '\'' +
                ", VBID='" + VBID + '\'' +
                ", VASK='" + VASK + '\'' +
                ", EVOL='" + EVOL + '\'' +
                ", TVOL='" + TVOL + '\'' +
                ", TAMT='" + TAMT + '\'' +
                ", BIVL='" + BIVL + '\'' +
                ", ASVL='" + ASVL + '\'' +
                ", STRN='" + STRN + '\'' +
                ", MTYP='" + MTYP + '\'' +
                '}';
    }
}