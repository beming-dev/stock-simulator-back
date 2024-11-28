package com.stock.stock_simulator.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "API 응답 모델")
public class KoreaRealtimeDto {

    @Schema(description = "유가증권 단축 종목코드", example = "123456789", required = true)
    private String MKSC_SHRN_ISCD;

    @Schema(description = "주식 체결 시간", example = "120030", required = true)
    private String STCK_CNTG_HOUR;

    @Schema(description = "주식 현재가", example = "1234", required = true)
    private Number STCK_PRPR;

    @Schema(description = "전일 대비 부호 (1: 상한, 2: 상승, 3: 보합, 4: 하락, 5: 하한)", example = "2", required = true)
    private String PRDY_VRSS_SIGN;

    @Schema(description = "전일 대비", example = "12", required = true)
    private Number PRDY_VRSS;

    @Schema(description = "전일 대비율", example = "1.23", required = true)
    private Number PRDY_CTRT;

    @Schema(description = "가중 평균 주식 가격", example = "1234.56", required = true)
    private Number WGHT_AVRG_STCK_PRC;

    @Schema(description = "주식 시가", example = "1200", required = true)
    private Number STCK_OPRC;

    @Schema(description = "주식 최고가", example = "1300", required = true)
    private Number STCK_HGPR;

    @Schema(description = "주식 최저가", example = "1100", required = true)
    private Number STCK_LWPR;

    @Schema(description = "매도호가1", example = "1205", required = true)
    private Number ASKP1;

    @Schema(description = "매수호가1", example = "1195", required = true)
    private Number BIDP1;

    @Schema(description = "체결 거래량", example = "10000", required = true)
    private Number CNTG_VOL;

    @Schema(description = "누적 거래량", example = "50000", required = true)
    private Number ACML_VOL;

    @Schema(description = "누적 거래 대금", example = "60000000", required = true)
    private Number ACML_TR_PBMN;

    @Schema(description = "매도 체결 건수", example = "100", required = true)
    private Number SELN_CNTG_CSNU;

    @Schema(description = "매수 체결 건수", example = "120", required = true)
    private Number SHNU_CNTG_CSNU;

    @Schema(description = "순매수 체결 건수", example = "20", required = true)
    private Number NTBY_CNTG_CSNU;

    @Schema(description = "체결 강도", example = "80", required = true)
    private Number CTTR;

    @Schema(description = "총 매도 수량", example = "50000", required = true)
    private Number SELN_CNTG_SMTN;

    @Schema(description = "총 매수 수량", example = "52000", required = true)
    private Number SHNU_CNTG_SMTN;

    @Schema(description = "체결 구분 (1: 매수, 3: 잔전, 5: 매도)", example = "1", required = true)
    private String CCLD_DVSN;

    @Schema(description = "매수 비율", example = "52.0", required = true)
    private Number SHNU_RATE;

    @Schema(description = "전일 거래량 대비 등락률", example = "1.5", required = true)
    private Number PRDY_VOL_VRSS_ACML_VOL_RATE;

    @Schema(description = "시가 시간", example = "090000", required = true)
    private String OPRC_HOUR;

    @Schema(description = "시가 대비 구분 (1: 상한, 2: 상승, 3: 보합, 4: 하락, 5: 하한)", example = "2", required = true)
    private String OPRC_VRSS_PRPR_SIGN;

    @Schema(description = "시가 대비", example = "20", required = true)
    private Number OPRC_VRSS_PRPR;

    @Schema(description = "최고가 시간", example = "100000", required = true)
    private String HGPR_HOUR;

    @Schema(description = "고가 대비 구분 (1: 상한, 2: 상승, 3: 보합, 4: 하락, 5: 하한)", example = "1", required = true)
    private String HGPR_VRSS_PRPR_SIGN;

    @Schema(description = "고가 대비", example = "30", required = true)
    private Number HGPR_VRSS_PRPR;

    @Schema(description = "저가 시간", example = "110000", required = true)
    private String LWPR_HOUR;

    @Schema(description = "저가 대비 구분 (1: 상한, 2: 상승, 3: 보합, 4: 하락, 5: 하한)", example = "5", required = true)
    private String LWPR_VRSS_PRPR_SIGN;

    @Schema(description = "저가 대비", example = "-10", required = true)
    private Number LWPR_VRSS_PRPR;

    @Schema(description = "영업 일자", example = "20231128", required = true)
    private String BSOP_DATE;

