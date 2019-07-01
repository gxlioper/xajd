
package xgxt.pjpy.ahjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;

import common.Globals;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս�����ҵѧԺ��������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */ 
public class PjpyAhjgDAO{
	DAO  dao = DAO.getInstance();
	final String XJBJDM = "00001";//�Ƚ��༶����
	final String WMSSDM = "00002";//�����������
	
	List<String> values = new ArrayList<String>();// ���ڴ���ҳ������ֵ

	String xxdm = dao.getXxdm();
	/**
	 * ���÷�����ͨ��������������ֶ������ҳ���ѯTITLE
	 * 
	 * @param enCol
	 * @param cnCol
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle(String[] enCol,
			String[] cnCol) throws Exception {
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> mapTemp = new HashMap<String, String>();
			mapTemp.put("en", enCol[i]);// Ӣ������
			mapTemp.put("cn", cnCol[i]);// ��������
			listTopTr.add(mapTemp);
		}// end for
		return listTopTr;
	}

	/**
	 * ѧ���ɼ���ѯ���
	 * �γ����͹��� Уѡ�޿�
	 * �γ����ƹ��� ����������
	 * @param pjpyahjgxscjqryModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getPjpyXscjResult(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel,
			PjpyAhjgActionForm dataSearchForm, String userType, String userName) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		List<String[]> listRs = new ArrayList<String[]>();
		String sql = "";
		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,xq,cj,xh,xm,xymc,kcxz,bjmc,kcmc," +
					"(select round(avg(b.cj)) from view_cjb b where kcxz <>'Уѡ�޿�' and " +
					"kcmc not like '%����������%' and upper(xkkh) not like '%BY%' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf from view_cjb a";
		} else if (Globals.XXDM_YCSFXY.equalsIgnoreCase(xxdm)||Globals.XXDM_AHZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xq,cj,xh,xm,xymc,kcxz,zymc,bjmc,kcmc,khfs,xf," +
			"(select round(avg(b.cj)) from view_cjb b where a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf from view_cjb a";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,rownum r from (select a.*,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,(select round(avg(cj),2) from view_zhhcjb b where khfs like '����%' and kcmc not like '%����%' and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kscj,(select round(avg(cj),2) from cjb b where khfs like '����%' and kcmc not like '%����%' and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kccj from cjb a,view_xsjbxx b where a.xh=b.xh) a";
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// ���ݴ�ѧ
			// ----------2010/5/25 edit by luojw -------------
//			sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,"
//					+ "rownum r from (select a.*,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,"
//					+ "b.zymc,b.bjdm,b.bjmc,(select round(avg(cj),2) from cjb "
//					+ "b where khfs like '����%' and kcmc not like '%����%' and "
//					+ "a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kscj,(select round(avg(cj),2) "
//					+ "from cjb b where khfs like '����%' and kcmc not like '%����%' and a.xh=b.xh "
//					+ "and a.xn=b.xn and a.xq=b.xq) kccj, "
//					+ "case when a.bkcj is not null and a.cxcj is not null then '������'"
//					+ "when bkcj is not null then a.bkcj || '(�����ɼ�)'"
//					+ "when cxcj is not null then a.cxcj || '(���޳ɼ�)'"
//					+ "else '�����޻򲹿�' end bkcx "
//					+ "from cjb a,view_xsjbxx b where a.xh=b.xh) a";
			
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xq,"
					+ "cj,xh,xm,xymc,kcxz,bjmc,kcmc,(select round(avg(b.cj)) from view_cjb b where kcxz like "
					+ "'%����%' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf,"
					+ "case when a.bkcj is not null and a.cxcj is not null then '������'"
					+ "when bkcj is not null then a.bkcj || '(�����ɼ�)'"
					+ "when cxcj is not null then a.cxcj || '(���޳ɼ�)'"
					+ "else '�����޻򲹿�' end bkcx,xf from view_cjb a";
			// ----------end -------------
		} else {
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xq,cj,xh,xm,xymc,kcxz,bjmc,kcmc,"
					+ "'' pjf from view_cjb a";
		}
		String bzrSql = "";
		//����Ա�û�ֻ��ѯ�༶����
		if ("bzr".equalsIgnoreCase(userType)) {
			bzrSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);// WHERE�������
		String[] opCol = null;
		if (Globals.XXDM_YCSFXY.equalsIgnoreCase(xxdm)) {
			opCol = new String[] { "xn", "xq", "xh", "xm", "xymc",
					"bjmc", "kcmc", "kcxz","khfs", "cj","xf", "pjf" };
		}else if(Globals.XXDM_AHZYJSXY.equalsIgnoreCase(xxdm)){
			opCol = new String[] { "xn", "xq", "xh", "xm", "xymc",
					"zymc","bjmc", "kcmc","khfs", "cj", "pjf" };
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			opCol = new String[] { "xn", "xqmc", "xh", "xm", "xymc", "bjmc",
					"kcmc", "kcxz", "cj", "kscj", "kccj" };
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// ���ݴ�ѧ
			opCol = new String[] { "xn", "xq", "xh", "xm", "xymc", "bjmc",
					"kcmc", "kcxz", "xf", "cj","bkcx" };
		} else {
			opCol = new String[] { "xn", "xq", "xh", "xm", "nj", "xymc", "bjmc",
					"kcmc", "kcxz", "cj" };
		}
		
		sql = "select * from ("
			+ sql
			+ whereSql.toString()
			+ bzrSql
			+ " order by xh) where r<="
			+ (dataSearchForm.getPages().getStart() + dataSearchForm
					.getPages().getPageSize()) + " and r> "
			+ dataSearchForm.getPages().getStart();
		listRs = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return listRs;
	}
	
	/**
	 * ѧ���ɼ���ѯ�����
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public int getPjpyXscjResultNum(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel, String userType, String userName) throws Exception {
		//String sql = "select nj,xydm,zydm,bjdm,xn,xq,cj,xh,xm,xymc,zymc,bjmc,kcmc,(select round(avg(b.cj)) from view_cjb b where kcxz <>'Уѡ�޿�' and kcmc not like '%����������%' and upper(xkkh) not like '%BY%' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf from view_cjb a";
		String sql = "select nj,xydm,zydm,bjdm,xn,xq,cj,xh,xm,xymc,zymc,bjmc,kcmc,'' pjf from view_cjb a";
		String xxdm = Base.xxdm;
		if (Globals.XXDM_YCSFXY.equalsIgnoreCase(xxdm)||Globals.XXDM_AHZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xq,cj,xh,xm,xymc,zymc,kcxz,bjmc,kcmc," +
			"'' pjf from view_cjb a";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select a.* from (select a.*,rownum r,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,'' pjf from cjb a,view_xsjbxx b where a.xh=b.xh) a";
		} else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			sql = "select nj,xydm,zydm,bjdm,xn,xq,cj,xh,xm,xymc,zymc,bjmc,kcmc,(select round(avg(b.cj)) from view_cjb b where kcxz <>'Уѡ�޿�' and kcmc not like '%����������%' and upper(xkkh) not like '%BY%' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf from view_cjb a";
		}
		String bzrSql = "";
		//����Ա�û�ֻ��ѯ�༶����
		if ("bzr".equalsIgnoreCase(userType)) {
			bzrSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);// WHERE�������
//		String[] opCol = new String[] { "xn", "xq", "xh", "xm", "xymc", "zymc",
//				"bjmc", "kcmc", "cj", "pjf" };
		
		
		// ��ֹ�ڴ����  sjf
		StringBuilder builder = new StringBuilder();
		builder.append("select count(1) count from (")
			   .append(sql)
			   .append(whereSql)
			   .append(bzrSql)
			   .append(")");
		
		
		Map<String, String> map = dao.getMap(builder.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, new String[]{"count"});
		return Integer.parseInt(map.get("count"));
	}

	public List<String[]> getPjpyDjkscjResult(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel,
			PjpyAhjgActionForm dataSearchForm) throws Exception {
		List<String[]> listRs = new ArrayList<String[]>();
		String sql = "select rownum r,xn,(select xqmc from xqdzb where xq=xqdm) xq,xh,xm,nj,xymc,bjmc,djksmc,ksrq,cj from view_xsdjksb";
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);// WHERE�������
		String[] opCol = new String[] { "xn", "xq", "xh", "xm", "nj", "xymc",
				"bjmc", "djksmc", "ksrq", "cj" };
		listRs = dao.rsToVator("select * from ("
				+ sql
				+ whereSql
				+ ") where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " and r> "
				+ dataSearchForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return listRs;
	}
	
	public int getPjpyDjkscjResultNum(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel) throws Exception {
		List<String[]> listRs = new ArrayList<String[]>();
		String sql = "select xn,xq,xh,xm,xymc,zymc,bjmc,djksmc,ksrq,cj from view_xsdjksb";
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);// WHERE�������
		String[] opCol = new String[] { "xn", "xq", "xh", "xm", "xymc", "zymc",
				"bjmc", "djksmc", "ksrq", "cj" };
		listRs = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return listRs.size();
	}
	
	/**
	 * ����WHERE������ѯ���
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel)
			throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String xydm = pjpyahjgxscjqryModel.getXydm();
		String zydm = pjpyahjgxscjqryModel.getZydm();
		String bjdm = pjpyahjgxscjqryModel.getBjdm();
		String xn = pjpyahjgxscjqryModel.getXn();
		String nj = pjpyahjgxscjqryModel.getNj();
		String xh = DealString.toGBK(pjpyahjgxscjqryModel.getXh());
		String xm = DealString.toGBK(pjpyahjgxscjqryModel.getXm());
		String xq = pjpyahjgxscjqryModel.getXq();
		String djksmc = pjpyahjgxscjqryModel.getDjksmc();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh like ?");
			values.add("%" + xh + "%");
		}// end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm like ?");
			values.add("%" + xm + "%");
		}// end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}// end if
		if (!StringUtils.isNull(djksmc)) {
			whereSql.append(" and djksmc = ?");
			values.add(djksmc);
		}
		// ---------2010/5/24 edit by luojw ------------
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// ���ݴ�ѧ
			// TODO
			String[] kcxz = pjpyahjgxscjqryModel.getKcxz();
			if (kcxz != null && kcxz.length > 0) {
				whereSql.append(" and (");
				for (int i = 0; i < kcxz.length; i++) {
					if(i == 0){
						whereSql.append(" kcxz = '" + kcxz[i] + "'");
					}else{
						whereSql.append(" or kcxz = '" + kcxz[i] + "'");
					}
				}
				whereSql.append(" )");
			}
		}
		// --------- end -------------
		return whereSql;
	}

	/**
	 * �༶�����ʲ�ѯ���
	 * bjbklqryresult ---- �༶�����ʲ�ѯ���
	 * @param pjpyahjgxscjqryModel(����) �༶�����ʲ�ѯMODEL
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBjbklQryResult(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||bjdm,(case when (to_number(substr(bjbkl,0,length(bjbkl)-1))>=15) then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,bzxm,xsrs,bzr,bjbkl from view_pjpy_bjbkl";
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);
		String[] opList = new String[]{"xn||bjdm","bgcolor","rownum", "xn", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "bjbkl"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}

	/**
	 * ����༶��������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveBjbkl ----  ����༶������
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveBjbkl(BjbklSaveModel bjbklSaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String bjdm = bjbklSaveModel.getBjdm();
		String xn = bjbklSaveModel.getXn();
		String bzxm = DealString.toGBK(bjbklSaveModel.getBzxm());
		String bzr = DealString.toGBK(bjbklSaveModel.getBzr());
		String xsrs = bjbklSaveModel.getXsrs();
		String bjbkl = bjbklSaveModel.getBjbkl() + "%";
		String[] inList = new String[] { "xn", "bjdm", "bzxm", "bzr", "xsrs",
				"bjbkl" };
		bFlag = StandardOperation.insert("pjpy_bjbklb", inList, new String[] {
				xn, bjdm, bzxm, bzr, xsrs, bjbkl }, request);
		return bFlag;
	}
	
	/**
	 * ����ǰ��������Ƿ��ظ�,���ڷ���TRUE����֮����FALSE
	 * chkbjbkl ---- ���༶������
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkBjbkl(BjbklSaveModel bjbklSaveModel) throws Exception {
		boolean bFlag = false;
		String xn = bjbklSaveModel.getXn();
		String bjdm = bjbklSaveModel.getBjdm();
		String sql = "select xn,bjdm,bjbkl from view_pjpy_bjbkl where xn = ? and bjdm = ?";
		String[] tmpList = dao.getRs(sql, new String[]{xn, bjdm}, "bjbkl");
		if (tmpList != null && tmpList.length > 0) {
			bFlag = true;
		}//end if
		return bFlag;
	}

	/**
	 * ͨ��������ȡ�༶��������Ϣ
	 * getbjbklbypk ---- ͨ��������ȡ�༶��������Ϣ 
	 * @param sPk
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBjbklByPk(String sPk) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xn,bjdm,bzxm,xsrs,bzr,bjbkl from view_pjpy_bjbkl where xn||bjdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{!StringUtils.isNull(sPk) ? sPk.trim() : ""});
		return resMap;
	}
	
	/**
	 * �޸İ༶������Ϣ
	 * updatebjbkl ---- �޸İ༶������ 
	 * @param sPk
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateBjbkl(String sPk, BjbklSaveModel bjbklSaveModel, HttpServletRequest request) throws Exception{
		boolean bFlag = false;
		String bzxm = bjbklSaveModel.getBzxm();
		String bzr = bjbklSaveModel.getBzr();
		String xsrs = bjbklSaveModel.getXsrs();
		String bjbkl = bjbklSaveModel.getBjbkl();
		bFlag = StandardOperation.update("pjpy_bjbklb", new String[] { "bzxm",
				"bzr", "xsrs", "bjbkl" },
				new String[] { bzxm, bzr, xsrs, bjbkl + "%" }, "xn||bjdm", sPk,
				request);
		return bFlag;
	}
	
	/**
	 * �༶����������ɾ��
	 * delbjbkl ---- ɾ���༶������
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delBjbkl(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from pjpy_bjbklb where xn||bjdm = '" + whichxh + "'";
			// ��������ϳ�sql���
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * ��ٳ����ʲ�ѯ���
	 * getzccqlresult ---- ��ȡ��ٳ����ʲ�ѯ��� 
	 * @param zccqlQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZccqlResult(ZccqlQueryModel zccqlQryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||bjdm,(case when (to_number(substr(zccql,0,length(zccql)-1))<90)" +
				" then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xq,xymc,zymc,bjmc,bzxm," +
				"xsrs,bzr,zccql from view_pjpy_bjzccql ";
		StringBuffer whereSql = getWhereSql1(zccqlQryModel);
		String[] opList = new String[] { "xn||bjdm", "bgcolor", "rownum",
				"xn", "xq", "xymc", "zymc", "bjmc", "bzxm", "xsrs", "bzr",
				"zccql" };
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * ����WHERE������ѯ���
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(ZccqlQueryModel zccqlQryModel)
			throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String xydm = zccqlQryModel.getXydm();
		String zydm = zccqlQryModel.getZydm();
		String bjdm = zccqlQryModel.getBjdm();
		String xn = zccqlQryModel.getXn();
		String xq = zccqlQryModel.getXq();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}// end if
		return whereSql;
	}

	/**
	 * �����ٳ����Ƿ��ظ����ظ�����TURE����֮����FALSE
	 * chkzccql ---- �����ٳ��� 
	 * @param zccqlSaveModel ��ٳ��ڱ���MODEL
	 * @return
	 * @throws Exception
	 */
	public boolean chkZccql(ZccqlSaveModel zccqlSaveModel) throws Exception {
		boolean bFlag = false;
		String xn = zccqlSaveModel.getXn();
		String bjdm = zccqlSaveModel.getBjdm();
		String sql = "select xn,bjdm,zccql from view_pjpy_bjzccql where xn = ? and bjdm = ?";
		String[] tmpList = dao.getRs(sql, new String[]{xn, bjdm}, "zccql");
		if (tmpList != null && tmpList.length > 0) {
			bFlag = true;
		}//end if
		return bFlag;
	}

