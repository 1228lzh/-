package com.zh.yygh.dict.service;

import com.zh.yygh.pojo.dict.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author king
 */
public interface DictService extends IService<Dict> {
    /**
     *根据数据id查询子数据列表
     * @param id 数据id
     * @return list
     */
    List<Dict> findChild(Integer id);
}
