package com.stock.stock_simulator.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "API 응답 DTO")
public class KoreaTickDto {

    @Schema(description = "성공 실패 여부 (0: 성공, 0 이외 값: 실패)", example = "0", required = true)
    private String rt_cd;

    @Schema(description = "응답 코드", example = "0000", required = true)
    private String msg_cd;

    @Schema(description = "응답 메시지", example = "정상 처리되었습니다.", required = true)
    private String msg1;

    @Schema(description = "응답 상세 데이터1", required = false)
    private Output1 output1;

    @Schema(description = "응답 상세 데이터2", required = true)
    private List<Output2> output2;

    @Schema(description = "응답 상세 데이터1")
    public static class Output1 {

        @Schema(description = "전일 대비", example = "50", required = false)
        private String prdy_vrss;

        @Schema(description = "전일 대비 부호", example = "1", required = false)
        private String prdy_vrss_sign;

        @Schema(description = "전일 대비율", example = "1.23", required = false)
        private String prdy_ctrt;

        @Schema(description = "주식 전일 증가", example = "100", required = false)
        private String stck_prdy_clpr;

        @Schema(description = "누적 거래량", example = "50000", required = false)
        private String acml_vol;

        @Schema(description = "누적 거래 대금", example = "123456789", required = false)
        private String acml_tr_pbmn;

        @Schema(description = "HTS 한국 종목명", example = "삼성전자", required = false)
        private String hts_kor_isnm;

        @Schema(description = "주식 현재가", example = "60000", required = false)
        private String stck_prpr;

        public String getPrdy_vrss() {
            return prdy_vrss;
        }

        public void setPrdy_vrss(String prdy_vrss) {
            this.prdy_vrss = prdy_vrss;
        }

        public String getPrdy_vrss_sign() {
            return prdy_vrss_sign;
        }

        public void setPrdy_vrss_sign(String prdy_vrss_sign) {
            this.prdy_vrss_sign = prdy_vrss_sign;
        }

        public String getPrdy_ctrt() {
            return prdy_ctrt;
        }

        public void setPrdy_ctrt(String prdy_ctrt) {
            this.prdy_ctrt = prdy_ctrt;
        }

        public String getStck_prdy_clpr() {
            return stck_prdy_clpr;
        }

        public void setStck_prdy_clpr(String stck_prdy_clpr) {
            this.stck_prdy_clpr = stck_prdy_clpr;
        }

        public String getAcml_vol() {
            return acml_vol;
        }

        public void setAcml_vol(String acml_vol) {
            this.acml_vol = acml_vol;
        }

        public String getAcml_tr_pbmn() {
            return acml_tr_pbmn;
        }

        public void setAcml_tr_pbmn(String acml_tr_pbmn) {
            this.acml_tr_pbmn = acml_tr_pbmn;
        }

        public String getHts_kor_isnm() {
            return hts_kor_isnm;
        }

        public void setHts_kor_isnm(String hts_kor_isnm) {
            this.hts_kor_isnm = hts_kor_isnm;
        }

        public String getStck_prpr() {
            return stck_prpr;
        }

        public void setStck_prpr(String stck_prpr) {
            this.stck_prpr = stck_prpr;
        }

        @Override
        public String toString() {
            return "Output1{" +
                    "prdy_vrss='" + prdy_vrss + '\'' +
                    ", prdy_vrss_sign='" + prdy_vrss_sign + '\'' +
                    ", prdy_ctrt='" + prdy_ctrt + '\'' +
                    ", stck_prdy_clpr='" + stck_prdy_clpr + '\'' +
                    ", acml_vol='" + acml_vol + '\'' +
                    ", acml_tr_pbmn='" + acml_tr_pbmn + '\'' +
                    ", hts_kor_isnm='" + hts_kor_isnm + '\'' +
                    ", stck_prpr='" + stck_prpr + '\'' +
                    '}';
        }
    }

    @Schema(description = "응답 상세 데이터2")
    public static class Output2 {

