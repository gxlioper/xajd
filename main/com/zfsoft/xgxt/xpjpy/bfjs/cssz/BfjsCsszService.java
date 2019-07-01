/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:31:23 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.cssz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.bfjs.fswh.BfjsFswhDao;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 班风竞赛管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-3-31 下午04:05:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BfjsCsszService extends SuperServiceImpl<BfjsCsszModel, BfjsCsszDao>  implements Constants{

	public static final String CPZ_CSH_NJZY = "1";//参评组初始化-年级+专业
	public static final String CPZ_CSH_BJ = "2";//参评组初始化-班级
	public static final String ZCFLR_WYQ = "1";//竞赛分录入--无页签
	public static final String ZCFLR_YYQ = "0";//竞赛分录入--有页签
	public static final String DFFS_XQ = "1";//打分方式  1:学期	
	public static final String DFFS_XN = "2";//打分方式  2：学年
	private static final String SplitChar = "#";
	public static final String XQKG="on"; //学期开关标识
	
	//是否需要计算学年竞赛
	public static final boolean getDfzq(){
		
		BfjsCsszService cs=new BfjsCsszService();
		String bfjsdfzq = cs.getCsz("bfjsdfzq");
		
		if(!StringUtils.isBlank(bfjsdfzq)&&!BfjsCsszService.DFFS_XN.equals(bfjsdfzq)){
			return true;
		}
		return false;
	}
	
	
	//根据参数设定配置竞赛周期
	public static final Map<String, String> getPdzq() throws Exception{
		
		BfjsCsszService cs=new BfjsCsszService();
		String Pdzq = cs.getCsz("pdzq");
		String xn = cs.getModel().getXn();
		String xq = cs.getModel().getXq();
		String cxxq = cs.getModel().getXq();
		
		if(DFFS_XN.equals(Pdzq)){
			xq = "on";
			cxxq = "";
		}
		
		Map<String, String> PdzqMap = new HashMap<String, String>();
		
		PdzqMap.put("xn", xn);
		PdzqMap.put("xq", xq);
		//默认高级查询学期
		PdzqMap.put("cxxq", cxxq);
		
		//竞赛周期名称
		String xqmc = Base.getXqmcForXqdm(xq);
		PdzqMap.put("Pdzqmc", xn+" "+xqmc);
		
		
		return PdzqMap;
	}
	
	
	private BfjsCsszDao BfjsCsszDao = new BfjsCsszDao();
	
	public BfjsCsszService(){
		dao = BfjsCsszDao;
	}
	
	
	/**
	 * 
	 * @描述:周期列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-3-31 下午04:20:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getPdzqList(){
		
		//系统学期列表
		List<HashMap<String,String>> xqList = Base.getXqList(); 
		//竞赛周期列表
		List<HashMap<String,String>> pdzqList = new ArrayList<HashMap<String,String>>();
		
		String zczq = dao.getCsz("bfjspdzq");
		
		String xn = null;
		
		for (int i = 0 ; i < 2 ; i++){
			
			if (i == 0){
				xn = String.valueOf(Integer.parseInt(Base.currXn.substring(0,4))-1) + "-" + Base.currXn.substring(0,4);
			} else {
				xn = Base.currXn;
			}
			
			if(!StringUtils.isBlank(zczq) && DFFS_XN.equals(zczq)){
				
				HashMap<String,String> zqMap = new HashMap<String, String>();
				zqMap.put("zqdm", xn+SplitChar+XQKG);
				zqMap.put("zqmc", xn);
				pdzqList.add(zqMap);
				
			} else {
				for (HashMap<String,String> map : xqList){
					HashMap<String,String> zqMap = new HashMap<String, String>();
					zqMap.put("zqdm", xn+SplitChar+map.get("xqdm"));
					zqMap.put("zqmc", xn+" "+map.get("xqmc"));
					pdzqList.add(zqMap);
				}
			}
		}
		
		return pdzqList;
	}
	
	
	
	/**
	 * 
	 * @描述:获取周期名称
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-3-31 下午04:20:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zqdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZqmc(String zqdm){
		
		if (StringUtil.isNull(zqdm) || zqdm.indexOf(SplitChar) == -1){
			return null;
		}
		
		String[] zqInfo = zqdm.split(SplitChar);
		
		if (zqInfo.length != 2){
			return null;
		}
		
		List<HashMap<String,String>> xqList = Base.getXqList();
		
		String xqmc = null;
		
		for (HashMap<String,String> map : xqList){
			
			if (map.get("xqdm").equals(zqInfo[1])){
				xqmc = map.get("xqmc");
				break;
			}
			
		}
		return zqInfo[0]+" "+xqmc;
	}
	
	
	
	
	/**
	 * 
	 * @描述:查询参数设置信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-3-31 下午04:36:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * BfjsCsszModel 返回类型 
	 * @throws
	 */
	public BfjsCsszModel getModel() throws Exception{
		
		BfjsCsszModel model = dao.getModel();
		
		if (model == null){
			//初始化参数设置
			model = new BfjsCsszModel();
			model.setSqkg(CLOSE);
			model.setJsfs(DFFS_XQ);
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			boolean result = dao.runInsert(model);
			
		} else {
			String xn = model.getXn();
			String xq = model.getXq();
			
			//拼装竞赛周期
			if (!StringUtil.isNull(xn) && !StringUtil.isNull(xq)){
				model.setPdzq(xn+SplitChar+xq);
			}
		}
		
		boolean isOpen = isOpen(model);
		model.setKgzt(String.valueOf(isOpen));
		
		return model;
	}
	
	
	
	/**
	 * 
	 * @描述: 更新参数设置
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-22 上午11:16:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdKey
	 * @param zdValue
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateBfjsCssz(String zdKey,String zdValue) throws Exception{
		
		if ("pdzq".equals(zdKey)){
			String[] zqArray = zdValue.split(SplitChar);
			return dao.updatePdzq(zqArray);
		} else {
			return dao.updateBfjsCssz(zdKey, zdValue);
		}
		
	}
	
	
	
	/**
	 * 
	 * @描述: 判断当前竞赛开关
	 * @作者：夏夏 [工号：1104]
	 * @日期：2013-7-22 下午01:48:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean isOpen(BfjsCsszModel model) throws Exception{
	
		if (model == null){
			model = dao.getModel();
		}
		
		//无参数设置数据 或者开关状态为关闭 ，为关闭状态
		if (model == null || CLOSE.equals(model.getSqkg())){
			return false;
		}
		
		//格式化开始时间、结束时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;
		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
		
		if (StringUtil.isNull(model.getKssj()) && StringUtil.isNull(model.getJssj())){
			return OPEN.equals(model.getSqkg());
		} else if (!StringUtil.isNull(model.getKssj()) && !StringUtil.isNull(model.getJssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			endTime=sdf.parse(model.getJssj()+":59");
			//当前时间早于结束时间 && 晚于开始时间  && 开关状态为开启
			return now.before(endTime) && now.after(startTime) && OPEN.equals(model.getSqkg());
		} else if (!StringUtil.isNull(model.getKssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			return now.after(startTime);
		} else if (!StringUtil.isNull(model.getJssj())){
			endTime=sdf.parse(model.getJssj()+":59");
			return now.before(endTime);
		}
		
		return false;
	}
	
	
	/**
	 * 
	 * @描述:获得参数配置
	 * @作者：cq [工号：785]
	 * @日期：2014-8-1 下午04:30:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCsz(String csdm){
		
		return dao.getCsz(csdm);
	}
	
	/**
	 * 
	 * @描述:初始化竞赛班级
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-22 上午10:00:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void initJsbj(User user) throws Exception{
		BfjsCsszModel model = dao.getModel();
		//当前周期是否已经有竞赛班级，有：return , 没有： 初始化
		boolean isHaveJsbj = dao.isHaveJsbj(model);
		dao.initMrf(model,user);
		if (isHaveJsbj){
			return ;
		}
		BfjsFswhDao fswhDao  = new BfjsFswhDao();
		fswhDao.delTjjl(model.getXn(),model.getXq(),user);
		//初始化竞赛班级
		dao.initJsbjByBj(model,user);
		
		
	}
	
}
