/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午06:11:22 
 */  
package com.zfsoft.xgxt.xsxx.cxdj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(操行评语管理) 
 * @作者： cmj [工号：913]
 * @时间： 2013-7-30 下午06:11:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxdjDao extends SuperDAOImpl<CxdjForm> {

	

	@Override
	protected void setTableInfo() {
		super.setTableName("xsxx_cxdjdmb");
		super.setKey("cxdjdm");

	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxdjForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select * from (select cxdjdm,cxdjmc from xsxx_cxdjdmb order by cxdjdm) where 1=1 ");
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxdjForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/** 
	 * @描述:TODO(操行等级代码是否存在)
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-30 下午06:49:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(CxdjForm model) {
		// TODO 自动生成方法存根
		String sql="select * from xsxx_cxdjdmb where cxdjdm='"+model.getCxdjdm()+"'";
		DAO dao=DAO.getInstance();
		List<HashMap<String, String>> list=dao.getList(sql, new String[]{}, new String[]{"cxdjdm"});
		return list.size()!=0;
	}
	
	@Override
	public int runDelete(String[] values) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("delete from xsxx_cxdjdmb a");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("cxdjdm=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		sql.append(" and not exists(select 1 from xsxx_cxpyb b where a.cxdjdm=b.cxdj)");
		return dao.runDelete(sql.toString(), values);
	}
	//浙江旅游个性化判断
	public boolean isNowUsing(String[] pj){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from ");
		sql.append(" (select pj from xg_xsxx_cxpy_pysb_xs ");
		sql.append(" union all ");
		sql.append(" select pj from xg_xsxx_cxpy_pysb_jg");
		sql.append(" ) t");
		sql.append(" where t.pj in ");
		sql.append(" (");
		for (int i = 0; i < pj.length; i++) {
			sql.append("?");
			if(i!=pj.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		
		String num = dao.getOneRs(sql.toString(), pj, "num");
		if("0".equalsIgnoreCase(num)){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean checkSameNameIsNotExists(String cxdjdm,String cxdjmc){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select count(1) num");
		sql.append(" from xsxx_cxdjdmb");
		sql.append(" where cxdjmc = ?");
		paralist.add(cxdjmc);
		if(StringUtils.isNotNull(cxdjdm)){
			sql.append("  and cxdjdm != ?");
			paralist.add(cxdjdm);
		}
	
		sql.append(" ");
		String num = dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "num");
		if(num.equals("0")){
			return true;
		}else{
			return false;
		}
	}
}
