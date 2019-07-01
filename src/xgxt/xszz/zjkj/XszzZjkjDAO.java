package xgxt.xszz.zjkj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommDAO;

import common.XszzXmdm;

public class XszzZjkjDAO {
	public List<String[]> getKnsData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XszzCommDAO commDao = new XszzCommDAO();
		
		String xn = model.getXn();
		String sxn = "";
		
		if(StringUtils.isNotNull(xn) && xn.length()==9){
			sxn = (Integer.parseInt(xn.substring(0, 4))-1) + "-" + (Integer.parseInt(xn.substring(5))-1);
		}
		
		HashMap<String,String> map = CommonQueryDAO.commonQueryOne("xszz_zzxmb", new String[]{"shjb", "sqzq"}, "xmdm", XszzXmdm.XSZZ_KNS);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.*,")
			.append("(select c.jtrs from jtqkdcb c where c.xh=a.xh and c.xn=a.xn) jtrs,")
			.append("(select c.jtnzsr from jtqkdcb c where c.xh=a.xh and c.xn=a.xn) jtnzsr,")
			.append("(select c.jtdz from jtqkdcb c where c.xh=a.xh and c.xn=a.xn) jtdz,")
			.append("(select c.jtdh from jtqkdcb c where c.xh=a.xh and c.xn=a.xn) jtdh,")
			.append("(select c.jtjbjjqk from jtqkdcb c where c.xh=a.xh and c.xn=a.xn) jtjbjjqk,")
			.append("(select count(*) from cjb c where c.xh=a.xh and c.xn=? and c.cj<60) sxnbjgkms,")
			.append("'kndj' kndj")
			.append(" from (select a.xydm,a.xymc,a.bjmc,a.xh,a.xm,a.lxdh,b.xn")
			.append(" from view_xsjbxx a,xszz_knsb b where a.xh=b.xh and b.xn=?) a");
		
		String[] output = new String[]{"r","xymc","bjmc","xh","xm","lxdh","jtrs",
				"jtnzsr","jtdz","jtdh","jtjbjjqk","sxnbjgkms","kndj"}; 
		
		String[] input = null;
		
		if(StringUtils.isNotNull(model.getXydm())){
			sql.append(" where xydm=?");
			input = new String[]{sxn, xn, model.getXydm()};
		}else{
			input = new String[]{sxn, xn};
		}
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql.toString(), input, output);
		
		for(String[] strs : list){
			strs[12] = commDao.getKnjbForXh(map.get("shjb"), map.get("sqzq"), xn, strs[3], "");
		}
		
		return list;
	}
	
	/**
	 * �й��ش󱨱��ӡ
	 * 
	 * @param form
	 * @param os
	 */
	public List<String[]> getZgddZzList(String xmdm,
			HashMap<String, String> xmInfo, String[] outValue, XszzTyForm model) {

		DAO dao = DAO.getInstance();
		XszzCommDAO commDao = new XszzCommDAO();

		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ����ʱ��
		String pdsj = xmInfo.get("pdsj");
		// ��˼���
		String shjb = xmInfo.get("shjb");
		// ѧ��
		String xn = Base.currXn;
		model.setXn(xn);
		// ѧ��
		String xq = Base.currXq;
		model.setXq(xq);
		// ���
		String nd = Base.currNd;
		model.setNd(nd);
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = commDao.getBeforeXnXqNd(sqzq,
					pdsj, model);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			nd = befInfo.get("nd");
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,t.* from ( select t.* from (");
		sql.append("select a.*,d.jtjbjjqk,c.jsjsp,c.wysp,c.zjqk,");
		sql.append("c.sbxqxfjd,c.xbxqxfjd,c.zcbjpm,c.kyqk from (");
		sql.append("select b.xymc,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.zydm,b.bjdm,a.* from ");
		sql.append(xmb);
		sql.append(" a,view_xsjbxx b ");
		sql.append(" where a.xh = b.xh ");
		sql.append(" and a.xmdm = '" + xmdm + "' ");
		
		if("һ�����".equalsIgnoreCase(shjb)){
			sql.append(" and a.shzt1 = 'ͨ��' ");
		}else if("�������".equalsIgnoreCase(shjb)){
			sql.append(" and a.shzt2 = 'ͨ��' ");
		}else if("�������".equalsIgnoreCase(shjb)){
			sql.append(" and a.shzt3 = 'ͨ��' ");
		}
		
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'" : "");
		sql.append("���".equalsIgnoreCase(sqzq) ? " and a.nd = '" + nd + "'" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = '" + xn + "'" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xq = '" + xq + "'" : "");
		
		sql.append(" ) a ");
		sql.append(" left join xsqtxxb c ");
		sql.append(" on a.xh = c.xh ");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = c.xn" : "");
		sql.append("���".equalsIgnoreCase(sqzq) ? " and a.nd = c.nd" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xn = c.xn" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and a.xq = c.xq" : "");
		
		sql.append(" left join (select f.xh,f.jtjbjjqk ");
		sql.append(" from jtqkdcb f where exists (select 1 ");
		sql.append(" from (select e.xh, max(e.sqsj) sqsj ");      
		sql.append(" from jtqkdcb e group by e.xh) g");                       
		sql.append(" where g.xh = f.xh ");                           
		sql.append(" and f.sqsj = g.sqsj)) d on a.xh = d.xh ");  
		sql.append(" ) t ");
		sql.append(" where 1 = 1  ");
		
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and t.xn = '" + xn + "'" : "");
		sql.append("���".equalsIgnoreCase(sqzq) ? " and t.nd = '" + nd + "'" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and t.xn = '" + xn + "'" : "");
		sql.append("ѧ��".equalsIgnoreCase(sqzq) ? " and t.xq = '" + xq + "'" : "");
		
		sql.append(Base.isNull(nj) ? "" : " and t.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and t.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and t.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and t.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and t.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and t.xm like '%" + xm + "%' ");
		sql.append(" ) t ");
		
		String[] arrOut = new String[] { "r", "xh", "xymc", "xm", "xb",
				"jsjsp", "wysp", "sbxqxfjd", "xbxqxfjd", "zcbjpm", "jtjbjjqk",
				"", "zjqk", "sfzh", "kyqk", "bz" };

		if (outValue != null && outValue.length > 0) {
			for (String zd : outValue) {
				// �϶�����,��������,����˵��
				if ("rdly".equalsIgnoreCase(zd) || "sqly".equalsIgnoreCase(zd)
						|| "sqsm".equalsIgnoreCase(zd)) {
					arrOut[11] = zd;
					break;
				}
			}
		}

		// ��ѯ
		List<String[]> list = dao.rsToVator(sql.toString(), new String[] {},
				arrOut);

		return list;
	}
}
