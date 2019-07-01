/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-13 ����09:11:14 
 */  
package xsgzgl.gygl.gybxgl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�ChenQ-856
 * @ʱ�䣺 2015-7-13 ����09:11:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GybxRyglDao extends SuperDAOImpl<GybxRyglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(GybxRyglForm model)
			throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select dm bxdm,mc bxmc,(select count(1) from XG_GYGL_NEW_GYBXRYB ");
		sql.append("where a.dm=bxdm) ryrs from GYGL_BXLBDMB  a  ) where 1=1 ");
		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(GybxRyglForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setTableName("XG_GYGL_NEW_GYBXRYB");
		super.setClass(GybxRyglForm.class);
	}
    
	public List<HashMap<String, String>> getBxlbYhList(GybxRyglForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		if("0".equals(model.getFlag())){
			sql.append("select * from (select a.yhm,a.xm,a.szbm bmdm,c.bmmc from yhb a left join  ");
			sql.append("zxbz_xxbmdm c on a.szbm=c.bmdm where not exists (select 1 ");
			sql.append("from XG_GYGL_NEW_GYBXRYB b where a.yhm=b.yhm and bxdm=?)) where 1=1");
		}else{
			sql.append("select * from (select a.bxdm,a.yhm,b.xm,b.szbm bmdm,c.bmmc from  ");
			sql.append("XG_GYGL_NEW_GYBXRYB a left join yhb b on a.yhm=b.yhm");
			sql.append(" left join zxbz_xxbmdm c on b.szbm=c.bmdm ) where bxdm=? and 1=1");
		}
		sql.append(searchTj);
		params.add(model.getBxlb());
		params.addAll(Arrays.asList(inputV));
		return getPageList(model,sql.toString(),params.toArray(new String[params.size()]));
	}
	public boolean saveBxry(String bxdm,String yhm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into XG_GYGL_NEW_GYBXRYB values (");
		sql.append("?,? )");
		return dao.runUpdate(sql.toString(), new String[]{bxdm,yhm});
	}
	
	public boolean delBxry(String bxdm,String yhm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  XG_GYGL_NEW_GYBXRYB where ");
		sql.append("bxdm=? and yhm=? ");
		return dao.runUpdate(sql.toString(), new String[]{bxdm,yhm});
	}
}
