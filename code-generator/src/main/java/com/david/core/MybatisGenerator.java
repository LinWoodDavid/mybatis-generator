package com.david.core;

import com.google.common.base.CaseFormat;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * =================================
 * Created by David on 2019/4/28.
 * mail:    17610897521@163.com
 * 描述:
 */

public class MybatisGenerator {

    public static void main(String[] args) {
        genModelAndMapper("user_info_test", "UserInfoTest");
    }

    public static void genModelAndMapper(String tableName, String modelName) {
        /**
         * context:生成一组对象的环境
         * id:必选，上下文id，用于在生成错误时提示
         * defaultModelType:指定生成对象的样式
         * 1，conditional：类似hierarchical；
         * 2，flat：所有内容（主键，blob）等全部生成在一个对象中；
         * 3，hierarchical：主键生成一个XXKey对象(key class)，Blob等单独生成一个对象，其他简单属性在一个对象中(record class)
         * targetRuntime:
         * 1，MyBatis3：默认的值，生成基于MyBatis3.x以上版本的内容，包括XXXBySample；
         * 2，MyBatis3Simple：类似MyBatis3，只是不生成XXXBySample；
         * introspectedColumnImpl：类全限定名，用于扩展MBG
         */
        Context context = new Context(ModelType.FLAT);
        context.setId("mysql");//id:必选，上下文id，用于在生成错误时提示
        context.setTargetRuntime("MyBatis3Simple");
        /**
         * 自动识别数据库关键字，默认false，如果设置为true，根据SqlReservedWords中定义的关键字列表；
         * 一般保留默认值，遇到数据库关键字（Java关键字），使用columnOverride覆盖
         */
        context.addProperty(PropertyRegistry.CONTEXT_AUTO_DELIMIT_KEYWORDS, "false");
        /**
         * 生成的Java文件的编码
         */
        context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, "UTF-8");
        /**
         * 格式化java代码
         */
        context.addProperty(PropertyRegistry.CONTEXT_JAVA_FORMATTER, "org.mybatis.generator.api.dom.DefaultJavaFormatter");
        /**
         * 格式化XML代码
         */
        context.addProperty(PropertyRegistry.CONTEXT_XML_FORMATTER, "org.mybatis.generator.api.dom.DefaultXmlFormatter");
        /**
         * beginningDelimiter和endingDelimiter：指明数据库的用于标记数据库对象名的符号，
         * 比如ORACLE就是双引号，MYSQL默认是`反引号；
         */
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
        //JDBC
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration());
        //java类型处理器
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration());
        //java模型创建器(是必须要的元素)
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration());
        //生成SQL map的XML文件生成器，
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration());
        //生成Mapper接口
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration());
        /**
         * 选择一个table来生成相关文件，可以有一个或多个table，必须要有table元素
         * 选择的table会生成一下文件：
         * 1，SQL map文件
         * 2，生成一个主键类；
         * 3，除了BLOB和主键的其他字段的类；
         * 4，包含BLOB的类；
         * 5，一个用户生成动态查询的条件类（selectByExample, deleteByExample），可选；
         * 6，Mapper接口（可选）
         * tableName（必要）：要生成对象的表名；
         * 注意：大小写敏感问题。正常情况下，MBG会自动的去识别数据库标识符的大小写敏感度，在一般情况下，MBG会
         * 根据设置的schema，catalog或tablename去查询数据表，按照下面的流程：
         * 1，如果schema，catalog或tablename中有空格，那么设置的是什么格式，就精确的使用指定的大小写格式去查询；
         * 2，否则，如果数据库的标识符使用大写的，那么MBG自动把表名变成大写再查找；
         * 3，否则，如果数据库的标识符使用小写的，那么MBG自动把表名变成小写再查找；
         * 4，否则，使用指定的大小写格式查询；
         * 另外的，如果在创建表的时候，使用的""把数据库对象规定大小写，就算数据库标识符是使用的大写，在这种情况下也会使用给定的大小写来创建表名；
         * 这个时候，请设置delimitIdentifiers="true"即可保留大小写格式；
         * 可选：
         * 1，schema：数据库的schema；
         * 2，catalog：数据库的catalog；
         * 3，alias：为数据表设置的别名，如果设置了alias，那么生成的所有的SELECT SQL语句中，列名会变成：alias_actualColumnName
         * 4，domainObjectName：生成的domain类的名字，如果不设置，直接使用表名作为domain类的名字；可以设置为somepck.domainName，那么会自动把domainName类再放到somepck包里面；
         * 5，enableInsert（默认true）：指定是否生成insert语句；
         * 6，enableSelectByPrimaryKey（默认true）：指定是否生成按照主键查询对象的语句（就是getById或get）；
         * 7，enableSelectByExample（默认true）：MyBatis3Simple为false，指定是否生成动态查询语句；
         * 8，enableUpdateByPrimaryKey（默认true）：指定是否生成按照主键修改对象的语句（即update)；
         * 9，enableDeleteByPrimaryKey（默认true）：指定是否生成按照主键删除对象的语句（即delete）；
         * 10，enableDeleteByExample（默认true）：MyBatis3Simple为false，指定是否生成动态删除语句；
         * 11，enableCountByExample（默认true）：MyBatis3Simple为false，指定是否生成动态查询总条数语句（用于分页的总条数查询）；
         * 12，enableUpdateByExample（默认true）：MyBatis3Simple为false，指定是否生成动态修改语句（只修改对象中不为空的属性）；
         * 13，modelType：参考context元素的defaultModelType，相当于覆盖；
         * 14，delimitIdentifiers：参考tableName的解释，注意，默认的delimitIdentifiers是双引号，如果类似MYSQL这样的数据库，使用的是`（反引号，那么还需要设置context的beginningDelimiter和endingDelimiter属性）
         * 15，delimitAllColumns：设置是否所有生成的SQL中的列名都使用标识符引起来。默认为false，delimitIdentifiers参考context的属性
         * 注意，table里面很多参数都是对javaModelGenerator，context等元素的默认属性的一个复写；
         */
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        /**
         * 表名
         */
        tableConfiguration.setTableName(tableName);
        /**
         * 类名
         */
        if (!StringUtils.isEmpty(modelName)) tableConfiguration.setDomainObjectName(modelName);
        /**
         * generatedKey用于生成生成主键的方法，
         * 如果设置了该元素，MBG会在生成的<insert>元素中生成一条正确的<selectKey>元素，该元素可选
         * column:主键的列名；
         * sqlStatement：要生成的selectKey语句，有以下可选项：
         * Cloudscape:相当于selectKey的SQL为： VALUES IDENTITY_VAL_LOCAL()
         * DB2       :相当于selectKey的SQL为： VALUES IDENTITY_VAL_LOCAL()
         * DB2_MF    :相当于selectKey的SQL为：SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1
         * Derby      :相当于selectKey的SQL为：VALUES IDENTITY_VAL_LOCAL()
         * HSQLDB      :相当于selectKey的SQL为：CALL IDENTITY()
         * Informix  :相当于selectKey的SQL为：select dbinfo('sqlca.sqlerrd1') from systables where tabid=1
         * MySql      :相当于selectKey的SQL为：SELECT LAST_INSERT_ID()
         * SqlServer :相当于selectKey的SQL为：SELECT SCOPE_IDENTITY()
         * SYBASE      :相当于selectKey的SQL为：SELECT @@IDENTITY
         * JDBC      :相当于在生成的insert元素上添加useGeneratedKeys="true"和keyProperty属性
         */
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
        context.addTableConfiguration(tableConfiguration);
        List<String> warnings;
        //tk插件
        context.addPluginConfiguration(pluginConfiguration());
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();
            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)) {
            modelName = tableNameConvertUpperCamel(tableName);
        }
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    private static PluginConfiguration pluginConfiguration() {
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        //pluginConfiguration.setConfigurationType("com.itfsw.mybatis.generator.plugins.SelectOneByExamplePlugin");
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", GeneratorConstant.MAPPER_INTERFACE_REFERENCE.getValue());
        return pluginConfiguration;
    }

    /**
     * 对于mybatis来说，即生成Mapper接口，注意，如果没有配置该元素，那么默认不会生成Mapper接口
     * targetPackage/targetProject:同javaModelGenerator
     * type：选择怎么生成mapper接口（在MyBatis3/MyBatis3Simple下）：
     * 1，ANNOTATEDMAPPER：会生成使用Mapper接口+Annotation的方式创建（SQL生成在annotation中），不会生成对应的XML；
     * 2，MIXEDMAPPER：使用混合配置，会生成Mapper接口，并适当添加合适的Annotation，但是XML会生成在XML中；
     * 3，XMLMAPPER：会生成Mapper接口，接口完全依赖XML；
     * 注意，如果context是MyBatis3Simple：只支持ANNOTATEDMAPPER和XMLMAPPER
     */
    private static JavaClientGeneratorConfiguration javaClientGeneratorConfiguration() {
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(GeneratorConstant.PROJECT_PATH.getValue() + GeneratorConstant.JAVA_PATH.getValue());
        javaClientGeneratorConfiguration.setTargetPackage(GeneratorConstant.MAPPER_PACKAGE.getValue());
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        return javaClientGeneratorConfiguration;
    }

    /**
     * 生成SQL map的XML文件生成器，
     * 注意，在Mybatis3之后，我们可以使用mapper.xml文件+Mapper接口（或者不用mapper接口），
     * 或者只使用Mapper接口+Annotation，所以，如果 javaClientGenerator配置中配置了需要生成XML的话，这个元素就必须配置
     * targetPackage/targetProject:同javaModelGenerator
     */
    private static SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration() {
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(GeneratorConstant.PROJECT_PATH.getValue() + GeneratorConstant.RESOURCES_PATH.getValue());
        sqlMapGeneratorConfiguration.addProperty(PropertyRegistry.ANY_ENABLE_SUB_PACKAGES, "true");
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        return sqlMapGeneratorConfiguration;
    }

    /**
     * java模型创建器
     * 负责：
     * 1，key类（见context的defaultModelType）；
     * 2，java类；
     * 3，查询类
     * targetPackage：生成的类要放的包，真实的包受enableSubPackages属性控制；
     * targetProject：目标项目，指定一个存在的目录下，生成的内容会放到指定目录中，如果目录不存在，MBG不会自动建目录
     */
    private static JavaModelGeneratorConfiguration javaModelGeneratorConfiguration() {
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        /**
         * for MyBatis3/MyBatis3Simple
         * 自动为每一个生成的类创建一个构造方法，构造方法包含了所有的field；而不是使用setter；
         */
        javaModelGeneratorConfiguration.addProperty(PropertyRegistry.ANY_CONSTRUCTOR_BASED, "false");
        /**
         * 在targetPackage的基础上，根据数据库的schema再生成一层package，最终生成的类放在这个package下，默认为false
         */
        javaModelGeneratorConfiguration.addProperty(PropertyRegistry.ANY_ENABLE_SUB_PACKAGES, "true");
        /**
         * for MyBatis3 / MyBatis3Simple
         * 是否创建一个不可变的类，如果为true，
         * 那么MBG会创建一个没有setter方法的类，取而代之的是类似constructorBased的类
         */
        javaModelGeneratorConfiguration.addProperty(PropertyRegistry.ANY_IMMUTABLE, "false");
        /**
         * 设置一个根对象，
         * 如果设置了这个根对象，那么生成的keyClass或者recordClass会继承这个类；在Table的rootClass属性中可以覆盖该选项
         * 注意：如果在key class或者record class中有root class相同的属性，MBG就不会重新生成这些属性了，包括：
         * 1，属性名相同，类型相同，有相同的getter/setter方法；
         */
        //javaModelGeneratorConfiguration.addProperty(PropertyRegistry.ANY_ROOT_CLASS, "com.david.model.BaseModel");
        /**
         * 设置是否在getter方法中，对String类型字段调用trim()方法
         */
        javaModelGeneratorConfiguration.addProperty(PropertyRegistry.MODEL_GENERATOR_TRIM_STRINGS, "false");
        javaModelGeneratorConfiguration.setTargetProject(GeneratorConstant.PROJECT_PATH.getValue() + GeneratorConstant.JAVA_PATH.getValue());
        javaModelGeneratorConfiguration.setTargetPackage(GeneratorConstant.MODEL_PACKAGE.getValue());
        return javaModelGeneratorConfiguration;
    }

    /**
     * java类型处理器
     * 用于处理DB中的类型到Java中的类型，默认使用JavaTypeResolverDefaultImpl；
     * 注意一点，默认会先尝试使用Integer，Long，Short等来对应DECIMAL和 NUMERIC数据类型；
     */
    private static JavaTypeResolverConfiguration javaTypeResolverConfiguration() {
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        /**
         * true：使用BigDecimal对应DECIMAL和 NUMERIC数据类型
         * false：默认,
         * scale>0;length>18：使用BigDecimal;
         * scale=0;length[10,18]：使用Long;
         * scale=0;length[5,9]：使用Integer;
         * scale=0;length<5：使用Short;
         */
        javaTypeResolverConfiguration.addProperty(PropertyRegistry.TYPE_RESOLVER_FORCE_BIG_DECIMALS, "false");
        return javaTypeResolverConfiguration;
    }

    /**
     * JDBC 配置
     *
     * @return
     */
    private static JDBCConnectionConfiguration jdbcConnectionConfiguration() {
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        /**
         * 连接url
         */
        jdbcConnectionConfiguration.setConnectionURL(GeneratorConstant.JDBC_URL.getValue());
        //用户名
        jdbcConnectionConfiguration.setUserId(GeneratorConstant.JDBC_USERNAME.getValue());
        //密码
        jdbcConnectionConfiguration.setPassword(GeneratorConstant.JDBC_PASSWORD.getValue());
        //驱动
        jdbcConnectionConfiguration.setDriverClass(GeneratorConstant.JDBC_DIVER_CLASS_NAME.getValue());
        return jdbcConnectionConfiguration;
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }
}