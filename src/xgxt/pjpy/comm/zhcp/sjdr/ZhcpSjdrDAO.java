package xgxt.pjpy.comm.zhcp.sjdr;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mortbay.html.Page;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_���ݵ���_DAO��
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

public class ZhcpSjdrDAO extends PjpyCommDAO {

	/**
	 * ���Ʒ�»����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPdhpInfoList(ZhcpSjdrForm model, User user,
			List<HashMap<String, String>> xmList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		//�۲�����
		String zczq=model.getZczq();
		
		ArrayList<String> colList = new ArrayList<String>();
		colList.add("pk");
		colList.add("pjsj");
		colList.add("bjmc");
		colList.add("pfr");
		colList.add("bpfr");
		
		StringBuilder tableSql = new StringBuilder();
		tableSql.append("(");
		tableSql.append("select a.xn||a.xq||a.nd||a.pfr||a.bpfr pk,a.xn, a.xq, a.nd,");
		if("xn".equalsIgnoreCase(zczq)){//ѧ��
			tableSql.append("a.xn||'ѧ��' pjsj,");
		}else if("xq".equalsIgnoreCase(zczq)){//ѧ��
			tableSql.append("a.xn||'ѧ��'||(select d.xqmc from xqdzb d where d.xqdm = a.xq) pjsj,");
		}else{//���
			tableSql.append("a.nd||'���' pjsj,");
		}
		
		tableSql.append("b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm, ");
		tableSql.append("b.bjmc, a.pfr pfrxh,b.xm pfrxm,a.bpfr bpfrxh,c.xm bpfrxm, ");
		tableSql.append("b.xm||'('||a.pfr||')' pfr,c.xm||'('||a.bpfr||')' bpfr, ");
		tableSql.append("a.sftj,a.jsqr,a.sfdr ");
		if (xmList != null && xmList.size() > 0) {
			for (int i = 0; i < xmList.size(); i++) {
				HashMap<String, String> xmInfo = xmList.get(i);
				tableSql.append("," + xmInfo.get("xmdm")+" ");
				colList.add(xmInfo.get("xmdm"));
			}
		}
		tableSql.append("from xg_pjpy_pdbxhpb a ");
		tableSql.append("left join view_xsjbxx b on a.pfr = b.xh ");
		tableSql.append("left join view_xsjbxx c on a.bpfr = c.xh ");
		tableSql.append(")");
		
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		
		return getRsArrList(tableSql.toString(), query, inputV, colList.toArray(new String[]{}), "", model);
	}
	
	/**
	 * ����ۺϷ�ά���б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZhfwhList(ZhcpSjdrForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		// �۲�����
		String zczq = model.getZczq();
		// ������Ŀ
		String czxm = model.getCzxm();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		
		ArrayList<String> colList = new ArrayList<String>();
		colList.add("pk");
		colList.add("pjsj");
		colList.add("xh");
		colList.add("xm");
		//colList.add("xb");
		colList.add("nj");
		colList.add("bjmc");
		colList.add("xmdm");
		colList.add("xmmc");
		
		StringBuilder tableSql = new StringBuilder();
		tableSql.append("(");
		tableSql.append("select a.xn||a.xq||a.nd||a.xh pk,a.xn,a.xq,a.nd,");
		if("xn".equalsIgnoreCase(zczq)){//ѧ��
			tableSql.append("a.xn||'ѧ��' pjsj,");
		}else if("xq".equalsIgnoreCase(zczq)){//ѧ��
			tableSql.append("a.xn||'ѧ��'||(select d.xqmc from xqdzb d where d.xqdm = a.xq) pjsj,");
		}else{//���
			tableSql.append("a.nd||'���' pjsj,");
		}
		tableSql.append("a.xh,b.xm,");
		tableSql.append("b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,");
		tableSql.append(czxm);
		tableSql.append(" xmdm,");
		tableSql.append("'"+xmmc+"' ");
		tableSql.append(" xmmc,");
		tableSql.append("b.bjdm, b.bjmc from xg_pjpy_zhcpb a ");
		tableSql.append("left join ");
		
		if("ss".equalsIgnoreCase(model.getRyk())){
			tableSql.append(" view_xsjbxx ");
		}else{
			tableSql.append(model.getPjry_sql());
		}
		
		tableSql.append(" b on a.xh = b.xh ");
		tableSql.append(")");
		
		SearchService searchService = new SearchService();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		CommService commService = new CommService();
		ArrayList<String> inputList = new ArrayList<String>();

		if(inputV!=null && inputV.length>0){
			for(int i=0;i<inputV.length;i++){
				inputList.add(commService.unicode2Gbk(inputV[i]));
			}
		}
		
		// Ȩ�޿���
		HashMap<String, String> notCtrlStatus = new HashMap<String, String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		query += " order by bjdm,xh ";
		
		return getRsArrList(tableSql.toString(), query, inputList
				.toArray(new String[] {}), colList.toArray(new String[] {}),
				"", model);
	}
	
	/**
	 * �����ۺ�������ط���
	 * 
	 * @author ΰ�����
	 * @throws Exception 
	 */
	public Boolean saveZhcpfXgInfo(ZhcpSjdrForm model, User user)
			throws Exception {

		boolean flag = true;

		CommService commService = new CommService();

		//������Ŀ
		String czxm = model.getCzxm();
		//����
		String[] pk = model.getPk();
		//����
		String[] fs = model.getFs();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_zhcpb ");
		sql.append("set ");
		sql.append(czxm);
		sql.append("= ");
		sql.append("? ");
		sql.append("where xn||xq||nd||xh = ? ");

		List<String[]> params = new ArrayList<String[]>();
		String tableName = "xg_pjpy_zhcpb";

		if (fs != null && fs.length > 0) {
			
			for (int i = 0; i < fs.length; i++) {
				
				String[] value = new String[] { fs[i],
						commService.unicode2Gbk(pk[i]) };
				
				params.add(value);
			}

			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;

	}
	
	/**
	 * ͬ���ۺ�������ط���
	 * 
	 * @author ΰ�����
	 */
	public Boolean tbZhcpfXgInfo(ZhcpSjdrForm model, User user){

		boolean flag = true;

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		
		// �۲�����
		String zczq = jbszModel.getZczq();
		// ��Դ��
		String lyb = model.getLyb();
		// ��Դ��
		String czxm = model.getCzxm();
		// ѧ��
		String xn = jbszModel.getPjxn();
		// ѧ��
		String xq = jbszModel.getPjxq();
		// ���
		String nd = jbszModel.getPjnd();
			
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_zhcpb a ");
		sql.append("set ");
		sql.append(czxm);
		sql.append("= ");
		sql.append("(select b.fs from ");
		sql.append(lyb);
		sql.append(" b where 1 = 1 ");
		// �۲����ڣ�ѧ��
		sql.append("xn".equalsIgnoreCase(zczq) ? " and b.xn = '" + xn + "'" : "");
		sql.append("xq".equalsIgnoreCase(zczq) ? " and b.xn = '" + xn + "' and b.xq = '" + xq + "'" : "");
		sql.append("nd".equalsIgnoreCase(zczq) ? " and b.nd = '" + nd + "'" : "");
		sql.append(" and a.xh = b.xh ) where 1 = 1 ");
		// �۲����ڣ�ѧ��
		sql.append("xn".equalsIgnoreCase(zczq) ? " and a.xn = '" + xn + "'" : "");
		sql.append("xq".equalsIgnoreCase(zczq) ? " and a.xn = '" + xn + "' and a.xq = '" + xq + "'" : "");
		sql.append("nd".equalsIgnoreCase(zczq) ? " and a.nd = '" + nd + "'" : "");
		
		DAO dao = DAO.getInstance();
		
		try {
			flag = dao.runUpdate(sql.toString(), new String[]{});
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * ����赼��������Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getExpBmInfo(ZhcpSjdrForm model) {

		// ��ѯmodel
		SearchModel searchModel = model.getSearchModel();
		// �꼶
		String[] nj = searchModel.getSearch_tj_nj();
		// ѧԺ
		String[] xy = searchModel.getSearch_tj_xy();
		// רҵ
		String[] zy = searchModel.getSearch_tj_zy();
		// �༶
		String[] bj = searchModel.getSearch_tj_bj();
		// ������ʽ
		String dcxs = model.getDcxs();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct");
		sql.append("xy".equalsIgnoreCase(dcxs) ? " xydm " : " bjdm ");
		sql.append("bmdm,");
		sql.append("xy".equalsIgnoreCase(dcxs) ? " xymc " : " bjmc ");
		sql.append("bmmc ");
		sql.append("from view_njxyzybj ");
		sql.append("where 1 = 1 ");

		// �꼶����
		if (nj != null && nj.length > 0) {
			sql.append("and( ");
			for (int i = 0; i < nj.length; i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("nj = '" + nj[i] + "' ");
			}
			sql.append(") ");
		}

		// ѧԺ����
		if (xy != null && xy.length > 0) {
			sql.append("and( ");
			for (int i = 0; i < xy.length; i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("xydm = '" + xy[i] + "' ");
			}
			sql.append(") ");
		}

		// רҵ����
		if (zy != null && nj.length > 0) {
			sql.append("and( ");
			for (int i = 0; i < zy.length; i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("zydm = '" + zy[i] + "' ");
			}
			sql.append(") ");
		}

		// �༶����
		if (bj != null && bj.length > 0) {
			sql.append("and( ");
			for (int i = 0; i < bj.length; i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("bjdm = '" + bj[i] + "' ");
			}
			sql.append(") ");
		}

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "bmdm", "bmmc" });

		return list;
	}
	
	/**
	 * ����ۺϷ�ѧ���б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<String[]> getZhfxsList(ZhcpSjdrForm model, User user)
			throws Exception {
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// �۲�����
		String zczq = model.getZczq();
		// ѧ��
		String xn = jbszModel.getPjxn();
		// ѧ��
		String xq = jbszModel.getPjxq();
		// ���
		String nd = jbszModel.getPjnd();
		// ѧԺ����
		String xydm = model.getXydm();
		// �༶����
		String bjdm = model.getBjdm();
		
		ArrayList<String> colList = new ArrayList<String>();
		
		StringBuilder tableSql = new StringBuilder();
		tableSql.append("(");
		tableSql.append("select ");
		if("xn".equalsIgnoreCase(zczq)){//ѧ��
			tableSql.append("'" + xn + "' xn,");
			colList.add("xn");
		}else if("xq".equalsIgnoreCase(zczq)){//ѧ��
			tableSql.append("'" + xn + "' xn,");
			tableSql.append("(select b.xqmc from xqdzb b where b.xqdm = '"+xq+"') xq,");
			colList.add("xn");
			colList.add("xq");
		}else{//���
			tableSql.append("'" + nd + "' nd,");
			colList.add("nd");
		}
		tableSql.append("a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,");
		tableSql.append("a.xydm,a.zydm,a.bjdm,");
		tableSql.append("a.bjmc,'' zcxm from  ");
		if("ss".equalsIgnoreCase(model.getRyk())){
			
			tableSql.append(" view_xsjbxx ");
		}else{
			
			tableSql.append(model.getPjry_sql());
		}
		tableSql.append(" a ");
		tableSql.append(")");
		
		colList.add("xh");
		colList.add("xm");
		colList.add("xb");
		colList.add("nj");
		colList.add("xymc");
		colList.add("zymc");
		colList.add("bjmc");
		colList.add("zcxm");
		
		String query = " where 1 = 1 ";
		query += Base.isNull(xydm) ? "" : "and xydm = '" + xydm + "'";
		query += Base.isNull(bjdm) ? "" : "and bjdm = '" + bjdm + "'";

		return CommonQueryDAO.commonQueryNotFy(tableSql.toString(), query,
				new String[] {}, colList.toArray(new String[] {}), "", model);
	}
	
	/**
	 * ����ģ�����ɼ�¼
	 * @param fileList
	 * @return
	 * @throws Exception 
	 */
	public boolean saveStencilLog(List<String[]> fileList) throws Exception{
		
		DAO dao = DAO.getInstance();
		StringBuilder delSQL = new StringBuilder();
		delSQL.append("delete from pjpy_zhcp_mmccb where ");
		for (int i = 0 ; i < fileList.size() ; i++){
			delSQL.append("wjmc = '")
			      .append(fileList.get(i)[0])
			      .append("'");
			if (i != fileList.size()-1){
				delSQL.append(" or ");
			}
		}
		
		boolean flg = dao.runUpdate(delSQL.toString(), new String[]{});
		
		if (flg) {
		
			String sql = "insert into pjpy_zhcp_mmccb (wjmc,bmdm,bmlx,cjr) values(?,?,?,?)";
			try {
				int[] result = dao.runBatch(sql, fileList);
				return dao.checkBatchResult(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