	/**
	 * ������ٳ�����Ϣ,�ɹ�����TRUE����֮����FALSE
	 * saveZccql ---- ������ٳ��� 
	 * @param zccqlSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveZccql(ZccqlSaveModel zccqlSaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = zccqlSaveModel.getXn();
		String bzxm = DealString.toGBK(zccqlSaveModel.getBzxm());
		String bzr = DealString.toGBK(zccqlSaveModel.getBzr());
		String xsrs = zccqlSaveModel.getXsrs();
		String bjdm = zccqlSaveModel.getBjdm();
		String zccql = zccqlSaveModel.getZccql() + "%";
		String[] inList = new String[] { "xn", "bjdm", "bzxm", "bzr", "xsrs",
		"zccql" };
		bFlag = StandardOperation.insert("pjpy_bjzccqlb", inList, new String[] {
				xn, bjdm, bzxm, bzr, xsrs, zccql }, request);//������ٳ�����Ϣ
		return bFlag;
	}

	/**
	 * ͨ��������ȡ��ٳ�����Ϣ
	 * getzccqlbypk ---- ͨ��������ȡ��ٳ��� 
	 * @param zccqlSaveModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZccqlByPk(String sPk) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,xsrs,bzr,bzxm,zccql from view_pjpy_bjzccql where xn||bjdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{!StringUtils.isNull(sPk) ? sPk.trim() : ""});
		return resMap;
	} 

	/**
	 * �޸���ٳ�����Ϣ,�ɹ�����TRUE����֮����FALSE
	 * updatezccql ---- �޸���ٳ��� 
	 * @param sPk
	 * @param zccqlSaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateZccql(String sPk, ZccqlSaveModel zccqlSaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String bzxm = zccqlSaveModel.getBzxm();
		String bzr = zccqlSaveModel.getBzr();
		String xsrs = zccqlSaveModel.getXsrs();
		String zccql = zccqlSaveModel.getZccql();
		bFlag = StandardOperation.update("pjpy_bjzccqlb", new String[] {
				"bzxm", "bzr", "xsrs", "zccql" }, new String[] { bzxm, bzr,
				xsrs, zccql + "%" }, "xn||bjdm", sPk, request);
		return bFlag;
	}
	
	/**
	 * ��ٳ���������ɾ��
	 * delbjbkl ---- ��ٳ�����
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delZccql(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from pjpy_bjzccqlb where xn||bjdm = '" + whichxh + "'";
			// ��������ϳ�sql���
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * �����Ƚ��༶��Ϣ
	 * savexjbjinfo ---- �����Ƚ��༶��Ϣ 
	 * @param xjbjSqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjBjInfo(XjBjSqModel xjbjSqModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = xjbjSqModel.getXn();
		String xq = xjbjSqModel.getXq();
		String bjdm = xjbjSqModel.getBjdm();
		String bzxm = DealString.toGBK(xjbjSqModel.getBzxm());
		String bzr = DealString.toGBK(xjbjSqModel.getBzr());
		String xsrs = xjbjSqModel.getXsrs();
		String zysj = DealString.toGBK(xjbjSqModel.getZysj());
		String[] inList = new String[] { "xn", "xq", "rychdm", "bjdm", "bzxm",
				"bzr", "xsrs", "zysj" };
		String rychdm = getRychdm("�Ƚ��༶");// ��ȡ�����ƺŴ���
		bFlag = StandardOperation.insert("pjpy_xjbjandwmsqb", inList,
				new String[] { xn, xq, rychdm, bjdm, bzxm, bzr, xsrs, zysj },
				request);

		return bFlag;
	}
	
	/**
	 * ��֤�����Ƿ��ظ�
	 * chkDataByXjbj ---- ��֤�Ƚ��༶�����Ƿ��ظ� 
	 * @param xjbjSqModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataByXjbj(XjBjSqModel xjbjSqModel) throws Exception {
		boolean bFlag = false;
		String xn = xjbjSqModel.getXn();
		String xq = xjbjSqModel.getXq();
		String bjdm = xjbjSqModel.getBjdm();
		String rychdm = getRychdm("�Ƚ��༶");//��ȡ�����ƺŴ���;
		String sql = "select xn,xq,bjdm,rychdm from pjpy_xjbjandwmsqb where xn=? and xq=? and bjdm=? and rychdm=?";
		String[] temList = dao.getOneRs(sql, new String[]{xn, xq, bjdm, rychdm}, new String[]{"xn", "xq", "bjdm", "rychdm"});
		if (temList != null && temList.length > 0) {//����
			bFlag = true;
		}//end if
		return bFlag;
	}
	
	/**
	 * �Ƚ��༶��ѯ��ͷ������Ա
	 * getxjbjtitlebyfdy ---- (����Ա) �Ƚ��༶��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjTitleByFdy() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "xn||xq||bjdm||rychdm", "bgcolor",
				"rownum", "xn", "xymc", "zymc", "rychmc", "bzxm", "xsrs",
				"bzr", "fdysh" };
		String[] cnList = new String[] { "����", "bgcolor", "�к�", "ѧ��", "ѧԺ����",
				"רҵ����", "�༶����", "�����ƺ�����", "ѧ������", "����Ա", "����Ա���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * �Ƚ��༶��ѯ��ͷ��ѧԺ
	 * getxjbjtitlebyxy ---- (ѧԺ) �Ƚ��༶��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjTitleByXy() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xysh"};
		String[] cnList = new String[]{"����", "bgcolor", "�к�", "ѧ��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�����ƺ�����", "ѧ������", "����Ա",  Base.YXPZXY_KEY+"���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * �Ƚ��༶��ѯ��ͷ��ѧУ
	 * getxjbjtitlebyxx ---- (ѧУ) �Ƚ��༶��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjTitleByXx() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xxsh"};
		String[] cnList = new String[]{"����", "bgcolor", "�к�", "ѧ��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�����ƺ�����", "ѧ������", "����Ա", "ѧУ���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ��ȡ�Ƚ��༶��ѯ���������Ա
	 * getxjbjresultbyfdy ---- ��ȡ�Ƚ��༶��ѯ���������Ա 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjResultByFdy(XjBjQryModel xjbjQryModel, String isFdy, String userName) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(fdysh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,fdysh from view_pjpy_xjbjandwmsq a where 1=1";
		StringBuffer whereSql = getWhereSql2(xjbjQryModel);
		if ("true".equalsIgnoreCase(isFdy)) {
			whereSql.append(" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "fdysh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡ�Ƚ��༶��ѯ�����ѧԺ
	 * getxjbjresultbyxy ---- ��ȡ�Ƚ��༶��ѯ�����ѧԺ 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjResultByXy(XjBjQryModel xjbjQryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xysh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,xysh from view_pjpy_xjbjandwmsq where 1=1 ";
		StringBuffer whereSql = getWhereSql2(xjbjQryModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xysh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡ�Ƚ��༶��ѯ�����ѧУ
	 * getxjbjresultbyxx ---- ��ȡ�Ƚ��༶��ѯ�����ѧУ 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjResultByXx(XjBjQryModel xjbjQryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xxsh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,xxsh from view_pjpy_xjbjandwmsq where xysh='ͨ��'";
		StringBuffer whereSql = getWhereSql2(xjbjQryModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xxsh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡ��ѯ������ֵ
	 * getwheresql ---- ��ȡ��ѯ���� 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql2(XjBjQryModel xjbjQryModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = xjbjQryModel.getXn();
		String shxm = xjbjQryModel.getShxm();
		String xydm = xjbjQryModel.getXydm();
		String zydm = xjbjQryModel.getZydm();
		String bjdm = xjbjQryModel.getBjdm();
		String rychdm = getRychdm("�Ƚ��༶");//��ȡ�����ƺŴ���
		shxm = StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(shxm)) {
			whereSql.append(" and rychdm = ?");
			values.add(shxm);
		}//end if
		
		return whereSql;
	}

	/**
	 * ����Ա����Ƚ�����
	 * fdyshresult ---- ����Ա��˽��
	 * @param xjjtShModel
	 * @return
	 * @throws Exception
	 */
	public boolean[] fdyshResult(XjjtShModel xjjtShModel, String sRes, HttpServletRequest request) throws Exception {
		String[] cbv = xjjtShModel.getCbv();//����
		Arrays2.trim(cbv);	
		boolean[] bFlag = new boolean[cbv.length]; 
		String sPk = "xn||xq||bjdm||rychdm";
		String sJg = "tg".equalsIgnoreCase(sRes) ? "ͨ��" : ("btg".equalsIgnoreCase(sRes) ? "��ͨ��" : "δ���");
		String shxm = xjjtShModel.getShxm();
		String rychdm = getRychdm("�Ƚ��༶");//��ȡ�����ƺŴ���
		shxm = StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		for (int i = 0; i < cbv.length; i++) {
			StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"fdysh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
		}//end for
		return bFlag;
	}
	
