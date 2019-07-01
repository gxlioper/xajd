/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-28 ����11:08:45 
 */  
package com.zfsoft.xgxt.jjgl.zcyh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-28 ����11:08:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcyhDao extends SuperDAOImpl<ZcyhForm> {

	/**
	 * 
	 * @����:��ȡ��Ů��Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����11:16:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public HashMap<String , String> getZnxxMapById(String id) throws Exception{
		String sql = "select a.* from XSGGFW_JJFW_JZZNXXB a where a.znid = ? ";
		
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	/**
	 * 
	 * @����:��ȡע���û���Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����11:27:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getZcyhMapById(String id) throws Exception{
		String sql = "select a.* from XSGGFW_JJFW_YHZCXXB a where a.yhm = ? ";
		
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	/**
	 * 
	 * @����:��ȡ������Ů��Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����11:19:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getZnxxMapByYhm(String yhm) throws Exception{
		String sql = "select a.* from XSGGFW_JJFW_JZZNXXB a where a.yhm = ? ";
		return dao.getListNotOut(sql,  new String[]{yhm});
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZcyhForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		//������
		if(StringUtils.equals("w", t.getType())){
			sql.append("select t1.* from XSGGFW_JJFW_YHZCXXB t1 where not exists (select 1 from XSGGFW_JJFW_JZHMD t2 where t2.zcid = t1.yhm and t2.sfsx = '1') ");
		}else if(StringUtils.equals("b", t.getType())){
			sql.append("select t2.yy , t2.sj , t2.sfsx , t1.* from XSGGFW_JJFW_JZHMD t2 left join XSGGFW_JJFW_YHZCXXB t1 on t2.zcid = t1.yhm where t2.sfsx = '1' ");
		}
		
		if (!StringUtil.isNull(t.getYhm())){
			sql.append(" and t1.yhm like '%'||?||'%' ");
			params.add(t.getYhm());
		}

		if (!StringUtil.isNull(t.getXm())){
			sql.append(" and t1.xm like '%'||?||'%' ");
			params.add(t.getXm());
		}

		if (!StringUtil.isNull(t.getSfzh())){
			sql.append(" and t1.sfzh like '%'||?||'%' ");
			params.add(t.getSfzh());
		}
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	
	/**
	 * 
	 * @����:���ú�����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����03:11:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean szHmd(ZcyhForm model)throws Exception{
		String sql = "insert into XSGGFW_JJFW_JZHMD (zcid , yy , sj , sfsx) values ( ? ,? ,? ,?)";
		return dao.insert(sql, new String[]{model.getYhm() , model.getHmdyy() , model.getHmdsj() , "1"});
	}
	
	/**
	 * 
	 * @����:Cancel������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����03:11:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelHmd(ZcyhForm model)throws Exception{
		String sql = "update XSGGFW_JJFW_JZHMD set sfsx = '0' where zcid = ?";
		return dao.update(sql, new String[]{model.getYhm()}) > 0;
	}
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZcyhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(ZcyhForm.class);
		super.setKey("yhm");
		super.setTableName("XSGGFW_JJFW_YHZCXXB");
	}

	/**
	 * �ж��û����Ƿ�ע��.
	 *
	 * @param yhm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:26
	 */
    public boolean isYhmExist(String yhm) {

    	String sql = "SELECT count(1) num FROM XSGGFW_JJFW_YHZCXXB WHERE YHM = ?";
    	String num = dao.getOneRs(sql,new String[]{yhm},"num");
		return Integer.parseInt(num) > 0;
    }

	/**
	 * �ж����֤���Ƿ�ע��.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:54
	 */
	public boolean isSfzhExist(ZcyhForm zcyhForm) {

		StringBuilder sbd = new StringBuilder();

		List<String> paraList = new ArrayList<String>();
		paraList.add(zcyhForm.getSfzh());

		sbd.append("SELECT count(1) num FROM XSGGFW_JJFW_YHZCXXB WHERE sfzh = ?");
		if(zcyhForm.getYhm() != null){
			sbd.append(" AND yhm != ?");
			paraList.add(zcyhForm.getYhm());
		}

		String num = dao.getOneRs(sbd.toString(),paraList.toArray(new String[]{}),"num");
		return Integer.parseInt(num) > 0;
	}

	/**
	 * �ж���ϵ�绰�Ƿ�ע��.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:54
	 */
	public boolean isLxdhExist(ZcyhForm zcyhForm) {

		StringBuilder sbd = new StringBuilder();

		List<String> paraList = new ArrayList<String>();
		paraList.add(zcyhForm.getLxdh());

		sbd.append("SELECT count(1) num FROM XSGGFW_JJFW_YHZCXXB WHERE lxdh = ?");
		if(zcyhForm.getYhm() != null){
			sbd.append(" AND yhm != ?");
			paraList.add(zcyhForm.getYhm());
		}

		String num = dao.getOneRs(sbd.toString(),paraList.toArray(new String[]{}),"num");
		return Integer.parseInt(num) > 0;
	}

	/**
	 * �����ҳ���Ϣ.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 10:26
	 */
	public boolean jzxxSaveForAdd(ZcyhForm zcyhForm) throws Exception {

		StringBuilder sbd = new StringBuilder();
		sbd.append("INSERT INTO XSGGFW_JJFW_YHZCXXB (YHM, XM, SFZH, LXDH, JTZZ, GZDW, ZT, ZCSJ,XB) ");
		sbd.append("VALUES (?, ?, ?, ?, ?, ?, ?,?,?)");
		String [] inputValue = new String[]{zcyhForm.getYhm(),zcyhForm.getXm(),zcyhForm.getSfzh(),
				zcyhForm.getLxdh(),zcyhForm.getJtzz(),zcyhForm.getGzdw(),zcyhForm.getZt(),zcyhForm.getZcsj(),zcyhForm.getXb()};
		return dao.runUpdate(sbd.toString(),inputValue);
	}

	/**
	 * ���¼ҳ���Ϣ.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 10:26
	 */
	public boolean jzxxSaveForEdit(ZcyhForm zcyhForm) throws Exception {

		StringBuilder sbd = new StringBuilder();
		sbd.append("UPDATE XSGGFW_JJFW_YHZCXXB SET XM = ?,SFZH = ?,LXDH = ?,");
		sbd.append("JTZZ = ?,GZDW = ?,ZT = ?,ZCSJ = ?,XB = ? WHERE YHM = ?");
		String [] inputValue = new String[]{zcyhForm.getXm(),zcyhForm.getSfzh(),zcyhForm.getLxdh(),zcyhForm.getJtzz(),
				zcyhForm.getGzdw(),zcyhForm.getZt(),zcyhForm.getZcsj(),zcyhForm.getXb(),zcyhForm.getYhm()};
		return dao.runUpdate(sbd.toString(),inputValue);
	}

	/**
	 * ������Ů��Ϣ����ɾ��壩.
	 *
	 * @param znxxModelList
	 * @param yhm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 10:26
	 */
	public boolean saveZnxx(List<ZnxxModel> znxxModelList, String yhm) throws Exception {

		String sql_del = "DELETE FROM XSGGFW_JJFW_JZZNXXB WHERE YHM = ?";
		boolean result = dao.runUpdate(sql_del,new String[]{yhm});
		if(result){
			String sql_insert = "INSERT INTO XSGGFW_JJFW_JZZNXXB (ZNID, XM, XB, CSRQ, ZDXX, NJ, YHM) VALUES (?, ?,?, ?, ?, ?, ?)";
			List<String[]> paraList = new ArrayList<String[]>();
			for(ZnxxModel znxxModel:znxxModelList){
				String [] para = new String[] {UniqID.getInstance().getUniqIDHash(),znxxModel.getXm(),znxxModel.getXb(),znxxModel.getCsrq(),
				znxxModel.getZdxx(),znxxModel.getNj(),yhm};
				paraList.add(para);
			}
			result = dao.runBatchBoolean(sql_insert,paraList);
		}
		return result;
	}

	/**
	 * �����û�������ɾ����Ů��Ϣ.
	 *
	 * @param ids
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 17:17
	 */
	public int delZnxx(String[] ids) throws Exception {

		if(ids == null || ids.length == 0){
			return 0;
		}else {
			StringBuilder sbd = new StringBuilder();
			sbd.append("DELETE FROM XSGGFW_JJFW_JZZNXXB WHERE YHM IN (");
			for(int i=0;i<ids.length;i++){
				sbd.append(ids[i]);
				if(i != ids.length){
					sbd.append(",");
				}
			}
			sbd.append(")");
			int num = dao.runDelete(sbd.toString(),ids);
			return num;
		}
	}

	/**
	 * ���ݼҳ��û�����ѯ��Ů��Ϣ�б�
	 * @param sqr
	 * @return
	 */
	public List<HashMap<String,String>> getZnxxListByPid(String sqr) {

		String sql = "select ZNID,XM,XB from XSGGFW_JJFW_JZZNXXB WHERE YHM = ?";
		return dao.getListNotOut(sql,new String[]{sqr});
	}

	/**
	 * @��������:��ȡ��ʱ����û���
	 * @auther: ������[1692]
	 */
	public String getMaxYhm() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT max(yhm) yhm FROM XSGGFW_JJFW_YHZCXXB order by yhm desc");
		return dao.getOneRs(sql.toString(),new String[]{},"yhm");
	}
}
