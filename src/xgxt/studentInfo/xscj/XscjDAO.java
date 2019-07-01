package xgxt.studentInfo.xscj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.pjpy.ahjg.PjpyAhjgActionForm;
import xgxt.pjpy.ahjg.PjpyAhjgXscjQryModel;
import xgxt.pjpy.comm.zhcp.zczf.ZhcpZczfForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.dagl.guizdx.XsxxGuizdxDaglForm;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: ����ѧԺѧ���ɼ�ά��DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: honglin
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2012-03-14
 * </p>
 */
public class XscjDAO extends CommDAO{

	DAO  dao = DAO.getInstance();
	final String XJBJDM = "00001";//�Ƚ��༶����
	final String WMSSDM = "00002";//�����������
	List<String> values = new ArrayList<String>();// ���ڴ���ҳ������ֵ
	String xxdm = dao.getXxdm();
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
	 * @return
	 */
	public List<HashMap<String, String>> getKsxzList() {

		DAO dao = DAO.getInstance();

		String sql = "select distinct (kcxz) dm, kcxz mc from view_cjb where kcxz is not null";

		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	/**
	 * ���ѧ�����������б���
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getDjksmc() {
		return dao.getList("select distinct djksmc dm,djksmc mc from xsdjksb", new String[]{}, new String[]{"dm", "mc"});
	}
	/**
	 * ����WHERE������ѯ���
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(XscjModel xscjModel)
			throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String xydm = xscjModel.getXydm();
		String zydm = xscjModel.getZydm();
		String bjdm = xscjModel.getBjdm();
		String xn = xscjModel.getXn();
		String nj = xscjModel.getNj();
		String xh = DealString.toGBK(xscjModel.getXh());
		String xm = DealString.toGBK(xscjModel.getXm());
		String xq = xscjModel.getXq();
		String cjlx = xscjModel.getCjlx();
		String djksmc = xscjModel.getDjksmc();
		String kcmc=xscjModel.getKcmc();
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
		if ("cjb".equalsIgnoreCase(cjlx)&&!StringUtils.isNull(kcmc)) {
			whereSql.append(" and kcmc = ?");
			values.add(kcmc);
		}// end if
		if ("djksb".equalsIgnoreCase(cjlx)&&!StringUtils.isNull(djksmc)) {
			whereSql.append(" and djksmc = ?");
			values.add(djksmc);
		}
		
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// ���ݴ�ѧ
			// TODO
			String[] kcxz = xscjModel.getKcxz();
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
		return whereSql;
	}
	
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
	 * �ȼ����Գɼ���ѯ���
	 * @param xscjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKscjResult(
			XscjModel xscjModel,
			XscjActionForm dataSearchForm, User user,String[] colList) throws Exception {
		List<String[]> listRs = new ArrayList<String[]>();
		String sql = "select rownum r,xn,(select xqmc from xqdzb where xq=xqdm) xq,xh,xm,nj,xymc,bjmc,djksmc,ksrq,cj from view_xsdjksb";
		
		StringBuffer whereSql = getWhereSql(xscjModel);// WHERE�������
//		String[] opCol = new String[] { "xn", "xq", "xh", "xm", "nj", "xymc",
//				"bjmc", "djksmc", "ksrq", "cj" };
		String[] opCol = colList;
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
	/**
	 * �ȼ����Գɼ���ѯ�����
	 * @param xscjModel
	 * @return
	 * @throws Exception
	 */
	public int getKscjResultNum(XscjModel xscjModel) throws Exception {
		List<String[]> listRs = new ArrayList<String[]>();
		String sql = "select xn,xq,xh,xm,xymc,zymc,bjmc,djksmc,ksrq,cj from view_xsdjksb";
		StringBuffer whereSql = getWhereSql(xscjModel);// WHERE�������
		String[] opCol = new String[] { "xn", "xq", "xh", "xm", "xymc", "zymc",
				"bjmc", "djksmc", "ksrq", "cj" };
		listRs = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return listRs.size();
	}
	
	/**
	 * ѧ���ɼ���ѯ���
	 * �γ����͹��� Уѡ�޿�
	 * �γ����ƹ��� ����������
	 * @param xscjModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscjResult(
			XscjModel xscjModel,
			XscjActionForm dataSearchForm, User user,String[] colList) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		List<String[]> listRs = new ArrayList<String[]>();
//		System.out.println(colList.length);
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
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xq,"
					+ "cj,xh,xm,xymc,kcxz,bjmc,kcmc,(select round(avg(b.cj)) from view_cjb b where kcxz like "
					+ "'%����%' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf,"
					+ "case when a.bkcj is not null and a.cxcj is not null then '������'"
					+ "when bkcj is not null then a.bkcj || '(�����ɼ�)'"
					+ "when cxcj is not null then a.cxcj || '(���޳ɼ�)'"
					+ "else '�����޻򲹿�' end bkcx,xf from view_cjb a";
		} else {
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xqmc,xq,cj,xh,xm,xymc,kcxz,bjmc,kcmc,"
					+ "'' pjf from view_cjb a";
		}
		StringBuffer whereSql = getWhereSql(xscjModel);// WHERE�������
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
//			opCol = new String[] { "xn", "xq", "xh", "xm", "nj", "xymc", "bjmc",
//					"kcmc", "kcxz", "cj" };
			opCol = colList;
		}
		sql = "select * from ("
			+ sql
			+ whereSql.toString()
			+ getUserSql(user)
			+ " order by xh) where r<="
			+ (dataSearchForm.getPages().getStart() + dataSearchForm
					.getPages().getPageSize()) + " and r> "
			+ dataSearchForm.getPages().getStart();
		listRs = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return listRs;
	}
	
	
	// -----------------------2012.4.9 qlj begin --------------------------
	/**
	 * ѧ���ɼ���ѯ���(�㶫����ְҵ����ѧԺ���Ի�)
	 * @param xscjModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscjList(
			XscjModel xscjModel,
			XscjActionForm dataSearchForm, User user,String[] colList) throws Exception {
	
		List<String[]> listRs = new ArrayList<String[]>();

		StringBuilder sql = new StringBuilder();
		 
		String bjdm=xscjModel.getBjdm();
		String xn=xscjModel.getXn();
		String xq=xscjModel.getXq();
		String kcmc=xscjModel.getKcmc();
		
		sql.append(" select rownum r,a.* from( ");
		sql.append(" select a.*,b.xymc,b.zymc,b.bjmc, ");
		sql.append(" (select xqmc from xqdzb b where a.xq=b.xqdm)xqmc  from (select a.* from( ");
		sql.append(" select a.XN,a.XQ,a.XH,a.KCSBM,a.KCMC,a.XF,a.CJ,a.KCXZ,a.BKCJ, ");
		sql.append(" a.CXCJ,a.JD,a.KHFS,a.ZPCJ1,a.CXBJ,a.ND,a.KCLX, a.XKKH, ");
		sql.append(" b.xm, b.bjdm,b.zydm,b.xydm,b.nj, ");
		sql.append(" (rank()over(partition by a.kcmc order by to_number(cj) desc nulls last)) pm ");
		sql.append("  from cjb a,("+Base.xsxxb+")b  ");
		sql.append("  where kcmc = '"+kcmc+"' and a.xh=b.xh ");
		sql.append("  and exists (select 1  from ("+Base.xsxxb+") b ");
		sql.append("  where a.xh = b.xh ");
		sql.append("  and b.bjdm ='"+bjdm+"') ");
		sql.append("  and xn ='"+xn+"' ");
		sql.append("  and xq ='"+xq+"' )a)a,view_njxyzybj b where a.bjdm=b.bjdm)a  ");
		
		StringBuffer whereSql = getWhereSql(xscjModel);// WHERE�������
		sql.append(whereSql.toString());
		
		sql.append(getUserSql(user));
		sql.append(" order by pm ");
		
		listRs=CommonQueryDAO.commonQuery(sql.toString(), "",  values
				.toArray(new String[]{}), colList, dataSearchForm);
		
		return listRs;
	}
	
	/**
	 * ѧ���ɼ���ѯ�����
	 * 
	 * @param xscjModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public int getXscjResultNum(
			XscjModel xscjModel, String userType, String userName) throws Exception {
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
		StringBuffer whereSql = getWhereSql(xscjModel);// WHERE�������
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
	
	/**
	 * ��ɾ������ѡ
	 * @param yhm
	 * @param yhlx
	 * @param xszd
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public int[] saveKindChoose(String yhm,String yhlx,String xszd,String path,String type) throws Exception{
		DAO dao=DAO.getInstance();
		String[]sql=new String[2];
		sql[0]=" delete from xg_xsxx_xscjlxb where yhm='"+yhm+"' and yhlx='"+yhlx+"' and path='"+path+"' and btype='"+type+"'";
		sql[1]=" insert into xg_xsxx_xscjlxb(yhm,yhlx,path,xxszd,btype) values('"+yhm+"','"+yhlx+"','"+path+"','"+xszd+"','"+type+"') ";
		//ֻ���ж��Ƿ�ɹ�����
		int[]result=dao.runBatch(sql);
		return  new int[]{result[1]};
	}
	/**
	 * ��ȡָ���û�ѡ�е���
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public HashMap<String, String> getCheckKind(XscjActionForm model, User user,String type) {
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		String yhlx="tea";
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			yhlx="stu";
		}
		//ѧ���ɼ���ѡ��
		sql.append("  select * from xg_xsxx_xscjlxb where 1=1 ");
		sql.append(" and yhm=? ");
		sql.append(" and yhlx=? ");
		sql.append(" and path=?");
		sql.append(" and btype=?");
		sql.append(" and rownum=1 ");
		return dao.getMap(sql.toString(), new String[]{user.getUserName(),yhlx,model.getPath(),type}, new String[]{"xxszd"});
	}
	
	// -----------------2012.4.9 qlj begin--------------------------
	/**
	 * ��ȡĳѧ�ꡢĳѧ��xx�༶�γ�
	 */
	public List<HashMap<String,String>>getBjkcInfo(XscjActionForm model){
		String bjdm=model.getBjdm();
		
		String xn=model.getXn();
		
		String xq=model.getXq();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select distinct kcmc from cjb a where xn=? and xq=? ");
		sql.append(" and exists(select 1 from view_xsjbxx b where bjdm=? and a.xh=b.xh) ");
		
		return dao.getList(sql.toString(), new String[]{xn,xq,bjdm}, new String[]{"kcmc"});
	}
	
	/**
	 * ��ȡ�û�Ȩ����Ϣ
	 * @param model
	 * @return
	 */
	public String getUserSql(User user){
		
		String fdyQx=user.getFdyQx();
		String bzrQx=user.getBzrQx();
		String userName=user.getUserName();
		String userDep=user.getUserDep();
		String userType=user.getUserType();
		StringBuilder userQuery=new StringBuilder();
		if("true".equalsIgnoreCase(fdyQx) && "true".equalsIgnoreCase(bzrQx)){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if("true".equalsIgnoreCase(fdyQx)){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("true".equalsIgnoreCase(bzrQx)){
			userQuery.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("xy".equalsIgnoreCase(userType)){
			userQuery.append(" and xydm='"+userDep+"' ");
		}
		return userQuery.toString();
	}
	
	// -----------------2012.4.9 qlj end-----------------------------
}