	/**
	 * ѧԺ����Ƚ�����
	 * xyshresult ---- ѧԺ��˽��
	 * @param xjjtShModel
	 * @return
	 * @throws Exception
	 */
	public String xyshResult(XjjtShModel xjjtShModel, String sRes, HttpServletRequest request) throws Exception {
		String[] cbv = xjjtShModel.getCbv();//����
		Arrays2.trim(cbv);	
//		boolean[] bFlag = new boolean[cbv.length]; 
		String sPk = "xn||xq||bjdm||rychdm";
		String sJg = "tg".equalsIgnoreCase(sRes) ? "ͨ��" : ("btg".equalsIgnoreCase(sRes) ? "��ͨ��" : "δ���");
		String shxm = xjjtShModel.getShxm();
		String rychdm = getRychdm("�Ƚ��༶");//��ȡ�����ƺŴ���
		shxm = StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		String sBjs = getBjzs();//�༶���ȱ���
		String res = "";
		int xzs = StringUtils.isNull(sBjs) ? 0 : Integer.parseInt(sBjs);
		if (sJg.equalsIgnoreCase("ͨ��")) {
			for (int i = 0; i < cbv.length; i++) {
				int tg = getXjbjShjg(request);//��ͨ���༶��
				if ((xzs != 0) && (tg >= xzs)) {
					res += (i+1);
					res += ",";
					continue;
				}
				StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xysh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
			}//end for
		} else {
			for (int i = 0; i < cbv.length; i++) {
				
				StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xysh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
			}//end for
		}
		return res;
	}
	