    @Schema(description = "신 장운영 구분 코드", example = "12", required = true)
    private String NEW_MKOP_CLS_CODE;

    @Schema(description = "거래 정지 여부 (Y: 정지, N: 정상 거래)", example = "N", required = true)
    private String TRHT_YN;

    @Schema(description = "매도호가 잔량1", example = "3000", required = true)
    private Number ASKP_RSQN1;

    @Schema(description = "매수호가 잔량1", example = "4000", required = true)
    private Number BIDP_RSQN1;

    @Schema(description = "총 매도호가 잔량", example = "15000", required = true)
    private Number TOTAL_ASKP_RSQN;

    @Schema(description = "총 매수호가 잔량", example = "18000", required = true)
    private Number TOTAL_BIDP_RSQN;

    @Schema(description = "거래량 회전율", example = "0.05", required = true)
    private Number VOL_TNRT;

    @Schema(description = "전일 동시간 누적 거래량", example = "10000", required = true)
    private Number PRDY_SMNS_HOUR_ACML_VOL;

    @Schema(description = "전일 동시간 누적 거래량 비율", example = "0.12", required = true)
    private Number PRDY_SMNS_HOUR_ACML_VOL_RATE;

    @Schema(description = "시간 구분 코드", example = "A", required = true)
    private String HOUR_CLS_CODE;

    @Schema(description = "임의종료구분코드", example = "A", required = true)
    private String MRKT_TRTM_CLS_CODE;

    @Schema(description = "정적VI발동기준가", example = "12345", required = true)
    private Number VI_STND_PRC;

    public String getMKSC_SHRN_ISCD() {
        return MKSC_SHRN_ISCD;
    }

    public void setMKSC_SHRN_ISCD(String MKSC_SHRN_ISCD) {
        this.MKSC_SHRN_ISCD = MKSC_SHRN_ISCD;
    }

    public String getSTCK_CNTG_HOUR() {
        return STCK_CNTG_HOUR;
    }

    public void setSTCK_CNTG_HOUR(String STCK_CNTG_HOUR) {
        this.STCK_CNTG_HOUR = STCK_CNTG_HOUR;
    }

    public Number getSTCK_PRPR() {
        return STCK_PRPR;
    }

    public void setSTCK_PRPR(Number STCK_PRPR) {
        this.STCK_PRPR = STCK_PRPR;
    }

    public String getPRDY_VRSS_SIGN() {
        return PRDY_VRSS_SIGN;
    }

    public void setPRDY_VRSS_SIGN(String PRDY_VRSS_SIGN) {
        this.PRDY_VRSS_SIGN = PRDY_VRSS_SIGN;
    }

    public Number getPRDY_VRSS() {
        return PRDY_VRSS;
    }

    public void setPRDY_VRSS(Number PRDY_VRSS) {
        this.PRDY_VRSS = PRDY_VRSS;
    }

    public Number getPRDY_CTRT() {
        return PRDY_CTRT;
    }

    public void setPRDY_CTRT(Number PRDY_CTRT) {
        this.PRDY_CTRT = PRDY_CTRT;
    }

    public Number getWGHT_AVRG_STCK_PRC() {
        return WGHT_AVRG_STCK_PRC;
    }

    public void setWGHT_AVRG_STCK_PRC(Number WGHT_AVRG_STCK_PRC) {
        this.WGHT_AVRG_STCK_PRC = WGHT_AVRG_STCK_PRC;
    }

    public Number getSTCK_OPRC() {
        return STCK_OPRC;
    }

    public void setSTCK_OPRC(Number STCK_OPRC) {
        this.STCK_OPRC = STCK_OPRC;
    }

    public Number getSTCK_HGPR() {
        return STCK_HGPR;
    }

    public void setSTCK_HGPR(Number STCK_HGPR) {
        this.STCK_HGPR = STCK_HGPR;
    }

    public Number getSTCK_LWPR() {
        return STCK_LWPR;
    }

    public void setSTCK_LWPR(Number STCK_LWPR) {
        this.STCK_LWPR = STCK_LWPR;
    }

    public Number getASKP1() {
        return ASKP1;
    }

    public void setASKP1(Number ASKP1) {
        this.ASKP1 = ASKP1;
    }

    public Number getBIDP1() {
        return BIDP1;
    }

    public void setBIDP1(Number BIDP1) {
        this.BIDP1 = BIDP1;
    }

    public Number getCNTG_VOL() {
        return CNTG_VOL;
    }

