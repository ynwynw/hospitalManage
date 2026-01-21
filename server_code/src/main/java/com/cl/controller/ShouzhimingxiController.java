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

import com.cl.entity.ShouzhimingxiEntity;
import com.cl.entity.view.ShouzhimingxiView;

import com.cl.service.ShouzhimingxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 收支明细
 * 后端接口
 * @author 
 * @email 
 * @date 2024-12-27 22:23:16
 */
@RestController
@RequestMapping("/shouzhimingxi")
public class ShouzhimingxiController {
    @Autowired
    private ShouzhimingxiService shouzhimingxiService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShouzhimingxiEntity shouzhimingxi,
                                                                                                                    HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                        if(tableName.equals("yuangong")) {
                    shouzhimingxi.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                            EntityWrapper<ShouzhimingxiEntity> ew = new EntityWrapper<ShouzhimingxiEntity>();
                                                                                                                                                                                
        
        
        PageUtils page = shouzhimingxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shouzhimingxi), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShouzhimingxiEntity shouzhimingxi, 
		HttpServletRequest request){
        EntityWrapper<ShouzhimingxiEntity> ew = new EntityWrapper<ShouzhimingxiEntity>();

		PageUtils page = shouzhimingxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shouzhimingxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShouzhimingxiEntity shouzhimingxi){
       	EntityWrapper<ShouzhimingxiEntity> ew = new EntityWrapper<ShouzhimingxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shouzhimingxi, "shouzhimingxi")); 
        return R.ok().put("data", shouzhimingxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShouzhimingxiEntity shouzhimingxi){
        EntityWrapper< ShouzhimingxiEntity> ew = new EntityWrapper< ShouzhimingxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shouzhimingxi, "shouzhimingxi")); 
		ShouzhimingxiView shouzhimingxiView =  shouzhimingxiService.selectView(ew);
		return R.ok("查询收支明细成功").put("data", shouzhimingxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShouzhimingxiEntity shouzhimingxi = shouzhimingxiService.selectById(id);
		shouzhimingxi = shouzhimingxiService.selectView(new EntityWrapper<ShouzhimingxiEntity>().eq("id", id));
        return R.ok().put("data", shouzhimingxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShouzhimingxiEntity shouzhimingxi = shouzhimingxiService.selectById(id);
		shouzhimingxi = shouzhimingxiService.selectView(new EntityWrapper<ShouzhimingxiEntity>().eq("id", id));
        return R.ok().put("data", shouzhimingxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增收支明细")
    public R save(@RequestBody ShouzhimingxiEntity shouzhimingxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(shouzhimingxi);
        shouzhimingxiService.insert(shouzhimingxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增收支明细")
    @RequestMapping("/add")
    public R add(@RequestBody ShouzhimingxiEntity shouzhimingxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(shouzhimingxi);
        shouzhimingxiService.insert(shouzhimingxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改收支明细")
    public R update(@RequestBody ShouzhimingxiEntity shouzhimingxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shouzhimingxi);
        shouzhimingxiService.updateById(shouzhimingxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除收支明细")
    public R delete(@RequestBody Long[] ids){
        shouzhimingxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	





    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        EntityWrapper<ShouzhimingxiEntity> ew = new EntityWrapper<ShouzhimingxiEntity>();
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yuangong")) {
            ew.eq("yuangongzhanghao", (String)request.getSession().getAttribute("username"));
		}
        List<Map<String, Object>> result = shouzhimingxiService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<ShouzhimingxiEntity> ew = new EntityWrapper<ShouzhimingxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yuangong")) {
            ew.eq("yuangongzhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = shouzhimingxiService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        params.put("timeStatType", timeStatType);
        EntityWrapper<ShouzhimingxiEntity> ew = new EntityWrapper<ShouzhimingxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yuangong")) {
            ew.eq("yuangongzhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = shouzhimingxiService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<ShouzhimingxiEntity> ew = new EntityWrapper<ShouzhimingxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yuangong")) {
            ew.eq("yuangongzhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = shouzhimingxiService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", MPUtil.camelToSnake(columnName));
        EntityWrapper<ShouzhimingxiEntity> ew = new EntityWrapper<ShouzhimingxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yuangong")) {
            ew.eq("yuangongzhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = shouzhimingxiService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }







}
