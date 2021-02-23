package com.compy.check.bean;

import java.util.List;

public class TixianRecoderBean {
    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":[{"id":1609196862612201,"walletId":14,"amount":2000,"receiptId":null,"isAudit":0,"requestParam":"{\n  \"account\": \"55546466464\",\n  \"accountIFSC\": \"070 Fidelity Bank\",\n  \"amount\": \"2000\",\n  \"appId\": \"30003\",\n  \"applyDate\": \"2021-01-16 21:12:01\",\n  \"channel\": \"911\",\n  \"clientIp\": \"117.174.26.60\",\n  \"clientSn\": \"8698134688244\",\n  \"name\": \"39397937\",\n  \"notifyUrl\": \"http://pay.zxm88.net/v1/WebPayToPay/payoutWithdrawCallback\",\n  \"outOrderNo\": \"1609196862612201\",\n  \"sign\": \"9DFAD504F2072A583355CA8F76491E45\",\n  \"userId\": \"14\"\n}","responseParam":null,"summary":"","status":0,"createdAt":{"date":{"year":2021,"month":1,"day":16},"time":{"hour":14,"minute":12,"second":2,"nano":0}},"updatedAt":null}]}
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
             * id : 1609196862612201
             * walletId : 14
             * amount : 2000.0
             * receiptId : null
             * isAudit : 0
             * requestParam : {
             "account": "55546466464",
             "accountIFSC": "070 Fidelity Bank",
             "amount": "2000",
             "appId": "30003",
             "applyDate": "2021-01-16 21:12:01",
             "channel": "911",
             "clientIp": "117.174.26.60",
             "clientSn": "8698134688244",
             "name": "39397937",
             "notifyUrl": "http://pay.zxm88.net/v1/WebPayToPay/payoutWithdrawCallback",
             "outOrderNo": "1609196862612201",
             "sign": "9DFAD504F2072A583355CA8F76491E45",
             "userId": "14"
             }
             * responseParam : null
             * summary :
             * status : 0
             * createdAt : {"date":{"year":2021,"month":1,"day":16},"time":{"hour":14,"minute":12,"second":2,"nano":0}}
             * updatedAt : null
             */

            private long id;
            private int walletId;
            private double amount;
            private Object receiptId;
            private int isAudit;
            private String requestParam;
            private Object responseParam;
            private String summary;
            private int status;
            private CreatedAtBean createdAt;
            private Object updatedAt;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getWalletId() {
                return walletId;
            }

            public void setWalletId(int walletId) {
                this.walletId = walletId;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public Object getReceiptId() {
                return receiptId;
            }

            public void setReceiptId(Object receiptId) {
                this.receiptId = receiptId;
            }

            public int getIsAudit() {
                return isAudit;
            }

            public void setIsAudit(int isAudit) {
                this.isAudit = isAudit;
            }

            public String getRequestParam() {
                return requestParam;
            }

            public void setRequestParam(String requestParam) {
                this.requestParam = requestParam;
            }

            public Object getResponseParam() {
                return responseParam;
            }

            public void setResponseParam(Object responseParam) {
                this.responseParam = responseParam;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public CreatedAtBean getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(CreatedAtBean createdAt) {
                this.createdAt = createdAt;
            }

            public Object getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(Object updatedAt) {
                this.updatedAt = updatedAt;
            }

            public static class CreatedAtBean {
                /**
                 * date : {"year":2021,"month":1,"day":16}
                 * time : {"hour":14,"minute":12,"second":2,"nano":0}
                 */

                private DateBean date;
                private TimeBean time;

                public DateBean getDate() {
                    return date;
                }

                public void setDate(DateBean date) {
                    this.date = date;
                }

                public TimeBean getTime() {
                    return time;
                }

                public void setTime(TimeBean time) {
                    this.time = time;
                }

                public static class DateBean {
                    /**
                     * year : 2021
                     * month : 1
                     * day : 16
                     */

                    private int year;
                    private int month;
                    private int day;

                    public int getYear() {
                        return year;
                    }

                    public void setYear(int year) {
                        this.year = year;
                    }

                    public int getMonth() {
                        return month;
                    }

                    public void setMonth(int month) {
                        this.month = month;
                    }

                    public int getDay() {
                        return day;
                    }

                    public void setDay(int day) {
                        this.day = day;
                    }
                }

                public static class TimeBean {
                    /**
                     * hour : 14
                     * minute : 12
                     * second : 2
                     * nano : 0
                     */

                    private int hour;
                    private int minute;
                    private int second;
                    private int nano;

                    public int getHour() {
                        return hour;
                    }

                    public void setHour(int hour) {
                        this.hour = hour;
                    }

                    public int getMinute() {
                        return minute;
                    }

                    public void setMinute(int minute) {
                        this.minute = minute;
                    }

                    public int getSecond() {
                        return second;
                    }

                    public void setSecond(int second) {
                        this.second = second;
                    }

                    public int getNano() {
                        return nano;
                    }

                    public void setNano(int nano) {
                        this.nano = nano;
                    }
                }
            }
        }
    }
}
