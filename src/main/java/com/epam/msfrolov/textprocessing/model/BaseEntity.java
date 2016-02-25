package com.epam.msfrolov.textprocessing.model;

public abstract class BaseEntity {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        isNull(id);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        isNull(o);
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

    public static boolean isNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
            //return false;
        }
        return true;
    }
}
