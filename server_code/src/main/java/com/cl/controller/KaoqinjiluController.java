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

import com.cl.entity.KaoqinjiluEntity;
import com.cl.entity.view.KaoqinjiluView;

import com.cl.service.KaoqinjiluService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 考勤记录
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-27 22:23:15
 */
@RestController
@RequestMapping("/kaoqinjilu")
public class KaoqinjiluController {
    @Autowired
    private KaoqinjiluService kaoqinjiluService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KaoqinjiluEntity kaoqinjilu,
                                                                                                                                        HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                            if(tableName.equals("yuangong")) {
                    kaoqinjilu.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                                                        EntityWrapper<KaoqinjiluEntity> ew = new EntityWrapper<KaoqinjiluEntity>();
                                                                                                                                                                                                            
        
        
        PageUtils page = kaoqinjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqinjilu), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KaoqinjiluEntity kaoqinjilu, 
		HttpServletRequest request){
        EntityWrapper<KaoqinjiluEntity> ew = new EntityWrapper<KaoqinjiluEntity>();

		PageUtils page = kaoqinjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqinjilu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KaoqinjiluEntity kaoqinjilu){
       	EntityWrapper<KaoqinjiluEntity> ew = new EntityWrapper<KaoqinjiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kaoqinjilu, "kaoqinjilu")); 
        return R.ok().put("data", kaoqinjiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KaoqinjiluEntity kaoqinjilu){
        EntityWrapper< KaoqinjiluEntity> ew = new EntityWrapper< KaoqinjiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kaoqinjilu, "kaoqinjilu")); 
		KaoqinjiluView kaoqinjiluView =  kaoqinjiluService.selectView(ew);
		return R.ok("查询考勤记录成功").put("data", kaoqinjiluView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KaoqinjiluEntity kaoqinjilu = kaoqinjiluService.selectById(id);
		kaoqinjilu = kaoqinjiluService.selectView(new EntityWrapper<KaoqinjiluEntity>().eq("id", id));
        return R.ok().put("data", kaoqinjilu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KaoqinjiluEntity kaoqinjilu = kaoqinjiluService.selectById(id);
		kaoqinjilu = kaoqinjiluService.selectView(new EntityWrapper<KaoqinjiluEntity>().eq("id", id));
        return R.ok().put("data", kaoqinjilu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增考勤记录")
    public R save(@RequestBody KaoqinjiluEntity kaoqinjilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaoqinjilu);
        kaoqinjiluService.insert(kaoqinjilu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增考勤记录")
    @RequestMapping("/add")
    public R add(@RequestBody KaoqinjiluEntity kaoqinjilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kaoqinjilu);
        kaoqinjiluService.insert(kaoqinjilu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改考勤记录")
    public R update(@RequestBody KaoqinjiluEntity kaoqinjilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqinjilu);
        kaoqinjiluService.updateById(kaoqinjilu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除考勤记录")
    public R delete(@RequestBody Long[] ids){
        kaoqinjiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
