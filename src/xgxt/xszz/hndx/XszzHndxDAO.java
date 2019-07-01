package xgxt.xszz.hndx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.jxgl.JxglTyDAO;
import xgxt.jxgl.JxglTyForm;
import xgxt.rcsw.gzdx.RcswGzdxModel;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.sztz.bjlhdx.QuerryModel;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzDAO;
import xgxt.xszz.XszzTyForm;

public class XszzHndxDAO extends XszzDAO {

	/**
	 * ���ѧԺ�����б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXyRsList(XszzTyForm model) {
		
		DAO dao = DAO.getInstance();
		
		//ѧ��
		String xn = model.getXn();
		
		//���������������б�
		List<HashMap<String, String>> djList = dao.getWhList(
				"hndx_xszz_jjknsjb", "dm", "mc", "", "", "");
		
		//SQL
		StringBuilder xySb = new StringBuilder();
		xySb.append("select a.xydm, a.xymc, b.num,? xn ");
		xySb.append("from (select distinct xydm, xymc from view_njxyzybj) a ");
		xySb.append("left join (select xydm, count(xh) num from  ");
		xySb.append("view_xsjbxx group by xydm) b on a.xydm = b.xydm ");
		
		String[] inputValue = new String[]{xn};
		String[] outputValue = new String[] { "xydm", "xn", "xymc", "num", };
		
		//SQL
		StringBuilder jbSb = new StringBuilder();
		if (djList != null && djList.size() > 0) {
			
			outputValue = new String[3 + djList.size()];
			outputValue[0] = "xydm";
			outputValue[1] = "xn";
			outputValue[2] = "xymc";
			outputValue[3] = "num";
			
			jbSb.append("select a.* ");
			for (int i = 1; i < djList.size(); i++) {
				
				HashMap<String, String> map = djList.get(i);
				String jb = "jb" + map.get("dm");
				jbSb.append(" ,");
				jbSb.append("nvl((select b.xmrs from hndx_xszz_xyrsb b where a.xydm = b.szxy and b.xn = '"+xn+"' and b.xmmc = '�����������϶�' and b.xmjb = '"+map.get("dm")+"'),0) ");
				jbSb.append(jb);
				
				outputValue[3 + i] = jb;
			}
			jbSb.append(" from (");
			jbSb.append(xySb);
			jbSb.append(" ) a");
		}
		
		String sql = Base.isNull(jbSb.toString())?xySb.toString():jbSb.toString();
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue, outputValue);
		
		return list;
	}
	

	/**
	 * ���ѧԺ��������(����)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXyRsBlList(XszzTyForm model) {

		DAO dao = DAO.getInstance();
		
		// ѧ��
		String xn = model.getXn();
		// ��Ŀ����
		String xmmc = model.getXmmc();

		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select a.dm, a.mc, b.xmbl from hndx_xszz_jjknsjb a ");
		sql.append("left join view_hddx_xyrsbl b on a.dm = b.xmjb  ");
		sql.append("and b.xn =? and b.xmmc = ? order by a.dm ");

		String[] inputValue = new String[] { xn, xmmc };
		String[] outputValue = new String[] { "dm", "mc", "xmbl" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ���ѧԺ����(��������)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXySzRsList(XszzTyForm model) {

		DAO dao = DAO.getInstance();

		// ѧ��
		String xn = model.getXn();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ѧԺ����
		String xydm = model.getXydm();
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select a.dm, a.mc,b.xmbl, b.xmjb,nvl(b.xmrs,0) xmrs from hndx_xszz_jjknsjb a ");
		sql.append("left join hndx_xszz_xyrsb b on a.dm = b.xmjb  ");
		sql.append("and b.xn =? and b.xmmc = ? and b.szxy = ? order by a.dm ");

		String[] inputValue = new String[] { xn, xmmc, xydm };
		String[] outputValue = new String[] { "dm", "mc","xmbl","xmjb", "xmrs" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}

	/**
	 * ����������ѧ������ѧԺ��ǰѧ�����Ŀ��������
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPlshXyZrsList(XszzTyForm model) {

		DAO dao = DAO.getInstance();

		// ����ֵ
		String[] pkValue = model.getPrimarykey_checkVal();
		// ��Ŀ����
		String xmjb = model.getKnsjb();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ����ֶ�
		String shzd = model.getShzd();
		// ѧ��
		String xn = model.getXn();

		if (pkValue == null || pkValue.length == 0) {
			return null;
		}

		// SQL
		StringBuilder sql = new StringBuilder();

		// ��������
		sql.append("select a.xydm, a.xymc, a.sqrs, nvl(b.qttgr, 0) qttgr ");
		sql.append(", nvl(c.xmrs, 0) xmrs from (select xydm, xymc ");
		sql.append(", count(xh) sqrs from view_hddx_jjknssq ");
		for (int i = 0; i < pkValue.length; i++) {
			if (i == 0) {
				sql.append("where pk = '" + pkValue[i] + "' ");
			} else {
				sql.append("or pk = '" + pkValue[i] + "' ");
			}
		}
		sql.append(" group by xydm, xymc) a ");

		// ������������
		sql.append("left join (select a.xydm, count(a.xh) qttgr from view_hddx_jjknssq a ");
		sql.append("where a.knsjb = '" + xmjb + "'");
		sql.append("and a." + shzd + " = 'ͨ��' ");
		sql.append("and xn = '" + xn + "' ");
		sql.append("and not exists (select 1 from (select pk from view_hddx_jjknssq ");
		for (int i = 0; i < pkValue.length; i++) {
			if (i == 0) {
				sql.append("where pk = '" + pkValue[i] + "' ");
			} else {
				sql.append("or pk = '" + pkValue[i] + "' ");
			}
		}
		sql.append(") b where a.pk = b.pk) group by a.xydm) b on a.xydm = b.xydm ");
		
		// ѧԺ��������
		sql.append("left join (select a.szxy xydm, xmrs from hndx_xszz_xyrsb a ");
		sql.append("where a.xmmc = '"+xmmc+"' ");
		sql.append("and a.xmjb = '"+xmjb+"' ");
		sql.append("and exists (select 1 from (select distinct xn, xydm from view_hddx_jjknssq ");
		for (int i = 0; i < pkValue.length; i++) {
			if (i == 0) {
				sql.append("where pk = '" + pkValue[i] + "' ");
			} else {
				sql.append("or pk = '" + pkValue[i] + "' ");
			}
		}	                             
		sql.append(") b where a.xn = b.xn and a.szxy = b.xydm)) c on a.xydm = c.xydm ");

		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "xydm", "xymc","sqrs","qttgr","xmrs" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * ����ѧԺ�����ȡѧԺ����
	 * @param xydm
	 * @return String
	 * */
	public String getXymc(String xydm){
		DAO dao = DAO.getInstance();
		String sql = "select distinct xymc from view_njxyzybj where xydm=?";
		String xymc = dao.getOneRs(sql, new String[]{xydm}, "xymc");
		
		return StringUtils.isNull(xymc) ? "" : xymc;
	}
	
