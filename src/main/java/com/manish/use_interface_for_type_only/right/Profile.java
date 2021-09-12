package com.manish.use_interface_for_type_only.right;

import static com.manish.use_interface_for_type_only.right.Status.ACTIVE;

public class Profile {
    private final String name;
    private final String status;

    public Profile(String name) {
        this.name = name;
        this.status = ACTIVE;
    }
}
