package com.cl.dao;

import com.cl.entity.ShouzhimingxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ShouzhimingxiView;


/**
 * 收支明细
 * 
 * @author 
 * @email 
 * @date 2024-12-27 22:23:16
 */
public interface ShouzhimingxiDao extends BaseMapper<ShouzhimingxiEntity> {
	
	List<ShouzhimingxiView> selectListView(@Param("ew") Wrapper<ShouzhimingxiEntity> wrapper);

	List<ShouzhimingxiView> selectListView(Pagination page,@Param("ew") Wrapper<ShouzhimingxiEntity> wrapper);
	
	ShouzhimingxiView selectView(@Param("ew") Wrapper<ShouzhimingxiEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ShouzhimingxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ShouzhimingxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ShouzhimingxiEntity> wrapper);



}
