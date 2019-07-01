/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-19 ����02:21:23 
 */  
package com.zfsoft.xgxt.jjgl.jjxq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @�๦������: �ҽ�����
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-19 ����02:21:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjxqDao extends SuperDAOImpl<JjxqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("XSGGFW_JJFW_JZJJXQSQB");
		super.setKey("xqid");
		super.setClass(JjxqForm.class);
	}
	
	/**
	 * 
	 * @����:��ȡmodelMap
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-27 ����10:22:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(String id) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,decode(t1.shzt,'0','δ���','1','ͨ��','2','��ͨ��') shztmc,t2.xm znxm,t2.xb znxb,t2.nj znnj,");
		sql.append("decode(t1.jjzt , 'S00','��ʼ��','S05','���Խ�','S10','�Խ���','S20','������ѧ','S30','����','S95','��˲�ͨ��','S99','��Ч','δ֪״̬') as jjztmc,");
		sql.append("(select count(1) from XSGGFW_JJFW_XSJJXQSQB tt1 where tt1.xqid = t1.xqid and tt1.zt = '1' and tt1.shzt = '1') as sfjjz ");
		sql.append("from XSGGFW_JJFW_JZJJXQSQB t1 ");
		sql.append("left join XSGGFW_JJFW_JZZNXXB t2 on t2.znid = t1.znid ");
		sql.append("where t1.xqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}

	public Map<String,String> getJjxqMap(String keyValue) throws Exception {

		StringBuilder sbl = new StringBuilder();
		sbl.append("SELECT xqid,jjcz,decode(jjcz,'0','�˼ҽ�','1','�ɼҽ�','4','�رռҽ�','δ֪����') jjczmc,sqly,splc FROM XSGGFW_JJFW_XSJJXQSQB ");
		sbl.append("WHERE SQID = ?");
		return dao.getMapNotOut(sbl.toString(),new String[]{keyValue});
	}

	public Map<String,String> getTjjxqMap(String sqid) {

		StringBuilder sbl = new StringBuilder();
		sbl.append("SELECT xqid,jjcz,decode(jjcz,'0','�˼ҽ�','1','�ɼҽ�','4','�رռҽ�','δ֪����') jjczmc,sqly,splc FROM XSGGFW_JJFW_XSTJJSQB ");
		sbl.append("WHERE SQID = ?");
		return dao.getMapNotOut(sbl.toString(),new String[]{sqid});
	}

	@Override
	public List<HashMap<String, String>> getPageList(JjxqForm t)
			throws Exception {
		return null;
	}

	
	public List<HashMap<String, String>> getJjxqList(JjxqForm t,User user)
		throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xm znxm,t2.xb znxb,t2.nj znnj,");
		//������ �����������������뻹δ������ ������ͨ�������ҽ̽������Э���飬�г��ǿ������ˣ��� δ���� ��δͨ����
		//�򵥷���ֻ��2��״̬���������δ���� ����һ�����״̬����
		//δ���� ����У��Ա�����˵���������� ��ͨ�� �˻�
		//decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc ��һ��������
		sql.append(" case when t5.xh is null then 'δ����' ");
		sql.append(" when t5.SHZT = '3' or t5.SHZT ='2'then 'δ����' ");
		sql.append(" when t3.SHZT = '1' and t5.SHZT = '1' then 'δ����'");
		sql.append(" when t5.xh = ? then decode(t5.shzt,'1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t5.shzt)  ");
		sql.append(" else '������' end sqztmc ");
		sql.append("from XSGGFW_JJFW_JZJJXQSQB t1 ");
		sql.append("left join XSGGFW_JJFW_JZZNXXB t2 on t2.znid = t1.znid ");
		sql.append("left join (select * from ( " +
				"select a.* ,row_number() over(partition by a.xqid order by a.sqsj desc) rn1 from  XSGGFW_JJFW_XSJJXQSQB a ) where rn1 = 1) t5 on t1.xqid=t5.xqid ");
		sql.append("left join (select * from ( " +
				"select a.* ,row_number() over(partition by a.xqid order by a.sqsj desc) rn1 from  XSGGFW_JJFW_XSTJJSQB a ) where rn1 = 1) t3 on t1.xqid=t3.xqid ");
		sql.append("where t1.jjzt = '0' ");
		
		params.add(user.getUserName());

		if (!StringUtil.isNull(t.getJjxk())){
			sql.append(" and t1.jjxk = ? ");
			params.add(t.getJjxk());
		}
		if (!StringUtil.isNull(t.getJjnj())){
			sql.append(" and t1.jjnj = ? ");
			params.add(t.getJjnj());
		}

		sql.append(" order by t1.xqid,t5.sqsj desc");
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}


	/**
	 * �ҵļҽ̸�λ.
	 * �ҽ�״̬��δ�ɳ��� ��Э���� �ѳ��� �ѽ�Э���� �ѹر�
	 * ���루���״̬�����رռҽ̵����룬�˼ҽ̵�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(JjxqForm t, User user)
			throws Exception {

		//�رռҽ������ �˼ҽ������
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT t.*,t5.sqid jjczsqid,t5.shzt jjczshzt,t5.jjcz tjjcz, ");
		sql.append("decode(t5.jjcz,'0','�˼ҽ�','4','�رռҽ�','')||decode(t5.shzt,'1','[ͨ��]','2','[��ͨ��]','3','[�˻�]','5','[�����]','') jjczshztmc FROM ( ");

		sql.append("select t.sqid,t1.*,t2.xm znxm, t2.xb znxb,");
		sql.append("decode(t1.jjzt,'0', 'δ�ɳ�', '1', '����Э����', '2', '�ѳ���', '3', '�ѽ�Э����', '4', '�ѹر�','δ֪״̬') jjztmc,");
		sql.append(" t2.nj znnj,t.xh,");
		sql.append("t.kssj,t.jssj,t.zt from XSGGFW_JJFW_XSJJXQSQB t,");
		sql.append("XSGGFW_JJFW_JZJJXQSQB t1,XSGGFW_JJFW_JZZNXXB t2 ");
		sql.append("where t1.znid = t2.znid and t.xqid = t1.xqid and t.shzt = '1' ");
//		sql.append(" and t1.jjzt != '0' ");


		if (!StringUtil.isNull(t.getJjxk())){
			sql.append(" and t1.jjxk = ? ");
			params.add(t.getJjxk());
		}
		if (!StringUtil.isNull(t.getJjnj())){
			sql.append(" and t1.jjnj = ? ");
			params.add(t.getJjnj());
		}
		if (!StringUtil.isNull(t.getJjzt())){
			sql.append(" and t1.jjzt = ? ");
			params.add(t.getJjzt());
		}
		
		sql.append(" and t.xh=?");
		params.add(user.getUserName());
		sql.append(") t ");

		sql.append("LEFT JOIN ");
		sql.append("(SELECT b.SQID,b.SQSJ,b.PJJSQID,b.XH,b.XQID,b.SHZT,b.JJCZ FROM ( ");
		sql.append("SELECT a.*,row_number() OVER (PARTITION BY PJJSQID ORDER BY SQSJ DESC ) rw ");
		sql.append("FROM XSGGFW_JJFW_XSTJJSQB a ");
		sql.append(") b WHERE b.rw = 1) t5 ON t.sqid = t5.pjjsqid ");
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}


	/**
	 * 
	 * @����: ����ѧ���ҽ�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-27 ����01:43:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXssq(JjxqForm t, User user) throws Exception{
		String sql = "insert into xsggfw_jjfw_xsjjxqsqb(sqid,xh,xqid,sqsj,zt,shzt,splc,jjcz,sqly) values (?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'1',?,?,?,?)";
		
		return dao.runUpdate(sql, new String[]{t.getSqid(),user.getUserName(),t.getXqid(),t.getShzt(),t.getSplc(),t.getJjcz(),t.getSqly()});
	}

	
	/**
	 * 
	 * @����: ��������ҽ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-27 ����04:04:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJsjj(JjxqForm t,User user) throws Exception{

		//�˼ҽ� �رռҽ̵����뱣�浽�����
		String sql = "INSERT INTO XSGGFW_JJFW_XSTJJSQB (SQID,PJJSQID,XH,XQID,SQSJ,SHZT,SPLC,JJCZ,SQLY) VALUES (?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?)";
		return dao.runUpdate(sql, new String[]{t.getSqid(),t.getPjjsqid(),user.getUserName(),t.getXqid(),t.getShzt(),t.getSplc(),t.getJjcz(),t.getSqly()});
	}
	
	
	/**
	 *  
	 * @����: ���¼ҽ�״̬
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-27 ����04:07:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean updateJjzt(JjxqForm t) throws Exception{
		
		String sql = "update XSGGFW_JJFW_JZJJXQSQB set jjzt='S30' where xqid=(select xqid from XSGGFW_JJFW_XSJJXQSQB where sqid=?)";
		return dao.runUpdate(sql, new String[]{t.getSqid()});
	}
	
	
	/**
	 * 
	 * @����: ��ѯѧ��������Ϣ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-2 ����04:30:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXssqInfo(String sqid){
		
		String sql = "select * from XSGGFW_JJFW_XSJJXQSQB where sqid=?";
		return dao.getMapNotOut(sql, new String[]{sqid});
	}

    /**
     * ��ȡ�ҽ̲�������б�����
     * @param jjxqForm
     * @param user
     * @return
     */
    public List<HashMap<String,String>> getJjczshList(JjxqForm jjxqForm, User user) throws Exception {

        String searchTj = SearchService.getSearchTj(jjxqForm.getSearchModel());
        String[] inputV = SearchService.getTjInput(jjxqForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* from (");

        sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,decode(t1.jjcz,'0','�˼ҽ�','1','�ɼҽ�','4','�رռҽ�','δ֪����') jjczmc,");
        sql.append("t2.bjdm,t2.bjmc,t2.nj,t2.xb, t4.guid shid,t4.gwid,t4.shr,t4.shyj,");
        sql.append("t6.mc || '[' ||decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,");
        sql.append("t6.gwz,");
        sql.append("row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
        sql.append("from VIEW_XSGGFW_JJFW_XSJJCZSQB t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
        sql.append("left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
        sql.append("left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw ");
        sql.append("left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");

        sql.append(" where t5.spyh ='"+ user.getUserName() + "' ");
        String shlx = jjxqForm.getShzt();
        if (!shlx.equals("dsh")) {
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
        } else {
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
        }
        sql.append(" ) t where 1=1 ");
        sql.append(" and  rn = 1 ");

        sql.append(searchTj);
        sql.append(searchTjByUser);

        return getPageList(jjxqForm, sql.toString(), inputV);
    }

	/**
	 * ��������id�������״̬
	 * @param jjxqForm
	 * @return
	 * @throws Exception
	 */
	public boolean updateShztBySqid(JjxqForm jjxqForm) throws Exception {

    	StringBuilder sql = new StringBuilder();
    	sql.append("update XSGGFW_JJFW_XSJJXQSQB set shzt=? ");

		if(StringUtils.isNotNull(jjxqForm.getPcsj())){
			sql.append(", kssj = '");
			sql.append(jjxqForm.getPcsj());
			sql.append("' ");
		}
		if(StringUtils.isNotNull(jjxqForm.getGbsj())){
			sql.append(", jssj = '");
			sql.append(jjxqForm.getGbsj());
			sql.append("' ");
		}
    	sql.append("where sqid=?");
    	return dao.runUpdate(sql.toString(),new String[]{jjxqForm.getShzt(),jjxqForm.getSqid()});
	}

	/**
	 * ��������id���¹ر�ʱ��
	 * @param jjxqForm
	 * @return
	 * @throws Exception
	 */
	public boolean updateGbsjBySqid(JjxqForm jjxqForm) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("update XSGGFW_JJFW_XSJJXQSQB set jssj=? ");
		sql.append("where sqid=?");
		return dao.runUpdate(sql.toString(),new String[]{jjxqForm.getGbsj(),jjxqForm.getSqid()});
	}

	/**
	 * ����pjjsqid��jjcz�жϵ�ǰ�ҽ̲����Ƿ��Ѿ���������
	 * @param jjxqForm
	 * @return
	 */
	public String isJjczExist(JjxqForm jjxqForm) {

		StringBuilder sql = new StringBuilder("select nvl(count(1),0) cnt from XSGGFW_JJFW_XSTJJSQB where shzt != '3' ");
		sql.append("and pjjsqid = ? and jjcz = ? ");

		return dao.getOneRs(sql.toString(),new String[]{jjxqForm.getPjjsqid(),jjxqForm.getJjcz()},"cnt");
	}

	/**
	 * ����pjjsqid jjcz shzt=3��һ���Ƿ����˻ؼ�¼ �еĻ�ֱ���ύ
	 * @param t
	 * @return
	 */
	public Map<String,String> getTjjSqxx(JjxqForm t) {

		String sql = "SELECT * FROM XSGGFW_JJFW_XSTJJSQB WHERE shzt = '3' and pjjsqid = ? and jjcz = ? ";
		return dao.getMapNotOut(sql,new String[]{t.getPjjsqid(),t.getJjcz()});
	}

	/**
	 * ����sqid�޸��˼ҽ̣��رռҽ̣���������״̬
	 * @param t
	 * @return
	 */
	public boolean updateTjjShztBySqid(JjxqForm t) throws Exception {

		StringBuilder sbl = new StringBuilder();
		List<String> paras = new ArrayList<String>();
		paras.add(t.getShzt());
		sbl.append("UPDATE XSGGFW_JJFW_XSTJJSQB SET SHZT = ? ");
		if(StringUtils.isNotNull(t.getSqly())){
			sbl.append(",SQLY = ? ");
			paras.add(t.getSqly());
		}
		paras.add(t.getSqid());
		sbl.append("WHERE SQID = ? ");

		return dao.runUpdate(sbl.toString(),paras.toArray(new String[] {}));
	}

	public HashMap<String,String> getPjxxMap(String xqid) {

		String sql = "SELECT PJID,XQID,PJZS,PY,PJSJ FROM XSGGFW_JJFW_JZPJB WHERE XQID = ?";
		return dao.getMapNotOut(sql,new String[]{xqid});
	}

	public Boolean jjpjInsert(String xqid, String pjzs, String py) throws Exception {

		String sql = "INSERT INTO XSGGFW_JJFW_JZPJB (PJID,XQID,PJZS,PY,PJSJ) VALUES (sys_guid(),?,?,?,to_char(SYSDATE,'YYYY-MM-DD'))";
		return dao.runUpdate(sql,new String [] {xqid,pjzs,py});
	}

	public Boolean jjpjUpdate(String pjid, String xqid, String pjzs, String py) throws Exception {

		String sql = "UPDATE XSGGFW_JJFW_JZPJB SET xqid = ?,pjzs=?,py=?,pjsj = to_char(SYSDATE,'YYYY-MM-DD') WHERE pjid = ?";
		return dao.runUpdate(sql,new String [] {xqid,pjzs,py,pjid});
	}

	public List<HashMap<String,String>> getJjgsxxList(String xqid) throws Exception {

		String sql = "SELECT * FROM XSGGFW_JJFW_JJGSWHB WHERE JJBH = ? ORDER BY JJNY DESC";
		return dao.getListNotOut(sql,new String[] {xqid});
	}

	public boolean jjgsSave(String jjbh, String jjny, String jjgs) throws Exception {

		String sql = "INSERT INTO XSGGFW_JJFW_JJGSWHB (JJNY,JJBH,JJGS) VALUES (?,?,?)";
		return dao.runUpdate(sql,new String[] {jjny,jjbh,jjgs});
	}

	public String getJjgsjlCount(String jjbh, String jjny) {

		String sql = "SELECT count(1) count FROM XSGGFW_JJFW_JJGSWHB WHERE JJBH = ? AND JJNY = ?";
		return dao.getOneRs(sql,new String [] {jjbh,jjny},"count");
	}
}
