package com.origin.core.model;

/**
 * Created by lc on 2017/6/21.
 */
public class IdCardRecognitionResultModel {


    /**
     * status : 0
     * msg : ok
     * result : {"address":"四川省武胜县胜利镇龙头沟村5组43号","birth":"1995-05-06","name":"舒乙峰","number":"511622199505061910","nation":"汉","sex":"男","portrait":"","retain":""}
     */

    private String status;
    private String msg;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 四川省武胜县胜利镇龙头沟村5组43号
         * birth : 1995-05-06
         * name : 舒乙峰
         * number : 511622199505061910
         * nation : 汉
         * sex : 男
         * portrait :
         * retain :
         */

        private String address;
        private String birth;
        private String name;
        private String number;
        private String nation;
        private String sex;
        private String portrait;
        private String retain;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getRetain() {
            return retain;
        }

        public void setRetain(String retain) {
            this.retain = retain;
        }
    }
}
