package com.b3to.newcash.Models;

public class Base {


    public String user_token;
    public Integer sync_type;
    public Boolean sync_success;
    public Long sync_date;
    public Integer sync_term;
    public Long created_at;
    public Long updated_at;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public Integer getSync_type() {
        return sync_type;
    }

    public void setSync_type(Integer sync_type) {
        this.sync_type = sync_type;
    }

    public Boolean getSync_success() {
        return sync_success;
    }

    public void setSync_success(Boolean sync_success) {
        this.sync_success = sync_success;
    }

    public Long getSync_date() {
        return sync_date;
    }

    public void setSync_date(Long sync_date) {
        this.sync_date = sync_date;
    }

    public Integer getSync_term() {
        return sync_term;
    }

    public void setSync_term(Integer sync_term) {
        this.sync_term = sync_term;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Long updated_at) {
        this.updated_at = updated_at;
    }
}
