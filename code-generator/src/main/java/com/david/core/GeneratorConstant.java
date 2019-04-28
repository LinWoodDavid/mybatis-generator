package com.david.core;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * =================================
 * Created by David on 2019/4/28.
 * mail:    17610897521@163.com
 * 描述:
 */

public enum GeneratorConstant {

    /**
     * 数据库连接
     */
    JDBC_URL("jdbc:mysql://localhost:3306/数据库?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false"),

    /**
     * 数据库用户名
     */
    JDBC_USERNAME("root"),

    /**
     * 数据库密码
     */
    JDBC_PASSWORD("密码"),

    /**
     * JDBC驱动
     */
    JDBC_DIVER_CLASS_NAME("com.mysql.jdbc.Driver"),

    /**
     * 项目路径
     */
    PROJECT_PATH(System.getProperty("user.dir") + "\\code-generator"),

    /**
     * 用于生成controller,service的模板路径
     */
    TEMPLATE_FILE_PATH(PROJECT_PATH.getValue() + "/src/main/resources/generator/template-single-project"),

    /**
     * java文件路径
     */
    JAVA_PATH("/src/main/java"),

    /**
     * 资源文件路径
     */
    RESOURCES_PATH("/src/main/resources"),

    /**
     * 生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）
     */
    BASE_PACKAGE("com.david"),

    /**
     * 生成的Model所在包
     */
    MODEL_PACKAGE(BASE_PACKAGE.value + ".model"),

    /**
     * 生成的Mapper所在包
     */
    MAPPER_PACKAGE(BASE_PACKAGE.value + ".mapper"),

    /**
     * 生成的Service所在包
     */
    SERVICE_PACKAGE(BASE_PACKAGE.value + ".service"),

    /**
     * 生成的ServiceImpl所在包
     */
    SERVICE_IMPL_PACKAGE(SERVICE_PACKAGE.value + ".impl"),

    /**
     * 生成的Controller所在包
     */
    CONTROLLER_PACKAGE(BASE_PACKAGE.value + ".web"),

    /**
     * 生成的Service存放路径
     */
    PACKAGE_PATH_SERVICE(packageConvertPath(SERVICE_PACKAGE.value)),

    /**
     * 生成的Service实现类存放路径
     */
    PACKAGE_PATH_SERVICE_IMPL(packageConvertPath(SERVICE_IMPL_PACKAGE.value)),

    /**
     * 生成的Controller存放路径
     */
    PACKAGE_PATH_CONTROLLER(packageConvertPath(CONTROLLER_PACKAGE.value)),

    AUTHOR("David"),

    /**
     * Mapper插件基础接口的完全限定名
     */
    MAPPER_INTERFACE_REFERENCE(BASE_PACKAGE.value + ".core.Mapper"),

    DATE(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    private final String value;

    GeneratorConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
