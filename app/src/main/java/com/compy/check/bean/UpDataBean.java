package com.compy.check.bean;

public class UpDataBean {
    /**
     * head : {"code":1,"count":1,"message":"Success"}
     * body : {"data":{"status":0,"version":1,"enforce":false,"content":"No Special Action","download":"https://www.zxm88.net/download/zxmshop.apk"}}
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
         * data : {"status":0,"version":1,"enforce":false,"content":"No Special Action","download":"https://www.zxm88.net/download/zxmshop.apk"}
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
             * status : 0
             * version : 1.0
             * enforce : false
             * content : No Special Action
             * download : https://www.zxm88.net/download/zxmshop.apk
             */

            private int status;
            private String version;
            private boolean enforce;
            private String content;
            private String download;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public boolean isEnforce() {
                return enforce;
            }

            public void setEnforce(boolean enforce) {
                this.enforce = enforce;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDownload() {
                return download;
            }

            public void setDownload(String download) {
                this.download = download;
            }
        }
    }
}
