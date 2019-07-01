/**
 * @部门:学工产品事业部
 * @日期：2015-07-31 下午02:33:17 
 */
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 社团活动管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-07-31 下午02:33:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmlbglDao extends SuperDAOImpl<XmlbglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XmlbglForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.stlbmc,c.lcxx,'" +GetTime.getTimeByFormat("yyyyMMdd")+"' currDate"+
				" from xg_stgl_xmlb a left join xg_stgl_stlb b on a.stlbdm=b.stlbdm ");
		sql.append("left join (select splc, replace(max(lcxx), ';', '->') lcxx from(select splc,a.mc,to_char(WM_CONCAT(c.mc)over(partition by splc  order by xh))lcxx from xg_xtwh_splc a,");
		sql.append("xg_xtwh_spbz b,xg_xtwh_spgw c where a.id=b.splc and b.spgw=c.id) group by splc)c on a.shlc=c.splc");
		sql.append(" where 1=1");
		if (!StringUtil.isNull(model.getXmlbmc())) {
			params.add(model.getXmlbmc());
			sql.append(" and a.xmlbmc like '%'||?||'%'");
		}
		if(!StringUtil.isNull(model.getStlbmc())){
			params.add(model.getStlbmc());
			sql.append(" and b.stlbmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * 获取项目类别列表
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XmlbglForm model, User user) throws Exception {
		return null;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:增加项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午03:52:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean addXmlb(XmlbglForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from xg_stgl_xmlb where xmlbdm=? or xmlbmc = ?";
		String num = dao.getOneRs(sql, new String[] { model.getXmlbdm(),model.getXmlbmc() }, "num");
		if ("0".equals(num)) {
			flag=runInsert(model);
		} else {
			throw new SystemException(MessageKey.STGL_JCSZ_XMLB_REPEAT);
		}

		return flag;

	}

	/**
	 *删除
	 */
	public int deleteXmlb(String values) throws Exception {
		String sql = "delete from xg_stgl_xmlb where xmlbdm =?";
		return dao.runDelete(sql, new String[] {values});

	}
	

	/**
	 *获取项目类别
	 */
	public String getXmlbmc(String lbdm) throws Exception {
		String sql = "select mc Xmlbmc from xg_stgl_xmlb where dm =?";
		return dao.getOneRs(sql, new String[]{lbdm}, "Xmlbmc");

	}
	
	public boolean isExistsXmData(String lbdm) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from(");
		sql.append(" select xmlbdm from  xg_stgl_jtsq where xmlbdm=?");
		sql.append(" union all select xmlbdm from  xg_stgl_jtjg where xmlbdm=?)");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{lbdm,lbdm}, "num"))>0;
		
	}
	public List<HashMap<String, String>> getXmlbList(String stlbdm){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from( ");
		sql.append(" select a.*,case when a.sqkg = 1 and sysdate between to_date(nvl(a.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(a.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end kgzt from xg_stgl_xmlb a)");
		sql.append(" where kgzt='1' and stlbdm = ? order by xmlbdm asc");
		return dao.getListNotOut(sql.toString(), new String[]{stlbdm});
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_stgl_xmlb");
		super.setKey("xmlbdm");

	}

}
