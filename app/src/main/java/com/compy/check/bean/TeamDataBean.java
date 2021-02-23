package com.compy.check.bean;

public class TeamDataBean {
    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":{"primaryMemberCount":0,"primaryShareAmount":0,"secondaryMemberCount":0,"secondaryShareAmount":0,"recessiveMemberCount":0,"recessiveShareAmount":0}}
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
         * count : 1
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
        /**
         * data : {"primaryMemberCount":0,"primaryShareAmount":0,"secondaryMemberCount":0,"secondaryShareAmount":0,"recessiveMemberCount":0,"recessiveShareAmount":0}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * primaryMemberCount : 0
             * primaryShareAmount : 0.0
             * secondaryMemberCount : 0
             * secondaryShareAmount : 0.0
             * recessiveMemberCount : 0
             * recessiveShareAmount : 0.0
             */

            private int primaryMemberCount;
            private double primaryShareAmount;
            private int secondaryMemberCount;
            private double secondaryShareAmount;
            private int recessiveMemberCount;
            private double recessiveShareAmount;

            public int getPrimaryMemberCount() {
                return primaryMemberCount;
            }

            public void setPrimaryMemberCount(int primaryMemberCount) {
                this.primaryMemberCount = primaryMemberCount;
            }

            public double getPrimaryShareAmount() {
                return primaryShareAmount;
            }

            public void setPrimaryShareAmount(double primaryShareAmount) {
                this.primaryShareAmount = primaryShareAmount;
            }

            public int getSecondaryMemberCount() {
                return secondaryMemberCount;
            }

            public void setSecondaryMemberCount(int secondaryMemberCount) {
                this.secondaryMemberCount = secondaryMemberCount;
            }

            public double getSecondaryShareAmount() {
                return secondaryShareAmount;
            }

            public void setSecondaryShareAmount(double secondaryShareAmount) {
                this.secondaryShareAmount = secondaryShareAmount;
            }

            public int getRecessiveMemberCount() {
                return recessiveMemberCount;
            }

            public void setRecessiveMemberCount(int recessiveMemberCount) {
                this.recessiveMemberCount = recessiveMemberCount;
            }

            public double getRecessiveShareAmount() {
                return recessiveShareAmount;
            }

            public void setRecessiveShareAmount(double recessiveShareAmount) {
                this.recessiveShareAmount = recessiveShareAmount;
            }
        }
    }
}
