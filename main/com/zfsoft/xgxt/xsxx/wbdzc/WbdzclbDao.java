/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����10:57:51 
 */  
package com.zfsoft.xgxt.xsxx.wbdzc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-8 ����10:57:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

	public class WbdzclbDao extends SuperDAOImpl<WbdzclbForm> {
		/*
    		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
		 */
		@Override
		/**
		 * ���δ����ע������
		 */
		public List<HashMap<String, String>> getPageList (WbdzclbForm t)
			throws Exception {
			List<String> params = new ArrayList<String>();
			StringBuilder sql = new StringBuilder();
			sql.append(" select wbdlbdm,wbdlbmc from xg_xsxx_bdzc_wbdlb a where 1 = 1 ");
			if (!StringUtil.isNull(t.getWbdlbmc())) {
				params.add(t.getWbdlbmc());
				sql.append("  and wbdlbmc like '%'||?||'%'");
			}
			return getPageList(t, sql.toString(),params.toArray(new String[] {}));
		}
		
		
		/*
		      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
		 */
		
		
		@Override
		public List<HashMap<String, String>> getPageList(WbdzclbForm t,
				User user) throws Exception {
			// TODO �Զ����ɷ������
			return null;
		}
		
		
		/*
	      	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
		 */

			@Override
			protected void setTableInfo() {	
				super.setTableName("xg_xsxx_bdzc_wbdlb");
				super.setKey("wbdlbdm");
			}
			
			
		/**
		 * @����: 	��������
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�2016-3-10 ����09:14:55
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param model
		 * @return
		 * @throws Exception
		 * boolean �������� 
		 * @throws
		 */
			public boolean addLxInfo(WbdzclbForm model) throws Exception {
				boolean wbdzc = false;
				String sql;
				sql = " select count(1) num from xg_xsxx_bdzc_wbdlb where wbdlbmc=?  ";
				String num = dao.getOneRs(sql,new String[] {model.getWbdlbmc()}, "num");
				if ("0".equals(num)) {
					sql = " insert into xg_xsxx_bdzc_wbdlb(wbdlbdm,wbdlbmc) values(?,?)";
					wbdzc = dao.runUpdate(sql, new String[] {model.getWbdlbdm(),model.getWbdlbmc()});
				} else {
					// msg="��δ����ע�����������Ѵ��ڣ�";
					throw new SystemException(MessageKey.XSXX_WBDZC_WBDLBMC);
				}			
				return wbdzc;
			}
			
			
		/**
		 * @����:	δ����ע���޸�
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�2016-3-10 ����09:24:14
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param model
		 * @return
		 * @throws Exception
		 * boolean �������� 
		 * @throws
		 */
			public boolean updateLxInfo(WbdzclbForm model)
			throws Exception {
				boolean wbdzc = false;
				String sql1;
				String sql;
				sql1 = "select count(1) num from xg_xsxx_bdzc_wbdlb where wbdlbmc=? ";
				String num = dao.getOneRs(sql1,new String[] { model.getWbdlbmc() }, "num");
				//����ͬ���������ƺ����ʹ���  ���������Ƿ��Ѵ���
				sql = "select wbdlbdm  from xg_xsxx_bdzc_wbdlb where wbdlbdm = ? and wbdlbmc=? ";
				String wbdlbdm = dao.getOneRs(sql, new String[] { model.getWbdlbdm(),model.getWbdlbmc()},"wbdlbdm");
				if ((wbdlbdm.equals(model.getWbdlbdm())) || "0".equals(num)) {
					sql = "update xg_xsxx_bdzc_wbdlb set wbdlbmc=? where wbdlbdm=?";
					wbdzc = dao.runUpdate(sql, new String[] { model.getWbdlbmc(),model.getWbdlbdm()});
				} else {
					// msg="��δ����ע�����������Ѵ��ڣ�";
					throw new SystemException(MessageKey.XSXX_WBDZC_WBDLBMC);
				}
				return wbdzc;
			}
			
			
		/**
		 * @����:	��ȡ������ʹ���
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�2016-3-10 ����09:25:07
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * String �������� 
		 * @throws
		 */
			public String getWbdlbdm() {
				String sql = "select max(to_number(wbdlbdm)) max from xg_xsxx_bdzc_wbdlb ";
				return dao.getOneRs(sql, new String[]{}, "max");
			}
			
			
		/**
		 * @����:TODO(������һ�仰�����������������)
		 * @���ߣ�����[���ţ�982]
		 * @���ڣ�2016-3-10 ����09:48:31
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param wbdlbdm
		 * @return
		 * boolean �������� 
		 * @throws
		 */
			public boolean isCanDel(String wbdlbdm){
				StringBuffer sql = new StringBuffer();
				sql.append(" select distinct a.wbdlbmc from xg_xsxx_bdzc_wbdlb a left join ");
				sql.append(" xg_xsxx_bdzcb b on a.wbdlbdm=b.wbdlbdm ");
				sql.append(" where a.wbdlbdm  = ?   and  ( b.wbdlbdm is not null ) " );
				Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{wbdlbdm});
				String wbdlbmc = map.get("wbdlbmc");
				//���δ�ύ�ſ����ύ
				return wbdlbmc==null?true:false;
			}
			
			
			/**
			 * �ж��Ƿ���ڽ��Ӧ��
			 * ����
			 */
			public String[] pdsfsy( String value) throws Exception{
				
				StringBuilder sql = new StringBuilder(" select distinct b.wbdlbmc from xg_xsxx_bdzcb a,xg_xsxx_bdzc_wbdlb b where a.wbdlbdm = b.wbdlbdm and a.wbdlbdm in (" +value +") ");
				String[] xmmc=dao.getRs(sql.toString(), new String[]{}, "wbdlbmc");
					
				return xmmc;
			}
			
			
			/**
			 * @����:	�ж�δ������������Ƿ����
			 * @���ߣ�	����[���ţ�1186]
			 * @���ڣ�	2016-3-16 ����10:02:01
			 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
			 * @param model
			 * @return
			 * String �������� 
			 * @throws
			 */
			public String checkExistForSave(WbdzclbForm model) {
				StringBuilder sql = new StringBuilder(" select count(1) num from xg_xsxx_bdzc_wbdlb where wbdlbmc = ? ");
				String num=dao.getOneRs(sql.toString(), new String[]{model.getWbdlbmc()}, "num");
				return num;	
			}
			
			
			/**
			 *�޸ı���ʱ���ж��Ƿ���ڼ�¼
			 *����
			 */
			public String checkExistForUpdate(WbdzclbForm model) {
				String sql=" select count(1) num from xg_xsxx_bdzc_wbdlb where wbdlbmc = ? and wbdlbdm <> ?";
				String num=dao.getOneRs(sql.toString(), new String[]{model.getWbdlbmc(),model.getWbdlbdm()}, "num");
				return num;	
			}
			
			
}
