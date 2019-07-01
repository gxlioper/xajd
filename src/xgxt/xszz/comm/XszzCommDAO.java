package xgxt.xszz.comm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzDAO;
import xgxt.xszz.XszzTyForm;

import common.Globals;
import common.XszzXmdm;

public class XszzCommDAO extends XszzDAO {

	/**
	 * ��������
	 * 
	 * @author luojw
	 */
	public String getXmbh(String tableName, String zd) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select min(" + zd + ") minbh from " + tableName);

		String minbh = dao.getOneRs(sql.toString(), new String[] {}, "minbh");

		sql = new StringBuilder();

		sql.append("select d.bh from (select rownum num, c.bh, c.tempbh ");
		sql.append("from (select a.bh, (to_char(b.bh) - to_char(a.bh)) tempbh ");
		sql.append("from (select rownum num, t.bh from (select t." + zd);
		sql.append(" bh from " + tableName + " t order by " + zd + ") t) a, ");
		sql.append("(select rownum - 1 num, t.bh from (select t." + zd);
		sql.append(" bh from " + tableName + " t order by " + zd + ") t) b ");
		sql.append("where a.num = b.num) c where c.tempbh > 1) d where d.num = 1 ");

		String bzdm1 = dao.getOneRs(sql.toString(), new String[] {}, "bh");

		int newDm = 0;
		if (bzdm1 != null && !"".equals(bzdm1)) {
			newDm = Integer.parseInt(bzdm1) + 1;
		}

		sql = new StringBuilder();
		sql.append(" select MAX(t." + zd + ")+1 bh from " + tableName + " t");
		String bzdm2 = dao.getOneRs(sql.toString(), new String[] {}, "bh");

		if (bzdm2 == null || "".equals(bzdm2)) {
			newDm = 1;
		}
		if ((bzdm1 == null || "".equals(bzdm1))
				&& (bzdm2 != null && !"".equals(bzdm2))) {
			newDm = Integer.parseInt(bzdm2);
		}
		
		String str = !"0001".equalsIgnoreCase(minbh) ? "0001" : String
				.valueOf(newDm);

		if (str.length() == 1) {
			str = "000" + str;
		} else if (str.length() == 2) {
			str = "00" + str;
		} else if (str.length() == 3) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * �޸Ŀ���״̬
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateKgzt(XszzTyForm model) throws Exception {

		boolean flag = false;
		// ��Ŀ����
		String[] zzxmdm = model.getZzxmdm();
		// ��Ŀ����
		String[] zzxmkg = model.getZzxmkg();

		StringBuffer sql = new StringBuffer();

		if (zzxmdm != null && zzxmdm.length > 0) {

			String[] arr_sql = new String[zzxmdm.length];

			for (int i = 0; i < zzxmdm.length; i++) {
				sql = new StringBuffer();

				sql.append("update xszz_zzxmb set ");
				sql.append("kgzt = '" + zzxmkg[i] + "' ");
				sql.append("where xmdm = '" + zzxmdm[i] + "' ");

				arr_sql[i] = sql.toString();
			}

			flag = saveArrDate(arr_sql);
		}

		return flag;
	}

