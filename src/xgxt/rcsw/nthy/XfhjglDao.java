package xgxt.rcsw.nthy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

/**
 * ѧ�ѻ�������DAO
 */
public class XfhjglDao {

	DAO dao = DAO.getInstance();
	
	/**
	 * ��ѯѧ�ѻ���������Ϣ
	 * @return
	 */
	public HashMap<String, String> queryXfhjkg() {
		return dao
				.getMapNotOut(
						"select kg,replace(replace(replace(hjkssj,'-',''),':',''),' ','') chkhjkssj,hjkssj,replace(replace(replace(hjjssj,'-',''),':',''),' ','') chkhjjssj,hjjssj from xg_rcsw_nthy_xfhjcsb",
						new String[] {});
	}
	
	/**
	 * ��������������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean insertXfhjkg(XfhjglActionForm form) throws Exception{
		return dao
				.runUpdate(
						"insert into xg_rcsw_nthy_xfhjcsb(kg,hjkssj,hjjssj) values (?,?,?)",
						new String[] { form.getKg(), form.getHjkssj(),
								form.getHjjssj() });
	}
	
	/**
	 * �޸Ļ���������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean updateXfhjkg(XfhjglActionForm form) throws Exception{
		return dao
				.runUpdate(
						"update xg_rcsw_nthy_xfhjcsb set kg=?,hjkssj=?,hjjssj=?",
						new String[] { form.getKg(), form.getHjkssj(),
								form.getHjjssj()});
	}
	
	/**
	 * ��ѯѧ���Ƿ�Ƿ����Ϣ
	 * @param form
	 * @return
	 */
	public HashMap<String, String> queryXsxfsfqf(XfhjglActionForm form) {
		return dao
				.getMapNotOut(
						"select xh,xn,sfqf,jlsj,bz from xg_rcsw_nthy_xsqfxxb where xh=? and xn=? and sfqf='1'",
						new String[] { form.getXh(), form.getXn() });
	}
	
	/**
	 * ��ѯѧ��ѧ�ѻ�����Ϣ
	 * @param form
	 * @return
	 */
	public HashMap<String, String> queryXsxfhjxx(XfhjglActionForm form) {
		return dao
		.getMapNotOut(
				"select xh,xn,sqsj,sqyy from xg_rcsw_nthy_xfhjsqb where xh=? and xn=?",
				new String[] { form.getXh(), form.getXn() });
	}
	
	/**
	 * ����ѧ�ѻ���������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean insertXfhjsqxx(XfhjglActionForm form) throws Exception{
		return dao.runUpdate(
						"insert into xg_rcsw_nthy_xfhjsqb(xh,xn,sqsj,sqyy) values (?,?,?,?)",
						new String[] { form.getXh(), form.getXn(),
								form.getSqsj(), form.getSqyy() });
	}
	
	/**
	 * �޸�ѧ��������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean updateXfhjsqxx(XfhjglActionForm form) throws Exception {
		return dao.runUpdate(
				"update xg_rcsw_nthy_xfhjsqb set xn=?,sqsj=?,sqyy=? where xh||xn = ?",
				new String[] { form.getXn(), form.getSqsj(), form.getSqyy(),form.getXh()+form.getXn()  });
	}
	
	/**
	 * ��ѯѧ��ѧ�ѻ����Ƿ�������
	 * @param form
	 * @return
	 */
	public HashMap<String, String> queryXfhjsqxx(XfhjglActionForm form) {
		return dao
				.getMapNotOut(
						"select xh,xn,sqsj,sqyy,fdysh,xysh,xxsh from xg_rcsw_nthy_xfhjsqb where xh=? and xn=?",
						new String[] { form.getXh(), form.getXn()});
	}
	
