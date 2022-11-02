package com.github.fabriciolfj.configproperties;

import org.eclipse.microprofile.config.inject.ConfigProperties;

@ConfigProperties(prefix = "loan-support")
public class LoanSupportProperties {

    public Integer age;
    public Integer years;
}
