package ${basePackage}.service;
import ${basePackage}.model.${modelNameUpperCamel};
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * Created by ${author} on ${date}.
 *
 *  添加缓存
 *
 *  @Cacheable(value = "user" ,key = "targetClass.name + methodName +#p0")
 *  方法上已经加了@CacheConfig 注解此处value可以省略
 *
 *  清空缓存
 *
 *  1.清除一条缓存，key为要清空的数据
 *  @CacheEvict(value="emp",key="#id")
 *
 *  2.方法调用后清空所有缓存
 *  @CacheEvict(value="accountCache",allEntries=true)
 *
 *  3.方法调用前清空所有缓存
 *  @CacheEvict(value="accountCache",beforeInvocation=true)
 */
@CacheConfig(cacheNames = {"${modelNameUpperCamel}Cache"})
public interface ${modelNameUpperCamel}Service {

    //持久化
    int save(${modelNameUpperCamel} ${modelNameLowerCamel});

    //批量持久化
    int save(List<${modelNameUpperCamel}> ${modelNameLowerCamel}s);

    //通过主鍵刪除
    int deleteById(Object primaryKey);

    //批量刪除 eg：ids -> “1,2,3,4”
    int deleteByIds(String ids);

    //通过主键更新
    int updateByPrimaryKey(${modelNameUpperCamel} ${modelNameLowerCamel});

    //通过主键查找
    ${modelNameUpperCamel} findById(Object primaryKey);

    //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
    //T findBy(String fieldName, Object value) throws TooManyResultsException;

    //通过多个ID查找//eg：ids -> “1,2,3,4”
    List<${modelNameUpperCamel}> findByIds(String ids);

    //获取所有
    List<${modelNameUpperCamel}> findAll();

    //通过条件获得详情
    ${modelNameUpperCamel} selectOne(${modelNameUpperCamel} ${modelNameLowerCamel});

    //分页获取所有
    PageInfo listOf${modelNameUpperCamel}(Integer page, Integer size);

    //通过条件进行分页查询
    PageInfo find${modelNameUpperCamel}ByCondition(Integer page, Integer size, ${modelNameUpperCamel} ${modelNameLowerCamel});
}
