/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-27 ����04:21:12 
 */  
package com.zfsoft.xgxt.jjgl.xqwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jjgl.cssz.CsszService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-27 ����04:21:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqwhDao extends SuperDAOImpl<XqwhForm> {

	/**
	 * 
	 * @����:��������ID��ȡ�ҽ�������Ϣ��������ǰ��״̬�ͼҽ���Ա��Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����08:21:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getXqxxDetailsByXqid(String xqid) throws Exception{
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xm znxm,t2.xb znxb,t2.nj znnj,");
		sql.append("decode(t1.jjzt , '0','δ�ɳ�','1','����Э����','2','�ѳ���','3','�ѽ�Э����','4','�ѹر�','δ֪״̬') as jjztmc,");
		sql.append("decode(t1.shzt,'0','δ���','1','ͨ��','2','��ͨ��') as shztmc,");
		sql.append("t5.sqid as xssqid, t5.zt as xszt, t5.shzt as xsshzt, ");
		sql.append("t5.sqsj as xssqsj , t5.kssj as xskssj , t5.zsc as xszsc , t5.zfy as xszfy , t5.jjxd as xsjjxd , t5.jzpj as xsjzpj ,");
		sql.append("t7.xm as xsxm ,  t7.xh as xsxh ");
		sql.append("from XSGGFW_JJFW_JZJJXQSQB t1 ");
		sql.append("left join XSGGFW_JJFW_JZZNXXB t2 on t2.znid = t1.znid ");
/*		sql.append("left join XSGGFW_JJFW_JJNJDMB t3 on t1.jjnjdm = t3.jjnjdm ");
		sql.append("left join XSGGFW_JJFW_JJXKDMB   t4 on t1.jjxkdm = t4.jjxkdm ");*/
		sql.append("left join XSGGFW_JJFW_XSJJXQSQB t5 on t1.xqid = t5.xqid and t5.zt = '1' and t5.shzt = '1' ");
		sql.append("left join XG_JJGL_JJLSJGB t6 on t5.xh = t6.xh ");
		sql.append("left join view_xsjbxx t7 on t6.xh = t7.xh ");
		sql.append("where t1.xqid = ?");
		params.add(xqid);
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:��ȡ�ҽ�ѧ���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����11:36:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getXqXsSqList(String xqid)throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.* , t2.lxdh ,t2.rddj, t3.xm , t3.xb , t3.nj , t3.xymc, t3.zymc , t3.bjmc  from XSGGFW_JJFW_XSJJXQSQB t1 ");
		sql.append("left join XSGGFW_JJFW_XSKGZGSQB t2 on t2.xh = t1.xh ");
		sql.append(" left join view_xsjbxx t3 on t3.xh = t1.xh ");
		sql.append(" where t1.xqid = ? ");
		sql.append(" and t1.zt = '1' and t1.shzt = '0'");
		
		params.add(xqid);
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:����ҽ���ʦ�����ø���״̬
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����11:36:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqid xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean updateForAssign(String xqid , String xh)throws Exception {
		String batchUpdate = "update XSGGFW_JJFW_XSJJXQSQB a set a.shzt = '2' where a.xqid = ? and a.xh <> ? and a.zt = '1'";
		
		//String update = "update XSGGFW_JJFW_XSJJXQSQB a set a.shzt = '1' where a.xqid = ? and a.xh = ? and a.zt = '1'";
		
		
		return  dao.runUpdate(batchUpdate, new String[]{xqid , xh});// && 
				//dao.runUpdate(update, new String[]{xqid , xh});
		
	}
	
	
	/**
	 * 
	 * @����:����ID �� ״̬ ��ȡ��ѧ�������¼
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����03:55:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String , String>> �������� 
	 * @throws
	 */
	public int getJjzXqListByXh(String xh) throws Exception{
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) as rs from XSGGFW_JJFW_XSJJXQSQB t1 ");
		sql.append("  left join XSGGFW_JJFW_JZJJXQSQB t2 ");
		sql.append("    on t1.xqid = t2.xqid ");
		sql.append("where t1.zt = '1'and t1.shzt = '1'   and (t2.jjzt = '1' or t2.jjzt = '2')  and xh = ?");
		params.add(xh);
		return Integer.valueOf(dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "rs"));
	}
	
	/**
	 * 
	 * @����:�ڸ������Ƽ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����03:46:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkZgs(String xh) throws Exception{
		boolean isSuccess = false;
		CsszService csszService = new CsszService();
		Map<String , String> configMap = csszService.getConfigMap();
		int zgs = Integer.valueOf(configMap.get("zgs") == null ? "-1" : configMap.get("zgs"));
		
		if(zgs == -1){
			isSuccess = true;
		}else{
			int rs = getJjzXqListByXh(xh);
			
			if(rs < zgs)
				isSuccess = true;
			else
				isSuccess = false;
		}
		return isSuccess;
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XqwhForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,decode(t.xjjzt , '0', 'δ�ɳ�', '1', '����Э����', '2', '�ѳ���', '3', '�ѽ�Э����', '4', '�ѹر�','δ֪״̬') AS jjztmc");
		sql.append(" from ( select t1.*,decode(t1.jjzt,'0','��','��') sfypjj, decode(t1.jjzt ,'3','��','��') sfyjxys,t2.xm znxm,t2.xb znxb,t2.nj znnj,");
		sql.append("CASE WHEN t1.jjzt = '1' AND t5.SJQ IS NOT NULL AND floor(to_number(sysdate - to_date(t5.kssj, 'yyyy-MM-dd HH24:mi:ss'))) > t5.SJQ THEN '2' ELSE t1.jjzt END xjjzt,");
		sql.append("t5.sqsj as xssqsj , t5.kssj as pcsj ,t5.jxyssj,t5.jssj gbsj, t5.zsc as xszsc , t5.jjxd as xsjjxd , t5.jzpj as xsjzpj ,");
		sql.append("t5.sqid as xssqid, t5.zt as xszt, t5.shzt as xsshzt, ");
		sql.append("t6.xm as jjlsxm ,  t6.xh as jjlsxh ");

//		sql.append("(select count(1) from XSGGFW_JJFW_XSJJXQSQB tt1 where tt1.xqid = t1.xqid and tt1.zt = '1' and tt1.shzt = '0') as xssqrs, ");
//		sql.append("(select count(1) from XSGGFW_JJFW_TSFKB tt2 where tt2.xqid = t1.xqid) as jztsxx, ");
//		sql.append("(select count(1) from XSGGFW_JJFW_JZPJB tt3 where tt3.xqid = t1.xqid) as jzpjxx, ");

		sql.append("from XSGGFW_JJFW_JZJJXQSQB t1 ");
		sql.append("left join XSGGFW_JJFW_JZZNXXB t2 on t2.znid = t1.znid ");
/*		sql.append("left join XSGGFW_JJFW_JJNJDMB t3 on t1.jjnjdm = t3.jjnjdm ");
		sql.append("left join XSGGFW_JJFW_JJXKDMB   t4 on t1.jjxkdm = t4.jjxkdm ");*/
		sql.append("left join XSGGFW_JJFW_XSJJXQSQB t5 on t1.xqid = t5.xqid and t5.zt = '1' ");
		sql.append("left join XG_JJGL_JJLSJGB t7 on t5.xh = t7.xh ");
		sql.append("left join view_xsjbxx t6 on t7.xh = t6.xh ");
		sql.append(") t ");

		//δ�ɳ�
		if(StringUtils.equals("0", t.getType())){
			sql.append("where t.jjzt = '0'");
		//����Э����
		}else if(StringUtils.equals("1", t.getType())){
			sql.append("where t.jjzt = '1' ");
		//�ѳ���
		}else if(StringUtils.equals("2", t.getType())){
			sql.append("where t.jjzt = '2' ");
		//�ѽ�Э����
		}else if(StringUtils.equals("3", t.getType())){
			sql.append("where t.jjzt = '3' ");
		//�ѹر�
		}else{
			sql.append("where t.jjzt = '4' ");
		}
		
		sql.append(" and t.shzt = '1'");
		if (!StringUtil.isNull(t.getJjxk())){
			sql.append(" and t.jjxk = ? ");
			params.add(t.getJjxk());
		}
		if (!StringUtil.isNull(t.getJjnj())){
			sql.append(" and t.jjnj = ? ");
			params.add(t.getJjnj());
		}
		if(!StringUtil.isNull(t.getXqid())){
			sql.append(" and t.xqid = ? ");
			params.add(t.getXqid());
		}
		if(!StringUtil.isNull(t.getJjlsxm())){
			sql.append(" and t.jjlsxm like '%'||?||'%' ");
			params.add(t.getJjlsxm());
		}

		if(!StringUtil.isNull(t.getSfypjj())){
			sql.append(" and t.sfypjj =? ");
			params.add(t.getSfypjj());
		}
		if(!StringUtil.isNull(t.getSfyjxys())){
			sql.append(" and t.sfyjxys = ? ");
			params.add(t.getSfyjxys());
		}
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/**
	 * 
	 * @����:��ȡ�û�Ͷ����Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����05:09:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getTsxxList(String xqid) throws Exception{
		String sql = "select t1.* from XSGGFW_JJFW_TSFKB t1 where t1.xqid = ? ";
		return dao.getListNotOut(sql, new String[]{xqid});
	}
	
	
	/**
	 * 
	 * @����:����Ͷ����Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-30 ����10:47:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public int updateTsxx(XqwhForm model) throws Exception{
		String tsid = model.getTsid();
		String fkxx = model.getFkxx();
		String fksj = model.getFksj();
		String sql = "update XSGGFW_JJFW_TSFKB set fkxx = ? , fksj = ? where tsid = ? ";
		
		return dao.update(sql, new String[]{fkxx , fksj , tsid});
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����05:39:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String , String> getPjxxList(String xqid) throws Exception{
		String sql = "select t1.* , decode(t1.pjzs , '5' , '�ǳ�����' , '4' , '����' , '3' , '������' , '2' , 'һ��' , '1' , '������' , '') as pjzsmc from XSGGFW_JJFW_JZPJB t1 where t1.xqid = ? ";
		return dao.getMapNotOut(sql, new String[]{xqid});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XqwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XSGGFW_JJFW_JZJJXQSQB");
		super.setKey("xqid");
		super.setClass(XqwhForm.class);
	}

	/**
	 * �ҽ��������ӣ��ı���
	 * @param xqwhForm
	 * @return
	 */
	public boolean xqwhSaveForAdd(XqwhForm xqwhForm) throws Exception {

		String sql = "INSERT INTO XSGGFW_JJFW_JZJJXQSQB (XQID,SQR,ZNID,JJXK,JJSJ,JJDD,JJLSYQ,BZ,JJNJ,SQSJ,DJR,JJSX,SHZT,JJZT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.runUpdate(sql,new String[]{xqwhForm.getXqid(),xqwhForm.getSqr(),xqwhForm.getZnid(),xqwhForm.getJjxk(),xqwhForm.getJjsj(),xqwhForm.getJjdd(),
		xqwhForm.getJjlsyq(),xqwhForm.getBz(),xqwhForm.getJjnj(),xqwhForm.getSqsj(),xqwhForm.getDjr(),xqwhForm.getJjsx(),xqwhForm.getShzt(),xqwhForm.getJjzt()});
	}

	/**
	 * ���ݹ�������xqid
	 * yyyyMMdd+��λ��ˮ��
	 * @return
	 */
	public String getXqidAsKey() {

		String sql = "select nvl(max(XQID)+1,to_char(sysdate,'yyyyMMdd')||'0001') xqid FROM XSGGFW_JJFW_JZJJXQSQB WHERE XQID LIKE to_char(sysdate,'yyyyMMdd')||'%'";
		return dao.getOneRs(sql,new String []{},"xqid");
	}

	/**
	 * ��ѯ�����ҽ�������Ϣ
	 * @param xqid
	 * @return
	 */
	public Map<String,String> getXqwhMap(String xqid) {

		StringBuilder sbl = new StringBuilder();
		sbl.append("SELECT a.*,b.XM jzxm,b.lxdh,c.xm znxm,c.xb znxb FROM XSGGFW_JJFW_JZJJXQSQB a ");
		sbl.append("LEFT JOIN XSGGFW_JJFW_YHZCXXB b ON a.sqr = b.YHM ");
/*		sbl.append("LEFT JOIN XSGGFW_JJFW_JJNJDMB d ON a.JJNJDM = d.JJNJDM ");
		sbl.append("LEFT JOIN XSGGFW_JJFW_JJXKDMB e ON a.JJXKDM = e.JJXKDM ");*/
		sbl.append("LEFT JOIN XSGGFW_JJFW_JZZNXXB c ON a.ZNID = c.ZNID WHERE a.XQID = ?");
		return dao.getMapNotOut(sbl.toString(),new String[]{xqid});
	}

	/**
	 * �ҽ������޸ģ��ı���
	 * @param xqwhForm
	 * @return
	 */
	public boolean xqwhSaveForEdit(XqwhForm xqwhForm) throws Exception {

		String sql = "UPDATE XSGGFW_JJFW_JZJJXQSQB SET ZNID = ?,JJXK = ?,JJSJ = ?,JJDD = ?,JJLSYQ = ?,BZ = ?,JJNJ = ?,JJSX = ? WHERE XQID = ?";
		return dao.runUpdate(sql,new String[]{xqwhForm.getZnid(),xqwhForm.getJjxk(),xqwhForm.getJjsj(),xqwhForm.getJjdd(),xqwhForm.getJjlsyq(),xqwhForm.getBz(),
		xqwhForm.getJjnj(),xqwhForm.getJjsx(),xqwhForm.getXqid()});
	}

	/**
	 * �ҽ�����ɾ��
	 * @param ids
	 * @return
	 */
	public int xqwhDel(String[] ids) throws Exception {

		StringBuilder sbl = new StringBuilder();
		sbl.append("DELETE FROM XSGGFW_JJFW_JZJJXQSQB WHERE XQID IN (");
			for(int i=0;i<ids.length;i++){
				sbl.append("?");
				if(i != ids.length - 1){
					sbl.append(",");
				}
			}
		sbl.append(")");
		return dao.runDelete(sbl.toString(),ids);
	}

	/**
	 * ���¼ҽ�״̬
	 * @param xqwhForm
	 * @return
	 */
	public boolean runUpdateJjzt(XqwhForm xqwhForm) throws Exception {

		String sql = "UPDATE XSGGFW_JJFW_JZJJXQSQB SET jjzt = ?,yjjzt = jjzt WHERE XQID = ?";
		return dao.runUpdate(sql,new String[]{xqwhForm.getJjzt(),xqwhForm.getXqid()});
	}

	/**
	 * ���¼ҽ�״̬�����һ�����ͨ��֮ǰ��״̬
	 * @param xqid
	 * @return
	 */
	public boolean updateJjztForCancel(String xqid) throws Exception {

		String sql = "UPDATE XSGGFW_JJFW_JZJJXQSQB SET jjzt = yjjzt WHERE XQID = ?";
		return dao.runUpdate(sql,new String[]{xqid});
	}
}
