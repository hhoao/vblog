package com.hhoa.blog.mgb;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * @author hhoa
 * @since 2022/9/3
 **/

public class TinyIntTypeResolver extends JavaTypeResolverDefaultImpl {
    /**
     * 将tinyint转换为Integer，这里是关键所在
     */
    public TinyIntTypeResolver() {
        super();
        super.typeMap.put(-6, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }

}
