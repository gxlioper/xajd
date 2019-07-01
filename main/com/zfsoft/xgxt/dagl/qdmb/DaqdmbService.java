/**
 * @部门:学工产品事业部
 * @日期：2014-2-13 下午05:25:02 
 */  
package com.zfsoft.xgxt.dagl.qdmb;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xtwh.bjdl.BjdlDao;
import com.zfsoft.xgxt.xtwh.cxpz.CxpzDao;
import com.zfsoft.xgxt.xtwh.cxpz.CxpzForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：  wanghj [工号：1004]
 * @时间： 2014-2-13 下午05:25:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaqdmbService extends SuperServiceImpl<DaqdmbForm, DaqdmbDao> {

	private DaqdmbDao dao = new DaqdmbDao();
	
	public DaqdmbService(){
		super.setDao(dao);
	}
	
	public HashMap<String, String> getDaqdmbById(String daqdmb_id) throws Exception{
		
		return dao.getDaqdmbById(daqdmb_id);
	}
	
	public HashMap<String, String> getDaqdmbByName(String daqdmb_mc) throws Exception{
		
		return dao.getDaqdmbByName(daqdmb_mc);
	}
	
	public List<HashMap<String, String>> getDaqdclListByMbid(String daqdmb_id) throws Exception{
		
		return dao.getDaqdclListByMbid(daqdmb_id);
	}
	
	
	public boolean updateQdmbQyzt(String qyzt,String[] daqdmb_id) throws Exception{
		return dao.updateQdmbQyzt(qyzt, daqdmb_id);
	}
	
	public boolean addMbclInfo(String daqdmb_id,String[] daqdcl) throws Exception{
		
		return dao.addMbclInfo(daqdmb_id, daqdcl);
	}
	
	public int delMbclInfo(String daqdmb_id) throws Exception{
		
		return dao.delMbclInfo(daqdmb_id);
	}

	public int delBatchMbcl(String[] daqdmb_id) throws Exception {
		return dao.delBatchMbcl(daqdmb_id);
	}
	public List<HashMap<String, String>> getDaqdmbList() throws Exception{
		
		return dao.getDaqdmbList();
	}
}
