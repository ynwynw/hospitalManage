package com.cl.dao;

import com.cl.entity.YaopindingdanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YaopindingdanView;


/**
 * 药品订单
 * 
 * @author 
 * @email 
 * @date 2024-12-27 22:23:16
 */
public interface YaopindingdanDao extends BaseMapper<YaopindingdanEntity> {
	
	List<YaopindingdanView> selectListView(@Param("ew") Wrapper<YaopindingdanEntity> wrapper);

	List<YaopindingdanView> selectListView(Pagination page,@Param("ew") Wrapper<YaopindingdanEntity> wrapper);
	
	YaopindingdanView selectView(@Param("ew") Wrapper<YaopindingdanEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YaopindingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YaopindingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YaopindingdanEntity> wrapper);



}
