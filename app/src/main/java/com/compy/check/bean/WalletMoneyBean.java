package com.compy.check.bean;

public class WalletMoneyBean {
    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":{"balance":0,"reward":500}}
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
         * data : {"balance":0,"reward":500}
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
             * balance : 0.0
             * reward : 500.0
             */

            private double balance;//可用余额
            private double reward;

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public double getReward() {
                return reward;
            }

            public void setReward(double reward) {
                this.reward = reward;
            }
        }
    }
}
