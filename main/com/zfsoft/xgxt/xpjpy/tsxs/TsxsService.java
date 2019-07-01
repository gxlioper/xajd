/**
 * @部门:学工产品事业部
 * @日期：2013-8-2 上午10:14:04 
 */
package com.zfsoft.xgxt.xpjpy.tsxs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.ezmorph.bean.MorphDynaBean;
import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.JsonUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优管理模块
 * @类功能描述: 特殊学生维护
 * @作者：CQ [工号：785]
 * @时间： 2013-8-2 上午10:14:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TsxsService extends SuperServiceImpl<TsxsModel, TsxsDao> {

	private TsxsDao dao = new TsxsDao();

	public TsxsService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:特殊学生未添加列表
	 * @作者：tgj[工号：1075]
	 * @日期：2017-7-11 上午08:45:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTsxsDtjPageList(TsxsModel model, User user)
	throws Exception {
		return dao.getTsxsDtjPageList(model, user);
	}
	
	/**
	 * 
	 * @描述:获取特殊学生类型代码、名称
	 * @作者：cq [工号：785]
	 * @日期：2013-8-2 下午04:08:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTslxList() {
		return getTslxList(null);
	}
	
	/**
	 * 
	 * @描述:获取特殊学生类型代码、名称,  lxsx,1:条件，2：范围
	 * @作者：cq [工号：785]
	 * @日期：2013-8-2 下午04:08:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTslxList(String lxsx) {
		List<HashMap<String, String>> list = dao.getTslx(lxsx);
		return list;
	}

	/**
	 * 
	 * @描述:保存特殊学生
	 * @作者：ligl
	 * @日期：2013-8-15 下午04:10:59
	 * @修改记录:
	 * @param model
	 * @param user
	 * @return
	 * @throws SQLException boolean 返回类型
	 * @throws
	 */
	public boolean saveTsxs(TsxsModel model, User user) throws Exception {

		boolean isSuccess = true;// 不对操作失败进行处理
		String tsxsxh = model.getTsxsxh();

		if (!StringUtil.isNull(tsxsxh)) {
			String[] tsxsxhs = tsxsxh.replaceAll("\r", " ").replaceAll("\n",
					" ").split(" ");
			List<String> tsxsxhList = new ArrayList<String>();
			for (int i = 0; i < tsxsxhs.length; i++) {
				if (!StringUtil.isNull(tsxsxhs[i])) {
					tsxsxhList.add(tsxsxhs[i].trim());
				}
				if (i > 0 && i % 800 == 0) {// 每500条做一次操作，
					isSuccess = saveTsxsForParams(model, user, tsxsxhList);
					tsxsxhList = new ArrayList<String>();// 重置
				}
			}
			isSuccess = saveTsxsForParams(model, user, tsxsxhList);

		}
		return isSuccess;
	}

	/*
	 * 根据参数，对数据进行处理
	 */
	private boolean saveTsxsForParams(TsxsModel model, User user,
			List<String> tsxsxhList) throws Exception {
		String xh = null;
		String[] param = null;
		boolean isSuccess = true;
		String lxdm = model.getLxdm();
		List<String[]> params = new ArrayList<String[]>();

		String userName = user.getUserName();

		List<HashMap<String, String>> tsxsInfoList = dao.sctsxs(tsxsxhList,
				model);
		if (tsxsInfoList != null && tsxsInfoList.size() > 0) {
			for (HashMap<String, String> map : tsxsInfoList) {
				xh = map.get("xh");
				param = new String[] { xh, model.getXn(), model.getXq(), lxdm,
						userName };
				params.add(param);
			}
			if (params.size() > 0) {
				isSuccess = dao.saveTsxs(params);// 批量插入
			}
		}

		return isSuccess;
	}

	/**
	 * @描述:删除特殊人员
	 * @作者：cq [工号：785]
	 * @日期：2013-8-5 下午07:02:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean delTsxs(String values) {

		if (values == null || "".equalsIgnoreCase(values)) {
			logger.error("未选择勾选特殊人员信息！");
			throw new NullPointerException();
		}

		boolean delTsxs = false;

		// 删除特殊人员信息
		delTsxs = dao.delTsxs(values);

		return delTsxs;
	}
	
	/**
	 * 
	 * @描述:根据学年学期类型，批量删除
	 * @作者：ligl
	 * @日期：2013-8-16 上午10:42:37
	 * @修改记录: 
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean tsxsScForLx(String json)  throws Exception {

		if (json == null || json.trim().equals("")) {
			logger.error("未勾选数据！");
			throw new NullPointerException();
		}

		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
	
		boolean isSuccess = false;
		List list = JsonUtil.jsonToList(json);
		String xn = null;
		String xq = null;
		String lxdm = null;
		for (Object object : list) {
			MorphDynaBean bean = (MorphDynaBean)object;
			xn = (String)bean.get("xn");
			xq = (String)bean.get("xq");
			lxdm = (String)bean.get("lxdm");
			param = new String[] { xn,xq,lxdm};
			params.add(param);
		}
		
		if (params.size() > 0) {
			isSuccess = dao.tsxsScForLx(params);// 
		}
		return isSuccess;

	}
	
	/**
	 * 
	 * @描述:查询条件，学年
	 * @作者：ligl
	 * @日期：2013-8-16 下午03:44:06
	 * @修改记录: 
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXnList() throws Exception{
		List<HashMap<String, String>> list = dao.getXnList();
		return list;
	}
	
	/**
	 * 
	 * @描述:得到学期代码
	 * @作者：ligl
	 * @日期：2013-8-17 上午11:22:32
	 * @修改记录: 
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXqmc(String xq){
		String xqmc = null;
		List<HashMap<String, String>> xqList = Base.getXqList();
		for (HashMap<String, String> map : xqList) {
			if (xq.equals(map.get("xqdm"))) {
				xqmc = map.get("xqmc");
				break;
			}
		}
		return xqmc;
	}
	
	/**
	 * 
	 * @描述:得到类型名称
	 * @作者：ligl
	 * @日期：2013-8-17 上午11:23:56
	 * @修改记录: 
	 * @param lxdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getLxmc(String lxdm) throws Exception{
		return dao.getNameById(lxdm);
	}
	
	/**
	 * @throws SQLException  
	 * @描述:批量保存特殊学生(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-26 上午09:30:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean PlbcTsxs(String ids,String xn,String xq,String lxdm,String userName) throws SQLException{
		return dao.plbcTsxs(ids, xn, xq, lxdm, userName);
	}
}
