package com.alapshin.issue144513656;

public enum Membership {
    GUEST("Guest"),
    MEMBER("Member");

    public final String type;
    Membership(String type) {
        this.type = type;
    }
}
