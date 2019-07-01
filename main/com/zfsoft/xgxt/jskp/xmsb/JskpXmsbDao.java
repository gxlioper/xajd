package com.zfsoft.xgxt.jskp.xmsb;



import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jskp.sbjg.JskpXmsbjgForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 纪实考评
 * @作者： xiaxia[工号:1104]
 * @时间： 2017-7-5 下午04:44:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JskpXmsbDao extends SuperDAOImpl<JskpXmsbForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		if(t.getSbzt().equals("1")){//已申报
			sql.append(" select a.*,d.xm fzrxm,e.bmmc zdbmmc,decode(b.xmdl,'gdx','固定项','自立项') xmdlmc,b.xmmc,c.xmlbmc from xg_jskp_xmsbb a left join xg_jskp_xmjgb b on a.xmid=b.xmid left join xg_jskp_xmlbb c on b.xmlb=c.xmlbdm ");
			sql.append(" left join yhb d");
			sql.append(" on b.fzr = d.yhm");
			sql.append(" left join zxbz_xxbmdm e on b.zdbm=e.bmdm");
			sql.append(" where  a.xh='"+user.getUserName()+"' and a.shzt != '0' ");
		}else{
			sql.append(" select * from (select a.*, d.xm fzrxm,e.bmmc zdbmmc,decode(a.xmdl,'gdx','固定项','自立项') xmdlmc, c.xmlbmc,");
			sql.append(" case when a.kgzt = 1 and sysdate between to_date(nvl(a.sbkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
			sql.append(" and to_date(nvl(a.sbjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end sbkg ");
			sql.append(" from xg_jskp_xmjgb a  left join xg_jskp_xmlbb c on a.xmlb=c.xmlbdm " );
			sql.append(" left join yhb d");
			sql.append(" on a.fzr = d.yhm");
			sql.append(" left join zxbz_xxbmdm e on a.zdbm=e.bmdm");
			sql.append(" where a.xmdl='gdx') where sbkg = 'true' ");
		}
		sql.append(")t where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:判断是否存在填写记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午04:29:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public String xmsbCheck(JskpXmsbForm model,User user) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select case when jgsbzq='xn' and years>0 then '0' when jgsbzq='month' and mons>0 then '0' ");
		sql.append(" when jgsbzq='day' and days>0 then '0' else '1' end jgzq   from (select a.*,b.jgsbzq, ");
		sql.append(" months_between(to_date(substr(a.sbsj,1,10),'yyyy-mm-dd'),to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'))/12 years,"); 
		sql.append(" months_between(to_date(substr(a.sbsj,1,10),'yyyy-mm-dd'),to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')) mons,");
		sql.append(" to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')-to_date(substr(a.sbsj,1,10),'yyyy-mm-dd') days,");
        sql.append(" rownum rn from xg_jskp_xmsbb a left join xg_jskp_xmjgb b ");
        sql.append(" on a.xmid=b.xmid where a.xh =? and a.xmid=? order by sbsj desc) where rn=1");
        return dao.getOneRs(sql.toString(), new String[]{user.getUserName(),model.getXmid()}, "jgzq");
	}

	public JskpXmsbForm getModel(JskpXmsbForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.xmmc  from xg_jskp_xmsbb t1 left join xg_jskp_xmjgb t2 on t1.xmid = t2.xmid");
		sql.append(" where t1.sqid=? ");
		return super.getModel(sql.toString(),new String[]{model.getSqid()});
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(JskpXmsbForm.class);
		super.setKey("sqid");
		super.setTableName("xg_jskp_xmsbb");
		
	}
	
	/**
	 * @描述: 当参数设置为0时，审核列表学号查看（xhLink）
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-11-22 下午03:03:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXxck (String id) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select xmmc,lxsj,lxly,filepath fjid,sqid from xg_jskp_xmsqb where sqid = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{id});
	}
	
}
