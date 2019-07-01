/**
 * @部门:学工产品事业部
 * @日期：2013-10-25 上午09:11:33 
 */  
package com.zfsoft.xgxt.wjcf.cfyydmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.wjcf.cfsb.CfsbglForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分原因代码维护) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-25 上午09:07:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfyydmDao extends SuperDAOImpl<CfyydmForm> {



	public List<HashMap<String, String>> getPageList(CfyydmForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select * from xg_wjcf_cfyydmb where 1=1");
		
		if (!StringUtil.isNull(model.getCfyymc())){
			params.add(model.getCfyymc());
			sql.append(" and cfyymc like '%'||?||'%'");
		}

		
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfyydmForm t, User user)
			throws Exception {
		return null;
	}



	@Override
	protected void setTableInfo() {
		this.setKey("cfyydm");
		this.setTableName("xg_wjcf_cfyydmb");
		this.setClass(CfyydmForm.class);

	}


	/** 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 违纪管理模块
	 * @类功能描述: (处分原因名称是否存在) 
	 * @作者： 陈敏杰[工号:913]
	 * @时间： 2013-10-24 上午10:52:35 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明  
	 */
	public boolean checkIsExist(CfyydmForm myForm) {
		String sql="select cfyymc from xg_wjcf_cfyydmb where cfyymc=?";
		String[] inputVal=null;
		if(myForm.getCfyydm()!=null&&!myForm.getCfyydm().equalsIgnoreCase("")){
			sql+=" and cfyydm<>?";
			inputVal=new String[2];
			inputVal[0]=myForm.getCfyymc();
			inputVal[1]=myForm.getCfyydm();
		}else{
			inputVal=new String[1];
			inputVal[0]=myForm.getCfyymc();
		}
		String cfyymc=dao.getOneRs(sql, inputVal, "cfyymc");
		return cfyymc!=null&&!"".equalsIgnoreCase(cfyymc);
	}
	
	/**
	 * 
	 * @描述:(处分原因代码删除)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-24 下午01:33:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@Override
	public int runDelete(String[] values) throws Exception {
		StringBuilder sql=new StringBuilder();
		sql.append("delete xg_wjcf_cfyydmb a  where (");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("cfyydm=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")and not exists(select 1 from xg_wjcf_wjcfsbb t where a.cfyydm=t.cfyydm)");
		return dao.runDelete(sql.toString(), values);
	}

}
