/**
 * @部门:学工产品事业部
 * @日期：2015-6-4 上午11:07:03 
 */  
package com.zfsoft.extend.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zfsoft.extend.dao.ExtendGroupDAO;
import com.zfsoft.extend.dao.ExtendGroupElementDAO;
import com.zfsoft.extend.dao.ExtendModuleDAO;
import com.zfsoft.extend.dao.ExtendModuleElementDAO;
import com.zfsoft.extend.dao.ExtendPluginDAO;
import com.zfsoft.extend.dao.ExtendStoreDataDAO;
import com.zfsoft.extend.dao.ExtendStoreDataTempDAO;
import com.zfsoft.extend.dataFormat.FieldFormat;
import com.zfsoft.extend.model.DateModuleConfig;
import com.zfsoft.extend.model.ExtendGroup;
import com.zfsoft.extend.model.ExtendGroupElement;
import com.zfsoft.extend.model.ExtendModule;
import com.zfsoft.extend.model.ExtendModuleElement;
import com.zfsoft.extend.model.ExtendStoreData;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-4 上午11:07:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendService {

	private static final Log log = LogFactory.getLog(ExtendService.class);
	
	private ExtendModuleDAO extendModuleDAO = new ExtendModuleDAO();
	
	private ExtendModuleElementDAO extendModuleElementDAO = new ExtendModuleElementDAO();
	
	private ExtendGroupDAO extendGroupDAO = new ExtendGroupDAO();
	
	private ExtendGroupElementDAO extendGroupElementDAO = new ExtendGroupElementDAO();
	
	private ExtendStoreDataDAO extendStoreDataDAO = new ExtendStoreDataDAO();
	
	private ExtendStoreDataTempDAO extendStoreDataTempDAO = new ExtendStoreDataTempDAO();
	
	public ExtendPluginDAO extendPluginDAO = new ExtendPluginDAO();
	
	
	/**
	 * 
	 * @描述: 复制数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-19 下午03:21:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tempid
	 * @param finalId
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteData(String dataid, String mid) throws Exception{
		return extendStoreDataDAO.deleteData(dataid, mid);
	}
	
	/**
	 * 
	 * @描述: 复制数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-19 下午03:21:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tempid
	 * @param finalId
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteDataTemp(String dataid, String mid) throws Exception{
		return extendStoreDataTempDAO.deleteData(dataid, mid);
	}
	
	/**
	 * 
	 * @描述: 拷贝数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-19 下午03:55:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tempId
	 * @param finalId
	 * @param mid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean copyTempDataToFinal(String tempId , String finalId , String mid) throws Exception{
		List<ExtendStoreData> extendStoreDataList = extendStoreDataTempDAO.getExtendStoreDataList(tempId, mid);
		for (ExtendStoreData extendStoreData : extendStoreDataList) {
			extendStoreData.setId(finalId);
		}
		return extendStoreDataDAO.saveData(extendStoreDataList, finalId, mid);
	}
	
	
	/**
	 * 
	 * @描述: 检查该配置用户是否可以调用
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-17 下午05:23:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param moduleId
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] checkDataModuleConfig(ExtendModule queryExtentModule) throws Exception{
		if(queryExtentModule == null){
			return new String[]{"0" , "无表单配置信息！"};
		}
		if(StringUtils.equals("0", queryExtentModule.getSfqy())){
			return new String[]{"0" , "表单配置未启用！"};
		}
		String sjxz = queryExtentModule.getSjxz();
		if(StringUtils.equals("1", sjxz)){
			String kssj = queryExtentModule.getKssj();
			String jssj = queryExtentModule.getJssj();
			Date currentDate = new Date();
			Date beforDate = null;
			Date afterDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(kssj)){
				beforDate = sdf.parse(kssj);
				if(currentDate.compareTo(beforDate) < 0)
					return new String[]{"0" , "当前不在配置开放时间区间内！"};
			}
			if(StringUtils.isNotBlank(jssj)){
				afterDate = sdf.parse(jssj);
				if(currentDate.compareTo(afterDate) > 0)
					return new String[]{"0" , "当前不在配置开放时间区间内！"};
			}
		}
		return new String[]{"1",null};
	}
	
	public DateModuleConfig obtainDataModuleConfig(String id) throws Exception{
		log.info("查询扩展字段配置信息，参数：" + id);
		long time = System.currentTimeMillis();
		DateModuleConfig config = new DateModuleConfig();
		//1
		ExtendModule queryExtentModule = queryExtentModule(id);
		if(queryExtentModule == null)
			return null;		
		//2
		List<ExtendModuleElement> extendModuleElements = extendModuleElementDAO.getExtendModuleElements(id);
		//3
		for (ExtendModuleElement extendModuleElement : extendModuleElements) {
			List<ExtendGroup> extendGroupListByMEID = extendGroupDAO.getExtendGroupListByMEID(extendModuleElement.getId());
			
			for (ExtendGroup extendGroup : extendGroupListByMEID) {
				String gid = extendGroup.getId();
				List<ExtendGroupElement> extendGroupElementListByGID = extendGroupElementDAO.getExtendGroupElementListByGID(gid);
				//查询字段的数据
				for (ExtendGroupElement extendGroupElement : extendGroupElementListByGID) {
					ZDSource zdSourceInstance = extendGroupElement.obtainZDSourceInstance();
					DataSourceQuery dataSouceQueryer = zdSourceInstance == null ? null : zdSourceInstance.getDataSouceQueryer();
					extendGroupElement.setZdData(dataSouceQueryer==null?null:dataSouceQueryer.getData());
				}
				extendGroup.setExtendGroupElementList(extendGroupElementListByGID);
			}
			
			extendModuleElement.setExtendGroupList(extendGroupListByMEID);
		}
		
		config.setExtendModule(queryExtentModule);
		config.setExtendModuleElementList(extendModuleElements);
		
		log.debug("------查询扩展字段配置耗时：" + (System.currentTimeMillis()-time)/1000 + " ms");
		
		return config;
	}
	
	public ExtendModule queryExtentModule(String id) throws Exception{
		return StringUtils.isNotBlank(id) ? extendModuleDAO.getModel(id) : null;
	}
	
	public List<ExtendModuleElement> queryExtendModuleElements(String id) throws Exception{
		return StringUtils.isNotBlank(id) ? extendModuleElementDAO.getExtendModuleElements(id) : null;
	}
	
	/**
	 * 
	 * @描述: 保存扩展信息配置
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-17 下午01:49:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param extendModule
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveConfig(ExtendModule extendModule) throws Exception{
		return extendModuleDAO.runUpdate(extendModule);
	}
	
	/**
	 * 
	 * @描述:数据持久化（到正表）
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-16 下午03:26:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mid
	 * @param data
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean dataPersistent(String dataID, String mid, JSONArray data) throws Exception{
		List<ExtendStoreData> parseJSONData = parseJSONData(dataID, data);
		return extendStoreDataDAO.saveData(parseJSONData, dataID, mid);
	}
	
	/**
	 * 
	 * @描述:数据持久化（到中间表）
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-16 下午03:26:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mid
	 * @param data
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean dataPersistentTemp(String dataID, String mid, JSONArray data) throws Exception{
		List<ExtendStoreData> parseJSONData = parseJSONData(dataID, data);
		return extendStoreDataTempDAO.saveData(parseJSONData, dataID, mid);
	}
	
	/**
	 * 
	 * @描述: 解析JSON数据
	 */
	private List<ExtendStoreData> parseJSONData(String dataID , JSONArray data){
		List<ExtendStoreData> dataList = null;
		if(data != null && data.length() > 0){
			dataList = new ArrayList<ExtendStoreData>();	
			for (int i = 0; i < data.length(); i++) {
				JSONObject group = data.getJSONObject(i);
				String mid = group.getString("mid");
				String meid = group.getString("meid");
				String gid = group.getString("gid");
				String lx = group.getString("lx");
				if(StringUtils.equalsIgnoreCase(ExtendGroup.LX_NORMAL, lx)){
					JSONArray gdata = group.getJSONArray("gdata");
					for (Iterator<JSONObject> iterator = gdata.iterator(); iterator
							.hasNext();) {
						JSONObject gitem = iterator.next();
						String zd = gitem.getString("zd");
						String zdz = gitem.getString("zdz");
						if(StringUtils.isNotBlank(zdz)){
							ExtendStoreData d = new ExtendStoreData();
							d.setGid(gid);
							d.setId(dataID);
							d.setMeid(meid);
							d.setMid(mid);
							d.setZd(zd);
							d.setZdz(zdz);
							dataList.add(d);
						}
						
					}
				}else if(StringUtils.equalsIgnoreCase(ExtendGroup.LX_LIST, lx)){
					JSONArray gdateList = group.getJSONArray("gdata");
					int rn = 0;
					for (Iterator<JSONArray> iterator = gdateList.iterator(); iterator.hasNext();) {
						JSONArray gitem = iterator.next();
						for (Iterator<JSONObject> it2 = gitem.iterator(); it2.hasNext();) {
							JSONObject item = it2.next();
							String zd = item.getString("zd");
							String zdz = item.getString("zdz");
							if(StringUtils.isNotBlank(zdz)){
								ExtendStoreData d = new ExtendStoreData();
								d.setGid(gid);
								d.setId(dataID);
								d.setMeid(meid);
								d.setMid(mid);
								d.setZd(zd);
								d.setZdz(zdz);
								d.setRn(rn+"");
								dataList.add(d);
							}
						}
						rn++;
					}
				}
			}
		}
		
		return dataList;
	}
	
	/**
	 * 
	 * @描述: 查询临时数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-18 上午09:30:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dataId
	 * @return
	 * @throws Exception
	 * List<ExtendStoreData> 返回类型 
	 * @throws
	 */
	public List<ExtendStoreData> dataQueryTemp(String dataId, String moduleId) throws Exception{
		return StringUtils.isBlank(dataId) ? null : extendStoreDataTempDAO.getExtendStoreDataList(dataId, moduleId);
	}
	
	/**
	 * 
	 * @描述: 查询数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-18 上午09:30:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dataId
	 * @return
	 * @throws Exception
	 * List<ExtendStoreData> 返回类型 
	 * @throws
	 */
	public List<ExtendStoreData> dataQuery(String dataId, String moduleId) throws Exception{
		return StringUtils.isBlank(dataId) ? null : extendStoreDataDAO.getExtendStoreDataList(dataId, moduleId);
	}
	
	public JSONArray dataQueryJSON(String dataId , String moduleId , String dataType) throws Exception{
		JSONArray data = new JSONArray();
		List<HashMap<String, String>> extendGroups = extendGroupDAO.getExtendGroupListByMID(moduleId);
		HashMap<String,String> buf = new HashMap<String, String>();
		List<ExtendStoreData> list = null;
		if(StringUtils.equals("1", dataType)){
			list = dataQuery(dataId, moduleId);
		}else if(StringUtils.equals("2", dataType)){
			list = dataQueryTemp(dataId, moduleId);
		}
		if(list == null){
			return null;
		}
		for (HashMap<String, String> g : extendGroups) {
			JSONObject _gl = new JSONObject();
			String _gid = g.get("gid");
			String _mid = g.get("mid");
			String _meid = g.get("meid");
			String _lx = g.get("lx");
			_gl.put("gid", _gid);
			_gl.put("mid", _mid);
			_gl.put("meid", _meid);
			_gl.put("lx", _lx);
			if(StringUtils.equals(ExtendGroup.LX_NORMAL, _lx)){
				JSONArray _normalData = new JSONArray();
				for (ExtendStoreData s : list) {
					String gid = s.getGid();
					String rn = s.getRn();
					String zd = s.getZd();
					String zdz = s.getZdz();
					if(StringUtils.equals(_gid, gid)){
						JSONObject _item = new JSONObject();
						_item.put("zd", zd);
						_item.put("zdz", zdz);
						_normalData.put(_item);
					}
				}
				if(_normalData.length() > 0){
					_gl.put("gdata", _normalData);
					data.put(_gl);
				}
			}else if(StringUtils.equals(ExtendGroup.LX_LIST, _lx)){
				JSONArray _listData = new JSONArray();
				//计算有几行数据
				Set<Integer> rns = new TreeSet<Integer>();
				for (ExtendStoreData s : list) {
					if(StringUtils.equals(_gid, s.getGid()) && StringUtils.isNotBlank(s.getRn())){
						rns.add(Integer.parseInt(s.getRn()));
					}
				}
				
				if(rns.size()>0){
					for (Integer _rn : rns) {
						JSONArray _listData2 = new JSONArray();
						for (ExtendStoreData s : list) {
							String gid = s.getGid();
							String rn = s.getRn();
							String zd = s.getZd();
							String zdz = s.getZdz();
							if(StringUtils.equals(_gid, gid) && StringUtils.equals(_rn.toString(), rn)){
								JSONObject _item = new JSONObject();
								_item.put("zd", zd);
								_item.put("zdz", zdz);
								_listData2.put(_item);
							}
						}
						_listData.put(_listData2);
					}
				}
				
				
				if(_listData.length() > 0){
					_gl.put("gdata", _listData);
					data.put(_gl);
				}
			}	
		}
		return data;
	}
	
	
	private List<ExtendStoreData> queryStoreData(String dataId, String moduleId) throws Exception{
		return StringUtils.isBlank(dataId) ? null : extendStoreDataDAO.getExtendStoreDataList(dataId, moduleId);
	}
	
	/**
	 * 
	 * @描述: 校验数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-16 下午01:52:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mid
	 * @param data
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	public JSONObject dataValidation(String mid, JSONArray data) throws Exception{
		JSONObject d = new JSONObject();
		boolean check = true;
		if(data != null){
			for (int i = 0; i < data.length(); i++) {
				JSONObject group = data.getJSONObject(i);
				String meid = group.getString("meid");
				String gid = group.getString("gid");
				String lx = group.getString("lx");
				if(StringUtils.equalsIgnoreCase(ExtendGroup.LX_NORMAL, lx)){
					JSONArray gdata = group.getJSONArray("gdata");
					for (Iterator<JSONObject> iterator = gdata.iterator(); iterator
							.hasNext();) {
						JSONObject gitem = iterator.next();
						String zdz = gitem.getString("zdz");
						String gs = gitem.getString("gs");
						if(StringUtils.isBlank(gs)){
							continue;
						}
						FieldFormat formator = FieldFormat.getInstance(zdz, gs);
						String[] validate = formator.validate();
						if(StringUtils.equals("0", validate[0])){check = false;}
						gitem.put("v", validate[0]);
						gitem.put("m", validate[1]);
					}
				}else if(StringUtils.equalsIgnoreCase(ExtendGroup.LX_LIST, lx)){
					JSONArray gdateList = group.getJSONArray("gdata");
					for (Iterator<JSONArray> iterator = gdateList.iterator(); iterator.hasNext();) {
						JSONArray gitem = iterator.next();
						for (Iterator<JSONObject> it2 = gitem.iterator(); it2.hasNext();) {
							JSONObject item = it2.next();
							String zdz = item.getString("zdz");
							String gs = item.getString("gs");
							if(StringUtils.isBlank(gs)){
								continue;
							}
							FieldFormat formator = FieldFormat.getInstance(zdz, gs);
							String[] validate = formator.validate();
							if(StringUtils.equals("0", validate[0])){check = false;}
							item.put("v", validate[0]);
							item.put("m", validate[1]);
						}
					}
				}
			}
		}
		d.put("success", check?"1":"0");
		d.put("data", data);
		return d;
	}
	
}
