package xgxt.pjpy.comm.pjpy.pjlc.xmsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��Ŀ���_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyXmshDAO extends PjpyCommDAO {

	/**
	 * ��������Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXhxmList(PjpyXmshForm model,
			User user) {

		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		String xmdm = model.getXmdm();// ��Ŀ����
		String xmmc = model.getXmmc();// ��Ŀ����
		String ywmc = model.getYwmc();// Ӣ������
		String xmxz = model.getXmxz();// ��Ŀ����
		String xmfw = model.getXmfw();// ��Ŀ��Χ
		String xmlx = model.getXmlx();// ��Ŀ����
		
		// ������Ŀ��
		String tableName = "xg_pjpy_pjxmwhb a";
		// ����
		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append(" and pjxn = ? ");
		query.append(" and pjxq = ? ");
		query.append(" and pjnd = ? ");
		query.append(" and sfqy = '��' ");
		query.append(Base.isNull(xmdm) ? "" : " and xmdm = '" + xmdm + "' ");
		query.append(Base.isNull(xmmc) ? "" : " and xmmc like '%' || '" + xmmc + "' || '%' ");
		query.append(Base.isNull(ywmc) ? "" : " and ywmc like '%' || '" + ywmc + "'|| '%' ");
		query.append(Base.isNull(xmxz) ? "" : " and xmxz = '" + xmxz + "' ");
		query.append(Base.isNull(xmfw) ? "" : " and xmfw = '" + xmfw + "' ");
		query.append(Base.isNull(xmlx) ? "" : " and xmlx = '" + xmlx + "' ");
		
		//���ʱ����ƿ���
		query.append(" and not exists (select 1 from (select * from xg_pjpy_sjszb where ")
		     .append("(sysdate not between to_date(shkssj,'yyyy-mm-dd') ")
		     .append(" and to_date(shjssj,'yyyy-mm-dd')+1) or shkzkg<>'0'")
		     .append(" ) b where a.pjxn=b.pjxn ")
		     .append(" and a.pjxq=b.pjxq and a.pjnd=b.pjnd and a.xmdm=b.xmdm)");
		
		query.append(" order by to_number(xssx) ");

		String[] colList = new String[] { "xmdm", "xmmc", "lcid" };

		return getRsList(tableName, query.toString(), new String[] {pjxn,pjxq,pjnd}, colList,
				"");
	}
	
	/**
	 * �����Ŀ���ѧ���б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getXmshXsList(PjpyXmshForm model, User user,
			String[] colList, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchService searchService = new SearchService();

		PjpyXmszModel xmszModel = model.getXmszModel();

		// ��������
		String sqzq = xmszModel.getSqzq();
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// ��˼���
		String shjb = model.getShjb();
		// ��˸�λ
		String spgw = model.getSpgw();

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;

		// �����Ŀ
		String xmdm = model.getShxm();

		StringBuilder sql = new StringBuilder();
		// �¼����
		String nextShjb = String.valueOf(Integer.parseInt(shjb) + 1);
		
		sql.append("select rownum r,a.*, ");
		sql.append("nvl((select d.shzt from xg_pjpy_pjxmshb d, xg_xtwh_spbz e ");
		sql.append(" where d.xtgwid = e.spgw and a.xmdm = d.xmdm ");
		sql.append(" and a.pjxn = d.pjxn and a.pjxq = d.pjxq ");
		sql.append(" and a.pjnd = d.pjnd and a.xh = d.xh and e.splc = a.lcid");
		sql.append(" and e.xh = '"+nextShjb+"'),'δ���') sjzt from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(xmdm) ? "" : " and xmdm = '" + xmdm + "'");
		sql.append(Base.isNull(spgw) ? "" : " and spgw = '" + spgw + "'");
		sql.append(Base.isNull(shjb) ? "" : " and shjb = '" + shjb + "'");
		
		
		//������Ŀ��������
		if("xn".equalsIgnoreCase(sqzq)){
			sql.append(" and pjxn = '" + pjxn + "'");
		}else if("nd".equalsIgnoreCase(sqzq)){
			sql.append(" and pjnd = '" + pjnd + "'");
		}else if("xq".equalsIgnoreCase(sqzq)){
			sql.append(" and pjxn = '" + pjxn + "'");
			sql.append(" and pjxq = '" + pjxq + "'");
		}
		
		// ��һ�����
		if ("1".equalsIgnoreCase(shjb)) {

		} else {
			// �ϼ����
			String preShjb = String.valueOf(Integer.parseInt(shjb) - 1);
			sql.append(" and exists (select 1 from xg_xtwh_spbz b,xg_pjpy_pjxmshb c ");
			sql.append(" where b.splc = a.lcid ");
			sql.append(" and a.xh = c.xh ");
			sql.append(" and a.xmdm = c.xmdm ");
			sql.append(" and a.pjxn = c.pjxn ");
			sql.append(" and a.pjxq = c.pjxq ");
			sql.append(" and a.pjnd = c.pjnd ");	
			sql.append(" and b.spgw = c.xtgwid ");
			sql.append(" and b.xh = '" + preShjb + "' ");
			sql.append(" and (c.shzt = 'ͨ��')");
			sql.append(" ) ");
		}
		
		sql.append(searchTj);
		ArrayList<String[]> list = commonQuery(sql.toString(), "", inputV, colList, model);

		return list;
	}
	
	/**
	 * �����Ҫ�˻ز�������Ŀ����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String[] getXmthPk(PjpyXmshForm model) throws Exception {

		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm||a.pjxn||a.pjxq||a.pjnd||a.xh||a.xtgwid pk ");
		sql.append(" from xg_view_pjpy_xmsh a where exists (select 1 ");
		sql.append(" from (select a.xmdm,a.pjxn,a.pjxq,a.pjnd,a.xh, ");
		sql.append(" (a.shjb - 1) shjb from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1");

		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.pk = ? ");
			}
			sql.append(" ) ");
		}
		sql.append(" ) b ");
		sql.append(" where a.xmdm = b.xmdm and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq and a.pjnd = b.pjnd ");
		sql.append(" and a.xh = b.xh and a.shjb = b.shjb) ");

		DAO dao = DAO.getInstance();

		String[] rs = dao.getRs(sql.toString(), pkValue, "pk");

		return rs;
	}
	
	/**
	 * ���������Ŀ����Ŀ����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String[] getXmcsPk(PjpyXmshForm model) throws Exception {

		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm||a.pjxn||a.pjxq||a.pjnd||a.xh||a.xtgwid pk ");
		sql.append(" from xg_view_pjpy_xmsh a where exists (select 1 ");
		sql.append(" from (select a.xmdm,a.pjxn,a.pjxq,a.pjnd,a.xh, ");
		sql.append(" (a.shjb + 1) shjb from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1");

		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.pk = ? ");
			}
			sql.append(" ) ");
		}
		sql.append(" ) b ");
		sql.append(" where a.xmdm = b.xmdm and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq and a.pjnd = b.pjnd ");
		sql.append(" and a.xh = b.xh and a.shjb = b.shjb) ");

		DAO dao = DAO.getInstance();

		String[] rs = dao.getRs(sql.toString(), pkValue, "pk");

		return rs;
	}
	
	/**
	 * �����Ŀ��˼������գ�
	 * 
	 * @author ΰ�����
	 */
	public String getXmzzshjb(PjpyXmshForm model) {

		// ��Ŀ����
		String xmdm = model.getShxm();
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select MAX(b.xh) shjb from xg_pjpy_pjxmwhb a ");
		sql.append(",xg_xtwh_spbz b where 1 = 1 ");
		sql.append(" and a.lcid = b.splc ");
		sql.append(" and a.xmdm = ? ");
		sql.append(" and a.pjxn = ? ");
		sql.append(" and a.pjxq = ? ");
		sql.append(" and a.pjnd = ? ");

		DAO dao = DAO.getInstance();

		String zzShjb = dao.getOneRs(sql.toString(), new String[] { xmdm, pjxn,
				pjxq, pjnd }, "shjb");

		return zzShjb;
	}
	
	/**
	 * �����Ŀ�������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmshBshr(PjpyXmshForm model)
			throws Exception {

		// ��Ŀ���ö���
		PjpyXmszModel xmszModel = model.getXmszModel();

		// ��Ŀ����
		String xmdm = model.getShxm();
		// ��������
		String sqzq = xmszModel.getSqzq();
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm, ");
		sql.append(" a.zymc,a.bjdm,a.bjmc from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.xmdm = '" + xmdm + "' ");

		// ������Ŀ��������
		if ("xn".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjxn = '" + pjxn + "'");
		} else if ("nd".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjnd = '" + pjnd + "'");
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjxn = '" + pjxn + "'");
			sql.append(" and a.pjxq = '" + pjxq + "'");
		}

		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.pk = ? ");
			}
			sql.append(" ) ");
		}

		sql.append(" order by a.xydm,a.zydm,a.bjdm ");
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				pkValue, new String[] { "xh", "xm", "nj", "xydm", "zydm",
						"bjdm", "xymc", "zymc", "bjmc" });

		return list;
	}
	
	/**
	 * ��ñ���������ڲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String[] getBshrbm(List<HashMap<String, String>> bshrList,
			String kzfw) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		
		//���Ʒ�Χ
		if ("nj".equalsIgnoreCase(kzfw)) {
			sql.append(" distinct nj bmdm");
		} else if ("xy".equalsIgnoreCase(kzfw)) {
			sql.append(" distinct xydm bmdm");
		} else if ("zy".equalsIgnoreCase(kzfw)) {
			sql.append(" distinct nj||zydm bmdm ");
		} else {
			sql.append(" distinct bjdm bmdm ");
		}
		
		sql.append(" from view_njxyzybj a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and exists (select 1 from view_xsjbxx b ");
		sql.append(" where a.bjdm = b.bjdm  ");
		
		if (bshrList != null && bshrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bshrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.xh = '" + bshrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(" ) ");
		
		DAO dao = DAO.getInstance();

		String[] bmdm = dao.getRs(sql.toString(), new String[]{}, "bmdm");

		return bmdm;
	}
	
	/**
	 * �����Ŀ��������
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmshrs(PjpyXmshForm model,
			String[] bmdm) {

		// ��Ŀ���ö���
		PjpyXmszModel xmszModel = model.getXmszModel();
		// ���Ʒ�Χ
		String kzfw = xmszModel.getKzfw();
		// ��Ŀ����
		String xmdm = model.getShxm();
		// ��˼���
		String shjb = model.getShjb();
		// ��������
		String sqzq = xmszModel.getSqzq();
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.bmdm, a.bmrs, nvl(b.ytgrs, 0) ytgrs from ");
		
		sql.append(" (select a.bmdm,nvl(a.qdrs,0) bmrs from xg_pjpy_rsszb a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.xmdm = ? ");
		sql.append(" and a.szfw = ? ");
		if (bmdm != null && bmdm.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bmdm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.bmdm = '" + bmdm[i] + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(") a");

		String dm = "";
		// ���Ʒ�Χ
		if ("nj".equalsIgnoreCase(kzfw)) {
			dm = "nj";
		} else if ("xy".equalsIgnoreCase(kzfw)) {
			dm = "xydm";
		} else if ("zy".equalsIgnoreCase(kzfw)) {
			dm = "nj||zydm";
		} else {
			dm = "bjdm";
		}
		
		sql.append(" left join (select b."+dm+" bmdm, count(1) ytgrs ");
		sql.append(" from xg_view_pjpy_xmsh b ");
		sql.append(" where 1 = 1 ");
		sql.append(" and b.xmdm = ? ");
		sql.append(" and b.shjb = ? ");
		// ������Ŀ��������
		if ("xn".equalsIgnoreCase(sqzq)) {
			sql.append(" and b.pjxn = '" + pjxn + "'");
		} else if ("nd".equalsIgnoreCase(sqzq)) {
			sql.append(" and b.pjnd = '" + pjnd + "'");
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			sql.append(" and b.pjxn = '" + pjxn + "'");
			sql.append(" and b.pjxq = '" + pjxq + "'");
		}
		sql.append(" and b.shzt = 'ͨ��' ");
		
		//�������
		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" and ");
				}
				sql.append(" b.pk <> '"+pkValue[i]+"' ");
			}
			sql.append(" ) ");
		}
		sql.append(" group by "+dm+") b on a.bmdm = b.bmdm ");
		sql.append(" order by  a.bmdm");
	              
	              
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm, kzfw, xmdm, shjb }, new String[] { "bmdm",
						"bmrs", "ytgrs" });

		return list;
	}

	/**
	 * ��÷Ǽ����Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmjdList(PjpyXmshForm model) {
		
		// ��Ŀ����
		String xmdm = model.getShxm();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm,a.fjddm,b.xmmc fjdmc from xg_pjpy_jdszb a ");
		sql.append(" ,xg_pjpy_pjxmwhb b where a.fjddm=b.xmdm and a.xmdm = ? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao
				.getList(sql.toString(), new String[] { xmdm }, new String[] {
						"xmdm", "fjddm", "fjdmc" });

		return list;
	}
	
	/**
	 * ����Ի�ý�ѧ���б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getYhdjxjList(PjpyXmshForm model,
			List<HashMap<String, String>> bshrList) {
		
		// ��Ŀ����
		String xmdm = model.getShxm();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh, a.xm, a.xmdm, a.pjxn, a.pjxq, a.pjnd,b.sqzq from ");
		sql.append(" (select a.*,b.shzt from ");
		sql.append(" (select a.xh, a.xm, a.xmdm, a.pjxn, a.pjxq, a.pjnd, ");
		sql.append(" a.lcid,max(a.shjb) shjb from xg_view_pjpy_xmsh a ");
		sql.append(" where 1 = 1 ");	
		sql.append(" and a.xmdm <> ? ");		
		
		// �������
		if (bshrList != null && bshrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bshrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xh = '" + bshrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(" group by a.xh, a.xm, a.xmdm, a.pjxn, a.pjxq, a.pjnd,a.lcid) a, ");
		sql.append(" xg_pjpy_pjxmshb b,xg_xtwh_spbz c ");
		sql.append(" where a.xh = b.xh and a.xmdm = b.xmdm and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq and a.pjnd = b.pjnd and b.xtgwid = c.spgw ");
		sql.append(" and a.lcid = c.splc and c.xh = a.shjb ");
		sql.append(" )a, xg_pjpy_pjxmwhb b where a.xmdm = b.xmdm and a.shzt = 'ͨ��' ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm }, new String[] { "xmdm", "xh", "xm",
						"pjxn", "pjxq", "pjnd","sqzq" });

		return list;
	}

	/**
	 * �����Ŀ�����Ϣ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmshInfoList(PjpyXmshForm model) {

		//����ֵ
		String pkValue = model.getPkValue();

		StringBuilder sql = new StringBuilder();

		sql.append(" select a.xh,a.xm,a.pjxn,a.pjxq,a.pjnd,a.mc,a.shjb,a.shzt,a.shyj,a.shsj ");
		sql.append(" from xg_view_pjpy_xmsh a where 1 = 1 ");
		sql.append(" and exists (select 1 from xg_view_pjpy_xmsh b ");
		sql.append(" where a.xmdm = b.xmdm ");
		sql.append(" and a.xh = b.xh ");
		sql.append(" and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and b.pk = ?) ");

		DAO dao = DAO.getInstance();
		System.out.println("����ֵ:....."+pkValue);
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pkValue }, new String[] { "xh", "xm", "pjxn",
						"pjxq", "pjnd", "mc", "shjb", "shzt", "shyj", "shsj" });

		return list;
	}
	
	/**
	 * �����Ŀ�����б��б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSyxmList(PjpyXmshForm model) {

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.tzxmdm xmdm,b.xmmc from xg_pjpy_pjfwtzb a ");
		sql.append(" ,xg_pjpy_pjxmwhb b where a.tzxmdm = b.xmdm ");
		sql.append(" and a.xmdm = ? ");
		sql.append(" and b.pjxn = ? ");
		sql.append(" and b.pjxq = ? ");
		sql.append(" and b.pjnd = ? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm,pjxn,pjxq,pjnd }, new String[] { "xmdm", "xmmc" });

		return list;
	}
	
	/**
	 * �����Ŀ�����б��б�
	 * 
	 * @author ΰ�����
	 */
	public Boolean getKfsy(PjpyXmshForm model, PjpyXmszModel xmszModel) {

		// ��Ŀ����
		String xmdm = model.getSyxm();
		// ѧ��
		String xh = model.getXh();
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// ��������
		String sqzq = xmszModel.getSqzq();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_pjpy_pjxmsqb a ");
		sql.append("where 1 = 1 ");
		sql.append(" and a.xmdm = '" + xmdm + "'");
		sql.append(" and a.xh = '" + xh + "'");
		// ������Ŀ��������
		if ("xn".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjxn = '" + pjxn + "'");
		} else if ("nd".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjnd = '" + pjnd + "'");
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.pjxn = '" + pjxn + "'");
			sql.append(" and a.pjxq = '" + pjxq + "'");
		}

		DAO dao = DAO.getInstance();

		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");

		boolean flag = false;
		
		if (Base.isNull(num) || "0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
}
