package com.zfsoft.xgxt.dekt.xfsq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DektxfsqDao extends SuperDAOImpl<DektxfsqForm> {
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_DEKT_DEKTXFSQB");
		super.setKey("sqid");
		super.setClass(DektxfsqForm.class);
	}
	
	
	@Override
	public List<HashMap<String, String>> getPageList(DektxfsqForm model) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DektxfsqForm model, User user) throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中')shztmc, ");
		sql.append("decode(a.cyfs,'gr','个人','tt','团体')cyfsmc,b.xm,b.bjdm,b.bjmc,b.zydm,b.zymc,b.xydm,b.xymc,");
		sql.append("replace( c.lx||'->'||c.rdxm||'->'||c.rdnrbz||'->'||c.dj,'->无',' ') rdnr, ");
		sql.append("c.ssxydm,c.xmdl,c.lx,c.rdxm,c.rdnrbz,c.dj,c.xf from XG_DEKT_DEKTXFSQB a ");
		sql.append("left join view_xsbfxx b on a.xh=b.xh left join XG_DEKT_XMDMB c on a.xmid=c.xmid )t where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(model, sql.toString(), inputV);
	}
	
	
	
	public boolean checkExist(DektxfsqForm form){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from XG_DEKT_DEKTXFSQB where xh = ? and hjsj=? ");
		sql.append(" and xmid in (select xmid from xg_dekt_xmdmb where lx=? and rdxm=? and rdnrbz=? and dj=? and rownum=1) ");
		if(StringUtils.isNotNull(form.getSqid())){
			sql.append("and sqid<> '");
			sql.append(form.getSqid());
			sql.append("'");
		}
		return Integer.parseInt(dao.getOneRs(sql.toString(), new String[]{form.getXh(),form.getHjsj(),
				form.getLx(),form.getRdxm(),form.getRdnrbz(),form.getDj()}, "num"))>0.;
		
	}


	/**
	 * @描述：保存
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月26日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public boolean saveSq(DektxfsqForm form) throws Exception {
		String sql = "insert into XG_DEKT_DEKTXFSQB (SQID, XH, XMID, CYFS, HJSJ, SQSM, FILEPATH, SPLC, SHZT)"
				+"select ?,?,xmid,?,?,?,?,splc,'0' from xg_dekt_xmdmb where lx=? and rdxm=? and rdnrbz=? and dj=? and rownum=1";
		String[]input=new String[]{form.getSqid(),form.getXh(),form.getCyfs(),form.getHjsj(),form.getSqsm(),
				form.getFilepath(),form.getLx(),form.getRdxm(),form.getRdnrbz(),form.getDj()};
		return dao.runUpdate(sql, input);
	}
	
	public String getSplc(DektxfsqForm form) throws Exception {
		String sql = "select splc from XG_DEKT_DEKTXFSQB where sqid=? ";
		return dao.getOneRs(sql, new String[]{form.getSqid()}, "splc");
	}
	
	public boolean updateShzt (String sqid,String shzt) throws Exception{
		String sql="update XG_DEKT_DEKTXFSQB set shzt=? where sqid=? ";
		return dao.runUpdate(sql, new String[]{shzt,sqid});
	}
	
	public Map<String, String> getView(DektxfsqForm form) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("select * from (select t1.sqid,t1.xh,t1.xmid,t1.cyfs,decode(t1.cyfs,'gr','个人','tt','团体') cyfsmc,t1.hjsj,t1.sqsm, ");
		sql.append("t1.filepath,t1.splc,t1.shzt,t2.ssxydm,t2.xmdl,t2.lx,t2.rdxm,t2.rdnrbz,t2.dj,t2.yjsm,replace( t2.lx||'->'||t2.rdxm||'->'||t2.rdnrbz||'->'||t2.dj,'->无',' ') rdnr ");
		sql.append("from XG_DEKT_DEKTXFSQB t1 left join XG_DEKT_XMDMB t2 on t1.xmid=t2.xmid ) ");
		sql.append("where sqid=?");
		return dao.getMapNotOut(sql.toString(), new String[]{form.getSqid()});
	}

	/**
	 *  批量导入申请表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-11 17:16
	 * @param paraList
	 * @return boolean
	 * @throw
	 */
	public boolean batchInsertDataIntoDB(List<String[]> paraList) throws SQLException {
		String sql = "insert into XG_DEKT_DEKTXFSQB (XH, XMID, CYFS, HJSJ, SQSM, SPLC, SHZT)"
				+"select ?,xmid,?,?,?,splc,'0' from xg_dekt_xmdmb where lx=? and rdxm=? and rdnrbz=? and dj=? and rownum=1";

		return dao.runBatchBoolean(sql,paraList);
	}
}
