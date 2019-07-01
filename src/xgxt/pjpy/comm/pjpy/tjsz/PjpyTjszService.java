package xgxt.pjpy.comm.pjpy.tjsz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.exception.SystemException;

import xgxt.action.Base;
import xgxt.utils.String.StringUtils;


public class PjpyTjszService {

	private PjpyTjszDAO dao = new PjpyTjszDAO();
	
	/**
	 * �����������е�ȫ������
	 * @param xmdm
	 * @return
	 */
	public List<HashMap<String,String>> getTjk(){
		
		return getTjk(null);
	}
	
	
	/**
	 * �����������е�����������������Ŀ�Ѿ����õ�������
	 * @param xmdm
	 * @return
	 */
	public List<HashMap<String,String>> getTjk(String xmdm){
		
		if (StringUtils.isNotNull(xmdm)){
			return dao.getTjk(xmdm);
		} else {
			return dao.getTjk();
		}
	}
	
	
	
	/**
	 * ����������Ŀ�����ѯ����������
	 * @param xmdm
	 * @return
	 */
	public List<HashMap<String,String>> getXmtj(String xmdm){
		
		return dao.getXmtj(xmdm);
	}
	
	
	
	/**
	 * ������Ŀ�����ѯָ����������Ϣ
	 * @param xmdm
	 * @param tjdm
	 * @return
	 */
	public List<HashMap<String,String>> getXmtjInfo(String xmdm,String[] tjdm){
		
		if (null != tjdm && tjdm.length > 0) {

			List<HashMap<String, String>> list = dao.getXmtjInfo(xmdm, tjdm);
			return dao.getXmtjInfo(xmdm, tjdm);
		} else {
			throw new SystemException();
		}
	}
	
	/**
	 * ������Ŀ�����ѯָ����������Ϣ
	 * 
	 * @param xmdm
	 * @param tjdm
	 * 
	 * @author ΰ�����
	 * @return
	 */
	public List<HashMap<String,Object>> getXmtjList(String xmdm,String[] tjdm){
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		if (tjdm != null && tjdm.length > 0) {

			// �����б�
			List<HashMap<String, String>> tjList = dao.getXmtjInfo(xmdm, tjdm);

			if (tjList != null && tjList.size() > 0) {
				for (int i = 0; i < tjList.size(); i++) {
					HashMap<String, String> map = tjList.get(i);

					// ����value
					String tjvalue = map.get("tjvalue");
					
					HashMap<String, Object> rs = new HashMap<String, Object>();
					
					rs.put("tjdm", map.get("tjdm"));
					rs.put("tjmc", map.get("tjmc"));
					rs.put("tjgx", map.get("gx"));
					rs.put("tjvalue", map.get("tjvalue"));
					rs.put("tsgs", map.get("tsgs"));
					rs.put("tjfw", map.get("tjfw"));
					rs.put("tjz", map.get("tjz"));
					
					if (!Base.isNull(tjvalue)) {
						String[] value = tjvalue.split("!!@@!!");
						if (value != null && value.length > 0) {
							List<String> valueList = new ArrayList<String>();
							for (int j = 0; j < value.length; j++) {
								valueList.add(value[j]);
							}
							rs.put("valueList", valueList);
						}
					}
					list.add(rs);
				}
			}
		}
		
		return list;
	}
 public List<HashMap<String,String>> getXmtjList(String xmdm,String tjdm){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		
		if (tjdm != null) {

			// �����б�
			List<HashMap<String, String>> tjList = dao.getXmtjInfo(xmdm, tjdm);

			if (tjList != null && tjList.size() > 0) {
				for (int i = 0; i < tjList.size(); i++) {
					HashMap<String, String> map = tjList.get(i);
                    if ("instanceReverse".equals(map.get("tjlx"))){
                    	List<HashMap<String, String>> gxList = getList("tjgx=");
                    	list.addAll(gxList);
                    }else if("PjpyTjszMethod".equalsIgnoreCase(map.get("tablename"))){
                    	List<HashMap<String, String>> gxList = getList("tjgx=");
                    	list.addAll(gxList);
                    }else{
                    	List<HashMap<String, String>> gxList = getList("tjgx");
                    	list.addAll(gxList);
                    }
					
				}
			}
		}
		return list;
	}
 
 public List<HashMap<String,String>> getTjzList(String xmdm,String tjdm){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		
		if (tjdm != null) {

			// �����б�
			List<HashMap<String, String>> tjList = dao.getXmtjInfo(xmdm, tjdm);

			if (tjList != null && tjList.size() > 0) {
				for (int i = 0; i < tjList.size(); i++) {
					HashMap<String, String> map = tjList.get(i);
					HashMap<String, String> rs = new HashMap<String, String>();

					// ����value
					String tjvalue = map.get("tjvalue");
					String tsgs = map.get("tsgs");
					if(!StringUtils.isNull(tsgs)){
						rs.put("tsgs", map.get("tsgs"));
						list.add(rs);
					}
					
					List<HashMap<String, String>> tjzList = new ArrayList<HashMap<String,String>>();
					if (!Base.isNull(tjvalue)) {
						String[] value = tjvalue.split("!!@@!!");
						String[] dm = null;
						if (value != null && value.length > 0) {
							List<String> valueList = new ArrayList<String>();
							for (int j = 0; j < value.length; j++) {
								valueList.add(value[j]);
								rs.put("tjRs",value[j]);
								dm = value;
								
							}
							tjzList = dao.arrayToList(dm, dm);
						}
					}
					list.addAll(tjzList);
				}
			}
		}
		
		return list;
	}
	/**
	 * ������ѡ��
	 * @param flg
	 * @return
	 * @throws SQLException 
	 */
	public List<HashMap<String,String>> getList(String flg){
		
		String[] dm = null;
		
		if ("tjgx".equals(flg)){
			dm = new String[]{">",">=","=","<=","<"};
		}else if ("tjgx=".equals(flg)){
			dm = new String[]{"="};
		} else if ("isnot".equals(flg)){
			dm = new String[]{"��","��"};
		} else if ("knsjb".equals(flg)){
			dm = dao.getKnsjb();
		} else if ("kcxz".equals(flg)){
			dm = dao.getKcxz();
		} else if ("khfs".equals(flg)){
			dm = dao.getKhfs();
		}
		
		return dao.arrayToList(dm, dm);
	}
	
	
	
	/**
	 * �����������ã���ɾ�����ˣ�
	 * @param model
	 * @return
	 */
	protected boolean saveTjsz(PjpyTjszForm model){
		
		String xmdm = model.getXmdm();
		String[] tjdm = model.getTjdm();
		String[] tjgx = model.getTjgx();
		String[] tjz = model.getTjz();
		String[] tjfw = model.getTjfw();
		String[] qtxz = model.getQtxz();
		
		if (StringUtils.isNotNull(xmdm) && null != tjdm && null != tjgx
				&& null != tjz && null != tjfw && null != qtxz) {
			try {
				return dao.delTjsz(xmdm) && dao.saveTjsz(model);
			} catch (SQLException e) {
				e.printStackTrace();
				
				return false;
			}
			
		} else {
			throw new SystemException();
		}
	}

	
	/**
	 * �޸���������
	 * @param model
	 * @return
	 */
	protected boolean updateTjsz(PjpyTjszForm model){
		
		return dao.updateTjsz(model);
	}
	
}
