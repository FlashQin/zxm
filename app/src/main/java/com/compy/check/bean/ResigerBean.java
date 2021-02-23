package com.compy.check.bean;

public class ResigerBean {


    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":{"id":4,"name":"al989l白","level":1,"avatar":"","summary":"","email":"","mobile":"528575885","address":"","shareCode":"DpPLy39","token":"80c78b5c62dafa3f6744c0d9b0b459b4bw"}}
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
         * data : {"id":4,"name":"al989l白","level":1,"avatar":"","summary":"","email":"","mobile":"528575885","address":"","shareCode":"DpPLy39","token":"80c78b5c62dafa3f6744c0d9b0b459b4bw"}
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
             * id : 4
             * name : al989l白
             * level : 1
             * avatar :
             * summary :
             * email :
             * mobile : 528575885
             * address :
             * shareCode : DpPLy39
             * token : 80c78b5c62dafa3f6744c0d9b0b459b4bw
             */

            private int id;
            private String name;
            private int level;
            private String avatar;
            private String summary;
            private String email;
            private String mobile;
            private String address;
            private String shareCode;
            private String token;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getShareCode() {
                return shareCode;
            }

            public void setShareCode(String shareCode) {
                this.shareCode = shareCode;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }
    }
}
