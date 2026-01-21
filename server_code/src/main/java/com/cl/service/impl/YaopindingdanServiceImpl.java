package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.YaopindingdanDao;
import com.cl.entity.YaopindingdanEntity;
import com.cl.service.YaopindingdanService;
import com.cl.entity.view.YaopindingdanView;

@Service("yaopindingdanService")
public class YaopindingdanServiceImpl extends ServiceImpl<YaopindingdanDao, YaopindingdanEntity> implements YaopindingdanService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YaopindingdanEntity> page = this.selectPage(
                new Query<YaopindingdanEntity>(params).getPage(),
                new EntityWrapper<YaopindingdanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YaopindingdanEntity> wrapper) {
		  Page<YaopindingdanView> page =new Query<YaopindingdanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YaopindingdanView> selectListView(Wrapper<YaopindingdanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YaopindingdanView selectView(Wrapper<YaopindingdanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YaopindingdanEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YaopindingdanEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YaopindingdanEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