	/**
	 * ѧУ����Ƚ�����
	 * xxhresult ---- ѧУ��˽��
	 * @param xjjtShModel
	 * @return
	 * @throws Exception
	 */
	public String xxshResult(XjjtShModel xjjtShModel, String sRes, HttpServletRequest request) throws Exception {
		String[] cbv = xjjtShModel.getCbv();//����
		Arrays2.trim(cbv);	
		boolean[] bFlag = new boolean[cbv.length]; 
		String sPk = "xn||xq||bjdm||rychdm";
		String sJg = "tg".equalsIgnoreCase(sRes) ? "ͨ��" : ("btg".equalsIgnoreCase(sRes) ? "��ͨ��" : "δ���");
		String shxm = xjjtShModel.getShxm();
		String rychdm = getRychdm("�Ƚ��༶");//��ȡ�����ƺŴ���
		shxm = StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		String sBjs = getBjzs();//�༶���ȱ���
		String res = "";
		int xzs = StringUtils.isNull(sBjs) ? 0 : Integer.parseInt(sBjs);
		if (sJg.equalsIgnoreCase("ͨ��")) {
			for (int i = 0; i < cbv.length; i++) {
				int tg = getXjbjShjg(request);//��ͨ���༶��
				if ((xzs != 0) && (tg >= xzs)) {
					res += (i+1);
					res += ",";
					continue;
				}
				bFlag[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xxsh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
			}//end for
		} else {
			for (int i = 0; i < cbv.length; i++) {
				
				bFlag[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xxsh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
			}//end for
		}
		return res;
	}

	/**
	 * ͨ��������ȡ�Ƚ�������Ϣ������Ա��
	 * getxsjtjg ---- ��ȡ�Ƚ�������
	 * @param sPkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsJtJgByFdy(String sPkValue) throws Exception {
		HashMap<String, String> resList = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,rychdm,xymc,zymc,bjmc,bjbkl,zccql,zysj,xsrs,bzxm,bzr,rychmc,fdysh sh,fdyyj yj from view_pjpy_xjbjandwmsq where xn||xq||bjdm||rychdm = ?";
		resList = dao.getMapNotOut(sql, new String[]{sPkValue});
		return resList;
	}
	
	/**
	 * ͨ��������ȡ�Ƚ�������Ϣ��ѧԺ��
	 * getxsjtjg ---- ��ȡ�Ƚ�������
	 * @param sPkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsJtJgByXy(String sPkValue) throws Exception {
		HashMap<String, String> resList = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,rychdm,xymc,zymc,bjmc,xsrs,zccql,bjbkl,zysj,bzxm,bzr,rychmc,xysh sh,xyyj yj from view_pjpy_xjbjandwmsq where xn||xq||bjdm||rychdm = ?";
		resList = dao.getMapNotOut(sql, new String[]{sPkValue});
		return resList;
	}
	
	/**
	 * ͨ��������ȡ�Ƚ�������Ϣ��ѧУ��
	 * getxsjtjg ---- ��ȡ�Ƚ�������
	 * @param sPkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsJtJgByXx(String sPkValue) throws Exception {
		HashMap<String, String> resList = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,rychdm,xymc,zymc,bjmc,xsrs,bzxm,bzr,zccql,bjbkl,zysj,rychmc,xxsh sh,xxyj yj from view_pjpy_xjbjandwmsq where xn||xq||bjdm||rychdm = ?";
		resList = dao.getMapNotOut(sql, new String[]{sPkValue});
		return resList;
	}
	
	/**
	 * ��ȡ�б�ֵ
	 * @param iType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int iType) throws Exception {
		return dao.getChkList(iType);
	}
	
	/**
	 * ����Ա��������Ƚ�����
	 * fdysavexjjtone ---- ����Ա��������Ƚ����� 
	 * @param xjjtshModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean fdySaveXjjtOne(XjjtShModel xjjtshModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = xjjtshModel.getPkValue();
		String sh = DealString.toGBK(xjjtshModel.getShxm());
		String shyj = DealString.toGBK(xjjtshModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
				"fdysh", "fdyyj" }, new String[] { sh, shyj },
				"xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * ѧԺ��������Ƚ�����
	 * fdysavexjjtone ---- ѧԺ��������Ƚ����� 
	 * @param xjjtshModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xySaveXjjtOne(XjjtShModel xjjtshModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = xjjtshModel.getPkValue();
		String sh = DealString.toGBK(xjjtshModel.getShxm());
		String shyj = DealString.toGBK(xjjtshModel.getShyj());
		
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
				"xysh", "xyyj" }, new String[] { sh, shyj },
				"xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * ѧУ��������Ƚ�����
	 * fdysavexjjtone ---- ѧУ��������Ƚ����� 
	 * @param xjjtshModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xxSaveXjjtOne(XjjtShModel xjjtshModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = xjjtshModel.getPkValue();
		String sh = DealString.toGBK(xjjtshModel.getShxm());
		String shyj = DealString.toGBK(xjjtshModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
				"xxsh", "xxyj" }, new String[] { sh, shyj },
				"xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * �õ�ȫУ�༶������
	 * getbjzs ---- �õ��༶���� 
	 * @return
	 * @throws Exception
	 */
	public String getBjzs() throws Exception {
		String sql = "select round((select count(bjmc) from view_njxyzybj)*pxbl/100) bjzs from pjpy_jtrydmb where rychmc='�Ƚ��༶'";
		String[] bjList = dao.getRs(sql, new String[]{}, "bjzs");
		String tem = "";
		if (bjList != null && bjList.length >0) {
			tem = bjList[0];
		}
		return tem;
	}

	/**
	 * ��ȡ�༶ѧ��Υ������
	 * getbjcfrs ---- ��ȡ�༶ѧ��Υ������ 
	 * @param bjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String getBjcfRs(String bjdm, String xn) throws Exception {
		String sBjcfRs = "";
		String sql = "select xh from view_xsjbxx where bjdm = ?";
		String[] bjList = dao.getRs(sql, new String[]{bjdm}, "xh");//�༶����ѧ��ѧ��
		int j = 0;//������
		if (bjList != null && bjList.length > 0){
			for (int i = 0; i < bjList.length; i++) {
				sql = "select xh from view_wjcf where (ssjg='���Ĵ���' or ssjg='ά��ԭ����' or ssjg is null) and xn=? and xh=?";
				String[] tempList = dao.getOneRs(sql, new String[]{xn, bjList[i]}, new String[]{"xh"});
				if (tempList != null && tempList.length > 0){//�ܴ���ѧ��ѧ��
					j++;
				}//end if
			}//end for
			sBjcfRs = j + "";//�༶ѧ��Υ������
		}//end if
		return sBjcfRs;
	}
	
	/**
	 * �Ƚ��༶��ѯ�����ͷ
	 * xjbjjgcxbt ---- �Ƚ��༶��ѯ�����ͷ 
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xjbjJgCxBt() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr","xysh","xxsh"};
		String[] cnList = new String[]{"����", "bgcolor", "�к�", "ѧ��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�����ƺ�����","ѧ������", "����Ա", Base.YXPZXY_KEY+"���", "ѧУ���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * �Ƚ��༶��ѯ���
	 * xjbjcxjg ---- �Ƚ��༶��ѯ��� 
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xjbjCxJg(XjBjQryModel xjbjqryModel, String isFdy, String userName) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xxsh='δ���' and xysh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,replace(xysh,' ','') xysh,replace(xxsh,' ','') xxsh from view_pjpy_xjbjandwmsq a where 1=1 ";
		StringBuffer whereSql = getWhereSql2(xjbjqryModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr","xysh", "xxsh"};
		if ("true".equalsIgnoreCase(isFdy)) {//����Աֻ�����Լ������༶
			whereSql.append(" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}
		resList = dao.rsToVator(sql + whereSql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ͨ��������ȡ�Ƚ��༶��Ϣ
	 * xjbjxxbypk ---- ͨ��������ȡ�Ƚ��༶��Ϣ 
	 * @param sPk
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xjbjXxByPk(String sPk) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,rychdm,xsrs,bzr,bzxm,bjmc,zysj,rychmc from view_pjpy_xjbjandwmsq where xn||xq||bjdm||rychdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{sPk});
		return resMap;
	}
	
	/**
	 * �����Ƚ��༶���
	 * bcxjbjjg ---- �����Ƚ��༶��� 
	 * @param xjbjsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean bcxjbjJg (XjBjSqModel xjbjsqModel, String sPk, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xsrs = xjbjsqModel.getXsrs();
		String bzr = xjbjsqModel.getBzr();
		String bzxm = xjbjsqModel.getBzxm();
		String zysj = xjbjsqModel.getZysj();
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
				"xsrs", "bzr", "bzxm", "zysj" }, new String[] { xsrs, bzr,
				bzxm, zysj }, "xn||xq||bjdm||rychdm", sPk, request);//�޸�
		return bFlag;
	}
	
	/**
	 * �Ƚ��༶����ɾ��
	 * delxjbjxx ---- �Ƚ��༶��Ϣ����ɾ��
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delXjbjXx(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from pjpy_xjbjandwmsqb where xn||xq||bjdm||rychdm = '" + whichxh + "'";
			// ��������ϳ�sql���
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * �Ƚ�������������
	 * getbjzsbygr ---- �Ƚ������������� 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBjzsByGr() throws Exception {
		List<String[]> bjList = new ArrayList<String[]>();
		String[] jxjSqxnnd = getJxjsqxn();
		String xn = "";
		String nd = "";
		if (jxjSqxnnd != null && jxjSqxnnd.length > 0) {
			xn = jxjSqxnnd[0];
			nd = jxjSqxnnd[1];
		}
		String sql = "select rownum,rychdm,rychmc,pxbl,(case when (rychmc = '\"ʮ��\"��ѧ��') then '10' when (rychmc = '����ѧ��') then 'ռ�༶����' || pxbl when (rychmc = '����ѧ���ɲ�') then 'ռ�༶�ɲ�' || pxbl when (rychmc = 'У��Ʒѧ���ű�ҵ��') then (select sum(bysrs)*substr(pxbl,0,length(pxbl)-1) from xyjxjrs where xn='"+xn+"' and nd= '"+nd+"')||'' when (rychmc = 'ʡ��Ʒѧ���ű�ҵ��') then (select sum(bysrs)*substr(pxbl,0,length(pxbl)-1) from xyjxjrs where xn='"+xn+"' and nd= '"+nd+"')||'' when (rychmc = '��Ṥ��(ʵ��)��������' or rychmc = '������������' or rychmc = 'ѧϰ(����)��������' or rychmc = 'У԰����������������') then (select round(count(*) * substr(pxbl, 0, length(pxbl) - 1) / 100) from view_xsjbxx) || '' end) bjzs from rychdmb";
		bjList = dao.rsToVator(sql, new String[]{}, new String[]{"rownum", "rychdm", "rychmc", "pxbl", "bjzs"});
		return bjList;
	}
	
	/**
	 * ��ȡ�����ƺ��б�
	 * getrychlist ---- ��ȡ�����ƺ��б� 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychList() throws Exception {
		String sql = "select rychdm,rychmc from rychdmb";
		List<HashMap<String, String>> rychList = dao.getList(sql, new String[] {}, new String[] {
				"rychdm", "rychmc" });
		return rychList;
	}
	
	/**
	 * ����Ա��ѯ�Ƚ����˽��
	 * fdyqryxjgrresult ---- ����Ա��ѯ�Ƚ����˽�� 
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> fdyqryXjgrResult(XjgrQryModel xjgrqryModel, String isFdy, String userName) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select (case nvl(fdysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor, xn||nd||xh||rychdm,rownum,xn,nd,xh,xm,bjmc,rychmc,fdysh from view_xsrychb a where 1=1 ";
		String[] opList = new String[] { "bgcolor", "xn||nd||xh||rychdm", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "rychmc", "fdysh" };
		StringBuffer whereSql = getWhereSql3(xjgrqryModel);
		if ("true".equalsIgnoreCase(isFdy)) {
			whereSql.append(" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 *��ȡ��ѯ������ֵ 
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql3(XjgrQryModel xjgrqryModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = xjgrqryModel.getXn();
		String nd = xjgrqryModel.getNd();
		String nj = xjgrqryModel.getNj();
		String xydm = xjgrqryModel.getXydm();
		String xmdm = xjgrqryModel.getXmdm();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}// end if
		if (!StringUtils.isNull(xmdm)) {
			whereSql.append(" and rychdm = ?");
			values.add(xmdm);
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		return whereSql;
	}

	/**
	 * ѧԺ��ѯ�Ƚ����˽��
	 * Xyqryxjgrresult ---- ѧԺ��ѯ�Ƚ����˽�� 
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyqryXjgrResult(XjgrQryModel xjgrqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select (case nvl(xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor, xn||nd||xh||rychdm,rownum,xn,nd,xh,xm,bjmc,rychmc,xysh from view_xsrychb where 1=1 ";
		String[] opList = new String[] { "bgcolor", "xn||nd||xh||rychdm", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "rychmc", "xysh" };
		StringBuffer whereSql = getWhereSql3(xjgrqryModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ѧУ��ѯ�Ƚ����˽��
	 * Xxqryxjgrresult ---- ѧУ��ѯ�Ƚ����˽�� 
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxqryXjgrResult(XjgrQryModel xjgrqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select (case nvl(xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor, xn||nd||xh||rychdm,rownum,xn,nd,xh,xm,bjmc,rychmc,xxsh from view_xsrychb where 1=1 and xysh='ͨ��' ";
		String[] opList = new String[] { "bgcolor", "xn||nd||xh||rychdm", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "rychmc", "xxsh" };
		StringBuffer whereSql = getWhereSql3(xjgrqryModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡ��ѧ������ѧ�꣬���
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqxn() throws Exception {
		String[] colList = null;
		String sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] {"jxjsqxn", "jxjsqnd" });
		return colList;
	}
	
	/**
	 * ͨ��������ȡ�Ƚ�������Ϣ(����Ա)
	 * fdyqryXjgrByPk ---- ͨ��������ȡ�Ƚ�������Ϣ(����Ա) 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> fdyqryXjgrByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select "
			+ "xn||nd||xh||rychdm"
			+ ",nd,xn,xh,xm,nj,xymc,zymc,bjmc,xb,rychmc,rychdm,fdysh yesNo,dcj,zcj,tcj from view_xsrychb where "
			+ "xn||nd||xh||rychdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ͨ��������ȡ�Ƚ�������Ϣ(ѧԺ)
	 * XyqryXjgrByPk ---- ͨ��������ȡ�Ƚ�������Ϣ(ѧԺ) 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xyqryXjgrByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select "
			+ "xn||nd||xh||rychdm"
			+ ",nd,xn,xh,xm,nj,xymc,zymc,bjmc,xb,rychmc,rychdm,xysh yesNo,dcj,zcj,tcj from view_xsrychb where "
			+ "xn||nd||xh||rychdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ͨ��������ȡ�Ƚ�������Ϣ(ѧУ)
	 * xxqryXjgrByPk ---- ͨ��������ȡ�Ƚ�������Ϣ(ѧУ) 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xxqryXjgrByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select "
			+ "xn||nd||xh||rychdm"
			+ ",nd,xn,xh,xm,nj,xymc,zymc,bjmc,xb,rychmc,rychdm,xxsh yesNo,dcj,zcj,tcj from view_xsrychb where "
			+ "xn||nd||xh||rychdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ����Ա����Ƚ�����
	 * @param xjgrqryMode
	 * @return
	 * @throws Exception
	 */
	public boolean fdyshXjgr(String yesNo, HttpServletRequest request, String pkValue) throws Exception {
		boolean bFlag = false;
		StandardOperation.update("xsrychb", new String[]{"fdysh"}, new String[]{DealString.toGBK(yesNo)}, "xn||nd||xh||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * ѧԺ����Ƚ�����
	 * @param xjgrqryMode
	 * @return
	 * @throws Exception
	 */
	public String xyshXjgr(String yesNo, HttpServletRequest request, String pkValue, String rychdm, String oldxh) throws Exception {
		String[] jxjsq = dao.getOneRs("select jxjsqxn,jxjsqnd from xtszb ", new String[]{}, new String[]{"jxjsqxn","jxjsqnd"});
		String sql = "select tjzdm,zdbj,tj from jxjhdtj where zdlx='rych' and jxjdm=?";
		List<String[]> tjList = dao.rsToVator(sql, new String[]{rychdm}, new String[]{"tjzdm", "zdbj", "tj"});
		String bjrs = dao
				.getOneRs(
						"select count(*) num from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh=?)",
						new String[] { oldxh }, "num");//�༶����
		bjrs = StringUtils.isNull(bjrs) ? "0" : bjrs;
		if (StringUtils.isEqual(DealString.toGBK(yesNo), "ͨ��")) {//���ͨ��ʱ��֤
			for (int i=0;i<tjList.size();i++) {
				String[] tmpList = tjList.get(i);
				if (tmpList != null && tmpList.length == 3) {
					if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "bl")) {
						float iRs = Float.parseFloat(bjrs)
								* (StringUtils.isNull(tmpList[2]) ? 0 : Float
										.parseFloat(tmpList[2])) / 100;//��������
						String sTgrs = dao.getOneRs("select count(*) num from view_xsrychb where xysh='ͨ��' and rychdm=? and xn=? and nd=?  and bjdm=(select bjdm from view_xsjbxx where xh=?)", new String[]{rychdm,jxjsq != null ? jxjsq[0] : "", jxjsq != null ? jxjsq[1] : "",oldxh}, "num");
						int iTgrs = StringUtils.isNull(sTgrs) ? 0 : Integer.parseInt(sTgrs);//�༶��ͨ������
						if ((iTgrs != 0) && (iRs != 0) && (iTgrs >= Math.round(iRs))) {
							return String
									.format(
											"��ʾ���༶ͨ�������ѳ���������������������\n��ͨ�� %1$d �ˣ������������� %2$d ��",
											iTgrs, Math.round(iRs));
						}
					} else if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "sjdxspfmc")) {
						String mc = dao.getOneRs("select count(*) num from (select (dense_rank() over (partition by xh order by zf desc nulls last)) mc from sjdxspfxzb where xh=?) where to_number(mc)<='"+tmpList[3]+"'", new String[]{oldxh}, "num");
						if (!StringUtils.isNull(mc) && StringUtils.isEqual(mc, "0")) {
							return "��ʾ������ʮ�Ѵ�ѧ�������ܷ�δ����������";
						}
					}
				}
			}
		}
		boolean bFlag = StandardOperation.update("xsrychb", new String[] { "xysh" },
				new String[] { DealString.toGBK(yesNo) }, "xn||nd||xh||rychdm",
				pkValue, request);
		if (bFlag) {
			return "";
		} else {
			return "no";
		}
	}
	
