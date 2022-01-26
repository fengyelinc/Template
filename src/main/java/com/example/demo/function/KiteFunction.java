package com.example.demo.function;
@FunctionalInterface
public interface KiteFunction <T,R,S>{

    /**
     *
     * @param t
     * @param s
     * @return R
     */
    public R run(T t,S s);
}
