package xgxt.xsh.stgl.cdty;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class CdtyXsstDAO extends CommDAO {

	/**
	 * 获取社团信息列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getStxxList(CdtyXsstForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select stdm dm,stmc mc from xsh_stglb ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}

	/**
	 * 获取社团详细信息
	 * 
	 * @return
	 */
	public HashMap<String, String> getStInfoList(CdtyXsstForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select stdm,stmc,stxz,stcsr,stclsj,zdls,sthddd from xsh_stglb where stdm = ? ");
		return dao.getMap(sql.toString(), new String[] {model.getStdm()}, new String[] {
				"stdm", "stmc", "stxz", "stcsr", "stclsj", "zdls", "sthddd" });
	}
	
	/**
	 * 获取学生申请社团信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXsstMap(CdtyXsstForm model){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from(select a.xh,a.stdm,a.shzt,a.shsj,a.shr,a.shyj, ");
		sql.append(" b.xm,b.xb,b.xymc,b.zymc,b.bjmc,b.nj,c.stmc,c.bmdm, ");
		sql.append(" c.stcsr,c.stclsj,c.sthddd,c.zdls from xsh_cyglb a  ");
		sql.append(" left join view_xsjbxx b on a.xh=b.xh ");
		sql.append(" left join  xsh_stglb c on a.stdm=c.stdm)where  ");
		sql.append(" xh||'!!@@!!'||stdm= ? ");
		return dao.getMap(sql.toString(), new String[]{model.getPkValue()}, new String[]{"xh","stdm",
			"shzt","shsj","shr","shyj","xm","xb","xymc","zymc","bjmc","nj","stmc","bmdm",
			"stcsr","stclsj","sthddd","zdls"});
	}
	
	/**
	 * 获取学生申请社团信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getStxsByXh(CdtyXsstForm model){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select b.xh,b.xm,b.xb,b.nj,b.xymc,b.zymc,b.bjmc from (select * from xsh_cyglb where  ");
		sql.append(" xh= ? and rownum=1)a left join view_xsjbxx b on a.xh=b.xh ");
		return dao.getMap(sql.toString(), new String[]{model.getXh()}, new String[]{"xh",
			"xm","xb","xymc","zymc","bjmc","nj"});
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getStxxInfo(CdtyXsstForm model)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r,stdm,stmc,stxz,stclsj, ");
		sql.append(" (select xm from yhb where yhm=zdls)zdls from xsh_stglb ");

		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},
				new String[] { "stdm", "stmc", "stxz", "stclsj", "zdls" },
				model);
	}

	/**
	 * 保存学生社团信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsstInfo(CdtyXsstForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xsh_cyglb(xh,stv,rtsj,stdm)values('"
				+ model.getXh() + "'" + ",'" + model.getStmc() + "'" + ",'"
				+ model.getRtsj() + "'" + ",'" + model.getStdm() + "') ");
		return this.saveArrDate(new String[] { sql.toString() });
	}

	/**
	 * 获取学生申报社团信息
	 * 
	 * @return
	 */
	public ArrayList<String[]> getXsstInfo(CdtyXsstForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		MakeQuery mQuery = new MakeQuery();
		StringBuilder sql = new StringBuilder();

		String[] colList = { "xydm", "zydm", "bjdm", "shzt", "nj" };
		String[] colLikeList = { "xh", "xm" };
		mQuery.makeQuery(colList, colLikeList, model);

		sql.append(" select rownum r,a.* from( ");
		sql
				.append(" select a.xh||'!!@@!!'||c.stdm pkValue,a.xh, b.xm, b.xydm, b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc, b.nj,c.stmc,c.stdm, ");
		sql.append(" a.shzt from xsh_cyglb a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" left join xsh_stglb c on a.stdm=c.stdm)a ");

		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString(), mQuery.getInputList(), new String[] {
				"pkValue", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "stmc",
				"shzt" }, model);
	}

	/**
	 * 删除学生申请社团信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delXsstInfo(CdtyXsstForm model) throws Exception {
		String[] sql = new String[model.getPrimarykey_checkVal().length];

		for (int i = 0; i < model.getPrimarykey_checkVal().length; i++) {
			sql[i] = " delete from xsh_cyglb where xh||'!!@@!!'||stdm='"
					+ model.getPrimarykey_checkVal()[i] + "' ";
		}
		return saveArrDate(sql);
	}
	
	public boolean updateShzt(CdtyXsstForm model) throws Exception {
		String[] sql = new String[model.getPrimarykey_checkVal().length];

		for (int i = 0; i < model.getPrimarykey_checkVal().length; i++) {
			sql[i] = " update xsh_cyglb ";
			sql[i] += " set shzt='" + model.getShzt() + "' ";
			if (!Base.isNull(model.getShr())) {
				sql[i] += " ,shr='" + model.getShr() + "' ";
			}
			if (!Base.isNull(model.getShsj())) {
				sql[i] += " ,shsj='" + model.getShsj() + "' ";
			}
			if (!Base.isNull(model.getShyj())) {
				sql[i] += " ,shyj='" + model.getShyj() + "' ";
			}
			sql[i] += "where xh||'!!@@!!'||stdm='";
			sql[i] += model.getPrimarykey_checkVal()[i] + "' ";

		}
		return saveArrDate(sql);
	}
	
	/**
	 * 社团干部列表获取
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getStgbList(CdtyXsstForm model){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select dm||'ty' dm,gbmc from bjxh_sttygbb ");
		sql.append(" union ");
		sql.append(" select dm,gbmc from ");
		sql.append(" (select dm,gbmc from bjxh_stdygbb order by stmc) ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","gbmc"});
	}
	
	/**
	 * 社团列表()
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getStByXh(CdtyXsstForm model){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select stdm,stv from xsh_cyglb a where xh=?  ");
		sql.append(" and exists(select 1 from xsh_stglb b where a.stdm=b.stdm) ");
		sql.append(" group by stdm,stv  ");
		return dao.getList(sql.toString(), new String[]{model.getXh()}, new String[]{"stdm","stv"});
	}
	
	public boolean saveXsstgb(CdtyXsstForm model) throws Exception{
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" insert into xg_xsst_stgbb (xh,stdm,zwdm,sqsj)values(?,?,?,?) ");
		String []inputV={model.getXh(),model.getStdm(),model.getZwdm(),model.getSqsj()};
		return dao.runUpdate(sql.toString(), inputV);
		
	}
	
	/**
	 * 获取学生申报社团信息
	 * 
	 * @return
	 */
	public ArrayList<String[]> getStgbInfo(CdtyXsstForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		MakeQuery mQuery = new MakeQuery();
		StringBuilder sql = new StringBuilder();

		String[] colList = { "xydm", "zydm", "bjdm", "shzt", "nj" };
		String[] colLikeList = { "xh", "xm" };
		mQuery.makeQuery(colList, colLikeList, model);

		sql.append(" select rownum r,a.* from( ");
		sql
				.append(" select a.xh||'!!@@!!'||c.stdm pkValue,a.xh, b.xm, b.xydm, b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc, b.nj,c.stmc,c.stdm, ");
		sql.append(" a.shzt from xg_xsst_stgbb a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" left join xsh_stglb c on a.stdm=c.stdm)a ");

		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString(), mQuery.getInputList(), new String[] {
				"pkValue", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "stmc",
				"shzt" }, model);
	}
	
	public String checkStgb(CdtyXsstForm model){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		String xh=model.getXh();
		String stdm=model.getStdm();
	
		sql.append(" select count(1)num from xg_xsst_stgbb where xh=? and stdm=? and shzt<>'不通过' ");
		
		return dao.getOneRs(sql.toString(), new String[]{xh,stdm}, "num");
	}
}
