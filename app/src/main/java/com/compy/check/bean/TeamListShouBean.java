package com.compy.check.bean;

import java.util.List;

public class TeamListShouBean {
    /**
     * head : {"code":1,"count":2,"message":"Success"}
     * body : {"data":[{"tradeId":46,"memberId":14,"baseDate":"2021-01-16","type":0,"relatedId":0,"primaryShareIncome":418.08,"secondaryShareIncome":0,"recessiveShareIncome":0,"summary":"","version":0},{"tradeId":45,"memberId":14,"baseDate":"2021-01-16","type":0,"relatedId":0,"primaryShareIncome":304.37,"secondaryShareIncome":0,"recessiveShareIncome":0,"summary":"","version":0}]}
     */

    private HeadBean head;
    private BodyBean body;

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class HeadBean {
        /**
         * code : 1
         * count : 2
         * message : Success
         */

        private int code;
        private int count;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class BodyBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * tradeId : 46
             * memberId : 14
             * baseDate : 2021-01-16
             * type : 0
             * relatedId : 0
             * primaryShareIncome : 418.08
             * secondaryShareIncome : 0.0
             * recessiveShareIncome : 0.0
             * summary :
             * version : 0
             */

            private int tradeId;
            private int memberId;
            private String baseDate;
            private int type;
            private int relatedId;
            private double primaryShareIncome;
            private double secondaryShareIncome;
            private double recessiveShareIncome;
            private String summary;
            private int version;

            public int getTradeId() {
                return tradeId;
            }

            public void setTradeId(int tradeId) {
                this.tradeId = tradeId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getBaseDate() {
                return baseDate;
            }

            public void setBaseDate(String baseDate) {
                this.baseDate = baseDate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getRelatedId() {
                return relatedId;
            }

            public void setRelatedId(int relatedId) {
                this.relatedId = relatedId;
            }

            public double getPrimaryShareIncome() {
                return primaryShareIncome;
            }

            public void setPrimaryShareIncome(double primaryShareIncome) {
                this.primaryShareIncome = primaryShareIncome;
            }

            public double getSecondaryShareIncome() {
                return secondaryShareIncome;
            }

            public void setSecondaryShareIncome(double secondaryShareIncome) {
                this.secondaryShareIncome = secondaryShareIncome;
            }

            public double getRecessiveShareIncome() {
                return recessiveShareIncome;
            }

            public void setRecessiveShareIncome(double recessiveShareIncome) {
                this.recessiveShareIncome = recessiveShareIncome;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }
        }
    }
}
