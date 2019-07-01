/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.xmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.xsxx.xsgl.XsxxDao;
import xgxt.action.Base;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhService extends SuperServiceImpl<XmwhForm, XmwhDao> {

	private XmwhDao dao = new XmwhDao();
	private XsxxDao xsxxDao = new XsxxDao();

	public XmwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:获取所有项目，不包含当前项目
	 * @作者：Administrator
	 * @日期：2013-4-24 下午04:12:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getOthers(String xmdm) throws Exception{
		return dao.getOthers(xmdm);
	}

	
	/**
	 * 
	 * @描述:判断重复数据，以名称为准
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRepeat(XmwhForm model) throws Exception {
		
		//CsszModel csszModel = new CsszDao().getModel();
		//String currXn = csszModel.getXn();// 当前学年
		//String currXq = csszModel.getXq();// 当年学期
		String currXn = Base.currXn;// 当前学年
		String currXq = Base.currXq;// 当年学期
		return dao.isRepeat(model,currXn,currXq);
	}
	
	/**
	 * 
	 * @描述:判断关联性，数据可否处理
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(XmwhForm model) throws Exception {
		XmwhForm modelOld = dao.getModel(model);
		boolean flag = false;
		if(!model.getXmmc().trim().equals(modelOld.getXmmc().trim()) || !model.getLbdm().equals(modelOld.getLbdm())){
			flag = dao.isRalate(model);
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:判断关联性，数据可否处理
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		return dao.isRalate(values);
	}
	
	/**
	 * 
	 * @删除关联表
	 * @作者：ligl
	 * @日期：2013-4-27 下午05:08:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delRalate(String values) throws Exception {
		return dao.delRalate(values);
	}	
	
	/**
	 * 
	 * @描述:根据项目代码查询记录
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getDataById(String xmdm) throws Exception {
		return dao.getDataById(xmdm);
	}
	
	/**
	 * 
	 * @描述:根据项目名称查询单条记录
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String,String>  getDataByName(String xmmc, String xn, String xq) throws Exception {
		return dao.getDataByName(xmmc,xn,xq);
	}
	
	/**
	 * 
	 * @描述:根据项目代码查询项目名称
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		return dao.getNameById(xmdm);
	}
	
		
	/**
	 * 
	 * @描述:根据项目名称查询配置的报表代码
	 * @作者：honglin
	 * @日期：2013-5-4
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXszzdm (String xmmc, String xn, String xq){		
		if (StringUtil.isNull(xmmc)){
			logger.error("项目名称不能为空！");
			throw new NullPointerException();
		}		
		return dao.getXszzdm(xmmc.trim(),xn,xq);
	}
	
	
	/**
	 * 
	 * @描述:通过项目代码获取已经设置的年级,年级以逗号分割
	 * @作者：ligl
	 * @日期：2013-7-5 上午09:14:42
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getRskznj(String xmdm) throws Exception {
		return dao.getRskznj(xmdm);
	}
	
	/*
	 * 学年学期格式化，返回 2012-2013 春
	 */
	private String xnXqGshMc(String xn, String xq) throws Exception {
		String xqmc = null;
		List<HashMap<String, String>> xqList = Base.getXqList();
		for (HashMap<String, String> map : xqList) {
			if (xq != null && xq.equals(map.get("xqdm"))) {
				xqmc = map.get("xqmc");
			}
		}
		if (xqmc == null) {
			xqmc = xq;
		}
		return xn + " " + xqmc;
	}
	
	/*
	 * 学年学期格式化，返回 2012-2013;01
	 */
	private String xnXqGshDm(String xn, String xq) throws Exception {
		return xn + ";" + xq;
	}
	
	
	/**
	 * 
	 * @描述:获取项目维护当中的周期List
	 * @作者：程强 [工号：785]
	 * @日期：2014-1-21 上午11:41:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmzqList(String Type) throws Exception {
		
		List<HashMap<String, String>> xmfzList = new ArrayList<HashMap<String, String>>();
		
		List<HashMap<String, String>> xnxqList = dao.getXmzqList();
		String xn = null;
		String xq = null;
		HashMap<String, String> xmfzMap = null;
		if (xnxqList != null) {
			for (HashMap<String, String> map : xnxqList) {
				xn = map.get("sqxn");
				xq = map.get("sqxq");
				if (Type.equals("Query")||(!xn.equals(Base.currXn) || !xq.equals(Base.currXq))) {
					xmfzMap = new HashMap<String, String>();
					xmfzMap.put("zqdm", xnXqGshDm(xn, xq));
					xmfzMap.put("zqmc", xnXqGshMc(xn, xq));
					xmfzList.add(xmfzMap);
				}
			}
		}
		return xmfzList;
	}

	/**
	 * 
	 * @描述:资助项目复制
	 * @作者：cq [工号：785]
	 * @日期：2014-1-21 下午04:22:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxfznd
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runXmfz(String xmfznd) throws Exception {
		boolean flag = false;

		String currXn = Base.currXn; //当前学年
		String currXq = Base.currXq; //当前学期

		String fzXn = xmfznd.split(";")[0];
		String fzXq = xmfznd.split(";")[1];
		List<HashMap<String, String>> xmfzList = dao.getXmfz(fzXn, fzXq,
				currXn, currXq);
		if (xmfzList != null && xmfzList.size() > 0) {
			flag = dao.saveData(xmfzList, currXn, currXq);
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @描述: 查询在同一个项目组下的其它项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-21 下午03:53:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSameGroupXmList(String xmdm){
		
		return dao.getSameGroupXmList(xmdm);
	}
	
	
	/**
	 * 
	 * @描述: 保存经费设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-22 上午09:22:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param groupXmdm
	 * @param xydm
	 * @param je
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savJfsz(String xmdm,String[] groupXmdm , String[] xydm,String[] je) throws Exception{
		
		if (je == null || je.length == 0){
			return false;
		}
		
		/*先做删除操作*/
		dao.delXyjf(xmdm);
		dao.delXmfz(xmdm);
		
		/*1、划分组**/
		String uuid = UniqID.getInstance().getUniqIDHash();//组ID
		List<String[]> params = new ArrayList<String[]>();
		
		if (groupXmdm != null && groupXmdm.length > 0){
			for (String str : groupXmdm){
				String[] param = new String[]{uuid,str};
				params.add(param);
			}
		} 
		
		String[] param = new String[]{uuid,xmdm};
		params.add(param);
		
		boolean result = true;

		/*2、按组存各学院经费额度**/
		List<String[]> jfList = new ArrayList<String[]>();
		
		for (int i = 0 , j = je.length ; i < j ; i++){
			
			if (!StringUtil.isNull(je[i])){
				String[] xyjf = new String[]{uuid,xydm[i],je[i]};
				jfList.add(xyjf);
			}
		}
		
		//学院经费设置有
		if(jfList.size() > 0){
			
			//经费项目分组保存
			result = dao.saveXmfz(params);
			
			if (result){
				//项目经费保存
				result = dao.saveXyjf(jfList);
			}
		}
		
		
		return result;
	}
	
	
	public Map<String,String> getXyjf(String xmdm){
		
		List<HashMap<String,String>> xyjfList = dao.getXyjfList(xmdm);
		
		Map<String,String> result = new HashMap<String, String>();
		
		for (HashMap<String,String> map : xyjfList){
			result.put(map.get("xydm"), map.get("je"));
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:获取项目类别
	 * @作者：Huang Chenji
	 * @日期：2015-10-27 上午11:26:33
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		return dao.getXmlb();
	}
	
	/**
	 * 浙大个性化功能
	 */
	public HashMap<String, String> showXmxx_10335(String xmdm) throws Exception {
		return dao.showXmxx_10335(xmdm);
	}

	public List<HashMap<String,String>> getPyccList(){
		return xsxxDao.getPyccList();
	}
}
