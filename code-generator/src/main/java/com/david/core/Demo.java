package com.david.core;

/**
 * =================================
 * Created by David on 2019/4/28.
 * mail:    17610897521@163.com
 * 描述:
 */

public class Demo {

    public static void main(String[] args) {
        createCode("user_info_test");
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     *
     * @param tableNames 数据表名称...
     */
    public static void createCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     *
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName, String modelName) {
        MybatisGenerator.genModelAndMapper(tableName, modelName);
        FreemarkerTemplate.genController(tableName, modelName);
        FreemarkerTemplate.genService(tableName, modelName);
    }

}
