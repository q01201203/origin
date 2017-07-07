package com.origin.core.model;

/**
 * Created by lc on 2017/6/30.
 */
public class IdCardRecognizeResultModel {


    /**
     * global_request_id : AAAAAFiOWYQjUwUATmkAAAAAAABKlQEA
     * idcard_ocr_result : {"address":"四川省瓮柱彝鼓杓淼芰头一付科队3H","birthday":"1995.05.06","citizen_id":"511622199505061910","gender":"男","idcard_type":1,"name":"舒乙锌","nation":"汉"}
     * message : OK
     * rtn : 0
     */

    private String global_request_id;
    private IdcardOcrResultBean idcard_ocr_result;
    private String message;
    private int rtn;

    public String getGlobal_request_id() {
        return global_request_id;
    }

    public void setGlobal_request_id(String global_request_id) {
        this.global_request_id = global_request_id;
    }

    public IdcardOcrResultBean getIdcard_ocr_result() {
        return idcard_ocr_result;
    }

    public void setIdcard_ocr_result(IdcardOcrResultBean idcard_ocr_result) {
        this.idcard_ocr_result = idcard_ocr_result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRtn() {
        return rtn;
    }

    public void setRtn(int rtn) {
        this.rtn = rtn;
    }

    public static class IdcardOcrResultBean {
        /**
         * address : 四川省瓮柱彝鼓杓淼芰头一付科队3H
         * birthday : 1995.05.06
         * citizen_id : 511622199505061910
         * gender : 男
         * idcard_type : 1
         * name : 舒乙锌
         * nation : 汉
         */

        private String address;
        private String birthday;
        private String citizen_id;
        private String gender;
        private int idcard_type;
        private String name;
        private String nation;

        private String agency;
        private String valid_date_begin;
        private String valid_date_end;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCitizen_id() {
            return citizen_id;
        }

        public void setCitizen_id(String citizen_id) {
            this.citizen_id = citizen_id;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getIdcard_type() {
            return idcard_type;
        }

        public void setIdcard_type(int idcard_type) {
            this.idcard_type = idcard_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getAgency() {
            return agency;
        }

        public void setAgency(String agency) {
            this.agency = agency;
        }

        public String getValid_date_begin() {
            return valid_date_begin;
        }

        public void setValid_date_begin(String valid_date_begin) {
            this.valid_date_begin = valid_date_begin;
        }

        public String getValid_date_end() {
            return valid_date_end;
        }

        public void setValid_date_end(String valid_date_end) {
            this.valid_date_end = valid_date_end;
        }
    }
}
