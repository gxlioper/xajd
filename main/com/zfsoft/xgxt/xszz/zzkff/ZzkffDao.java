/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016��12��27�� ����11:34:16 
 */  
package com.zfsoft.xgxt.xszz.zzkff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������-������Ź���ģ��
 * @�๦������: ѧ������Dao
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2016��12��27�� ����11:34:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzkffDao extends SuperDAOImpl<ZzkffForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzkff");
		super.setKey("id");
		super.setClass(ZzkffForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzkffForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzkffForm t, User user) throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (select t1.id,t2.xh,t2.xm,t2.xb,t2.sfzh,t2.xymc,t2.xydm,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,");
		sql.append("t1.xn,t2.zzmmmc,t2.mz,t2.nj,t2.yhmc,t2.yhkh,t1.wsyhzt,t1.yhfkxx,");
		sql.append("(select xqmc from xqdzb where xqdm = t1.xq) xqmc,t1.xq,");
		sql.append("t1.xmmc,t1.je from xg_xszz_new_zzkff t1 left join VIEW_XSJBXX t2 on t1.xh = t2.xh) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @����:����ѧ�š�ѧ�ꡢѧ�ڡ���Ŀ���Ʋ�ѯ��������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2016��12��28�� ����5:59:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Integer �������� 
	 * @throws 
	 */
	public boolean IsXmmcRepeat(ZzkffForm zzkffForm){
		String sql = "select count(1) count from xg_xszz_new_zzkff where xh = ? and xn = ? and xq = ? and xmmc = ? ";
		String xh = zzkffForm.getXh();
		String xn = zzkffForm.getXn();
		String xq = zzkffForm.getXq();
		String xmmc = zzkffForm.getXmmc();
		String id = zzkffForm.getId();
		List<String> inputList = new ArrayList<String>();
		inputList.add(xh);
		inputList.add(xn);
		inputList.add(xq);
		inputList.add(xmmc);
		
		if(!StringUtil.isNull(zzkffForm.getId())){
			sql += "and id != ?";
			inputList.add(id);
		}
		String [] inputValue = inputList.toArray(new String[]{});
		String count = dao.getOneRs(sql, inputValue, "count");
		return Integer.parseInt(count)>0;
	}
	
	/**
	 * @����:����getModel���Զ���sql�����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2016��12��28�� ����5:59:05
	 * @throws Exception 
	 */
	public ZzkffForm getModel(String id) throws Exception{
		String sql = "select t1.*,t2.xqmc from  xg_xszz_new_zzkff t1 left join xqdzb t2 on t1.xq = t2.xqdm where id = ?";
		return super.getModel(sql, new String[]{id});
	}

}
