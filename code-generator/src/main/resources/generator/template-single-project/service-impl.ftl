package ${basePackage}.service.impl;

import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.core.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by ${author} on ${date}.
 */
@Service
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {

    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    /**
     *  持久化
     */
    @Override
    public void save(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Mapper.insert(${modelNameLowerCamel});
    }

    /**
     *  批量持久化
     */
    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(List<${modelNameUpperCamel}> ${modelNameLowerCamel}s) {
        ${modelNameLowerCamel}Mapper.insertList(${modelNameLowerCamel}s);
    }

    /**
     *  通过主鍵刪除
     */
    @Override
    public void deleteById(Object primaryKey) {
        ${modelNameLowerCamel}Mapper.deleteByPrimaryKey(primaryKey);
    }

    /**
     *  批量刪除 eg：ids -> “1,2,3,4”
     */
    @Override
    public void deleteByIds(String ids) {
        ${modelNameLowerCamel}Mapper.deleteByIds(ids);
    }

    /**
     *  通过主键更新
     */
    @Override
    public void updateByPrimaryKey(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Mapper.updateByPrimaryKey(${modelNameLowerCamel});
    }
    /**
     *  通过主键更新不为空的属性
     */
    @Override
    public void updateByPrimaryKeySelective(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(${modelNameLowerCamel});
    }

    /**
     *  通过主键查找
     */
    @Override
    public ${modelNameUpperCamel} findById(Object primaryKey) {
        return ${modelNameLowerCamel}Mapper.selectByPrimaryKey(primaryKey);
    }

    /**
     *  通过多个ID查找      eg：ids -> “1,2,3,4”
     */
    @Override
    public List<${modelNameUpperCamel}> findByIds(String ids) {
        return ${modelNameLowerCamel}Mapper.selectByIds(ids);
    }

    /**
     *  获取所有
     */
    @Override
    public List<${modelNameUpperCamel}> findAll() {
        return ${modelNameLowerCamel}Mapper.selectAll();
    }

    /**
     * 通过条件获得详情
     * @param ${modelNameLowerCamel}
     * @return
     */
    @Override
    public ${modelNameUpperCamel} selectOne(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return ${modelNameLowerCamel}Mapper.selectOne(${modelNameLowerCamel});
    }

    /**
     *  分页获取所有
     * @param page
     * @param size
     */
    @Override
    public PageInfo listOf${modelNameUpperCamel}(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Mapper.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
    * 通过条件进行分页查询
    * @param page
    * @param size
    * @param ${modelNameLowerCamel}
    * @return
    */
    @Override
    public PageInfo find${modelNameUpperCamel}ByProperty(Integer page, Integer size, ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Mapper.select(${modelNameLowerCamel});
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
