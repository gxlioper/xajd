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
	 * 现有条件库中的全部条件
	 * @param xmdm
	 * @return
	 */
	public List<HashMap<String,String>> getTjk(){
		
		return getTjk(null);
	}
	
	
	/**
	 * 现有条件库中的条件（不包含该项目已经设置的条件）
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
	 * 根据评奖项目代码查询已设置条件
	 * @param xmdm
	 * @return
	 */
	public List<HashMap<String,String>> getXmtj(String xmdm){
		
		return dao.getXmtj(xmdm);
	}
	
	
	
	/**
	 * 根据项目代码查询指定条件的信息
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
	 * 根据项目代码查询指定条件的信息
	 * 
	 * @param xmdm
	 * @param tjdm
	 * 
	 * @author 伟大的骆
	 * @return
	 */
	public List<HashMap<String,Object>> getXmtjList(String xmdm,String[] tjdm){
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		if (tjdm != null && tjdm.length > 0) {

			// 条件列表
			List<HashMap<String, String>> tjList = dao.getXmtjInfo(xmdm, tjdm);

			if (tjList != null && tjList.size() > 0) {
				for (int i = 0; i < tjList.size(); i++) {
					HashMap<String, String> map = tjList.get(i);

					// 条件value
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

			// 条件列表
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

			// 条件列表
			List<HashMap<String, String>> tjList = dao.getXmtjInfo(xmdm, tjdm);

			if (tjList != null && tjList.size() > 0) {
				for (int i = 0; i < tjList.size(); i++) {
					HashMap<String, String> map = tjList.get(i);
					HashMap<String, String> rs = new HashMap<String, String>();

					// 条件value
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
	 * 下拉框选项
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
			dm = new String[]{"是","否"};
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
	 * 保存条件设置（先删后增了）
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
	 * 修改条件设置
	 * @param model
	 * @return
	 */
	protected boolean updateTjsz(PjpyTjszForm model){
		
		return dao.updateTjsz(model);
	}
	
}
