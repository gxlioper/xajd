/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-25 ����09:11:33 
 */  
package com.zfsoft.xgxt.wjcf.cfyydmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.wjcf.cfsb.CfsbglForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (����ԭ�����ά��) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-25 ����09:07:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfyydmDao extends SuperDAOImpl<CfyydmForm> {



	public List<HashMap<String, String>> getPageList(CfyydmForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select * from xg_wjcf_cfyydmb where 1=1");
		
		if (!StringUtil.isNull(model.getCfyymc())){
			params.add(model.getCfyymc());
			sql.append(" and cfyymc like '%'||?||'%'");
		}

		
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfyydmForm t, User user)
			throws Exception {
		return null;
	}



	@Override
	protected void setTableInfo() {
		this.setKey("cfyydm");
		this.setTableName("xg_wjcf_cfyydmb");
		this.setClass(CfyydmForm.class);

	}


	/** 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: Υ�͹���ģ��
	 * @�๦������: (����ԭ�������Ƿ����) 
	 * @���ߣ� ������[����:913]
	 * @ʱ�䣺 2013-10-24 ����10:52:35 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
	 */
	public boolean checkIsExist(CfyydmForm myForm) {
		String sql="select cfyymc from xg_wjcf_cfyydmb where cfyymc=?";
		String[] inputVal=null;
		if(myForm.getCfyydm()!=null&&!myForm.getCfyydm().equalsIgnoreCase("")){
			sql+=" and cfyydm<>?";
			inputVal=new String[2];
			inputVal[0]=myForm.getCfyymc();
			inputVal[1]=myForm.getCfyydm();
		}else{
			inputVal=new String[1];
			inputVal[0]=myForm.getCfyymc();
		}
		String cfyymc=dao.getOneRs(sql, inputVal, "cfyymc");
		return cfyymc!=null&&!"".equalsIgnoreCase(cfyymc);
	}
	
	/**
	 * 
	 * @����:(����ԭ�����ɾ��)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-24 ����01:33:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@Override
	public int runDelete(String[] values) throws Exception {
		StringBuilder sql=new StringBuilder();
		sql.append("delete xg_wjcf_cfyydmb a  where (");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("cfyydm=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")and not exists(select 1 from xg_wjcf_wjcfsbb t where a.cfyydm=t.cfyydm)");
		return dao.runDelete(sql.toString(), values);
	}

}
