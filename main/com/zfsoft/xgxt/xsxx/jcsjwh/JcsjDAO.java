package com.zfsoft.xgxt.xsxx.jcsjwh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������:��������ά�����꼶ѧԺרҵ�༶��
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-29
 * @�汾�� V1.0
 */
public class JcsjDAO extends SuperDAOImpl<JcsjForm> {

	/*
	      ����:
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("xh");
		super.setTableName("xsxxb");
		super.setClass(JcsjForm.class);
	}

	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcsjForm model)
			throws Exception {
		return null;
	}
	
	/**
	 * @����: ��ȡѧԺ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-9 ����09:08:01
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyList() {
		// ��ȡѧԺ�б�
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct bmdm xydm, bmmc xymc, substr(f_pinyin(bmmc), 0, 1) || bmmc pyszm ");
		sql.append("   from zxbz_xxbmdm ");
		sql.append("   where bmlb = '5' ");
		sql.append("  order by pyszm " );
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��ȡרҵ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-9 ����09:12:26
	 * @param xydm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyList(String xydm) {
		// ��ȡרҵ�б�
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.zydm,a.zymc,a.bmdm xydm,c.bmmc xymc,substr(f_pinyin(zymc),0,1)||a.zymc pyszm ");
		sql.append(" from bks_zydm a ");
		sql.append(" left join zxbz_xxbmdm c ");
		sql.append(" on a.bmdm = c.bmdm ");
		
		if (!((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all") || xydm.equalsIgnoreCase("%"))) {
			sql.append("where a.bmdm='"+ xydm + "' ");
		}

		sql.append(" order by pyszm ");		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * ȡ����Ҫ�޸ĵĻ���������Ϣ
	 */
	@Override
	public JcsjForm getModel(JcsjForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String xzflg = model.getXzflg();
		// ѧԺ
		if("".equals(xzflg) || "0".equals(xzflg)){
			sql.append(" select * from (" );
			sql.append(" select a.bmdm xydm,a.bmdm xydmold,a.bmmc xymc,nvl(b.xss,0) xss,nvl(c.zys,0) zys,nvl(d.bjs, 0) bjs, a.bmlb  ");		
			sql.append(" from zxbz_xxbmdm a ");		
			sql.append(" left join (select count(xh) xss,xydm ");		
			sql.append("            from view_xsbfxx ");		
			sql.append("            group by xydm ");		
			sql.append("            )b on b.xydm = a.bmdm ");
			sql.append(" left join ( select count(distinct zydm) zys,bmdm ");		
			sql.append("            from bks_zydm ");		
			sql.append("            group by bmdm ");		
			sql.append("            ) c on c.bmdm = a.bmdm ");			
			sql.append(" left join (select count(distinct a.bjdm) bjs, b.bmdm ");		
			sql.append("            from bks_bjdm a ");		
			sql.append("            left join bks_zydm b ");	
			sql.append("                on a.zydm = b.zydm ");		
			sql.append("            group by b.bmdm) d ");	
			sql.append("            on d.bmdm = a.bmdm ");
			sql.append(" )a "); 
			sql.append(" where 1=1 ");
			sql.append(" and xydm = '"+ model.getXydm() +"' ");
			
			//רҵ
		}else if("1".equals(xzflg)){

			sql.append(" select * from (" );
			sql.append(" select a.zydm,a.zydm zydmold,a.zymc,a.bmdm xydmzy,c.bmmc xymc,nvl(b.xss,0) xss,nvl(d.bjs,0) bjs ");
			sql.append("   from bks_zydm a ");
			sql.append("   left join (select count(xh) xss,xydm,zydm ");
			sql.append(" 	   from view_xsbfxx ");
			sql.append(" 	   group by xydm,zydm ");
			sql.append(" 	   )b on b.xydm = a.bmdm and a.zydm = b.zydm ");
			sql.append("   left join zxbz_xxbmdm c ");
			sql.append("   on a.bmdm = c.bmdm ");
			sql.append("   left join ( select count(distinct bjdm) bjs,zydm ");
			sql.append("      from bks_bjdm ");
			sql.append("      group by zydm ");
			sql.append(" ) d on d.zydm = a.zydm ");
			sql.append(" )a "); 
			sql.append(" where 1=1 ");	
			sql.append(" and zydm = '"+ model.getZydm() +"' ");
			
			//�༶
		}else if("2".equals(xzflg)){

			sql.append(" select * from (" );
			sql.append(" select d.bmdm xydmbj, ");
			sql.append("        d.bmmc xymc, ");
			sql.append("        a.zydm zydmbj, ");
			sql.append("        c.zymc, ");
			sql.append("        a.bjdm, ");
			sql.append("        a.bjdm bjdmold, ");
			sql.append("        a.bjmc, ");
			sql.append("        a.nj, ");
			sql.append("        a.tgxydm, ");
			sql.append("        nvl(b.xss, 0) xss ");
			sql.append("   from bks_bjdm a ");
			sql.append("   left join (select count(xh) xss, xydm, zydm, bjdm,nj ");
			sql.append("                from view_xsbfxx ");
			sql.append("               group by xydm, zydm, bjdm,nj) b ");
			sql.append("     on a.zydm = b.zydm ");
			sql.append("    and a.bjdm = b.bjdm ");
			sql.append("    and a.nj = b.nj ");
			sql.append(" left join bks_zydm  c ");
			sql.append("   on a.zydm = c.zydm ");
			sql.append(" left join zxbz_xxbmdm d ");
			sql.append("   on c.bmdm = d.bmdm ");
			sql.append(" )a "); 
			sql.append(" where 1=1 ");
			sql.append(" and bjdm = '"+ model.getBjdm() +"' ");
		}
		logger.debug(sql);
		return getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * ȡ����ҵ�����б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(JcsjForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();
		
		// ѡ��FLG
		String xzflg = model.getXzflg();

		//רҵ
		if("1".equals(xzflg)){

			sql.append(" select * from " );
			sql.append(" VIEW_NEW_DC_XSXX_JCSJWH_ZY ");
			sql.append(" a "); 
			sql.append(" where 1=1 ");		
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			//�༶
		}else if("2".equals(xzflg)){

			sql.append(" select * from " );
			sql.append(" VIEW_NEW_DC_XSXX_JCSJWH_BJ ");
			sql.append(" a "); 
			sql.append(" where 1=1 ");		
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			// ѧԺ
		}else{

			sql.append(" select * from " );
			sql.append(" VIEW_NEW_DC_XSXX_JCSJWH_XY ");
			sql.append(" a "); 
			sql.append(" where 1=1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			
		}
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * @����: �ж��Ƿ��Ѵ���(�����Ƿ��ͻ)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-6 ����04:33:24
	 * @param myForm
	 * @return boolean ��������
	 * @throws
	 */
	public boolean ishasExist(JcsjForm myForm) throws Exception {

		StringBuilder sql = new StringBuilder();

		// ѡ�����(0��ѧԺ��1��רҵ��2���༶)
		String xzflg = myForm.getXzflg();

		// 0��ѧԺ
		if ("".equals(xzflg) || "0".equals(xzflg)) {
			
			// ����޸�ǰ��Ĵ���һ�� �������ж�
			if(myForm.getXydm().equals(myForm.getXydmold())){
				return false;
			}
			
			sql.append(" select count(1) counts ");
			sql.append(" from zxbz_xxbmdm ");
			sql.append(" where bmdm = ? ");

			List<HashMap<String, String>> lst = dao.getListNotOut(sql
					.toString(), new String[] { myForm.getXydm() });

			if (lst != null) {
				return Integer.parseInt(lst.get(0).get("counts")) > 0 ? true
						: false;
			} else {
				return false;
			}

		} else if ("1".equals(xzflg)) {
			
			// ����޸�ǰ��Ĵ���һ�� �������ж�
			if(myForm.getZydm().equals(myForm.getZydmold())){
				return false;
			}

			sql.append(" select count(1) counts ");
			sql.append(" from bks_zydm ");
			sql.append(" where zydm = ? ");

			List<HashMap<String, String>> lst = dao.getListNotOut(sql
					.toString(), new String[] { myForm.getZydm() });

			if (lst != null) {
				return Integer.parseInt(lst.get(0).get("counts")) > 0 ? true
						: false;
			} else {
				return false;
			}
		} else if ("2".equals(xzflg)) {

			// ����޸�ǰ��Ĵ���һ�� �������ж�
			if(myForm.getBjdm().equals(myForm.getBjdmold())){
				return false;
			}
			
			sql.append(" select count(1) counts ");
			sql.append(" from bks_bjdm ");
			sql.append(" where bjdm = ? ");

			List<HashMap<String, String>> lst = dao.getListNotOut(sql
					.toString(), new String[] { myForm.getBjdm() });

			if (lst != null) {
				return Integer.parseInt(lst.get(0).get("counts")) > 0 ? true
						: false;
			} else {
				return false;
			}
		}
		return false;
	}
	/** 
	 * @����: ���ӻ�������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-6 ����04:33:24
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveJcsj(JcsjForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();

		// ѡ�����(0��ѧԺ��1��רҵ��2���༶)
		String xzflg = myForm.getXzflg();

		// 0��ѧԺ
		if ("0".equals(xzflg)) {
			
			sql.append(" insert into zxbz_xxbmdm ");
			sql.append(" values(?,?,'','','1',?,'') ");
			
			return dao.runUpdate(sql.toString(), new String[]{myForm.getXydm(),myForm.getXymc(),myForm.getBmlb()});
			
			// 1��רҵ
		} else if ("1".equals(xzflg)) {

			sql.append(" insert into bks_zydm (zydm,bmdm,xkmldm,zymc)");
			sql.append(" values(?,?,'00',?) ");
			
			return dao.runUpdate(sql.toString(), new String[]{myForm.getZydm(),myForm.getXydmzy(),myForm.getZymc()});
			
			
			//2���༶
		} else if ("2".equals(xzflg)) {

			sql.append(" insert into bks_bjdm (bjdm,zydm,bjmc,nj,tgxydm)");
			sql.append(" values(?,?,?,?,?) ");
			
			return dao.runUpdate(sql.toString(), new String[]{myForm.getBjdm(),myForm.getZydmbj(),myForm.getBjmc(),myForm.getNj(),myForm.getTgxydm()});
		}
		
		return false;
	}

	/** 
	 * @����: ���»�������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-9 ����11:38:44
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updJcsj(JcsjForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		// ѡ�����(0��ѧԺ��1��רҵ��2���༶)
		String xzflg = myForm.getXzflg();

		boolean bolFlg = false;
		
		// ��/0��ѧԺ
		if ("".equals(xzflg) || "0".equals(xzflg)) {
			
			sql.append(" update ZXBZ_XXBMDM ");
			sql.append(" set bmdm='"+myForm.getXydm()+"', bmmc='"+myForm.getXymc()+"' ");
			sql.append(" where bmdm='" + myForm.getXydmold() +"' ");
			
			bolFlg = dao.runUpdate(sql.toString(), new String[]{});
			
			// ����޸�ǰ��Ĵ���һ�� �������ж�
			if(bolFlg && !myForm.getXydm().equals(myForm.getXydmold())){

				sql = new StringBuilder();
				sql.append(" update BKS_ZYDM ");
				sql.append(" set bmdm='" + myForm.getXydm() +"' ");
				sql.append(" where bmdm='" + myForm.getXydmold() +"' ");				
				bolFlg = dao.runUpdate(sql.toString(), new String[]{});
				
			}
			
			// 1��רҵ
		} else if ("1".equals(xzflg)) {

			sql.append(" update BKS_ZYDM ");
			sql.append(" set zydm=?, zymc=?,bmdm=? ");
			sql.append(" where zydm=? ");

			bolFlg = dao.runUpdate(sql.toString(), new String[]{myForm.getZydm(),myForm.getZymc(),myForm.getXydmzy(),myForm.getZydmold()});
			
			// ����޸�ǰ��Ĵ���һ�� �������ж�
			if(bolFlg && !myForm.getZydm().equals(myForm.getZydmold())){

				sql = new StringBuilder();
				sql.append(" update BKS_BJDM ");
				sql.append(" set zydm='" + myForm.getZydm() +"' ");
				sql.append(" where zydm='" + myForm.getZydmold() +"' ");			
				bolFlg = dao.runUpdate(sql.toString(), new String[]{});
			}
			
			//2���༶
		} else if ("2".equals(xzflg)) {

			sql.append(" update BKS_BJDM ");
			sql.append(" set bjdm=?, bjmc=? ,nj=?,zydm=?,tgxydm=? ");
			sql.append(" where bjdm=? ");
			
			bolFlg = dao.runUpdate(sql.toString(), new String[]{myForm.getBjdm(),myForm.getBjmc(),myForm.getNj(),myForm.getZydmbj(),myForm.getTgxydm(),myForm.getBjdmold()});
		}
		
		return bolFlg;
	}

	/**
	 * @����: ɾ����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-9 ����03:29:43
	 * @param xzflg
	 * @param split
	 * @return
	 * int �������� 
	 * @throws Exception  
	 */
	public int runDelete(String xzflg, String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		String tableName = "";
		String key = "";
		
		// ѧԺ
		if("".equals(xzflg) || "0".equals(xzflg)){
			tableName = "ZXBZ_XXBMDM";
			key = "BMDM";
			// 1��רҵ
		}else if ("1".equals(xzflg)) {
			tableName = "BKS_ZYDM";
			key = "ZYDM";
			
			//2���༶
		}else if ("2".equals(xzflg)) {
			tableName = "BKS_BJDM";
			key = "BJDM";
		}
		
		sql.append("delete from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append(key);
			sql.append("=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		logger.debug(sql);
		logger.debug(Arrays.toString(values));
		
		return dao.runDelete(sql.toString(), values);
		
	}

	/** 
	 * @����: ��������б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-19 ����05:22:06
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmlbList() {
		// ��ȡ��������б�
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmlbdm , bmlbmc ");
		sql.append("   from dm_bmlb ");
		sql.append("  order by bmlbdm desc " );
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @����: ��ȡѧԺ/רҵ/�༶�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-25 ����02:31:03
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllList(JcsjForm model, User user) {
		
		// �Ƿ�������ݷ�Χ sfkzSjfw:�Ƿ�������ݷ�Χ[0:����;1:������]	
		String sfkzSjfw = model.getSfkzSjfw();
		
		// �Ƿ���У�������ݼ�sfzx:�Ƿ���У��Χ[0:��Уview_njxyzybj;1:����Уview_njxyzybj_all]
		String sfzx = model.getSfzx();
		
		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		String xzFlg = model.getXzflg();
		
		String tableName = "view_njxyzybj";
		if("1".equals(sfzx)){
			tableName = "view_njxyzybj_all";
		}	    
			
		// ��ȡ��������б�
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		// ѧԺ�б�
		if("0".equals(xzFlg)){
			
			sql.append(" distinct xydm, xymc ");
			
			//רҵ�б�
		}else if("1".equals(xzFlg)){

			sql.append(" distinct zydm, zymc ");
			
			// �༶�б� 
		}else if("2".equals(xzFlg)){

			sql.append(" distinct bjdm, bjmc ");
			
			// �꼶�б�
		}else if("3".equals(xzFlg)){
			
			sql.append(" distinct nj ");			
		}
		
		sql.append("   from " + tableName +" a ");
		sql.append(" where 1=1 ");
		// �꼶����
		if(model.getNj()!=null && !"".equals(model.getNj())){
			sql.append(" and nj = '" + model.getNj() +"'");
		}
		// ѧԺ��������
		if(model.getXydm()!=null && !"".equals(model.getXydm())){
			sql.append(" and xydm = '" + model.getXydm() +"'");
		}
		// רҵ��������
		if(model.getZydm()!=null && !"".equals(model.getZydm())){
			sql.append(" and zydm = '" + model.getZydm() +"'");
		}
		// �༶��������
		if(model.getBjdm()!=null && !"".equals(model.getBjdm())){
			sql.append(" and bjdm = '" + model.getBjdm() +"'");
		}
		
		// �����û������ݷ�Χ
		if("0".equals(sfkzSjfw)){
			
			String userStatus = user.getUserStatus();
			String userName = user.getUserName();
			String userDep = user.getUserDep();
			if ("jd".equalsIgnoreCase(userStatus)) {
				// ���
				sql.append(" and (exists(select 1 from fdybjb b ");
				sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
				sql.append(" or exists(select 1 from bzrbbb b ");
				sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
				
			} else if ("fdy".equalsIgnoreCase(userStatus)) {
				// ����Ա
				sql.append(" and exists(select 1 from fdybjb b ");
				sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			} else if ("bzr".equalsIgnoreCase(userStatus)) {
				// ������
				sql.append(" and exists(select 1 from bzrbbb b ");
				sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			} else if ("xy".equalsIgnoreCase(userStatus)) {
				// ѧԺ
				sql.append(" and a.xydm = '"+userDep+"' ");
			} else if ("stu".equalsIgnoreCase(userStatus)) {
				// ѧ��
				sql.append(" and exists(select 1 from view_xsjbxx b ");
				sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
			}
		}
		// ����
		sql.append("  order by " );

		// ѧԺ�б�
		if("".equals(xzFlg) || "0".equals(xzFlg)){
			
			sql.append(" xydm, xymc ");
			
			//רҵ�б�
		}else if("1".equals(xzFlg)){

			sql.append(" zydm, zymc ");
			
			// �༶�б� 
		}else if("2".equals(xzFlg)){

			sql.append(" bjdm, bjmc ");
			
			// �꼶�б�
		}else if("3".equals(xzFlg)){
			
			sql.append(" nj ");			
		}
		

		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
