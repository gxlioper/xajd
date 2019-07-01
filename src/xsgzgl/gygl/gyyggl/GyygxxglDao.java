/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:46:37 
 */  
package xsgzgl.gygl.gyyggl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy [���ţ�754]
 * @ʱ�䣺 2013-7-30 ����04:46:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyygxxglDao  extends SuperDAOImpl<GyygxxglForm>{

	
	public List<HashMap<String, String>> getPageList(GyygxxglForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		/*sql.append(" select * from ( select a.*,decode(a.zgzt,'','��','0','��','1','��')zgztmc,( select b.xb from dmk_xb b where b.xbdm = a.xb) xbmc,(select b.zwmc from XG_GYGL_NEW_GYYGZWDMB b where b.zwdm = a.zwdm) zwmc" +
				" from XG_GYGL_NEW_GYYGXXB a) a where 1=1 ");*/

		sql.append("select * from VIEW_NEW_DC_GYGL_YGGL where 1=1 ");
		
		sql.append(searchTj);
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(inputV,
				new String[] {}));
	}
	
	public List<HashMap<String, String>> getZwdmList()
	throws Exception {
		String sql = "select zwdm,zwmc from XG_GYGL_NEW_GYYGZWDMB";
		return dao.getList(sql, new String[]{}, new String[]{"zwdm","zwmc"});
	}
	
	
	public HashMap<String, String> getYgxxmap (String ygbh){
		String sql = " select a.*,decode(a.zgzt,'','��','0','��','1','��')zgztmc,( select b.xb from dmk_xb b where b.xbdm = a.xb) xbmc,(select b.zwmc from XG_GYGL_NEW_GYYGZWDMB b where b.zwdm = a.zwdm) zwmc " +
				"from XG_GYGL_NEW_GYYGXXB a where a.ygbh=?";
		return dao.getMapNotOut(sql, new String[]{ygbh});
	}
	
	public List<HashMap<String, String>> getPageList(GyygxxglForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	public String checkYgbh(String ygbh){
		String sql="select count(1) num from XG_GYGL_NEW_GYYGXXB where ygbh=?";
		String num=dao.getOneRs(sql, new String[]{ygbh}, "num");
		return "0".equals(num)?"true":"false";
	}

	protected void setTableInfo() {
		super.setTableName("XG_GYGL_NEW_GYYGXXB");
		super.setKey("ygbh");// ����һ��Ҫ��FORM�е�set����������һ��,����Сд
		super.setClass(GyygxxglForm.class);
	}

}
