package xgxt.comm.xmzdsz;

import java.sql.SQLException;
import java.util.*;

import xgxt.action.Base;

public class XmzdszService {

	XmzdszDAO dao = new XmzdszDAO();
	
	/**
	 * ��ȡ��ģ����Ŀ����Ŀ�ֶα�
	 * @return
	 */
	public List<HashMap<String,String>> getMkccb(){
		
		List<HashMap<String,String>> mkccList = new ArrayList<HashMap<String,String>>();
		
		//ѧ������
		HashMap<String,String> xszzMap = new HashMap<String, String>();
		xszzMap.put("mkmc", "xszz");//ģ������
		xszzMap.put("xmdmb", "xszz_zzxmb");//��Ŀ�����
		xszzMap.put("xmbzd", "xmb");//��Ŀ���ֶ�
		xszzMap.put("xmdmzd", "xmdm");//��Ŀ�����ֶ�
		xszzMap.put("xmmczd", "xmmc");//��Ŀ�����ֶ�
		xszzMap.put("xmzdb", "xszz_xmnrzdb");//��Ŀ�����ֶα�
		xszzMap.put("xmzdView", "xg_view_xszz_xmnrzdb");//��Ŀ�����ֶα�
		xszzMap.put("xmzdLybzd", "lyb");//��Ŀ�����ֶ���Դ���ֶ���
		mkccList.add(xszzMap);
		
		//-------�¼�ģ�����������½���Map ����ӵ�-- mkccList������ͬ�ϣ�----------
		
		
		return mkccList;
	}
	
	
	
	/**
	 * ��ȡָ��ģ����Ŀ����Ŀ�ֶα�
	 * @param mkmc
	 * @return
	 */
	public HashMap<String,String> getMkccb(String mkmc){
		
		List<HashMap<String,String>> mkccList = getMkccb();
		
		for (int i = 0 ; i < mkccList.size() ; i++) {
			if (mkmc.equals(mkccList.get(i).get("mkmc"))) {
				return mkccList.get(i);
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * ������Ŀ�б�
	 * @param mkmc
	 * @return
	 */
	public List<HashMap<String,String>> szxmList(String mkmc){
		
		HashMap<String,String> mkccMap = getMkccb(mkmc);
		
		if (null != mkccMap) {
			return dao.getXmList(mkccMap);
		}
		
		return null;
	}
	
	
	
	/**
	 * ��ȡģ����Ŀ�����ֶα�
	 * @param mkmc
	 * @return
	 */
	public List<HashMap<String,String>> getMkxmbzzdList(String mkmc){
		return dao.getMkxmbzzdList(mkmc);
	}
	
	
	
	/**
	 * ��ȡģ����Ŀ�����ֶ�
	 * @param mkmc
	 * @return
	 */
/*	public String[] getMkxmbzzd(String mkmc){
		try {
			return dao.getMkxmbzzd(mkmc);
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[] {};
		}
	}*/
	
	
	
	/**
	 * ��������Դ
	 * @param tableName
	 * @param lybzd
	 * @return
	 * @throws SQLException
	 */
	public String[] getSjy(String xmdm,String mkmc) {
		HashMap<String,String> map = getMkccb(mkmc);
		
		String tableName = map.get("xmzdb");
		String lybzd = map.get("xmzdLybzd");
		
		try {
			return dao.getSjy(xmdm,tableName, lybzd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * ���ز�ѯ��ʾ�ֶ�
	 * @param xmdm
	 * @param mkmc
	 * @param model
	 * @param sjy
	 * @return
	 */
	public List<HashMap<String,String>> getZdList(String xmdm,String mkmc,XmzdszForm model,String sjy){
		
		HashMap<String,String> map = getMkccb(mkmc);//ģ��洢��Ϣ
		//String[] bzzdArr = getMkxmbzzd(mkmc);//ģ����Ҫ������ʾ���ֶ�
		//String[] colList = new String[] {};
		String tableName = map.get("xmzdView");//��Ŀ�����ֶα�
		//String xmdmb = map.get("xmdmb");//��Ŀ�����
		//String xmbzd = map.get("xmb");//��Ŀ���ֶ�
		String[] sjyArr = null;
		
		if (!Base.isNull(sjy)) {
			sjyArr = sjy.split(",");
		} else {
			sjyArr = getSjy(xmdm, mkmc);
		}
		
		
		//String xmbmc = dao.getXmbmc(xmdm, xmdmb, xmbzd);
		String[] column = new String[] {"pkValue","checked","lyb","lybmc","zd","zdmc","zdm","bxlr","zdlx","lrxz","zdsx"};
		
		//��Ŀ���Ѱ����ֶ��б�
		List<HashMap<String,String>> xmzdList = dao.getXmzdList(tableName,sjyArr, xmdm,column ,model);
		
		return xmzdList;
	}
	
	
	
	/**
	 * ���������ֶ�
	 * @param xmdm
	 * @param xmnrzdb
	 * @param pkValue
	 * @param flg
	 * @param lyb
	 * @param zd
	 * @param zdm
	 * @param bxlr
	 * @param zdlx
	 * @param lrxz
	 * @param zdsx
	 * @param sjy
	 * @return
	 */
	public boolean bcszzd(String xmdm,String xmnrzdb, String[] pkValue, String[] flg,
			String[] lyb, String[] zd, String[] zdm, String[] bxlr,
			String[] zdlx, String[] lrxz, String[] zdsx,String sjy) {
		
		if (Base.isNull(xmdm) || Base.isNull(xmnrzdb) || null == pkValue 
				|| null == flg || null == lyb || null == zd || null == zdm 
				|| null == bxlr|| null == zdlx || null == lrxz || null == zdsx) {
			return false;
		}
		
		
		
		
		try {
			return dao.bcszzd(sjy.split(","),xmdm, xmnrzdb, pkValue, flg, lyb, zd, zdm, bxlr, zdlx, lrxz, zdsx);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	/**
	 * ������Ŀ������
	 * @param xmdm
	 * @param xmdmb
	 * @param xmbzd
	 * @return
	 */
	public String getXmbmc(String xmdm,String xmdmb,String xmbzd) {
		
		
		return dao.getXmbmc(xmdm, xmdmb, xmbzd);
	}
	



	/**
	 * ����ĳ��Ŀ�Ƿ�Ĭ����Ŀ
	 * @param xmdm
	 * @return
	 */
	public String getIsMrxm(String xmdm) {
		
		String sql = "select mrxm from xszz_zzxmb where xmdm=?";
		
		return dao.getIsMrxm(xmdm);
	}

}
