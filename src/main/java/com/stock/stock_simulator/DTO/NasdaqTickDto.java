package com.stock.stock_simulator.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "API 응답 DTO")
public class NasdaqTickDto {

    @Schema(description = "성공 실패 여부 (0: 성공, 0 이외 값: 실패)", example = "0", required = true)
    private String rt_cd;

    @Schema(description = "응답 코드", example = "0000", required = true)
    private String msg_cd;

    @Schema(description = "응답 메시지", example = "정상 처리되었습니다.", required = true)
    private String msg1;

    @Schema(description = "응답 상세 데이터1", required = true)
    private List<Output1> Output1;

    @Schema(description = "응답 상세 데이터2", required = true)
    private List<Output2> Output2;

    // Output1 클래스
    @Schema(description = "응답 상세 데이터1")
    public static class Output1 {

        @Schema(description = "실시간 종목 코드", example = "AAPL", required = true)
        private String rsym;

        @Schema(description = "소수점 자리수", example = "2", required = true)
        private String zdiv;

        @Schema(description = "장 시작 전 시간", example = "090000", required = true)
        private String stim;

        @Schema(description = "장 종료 후 시간", example = "153000", required = true)
        private String etim;

        @Schema(description = "장 시작 한국 시간", example = "090000", required = true)
        private String sktm;

        @Schema(description = "장 종료 한국 시간", example = "153000", required = true)
        private String ektm;

        @Schema(description = "다음 가능 여부", example = "Y", required = true)
        private String next;

        @Schema(description = "추가 데이터 여부", example = "Y", required = true)
        private String more;

        @Schema(description = "레코드 개수", example = "10", required = true)
        private String nrec;

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

        public String getStim() {
            return stim;
        }

        public void setStim(String stim) {
            this.stim = stim;
        }

        public String getEtim() {
            return etim;
        }

        public void setEtim(String etim) {
            this.etim = etim;
        }

        public String getSktm() {
            return sktm;
        }

        public void setSktm(String sktm) {
            this.sktm = sktm;
        }

        public String getEktm() {
            return ektm;
        }

        public void setEktm(String ektm) {
            this.ektm = ektm;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public String getNrec() {
            return nrec;
        }

        public void setNrec(String nrec) {
            this.nrec = nrec;
        }

        @Override
        public String toString() {
            return "Output1{" +
                    "rsym='" + rsym + '\'' +
                    ", zdiv='" + zdiv + '\'' +
                    ", stim='" + stim + '\'' +
                    ", etim='" + etim + '\'' +
                    ", sktm='" + sktm + '\'' +
                    ", ektm='" + ektm + '\'' +
                    ", next='" + next + '\'' +
                    ", more='" + more + '\'' +
                    ", nrec='" + nrec + '\'' +
                    '}';
        }
    }

    // Output2 클래스
    @Schema(description = "응답 상세 데이터2")
    public static class Output2 {

        @Schema(description = "현재 영업일자", example = "20231128", required = true)
        private String tymd;

        @Schema(description = "현재 기준 일자", example = "1128", required = true)
        private String xymd;

        @Schema(description = "현재 기준 시간", example = "153000", required = true)
        private String xhms;

        @Schema(description = "한국 기준 일자", example = "1128", required = true)
        private String kymd;

        @Schema(description = "한국 기준 시간", example = "153000", required = true)
        private String khms;

        @Schema(description = "시가", example = "1200", required = true)
        private String open;

        @Schema(description = "고가", example = "1300", required = true)
        private String high;

        @Schema(description = "저가", example = "1100", required = true)
        private String low;

        @Schema(description = "종가", example = "1250", required = true)
        private String last;

        @Schema(description = "체결량", example = "10000", required = true)
        private String evol;

        @Schema(description = "체결대금", example = "1250000", required = true)
        private String eamt;

        public String getTymd() {
            return tymd;
        }

        public void setTymd(String tymd) {
            this.tymd = tymd;
        }

        public String getXymd() {
            return xymd;
        }

        public void setXymd(String xymd) {
            this.xymd = xymd;
        }

        public String getXhms() {
            return xhms;
        }

        public void setXhms(String xhms) {
            this.xhms = xhms;
        }

        public String getKymd() {
            return kymd;
        }

        public void setKymd(String kymd) {
            this.kymd = kymd;
        }

        public String getKhms() {
            return khms;
        }

        public void setKhms(String khms) {
            this.khms = khms;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getEvol() {
            return evol;
        }

        public void setEvol(String evol) {
            this.evol = evol;
        }

        public String getEamt() {
            return eamt;
        }

        public void setEamt(String eamt) {
            this.eamt = eamt;
        }

        @Override
        public String toString() {
            return "Output2{" +
                    "tymd='" + tymd + '\'' +
                    ", xymd='" + xymd + '\'' +
                    ", xhms='" + xhms + '\'' +
                    ", kymd='" + kymd + '\'' +
                    ", khms='" + khms + '\'' +
                    ", open='" + open + '\'' +
                    ", high='" + high + '\'' +
                    ", low='" + low + '\'' +
                    ", last='" + last + '\'' +
                    ", evol='" + evol + '\'' +
                    ", eamt='" + eamt + '\'' +
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

    public List<NasdaqTickDto.Output1> getOutput1() {
        return Output1;
    }

    public void setOutput1(List<NasdaqTickDto.Output1> output1) {
        Output1 = output1;
    }

    public List<NasdaqTickDto.Output2> getOutput2() {
        return Output2;
    }

    public void setOutput2(List<NasdaqTickDto.Output2> output2) {
        Output2 = output2;
    }

    @Override
    public String toString() {
        return "NasdaqTickDto{" +
                "rt_cd='" + rt_cd + '\'' +
                ", msg_cd='" + msg_cd + '\'' +
                ", msg1='" + msg1 + '\'' +
                ", Output1=" + Output1 +
                ", Output2=" + Output2 +
                '}';
    }
}