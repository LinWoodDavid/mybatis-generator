package ${basePackage}.web;
import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    private static final Logger logger = LoggerFactory.getLogger(${modelNameUpperCamel}Controller.class);

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
     *  添加一条记录
     * @param ${modelNameLowerCamel}
     * @return
     */
    @PostMapping("/addThe${modelNameUpperCamel}")
    public Result addThe${modelNameUpperCamel}(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 通过主键删除一条记录
     * @param ${modelNameLowerCamel}
     * @return
     */
    @PostMapping("/deleteOf${modelNameUpperCamel}")
    public Result deleteOf${modelNameUpperCamel}(${modelNameUpperCamel} ${modelNameLowerCamel} ) {
        ${modelNameLowerCamel}Service.deleteById(${modelNameLowerCamel}.getId());
        return ResultGenerator.genSuccessResult();
    }

    /**
     *  通过主键更新一条记录
     * @param ${modelNameLowerCamel}
     * @return
     */
    @PostMapping("/updateThe${modelNameUpperCamel}")
    public Result updateThe${modelNameUpperCamel}(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.updateByPrimaryKey(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    /**
     *  详情
     * @param ${modelNameLowerCamel}
     * @return
     */
    @PostMapping("/detailOf${modelNameUpperCamel}")
    public Result detailOf${modelNameUpperCamel}(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameUpperCamel} model = ${modelNameLowerCamel}Service.findById(${modelNameLowerCamel}.getId());
        return ResultGenerator.genSuccessResult(model);
    }

    /**
     *  分页查询所有数据
     * @param page          页码
     * @param size          每页显示大小
     * @return
     */
    @PostMapping("/listOf${modelNameUpperCamel}")
    public Result listOf${modelNameUpperCamel}(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo pageInfo = ${modelNameLowerCamel}Service.listOf${modelNameUpperCamel}(page,size);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     *  条件查询数据
     * @param page          页码
     * @param size          每页显示大小
     * @param ${modelNameLowerCamel}
     * @return
     */
    @PostMapping("/find${modelNameUpperCamel}ByProperty")
    public Result find${modelNameUpperCamel}ByProperty(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        PageInfo pageInfo = ${modelNameLowerCamel}Service.find${modelNameUpperCamel}ByProperty(page, size, ${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
