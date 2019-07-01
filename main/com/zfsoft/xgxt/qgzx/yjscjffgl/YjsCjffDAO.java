package com.zfsoft.xgxt.qgzx.yjscjffgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ���ѳ�����  �о�����𷢷�dao
 * @���ߣ�xiaxia 
 * @ʱ�䣺2016-05-04 ����09:46:37 
 * @�汾�� V5.1.75
 * @�޸ļ�¼: 
 */
public class YjsCjffDAO  extends SuperDAOImpl<YjsCjffForm>{

	@Override
	public List<HashMap<String, String>> getPageList(YjsCjffForm t)
			throws Exception {
		
		return null;
	}
	
	
	
	/**
	 * ʹ�ø߼���ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(YjsCjffForm model,User user) throws Exception{
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		//Ȩ�޿��� 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//��������ڹ�����Ա
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select b.*,c.bmmc yrdwmc,substr(b.ffny, 0, 4) nd,substr(b.ffny, 6) yf ");
		sql.append(" from XG_QGZX_CJFF_YJS  b  left join view_xg_qgzx_yrdwdmb c on b.yrbm = c.bmdm )where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(searchTjQx);
		return getPageList(model, sql.toString(), inputV);
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_QGZX_CJFF_YJS");
		super.setKey("guid");
	}
	/**
	 *  �Ƿ���ڷ�����Ϣ
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-05-04 ����02:33:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param yrdwdm
	 * @param gwdm
	 * @return
	 * boolean �������� 
	 */
	public boolean isHaveFfxx(String guid,String xh,String xn,String yrdwdm,String gwmc,String ffny){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from XG_QGZX_CJFF_YJS t where t.xh=? and t.xn=? and t.yrbm=? and t.gwmc=? and ffny=? and guid<> nvl(?,'-1')");
		List<HashMap<String, String>> list=dao.getListNotOut(sb.toString(), new String[]{xh,xn,yrdwdm,gwmc,ffny,guid});
		return null==list||list.size()<=0?false:true;
	}
	
	
	
}
