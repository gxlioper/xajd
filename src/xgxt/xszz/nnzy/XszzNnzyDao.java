package xgxt.xszz.nnzy;

import java.util.HashMap;
import java.util.List;

import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

public class XszzNnzyDao {
	
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
		String[] outputValue = {"r", "xm", "sfzh", "xymc", "zymc", "xh", "xb",
				                "mzmc", "rxrq"};
		
		
		
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
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from xszz_gjjxjb a ) ",
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
		String[] outputValue = {"r", "xm", "sfzh", "xymc", "zymc", "xh", "xb",
                "mzmc", "rxrq"};
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
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "(select xmzzjb from xszz_knsb b where a.xh=b.xh",
				                         knsb.toString(),
				                         ")xmzzjb,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from gjlzjxj a ) ",
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
		String[] outputValue = {"r", "xm", "sfzh", "xymc", "zymc", "xh", "xb",
                "mzmc", "rxrq"};
		
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
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "a.xmzzjb,a.xmzzje,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from gjzxj a ) ",
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
	 * ����������������ѧ��
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getZzqrmzfjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xm","xb","mzmc","csrq","xybj","xh","rxrq","jtdz","lxdh", "sfzh"};
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select zymc||'��'||bjmc from view_xsjbxx b where a.xh=b.xh)xybj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select jtdz from view_xsxxb b where a.xh=b.xh)jtdz,",
				                         "(select lxdh from view_xsxxb b where a.xh=b.xh)lxdh,",
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "a.xmzzjb,a.xmzzje,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from xszz_comm_zzwsb a where xmdm='4001' ) ",
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
	 * У����ѧ��
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getXzjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		String[] inputValue = {};
		String[] outputValue = {"r", "xm","xb","mzmc","csrq","xybj","xh","rxrq","jtdz","lxdh", "sfzh"};
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select zymc||'��'||bjmc from view_xsjbxx b where a.xh=b.xh)xybj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select jtdz from view_xsxxb b where a.xh=b.xh)jtdz,",
				                         "(select lxdh from view_xsxxb b where a.xh=b.xh)lxdh,",
				                         "(select sfzh from view_xsxxb b where a.xh=b.xh)sfzh,",
				                         "a.xmzzjb,a.xmzzje,a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3,a.bz",
				                         " from xszz_comm_zzwsb a where xmdm='4002' ) ",
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
