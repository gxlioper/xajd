package xgxt.mdqr;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class MdqrDAO {

	/**
	 * method getXmlbList 获取项目类别列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmlbList(String gnmk) {
		DAO dao = DAO.getInstance();
		String sql = " select xmlbdm dm,xmlbmc mc from mdqr_xmlbb where gnmk=?";
		return dao.getList(sql, new String[] {gnmk}, new String[] { "dm", "mc" });
	}

	/**
	 * @param xmdm(项目代码)
	 * method getXmList 获取项目列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmList(String xmlbdm) {
		DAO dao = DAO.getInstance();
		String sql = " select xmdm dm,xmmc mc from mdqr_xmszb where xmlbdm =? ";
		return dao.getList(sql, new String[] { xmlbdm}, new String[] { "dm",
				"mc" });
	}
	
	/**
	 * 根据mdsz权限获取信息
	 * @param xmdm(项目代码) yhqx(用户权限)
	 * method getXmList 获取项目列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmList(String xmlbdm,String yhqx) {
		DAO dao = DAO.getInstance();
		String sql = " select xmdm dm,xmmc mc from mdqr_xmszb where xmlbdm =? and kfsq='yes' and (mdsz=? or mdsz='no')";
		return dao.getList(sql, new String[] { xmlbdm,yhqx }, new String[] { "dm",
				"mc" });
	}
	
	/**
	 * 根据mdsz权限获取信息
	 * @param xmdm(项目代码) yhqx(用户权限)
	 * method getXmList 获取项目列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmListByShqx(String xmlbdm,String shjb) {
		DAO dao = DAO.getInstance();
		String sql = " select xmdm dm,xmmc mc from mdqr_xmszb where xmlbdm =? and kfsh='yes' "+shjb;
		return dao.getList(sql, new String[] { xmlbdm }, new String[] { "dm",
				"mc" });
	}
	
	/**
	 * method getXmList 获取(需要审核)项目列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmShList(String xmlbdm,String mdqr) {
		DAO dao = DAO.getInstance();
		String sql = " select xmdm dm,xmmc mc from mdqr_xmszb where xmlbdm like ? and kfqr='yes' and ( mdqr=? or mdqr='no')" ;
		return dao.getList(sql, new String[] { xmlbdm,mdqr }, new String[] { "dm",
				"mc" });
	}

	/**
	 * 项目内容 method getXmxx
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXmxx(MdqrForm form) throws Exception {
		MdqrService service = new MdqrService();
		String szzq=service.getSzzq(form.getQueryequals_xmdm());
		StringBuilder sql = new StringBuilder();
		String[] list = { "xydm", "zydm", "bjdm","yesNo" ,"nj"};
		String[] colLikeList = { "xh", "xm" };
		String[] colList = { "pkValue","disabled", "sf", "xh", "xm", "nj", "xymc", "zymc",
				"bjmc","yesNo" };
		
		String xn=form.getXn();
		String xq=form.getXq();
		String nd=form.getNd();
		String sbsj=form.getSbsj();
		
		String query="";
		if("true".equalsIgnoreCase(form.getIsFdy())){
			query=" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+form.getUserName()+"') ";
		}
		
		
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(list, colLikeList, form);
		sql.append("select rownum r,a.* from( select a.*,a.xh pkValue,b.xmdm,b.xmlbdm,b.xn,b.xq,b.nd, (case when b.xysh<>'未审核' or b.xxsh<>'未审核' or mdqr='checked'  then 'disabled' else '' end)disabled, (case when b.xh is not null then 'checked' else '' end)sf,");
		sql.append(" (select xqmc from xqdzb c where b.xq=c.xqdm)xqmc,");
		sql.append(" (case when b.xh is not null then '是' else '否' end)yesNo from view_xsjbxx a left join(select * from mdqr_xmnrb where xmdm='"
						+ form.getQueryequals_xmdm() + "'");
		if("xn".equalsIgnoreCase(szzq)){
			sql.append(" and xn='"+xn+"' ");
		}
		if("xq".equalsIgnoreCase(szzq)){
			sql.append(" and xn='"+xn+"' ");
			sql.append(" and xq='"+xq+"' ");
		}
		if("nd".equalsIgnoreCase(szzq)){
			sql.append(" and nd='"+nd+"' ");
		}
		if("wzq".equalsIgnoreCase(szzq)){
			sql.append(" and sbsj='"+sbsj+"' ");
		}
		
		sql.append(" ) b on a.xh=b.xh )a ");
		System.out.println(sql);
		return CommonQueryDAO.commonQuery(sql.toString(), makeQuery
				.getQueryString()+query, makeQuery.getInputList(), colList, form);
	}

	/**
	 * 项目内容 method getXmxx
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getMdqrXx(MdqrForm form) throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] list = { "xydm", "zydm", "bjdm", "xmlbdm", "xmdm","nj","yesNo" };
		String[] colLikeList = { "xh", "xm" };
		String[] colList = { "pkValue", "mdqr","xysh","xxsh","xh", "xm", "nj","sbsj",  "xymc",
				"bjmc","yesNo" };
		String query="";
		if(!"".equalsIgnoreCase(form.getKssj()) && form.getKssj()!=null){
			query+=" and sbsj>="+form.getKssj();
		}
		if(!"".equalsIgnoreCase(form.getJssj()) && form.getJssj()!=null){
			query+=" and sbsj<="+form.getJssj();
		}
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(list, colLikeList, form);
		sql.append("  select  rownum r, a.* from (select a.* from(select a.xmdm||a.xmlbdm||a.xh||a.sbsj pkValue,");
		sql.append(" a.xmdm,a.xmlbdm,a.xn,(select xqmc from xqdzb c where a.xq=c.xqdm)xqmc,a.xq, a.nd,a.nj,a.xh, a.xm, a.xydm, a.bjdm,a.zydm,(case when a.mdqr='checked' then '是' else '否' end) yesNo,a.mdqr,a.xymc,a.zymc,a.bjmc,a.sbsj,a.xysh,a.xxsh,b.shjb");
		sql.append(" from view_mdqr_xmnrb a, mdqr_xmszb b   where a.xmdm = b.xmdm ) a ");
		sql.append(" where (a.shjb = 'xysh' and xysh = '通过') or (a.shjb = 'xyxsh' and xxsh = '通过')  or (a.shjb = 'xxsh' and xxsh = '通过') or (a.shjb ='no')) a ");
		return CommonQueryDAO.commonQuery(sql.toString(), makeQuery
				.getQueryString()+query, makeQuery.getInputList(), colList, form);
	}

	public HashMap<String, String> getXmShjb(MdqrForm form) {
		DAO dao = DAO.getInstance();
		String sql = " select shjb from mdqr_xmszb where xmdm=? ";
		return dao.getMap(sql, new String[] { form.getQueryequals_xmdm() },
				new String[] { "shjb" });
	}

	/**
	 * 根据XMDM获取设置（设置名单权限）
	 * @param xmdm
	 * @return
	 */
	public HashMap<String, String> checkMdSz(String xmdm) {
		DAO dao = DAO.getInstance();
		String sql = " select mdsz from mdqr_xmszb where xmdm=? ";
		return dao.getMap(sql, new String[] { xmdm }, new String[] { "mdsz" });
	}
	
	/**
	 * 根据XMDM获取设置（确认名单的权限）
	 * @param xmdm
	 * @return
	 */
	public HashMap<String, String> checkMdQr(String xmdm) {
		DAO dao = DAO.getInstance();
		String sql = " select mdqr from mdqr_xmszb where xmdm=? ";
		return dao.getMap(sql, new String[] { xmdm }, new String[] { "mdqr" });
	}
	
	public HashMap<String,String> getSzzq(String xmdm){
		DAO dao = DAO.getInstance();
		String sql = " select szzq from mdqr_xmszb where xmdm=? ";
		return dao.getMap(sql, new String[] { xmdm }, new String[] { "szzq" });
	}
	
	public HashMap<String,String>getShjb(MdqrForm form){
		DAO dao=DAO.getInstance();
		String sql = " select shjb from mdqr_xmszb where xmdm=? ";
		return dao.getMap(sql, new String[]{form.getQueryequals_xmdm()}, new String[]{"shjb"});
	}
	
	public boolean deleteXmxx(MdqrForm form) throws Exception{
		DAO dao=DAO.getInstance();
		String sql=" delete from mdqr_xmnrb a where not exists(select * from mdqr_xmszb b where a.xmdm=b.xmdm) ";
		return dao.runUpdate(sql, new String[]{});
		
	}
	
	public HashMap<String,String>getXqmc(String xqdm){
		DAO dao = DAO.getInstance();
		String sql=" select xqmc from xqdzb where xqdm=? ";
		return dao.getMap(sql, new String[]{xqdm}, new String[]{"xqmc"});
	}
}
