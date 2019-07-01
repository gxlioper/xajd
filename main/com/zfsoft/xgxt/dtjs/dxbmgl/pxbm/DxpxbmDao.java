package com.zfsoft.xgxt.dtjs.dxbmgl.pxbm;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @������������У��ѵ����dao
 * @author������ ��1346��
 * @date��2017-11-1 ����03:12:34 
 */
public class DxpxbmDao extends SuperDAOImpl<DxpxbmForm> {

	@Override
	public List<HashMap<String, String>> getPageList(DxpxbmForm t) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DxpxbmForm t, User user) throws Exception {
		//String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		//String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer("select * from (select x.*,y.xm fbrxm,y.lxdh,z.*,case when z.xh is not null then '1' else '0' end sfbm,");
		sql.append(" case when z.xh is not null then '�ѱ���' else 'δ����' end sfbmmc,decode(z.shzt,'0','δ���','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����','δ���') shztmc ");
		sql.append(" from xg_dtjs_dxpxb x left join (select a.yhm,a.xm,b.lxdh from yhb a left join fdyxxb b on a.yhm=b.zgh) y on x.fbr=y.yhm ");
		sql.append(" left join (select * from xg_dtjs_dxbmsqb where xh=?) z on x.id=z.pxid) a where 1=1 ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		//sql.append(searchTj);
		return this.getPageList(t, sql.toString(), new String[]{user.getUserName()});
	}
	
	/** 
	 * @�������������°ٷֱ�
	 * @author������ ��1346��
	 * @date��2017-11-6 ����07:09:59 
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateBm(DxpxbmForm t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("insert into xg_dtjs_dxbmsqb(sqid,pxid,xh,splc,shzt,sqsj) values (?,?,?,?,'5',to_char(sysdate,'yyyy-mm-dd'))");
		return dao.runUpdate(sb.toString(), new String[]{t.getSqid(),t.getPxid(),t.getXh(),t.getSplc()});
	}
	/** 
	 * @������������ȡ��������
	 * @author������ ��1346��
	 * @date��2017-11-7 ����09:53:02 
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String getSplc() throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select shl from XG_DTJS_DXBMSHLB");
		return dao.getOneRs(sb.toString(), new String[]{}, "shl");
	}
	@Override
	protected void setTableInfo() {
		this.setKey("sqid");
		this.setTableName("xg_dtjs_dxbmsqb");
		this.setClass(DxpxbmForm.class);
	}

}
