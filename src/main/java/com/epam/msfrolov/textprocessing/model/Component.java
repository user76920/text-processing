package com.epam.msfrolov.textprocessing.model;


import com.epam.msfrolov.textprocessing.util.Handler;

public abstract class Component {
    protected Type type;

    public Type getType() {
        Handler.isNull(type);
        return type;
    }

    protected void setType(Type type) {
        Handler.isNull(type);
        this.type = type;
    }

    public abstract StringBuilder toPlainString(StringBuilder sb);
}
