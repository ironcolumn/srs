package com.srs.specification;

public interface Specification<T> {

    /**
     * 实否满足规约
     * @param t 需要判断的对象
     * @return 判断结果
     */
    boolean isSatisfiedBy ( T t );

}
