package com.origin.core.util;

/**
 * Created by lc on 2017/5/12.
 */
public class SimpleToken {

    private int id;
    private int authority;

    public SimpleToken() {
    }

    public SimpleToken(int id, int authority) {
        this.id = id;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
