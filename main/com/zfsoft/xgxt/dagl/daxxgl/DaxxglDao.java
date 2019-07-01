/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-18 ����04:43:31 
 */  
package com.zfsoft.xgxt.dagl.daxxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.xjyd.XjydDao;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-18 ����04:43:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaxxglDao extends SuperDAOImpl<DaxxglForm> {

	protected void setTableInfo() {
		super.setTableName("xg_xsxx_dagl_daxxb");
		//super.setKey("pk");
		super.setClass(DaxxglForm.class);
	}

	public List<HashMap<String, String>> getPageList(DaxxglForm t)
			throws Exception {
		
		return null;
	}

	public String  getXsszInfo() throws Exception{
		String result="";
		String sql = " select sfxsbyqx from XG_XSXX_DAGL_XSSZ ";
		HashMap<String,String> xsszInfo = dao.getMapNotOut(sql, new String[]{});
		if(xsszInfo!=null){
			result = xsszInfo.get("sfxsbyqx");
		}
		return result;
	}
	public List<HashMap<String, String>> getByqxList(){
		
		String sql = " select byqxdm,byqxmc from xg_xsxx_dagl_byqx";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public List<HashMap<String, String>> getPageList(DaxxglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t","xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*   ");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm  ");
		}
		sql.append(" from view_xsxx_daxx t ");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on t.bjdm = bzr.bjdm");
		}
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by t.dazrsj desc ");
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	public HashMap<String, String> getDaxxInfoByPk(String pk) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from view_xsxx_daxx  where pk=?");
		return dao.getMapNotOut(sql.toString(), new String[]{pk});
	}
	
	public HashMap<String, String> getDaxxTableByPk(String pk) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from XG_XSXX_DAGL_DAXXB  where xh||dazrsj=?");
		return dao.getMapNotOut(sql.toString(), new String[]{pk});
	}

	
	//ѧ��ģ���ڵĲ���List
	public List<HashMap<String, String>> getXsdaclListByBmid(String daqdmb_id,String pk) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.daqdmb_id,a.sx,a.daqdcl_id,(select daqdcl_mc from XG_XSXX_DAGL_DAQDCLB where daqdcl_id = a.daqdcl_id) daqdcl_mc,b.xh,b.dazrsj,b.daqdmb_id,c.fs,c.ys,c.gdrq,c.bz,decode(c.fs,'','N','Y') bdzt");
		sql.append(" from XG_XSXX_DAGL_DAMBCLBDB a left join XG_XSXX_DAGL_DAXXB b on 1=1");
		sql.append(" left join XG_XSXX_DAGL_XSDACLBDB c on b.xh = c.xh and b.dazrsj=c.dazrsj and a.daqdcl_id=c.daqdcl_id");
		sql.append(" where a.daqdmb_id=? and b.xh||b.dazrsj=? order by sx");
		return dao.getListNotOut(sql.toString(), new String[]{daqdmb_id,pk});
	}
	
	//ѧ��ģ����Ĳ���List
	public List<HashMap<String, String>> getXsMbwclListByBmid(String daqdmb_id,String pk) throws Exception{
		
		String sql = "select a.xh,a.dazrsj,a.daqdcl_id,(select daqdcl_mc from XG_XSXX_DAGL_DAQDCLB  where daqdcl_id = a.daqdcl_id) daqdcl_mc,a.sx,a.fs,a.ys,a.gdrq,a.bz  from XG_XSXX_DAGL_XSDACLBDB a where a.daqdcl_id not in (select daqdcl_id from XG_XSXX_DAGL_DAMBCLBDB b where b.daqdmb_id=?) and a.xh||a.dazrsj=? order by a.sx ";
		return dao.getListNotOut(sql, new String[]{daqdmb_id,pk});
	}
	
	
	public boolean updateDaxxgl(DaxxglForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("update XG_XSXX_DAGL_DAXXB t set t.xh=?,t.dazrsj=?, t.dazrfs=?,t.zrfsbh=?,t.dazcsj=?,t.dazcfs=?,t.zcfsbh=?,t.dazjdw=?,t.dazjyb=?,t.dazjdz=?,t.dazjdwlxr=?,");
		sql.append("t.dazjdwdh=?,t.byqxdm=?,t.dwmc=?,t.dwyb=?,t.dwdz=?,t.dwlxr=?,t.dwlxdh=?,t.bz=? where t.xh||t.dazrsj=?");
		String[] input = {t.getXh(),t.getDazrsj(),t.getDazrfs(),t.getZrfsbh(),t.getDazcsj(),t.getDazcfs(),t.getZcfsbh(),t.getDazjdw(),t.getDazjyb(),t.getDazjdz(),t.getDazjdwlxr(),t.getDazjdwdh(),t.getByqxdm(),t.getDwmc(),t.getDwyb(),t.getDwdz(),t.getDwlxr(),t.getDwlxdh(),t.getBz(),t.getXh()+t.getDazrsj()};
		return dao.runUpdate(sql.toString(), input);
	}
	
	public boolean updateDaxxgl(String daqdmb_id,String pk) throws Exception{
		String updateSql = " update XG_XSXX_DAGL_DAXXB t set t.daqdmb_id=? where t.xh||t.dazrsj=?";
		return dao.runUpdate(updateSql, new String[]{daqdmb_id,pk});
	}
	
	public boolean updateBatchDaxxgl(String daqdmb_id,String[] pk) throws Exception{
		StringBuffer updateSql = new StringBuffer();
		updateSql.append(" update XG_XSXX_DAGL_DAXXB t set t.daqdmb_id='"+daqdmb_id+"' where 1=1 and ");
		for(int i=0;i<pk.length;i++){
			if(i==pk.length-1){
				updateSql.append(" t.xh||t.dazrsj= ?");
			}else{
				updateSql.append(" t.xh||t.dazrsj= ? or");
			}
		}
		return dao.runUpdate(updateSql.toString(), pk);
	}

	public int delDaxxgl(String[] pk) throws Exception{
		
		StringBuffer delDaxxSql =new StringBuffer();
		delDaxxSql.append(" delete XG_XSXX_DAGL_DAXXB  where ");
		
		for(int i=0;i<pk.length;i++){
			if(i==pk.length-1){
				delDaxxSql.append(" xh||dazrsj=?");
			}else{
				delDaxxSql.append(" xh||dazrsj=? or");
			}
		}
		return dao.runDelete(delDaxxSql.toString(),pk);
	}
	
	public int delXscl(String[] pk) throws Exception{
		
		StringBuffer delXsclsql =new StringBuffer();
		delXsclsql.append(" delete XG_XSXX_DAGL_XSDACLBDB where ");//ɾ��ѧ��ת������ʱ�����в���
		for(int i=0;i<pk.length;i++){
			if(i==pk.length-1){
				delXsclsql.append(" xh||dazrsj=?");
			}else{
				delXsclsql.append(" xh||dazrsj=? or");
			}
		}
		return dao.runDelete(delXsclsql.toString(),pk);
	}
	
	/**
	 * 
	 * @����:����ѧ���������������Ϣ
	 * @���ڣ�2014-4-25 ����11:37:15
	 * @param xh
	 * @param dazrsj
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXsdaxxBand(String xh,String dazrsj,JSONArray jsonArray) throws Exception{

		//ɾ��ѧ��ת������ʱ�����в���
		String delSql = "delete XG_XSXX_DAGL_XSDACLBDB where xh||dazrsj = ?";
		dao.runDelete(delSql, new String[]{xh+dazrsj});
		
		// ���뵵��������Ϣ
		if(jsonArray.length()!=0 ){			
			String[] sql = new String[jsonArray.length()];			
			for(int i=0;i<jsonArray.length();i++){
				String  daqdcl_id = jsonArray.getJSONObject(i).getString("daqdcl_id"); 
				String  sx = jsonArray.getJSONObject(i).getString("sx"); 
				String  fs = jsonArray.getJSONObject(i).getString("fs"); 
				String  ys = jsonArray.getJSONObject(i).getString("ys"); 
				String  gdrq = jsonArray.getJSONObject(i).getString("gdrq"); 
				String  bz = jsonArray.getJSONObject(i).getString("bz"); 
				sql[i] = "insert into XG_XSXX_DAGL_XSDACLBDB(xh,dazrsj,daqdcl_id,sx,fs,ys,gdrq,bz) values('"+xh+"','"+dazrsj+"','"+daqdcl_id+"','"+sx+"','"+fs+"','"+ys+"','"+gdrq+"','"+bz+"')";
			}
			dao.runBatch(sql);
		}
		return true;
	}
	
	public boolean saveBatchXsdaxxBand(String[] pk,JSONArray jsonArray) throws Exception{
		String[] addSql = new String[(jsonArray.length()-1)*pk.length];
		StringBuffer delSql = new StringBuffer();
		delSql.append("delete XG_XSXX_DAGL_XSDACLBDB where ");
		for(int k=0;k<pk.length;k++){
			if(k==pk.length-1){
				delSql.append(" xh||dazrsj = ? ");
			}else{
				delSql.append(" xh||dazrsj = ? or ");
			}
			String dazrsj = pk[k].substring(pk[k].length()-10, pk[k].length());
			String xh = pk[k].substring(0, pk[k].length()-10);
			for(int i=1;i<jsonArray.length();i++){
				String  daqdcl_id = jsonArray.getJSONObject(i).getString("daqdcl_id"); 
				String  sx = jsonArray.getJSONObject(i).getString("sx"); 
				String  fs = jsonArray.getJSONObject(i).getString("fs"); 
				String  ys = jsonArray.getJSONObject(i).getString("ys"); 
				String  gdrq = jsonArray.getJSONObject(i).getString("gdrq"); 
				String  bz = jsonArray.getJSONObject(i).getString("bz"); 
				addSql[i+4*k-1] = "insert into XG_XSXX_DAGL_XSDACLBDB(xh,dazrsj,daqdcl_id,sx,fs,ys,gdrq,bz) values('"+xh+"','"+dazrsj+"','"+daqdcl_id+"','"+sx+"','"+fs+"','"+ys+"','"+gdrq+"','"+bz+"')";
			}
		}
		dao.runDelete(delSql.toString(), pk);//ɾ��ѧ��ת������ʱ�����в���
		dao.runBatch(addSql);
		return true;
	}

	/** 
	 * @����:ȡ�õ���ά��״̬ͳ��
	 * @���ڣ�2014-4-25 ����02:56:20
	 * @param values
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getDabdxx(DaxxglForm model, User user) throws Exception{
		StringBuffer sql = new StringBuffer();
		HashMap<String, String> backMap = new HashMap<String, String>();
		
		// �й�ѡ��¼�����
		if(StringUtils.isNotNull(model.getValues())){
			
			// ȡ�õ���ά��״̬ͳ��
			sql.append(" select count(pk) ywhdaqdxss,whzt from view_xsxx_daxx where 1=1 ");
			sql.append(" and pk in ( ");
			String[] pks = model.getValues().split(",");
			for (int i=0;i<pks.length ;i++){
				if(i != pks.length-1){
					sql.append( "'" + pks[i].trim() + "', ");
				}else{
					sql.append( "'" + pks[i].trim() + "' ");
				}
			}
			sql.append(" ) ");
			sql.append("  and whzt='��ά��' ");
			sql.append("  group by whzt ");
			backMap = dao.getMapNotOut(sql.toString(), new String[]{});
			
			//ȫѡ�����
		}else{

			//���ɸ߼���ѯ�������������ֵ 
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");

			// ȡ�õ���ά��״̬ͳ��
			sql.append(" select count(pk) ywhdaqdxss,whzt from view_xsxx_daxx where 1=1 ");
			sql.append("  and whzt='��ά��' ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ");
			sql.append(" group by whzt ");
			backMap = dao.getMapNotOut(sql.toString(), inputV);	
			
			// ȡ�õ���ά��״̬ͳ��
			sql = new StringBuffer();
			sql.append(" select count(pk) yxzxss from view_xsxx_daxx where 1=1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			sql.append(" ");
			HashMap<String, String> yxzxss = dao.getMapNotOut(sql.toString(), inputV);	
			backMap.put("yxzxss", yxzxss!=null && yxzxss.size() > 0? yxzxss.get("yxzxss") : "0");
		}
		
		return backMap;
	}

	/**  ���������ύ����������Ϣ
	 * @���ڣ�2014-4-28 ����02:31:55
	 * @param daqdmbId
	 * @param jsonArray
	 * @param myForm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateBatchDaxxgl(String daqdmbId, JSONArray jsonArray,
			DaxxglForm model, User user){

		StringBuilder sql = new StringBuilder();
		// ����SQL
		ArrayList<String> sqlALL = new ArrayList<String>();

		try{
			// �й�ѡ��¼�����
			if (StringUtils.isNotNull(model.getValues())) {

				// �������µ����嵥����ID
				sql.append(" update XG_XSXX_DAGL_DAXXB t set t.daqdmb_id = '"
						+ daqdmbId + "' where 1=1 and ");
				// ����SQL
				StringBuilder sqlPks = new StringBuilder();
				String[] pks = model.getValues().split(",");
				for (int i = 0; i < pks.length; i++) {
					if (i == pks.length - 1) {
						sqlPks.append(" t.xh||t.dazrsj= '" + pks[i].trim()
								+ "' ");
					} else {
						sqlPks.append(" t.xh||t.dazrsj= '" + pks[i].trim()
								+ "' or ");
					}
				}
				sql.append(sqlPks.toString());
				sqlALL.add(sql.toString());

				// ����ɾ���󶨵ĵ����嵥����
				sql = new StringBuilder();
				sql.append(" delete XG_XSXX_DAGL_XSDACLBDB t where 1=1 and ");

				// ����ɾ���󶨵ĵ����嵥����
				sql.append(sqlPks.toString());
				sqlALL.add(sql.toString());
				String pk = "";
				String dazrsj = "";
				String xh = "";
				String insertSql = "";
				
				// ����׷�ӵ����嵥����
				for (int k = 0; k < pks.length; k++) {
					pk = pks[k];
					dazrsj = pk.substring(pk.length() - 10, pk.length());
					xh = pk.substring(0, pk.length() - 10);

					for (int i = 0; i < jsonArray.length(); i++) {
						String daqdcl_id = jsonArray.getJSONObject(i)
								.getString("daqdcl_id");
						String sx = jsonArray.getJSONObject(i).getString("sx");
						String fs = jsonArray.getJSONObject(i).getString("fs");
						String ys = jsonArray.getJSONObject(i).getString("ys");
						String gdrq = jsonArray.getJSONObject(i).getString(
								"gdrq");
						String bz = jsonArray.getJSONObject(i).getString("bz");
						insertSql = "insert into XG_XSXX_DAGL_XSDACLBDB(xh,dazrsj,daqdcl_id,sx,fs,ys,gdrq,bz) values('"
								+ xh
								+ "','"
								+ dazrsj
								+ "','"
								+ daqdcl_id
								+ "','"
								+ sx
								+ "','"
								+ fs
								+ "','"
								+ ys
								+ "','"
								+ gdrq + "','" + bz + "')";
						sqlALL.add(insertSql);
					}
				}

				dao.runBatch((String[]) sqlALL.toArray(new String[sqlALL.size()]));
				// ȫѡ�����
			} else {

				// ���ɸ߼���ѯ�������������ֵ
				String searchTj = SearchService.getSearchTj(model
						.getSearchModel());
				String[] inputV = SearchService.getTjInput(model
						.getSearchModel());
				String searchTjByUser = SearchService.getSearchTjByUser(user,
						"a", "xydm", "bjdm");
				
				// �������µ����嵥����ID
				sql.append(" update XG_XSXX_DAGL_DAXXB t set t.daqdmb_id = '"
						+ daqdmbId + "' where xh||dazrsj in ( ");
				sql.append(" select pk from view_xsxx_daxx where 1=1 ");
				sql.append(searchTjByUser);
				sql.append(" ");
				sql.append(searchTj);
				sql.append(" ) ");		
				dao.runUpdate(sql.toString(), inputV);

				// ����ɾ���󶨵ĵ����嵥����
				sql = new StringBuilder();
				sql.append(" delete XG_XSXX_DAGL_XSDACLBDB t where xh||dazrsj in ( ");
				sql.append(" select pk from view_xsxx_daxx where 1=1 ");
				sql.append(searchTjByUser);
				sql.append(" ");
				sql.append(searchTj);
				sql.append(" ) ");
				dao.runUpdate(sql.toString(), inputV);
				
				//�����뵵���嵥����
				StringBuilder insertSql = new StringBuilder();
				for (int i = 1; i < jsonArray.length(); i++) {
					String daqdcl_id = jsonArray.getJSONObject(i)
							.getString("daqdcl_id");
					String sx = jsonArray.getJSONObject(i).getString("sx");
					String fs = jsonArray.getJSONObject(i).getString("fs");
					String ys = jsonArray.getJSONObject(i).getString("ys");
					String gdrq = jsonArray.getJSONObject(i).getString(
							"gdrq");
					String bz = jsonArray.getJSONObject(i).getString("bz");
					insertSql = new StringBuilder();
					insertSql.append("insert into XG_XSXX_DAGL_XSDACLBDB ( select xh,dazrsj , '" +
							""+ daqdcl_id+ "' daqdcl_id,'"
							+sx+"'sx,'"
							+fs+"' fs,'"
							+ys+"' ys,'"
							+gdrq+"' gdrq,'"
							+bz+"' bz from view_xsxx_daxx  ");
					insertSql.append(" where 1=1 ");
					insertSql.append(searchTjByUser);
					insertSql.append(" ");
					insertSql.append(searchTj);
					insertSql.append(" ) ");
					dao.runUpdate(insertSql.toString(), inputV);
				}
			}

			return true;
		} catch (Exception e) {

			return false;
		}
	}
	
}