    public void setCNTG_VOL(Number CNTG_VOL) {
        this.CNTG_VOL = CNTG_VOL;
    }

    public Number getACML_VOL() {
        return ACML_VOL;
    }

    public void setACML_VOL(Number ACML_VOL) {
        this.ACML_VOL = ACML_VOL;
    }

    public Number getACML_TR_PBMN() {
        return ACML_TR_PBMN;
    }

    public void setACML_TR_PBMN(Number ACML_TR_PBMN) {
        this.ACML_TR_PBMN = ACML_TR_PBMN;
    }

    public Number getSELN_CNTG_CSNU() {
        return SELN_CNTG_CSNU;
    }

    public void setSELN_CNTG_CSNU(Number SELN_CNTG_CSNU) {
        this.SELN_CNTG_CSNU = SELN_CNTG_CSNU;
    }

    public Number getSHNU_CNTG_CSNU() {
        return SHNU_CNTG_CSNU;
    }

    public void setSHNU_CNTG_CSNU(Number SHNU_CNTG_CSNU) {
        this.SHNU_CNTG_CSNU = SHNU_CNTG_CSNU;
    }

    public Number getNTBY_CNTG_CSNU() {
        return NTBY_CNTG_CSNU;
    }

    public void setNTBY_CNTG_CSNU(Number NTBY_CNTG_CSNU) {
        this.NTBY_CNTG_CSNU = NTBY_CNTG_CSNU;
    }

    public Number getCTTR() {
        return CTTR;
    }

    public void setCTTR(Number CTTR) {
        this.CTTR = CTTR;
    }

    public Number getSELN_CNTG_SMTN() {
        return SELN_CNTG_SMTN;
    }

    public void setSELN_CNTG_SMTN(Number SELN_CNTG_SMTN) {
        this.SELN_CNTG_SMTN = SELN_CNTG_SMTN;
    }

    public Number getSHNU_CNTG_SMTN() {
        return SHNU_CNTG_SMTN;
    }

    public void setSHNU_CNTG_SMTN(Number SHNU_CNTG_SMTN) {
        this.SHNU_CNTG_SMTN = SHNU_CNTG_SMTN;
    }

    public String getCCLD_DVSN() {
        return CCLD_DVSN;
    }

    public void setCCLD_DVSN(String CCLD_DVSN) {
        this.CCLD_DVSN = CCLD_DVSN;
    }

    public Number getSHNU_RATE() {
        return SHNU_RATE;
    }

    public void setSHNU_RATE(Number SHNU_RATE) {
        this.SHNU_RATE = SHNU_RATE;
    }

    public Number getPRDY_VOL_VRSS_ACML_VOL_RATE() {
        return PRDY_VOL_VRSS_ACML_VOL_RATE;
    }

    public void setPRDY_VOL_VRSS_ACML_VOL_RATE(Number PRDY_VOL_VRSS_ACML_VOL_RATE) {
        this.PRDY_VOL_VRSS_ACML_VOL_RATE = PRDY_VOL_VRSS_ACML_VOL_RATE;
    }

    public String getOPRC_HOUR() {
        return OPRC_HOUR;
    }

    public void setOPRC_HOUR(String OPRC_HOUR) {
        this.OPRC_HOUR = OPRC_HOUR;
    }

    public String getOPRC_VRSS_PRPR_SIGN() {
        return OPRC_VRSS_PRPR_SIGN;
    }

    public void setOPRC_VRSS_PRPR_SIGN(String OPRC_VRSS_PRPR_SIGN) {
        this.OPRC_VRSS_PRPR_SIGN = OPRC_VRSS_PRPR_SIGN;
    }

    public Number getOPRC_VRSS_PRPR() {
        return OPRC_VRSS_PRPR;
    }

    public void setOPRC_VRSS_PRPR(Number OPRC_VRSS_PRPR) {
        this.OPRC_VRSS_PRPR = OPRC_VRSS_PRPR;
    }

    public String getHGPR_HOUR() {
        return HGPR_HOUR;
    }

    public void setHGPR_HOUR(String HGPR_HOUR) {
        this.HGPR_HOUR = HGPR_HOUR;
    }

    public String getHGPR_VRSS_PRPR_SIGN() {
        return HGPR_VRSS_PRPR_SIGN;
    }

    public void setHGPR_VRSS_PRPR_SIGN(String HGPR_VRSS_PRPR_SIGN) {
        this.HGPR_VRSS_PRPR_SIGN = HGPR_VRSS_PRPR_SIGN;
    }

