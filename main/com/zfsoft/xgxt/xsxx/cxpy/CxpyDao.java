/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午04:56:39 
 */  
package com.zfsoft.xgxt.xsxx.cxpy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;


import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(操行评语管理) 
 * @作者： CMJ [工号：913]
 * @时间： 2013-7-24 下午04:56:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxpyDao extends SuperDAOImpl<CxpyForm> {



	@Override
	public List<HashMap<String, String>> getPageList(CxpyForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CxpyForm model, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (select a.sqsj,a.xh,sjly,pk,cxdj cxdjdm,(select cxdjmc from xsxx_cxdjdmb e where a.cxdj=e.cxdjdm) cxdjmc,bzr,cxpy,");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,a.xq,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc from xsxx_cxpyb a left join  view_xsjbxx b on a.xh=b.xh) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}


	public List<HashMap<String, String>> getCxdjList(){
		String sql="select cxdjdm dm,cxdjmc mc from  xsxx_cxdjdmb order by cxdjdm ";
		return dao.getListNotOut(sql, new String[]{});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xsxx_cxpyb");
		super.setKey("pk");
		super.setClass(CxpyForm.class);

	}


	/** 
	 * @描述:TODO(获取学生)
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-25 下午05:03:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsPageList(CxpyForm model, User user) throws Exception{
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select a.* from view_xsjbxx a where not exists (select 1 from xsxx_cxpyb c where a.xh = c.xh and c.xn='"+model.getXn()+"' and c.xq='"+model.getXq()+"' ) ");
		sql.append("order by a.nj, a.xydm, a.zydm, a.bjdm, a.xh) a where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}


	/** 
	 * @描述:TODO(获取选择的学生list)
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-25 下午07:13:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhs
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXzxsList(String xhs) {
		// TODO 自动生成方法存根
		String[] xh=xhs.split(",");
		StringBuilder sql=new StringBuilder();
		sql.append("select * from view_xsjbxx where 1=1 ");
		StringBuilder whereSql=new StringBuilder(" and(");
		for (int i=0;i<xh.length;i++){
			if(i!=xh.length-1){
				whereSql.append(" xh=? or ");
			}else{
				whereSql.append(" xh=? )");
			}
		}
		sql.append(whereSql);
		
		return dao.getList(sql.toString(), xh, new String[]{"xh","xm","xymc","zymc","bjmc"});
	}


	/**
	 * @throws SQLException  
	 * @描述:TODO(保存操行评语信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-26 上午10:48:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public boolean save(CxpyForm model) throws SQLException {
		// TODO 自动生成方法存根
		StringBuilder sql=new StringBuilder();
		sql.append("insert into xsxx_cxpyb(xn,xq,xh,cxdj,cxpy,bzr,sqsj) values(?,?,?,?,?,?,?)");
		List<String[]> params=new ArrayList<String[]>();
		String xhs=model.getXhs();
		String[] xh=xhs.split(",");
		HashSet<String> set=new HashSet<String>();
		for(int i=0;i<xh.length;i++){
			set.add(xh[i]);
		}
		for (String string : set) {
			String[] param=new String[7];
			param[0]=model.getXn();
			param[1]=model.getXq();
			param[2]=string;
			param[3]=model.getCxdjdm();
			param[4]=model.getCxpy();
			param[5]=model.getBzr();
			param[6]=model.getSqsj();
			params.add(param);
		}
		int[] count=dao.runBatch(sql.toString(), params);
		return count.length==set.size();
	}
	
	@Override
	public CxpyForm getModel(CxpyForm model) throws Exception{
		StringBuilder sql=new StringBuilder();
		CxpyForm myForm=new CxpyForm();
		sql.append("select * from (select a.sqsj,a.xh,pk,cxdj,(select cxdjmc from xsxx_cxdjdmb e where a.cxdj=e.cxdjdm) cxdjmc,bzr,cxpy,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc, ");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,xq from xsxx_cxpyb a left join  view_xsjbxx b on a.xh=b.xh) where 1=1 and pk=?");
		HashMap<String, String> map=dao.getMap(sql.toString(), new String[]{model.getPk()}, new String[]{"sqsj","xh","xm","nj","pk","xymc","cxdjmc","xqmc","bzr","zymc","bjmc","xn","xq","cxdj","cxpy"});
		BeanUtils.copyProperties(myForm,map );
		return myForm;
	}

	
	public String getCount(CxpyForm t){
		String[] params = null;
		StringBuilder sql = new StringBuilder("select count(1) c from xsxx_cxpyb where xh = ? and xn=? and xq=?");
		
		if (!StringUtil.isNull(t.getPk())){
			sql.append(" and pk <> ?");
			params = new String[]{t.getXh(),t.getXn(),t.getXq(),t.getPk()};
		} else {
			params = new String[]{t.getXh(),t.getXn(),t.getXq()};
		}
		return dao.getOneRs(sql.toString(), params , "c");
	}
	
	/** 
	 * 
	 * @描述:修改学生操行评语
	 * @作者：陶钢军 [1075]
	 * @日期：2015-4-3 下午02:15:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updCxpy(CxpyForm model) {
		
		//更新SQL
		StringBuilder sql=new StringBuilder();
		sql.append(" update ");
		sql.append(" xsxx_cxpyb ");
		sql.append(" set ");
		sql.append(" cxdj = ? , ");
		sql.append(" cxpy = ? , ");
		sql.append(" bzr = ? , ");
		sql.append(" sjly = ? ");
		sql.append(" where ");
		sql.append(" xh = ?  ");
		sql.append(" and xn=?  ");
		sql.append(" and xq=?  ");

		boolean flag = false;
		try {
			flag = dao.runUpdate(sql.toString(), 
					new String[] {model.getCxdj(),
						model.getCxpy(),
						model.getBzr(),
						model.getSjly(),
						model.getXh(),
						model.getXn(),
						model.getXq()});
			
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public HashMap<String,String> getCxpyByXhXnXq(String xh,String xn,String xq){
		StringBuilder sql=new StringBuilder();
		sql.append("select * from (select a.xh,pk,cxdj,(select cxdjmc from xsxx_cxdjdmb e where " );
		sql.append("a.cxdj=e.cxdjdm) cxdjmc,bzr,cxpy,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc, ");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,xq from xsxx_cxpyb a left join ");
		sql.append("view_xsjbxx b on a.xh=b.xh) where xh=? and xn=? and xq=?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,xn,xq});
	}
	
	public HashMap<String,String> getCxpyByXhXn(String xh,String xn){
		StringBuilder sql=new StringBuilder();
		sql.append("select * from (select a.xh,pk,cxdj,(select cxdjmc from xsxx_cxdjdmb e where " );
		sql.append("a.cxdj=e.cxdjdm) cxdjmc,substr(sqsj,0,4) y,substr(sqsj,6,2) m, substr(sqsj,9,2) d,bzr,cxpy,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc, ");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,xq from xsxx_cxpyb a left join ");
		sql.append("view_xsjbxx b on a.xh=b.xh) where xh=? and xn=?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,xn});
	}
	//徐州医药个性化操行
	public HashMap<String,String> getCxpyForXzyy(String xh,String xn,String xq){
		StringBuilder sql=new StringBuilder();
		sql.append("select * from (select a.xh,pk,cxdj,(select cxdjmc from xsxx_cxdjdmb e where " );
		sql.append("a.cxdj=e.cxdjdm) cxdjmc,substr(sqsj,0,4) y,substr(sqsj,6,2) m, substr(sqsj,9,2) d,bzr,cxpy,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc, ");
		sql.append("xm,xb,nj,xymc,zymc,bjmc,xydm,zydm,bjdm,a.xn,xq from xsxx_cxpyb a left join ");
		sql.append("view_xsjbxx b on a.xh=b.xh) where xh=? and xn=? and xq = ? order by xn ");
		return dao.getMapNotOut(sql.toString(),new String[]{xh,xn,xq});
	}
}
