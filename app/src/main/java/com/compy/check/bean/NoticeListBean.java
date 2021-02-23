package com.compy.check.bean;

import java.util.List;

public class NoticeListBean {

    /**
     * head : {"code":1,"count":3,"message":"Success"}
     * body : {"data":[{"name":"Leon Spark","amount":600050},{"name":"Cloud Afiick","amount":40000},{"name":"Leon Spark","amount":2000}]}
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
         * count : 3
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
             * name : Leon Spark
             * amount : 600050.0
             */

            private String name;
            private double amount;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }
        }
    }
}
