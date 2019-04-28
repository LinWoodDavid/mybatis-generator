package com.david.core;

import com.google.common.base.CaseFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * =================================
 * Created by David on 2019/4/28.
 * mail:    17610897521@163.com
 * 描述:      使用Freemarker 模板创建controller,service,impl
 */

public class FreemarkerTemplate {

    public static void genController(String tableName, String modelName) {
        try {
            Configuration cfg = getConfiguration();
            Map<String, Object> data = new HashMap<>();
            data.put("date", GeneratorConstant.DATE.getValue());
            data.put("author", GeneratorConstant.AUTHOR.getValue());
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            //data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("baseRequestMapping", "user");
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", GeneratorConstant.BASE_PACKAGE.getValue());

            File file = new File(GeneratorConstant.PROJECT_PATH.getValue() + GeneratorConstant.JAVA_PATH.getValue() + GeneratorConstant.PACKAGE_PATH_CONTROLLER.getValue() + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }
    }

    public static void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();
            Map<String, Object> data = new HashMap<>();
            data.put("date", GeneratorConstant.DATE.getValue());
            data.put("author", GeneratorConstant.AUTHOR.getValue());
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", GeneratorConstant.BASE_PACKAGE.getValue());

            File file = new File(GeneratorConstant.PROJECT_PATH.getValue() + GeneratorConstant.JAVA_PATH.getValue() + GeneratorConstant.PACKAGE_PATH_SERVICE.getValue() + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(GeneratorConstant.PROJECT_PATH.getValue() + GeneratorConstant.JAVA_PATH.getValue() + GeneratorConstant.PACKAGE_PATH_SERVICE_IMPL.getValue() + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(GeneratorConstant.TEMPLATE_FILE_PATH.getValue()));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }
}
