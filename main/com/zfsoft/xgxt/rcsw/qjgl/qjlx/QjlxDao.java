/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjlx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:07:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QjlxDao extends SuperDAOImpl<QjlxForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjlxForm t)
			throws Exception {
		List<String> param=new ArrayList<String>();
		StringBuffer sb=new StringBuffer();
		sb.append("select t.* from xg_rcsw_qjgl_qjlx t");
		sb.append(" where 1=1 ");
		if(StringUtils.isNotNull(t.getQjlxid())){
			sb.append(" and qjlxid=?");
			param.add(t.getQjlxid());
		}
		if(StringUtils.isNotNull(t.getQjlxmc())){
			sb.append(" and qjlxmc=?");
			param.add(t.getQjlxmc());
		}
		return this.dao.getListNotOut(sb.toString(),param.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QjlxForm t, User user)
			throws Exception {
/*		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());*/
		
		StringBuffer sb=new StringBuffer();
		sb.append("select t.*,decode(t.open,'1','����','�ر�') openzt from xg_rcsw_qjgl_qjlx t");
		sb.append(" where 1=1 ");
		return this.getPageList(t, sb.toString(), new String[]{});
	}
	/**
	 * 
	 * @��������ȡ��һ���������id
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:54:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getNextId(){
		String qjlxid=dao.getOneRs("select max(to_number(qjlxid)) qjlxid from xg_rcsw_qjgl_qjlx ",new String[]{},"qjlxid");
		//������������ݣ�Ĭ��Ϊ00
		if(StringUtils.isNull(qjlxid)){qjlxid="00";}
		String newId=String.valueOf(Integer.parseInt(qjlxid)+1);
		if(newId.length()==1){
			newId="0"+newId;
		}
		return newId;
	}
	/**
	 * 
	 * @����:�Ƿ����ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-13 ����02:12:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String qjlxid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_qjgl_qjsq where qjlxid=?");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{qjlxid});
		return null==map||map.size()<=0?true:false;
	}
	/**
	 * 
	 * @����:��֤������ͣ�����Ѿ���ӹ������ٽ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����07:18:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkQjlx(QjlxForm qf){
		String qjlxid=dao.getOneRs("select qjlxid from xg_rcsw_qjgl_qjlx where qjlxmc=?",new String[]{qf.getQjlxmc()},"qjlxid");
		//�����ȡ����Ӧ��id�����������
		//����������
		if(null!=qf.getQjlxid()&&qf.getQjlxid().equals(qjlxid)){return true;}
		return StringUtils.isNotNull(qjlxid)?false:true;
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("qjlxid");
		this.setTableName("xg_rcsw_qjgl_qjlx");
		this.setClass(QjlxForm.class);
	}
	

}
