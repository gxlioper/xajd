package xsgzgl.gygl.xyzsgl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XyzsglDao extends SuperDAOImpl<XyzsglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyzsglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyzsglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t2.*,t1.xm,t1.xb, t1.zydm, t1.nj, t1.bjdm,t1.xydm,t1.xymc,t1.bjmc,t1.zymc,t1.sfzh,t1.mz mzmc,t1.zzmmmc,");
		sql.append(" substr(t2.sqsjstart, 0, 4) || '-' || substr(t2.sqsjstart, 5, 2) || '-' ||substr(t2.sqsjstart, 7, 2) ||'  ' ||'至' || ' ' ||substr(t2.sqsjend, 0, 4) || '-' || substr(t2.sqsjend, 5, 2) || '-' ||substr(t2.sqsjend, 7, 2)  as sqsj1 ");
		sql.append("from xg_gygl_xyzsgl t2 join VIEW_XSJBXX t1 on t2.xh = t1.xh)");
		sql.append(" t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XyzsglForm.class);
		super.setKey("sqbh");
		super.setTableName("XG_GYGL_XYZSGL");
		
	}
	
	/**
	 * 获取在外居住原因
	 */
	public List<HashMap<String, String>> getZyjzxx(XyzsglForm model) {
		StringBuffer sql = new StringBuffer();
    	sql.append("select dm,mc from xg_gygl_xyzsgl_zwjzyydmb");
//		sql.append(" where t1.xh=? and t2.yrdwdm=? and t1.zgzt='zg' order by t2.xn ");
//		return dao.getList(sql.toString(), new String[] { model.getXh(), model.getYrdw() }, new String[] { "gwdm", "gwmc" });
		return dao.getList(sql.toString(),new String[] {},new String[] { "dm", "mc" });
	}
	
	/**
	 * 判断该学生的在校住宿结果是否存在
	 */
	 
		public boolean checkExistForSave(XyzsglForm model) {
			boolean flag = false;
			StringBuilder sql = new StringBuilder();
			sql.append("select t.sqbh from xg_gygl_xyzsgl t where t.xh = ? and t.xn = ?  ");
			String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),Base.currXn}, "sqbh");
			if (num != null && ! num.equals("") ){
				flag = true;
			}
			return flag;
		}
		/**
		 * 
		 *保存增加结果
		 */
		public boolean saveZsjg(XyzsglForm model, User user) throws Exception {
			
			StringBuffer sql = new StringBuffer();
	    	sql.append("insert into xg_gygl_xyzsgl(sqbh,sqsjstart,sqsjend,xxdz,lxdh,parentslxdy,xl,zwjzyy,xh,xn,sqsj,bz) values(xyzsgl_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)");
	    	boolean flag = dao.runUpdate2(sql.toString(),new String[]{model.getSqsjstart(),model.getSqsjend(),model.getXxdz(),model.getLxdh(),model.getParentslxdy(),model.getXl(),model.getZwjzyy(),model.getXh(),model.getXn(),model.getSqsj(),model.getBz()},"xg_gygl_xyzsgl",user);
			return flag;
		}
		
		/*
		 * 导出申请表的时候获取学生基本信息以及住宿申请信息
		 */
		public HashMap<String, String> getXyzsxxMap(XyzsglForm model, User user) throws Exception {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (");
			sql.append("select t2.*,t1.xm,t1.xb, t1.zydm, t1.nj, t1.bjdm,t1.xydm,t1.xymc,t1.bjmc,t1.zymc,t1.sfzh,t3.xlmc,n.bzrxm,substr(t2.sqsj,0,10) sqrq,t4.mc yymc,");
			sql.append(" substr(t2.sqsjstart, 0, 4) || '年' || substr(t2.sqsjstart, 5, 2) || '月' ||substr(t2.sqsjstart, 7, 2) || '日' ||'到'||substr(t2.sqsjend, 0, 4) || '年' || substr(t2.sqsjend, 5, 2) || '月' ||substr(t2.sqsjend, 7, 2) || '日' as sqsj1 ");
			sql.append("from (select a.*,b.splc from XG_GYGL_XYZSGL a left join xg_gygl_xyzsgl_sqb b on a.sqbh=b.sqbh) t2 join VIEW_XSJBXX t1 on t2.xh = t1.xh join xldmb t3 on t2.xl = t3.xldm ");
			sql.append("left join (select a.bjdm, WM_CONCAT(b.xm) bzrxm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) n on t1.bjdm=n.bjdm ");
			sql.append("left join xg_gygl_xyzsgl_zwjzyydmb t4 on t2.zwjzyy=t4.dm )");
			sql.append(" t where sqbh = ?  ");
			return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
		}
		
		/*
		 * 导出申请表的时候获取学生基本信息以及住宿申请信息
		 */
		public HashMap<String, String> getXyzsxxMap1(XyzsglForm model, User user) throws Exception {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (");
			sql.append("select t2.*,t1.xm,t1.xb, t1.zydm, t1.nj, t1.bjdm,t1.xydm,t1.xymc,t1.bjmc,t1.zymc,t1.sfzh,t3.xlmc,n.bzrxm,substr(t2.sqsj,0,10) sqrq,t4.mc yymc, ");
			sql.append(" substr(t2.sqsjstart, 0, 4) || '年' || substr(t2.sqsjstart, 5, 2) || '月' ||substr(t2.sqsjstart, 7, 2) || '日' ||'  至    '||substr(t2.sqsjend, 0, 4) || '年' || substr(t2.sqsjend, 5, 2) || '月' ||substr(t2.sqsjend, 7, 2) || '日' as sqsj1 ");
			sql.append("from xg_gygl_xyzsgl_sqb t2 join VIEW_XSJBXX t1 on t2.xh = t1.xh join xldmb t3 on t2.xl = t3.xldm ");
			sql.append( "left join (select a.bjdm, WM_CONCAT(b.xm) bzrxm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) n on t1.bjdm=n.bjdm ");
			sql.append("left join xg_gygl_xyzsgl_zwjzyydmb t4 on t2.zwjzyy=t4.dm )");
			sql.append(" t where sqbh = ?  ");
			return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
		}
		
		/**
		 *获取学历代码和学历名称map,给下拉框使用
	     *
		 */
		public List<HashMap<String, String>> getXl(XyzsglForm model) {
			StringBuffer sql = new StringBuffer();
	    	sql.append("select xldm,xlmc from xldmb");
			return dao.getList(sql.toString(),new String[] {},new String[] { "xldm", "xlmc" });
		}
		
		/**
		 * 查看时显示学历名称
		 */
		public HashMap<String, String> getXlCk(XyzsglForm model) {
			StringBuffer sql = new StringBuffer();
	    	sql.append("select t1.xldm,t1.xlmc from  xg_gygl_xyzsgl t");
	    	sql.append(" join xldmb t1 on t.xl = t1.xldm  where t.sqbh = ?");
	    	return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
		}
		
		/**
		 *查看时显示申请校外住宿的原因
		 */
		public HashMap<String, String> getXyZsyy(XyzsglForm model) {
			StringBuffer sql = new StringBuffer();
	    	sql.append("select t1.dm,t1.mc from  xg_gygl_xyzsgl t");
	    	sql.append(" join xg_gygl_xyzsgl_zwjzyydmb t1 on t.zwjzyy = t1.dm  where t.sqbh = ?");
	    	return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
		}
		
		public boolean delZsjgById(String id)throws Exception{
			StringBuffer sql = new StringBuffer();
			sql.append("delete from xg_gygl_xyzsgl where sqbh=? ");
			return dao.runUpdate(sql.toString(),new String[]{id});
		}
		
		public String checkExistForSave2(XyzsglForm model) {
			String flag = "";
			StringBuilder sql = new StringBuilder();
			sql.append("select t.sqbh from xg_gygl_xyzsgl t where t.xh = ? and t.xn = ?  ");
			String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),Base.currXn}, "sqbh");
			if (num != null && ! num.equals("") ){
				flag = num;
			}
			return flag;
		}
}
