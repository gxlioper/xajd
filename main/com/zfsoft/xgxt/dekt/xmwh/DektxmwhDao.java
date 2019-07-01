package com.zfsoft.xgxt.dekt.xmwh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DektxmwhDao extends SuperDAOImpl<DektxmwhForm> {
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_DEKT_XMDMB");
		super.setKey("xmid");
		super.setClass(DektxmwhForm.class);
	}
	
	
	@Override
	public List<HashMap<String, String>> getPageList(DektxmwhForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		sql.append("select * from ( ");
		sql.append("select t.*,a.bmmc xymc, ");
		sql.append("replace( t.lx||'->'||t.rdxm||'->'||t.rdnrbz||'->'||t.dj,'->无',' ') rdnr, ");
		sql.append("decode(e.lcxx,'','未设置',mc || '：' || replace(lcxx, ';', '->')) lcxx ");
		sql.append("from XG_DEKT_XMDMB t left join ZXBZ_XXBMDM a on t.ssxydm=a.bmdm ");
		sql.append("left join (select splc, mc,max(lcxx) lcxx ");
		sql.append("from (select splc, a.mc, to_char(WM_CONCAT(c.mc) over(partition by splc order by xh)) lcxx ");
		sql.append("from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c ");
		sql.append("where a.id = b.splc and b.spgw = c.id) group by splc, mc) e on t.splc = e.splc ");
		sql.append("order by t.lx,t.rdxm,t.rdnrbz,t.xf desc ) ");
		sql.append("where 1=1 ");
		sql.append(searchTj);
		List<String> params = new ArrayList<String>();
		if(StringUtils.isNotNull(model.getLx())){
			sql.append(" and lx = ? ");
			params.add(model.getLx());
			inputV=StringUtils.joinStrArr(inputV, new String[]{model.getLx()});
		}
		
		return getPageList(model, sql.toString(), inputV);
	}
	

	@Override
	public List<HashMap<String, String>> getPageList(DektxmwhForm model, User user) throws Exception {
		return null;
	}
	
	public List<HashMap<String, String>> getLx(String xmdl) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct lx,tb from XG_DEKT_XMDMB where xmdl = ?");
		if(xmdl.equals("dekt")){
			return dao.getListNotOut(sql.toString(), new String[]{"第二课堂学分"});			
		}else{
			return dao.getListNotOut(sql.toString(), new String[]{"创新创业学分"});
		}
	}
	
	public List<HashMap<String, String>> getRdxm(String lx) throws Exception{
		String sql="select distinct rdxm from XG_DEKT_XMDMB where lx = ?";
		return dao.getListNotOut(sql, new String[]{lx});
	}
	
	public List<HashMap<String, String>> getRdnrbz(String lx,String rdxm) throws Exception{
		String sql="select distinct rdnrbz from XG_DEKT_XMDMB where lx = ? and rdxm = ?";
		return dao.getListNotOut(sql, new String[]{lx,rdxm});
	}
	
	public List<HashMap<String, String>> getDj(String lx,String rdxm,String rdnrbz) throws Exception{
		String sql="select distinct dj from XG_DEKT_XMDMB where lx = ? and rdxm = ? and rdnrbz = ? and splc is not null";
		return dao.getListNotOut(sql, new String[]{lx,rdxm,rdnrbz});
	}
	
	
//	public String checkExist(DektxmwhForm form){
//		StringBuilder sql = new StringBuilder();
//		sql.append("select count(1) num from XG_QGZX_NEW_JTFFB where xh = ? and sj = ? ");
//		if(StringUtils.isNotNull(form.getId())){
//			sql.append("and id<> '");
//			sql.append(form.getId());
//			sql.append("'");
//		}
//		return dao.getOneRs(sql.toString(), new String[]{form.getXh(),form.getSj()}, "num");
//	}

	
}
