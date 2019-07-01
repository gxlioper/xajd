package xgxt.xszz;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.xszz.comm.XszzCommService;
import xgxt.xszz.commonN05.XszzCommonN05ActionForm;

public class XszzDAO extends CommonQueryDAO {

	/**
	 * ���ѧ����������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszzListInfo(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"xq", "xb", "szbm" };
		String[] queryLikeList = new String[] { "xh", "xm", "zgh" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		other_query = Base.isNull(other_query) ? "" : other_query;

		if (!Base.isNull(query)) {
			query += " " + other_query;
		} else {
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * ���ѧ�����������Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getXszzInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
	}

	/**
	 * ɾ�����ϴ��ļ�
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		DAO dao = new DAO();

		boolean flag = dao.fileDel(tableName, dzzd, pk, pkValue);
		
		return flag;
	}

	/**
	 * ���ϵͳ��ǰʱ��
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		
		DAO dao = DAO.getInstance();
		
		String now = dao.getNowTime(lx);
		
		return now;
	}

	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		
		DAO dao = DAO.getInstance();

		String value = dao.getOneValue(tableName, dm, pk, pkValue);
		
		return value;
	}
	
	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		
		boolean flag = dao.saveArrDate(sql);
		
		return flag;
	}
	
	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXszzList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();
			
		List<HashMap<String, String>> list = dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
		
		return list ;
	}
	
	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXszzxmList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" ,mrxm,xmmc from " + tableName +" where 1=1 ");
		if (!Base.isNull(pk)) {
			sql.append(" and " + pk + " = '" + pkValue + "' ");
		}
		sql.append(" order by mrxm desc,xmmc,dm nulls first ");
		return dao.getList(sql.toString(), new String[] {}, new String[] { "dm",
				"mc" });
	}
	
	/**
	 * ��������������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getShxmList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName +" where 1=1 ");
		if (!Base.isNull(pk)) {
			sql.append(" and " + pk + " = '" + pkValue + "' ");
		}
		sql.append(" and shjb <> '�������' ");
		sql.append(" order by dm nulls first");
		return dao.getList(sql.toString(), new String[] {}, new String[] { "dm",
				"mc" });
	}
	
	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param bsf(��ʶ��)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXszzLikeList(String tableName,
			String dm, String mc, String msg, String pk,String bsf, String pkValue) {

		DAO dao = DAO.getInstance();
		
		List<HashMap<String, String>> list = dao.getWhList(tableName, dm, mc, msg, pk, bsf, pkValue);
		
		return list ;
	}
	
	/**
	 * �������ά��������ֵ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "Ů" };
			mc = new String[] { "��", "Ů" };
		} else if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if ("shlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ���", "ͨ��", "��ͨ��" };
			mc = new String[] { "δ���", "ͨ��", "��ͨ��" };
		} else if ("shjb".equalsIgnoreCase(lx)) {
			dm = new String[] { "�������", "һ�����", "�������", "�������" };
			mc = new String[] { "�������", "һ�����", "�������", "�������" };
		} else if ("rsjb".equalsIgnoreCase(lx)) {
			dm = new String[] { "����Ҫ", "��Ҫ" };
			mc = new String[] { "����Ҫ", "��Ҫ" };
		} else if ("kzjb".equalsIgnoreCase(lx)) {
			dm = new String[] { "ѧԺ", "רҵ", "�༶" };
			mc = new String[] { "ѧԺ", "רҵ", "�༶" };
		} else if ("szsflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "������", "��" };
			mc = new String[] { "������", "��" };
		} else if ("sqzq".equalsIgnoreCase(lx)) {
			dm = new String[] { "������", "ѧ��", "���", "ѧ��" };
			mc = new String[] { "������", "ѧ��", "���", "ѧ��" };
		} else if ("sfje".equalsIgnoreCase(lx)) {
			dm = new String[] { "��Ҫ", "����Ҫ" };
			mc = new String[] { "��Ҫ", "����Ҫ" };
		} else if ("sffj".equalsIgnoreCase(lx)) {
			dm = new String[] { "�ּ�", "���ּ�" };
			mc = new String[] { "�ּ�", "���ּ�" };
		} else if ("jelx".equalsIgnoreCase(lx)) {
			dm = new String[] { "ȷ��ֵ", "����" };
			mc = new String[] { "ȷ��ֵ", "����" };
		} else if ("kgzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "��������", "�ر�����", "��Ŀ�ر�" };
			mc = new String[] { "��������", "�ر�����", "��Ŀ�ر�" };
		} else if ("pdsj".equalsIgnoreCase(lx)) {// ����ʱ��
			dm = new String[] { "����", "ǰ��" };
			mc = new String[] { "����", "ǰ��" };
		} else if ("jtcyzd".equalsIgnoreCase(lx)) {
			String xxdm = Base.xxdm;
			dm = getJtcyzd(xxdm);
			mc = getJtcyzd(xxdm);
		} else if ("tjlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", ">", ">=", "=", "<=", "<" };
			mc = new String[] { "-----��ѡ��-----", ">", ">=", "=", "<=", "<" };
		} else if ("zhftj".equalsIgnoreCase(lx)) {
			dm = new String[] { "", ">", "=", "<" };
			mc = new String[] { "", ">", "=", "<" };
		} else if ("xmlb".equalsIgnoreCase(lx)) {
			dm = new String[] { "��ѧ��", "�����ƺ�","��ѧ��", "���Ѳ���", "����" };
			mc = new String[] { "��ѧ��", "�����ƺ�","��ѧ��", "���Ѳ���", "����" };
		}else if ("xmlb_pj".equalsIgnoreCase(lx)) {
			dm = new String[] { "��ѧ��", "�����ƺ�" };
			mc = new String[] { "��ѧ��", "�����ƺ�" };
		}else if ("xmlb_zz".equalsIgnoreCase(lx)) {
			dm = new String[] {  "��ѧ��", "���Ѳ���", "����" };
			mc = new String[] {  "��ѧ��", "���Ѳ���", "����" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * ��ü�ͥ��Աά������
	 * 
	 * @author luojw
	 */
	private String[] getJtcyzd(String xxdm) {

		String[] title = null;

		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			title = new String[] { "", "����", "����", "��ϵ", "������ѧϰ����λ", "ְҵ",
					"��ϵ�绰", "�����ʱ�", "������", "������", "����״��" };
		} else if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			title = new String[] { "", "����", "����", "��ϵ", "������ѧϰ����λ", "ְҵ",
					"��ϵ�绰", "������", "��֧��", "����״��" };
		} else if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			title = new String[] { "", "����", "����", "��ϵ", "������ѧϰ����λ", "ְҵ",
					 "������", "����״��" };
		} else {
			title = new String[] { "", "����", "����", "��ϵ", "������ѧϰ����λ", "ְҵ",
					"��ϵ�绰", "������", "����״��" };
		}
		
		return title;
	}
	
	/**
	 * ����Ѿ�ͨ����˸ü�����������ѧԺ���������ϴ�ѧ��
	 * 
	 * @author luojw
	 */
	public String getJjknsRs_Hndx(String xn, String xydm, String knsjb,
			String shzd,String xh) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new 	StringBuilder ();
		sql.append("select count(xh) num from view_hddx_jjknssq a ");
		sql.append("where a.xn = ? ");
		sql.append("and a.xydm = ? ");
		sql.append("and a.knsjb = ? ");
		sql.append("and "+shzd+" = 'ͨ��' ");
		sql.append("and a.xh <> ? ");
		
		String[] inputValue = new String[]{xn,xydm,knsjb,xh};
		
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		
		return num;
	}
	
	/**
	 * ���ָ������ֶ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getTableZd(String tableName) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct zd from (select lower(COLUMN_NAME) zd ");
		sql.append(",length(lower(COLUMN_NAME)) cd ");
		sql.append("from user_tab_cols where table_name ");
		sql.append("in (upper('" + tableName + "'), upper('" + tableName
				+ "'))) order by cd");

		String[] zd = dao.getRs(sql.toString(), new String[] {}, "zd");

		return zd;
	}
	
	/**
	 * ��ð༶����
	 * 
	 * @author luojw
	 */
	public String getBjrs(HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		// ѧ��
		String xh = map.get("xh");

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from view_xsjbxx a ");
		sql.append("where a.bjdm = ");
		sql.append("(select b.bjdm from view_xsjbxx b where b.xh = ?) ");

		String[] inputValue = new String[] { xh };
		String num = dao.getOneRs(sql.toString(), inputValue, "num");

		return num;

	}

	/**
	 * ����moedelֵ
	 * 
	 * @author luojw
	 */
	public void setModelValue(XszzTyForm model) {

		// �꼶
		String nj = model.getQueryequals_nj();
		// ѧԺ
		String xydm = model.getQueryequals_xydm();
		// רҵ
		String zydm = model.getQueryequals_zydm();
		// �༶
		String bjdm = model.getQueryequals_bjdm();
		// ���
		String nd = model.getQueryequals_nd();
		// �·�
		String yf = model.getQueryequals_yf();
		// ��������
		String bzlx = model.getQueryequals_bzlx();
		// ѧ��
		String xh = model.getQuerylike_xh();
		// ����
		String xm = model.getQuerylike_xm();

		model.setNj(nj);
		model.setXydm(xydm);
		model.setZydm(zydm);
		model.setBjdm(bjdm);
		model.setNd(nd);
		model.setYf(yf);
		model.setBzlx(bzlx);
		model.setXh(xh);
		model.setXm(xm);
		
	}
	
	/**
	 * ���ѧ������������Ϣ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getLnXszzList(String[] xmb,
			String[] xmdm, String[] xmmc, String[] sqzq, String[] shjb,
			String xh) {

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = null;

		if (xmb != null && xmb.length > 0) {

			StringBuilder sql = new StringBuilder();

			for (int i = 0; i < xmb.length; i++) {
				if (i != 0) {
					sql.append(" union all ");
				}
				sql.append(" select '" + xmmc[i] + "' xmmc,a.xmdm,");
				sql.append(" '" + sqzq[i] + "' sqzq,");
				sql.append("(select b.xqdm from xqdzb b where a.xq = b.xqdm) xqmc,");
				sql.append(" a.xn,a.xq,a.nd,a.sqsj,a.xmzzjb,a.xmzzje from ");
				sql.append(xmb[i]);
				sql.append(" a where a.xh = '" + xh + "' ");
				sql.append(" and a.xmdm = '" + xmdm[i] + "' ");
				if ("һ�����".equalsIgnoreCase(shjb[i])) {
					sql.append(" and a.shzt1 = 'ͨ��' ");
				} else if ("�������".equalsIgnoreCase(shjb[i])) {
					sql.append(" and a.shzt2 = 'ͨ��' ");
				} else if ("�������".equalsIgnoreCase(shjb[i])) {
					sql.append(" and a.shzt3 = 'ͨ��' ");
				}
			}
			
			String[] outputValue = new String[] { "xmdm", "xmmc", "sqzq", "xn",
					"xq", "xqmc", "nd", "sqsj", "xmzzjb","xmzzje" };
			list = dao.getList(sql.toString(), new String[] {}, outputValue);
		}

		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// ��Ŀ����
				String dm = map.get("xmdm");
				// ��������
				String zq = map.get("sqzq");
				// ѧ��
				String xn = map.get("xn");
				// ѧ��
				String xq = map.get("xqmc");
				// ���
				String nd = map.get("nd");
				//��Ŀ����
				String xmzzjb = map.get("xmzzjb");
				// ��Ŀ���
				String xmzzje = map.get("xmzzje");
				
				if (!XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(dm)
						&& !XszzXmdm.XSZZ_QTXX.equalsIgnoreCase(dm)) {
					
					String zzsj = "����������";
				
					if ("ѧ��".equalsIgnoreCase(zq)) {
						zzsj = xn + "ѧ��";
					} else if ("ѧ��".equalsIgnoreCase(zq)) {
						zzsj = xn + "ѧ��" + xq + "����";
					} else if ("���".equalsIgnoreCase(zq)) {
						zzsj = nd + "���";
					}
					
					if (Base.isNull(xmzzje)) {
						xmzzje = "���漰���";
					}
					if (Base.isNull(xmzzjb)) {
						xmzzjb = "���漰����";
					}
					map.put("xmzzjb", xmzzjb);
					map.put("xmzzje", xmzzje);
					map.put("zzsj", zzsj);
					
					resultList.add(map);
				}
			}
		}
		
		return resultList;

	}
	
	/**
	 * �ж��Ƿ���������N32��
	 * 
	 * @author luojw
	 */
	public Boolean isKns(HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		boolean flag = false;

		// ѧ��
		String xh = map.get("xh");
		// ѧ��
		String xn = map.get("xn");
		// ѧ��
		String xq = map.get("xq");
		// ���
		String nd = map.get("nd");
		// ����ѧ��
		String pjxn = map.get("pjxn");
		// ����ѧ��
		String pjxq = map.get("pjxq");
		// �������
		String pjnd = map.get("pjnd");
		// ��������Ŀ����
		String xmdm = XszzXmdm.XSZZ_KNS;
		// ��������������
		String sqzq = map.get("sqzq");
		// ��������˼���
		String shjb = map.get("shjb");

		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xszz_shztb where 1 = 1 ");
		sql.append(" and xmdm = '" + xmdm + "' ");
		sql.append(" and xh = '" + xh + "'");

		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and xn = '" + xn + "'" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and xn = '" + xn + "'" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and xq = '" + xq + "'" : "");
		sql.append("���".equalsIgnoreCase(sqzq) ? " and nd = '" + nd + "'" : "");

		sql.append("һ�����".equalsIgnoreCase(shjb) ? " and shzt1 = 'ͨ��'" : "");
		sql.append("�������".equalsIgnoreCase(shjb) ? " and shzt2 = 'ͨ��'" : "");
		sql.append("�������".equalsIgnoreCase(shjb) ? " and shzt3 = 'ͨ��'" : "");

		sql.append("and xmzzjb not like '��%' ");
//		sql.append("and xmzzjb not like '��%' ");
		
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");

		if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
	// ======================����made by ΰ���luo===============================
	
	/**
	 * ��������ȫ����ʼ��
	 * @return
	 * @throws Exception 
	 */
	public boolean dknxCsh() throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "delete from zgdzdx_xsdknxb";
		
		boolean result = dao.runUpdate(sql, new String[] {});
		
		if (result) {
			sql = "insert into zgdzdx_xsdknxb(bjdm) select bjdm from view_njxyzybj group by bjdm";
			result = dao.runUpdate(sql, new String[] {});
		}
		
		return result;
	}
	
	/**
	 * �������޲��ֳ�ʼ��
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public boolean dknxCsh(String[] bjdm) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		
		String[] sql = new String[bjdm.length];
		StringBuilder delSql = new StringBuilder();
		
		delSql.append("delete from zgdzdx_xsdknxb where ");
		
		for (int i = 0 ; i < bjdm.length ; i++) {
			delSql.append("bjdm=?");
			
			if ( i != bjdm.length-1) {
				delSql.append(" or ");
			}
			sql[i] = "insert into zgdzdx_xsdknxb(bjdm,dknx,dkkssj) values ('"+bjdm[i]+"','0','')";
			
		}
		result = dao.runUpdate(delSql.toString(), bjdm);
		if (result) {
			int[] checkResult = dao.runBatch(sql);
			result = dao.checkBatch(checkResult);
		}
		
		return result;
	}
	
	
	/**
	 * ����������Ŀ�����ȡ������Ŀ��ϸ��Ϣ
	 * ��DWR����
	 * @author qph
	 * @param xmdm
	 * @return
	 */
	public HashMap<String,String> getXszzInfoByXmdm(String xmdm) {
		DAO dao = DAO.getInstance();
		String sql = "select * from xszz_zzxmb where xmdm=?";
		
		return dao.getMapNotOut(sql, new String[] {xmdm});
		
	}
	
	/**
	 * ������Ŀ����ȡ��Ŀ��Ϣ
	 * @param xmlb
	 * @return
	 */
	public List<HashMap<String,String>>getXmxxByRssz(String xmlb,String query){
		DAO dao = DAO.getInstance();
		String sql=" select xmdm dm,xmmc mc from xszz_zzxmb where xmlb like ? and rskz='��Ҫ' and kgzt<>'��Ŀ�ر�' "+query;
		return dao.getList(sql, new String[]{xmlb}, new String[]{"dm","mc"});
	}
	
	
	/**
	 * ������Ŀ����ȡ��Ŀ��Ϣ
	 * @param xmlb
	 * @return
	 */
	public List<HashMap<String,String>>getXmdmByLb(String query){
		DAO dao = DAO.getInstance();
		String sql=" select xmdm dm,xmmc mc,xmlb lb from xszz_zzxmb "+query+" and rskz='��Ҫ' and kgzt<>'��Ŀ�ر�' ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc","lb"});
	}
	
	/**
	 * @describe ɾ�����ϴ��ļ�
	 * @author luojw
	 * @throws Exception
	 */
	public boolean filePathDel(String xh,String sqsj)
			throws Exception {
		XszzCommService service = new XszzCommService();
		boolean flag = false;
		String pk = "xh||sqsj";
		String pkValue = xh+sqsj;
		String realTable = "xszz_knsb";
		try {
		if (!Base.isNull(pkValue)) {
			service.fileDel(realTable, "scdz", pk, pkValue);
			flag= true;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
