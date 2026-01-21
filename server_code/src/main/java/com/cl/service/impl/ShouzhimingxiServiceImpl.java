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


import com.cl.dao.ShouzhimingxiDao;
import com.cl.entity.ShouzhimingxiEntity;
import com.cl.service.ShouzhimingxiService;
import com.cl.entity.view.ShouzhimingxiView;

@Service("shouzhimingxiService")
public class ShouzhimingxiServiceImpl extends ServiceImpl<ShouzhimingxiDao, ShouzhimingxiEntity> implements ShouzhimingxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShouzhimingxiEntity> page = this.selectPage(
                new Query<ShouzhimingxiEntity>(params).getPage(),
                new EntityWrapper<ShouzhimingxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShouzhimingxiEntity> wrapper) {
		  Page<ShouzhimingxiView> page =new Query<ShouzhimingxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ShouzhimingxiView> selectListView(Wrapper<ShouzhimingxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShouzhimingxiView selectView(Wrapper<ShouzhimingxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<ShouzhimingxiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<ShouzhimingxiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<ShouzhimingxiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
