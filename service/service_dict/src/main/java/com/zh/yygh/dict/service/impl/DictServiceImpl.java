package com.zh.yygh.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.yygh.pojo.dict.Dict;
import com.zh.yygh.dict.service.DictService;
import com.zh.yygh.dict.mapper.DictMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService{

    @Override
    public List<Dict> findChild(Integer id) {
        // 查找当前parent_id的子结点
        List<Dict> childList = query().eq("parent_id", id).list();

        // 判断子节点是否还有子节点,并赋值
        for (Dict dict : childList) {
            Long dictId = dict.getId();
            boolean hasChild = hasChild(dictId);
            dict.setHasChildren(hasChild);
        }
        return childList;
    }

    /**
     * 查看当前是否是否有子结点
     * @param id parent_id
     * @return true or false
     */
    private boolean hasChild(Long id){
        Integer childCount = query().eq("parent_id", id).count();
        return childCount>0;
    }
}