        @Schema(description = "주식 영업 일자", example = "20231128", required = true)
        private String stck_bsop_date;

        @Schema(description = "주식 체결 시간", example = "153000", required = true)
        private String stck_cntg_hour;

        @Schema(description = "누적 거래 대금", example = "123456789", required = false)
        private String acml_tr_pbmn;

        @Schema(description = "주식 현재가", example = "60000", required = true)
        private String stck_prpr;

        @Schema(description = "주식 시가", example = "59000", required = false)
        private String stck_oprc;

        @Schema(description = "주식 최고가", example = "60500", required = false)
        private String stck_hgpr;

        @Schema(description = "주식 최저가", example = "58000", required = false)
        private String stck_lwpr;

        @Schema(description = "체결 거래량", example = "1000", required = false)
        private String cntg_vol;

        public String getStck_bsop_date() {
            return stck_bsop_date;
        }

        public void setStck_bsop_date(String stck_bsop_date) {
            this.stck_bsop_date = stck_bsop_date;
        }

        public String getStck_cntg_hour() {
            return stck_cntg_hour;
        }

        public void setStck_cntg_hour(String stck_cntg_hour) {
            this.stck_cntg_hour = stck_cntg_hour;
        }

        public String getAcml_tr_pbmn() {
            return acml_tr_pbmn;
        }

        public void setAcml_tr_pbmn(String acml_tr_pbmn) {
            this.acml_tr_pbmn = acml_tr_pbmn;
        }

        public String getStck_prpr() {
            return stck_prpr;
        }

        public void setStck_prpr(String stck_prpr) {
            this.stck_prpr = stck_prpr;
        }

        public String getStck_oprc() {
            return stck_oprc;
        }

        public void setStck_oprc(String stck_oprc) {
            this.stck_oprc = stck_oprc;
        }

        public String getStck_hgpr() {
            return stck_hgpr;
        }

        public void setStck_hgpr(String stck_hgpr) {
            this.stck_hgpr = stck_hgpr;
        }

        public String getStck_lwpr() {
            return stck_lwpr;
        }

        public void setStck_lwpr(String stck_lwpr) {
            this.stck_lwpr = stck_lwpr;
        }

        public String getCntg_vol() {
            return cntg_vol;
        }

        public void setCntg_vol(String cntg_vol) {
            this.cntg_vol = cntg_vol;
        }

        @Override
        public String toString() {
            return "Output2{" +
                    "stck_bsop_date='" + stck_bsop_date + '\'' +
                    ", stck_cntg_hour='" + stck_cntg_hour + '\'' +
                    ", acml_tr_pbmn='" + acml_tr_pbmn + '\'' +
                    ", stck_prpr='" + stck_prpr + '\'' +
                    ", stck_oprc='" + stck_oprc + '\'' +
                    ", stck_hgpr='" + stck_hgpr + '\'' +
                    ", stck_lwpr='" + stck_lwpr + '\'' +
                    ", cntg_vol='" + cntg_vol + '\'' +
                    '}';
        }
    }

    public String getRt_cd() {
        return rt_cd;
    }

    public void setRt_cd(String rt_cd) {
        this.rt_cd = rt_cd;
    }

    public String getMsg_cd() {
        return msg_cd;
    }

    public void setMsg_cd(String msg_cd) {
        this.msg_cd = msg_cd;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public Output1 getOutput1() {
        return output1;
    }

    public void setOutput1(Output1 output1) {
        this.output1 = output1;
    }

    public List<Output2> getOutput2() {
        return output2;
    }

    public void setOutput2(List<Output2> output2) {
        this.output2 = output2;
    }

    @Override
    public String toString() {
        return "KoreaTickDto{" +
                "rt_cd='" + rt_cd + '\'' +
                ", msg_cd='" + msg_cd + '\'' +
                ", msg1='" + msg1 + '\'' +
                ", output1=" + output1 +
                ", output2=" + output2 +
                '}';
    }
}