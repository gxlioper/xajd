package xgxt.xsgygl.bjlh.ssfp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.bjlh.BjlhGyglDAO;

public class SsfpDAO extends BjlhGyglDAO {
	DAO dao = DAO.getInstance();
	
	public static final String TWDM = "0202";//��ί���Ŵ���
	public static final String TYDM = "0405";//������ѧ�����Ŵ���
	public static final String KYDM = "0110";//���в��Ŵ���
	public static final String CJDM = "0117";//�ɽ̲��Ŵ���
	
	//ѧԺ�б�����SQL
	 String QUERY_XYLIST = "select distinct xydm,xymc,(case when xymc like ? then 4 when xymc like ? then 3 when xymc like ? then 2 when xymc like ? then 1 else 0 end) mc from view_njxyzybj order by mc desc"; 
	//��ίδ����ѧ������SQL
	String QUERY_TW_WFPXSZS = "select nvl(count(xh),'0') rs,xb from (select twsxh xh,(select xb from view_xsjbxx b where a.twsxh=b.xh) xb from bjlh_twxsb a where sftws='��') a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh and c.zzbj='yes' and c.lx='����') group by xb";
	//����δ����ѧ������SQL
	String QUERY_TY_WFPXSZS = "select nvl(count(xh),'0') rs,xb from (select tysxh xh,(select xb from view_xsjbxx b where a.tysxh=b.xh) xb from bjlh_tyxsb a where sftys='��') a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh and c.zzbj='yes' and c.lx='����') group by xb";
	//����δ����ѧ������SQL
	String QUERY_KY_WFPXSZS = "select nvl(count(xh),'0') rs,xb from view_bjlh_yjsxx a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh  and c.lx='�о���' and c.zzbj='yes') group by xb";
	//�ɽ�δ����ѧ������SQL
	String QUERY_CJ_WFPXSZS = "select nvl(count(xh),'0') rs,xb from view_bjlh_cjsxx a where not exists(select 1 from bjlh_xszsxxb c where a.xh=c.xh  and c.lx='�ɽ���' and c.zzbj='yes') group by xb";;
	//�ѻ��ִ�λ����SQL
	String QUERY_CWZS = "select sum(cws) rs,xbxd from (select a.xydm,a.ssbh,a.cws,b.xbxd from ssfpb a,view_bjlh_ssxx b where a.ssbh=b.pk and b.fbbj ='һ��') where 1=1";
	//δ���������б�SQL
	String QUERY_WHFSS = "select pk||'/'||cws dm,ldmc||'/'||cs||'/'||qsh||'/'||cws mc from view_bjlh_ssxx a where fbbj='һ��' and not exists (select 1 from ssfpb b where a.pk=b.ssbh) ";
	//�ѻ��������б�SQL
	String QUERY_YHFSS = "select xydm||'/'||ssbh||'/'||cws dm,xymc||'/'||ldmc||'/'||cs||'/'||qsh||'/'||cws mc from (select distinct a.xydm,(case a.xydm when '0202' then '��ί' when '0405' then '������ѧ��' when '0110' then '���д�' when '0117' then '���˽�����' else (select xymc from view_njxyzybj b where a.xydm=b.xydm and rownum=1) end) xymc,a.ssbh,a.cws,b.xqdm,b.xqmc,b.lddm,b.ldmc,b.cs,b.qsh from ssfpb a left join view_bjlh_ssxx b on a.ssbh=b.pk and b.fbbj='һ��')";
	
	//��������ѯSQL
	StringBuffer QUERY_SSFP = new StringBuffer("select ssbh||a.xydm pk,rownum r,a.*,(select xqmc from dm_zju_xq b where a.xqdm=b.dm) xqmc from (")
	                                  .append("select a.*,(select xqdm from sslddmb b where a.lddm=b.lddm) xqdm,")
	                                  .append("(select xbxd from sslddmb b where a.lddm=b.lddm) xb,")
	                                  .append("(select ldmc from sslddmb b where a.lddm=b.lddm) ldmc,")
	                                  .append("(case when xydm='0202' then '��ί' when xydm='0405' then '������ѧ��' when xydm='0110' then '���д�' when xydm='0117' then '���˽�����' else (select xymc from view_njxyzybj b where a.xydm=b.xydm and rownum=1) end) xymc from (")
	                                  .append("select xydm,ssbh,cws,(select lddm from bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) lddm,")
	                                  .append("(select cs from bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) cs,")
	                                  .append("(select qsh from bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) qsh from ssfpb a) a) a"); 
	/**
	 * ����������鷵������б�,Ӣ����ǰ,�����ں�
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>> getList(String[] en, String[] cn) {
		return dao.arrayToList(en, cn);
	}

	/**
	 * ��ѯѧԺ�б�,������,��Ϣ,�Զ���,����ѧԺ������ǰ��
	 * @return
	 */
	public List<HashMap<String, String>> getXyList() {
		return dao.getList(QUERY_XYLIST, new String[] { "����ѧԺ%", "��ϢѧԺ%",
				"�Զ���ѧԺ%", "����ѧԺ%" }, new String[] { "xydm", "xymc" });
	}

