package com.holmes.callList.model;

import com.holmes.callList.util.PhoneType;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Phone {

    private long number;
    private PhoneType type;

    @Override
    public String toString() {
        return "Phone{" +
                "number=" + number +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return number == phone.number && type == phone.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type);
    }
}
