/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-10 ����11:09:52 
 */  
package com.zfsoft.xgxt.xlzx.tsxsgl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ѧ��ά��ģ��(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-9-10 ����11:10:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsDao extends SuperDAOImpl<TsxsForm> {
	protected Log logger = LogFactory.getLog(TsxsDao.class);



	/**�����Ƽ���ѧ��һЩ���Ի���ѯ�ֶΡ�������ͨ�÷����ϣ�ͨ�÷�����Ȼ���� 
	 * ����ѧ��ά��ģ��-�����Ƽ���ѧ  ��Ԥ���̶��п���ʵ�ֶ����ѯ��
	 */
	public List<HashMap<String, String>> getPageList(TsxsForm model, User user)
			throws Exception {

		
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*,rownum r  ");

		if("10704".equals(Base.xxdm)){
			sql.append(" ,c.dbfdy ");
		}

		sql.append("  from  VIEW_XLZX_TSXSXX  t ");

		if("10704".equals(Base.xxdm)){
			sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t.BJDM = c.BJDM ");
		}

		sql.append(" where 1=1 ");

		// ��ע״̬��Ϊ��,�����Ƽ���ѧ������Ҫ��ע״̬�ֶ�
		if(!"10704".equals(Base.xxdm)){
			if(StringUtils.isNotNull(model.getGzzt())){
				sql.append(" and gzzt = '" + model.getGzzt() + "' ");	
			}
		}
		//�����Ƽ���ѧ��������ƴ��
		if("10704".equals(Base.xxdm)){
			
			//���ɸ߼���ѯ�������������ֵ 
			StringBuilder kntj = new StringBuilder();
			//�����Ƽ���ѧ�߼���ѯ������ɸѡ����Ԥ���̶���ʵ�ֶ���ѡ��ɸѡ��ʽ��
			SearchModel sm = model.getSearchModel();
			if(null != sm.getSearch_tj_knlx()){
				String[] knVaule = sm.getSearch_tj_knlx();
				kntj.append("and 1=1 and (");
				for(int i = 0;i<knVaule.length;i++){
						kntj.append("knlxdm like '%"+knVaule[i]+"%'");
						if(i != knVaule.length - 1){
							kntj.append(" or ");
						}
				}
				kntj.append(" ) ");
				sm.setSearch_tj_knlx(null);
				
			}
			sql.append(kntj);
		}
		sql.append(searchTjByUser);
		
		sql.append(searchTj);
		
		sql.append(" order by t.xgsj desc ");
		
		return getPageList(model, sql.toString(), inputV);
		
	}
	/**
	 * ����Id��ѯ����ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsDetailByXh(String xh){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.*  ");

		//����Ա����  �����Ƽ���ѧ
		if("10704".equals(Base.xxdm)){
			sql.append(",fdy.fdyxm fdyxmnew ");
		}

		sql.append(" from VIEW_XLZX_TSXSXX a ");

		//����Ա����  �����Ƽ���ѧ
		if("10704".equals(Base.xxdm)){
			sql.append(" left join (select t.bjdm,replace(wm_concat(t1.xm),';',',')  fdyxm from fdybjb t");
			sql.append(" left join fdyxxb t1 on t.zgh = t1.zgh group by t.bjdm) fdy on a.bjdm = fdy.bjdm");
		}
		
		sql.append(" where a.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * ����ѧ�š��ܴβ�ѯ����ѧ����Ϣ�����ϳ���ѧԺ��
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsDetailByXhZc(TsxsForm model){
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.*  ");
		sql.append(" from VIEW_XLZX_TSXSXX a ");
		sql.append(" where a.xh = ? ");
		sql.append(" and a.zc = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXh(),model.getZc()});
	}
	
	/**
	 * ����Id��ѯ����ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsInfoById(String id){
		
		StringBuilder sql=new StringBuilder();
		
		//�����Ƽ���ѧ��ѯ��ʽ���Ի�
		if("10704".equals(Base.xxdm)){
			sql.append(" select a.id,a.xh,a.knlxdm,a.gzzt,");
			sql.append(" (case a.gzzt when '1' then '��ע' when '2' then 'ȡ����ע' else '' end) gzztmc,a.qksm,a.bz,a.cjsj,a.cjr,a.xgsj,a.xgr,a.sjzt,a.clcs,a.lrsj,a.jbqkms,a.gzsj,a.gznr,a.fjid");
			sql.append(" from xg_xlzx_tsxsxxb a where a.sjzt = '1' and a.id = ?");
		}else if("11527".equals(Base.xxdm)){
			sql.append(" select a.id,a.xh,a.knlxdm,a.gzzt,");
			sql.append(" (case a.gzzt  when '1' then 'ѧУ�ص��ע' when '2' then 'ѧԺ�ص��ע' " +
					"when '3' then 'ѧԺԤ������'  when '0' then  'ȡ����ע' else '' end) gzztmc," +
					"a.qksm,a.bz,a.cjsj,a.cjr,a.xgsj,a.xgr,a.sjzt,a.zc,a.yyms");
			sql.append(" from xg_xlzx_tsxsxxb a where a.sjzt = '1' and a.id = ?");
		}else{
			sql.append(" select a.id,a.xh,a.knlxdm,a.gzzt,");
			sql.append(" (case a.gzzt when '1' then '��ע' when '2' then 'ȡ����ע' else '' end) gzztmc,a.qksm,a.bz,a.cjsj,a.cjr,a.xgsj,a.xgr,a.sjzt");
			sql.append(" from xg_xlzx_tsxsxxb a where a.sjzt = '1' and a.id = ?");
			
		}
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * ����������ѧ����Ϣ��
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveTsxsInfo(TsxsForm model)
			throws Exception {
		
		//�����Ƽ���ѧ����ѧ����Ϣ
		if("10704".equals(Base.xxdm)){
			String[] input = {UniqID.getInstance().getUniqIDHash(),model.getXh(),model.getKnlxdm(),model.getQksm(),model.getBz(),model.getGzzt(),model.getClcs(),model.getLrsj(),model.getJbqkms(),model.getGzsj(),model.getGznr(),model.getFjid()};		
			
			boolean flag = false;
			String sql = " insert into xg_xlzx_tsxsxxb (id,xh,knlxdm,qksm,bz,cjsj,xgsj,gzzt,clcs,lrsj,jbqkms,gzsj,gznr,fjid) values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?)";
			flag = dao.insert(sql, input);
			return flag;
		}else if("11527".equals(Base.xxdm)){
			String[] input = {UniqID.getInstance().getUniqIDHash(),model.getXh(),model.getKnlxdm(),model.getQksm(),model.getBz(),model.getGzzt(),model.getFjid(),model.getZc(),model.getYyms()};		
			boolean flag = false;
			String sql = " insert into xg_xlzx_tsxsxxb (id,xh,knlxdm,qksm,bz,cjsj,xgsj,gzzt,fjid,zc,yyms) values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?)";
			flag = dao.insert(sql, input);
			return flag;
		}else{
			String[] input = {UniqID.getInstance().getUniqIDHash(),model.getXh(),model.getKnlxdm(),model.getQksm(),model.getBz(),model.getGzzt()};
		
			boolean flag = false;
			String sql = " insert into xg_xlzx_tsxsxxb (id,xh,knlxdm,qksm,bz,cjsj,xgsj,gzzt) values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?)";
			flag = dao.insert(sql, input);
			return flag;
		}
	}
	
	
	/**
	 * 
	 * @����:ɾ������ѧ����Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-10 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delTsxsById(String[] id) throws Exception {
		if (id == null || id.length == 0){
			logger.error("ɾ������ѧ���������ܽ���!");
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xlzx_tsxsxxb");
		sql.append(" where  ");
		
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("id=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		return dao.runDelete(sql.toString(), id);
	}
	

	/**
	 * 
	 * @����:�����޸�����ѧ����ע״̬
	 * @���ߣ�1004
	 * @���ڣ�2013-9-10 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public boolean updateBatchGzStatus(String[] id,String gzzt) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_xlzx_tsxsxxb set ");
		
		if(!StringUtil.isNull(gzzt)){
			sql.append(" xgsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), gzzt = '" +gzzt+"' where ");
		}
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("id ='"+id[i]+"'");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		boolean flag = dao.runUpdate(sql.toString(), new String []{});
		return  flag;
		
	}
	
	/**
	 * 
	 * @����:�޸�����ѧ����Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-10 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public boolean updateTsxsInfo(TsxsForm model) throws Exception{
		
		//�����Ƽ���ѧ���޸���Ϣ���Ի���
		if("10704".equals(Base.xxdm)){
			HashMap<String,String>  tsxsList = this.getTsxsInfoById(model.getId());
			if(model.getId()==null || model.getId().equals("") || tsxsList==null || tsxsList.size()==0){
				return false;	
			}
			String[] inputValue = new String []{model.getKnlxdm(),model.getQksm(),model.getSjzt(),model.getGzzt(),model.getClcs(),model.getLrsj(),model.getJbqkms(),model.getGzsj(),model.getGznr(),model.getBz(),model.getFjid(), model.getId()};
			StringBuffer sql = new StringBuffer();
			sql.append(" update xg_xlzx_tsxsxxb set knlxdm=?,qksm=?,sjzt=?,gzzt=?,clcs=?,lrsj=?,jbqkms=?,gzsj=?,gznr=?,bz=?,fjid=?  where id = ? ");
			
			boolean flag = dao.runUpdate(sql.toString(), inputValue);
			return flag;
		}else{
			HashMap<String,String>  tsxsList = this.getTsxsInfoById(model.getId());
			if(model.getId()==null || model.getId().equals("") || tsxsList==null || tsxsList.size()==0){
				return false;	
			}
			boolean flag = false;
			StringBuffer sql = new StringBuffer();
			if("11527".equals(Base.xxdm)){
				String[] inputValue = new String []{model.getKnlxdm(),model.getQksm(),model.getSjzt(),model.getGzzt(),model.getYyms(),model.getFjid(), model.getId()};
				sql.append(" update xg_xlzx_tsxsxxb set knlxdm=?,qksm=?,sjzt=?,xgsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ,gzzt = ?,yyms=?,fjid=? where id = ? ");
				flag = dao.runUpdate(sql.toString(), inputValue);
			}else{
				String[] inputValue = new String []{model.getKnlxdm(),model.getQksm(),model.getSjzt(),model.getGzzt(),model.getYyms(), model.getId()};
				sql.append(" update xg_xlzx_tsxsxxb set knlxdm=?,qksm=?,sjzt=?,xgsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ,gzzt = ?,yyms=? where id = ? ");
				flag = dao.runUpdate(sql.toString(), inputValue);
			}
			
			return flag;
		}
	}
	
	public List<HashMap<String, String>> getKnlxList(){
		
		String sql = "select knlxdm,knlxmc from tsxs_knlxb";
		
		return dao.getListNotOut(sql, new String[]{});
		
	}
	
	public List<HashMap<String, String>> getKnlxList(String[] knlxdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select knlxdm,knlxmc from tsxs_knlxb where ");
		
		for (int i = 0 , n = knlxdm.length ; i < n ; i++){
			sql.append("  knlxdm =? ");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), knlxdm);
		
	}
	
	protected void setTableInfo() {
		
	}
	

	public List<HashMap<String, String>> getPageList(TsxsForm t)
			throws Exception {
		return null;
	}
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-4-8 ����05:00:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getZcTsxsInfo(TsxsForm model) {
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.*  ");
		sql.append(" from VIEW_XLZX_TSXSXX a ");
		sql.append(" where a.xh = ? ");
		sql.append(" order by a.xgsj desc  )");
		sql.append(" where rownum=1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXh()});
	}
}
