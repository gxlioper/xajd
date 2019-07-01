/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 下午02:37:52 
 */  
package com.zfsoft.xgxt.jjgl.jjnj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import org.apache.commons.lang.StringUtils;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/** 
 * @类功能描述: 家教年级
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-18 下午02:37:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjnjDao extends SuperDAOImpl<JjnjForm> {


	@Override
	protected void setTableInfo() {
		super.setTableName("XSGGFW_JJFW_JJNJDMB");
		super.setKey("jjnjdm");
		super.setClass(JjnjForm.class);
	}


	@Override
	public List<HashMap<String, String>> getPageList(JjnjForm t)
			throws Exception {
		
		String sql = "select * from XSGGFW_JJFW_JJNJDMB order by jjnjdm";
		return super.getPageList(t, sql, null);
	}


	//获取主键
	public String getPrimayKey(){
		String sql = "select  max(to_number(jjnjdm)) as maxjjnjdm from XSGGFW_JJFW_JJNJDMB";
		String maxPrimaryKey =  dao.getOneRs(sql, new String[]{}, "maxjjnjdm");
		
		if(StringUtils.isBlank(maxPrimaryKey)){
			return "1";
		}else{
			return Integer.valueOf(maxPrimaryKey) + 1 + "";
		}
	}
	
	//检查数据关联
	public String checkReference(String jjnjdm){
		String sql = "select count(1) rs from XSGGFW_JJFW_JZJJXQSQB a where a.jjnjdm = ?";
		return dao.getOneRs(sql, new String[]{jjnjdm}, "rs");
	} 
	
	@Override
	public List<HashMap<String, String>> getPageList(JjnjForm t, User user)
			throws Exception {
		return null;
	}

    public List<HashMap<String,String>> getJjnjList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct jjnj from XSGGFW_JJFW_JZJJXQSQB");
		return dao.getListNotOut(sql.toString(),new String[]{});
    }

}
