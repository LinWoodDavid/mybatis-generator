package ${basePackage}.service;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.core.Service;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

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
//@CacheConfig(cacheNames = {"${modelNameUpperCamel}Cache"})
public interface ${modelNameUpperCamel}Service {

    //持久化
    void save(${modelNameUpperCamel} ${modelNameLowerCamel});

    //批量持久化
    void save(List<${modelNameUpperCamel}> ${modelNameLowerCamel}s);

    //通过主鍵刪除
    void deleteById(Object primaryKey);

    //批量刪除 eg：ids -> “1,2,3,4”
    void deleteByIds(String ids);

    //通过主键更新
    void updateByPrimaryKey(${modelNameUpperCamel} ${modelNameLowerCamel});

    //通过主键更新不为空的属性
    void updateByPrimaryKeySelective(${modelNameUpperCamel} ${modelNameLowerCamel});

    //通过主键查找
    ${modelNameUpperCamel} findById(Object primaryKey);

    //通过多个ID查找//eg：ids -> “1,2,3,4”
    List<${modelNameUpperCamel}> findByIds(String ids);

    //获取所有
    List<${modelNameUpperCamel}> findAll();

    //通过条件获得详情
    ${modelNameUpperCamel} selectOne(${modelNameUpperCamel} ${modelNameLowerCamel});

    //分页获取所有
    PageInfo listOf${modelNameUpperCamel}(Integer page, Integer size);

    //通过条件进行分页查询
    PageInfo find${modelNameUpperCamel}ByProperty(Integer page, Integer size, ${modelNameUpperCamel} ${modelNameLowerCamel});
}
