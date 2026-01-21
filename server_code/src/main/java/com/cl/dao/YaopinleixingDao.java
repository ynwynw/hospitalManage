package com.cl.dao;

import com.cl.entity.YaopinleixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YaopinleixingView;


/**
 * 药品类型
 * 
 * @author 
 * @email 
 * @date 2024-12-27 22:23:16
 */
public interface YaopinleixingDao extends BaseMapper<YaopinleixingEntity> {
	
	List<YaopinleixingView> selectListView(@Param("ew") Wrapper<YaopinleixingEntity> wrapper);

	List<YaopinleixingView> selectListView(Pagination page,@Param("ew") Wrapper<YaopinleixingEntity> wrapper);
	
	YaopinleixingView selectView(@Param("ew") Wrapper<YaopinleixingEntity> wrapper);


}
