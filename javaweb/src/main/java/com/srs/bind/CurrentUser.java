package com.srs.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target ( ElementType.PARAMETER )          // 可用在方法的参数上
@Retention ( RetentionPolicy.RUNTIME )     // 运行时有效
public @interface CurrentUser {

}
