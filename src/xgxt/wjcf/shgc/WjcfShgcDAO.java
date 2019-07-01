package xgxt.wjcf.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: �Ϻ����̼�����ѧΥ�ʹ���DAO�̳й���DAO
 * </p>
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
 * Time: 2008-05-19
 * </p>
 */
public class WjcfShgcDAO  {
	DAO dao = DAO.getInstance(); 
	ArrayList<String> values = new ArrayList<String>();// ��ѯ����ֵ
    
	public List<HashMap<String, String>> getChkList(int type) {
		return dao.getChkList(type);
	}
	
	
	
	
	/**
	 * ��ȡ��������б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCflbList() throws Exception {
		List<HashMap<String, String>> cflbList = new ArrayList<HashMap<String, String>>();
		String sql = "select cflbdm,cflbmc from cflbdmb";// �������
		cflbList = dao.getList(sql, new String[] {}, new String[] { "cflbdm",
				"cflbmc" });
		return cflbList;
	}

	/**
	 * ��ȡ����ԭ���б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfyyList() throws Exception {
		List<HashMap<String, String>> cfyyList = new ArrayList<HashMap<String, String>>();
		String sql = "select cfyydm,cfyymc from cfyydmb";// ����ԭ��
		cfyyList = dao.getList(sql, new String[] {}, new String[] { "cfyydm",
				"cfyymc" });
		return cfyyList;
	}

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQryStuInfo(String xh, String userType)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_xsjbxx where xh = ?";
		String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");// �������
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
			map.put("stuExists", "yes");// ѧ�Ŵ���
		} else {
			map.put("stuExists", "no");// ѧ���޴���
		}// end if
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		String tmp[] = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
				"dqnd" });
		map.put("xn", tmp[0]);// ��ǰѧ��
		map.put("nd", tmp[1]);// ��ǰ���
		map.put("userType", userType);
		return map;
	}

	/**
	 * ����ѧԺ�걨��Ϣ
	 * 
	 * @param shgcxysbModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXysbXx(WjcfShgcXysbModel shgcxysbModel, String xn,
			String nd) throws Exception {
		boolean flag = false;
		DAO dao=DAO.getInstance();
		String xxdm=dao.getXxdm();
		String xh = shgcxysbModel.getXh();
		String cflbdm = shgcxysbModel.getCflb();
		String cfyydm = shgcxysbModel.getCfyy();
		String xxyj=shgcxysbModel.getXxyj();
		String xyyj=shgcxysbModel.getXyyj();
		String xgcyj=shgcxysbModel.getXgcyj();
		String bzryj=shgcxysbModel.getBzryj();
		String jtwjsy = DealString.toGBK(shgcxysbModel.getJtwjsy());
		String zacfqk = DealString.toGBK(shgcxysbModel.getZacfqk());
		String qtcfqk = DealString.toGBK(shgcxysbModel.getQtcfqk());
		String bz = DealString.toGBK(shgcxysbModel.getBz());
		String sql="";
		String[]input=null;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
			sql = "insert into wjcfb (xh,xn,nd,cflb,cfyy,jtwjsy,zacfqk,qtcfqk,bz,xyyj,xxyj,bzryj,xgcyj,sbsj,xxsh,xq) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,'δ���',?)";
			input=new String[] { xh, xn, nd, cflbdm, cfyydm,jtwjsy, zacfqk,qtcfqk, bz,xyyj,xxyj,bzryj,xgcyj,shgcxysbModel.getSbsj(),shgcxysbModel.getXq()};
		}else{
			sql = "insert into wjcfb (xh,xn,nd,cflb,cfyy,jtwjsy,zacfqk,qtcfqk,bz,sbsj,xxsh,xq) values (?,?,?,?,?,?,?,?,?,?,'δ���',?)";
			input=new String[] { xh, xn, nd, cflbdm, cfyydm,jtwjsy, zacfqk, qtcfqk,bz, shgcxysbModel.getSbsj(),shgcxysbModel.getXq()};
		}
		flag = dao.runUpdate(sql, input);// ������Ϣ
		return flag;
	}

	/**
	 * ��ȡ��ǰѧ������б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getXnNdList() throws Exception {
		String[] xnndList = null;
		String sql = "select dqxn,dqnd from xtszb where rownum=1";
		xnndList = dao.getOneRs(sql, new String[] {},
				new String[] { "dqxn", "dqnd" });
		return xnndList;
	}

	/**
	 * ��ȡ��ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getSearchTitle() throws Exception {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] enCol = { "xh||xn||nd||sbsj", "bgcolor", "rownum", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" };
		String[] cnCol = { "����", "bgcolor", "�к�", "ѧ��", "���", "ѧ��", "����",
				"�༶����", "�������", "����ԭ��", "�걨ʱ��", "ѧУ���" };
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}// end for
		return result;
	}

	/**
	 * ��ȡ��˲�ѯ���
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getSearchResult(
			WjcfShgcXxshQryModel shgcxxshqryModel) throws Exception {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||sbsj ����,(case nvl(xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,rownum �к�,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,sbsj,xxsh from view_wjcf where sbsj is not null ";
		String[] opCol = new String[] { "����", "bgcolor", "�к�", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" };
		StringBuffer whereSql = getWhereSql(shgcxxshqryModel);
		result = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return result;
	}

	/**
	 * ��ȡѧУ��˵ĵ���ѧ����Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxShInfo(String pkVal) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_wjcf where xh||xn||nd||sbsj = ?";
		map = dao.getMapNotOut(sql, new String[] { pkVal });
		return map;
	}

	/**
	 * ��ȡWHERE��乫�÷���
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 */
	public StringBuffer getWhereSql(WjcfShgcXxshQryModel shgcxxshqryModel) {
		StringBuffer whereSql = new StringBuffer();
		String xn = shgcxxshqryModel.getXn();
		String nd = shgcxxshqryModel.getNd();
		String nj = shgcxxshqryModel.getNj();
		String xydm = shgcxxshqryModel.getXydm();
		String cflb = shgcxxshqryModel.getCflb();
//		String xm = shgcxxshqryModel.getXm();
		String zydm = shgcxxshqryModel.getZydm();
		String xh = shgcxxshqryModel.getXh();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}// end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(cflb)) {
			whereSql.append(" and cflb = ?");
			values.add(cflb);
		}// end if
		
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(DealString.toGBK(xh));
		}// end if
		if (!StringUtils.isNull(shgcxxshqryModel.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(shgcxxshqryModel.getXm()) + "%");
		}
		if (!StringUtils.isNull(shgcxxshqryModel.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(shgcxxshqryModel.getBjdm());
		}
		return whereSql;
	}

	/**
	 * Υ�ʹ���ѧУ��˱���
	 * 
	 * @param shgcxxshModel
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public boolean saveXxshInfo(WjcfShgcXxshModel shgcxxshModel, String pkVal)
			throws Exception {
		boolean flag = false;
		String yesNo = DealString.toGBK(shgcxxshModel.getYesNo());
		String jtwjsy = DealString.toGBK(shgcxxshModel.getJtwjsy());
		String xxclyj = DealString.toGBK(shgcxxshModel.getXxclyj());
		String sql = "update wjcfb set xxsh = ?,jtwjsy = ?,xxclyj = ? where xh||xn||nd||sbsj = ?";
		flag = dao.runUpdate(sql, new String[] { yesNo, jtwjsy, xxclyj, pkVal });
		return flag;
	}

	/**
	 * ����ɾ��Υ�ʹ�����Ϣ
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delxxshInfo(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = keys[i];// �õ�����
			sql = "delete from wjcfb where xh||xn||nd||sbsj = '" + whichxh
					+ "'";
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
	 * ͨ�������õ�ѧ����Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getwjcfysbInfo(String pkVal, String xh, String cflb, String cfyy)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.xb,a.jtwjsy,a.zacfqk,a.qtcfqk,a.bz,b.rxrq,b.sfzh from view_wjcf a,view_xsxxb b where a.xh=b.xh and a.xh||a.xn||a.nd||a.sbsj = ?";
		if (StringUtils.isNull(pkVal)) {
			sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.xb,a.rxrq,a.sfzh from view_xsxxb a where a.xh = ?";
			map = dao.getMapNotOut(sql, new String[]{xh});
		} else {
			map = dao.getMapNotOut(sql, new String[] { pkVal });
		}
		return map;
	}

	/**
	 * ���ں��ĺ�ά����ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getSearchTitle1()
			throws Exception {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] enCol = { "xh||xn||nd||sbsj", "bgcolor", "rownum", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "cfsj", "cfwh",
				"xxsh" };
		String[] cnCol = { "����", "bgcolor", "�к�", "ѧ��", "���", "ѧ��", "����",
				"�༶����", "�������", "����ԭ��", "�걨ʱ��", "��������", "�����ĺ�", "ѧ�������" };
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}// end for
		return result;
	}

	/**
	 * ���ں��ĺ�ά����ѯ���
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getSearchResult1(
			WjcfShgcXxshQryModel shgcxxshqryModel) throws Exception {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||sbsj ����,(case nvl(xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,rownum �к�,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,sbsj,cfsj,cfwh,xxsh from view_wjcf where sbsj is not null and xxsh = 'ͨ��'";
		String[] opCol = new String[] { "����", "bgcolor", "�к�", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "cfsj", "cfwh",
				"xxsh" };
		StringBuffer whereSql = getWhereSql(shgcxxshqryModel);
		result = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return result;
	}

	/**
	 * ���ں��ĺ�ά��
	 * 
	 * @param pkVal
	 * @param cfsj
	 * @param cfwh
	 * @return
	 * @throws Exception
	 */
	public boolean wjcfrqwhsh(String pkVal, String cfsj, String cfwh, String cflb, String cfyy)
			throws Exception {
		boolean flag = false;
		String xxdm = Base.xxdm;
		String sql = "";
		if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			sql = "update wjcfb set cfsj=?,cfwh=?,cflb=?,cfyy=? where xh||xn||nd||sbsj = ?";
			flag = dao.runUpdate(sql, new String[] { cfsj, cfwh,cflb,cfyy, pkVal });
		} else {
			sql = "update wjcfb set cfsj=?,cfwh=? where xh||xn||nd||sbsj = ?";
			flag = dao.runUpdate(sql, new String[] { cfsj, cfwh, pkVal });
		}
		return flag;
	}

	/**
	 * ��ȡ����ԭ��
	 * 
	 * @param cfyy
	 * @return
	 * @throws Exception
	 */
	public String getCfYy(String cfyy) throws Exception {
		String cfyydm = "";
		String sql = "select cfyydm,cfyymc from cfyydmb where cfyymc = ?";
		cfyydm = dao.getOneRs(sql, new String[] { cfyy }, "cfyydm");
		return cfyydm;
	}

	/**
	 * ������������ά����ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle2() throws Exception {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] enCol = { "xh||xn||nd||sbsj", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "cfsj", "cfwh" };
		String[] cnCol = { "����", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�������",
				"����ԭ��", "�걨ʱ��", "��������", "�����ĺ�" };
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}// end for
		return result;
	}

	/**
	 * ������������ά����ѯ���
	 * 
	 * @param shgcxxshqryModel
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult2(
			WjcfShgcXxshQryModel shgcxxshqryModel, String cfyydm)
			throws Exception {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||sbsj ����,rownum �к�,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,sbsj,cfsj,cfwh from view_wjcf where sbsj is not null and cfyymc='��������' and cfyy='"
				+ cfyydm + "'";
		String[] opCol = new String[] { "����", "�к�", "xn", "nd", "xh", "xm",
				"bjmc", "cflbmc", "cfyymc", "sbsj", "cfsj", "cfwh" };
		StringBuffer whereSql = getWhereSql(shgcxxshqryModel);
		result = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return result;
	}

	/**
	 * ͨ��PK��PKVAL��TABLENAME��ȡѧ����Ϣ���÷���
	 * 
	 * @param pk
	 * @param pkVal
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getxxinfo(String pk, String pkVal,
			String tableName) throws Exception { 
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select a.*,b.csrq,b.sfzh from " + tableName
				+ " a left join view_xsxxb b on a.xh=b.xh" + " where " + pk
				+ " = ?";
		map = dao.getMapNotOut(sql, new String[] { pkVal });
		return map;
	}

	/**
	 * ��ȡѧ������
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public String getxxNn(HashMap<String, String> rs) throws Exception {
		String nn = "";
		String csrq = rs.get("csrq");
		String sfzh = rs.get("sfzh");
		if (StringUtils.isNull(csrq)) {
			csrq = !StringUtils.isNull(sfzh) && sfzh.length() > 15 ? sfzh.substring(6, 14) : "";
		}
		if (!StringUtils.isNull(csrq)) {
			String sql = "select floatToInt((months_between(to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),to_date('"
					+ csrq + "','yyyy-mm-dd')))/12) nn from dual";
			nn = dao.getOneRs(sql, new String[] {},"nn" );// ��ȡ����
			
		}// end if
		return nn;
	}

	/**
	 * δͨ�������Ϣ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle3() throws Exception {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] enCol = { "xh||sbsj", "rownum", "xn", "nd", "xh", "xm",
				"bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" };
		String[] cnCol = { "����", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�������",
				"����ԭ��", "�걨ʱ��", "ѧУ���" };
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}// end for
		return result;
	}

	/**
	 * δͨ�������Ϣ��ѯ���
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult3(WjcfShgcXxshQryModel shgcxxshqryModel)
			throws Exception {
		List<String[]> result = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(shgcxxshqryModel);
		String[] colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd",
				"xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" };
		String sql = "select xh||sbsj ����,rownum �к�,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ " a.* from view_wjcf a where 1=1 "
				+ " and sbsj is not null and xxsh='��ͨ��' ";
		result = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		System.out.println(sql + whereSql);
		return result;
	}

	/**
	 * δͨ�������Ϣ����ɾ��
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteWtgWjxx(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = keys[i];// �õ�����
			sql = "delete from wjcfb where xh||sbsj = '" + whichxh + "'";
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
	 * ���÷���ͨ��PK��PKVAL��tableName��COLLIST��ȡ��ѯ����������
	 * 
	 * @param pk
	 * @param pkVal
	 * @param sql
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getFindResult(String pk, String pkVal,
			String tableName, String[] colList) throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		String sql = "select * from " + tableName + " where " + pk + " = '"
				+ pkVal + "'";
		String rs[] = dao.getOneRs(sql, new String[] {}, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				result.put(colList[i].toLowerCase(), rs[i]);
			}// end for
		}// end if
		return result;
	}

	// ��ȡ�ļ��ϴ��б�
	public List<HashMap<String, String>> getFileList(String pkValue) {
		pkValue = "%" + pkValue + "%";
		String sql = "select length(xh||cfwh||cfsj)len, cfwh,cflbmc,cfyymc,sssj,wjsclj from wjcf_xsssb where xh||cfwh||cfsj like ? and wjsclj is not null";
		List<HashMap<String, String>> rs = dao.getList(sql,
				new String[] { pkValue }, new String[] { "len", "cfwh",
						"cflbmc", "cfyymc", "sssj", "wjsclj" });
		return rs;
	}

	/**
	 * ͨ��INT PARAM �������ͬ���б�
	 */
	public List<HashMap<String, String>> getChkList1(int type) {
		String[] sl = null;
		if (type == 1) {
			sl = new String[] { "�ȴ�����", "������", "������" };
		}// end if
		if (type == 2) {
			sl = new String[] { "�ȴ�����", "�������", "���˳���", "���Ĵ���", "ά��ԭ����" };
		}// end if
		return dao.arrayToList(sl, sl);
	}

	/**
	 * ��������������Ϣ
	 * 
	 * @param shgcssslModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsSlXx(WjcfShgcSsSlModel shgcssslModel, String pkVal,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String slqk = DealString.toGBK(shgcssslModel.getSlqk());
		String slrq = shgcssslModel.getSlrq();
		String sltzs = DealString.toGBK(shgcssslModel.getSltzs());
		flag = StandardOperation.update("wjcf_xsssb", new String[] { "slqk",
				"slrq", "sltzs" }, new String[] { slqk, slrq, sltzs },
				"xh||cfwh||cfsj", pkVal, request);// ����STANDARDOPERATION��ķ������Զ�д����־
		return flag;
	}

	/**
	 * �������߾�����Ϣ
	 * 
	 * @param shgcssjdModel
	 * @param pkVal
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsJdXx(WjcfShgcSsJdModel shgcssjdModel, String pkVal,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String fcrq = shgcssjdModel.getFcrq();
		String mqzt = DealString.toGBK(shgcssjdModel.getMqzt());
		String csqk = DealString.toGBK(shgcssjdModel.getCsqk());
		String fcqk = DealString.toGBK(shgcssjdModel.getFcqk());
		String fctzs = DealString.toGBK(shgcssjdModel.getFctzs());
		String jdsj = shgcssjdModel.getJdsj();
		String jdwh = DealString.toGBK(shgcssjdModel.getJdwh());
		flag = StandardOperation.update("wjcf_xsssb", new String[] { "fcrq",
				"mqzt", "csqk", "fcqk", "fctzs" , "jdsj", "jdwh"}, new String[] { fcrq, mqzt,
				csqk, fcqk, fctzs, jdsj, jdwh }, "xh||cfwh||cfsj", pkVal, request);
		//�������ɹ������WJCFB��������Ϣ
		if (flag) {
			if (!StringUtils.isNull(jdwh)) {
				StandardOperation.update("wjcfb", new String[] { "cxsj", "cxwh",
				"ssjg" }, new String[] { jdsj, jdwh, mqzt },
				"xh||cfwh||cfsj", pkVal, request);
			}//end if
		}//end if
		return flag;
	}
	
	/**
	 * ����Υ�Ͳ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> kswjTitle() throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "bjmc",
				"xn", "xq", "cfyymc", "cfsj", "cfwh" };
		String[] cnList = new String[] { "pk", "�к�", "ѧ��", "����", "�༶����", "ѧ��",
				"ѧ��", "����ԭ��", "����ʱ��", "�����ĺ�" };
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> kswjQryRes(KswjModel model) throws Exception {
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select xh||xn||xq||sbsj pk,rownum,xh,xm,bjmc,xn,(select b.xqmc from xqdzb b where b.xqdm=view_kswjcf.xq) xq,cfyymc,cfsj,cfwh" +
				" from view_kswjcf where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xh", "xm", "bjmc",
				"xn", "xq", "cfyymc", "cfsj", "cfwh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public StringBuffer getWhereSql1(KswjModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getNd())) {
			whereSql.append(" and nd = ?");
			values.add(model.getNd());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getCfyy())) {
			whereSql.append(" and cfyy = ?");
			values.add(model.getCfyy());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		return whereSql;
	}
	
	/**
	 * ����Υ�ʹ���ԭ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKscfyyList() throws Exception {
		return dao.getList("select cfyydm, cfyymc from ks_cfyydmb", 
				new String[]{}, new String[]{"cfyydm", "cfyymc"});
	}
	
	/**
	 * ����Υ�ʹ�������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKscflbList() throws Exception {
		return dao.getList("select cflbdm, cflbmc from ks_cflbdmb", 
				new String[]{}, new String[]{"cflbdm", "cflbmc"});
	}
	
	public HashMap<String, String> getStuDetails(String xh) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where" +
				" xh=?", new String[]{xh});
	}
	
	public boolean savekswjInfo(KswjModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.insert("kswjcfb", new String[]{"XN","ND","XH","CFLB",
				"CFYY","CFSJ","CFWH","XQ","XYCLYJ","JTWJSY","ZACFQK","QTCFQK","XXCLYJ"},
				new String[] { model.getXn(),
				model.getNd(), model.getXh(), model.getCflb(), model.getCfyy(),
				model.getCfsj(), DealString.toGBK(model.getCfwh()), model.getXq(), 
				DealString.toGBK(model.getXyclyj()), DealString.toGBK(model.getJtwjsy()), 
				DealString.toGBK(model.getZacfqk()), DealString.toGBK(model.getQtcfqk()) ,DealString.toGBK(model.getXxclyj())}, request);
	}
	
	public String kswjDel(String[] keys, HttpServletRequest request) throws Exception {
		String sDel = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("kswjcfb", "xh||xn||xq||sbsj", keys[i], request);
			if (!bFlag) {//��¼ɾ��ʧ�ܵ�������
				sDel += (i+1);
				sDel += ",";
			}
		}
		return StringUtils.isNull(sDel) ? "" : sDel.substring(0, sDel.length() - 1);
	}
	
	public HashMap<String, String> kswjModi(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,cflb,cfyy,cfsj," +
						"cfwh,xq,xyclyj,jtwjsy,zacfqk,qtcfqk,xxclyj from view_kswjcf where xh||xn||xq||sbsj=?",
						new String[] { pkValue });
	}
	
	public boolean kswjModisave(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.update("kswjcfb", new String[]{"XN","ND","XH","CFLB",
				"CFYY","CFSJ","CFWH","XQ","XYCLYJ","JTWJSY","ZACFQK","QTCFQK", "XXCLYJ"}, 
				new String[]{model.getXn(),
				model.getNd(), model.getXh(), model.getCflb(), model.getCfyy(),
				model.getCfsj(), DealString.toGBK(model.getCfwh()), model.getXq(), 
				DealString.toGBK(model.getXyclyj()), DealString.toGBK(model.getJtwjsy()), 
				DealString.toGBK(model.getZacfqk()), DealString.toGBK(model.getQtcfqk()),
				DealString.toGBK(model.getXxclyj())}, "xh||xn||xq||sbsj", pkValue, request);
	}
	
	public List<HashMap<String, String>> kswjgzjyTitle() throws Exception {
		String[] enList = new String[]{"pk", "rownum", "xh", "xm", "xn", "xq", 
				"bjmc","cfyymc", "cfwh", "cfsj", "zt"};
		String[] cnList = new String[]{"pk", "�к�", "ѧ��", "����", "ѧ��", "ѧ��",
				"�༶", "����ԭ��", "�����ĺ�", "����ʱ��", "����״̬"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> kswjgzjyResult(KswjModel model) throws Exception {
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select xh||xn||xq||sbsj pk,rownum,xh,xm,xn,(select b.xqmc from " +
				"xqdzb b where b.xqdm=view_kswjcf.xq) xq,bjmc,cfyymc,cfwh,cfsj,(case when(months_between(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd'),to_date(cfsj,'yyyymmdd')))>=12 then 'pass' else 'nopass' end) zt " +
				"from view_kswjcf where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "xh", "xm", "xn", "xq", 
				"bjmc","cfyymc", "cfwh", "cfsj", "zt"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public HashMap<String, String> viewKswjgzjyxx(String pkValue) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,(select b.xqmc " +
				"from xqdzb b where b.xqdm=view_kswjcf.xq) xq,cfwh,cfsj,cflbmc,cfyymc," +
				"xsbx1,xsbx2,xsbx3,xsbx4,xyclyj from view_kswjcf where xh||xn||xq||sbsj=?", new String[]{pkValue});
	}
	
	public boolean kswjGzjysaveres(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.update("kswjcfb", new String[]{"xsbx1", "xsbx2", 
				"xsbx3", "xsbx4", "xyclyj" }, new String[] {
				DealString.toGBK(model.getXsbx1()),
				DealString.toGBK(model.getXsbx2()),
				DealString.toGBK(model.getXsbx3()),
				DealString.toGBK(model.getXsbx4()),
				DealString.toGBK(model.getXyclyj()) }, "xh||xn||xq||sbsj",
				pkValue, request);
	}
	
	public String wjshres(String[] keys, HttpServletRequest request, String ress) throws Exception {
		String res = "";
		ress = StringUtils.isNull(ress) ? "δ���" : (StringUtils.isEqual("tg", ress) ? "ͨ��" : "��ͨ��");
		for (String s :keys) {
			dao.runUpdate("update wjcfb set xxsh=? where xh||xn||nd||sbsj=?", new String[]{ress, s});
		}
		return res;
	}
	
	public String sjzy(String[] keys) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		for (String s : keys) {
			String sql = "";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				sql = "insert into wjcflsb (XN,ND,XH,CFLB,CFYY,CFSJ,CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ"
						+ " ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,"
						+ " CXJG,XYSH,XNDSH,XNDCLYJ) select XN,ND,XH,CFLB,CFYY,CFSJ,"
						+ " CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,"
						+ " XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,CXJG,XYSH,XNDSH,XNDCLYJ "
						+ " from wjcfb where xh||xn||nd||sbsj=?";
			} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
				sql = "insert into wjcflsb (XN,ND,XH,CFLB,CFYY,CFSJ,CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ"
					+ " ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,"
					+ " CXJG,XYSH,XNDSH,XNDCLYJ) select XN,ND,XH,CFLB,CFYY,CFSJ,"
					+ " CFWH,SSJG,BZ,XQ,CXCLWH,CXCLSJ,XYCLYJ,XXCLYJ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,"
					+ " XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,CXJG,XYSH,XNDSH,XNDCLYJ "
					+ " from wjcfb where xh||cfwh||cfsj=?";

			}else {
				sql = "insert into wjcflsb (XN,ND,XH,CFLB,CFYY,CFSJ,CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ"
						+ " ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,"
						+ " CXJG,XYSH,XNDSH,XNDCLYJ,ycflb,sfjw) select XN,ND,XH,CFLB,CFYY,CFSJ,"
						+ " CFWH,SSJG,BZ,XQ,CXWH,CXSJ,XYCLYJ,XXCLYJ,CFNX,KCSJ,XSBX1,XSBX2,XSBX3,"
						+ " XSBX4,XYYJ,SBSJ,XXSH,LXDH,WJSJ,JTWJSY,ZACFQK,QTCFQK,CXJG,XYSH,XNDSH,XNDCLYJ,ycflb,sfjw "
						+ " from wjcfb where xh||cfwh||cfsj=?";

			}
			boolean bFlag = dao.runUpdate(sql, new String[] { DealString.toGBK(s) });
			if (bFlag) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
						|| Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
					sql = "delete from wjcfb where xh||xn||nd||sbsj=?";
				} else {
					sql = "delete from wjcfb where xh||cfwh||cfsj=?";
				}
				dao.runUpdate(sql, new String[] { DealString.toGBK(s) });
			}
		}
		return "";
	}
	
	public HashMap<String, String> wtgview(String pkValue) throws Exception {
		return dao.getMapNotOut("select * from view_wjcf where xh||sbsj=?", new String[]{pkValue});
	}
	
	public static void main(String[] args) throws Exception {
		String[] keys = { "30422112522007-2008200820080505093921",
				"30308110922007-2008200820080505151901" };
		WjcfShgcDAO dao = new WjcfShgcDAO();
		String pk = dao.delxxshInfo(keys);
		System.out.println(pk);
	}
}
