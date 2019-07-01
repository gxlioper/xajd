/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmlbwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目类别维护
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmlbwhDao extends SuperDAOImpl<XmlbwhForm> {

	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmlbwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select lbdm,lbmc,lbsm");
		sql.append(" from xg_xszz_new_zzxmlbb where 1=1 ");

		if (!StringUtil.isNull(model.getLbmc())) {
			params.add(model.getLbmc());
			sql.append(" and lbmc like '%'||?||'%'");
		}

		return getPageList(model, sql.toString(), params
				.toArray(new String[] {}));
	}

	/**
	 * 
	 * @描述:查询最大的id值
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public int getMaxId() throws Exception {
		String sql = "select nvl(max(lbdm),0) bjdm from xg_xszz_new_zzxmlbb";
		return dao.getOneRsint(sql);
	}

	/**
	 * 
	 * @描述:判断重复数据
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRepeat(XmlbwhForm model) throws Exception {
		boolean flag = false;
		String sql = "select count(*) count from xg_xszz_new_zzxmlbb t ";
		sql += " where t.lbmc=?";
		if(model.getLbdm() != null){
			sql += " and lbdm!='"+model.getLbdm()+"'";
		}
		String[] input = new String[1];
		String lbmc = model.getLbmc();
		if(lbmc!= null){
			lbmc = lbmc.trim();
		}
		input[0] = lbmc;
		String[] output = new String[1];
		output[0] = "count";
		String[] oneRs = dao.getOneRs(sql,input,output);/////此方法异常已被捕获掉。，出错无法处理 。。。。。。。////。。。。。。////
		if(oneRs != null && oneRs.length > 0){
			if(!oneRs[0].equals("0")){
				flag = true;
			}
		}
		return flag;
	}
	
	

	/**
	 * 
	 * @描述:可否修改删除验证
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(XmlbwhForm model) throws Exception {
		boolean flag = false;
		String sql = "select count(*)  from xg_xszz_new_zzxmdmb  ";
		sql += " where lbdm='"+model.getLbdm()+"'";
		int result = dao.getOneRsint(sql);/////此方法异常已被捕获掉，出错无法处理 。。。。。。。////
		if(result > 0){
			flag = true;
		}
		return flag;
	}


	/**
	 * 
	 * @描述:可否修改删除验证
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		boolean flag = false;
		String sql = "select count(*)  from xg_xszz_new_zzxmdmb  ";
		sql += " where lbdm in("+values+")";
		int result = dao.getOneRsint(sql);/////此方法异常已被捕获掉，出错无法处理 。。。。。。。////
		if(result > 0){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XmlbwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 
	 * @描述:获取项目类别代码及名称
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select lbdm,lbmc ");
		sb.append(" from  xg_xszz_new_zzxmlbb ");
		return dao.getListNotOut(sb.toString(), null);
	}

	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmlbb");
		super.setKey("lbdm");
	}

}
