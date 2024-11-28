package com.stock.stock_simulator.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "API 응답 모델")
public class NasdaqStockPriceDto {
    @Schema(description = "성공 실패 여부 (0: 성공, 0 이외의 값: 실패)", example = "0", required = true)
    private String rt_cd;

    @Schema(description = "응답 코드", example = "0000", required = true)
    private String msg_cd;

    @Schema(description = "응답 메시지", example = "정상 처리되었습니다.", required = true)
    private String msg1;

    @Schema(description = "응답 상세 데이터", required = true)
    private OutputDto output;

    // Getters and Setters
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

    public OutputDto getOutput() {
        return output;
    }

    public void setOutput(OutputDto output) {
        this.output = output;
    }

    @Schema(description = "응답 상세 데이터 모델")
    public static class OutputDto {

        @Schema(description = "실시간 조회 종목 코드 (D+시장구분+종목코드)", example = "DNAAPL", required = true)
        private String rsym;

        @Schema(description = "소수점 자리수", example = "1", required = true)
        private String zdiv;

        @Schema(description = "전일 증가", example = "1000", required = true)
        private String base;

        @Schema(description = "전일 거래량", example = "20000", required = true)
        private String pvol;

        @Schema(description = "현재가", example = "15000", required = true)
        private String last;

        @Schema(description = "대비 기호 (1: 상한, 2: 상승, 3: 보합, 4: 하락, 5: 하한)", example = "2", required = true)
        private String sign;

        @Schema(description = "대비 (당일 현재가 - 전일 증가)", example = "500", required = true)
        private String diff;

        @Schema(description = "등락률 (전일 대비 / 당일 현재가 * 100)", example = "3.3", required = true)
        private String rate;

        @Schema(description = "거래량", example = "30000", required = true)
        private String tvol;

        @Schema(description = "거래 대금", example = "45000000", required = true)
        private String tamt;

        @Schema(description = "매수 가능 여부", example = "Y", required = true)
        private String ordy;

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

        public String getOrdy() {
            return ordy;
        }

        public void setOrdy(String ordy) {
            this.ordy = ordy;
        }

        @Override
        public String toString() {
            return "OutputDto{" +
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
                    ", ordy='" + ordy + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NasdaqStockPriceDto{" +
                "rt_cd='" + rt_cd + '\'' +
                ", msg_cd='" + msg_cd + '\'' +
                ", msg1='" + msg1 + '\'' +
                ", output=" + output +
                '}';
    }
}