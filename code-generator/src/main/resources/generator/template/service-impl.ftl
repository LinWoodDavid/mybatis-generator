package ${basePackage}.service.impl;

import ${basePackage}.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import tk.mybatis.mapper.entity.Condition;

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
     *  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
     */
    @Override
    public int save(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return ${modelNameLowerCamel}Mapper.insert(${modelNameLowerCamel});
    }

    /**
     *  批量持久化
     *  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
     */
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Override
    public int save(List<${modelNameUpperCamel}> ${modelNameLowerCamel}s) {
        return ${modelNameLowerCamel}Mapper.insertList(${modelNameLowerCamel}s);
    }

    /**
     *  通过主鍵刪除
     */
    @Override
    public int deleteById(Object primaryKey) {
        return ${modelNameLowerCamel}Mapper.deleteByPrimaryKey(primaryKey);
    }

    /**
     *  批量刪除 eg：ids -> “1,2,3,4”
     *  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
     */
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = { Exception.class })
    @Override
    public int deleteByIds(String ids) {
        return ${modelNameLowerCamel}Mapper.deleteByIds(ids);
    }

    /**
     *  通过主键更新
     */
    @Override
    public int updateByPrimaryKey(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return ${modelNameLowerCamel}Mapper.updateByPrimaryKey(${modelNameLowerCamel});
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
        Condition condition=new Condition(${modelNameUpperCamel}.class);
        condition.orderBy("createTime").desc();
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Mapper.selectByCondition(condition);
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
    public PageInfo find${modelNameUpperCamel}ByCondition(Integer page, Integer size, ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Mapper.select(${modelNameLowerCamel});
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
