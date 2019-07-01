package xgxt.qgzx.zgdzdx;

import java.sql.SQLException;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.qgzx.dao.QgzxDao;

public class QgzxGwsqDAO extends DAO {
	DAO dao = DAO.getInstance();
	
	/**����ѧ����λ������Ϣ
	 * @throws Exception */
	public boolean gwxx_save(HashMap<String, String> tmp,String i) throws Exception{
		QgzxDao qgzxDao = new QgzxDao();
		String xh = tmp.get("xh");
		String gwdm = tmp.get("gwdm");
		String gwsbsj = tmp.get("gwsbsj");
		String xn = tmp.get("xn");
		String nd = tmp.get("nd");
		String xq = tmp.get("xq");
		String lxdh = tmp.get("lxdh");
		String sqly = tmp.get("sqly");
		String yhtc = tmp.get("yhtc");
		String bz = tmp.get("bz");
		String zzmmdm = tmp.get("zzmmdm");
		
		String pkValue = xh+gwdm+gwsbsj+xn+nd+xq;
		if(qgzxDao.checkExists("xsgwxxb", "xh||gwdm||gwsbsj||xn||nd||xq", pkValue)){//���ݴ��ڽ����޸Ĳ���
			String sql = "update xsgwxxb set lxdh=?,xssq=?,yhtc=?,bz=?,zzmmdm=?,gwzydj=? where xh=? and gwdm=? and gwsbsj=? and xn=? and nd=? and xq=?";
			return dao.runUpdate(sql, new String[]{lxdh,sqly,yhtc,bz,zzmmdm,i+"",xh,gwdm,gwsbsj,xn,nd,xq});
		}else{//���ݲ�����ִ�����Ӳ���
			String sql = "insert into xsgwxxb(xh,gwdm,gwsbsj,lxdh,xn,nd,xq,xssq,yhtc,bz,zzmmdm,gwzydj)values(?,?,?,?,?,?,?,?,?,?,?,'"+i+"')";
			return dao.insert(sql,new String[]{xh,gwdm,gwsbsj,lxdh,xn,nd,xq,sqly,yhtc,bz,zzmmdm});
		}
	}
	/**����ѧ��δ��˸�λ������Ϣ����*/
	public String getgwxxcount(String xh){
		String sql = "select case when max(gwzydj) is null then 1 else max(gwzydj)+1 end num from xsgwxxb where xh=? and xxyj='δ���'";
		return dao.getOneRs(sql, new String[] {xh}, "num");
	}
	
	/**�ڹ���ѧ�Ƿ����*/
	public String getQgzxcount(String xh,String xn,String nd,String xq){
		String sql = "select count(*) qgzxNum from qgzxsqb where xh=? and xn=? and nd=? and xq=?";
		return dao.getOneRs(sql, new String[]{xh,xn,nd,xq}, "qgzxNum");
	}
	
	/**�ڹ���ѧ��¼����
	 * @throws SQLException */
	public boolean qgzx_save(HashMap<String, String> map) throws SQLException{
		String sql = "insert into qgzxsqb (xh,xn,nd,xq,lxdh,sqly,yhtc,bz,zzmmdm,gwxzdm)values(?,?,?,?,?,?,?,?,?,'##')";
		String[] input = {map.get("xh"),map.get("xn"),map.get("nd"),map.get("xq"),
				          map.get("lxdh"), map.get("sqly"), map.get("yhtc"),
				          map.get("bz"),map.get("zzmmdm")};
		return dao.insert(sql,input);
	}
}
