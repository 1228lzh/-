package com.zh.yygh.dict.controller;

import com.zh.common.result.Result;
import com.zh.yygh.dict.service.DictService;
import com.zh.yygh.pojo.dict.Dict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ZH
 * @Date: 2022/11/15 20:32
 */
@RestController
@RequestMapping("admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Resource
    private DictService dictService;

    /**
     * 根据数据id查询子数据列表
     * @param id 数据id
     * @return list
     */
    @GetMapping("findChild/{id}")
    public Result findChild(@PathVariable Integer id){
        List<Dict> childList = dictService.findChild(id);
        return Result.ok(childList);
    }
}
