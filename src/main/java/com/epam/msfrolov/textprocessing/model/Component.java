package com.epam.msfrolov.textprocessing.model;


import com.epam.msfrolov.textprocessing.util.Checker;

public abstract class Component {
    protected Type type;

    public Type getType() {
        Checker.isNull(type);
        return type;
    }

    protected void setType(Type type) {
        Checker.isNull(type);
        this.type = type;
    }

    public abstract StringBuilder toPlainString(StringBuilder sb);
}
