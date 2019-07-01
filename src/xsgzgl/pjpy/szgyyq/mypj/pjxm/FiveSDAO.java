package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

public class FiveSDAO extends CommDAO{

	/**
	 * ��ȡ���Ա���б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getFiveSList(PjpyStuForm model) {

		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ѧ��
		String xh = model.getXh();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id,case when length(a.fzxm) > 6 then ");
		sql.append(" substr(a.fzxm, 0, 6) || '...'  ");
		sql.append("  else a.fzxm end fzxm, a.fzxm allfzxm, a.jfrq, ");
		sql.append("  yy,jfyy,jjf, ");
		
		sql.append(" a.sqf, case when xysh = 'ͨ��' then ");
		sql.append(" xyshf else '0' end xyshf, case when xxsh = 'ͨ��' then ");
		sql.append(" xxshf else '0' end xxshf, case ");
		sql.append(" when clr is not null then '�Ѵ���' ");
		sql.append(" when b.xh is not null then '������' ");
		sql.append(" else '����' end cz,b.ssnr,b.clyj,b.clr, ");
		sql.append(" (select xm from yhb c where c.yhm = b.clr) clrxm, ");
		sql.append(" xysh,xxsh,xyshf xylrf,xxshf xxlrf ");
		sql.append(" from (");
		sql.append(" select a.id,a.xh,a.jfrq,a.jfyy, case when a.fzxm='grszf' then '�������ʷ�' ");
		sql.append(" when a.fzxm='jsssf' then '����������5S��' when a.fzxm='ktf' then '����5S��' ");
		sql.append(" when a.fzxm='cxf' then '���ŷ�' when a.fzxm='qtf' then '������' end fzxm, ");
		sql.append(" a.sqf,nvl(a.xyshf,0) xyshf,nvl(a.xxshf,0) xxshf,a.xysh,a.xxsh,a.jjf, ");
		sql.append(" case when xysh = 'δ���' and xxsh = 'δ���' then 'yes' else 'no' end shzt, ");
		sql.append(" (select b.mc from(select mc,dm from szyc_jjfyyb  ");
		sql.append("  union  select '����','other' from dual )b ");
		sql.append(" where a.yy=b.dm) yy ");
		sql.append(" from szyc_5sb a ");
		sql.append(" where xn = ? ");
		sql.append(" and xq = ? ");
		sql.append(" and xh = ? ");
		sql.append(") a ");
		sql.append(" left join xg_pjpy_szgyyq_xsssb b on ");
		sql.append(" a.xh = b.xh and a.id = b.xmid ");
		sql.append(" order by jfrq ");
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "id", "fzxm",
						"allfzxm", "jfrq","yy", "jfyy","jjf","sqf",
						 "xyshf", "xxshf", "cz", "ssnr", "clyj",
						"clrxm", "xysh", "xxsh","xylrf","xxlrf" });

		System.out.println(sql);
//		new String[] { "id", "fzxm", "jfrq","yy","jfyy", "jjf", "sqf", "xyshf",
//				"xysh", "xxshf", "xxsh","shzt" }
		return list;
	}
	
	
	/**
	 * ������ʵ����Ϣ�б�
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getFiveSList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		CommService commService = new CommService();
		SearchModel searchModel = model.getSearchModel();

		// ѧ��
		String xh = model.getXh();
		// �༶
		String bjdm = getOneValue("view_xsjbxx", "bjdm", "xh", xh);
		// ѧ��
		String xn = searchModel.getSearch_tj_xn()[0];
		// ѧ��
		String xq = searchModel.getSearch_tj_xq()[0];
		// ����
		String[] cz = searchModel.getSearch_tj_cz();
		// ģ����ѯ
		String input_mhcx = searchModel.getInput_mhcx();
		// ��ѯ����
		String mhcx_lx = searchModel.getMhcx_lx();
		// �û�����
		String yhlx = model.getYhlx();
		
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("( select a.xh,a.xm,a.sqf,a.zzf,a.cz,a.clr,");
		tableSql.append("a.clyj,a.clsj,a.tsnr,a.clrxm,a.nj,a.xydm,a.zydm,a.bjdm from ");
		tableSql.append("( ");
		tableSql.append("select a.xh, a.xm, nvl(b.sqf, 0) sqf, nvl(b.zzf, 0) zzf, ");
		tableSql.append("'"+xn+"' xn,'"+xq+"' xq, ");
		tableSql.append("case when a.xh = '" + xh + "' then '�鿴' ");
		tableSql.append("when c.clr is not null then '�Ѵ���' ");
		tableSql.append("when c.xh is not null then '��Ͷ��' else '��Ͷ��' end cz, ");
		tableSql.append("c.clr,c.clyj,c.clsj,c.tsnr,a.nj,a.xydm,a.zydm,a.bjdm, ");
		tableSql.append("(select xm from yhb d where d.yhm = c.clr) clrxm ");
		tableSql.append("from (select a.xh,a.xm,a.nj,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where 1=1 ");
		tableSql.append("bz".equalsIgnoreCase(yhlx)||"stu".equalsIgnoreCase(yhlx) ? " and a.bjdm = '" + bjdm + "'" : "");
		tableSql.append(") a ");
		tableSql.append("left join (select b.xh, sum(b.sqf) sqf, sum(b.fz) zzf from ( ");
		tableSql.append(" select xh,case when jjf='����' and b.fz is not null then '-'||b.fz  else fz end fz, ");
		tableSql.append(" case when jjf='����'  and b.sqf is not null then '-'||b.sqf  else sqf end sqf ");
		tableSql.append("from szyc_5sb b ");
		tableSql.append("where b.xn = '"+xn+"' ");
		tableSql.append("and b.xq = '"+xq+"' )b ");
		tableSql.append("group by b.xh) b on a.xh = b.xh ");
		tableSql.append("left join (select c.xh, c.btsr, c.tsnr, c.tssj, c.clr, c.clyj, c.clsj ");
		tableSql.append("from xg_pjpy_szgyyq_xstsb c ");
		tableSql.append("where c.xmlx = 'szyc_5sb' ");
		if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {
			tableSql.append("and c.xh = '" + xh + "' ");
		} else {
			tableSql.append("and exists (select 1 ");
			tableSql.append("from (select btsr, max(tssj) tssj ");
			tableSql.append("from xg_pjpy_szgyyq_xstsb ");
			tableSql.append("where xmlx = 'szyc_5sb' ");
			tableSql.append("and xn = '" + xn + "' ");
			tableSql.append("and xq = '" + xq + "' ");
			tableSql.append("group by btsr) d ");
			tableSql.append("where c.btsr = d.btsr ");
			tableSql.append("and c.tssj = d.tssj) ");
		}
		tableSql.append("and c.xn = '"+xn+"' ");
		tableSql.append("and c.xq = '"+xq+"') c on a.xh = c.btsr ");
		tableSql.append("order by zzf,xh ");
		tableSql.append(") a ");
		tableSql.append("where 1 = 1 ");

		String[] inputV = new String[]{};
		
		if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {//�೤
			// ����
			if (cz != null && cz.length > 0) {
				tableSql.append("and ( ");
				for (int i = 0; i < cz.length; i++) {
					if (i != 0) {
						tableSql.append(" or ");
					}
					tableSql.append("a.cz = '" + commService.unicode2Gbk(cz[i])
							+ "'");
				}
				tableSql.append(") ");
			}

			// ģ����ѯ
			if (!Base.isNull(input_mhcx.trim())) {

				String[] mhcx = input_mhcx.split(" ");

				if (!"all".equalsIgnoreCase(mhcx_lx)) {

					tableSql.append("and ( ");
					for (int i = 0; i < mhcx.length; i++) {
						if (i != 0) {
							tableSql.append(" or ");
						}
						tableSql.append("a." + mhcx_lx + " like '%" + mhcx[i]
								+ "%'");
					}
					tableSql.append(") ");

				} else {

					tableSql.append("and ( ");
					for (int i = 0; i < mhcx.length; i++) {
						if (i != 0) {
							tableSql.append(" or ");
						}
						tableSql.append("(a.xh like '%" + mhcx[i] + "%' ");
						tableSql.append("or ");
						tableSql.append("a.xm like '%" + mhcx[i] + "%') ");
					}
					tableSql.append(") ");
				}
			}
		}else{
			user.setUserStatus(yhlx);
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");

			tableSql.append(searchTj);
			tableSql.append(searchTjByUser);

			inputV = SearchService.getTjInput(model.getSearchModel());
		}

		tableSql.append(")");

		String query = " order by xh ";
		
		return getRsArrList(tableSql.toString(), query, inputV, new String[] {
				"xh", "xm", "sqf", "zzf", "clr", "clsj", "clrxm", "clyj",
				"tsnr", "cz" }, "", model);


	}
}
