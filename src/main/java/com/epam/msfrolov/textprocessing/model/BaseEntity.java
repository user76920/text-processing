package com.epam.msfrolov.textprocessing.model;

import static com.epam.msfrolov.textprocessing.util.Validator.validate;

public abstract class BaseEntity {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null) validate();
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) validate();
        if (this == o) return true;
        if (o.getClass() != getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
