/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-13 ����04:25:14 
 */
package com.zfsoft.xgxt.comm.shlc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��˲���DAO
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-6-13 ����04:25:14
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ShlcDao {

	private DAO dao = DAO.getInstance();

	/**
	 * 
	 * @����: ��������̲�ѯ��һ����˸�λ��ID
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-13 ����04:41:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @return String ��������
	 */
	public String getFirstGwid(String shlc) {

		String sql = "select spgw from xg_xtwh_spbz where splc=? and xh='1'";

		return dao.getOneRs(sql, new String[] { shlc }, "spgw");
	}
	
	public static HashMap<String,String> getDqjbByCondition(String sqid,String userId,String lcid,String type) {
		
		String sql = "select a.*, b.xh,b.splc from ( select t.*,lead(t.zd2, 1, t2.xmdm) over(partition by t.ywid order by t.ywid, t.shsj desc) as sjxmdm," +
				"row_number() over(partition by t.ywid order by t.ywid, t.shsj desc) as rn from XG_XTWH_SHZTB t " +
				"left join xg_pjpy_new_xmsq t2 on t.ywid = t2.sqid where t.lcid = ? and ywid = ? ";
		if(type.equals("sh")){
			sql+= " and t.shzt in ('0','4') ";
		}else{
			sql+= " and t.shzt not in ('0','4') ";
		}
			sql+= " ) a left join XG_XTWH_SPBZ b on a.lcid = b.splc and a.gwid = b.spgw where a.rn = 1";
		
		
		return DAO.getInstance().getMapNotOut(sql,  new String[]{lcid,sqid});
	}
	
	public static HashMap<String,String> getDqjbZdByCondition(String id,String userId,String lcid,String type) {
		String sql = "select a.*, b.xh,b.splc from ( select t.*,lead(t.zd2, 1, t2.xmdm) over(partition by t.ywid order by t.ywid, t.shsj desc) as sjxmdm," +
				"row_number() over(partition by t.ywid order by t.ywid, t.shsj desc) as rn from XG_XTWH_SHZTB t " +
				"left join xg_zjdx_pjpy_xmsq t2 on t.ywid = t2.id where t.lcid = ? and ywid = ? ";
		if(type.equals("sh")){
			sql+= " and t.shzt in ('0','4') ";
		}else{
			sql+= " and t.shzt not in ('0','4') ";
		}
			sql+= " ) a left join XG_XTWH_SPBZ b on a.lcid = b.splc and a.gwid = b.spgw where a.rn = 1";
		return DAO.getInstance().getMapNotOut(sql,  new String[]{lcid,id});
	}

	/**
	 * 
	 * @����:��ȡ���һ���ĸ�λ���������һ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-8 ����11:28:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @return String ��������
	 */
	public String getLastGwid(String shlc) {
		String gwid = null;
		String sql = "select * from xg_xtwh_spbz where splc=? order by xh";
		List<HashMap<String, String>> data = dao.getListNotOut(sql,
				new String[] { shlc });
		if (null != data && data.size() > 0) {
			HashMap<String, String> map = data.get(data.size()-1);
			gwid = map.get("spgw");
		}
		return gwid;
	}

	/**
	 * 
	 * @����: ��ѯ��һ����λID
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-13 ����06:59:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param gwid
	 * @return String ��������
	 * @throws
	 */
	public String getNextGwid(String shlc, String gwid) {

		String sql = "select xjgw from (select spgw,lead(spgw,1) over (order by xh) xjgw from xg_xtwh_spbz t1 where splc=? ) where spgw=?";

		return dao.getOneRs(sql, new String[] { shlc, gwid }, "xjgw");
	}
	/**
	 * ��ѯ��һ����λID
	 */
	public String getBeforeGwid(String shlc, String gwid) {
		
		String sql = "select spgw from (select spgw,lead(spgw,1) over (order by xh) xjgw from xg_xtwh_spbz t1 where splc=? ) where xjgw=?";
		
		return dao.getOneRs(sql, new String[] { shlc, gwid }, "spgw");
	}

	/**
	 * 
	 * @����: ������˴���ڵ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-13 ����04:47:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param jdid
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean insertDbjd(String ywid, String jdid,String lcid,String xh,String tzlj,String tzljsq) throws Exception {

		String sql = "insert into xg_xtwh_shztb(ywid,gwid,lcid,sqrid,tzlj,tzljsq) values (?,?,?,?,?,?)";

		return dao.runUpdate(sql, new String[] { ywid, jdid ,lcid ,xh ,tzlj,tzljsq});
	}
	
	public boolean insertDbjdNotCommit(String ywid, String jdid,String lcid,String xh,String tzlj,String tzljsq) throws Exception {

		String sql = "insert into xg_xtwh_shztb(ywid,gwid,lcid,sqrid,tzlj,tzljsq) values (?,?,?,?,?,?)";

		return dao.runUpdateNotCommit(sql, new String[] { ywid, jdid ,lcid ,xh ,tzlj,tzljsq});
	}
	
	public boolean insertDbjdBatchNotCommit(List<String[]> paraList) throws Exception {

		String sql = "insert into xg_xtwh_shztb(ywid,gwid,lcid,sqrid,tzlj,tzljsq) values (?,?,?,?,?,?)";

		return dao.runBatchNotCommit(sql.toString(), paraList);
	}

	/**
	 * 
	 * @����: ������˴���ڵ�(�˻�)
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-11
	 * @param ywid
	 * @param jdid
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean insertThjd(String ywid, String jdid, String shzt,String lcid,String xh,String tzlj,String tzljsq)
			throws Exception {

		String sql = "insert into xg_xtwh_shztb(ywid,gwid,shzt,lcid,sqrid,tzlj,tzljsq,shqzt) values (?,?,?,?,?,?,?,?)";
		
		return dao.runUpdate(sql, new String[] { ywid, jdid, shzt,lcid ,xh ,tzlj ,tzljsq,shzt });
	}
	
	public boolean insertThjdNotCommit(String ywid, String jdid, String shzt,String lcid,String xh,String tzlj,String tzljsq)
	throws Exception {

	String sql = "insert into xg_xtwh_shztb(ywid,gwid,shzt,lcid,sqrid,tzlj,tzljsq,shqzt) values (?,?,?,?,?,?,?,?)";
	
	return dao.runUpdateNotCommit(sql, new String[] { ywid, jdid, shzt,lcid ,xh ,tzlj ,tzljsq,shzt });
	}

	/**
	 * 
	 * @����: ���������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-13 ����06:26:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean updateShxx(String shr, String shzt, String shyj, String guid)
			throws Exception {
		shyj = ((String)shyj).replaceAll("\\n", "<br/>");
		String sql = "update xg_xtwh_shztb set shr=?,shsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),shzt=?,shyj=? where guid=?";

		return dao.runUpdate(sql, new String[] { shr, shzt, shyj, guid });
	}

	/**
	 * 
	 * @����: ���������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-13 ����06:26:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean updateShxx(ShxxModel model, String guid) throws Exception {
		
		model = (ShxxModel)StringUtils.formatBean(model);
		StringBuffer sql = new StringBuffer("");
		sql.append(" update xg_xtwh_shztb ");
		sql.append(" set shr=? ");
		sql.append(" , shsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ");
		sql.append(" , shzt=? ");
		sql.append(" , shyj=? ");
		List<String> objectTmp = new ArrayList<String>();
		objectTmp.add(model.getShr());
		objectTmp.add(model.getShzt());
		objectTmp.add(model.getShyj());

		// ҵ���ֶδ��� ��׷��
		if (model.getZd1() != null && !"".equalsIgnoreCase(model.getZd1())) {
			sql.append(" , zd1=? ");
			objectTmp.add(model.getZd1());
		}
		if (model.getZd2() != null && !"".equalsIgnoreCase(model.getZd2())) {
			sql.append(" , zd2=? ");
			objectTmp.add(model.getZd2());
		}
		if (model.getZd3() != null && !"".equalsIgnoreCase(model.getZd3())) {
			sql.append(" , zd3=? ");
			objectTmp.add(model.getZd3());
		}
		// ҵ���ֶδ��� ��׷��
		if (model.getZd4() != null && !"".equalsIgnoreCase(model.getZd4())) {
			sql.append(" , zd4=? ");
			objectTmp.add(model.getZd4());
		}
		if (model.getZd5() != null && !"".equalsIgnoreCase(model.getZd5())) {
			sql.append(" , zd5=? ");
			objectTmp.add(model.getZd5());
		}
		if (model.getZd6() != null && !"".equalsIgnoreCase(model.getZd6())) {
			sql.append(" , zd6=? ");
			objectTmp.add(model.getZd6());
		}
		if (model.getZd7() != null && !"".equalsIgnoreCase(model.getZd7())) {
			sql.append(" , zd7=? ");
			objectTmp.add(model.getZd7());
		}
		if (model.getZd8() != null && !"".equalsIgnoreCase(model.getZd8())) {
			sql.append(" , zd8=? ");
			objectTmp.add(model.getZd8());
		}
		if (model.getZd9() != null && !"".equalsIgnoreCase(model.getZd9())) {
			sql.append(" , zd9=? ");
			objectTmp.add(model.getZd9());
		}
		if (model.getZd10() != null && !"".equalsIgnoreCase(model.getZd10())) {
			sql.append(" , zd10=? ");
			objectTmp.add(model.getZd10());
		}
		sql.append(" where guid=? ");
		objectTmp.add(guid);

		return dao.runUpdate(sql.toString(), objectTmp
				.toArray(new String[objectTmp.size()]));
	}
	
	public boolean updateShxxNotCommit(ShxxModel model, String guid) throws Exception {
		
		model = (ShxxModel)StringUtils.formatBean(model);
		StringBuffer sql = new StringBuffer("");
		sql.append(" update xg_xtwh_shztb ");
		sql.append(" set shr=? ");
		sql.append(" , shsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ");
		sql.append(" , shzt=? ");
		sql.append(" , shyj=? ");
		List<String> objectTmp = new ArrayList<String>();
		objectTmp.add(model.getShr());
		objectTmp.add(model.getShzt());
		objectTmp.add(model.getShyj());

		// ҵ���ֶδ��� ��׷��
		if (model.getZd1() != null && !"".equalsIgnoreCase(model.getZd1())) {
			sql.append(" , zd1=? ");
			objectTmp.add(model.getZd1());
		}
		if (model.getZd2() != null && !"".equalsIgnoreCase(model.getZd2())) {
			sql.append(" , zd2=? ");
			objectTmp.add(model.getZd2());
		}
		if (model.getZd3() != null && !"".equalsIgnoreCase(model.getZd3())) {
			sql.append(" , zd3=? ");
			objectTmp.add(model.getZd3());
		}
		// ҵ���ֶδ��� ��׷��
		if (model.getZd4() != null && !"".equalsIgnoreCase(model.getZd4())) {
			sql.append(" , zd4=? ");
			objectTmp.add(model.getZd4());
		}
		if (model.getZd5() != null && !"".equalsIgnoreCase(model.getZd5())) {
			sql.append(" , zd5=? ");
			objectTmp.add(model.getZd5());
		}
		if (model.getZd6() != null && !"".equalsIgnoreCase(model.getZd6())) {
			sql.append(" , zd6=? ");
			objectTmp.add(model.getZd6());
		}
		if (model.getZd7() != null && !"".equalsIgnoreCase(model.getZd7())) {
			sql.append(" , zd7=? ");
			objectTmp.add(model.getZd7());
		}
		if (model.getZd8() != null && !"".equalsIgnoreCase(model.getZd8())) {
			sql.append(" , zd8=? ");
			objectTmp.add(model.getZd8());
		}
		if (model.getZd9() != null && !"".equalsIgnoreCase(model.getZd9())) {
			sql.append(" , zd9=? ");
			objectTmp.add(model.getZd9());
		}
		if (model.getZd10() != null && !"".equalsIgnoreCase(model.getZd10())) {
			sql.append(" , zd10=? ");
			objectTmp.add(model.getZd10());
		}
		sql.append(" where guid=? ");
		objectTmp.add(guid);
		
		return dao.runUpdateNotCommit(sql.toString(), objectTmp
				.toArray(new String[objectTmp.size()]));
	}


	/**
	 * 
	 * @����: ��ҵ��������ѯ����˸�λ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-14 ����08:48:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return String ��������
	 */
	public HashMap<String, String> getDshGwid(String ywid) {

		String sql = "select * from xg_xtwh_shztb where ywid=? and (shzt=0 or shzt=4)";

		return dao.getMapNotOut(sql, new String[] { ywid });
	}
	
	public HashMap<String, String> getShxxByCondition(String ywid,String gwid) {

		String sql = "select * from ( select t.*," +
				" decode(lead(t.zd2,1,null) over (partition by t.ywid order by t.shsj desc),null,zd2,lead(t.zd2,1,null) over (partition by t.ywid order by t.shsj desc)) as xjzd2, " +
				" decode(lead(t.zd5,1,null) over (partition by t.ywid order by t.shsj desc),null,zd5,lead(t.zd5,1,null) over (partition by t.ywid order by t.shsj desc)) as xjzd5, " +
				" row_number() over(partition by t.ywid order by t.shsj desc) as rn from xg_xtwh_shztb t " +
				" where t.ywid=? and t.shzt in ('0','1')) where rn = 1";

		return dao.getMapNotOut(sql, new String[] { ywid });
	}

	/**
	 * 
	 * @����: ��ҵ��IDɾ������˼�¼
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-14 ����01:51:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 *            ҵ��ID
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean delNextDsjl(String ywid, String nextSpgw) throws Exception {

		String sql = "delete from xg_xtwh_shztb where ywid=? and gwid=? and (shzt=0 or shzt=4)";

		return dao.runDelete(sql, new String[] { ywid, nextSpgw }) == 1;
	}

	/**
	 * 
	 * @����: ����Ŀ���λ���״̬
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-14 ����01:55:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param gwid
	 * @param shzt
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean updateShzt(String ywid, String gwid, String shzt)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xtwh_shztb t1 set shzt=?,shr='',shsj='',");
		sql.append("shyj='',zd1='',zd2='',zd3='',zd4='',zd5='',");
		sql.append("zd6='',zd7='',zd8='',zd9='',zd10='' where ywid=? and gwid=? and ");
		sql.append("guid=(select guid from (select guid from xg_xtwh_shztb where ywid=? order by shsj desc) where rownum=1)");
		

		return dao.runUpdate(sql.toString(), new String[] { shzt, ywid, gwid,ywid });
	}

	/**
	 * 
	 * @����: ����Ŀ���λ���״̬���������ID)
	 * @���ߣ�qilm
	 * @���ڣ�2013-6-14 ����01:55:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 *            ���ID(���״̬��������
	 * @param shzt
	 *            ���״̬
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean updateShzt(String guid, String shzt) throws Exception {

		String sql = "update xg_xtwh_shztb set shzt=?,shr='',shsj='',shyj='',zd1='',zd2='',zd3='',zd4='',zd5='',zd6='',zd7='',zd8='',zd9='',zd10='' where guid=?";

		return dao.runUpdate(sql, new String[] { shzt, guid });
	}
	
	/**
	 * 
	 * @����:�ж������˵�һ����˵����״̬
	 * @���ߣ�945
	 * @���ڣ�2013-11-25 ����05:06:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFirstShzt(String ywid, String lcid) {
		String sql = " select a.shzt,a.gwid from XG_XTWH_SHZTB a left join XG_XTWH_SPBZ b on a.lcid=b.splc and a.gwid=b.spgw where a.ywid=? and a.lcid=? and b.xh='1' and shzt <> '3' " +
				"and ( shsj > (select max(shsj) from XG_XTWH_SHZTB where shzt = '3' and ywid = ? and lcid = ? ) or shsj is null )";
		//return dao.getMapNotOut(sql, new String[] { ywid, lcid });
		return dao.getListNotOut(sql.toString(), new String[] { ywid, lcid, ywid, lcid });
	}
	
	/**
	 * 
	 * @����: ��ѯ�����������ʱ��״̬
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-14 ����02:24:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shr
	 *            ����˹���
	 * @param ywid
	 *            ҵ��ID
	 * @param gwid
	 *            ��λID
	 * @return String ��������
	 * @throws
	 */
	public String getLastShzt(String shr, String ywid, String gwid) {

		String sql = " select shzt from ( select b.shzt,row_number() over (order by b.shsj desc) as sss from xg_xtwh_shztb b where ywid=? and gwid=? and shr=?  and shzt<> '9')where sss = 1 ";

		return dao.getOneRs(sql, new String[] { ywid, gwid, shr }, "shzt");
	}

	/**
	 * @����:ɾ����˼�¼
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-1 ����03:47:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean delelteShjl(String ywid) throws Exception {

		String sql = "delete from xg_xtwh_shztb where ywid=? and shzt='0'";

		return dao.runDelete(sql, new String[] { ywid }) == 1;
	}

	/**
	 * 
	 * @����: ��ѯ���˻ص�Ŀ���λ�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����02:05:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param gwid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getKthSpgw(String splc, String gwid) {

		StringBuilder sql = new StringBuilder();

		sql.append(" select t1.spgw,t2.mc gwmc,t1.xh from xg_xtwh_spbz t1 ");
		sql.append(" left join xg_xtwh_spgw t2 on t1.spgw=t2.id");
		sql
				.append(" where t1.splc=? and t1.xh < (select xh from xg_xtwh_spbz where splc=? and spgw=?)");
		sql.append(" order by t1.xh");

		return dao.getListNotOut(sql.toString(), new String[] { splc, splc,
				gwid });
	}
	
	/**
	 * 
	 * @����:���������Ƿ����
	 * @���ߣ�945
	 * @���ڣ�2013-12-27 ����10:10:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lcid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> splcsfwc(String ywid) {
		String sql = " select * from (select t.*, row_number() over(partition by t.ywid order by t.ywid, t.shsj desc) as dd " +
				" from XG_XTWH_SHZTB t where t.ywid = ?) where dd = 1 and shzt in ('0', '3', '4') ";
		return dao.getListNotOut(sql.toString(), new String[] { ywid });
	}
	
	/**
	 * 
	 * @����:��ѯҵ��ID�Ƿ��й��˻ؼ�¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-6 ����03:35:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getExistTh(String ywid){
		
		StringBuffer sql = new StringBuffer(" select count(1) num from xg_xtwh_shztb where shzt = '3' and  ywid = ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{ywid}, "num");
		
		return num;
	}

	/**
	 * ��ѯ���������������ʱ������
	 */
	public List<HashMap<String , String>> getShyjList(String ywid, String order){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.xm,d.zwmc,b.lxdh,b.bgdh,c.bmmc from xg_xtwh_shztb a ");
		sql.append(" left join fdyxxb b on a.shr=b.zgh ");
		sql.append(" left join zxbz_xxbmdm c on b.bmdm=c.bmdm ");
		sql.append(" left join zwb d on d.zwdm=b.zw ");
		sql.append(" where a.ywid = ? and a.shsj is not null order by a.shsj " + order);
		return dao.getListNotOut(sql.toString(), new String[]{ywid});
	}
	
	/**
	 * 
	 * @����: �����ܺ��û���ѯ����������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-5-4 ����03:51:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param gnid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getShyjList(User user, String gnid) {
		
		String sql = "select * from xg_xtwh_cyshyj where zgh=? and gnid=?";
		
		return dao.getListNotOut(sql, new String[]{user.getUserName(),gnid});
	}
	
	
	/**
	 * 
	 * @����: �����ܺ��û�ɾ���������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-5-5 ����08:52:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param gnid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delCyyj(User user ,String gnid) throws Exception{
		
		String sql = "delete from xg_xtwh_cyshyj where zgh=? and gnid=?";
		return dao.runUpdate(sql, new String[]{user.getUserName(),gnid});
	}
	
	
	/**
	 * 
	 * @����: �������泣��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-5-5 ����08:57:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param gnid
	 * @param shyj
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCyyj(User user,String gnid,String[] shyj) throws SQLException{
		
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		
		for (String str : shyj){
			
			if (StringUtil.isNull(str))
				continue;
			
			param = new String[]{user.getUserName(),gnid,str};
			params.add(param);
		}
		
		String sql = "insert into xg_xtwh_cyshyj(id,zgh,gnid,shyj) values (sys_guid(),?,?,?)";
		
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @����:�����������̺�������λ��ȡ��ǰ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-28 ����12:06:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param spgw
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getGwxh(String splc, String spgw){
		
		String sql = "select xh from xg_xtwh_spbz where splc = ? and spgw = ? ";
		
		return dao.getOneRs(sql, new String[]{splc,spgw}, "xh");
	}
	
	
	/**
	 * 
	 * @����:��ѯ�������״̬�Ƿ�����,�����ظ��ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-20 ����05:01:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean getExistForShzt(String ywid, String dqshgw){
		
		String sql = "select shzt from ( select * from xg_xtwh_shztb a where a.ywid = ? and gwid =? order by a.shsj desc ) where rownum = 1";
		
		String shzt = dao.getOneRs(sql, new String[]{ywid,dqshgw}, "shzt");
		
		boolean ExistForShzt=(!StringUtil.isNull(shzt)&&!(shzt.equals(Constants.SH_DSH)||shzt.equals(Constants.SH_XCS)))? true:false;
		
		return ExistForShzt;
	}
	
	/**
	 * 
	 * @����:�����ж��Ƿ��ظ��ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-20 ����05:01:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean getExistForShzt(String ywid){
		
		String sql = "select shzt from ( select * from xg_xtwh_shztb a where a.ywid = ? order by a.shsj desc ) where rownum = 1";
		
		String shzt = dao.getOneRs(sql, new String[]{ywid}, "shzt");
		
		boolean ExistForShzt=(!StringUtil.isNull(shzt)&&!shzt.equals(Constants.SH_TH))? true:false;
		
		return ExistForShzt;
	}

/**
 * 
 * @����:����������չ�����ж��������Ƽ���
 * @���ߣ�yxy[���ţ�1206]
 * @���ڣ�2016-7-28 ����11:11:35
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param sqid
 * @param userId
 * @param lcid
 * @param type
 * @return
 * HashMap<String,String> �������� 
 * @throws
 */
public static HashMap<String,String> getXstzxmByCondition(String sqid,String userId,String lcid,String type) {
		
		String sql = "select a.*, b.xh,b.splc from ( select t.*,lead(t.zd2, 1, t2.xmdm) over(partition by t.ywid order by t.ywid, t.shsj desc) as sjxmdm," +
				"row_number() over(partition by t.ywid order by t.ywid, t.shsj desc) as rn from XG_XTWH_SHZTB t " +
				"left join xg_sztz_xsxmsq t2 on t.ywid = t2.sqid where t.lcid = ? and ywid = ? ";
		if(type.equals("sh")){
			sql+= " and t.shzt in ('0','4') ";
		}else{
			sql+= " and t.shzt not in ('0','4') ";
		}
			sql+= " ) a left join XG_XTWH_SPBZ b on a.lcid = b.splc and a.gwid = b.spgw where a.rn = 1";
		
		
		return DAO.getInstance().getMapNotOut(sql,  new String[]{lcid,sqid});
	}
	/**
	 * 
	 * @����:��ȡ�ϼ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-5 ����09:41:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public static HashMap<String,String> getLastShxx(String sqid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from (select t.*, lead(t.zd2, 1) over(partition by t.ywid order by t.ywid, t.shsj desc) as sjxmdm,");
		sql.append(" row_number() over(partition by t.ywid order by t.ywid, t.shsj desc) as rn");
		sql.append("  from XG_XTWH_SHZTB t  where t.ywid =? ) a where rn=2 ");
		return DAO.getInstance().getMapNotOut(sql.toString(),  new String[]{sqid});
	}
	
	/**
	 * 
	 * @����:����������չ�����ж��������Ƽ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-28 ����11:11:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param userId
	 * @param lcid
	 * @param type
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public static HashMap<String,String> getTttzxmByCondition(String sqid,String userId,String lcid,String type) {
			
			String sql = "select a.*, b.xh,b.splc from ( select t.*,lead(t.zd2, 1, t2.xmdm) over(partition by t.ywid order by t.ywid, t.shsj desc) as sjxmdm," +
					"row_number() over(partition by t.ywid order by t.ywid, t.shsj desc) as rn from XG_XTWH_SHZTB t " +
					"left join xg_sztz_ttxmsq t2 on t.ywid = t2.ttsqid where t.lcid = ? and ywid = ? ";
			if(type.equals("sh")){
				sql+= " and t.shzt in ('0','4') ";
			}else{
				sql+= " and t.shzt not in ('0','4') ";
			}
				sql+= " ) a left join XG_XTWH_SPBZ b on a.lcid = b.splc and a.gwid = b.spgw where a.rn = 1";
			
			
			return DAO.getInstance().getMapNotOut(sql,  new String[]{lcid,sqid});
		}

	/** 
	 * @����:����ҵ��id��ȡ��Ӧ�ĸ���λ��������ͨ��ʱ���������б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��16�� ����10:59:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getShyjListByYwid(String ywid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT shr,shsj,shyj,(SELECT xm FROM yhb WHERE yhm = shr) shrmc FROM (");
		sql.append("SELECT ywid,shyj,gwxh,shr,shsj,row_number() over(partition by ywid,gwxh order by shsj desc) rn ");
		sql.append("FROM(SELECT ywid,shyj,gwid,shsj,shr,");
		sql.append("(SELECT XH FROM xg_xtwh_spbz WHERE SPLC = t.lcid AND SPGW = t.gwid) gwxh ");
		sql.append("FROM xg_xtwh_shztb t WHERE shzt = '1')) ");
		sql.append("WHERE rn = '1' AND ywid = ?");
		
		return dao.getListNotOut(sql.toString(), new String[]{ywid});
	}

	/**
	 *  ����splc��ѯģ������.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-12 19:12
	 * @param splc
	 * @return java.lang.String
	 * @throw
	 */
	public String getSsmkBySplc(String splc) {

		String sql = "SELECT b.MKMC FROM XG_XTWH_SPLC a LEFT JOIN xg_xtwh_splcmkdzb b " +
				"ON a.DJLX = b.MKDM WHERE a.ID = ?";
		return dao.getOneRs(sql,new String[]{splc},"mkmc");
	}

	/**
	 *  ����gwid��������xh��ѯ�����ְ�����б�.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-13 14:48
	 * @param gwid
	 * @param sqr
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw SQLException
	 */
	public List<String> getShrListByGwidAndSqr(String gwid, String sqr) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ZGH FROM ");
		sql.append("(SELECT b.ZGH,b.XM,b.BMDM,b.SFJRYX,c.BMLB,d.GWZ FROM XG_XTWH_SPGWYH a LEFT JOIN FDYXXB b ON a.spyh = b.ZGH ");
		sql.append("LEFT JOIN ZXBZ_XXBMDM c ON b.BMDM = c.BMDM ");
		sql.append("LEFT JOIN XG_XTWH_SPGW d ON a.SPGW = d.ID ");
		sql.append("WHERE a.SPGW = ? AND b.ZGH IS NOT NULL) t1, ");
		sql.append("(SELECT NJ,XYDM,ZYDM,BJDM FROM  VIEW_XSBFXX where xh = ?) t2 ");
		sql.append("WHERE ");
		sql.append("((GWZ IS NULL AND  ((BMLB = '1') ");		//У���û�
		sql.append("OR ");
		sql.append("(BMLB = '5' AND SFJRYX = 'true' AND BMDM = XYDM) ");	//ѧԺ�û�
		sql.append("OR ");
		sql.append("(BMLB = '5' AND SFJRYX != 'true' AND NOT exists( ");	//ѧԺ�û�
		sql.append("SELECT 1 FROM (SELECT ZGH,BJDM FROM BZRBBB UNION SELECT ZGH,BJDM FROM FDYBJB) WHERE ZGH = t1.ZGH) AND BMDM = XYDM) ");
		sql.append("OR ");
		sql.append("(BMLB = '5' AND SFJRYX != 'true' AND exists( ");	//�༶�û�
		sql.append("SELECT 1 FROM (SELECT ZGH,BJDM FROM BZRBBB UNION SELECT ZGH,BJDM FROM FDYBJB) WHERE ZGH = t1.ZGH AND BJDM = t2.BJDM)) ");
		sql.append(")) ");
		sql.append("OR ");
		sql.append("(GWZ IS NOT NULL AND ((GWZ = 'bzr' AND exists(SELECT 1 FROM BZRBBB WHERE ZGH = t1.ZGH AND BJDM = t2.BJDM)) ");	//�����ο�
		sql.append("OR ");
		sql.append("(GWZ = 'fdy' AND exists(SELECT 1 FROM FDYBJB WHERE ZGH = t1.ZGH AND BJDM = t2.BJDM)) ");
		sql.append("OR ");
		sql.append("(GWZ = 'bzrfdy' AND exists( ");	//�����θ���Ա��
		sql.append("SELECT 1 FROM (SELECT ZGH,BJDM FROM BZRBBB UNION SELECT ZGH,BJDM FROM FDYBJB) WHERE ZGH = t1.ZGH AND BJDM = t2.BJDM)))))");

		return dao.getList(sql.toString(),new String[] {gwid,sqr},"zgh");
	}
	
	/**
	 * 
	 * @����:��˽��������Ϣ��(��ͨ�����˻�)
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2018-3-8 ����09:58:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertShjgXxts(String guid) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xtwh_shjgtsxxb(ywmc,xh,xm,shztmc,shsj,shyj,sjhm) ");
		sql.append(" select ywmc,xh,xm,shztmc,shsj,shyj,sjhm from (select a.tzljsq as gnmkpath,(select gnmkmc from gnmkdmb d where a.tzljsq = d.dyym and rownum = 1) as ywmc, ");
		sql.append("  a.sqrid xh,b.xm,b.sjhm,decode(a.shzt, '1', '�����ͨ��', '2', '��˲�ͨ��', '3', '�ѱ��˻�', a.shzt) shztmc,a.shsj,a.shyj ");
		sql.append("    from (select tzljsq,sqrid,shzt,shsj,shyj from XG_XTWH_SHZTB where guid = ? and shzt in ('2', '3'))a ");
		sql.append("    left join xsxxb b on a.sqrid = b.xh) ");

		return dao.runUpdate(sql.toString(), new String[] { guid});
	}
}