	/**
	 * ѧУ����Ƚ�����
	 * @param xjgrqryMode
	 * @return
	 * @throws Exception
	 */
	public String xxshXjgr(String yesNo, HttpServletRequest request, String pkValue, String rychdm, String oldxh) throws Exception {
		String[] jxjsq = dao.getOneRs("select jxjsqxn,jxjsqnd from xtszb ", new String[]{}, new String[]{"jxjsqxn","jxjsqnd"});
		String sql = "select tjzdm,zdbj,tj from jxjhdtj where zdlx='rych' and jxjdm=?";
		List<String[]> tjList = dao.rsToVator(sql, new String[]{rychdm}, new String[]{"tjzdm", "zdbj", "tj"});
		String bjrs = dao
				.getOneRs(
						"select count(*) num from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh=?)",
						new String[] { oldxh }, "num");//�༶����
		bjrs = StringUtils.isNull(bjrs) ? "0" : bjrs;
		if (StringUtils.isEqual(DealString.toGBK(yesNo), "ͨ��")) {//���ͨ��ʱ��֤
			for (int i=0;i<tjList.size();i++) {
				String[] tmpList = tjList.get(i);
				if (tmpList != null && tmpList.length == 3) {
					if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "bl")) {
						float iRs = Float.parseFloat(bjrs)
								* (StringUtils.isNull(tmpList[2]) ? 0 : Float
										.parseFloat(tmpList[2])) / 100;//��������
						String sTgrs = dao.getOneRs("select count(*) num from view_xsrychb where xysh='ͨ��' and xxsh='ͨ��' and rychdm=? and xn=? and nd=?  and bjdm=(select bjdm from view_xsjbxx where xh=?)", new String[]{rychdm,jxjsq != null ? jxjsq[0] : "", jxjsq != null ? jxjsq[1] : "",oldxh}, "num");
						int iTgrs = StringUtils.isNull(sTgrs) ? 0 : Integer.parseInt(sTgrs);//�༶��ͨ������
						if ((iTgrs != 0) && (iRs != 0) && (iTgrs >= Math.round(iRs))) {
							return String
									.format(
											"��ʾ���༶ͨ�������ѳ���������������������\n��ͨ�� %1$d �ˣ������������� %2$d ��",
											iTgrs, Math.round(iRs));
						}
					} else if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "sjdxspfmc")) {
						String mc = dao.getOneRs("select count(*) num from (select (dense_rank() over (partition by xh order by zf desc nulls last)) mc from sjdxspfxzb where xh=?) where to_number(mc)<='"+tmpList[3]+"'", new String[]{oldxh}, "num");
						if (!StringUtils.isNull(mc) && StringUtils.isEqual(mc, "0")) {
							return "��ʾ������ʮ�Ѵ�ѧ�������ܷ�δ����������";
						}
					}
				}
			}
		}
		boolean bFlag = false;
		bFlag = StandardOperation.update("xsrychb", new String[] { "xxsh" },
				new String[] { DealString.toGBK(yesNo) }, "xn||nd||xh||rychdm",
				pkValue, request);
		if (bFlag) {
			return "";
		} else {
			return "no";
		}
	}
	
	/**
	 * ͨ�������ƺ����ƻ�ȡ�����ƺŴ���
	 * getrychdm ---- ��ȡ�����ƺŴ��� 
	 * @param rychmc
	 * @return
	 * @throws Exception
	 */
	public String getRychdm (String rychmc) throws Exception {
		String rychdm ="";
		String sql = "select rychdm from pjpy_jtrydmb where rychmc=? and rownum=1";
		String[] rychList = dao.getOneRs(sql, new String[]{rychmc}, new String[]{"rychdm"});
		if (rychList != null && rychList.length > 0) {
			rychdm = rychList[0];
		}
		return rychdm;
	}
	
	/**
	 * ��ȡѧϰ������ѯ��ͷ
	 * getxxjstitle ---- ��ȡѧϰ������ѯ��ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXxjsTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = new String[]{"xh||xn||nd||xxjsdm","rownum","xh","xm","xn","nd","xymc","zymc","bjmc","xxjsmc"};
		String[] cn = new String[]{"����","�к�","ѧ��","����","ѧ��","���", Base.YXPZXY_KEY,"רҵ","�༶","����������"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);//����������ҳѡ��������Ϣ
		}//end for
		return topList;
	}
	
	/**
	 * ��ȡѧϰ������ѯ���
	 * getxxjsqryresult ---- ��ȡѧϰ������ѯ��� 
	 * @param xxjsqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXxjsResult(XxjsQryModel xxjsqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||xxjsdm,rownum,xh,xm,xn,nd,xymc,zymc,bjmc,xxjsmc from view_xsxxjs where 1=1 ";
		StringBuffer whereSql = getWhereSql4(xxjsqryModel);
		String[] opList = new String[]{"xh||xn||nd||xxjsdm","rownum","xh","xm","xn","nd","xymc","zymc","bjmc","xxjsmc"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡ��ѯ����ֵ
	 * @param xxjsqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql4(XxjsQryModel xxjsqryModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = xxjsqryModel.getXn();
		String nd = xxjsqryModel.getNd();
		String xydm = xxjsqryModel.getXydm();
		String xh = xxjsqryModel.getXh();
		String zydm = xxjsqryModel.getZydm();
		String bjdm = xxjsqryModel.getBjdm();
		String xm = DealString.toGBK(xxjsqryModel.getXm());
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}// end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm = ?");
			values.add(xm);
		}// end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}// end if
		return whereSql;
	}
	
	/**
	 * ѧϰ�����б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXxjSList() throws Exception {
		List<HashMap<String, String>> xxjsList = new ArrayList<HashMap<String,String>>();
		String sql = "select xxjsdm,xxjsmc from xxjsdmb";
		xxjsList = dao.getArrayList(sql, new String[]{}, new String[]{"xxjsdm","xxjsmc"});
		return xxjsList;
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡѧ��������ѧԺ��רҵ���༶
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsInfo(String xh) throws Exception {
		String sql = "select xm,xymc,zymc,bjmc from view_xsjbxx where xh=?";
		HashMap<String, String> resMap = dao.getMapNotOut(sql, new String[]{xh});
		return resMap;
	}
	
	/**
	 * ����ѧϰ������Ϣ
	 * @param xsjxsaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXxjsInfo(XsjxSaveModel xsjxsaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = xsjxsaveModel.getXh();
		String xn = xsjxsaveModel.getXn();
		String nd = xsjxsaveModel.getNd();
		String hjsj = xsjxsaveModel.getHjsj();
		String xxjsdm = xsjxsaveModel.getXxjsdm();
		String bz = DealString.toGBK(xsjxsaveModel.getBz());
		boolean bDel = StandardOperation.delete("xsxxjsb", new String[] { "xh",
				"xn", "nd", "xxjsdm" }, new String[] { xh, xn, nd, xxjsdm },
				request);
		if (bDel) {
			bFlag = StandardOperation.insert("xsxxjsb", new String[] { "xh",
					"xn", "nd", "xxjsdm", "hjsj", "bz" }, new String[] { xh,
					xn, nd, xxjsdm, hjsj, bz }, request);
		}
		return bFlag;
	}
	
	/**
	 * ͨ��������ȡѧϰ������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxjsInfoByPk(String pkValue)throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xn,nd,xm,xymc,zymc,bjmc,xxjsdm,hjsj,bz from view_xsxxjs where xh||xn||nd||xxjsdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ѧϰ��������ɾ��
	 * xxjsdel ---- ѧϰ��������ɾ�� 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String xxjsDel(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from xsxxjsb where xh||xn||nd||xxjsdm = '" + whichxh + "'";
			// ��������ϳ�sql���
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * ��ȡѧ��ѧ����ƽ���ּ��༶����
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	/*public HashMap<String, String> getXsPjfMc(String xh, String xn) throws Exception {
		String sql = "select xh,xn,pjf,mc from view_xspjfandmc where xh=? and xn=? and rownum=1";
		HashMap<String, String> xspjfmcList = dao.getMapNotOut(sql, new String[]{xh,xn});
		//System.out.println(xspjfmcList[0]+xspjfmcList[1]);
		return xspjfmcList;
	}
	
	public HashMap<String, String> getXjbjByxx(String xh, String xn) throws Exception {
		String sql = "select a.bjdm from view_xsjbxx a,(select bjdm from view_pjpy_xjbjandwmsq where fdysh = 'ͨ��' and xysh = 'ͨ��' and xxsh = 'ͨ��' and xn=?) b  where xh=? and a.bjdm=b.bjdm";
		HashMap<String, String> resMap = dao.getMapNotOut(sql, new String[]{xn,xh});
		return resMap;
	}*/
	
	/**
	 * ѧ���������Ϣ��
	 */
	public HashMap<String, String> getXsPsxxb(String xh, String xn) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = " select (select bjgbmc from view_bjgbxx where xh='"
			+ xh
			+ "' and rownum=1) drzw,(select xxjsmc from view_xsxxjs where xh='"+xh+"' and xn='"+xn+"' and rownum=1) xxjsmc,(select pjf from view_xspjfandmc where xh='"+xh+"' and xn='"+xn+"' and rownum=1) pjf,(select mc from view_xspjfandmc where xh='"+xh+"' and xn='"+xn+"' and rownum=1) mc,(select b.rychmc from view_xsjbxx a,(select bjdm, xn,rychmc from view_pjpy_xjbjandwmsq where xysh = 'ͨ��' and xxsh = 'ͨ��') b where a.bjdm = b.bjdm and xh='"+xh+"' and rownum=1) bjrychmc, (select (case when (instr((select xsdjks_xnxq from zfxfzb.xxmc),'CET4',1,1)>0) then (case when (to_number(a.dcj)>=420) then 'tg' else 'btg' end) else (case when a.dcj>=60 then 'tg' else 'btg' end) end) tg from (select xh,xn,xq,max(cj) dcj from zfxfzb.xsdjksb where djksmc like '%��ѧӢ���ļ�%' group by xh,xn,xq) a where a.xh='"+xh+"' and a.xn='"+xn+"' and rownum=1) cet4, (select (case when (to_number(dcj)>60) then 'tg' else 'btg' end) tg from (select xh,xn,max(cj) dcj,djksmc from zfxfzb.xsdjksb a where djksmc like '%����%' group by xh,xn,djksmc) where xh='"+xh+"' and xn='"+xn+"') jsjej,(select lbmc from view_wmqspbxx where ssbh=(select ssbh from view_xszsxx where xh='"+xh+"' ) and xn='"+xn+"') wmss from dual";
		//select (select drzw from xsgbxxb where xh='"+xh+"' and xn='"+xn+"' and rownum=1) drzw,(select xxjsmc from view_xsxxjs where xh='"+xh+"' and xn='"+xn+"' and rownum=1) xxjsmc,(select pjf from view_xspjfandmc where xh='"+xh+"' and xn='"+xn+"' and rownum=1) pjf,(select mc from view_xspjfandmc where xh='"+xh+"' and xn='"+xn+"' and rownum=1) mc,(select b.rychmc from view_xsjbxx a,(select bjdm, xn,rychmc from view_pjpy_xjbjandwmsq where fdysh = 'ͨ��' and xysh = 'ͨ��' and xxsh = 'ͨ��') b where a.bjdm = b.bjdm and xh='"+xh+"' and rownum=1) rychmc, (select (case when (instr((select xsdjks_xnxq from hzdz.xxmc),'CET4',1,1)>0) then (case when (to_number(a.dcj)>=420) then 'tg' else 'btg' end) else (case when a.dcj>=60 then 'tg' else 'btg' end) end) tg from (select xh,xn,xq,max(cj) dcj from hzdz.xsdjksb where djksmc='CET4' group by xh,xn,xq) a where a.xh='"+xh+"' and a.xn='"+xn+"' and rownum=1) cet4, (select (case when (to_number(dcj)>60) then 'tg' else 'btg' end) tg from (select xh,xn,max(cj) dcj,djksmc from hzdz.xsdjksb a where djksmc like '����%' group by xh,xn,djksmc) where xh='"+xh+"' and xn='"+xn+"') jsjej from dual";
		resMap = dao.getMapNotOut(sql, new String[]{});
		return resMap;
	}
	
	/**
	 * ѧ��֤��ӡλ��
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String[] getTopLeftStr(String page) throws Exception {
		String[] topleftList = new String[]{};
		String sql = "select top,left from printpage where page=?";
		topleftList = dao.getOneRs(sql, new String[]{page}, new String[]{"top", "left"});
		return topleftList;
	}

	/**
	 * �ɼ�ͬ��
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean cjbTb(String xn, String xq) throws Exception {
		return dao.runProcuder("{call PJPY_GDB_CJTB(?,?)}", new String[]{xn,xq});
	}
	
	/**
	 * �����ȼ�����ͬ���ɼ�
	 * @param xn
	 * @param xq
	 * @param djksmc
	 * @throws Exception
	 */
	public boolean djksCjTb(String xn, String xq) throws Exception {
		boolean flg = false;
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {
			flg=dao.runProcuder("{call pjpy_jhzy_djkscjtb(?,?)}", new String[]{xn,xq});
		}else{
			flg=dao.runProcuder("{call PJPY_GDB_DJKSCJTB(?,?)}", new String[]{xn,xq});
		}
		return flg;
	}
	
	/**
	 * ��ѧ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		return dao.getList("select jxjdm xmdm,jxjmc xmmc from jxjdmb", new String[]{}, new String[]{"xmdm", "xmmc"});
	}
	
	/**
	 * ����TYPE��������õ������б�0��ѧ��,1�����ƺ�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjTjList(int type) throws Exception {
		List<HashMap<String, String>> jxjtjList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
		if (type == 0) {
			enList = new String[]{ "pjcj"};
			cnList = new String[]{ "ƽ���ɼ�"};
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				enList = new String[]{"xybxf","xybxkf","gybxf","gybxkf","dyfjf", "dyzf", "tycj", "pjcj", "zhszcpcjpm"};
				cnList = new String[]{"У԰�����ܷ�","У԰�����ܿ۷�","��Ԣ�����ܷ�","��Ԣ�����ܿ۷�", "�������ӷ�", "�����ܷ�", "�����ɼ�", "ƽ���ɼ�", "�ۺ���������"};
			}
		} else if (3==type) {
			enList = new String[]{ "dkcj","pjcj"};
			cnList = new String[]{ "���Ƴɼ�","ƽ���ɼ�"};
		} else if (4==type) {
			enList = new String[]{ "jd","dcj","tcj","zfpm"};
			cnList = new String[]{ "ƽ��ѧ�ּ���","�����ɼ�","�����ɼ�","�ۺϷ�"};
		} else if (5==type) {
			enList = new String[]{"jd", "tcj", "drxf"};
			cnList = new String[]{"ƽ��ѧ�ּ���", "�����ɼ�", "�ڶ�����ѧ��"};
		} else {
			enList = new String[]{"pjcj", "cjmc", "sjdxspfmc"};
			cnList = new String[]{"ƽ���ɼ�", "�ɼ�����", "ʮ�Ѵ�ѧ����������"};
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				enList = new String[]{ "dyzf", "tycj", "pjcj", "cjpm", "dkcj", "zhszcpcjpm", "pxbl"};
				cnList = new String[]{ "�����ܷ�", "�����ɼ�", "ƽ���ɼ�", "�ɼ�����", "���Ƴɼ�", "�ۺ����ʲ����ɼ�����", "��ѡ����"};
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
				enList = new String[]{"pjcj", "cjmc", "sjdxspfmc","xnxqcj","bjgkm","wjcf"};
				cnList = new String[]{"ƽ���ɼ�", "�ɼ�����", "ʮ�Ѵ�ѧ����������","ѧ���ÿѧ��ƽ���ɼ�","�������Ŀ","Υ�ʹ���"};
			}
		}
		for (int i = 0;i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("tjdm", enList[i]);
			tmpMap.put("tjmc", cnList[i]);
			jxjtjList.add(tmpMap);
		}
		return jxjtjList;
	}
	
	/**
	 * ����TYPE��������õ������б�0��ѧ��,1�����ƺ�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdczList(int type) throws Exception {
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
		if (type == 0) {
//			enList = new String[]{"max", "min", "sum", "pm"};
//			cnList = new String[]{"���ֵ", "��Сֵ", "�ܺ�", "����"};
			enList = new String[]{"max",  "pm"};
			cnList = new String[]{"���ֵ", "����"};			
		} else {
			enList = new String[]{"max", "min", "avg", "pm"};
			cnList = new String[]{"���ֵ", "��Сֵ", "ƽ��ֵ", "����"};						
		}
		for (int i = 0;i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("zdczdm", enList[i]);
			tmpMap.put("zdczmc", cnList[i]);
			zdczList.add(tmpMap);
		}
		return zdczList;
	}
	
	/**
	 * ����TYPE��������õ������б�0��ѧ��,1�����ƺ�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdbjList(int type) throws Exception {
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
//		enList = new String[]{">=", ">", "=", "<", "<="};
//		cnList = new String[]{"���ڻ����", "����", "����", "С��", "С�ڻ����"};
		enList = new String[]{">=","<"};
		cnList = new String[]{"���ڻ����","С��"};		
		if (1==type) {
			enList = new String[]{">=", };
			cnList = new String[]{"���ڻ����"};
		} else if (2==type) {
			enList = new String[]{">=", ">="};
			cnList = new String[]{"���ڻ����","�༶����ǰ"};
		} else if (3==type) {
			enList = new String[]{">="};
			cnList = new String[]{"�༶����ǰ"};
		} else if (4==type) {
			enList = new String[]{">="};
			cnList = new String[]{"���ڻ����"};
		}else if (5==type) {
			enList = new String[]{">=","<","="};
			cnList = new String[]{"���ڻ����","С��","����"};		
		}
		for (int i = 0;i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("zjbjdm", enList[i]);
			tmpMap.put("zjbjmc", cnList[i]);
			zdbjList.add(tmpMap);
		}
		return zdbjList;
	}
	
	/**
	 * �����ƺ��б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRych() throws Exception {
		return dao.getList("select rychdm xmdm,rychmc xmmc from rychdmb", new String[]{}, new String[]{"xmdm", "xmmc"});
	}

	public List<HashMap<String, String>> getZdczbjList(String zdcz) throws Exception {
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
		enList = new String[]{">=", ">", "=", "<", "<="};
		cnList = new String[]{"���ڻ����", "����", "����", "С��", "С�ڻ����"};
		for (int i = 0;i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("zjbjdm", enList[i]);
			tmpMap.put("zjbjmc", cnList[i]);
			zdbjList.add(tmpMap);
		}
		return zdbjList;
	}
	
	/**
	 * �������ñ���
	 * @param tjzdModel
	 * @return
	 * @throws Exception
	 */
	public boolean tjSave(TjszModel tjzdModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = StandardOperation
				.delete("jxjhdtj", "jxjdm||tjzdm", 
						tjzdModel.getXmdm() + tjzdModel.getTjdm(), request);
		if (bDel) {
			bFlag = StandardOperation.insert("jxjhdtj", new String[] { "xn",
					"jxjdm", "tjzdm", "tjzdlyb", "tj", "zdcz", "zdbj", "zdlx", "zdmc" ,"czmc"},
					new String[] { tjzdModel.getXn(), tjzdModel.getXmdm(),
							tjzdModel.getTjdm(), tjzdModel.getTjzdlyb(),
							tjzdModel.getZdval(), ">=", tjzdModel
							.getZdbj(), tjzdModel.getCslx(), tjzdModel.getTjmc(),tjzdModel.getCzmc() }, request);
		}
		return bFlag;
	}
	
	/**
	 * �������ò�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xmmc", "tjzdm", "zdbj", "tj"};
		String[] cnList = new String[]{"pk", "�к�", "��Ŀ����", "�����ֶ���", "�ֶαȽ�", "��Ӧֵ"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**
	 * �������ò�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTjResult(String xmdm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] enList = new String[]{"pk", "rownum", "jxjmc", "zdmc", "czmc", "tj"};
		String sql = "select a.jxjdm||a.tjzdm pk,rownum,(case when a.zdlx='jxj' then (select b.jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) else (select b.rychmc from rychdmb b where a.jxjdm=b.rychdm) end) jxjmc,a.zdmc,a.czmc,(case  when a.tjzdm='zhszcpcjpm' then a.tj||'%' else a.tj end)tj from jxjhdtj a where a.jxjdm=?";
		resList = dao.rsToVator(sql, new String[]{xmdm}, enList);
		return resList;
	}
	
	/**
	 * ������������ɾ��
	 * @return
	 * @throws Exception
	 */
	public boolean tjszplDel() throws Exception {
		return dao.runUpdate("delete from jxjhdtj", new String[]{});
	}
	
	/**
	 * �������õ���ɾ��
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean tjszdgDel(String pkValue) throws Exception {
		return dao.runUpdate("delete from jxjhdtj where jxjdm||tjzdm=?", new String[]{pkValue});
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡ������ʮ�Ѵ�ѧ���÷ּ�����
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String[] getStudfAndPm(String xh) throws Exception {
		String[] dfAndPmList = new String[2];
		String sql = "select num,xh,zf from (select rownum num,xh,zf from (select xh,zf from sjdxspfxzb order by to_number(zf) desc)) where xh=? ";
		dfAndPmList = dao.getOneRs(sql, new String[]{xh}, new String[]{"num", "zf"});
		return dfAndPmList;
	}
	
	/**
	 * �Ƚ��༶��˽��
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public int getXjbjShjg(HttpServletRequest request) throws Exception {
		String[] jxjsq = dao.getOneRs("select jxjsqxn,jxjsqxq from xtszb ", new String[]{}, new String[]{"jxjsqxn","jxjsqxq"}); 
		String tg = "";
		if (request.getSession().getAttribute("userType").toString().equalsIgnoreCase("xy")) {
			tg = dao.getOneRs("select count(*) num from pjpy_xjbjandwmsqb where xn=? and xq=? and xysh='ͨ��'", new String[]{jxjsq != null ? jxjsq[0] : "" , jxjsq != null ? jxjsq[1] : ""}, "num");
		} else {
			tg = dao.getOneRs("select count(*) num from pjpy_xjbjandwmsqb where xn=? and xq=? and xysh='ͨ��' and xxsh='ͨ��'", new String[]{jxjsq != null ? jxjsq[0] : "" , jxjsq != null ? jxjsq[1] : ""}, "num");
		}
		return StringUtils.isNull(tg) ? 0 : Integer.parseInt(tg);
	}
	
	public String[] getJxjsqxnxq() throws Exception {
		return dao.getOneRs("select jxjsqxn,jxjsqxq from xtszb",
				new String[] {}, new String[] { "jxjsqxn", "jxjsqxq" });
	}
	
	/**
	 * ѧ���ɼ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscj(String xh) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String[] colList = null;
		String[] valList = null;

		List<String[]> resList = new ArrayList<String[]>();
		String[] jxjsqxnxq = getJxjsqxnxq();
		if (jxjsqxnxq == null) {
			jxjsqxnxq = new String[2];
		}

		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			sql = "select * from (select kcmc, (select xqmc from xqdzb where xqdm = xq) xq, cj"
					+ " from cjb where xh = ? and xn = ? and kcxz not like '%Уѡ�޿�%'and kcmc not like "
					+ " '%����������%'and upper(xkkh) not like '%BY%' union select 'ƽ����' kcmc,'"
					+ jxjsqxnxq[0]
					+ " ѧ�� '||(select xqmc from xqdzb where xqdm = xq)||'ѧ��' xq, "
					+ " to_Char(round(avg(cj))) cj from view_zhhcjb where xh = ? and xn = ? "
					+ " and kcxz not like '%Уѡ�޿�%' and kcmc not like '%����������%' and upper(xkkh)"
					+ " not like '%BY%' group by xq) order by xq desc";
			colList = new String[] { xh, jxjsqxnxq[0], xh, jxjsqxnxq[0] };
			valList = new String[] { "kcmc", "xq", "cj" };
		} else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){
			sql = "select kcmc,cj from cjb where xh=? and xn=? ";
			colList = new String[] { xh, jxjsqxnxq[0]};
			valList = new String[] { "kcmc", "cj" };
		}else {
			sql = "select kcmc,cj from cjb where xh=? and xn=? and xq=?";
			colList = new String[] { xh, jxjsqxnxq[0], jxjsqxnxq[1] };
			valList = new String[] { "kcmc", "cj" };
		}

		resList = dao.rsToVator(sql, colList, valList);
		return resList;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(Math.round(3.55));
	}
	
	/**
	 * ��ѯ����汾 1Ϊѧ��,0Ϊ����,����Ϊ������˾����
	 * @return
	 */
	public String getJwFlag() {
		String jwflag = dao.getOneRs("select jwflag from xtszb", new String[]{}, "jwflag");
		return StringUtils.isNull(jwflag) ? "" : jwflag.trim();
	}
	
	/**
	 * ���ѧ�����������б�
	 * 
	 * @author luojw
	 * @return
	 */
	public List<HashMap<String, String>> getKsxzList() {

		DAO dao = DAO.getInstance();

		String sql = "select distinct (kcxz) dm, kcxz mc from view_cjb where kcxz is not null";

		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	public List<HashMap<String, String>> getDjksmc() {
		return dao.getList("select distinct djksmc dm,djksmc mc from xsdjksb", new String[]{}, new String[]{"dm", "mc"});
	}
}
