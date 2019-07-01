package xgxt.qgzx.xcxy.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.qgzx.form.QgzxForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ѧԺ�ڹ���ѧDAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-07</p>
 */
public class QgzxXcxyDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();
	/**
	 * ��ϲ�ѯ����
	 * @param model
	 * @return StringBuffer
	 * */
	private StringBuffer getWhereSql(QgzxForm model){
		String xn = model.getXn();
		String nd = model.getNd();
		String nj = model.getNj();
		String yrdwdm = model.getYrdwdm();
		String xydm = model.getXydm();
		String xmdm = model.getXmdm();
		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if(xn !=null && !xn.equalsIgnoreCase("")){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(nd !=null && !nd.equalsIgnoreCase("")){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(nj !=null && !nj.equalsIgnoreCase("")){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(yrdwdm !=null && !yrdwdm.equalsIgnoreCase("")){
			sb.append( " and yrdwdm=?");
			value.add(yrdwdm);
		}
		if(xydm !=null && !xydm.equalsIgnoreCase("")){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(xmdm !=null && !xmdm.equalsIgnoreCase("")){
			sb.append( " and gwdm=?");
			value.add(xmdm);
		}
		return sb;
	}
	
	/**
	 * ��ȡ��λ������Ϣ
	 * @param pkv
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsgwxx(String pkV){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql= "select a.*,a.gwdm||'-'||a.gwsbsj xmdm,xssq sqly,(select zzmmmc from view_xsxxb b where a.xh=b.xh)zzmmmc," +
					"(select kh from view_xsxxb b where a.xh=b.xh)kh ,(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh ," +
					"(select ykth from view_xsxxb b where a.xh=b.xh)ykth ,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc," +
				"(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw from view_xsgwxx a where xh||gwdm||'-'||gwsbsj=?";
		String[] output = {"xh","xn","nd","xq","xqmc","xmdm","xm","xb","nj","xymc","zymc","bjmc","gwdm", "gwsbsj", "xssq",
							"sqly", "gzjl", "bz","kh","zzmm","lxdh","sqdw","ykth","yhkh"};
		
		map = getMap(sql, new String[]{pkV}, output);
		return map;
	}
	
	/**
	 * ��ѯ��ǰѧ�����ѧ���·ݵĳ�𷢷���Ϣ
	 * @return List
	 * */
	public List getPayInfoForXcxy(String nd, String yf){		
		String sql = "select xm ,(select xymc from view_xsxxb b where a.xh=b.xh)xymc,(select zymc from view_xsxxb b where a.xh=b.xh)zymc," +
				"(select spbcbz from view_gwxx b where a.gwdm=b.gwdm and a.sqsj=b.gwsbsj)jcbz,gwdm, gzsj,cjje,bz," +
				"(select kh from view_xsxxb b where a.xh=b.xh)kh from view_xscjff a where nd=? and yf=?";		
		return getList(sql, new String[]{nd,yf}, new String[]{"xm","xymc","zymc","gwdm","jcbz","gzsj","cjje","kh","bz"});
	}
	
	/**
	 * ��ѯ��λ�������ͨ����ѧ������
	 * */
	public List getPassStuInfo(QgzxForm model){
		String whereSql = getWhereSql(model).toString();
		String sql = "select xm,xb,nj,xymc,zymc,gwdm,sfpks,lxdh from view_xsgwxx";
		String[] output = {"xm", "xb", "nj", "xymc", "zymc", "gwdm", "sfpks", "lxdh"};
		return getList(sql + whereSql + " and xyyj='ͨ��' and xxyj='ͨ��' ", value != null ? value.toArray(new String[0]) : new String[]{}, output);
		
	}
	
	/**
	 * �����û�����ѯ�û�����
	 * */
	public String getYhbm(String userName){
		String bmmc = "";
		String sql = "select yrdwmc from yrdwdmb where dlm=?";
		bmmc = getOneRs(sql, new String[]{userName}, "yrdwmc");
		
		if(bmmc == null || "".equalsIgnoreCase(bmmc)){//�����˵�λ�û�
			sql = "select bmmc from view_yhxx where yhm=?";
			bmmc = getOneRs(sql, new String[]{userName}, "bmmc");
		}
		
		return bmmc;
	}
	
	/**
	 * ����¼�Ƿ����
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;
		
		return Integer.parseInt(num) > 0 ? true : false;		
	}
}
