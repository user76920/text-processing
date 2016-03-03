package com.epam.msfrolov.textprocessing.model;

public abstract class Component {
    protected abstract StringBuilder toPlainString(StringBuilder sb);
    public abstract String toPlainString();
}
