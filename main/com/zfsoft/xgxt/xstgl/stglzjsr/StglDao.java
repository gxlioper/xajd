package com.zfsoft.xgxt.xstgl.stglzjsr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqForm;

public class StglDao extends SuperDAOImpl<StglForm> {
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_STGL_SRDX_STWHB");
		super.setKey("id");
		super.setClass(StglForm.class);
	}
	
	
	@Override
	public List<HashMap<String, String>> getPageList(StglForm model) throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select t1.*,t2.stlbmc,t3.xm szxm,t4.xm cwfzrxm,decode(t1.yxzt,'1','有效','0','无效') yxztmc, ");
		sql.append("t3.sjhm szch,t3.shgxxm1 szdh,t4.sjhm cwfzrch,t4.shgxxm1 cwfzrdh from XG_STGL_SRDX_STWHB t1 "); 
		sql.append("left join xg_stgl_stlb t2 on t1.stlb=t2.stlbdm ");
		sql.append("left join xsxxb t3 on t1.sz=t3.xh ");
		sql.append("left join xsxxb t4 on t1.cwfzr=t4.xh ");
		sql.append(" ) where 1=1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(StglForm model, User user) throws Exception {
		return null;
	}

	public String checkExist(StglForm form){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from XG_STGL_SRDX_STWHB where bh = ? or stmc = ? ");
		if(StringUtils.isNotNull(form.getId())){
			sql.append("and id<> '");
			sql.append(form.getId());
			sql.append("'");
		}
		return dao.getOneRs(sql.toString(), new String[]{form.getBh(),form.getStmc()}, "num");
	}
	
	//获取学生信息列表
		public List<HashMap<String, String>> getXsxxList(StglForm model, User user) throws Exception {
			// 生成高级查询相关条件、条件值
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
//			String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
			sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a ");
			sql.append(")a where 1=1 ");
//			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			return getPageList(model, sql.toString(), inputV);
		}
		
		//获取老师信息列表
		public List<HashMap<String, String>> getTeaList(StglForm model, User user) throws Exception {
			// 生成高级查询相关条件、条件值
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
//			String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (");
			sql.append(" select t.zgh,t.xm,t.xb,t.bmdm,t1.bmmc,t2.zcmc,t.lxdh,t.zc");
			sql.append(" from fdyxxb t left join zxbz_xxbmdm t1 on t.bmdm = t1.bmdm");
			sql.append(" left join zcb t2");
			sql.append(" on t.zc = t2.zcdm ");
			sql.append(" )a where 1=1 ");
//			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			return getPageList(model, sql.toString(), inputV);
		}
		
		public HashMap<String,String> getStgl(StglForm model) throws Exception{
			StringBuilder sql = new StringBuilder();
			sql.append("select * from ( ");
			sql.append("select t1.*,t2.stlbmc,t3.xm szxm,t4.xm cwfzrxm,decode(t1.yxzt,'1','有效','0','无效') yxztmc from XG_STGL_SRDX_STWHB t1 "); 
			sql.append("left join xg_stgl_stlb t2 on t1.stlb=t2.stlbdm ");
			sql.append("left join xsxxb t3 on t1.sz=t3.xh ");
			sql.append("left join xsxxb t4 on t1.cwfzr=t4.xh ");
			sql.append(" ) where id=? ");
			return dao.getMapNotOut(sql.toString(), new String[]{model.getId()});
		}
	
}
