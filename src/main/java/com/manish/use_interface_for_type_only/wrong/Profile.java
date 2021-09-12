package com.manish.use_interface_for_type_only.wrong;

/*
*   Wrong way of using interface..!
*   Interface should only be used as type not for constants
* */
public class Profile implements Status {
    private final String name;
    private final String status;


    public Profile(String name) {
        this.name = name;
        this.status = ACTIVE;
    }
}
