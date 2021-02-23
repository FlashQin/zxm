package com.compy.check.bean;

import java.util.List;

public class IFSListBean {

    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":[{"code":"070","name":"Fidelity Bank"},{"code":"082","name":"Keystone Bank"},{"code":"050","name":"Ecobank Nigeria"},{"code":"084","name":"Enterprise Bank"},{"code":"063","name":"Diamond Bank"},{"code":"030","name":"Heritage Bank"},{"code":"076","name":"Skye Bank"},{"code":"032","name":"Union Bank of Nigeria"},{"code":"044","name":"Access Bank"},{"code":"011","name":"First Bank of Nigeria"},{"code":"033","name":"United Bank For Africa"},{"code":"023","name":"Citibank Nigeria"},{"code":"221","name":"Stanbic IBTC Bank"},{"code":"232","name":"Sterling Bank"},{"code":"068","name":"Standard Chartered Bank"},{"code":"035","name":"Wema Bank"},{"code":"057","name":"Zenith Bank"},{"code":"058","name":"Guaranty Trust Bank"},{"code":"014","name":"MainStreet Bank"},{"code":"214","name":"First City Monument Bank"},{"code":"215","name":"Unity Bank"}]}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * code : 070
             * name : Fidelity Bank
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
