/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-24 ����10:55:16 
 */  
package com.zfsoft.xgxt.wjcf.cflbdmwh;

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
 * @�๦������: (����������ά��) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-24 ����10:55:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CflbdmwhDao extends SuperDAOImpl<CflbdmwhForm> {


	@Override
	public List<HashMap<String, String>> getPageList(CflbdmwhForm model)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select a.* from (select a.cflbdm, a.cflbmc,a.jsslqsr, replace(nvl(b.lcxx,'�������'),';','->') spl,  replace(replace(replace(a.sfkss, 'no', '��������'), 'xs', 'ѧ��������'), 'js', '��ʦ������') sfkss,sfksqjc, a.ssslgzr,");
		sql.append("  a.sfszcfqx, (case when  a.sfszcfqx='0' then '��' when  a.sfszcfqx='1' then '��' else '��' end) cfqxflag,cfqx,cjsj ");
		sql.append("from XG_WJCF_CFLBDMB a left join ( select splc, mc, lcxx  from (select splc,a.mc,  to_char(WM_CONCAT(c.mc) over(partition by splc order by xh )) lcxx, xh,row_number() over(partition by splc order by xh desc ) as ddd  from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c  where djlx = 'wjcf' and a.id = b.splc   and b.spgw = c.id) b   where ddd = 1  ) b on a.spl = b.splc)a where 1=1  ");
		
		if (!StringUtil.isNull(model.getCflbmc())){
			params.add(model.getCflbmc());
			sql.append(" and cflbmc like '%'||?||'%'");
		}

		
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

	@Override
	public List<HashMap<String, String>> getPageList(CflbdmwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	@Override
	protected void setTableInfo() {
		this.setKey("cflbdm");
		this.setTableName("xg_wjcf_cflbdmb");
		this.setClass(CflbdmwhForm.class);

	}

	/** 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: Υ�͹���ģ��
	 * @�๦������: (������������Ƿ����) 
	 * @���ߣ� ������[����:913]
	 * @ʱ�䣺 2013-10-24 ����10:52:35 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
	 */
	public boolean checkIsExist(CflbdmwhForm myForm) {
		
		String sql="select cflbmc from xg_wjcf_cflbdmb where cflbmc=?";
		String[] inputVal=null;
		if(myForm.getCflbdm()!=null&&!myForm.getCflbdm().equalsIgnoreCase("")){
			sql+=" and cflbdm<>?";
			inputVal=new String[2];
			inputVal[0]=myForm.getCflbmc();
			inputVal[1]=myForm.getCflbdm();
		}else{
			inputVal=new String[1];
			inputVal[0]=myForm.getCflbmc();
		}
		String cflbmc=dao.getOneRs(sql, inputVal, "cflbmc");
		return cflbmc!=null&&!"".equalsIgnoreCase(cflbmc);
	}
	
	/** 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: Υ�͹���ģ��
	 * @�๦������: (���ݴ�������ȡ���������Ϣ)
	 * @���ߣ� �����[����:1004]
	 * @ʱ�䣺 2013-11-26 ����3:44:35 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
	 */
	public  HashMap<String, String> cflbInfoByMc(String cflbmc) {
		String sql="select sfszcfqx,cfqx,qxnsfkzz from xg_wjcf_cflbdmb where cflbmc=?";
		HashMap<String, String> cflbInfo=dao.getMapNotOut(sql, new String[]{cflbmc});
		return cflbInfo;
	}
	
	/** 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: Υ�͹���ģ��
	 * @�๦������: (���ݴ�������ȡ���������Ϣ)
	 * @���ߣ� �����[����:1004]
	 * @ʱ�䣺 2013-11-26 ����3:44:35 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
	 */
	public  HashMap<String, String> cflbInfoById(String cflbdm) {
		String sql="select sfszcfqx,cfqx,qxnsfkzz from xg_wjcf_cflbdmb where cflbdm=?";
		HashMap<String, String> cflbInfo=dao.getMapNotOut(sql, new String[]{cflbdm});
		return cflbInfo;
	}
	
	/**
	 * 
	 * @����:(����������ɾ��)
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
		sql.append("delete xg_wjcf_cflbdmb a  where (");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("cflbdm=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")and not exists(select 1 from xg_wjcf_wjcfsbb t where a.cflbdm=t.cflbdm)");
		return dao.runDelete(sql.toString(), values);
	}

	/** 
	 * @����:���ݴ����������ȡ���ַ���Ȩ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��15�� ����2:19:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cflbdm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getFwqxByLbdm(String cflbdm) {
		String sql = "SELECT CFFWQX FROM XG_WJCF_CFLBDMB WHERE CFLBDM = ?";
		return dao.getOneRs(sql, new String[]{cflbdm}, "CFFWQX");
	}
	
	/**
	 * @description	�� ��ȡΥ�ʹ�������
	 * @author 		�� ������1282��
	 * @date 		��2017-12-1 ����11:14:52
	 * @return
	 */
	public List<HashMap<String,String>> getWjcfmcList(){
		String sql = "select cflbmc as dm,cflbmc as mc from XG_WJCF_CFLBDMB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
}
