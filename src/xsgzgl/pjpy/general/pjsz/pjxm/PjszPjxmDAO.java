package xsgzgl.pjpy.general.pjsz.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_������Ŀ_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjszPjxmDAO extends CommDAO {
	
	// ==================ִ�в�ѯ���� begin==============================
	/**
	 * ��ý����(������Ŀ)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPjszPjxmList(PjpyGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// �û�����
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ====================��������===================================
		// �Ƿ�����
//		String[] sfqy = model.getSearchModel().getSearch_tj_sf();
//		model.getSearchModel().setSearch_tj_sf(null);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.xmdm,a.xmdm pk,a.xmlx,a.xmxz,a.xmlxmc,a.xmxzmc, ");
		tableSql.append("case when length(a.xmmc)>10 then substr(a.xmmc,0,10)||'...' else a.xmmc end xmmc, xmmc xmmcxx,");
		tableSql.append("decode(a.tjsz,'0','δ����','������') tjsz, ");
		tableSql.append("decode(a.rssz,'0','δ����','������') rssz, ");
		tableSql.append("decode(a.jdsz,'0','δ����','������') jdsz, ");
		tableSql.append("decode(a.xmsy,'0','δ����','������') xmsy, ");
		tableSql.append("decode(a.sqzt,'0','�ر�����','��������') sqzt, ");
		tableSql.append("decode(a.shzt,'0','�ر����','�������') shzt, sfysq,  ");
		tableSql.append("case when a.sfqy = 'yes' then '��' ");
		tableSql.append("when a.sfqy = 'no' then '��' else a.sfqy end sfqy ");
		tableSql.append("from (");
		tableSql.append("select a.xmdm,a.xmmc,a.sfqy,a.xmlx, ");
		tableSql.append("decode(a.xmlx,'01','��ѧ��','�����ƺ�') xmlxmc,");
		tableSql.append("(select b.xzmc from xg_pjpy_xmxzb b where a.xmxz=b.xzdm) xmxzmc,xmxz, ");
		tableSql.append("case when h.xmdm is null then '��' else '��' end sfysq, ");
		tableSql.append("nvl(b.num,'0') tjsz, ");
		tableSql.append("nvl(c.num,'0') rssz, ");
		tableSql.append("nvl(d.num,'0') jdsz, ");
		tableSql.append("nvl(e.num,'0') xmsy, ");
		tableSql.append("nvl(f.num,'0') sqzt, ");
		tableSql.append("nvl(g.num,'0') shzt ");
		//tableSql.append("case when c.cpzmc is null then '��' else '��' end sfsz ");
		tableSql.append("from xg_pjpy_pjxmwhb a ");
		// ��������
		tableSql.append("left join ");
		tableSql.append("(select xmdm,count(1) num from xg_pjpy_tjszb group by xmdm) b ");
		tableSql.append("on a.xmdm = b.xmdm ");
		// ��������
		tableSql.append("left join ");
		tableSql.append("(select xmdm,count(1) num from xg_pjpy_rsszb where qdrs is not null group by xmdm) c ");
		tableSql.append("on a.xmdm = c.xmdm ");
		// �������
		tableSql.append("left join ");
		tableSql.append("(select xmdm,count(1) num from xg_pjpy_jdszb group by xmdm) d ");
		tableSql.append("on a.xmdm = d.xmdm ");
		// ��Ŀ˳��
		tableSql.append("left join ");
		tableSql.append("(select xmdm,count(1) num from xg_pjpy_pjfwtzb group by xmdm) e ");
		tableSql.append("on a.xmdm = e.xmdm ");
		// ʱ������
//		tableSql.append("left join ");
//		tableSql.append("(select xmdm,count(1) num from xg_pjpy_sjszb ");
//		tableSql.append("where pjxn='" + pjxn + "' ");
//		tableSql.append("and pjxq='" + pjxq + "' ");
//		tableSql.append("group by xmdm) f ");
//		tableSql.append("on a.xmdm = f.xmdm ");
		// ����״̬
		tableSql.append("left join ");
		tableSql.append("(select xmdm,count(1) num ");
		tableSql.append("from xg_pjpy_sjszb ");
		tableSql.append("where pjxn='" + pjxn + "' ");
		tableSql.append("and pjxq='" + pjxq + "' ");
		tableSql.append("and (to_char(sysdate,'yyyyMMdd') >= sqkssj or sqkssj is null) ");
		tableSql.append("and (to_char(sysdate,'yyyyMMdd') <= sqjssj or sqjssj is null) ");
		tableSql.append("and sqkzkg = 'yes' ");
		tableSql.append("group by xmdm) f ");
		tableSql.append("on a.xmdm = f.xmdm ");
		// ���״̬
		tableSql.append("left join ");
		tableSql.append("(select xmdm,count(1) num ");
		tableSql.append("from xg_pjpy_sjszb ");
		tableSql.append("where pjxn='" + pjxn + "' ");
		tableSql.append("and pjxq='" + pjxq + "' ");
		tableSql.append("and (to_char(sysdate,'yyyyMMdd') >= shkssj or shkssj is null) ");
		tableSql.append("and (to_char(sysdate,'yyyyMMdd') <= shjssj or shjssj is null) ");
		tableSql.append("and shkzkg = 'yes' ");
		tableSql.append("group by xmdm) g ");
		tableSql.append("on a.xmdm = g.xmdm ");
		
//		 ��������
		tableSql.append(" left join ");
		tableSql.append(" (select xmdm,pjxn,pjxq,pjnd from xg_pjpy_pjxmsqb  ");
		tableSql.append(" where pjxn='" + pjxn + "' and pjxq='" + pjxq + "' and pjnd='" + pjnd + "' ");
		tableSql.append(" group by xmdm,pjxn,pjxq,pjnd ");
		tableSql.append(" ) h on a.xmdm = h.xmdm and a.pjxn=h.pjxn and a.pjxq=h.pjxq and a.pjnd=h.pjnd ");
		
		
		
		
//		if(sfqy!=null && sfqy.length>0){
//			tableSql.append("and ( ");
//			for(int i=0;i<sfqy.length;i++){
//				if ("��".equalsIgnoreCase(sfqy[i])) {
//					tableSql.append(" a.sfqy = '��' or a.sfqy = 'yes' ");
//				}else{
//					tableSql.append(" a.sfqy = '��' or a.sfqy = 'no' ");
//				}
//			}
//			tableSql.append(") ");
//		}
		tableSql.append("where 1=1 ");
		tableSql.append("and a.pjxn = '" + pjxn + "' ");
		tableSql.append("and a.pjxq = '" + pjxq + "' ");
		tableSql.append("and a.pjnd = '" + pjnd + "' ");
		
		tableSql.append(" order by a.xmdm ) a ");
		tableSql.append(") a ");
	
		tableSql.append(query);
		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "xmmc", "xmlxmc", "xmxzmc", "sfysq",  "tjsz",
				"rssz", "jdsz", "sqzt", "shzt","xmmcxx" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/**
	 * ��ȡ����������������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getShlcList() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select lcid,lcmc,max(gzgw)gzgw from ( ");
		sql.append(" select lcid,lcmc,to_char(wm_concat (gzgw)over( ");
		sql.append(" partition by  lcid,lcmc order by lcid, xh)) as gzgw from ( ");
		sql.append(" select a.id lcid,a.mc lcmc,b.mc gzgw,c.xh from ");
		sql.append(" xg_xtwh_splc a,xg_xtwh_spgw b,xg_xtwh_spbz c ");
		sql
				.append(" where a.id=c.splc and b.id=c.spgw and LOWER(a.djlx)='pjpy'	) a )group by lcid,lcmc ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "lcid", "lcmc", "gzgw" });

		return list;
	}
	
	/**
	 * ��ȡ������̸�λ��Ϣ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getGwxxList(String lcid) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select c.id gwdm, c.mc gwmc ");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c ");
		sql.append(" where a.id = b.splc ");
		sql.append(" and b.spgw = c.id ");
		sql.append(" and a.id = ? ");
		sql.append(" order by to_number(xh) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { lcid }, new String[] { "gwdm", "gwmc" });

		return list;
	}
	
	/**
	 * ���������Ŀ�����Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getPjxmInfo(String xmdm) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm,a.xmmc,a.xmsm,a.xmlx,a.xmxz, ");
		sql.append(" a.sqfs,a.xmje,a.rssz,a.kzfw,a.sfsh, ");
		sql.append(" a.lcid,a.rskz,a.jdkz,a.xmsy,a.tsrq ");
		sql.append(" from xg_pjpy_pjxmwhb a ");
		sql.append(" where a.xmdm=? ");
		sql.append(" and a.pjxn=? ");
		sql.append(" and a.pjxq=? ");

		HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
				new String[] { xmdm, pjxn, pjxq });

		return map;
	}
	
	/**
	 * ��ȡ��Ҫ��ʦ�ϱ�����Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getLssbXmList() {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm, a.xmmc ");
		sql.append(" from xg_pjpy_pjxmwhb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.pjxn=? ");
		sql.append(" and a.pjxq=? ");
		sql.append(" and a.sqfs='lssb' ");
		sql.append(" order by a.xmdm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq }, new String[] { "xmdm", "xmmc" });

		return list;
	}
	// ==================ִ�в�ѯ���� end==============================
	
	// ==================ִ���������� =============================
	/**
	 * �������ݣ�xg_pjpy_pjxmwhb:������Ŀά����
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertPjxmwhb(String xmmc, String xmlx, String xmxz,
			String sqfs, String xmje, String lcid, String rssz, String xssx,
			String sfsh, String sfqy, String xmsm, String rskz, String jdkz,
			String xmsy, String kzfw, String tsrq, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		
		// ����
		String tableName = "xg_pjpy_pjxmwhb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_pjxmwhb ");
		sql.append("(");
		sql.append("pjxn,pjxq,pjnd,");
		sql.append("xmmc,xmlx,xmxz,sqfs,xmje,lcid,rssz,");
		sql.append("xssx,sfsh,sfqy,xmsm,rskz,jdkz,xmsy,kzfw,tsrq");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { pjxn, pjxq, pjnd, xmmc, xmlx, xmxz,
				sqfs, xmje, lcid, rssz, xssx, sfsh, sfqy, xmsm, rskz, jdkz,
				xmsy, kzfw, tsrq };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * �޸ģ�xg_pjpy_pjxmwhb:������Ŀά����
	 * @author qlj
	 * @throws Exception
	 */
	public Boolean updatePjxmwhb(String xmdm,String xmmc, String xmlx, String xmxz,
			String sqfs, String xmje, String lcid, String rssz, String xssx,
			String sfsh, String sfqy, String xmsm, String rskz, String jdkz,
			String xmsy, String kzfw, String tsrq, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		
		// ����
		String tableName = "xg_pjpy_pjxmwhb";

		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_pjpy_pjxmwhb ");
		sql.append(" set xmmc=?,xmlx=?,xmxz=?,sqfs=?,xmje=?,lcid=?,rssz=?,");

		if (!Base.isNull(rskz)) {
			sql.append(" rskz='" + rskz + "',");
		}

		if (!Base.isNull(jdkz)) {
			sql.append(" jdkz='" + jdkz + "',");
		}

		sql.append(" xssx=?,sfsh=?,sfqy=?,xmsm=?,xmsy=?,kzfw=?,tsrq=?");
		sql.append(" where  pjxn=? and pjxq=? and pjnd=? and xmdm=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { xmmc, xmlx, xmxz, sqfs, xmje, lcid,
				rssz, xssx, sfsh, sfqy, xmsm, xmsy, kzfw, tsrq, pjxn, pjxq,
				pjnd, xmdm };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * ɾ�����ݣ�xg_pjpy_pjxmwhb:������Ŀά����
	 * @author qlj
	 * @throws Exception
	 */
	public Boolean deletePjxmwhb(String[] pkValue, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		
		List<String>inputV=new ArrayList<String>();
		
		// ����
		String tableName = "xg_pjpy_pjxmwhb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete xg_pjpy_pjxmwhb ");
		sql.append(" where  pjxn=? and pjxq=? and pjnd=? and(  ");
		
		inputV.add(pjxn);
		inputV.add(pjxq);
		inputV.add(pjnd);
		for(int i=0;i<pkValue.length;i++){
			
			
			if(i!=0){
				sql.append(" or ");
			}
			sql.append(" xmdm=? ");
			inputV.add(pkValue[i]);
		}
		sql.append(")");

		List<String[]> params = new ArrayList<String[]>();
		
		params.add(inputV.toArray(new String[]{}));

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	

	/**
	 * ɾ�����ݣ�xg_pjpy_pjxmwhb:������Ŀά����
	 * @author qlj
	 * @throws Exception
	 */
	public Boolean checkSfsq(String[] pkValue, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		
		List<String>inputV=new ArrayList<String>();
		
		// ����
		String tableName = "xg_pjpy_pjxmwhb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete xg_pjpy_pjxmwhb ");
		sql.append(" where  pjxn=? and pjxq=? and pjnd=? and(  ");
		
		inputV.add(pjxn);
		inputV.add(pjxq);
		inputV.add(pjnd);
		for(int i=0;i<pkValue.length;i++){
			sql.append(" xmdm=? ");
			if(i!=0){
				sql.append(" or ");
			}
			
			inputV.add(pkValue[i]);
		}
		sql.append(")");

		List<String[]> params = new ArrayList<String[]>();
		
		params.add(inputV.toArray(new String[]{}));

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * ��ȡ���������б�
	 * @author qlj
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getRsszList(String xmdm, User user) throws Exception {

		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from xg_pjpy_rsszb  ");
		sql.append(" where xmdm =? ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * ��ȡ���������б�
	 * @author qlj
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getRsszList(String[] xmdm, User user) throws Exception {

		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select b.xmdm,b.xmmc from xg_pjpy_rsszb a,xg_pjpy_pjxmwhb b   ");
		sql.append(" where a.xmdm=b.xmdm and a.xmdm in ( ");
		
		for(int i=0;i<xmdm.length;i++){
			sql.append(" ? ");
			
			if(i!=0){
				
				sql.append(",");
			}
		}
		
		sql.append(" ) ");
		
		return dao.getListNotOut(sql.toString(), xmdm);
	}
	// ==================ִ���������� end==============================


	/**
	 * ������Ŀ�������������λ
	 */
	public String[] getSpgwByXmdm(String xmdm) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select spgw from xg_xtwh_spbz where splc=(");
		sql.append("select lcid from xg_pjpy_pjxmwhb where xmdm=?");
		sql.append(") order by xh");
		
		return DAO.getInstance().getArray(sql.toString(), new String[]{xmdm}, "spgw");
	}


}
