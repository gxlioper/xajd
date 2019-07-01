/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:31:23 
 */  
package com.zfsoft.xgxt.xpjpy.cssz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013版-参数设置
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:31:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszService extends SuperServiceImpl<CsszModel, CsszDao>  implements Constants{

	public static final String CPZ_CSH_NJZY = "1";//参评组初始化-年级+专业
	public static final String CPZ_CSH_BJ = "2";//参评组初始化-班级
	public static final String ZCFLR_WYQ = "1";//综测分录入--无页签
	public static final String ZCFLR_YYQ = "0";//综测分录入--有页签
	public static final String PJFS_XQ = "1";//评奖方式  1:学期	
	public static final String PJFS_XN = "2";//评奖方式  2：学年
	public static final String PJFS_XNXQ = "3";//评奖方式 3：学年学期
	public static final String ZHCP_MRPM = "cpzpm";//综合测评默认排名方式：参评组排名
	private static final String SplitChar = "#";
	public static final String XQKG="on"; //学期开关标识
	
	//是否需要计算学年综测
	public static final boolean getZczq(){
		
		CsszService cs=new CsszService();
		String zczq = cs.getCsz("zczq");
		
		if(!StringUtils.isBlank(zczq)&&!CsszService.PJFS_XN.equals(zczq)){
			return true;
		}
		return false;
	}
	
	
	//根据参数设定配置评奖周期
	public static final Map<String, String> getPjzq() throws Exception{
		
		CsszService cs=new CsszService();
		String pjzq = cs.getCsz("pjzq");
		String xn = cs.getModel().getXn();
		String xq = cs.getModel().getXq();
		String cxxq = cs.getModel().getXq();
		
		if(PJFS_XN.equals(pjzq)){
			xq = "on";
			cxxq = "";
		}
		
		Map<String, String> pjzqMap = new HashMap<String, String>();
		
		pjzqMap.put("xn", xn);
		pjzqMap.put("xq", xq);
		//默认高级查询学期
		pjzqMap.put("cxxq", cxxq);
		
		//评奖周期名称
		String xqmc = Base.getXqmcForXqdm(xq);
		pjzqMap.put("pjzqmc", xn+" "+xqmc);
		
		
		return pjzqMap;
	}
	
	
	private CsszDao csszDao = new CsszDao();
	
	public CsszService(){
		dao = csszDao;
	}
	
	
	/**
	 * 
	 * @描述: 处理评奖周期列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 上午09:09:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getPjzqList(){
		
		//系统学期列表
		List<HashMap<String,String>> xqList = Base.getXqList(); 
		//评奖周期列表
		List<HashMap<String,String>> pjzqList = new ArrayList<HashMap<String,String>>();
		
		String zczq = dao.getCsz("zczq");
		
		String xn = null;
		
		for (int i = 0 ; i < 2 ; i++){
			
			if (i == 0){
				xn = String.valueOf(Integer.parseInt(Base.currXn.substring(0,4))-1) + "-" + Base.currXn.substring(0,4);
			} else {
				xn = Base.currXn;
			}
			
			if(!StringUtils.isBlank(zczq) && PJFS_XN.equals(zczq)){
				
				HashMap<String,String> zqMap = new HashMap<String, String>();
				zqMap.put("zqdm", xn+SplitChar+XQKG);
				zqMap.put("zqmc", xn);
				pjzqList.add(zqMap);
				
			} else {
				for (HashMap<String,String> map : xqList){
					HashMap<String,String> zqMap = new HashMap<String, String>();
					zqMap.put("zqdm", xn+SplitChar+map.get("xqdm"));
					zqMap.put("zqmc", xn+" "+map.get("xqmc"));
					pjzqList.add(zqMap);
				}
			}
		}
		
		return pjzqList;
	}
	
	
	
	/**
	 * 
	 * @描述: 周期代码转换周期名称
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 下午04:23:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zqdm
	 * @return
	 * String 返回类型
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
	 * @描述: 查询参数设置信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 上午09:15:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * CsszModel 返回类型
	 * @throws Exception 
	 */
	public CsszModel getModel() throws Exception{
		
		CsszModel model = dao.getModel();
		
		if (model == null){
			//初始化参数设置
			model = new CsszModel();
			model.setPjkg(CLOSE);
			model.setCpzcsh(CPZ_CSH_NJZY);
			model.setZcflrfs(ZCFLR_WYQ);
			model.setPjfs(PJFS_XQ);
			model.setZcmrpm(ZHCP_MRPM);
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			
			boolean result = dao.runInsert(model);
			
			if (result){
				//参评人员初始化
				CpmdDao cpmdDao = new CpmdDao();
				//先删后插
				if(cpmdDao.initDel()){
					cpmdDao.init();
				}
			}
			
		} else {
			String xn = model.getXn();
			String xq = model.getXq();
			
			//拼装评奖周期
			if (!StringUtil.isNull(xn) && !StringUtil.isNull(xq)){
				model.setPjzq(xn+SplitChar+xq);
			}
		}
		
		boolean isOpen = isOpen(model);
		model.setKgzt(String.valueOf(isOpen));
		
		return model;
	}
	
	
	
	/**
	 * 
	 * @描述: 更新参数设置
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 上午11:16:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdKey
	 * @param zdValue
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateCssz(String zdKey,String zdValue) throws Exception{
		
		if ("pjzq".equals(zdKey)){
			String[] zqArray = zdValue.split(SplitChar);
			return dao.updatePjzq(zqArray);
		} else {
			return dao.updateCssz(zdKey, zdValue);
		}
		
	}
	
	
	
	/**
	 * 
	 * @描述: 判断当前评奖开关
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 下午01:48:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean isOpen(CsszModel model) throws Exception{
	
		if (model == null){
			model = dao.getModel();
		}
		
		//无参数设置数据 或者开关状态为关闭 ，为关闭状态
		if (model == null || CLOSE.equals(model.getPjkg())){
			return false;
		}
		
		//格式化开始时间、结束时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;
		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
		
		if (StringUtil.isNull(model.getKssj()) && StringUtil.isNull(model.getJssj())){
			return OPEN.equals(model.getPjkg());
		} else if (!StringUtil.isNull(model.getKssj()) && !StringUtil.isNull(model.getJssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			endTime=sdf.parse(model.getJssj()+":59");
			//当前时间早于结束时间 && 晚于开始时间  && 开关状态为开启
			return now.before(endTime) && now.after(startTime) && OPEN.equals(model.getPjkg());
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
	
}