	/**
	 * ��ȡ������ͳ�Ʊ�����
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getKnsData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {model.getXydm()};
		String[] outputValue = {"r", "xm", "xh", "xymc", "zybj", "jg", "csrq",
				                "sfzh", "xb", "mzmc", "xmzzjb", "jzqdqk", "ssbh",
				                "lxdh", "jtdz", "jtyb", "jtdh", "xiaoqumc", "bz"};
		
		
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc||bjmc from view_xsjbxx b where a.xh=b.xh)zybj,",
				                         "(select sfzh from view_xsjbxx b where a.xh=b.xh)sfzh,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select ssbh from view_xsjbxx b where a.xh=b.xh)ssbh,",
				                         "(select lxdh from view_xsjbxx b where a.xh=b.xh)lxdh,",
				                         "(select jg from view_xsxxb b where a.xh=b.xh)jg,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select jtdz from jtqkdcb b where a.xh=b.xh ",
				                         "and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtdz,",
				                         "(select jtyb from jtqkdcb b where a.xh=b.xh ",
				                         "and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtyb,",
				                         "(select jtdh from jtqkdcb b where a.xh=b.xh ",
				                         "and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtdh,",
				                         "a.xmzzjb,a.jzqdqk,a.bz,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,",
				                         "(select xqmc from dm_zju_xq b where ",
				                         "b.dm=(select xiaoqudm from jtqkdcb c where c.xh=a.xh and sqsj=(select max(sqsj) from jtqkdcb d where d.xh=a.xh)))xiaoqumc from xszz_knsb a ) ",
				                         "a where xydm=?");
		
		StringBuilder sb = new StringBuilder();//����
		if(StringUtils.isNotNull(model.getXn())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//���
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//�������ͨ������
		sb.append(getShtgtj(xmdm));
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		
		HashMap<String, String> configer = xsxxglDao.selectXsxxglXsxx();//����������Ϣ
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm"))){
			//ѧ��������Ϣȡ����
			if(list != null){
				for(int i=0; i<list.size(); i++){
					list.get(i)[5] = xsxxglDao.dzxxdmToMc(list.get(i)[5]);
				}
			}
		}
		
		return list;	
	}
	
	/**
	 * ��ȡ���ҽ�ѧ��ͳ�Ʊ�����
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getGjjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xxmc", "xymc", "xh", "xm", "xb", "csrq",
				                "hkszd", "mzmc", "zymc", "nj", "rxrq", "xiaoqumc",
				                "yhkh", "bz"};
		
		
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select nj from view_xsjbxx b where a.xh=b.xh)nj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select hkszd from view_xsxxb b where a.xh=b.xh)hkszd,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz,",
				                         "(select xqmc from dm_zju_xq b where a.xiaoqudm=b.dm)xiaoqumc,'���ϴ�ѧ'xxmc from xszz_gjjxjb a ) ",
				                         "a where 1=1 ");
		
		StringBuilder sb = new StringBuilder();//����
		if(StringUtils.isNotNull(model.getXn())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//���
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//�������ͨ������
		sb.append(getShtgtj(xmdm));
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		
		HashMap<String, String> configer = xsxxglDao.selectXsxxglXsxx();//����������Ϣ
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm"))){
			//ѧ��������Ϣȡ����
			if(list != null){
				for(int i=0; i<list.size(); i++){
					list.get(i)[7] = xsxxglDao.dzxxdmToMc(list.get(i)[7]);
				}
			}
		}
		
		return list;	
	}
	
	/**
	 * ��ȡ������־��ѧ��ͳ�Ʊ�����
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getGjlzjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xxmc", "xymc", "xh", "xm", "xb", "csrq",
				                "hkszd", "mzmc", "zymc", "nj", "rxrq", "yhkh", "xmzzjb",
				                "xiaoqumc", "bz"};
		//��������������
		String knzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm", XszzXmdm.XSZZ_KNS);
		//��������
		StringBuilder knsb = new StringBuilder();
		if("ѧ��".equalsIgnoreCase(knzq)){
			knsb.append(" and a.xn=b.xn ");
		}else if("���".equalsIgnoreCase(knzq)){
			knsb.append(" and a.nd=b.nd ");
		}else if("ѧ��".equalsIgnoreCase(knzq)){
			knsb.append(" and a.xq=b.xq ");
		}else if("������".equalsIgnoreCase(knzq)){
			knsb.append(" and sqsj=(select max(sqsj) from xszz_knsb c where c.xh=a.xh) ");
		}
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select nj from view_xsjbxx b where a.xh=b.xh)nj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select hkszd from view_xsxxb b where a.xh=b.xh)hkszd,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "(select xmzzjb from xszz_knsb b where a.xh=b.xh",
				                         knsb.toString(),
				                         ")xmzzjb,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz,",
				                         "(select xqmc from dm_zju_xq b where a.xiaoqudm=b.dm)xiaoqumc,'���ϴ�ѧ'xxmc from gjlzjxj a ) ",
				                         "a where 1=1 ");
		
		StringBuilder sb = new StringBuilder();//����
		if(StringUtils.isNotNull(model.getXn())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//���
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//�������ͨ������
		sb.append(getShtgtj(xmdm));
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		
		HashMap<String, String> configer = xsxxglDao.selectXsxxglXsxx();//����������Ϣ
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm"))){
			//ѧ��������Ϣȡ����
			if(list != null){
				for(int i=0; i<list.size(); i++){
					list.get(i)[7] = xsxxglDao.dzxxdmToMc(list.get(i)[7]);
				}
			}
		}
		
		return list;	
	}
	
	/**
	 * ��ȡ������ѧ��ͳ�Ʊ�����
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getGjzxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xxmc", "xymc", "xh", "xm", "xb", "csrq",
				                "hkszd", "mzmc", "zymc", "nj", "rxrq", "xmzzjb",
				                "xmzzje", "yhkh", "xiaoqumc", "bz"};
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select nj from view_xsjbxx b where a.xh=b.xh)nj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select hkszd from view_xsxxb b where a.xh=b.xh)hkszd,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "a.xmzzjb,a.xmzzje,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz,",
				                         "(select xqmc from dm_zju_xq b where a.xiaoqudm=b.dm)xiaoqumc,'���ϴ�ѧ'xxmc from gjzxj a ) ",
				                         "a where 1=1 ");
		
		StringBuilder sb = new StringBuilder();//����
		if(StringUtils.isNotNull(model.getXn())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//���
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//�������ͨ������
		sb.append(getShtgtj(xmdm));
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		
		HashMap<String, String> configer = xsxxglDao.selectXsxxglXsxx();//����������Ϣ
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm"))){
			//ѧ��������Ϣȡ����
			if(list != null){
				for(int i=0; i<list.size(); i++){
					list.get(i)[7] = xsxxglDao.dzxxdmToMc(list.get(i)[7]);
				}
			}
		}
		
		return list;	
	}
	
	/**
	 * ��ȡ������������ѧ��ͳ�Ʊ�����
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getYxpksjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		String[] inputValue = {};
		String[] outputValue = {"r", "xxmc", "xymc", "zymc", "xm", "xb", "csrq",
								"mzmc", "xh", "rxrq", "jtdz", "yhkh", "bz"};
						
		StringBuilder sb = new StringBuilder();//����
		if(StringUtils.isNotNull(model.getXn())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//���
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//�������ͨ������
		sb.append(getShtgtj(xmdm));
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "(select jtdz from jtqkdcb b where a.xh=b.xh ",
				                         " and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtdz,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz,",
				                         "(select xqmc from dm_zju_xq b where a.xiaoqudm=b.dm)xiaoqumc,'���ϴ�ѧ'xxmc from xszz_hnsgxyxpksjxj a ) ",
				                         "a where 1=1 ");
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);		
		return list;	
	}
	
	/**
	 * �к��ʹ�ѧ����ѧ�������ͳ�Ʊ�����
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getZhydxszxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		String[] inputValue = {};
		String[] outputValue = {"xm", "xb", "xmzzjb", "mzmc", "jtsfqz", "myshf", 
								"xyzymc", "lxdh", "sfzh"};
		
		StringBuilder sb = new StringBuilder();//����
		if(StringUtils.isNotNull(model.getXn())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		if(StringUtils.isNotNull(model.getNd())){//���
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getNd()});
			sb.append(" and nd=?");
		}
		if(StringUtils.isNotNull(model.getXq())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXq()});
			sb.append(" and xq=?");
		}
		//�������ͨ������
		sb.append(getShtgtj(xmdm));
		
		//��������������
		String knzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm", XszzXmdm.XSZZ_KNS);
		//��������
		StringBuilder knsb = new StringBuilder();
		if("ѧ��".equalsIgnoreCase(knzq)){
			knsb.append(" and a.xn=b.xn ");
		}else if("���".equalsIgnoreCase(knzq)){
			knsb.append(" and a.nd=b.nd ");
		}else if("ѧ��".equalsIgnoreCase(knzq)){
			knsb.append(" and a.xq=b.xq ");
		}else if("������".equalsIgnoreCase(knzq)){
			knsb.append(" and sqsj=(select max(sqsj) from xszz_knsb c where c.xh=a.xh) ");
		}
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select xydm from view_xsjbxx b where a.xh=b.xh)xydm,",
				                         "(select xymc||zymc from view_xsjbxx b where a.xh=b.xh)xyzymc,",
				                         "(select lxdh from view_xsjbxx b where a.xh=b.xh)lxdh,",
				                         "(select xb from view_xsxxb b where a.xh=b.xh)xb,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select sfzh from view_xsjbxx b where a.xh=b.xh)sfzh,",
				                         "(select xmzzjb from xszz_knsb b where a.xh=b.xh ",
				                         knsb.toString(),
				                         ")xmzzjb,",
				                         "(select sfqz from jtqkdcb b where a.xh=b.xh ",
				                         "and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh))jtsfqz,",
				                         " a.shf myshf,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.xmdm",
				                         " from xszz_comm_zzwsb a ) ",
				                         "a where xmdm='3003'");
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);		
		return list;	
	}
	
	/**
	 * ��ȡѧ���������
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuCollect(XszzTyForm model){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String xn = model.getXn();
		String shtj = getShtgtj(XszzXmdm.XSZZ_KNS).toString();
		String sql = StringUtils.joinStr("select (select count(*) from view_xsxxb where sfzx='��У') zxrs,",
					 "(select count(*) from xszz_knsb where xn=? and xmzzjb like '%��%��%' ",shtj,")tksrs,",
					 "(select count(*) from xszz_knsb where xn=? and xmzzjb like '%ƶ%��%' ",shtj,")pksrs,",
					 "(select count(*) from xszz_knsb a where xn=? and xmzzjb like '%��%��%'",shtj,"  and exists(select 1 from view_xsjbxx b where a.xh=b.xh and b.nj=(select max(nj) from view_xsjbxx))) xstkrs,",
					 "(select count(*) from xszz_knsb a where xn=? and xmzzjb like '%ƶ%��%' ",shtj," and exists(select 1 from view_xsjbxx b where a.xh=b.xh and b.nj=(select max(nj) from view_xsjbxx))) xspkrs,",
					 "(select count(*) from jtqkdcb a where xn=? and jthk='ũ��' ","and sqsj=(select max(sqsj) from jtqkdcb c where c.xh=a.xh and a.xn=b.xn))ncxss",
			         " from dual");
		//��У������
		//����������
		//ƶ��������
		//ũ�弮ѧ����
		//������������
		//ƶ����������
		map = dao.getMap(sql, new String[]{xn,xn,xn,xn,xn}, new String[]{"zxrs", "tksrs", "pksrs", "xstkrs", "xspkrs", "ncxss"});
		return map;		
	}
	
	/**
	 * ��ȡ���ͨ������
	 * @param xmdm
	 * @return StringBuilder
	 * */
	private StringBuilder getShtgtj(String xmdm){
		DAO dao = DAO.getInstance();		
		StringBuilder sb = new StringBuilder();
		String shjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm", xmdm);
		
		if("һ�����".equalsIgnoreCase(shjb)){
			sb.append(" and shzt1='ͨ��'");
		}else if("�������".equalsIgnoreCase(shjb)){
			sb.append(" and shzt2='ͨ��'");
		}
		else if("�������".equalsIgnoreCase(shjb)){
			sb.append(" and shzt3='ͨ��'");
		}
		return sb;
	}
}
