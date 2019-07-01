/**
 * @部门:学工产品事业部
 * @日期：2014年6月12日 上午11:00:27 
 */
package com.zfsoft.xgxt.gygl.ssyd.plyd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理模块
 * @类功能描述: 批量宿舍异动 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月12日 上午11:00:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PlydService extends SuperServiceImpl<PlydModel, PlydDao> {

	private static final String Split_CHAR = ",";
	
	/**
	 * 
	 * @描述: 已入住学生列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月13日 上午10:25:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getYrzPageList(PlydModel t, User user) throws Exception{
		
		return dao.getYrzPageList(t, user);
	}
	
	
	/**
	 * 
	 * @描述: 待调整列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月13日 上午10:26:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtzPageList(PlydModel t, User user) throws Exception{
		
		return dao.getDtzPageList(t, user);
	}
	
	
	/**
	 * 
	 * @描述: 确认调整列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月13日 上午10:26:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getQrtzList(PlydModel t, User user) throws Exception{
		return dao.getQrtzList(t, user);
	}
	
	
	/**
	 * 
	 * @描述: 批量设置为待调整
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月16日 上午10:46:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws Exception 
	 */
	public boolean szDtz(String ids) throws Exception{
		
		if (StringUtil.isNull(ids)){
			return false;
		}
		
		String[] xhs = ids.split(Split_CHAR);
		
		return dao.szDtz(xhs);
	}
	
	
	/**
	 * 
	 * @描述: 取消待调整
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午01:44:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qxDtz(String ids) throws Exception{
		
		if (StringUtil.isNull(ids)){
			return false;
		}
		
		String[] xhs = ids.split(Split_CHAR);
		
		return dao.qxDtz(xhs);
	}
	
	
	/**
	 * 
	 * @描述: 取消调整
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午01:44:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qxtz(String ids) throws Exception{
		
		if (StringUtil.isNull(ids)){
			return false;
		}
		
		String[] xhs = ids.split(Split_CHAR);
		
		return dao.qxtz(xhs);
	}
	
	
	/**
	 * 
	 * @描述: 查询调整学生信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午02:27:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTzxsList(String ids){
		
		if (StringUtil.isNull(ids)){
			return null;
		}
		
		String[] xhs = ids.split(Split_CHAR);
		return dao.getTzxsList(xhs);
	}
	
	
	/**
	 * 
	 * @描述: 可调整入住的床位信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午02:50:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xb
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String,Object> getCwxxList(String xb){
		
		
		List<HashMap<String,String>> ldxxList = dao.getLdxxList(xb);
		List<HashMap<String,String>> lcxxList = dao.getLcxxList(xb);
		List<HashMap<String,String>> cwxxList = dao.getCwxxList(xb);
		 
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("ldxxList", ldxxList);
		map.put("lcxxList", lcxxList);
		
		List<HashMap<String,Object>> qscwList = new ArrayList<HashMap<String,Object>>();
		
		String tempLddm = null;//楼栋代码
		String tempCh = null;//层号
		String tempQsh = null;//寝室号
		
		HashMap<String,Object> qscw = null;
		List<String> cwList = null;
		
		for (HashMap<String,String> cwxx : cwxxList){
			
			String lddm = cwxx.get("lddm");
			String ch = cwxx.get("ch");
			String qsh = cwxx.get("qsh");
			String cwh = cwxx.get("cwh");
			
			if (tempQsh == null || !((lddm.equals(tempLddm)) && ch.equals(tempCh) && qsh.equals(tempQsh))){
				
				if (cwList != null && !cwList.isEmpty()){
					qscw.put("cwList", cwList);
					qscwList.add(qscw);
				}
				
				tempLddm = lddm;
				tempCh = ch;
				tempQsh = qsh;
				qscw = new HashMap<String,Object>();
				cwList = new ArrayList<String>();
				
				qscw.put("lddm", lddm);
				qscw.put("ch", ch);
				qscw.put("qsh", qsh);
			} 
			
			cwList.add(cwh);
		}
		
		if (cwList != null && !cwList.isEmpty()){
			qscw.put("cwList", cwList);
			qscwList.add(qscw);
		}
		
		map.put("qscwList", qscwList);
		
		return map;
	}
	
	
	
	/**
	 * 
	 * @描述: 批量保存入住信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-19 下午03:25:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rzxx
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws Exception 
	 */
	public boolean saveRzxx(String rzxx) throws Exception{
		
		JSONArray jsonArr = JSONArray.fromString(rzxx);
		List<String[]> params = new ArrayList<String[]>();
		List<String> xhList = new ArrayList<String>();
		
		for (int i = 0 ; i < jsonArr.length() ; i++){
			JSONObject json = jsonArr.getJSONObject(i);
			
			String cwxx = json.getString("cwxx");
			String xh = json.getString("xh");
			
			xhList.add(xh);
			
			String[] cwInfo = cwxx.split("\\-");
			params.add(StringUtils.joinStrArr(cwInfo,new String[]{xh}));
		}
		
		dao.szDtz(xhList.toArray(new String[]{}));
		
		return dao.saveRzxx(params);
	}
	
	
	/**
	 * 
	 * @描述: 查询不可提交的总数
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-24 下午02:18:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int getCountByBktj(){
		
		int bktj = dao.getCountByBktj();
		int wrz = dao.getCountWrz();
		
		return bktj+wrz;
	}
	
	
	/**
	 *  
	 * 
	 * @描述: 确认调整
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-26 下午05:07:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean qrtz() throws Exception{
		
		//插入异动记录
		boolean result = dao.saveYdjg();
		//将目标床位原来住的学号清空
		if (result){
			result = dao.updateYcwToBlank();
		}
		//将调整更新到正式住宿信息表
		if (result){
			result = dao.updateToXcw();
		}
		//清除临时调整记录
		if (result){
			result = dao.clearTempData();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
}
