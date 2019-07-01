package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class KycxxmjgDao extends SuperDAOImpl<KycxxmjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_kycxgl_kycxxmjgb");
		super.setKey("jgid");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KycxxmjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KycxxmjgForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.jgid,t1.sqid,t1.xn,t1.xmmc,t1.zdls,t1.lbdm,t1.xmsqrxm,t1.xmsqsj,t1.xbjf,t1.czr,t1.czsj,t1.sjly, ");
		sql.append(" c.xmcy,t2.lbmc, ");
		sql.append(" t3.bmdm,t3.xm zdlsxm ");
		sql.append(" from xg_kycxgl_kycxxmjgb t1 ");
		
		sql.append(" left join ( ");
		sql.append(" SELECT cy.jgid,wm_concat('学号：'||cy.xh||'，姓名：'||vx.xm||'，联系电话：'||vx.sjhm) xmcy ");
		sql.append(" FROM xg_kycxgl_kycxxmcyb cy LEFT JOIN VIEW_XSBFXX vx ");
		sql.append(" ON cy.xh = vx.xh GROUP BY cy.jgid ");
		sql.append(" ) c on c.jgid = t1.jgid ");
		
		sql.append(" left join xg_kycxgl_kycxxmlb t2 on t1.lbdm = t2.lbdm ");
		sql.append(" left join fdyxxb t3 on t1.zdls = t3.zgh ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		String userStatus = user.getUserStatus();
		String userDep = user.getUserDep();
		String userName = user.getUserName();
		if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" and a.bmdm='" + userDep + "' ");
		}else if ("bzr".equalsIgnoreCase(userStatus) ||
				  "fdy".equalsIgnoreCase(userStatus) || 
				  "jd".equalsIgnoreCase(userStatus)){
			sql.append(" and a.zdls='" + userName + "' ");
		}
		return getPageList(t, sql.toString(), inputV);
	}
	@Override
	public KycxxmjgForm getModel(KycxxmjgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.jgid,t1.sqid,t1.xn,t1.xmmc,t1.zdls,t1.lbdm,t1.xmsqrxm,t1.xmsqsj,t1.xbjf,t1.czr,t1.czsj,t1.sjly, ");
		sql.append(" t1.fjxx,t2.lbmc, ");
		sql.append(" t3.bmdm,t3.xm zdlsxm ");
		sql.append(" from xg_kycxgl_kycxxmjgb t1 ");
		sql.append(" left join xg_kycxgl_kycxxmlb t2 on t1.lbdm = t2.lbdm ");
		sql.append(" left join fdyxxb t3 on t1.zdls = t3.zgh ");
		sql.append(" where t1.jgid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getJgid() });
		KycxxmjgForm model=new KycxxmjgForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * 是否已存在
	 */
	public boolean checkExistSave(KycxxmjgForm model, User user) {
		String jgid = "-1";
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_kycxgl_kycxxmjgb where xn = ? and xmmc = ? and jgid <> ? ");
		if(model.getJgid() != null){
			jgid = model.getJgid();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXn(),model.getXmmc(),jgid }, "num");
		return "0".equals(num);
	}
	/**
	 * 学生列表
	 */
	public List<HashMap<String, String>> getXsxxList(KycxxmjgForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhArr = new String[0];
		if(!"".equals(model.getXhs())){
			xhArr = model.getXhs().split(",");
		}
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm,a.sjhm lxfs from view_xsjbxx a ");
		if(xhArr.length > 0){
			sql.append(" where a.xh not in(");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"' ");
				if(i < xhArr.length - 1){
					sql.append(", ");
				}
			}
			sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 根据科研创新项目获取学生列表
	 */
	public List<HashMap<String, String>> getKycxxmcyList(String jgid, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.sjhm lxfs ");
		sql.append("from xg_kycxgl_kycxxmcyb a left join view_xsbfxx b on a.xh=b.xh where a.jgid=?");
		String userStatus = user.getUserStatus();
		String userName = user.getUserName();
		if ("stu".equalsIgnoreCase(userStatus)) {
			sql.append(" and a.xh='" + userName + "' ");
		}
		return dao.getListNotOut(sql.toString(), new String[]{ jgid });
	}
	/**
	 * 保存学生
	 */
	public boolean insertKycxxmcy(String jgid,String [] xhArr) throws Exception {
		String sql = "delete from xg_kycxgl_kycxxmcyb where jgid=?";
		boolean rs = dao.runUpdate(sql, new String[]{ jgid });
		if(rs){
			if(xhArr == null){
				return true;
			}
			List<String[]> params = new ArrayList<String[]>();
			for (int i = 0; i < xhArr.length; i++) {
				params.add(new String[]{ jgid, xhArr[i] });
			}
			sql = "insert into xg_kycxgl_kycxxmcyb(jgid,xh) values(?,?)";
			int[] num = dao.runBatch(sql, params);
			rs = dao.checkBatchResult(num);
		}
		return rs;
	}
	/**
	 * 删除科研创新项目成员
	 */
	public boolean delKycxxmcy(String[] jgidArr) throws Exception {
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < jgidArr.length; i++) {
			params.add(new String[]{ jgidArr[i] });
		}
		String sql = "delete from xg_kycxgl_kycxxmcyb where jgid=?";
		int[] num = dao.runBatch(sql, params);
		boolean rs = dao.checkBatchResult(num);
		return rs;
	}
	/**
	 * 科研项目查询
	 */
	public List<HashMap<String, String>> kycxxmjgManageCx(KycxxmjgForm t, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.jgid,t1.sqid,t1.xn,t1.xmmc,t1.zdls,t1.lbdm,t1.xmsqrxm,t1.xmsqsj,t1.xbjf,t1.czr,t1.czsj,t1.sjly, ");
		sql.append(" a.xh,b.xm,b.nj,b.xz,b.xb,b.xymc,b.zymc,b.bjmc,b.xydm,b.zydm,b.bjdm,b.sjhm lxfs, ");
		sql.append(" t2.lbmc, ");
		sql.append(" t3.bmdm,t3.xm zdlsxm ");
		sql.append(" from xg_kycxgl_kycxxmcyb a ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh ");
		sql.append(" left join xg_kycxgl_kycxxmjgb t1 on a.jgid=t1.jgid ");
		sql.append(" left join xg_kycxgl_kycxxmlb t2 on t1.lbdm = t2.lbdm ");
		sql.append(" left join fdyxxb t3 on t1.zdls = t3.zgh ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
}