    public Number getHGPR_VRSS_PRPR() {
        return HGPR_VRSS_PRPR;
    }

    public void setHGPR_VRSS_PRPR(Number HGPR_VRSS_PRPR) {
        this.HGPR_VRSS_PRPR = HGPR_VRSS_PRPR;
    }

    public String getLWPR_HOUR() {
        return LWPR_HOUR;
    }

    public void setLWPR_HOUR(String LWPR_HOUR) {
        this.LWPR_HOUR = LWPR_HOUR;
    }

    public String getLWPR_VRSS_PRPR_SIGN() {
        return LWPR_VRSS_PRPR_SIGN;
    }

    public void setLWPR_VRSS_PRPR_SIGN(String LWPR_VRSS_PRPR_SIGN) {
        this.LWPR_VRSS_PRPR_SIGN = LWPR_VRSS_PRPR_SIGN;
    }

    public Number getLWPR_VRSS_PRPR() {
        return LWPR_VRSS_PRPR;
    }

    public void setLWPR_VRSS_PRPR(Number LWPR_VRSS_PRPR) {
        this.LWPR_VRSS_PRPR = LWPR_VRSS_PRPR;
    }

    public String getBSOP_DATE() {
        return BSOP_DATE;
    }

    public void setBSOP_DATE(String BSOP_DATE) {
        this.BSOP_DATE = BSOP_DATE;
    }

    public String getNEW_MKOP_CLS_CODE() {
        return NEW_MKOP_CLS_CODE;
    }

    public void setNEW_MKOP_CLS_CODE(String NEW_MKOP_CLS_CODE) {
        this.NEW_MKOP_CLS_CODE = NEW_MKOP_CLS_CODE;
    }

    public String getTRHT_YN() {
        return TRHT_YN;
    }

    public void setTRHT_YN(String TRHT_YN) {
        this.TRHT_YN = TRHT_YN;
    }

    public Number getASKP_RSQN1() {
        return ASKP_RSQN1;
    }

    public void setASKP_RSQN1(Number ASKP_RSQN1) {
        this.ASKP_RSQN1 = ASKP_RSQN1;
    }

    public Number getBIDP_RSQN1() {
        return BIDP_RSQN1;
    }

    public void setBIDP_RSQN1(Number BIDP_RSQN1) {
        this.BIDP_RSQN1 = BIDP_RSQN1;
    }

    public Number getTOTAL_ASKP_RSQN() {
        return TOTAL_ASKP_RSQN;
    }

    public void setTOTAL_ASKP_RSQN(Number TOTAL_ASKP_RSQN) {
        this.TOTAL_ASKP_RSQN = TOTAL_ASKP_RSQN;
    }

    public Number getTOTAL_BIDP_RSQN() {
        return TOTAL_BIDP_RSQN;
    }

    public void setTOTAL_BIDP_RSQN(Number TOTAL_BIDP_RSQN) {
        this.TOTAL_BIDP_RSQN = TOTAL_BIDP_RSQN;
    }

    public Number getVOL_TNRT() {
        return VOL_TNRT;
    }

    public void setVOL_TNRT(Number VOL_TNRT) {
        this.VOL_TNRT = VOL_TNRT;
    }

    public Number getPRDY_SMNS_HOUR_ACML_VOL() {
        return PRDY_SMNS_HOUR_ACML_VOL;
    }

    public void setPRDY_SMNS_HOUR_ACML_VOL(Number PRDY_SMNS_HOUR_ACML_VOL) {
        this.PRDY_SMNS_HOUR_ACML_VOL = PRDY_SMNS_HOUR_ACML_VOL;
    }

    public Number getPRDY_SMNS_HOUR_ACML_VOL_RATE() {
        return PRDY_SMNS_HOUR_ACML_VOL_RATE;
    }

    public void setPRDY_SMNS_HOUR_ACML_VOL_RATE(Number PRDY_SMNS_HOUR_ACML_VOL_RATE) {
        this.PRDY_SMNS_HOUR_ACML_VOL_RATE = PRDY_SMNS_HOUR_ACML_VOL_RATE;
    }

    public String getHOUR_CLS_CODE() {
        return HOUR_CLS_CODE;
    }

    public void setHOUR_CLS_CODE(String HOUR_CLS_CODE) {
        this.HOUR_CLS_CODE = HOUR_CLS_CODE;
    }

    public String getMRKT_TRTM_CLS_CODE() {
        return MRKT_TRTM_CLS_CODE;
    }

