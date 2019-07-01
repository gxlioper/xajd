package xgxt.qgzx.zgdzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: �й����ʴ�ѧ�ڹ���ѧ��𷢷�DAO
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ����
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2009-02-10
 * </p>
 */
public class QgzxCjffDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * ��ϲ�ѯ����
	 * @param model
	 * @return StringBuffer
	 * */
	private StringBuffer getWhereSql(CommanForm model){
		String xxdm = StandardOperation.getXxdm();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = DealString.toGBK(model.getXh());
		String xm = DealString.toGBK(model.getXm());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String yrdwdm = model.getYrdwdm();
		String gwdm = model.getGwdm();
		String ffsj = model.getFfsj();
		String yf = model.getYf();
		String userName = model.getUserName();
		String fflx = model.getFflx();
		String ffsjks = model.getFfsjks();
		String ffsjjs = model.getFfsjjs();
		String xxsh = model.getXxsh();
		String tableName = model.getTableName();
		
		StringBuffer sb = new StringBuffer("where 1=1 ");
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(!StringUtils.isNull(xydm)){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(zydm)){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(nj)){
			sb.append( " and nj=?");
			value.add(nj);
		}		
		if(xn !=null && !xn.equalsIgnoreCase("")){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(xq)){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(!StringUtils.isNull(ffsj) && "view_xscjff".equalsIgnoreCase(tableName)){
			sb.append( " and substr(ffsj,0,8) like '%'||?||'%' ");
			value.add(ffsj);
		}
		if(!StringUtils.isNull(ffsj) && "view_xslscjff".equalsIgnoreCase(tableName)){
			sb.append( " and substr(ffsj,0,10) like '%'||?||'%' ");
			value.add(ffsj);
		}
		if(!StringUtils.isNull(ffsjks) && "view_xscjff".equalsIgnoreCase(tableName)){
			sb.append( " and sj >= to_number(?)");
			value.add(ffsjks.replaceAll("-", ""));
		}
		if(!StringUtils.isNull(ffsjks) && "view_xslscjff".equalsIgnoreCase(tableName)){
			sb.append( " and to_number(substr(ffsj,0,4)||substr(ffsj,6,2)||substr(ffsj,9,2)) >= to_number(?)");
			value.add(ffsjks.replaceAll("-", ""));
		}
		if(!StringUtils.isNull(ffsjjs) && "view_xscjff".equalsIgnoreCase(tableName)){
			sb.append( " and sj <= to_number(?)");
			value.add(ffsjjs.replaceAll("-", ""));
		}
		if(!StringUtils.isNull(ffsjjs) && "view_xslscjff".equalsIgnoreCase(tableName)){
			sb.append( " and to_number(substr(ffsj,0,4)||substr(ffsj,6,24)||substr(ffsj,9,2)) <= to_number(?)");
			value.add(ffsjjs.replaceAll("-", ""));
		}
		if(!StringUtils.isNull(gwdm)){
			sb.append( " and gwdm=?");
			value.add(gwdm);
		}
		if(!StringUtils.isNull(xxsh)){
			sb.append( " and xxsh=?");
			value.add(xxsh);
		}
		if(!StringUtils.isNull(yf) && !"0".equalsIgnoreCase(yf)){
			sb.append( " and yf=?");
			value.add(yf);
		}
		if(!StringUtils.isNull(yrdwdm)){
			sb.append( " and sqdw=?");
			value.add(yrdwdm);
		}else{
			if(checkExists("yrdwdmb", "dlm", model.getUserName())){
				//���˵�λ�û�
				sb.append( " and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm=?)");
				value.add(userName);				
			}
		}
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//�й����ʴ�ѧ
			if("��������".equalsIgnoreCase(fflx)){
				sb.append(" and fflx = ?");
				value.add("����");
			}
			if("�¹���".equalsIgnoreCase(fflx)){
				sb.append("  and fflx is null ");
			}
		}
		
		return sb;
	}
	/**
	 * ����¼�Ƿ����
	 * 
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 */
	public boolean checkExists(String tableName, String pk, String pkValue) {
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String result = getOneRs(sql, new String[] { pkValue }, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}	

	/**
	 * ��ȡ���¹̶���λ��ѧ���б� 
	 * @param model
	 * @return List
	 */
	public List<String[]> getGdgwStuList(QgzxCjffForm model) {
		String year = model.getYear();
		String month = model.getMonth();
		String pkValue = model.getPkValue();		
		String fflx = model.getFflx();
		String[] column = { "rownum", "yrdwmc", "gwdm", "bjdm", "xh", "xm",
				"gzsj", "cjje", "lxdh", "xsqm", "bz","zgcjje" };
		String sql = "select rownum, a.*,b.gzsj,b.cjje,b.bz,'' xsqm from"
				+ "("
				+ "select xh,xm,bjmc,bjdm,gwsbsj,gwdm,zgcjje,(SELECT lxdh FROM view_xsjbxx b WHERE a.xh=b.xh)lxdh,(SELECT yrdwmc FROM yrdwdmb b WHERE a.yrdwdm=b.yrdwdm)yrdwmc,(SELECT gwxzmc FROM view_gwxx b WHERE a.gwdm=b.gwdm AND a.gwsbsj=b.gwsbsj)gwxzmc"
				+ " from view_xsgwxx a where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx WHERE gwdm||gwsbsj=? and xxyj='ͨ��')";
	    if(fflx == null || "".equalsIgnoreCase(fflx)){
	    	sql += ")a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm and b.nd=? and b.yf=? and fflx is null order by a.xh";
	    }else{
	    	sql += ")a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm and b.nd=? and b.yf=? and fflx='" + fflx + "' order by a.xh";
	    }
	    System.out.println("�ڹ���ѧ��𷢷Ų�ѯsql:" + sql);
	    System.out.println("pkValue:" + pkValue);
	    System.out.println("year:" + year);
	    System.out.println("month:" + month);
	    
		List<String[]> list = rsToVator(sql, new String[] { pkValue, year, month}, column);
		// ================begin ���ΰ 2009/4/22========================
		//List<String[]> tzList = getTzqgw(pkValue, column, year, month ,fflx);
		// ================end ���ΰ 2009/4/22========================

		//list.addAll(tzList);
		return list;
	}

	/**
	 * ��ѯ����ܽ��
	 * 
	 * @param model
	 * @return String
	 */
	public String getZcjje(QgzxCjffForm model) {
		String pkValue = model.getPkValue();
		String year = model.getYear();
		String month = model.getMonth();

		String sql = "select sum(cjje) num from xscjffb where gwdm||sqsj=? and nd=? and yf=?";
		return getOneRs(sql, new String[] { pkValue, year, month }, "num");
	}

	/***************************************************************************
	 * ��ȡ��λ����ǰ�ĸ�λ��Ϣ
	 * 
	 * @param pkValue
	 * @return ArrayList
	 **************************************************************************/
	public ArrayList<String[]> getTzqgw(String pkValue, String[] columns,String year,String month,String fflx) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		String querry = "";
		if(fflx == null || "".equalsIgnoreCase(fflx)){
			querry += " and b.fflx is null ";
		}else{
			querry += " and b.fflx='" + fflx + "' ";
		}
		// ================begin ���ΰ 2009/4/22========================
		String sql = "select rownum ,a.xh,(select bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,'' xsqm,(select lxdh from view_xsjbxx b where a.xh=b.xh)lxdh,(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,a.tzn,a.tzy,a.tzqgw gwdm,"
				+ " a.tzqgwsbsj,a.nd,a.yf,a.dqn,a.dqy,a.cjje,a.bz ,a.gzsj,a.khqk,a.kh, (select xymc from view_xsjbxx b where a.xh=b.xh)xymc,gwxzmc,(select yrdwmc from view_gwxx b where a.tzqgw||a.tzqgwsbsj=b.gwdm||b.gwsbsj)yrdwmc from ("
				+ "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,(select gwxzmc from view_gwxx b where a.tzqgw||a.tzqgwsbsj=b.gwdm||b.gwsbsj)gwxzmc,a.dqn,a.dqy,b.nd,b.yf,b.gzsj,b.cjje,b.bz,b.khqk ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh"
				+ " from ("
				+ "select b.xh,substr(b.tzsj,0,4) tzn,substr(b.tzsj,6,2)tzy,b.tzqgw ,b.tzqgwsbsj,"
				+ year +" dqn,"
				+ month + " dqy"
				+ " from qgzx_gwtzb b where not exists(select a.xh from xsgwxxb a where a.xh=b.xh and a.gwdm=b.tzqgw and a.gwsbsj=b.tzqgwsbsj)"
				+ " and tzqgw||tzqgwsbsj='"
				+ pkValue
				+ "'"
				+ ")a left join xscjffb b on a.xh=b.xh and a.tzqgwsbsj=b.sqsj and a.tzqgw=b.gwdm and b.nd=a.dqn and b.yf=a.dqy "+querry+" left join khxgsqb c on c.xh=b.xh"
				+ ") a where dqn=tzn and dqy=tzy";
		list = rsToVator(sql, new String[] {}, columns);
		// ================end ���ΰ 2009/4/22========================
		return list;
	}

	/**
	 * ��ȡ��ǰ���·�
	 * 
	 * @return String
	 */
	public String getNowMonth() {
		String sql = "select to_char(sysdate,'mm') nowTime from dual";
		String nowTime = getOneRs(sql, new String[] {},
				new String[] { "nowTime" })[0];
		return nowTime;
	}

	/**
	 * ����������ѯ��λ�������Ϣ
	 * 
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getGwinfo(String pk, String pkValue) {
		String[] columns = { "nowtime", "gwdm", "gwsbsj", "spbcbz", "gwxzmc",
				"yrdwdm", "yrdwmc" };
		String sql = "select to_char(SYSDATE,'YYYYMMDDHH24MISS')nowtime,gwdm, gwsbsj, spbcbz,gwxzmc,sqdw yrdwdm,(select distinct yrdwmc from yrdwdmb b where a.sqdw=b.yrdwdm)yrdwmc from view_gwxx a  where "
				+ pk + "= ?";

		return getMap(sql, new String[] { pkValue }, columns);
	}

	/**
	 * ��ȡ���Ƶ���߳����
	 * 
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getQgzxConfig() {
		String sql = "select myzgbc,myzgxs from gwsqsjb";
		HashMap<String, String> map = getMap(sql, new String[] {}, new String[]{"myzgbc","myzgxs"});
		map.put("myzgbc", map.get("myzgbc") == null || "".equalsIgnoreCase(map.get("myzgbc"))? "0" : map.get("myzgbc"));
		map.put("myzgxs", map.get("myzgxs") == null || "".equalsIgnoreCase(map.get("myzgxs"))? "0" : map.get("myzgxs"));
		return map;
	}

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> selectGwsbsjb() {
		String sql = "select xn, nd, xq, kssj, jssj, knsbl, mxsbc, mtzgxs, myzgxs, myzgbc, xwkssj, xwjssj, myzgsjfs, shkssj, shjssj from gwsqsjb";
		String[] columns = { "xn", "nd", "xq", "kssj", "jssj", "knsbl",
				"mxsbc", "mtzgxs", "myzgxs", "myzgbc", "xwkssj", "xwjssj",
				"myzgsjfs", "shkssj", "shjssj" };
		return getMap(sql, new String[] {}, columns);
	}

	/**
	 * ��ѯѧ����𷢷���Ϣ
	 * 
	 * @param pkValue
	 * @return List
	 */
	public List<HashMap<String, String>> selectCjffInfoOfStu(String pkValue) {
		HashMap<String, String> time = selectGwsbsjb();

		String nd = time.get("nd");
		String yf = getNowMonth();
		

		String sql = "select xh,xm,bjmc,gzsj,cjje,nd,yf,bz,decode(xxsh,'ͨ��','CHECKED','') xxsh,decode(fflx,'','����',fflx)fflx from view_xscjff a where exists(select 1 from view_xsgwxx b where a.gwdm=b.gwdm and a.xh=b.xh and b.gwdm||b.gwsbsj=?) and ffsj like '"
				+ nd + yf + "%'";
		String[] columns = { "xh", "xm", "bjmc", "gzsj", "cjje", "nd", "yf",
				"bz", "xxsh", "fflx" };

		return getList(sql, new String[] { pkValue }, columns);
	}

	/**
	 * ��ȡ���˵�λ�б�
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getYrdwList() {
		String sql = "select * from yrdwdmb";
		return getList(sql, new String[] {},
				new String[] { "yrdwdm", "yrdwmc" });
	}

	/**
	 * ��ȡ��λ��Ϣ�б�
	 * 
	 * @return List
	 */
	public List<HashMap<String, String>> getGwList() {
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx where SHJG='ͨ��' and gzjsrq>to_char(sysdate,'yyyymmdd')";
		List<HashMap<String, String>> gwList = getList(sql, new String[] {}, new String[] { "gwdm",
				"gwdmgwsbsj" });
		return gwList;
	}

	/**
	 * �жϳ�𷢷ŵļ��ʱ���Ƿ����趨�ķ�Χ֮��
	 * 
	 * @param yrdwdm
	 * @return boolean
	 */
	public boolean checkFfsj(String pkValue) {
		boolean flag = false;
		String sql = "select ffsjjg from gwsqsjb";
		String ffsjjg = getOneRs(sql, new String[] {}, "ffsjjg");

		String yrdwdm = getOneRs(
				"select sqdw yrdwdm from view_gwxx where gwdm||gwsbsj=?",
				new String[] { pkValue }, "yrdwdm");

		if (ffsjjg != null && !"".equalsIgnoreCase(ffsjjg)) {
			HashMap<String, String> time = new HashMap<String, String>();
			sql = "select ffsj, to_char(sysdate,'YYYYMMDDHH24MISS')nowtime from cjffsjb where yrdwdm=?";
			time = getMap(sql, new String[] { yrdwdm }, new String[] { "ffsj",
					"nowtime" });
			String ffsj = time.get("ffsj");
			String nowtime = time.get("nowtime");
			if (ffsj != null && !"".equalsIgnoreCase(ffsj)) {
				String jgsj = GetTime.getDaysOfTowDateBySec(nowtime, ffsj);
				jgsj = jgsj == null || "".equalsIgnoreCase(jgsj) ? "0" : jgsj;
				if (Integer.parseInt(jgsj) < Integer.parseInt(ffsjjg)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * ��ʱ�ڳ�𷢷���Ϣ
	 * 
	 * @param pk
	 * @param pkValue
	 * @return List
	 */
	public List<String[]> getLsgcjffInfo(QgzxCjffForm model, String page) {
		String pk = "gwdm||gwsbsj";
		String pkValue = model.getPkValue();
		String nd = model.getYear();
		String month = model.getMonth();
		int startpage = Integer.parseInt(page) * 2;
		int endpage = (Integer.parseInt(page) + 1) * 2;
		String[] outputValue = { "����", "yrdwmc", "gwdm", "bjdm", "xh", "xm",
				"gzsj", "cjje", "lxdh", "xsqm", "bz" };
		String sql = "select * from (select rownum num, gwdm||gwsbsj ����,xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,(select xb from view_xsjbxx b where a.xh=b.xh)xb,"
				+ "(select bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,(select lxdh from view_xsjbxx b where a.xh=b.xh)lxdh,"
				+ "(select distinct yrdwmc from yrdwdmb b where b.yrdwdm=(select sqdw from view_gwxx c where c.gwdm=a.gwdm and c.gwsbsj=a.gwsbsj)) yrdwmc,"
				+ "gwdm,gwsbsj,nd,yf,cjje,gzsj,bz,'' xsqm from xslsgcjffb a where a."
				+ pk + "=? and nd=? and yf=?) where num>? and num <=?";

		return rsToVator(sql, new String[] { pkValue, nd, month,
				String.valueOf(startpage), String.valueOf(endpage) },
				outputValue);
	}

	/**
	 * ����������ѯ��λ�������Ϣ
	 * 
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * @author luo
	 */
	public HashMap<String, String> getGwinfoOne(QgzxCjffForm model) {

		@SuppressWarnings("unused")
		String pk = model.getPk();
		String pkValue = model.getPkValue();
		@SuppressWarnings("unused")
		String nd = model.getYear();
		@SuppressWarnings("unused")
		String month = model.getMonth();

		String[] outputValue = { "yrdwmc", "gwdm" };

		String sql = "select yrdwmc,gwdm from view_gwxx a where a.gwdm || gwsbsj = ?";

		return getMap(sql, new String[] { pkValue }, outputValue);
	}

	/**
	 * @return ѧ����Ϣ
	 * @author luo
	 */
	public String[] getXsInfo(String xh) {
		DAO dao = DAO.getInstance();
		String[] getPara = { "bjdm", "xm", "lxdh" };
		String sql = "select bjdm,xm,lxdh from view_xsxxb where xh=?";
		String[] reVal = dao.getOneRs(sql, new String[] { xh }, getPara);
		if(reVal != null && reVal.length>0){
			for(int i=0; i<reVal.length; i++){
				reVal[i] = reVal[i] == null ? "" : reVal[i];
			}
		}
		return reVal;
	}
	
	/**
	 * ��ȡ��ǰʱ��,��ʽ:****��**��**��
	 * @return String 
	 * */
	public String getNowTime() {
		DAO dao = DAO.getInstance();
		String sql= "select to_char(sysdate,'YYYY')||'��'||to_char(sysdate,'MM')||'��'||to_char(sysdate,'DD')||'�� ' nowtime from dual";
		String time = dao.getOneRs(sql, new String[] {}, "nowtime");
		return time;
	}
	
	/**
	 * ��ȡ��ʱ�ڳ���ܶ� 
	 * @param model
	 * @return String
	 */
	public String getLsgcjze(QgzxCjffForm model) {
		String pk = model.getPk();
		String pkValue = model.getPkValue();
		String nd = model.getYear();
		String month = model.getMonth();
		String sql = "select sum(cjje) zje from xslsgcjffb where " + pk
				+ "=? and nd=? and yf=?";

		return getOneRs(sql, new String[] { pkValue, nd, month }, "zje");
	}
	
	/**
	 * ��ȡ��𷢷���Ϣ��ѯ��ͷ�ֶ�
	 * @return String[]
	 * */
	public String[] getXscjffbCol(CommanForm model){
		String xxdm = StandardOperation.getXxdm();
		String[] colList = new String[] { "����", "nd", "yf", "xqmc", "xh", "xm", "bjmc", "gwdm", "gzsj", "cjje", "ffsj" };
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//�人����ѧ
			colList = new String[] { "����", "nd", "yf", "xqmc", "xh", "xm", "bjmc", "gwdm", "cjje", "ffsj", "gzsj", "xxsh"};
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//�й����ʴ�ѧ
			colList = new String[] { "����", "nd", "yf", "xqmc", "xh", "xm", "bjmc", "gwdm", "cjje", "ffsj", "gzsj", "fflx", "xxsh"};
			if(model.getFflx() != null && "��ʱ�ڹ���".equalsIgnoreCase(model.getFflx())){
				colList = new String[] { "����", "nd", "yf", "xqmc", "xh", "xm", "bjmc", "gwdm", "cjje", "ffsj", "gzsj", "fflx"};
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			// ��������
			colList = new String[] { "����", "xn", "yf", "xqmc", "xh", "xm", "bjmc", "gwdm", "cjje", "ffsj", "gzsj" };
		}
		if(Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){
			//�㽭��ýѧԺ
			colList = new String[]{"����","nd","yf","xh","xm","����������","xymc","yrdwmc","gwdm","cjje","yhmc","yhkh","xxsh","shsj","ffsj"};
		}
		return colList;
	}
	
	/**
	 * ѧ����𷢷���Ϣ��ѯ
	 * @param CommanForm model
	 * @return List<String>
	 * */
	public List<String[]> selectXscjffb(CommanForm model){
		String sql = "select * from (select rownum r ,xh||gwdm||sqsj||nd||yf ����,a.*,'' knslx,to_number(substr(ffsj,0,8)) sj from " + model.getTableName() + " a) a ";
		String[] outputValue = getXscjffbCol(model);		
		sql = sql + getWhereSql(model);
		
		//��ѯ�ܼ�¼��
		String cnt = getOneRs("select count(*)num from (" + sql + ")", value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(cnt));
		sql = "select * from (" + sql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize());		
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * �й����ʴ�ѧѧ����𷢷���Ϣ��ѯ
	 * @param CommanForm model
	 * @return List<String>
	 * */
	public List<String[]> selectXscjffbByZgdzdx(CommanForm model){
		String fflx = model.getFflx();
		String tableName = "��ʱ�ڹ���".equalsIgnoreCase(fflx) ? "view_xslscjff" : model.getTableName();
		String sql = "";
		String[] colList = getXscjffbCol(model);
		
		model.setTableName(tableName);
		String querry = getWhereSql(model).toString();
		
		if(fflx != null && "��ʱ�ڹ���".equalsIgnoreCase(fflx)){
			tableName = "view_xslscjff";
			colList = new String[] { "����", "nd", "yf", "xqmc", "xh", "xm", "bjmc", "gwdm", "cjje", "ffsj", "gzsj", "fflx"};
			sql = "select rownum r, xh||gwdm||gwsbsj||nd||yf ����, nd, yf, xh, xm, bjmc, gwdm, cjje, gzsj, '��ʱ' fflx, xn, xq, xxsh, ffsj,xqmc from " + tableName + " a " + querry;
		}else if(fflx != null && "��������".equalsIgnoreCase(fflx)){
			sql = "select rownum r, xh||gwdm||sqsj||nd||yf||fflx ����, nd, yf, xqmc, xh, xm, bjmc, gwdm, cjje, ffsj, gzsj, decode(fflx,'','����',fflx) fflx, xxsh from " + tableName + " a " + querry;
		}else{
			sql = "select rownum r, xh||gwdm||sqsj||nd||yf||fflx ����, nd, yf, xqmc, xh, xm, bjmc, gwdm, cjje, ffsj, gzsj, decode(fflx,'','����',fflx) fflx, xxsh from " + tableName + " a " + querry;
		}
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs("select count(*)num from (" + sql + ")", value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize());
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, colList);
	}
	
	/**
	 * �㽭��ýѧԺѧ����𷢷���Ϣ��ѯ
	 * @param CommanForm model
	 * @return List<String>
	 * */
	public List<String[]> selectXscjffbByZjcmxy(CommanForm model){
		String sql = "select rownum r ,xh||gwdm||sqsj||nd||yf ����,a.*,'' knslx from " + model.getTableName() + " a ";
		String[] outputValue = {"����","nd","yf","xh","xm","knslx","xymc","yrdwmc","gwdm","cjje","yhmc","yhkh","xxsh","shsj","ffsj"};
		sql = sql + getWhereSql(model);
		
		//��ѯ�ܼ�¼��
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs("select count(*)num from (" + sql + ")", value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize());		
		List<String[]> rs = rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
		//��ȡѧ���Ƿ���������Ϣ
		for(int i=0; i<rs.size(); i++){
			String xh = rs.get(i)[3];
			rs.get(i)[5] = getKnslx(xh);
		}
		return rs;
	}
	
	/**
	 * ����ʣ�ྭ��
	 * @param HashMap<String, String> map
	 * @return String
	 * */
	public String getSyjf(HashMap<String, String> map){
		String xh = map.get("xh");
		String nd = map.get("nd");
		String yf = map.get("yf");
		String gwdm = map.get("gwdm");
		String gwsbsj = map.get("gwsbsj");
		String num = "";
		//��ȡ��λ���
		HashMap<String, String> tmp = getMap("select sqdw ,gwxz gwxzdm,gwxzmc from view_gwxx where gwdm=? and gwsbsj=?", new String[]{gwdm,gwsbsj}, new String[]{"sqdw","gwxzdm","gwxzmc"});
		//��ȡ���ŵ��ܽ��
		String sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=? and (gwxzdm=? or gwxzdm is null) ),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and (gwxzmc=? or gwxzmc is null)),0) syjf from dual";
		num = getOneRs(sql, new String[]{nd,tmp.get("sqdw"),tmp.get("gwxzdm"),nd, tmp.get("sqdw"), tmp.get("gwxzmc")}, "syjf");
		num = StringUtils.isNull(num) ? "0" : num;
		
		//��ȡ����ѧ����ǰ�·��Ѿ����ŵĽ��
		sql = "select cjje from xscjffb where xh=? and nd=? and yf=? and gwdm=? and sqsj=?";
		String cjje = getOneRs(sql, new String[]{xh,nd,yf,gwdm,gwsbsj}, "cjje");
		cjje = StringUtils.isNull(cjje) ? "0" : cjje;
		
		//ʣ����=���ݿ��е�ʣ����-��ǰ����ѧ����ǰ��ȵ�ǰ�·ݵ�ǰ��λ�Ѿ����ŵĽ��
		num = (Float.parseFloat(num) - Float.parseFloat(cjje)) + "";
		num = StringUtils.isNull(num) ? "0" : num;		
		return num;
	}
	
	/**
	 * �ж��޸ĺ��¼�Ƿ����ظ�
	 * @param HashMap<String, String> map
	 * @return String
	 * */
	public String checkRepeat(HashMap<String, String> map){
		String result = ""; 
		String pk = "xh||gwdm||sqsj||nd||yf";
		String[] pkValue = map.get("pkV").split("!!");
		String nd = map.get("nd");
		String yf = map.get("yf");
		
		for(int i=0; i<pkValue.length; i++){
			String sql = "select xh,xm,gwdm,sqsj,nd,yf from view_xscjff where " + pk + " = ?";
			HashMap<String, String> tmp = getMap(sql, new String[]{pkValue[i]}, new String[]{"xh", "xm","gwdm", "sqsj","nd","yf"});
			
			nd = StringUtils.isNull(nd) ? tmp.get("nd") : nd;
			yf = StringUtils.isNull(yf) ? tmp.get("yf") : yf;
			
			sql = "select count(*)num from view_xscjff where xh=? and gwdm=? and sqsj=? and nd=? and yf=?";
			String num = getOneRs(sql, new String[]{tmp.get("xh"),tmp.get("gwdm"), tmp.get("sqsj"),nd,yf}, "num");
			if(Integer.parseInt(StringUtils.isNull(num) ? "0" : num)>0 ){//���ݿ����Ѿ����ڼ�¼  && !nd.equalsIgnoreCase(tmp.get("nd")) && !yf.equalsIgnoreCase(tmp.get("yf"))
				result += tmp.get("xm") + "ͬѧ" + tmp.get("gwdm") + "��λ" + nd + "��" + yf + "�µĳ���Ѿ����ţ�\n";
			}			
		}
		
		return result;
	}
	
	/**
	 * �����޸Ĳ�������ʣ�ྭ��
	 * @param HashMap<String, String> map
	 * @param String cjje
	 * @param String nd
	 * @return String
	 * */
	public String getBatchOperSyjf(HashMap<String, String> map){
		String result ="";
		String pk = "xh||gwdm||sqsj||nd||yf";
		String[] pkValue = map.get("pkV").split("!!");
		String cjje = map.get("cjje");
		
		String nd = map.get("nd");
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>(); //ѧ����𷢷���Ϣ��ѯ���
		StringBuffer sb = new StringBuffer();
		String sql = "";
		for(int i=0; i<pkValue.length; i++){
			if(StringUtils.isNotNull(pkValue[i])){
				sql = "select xh,xm,gwdm,sqsj gwsbsj,nd,yf,sqdw,gwxzmc from view_xscjff where " + pk + " = ?";
				HashMap<String, String> tmp = getMap(sql, new String[]{pkValue[i]}, new String[]{"xh", "xm","gwdm", "gwsbsj","nd","yf","sqdw","gwxzmc"});
				rs.add(tmp); 
				if(StringUtils.isNull(sb.toString())){
					sb.append(" where (gwdm='");
					sb.append(tmp.get("gwdm"));
					sb.append("' and sqsj='");
					sb.append(tmp.get("gwsbsj"));
					sb.append("') ");
				}else{
					sb.append(" or (gwdm='");
					sb.append(tmp.get("gwdm"));
					sb.append("' and sqsj='");
					sb.append(tmp.get("gwsbsj"));
					sb.append("') ");
				}
			}			
		}
		
		//��ѯ���޸ĵ�������������ȡ����뵥λ����λ����		
		sql = "select distinct nd,yrdwmc,sqdw,gwxzmc,(select gwxzdm from gwxzdmb b where a.gwxzmc=b.gwxzmc)gwxzdm from view_xscjff a ";
		List<HashMap<String, String>> list = getList(sql + sb, new String[]{}, new String[]{"nd","yrdwmc","sqdw","gwxzmc","gwxzdm"});
		System.out.println(sql + sb);
		for(int i=0; i<list.size();i++){
			HashMap<String, String> hMap = list.get(i);
//			String nd = hMap.get("nd");
			String yrdwdm = hMap.get("sqdw");
			String gwxzmc = hMap.get("gwxzmc");
			String gwxzdm = hMap.get("gwxzdm");
			
			//���Ѱ���Ҫ�޸ĵ����м�¼�Ļ�������
			sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=? and (gwxzdm=? or gwxzdm is null) ),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and (gwxzmc=? or gwxzmc is null)),0) syjf from dual";
			String syje = getOneRs(sql, new String[]{nd,yrdwdm,gwxzdm,nd,yrdwdm,gwxzmc}, "syjf");
			
			//��ѯ��Ҫ�޸ĵ����м�¼�Ļ����ܶ�
			sql = "select sum(cjje)num from view_xscjff ";
			StringBuffer buffer = new StringBuffer();
			int ffrs = 0;
			for(int j=0; j<rs.size(); j++){
				HashMap<String, String> stu = rs.get(j);
				if((stu.get("sqdw") == null ? "" : stu.get("sqdw")).equalsIgnoreCase(yrdwdm) && (stu.get("nd") == null ? "" : stu.get("nd")).equalsIgnoreCase(nd) && (stu.get("gwxzmc") == null ? "" : stu.get("gwxzmc")).equalsIgnoreCase(gwxzmc == null ? "" : gwxzmc)){
					if(StringUtils.isNull(buffer.toString())){
						buffer.append(" where (xh='");
						buffer.append(stu.get("xh"));
						buffer.append("' and gwdm='");
						buffer.append(stu.get("gwdm"));
						buffer.append("' and sqsj='");
						buffer.append(stu.get("gwsbsj"));
						buffer.append("' and nd='");
						buffer.append(stu.get("nd"));
						buffer.append("')");
					}else{
						buffer.append(" or (xh='");
						buffer.append(stu.get("xh"));
						buffer.append("' and gwdm='");
						buffer.append(stu.get("gwdm"));
						buffer.append("' and sqsj='");
						buffer.append(stu.get("gwsbsj"));
						buffer.append("' and nd='");
						buffer.append(stu.get("nd"));
						buffer.append("')");
					}
					ffrs += 1;
				}				
			}
			String ffje = getOneRs(sql + buffer, new String[]{}, "num");
			cjje = Base.isNull(cjje) ? ffje : (Float.parseFloat(cjje)*ffrs)+"";
			System.out.println(Float.parseFloat(StringUtils.isNull(syje) ? "0" : syje) + ":syje");
			System.out.println(Float.parseFloat(StringUtils.isNull(ffje) ? "0" : ffje) + ":ffje");
			System.out.println(cjje + ":���Ž��");
			
			//��ȵ�λ��λѡ���ʣ�ྭ��+�޸ļ�¼�Ѿ����ŵĽ��-��ȵ�λ��λ����ѧ����*�����Ϊ���յ�ʣ����
//			if(Float.parseFloat(StringUtils.isNull(syje) ? "0" : syje) + Float.parseFloat(StringUtils.isNull(ffje) ? "0" : ffje) - Float.parseFloat(cjje)*ffrs <0){
			if(Float.parseFloat(StringUtils.isNull(syje) ? "0" : syje) - Float.parseFloat(cjje) <0){
				result += (hMap.get("nd") == null ? "" : hMap.get("nd")) + "������˵�λ'" + (hMap.get("yrdwmc") == null ? "" : hMap.get("yrdwmc")) + "'��λ����'" + (hMap.get("gwxzmc") == null ? "" : hMap.get("gwxzmc"))+"'ʣ�ྭ�Ѳ���!�ɷ��Ž�" + (Integer.parseInt(StringUtils.isNull(syje) ? "0" : syje) ) + "\n";
			}			
		}
		System.out.println(result+":���");
		return result;
	}
	
}
