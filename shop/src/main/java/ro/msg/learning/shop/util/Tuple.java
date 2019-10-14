package ro.msg.learning.shop.util;

import lombok.Data;

@Data
public class Tuple<X, Y> {
    private final X x;
    private final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}
