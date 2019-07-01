package xgxt.xszz.zjyd;

import java.util.List;
import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

public class XszzZjydDAO {
	/**
	 * ��ȡ���ҽ�ѧ��ͳ�Ʊ�����
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getGjjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		String[] inputValue = {};
		String[] outputValue = {"r", "xm", "sfzh","xymc", "zymc", "xh", "xb", "mzmc","rxrq"};
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select sfzh from view_xsjbxx b where a.xh=b.xh)sfzh,",
										 "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select nj from view_xsjbxx b where a.xh=b.xh)nj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select hkszd from view_xsxxb b where a.xh=b.xh)hkszd,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3",
				                         " from xszz_gjjxjb a ) ",
				                         "a where 1=1 ");
		
		StringBuilder sb = new StringBuilder();//����
		if(StringUtils.isNotNull(model.getXn())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		return list;	
	}
	
	/**
	 * ��ȡ���ҽ�ѧ��ͳ�Ʊ�����
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getGjzxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		String[] inputValue = {};
		String[] outputValue = {"r", "xm", "sfzh","xymc", "zymc", "xh", "xb", "mzmc","rxrq","zzdc"};
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.*, '' zzdc from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select sfzh from view_xsjbxx b where a.xh=b.xh)sfzh,",
										 "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select nj from view_xsjbxx b where a.xh=b.xh)nj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select hkszd from view_xsxxb b where a.xh=b.xh)hkszd,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3",
				                         " from gjzxj a ) ",
				                         "a where 1=1 ");
		
		StringBuilder sb = new StringBuilder();//����
		if(StringUtils.isNotNull(model.getXn())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		return list;	
	}
	
	/**
	 * ��ȡ���ҽ�ѧ��ͳ�Ʊ�����
	 * @param xmdm
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> getGjlzjxjData(String xmdm, XszzTyForm model){
		DAO dao = DAO.getInstance();
		String[] inputValue = {};
		String[] outputValue = {"r", "xm", "sfzh","xymc", "zymc", "xh", "xb", "mzmc","rxrq"};
		
		//��ѯ����Ҫ��ʾ����Ϣ���
		String sql = StringUtils.joinStr("select rownum r, a.* from (select xh,(select xm from view_xsjbxx b where a.xh=b.xh)xm,",
				                         "(select sfzh from view_xsjbxx b where a.xh=b.xh)sfzh,",
										 "(select xymc from view_xsjbxx b where a.xh=b.xh)xymc,",
				                         "(select zymc from view_xsjbxx b where a.xh=b.xh)zymc,",
				                         "(select nj from view_xsjbxx b where a.xh=b.xh)nj,",
				                         "(select xb from view_xsjbxx b where a.xh=b.xh)xb,",
				                         "(select hkszd from view_xsxxb b where a.xh=b.xh)hkszd,",
				                         "(select csrq from view_xsxxb b where a.xh=b.xh)csrq,",
				                         "(select mzmc from view_xsxxb b where a.xh=b.xh)mzmc,",
				                         "(select rxrq from view_xsxxb b where a.xh=b.xh)rxrq,",
				                         "(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,",
				                         "a.xn,a.nd,a.xq,a.shzt1,a.shzt2,a.shzt3",
				                         " from gjlzjxj a ) ",
				                         "a where 1=1 ");
		
		StringBuilder sb = new StringBuilder();//����
		if(StringUtils.isNotNull(model.getXn())){//ѧ��
			inputValue = StringUtils.joinStrArr(inputValue, new String[]{model.getXn()});
			sb.append(" and xn=?");
		}
		
		//��ѯ
		List<String[]> list = dao.rsToVator(sql+sb, inputValue, outputValue);
		return list;	
	}
}
