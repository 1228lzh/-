package com.zh.yygh.hospital.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.common.result.Result;
import com.zh.yygh.hospital.service.HospitalSetService;
import com.zh.yygh.pojo.hospital.HospitalSet;
import com.zh.yygh.vo.hospital.HospitalSetQueryVo;
import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @Author: ZH
 * @Date: 2022/10/31 20:53
 */
@RestController
@RequestMapping("/admin/hospital/hospitalSet")
@CrossOrigin
public class HospitalSetController {
    @Resource
    private HospitalSetService hospitalSetService;

    @GetMapping("{id}")
    public Result getOneHospitalSet(@PathVariable Integer id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return hospitalSet!=null?Result.ok(hospitalSet):Result.fail();
    }

    @GetMapping
    public Result getAllHospitalSet(){
        List<HospitalSet> hospitalSetList = hospitalSetService.list();
        return !hospitalSetList.isEmpty()?Result.ok(hospitalSetList):Result.fail();
    }

    /**
     * 带条件分页查询
     * @param current 当前页
     * @param limit 每页显示记录数
     * @param hospitalSetQueryVo 查询条件
     * @return page
     */
    @PostMapping("/page/{current}/{limit}")
    public Result getPages(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        //1.创建page对象
        Page<HospitalSet> page = new Page<>(current, limit);

        //2.构造条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        //有bug,当为空的时候还是会报空指针异常,直接使用if语句判空即可
        //wrapper.like(hospitalSetQueryVo!=null,"hosname",hospitalSetQueryVo.getHosname());

        if (hospitalSetQueryVo!=null){
            String hosname = hospitalSetQueryVo.getHosname();
            String hoscode = hospitalSetQueryVo.getHoscode();
            if (!StringUtils.isEmpty(hosname)){
                wrapper.like("hosname",hosname);
            }
            if (!StringUtils.isEmpty(hoscode)){
                wrapper.eq("hoscode", hoscode);
            }
        }
        //3.调用方法
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);
        return Result.ok(pageHospitalSet);
    }

    @PostMapping
    public Result addHospitalSet(@RequestBody HospitalSet hospitalSet){
        //1可使用，0不可使用
        hospitalSet.setStatus((byte) 1);
        //签名密钥:当前时间+随即数 MD5加密
        Random random = new Random();
        //SecureUtil.md5 糊涂工具包md5
        hospitalSet.setSignKey(SecureUtil.md5(System.currentTimeMillis()+""+random.nextInt(1000)));
        boolean isSave = hospitalSetService.save(hospitalSet);
        return isSave?Result.ok():Result.fail();
    }

    @DeleteMapping("{id}")
    public Result delHospitalSetById(@PathVariable Integer id){
        //逻辑删除医院设置
        boolean isDel = hospitalSetService.removeById(id);
        return isDel?Result.ok():Result.fail();
    }

    @DeleteMapping
    public Result batchDelHospitalSet(@RequestBody List<Integer> ids){
        //List集合
        hospitalSetService.removeByIds(ids);
        return Result.ok();
    }


    @PutMapping
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){

        if (hospitalSet.getId()==null){
            return Result.fail("修改失败");
        }
        boolean isUpdate = hospitalSetService.updateById(hospitalSet);
        return isUpdate?Result.ok("修改成功"):Result.fail("修改失败");
    }

    /**
     *医院设置的锁定与解锁
     * @param id 医院id
     * @param status 医院状态锁定，解锁
     * @return ok
     */
    @PutMapping("lock/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Integer id,@PathVariable Byte status){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        hospitalSet.setStatus(status);
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }

    @GetMapping("sendKey/{id}")
    public Result sendKey(@PathVariable Integer id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        // TODO 发送短信
        return Result.ok();
    }
}
