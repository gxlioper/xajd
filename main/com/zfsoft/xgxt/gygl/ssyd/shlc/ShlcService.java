/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.gygl.ssyd.shlc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 宿舍异动模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ShlcService extends SuperServiceImpl<ShlcForm, ShlcDao> {
	ShlcDao dao = new ShlcDao();
	public ShlcService() {
		this.setDao(dao);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:保存请假类型
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:46:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean save(ShlcForm qf) throws Exception {
		if(qf.getId()==null ||"".equalsIgnoreCase(qf.getId())){
			String guid = UniqID.getInstance().getUniqIDHash();
			qf.setId(guid);
			return this.runInsert(qf);
		}else{
			return this.runUpdate(qf);
		}
	}
	public ShlcForm getNowShlc(String ssydlx) throws Exception{
		HashMap<String, String> hm = dao.getXxBySsydlx(ssydlx);
		if(hm.size() <= 0){
			return null;
		}
		return getModel(hm.get("id"));
	}
	
	public ShlcForm getNowShlc() throws Exception{
		List<HashMap<String, String>> list= getAllList(new ShlcForm());
		if(null==list||list.size()<=0){
			return null;
		}
		//获取第一条数据
		HashMap<String, String> hm=list.get(0);
		return getModel(hm.get("id"));
	}
	
	public HashMap<String,String> getXx(String splcid){
		return dao.getXx(splcid);
	}
	
	public boolean checkSsydlx(String ssydlx) throws Exception{
		boolean rs = false;
		String count =  dao.checkSsydlx(ssydlx);
		if(Integer.parseInt(count) > 0) rs=true;
		 return rs;
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-3-13 下午05:54:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ssydlx
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getSplcid(String ssydlx) {
		return dao.getSplcid(ssydlx);
	}
	
	public List<HashMap<String,String>> getShlcList(){
		return dao.getShlcList();
	}
	
	public boolean delete() throws Exception{
		return dao.delete();
	}
	
	public boolean insert(ShlcForm t) throws SQLException{
		String tssplcid = t.getTssplcid();
		String sstzsplcid = t.getSstzsplcid();
		String sxlssplcid = t.getSxlssplcid();
		String rzsplcid = t.getRzsplcid();
		List<String[]> paramList = new ArrayList<String[]>();
		if(StringUtils.isNotNull(tssplcid)){
			paramList.add(new String[]{"00",tssplcid});
		}
		if(StringUtils.isNotNull(sstzsplcid)){
			paramList.add(new String[]{"01",sstzsplcid});
		}
		if(StringUtils.isNotNull(sxlssplcid)){
			paramList.add(new String[]{"02",sxlssplcid});
		}
		if(StringUtils.isNotNull(rzsplcid)){
			paramList.add(new String[]{"03",rzsplcid});
		}
		t.setParamList(paramList);
		return dao.insert(t);
	}
	
	public ShlcForm getShlcForm(){
		ShlcForm shlcForm = new ShlcForm();
		List<HashMap<String, String>> ShlxList = getShlcList();
		if(null != ShlxList && ShlxList.size() > 0){			
			for(HashMap<String,String> map : ShlxList){
				String ssydlx = map.get("ssydlx");
				String splcid = map.get("splcid");
				if("00".equals(ssydlx)){
					shlcForm.setTssplcid(splcid);
				}else if("01".equals(ssydlx)){
					shlcForm.setSstzsplcid(splcid);
				}else if("02".equals(ssydlx)){
					shlcForm.setSxlssplcid(splcid);
				}else if("03".equals(ssydlx)){
					shlcForm.setRzsplcid(splcid);
				}
			}
		}
		return shlcForm;
	}
}
