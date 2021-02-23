package com.compy.check.bean;

import java.util.List;

public class WalletListBean {
    /**
     * head : {"code":1,"count":18,"message":"Success"}
     * body : {"data":[{"id":56,"walletId":14,"relatedId":52,"type":3,"side":"+","amount":458.19,"summary":"在 52 订单上获得佣金"},{"id":55,"walletId":14,"relatedId":51,"type":3,"side":"+","amount":280.65,"summary":"在 51 订单上获得佣金"},{"id":54,"walletId":14,"relatedId":50,"type":3,"side":"+","amount":119.82,"summary":"在 50 订单上获得佣金"},{"id":53,"walletId":14,"relatedId":49,"type":3,"side":"+","amount":116.82,"summary":"在 49 订单上获得佣金"},{"id":52,"walletId":14,"relatedId":48,"type":4,"side":"+","amount":405,"summary":"在 48 订单上获得分账"},{"id":50,"walletId":14,"relatedId":47,"type":3,"side":"+","amount":37.67,"summary":"在 47 订单上获得佣金"},{"id":49,"walletId":14,"relatedId":46,"type":4,"side":"+","amount":418.08,"summary":"在 46 订单上获得分账"},{"id":47,"walletId":14,"relatedId":45,"type":4,"side":"+","amount":304.37,"summary":"在 45 订单上获得分账"},{"id":45,"walletId":14,"relatedId":44,"type":4,"side":"+","amount":389.9,"summary":"在 44 订单上获得分账"},{"id":43,"walletId":14,"relatedId":43,"type":4,"side":"+","amount":159,"summary":"在 43 订单上获得分账"},{"id":41,"walletId":14,"relatedId":42,"type":4,"side":"+","amount":44.55,"summary":"在 42 订单上获得分账"},{"id":39,"walletId":14,"relatedId":41,"type":4,"side":"+","amount":219.99,"summary":"在 41 订单上获得分账"},{"id":37,"walletId":14,"relatedId":40,"type":4,"side":"+","amount":107.01,"summary":"在 40 订单上获得分账"},{"id":35,"walletId":14,"relatedId":39,"type":4,"side":"+","amount":184.14,"summary":"在 39 订单上获得分账"},{"id":33,"walletId":14,"relatedId":38,"type":4,"side":"+","amount":108.81,"summary":"在 38 订单上获得分账"},{"id":31,"walletId":14,"relatedId":37,"type":4,"side":"+","amount":367.44,"summary":"在 37 订单上获得分账"},{"id":29,"walletId":14,"relatedId":36,"type":4,"side":"+","amount":261.27,"summary":"在 36 订单上获得分账"},{"id":27,"walletId":14,"relatedId":35,"type":4,"side":"+","amount":254.42,"summary":"在 35 订单上获得分账"}]}
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
         * count : 18
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
             * id : 56
             * walletId : 14
             * relatedId : 52
             * type : 3
             * side : +
             * amount : 458.19
             * summary : 在 52 订单上获得佣金
             */

            private int id;
            private int walletId;
            private int relatedId;
            private int type;
            private String side;
            private double amount;
            private String summary;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getWalletId() {
                return walletId;
            }

            public void setWalletId(int walletId) {
                this.walletId = walletId;
            }

            public int getRelatedId() {
                return relatedId;
            }

            public void setRelatedId(int relatedId) {
                this.relatedId = relatedId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getSide() {
                return side;
            }

            public void setSide(String side) {
                this.side = side;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }
        }
    }
}