	/**
	 * δ����ѧ����������������Ե���ѧ��������
	 * @param xydm
	 * @param fpbj ȫ���ƣ���ί��������ѧ�������д������˽����� Ĭ��Ϊȫ����
	 * @return
	 */
	public String[] getCwFpZsData(String xydm, String fpbj) {
		String[] data = new String[] { "0", "0", "0" };
		List<String[]> rs = null;
		String sql = "";
		StringBuffer querry = new StringBuffer();
		if (TWDM.equalsIgnoreCase(fpbj)) {//�����Ϊ��ί
			sql = QUERY_TW_WFPXSZS;
		} else if (TYDM.equalsIgnoreCase(fpbj)) {//������Ϊ������ѧ��
			sql = QUERY_TY_WFPXSZS;
		} else if (KYDM.equalsIgnoreCase(fpbj)) {//������Ϊ���д�
			sql = QUERY_KY_WFPXSZS;
		} else if (CJDM.equalsIgnoreCase(fpbj)) {//������Ϊ���˽�����
			sql = QUERY_CJ_WFPXSZS;
		} else {//ȫ���Ʊ�����
			querry.append(Base.isNull(xydm) ? " where 1=1 " : " where xydm='"
					+ xydm + "' ");
			sql = "select xb,nvl(count(xh),'0') rs from view_xsjbxx a "
					+ querry.toString()
					+ " and not exists (select 1 from (select twsxh xh from bjlh_twxsb where sftws='��' union select tysxh xh from  bjlh_tyxsb where sftys='��') b where a.xh=b.xh)"
					+ " and not exists (select 1 from bjlh_xszsxxb c where a.xh=c.xh and c.lx='����' and c.zzbj='yes') group by xb";
		}

		rs = dao.rsToVator(sql, new String[] {}, new String[] { "xb", "rs" });
		int boy = 0;
		int girl = 0;
		if (rs != null) {
			for (int i = 0; i < rs.size(); i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 2) {
					if ("��".equalsIgnoreCase(oneData[0])) {//����δ����������
						data[1] = oneData[1];
						boy = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
					} else if ("Ů".equalsIgnoreCase(oneData[0])) {//Ů��δ����������
						girl = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[2] = oneData[1];
					}
				}
			}
		}
		//δ����������,��,Ů��֮��
		data[0] = (boy + girl) > 0 ? String.valueOf(boy + girl) : "0";
		return data;
	}

	/**
	 * �ѻ��ִ�λ������������Ե��Ǵ�λ������ 
	 * @param xydm
	 * @param fpbj ȫ���ƣ���ί��������ѧ�������д������˽�����  Ĭ��Ϊȫ����
	 * @return
	 */
	public String[] getSsFpYhfcws(String xydm, String fpbj) {
		StringBuffer query = new StringBuffer();
		String[] data = new String[] { "0", "0", "0", "0" };
		if (TWDM.equalsIgnoreCase(fpbj)) {// ������Ϊ��ί
			query.append(" and xydm='");
			query.append(TWDM);
			query.append("'");
		} else if (TYDM.equalsIgnoreCase(fpbj)) {// ������Ϊ������ѧ��
			query.append(" and xydm='");
			query.append(TYDM);
			query.append("'");
		} else if (KYDM.equalsIgnoreCase(fpbj)) {// ������Ϊ���д�
			query.append(" and xydm='");
			query.append(KYDM);
			query.append("'");
		} else if (CJDM.equalsIgnoreCase(fpbj)) {// ������Ϊ���˽�����
			query.append(" and xydm='");
			query.append(CJDM);
			query.append("'");
		} else {//ȫ���Ʊ�����
			if (StringUtils.isNotNull(xydm)) {//ѧԺ���벻Ϊ��ʱ
				query.append(" and xydm='");
				query.append(xydm);
				query.append("'");
			} else {//ѧԺδ��ʱ,ֻ���ȫ���Ʊ���������
				query.append(" and xydm not in ('");
				query.append(TWDM);
				query.append("','");
				query.append(TYDM);
				query.append("','");
				query.append(KYDM);
				query.append("','");
				query.append(CJDM);
				query.append("') ");
			}
		}
		query.append(" group by xbxd");

		List<String[]> rs = dao.rsToVator(QUERY_CWZS + query.toString(),
				new String[] {}, new String[] { "xbxd", "rs" });
		int boy = 0;
		int girl = 0;
		int hh = 0;
		if (rs != null) {
			for (int i = 0; i < rs.size(); i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 2) {
					if ("��".equalsIgnoreCase(oneData[0])) {//�������ᴲλ��
						data[1] = oneData[1];
						boy = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
					} else if ("Ů".equalsIgnoreCase(oneData[0])) {//Ů�����ᴲλ��
						girl = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[2] = oneData[1];
					} else if ("���".equalsIgnoreCase(oneData[0])) {//������ᴲλ��
						hh = StringUtils.isNotNull(oneData[1]) ? Integer
								.parseInt(oneData[1]) : 0;
						data[3] = oneData[1];
					}
				}
			}
		}
		//�ѻ��ִ�λ����
		data[0] = (boy + girl + hh) > 0 ? String.valueOf(boy + girl + hh) : "0";
		return data;
	}

	/**
	 * δ���������б�
	 * 
	 * @param xqdm
	 * @param xb
	 * @param lddm
	 * @param cs
	 * @return
	 */
	public List<HashMap<String, String>> getSsFpSsXxList(String xqdm,
			String xb, String lddm, String cs) {
		List<HashMap<String, String>> ssList = null;
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(lddm) ? " and 1=2 " : " and lddm = '" + lddm
				+ "' ");
		querry
				.append(Base.isNull(cs) ? " and 1=1 " : " and cs = '" + cs
						+ "' ");
		querry.append(Base.isNull(xqdm) ? " and 1=1 " : " and xqdm = '" + xqdm
				+ "' ");
		querry.append(Base.isNull(xb) ? " and 1=1 " : " and xbxd = '" + xb
				+ "' ");
		querry.append(" order by xqdm,lddm,cs,qsh");
		ssList = dao.getList(QUERY_WHFSS + querry.toString(), new String[] {},
				new String[] { "dm", "mc" });
		return ssList;
	}

	/**
	 * �����ѻ���������Ϣ�б�
	 * 
	 * @param lddm
	 *            ¥������
	 * @param xqdm
	 *            У������
	 * @param xydm
	 *            ѧԺ����
	 * @param bjdm
	 *            �༶����
	 * @return
	 */
	public List<HashMap<String, String>> getSsFpFpSsXxList(String lddm,
			String cs, String xydm, String fpbj) {
		xydm = TWDM.equalsIgnoreCase(fpbj) ? TWDM : (TYDM
				.equalsIgnoreCase(fpbj) ? TYDM
				: (KYDM.equalsIgnoreCase(fpbj) ? KYDM : (CJDM
						.equalsIgnoreCase(fpbj) ? CJDM : xydm)));
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(lddm) ? " and 1=2 " : " and lddm = '" + lddm
				+ "' ");
		querry.append(Base.isNull(xydm) ? " and 1=1 " : " and xydm = '" + xydm
				+ "' ");
		querry
				.append(Base.isNull(cs) ? " and 1=1 " : " and cs = '" + cs
						+ "' ");
		if ("qrz".equalsIgnoreCase(fpbj) && StringUtils.isNull(xydm)) {
			querry.append(" and xydm not in ('");
			querry.append(TWDM);
			querry.append("','");
			querry.append(TYDM);
			querry.append("','");
			querry.append(KYDM);
			querry.append("','");
			querry.append(CJDM);
			querry.append("') ");
		}
		querry.append(" order by xydm,lddm,cs,qsh");
		return dao.getList(QUERY_YHFSS + querry.toString(), new String[] {},
				new String[] { "dm", "mc" });
	}

	/**
	 * �������������Ϣ
	 * @param oldMappingItems  Ҫɾ�����ѻ��������б��ַ���
	 * @param newMappingItems  �ѻ��������б��ַ���
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsfpxx(String oldMappingItems, String newMappingItems)
			throws Exception {
		String[] delSql = null;
		String[] inserSql = null;
		if (((oldMappingItems != null) && !oldMappingItems.equals(""))) {
			//ɾ�����ƴ��
			String delItems1[] = oldMappingItems.split(",");
			int delNum = delItems1 != null ? delItems1.length : 0;
			String delItems2[][] = new String[delNum][];
			delSql = new String[delNum];
			for (int i = 0; i < delNum; i++) {
				delItems2[i] = delItems1[i].split("/");
				delSql[i] = "delete from ssfpb where  xydm='" + delItems2[i][0]
						+ "'" + " and ssbh= " + "'" + delItems2[i][1] + "'";
			}
		} else {
			delSql = new String[1];
			delSql[0] = "delete from ssfpb where 1=2";
		}
		if (((newMappingItems != null) && !newMappingItems.equals(""))) {
			//�������ƴ��
			String inserItems1[] = newMappingItems.split(",");
			int inNum = inserItems1 != null ? inserItems1.length : 0;
			String inserItems2[][] = new String[inNum][];
			inserSql = new String[inNum];
			for (int i = 0; i < inNum; i++) {
				inserItems2[i] = inserItems1[i].split("/");
				inserSql[i] = "insert into ssfpb(xydm,ssbh,cws) values('"
						+ inserItems2[i][0] + "','" + inserItems2[i][1] + "','"
						+ inserItems2[i][2] + "') ";
			}
		} else {
			inserSql = new String[1];
			inserSql[0] = "delete from ssfpb where 1=2";
		}
		String[] exesql = dao.unionArray(delSql, inserSql);
		int[] array = null;
		array = dao.runBatch(exesql);
		return dao.checkBatch(array);
	}
	
	/**
	 * ��ѯ���Ữ�ֽ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> querySsfpResult(SsfpModel model) throws Exception{
		model = replaceXydm(model);
		String[] queryList = new String[] { "lddm", "xqdm", "cs", "qsh", "xb", "xydm"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "pk", "r", "xqmc", "ldmc","xb", "cs", "qsh", "xymc"};
		String whereSql = "";
		if ("qrz".equalsIgnoreCase(model.getFbbj())) {
			whereSql = " and xydm not in ('"+TWDM+"','"+TYDM+"','"+KYDM+"','"+CJDM+"')";
		}
		return CommonQueryDAO.commonQuery(QUERY_SSFP.toString(), StringUtils.isNull(queryObject
				.getQueryString()) ? "" : queryObject
						.getQueryString() + whereSql , queryObject.getInputList(), colList, model);
	}
		
	/**
	 * �����ⲿ��ѧԺ��������滻
	 * @param model
	 * @return
	 */
	public SsfpModel replaceXydm(SsfpModel model) {
		if (TWDM.equalsIgnoreCase(model.getFbbj())) {
			model.setXydm(TWDM);
		} else if (TYDM.equalsIgnoreCase(model.getFbbj())) {
			model.setXydm(TYDM);
		} else if (KYDM.equalsIgnoreCase(model.getFbbj())) {
			model.setXydm(KYDM);
		} else if (CJDM.equalsIgnoreCase(model.getFbbj())) {
			model.setXydm(CJDM);
		} 
		return model;
	}
	
	/**
	 * ͨ������(ssbh)ɾ�����������Ϣ
	 * @param pkList
	 * @return
	 */
	public boolean deleteSsfpxx(String[] pkList) throws Exception{
		if (pkList == null) {
			return false;
		} else {
			String[] sqlArr = new String[pkList.length];
			for (int i=0;i<sqlArr.length;i++) {
				StringBuffer sql = new StringBuffer("delete from ssfpb where ssbh||xydm='");
				sql.append(pkList[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		}
	}
	
	/**
	 * ��ù�Ԣ��������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, SsfpModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm",
				"sfbj", "xqdm", "lddm", "cs", "qsh","lx" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}
}
