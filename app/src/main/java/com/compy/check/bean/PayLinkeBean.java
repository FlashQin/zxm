package com.compy.check.bean;

public class PayLinkeBean {
    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":{"amount":"500","appId":"30003","applyDate":"2021-01-14 10:57:20","channel":"911","clientIp":"118.114.237.77","clientSn":"123321","email":"124208627@qq.com","notifyUrl":"http://pay.zxm88.net/v1/WebPayToPay/rechargeCallback","outOrderNo":"1608994352812201","sign":"A402BE482B1349F0547A4BDE89A689AA","userId":"7"}}
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
         * data : {"amount":"500","appId":"30003","applyDate":"2021-01-14 10:57:20","channel":"911","clientIp":"118.114.237.77","clientSn":"123321","email":"124208627@qq.com","notifyUrl":"http://pay.zxm88.net/v1/WebPayToPay/rechargeCallback","outOrderNo":"1608994352812201","sign":"A402BE482B1349F0547A4BDE89A689AA","userId":"7"}
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
             * amount : 500
             * appId : 30003
             * applyDate : 2021-01-14 10:57:20
             * channel : 911
             * clientIp : 118.114.237.77
             * clientSn : 123321
             * email : 124208627@qq.com
             * notifyUrl : http://pay.zxm88.net/v1/WebPayToPay/rechargeCallback
             * outOrderNo : 1608994352812201
             * sign : A402BE482B1349F0547A4BDE89A689AA
             * userId : 7
             */

            private String amount;
            private String appId;
            private String applyDate;
            private String channel;
            private String clientIp;
            private String clientSn;
            private String email;
            private String notifyUrl;
            private String outOrderNo;
            private String sign;
            private String userId;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAppId() {
                return appId;
            }

            public void setAppId(String appId) {
                this.appId = appId;
            }

            public String getApplyDate() {
                return applyDate;
            }

            public void setApplyDate(String applyDate) {
                this.applyDate = applyDate;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getClientIp() {
                return clientIp;
            }

            public void setClientIp(String clientIp) {
                this.clientIp = clientIp;
            }

            public String getClientSn() {
                return clientSn;
            }

            public void setClientSn(String clientSn) {
                this.clientSn = clientSn;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getNotifyUrl() {
                return notifyUrl;
            }

            public void setNotifyUrl(String notifyUrl) {
                this.notifyUrl = notifyUrl;
            }

            public String getOutOrderNo() {
                return outOrderNo;
            }

            public void setOutOrderNo(String outOrderNo) {
                this.outOrderNo = outOrderNo;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
