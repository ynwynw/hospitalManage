package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.annotation.SysLog;

import com.cl.entity.YaopinleixingEntity;
import com.cl.entity.view.YaopinleixingView;

import com.cl.service.YaopinleixingService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 药品类型
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-27 22:23:16
 */
@RestController
@RequestMapping("/yaopinleixing")
public class YaopinleixingController {
    @Autowired
    private YaopinleixingService yaopinleixingService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YaopinleixingEntity yaopinleixing,
                                            HttpServletRequest request){
                                    EntityWrapper<YaopinleixingEntity> ew = new EntityWrapper<YaopinleixingEntity>();
                                                        
        
        
        PageUtils page = yaopinleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopinleixing), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YaopinleixingEntity yaopinleixing, 
		HttpServletRequest request){
        EntityWrapper<YaopinleixingEntity> ew = new EntityWrapper<YaopinleixingEntity>();

		PageUtils page = yaopinleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopinleixing), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YaopinleixingEntity yaopinleixing){
       	EntityWrapper<YaopinleixingEntity> ew = new EntityWrapper<YaopinleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yaopinleixing, "yaopinleixing")); 
        return R.ok().put("data", yaopinleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YaopinleixingEntity yaopinleixing){
        EntityWrapper< YaopinleixingEntity> ew = new EntityWrapper< YaopinleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yaopinleixing, "yaopinleixing")); 
		YaopinleixingView yaopinleixingView =  yaopinleixingService.selectView(ew);
		return R.ok("查询药品类型成功").put("data", yaopinleixingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YaopinleixingEntity yaopinleixing = yaopinleixingService.selectById(id);
		yaopinleixing = yaopinleixingService.selectView(new EntityWrapper<YaopinleixingEntity>().eq("id", id));
        return R.ok().put("data", yaopinleixing);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YaopinleixingEntity yaopinleixing = yaopinleixingService.selectById(id);
		yaopinleixing = yaopinleixingService.selectView(new EntityWrapper<YaopinleixingEntity>().eq("id", id));
        return R.ok().put("data", yaopinleixing);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增药品类型")
    public R save(@RequestBody YaopinleixingEntity yaopinleixing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yaopinleixing);
        yaopinleixingService.insert(yaopinleixing);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增药品类型")
    @RequestMapping("/add")
    public R add(@RequestBody YaopinleixingEntity yaopinleixing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yaopinleixing);
        yaopinleixingService.insert(yaopinleixing);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改药品类型")
    public R update(@RequestBody YaopinleixingEntity yaopinleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yaopinleixing);
        yaopinleixingService.updateById(yaopinleixing);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除药品类型")
    public R delete(@RequestBody Long[] ids){
        yaopinleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