	/**
	 * �����Ŀ�����б�����Ŀ�رգ�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZzxmList() {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '----��ѡ��-----'mc from dual union");
		sql.append(" select distinct(xmdm) dm, xmmc mc ");
		sql.append(" from xszz_zzxmb ");
		sql.append(" where kgzt <>'��Ŀ�ر�' ");
		sql.append(" and rskz = '��Ҫ'");
		sql.append(" order by dm nulls first,dm");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}

	/**
	 * �����Ŀ�����б�����Ŀ�رգ�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZzxmList(String mklx,String query) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct(xmdm) dm, xmmc mc,xmlb lb ");
		sql.append(" from xszz_zzxmb ");
		sql.append(" where kgzt <>'��Ŀ�ر�' ");
		sql.append(" and rskz = '��Ҫ' ");
		sql.append(query);
		if("pj".equalsIgnoreCase(mklx)){
			sql.append(" and xmlb in ('��ѧ��', '�����ƺ�')");
		}else if("zz".equalsIgnoreCase(mklx)){
			sql.append(" and xmlb in ('��ѧ��', '���Ѳ���', '����')");
		}
		
		
		sql.append(" order by dm nulls first,dm");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc","lb" });
	}
	
	/**
	 * �����Ŀ�����б�����Ŀ�رգ�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZzxmList(String mklx,String xmlb,String query) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct(xmdm) dm, xmmc mc,xmlb lb ");
		sql.append(" from xszz_zzxmb ");
		sql.append(" where kgzt <>'��Ŀ�ر�' ");
		sql.append(" and rskz = '��Ҫ' ");
		sql.append(query);
		if("pj".equalsIgnoreCase(mklx)){
			sql.append(" and xmlb in ('��ѧ��', '�����ƺ�')");
		}else if("zz".equalsIgnoreCase(mklx)){
			sql.append(" and xmlb in ('��ѧ��', '���Ѳ���', '����')");
		}
		
		if(!Base.isNull(xmlb)){
			sql.append(" and xmlb = '");
			sql.append(xmlb);
			sql.append("' ");
		}
		sql.append(" order by dm nulls first,dm");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc","lb" });
	}
	
	/**
	 * ����ѧԺ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXyBlrs(XszzTyForm model,HashMap<String, String> xmInfo) throws Exception {

		boolean flag = false;

		DAO dao = DAO.getInstance();

		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ����ʱ��
		String pdsj = xmInfo.get("pdsj");
		// ���ż���
		String bmjb = "xy";
		// ��������
		String rssx = model.getRssx();
		// /����
		String bl = model.getBl();
		// ���䲿��
		String[] bmdm = model.getBmdm();
		
		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq, pdsj, model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
			model.setXn(xn);
			model.setXq(xq);
			model.setNd(nd);
		}

		StringBuilder query = new StringBuilder();

		query.append("where 1=1 ");

		if (bmdm != null && bmdm.length > 0) {// ѡ��ѧԺ
			query.append(" and ( ");
			for (int i = 0; i < bmdm.length; i++) {
				if (i == 0) {
					query.append("b.xydm = '" + bmdm[i] + "' ");
				} else {
					query.append("or b.xydm = '" + bmdm[i] + "' ");
				}
			}
			query.append(") ");
		}
		
		// ɾ��ԭ����Ŀ��������
		flag = delXmRs(model);
		
		StringBuilder sql = new StringBuilder();

		sql = new StringBuilder();
		sql.append("select distinct b.xydm, a.num from view_njxyzybj b, ");
		sql.append("(select a.xydm, count(1) num from view_xsjbxx a group by a.xydm) a ");
		sql.append(query);
		sql.append(" and a.xydm = b.xydm");

		List<HashMap<String, String>> xyList = dao.getList(sql.toString(),
				new String[] {}, new String[] { "xydm", "num" });

		if (flag) {

			if (xyList != null && xyList.size() > 0) {

				String[] arrSql = new String[xyList.size()];

				float yfprs = 0;

				for (int i = 0; i < xyList.size(); i++) {
					sql = new StringBuilder();

					HashMap<String, String> map = xyList.get(i);

					String xydm = map.get("xydm");
					String num = map.get("num");
					String szrs = "0";

					// ��������
					if (!Base.isNull(num) && !Base.isNull(bl)) {
						szrs = String.valueOf(Math.round(Float.parseFloat(num)
								* Float.parseFloat(bl) / 100));
					}

					// ���������Ƿ񳬹�����
					yfprs += Float.parseFloat(szrs);
					if (!Base.isNull(rssx) && yfprs > Float.parseFloat(rssx)) {
						return false;
					}

					sql.append("insert into xszz_xmrsb (xmdm,");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "nd," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xq," : "");
					sql.append("bmjb,bmdm,szrs) values(");
					sql.append("'" + xmdm + "',");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "',"
							: "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "'" + nd + "',"
							: "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "',"
							: "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xq + "',"
							: "");
					sql.append("'" + bmjb + "',");
					sql.append("'" + xydm + "',");
					sql.append("'" + szrs + "')");

					arrSql[i] = sql.toString();
				}

				flag = saveArrDate(arrSql);
			}
		}

		return flag;
	}

	/**
	 * ����רҵ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveZyBlrs(XszzTyForm model,HashMap<String, String> xmInfo) throws Exception {

		boolean flag = false;

		DAO dao = DAO.getInstance();

		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ����ʱ��
		String pdsj = xmInfo.get("pdsj");
		// ���ż���
		String bmjb = "zy";
		// ��������
		String rssx = model.getRssx();
		// /����
		String bl = model.getBl();
		// �꼶
		String nj = model.getNj();
		// ѧԺ����
		String xydm = model.getXydm();
		// ���䲿��
		String[] bmdm = model.getBmdm();

		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq, pdsj, model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
		}
		
		StringBuilder query = new StringBuilder();

		query.append("where 1=1 ");
		query.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "'");
		query.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "'");

		if (bmdm != null && bmdm.length > 0) {// ѡ��רҵ
			query.append(" and ( ");
			for (int i = 0; i < bmdm.length; i++) {
				if (i == 0) {
					query.append("b.zydm = '" + bmdm[i] + "' ");
				} else {
					query.append("or b.zydm = '" + bmdm[i] + "' ");
				}
			}
			query.append(") ");
		}

//		ɾ��ԭ����Ŀ��������	
		flag = delXmRs(model);
		
		StringBuilder sql = new StringBuilder();

		sql = new StringBuilder();
		sql.append("select distinct b.nj,b.zydm,a.num from view_njxyzybj b, ");
		sql.append("(select a.nj,a.zydm, count(1) num from view_xsjbxx a group by a.nj,a.zydm) a ");
		sql.append(query);
		sql.append(" and a.nj = b.nj and a.zydm=b.zydm");

		List<HashMap<String, String>> zyList = dao.getList(sql.toString(),
				new String[] {}, new String[] { "nj", "zydm", "num" });

		if (flag) {

			if (zyList != null && zyList.size() > 0) {

				String[] arrSql = new String[zyList.size()];

				float yfprs = 0;

				for (int i = 0; i < zyList.size(); i++) {
					sql = new StringBuilder();

					HashMap<String, String> map = zyList.get(i);

					String zydm = map.get("zydm");
					String sznj = map.get("nj");
					String num = map.get("num");
					String szrs = "0";

					// ��������
					if (!Base.isNull(num) && !Base.isNull(bl)) {
						szrs = String.valueOf(Math.round(Float.parseFloat(num)
								* Float.parseFloat(bl) / 100));
					}

					// ���������Ƿ񳬹�����
					yfprs += Float.parseFloat(szrs);
					if (!Base.isNull(rssx) && yfprs > Float.parseFloat(rssx)) {
						return false;
					}

					sql.append("insert into xszz_xmrsb (xmdm,");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "nd," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xq," : "");
					sql.append("bmjb,nj,bmdm,szrs) values(");
					sql.append("'" + xmdm + "',");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "',"
							: "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "'" + nd + "',"
							: "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "',"
							: "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xq + "',"
							: "");
					sql.append("'" + bmjb + "',");
					sql.append("'" + sznj + "',");
					sql.append("'" + zydm + "',");
					sql.append("'" + szrs + "')");

					arrSql[i] = sql.toString();
				}

				flag = saveArrDate(arrSql);
			}
		}

		return flag;
	}
	
	/**
	 * ����༶��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveBjBlrs(XszzTyForm model,HashMap<String, String> xmInfo) throws Exception {

		boolean flag = false;

		DAO dao = DAO.getInstance();

		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ����ʱ��
		String pdsj = xmInfo.get("pdsj");
		// ���ż���
		String bmjb = "bj";
		// ����
		String bl = model.getBl();
		// ��������
		String rssx = model.getRssx();
		// �꼶
		String nj = model.getNj();
		// ѧԺ����
		String xydm = model.getXydm();
		// רҵ����
		String zydm = model.getZydm();
		// ���䲿��
		String[] bmdm = model.getBmdm();

		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq, pdsj, model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
			model.setXn(xn);
			model.setXq(xq);
			model.setNd(nd);
		}
		
		StringBuilder query = new StringBuilder();

		query.append("where 1=1 ");
		query.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "'");
		query.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "'");
		query.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "'");
		
		if (bmdm != null && bmdm.length > 0) {// ѡ��༶
			query.append(" and ( ");
			for (int i = 0; i < bmdm.length; i++) {
				if (i == 0) {
					query.append("b.bjdm = '" + bmdm[i] + "' ");
				} else {
					query.append("or b.bjdm = '" + bmdm[i] + "' ");
				}
			}
			query.append(") ");
		}

		// ɾ��ԭ����Ŀ��������
		flag = delXmRs(model);
		
		StringBuilder sql = new StringBuilder();

		sql = new StringBuilder();
		sql.append("select distinct b.nj,b.bjdm,a.num from view_njxyzybj b, ");
		sql.append("(select a.nj,a.bjdm, count(1) num from view_xsjbxx a group by a.nj,a.bjdm) a ");
		sql.append(query);
		sql.append(" and a.nj = b.nj and a.bjdm=b.bjdm");

		List<HashMap<String, String>> bjList = dao.getList(sql.toString(),
				new String[] {}, new String[] { "nj", "bjdm", "num" });

		if (flag) {

			if (bjList != null && bjList.size() > 0) {

				String[] arrSql = new String[bjList.size()];

				float yfprs = 0;
				
				for (int i = 0; i < bjList.size(); i++) {
					sql = new StringBuilder();

					HashMap<String, String> map = bjList.get(i);

					String bjdm = map.get("bjdm");
					String sznj = map.get("nj");
					String num = map.get("num");
					String szrs = "0";

					// ��������
					if (!Base.isNull(num) && !Base.isNull(bl)) {
						szrs = String.valueOf(Math.round(Float.parseFloat(num)
								* Float.parseFloat(bl) / 100));
					}
					
					//���������Ƿ񳬹�����
					yfprs += Float.parseFloat(szrs);
					if (!Base.isNull(rssx) && yfprs > Float.parseFloat(rssx)) {
						return false;
					}
					
					sql.append("insert into xszz_xmrsb (xmdm,");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "nd," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xq," : "");
					sql.append("bmjb,nj,bmdm,szrs) values(");
					sql.append("'" + xmdm + "',");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "',"
							: "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "'" + nd + "',"
							: "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "',"
							: "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xq + "',"
							: "");
					sql.append("'" + bmjb + "',");
					sql.append("'" + sznj + "',");
					sql.append("'" + bjdm + "',");
					sql.append("'" + szrs + "')");

					arrSql[i] = sql.toString();
				}

				flag = saveArrDate(arrSql);
			}
		}

		return flag;
	}

	/**
	 * �����������ã��������䣩
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveRsszRsfp(XszzTyForm model,HashMap<String, String> xmInfo) throws Exception {
		
		boolean flag = false;
		
		DAO dao = DAO.getInstance();

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ����ʱ��
		String pdsj = xmInfo.get("pdsj");
		// ���ż���
		String bmjb = model.getBmjb();
		// ��������
		String fprs = model.getFprs();

		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq, pdsj, model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
			model.setXn(xn);
			model.setXq(xq);
			model.setNd(nd);
		}
		
		String query = setGetBmInfoQuery(model);

		//ɾ��ԭ����Ŀ��������	
		flag = delXmRs(model);

		String[] outputValue = null;
		String dm = "";
		if ("xy".equalsIgnoreCase(bmjb)) {
			dm = "xydm";
			outputValue = new String[] { "xydm", "num" };
		} else if ("zy".equalsIgnoreCase(bmjb)) {
			dm = "zydm";
			outputValue = new String[] { "nj", "zydm", "num" };
		} else if ("bj".equalsIgnoreCase(bmjb)) {
			dm = "bjdm";
			outputValue = new String[] { "nj", "bjdm", "num" };
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct b."+dm+",a.num ");
		sql.append("xy".equalsIgnoreCase(bmjb) ? "" : ",b.nj ");
		sql.append("from view_njxyzybj b,(select ");
		sql.append("xy".equalsIgnoreCase(bmjb) ? "" : "a.nj,");
		sql.append("a."+dm+", count(1) num from ");
		sql.append("view_xsjbxx a group by ");
		sql.append("xy".equalsIgnoreCase(bmjb) ? "" : "a.nj,");
		sql.append("a."+dm+") a ");
		sql.append(query);
		sql.append("xy".equalsIgnoreCase(bmjb)?" and a.xydm = b.xydm ":"");
		sql.append("zy".equalsIgnoreCase(bmjb)?" and a.nj = b.nj and a.zydm = b.zydm ":"");
		sql.append("bj".equalsIgnoreCase(bmjb)?" and a.nj = b.nj and a.bjdm = b.bjdm ":"");
		
		List<HashMap<String, String>> infoList = dao.getList(sql.toString(),
				new String[] {}, outputValue);
		
		if (flag) {

			if (infoList != null && infoList.size() > 0) {

				String[] arrSql = new String[infoList.size()];

				String szrs = "0";
				
				if (!Base.isNull(fprs) && !"0".equalsIgnoreCase(fprs)) {
					szrs = String.valueOf(Float.parseFloat(fprs)
							/ infoList.size());

					// ȡ��������
					String temp_szrs="";
					for(int i = 0;i<szrs.length();i++){
						String value = String.valueOf(szrs.charAt(i));
						if(".".equalsIgnoreCase(value)){
							break;
						}
						temp_szrs +=value;
					}
					szrs = (!Base.isNull(temp_szrs)) ? temp_szrs : szrs;
				}
				
				for (int i = 0; i < infoList.size(); i++) {
					
					sql = new StringBuilder();

					HashMap<String, String> map = infoList.get(i);

					String czdm = map.get(dm);
					String sznj = map.get("nj");
					sznj = Base.isNull(sznj) ? "ȫ��" : sznj;
					
					sql.append("insert into xszz_xmrsb (xmdm,");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "nd," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xq," : "");
					sql.append("bmjb,nj,bmdm,szrs) values(");
					sql.append("'" + xmdm + "',");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "'," : "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "'" + nd + "'," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "'," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xq + "'," : "");
					sql.append("'" + bmjb + "',");
					sql.append("'" + sznj + "',");
					sql.append("'" + czdm + "',");
					sql.append("'" + szrs + "')");

					arrSql[i] = sql.toString();
				}

				flag = saveArrDate(arrSql);
			}
		}
		
		return flag;
	}

	/**
	 * @param bmjb
	 * @param nj
	 * @param xydm
	 * @param zydm
	 * @param bmdm
	 * @param query
	 */
	private String setGetBmInfoQuery(XszzTyForm model) {

		StringBuilder query = new StringBuilder();
		// ���ż���
		String bmjb = model.getBmjb();
		// ��������
		String dm = "xydm";
		// �꼶
		String nj = model.getNj();
		// ѧԺ����
		String xydm = model.getXydm();
		// רҵ����
		String zydm = model.getZydm();
		// ���䲿��
		String[] bmdm = model.getBmdm();

		query.append("where 1=1 ");
		
		if ("bj".equalsIgnoreCase(bmjb)) {
			dm = "bjdm";
			query.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "'");
			query.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm
					+ "'");
			query.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm
					+ "'");
		} else if ("zy".equalsIgnoreCase(bmjb)) {
			dm = "zydm";
			query.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "'");
			query.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm
					+ "'");
		}

		if (bmdm != null && bmdm.length > 0) {// ѡ��༶
			query.append(" and ( ");
			for (int i = 0; i < bmdm.length; i++) {
				if (i == 0) {
					query.append("b." + dm + " = '" + bmdm[i] + "' ");
				} else {
					query.append("or b." + dm + " = '" + bmdm[i] + "' ");
				}
			}
			query.append(") ");
		}

		return query.toString();
	}

	/**
	 * @param dao
	 * @param xn
	 * @param xq
	 * @param nd
	 * @param xmdm
	 * @param sqzq
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	private boolean delXmRs(XszzTyForm model) throws Exception {

		DAO dao = DAO.getInstance();

		boolean flag;

		// ��Ŀ����
		String xmdm = model.getXmdm();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xszz_xmrsb a where a.xmdm = ? ");
		sql.append(setRsszQuesy(model));

		flag = dao.runUpdate(sql.toString(), new String[] { xmdm });
		return flag;
	}
	
	/**
	 * @param xn
	 * @param xq
	 * @param nd
	 * @param sqzq
	 * @param sql
	 */
	private String setRsszQuesy(XszzTyForm model) {

		DAO dao = DAO.getInstance();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��������
		String sqzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm", xmdm);
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		
		StringBuilder sql = new StringBuilder();

		// ��������Ϊѧ��
		if ("ѧ��".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.xn = '" + xn + "'");
		}
		// ��������Ϊ���
		else if ("���".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.nd = '" + nd + "'");
		}
		// ��������Ϊѧ��
		else if ("ѧ��".equalsIgnoreCase(sqzq)) {
			sql.append(" and a.xn = '" + xn + "'");
			sql.append(" and a.xq = '" + xq + "'");
		}
		// ������
		else {

		}
		return sql.toString();
	}
	
	/**
	 * ���������ĿѧԺ����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getRsszList(XszzTyForm model,
			HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		// ��Ŀ����
		String bmjb = model.getBmjb();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ѧԺ
		String xydm = model.getQueryequals_xydm();
		// רҵ
		String zydm = model.getQueryequals_zydm();
		// �༶
		String bjdm = model.getQueryequals_bjdm();
		// �꼶
		String nj = model.getQueryequals_nj();
		
		String[] inputValue = new String[] { bmjb, xmdm };
		String[] outputValue = null;
		
		StringBuilder sql = new StringBuilder();

		//�����ֶΣ�xydm,bjdm�ȣ�
		String dm = "";
		
		if ("xy".equalsIgnoreCase(bmjb)) {// ѧԺ

			sql.append(" select distinct a.xydm, a.xymc, b.nj sznj,");

			dm = "xydm";

			outputValue = new String[] { "xydm", "iscz", "szrs", "sznj", "xymc" };

		} else if ("zy".equalsIgnoreCase(bmjb)) {// רҵ

			sql.append(" select distinct a.xydm,a.xymc,a.nj,a.zydm, a.zymc,");

			dm = "zydm";

			outputValue = new String[] { "zydm", "iscz", "szrs", "sznj",
					"xymc", "nj", "zymc" };

		} else if ("bj".equalsIgnoreCase(bmjb)) {// �༶

			sql.append(" select distinct a.xydm,a.xymc,a.nj,a.zydm, a.zymc,");
			sql.append(" a.bjdm,a.bjmc,");
			
			dm = "bjdm";

			outputValue = new String[] { "bjdm", "iscz", "szrs", "sznj",
					"xymc", "nj", "zymc", "bjmc" };

		}
		
		sql.append(" b.xn, b.xq, b.nd,");
		if (!"xy".equalsIgnoreCase(bmjb)) {// ��ѧԺ
			sql.append("nvl(b.nj,a.nj) sznj,");
		}
		sql.append(" case when xn is null then 'no' else 'yes' end iscz,");
		sql.append(" nvl(b.szrs,0) szrs from view_njxyzybj a left join xszz_xmrsb b");
		sql.append(" on a." + dm + " = b.bmdm ");
		if (!"xy".equalsIgnoreCase(bmjb)) {// ��ѧԺ
			sql.append(" and to_char(a.nj) = b.nj ");
		}
		sql.append(" and b.bmjb = ? ");
		sql.append(" and b.xmdm = ? ");
		sql.append(getSqzqQuery(model, map));
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(nj) ? "" : " and a.nj = '" + nj + "'");
		sql.append(Base.isNull(xydm) ? "" : " and a.xydm = '" + xydm + "'");
		sql.append(Base.isNull(zydm) ? "" : " and a.zydm = '" + zydm + "'");
		sql.append(Base.isNull(bjdm) ? "" : " and a.bjdm = '" + bjdm + "'");
		
		if ("xy".equalsIgnoreCase(bmjb)) {// ѧԺ
			sql.append(" order by a." + dm);
		}
		if ("zy".equalsIgnoreCase(bmjb)) {// רҵ
			sql.append(" order by a.xydm,a.nj,a." + dm);
		}
		if ("bj".equalsIgnoreCase(bmjb)) {// �༶
			sql.append(" order by a.xydm,a.nj,a.zydm,a." + dm);
		}
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue,
				outputValue);

		return list;
	}
	
	/**
	 * �޸���������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateRssz(XszzTyForm model, HashMap<String, String> map)
			throws Exception {

		boolean flag = false;

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ���ż���
		String bmjb = model.getBmjb();
		// ���Ŵ���
		String[] bmdm = model.getPrimarykey_checkVal();
		// ��������
		String[] szrs = model.getSzrs();
		// �Ƿ����
		String[] iscz = model.getIscz();
		// �����꼶
		String[] sznj = model.getSznj();
		// ���Ƽ���
		String kzjb = model.getKzjb();
		// ��������
		String sqzq = map.get("sqzq");
		// ����ʱ��
		String pdsj = map.get("pdsj");
		String zqQuery = "";
		// ��ǰѧ��
		String xn = Base.currXn;
		// ��ǰѧ��
		String xq = Base.currXq;
		// ��ǰ���
		String nd = Base.currNd;
		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq, pdsj, model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
		}
		// ��������Ϊѧ��
		if ("ѧ��".equalsIgnoreCase(sqzq)) {
			zqQuery = "xn = '" + xn + "'";
		}
		// ��������Ϊ���
		else if ("���".equalsIgnoreCase(sqzq)) {
			zqQuery = "nd = '" + nd + "'";
		}
		// ��������Ϊѧ��
		else if ("ѧ��".equalsIgnoreCase(sqzq)) {
			zqQuery = "xn||xq = '" + xn + xq + "'";
		}
		// ������
		else {
			zqQuery = "xn||xq||nd = '������'";
		}

		StringBuffer sql = new StringBuffer();
		StringBuffer del = new StringBuffer();
		
		if (bmdm != null && bmdm.length > 0) {

			String[] arr_sql = new String[bmdm.length];
			String[] del_sql = new String[bmdm.length];
			for (int i = 0; i < bmdm.length; i++) {

				sql = new StringBuffer();
				del = new StringBuffer();
				
				if ("yes".equalsIgnoreCase(iscz[i])) {
					sql.append(" update xszz_xmrsb set ");
					sql.append(" szrs = '" + szrs[i] + "' ");
					sql.append(" where bmjb = '" + bmjb + "' ");
					sql.append(" and bmdm = '" + bmdm[i] + "' ");
					sql.append(" and xmdm = '" + xmdm + "' ");
					sql.append((!"ѧԺ".equalsIgnoreCase(kzjb) && !Base
							.isNull(sznj[i])) ? " and nj = '" + sznj[i] + "'"
							: "");
					sql.append(" and " + zqQuery);
				} else {
					del.append("delete from xszz_xmrsb where 1= 1 ");
					del.append(" and " + zqQuery);
					del.append(" and xmdm='" + xmdm + "'");
					del.append(" and bmjb='" + bmjb + "'");
					del.append(" and bmdm='" + bmdm[i] + "'");
					
					sql.append(" insert into xszz_xmrsb (xmdm,");
					sql.append((!"ѧԺ".equalsIgnoreCase(kzjb) && !Base
							.isNull(sznj[i])) ? "nj," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "nd," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xn," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "xq," : "");
					sql.append("bmjb,bmdm,szrs) values(");
					sql.append("'" + xmdm + "',");
					sql.append((!"ѧԺ".equalsIgnoreCase(kzjb) && !Base
							.isNull(sznj[i])) ? "'" + sznj[i] + "'," : "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "',"
							: "");
					sql.append("���".equalsIgnoreCase(sqzq) ? "'" + nd + "',"
							: "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xn + "',"
							: "");
					sql.append("ѧ��".equalsIgnoreCase(sqzq) ? "'" + xq + "',"
							: "");
					sql.append("'" + bmjb + "',");
					sql.append("'" + bmdm[i] + "',");
					sql.append("'" + szrs[i] + "')");
				}
				del_sql[i] = del.toString();
				arr_sql[i] = sql.toString();
			}
			flag = saveArrDate(del_sql);
			if (flag) {
				flag = saveArrDate(arr_sql);
			}
		}

		return flag;
	}

	/**
	 * ����ѷ�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYfprsList(XszzTyForm model,
			HashMap<String, String> xmInfo) throws Exception {

		DAO dao = DAO.getInstance();

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��˿��Ƽ���
		String kzjb = model.getKzjb();
		
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append(" select b.bmjb,b.nj,b.bmdm,b.szrs from xszz_xmrsb b ");
		sql.append(" where b.xmdm = ? ");
		// ѧԺ
		if ("ѧԺ".equalsIgnoreCase(kzjb)) {
			sql.append(" and b.bmjb = 'xy' ");
		}
		// רҵ
		else if ("רҵ".equalsIgnoreCase(kzjb)) {
			sql.append(" and b.bmjb = 'zy' ");
		}
		// �༶
		else {
			sql.append(" and b.bmjb = 'bj' ");
		}
		sql.append(getSqzqQuery(model, xmInfo));

		String[] inputValue = new String[] { xmdm };
		String[] outputValue = new String[] { "bmjb", "nj", "bmdm", "szrs" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ���������Ŀ�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmrsInfo(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��˿��Ƽ���
		String kzjb = model.getKzjb();
		
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.rssx,b.szrs from  ");
		sql.append(" (select xmdm,rssx from xszz_zzxmb where xmdm = ?) a left join ");
		sql.append(" (select b.xmdm,sum(b.szrs) szrs from xszz_xmrsb b where b.xmdm = ? ");
		sql.append(getSqzqQuery(model, xmInfo));
		// ѧԺ
		if ("ѧԺ".equalsIgnoreCase(kzjb)) {
			sql.append(" and b.bmjb = 'xy' ");
		}
		// רҵ
		else if ("רҵ".equalsIgnoreCase(kzjb)) {
			sql.append(" and b.bmjb = 'zy' ");
		}
		// �༶
		else {
			sql.append(" and b.bmjb = 'bj' ");
		}
		sql.append(" group by b.xmdm)b  on a.xmdm = b.xmdm");

		String[] inputValue = new String[] { xmdm, xmdm };
		String[] outputValue = new String[] { "rssx", "szrs" };

		HashMap<String, String> map = dao.getMap(sql.toString(), inputValue,
				outputValue);

		return map;
	}

	/**
	 * @param dao
	 * @param xmdm
	 * @return
	 */
	private String getSqzqQuery(XszzTyForm model, HashMap<String, String> map) {

		// ��ǰѧ��
		String xn = Base.currXn;
		model.setXn(xn);
		// ��ǰѧ��
		String xq = Base.currXq;
		model.setXq(xq);
		// ��ǰ���
		String nd = Base.currNd;
		model.setNd(nd);
		// ��������
		String sqzq = map.get("sqzq");
		// ����ʱ��
		String pdsj = map.get("pdsj");

		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq, pdsj, model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
		}

		StringBuilder sql = new StringBuilder();

		// ��������Ϊѧ��
		if ("ѧ��".equalsIgnoreCase(sqzq)) {
			sql.append(" and b.xn = '" + xn + "'");
		}
		// ��������Ϊ���
		else if ("���".equalsIgnoreCase(sqzq)) {
			sql.append(" and b.nd = '" + nd + "'");
		}
		// ��������Ϊѧ��
		else if ("ѧ��".equalsIgnoreCase(sqzq)) {
			sql.append(" and b.xn = '" + xn + "'");
			sql.append(" and b.xq = '" + xq + "'");
		}
		// ������
		else {

		}
		return sql.toString();
	}

	/**
	 * ���ѧ����������Ŀ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsYsqXmList(XszzTyForm model,
			String[] xmb) {

		DAO dao = DAO.getInstance();

		// ѧ��
		String xh = model.getXh();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (xmb != null && xmb.length > 0) {
			StringBuilder sql = new StringBuilder();
//			for (int i = 0; i < xmb.length; i++) {
//				if (i != 0) {
//					sql.append(" union all ");
//				}
//				sql.append(" select xn,xq,nd,xmdm,sqsj, ");
//				sql.append(" bzrsh,fdysh,xysh,xxsh,shzt1,shzt2,shzt3 from ");
//				sql.append(xmb[i]);
//				sql.append(" where 1 = 1 ");
//				sql.append(" and xh = '" + xh + "' ");
//				
//			}
			
			sql.append(" select xn,xq,nd,xmdm,sqsj, ");
			sql.append(" bzrsh,fdysh,xysh,xxsh,shzt1,shzt2,shzt3 from ");
			sql.append(" xszz_shztb ");
			sql.append(" where 1 = 1 ");
			sql.append(" and xh = '" + xh + "' ");
			
			String[] inputValue = new String[] {};
			String[] outputValue = new String[] { "xn", "xq", "nd","sqsj", "xmdm",
					"bzrsh", "fdysh", "xysh", "xxsh", "shzt1" , "shzt2", "shzt3"};
			list = dao.getList(sql.toString(), inputValue, outputValue);
		}
		return list;
	}
	
	/**
	 * �����Ŀ���������б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmRsszList(XszzTyForm model,
			String[] xmdm) {

		DAO dao = DAO.getInstance();
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (xmdm != null && xmdm.length > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append(" select xn,xq,nd,xmdm,bmjb,nj,bmdm,szrs ");
			sql.append(" from xszz_xmrsb");
			for (int i = 0; i < xmdm.length; i++) {
				if (i == 0) {
					sql.append(" where xmdm = '" + xmdm[i] + "' ");
				} else {
					sql.append(" or xmdm = '" + xmdm[i] + "' ");
				}
			}
			String[] inputValue = new String[] {};
			String[] outputValue = new String[] { "xn", "xq", "nd", "xmdm",
					"bmjb", "nj", "bmdm", "szrs" };
			list = dao.getList(sql.toString(), inputValue, outputValue);
		}
		return list;
	}
	
	/**
	 * ���ѧ������ѧԺ��רҵ�༶�������ͨ��ѧ���б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getXmYtgList(XszzTyForm model,
			HashMap<String, String> stuInfo, String[] xmb, String[] xmdm ,String[] kzjb) {

		DAO dao = DAO.getInstance();

		// ѧԺ
		String xydm = stuInfo.get("xydm");
		// �꼶
		String nj = stuInfo.get("nj");
		// רҵ
		String zydm = stuInfo.get("zydm");
		// �༶
		String bjdm = stuInfo.get("bjdm");
		//��ǰ���
		String dqnd = Base.currNd;
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		if (xmb != null && xmb.length > 0) {
			
			StringBuilder sql = new StringBuilder();
			
//			for (int i = 0; i < xmb.length; i++) {
//				if (i != 0) {
//					sql.append(" union all ");
//				}
//				sql.append(" select xn,xq,nd,xmdm,xh, ");
//				sql.append(" bzrsh,fdysh,xysh,xxsh,shzt1,shzt2,shzt3 from ");
//				sql.append(xmb[i] + " a ");
//				sql.append(" where 1 = 1 ");
//				sql.append(" and a.xmdm = '"+xmdm[i]+"'");
//				sql.append(" and exists (select 1 from view_xsjbxx b ");
//				sql.append(" where a.xh = b.xh ");
//				if ("�༶".equalsIgnoreCase(kzjb[i])) {// ���Ƽ���Ϊ�༶
//					sql.append(" and b.bjdm = '" + bjdm + "' ");
//				} else if ("רҵ".equalsIgnoreCase(kzjb[i])) {// ���Ƽ���Ϊרҵ
//					sql.append(" and b.zydm = '" + zydm + "' ");
//					sql.append(" and b.nj = '" + nj + "' ");
//				} else if ("ѧԺ".equalsIgnoreCase(kzjb[i])) {// ���Ƽ���ΪѧԺ
//					sql.append(" and b.xydm = '" + xydm + "' ");
//				}
//				sql.append(" )");
//				
//			}
			
			sql.append(" select a.xn,a.xq,a.nd,a.xmdm,count(1) rs from (");
			sql.append(" select xn,xq,nd,xmdm,a.xh,sqsj, ");
			sql.append("(select c.kzjb from xszz_zzxmb c where a.xmdm = c.xmdm) kzjb, ");
			sql.append("(select c.shjb from xszz_zzxmb c where a.xmdm = c.xmdm) shjb, ");
			sql.append(" bzrsh,fdysh,xysh,xxsh,shzt1,shzt2,shzt3, ");
			sql.append(" b.xydm,b.zydm,b.bjdm,b.nj from ");
			sql.append(" xszz_shztb a,view_xsjbxx b where a.xh = b.xh) a");
			sql.append(" where ");	
			sql.append(" ( ");
			sql.append(" (a.kzjb = 'ѧԺ' and a.xydm = '" + xydm + "') or ");
			sql.append(" (a.kzjb = 'רҵ' and a.zydm = '" + zydm + "' and a.nj = '" + nj + "') or ");
			sql.append(" (a.kzjb = '�༶' and a.bjdm = '" + bjdm + "') or ");
			sql.append(" a.kzjb is null ");
			sql.append(" ) ");
			sql.append(" and (");
			sql.append("(a.shjb = 'һ�����' and a.shzt1 = 'ͨ��') or ");
			sql.append("(a.shjb = '�������' and a.shzt2 = 'ͨ��') or ");
			sql.append("(a.shjb = '�������' and a.shzt3 = 'ͨ��'))");
			sql.append("and a.sqsj like '" + dqnd + "%' ");
			sql.append("group by a.xn,a.xq,a.nd,a.xmdm");
			
			String[] inputValue = new String[] {};
			String[] outputValue = new String[] { "xn", "xq", "nd", "xmdm",
					"rs" };
//			String[] outputValue = new String[] { "xn", "xq", "nd", "xmdm",
//					"xh", "bzrsh", "fdysh", "xysh", "xxsh", "shzt1", "shzt2",
//					"shzt3" };
			list = dao.rsToVator(sql.toString(), inputValue, outputValue);
		}
		return list;
	}

	/**
	 * �Ƿ������Ŀ����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean isFhXmTj(String xh, String xmtjb, String xmtj, String xmtjz,
			String xmdm) {

		DAO dao = DAO.getInstance();

		boolean flag = false;
		String tableName = "";

		String[] inputValue = new String[] { xh };
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from ");
		try {
			tableName = getTjszSql(xmtjb, xmdm, xmtjz);
			if (!Base.isNull(tableName)) {
				sql.append(tableName);
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = ? ");
		if (!"xszz_knsb".equals(xmtjb) && !"zyfb".equals(xmtjb)
				&& !"zhfb".equals(xmtjb) && !"xszz_bjgkms".equals(xmtjb)
				&& !"pjcjb".equals(xmtjb)) {
			sql.append(" and " + xmtj + " = ? ");
			inputValue = new String[] { xh, xmtjz };
		}
		String num = dao.getOneRs(sql.toString(), inputValue, "num");

		if (Base.isNull(num) || "0".equalsIgnoreCase(num)) {
			flag = false;
		} else {
			flag = true;
		}

		return flag;
	}
	
	/**
	 * ����������ԴSQL
	 * @param xmtjb
	 * @return
	 * @throws Exception 
	 */
	private String getTjszSql(String xmtjb,String xmdm,String xmtjz) throws Exception {
		DAO dao = DAO.getInstance();
		String sqzq = dao.getOneRs("select * from xszz_zzxmb where xmdm=? ", new String[] {xmdm}, "sqzq");
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and xn =(select dqxn from xtszb)" : "");
		sql.append("���".equalsIgnoreCase(sqzq) ? " and nd =(select dqnd from xtszb)" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and xn =(select dqxn from xtszb) and xq =(select dqxq from xtszb)" : "");
			
		StringBuilder table = new StringBuilder();
		
		if ("view_xsjbxx".equals(xmtjb)) {
			return "view_xsjbxx";
		} else if ("xszz_wjqk".equals(xmtjb)) {
			return "(select a.*,'��' nowj from view_xsjbxx a where not exists (select 1 from view_wjcf where xh=a.xh"+sql.toString()+"))";
		} else if ("xszz_knsb".equals(xmtjb)){
			if (!Base.isNull(xmtjz)) {
				
				String shjb = getOneValue("xszz_zzxmb", "shjb", "xmdm", "5002");
				
				String[] knsjb = xmtjz.split("!!@@!!");
				table.append(" (select a.* from xszz_knsb a ");
				table.append(" where 1 = 1 ");
				if("һ�����".equalsIgnoreCase(shjb)){
					table.append(" and a.shzt1 = 'ͨ��' ");
				}else if("�������".equalsIgnoreCase(shjb)){
					table.append(" and a.shzt2 = 'ͨ��' ");
				}else if("�������".equalsIgnoreCase(shjb)){
					table.append(" and a.shzt3 = 'ͨ��' ");
				}
							
				if (knsjb != null && knsjb.length > 0) {
					table.append(" and (");
					for (int i = 0; i < knsjb.length; i++) {
						if (i == 0) {
							table.append(" xmzzjb = '" + knsjb[i] + "' ");
						}else{
							table.append(" or xmzzjb = '" + knsjb[i] + "' ");
						}
					}
					table.append(" )");
				}
				//---------2010.9.27 lr-----------
				String knssqzq = dao.getOneRs("select * from xszz_zzxmb where xmdm=? ", new String[] {XszzXmdm.XSZZ_KNS}, "sqzq");
				sql = new StringBuilder();
				sql.append("ѧ��".equalsIgnoreCase(knssqzq) ? " and xn =(select dqxn from xtszb)" : "");
				sql.append("���".equalsIgnoreCase(knssqzq) ? " and nd =(select dqnd from xtszb)" : "");
				sql.append("ѧ��".equalsIgnoreCase(knssqzq) ? " and xn =(select dqxn from xtszb) and xq =(select dqxq from xtszb)" : "");
				//---------end2010.9.27 lr-----------
				table.append(sql);
				table.append(" )");
			}
		} else if ("xszz_bjgqk".equals(xmtjb)) {
			return "(select a.*,'��' nobjg from view_xsjbxx a where not exists (select 1 from cjb where xh=a.xh and cj<60"
					+ sql.toString() + "))";
		} else if ("zyfb".equals(xmtjb) || "zhfb".equals(xmtjb)) {
			
			String pjxn = Base.getJxjsqxn();// ����ѧ��
			String pjxq = Base.getJxjsqxq();// ����ѧ��
			String pjnd = Base.getJxjsqnd();// �������

			String zqSql = "select xn,xq,nd,zcxn,zcnd,zcxq from pjpy_pjzqb where rownum = 1";
			HashMap<String, String> map = dao.getMap(zqSql, new String[] {},
					new String[] { "xn", "xq", "nd", "zcxn", "zcnd", "zcxq" });

			String xn = map.get("xn");
			String xq = map.get("xq");
			String nd = map.get("nd");
			String zcxn = map.get("zcxn");
			String zcnd = map.get("zcnd");
			String zcxq = map.get("zcxq");
			
			boolean flag = Base.isNull(zcxn) && Base.isNull(zcnd)
					&& Base.isNull(zcxq) ? false : true;
			
			table.append(" (select a.xh from view_pjpy_zhszcpb a ");
			table.append(" where 1 = 1 ");
			if (flag) {
				table.append("checked".equalsIgnoreCase(zcxn) ? " and a.xn = '" + pjxn + "'" : "");
				table.append("checked".equalsIgnoreCase(zcxq) ? " and a.xn = '" + pjxn + "'" : "");
				table.append("checked".equalsIgnoreCase(zcxq) ? " and a.xq = '" + pjxq + "'" : "");
				table.append("checked".equalsIgnoreCase(zcnd) ? " and a.nd = '" + pjnd + "'" : "");
			} else {
				table.append("checked".equalsIgnoreCase(xn) ? " and a.xn = '" + pjxn + "'" : "");
				table.append("checked".equalsIgnoreCase(xq) ? " and a.xn = '" + pjxn + "'" : "");
				table.append("checked".equalsIgnoreCase(xq) ? " and a.xq = '" + pjxq + "'" : "");
				table.append("checked".equalsIgnoreCase(nd) ? " and a.nd = '" + pjnd + "'" : "");
			}
			table.append("zyfb".equals(xmtjb) ? " and mc = '����������' and jb = '2'": " and jb = '1' ");
			table.append(" and a.fs " + xmtjz);
			table.append(" ) ");
		} else if ("zzhz".equals(xmtjb)) {
			// ��������
		}//----------�������Ŀ��  qph---------------- 
		else if ("xszz_bjgkms".equals(xmtjb)) {
			table.append(" (select * from view_xsjbxx a where not exists (select 1 from ");
			table.append("(select * from (select count(*) bjgkms,xh from  cjb where cj<60 ");
			table.append(sql);
			table.append(" group by xh) where bjgkms>");
			table.append(xmtjz);
			table.append(") where xh=a.xh))");
		}//----------------ƽ���ɼ� qph-------------------- 
		else if ("pjcjb".equals(xmtjb)){
			
			String pjxn = Base.getJxjsqxn();// ����ѧ��
			String pjxq = Base.getJxjsqxq();// ����ѧ��
			String pjnd = Base.getJxjsqnd();// �������
			
			sql = new StringBuilder();
			sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and xn ='"+pjxn+"'" : "");
			sql.append("���".equalsIgnoreCase(sqzq) ? " and nd ='"+pjnd+"'" : "");
			sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and xn ='"+pjxn+"' and xq ='"+pjxq+"'" : "");
			
			table.append("(select * from (");
			table.append(" select xh,round(zcj/kms,2) pjcj from (select sum(cj) zcj,count(cj) kms,xh from view_zhhcjb where 1=1 ");
			table.append(sql);
			table.append(" group by xh order by xh)) where pjcj");
			table.append(xmtjz);
			table.append(")");
			
		} else if ("jqpjfb".equals(xmtjb)){
			//��Ȩƽ����
			table.append("(select * from xg_view_gzdx_jqpjf where xn='")
			     .append(Base.getJxjsqxn())
			     .append("' and jqpjf")
			     .append(xmtjz)
			     .append(")");
		} else if ("jqpjjdb".equals(xmtjb)){
			//��Ȩƽ������
			table.append("(select * from xg_view_gzdx_jqpjf where xn='")
			     .append(Base.getJxjsqxn())
			     .append("' and jqpjjd")
			     .append(xmtjz)
			     .append(")");
		} else {
			throw new Exception("û�����������");
		}
		
		return table.toString();
	}
	
	/**
	 * �����Ŀ����������������Ŀ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmSqShList(XszzTyForm model,
			String[] xmdm, String[] xmlb, String[] xmmc, String[] xmb,
			String[] sqzq, String[] shjb, String[] pdsj, String[] tjjbOne,
			String[] tjjbTwo, String[] tjjbThr) {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		if (xmb != null && xmb.length > 0) {
			
//			StringBuilder sql = new StringBuilder();
			
			StringBuilder xmSql = new StringBuilder();
			StringBuilder sqSql = new StringBuilder();
			StringBuilder xySql = new StringBuilder();
			StringBuilder shSql = new StringBuilder();
			
			for (int i = 0; i < xmb.length; i++) {
				if (i != 0) {
//					sql.append(" union all ");
					
					xmSql.append(" union all ");
					sqSql.append(" union all ");
					xySql.append(" union all ");
					shSql.append(" union all ");
				}

				xmSql.append(" select '" + xmdm[i] + "' xmdm ");
				xmSql.append(" ,'" + xmmc[i] + "' xmmc ");
				xmSql.append(" ,'" + xmlb[i] + "' xmlb ");
				xmSql.append(" from dual ");
				
				sqSql.append(" select a.xmdm, count(1) sqrs from " + xmb[i] + " a ");
				sqSql.append(getXmshTjQuery(model, xmdm, sqzq, pdsj, i));
				sqSql.append(" group by a.xmdm ");

				xySql.append(" select a.xmdm, count(1) xshrs from " + xmb[i] + " a ");
				xySql.append(getXmshTjQuery(model, xmdm, sqzq, pdsj, i));
				xySql.append(getXmshjbQuery(model, shjb, tjjbOne, tjjbTwo, tjjbThr, i, "�����"));
				xySql.append(" group by a.xmdm ");

				shSql.append(" select a.xmdm, count(1) shrs from " + xmb[i] + " a ");
				shSql.append(getXmshTjQuery(model, xmdm, sqzq, pdsj, i));
				shSql.append(getXmshjbQuery(model, shjb, tjjbOne, tjjbTwo,
						tjjbThr, i, "�����"));
				shSql.append(" group by a.xmdm ");
				
//				sql.append(" select '" + xmdm[i] + "' xmdm ");
//				sql.append(" ,'" + xmmc[i] + "' xmmc ");
//				sql.append(" ,'" + xmlb[i] + "' xmlb ");
//				sql.append(" ,nvl(a.sqrs,0) sqrs ");
//				sql.append(" ,nvl(b.xshrs,0) xshrs ");
//				sql.append(" ,nvl(c.shrs,0) shrs ");
//				sql.append(" from dual ");
//				// ===================����Ŀ��������=======================
//				sql.append(" left join (");
//				sql.append(" select a.xmdm, count(1) sqrs from "+xmb[i]+" a ");
//				sql.append(getXmshTjQuery(model, sqzq, pdsj,i));
//				sql.append(" group by a.xmdm ");
//				sql.append(" ) a on a.xmdm = '" + xmdm[i] + "'");
//				// ===================����Ŀ���������=======================
//				sql.append(" left join (");
//				sql.append(" select a.xmdm, count(1) xshrs from "+xmb[i]+" a ");
//				sql.append(getXmshTjQuery(model, sqzq,pdsj, i));
//				sql.append(getXmshjbQuery(model, shjb, tjjbOne, tjjbTwo, tjjbThr, i, "�����"));
//				sql.append(" group by a.xmdm ");
//				sql.append(" ) b on b.xmdm = '" + xmdm[i] + "'");
//				// ===================����Ŀ���������=======================
//				sql.append(" left join (");
//				sql.append(" select a.xmdm, count(1) shrs from " + xmb[i]
//						+ " a ");
//				sql.append(getXmshTjQuery(model, sqzq, pdsj, i));
//				sql.append(getXmshjbQuery(model, shjb, tjjbOne, tjjbTwo, tjjbThr, i, "�����"));
//				sql.append(" group by a.xmdm ");
//				sql.append(" ) c on c.xmdm = '" + xmdm[i] + "'");				
			}
			
//			String[] inputValue = new String[] {};
//			String[] outputValue = new String[] { "xmdm", "xmmc", "xmlb",
//					"sqrs", "xshrs", "shrs" };
			
			String[] outputValue = new String[] { "xmdm", "xmmc", "xmlb" };
			
			List<HashMap<String, String>> xmList = dao.getList(
					xmSql.toString(), new String[] {}, outputValue);

			outputValue = new String[] { "xmdm", "sqrs" };

			List<HashMap<String, String>> sqList = dao.getList(
					sqSql.toString(), new String[] {}, outputValue);
			
			outputValue = new String[] { "xmdm", "xshrs" };

			List<HashMap<String, String>> xyList = dao.getList(
					xySql.toString(), new String[] {}, outputValue);
			
			outputValue = new String[] { "xmdm", "shrs" };

			List<HashMap<String, String>> shList = dao.getList(
					shSql.toString(), new String[] {}, outputValue);
			
			//System.out.println(sql);	
			//list = dao.getList(sql.toString(), inputValue, outputValue);
			System.out.println("sqSql:"+sqSql);
			System.out.println("xySql:"+xySql);
			System.out.println("shSql:"+shSql);
			for (HashMap<String, String> map : xmList) {

				String dm = map.get("xmdm");

				map.put("sqrs", "0");
				map.put("xshrs", "0");
				map.put("shrs", "0");
				
				for (HashMap<String, String> sqMap : sqList) {

					String sqdm = sqMap.get("xmdm");
					// ��������
					String sqrs = sqMap.get("sqrs");

					if (sqdm.equalsIgnoreCase(dm)) {
						map.put("sqrs", sqrs);
						break;
					}
				}

				for (HashMap<String, String> xyMap : xyList) {

					String xydm = xyMap.get("xmdm");
					// ��Ҫ����
					String xshrs = xyMap.get("xshrs");

					if (xydm.equalsIgnoreCase(dm)) {
						map.put("xshrs", xshrs);
						break;
					}
				}

				for (HashMap<String, String> shMap : shList) {

					String shdm = shMap.get("xmdm");
					// ���������
					String shrs = shMap.get("shrs");

					if (shdm.equalsIgnoreCase(dm)) {

						map.put("shrs", shrs);
						break;
					}
				}

				list.add(map);
			}
			
		}
		
		return list;
	}

	/**
	 * �����Ŀ���ͳ�Ƽ�������
	 * @param shjb
	 * @param tjjbOne
	 * @param tjjbTwo
	 * @param lx
	 * @param sql
	 * @param i
	 */
	private String getXmshjbQuery(XszzTyForm model, String[] shjb,
			String[] tjjbOne, String[] tjjbTwo, String[] tjjbThr, int i,
			String tjlx) {
		
		// �û�����
		String lx = model.getLx();

		StringBuilder sql = new StringBuilder();

		// һ�����
		if ("һ�����".equalsIgnoreCase(shjb[i])) {			
			if ("�����".equalsIgnoreCase(tjlx)) {
				sql.append(" and (");
				sql.append(" bzrsh <> 'δ���' ");
				sql.append(" or ");
				sql.append(" fdysh <> 'δ���' ");
				sql.append(" or ");
				sql.append(" xysh <> 'δ���' ");
				sql.append(" or ");
				sql.append(" xxsh <> 'δ���' ");
				sql.append(" ) ");
			}else{
				sql.append(" and (");
				sql.append(" bzrsh = 'δ���' ");
				sql.append(" and ");
				sql.append(" fdysh = 'δ���' ");
				sql.append(" and ");
				sql.append(" xysh = 'δ���' ");
				sql.append(" and ");
				sql.append(" xxsh = 'δ���' ");
				sql.append(" ) ");
			}
		}
		// �ɼ����
		else if ("�������".equalsIgnoreCase(shjb[i])) {
			// ��˼���1
			String[] shjb1 = tjjbOne[i].split("-");
			// ��˼���2
			String[] shjb2 = tjjbTwo[i].split("-");
			// ����1��־
			boolean jb1 = false;
			// ����2��־
			boolean jb2 = false;
			// ����ֶ�
			String[] shzd = new String[2];
			;
			// �û�Ϊ����Ա
			if ("fdy".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "";
			}
			// �û�Ϊ������
			if ("bzr".equalsIgnoreCase(lx)) {
				shzd[0] = "bzrsh";
				shzd[1] = "";
			}
			// �û�Ϊ�����μ渨��Ա
			if ("jd".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "bzrsh";
			}
			// �û�ΪѧԺ�û�
			if ("xy".equalsIgnoreCase(lx)) {
				shzd[0] = "xysh";
				shzd[1] = "";
			}
			// �û�ΪѧУ�û�
			if ("xx".equalsIgnoreCase(lx)) {
				shzd[0] = "xxsh";
				shzd[1] = "";
			}

			if (shjb1 != null && shjb1.length > 0) {
				for (String jb : shjb1) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb1 = true;
						break;
					}
				}
			}

			if (shjb2 != null && shjb2.length > 0) {
				for (String jb : shjb2) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb2 = true;
						break;
					}
				}
			}
			if ("�����".equalsIgnoreCase(tjlx)) {
				// ������ڼ���1
				if (jb1) {
					sql.append(" and (");
					for (int j = 0; j < shjb1.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb1[j] + " <> 'δ���' ");
					}
					sql.append(" ) ");
				}
				// ������ڼ���2
				if (jb2) {
					sql.append(" and (");
					for (int j = 0; j < shjb2.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb2[j] + " <> 'δ���' ");					
					}
					sql.append(" ) ");
				}
			}
			
			if ("�����".equalsIgnoreCase(tjlx)) {
				// ������ڼ���1
				if (jb1) {
					sql.append(" and (");
					for (int j = 0; j < shjb1.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb1[j] + " = 'δ���' ");
					}
					sql.append(" ) ");

					sql.append(" and (");
					for (int j = 0; j < shjb2.length; j++) {
						if (j != 0) {
							sql.append(" and ");
						}
						sql.append(shjb2[j] + " <> 'ͨ��' ");
					}
					sql.append(" ) ");
				}		
				// ������ڼ���2
				if (jb2) {
					sql.append(" and (");
					for (int j = 0; j < shjb1.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb1[j] + " = 'ͨ��' ");
					}
					sql.append(" ) ");
					
					sql.append(" and (");
					for (int j = 0; j < shjb2.length; j++) {
						if (j != 0) {
							sql.append(" and ");
						}
						sql.append(shjb2[j] + " = 'δ���' ");
					}
					sql.append(" ) ");
				}
			}
		}
		// �������
		else if ("�������".equalsIgnoreCase(shjb[i])) {
			// ��˼���1
			String[] shjb1 = tjjbOne[i].split("-");
			// ��˼���2
			String[] shjb2 = tjjbTwo[i].split("-");
			// ��˼���3
			String[] shjb3 = tjjbThr[i].split("-");
			// ����1��־
			boolean jb1 = false;
			// ����2��־
			boolean jb2 = false;
			// ����3��־
			boolean jb3 = false;
			// ����ֶ�
			String[] shzd = new String[2];

			// �û�Ϊ����Ա
			if ("fdy".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "";
			}
			// �û�Ϊ������
			if ("bzr".equalsIgnoreCase(lx)) {
				shzd[0] = "bzrsh";
				shzd[1] = "";
			}
			// �û�Ϊ�����μ渨��Ա
			if ("jd".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "bzrsh";
			}
			// �û�ΪѧԺ�û�
			if ("xy".equalsIgnoreCase(lx)) {
				shzd[0] = "xysh";
				shzd[1] = "";
			}
			// �û�ΪѧУ�û�
			if ("xx".equalsIgnoreCase(lx)) {
				shzd[0] = "xxsh";
				shzd[1] = "";
			}

			if (shjb1 != null && shjb1.length > 0) {
				for (String jb : shjb1) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb1 = true;
						break;
					}
				}
			}

			if (shjb2 != null && shjb2.length > 0) {
				for (String jb : shjb2) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb2 = true;
						break;
					}
				}
			}
			
			if (shjb3 != null && shjb3.length > 0) {
				for (String jb : shjb3) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb3 = true;
						break;
					}
				}
			}
			
			if ("�����".equalsIgnoreCase(tjlx)) {
				// ������ڼ���1
				if (jb1) {
					sql.append(" and (");
					for (int j = 0; j < shjb1.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb1[j] + " <> 'δ���' ");
					}
					sql.append(" ) ");
				}
				// ������ڼ���2
				if (jb2) {
					sql.append(" and (");
					for (int j = 0; j < shjb2.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb2[j] + " <> 'δ���' ");					
					}
					sql.append(" ) ");
				}
				// ������ڼ���3
				if (jb3) {
					sql.append(" and (");
					for (int j = 0; j < shjb3.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb3[j] + " <> 'δ���' ");
					}
					sql.append(" ) ");
				}
			}
			
			if ("�����".equalsIgnoreCase(tjlx)) {
				// ������ڼ���1
				if (jb1) {
					sql.append(" and (");
					for (int j = 0; j < shjb1.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb1[j] + " = 'δ���' ");
					}
					sql.append(" ) ");

					sql.append(" and (");
					for (int j = 0; j < shjb2.length; j++) {
						if (j != 0) {
							sql.append(" and ");
						}
						sql.append(shjb2[j] + " <> 'ͨ��' ");
					}
					sql.append(" ) ");
				}	
				// ������ڼ���2
				if (jb2) {
					sql.append(" and (");
					for (int j = 0; j < shjb1.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb1[j] + " = 'ͨ��' ");
					}
					sql.append(" ) ");
					
					sql.append(" and (");
					for (int j = 0; j < shjb2.length; j++) {
						if (j != 0) {
							sql.append(" and ");
						}
						sql.append(shjb2[j] + " = 'δ���' ");
					}
					sql.append(" ) ");
					
					sql.append(" and (");
					for (int j = 0; j < shjb3.length; j++) {
						if (j != 0) {
							sql.append(" and ");
						}
						sql.append(shjb3[j] + " <> 'ͨ��' ");
					}
					sql.append(" ) ");
				}
				// ������ڼ���3
				if (jb3) {
					sql.append(" and (");
					for (int j = 0; j < shjb2.length; j++) {
						if (j != 0) {
							sql.append(" or ");
						}
						sql.append(shjb2[j] + " = 'ͨ��' ");
					}
					sql.append(" ) ");
					
					sql.append(" and (");
					for (int j = 0; j < shjb3.length; j++) {
						if (j != 0) {
							sql.append(" and ");
						}
						sql.append(shjb3[j] + " = 'δ���' ");
					}
					sql.append(" ) ");
				}
			}
		}
		return sql.toString();
	}

	/**
	 * �����Ŀ���ͳ������
	 * 
	 * @param sqzq
	 * @param model
	 * @param i
	 */
	private String getXmshTjQuery(XszzTyForm model, String[] xmdm,String[] sqzq,
			String[] pdsj, int i) {
		
		// ��ǰѧ��
		String xn = model.getXn();
		// ��ǰѧ��
		String xq = model.getXq();
		// ��ǰ���
		String nd = model.getNd();
		// �û�ְ����
		String zgh = model.getZgh();
		// �û�����
		String lx = model.getLx();
		// �û����ڲ���
		String userDep = model.getUserDep();
		
		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj[i])) {
			HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq[i], pdsj[i],
					model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
		}
		  
		StringBuilder sql = new StringBuilder();
		
		sql.append(" where 1 = 1 ");
		sql.append("ѧ��".equalsIgnoreCase(sqzq[i]) ? " and a.xn = '" + xn + "'" : "");
		sql.append("���".equalsIgnoreCase(sqzq[i]) ? " and a.nd = '" + nd + "'" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq[i]) ? " and a.xn = '" + xn + "'"	: "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq[i]) ? " and a.xq = '" + xq + "'"	: "");
		sql.append(" and a.xmdm = '" + xmdm[i] + "'");	
		// �û�Ϊ����Ա
		if ("fdy".equalsIgnoreCase(lx)) {
			sql.append(" and exists (select 1 from "+Base.xsxxb+" b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from fdybjb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + zgh + "')) ");
		}
		// �û�Ϊ������
		if ("bzr".equalsIgnoreCase(lx)) {
			sql.append(" and exists (select 1 from "+Base.xsxxb+" b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from bzrbbb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + zgh + "')) ");
		}
		// �û�Ϊ�����μ渨��Ա
		if ("jd".equalsIgnoreCase(lx)) {
			sql.append(" and (exists (select 1 from "+Base.xsxxb+" b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from fdybjb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + zgh + "')) ");
			sql.append(" or exists (select 1 from "+Base.xsxxb+" b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from bzrbbb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + zgh + "'))) ");
		}
		// �û�ѧԺ�û�
		if ("xy".equalsIgnoreCase(lx)) {
			sql.append(" and exists (select 1 from "+Base.xsxxb+" b  ");
			sql.append(" where a.xh = b.xh and b.xydm = '" + userDep + "') ");
		}
		
		return sql.toString();
	}
	
	/**
	 * ��õ�½�û����ʸ���˵�ѧ���б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsShList(XszzTyForm model,
			HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ��
		String xmb = map.get("xmb");
		// ��������
		String sqzq = map.get("sqzq");
		// ��˼���
		String shjb = map.get("shjb");
		// ����ʱ��
		String pdsj = map.get("pdsj");
		// �Ƿ�ּ�
		String sffj = map.get("sffj");
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// �������
		String[] jbdm = model.getJb();
		// �û�����
		String lx = model.getLx();
		// ��������
		String xmzzjb = model.getXmzzjb();
		
		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq, pdsj, model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
		}
		  
		String[] inputValue = new String[]{};
		
		ArrayList<String> outputValue = new ArrayList<String>();
		outputValue.add("pk");
		outputValue.add("xh");
		outputValue.add("sqsj");
		outputValue.add("xm");
		outputValue.add("xb");
		outputValue.add("nj");
		outputValue.add("xymc");
		outputValue.add("zymc");
		outputValue.add("bjmc");
		outputValue.add("showbj");
		outputValue.add("showzy");
		outputValue.add("showxy");
		outputValue.add("showxm");
		if ("�ּ�".equalsIgnoreCase(sffj)) {
			outputValue.add("xmzzjb");
		}
		//String[] outputValue = null;

		// SQl
		StringBuilder sql = new StringBuilder();
		// ��ѯ����
		StringBuilder query = new StringBuilder();

		sql.append("select * from (");
		sql.append("select a.xh||a.sqsj||a.xmdm pk,a.sqsj,nvl(a.xmzzjb,'δָ��') xmzzjb,");
		sql.append("a.xh, b.xb, b.nj, b.xymc, b.zymc,b.xm, b.bjmc,b.bjdm,b.xydm, ");
		//ѧԺ��רҵ���༶����̫��
		sql.append(" case when length(b.xm) > 3 then  substr(b.xm, 0, 3) || '...' else b.xm end showxm,");
		sql.append(" case when length(b.bjmc) > 4 then  substr( b.bjmc, 0, 4) || '...' else  b.bjmc end showbj,");
		sql.append(" case when length(b.zymc) > 5 then  substr( b.zymc, 0, 5) || '...' else  b.zymc end showzy,");
		sql.append(" case when length(b.xymc) > 5 then  substr( b.xymc, 0, 5) || '...' else  b.xymc end showxy");
		//��������
		query.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '"+xn+"'" : "");
		query.append("���".equalsIgnoreCase(sqzq) ? " and a.nd = '"+nd+"'" : "");
		query.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '"+xn+"'" : "");
		query.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xq = '"+xq+"'" : "");
		//ѧԺ�༶������
		query.append(!Base.isNull(nj) ? " and b.nj = '"+nj+"'" : "");
		query.append(!Base.isNull(xydm) ? " and b.xydm = '"+xydm+"'" : "");
		query.append(!Base.isNull(zydm) ? " and b.zydm = '"+zydm+"'" : "");
		query.append(!Base.isNull(bjdm) ? " and b.bjdm = '"+bjdm+"'" : "");
		query.append(!Base.isNull(xh) ? " and b.xh like '%" + xh + "%'" : "");
		query.append(!Base.isNull(xm) ? " and b.xm like '%" + xm + "%'" : "");
		//TODO
		// һ�����
		if ("һ�����".equalsIgnoreCase(shjb)) {
			outputValue.add("shzt1");
			sql.append(",shzt1 ");
		}
		// �ɼ����
		else if ("�������".equalsIgnoreCase(shjb)) {
			// ��˼���1
			String[] shjb1 = jbdm[0].split("-");
			// ��˼���2
			String[] shjb2 = jbdm[1].split("-");
			// ����1��־
			boolean jb1 = false;
			// ����2��־
			boolean jb2 = false;
			// ����ֶ�
			String[] shzd = new String[2];

			// �û�Ϊ����Ա
			if ("fdy".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "";
			}
			// �û�Ϊ������
			if ("bzr".equalsIgnoreCase(lx)) {
				shzd[0] = "bzrsh";
				shzd[1] = "";
			}
			// �û�Ϊ�����μ渨��Ա
			if ("jd".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "bzrsh";
			}
			// �û�ΪѧԺ�û�
			if ("xy".equalsIgnoreCase(lx)) {
				shzd[0] = "xysh";
				shzd[1] = "";
			}
			// �û�ΪѧУ�û�
			if ("xx".equalsIgnoreCase(lx)) {
				shzd[0] = "xxsh";
				shzd[1] = "";
			}

			if (shjb1 != null && shjb1.length > 0) {
				for (String jb : shjb1) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb1 = true;
						break;
					}
				}
			}

			if (shjb2 != null && shjb2.length > 0) {
				for (String jb : shjb2) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb2 = true;
						break;
					}
				}
			}
			
			// ������ڼ���1
			if (jb1) {
				outputValue.add("shzt1");
				sql.append(",shzt1 ");
				query.append(" and shzt2 = 'δ���' ");
			}
			// ������ڼ���2
			if (jb2) {
				outputValue.add("shzt2");
				sql.append(",shzt2 ");
				query.append(" and shzt1 = 'ͨ��' ");
			}
		}
		// �������
		else if ("�������".equalsIgnoreCase(shjb)) {
			// ��˼���1
			String[] shjb1 = jbdm[0].split("-");
			// ��˼���2
			String[] shjb2 = jbdm[1].split("-");
			// ��˼���3
			String[] shjb3 = jbdm[2].split("-");
			// ����1��־
			boolean jb1 = false;
			// ����2��־
			boolean jb2 = false;
			// ����3��־
			boolean jb3 = false;
			// ����ֶ�
			String[] shzd = new String[2];

			// �û�Ϊ����Ա
			if ("fdy".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "";
			}
			// �û�Ϊ������
			if ("bzr".equalsIgnoreCase(lx)) {
				shzd[0] = "bzrsh";
				shzd[1] = "";
			}
			// �û�Ϊ�����μ渨��Ա
			if ("jd".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "bzrsh";
			}
			// �û�ΪѧԺ�û�
			if ("xy".equalsIgnoreCase(lx)) {
				shzd[0] = "xysh";
				shzd[1] = "";
			}
			// �û�ΪѧУ�û�
			if ("xx".equalsIgnoreCase(lx)) {
				shzd[0] = "xxsh";
				shzd[1] = "";
			}

			if (shjb1 != null && shjb1.length > 0) {
				for (String jb : shjb1) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb1 = true;
						break;
					}
				}
			}

			if (shjb2 != null && shjb2.length > 0) {
				for (String jb : shjb2) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb2 = true;
						break;
					}
				}
			}

			if (shjb3 != null && shjb3.length > 0) {
				for (String jb : shjb3) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb3 = true;
						break;
					}
				}
			}
			
			// ������ڼ���1
			if (jb1) {
				outputValue.add("shzt1");
				sql.append(",shzt1 ");
				query.append(" and shzt2 = 'δ���' ");
				query.append(" and shzt3 = 'δ���' ");
			}
			// ������ڼ���2
			if (jb2) {
				outputValue.add("shzt2");
				sql.append(",shzt2 ");
				query.append(" and shzt1 = 'ͨ��' ");
				query.append(" and shzt3 = 'δ���' ");
			}
			// ������ڼ���3
			if (jb3) {
				outputValue.add("shzt3");
				sql.append(",shzt3 ");
				query.append(" and shzt2 = 'ͨ��' ");
			}
		}
		
		sql.append("from " + xmb + " a, view_xsjbxx b ");
		sql.append("where a.xh = b.xh ");
		sql.append(" and a.xmdm = '" + xmdm + "'");
		sql.append(query);
		
		// ==============��˼������========================
		String[] jb = model.getJb();
		String tjjbOne = jb[0];
		String tjjbTwo = jb[1];
		String tjjbThr = jb[2];
		String shjbQuery = getXmshjbQuery(model, new String[] { shjb },
				new String[] { tjjbOne }, new String[] { tjjbTwo },
				new String[] { tjjbThr }, 0, "�����");
		sql.append(shjbQuery);
		// ==============end========================
		
		sql.append(") b where 1=1 ");
		sql.append(Base.isNull(xmzzjb) ? "" : " and xmzzjb = '" + xmzzjb
				+ "'");
		
		query=new StringBuilder();
		if ("fdy".equalsIgnoreCase(lx)) {
			query.append(" and exists (select 1 from fdybjb c where zgh='"+map.get("yhzgh")+"' and b.bjdm=c.bjdm)");
		}else if ("bzr".equalsIgnoreCase(lx)) {
			query.append(" and exists (select 1 from bzrbbb c where zgh='"+map.get("yhzgh")+"' and b.bjdm=c.bjdm)");
		}else if ("jd".equalsIgnoreCase(lx)) {
			query.append(" and (exists (select 1 from fdybjb c where zgh='"+map.get("yhzgh")+"' and b.bjdm=c.bjdm)");
			query.append(" or exists (select 1 from fdybjb c where zgh='"+map.get("yhzgh")+"' and b.bjdm=c.bjdm)) ");
		}else if ("xy".equalsIgnoreCase(lx)) {
			query.append(" and  b.xydm='"+model.getUserDep()+"'");
		}
		
		sql.append(query);
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue.toArray(new String[] {}));

		return list;
	}	
	
	/**
	 * ��������Ϣ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShInfoList(XszzTyForm model,
			HashMap<String, String> map) {
		
		DAO dao = DAO.getInstance();
		// ��Ŀ��
		String xmb = map.get("xmb");
		// ��������
		String sqzq = map.get("sqzq");
		// ��˼���
		String shjb = map.get("shjb");
		// ����ʱ��
		String pdsj = map.get("pdsj");
		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// �û�����
		String lx = model.getLx();
		// �������
		String[] jbdm = model.getJb();
		// ��������
		String[] jbmc = model.getJbmc();
		
		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq, pdsj, model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
		}
		  
		String[] inputValue = new String[] {};
		String[] outputValue = new String[] {};

		// SQl
		StringBuilder sql = new StringBuilder();
		// ��ѯ����
		StringBuilder query = new StringBuilder();

		sql.append("select a.bzrsh,a.fdysh,a.xysh,a.xxsh,a.shzt1,");
		sql.append("a.shzt1yj, a.shzt2yj, a.shzt3yj ");
		
		query.append(" where 1 = 1 ");
		query.append(Base.isNull(xh) ? " and rownum = 1" : " and a.xh = '"+xh+"' ");
		//=================2010.9.20 ����================
		query.append(Base.isNull(model.getSqsj()) ? " and rownum = 1" : " and a.sqsj = '"+model.getSqsj()+"' ");
		if("xszz_comm_zzwsb".equalsIgnoreCase(xmb)){
			query.append(Base.isNull(model.getXmdm()) ? "" : " and a.xmdm = '"+model.getXmdm()+"' ");
		}
		//=================end2010.9.20 ����================
		
		// ��������
		query.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '"+xn+"'" : "");
		query.append("���".equalsIgnoreCase(sqzq) ? " and a.nd = '"+nd+"'" : "");
		query.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '"+xn+"'" : "");
		query.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xq = '"+xq+"'" : "");
		
		// һ�����
		if ("һ�����".equalsIgnoreCase(shjb)) {
			sql.append(" ,'" + shjb + "' shjb ");
			sql.append(" ,'" + jbdm[0] + "' jbdm1 ");
			sql.append(" ,'" + jbmc[0] + "' jbmc1 ");
			outputValue = new String[] { "bzrsh", "fdysh", "xysh", "xxsh",
					"shzt1yj", "shzt1", "shjb", "jbdm1", "jbmc1" };
		}
		// �ɼ����
		else if ("�������".equalsIgnoreCase(shjb)) {
			
			// ��˼���1
			String[] shjb1 = jbdm[0].split("-");
			// ��˼���2
			String[] shjb2 = jbdm[1].split("-");
			// ����1��־
			boolean jb1 = false;
			// ����2��־
			boolean jb2 = false;
			// ����ֶ�
			String[] shzd = new String[2];

			// �û�Ϊ����Ա
			if ("fdy".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "";
			}
			// �û�Ϊ������
			if ("bzr".equalsIgnoreCase(lx)) {
				shzd[0] = "bzrsh";
				shzd[1] = "";
			}
			// �û�Ϊ�����μ渨��Ա
			if ("jd".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "bzrsh";
			}
			// �û�ΪѧԺ�û�
			if ("xy".equalsIgnoreCase(lx)) {
				shzd[0] = "xysh";
				shzd[1] = "";
			}
			// �û�ΪѧУ�û�
			if ("xx".equalsIgnoreCase(lx)) {
				shzd[0] = "xxsh";
				shzd[1] = "";
			}

			if (shjb1 != null && shjb1.length > 0) {
				for (String jb : shjb1) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb1 = true;
						break;
					}
				}
			}

			if (shjb2 != null && shjb2.length > 0) {
				for (String jb : shjb2) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb2 = true;
						break;
					}
				}
			}
			
			sql.append(" ,shzt2 ");
			sql.append(" ,'" + shjb + "' shjb ");
			sql.append(" ,'" + jbdm[0] + "' jbdm1 ");
			sql.append(" ,'" + jbdm[1] + "' jbdm2 ");
			sql.append(" ,'" + jbmc[0] + "' jbmc1 ");
			sql.append(" ,'" + jbmc[1] + "' jbmc2 ");
			// ��˼���Ϊ1��
			if (jb1) {
				sql.append(" ,'Lv1' jb ");
				outputValue = new String[] { "bzrsh", "fdysh", "xysh", "xxsh",
						"shzt1yj", "shzt1", "shzt2", "shjb", "jb", "jbdm1",
						"jbdm2", "jbmc1", "jbmc2" };
			}
			// ��˼���Ϊ2��
			if (jb2) {
				sql.append(" ,'Lv2' jb ");
				outputValue = new String[] { "bzrsh", "fdysh", "xysh", "xxsh",
						"shzt1yj", "shzt2yj", "shzt1", "shzt2", "shjb", "jb",
						"jbdm1", "jbdm2", "jbmc1", "jbmc2" };
			}		
		}	
		// �������
		else if ("�������".equalsIgnoreCase(shjb)) {
	
			// ��˼���1
			String[] shjb1 = jbdm[0].split("-");
			// ��˼���2
			String[] shjb2 = jbdm[1].split("-");
			// ��˼���3
			String[] shjb3 = jbdm[2].split("-");
			// ����1��־
			boolean jb1 = false;
			// ����2��־
			boolean jb2 = false;
			// ����3��־
			boolean jb3 = false;
			// ����ֶ�
			String[] shzd = new String[2];

			// �û�Ϊ����Ա
			if ("fdy".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "";
			}
			// �û�Ϊ������
			if ("bzr".equalsIgnoreCase(lx)) {
				shzd[0] = "bzrsh";
				shzd[1] = "";
			}
			// �û�Ϊ�����μ渨��Ա
			if ("jd".equalsIgnoreCase(lx)) {
				shzd[0] = "fdysh";
				shzd[1] = "bzrsh";
			}
			// �û�ΪѧԺ�û�
			if ("xy".equalsIgnoreCase(lx)) {
				shzd[0] = "xysh";
				shzd[1] = "";
			}
			// �û�ΪѧУ�û�
			if ("xx".equalsIgnoreCase(lx)) {
				shzd[0] = "xxsh";
				shzd[1] = "";
			}

			if (shjb1 != null && shjb1.length > 0) {
				for (String jb : shjb1) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb1 = true;
						break;
					}
				}
			}

			if (shjb2 != null && shjb2.length > 0) {
				for (String jb : shjb2) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb2 = true;
						break;
					}
				}
			}
			
			if (shjb3 != null && shjb3.length > 0) {
				for (String jb : shjb3) {
					if (shzd[0].equalsIgnoreCase(jb)
							|| shzd[1].equalsIgnoreCase(jb)) {
						jb3 = true;
						break;
					}
				}
			}
			
			sql.append(" ,shzt2 ");
			sql.append(" ,shzt3 ");
			sql.append(" ,'" + shjb + "' shjb ");
			sql.append(" ,'" + jbdm[0] + "' jbdm1 ");
			sql.append(" ,'" + jbdm[1] + "' jbdm2 ");
			sql.append(" ,'" + jbdm[2] + "' jbdm3 ");
			sql.append(" ,'" + jbmc[0] + "' jbmc1 ");
			sql.append(" ,'" + jbmc[1] + "' jbmc2 ");
			sql.append(" ,'" + jbmc[2] + "' jbmc3 ");
			
			// ��˼���Ϊ1��
			if (jb1) {
				sql.append(" ,'Lv1' jb ");
				outputValue = new String[] { "bzrsh", "fdysh", "xysh", "xxsh",
						"shzt1yj", "shzt1", "shzt2", "shzt3", "shjb", "jb",
						"jbdm1", "jbdm2", "jbdm3", "jbmc1", "jbmc2", "jbmc3" };
			}
			// ��˼���Ϊ2��
			if (jb2) {
				sql.append(" ,'Lv2' jb ");
				outputValue = new String[] { "bzrsh", "fdysh", "xysh", "xxsh",
						"shzt1yj", "shzt2yj", "shzt1", "shzt2", "shzt3",
						"shjb", "jb", "jbdm1", "jbdm2", "jbdm3", "jbmc1",
						"jbmc2", "jbmc3" };
			}
			// ��˼���Ϊ3��
			if (jb3) {
				sql.append(" ,'Lv3' jb ");
				outputValue = new String[] { "bzrsh", "fdysh", "xysh", "xxsh",
						"shzt1yj", "shzt2yj", "shzt3yj", "shzt1", "shzt2",
						"shzt3", "shjb", "jb", "jbdm1", "jbdm2", "jbdm3",
						"jbmc1", "jbmc2", "jbmc3" };
			}	
		}
		
		
		sql.append(" from " + xmb + " a ");
		sql.append(query);
		
		List<HashMap<String,String>> list = dao.getList(sql.toString(), inputValue, outputValue);

		return list;
	}
	
	/**
	 * ��������Ϣ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJgInfoList(XszzTyForm model,
			HashMap<String, String> map) {
		
		DAO dao = DAO.getInstance();
		// ��Ŀ��
		String xmb = map.get("xmb");
		// ��Ŀ����
		String xmdm = map.get("xmdm");
		// ��������
		String sqzq = map.get("sqzq");
		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String xn = map.get("xn");
		// ѧ��
		String xq = map.get("xq");
		// ���
		String nd = map.get("nd");
		
		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "shzt1yj", "shzt1", "shzt2yj",
				"shzt2", "shzt3yj", "shzt3" };

		// SQl
		StringBuilder sql = new StringBuilder();
		// ��ѯ����
		StringBuilder query = new StringBuilder();

		sql.append("select a.shzt1,a.shzt2,a.shzt3,");
		sql.append("a.shzt1yj, a.shzt2yj, a.shzt3yj ");
		
		query.append(" where 1 = 1 ");
		query.append(Base.isNull(xh) ? " and rownum = 1" : " and a.xh = '"+xh+"' ");
		// ��������
		query.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '"+xn+"'" : "");
		query.append("���".equalsIgnoreCase(sqzq) ? " and a.nd = '"+nd+"'" : "");
		query.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '"+xn+"'" : "");
		query.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xq = '"+xq+"'" : "");
	
		if(xmb.equalsIgnoreCase("xszz_comm_zzwsb")){
			query.append(" and a.xmdm = '"+xmdm+"'");
		}
		//2011-10-14 qph ˫�����ѯҳ�� ��δ������ʱ���������������������������¼��������
		query.append(" and a.sqsj = '").append(model.getSqsj()).append("'");
		
		sql.append(" from " + xmb + " a ");
		sql.append(query);
		
		List<HashMap<String,String>> list = dao.getList(sql.toString(), inputValue, outputValue);

		return list;
	}
	
	/**
	 * �����Ŀ��˽���б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmshJgList(XszzTyForm model,
			String[] xmb) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// ��ʼʱ��
		String kssj = model.getKssj();
		// ��ʼʱ��
		String jssj = model.getJssj();
		// �û�����
		String lx = model.getLx();
		// ְ����
		String zgh = model.getZgh();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ģ������
		String mklx = model.getMklx();
		// ��Ŀ���
		String xmlb = model.getXmlb();
		// ��Դ��
		String lyb = "";
		lyb = Base.isNull(xmdm) ? "" : getOneValue("xszz_zzxmb", "xmb", "xmdm",
				xmdm);
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn",
				"xq", "nd", "xmdm" };
		
		String[] queryLikeList = new String[] { "xh", "xm","xmmc" };
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(Base.isNull(kssj) ? "" : " and t.sqsj >= '" + kssj + "'");
		query.append(Base.isNull(jssj) ? "" : " and t.sqsj <= '" + jssj + "'");
		
		//TODO
		if ("fdy".equalsIgnoreCase(lx)) {
			query.append(" and exists(select 1 from fdybjb a, view_fdyxx b ");
			query.append(" where a.bjdm = t.bjdm ");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");
		} else if ("bzr".equalsIgnoreCase(lx)) {
			query.append(" and exists(select 1 from bzrbbb a, view_fdyxx b ");
			query.append(" where a.bjdm = t.bjdm");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");
		} else if("jd".equalsIgnoreCase(lx)){
			query.append(" and (");
			query.append(" exists(select 1 from fdybjb a, view_fdyxx b ");
			query.append(" where a.bjdm = t.bjdm");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");
			
			query.append(" or exists(select 1 from bzrbbb a, view_fdyxx b ");
			query.append(" where a.bjdm = t.bjdm");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");
			
			query.append(" )");
		}
		String xxdm = Base.xxdm;
		if (xmb != null && xmb.length > 0) {
			StringBuilder sql = new StringBuilder();
			
			
//			for (int i = 0; i < xmb.length; i++) {
//				if (i != 0) {
//					sql.append(" union all ");
//				}
//				sql.append("select a.xh||'!!@@!!'||a.sqsj||'!!@@!!'||a.xmdm||'!!@@!!'||'"+xmb[i]+"' pk ");
//				sql.append(",a.xh,a.xn,a.xq,a.nd,a.xmdm,a.sqsj, ");
//				sql.append("(select b.xmmc from xszz_zzxmb b where a.xmdm=b.xmdm) xmmc,");
//				sql.append(" b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zymc,b.zydm,b.bjdm,b.bjmc, ");
//				sql.append(" a.bzrsh,a.fdysh,a.xysh,a.xxsh,a.shzt1,a.shzt2,a.shzt3 from ");
//				sql.append(xmb[i]);
//				sql.append(" a ,view_xsjbxx b where 1= 1 and a.xh = b.xh");	
//			}
			
			sql.append(" select * from ( ");
			sql.append(" select rownum n,t.* from (");
			sql.append(" select a.* ");
			
			if (Globals.XXDM_BJLHLYDX.equalsIgnoreCase(xxdm)
					&& XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {
						
				sql.append(" ,kzzd1,kzzd2,kzzd3,kzzd4,kzzd5,kzzd6,kzzd7,sfdb,jtrjsr  ");
			}
			sql.append(" from (select a.xh||'!!@@!!'||a.sqsj||'!!@@!!'||a.xmdm||'!!@@!!'");
			sql.append(" ||(select c.xmb from xszz_zzxmb c where a.xmdm = c.xmdm) pk, ");
			sql.append(" (select c.sqzq from xszz_zzxmb c where a.xmdm = c.xmdm) sqzq, ");
			sql.append(" (select c.shjb from xszz_zzxmb c where a.xmdm = c.xmdm) shjb, ");
			sql.append(" (select c.sffj from xszz_zzxmb c where a.xmdm = c.xmdm) sffj, ");
			sql.append(" (select c.sfje from xszz_zzxmb c where a.xmdm = c.xmdm) sfje, ");
			sql.append(" a.xh,a.xn,a.xq,a.nd,a.xmdm,a.sqsj,a.xmzzje,");
			
			// ����ʦ��
			if (Globals.XXDM_XYSFXY.equalsIgnoreCase(xxdm)) {
				// ��ɫͨ��
				if (XszzXmdm.XSZZ_LSTD.equalsIgnoreCase(xmdm)) {
					sql.append(" (select c.yjje from xszz_lstdb c where a.xh = c.xh and a.sqsj = c.sqsj) yjje,");
					sql.append(" (select c.hjje from xszz_lstdb c where a.xh = c.xh and a.sqsj = c.sqsj) sjje,");
				}
				// ������ѧ����
				if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {
					sql.append(" (select c.dknx from gjzxdkb c where a.xh = c.xh and a.sqsj = c.sqsj) dknd,");
					sql.append(" (select c.zje from gjzxdkb c where a.xh = c.xh and a.sqsj = c.sqsj) dkje,");
				}
			}
			sql.append(Base.isNull(lyb)?"'' xmzzjb,":"nvl((select e.xmzzjb from "+lyb+" e where e.xmdm = a.xmdm and a.xh = e.xh and a.sqsj = e.sqsj),'δָ��') xmzzjb,");
			sql.append(" (select b.xmmc from xszz_zzxmb b where a.xmdm=b.xmdm) xmmc,");
			sql.append(" b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zymc,b.zydm,b.bjdm,b.bjmc, ");
			sql.append(" a.bzrsh,a.fdysh,a.xysh,a.xxsh,a.shzt1,a.shzt2,a.shzt3  ");
			
			sql.append(" from xszz_shztb a ,view_xsjbxx b ");
			
			sql.append("  where 1= 1 and a.xh = b.xh ");
			
			sql.append(" and not exists(select 1 from xszz_zzxmb c where a.xmdm = c.xmdm and c.kgzt = '��Ŀ�ر�')");	
			if("pj".equalsIgnoreCase(mklx)){
				sql.append(" and exists(select 1 from xszz_zzxmb c where a.xmdm = c.xmdm and c.xmlb in ('��ѧ��', '�����ƺ�'))");	
			}else if("zz".equalsIgnoreCase(mklx)){
				sql.append(" and exists(select 1 from xszz_zzxmb c where a.xmdm = c.xmdm and c.xmlb in ('��ѧ��', '���Ѳ���', '����'))");
			} else {
				if (!Base.isNull(xmlb)) {
					sql.append(" and exists(select 1 from xszz_zzxmb c where a.xmdm = c.xmdm and c.xmlb = '"
									+ xmlb + "') ");
				}
			}
			sql.append(")a ");
			if (Globals.XXDM_BJLHLYDX.equalsIgnoreCase(xxdm)
					&& XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {
				sql.append(" left join(select * from jtqkdcb x where exists(select 1 from  ");
				sql.append(" ( select xh,max(sqsj) sqsj from jtqkdcb group by xh) y ");
				sql.append("  where x.xh = y.xh and x.sqsj = y.sqsj)) b ");
				sql.append("  on a.xh = b.xh ");
			}
			sql.append(") t ");
			
			if(!Base.isNull(lyb)){
				String shzt1 = model.getShzt1();
				String shzt2 = model.getShzt2();
				String shzt3 = model.getShzt3();
				String xmzzjb = model.getXmzzjb();
				
				query.append(Base.isNull(shzt1)?"":" and t.shzt1 = '" + shzt1 + "' ");
				query.append(Base.isNull(shzt2)?"":" and t.shzt2 = '" + shzt2 + "' ");
				query.append(Base.isNull(shzt3)?"":" and t.shzt3 = '" + shzt3 + "' ");
				query.append(Base.isNull(xmzzjb)?"":" and t.xmzzjb = '" + xmzzjb + "' ");
			}
			
			sql.append(query);
			
			if (Globals.XXDM_BJLHLYDX.equalsIgnoreCase(xxdm)
					&& XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {
						
				sql.append(" order by jtrjsr asc  ");
			}
			
			String[] inputValue = myQuery.getInputList();
			String[] outputValue = new String[] { "pk", "xn", "xq", "nd",
					"sqsj", "xmdm", "xmmc", "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "bzrsh", "fdysh", "xysh", "xxsh", "shzt1",
					"shzt2", "shzt3","xmzzjb","xmzzje","sqzq","shjb","sffj","sfje" };
			

			// ����ʦ��
			if (Globals.XXDM_XYSFXY.equalsIgnoreCase(xxdm)) {
				// ��ɫͨ��
				if (XszzXmdm.XSZZ_LSTD.equalsIgnoreCase(xmdm)) {
					outputValue = new String[] { "pk", "xn", "xq", "nd",
							"sqsj", "xmdm", "xmmc", "xh", "xm", "xb", "nj",
							"xymc", "zymc", "bjmc", "bzrsh", "fdysh", "xysh",
							"xxsh", "shzt1", "shzt2", "shzt3", "xmzzjb",
							"xmzzje", "sqzq", "shjb", "sffj", "sfje", "yjje",
							"sjje" };
				}
				// ������ѧ����
				if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {

					outputValue = new String[] { "pk", "xn", "xq", "nd",
							"sqsj", "xmdm", "xmmc", "xh", "xm", "xb", "nj",
							"xymc", "zymc", "bjmc", "bzrsh", "fdysh", "xysh",
							"xxsh", "shzt1", "shzt2", "shzt3", "xmzzjb",
							"xmzzje", "sqzq", "shjb", "sffj", "sfje", "dkje",
							"dknd" };
				}
				
			}
			//�����������δ�ѧ
			if (Globals.XXDM_BJLHLYDX.equalsIgnoreCase(xxdm)) {
				// ��ɫͨ��
				if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {
					outputValue = new String[] { "pk", "xn", "xq", "nd",
							"sqsj", "xmdm", "xmmc", "xh", "xm", "xb", "nj", "xymc",
							"zymc", "bjmc", "bzrsh", "fdysh", "xysh", "xxsh", "shzt1",
							"shzt2", "shzt3","xmzzjb","xmzzje","sqzq","shjb","sffj","sfje","sfdb","kzzd3","jtrjsr" };
				}
				
			}
			
			String maxSql = "select count(1) num from (" + sql.toString() + "))";
			String num = dao.getOneRs(maxSql, inputValue, "num");
			Pages pages = model.getPages();
			pages.setMaxRecord(Integer.parseInt(num));

			int start = pages.getStart();
			int size = pages.getPageSize();

			sql.append(") where n >='" + start + "'");
			sql.append(" and n <='" + (start + size) + "'");
			//System.out.println(sql);
			list = dao.getList(sql.toString(), inputValue, outputValue);
		}
		return list;
	}

	
	/**
	 * ���������ѯ�б�
	 * 
	 * @author qph
	 * @param model
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getZzxmList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String xmdm = model.getXmdm();
		String lyb = Base.isNull(xmdm) ? "" : getOneValue("xszz_zzxmb", "xmb","xmdm", xmdm);
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn",
				"xq", "nd", "isxb","shzt1","shzt2","shzt3" };
		String[] queryLikeList = new String[] { "xh", "xm"};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] {"sqzq","pk","xh","xm","xb","nj","xymc","zymc","bjmc","sqsj","isxb"};
		
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		
		if (!Base.isNull(kssj)) {
			query.append( " and sqsj >= '").append(kssj).append("'");
		}
		
		if (!Base.isNull(jssj)) {
			query.append(" and sqsj <= '").append(jssj).append("'");
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (")
		   .append("select c.* ,rownum r,c.xn||c.nd||c.xq sqzq ").append(" from (")
		   .append("select a.*,a.xh||'!!@@!!'||a.sqsj||'!!@@!!'||'")
		   .append(xmdm)
		   .append("'||'!!@@!!'||'")
		   .append(lyb)
		   .append("' pk,")
		   .append("b.nj,b.xydm,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.xb,b.xm from ")
		   .append(lyb)
		   .append(" a left join view_xsjbxx b on a.xh=b.xh")
		   .append(") c")
		   .append(query);
		
		String maxSql = "select count(1) num from (" + sql.toString() + "))";
		String num = dao.getOneRs(maxSql, inputValue, "num");
		Pages pages = model.getPages();
		pages.setMaxRecord(Integer.parseInt(num));

		int start = pages.getStart();
		int size = pages.getPageSize();

		sql.append(") where r >='" + start + "'");
		sql.append(" and r <='" + (start + size) + "'");
		
		
		return dao.getList(sql.toString(), inputValue, outputValue);
	}

	
	/**
	 * �����Ŀ���ͨ������
	 * 
	 * @author luojw
	 */
	public String getXmshRs(XszzTyForm model,HashMap<String, String> map) {

		DAO dao = DAO.getInstance();
		
		// ѧ��
		String xh = model.getXh();
		// ѧԺ����
		String xydm = map.get("xydm");
		// רҵ����
		String zydm = map.get("zydm");
		// �༶����
		String bjdm = map.get("bjdm");
		// ��������
		String sqzq = map.get("sqzq");
		// ���Ƽ���
		String kzjb = map.get("kzjb");
		// ѧ��
		String xn = map.get("xn");
		// ѧ��
		String xq = map.get("xq");
		// ���
		String nd = map.get("nd");
		// �����ο���
		String bzrkz = map.get("bzrkz");
		// ����Ա����
		String fdykz = map.get("fdykz");
		// Ժϵ����
		String xykz = map.get("xykz");
		// ѧУ����
		String xxkz = map.get("xxkz");
		// ��Ŀ����
		String xmdm = map.get("xmdm");
		
		StringBuilder sql = new StringBuilder();

		sql.append("select count(1) num from xszz_shztb a ");
		sql.append(" where a.xmdm = '" + xmdm + "' ");
		sql.append(Base.isNull(xh) ? "" : " and a.xh <> '" + xh + "' ");
		
		// �������
		sql.append(!"��".equalsIgnoreCase(bzrkz)&&!"��".equalsIgnoreCase(fdykz) ? "" : " and (a.bzrsh = 'ͨ��' or a.fdysh = 'ͨ��')");
		sql.append(!"��".equalsIgnoreCase(xykz) ? "" : " and a.xysh = 'ͨ��' ");
		sql.append(!"��".equalsIgnoreCase(xxkz) ? "" : " and a.xxsh = 'ͨ��' ");

		// ��������
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'"
				: "");
		sql.append("���".equalsIgnoreCase(sqzq) ? " and a.nd = '" + nd + "'"
				: "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'"
				: "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xq = '" + xq + "'"
				: "");

		// ��������
		sql.append("ѧԺ".equalsIgnoreCase(kzjb) ? " and exists (select 1 from view_xsjbxx b where a.xh = b.xh and b.xydm = '"
						+ xydm + "') "
						: "");
		sql.append("רҵ".equalsIgnoreCase(kzjb) ? " and exists (select 1 from view_xsjbxx b where a.xh = b.xh and b.zydm = '"
						+ zydm + "') "
						: "");
		sql.append("�༶".equalsIgnoreCase(kzjb) ? " and exists (select 1 from view_xsjbxx b where a.xh = b.xh and b.bjdm = '"
						+ bjdm + "') "
						: "");
		//System.out.println(sql);
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");

		return num;
	}
	
	/**
	 * �����Ŀ���ͨ������
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXmshRsList(XszzTyForm model,
			HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		// ѧ��+����ʱ��
		String[] pkValue = model.getCheckVal();
		
		// ��Ŀ��
		String xmb = map.get("xmb");
		
		// ��������
		String sqzq = map.get("sqzq");
		// ���Ƽ���
		String kzjb = map.get("kzjb");
		
		// ѧ��
		String xn = map.get("xn");
		// ѧ��
		String xq = map.get("xq");
		// ���
		String nd = map.get("nd");
		
		// �����ο���
		String bzrkz = map.get("bzrkz");
		// ����Ա����
		String fdykz = map.get("fdykz");
		// Ժϵ����
		String xykz = map.get("xykz");
		// ѧУ����
		String xxkz = map.get("xxkz");
		// ��Ŀ����
		String xmdm = map.get("xmdm");
		
		String[] outValue = null;
		StringBuilder sql = new StringBuilder();

		if ("ѧԺ".equalsIgnoreCase(kzjb)) {
			outValue = new String[] { "xydm", "num" };
			sql.append("select b.xydm,");
		}
		if ("רҵ".equalsIgnoreCase(kzjb)) {
			outValue = new String[] { "nj", "zydm", "num" };
			sql.append("select b.nj,b.zydm,");
		}
		if ("�༶".equalsIgnoreCase(kzjb)) {
			outValue = new String[] { "bjdm", "num" };
			sql.append("select b.bjdm,");
		}
		
		sql.append("count(1) num from xszz_shztb a,view_xsjbxx b ");
		sql.append(" where xmdm = '" + xmdm + "' ");
		sql.append(" and a.xh = b.xh ");
		
		//��ȥ����
		if (pkValue != null && pkValue.length > 0) {
			for (int i = 0; i < pkValue.length; i++) {
				sql.append(" and a.xh || a.sqsj || a.xmdm <> '" + pkValue[i] + "' ");
			}
		}
		
		// �������
		sql.append(!"��".equalsIgnoreCase(bzrkz) ? "" : " and a.bzrsh = 'ͨ��' ");
		sql.append(!"��".equalsIgnoreCase(fdykz) ? "" : " and a.fdysh = 'ͨ��' ");
		sql.append(!"��".equalsIgnoreCase(xykz) ? "" : " and a.xysh = 'ͨ��' ");
		sql.append(!"��".equalsIgnoreCase(xxkz) ? "" : " and a.xxsh = 'ͨ��' ");

		// ��������
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'"
				: "");
		sql.append("���".equalsIgnoreCase(sqzq) ? " and a.nd = '" + nd + "'"
				: "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'"
				: "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xq = '" + xq + "'"
				: "");

		// ���Ƽ�������
		if ("ѧԺ".equalsIgnoreCase(kzjb)) {
			sql.append(" and exists (");
			sql.append(" select 1 from (select distinct ");
			sql.append(" a.xydm");
			sql.append(" from view_xsjbxx a where exists (select 1");
			sql.append(" from (select xh  from ");
			sql.append(xmb);
			if (pkValue != null && pkValue.length > 0) {
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						sql.append(" where xh || sqsj || xmdm = '" + pkValue[i] + "'");
					} else {
						sql.append(" or xh || sqsj || xmdm = '" + pkValue[i] + "'");
					}
				}
			}
			sql.append(" ) b where a.xh = b.xh)) c where c.xydm = b.xydm )");
			sql.append(" group by b.xydm");
		}
		if ("רҵ".equalsIgnoreCase(kzjb)) {
			sql.append(" and exists (");
			sql.append(" select 1 from (select distinct ");
			sql.append(" a.nj,a.zydm");
			sql.append(" from view_xsjbxx a where exists (select 1");
			sql.append(" from (select xh  from ");
			sql.append(xmb);
			if (pkValue != null && pkValue.length > 0) {
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						sql.append(" where xh || sqsj || xmdm = '" + pkValue[i] + "'");
					} else {
						sql.append(" or xh || sqsj || xmdm = '" + pkValue[i] + "'");
					}
				}
			}
			sql.append(" ) b where a.xh = b.xh)) c where c.zydm = b.zydm and c.nj = b.nj)");
			sql.append(" group by b.nj,b.zydm");
		}
		if ("�༶".equalsIgnoreCase(kzjb)) {
			sql.append(" and exists (");
			sql.append(" select 1 from (select distinct ");
			sql.append(" a.bjdm");
			sql.append(" from view_xsjbxx a where exists (select 1");
			sql.append(" from (select xh  from ");
			sql.append(xmb);
			if (pkValue != null && pkValue.length > 0) {
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						sql.append(" where xh || sqsj || xmdm = '" + pkValue[i] + "'");
					} else {
						sql.append(" or xh || sqsj || xmdm = '" + pkValue[i] + "'");
					}
				}
			}
			sql.append(" ) b where a.xh = b.xh)) c where c.bjdm = b.bjdm )");
			sql.append(" group by b.bjdm");
		}
		
		//System.out.println(sql);
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, outValue);

		return list;
	}
	
	/**
	 * �����Ŀ��������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getShRsList(XszzTyForm model,
			HashMap<String, String> map) {

		DAO dao = DAO.getInstance();

		// ѧ��+����ʱ��
		String[] pkValue = model.getCheckVal();

		// ��Ŀ��
		String xmb = map.get("xmb");

		// ���Ƽ���
		String kzjb = map.get("kzjb");

		String[] outValue = null;
		StringBuilder sql = new StringBuilder();
		String groupby = "";

		if ("ѧԺ".equalsIgnoreCase(kzjb)) {
			outValue = new String[] { "xydm", "num" };
			sql.append("select a.xydm, ");
			groupby = "group by a.xydm ";
		}
		if ("רҵ".equalsIgnoreCase(kzjb)) {
			outValue = new String[] { "nj","zydm", "num" };
			sql.append("select a.nj,a.zydm, ");
			groupby = "group by a.nj,a.zydm ";
		}
		if ("�༶".equalsIgnoreCase(kzjb)) {
			outValue = new String[] { "bjdm", "num" };
			sql.append("select a.bjdm, ");
			groupby = "group by a.bjdm ";
		}
		
		sql.append("count(1) num  from view_xsjbxx a");
		sql.append(" where 1=1");
		sql.append(" and exists (");
		sql.append(" select 1 from (select xh from ");	
		sql.append(xmb);
		if (pkValue != null && pkValue.length > 0) {
			for (int i = 0; i < pkValue.length; i++) {
				if (i == 0) {
					sql.append(" where xh || sqsj || xmdm = '" + pkValue[i] + "'");
				} else {
					sql.append(" or xh || sqsj || xmdm = '" + pkValue[i] + "'");
				}
			}
		}
		sql.append(" ) b where a.xh = b.xh)");		
		sql.append(groupby);

		//System.out.println(sql);

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, outValue);

		return list;
	}
	
	/**
	 * �����Ŀ��������
	 * 
	 * @author luojw
	 */
	public String getXmszRs(XszzTyForm model,HashMap<String, String> map) {

		DAO dao = DAO.getInstance();
		
		// ѧ��
		//String xh = model.getXh();
		// ѧԺ����
		String xydm = map.get("xydm");
		// �꼶
		//String nj = map.get("nj");
		// רҵ����
		String zydm = map.get("zydm");
		// �༶����
		String bjdm = map.get("bjdm");
		// ��Ŀ��
		//String xmb = map.get("xmb");
		// ��������
		String sqzq = map.get("sqzq");
		// ���Ƽ���
		String kzjb = map.get("kzjb");
		// ѧ��
		String xn = map.get("xn");
		// ѧ��
		String xq = map.get("xq");
		// ���
		String nd = map.get("nd");
		// ��Ŀ����
		String xmdm = map.get("xmdm");
		
		StringBuilder sql = new StringBuilder();

		sql.append("select szrs num from xszz_xmrsb a ");
		sql.append(" where a.xmdm = '" + xmdm + "' ");
		
		// ��������
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'"
				: "");
		sql.append("���".equalsIgnoreCase(sqzq) ? " and a.nd = '" + nd + "'"
				: "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'"
				: "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xq = '" + xq + "'"
				: "");

		// ��������
		sql.append("ѧԺ".equalsIgnoreCase(kzjb) ? " and a.bmjb = 'xy' and bmdm = '" + xydm + "'" : "");
		sql.append("רҵ".equalsIgnoreCase(kzjb) ? " and a.bmjb = 'zy' and bmdm = '" + zydm + "'" : "");
		sql.append("�༶".equalsIgnoreCase(kzjb) ? " and a.bmjb = 'bj' and bmdm = '" + bjdm + "'" : "");
		//System.out.println(sql);
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");

		return num;
	}
	
	/**
	 * �����Ŀ��������
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<HashMap<String,String>> getXmszRsList(XszzTyForm model,
			HashMap<String, String> map) throws SQLException {

		DAO dao = DAO.getInstance();

		// ѧ��+����ʱ��
		String[] pkValue = model.getCheckVal();

		// ��Ŀ��
		String xmb = map.get("xmb");
		// ��������
		String sqzq = map.get("sqzq");
		// ���Ƽ���
		String kzjb = map.get("kzjb");
		// ����
		String pk = "xh || sqsj || xmdm";
		
		// ѧ��
		String xn = map.get("xn");
		// ѧ��
		String xq = map.get("xq");
		// ���
		String nd = map.get("nd");
		// ��Ŀ����
		String xmdm = map.get("xmdm");
		// ����ʱ��
//		String pdsj = map.get("pdsj");
//		// ����ʱ��Ϊ��ѧ�꣨ѧ����ȣ�
//		if ("ǰ��".equalsIgnoreCase(pdsj)) {
//			if ("ѧ��".equalsIgnoreCase(sqzq)) {
//				String[] arrXn = xn.split("-");
//				xn = String.valueOf(Integer.parseInt(arrXn[0]) - 1) + "-"
//						+ String.valueOf(Integer.parseInt(arrXn[1]) - 1);
//				map.put("xn", xn);
//			} else if ("���".equalsIgnoreCase(sqzq)) {
//				nd = String.valueOf(Integer.parseInt(nd) - 1);
//				map.put("nd", nd);
//			} else if ("ѧ��".equalsIgnoreCase(sqzq)) {
//				map = dao.getBeforeXq(map);
//			}
//			xn = map.get("xn");
//			xq = map.get("xq");
//			nd = map.get("nd");
//		}
		
		StringBuilder sql = new StringBuilder();

		sql.append("select a.bmjb,a.nj,a.bmdm,a.szrs num from xszz_xmrsb a ");
		sql.append(" where a.xmdm = '" + xmdm + "' ");

		// ��������
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'"
				: "");
		sql.append("���".equalsIgnoreCase(sqzq) ? " and a.nd = '" + nd + "'"
				: "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'"
				: "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xq = '" + xq + "'"
				: "");

		// ��������
		if ("ѧԺ".equalsIgnoreCase(kzjb)) {
			sql.append(" and a.bmjb = 'xy' ");
			sql.append(" and exists (");
			sql.append(" select 1 from (select distinct ");
			sql.append(" a.xydm");
			sql.append(" from view_xsjbxx a where exists (select 1");
			sql.append(" from (select xh  from ");
			sql.append(xmb);
			if (pkValue != null && pkValue.length > 0) {
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						sql.append(" where " + pk + " = '" + pkValue[i] + "'");
					} else {
						sql.append(" or " + pk + " = '" + pkValue[i] + "'");
					}
				}
			}
			sql.append(" ) b where a.xh = b.xh)) b where a.bmdm = b.xydm )");
		}
		if ("רҵ".equalsIgnoreCase(kzjb)) {
			sql.append(" and a.bmjb = 'zy' ");
			sql.append(" and exists (");
			sql.append(" select 1 from (select distinct ");
			sql.append(" a.nj,a.zydm ");
			sql.append(" from view_xsjbxx a where exists (select 1");
			sql.append(" from (select xh  from ");
			sql.append(xmb);
			if (pkValue != null && pkValue.length > 0) {
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						sql.append(" where " + pk + " = '" + pkValue[i] + "'");
					} else {
						sql.append(" or " + pk + " = '" + pkValue[i] + "'");
					}
				}
			}
			sql.append(" ) b where a.xh = b.xh)) b where a.bmdm = b.zydm and a.nj = b.nj)");
		}
		if ("�༶".equalsIgnoreCase(kzjb)) {
			sql.append(" and a.bmjb = 'bj' ");
			sql.append(" and exists (");
			sql.append(" select 1 from (select distinct ");
			sql.append(" a.bjdm");
			sql.append(" from view_xsjbxx a where exists (select 1");
			sql.append(" from (select xh  from ");
			sql.append(xmb);
			if (pkValue != null && pkValue.length > 0) {
				for (int i = 0; i < pkValue.length; i++) {
					if (i == 0) {
						sql.append(" where " + pk + " = '" + pkValue[i] + "'");
					} else {
						sql.append(" or " + pk + " = '" + pkValue[i] + "'");
					}
				}
			}
			sql.append(" ) b where a.xh = b.xh)) b where a.bmdm = b.bjdm )");
		}
		//System.out.println(sql);
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "bmjb", "nj", "bmdm", "num" });

		return list;
	}
	
	/**
	 * ɾ����Ŀ������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delXmsqInfo(XszzTyForm model) throws Exception {

		//--------ע�Ͳ��ֲ���ɾ��˭ɾ��˭��------------------------
		
		String[] pk = model.getPrimarykey_checkVal();
		/*DAO dao = DAO.getInstance();*/
		
		boolean flag = false;

		if (pk != null && pk.length > 0) {

			String actSql[] = new String[pk.length * 2];
			StringBuilder sql ;
			/*StringBuilder querySQL = new StringBuilder();*/
			
			int count = 0;

			for (int i = 0; i < pk.length; i++) {

				sql = new StringBuilder();

				String[] arr_pk = !Base.isNull(pk[i]) ? pk[i].split("!!@@!!")
													  : null;
				if (null != arr_pk && arr_pk.length == 4) {
					String xh = arr_pk[0];
					String sqsj = arr_pk[1];
					String xmdm = arr_pk[2];
					String xmb = arr_pk[3];
	
					/*querySQL.append("select filepath from ");
					querySQL.append(xmb);
					querySQL.append(" where xh = '").append(xh).append("'");
					querySQL.append(" and xmdm = '").append(xmdm).append("'");
					querySQL.append(" and sqsj = '").append(sqsj).append("'");
					
					if (i != pk.length-1) {
						querySQL.append(" union all ");
					}*/
					
					sql.append("delete from ");
					sql.append(xmb);
					sql.append(" where xh = '").append(xh).append("'");
					sql.append(" and xmdm = '").append(xmdm).append("'");
					sql.append(" and sqsj = '").append(sqsj).append("'");
	
					actSql[count] = sql.toString();
	
					count++;
	
					sql = new StringBuilder();
	
					sql.append("delete from xszz_shztb ");
					sql.append(" where xh = '").append(xh).append("'");
					sql.append(" and xmdm = '").append(xmdm).append("'");
					sql.append(" and sqsj = '").append(sqsj).append("'");
	
					actSql[count] = sql.toString();
	
					count++;
				}
				
			}
			
			/*String[] filePaths = dao.getArray(querySQL.toString(), new String[] {}, "filepath");*/
			flag = saveArrDate(actSql);
			
			/*if (flag) {
				FileManage.delFiles(filePaths);
			}*/
		}
		
		return flag;
	}
	
	/**
	 * ɾ����Ŀ�����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delXmxgInfo(XszzTyForm model) throws Exception {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;
		
		// Ҫ��Ŀ����
		String[] xmdm = model.getPrimarykey_checkVal();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from xszz_zzxmb t");
		if (xmdm != null && xmdm.length > 0) {
			for (int i = 0; i < xmdm.length; i++) {
				if(i == 0){
					sql.append(" where xmdm = ? ");
				}else{
					sql.append(" or xmdm = ? ");
				}
			}
		}
		

		String[] outputValue = new String[] {"xmdm","xmb"};
		List<HashMap<String, String>> list = dao.getList(sql.toString(), xmdm,
				outputValue);
		
		if (list != null && list.size() > 0) {

			String[] actSql = new String[list.size() * 5];

			int n = 0;

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				String dm = map.get("xmdm");
				String xmb = map.get("xmb");

				actSql[n] = "delete from " + xmb + " where xmdm = '" + dm + "'";
				n++;
				actSql[n] = "delete from xszz_xmfjqkb where xmdm = '" + dm + "'";
				n++;
				actSql[n] = "delete from xszz_xmtjb where xmdm = '" + dm + "'";
				n++;
				actSql[n] = "delete from xszz_xmrsb where xmdm = '" + dm + "'";
				n++;
				actSql[n] = "delete from xszz_xmnrzdb where xmdm = '" + dm + "'";
				n++;
			}
			flag = saveArrDate(actSql);
		}
		
		return flag;
	}

	/**
	 * �����ѧ��ѧ�����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getBeforeXnXqNd(String sqzq, String pdsj,
			XszzTyForm model) {

		DAO dao = DAO.getInstance();

		HashMap<String, String> map = new HashMap<String, String>();

		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();

		map.put("xn", xn);
		map.put("xq", xq);
		map.put("nd", nd);

		// ����ʱ��Ϊ��ѧ�꣨ѧ����ȣ�
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			if ("ѧ��".equalsIgnoreCase(sqzq)) {
				String[] arrXn = xn.split("-");
				xn = String.valueOf(Integer.parseInt(arrXn[0]) - 1) + "-"
						+ String.valueOf(Integer.parseInt(arrXn[1]) - 1);
				map.put("xn", xn);
			} else if ("���".equalsIgnoreCase(sqzq)) {
				nd = String.valueOf(Integer.parseInt(nd) - 1);
				map.put("nd", nd);
			} else if ("ѧ��".equalsIgnoreCase(sqzq)) {
				map = dao.getBeforeXq(map);
			}
		}

		return map;
	}
	
	/**
	 * �����ѧ��ѧ�����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveXszzFile(XszzTyForm model) {

		// ѧ��
		String xh = model.getXh();
		// ����ʱ��
		String sqsj = model.getSqsj();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// �ֶ�
		String zd = model.getZd();
		// ·��
		String path = model.getPath();

		String tableName = "xszz_filepathb";
		String[] columns = new String[] { "xmdm", "xh", "sqsj", "zd", "path" };
		String[] values = new String[] { xmdm, xh, sqsj, zd, path };

		//StandardOperation.delete(tableName, primaryKey, value, request)
		return StandardOperation.insertNoLog(tableName, columns, values);
	}
	
	/**
	 * ͬ��״̬��Ϣ
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean tbZtInfo() throws Exception {

		DAO dao = DAO.getInstance();

		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xmdm,a.xmb from xszz_zzxmb a");

		List<HashMap<String, String>> xmList = dao.getList(sql.toString(),
				new String[] {}, new String[] { "xmdm", "xmb" });
		
		if (xmList != null && xmList.size() > 0) {
			
			String[] actSql = new String[ xmList.size()*2];
			int n = 0;
			
			for (int i = 0; i < xmList.size(); i++) {

				HashMap<String, String> map = xmList.get(i);

				sql = new StringBuilder();
				sql.append(" delete from xszz_shztb ");
				sql.append(" where xmdm = '" + map.get("xmdm") + "' ");
				
				actSql[n] = sql.toString();
				
				n++;
				
				sql = new StringBuilder();
				sql.append(" insert into xszz_shztb ");
				sql.append(" (xh,sqsj,xmdm,xn,xq,nd,bzrsh,fdysh,xysh,xxsh,shzt1,shzt2,shzt3,xmzzjb,xmzzje)");
				sql.append(" select xh,sqsj,xmdm,xn,xq,nd,bzrsh,fdysh,xysh,xxsh,shzt1,shzt2,shzt3,xmzzjb,xmzzje");
				sql.append(" from " + map.get("xmb"));
				sql.append(" where xmdm = '"+map.get("xmdm") +"'");
				
				actSql[n] = sql.toString();
				
				n++;
			}
			
			flag = saveArrDate(actSql);
		}
		
		return flag;
	}
	// =======================����made by ΰ���luo==========================
	
	/**
	 * ����ѧϰ�ɼ�����
	 * @param xh
	 * @return
	 */
	public String getCjpm(String xh) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (select a.*,rownum r from(");
		sql.append("select b.xh,sum(b.cj) cj from cjb b ");
		sql.append("where b.xn=(select dqxn from xtszb) and exists ");
		sql.append("(select 1 from view_xsjbxx where xydm=(select xydm from view_xsjbxx where xh=?) ");
		sql.append("and nj=(select nj from view_xsjbxx where xh=?)) ");
		sql.append("group by xh order by cj desc) a )");
		
		return dao.getOneRs(sql.toString(), new String[] {xh,xh}, "r");
	}
	
	/**
	 * ��������˼�������SQL
	 * @param xmdm
	 * @return
	 */
	public String getQuerySql(String xmdm) {
		String shjb = getOneValue("xszz_zzxmb", "shjb", "xmdm", xmdm);
		StringBuilder querySql = new StringBuilder();
		
		if ("һ�����".equals(shjb)) {
			querySql.append(" and shzt1='ͨ��'");
		} else if ("�������".equals(shjb)) {
			querySql.append(" and shzt2='ͨ��'");
		} else if ("�������".equals(shjb)) {
			querySql.append(" and shzt3='ͨ��'");
		} else {
			
		}
		
		return querySql.toString();
	}
	
	/**
	 * ��ɫͨ������
	 * @param xmdm
	 * @param xydm
	 * @return
	 */
	protected List<String[]> getLstdData(String xmdm,String xydm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,rownum r from (select a.xh,");
		sql.append("(select xm from view_xsjbxx where xh=a.xh) xm,");
		sql.append("(select xb from view_xsjbxx where xh=a.xh) xb,");
		sql.append("(select xymc from view_xsjbxx where xh=a.xh) xymc,");
		sql.append("(select xydm from view_xsjbxx where xh=a.xh) xydm,");
		sql.append("(select zymc from view_xsjbxx where xh=a.xh) zymc,");
		sql.append("(select bjmc from view_xsjbxx where xh=a.xh) bjmc,");
		sql.append("(select jg from xsxxb where xh=a.xh) jg,");
		sql.append("(select jtqkjj from xsxxb where xh=a.xh) jtqkjj,");
		sql.append(" sum(qjfy) hjje from xszz_lstdb a ");
		sql.append(" where 1=1");
		sql.append(getQuerySql(xmdm));
		sql.append(" group by xh) a where xydm=?");
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] { xydm },
				new String[] { "xh","r", "xm", "xb", "zymc", "bjmc", "jg", "jtqkjj",
						"hjje" });
		
		for (String[] str : result) {
			str[6] = new XsxxglDAO().dzxxdmToMc(str[6]);
		}
		
		return result ;
	}
	
	
	/**
	 * ��õ��������б�
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getExpList(XszzTyForm model, String[] outValue) {

		DAO dao = DAO.getInstance();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// ��ʼʱ��
		String kssj = model.getKssj();
		// ����ʱ��
		String jssj = model.getJssj();
		// ��Ŀ��
		String xmb = model.getXmb();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmzzjb = model.getXmzzjb();
		// ���״̬1
		String shzt1 = model.getShzt1();
		// ���״̬2
		String shzt2 = model.getShzt2();
		// ���״̬3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();

		sql.append("select ");

		if (outValue != null && outValue.length > 0) {
			for (int i = 0; i < outValue.length; i++) {
				if (i == 0) {
					sql.append(" a." + outValue[i]);
				} else {
					sql.append(" ,a." + outValue[i]);
				}
			}
		}
		sql.append(" from ");
		sql.append(xmb);
		sql.append(" a where 1 = 1 ");
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "' ");
		sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");
		
		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb + "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(" and exists ( select 1 from view_xsjbxx b ");
		sql.append(" where a.xh = b.xh  ");
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		sql.append(" )");
		
		//System.out.println(sql);
		ArrayList<String[]> list = dao.rsToVator(sql.toString(),
				new String[] {}, outValue);

		return list;
	}
	
	
	/**
	 * ��־��ѧ��ͳ������
	 * @param xmdm
	 * @param xn
	 * @return
	 */
	protected List<String[]> getLzjxjData(String xmdm, String xn,String[] title) {
		
		DAO dao =DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.*,rownum r,");
		sql.append("case when a.zyrs>0 then trunc(nvl(zcpm,0)/zyrs,2) else 0 end zcpmbl,");
		sql.append("case when a.zyrs>0 then trunc(dycpfpm/zyrs,2) else 0 end dycpfpmbl,");
		sql.append("case when a.zyrs>0 then trunc(zycpfpm/zyrs,2) else 0 end zycpfpmbl,");
		sql.append("case when a.zyrs>0 then trunc(tycpfpm/zyrs,2) else 0 end tycpfpmbl ");
		sql.append(" from (select a.xh,b.xm,b.sfzh, b.xymc,b.zymc,b.xb,b.mzmc,b.rxrq,b.zw,b.yhkh,a.hjqk,");
		sql.append("(select xmzzjb from xszz_knsb where xh=a.xh and xn=a.xn");
		sql.append(getQuerySql("5002"));
		sql.append(") knjb,");
		sql.append("(select nvl(pm,0) from pjpy_zhszcpb where jb='1' and xh=a.xh and xn=a.xn) zcpm,");
		sql.append("(select count(*) count from view_xsjbxx where ");
		sql.append("nj=(select nj from view_xsjbxx where xh=a.xh)");
		sql.append("and xydm=(select xydm from view_xsjbxx where xh=a.xh)) zyrs ");
		sql.append("from gjlzjxj a left join view_xsxxb b on a.xh=b.xh where xn=?");
		sql.append(getQuerySql(xmdm));
		sql.append(") a left join (");
		sql.append("select xh,nvl(sum(dycpf),0) dycpfpm,nvl(sum(zycpf),0) zycpfpm,nvl(sum(tycpf),0) tycpfpm from(");
		sql.append("select xh,case when b.mc='");
		sql.append(title[0]);
		sql.append("'  then a.pm end dycpf,");
		sql.append("case when b.mc='");
		sql.append(title[1]);
		sql.append("'  then a.pm end zycpf,");
		sql.append("case when b.mc='");
		sql.append(title[2]);
		sql.append("'  then a.pm end tycpf ");
		sql.append("from pjpy_zhszcpb a left join pjpy_zctjszb b on a.dm=b.dm ");
		sql.append("where a.jb='2' and b.mc >' '  and a.xn=?) group by xh");
		sql.append(") b on a.xh=b.xh");
		
		return dao.rsToVator(sql.toString(), new String[] { xn, xn },
				new String[] { "r", "xm", "sfzh", "xymc", "zymc", "xh", "xb",
						"mzmc", "rxrq", "zw", "yhkh", "knjb", "zyrs", "zcpm",
						"zcpmbl", "dycpfpmbl", "zycpfpmbl", "tycpfpmbl", "hjqk"});
	}
	
	
	/**
	 * ����ѧ��ͳ������
	 * @return
	 */
	public List<String[]> getHjxfData(String xmdm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,b.hjje,b.jmje,b.dkje from(");
		sql.append("select a.xh,sum(a.sqzje) qfje,");
		sql.append("(select xymc from view_xsjbxx where xh=a.xh) xymc,");
		sql.append("(select xm from view_xsjbxx where xh=a.xh) xm,");
		sql.append("(select xb from view_xsjbxx where xh=a.xh) xb,");
		sql.append("(select nj||'  '||zymc||'  '||bjmc from view_xsjbxx where xh=a.xh) njzybj,");
		sql.append("(select jg from xsxxb where xh=a.xh) jg,");
		sql.append("(select jtqkjj from xsxxb where xh=a.xh) jtqkjj,");
		sql.append("(select ssbh from view_xsjbxx where xh=a.xh) ssbh,");
		sql.append("(select lxdh from view_xsjbxx where xh=a.xh) lxdh ");
		sql.append("from hjxfb a where 1=1 ");
		sql.append(getQuerySql(xmdm));
		sql.append("group by xh) a left join (");
		sql.append("select xh,nvl(sum(sqhj),0) hjje,nvl(sum(sqjm),0) jmje,nvl(sum(sqdk),0) dkje from (");
		sql.append("select a.xh,");
		sql.append("case when a.sqjffs='����' then sum(a.sqzje) end sqhj,");
		sql.append("case when a.sqjffs='����' then sum(a.sqzje) end sqjm,");
		sql.append("case when a.sqjffs='����' then sum(a.sqzje) end sqdk ");
		sql.append("from hjxfb a group by xh,sqjffs");
		sql.append(") group by xh ) b on a.xh=b.xh");
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {}, new String[] {
			"xymc", "xm", "xb", "njzybj", "jg", "qfje", "jtqkjj", "ssbh",
			"lxdh", "dkje", "jmje", "hjje" });
		
		for (String[] str : result) {
			str[4] = new XsxxglDAO().dzxxdmToMc(str[4]);
		}
		
		return result ;
		
	}

	/**
	 * ���ظ��������Ѽ���
	 * @param shjb
	 * @param sqzq
	 * @param xn
	 * @param xh
	 * @param xq
	 * @return
	 */
	public String getKnjbForXh(String shjb, String sqzq, String xn, String xh, String xq) {
		
		DAO dao = DAO.getInstance();
		
		StringBuilder query = new StringBuilder(" where xh=? ");
		
		if(shjb.equalsIgnoreCase("һ�����")){
			query.append( " and shzt1 = 'ͨ��' ");
		}else if(shjb.equalsIgnoreCase("�������")){
			query.append( " and shzt2 = 'ͨ��' ");
		}else if(shjb.equalsIgnoreCase("�������")){
			query.append( " and shzt3 = 'ͨ��' ");
		}
		
		if(sqzq.equalsIgnoreCase("������")){
			
		}else if(sqzq.equalsIgnoreCase("ѧ��")){
			query.append( " and xn = '");
			query.append(xn);
			query.append("' ");
		}else if(sqzq.equalsIgnoreCase("ѧ��")){
			query.append(" and xn = '");
			query.append(xn);
			query.append("' ");
			query.append(" and xq = '");
			query.append(xq);
			query.append("' ");
		}
		
		query.append(" order by sqsj ");
		
		String sql = "select xmzzjb from xszz_knsb";
		String knjb = dao.getOneRs(sql+query.toString(), new String[]{xh}, "xmzzjb");
		return knjb;
	}
	
		
	
	/**
	 * ������ͳ������
	 * @return
	 */
	protected List<HashMap<String,String>> getKnsData(String xmdm,String xydm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,a.xh,a.xn,a.xq,b.xm,b.xb,b.mzmc,b.nj,b.bysj,b.zymc,b.jg,b.zzmmmc,b.jtqkjj,");
		sql.append("'' bkqk,");
		sql.append("'' hjqk,");
		sql.append("'' wjqk,");
		sql.append("'' szzqk,");
		sql.append("a.xmzzjb,");
		sql.append("case when a.xmzzje='���漰���' then '0' else nvl(a.xmzzje,0) end xmzzje,");
		sql.append("b.ssbh,b.lxdh,b.yhkh,b.sfzh,b.jtyb,b.jtdz,b.jtdh ");
		sql.append("from xszz_knsb a left join view_xsxxb b on a.xh=b.xh where xydm=?");
		
		List<HashMap<String,String>> result = dao.getList(sql.toString(), new String[] {xydm}, new String[] {
				"xh", "xm","xn","xq", "xb", "mzmc", "nj", "bysj", "zymc", "jg",
				"zzmmmc", "jtqkjj", "bkqk", "hjqk", "wjqk", "szzqk", "xmzzjb",
				"xmzzje", "ssbh", "lxdh", "yhkh", "sfzh", "jtyb", "jtdz",
				"jtdh" });
		
		for (HashMap<String,String> map : result) {
			map.put("jg", new XsxxglDAO().dzxxdmToMc(map.get("jg")));
		}
		
		return result ;
	}

	/**������������
	 * @param xmdm
	 */
	private HashMap<String, String> getQuerySqlBySqzq(String xmdm) {
		DAO dao = DAO.getInstance();
		String sqzq = getOneValue("xszz_zzxmb", "sqzq", "xmdm", xmdm);
		StringBuilder query = new StringBuilder();
		StringBuilder bxqQuery = new StringBuilder();
		if ("ѧ��".equals(sqzq)) {
			HashMap<String, String> xnxq = new HashMap<String, String>();
			xnxq.put("xn", Base.currXn);
			xnxq.put("xq", Base.currXq);

			HashMap<String, String> map = dao.getBeforeXq(xnxq);

			query.append(" and xn='");
			query.append(map.get("xn"));
			query.append("' and xq='");
			query.append(map.get("xq"));
			query.append("' ");

			bxqQuery.append(" and xn='");
			bxqQuery.append(Base.currXn);
			bxqQuery.append("' and xq='");
			bxqQuery.append(Base.currXq);
			bxqQuery.append("' ");
		} else {
			query.append(" and xn='");
			query.append(Base.beforXn);
			query.append("'");

			bxqQuery.append(" and xn='");
			bxqQuery.append(Base.currXn);
			bxqQuery.append("' ");
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sxq", query.toString());
		map.put("bxq", bxqQuery.toString());
		return map;
	}
	
	
	/**
	 *������ ͳ������
	 * @param xmdm
	 * @param flg
	 * @param xydm
	 * @return
	 */
	protected List<HashMap<String, String>> getSxqTj(String xmdm,String flg,String xydm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.fjmc,nvl(b.count,0) count from xszz_xmfjqkb a ");
		sql.append("left join (select count(*) count,xmzzjb from xszz_knsb a where ");
		sql.append("exists (select 1 from view_xsjbxx where xh=a.xh and xydm=?) ");
		if ("sxq".equals(flg)) {
			sql.append(getQuerySqlBySqzq(xmdm).get("sxq"));
		} else {
			sql.append(getQuerySqlBySqzq(xmdm).get("bxq"));
		}
		
		sql.append("group by xmzzjb) b ");
		sql.append("on a.fjmc = b.xmzzjb where a.xmdm=?");
		
		return dao.getList(sql.toString(), new String[] {xydm,xmdm}, new String[] {"fjmc","count"});
	}

	/**
	 * ��õ���ѧ����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getExpXsList(String xmdm,
			List<String> xhList) {

		// ѧУ����
		String xxdm = Base.xxdm;

		DAO dao = DAO.getInstance();
		String sqzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm", xmdm);
		String xmb = dao.getOneValue("xszz_zzxmb", "xmb", "xmdm", xmdm);

		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc" };

		StringBuilder sql = new StringBuilder();

		sql.append("select xh,xm,xb,nj,xymc,zymc,bjmc ");
		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			sql.append(",(select b.zcbjpm from xsqtxxb b where a.xh = b.xh ");
			sql.append(" and (select sqzq from xszz_zzxmb where xmdm = '"
					+ xmdm + "') =");
			sql.append("(select sqzq from xszz_zzxmb where xmdm = '5005') ");
			sql.append(" and exists(select 1 from " + xmb + " c ");
			sql.append("where 1 = 1 ");
			sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and b.xn = c.xn" : "");
			sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and b.xn = c.xn" : "");
			sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and b.xq = c.xq" : "");
			sql.append("���".equalsIgnoreCase(sqzq) ? " and b.nd = c.nd" : "");
			sql.append(")");
			sql.append(") zcbjpm ");

			colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "zcbjpm" };
		}
		sql.append("from view_xsjbxx a ");

		if (xhList != null && xhList.size() > 0) {
			for (int i = 0; i < xhList.size(); i++) {
				if (i == 0) {
					sql.append("where xh = '" + xhList.get(i) + "' ");
				} else {
					sql.append("or xh = '" + xhList.get(i) + "' ");
				}
			}
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, colList);

		return list;
	}
	
	
	
	/**
	 * ����-ͳ��-������ѧ��
	 * @return
	 */
	public List<String[]> getGjzxjData(String xmdm,String xn,String xq) {
		DAO dao = DAO.getInstance();
		String knsDm = XszzXmdm.XSZZ_KNS;
		String[] knjb = getKnjbData();
		String[] colList = new String[] {"xymc","xyzrs","bksrs","zksrs","sqrs"}; 
		StringBuilder sql = new StringBuilder();
		String[] outputColumn = new String[colList.length+(null==knjb ? 0 : knjb.length*11)+1];
		outputColumn[outputColumn.length-1] = "zj";
		System.arraycopy(colList, 0, outputColumn, 0, colList.length);
		
		sql.append("select a.*,(select bmmc from zxbz_xxbmdm where bmdm=a.xydm) xymc ");
		if (null != knjb && knjb.length>0) {
			sql.append(",");
			String[] temp = new String[knjb.length*11];
			String[] knhj = new String[knjb.length];
			
			for (int i = 0 ; i < knjb.length ; i++) {
				
				int count = i==0 ? 0 : 11;
				
				knhj[i] = "knhj"+i;
				
				temp[0+count*i] = "knhj"+i;
				temp[1+count*i] = "knbl"+i;
				temp[2+count*i] = "bksxj"+i;
				temp[3+count*i] = "bkynj"+i;
				temp[4+count*i] = "bkenj"+i;
				temp[5+count*i] = "bksnj"+i;
				temp[6+count*i] = "bksinj"+i;
				
				temp[7+count*i] = "zksxj"+i;
				temp[8+count*i] = "zkynj"+i;
				temp[9+count*i] = "zkenj"+i;
				temp[10+count*i] = "zksnj"+i;
				//���Ѻϼ�
				sql.append("nvl(b.knhj");
				sql.append(i);
				sql.append(",0) knhj");
				sql.append(i);
				sql.append(",");
				//���ѱ���
				sql.append("case when a.xyzrs!=0 and b.knhj");
				sql.append(i);
				sql.append(" is not null then trunc(b.knhj");
				sql.append(i);
				sql.append("/a.xyzrs*100,2) else 0.0 end knbl");
				sql.append(i);
				sql.append(",");
				//������С��
				sql.append("nvl(b.bksxj");
				sql.append(i);
				sql.append(",0) bksxj");
				sql.append(i);
				sql.append(",");
				//����һ�꼶
				sql.append("nvl(b.bkynj");
				sql.append(i);
				sql.append(",0) bkynj");
				sql.append(i);
				sql.append(",");
				//���ƶ��꼶
				sql.append("nvl(b.bkenj");
				sql.append(i);
				sql.append(",0) bkenj");
				sql.append(i);
				sql.append(",");
				//�������꼶
				sql.append("nvl(b.bksnj");
				sql.append(i);
				sql.append(",0) bksnj");
				sql.append(i);
				sql.append(",");
				//���������꼶
				sql.append("nvl(b.bksinj");
				sql.append(i);
				sql.append(",0) bksinj");
				sql.append(i);
				sql.append(",");
				//ר��һ�꼶
				sql.append("nvl(b.zkynj");
				sql.append(i);
				sql.append(",0) zkynj");
				sql.append(i);
				sql.append(",");
				//ר�ƶ��꼶
				sql.append("nvl(b.zkenj");
				sql.append(i);
				sql.append(",0) zkenj");
				sql.append(i);
				sql.append(",");
				//ר�����꼶
				sql.append("nvl(b.zksnj");
				sql.append(i);
				sql.append(",0) zksnj");
				sql.append(i);
				sql.append(",");
				//ר����С��
				sql.append("nvl(b.zksxj");
				sql.append(i);
				sql.append(",0) zksxj");
				sql.append(i);
				sql.append(",");
			}
			
			System.arraycopy(temp, 0, outputColumn, colList.length, temp.length);
			
			StringBuilder zjsql = new StringBuilder();
			for (String str : knhj) {
				if (!Base.isNull(str)) {
					zjsql.append("to_number(nvl(b.");
					zjsql.append(str);
					zjsql.append(",0))+");
				}
			}
			zjsql.deleteCharAt(zjsql.length()-1);
			sql.append(zjsql);
			sql.append(" zj ");
		}
		
		
		sql.append(" from (select xydm,(select count(*) from view_xsjbxx where xydm=a.xydm) xyzrs,");
		sql.append("sum(bks) bksrs,sum(zks) zksrs,");
		sql.append("(select count(*) from gjzxj b where exists (select 1 from view_xsjbxx where xh=b.xh and xydm=a.xydm) ");
		sql.append(getQuerySql(xmdm));
		sql.append(") sqrs ");
		sql.append(" from(select xydm,nvl(case when xz>4 or xz=4 then count(*) end,0) bks,");
		sql.append(" nvl(case when xz<4 or xz is null then count(*) end,0) zks ");
		sql.append(" from view_xsjbxx group by xydm,xz ");
		sql.append(") a group by xydm order by xydm ) a  ");
		sql.append("left join ( select xydm ");
		
		if (null != knjb && knjb.length>0) {
			sql.append(",");
			for (int i = 0 ; i < knjb.length ; i++) {
				sql.append("sum(knhj");
				sql.append(i);
				sql.append(") knhj");
				sql.append(i);
				sql.append(",");
				
				sql.append("sum(bksxj");
				sql.append(i);
				sql.append(") bksxj");
				sql.append(i);
				sql.append(",");
				
				sql.append("sum(zksxj");
				sql.append(i);
				sql.append(") zksxj");
				sql.append(i);
				sql.append(",");
				
				sql.append("sum(bkynj");
				sql.append(i);
				sql.append(") bkynj");
				sql.append(i);
				sql.append(",");
				
				sql.append("sum(bkenj");
				sql.append(i);
				sql.append(") bkenj");
				sql.append(i);
				sql.append(",");
				
				sql.append("sum(bksnj");
				sql.append(i);
				sql.append(") bksnj");
				sql.append(i);
				sql.append(",");
				
				sql.append("sum(bksinj");
				sql.append(i);
				sql.append(") bksinj");
				sql.append(i);
				sql.append(",");
				
				sql.append("sum(zkynj");
				sql.append(i);
				sql.append(") zkynj");
				sql.append(i);
				sql.append(",");
				
				sql.append("sum(zkenj");
				sql.append(i);
				sql.append(") zkenj");
				sql.append(i);
				sql.append(",");
				
				sql.append("sum(zksnj");
				sql.append(i);
				sql.append(") zksnj");
				sql.append(i);
				sql.append(",");
			}
			
			sql.deleteCharAt(sql.length()-1);
			sql.append(" from  (select xydm ");
			
			for (int i = 0 ; i < knjb.length ; i++) {
				sql.append(",");
				//�ϼ�
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(bksxj+zksxj) else 0 end knhj");
				sql.append(i);
				sql.append(",");
				//������С��
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(bksxj) else 0 end bksxj");
				sql.append(i);
				sql.append(",");
				//ר����С��
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(zksxj) else 0 end zksxj");
				sql.append(i);
				sql.append(",");
				//������һ�꼶
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(bkynj) else 0 end bkynj");
				sql.append(i);
				sql.append(",");
				//���������꼶
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(bkenj) else 0 end bkenj");
				sql.append(i);
				sql.append(",");
				
				//���������꼶
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(bksnj) else 0 end bksnj");
				sql.append(i);
				sql.append(",");

				//���������꼶
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(bksinj) else 0 end bksinj");
				sql.append(i);
				sql.append(",");

				//ר��һ�꼶
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(zkynj) else 0 end zkynj");
				sql.append(i);
				sql.append(",");

				//ר�ƶ��꼶
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(zkenj) else 0 end zkenj");
				sql.append(i);
				sql.append(",");

				//ר�����꼶
				sql.append("case when xmzzjb = '");
				sql.append(knjb[i]);
				sql.append("' then sum(zksnj) else 0 end zksnj");
				sql.append(i);
//				sql.append(",");
			}
//			sql.deleteCharAt(sql.length()-1);
		}
		
		
		sql.append(" from (select xydm,xmzzjb,");
		sql.append("sum(bkynj+bkenj+bksnj+bksinj) bksxj,");
		sql.append("sum(zkynj+zkenj+zksnj) zksxj,");
		sql.append("sum(bkynj) bkynj,");
		sql.append("sum(bkenj) bkenj,");
		sql.append("sum(bksnj) bksnj,");
		sql.append("sum(bksinj) bksinj,");
		sql.append("sum(zkynj) zkynj,");
		sql.append("sum(zkenj) zkenj,");
		sql.append("sum(zksnj) zksnj ");
		sql.append(" from (select xydm, xmzzjb,");
		sql.append("nvl(case when xz>3 and dqnj=1 then count(*) end,0) bkynj,");
		sql.append("nvl(case when xz>3 and dqnj=2 then count(*) end,0) bkenj,");
		sql.append("nvl(case when xz>3 and dqnj=3 then count(*) end,0) bksnj,");
		sql.append("nvl(case when xz>3 and dqnj=4 then count(*) end,0) bksinj,");
		sql.append("nvl(case when xz<4 and dqnj=1 then count(*) end,0) zkynj,");
		sql.append("nvl(case when xz<4 and dqnj=2 then count(*) end,0) zkenj,");
		sql.append("nvl(case when xz<4 and dqnj=3 then count(*) end,0) zksnj ");
		sql.append(" from (select a.xh,nvl(b.xz,0) xz ,b.nj,b.xydm,a.xmzzjb,");
		sql.append("case when (select dqxq from xtszb)=01 then (select dqnd from xtszb)-b.nj+1 ");
		sql.append("when (select dqxq from xtszb)=02 then (select dqnd from xtszb)-b.nj end dqnj ");
		sql.append(" from (select * from xszz_knsb where xn='");
		sql.append(xn);
		sql.append("' and xq='");
		sql.append(xq);
		sql.append("') a left join view_xsjbxx b on a.xh=b.xh where 1=1 ");
		sql.append(getQuerySql(knsDm));
		sql.append(") group by xz,dqnj,xydm,xmzzjb )group by xydm,xmzzjb");
		sql.append(") group by xydm,xmzzjb )group by xydm ) b on a.xydm=b.xydm ");
		
		return dao.rsToVator(sql.toString(), new String[] {}, outputColumn);
		
	}
	
	/**
	 * �����������Ѽ���
	 * @return
	 */
	public String[] getKnjbData() {
		String xmdm = XszzXmdm.XSZZ_KNS;
		DAO dao = DAO.getInstance();
		String[] knjb = null;
		try {
			knjb = dao.getArray("select * from xszz_xmfjqkb where xmdm=?", new String[] {xmdm}, "fjmc");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return knjb;
	}
	
	
	/**
	 * ����-������ѧ��ͳ����������������
	 * @param xymc
	 * @param flg
	 * @return
	 */
	public HashMap<String, String> getLsXs(String xymc,String flg) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select xydm,(select bmmc from zxbz_xxbmdm where bmdm=a.xydm) xymc,");
		sql.append("sum(bks)+sum(zks) xj, sum(bks) bks,sum(zks) zks,sum(sqzxj) sqzxj from (");
		sql.append("select xydm,sum(bks) bks,sum(zks) zks,sum (sqzxj)sqzxj from (select xydm, ");
		
		if ("xs".equals(flg)) {
			sql.append("nvl(case when xslb='����' and bks='������'  then count(bks) end,0) bks,");
			sql.append("nvl(case when xslb='����' and bks='ר����'  then count(bks) end,0) zks,");
			sql.append("nvl(case when xslb='����' and bks='������' then sum(sqzxj) end,0) sqzxj ");
		} else {
			sql.append("nvl(case when xslb='����' and bks='������'  then count(bks) end,0) bks,");
			sql.append("nvl(case when xslb='����' and bks='ר����'  then count(bks) end,0) zks,");
			sql.append("nvl(case when xslb='����' and bks='������' then sum(sqzxj) end,0) sqzxj ");
		}
		
		sql.append("from (select xydm, case when dqnj = 1 then '����' else '����'  end xslb ,");
		sql.append("case when xz>3 then '������' else 'ר����' end bks,");
		sql.append("sqzxj from (select nvl(b.xz,0) xz,");
		sql.append("b.nj,b.xydm,");
		sql.append("case when exists (select 1 from gjzxj where xh=b.xh) then 1 else 0 end sqzxj,");
		sql.append("case when (select dqxq from xtszb)=01 then (select dqnd from xtszb)-b.nj+1 ");
		sql.append("when (select dqxq from xtszb)=02 then (select dqnd from xtszb)-b.nj end dqnj ");
		sql.append("from  view_xsjbxx b ) ");
		sql.append(") a group by xydm,xslb,bks ) group by xydm) a group by xydm");
		sql.append(") where xymc=?");
		
		return dao.getMap(sql.toString(), new String[] {xymc}, new String[] {"xj","bks","zks","sqzxj"});
	}
	
	
	/**
	 * �Ͷ��ڼ��ۼƲ��Ƹ���޿�Ŀ��
	 * @param xh
	 * @return
	 */
	public String getBjgs(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) count from cjb where cj<60 and xh=?";
		
		
		return dao.getOneRs(sql, new String[] {xh}, "count");
	}
	
	
	/**
	 * ����Υ��
	 * 
	 * @param model
	 * @return
	 */
	public String isWj(XszzTyForm model, HashMap<String, String> xmInfo) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("select case when count>0 then '��' else '��' end sfwj from (");
		sql.append("select count(*) count from wjcfb b where xh=? ");
		sql.append(getSqzqQuery(model, xmInfo));
		sql.append(")");

		return dao.getOneRs(sql.toString(), new String[] { model.getXh() },
				"sfwj");
	}
	
	
	/**
	 * ���������ϰ������������
	 * @param model
	 * @return
	 */
	public String sxnDyf(XszzTyForm model) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select fs dyfs from (");
		sql.append("select a.*,(select mc from pjpy_zctjszb b where a.dm=b.dm) mc from pjpy_zhszcpb a ");
		sql.append("where jb='2' and xh=? and xn=?");
		sql.append(") where mc like '%����%'");
		
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),Base.beforXn}, "dyfs");
	}
	
	/**
	 * ��ȡѧ���ɼ� 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getXscjList(XszzTyForm model) {
		DAO dao = DAO.getInstance();
		String sqzq = getOneValue("xszz_zzxmb", "sqzq", "xmdm", model.getXmdm());
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xn,xq,xn,xh,xm,xymc,bjmc,kcxz,kcmc,cj from view_cjb where xh=?");
		
		if ("ѧ��".equals(sqzq)) {
			sql.append(" and xq=(select dqxq from xtszb)");
		}else if("���".equalsIgnoreCase(sqzq)){
			sql.append(" and nd=(select dqnd from xtszb)");
		}else if("ѧ��".equalsIgnoreCase(sqzq)){
			sql.append(" and xn=(select dqxn from xtszb)");			
		}
		
		String[] outputValue = {"xn","xq","xh","xm","xymc","bjmc","kcxz","kcmc","cj"};
		return dao.getList(sql.toString(), new String[] {model.getXh()}, outputValue);
	}
	
	
	/**
	 * ѧ����޿���Ϣ
	 * @param map
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXnbxkxx(HashMap<String, String> map){
		DAO dao = DAO.getInstance();
		String sql = "select xh,(select count(*) from cjb b where a.xh=b.xh and a.xn=b.xn and kclx like '%����%')bxkms,(select count(*) from cjb b where a.xh=b.xh and a.xn=b.xn and kclx like '%����%' and cj='����')yxkms, (select count(*) from cjb b where a.xh=b.xh and a.xn=b.xn and kclx like '%����%' and cj='����')lhkms from cjb a where xh=? and xn=?";
		return dao.getMap(sql, new String[]{map.get("xh"), map.get("xn")}, new String[]{"bxkms","yxkms","lhkms"});
	}
	

	/**
	 * ѧ���ۺϲ�����Ϣ
	 * @param map
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZhszcpxx(HashMap<String, String> map){
		DAO dao = DAO.getInstance();
		String sql = StringUtils.joinStr("select zhzf,(select count(*) from view_xsjbxx b where a.bjdm=b.bjdm)zrs,",
										 "zhzfpm from (select xh,xn,bjdm,zhzf,",
										 "dense_rank() over (partition by xydm,nj,bjdm order by to_number(nvl(zhzf,0)) desc nulls last)zhzfpm",
										 " from view_hndx_zhszf) a where xh=? and xn=?");		
		return dao.getMap(sql, 
				          new String[]{map.get("xh"), map.get("xn")}, 
				          new String[]{"zhzf", "zrs", "zhzfpm"});		
	}
	
	/**
	 * ��ȡ��ӡ����������ֶ�
	 * @param tjbbdm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTjbbZd(String tjbbdm){
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] outputValue = {"zd", "sfbt"};
		String sql = "select sqzq from xszz_zzxmb where xmdm=(select xmdm from xszz_zztjbb where tjbbdm=?)";
		String sqzq = dao.getOneRs(sql, new String[]{tjbbdm}, "sqzq");
		StringBuilder sb = new StringBuilder();
		
		if("ѧ��".equalsIgnoreCase(sqzq)){
			sb.append(" and zd<>'nd' and zd<>'xq'");
		}else if("���".equalsIgnoreCase(sqzq)){
			sb.append(" and zd<>'xq' and zd<>'xq'");
		}else if("ѧ��".equalsIgnoreCase(sqzq)){
			sb.append(" and zd<>'nd'");
		}
		sql = "select zd,sfbt from xszz_zztjbzdb where tjbbdm=?";
		list = dao.getList(sql+sb, new String[]{tjbbdm}, outputValue);
		return list;
	}
	
	
	/**
	 * ɾ����Ŀ���״̬
	 * @param pkValue
	 * @param xmb
	 * @return
	 * @throws Exception
	 */
	public boolean delXmshZt(String[] pkValue,String xmb) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xszz_shztb where xh||!!@@!!||sqsj||!!@@!!||xmdm||'");
		sql.append(xmb);
		sql.append("' in (");
		
		for (int i = 0 ; i < pkValue.length ; i ++) {
			
			if (i==pkValue.length-1) {
				sql.append("?,");
			} else {
				sql.append("?)");
			}
		}
		
		return dao.runUpdate(sql.toString(), pkValue);
	}
	
	
	
	public boolean saveZzxb(String[] pkValues,XszzTyForm model) throws Exception {
		
		DAO dao = DAO.getInstance();
		
		int count = 0 ;
		String nowTime = dao.getOneRs("select to_char(sysdate,'yyyymmdd') now from dual ", new String[] {}, "now");
		String[] sqlArr = new String[pkValues.length*2];
		
		
		for (int i = 0 ; i < pkValues.length ; i++) {
			
			String[] tempArr = pkValues[i].split("!!@@!!");
			
			if (null != tempArr && tempArr.length ==4) {
				
				String xh = tempArr[0];
				String sqsj = tempArr[1];
				String xmdm = tempArr[2];
				String xmb = tempArr[3];
				
				StringBuilder sql = new StringBuilder();
				
				sql.append("insert into ")
				   .append(xmb)
				   .append(" select '")
				   .append(Base.isNull(model.getSave_xn()) ? "" : model.getSave_xn())
				   .append("','")
				   .append(Base.isNull(model.getSave_xq()) ? "" : model.getSave_xq())
				   .append("','")
				   .append(Base.isNull(model.getSave_nd()) ? "" : model.getSave_nd())
				   .append("',to_char(sysdate,'yyyymmdd'),")
				   .append("xmdm,xh,xmzzjb,xmzzje,zje,xnje,sqsm,'ͨ��','','','ͨ��','','',")
				   .append("'ͨ��','','','ͨ��','','','ͨ��','ͨ��','ͨ��','','','',xxqkpj,sfblxy,")
				   .append("hdbh,dknx,jkzk,sfdk,bkkm,'")
				   .append(model.getSave_bz())
				   .append("',sqly,filename,filepath,'','','','','','','','','','����' ")
				   .append("from ")
				   .append(xmb)
				   .append(" where xh='")
				   .append(xh)
				   .append("' and sqsj='")
				   .append(sqsj).append("' ");
				
				sqlArr[count] = sql.toString();
				count ++;
				
				sql = new StringBuilder();
				
				sql.append("insert into xszz_shztb values ('")
				   .append(xmdm)
				   .append("','")
				   .append(xh)
				   .append("','")
				   .append(nowTime)
				   .append("','")
				   .append(Base.isNull(model.getSave_xn()) ? "" : model.getSave_xn())
				   .append("','")
				   .append(Base.isNull(model.getSave_xq()) ? "" : model.getSave_xq())
				   .append("','")
				   .append(Base.isNull(model.getSave_nd()) ? "" : model.getSave_nd())
				   .append("','ͨ��','ͨ��','ͨ��','ͨ��','ͨ��','ͨ��','ͨ��','','') ");
				   
				sqlArr[count] = sql.toString();
				count ++;
			}
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}

	
	
	/**
	 * �㽭�Ƽ���ѧ������ֶ���Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getZxdkInfo(XszzTyForm model){
		
		DAO dao = DAO.getInstance();
		
		String xh = model.getXh();
		String sqsj = model.getSqsj();
		
		String sql = "select * from gjzxdkb where xh=? and sqsj=? ";
		String[] outputColumn = new String[] { "xfdkje", "shfdkje", "zxfdkje",
				"dkhth", "jbyhzh", "jbyhzih", "pzrq", "hkksrq", "hkjsrq","zje" };

		return dao.getMap(sql, new String[] {xh,sqsj}, outputColumn);
	
	}
	
	
	/**
	 * ɾ��jtqkdcb���в�����
	 * ��ѧ���ļ�ͥ��Ա��¼
	 * @return boolean
	 * @throws Exception
	 * author qlj
	 */
	public boolean delXsJtcy() throws Exception{
		
		DAO dao=DAO.getInstance();
		
		String sql=" delete from xszz_jtcyb a where not exists(select 1 from jtqkdcb b where a.xh=b.xh ) ";
		
		return dao.runUpdate(sql,new String[]{});
	}
	

	/**
	 * �������϶����м�ͥ�����������ͬ��
	 * @return
	 * @throws Exception 
	 */
	public boolean saveJtqkdcDataFromKns() throws Exception{
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into jtqkdcb ");
		sql.append("(xn,nd,xq,sqsj,xh,xmdm,JTDH,JTDZ,JTYB,JTRJSR,SFGC,SFDQ,");
		sql.append("LSZN,SFDB,YHZZQK,JTSZQK,");
		sql.append("TFSJQK,CJNMQK,JTSYQK,JTQZQK,JTQTQK,JTRS,JTNZSR,JTYZSR,");
		sql.append("JTRJYSR,JTSRLY,JTDDXRS,JTJBJJQK,DYPDDJ,BRHJQK,TPSC,JTHK,");
		sql.append("SRLY,SZSHENG,SZSHI,SZXIAN,SZZHEN,SZCUN,SFYFJT,SFGE,SFCJ,");
		sql.append("SFPKDQSSMZ,SFQZ,SFPKX,FMJK,FMJZ,PKXJB,scdz,sqly,bz) ");
		sql.append("select xn,nd,xq,sqsj,xh,'");
		sql.append(XszzXmdm.XSZZ_JTQKDC);
		sql.append("', JTDH,JTDZ,JTYB,JTRJSR,SFGC,SFDQ,LSZN,SFDB,YHZZQK,JTSZQK,");
		sql.append("TFSJQK,CJNMQK,JTSYQK,JTQZQK,JTQTQK,JTRS,JTNZSR,JTYZSR,");
		sql.append("JTRJYSR,JTSRLY,JTDDXRS,JTJBJJQK,DYPDDJ,BRHJQK,TPSC,JTHK,");
		sql.append("SRLY,SZSHENG,SZSHI,SZXIAN,SZZHEN,SZCUN,SFYFJT,SFGE,SFCJ,");
		sql.append("SFPKDQSSMZ,SFQZ,SFPKX,FMJK,FMJZ,PKXJB,scdz,sqly,bz ");
		sql.append("from xszz_knsb a where not exists (select 1 from jtqkdcb b where a.xh=b.xh and a.sqsj=b.sqsj)");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	/**
	 * ͬ�������������״̬����ͥ�������
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcShztFromKns() throws Exception{
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xszz_shztb(xmdm,xh,sqsj,xn,xq,nd,");
		sql.append("bzrsh,fdysh,xysh,xxsh,shzt1,shzt2,shzt3 ) ");
		sql.append("select '");
		sql.append(XszzXmdm.XSZZ_JTQKDC);
		sql.append("',xh,sqsj,xn,xq,nd,bzrsh,fdysh,xysh,xxsh,shzt1,shzt2,shzt3 ");
		sql.append(" from xszz_shztb a where xmdm='");
		sql.append(XszzXmdm.XSZZ_KNS);
		sql.append("' and not exists (select 1 from xszz_shztb b where b.xmdm='");
		sql.append(XszzXmdm.XSZZ_JTQKDC);
		sql.append("' and a.xh=b.xh and a.sqsj=b.sqsj)");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	
	/**
	 * ɾ����ͥ�����������������Ϣ
	 * @param xh
	 * @param sqsj
	 * @return
	 * @throws Exception
	 */
	public boolean delJtqkdc(String xh,String sqsj) throws Exception{
		DAO dao=DAO.getInstance();
		String[] sqlArr = new String[2];
		
		sqlArr[0] = "delete from jtqkdcb where xh=? and sqsj=? ";
		sqlArr[1] = "delete from xszz_shztb where xmdm=? and xh=? and sqsj=?";
		
		
		return dao.runUpdate(sqlArr[0], new String[]{xh,sqsj}) 
			&& dao.runUpdate(sqlArr[1], new String[]{XszzXmdm.XSZZ_JTQKDC,xh,sqsj});
	}
	
	
	/**
	 * ɾ����ͥ�������ȫ������
	 * @return
	 * @throws Exception
	 */
	public boolean delAllJtqkdc() throws Exception{
		DAO dao=DAO.getInstance();
		String[] sqlArr = new String[2];
		
		sqlArr[0] = "delete from jtqkdcb";
		sqlArr[1] = "delete from xszz_shztb where xmdm=?";
		
		
		return dao.runUpdate(sqlArr[0], new String[]{}) 
			&& dao.runUpdate(sqlArr[1], new String[]{XszzXmdm.XSZZ_JTQKDC});
	}
	
	
	/**
	 * ��ѧ�ꡢѧ�Ų�ѯ������ɼ�
	 * @param xn
	 * @return
	 */
	public List<HashMap<String,String>> getBjgcjByXn(String xn,String xh){
		DAO dao=DAO.getInstance();
		String sql = "select kcmc,cj from cjb where cj<60 and xn=? and xh=?";
		
		return dao.getListNotOut(sql, new String[]{xn,xh});
	}

	/**
	 * ����ѧ�Ų�ѯ��������Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getKnsInfo(String xh) {
		DAO dao=DAO.getInstance();
		String sql = "select xn,xmzzjb from xszz_knsb where xh=?";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	public List<HashMap<String,String>>getPjpyInfo(String xh){
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.*, case when a.sqzq='xn' then a.pjxn||'ѧ��'  ");
		sql.append(" when a.sqzq='xq' then a.pjxn||'ѧ��'||a.xqmc||'ѧ��'  ");
        sql.append(" when a.sqzq='nd' then a.pjnd end zqxx from(  ");
		sql.append(" select a.xmdm, a.xmmc,a.sqzq, a.pjxn,a.pjxq, ");
		sql.append(" (select xqmc from xqdzb where xqdm=a.pjxq)xqmc,");
		sql.append(" a.pjnd,a.xmlx,b.spgw, c.shzt ");
		sql.append(" from (select * from xg_pjpy_pjxmshb where xh = ?) c, ");
		sql.append(" xg_pjpy_pjxmwhb a, ");
		sql.append(" (select b.*  from (select splc, max(xh) xh from xg_xtwh_spbz group by splc) a, ");
		sql.append(" xg_xtwh_spbz b where a.splc = b.splc and a.xh = b.xh) b ");
		sql.append("where a.lcid = b.splc ");
		sql.append(" and b.spgw = c.xtgwid ");
		sql.append(" and a.xmdm = c.xmdm ");
		sql.append(" and ((a.sqzq = 'xn' and a.pjxn = c.pjxn) or ");
		sql.append(" (a.sqzq = 'xq' and a.pjxn = c.pjxn and a.pjxq = c.pjxq) or ");
		sql.append(" (a.sqzq = 'nd' and a.pjnd = c.pjnd))and c.shzt='ͨ��')a ");
		
		String[]colList={"xmdm","xmmc","sqzq","pjxn","pjxq","xqmc","pjnd","xmlx","zqxx"};
		return dao.getList(sql.toString(), new String[]{xh}, colList);
	}
	
	
	
	/**
	 * ���治�ɼ����Ŀ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveBkjdxm(XszzTyForm model) throws Exception{
	
		String xmdm = model.getXmdm();
		String[] bkjdxm = model.getBkjdxm();
		List<String[]> params = new ArrayList<String[]>();
		
		if (null != bkjdxm && bkjdxm.length > 0){
			
			for (int i = 0 ; i < bkjdxm.length ; i++){
				
				if (!xmdm.equals(bkjdxm[i])){
					params.add(new String[]{xmdm,bkjdxm[i]});
				}
			}
		}
		
		DAO dao = DAO.getInstance();
		boolean del = dao.runUpdate("delete from xg_xszz_jdszb where xmdm=? or bkjdxm=?", new String[]{xmdm,xmdm});
		if (del){
			int[] result = dao.runBatch("insert into xg_xszz_jdszb(xmdm,bkjdxm) values (?,?) ", params);
			return dao.checkBatchResult(result);
		} else {
			return del;
		}
	}
	
	
	/**
	 * ���ɼ����Ŀ
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 */
	public String[] getBkjdxm(String xmdm) throws SQLException{
		
		String sql = "select bkjdxm xmdm from xg_xszz_jdszb where xmdm=? union select xmdm from xg_xszz_jdszb where bkjdxm=?";
		
		DAO dao = DAO.getInstance();
		return dao.getArray(sql, new String[]{xmdm,xmdm}, "xmdm");
	}
	
	
	
	/**
	 * �����Ѿ����ڵĲ��ɼ�õ���Ŀ
	 */
	public Map<String,String> getExistsBkjdxm(String xh , String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xmdm,xmmc from (")
		   .append("select a.xh,a.xmdm,b.xmmc,b.sqzq,a.xn,a.xq,a.nd,")
		   .append("case when b.shjb='һ�����' then a.shzt1 ")
		   .append("when b.shjb='�������' then a.shzt2 ")
		   .append("when b.shjb='�������' then a.shzt3 ")
		   .append("else 'ͨ��' end shzt ")
		   .append("from xszz_shztb a left join xszz_zzxmb b on a.xmdm=b.xmdm ")
		   .append("where xh=? ")
		   .append("and exists (select 1 from (select bkjdxm xmdm from xg_xszz_jdszb where xmdm=? ")
		   .append("union select xmdm from xg_xszz_jdszb where bkjdxm=?) d where a.xmdm=d.xmdm) ")
		   .append(") where (sqzq='������' or ")
		   .append("(sqzq='ѧ��' and xn=(select dqxn from xtszb)) or ")
		   .append("(sqzq='ѧ��' and xn=(select dqxn from xtszb) and xq=(select dqxq from xtszb)) or ")
		   .append("(sqzq='���' and nd=(select dqnd from xtszb)))")
		   .append("and shzt='ͨ��' ");
		
		DAO dao = DAO.getInstance();
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xmdm,xmdm});
	}
	
	/**
	 * �ж���Ŀ�Ƿ����ٴ�����
	 * @param xh
	 * @param xmdm
	 * @return
	 * @author honglin
	 * @date 2013-3-12
	 */
	public List<HashMap<String,String>> getSfccsq(String xh , String xmdm){
		DAO dao=DAO.getInstance();
		String sql = "select xh,xmdm from xszz_comm_zzwsb where xh=? and xmdm=?";
		return dao.getListNotOut(sql,  new String[]{xh,xmdm});
	}
}
