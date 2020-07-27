package org.codeforworld.winterredserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.codeforworld.winterredserver.entity.ProfessionalField;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.service.ProfessionalFieldService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 领域表 前端控制器
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@RestController
@RequestMapping("/professionalField")
@CrossOrigin
@Slf4j
public class ProfessionalFieldController {
    @Resource
    private ProfessionalFieldService professionalFieldService;

    @GetMapping("/queryByPage")
    public Result queryByPage (@RequestParam("curPage") Integer curPage, @RequestParam("pageSize") Integer pageSize, ProfessionalField professionalField) {
        Result result = new Result();
        PageHelper.startPage(curPage, pageSize);
        List<ProfessionalField> list = professionalFieldService.queryProfessionalField(professionalField);
        PageInfo<ProfessionalField> page = new PageInfo<>(list);
        result.setResults(page);
        return result;
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody @Valid ProfessionalField professionalField){
        Result result = new Result();
        boolean isSuccess = professionalFieldService.saveOrUpdate(professionalField);
        if(isSuccess){
            result.setSuccessMsg("保存成功！");
        }else {
            result.setFailedMsg("保存失败！");
        }
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete (ProfessionalField professionalField){
        Result result = new Result();
        boolean isSuccess = professionalFieldService.removeById(professionalField.getId());
        if (isSuccess){
            result.setSuccessMsg("删除成功！");
        }else {
            result.setFailedMsg("删除失败！");
        }
        return result;
    }
}
