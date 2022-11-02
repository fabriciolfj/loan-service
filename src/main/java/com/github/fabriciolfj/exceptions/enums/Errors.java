package com.github.fabriciolfj.exceptions.enums;

import java.util.ResourceBundle;

public enum Errors {

    ERROR_01;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("message/exceptions");
        return bundle.getString(this.name() + ".message");
    }
}
