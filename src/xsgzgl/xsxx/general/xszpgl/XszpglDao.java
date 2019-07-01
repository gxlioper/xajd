/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-26 ����01:51:13 
 */  
package xsgzgl.xsxx.general.xszpgl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy[����:754]
 * @ʱ�䣺 2013-9-26 ����01:51:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszpglDao extends SuperDAOImpl<XszpglForm>{

	
	public List<HashMap<String, String>> getPageList(XszpglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	//��ѯ�б�
	public List<HashMap<String, String>> getPageList(XszpglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select * from (select a.*,case when b.zp is null then '��' else '��' end sfdr,case when b.xszp is null then '��' else '��' end xssfdr,rownum r from (");
		tableSql.append("select a.* from view_xsxxcxjg a ");
		tableSql.append("where 1=1 and (sfzx='��У' or sfzx is null) ");
		
		tableSql.append(" ) a left join xszpb b on a.xh = b.xh ) a where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh ");
		return getPageList(t, tableSql.toString(), StringUtils.joinStrArr(inputV,
				new String[] {}));
	}

	/**
	 * ������Ƭ����ȡ��֮��Ӧ��ѧ����xh��Map
	 * @param photoNameType ��Ƭ������ʽ��xh��sfzh��ksh
	 * @param photoName ��Ƭ����������׺
	 * @return
	 */
	public HashMap<String, String> getXhByType(String photoNameType,String photoName){
		
		DAO dao=DAO.getInstance();
		String sql = "select xh from view_xsbfxx where " + photoNameType + "=?";
		HashMap<String,String> map = dao.getMap(sql, new String[]{photoName},new String[]{"xh"});
		return map;
	}
	
	/**
	 * ͳ����Ƭ���и�xh�ļ�¼��
	 * @param 
	 * @return
	 */
	public String countDataRowNum(String xh){
		DAO dao=DAO.getInstance();
		String sql = "select count(*) num from xszpb where xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "num");
	}
	
	/**
	 * ������Ƭ������⣬�������
	 * @param lqh ¼ȡ��
	 * @param instream ��Ƭ������
	 * @throws Exception 
	 */
	public void insertPhoto_xs(String lqh,InputStream instream) {
		DAO dao=DAO.getInstance();
		String sql = "insert into xszpb(xszp,xh) values(?,?)";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ������Ƭ������⣬���²���
	 * @param lqh ¼ȡ��
	 * @param instream ��Ƭ������
	 * @throws Exception 
	 */
	public void updatePhoto_xs(String lqh,InputStream instream) {
		
		DAO dao=DAO.getInstance();
		String sql = "update xszpb set xszp = ? where xh = ?";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	
	/**
	 * ��Ƭ������⣬�������
	 * 
	 * @param lqh
	 *            ¼ȡ��
	 * @param instream
	 *            ��Ƭ������
	 * @throws Exception
	 */
	public void insertPhoto(String lqh, InputStream instream) {
		DAO dao = DAO.getInstance();
		String sql = "insert into xszpb(zp,xh) values(?,?)";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * ��Ƭ������⣬���²���
	 * 
	 * @param lqh
	 *            ¼ȡ��
	 * @param instream
	 *            ��Ƭ������
	 * @throws Exception
	 */
	public void updatePhoto(String lqh, InputStream instream) {

		DAO dao = DAO.getInstance();
		String sql = "update xszpb set zp = ? where xh = ?";
		try {
			dao.upBlob(sql, lqh, instream.available(), instream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ����ѧ����Ƭ
	 * @return  List<HashMap<String, String>>
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<HashMap<String, String>> getXszpList(XszpglForm t, User user) 
		throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select * from (select a.*,case when b.zp is null then '��' else '��' end sfdr,case ");
		tableSql.append("when b.xszp is null then '��' else '��' end xssfdr, rownum r from (");
		tableSql.append("select a.* from view_xsxxcxjg a ");
		tableSql.append("where 1=1 and (sfzx='��У' or sfzx is null) ");
		
		tableSql.append(" ) a left join xszpb b on a.xh = b.xh ) a where 1=1  and sfdr='��' ");
		tableSql.append(searchTjByUser);
		tableSql.append(searchTj);
		tableSql.append(" order by nj desc nulls last,xydm,zydm,bjdm,xh ");
		String[] outputValue=new String[]{"xh", "xm","ksh", "sfzh" };
		return dao.getList(tableSql.toString(), inputV, outputValue);
	}
	
	protected void setTableInfo() {
		super.setTableName("");
		super.setKey("xh");// ����һ��Ҫ��FORM�е�set����������һ��,����Сд
		super.setClass(XszpglForm.class);
	}

	
}
