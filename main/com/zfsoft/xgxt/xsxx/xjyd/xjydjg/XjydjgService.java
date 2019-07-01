package com.zfsoft.xgxt.xsxx.xjyd.xjydjg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;
import com.zfsoft.xgxt.xsxx.xjyd.XjydService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述:学籍异动审核
 * @作者： qilm
 * @时间：2013-9-29
 * @版本： V1.0
 */
public class XjydjgService extends SuperServiceImpl<XjydjgForm, XjydjgDAO> {

	/**
	 * 东北石油大学特殊班级调整的异动类别
	 */
	public static final String DBSYDX_TSBJTZ      = "99";
	private XjydjgDAO dao = new XjydjgDAO();
	
	public XjydjgService() {
		super.setDao(dao);
		
	}

	/** 
	 * @描述:学生最近的一次学籍异动信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 上午10:04:32
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXsydInfo(XjydjgForm myForm) {
		return dao.getXsydInfo(myForm);
	}
	
	/**
	 * 
	 * @描述:获取map
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-31 上午08:54:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getModelInfoMap(String keyValue) throws Exception{
		return dao.getModelInfoMap(keyValue);
	}
	/**
	 * 根据学号、异动文号查找id
	 */
	public String queryExistId(XjydjgForm myForm, String type) throws Exception{
		return dao.queryExistId(myForm, type);
	}
	
	/**
	 * @throws Exception  
	 * @描述:学生最近的更多学籍异动信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 上午10:04:32
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsydList(XjydjgForm myForm) throws Exception {
		return dao.getXsydList(myForm);
	}

	/**
	 * @描述: 学籍异动批量处理
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午02:36:14
	 * @param myForm
	 * @param user
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public boolean xjydPlcl(XjydjgForm myForm, User user) {

		// 没有指定选择学生则返回
		if (StringUtils.isNull(myForm.getXzxsKey())) {
			return false;
		}

		try {
			// 取得学生列表
			XsxxService xsService = new XsxxService();
			List<HashMap<String, String>> xsList;
			xsList = xsService.getSelectedStudents(myForm.getXzxsKey());

			String xh = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());

			for (HashMap<String, String> xs : xsList) {
				xh = xs.get("xh");

				String guid = UniqID.getInstance().getUniqIDHash();
				myForm.setXjydjgid(guid);
				myForm.setXh(xh);
				myForm.setJrsj(date);
				myForm.setSqr(user.getUserName());

				// 加载学生基本信息
				HashMap<String, String> xsjbxx = xsService.getXsjbxxMore(xh);
				myForm.setYdqnj(xsjbxx.get("nj"));
				myForm.setYdqxydm(xsjbxx.get("xydm"));
				myForm.setYdqzydm(xsjbxx.get("zydm"));
				myForm.setYdqbjdm(xsjbxx.get("bjdm"));
				myForm.setYdqxjlb(xsjbxx.get("xjlbdm"));//学籍类别代码
				myForm.setYdqxjlbmc(xsjbxx.get("xjlb"));//学籍类别
				myForm.setYdqsfyxjmc(xsjbxx.get("xjztm"));//是否有学籍
				myForm.setYdqsfzxmc(xsjbxx.get("sfzx"));//是否在校

				// 数据来源
				myForm.setSjly(Constants.SJLY_JGK);

				// 学校代码
				String xxdm = Base.xxdm;	
				
				// 東北石油大學特殊判斷（是否為特殊異動类别调整【只调班级不调专业】）
				if(DBSYDX_TSBJTZ.equals(myForm.getYdlbdm()) &&  Globals.XXDM_DBSYDX.equals(xxdm)){
					
					// 专业学院不变
					myForm.setYdhxydm(xsjbxx.get("xydm"));
					myForm.setYdhzydm(xsjbxx.get("zydm"));
					 
				}
				String xjydjgidTemp = queryExistId(myForm, "add");
				if (!"".equals(xjydjgidTemp)){
					myForm.setXjydjgid(xjydjgidTemp);
					dao.runUpdate(myForm);
				}else{
					dao.runInsert(myForm);
				}
				updateXsxx(myForm);
			}
			// 删除临时库
			xsService.runDelSelectAll(myForm.getXzxsKey());
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @描述:删除该申请对应的学籍异动结果库
	 * @作者：qilm
	 * @日期：2013-10-10
	 * @param sjydsqid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int runDeleteYdjg(String sjydsqid) throws Exception{
		return dao.runDeleteYdjg(sjydsqid);
	}
	
	/**
	 * 
	 * @描述: 更新学生信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-12 下午03:11:21
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXsxx(XjydjgForm myForm)throws Exception {
		
		XjydService xjyd = new  XjydService();
		XjydForm model = new XjydForm();
		
		model.setXjlbdm(myForm.getYdlbdm());
		model = xjyd.getModelShpz(model);
		// 学籍类别名称
		String xjlbmc = model.getXjlbmc();
		
		//是否有学籍(有学籍/无学籍)
		String xjztm = model.getSfyxjmc();
		
		//是否在校(是/否)
		String sfzx = StringUtils.isNotNull(model.getSfzxmc())?model.getSfzxmc():"在校";
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			return dao.updateXsxxHZSF(model.getXjlbdm(),xjlbmc, xjztm, sfzx, myForm.getSfsfs(), myForm.getDqztdm(), myForm.getYdhnj(),myForm.getYdhxydm(), myForm.getYdhzydm(),myForm.getYdhbjdm(),myForm.getXh());
		}else {
			return dao.updateXsxx(model.getXjlbdm(),xjlbmc, xjztm, sfzx, myForm.getYdhnj(),myForm.getYdhxydm(), myForm.getYdhzydm(),myForm.getYdhbjdm(),myForm.getXh());
		}
	}

	/**
	 * 
	 * @描述: 取得学籍异动结果信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-12 下午04:15:46
	 * @param xjydsqid
	 * @return
	 * @throws Exception
	 * XjydjgForm 返回类型 
	 * @throws
	 */
	public XjydjgForm getModelBySqid(String xjydsqid) throws Exception{
		return dao.getModelBySqid(xjydsqid);
	}
	
	
	/**
	 * 
	 * @描述: 回滚学生信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-12 下午03:11:21
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean rollBackXsxx(String values)throws Exception {
		
		boolean bolFlg = false;
		for(String v :values.split(",")){
			XjydjgForm myForm = dao.getModel(v);

			bolFlg = dao.updateXsxx(myForm.getYdqxjlb(), myForm.getYdqxjlbmc(), myForm.getYdqsfyxjmc(), myForm.getYdqsfzxmc(),
					myForm.getYdqnj(),myForm.getYdqxydm(), myForm.getYdqzydm(),myForm.getYdqbjdm(), myForm.getXh());
			if(!bolFlg) return bolFlg;
		}
		return bolFlg;
	}
	/**
	 * 
	 * @描述: 取出学籍异动结果列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-27 下午04:10:56
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<String[]> getXsydListByXh(String xh)
			throws Exception {
		return dao.getXsydListByXh(xh);
	}
	
	/**
	 * 
	 * @描述: 取出学籍异动结果列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-27 下午04:10:56
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsydList(String xh)
			throws Exception {
		return dao.getXsydList(xh);
	}

	/**
	 * @描述:在校生信息学籍卡打印获取学籍异动信息（华东交通大学理工学院）
	 * @作者：lgx [工号：1553]
	 * @日期：2018/8/22 13:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh, n]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String, String>> getXsydListForWord(String xh,int n)
			throws Exception {
		List<HashMap<String, String>> list = dao.getXsydListForWord(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
}