    public void setMRKT_TRTM_CLS_CODE(String MRKT_TRTM_CLS_CODE) {
        this.MRKT_TRTM_CLS_CODE = MRKT_TRTM_CLS_CODE;
    }

    public Number getVI_STND_PRC() {
        return VI_STND_PRC;
    }

    public void setVI_STND_PRC(Number VI_STND_PRC) {
        this.VI_STND_PRC = VI_STND_PRC;
    }

    @Override
    public String toString() {
        return "KoreaRealtimeDto{" +
                "MKSC_SHRN_ISCD='" + MKSC_SHRN_ISCD + '\'' +
                ", STCK_CNTG_HOUR='" + STCK_CNTG_HOUR + '\'' +
                ", STCK_PRPR=" + STCK_PRPR +
                ", PRDY_VRSS_SIGN='" + PRDY_VRSS_SIGN + '\'' +
                ", PRDY_VRSS=" + PRDY_VRSS +
                ", PRDY_CTRT=" + PRDY_CTRT +
                ", WGHT_AVRG_STCK_PRC=" + WGHT_AVRG_STCK_PRC +
                ", STCK_OPRC=" + STCK_OPRC +
                ", STCK_HGPR=" + STCK_HGPR +
                ", STCK_LWPR=" + STCK_LWPR +
                ", ASKP1=" + ASKP1 +
                ", BIDP1=" + BIDP1 +
                ", CNTG_VOL=" + CNTG_VOL +
                ", ACML_VOL=" + ACML_VOL +
                ", ACML_TR_PBMN=" + ACML_TR_PBMN +
                ", SELN_CNTG_CSNU=" + SELN_CNTG_CSNU +
                ", SHNU_CNTG_CSNU=" + SHNU_CNTG_CSNU +
                ", NTBY_CNTG_CSNU=" + NTBY_CNTG_CSNU +
                ", CTTR=" + CTTR +
                ", SELN_CNTG_SMTN=" + SELN_CNTG_SMTN +
                ", SHNU_CNTG_SMTN=" + SHNU_CNTG_SMTN +
                ", CCLD_DVSN='" + CCLD_DVSN + '\'' +
                ", SHNU_RATE=" + SHNU_RATE +
                ", PRDY_VOL_VRSS_ACML_VOL_RATE=" + PRDY_VOL_VRSS_ACML_VOL_RATE +
                ", OPRC_HOUR='" + OPRC_HOUR + '\'' +
                ", OPRC_VRSS_PRPR_SIGN='" + OPRC_VRSS_PRPR_SIGN + '\'' +
                ", OPRC_VRSS_PRPR=" + OPRC_VRSS_PRPR +
                ", HGPR_HOUR='" + HGPR_HOUR + '\'' +
                ", HGPR_VRSS_PRPR_SIGN='" + HGPR_VRSS_PRPR_SIGN + '\'' +
                ", HGPR_VRSS_PRPR=" + HGPR_VRSS_PRPR +
                ", LWPR_HOUR='" + LWPR_HOUR + '\'' +
                ", LWPR_VRSS_PRPR_SIGN='" + LWPR_VRSS_PRPR_SIGN + '\'' +
                ", LWPR_VRSS_PRPR=" + LWPR_VRSS_PRPR +
                ", BSOP_DATE='" + BSOP_DATE + '\'' +
                ", NEW_MKOP_CLS_CODE='" + NEW_MKOP_CLS_CODE + '\'' +
                ", TRHT_YN='" + TRHT_YN + '\'' +
                ", ASKP_RSQN1=" + ASKP_RSQN1 +
                ", BIDP_RSQN1=" + BIDP_RSQN1 +
                ", TOTAL_ASKP_RSQN=" + TOTAL_ASKP_RSQN +
                ", TOTAL_BIDP_RSQN=" + TOTAL_BIDP_RSQN +
                ", VOL_TNRT=" + VOL_TNRT +
                ", PRDY_SMNS_HOUR_ACML_VOL=" + PRDY_SMNS_HOUR_ACML_VOL +
                ", PRDY_SMNS_HOUR_ACML_VOL_RATE=" + PRDY_SMNS_HOUR_ACML_VOL_RATE +
                ", HOUR_CLS_CODE='" + HOUR_CLS_CODE + '\'' +
                ", MRKT_TRTM_CLS_CODE='" + MRKT_TRTM_CLS_CODE + '\'' +
                ", VI_STND_PRC=" + VI_STND_PRC +
                '}';
    }
}