	/**
	 * ����Ա��ѯ��˽��
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXfhjfdyshResult(XfhjglActionForm form)
			throws Exception {
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(new String[] { "nj", "xydm", "zydm", "bjdm", "xn",
				"shjg" }, new String[] { "xh", "xm" }, form);
		return CommonQueryDAO.commonQuery(
						"select a.*,rownum r from (select xh||xn pk,(case when xysh!='δ���' or xxsh!='δ���' then 'disabled' else '' end) dis,xh,xm,xb,xydm,zydm,nj,bjdm,bjmc,xn,fdysh shjg,fdyshsj shsj,fdysh,xysh sjsh,xxsh from xg_view_rcsw_nthy_xfhjsqb)a ",
						makeQuery.getQueryString()
								+ " and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"
								+ form.getZgh() + "')", makeQuery
								.getInputList(),
						new String[] { "pk", "dis","r","xn", "xh", "xm", "xb", "bjmc",
								 "shsj", "shjg","sjsh" }, form);
	}
	
	/**
	 * ѧԺ��ѯ��˽��
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXfhjxyshResult(XfhjglActionForm form)
			throws Exception {
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(new String[] { "nj", "xydm", "zydm", "bjdm", "xn",
				"shjg" }, new String[] { "xh", "xm" }, form);
		return CommonQueryDAO.commonQuery(
						"select a.*,rownum r from (select xh||xn pk,(case when xxsh!='δ���' then 'disabled' else '' end) dis,xh,xm,xb,xydm,zydm,nj,bjdm,bjmc,xn,xysh shjg,xyshsj shsj,fdysh,xysh,xxsh sjsh from xg_view_rcsw_nthy_xfhjsqb)a ",
						makeQuery.getQueryString()+" and fdysh='ͨ��' ", makeQuery
								.getInputList(),
						new String[] { "pk", "dis","r","xn", "xh", "xm", "xb", "bjmc",
								"shsj", "shjg", "sjsh" }, form);
	}
	
	/**
	 * ѧУ��ѯ��˽��
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXfhjxxshResult(XfhjglActionForm form)
			throws Exception {
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(new String[] { "nj", "xydm", "zydm", "bjdm", "xn",
				"shjg" }, new String[] { "xh", "xm" }, form);
		return CommonQueryDAO.commonQuery(
						"select a.*,rownum r from (select xh||xn pk,'' dis,xh,xm,xb,xydm,zydm,nj,bjdm,bjmc,xn,xxsh shjg,xxshsj shsj,fdysh,xysh,xxsh from xg_view_rcsw_nthy_xfhjsqb)a ",
						makeQuery.getQueryString() + " and fdysh='ͨ��' " + " and xysh='ͨ��' ", makeQuery
								.getInputList(),
						new String[] { "pk", "dis","r","xn", "xh", "xm", "xb", "bjmc",
								 "shsj", "shjg" }, form);
	}
	
	/**
	 * ��ѯѧ����˵�����Ϣ
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> queryXfhjshxx(String pk) {
		return dao.getMapNotOut("select * from xg_view_rcsw_nthy_xfhjsqb where xh||xn=?", new String[]{pk});
	}
	
	/**
	 * �޸������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean updateXfhjshxx(XfhjglActionForm form) throws Exception{
		String sql = "update xg_rcsw_nthy_xfhjsqb set ";
		if ("xy".equalsIgnoreCase(form.getUserType())) {
			if (form.isFdy()) {
				sql += " fdysh = ?,fdyyj=?,fdyshsj=?,fdyzgh=?";
			} else {
				sql += " xysh = ?,xyyj=?,xyshsj=?,xyzgh=?";
			}
		} else {
			sql += " xxsh = ?,xxyj=?,xxshsj=?,xxzgh=?";
		}
		sql +=" where xh||xn = ?";
		return dao.runUpdate(sql, new String[]{form.getShjg(),form.getShyj(),form.getShsj(),form.getZgh(), form.getPkValue()});
	}
	
	/**
	 * ��ѯѧ�ѻ��������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXfhjjg(XfhjglActionForm form) throws Exception {
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(new String[] { "nj", "xydm", "zydm", "bjdm", "xn",
				"fdysh", "xysh", "xxsh" }, new String[] { "xh", "xm" }, form);
		String sql = "";
		if (form.isFdy()) {
			sql += " and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"
					+ form.getZgh() + "')";
		}
		return CommonQueryDAO
				.commonQuery(
						"select xh||xn pk,rownum r,(case when fdysh='δ���' and xysh='δ���' and xxsh='δ���' then '' else 'disabled' end) dis,xh,xm,xb,xn,bjmc,sqsj,fdysh,xysh,xxsh from xg_view_rcsw_nthy_xfhjsqb a",
						makeQuery.getQueryString() + sql, makeQuery
								.getInputList(), new String[] {"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "fdysh", "xysh", "xxsh"}, form);

	}
	
	/**
	 * ɾ��ѧ�ѻ���������Ϣ
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public boolean deleteXfhjsqxx(String[] pk) throws Exception{
		StringBuffer sql = new StringBuffer();
		for (String s : pk) {
			sql.append("delete from xg_rcsw_nthy_xfhjsqb where xh||xn = '");
			sql.append(s);
			sql.append("'!@");
		}
		DAO mydao = DAO.getInstance();
		return mydao.checkBatch(mydao.runBatch(sql.toString().split("!@")));
	}
}
