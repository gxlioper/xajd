package xsgzgl.gyjc.jcrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;

public class JcrcService extends SuperServiceImpl<JcrcForm, JcrcDao> {
	/**
	 * 
	 * @描述: 增加时抽查明细列表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-14 上午11:58:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcrcAddList(JcrcForm t){
		return dao.getJcrcAddList(t);
	}
	
	/**
	 * 
	 * @描述: 随机抽取寝室
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-18 上午08:53:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> produceSjqsList(JcrcForm t){
		return dao.produceSjqsList(t);
	}
	
	/**
	 * 
	 * @描述: 保存检查日程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-18 上午09:53:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean  saveJcRc(JcrcForm t) throws Exception {
		/**
		 * 如果guid不为空，即为修改类型，需要先删除原来的数据
		 */
		String[] xydms = t.getXydms();
		String[] jcbls = t.getJcbls();
		//参数组合
		String rcid = t.getGuid();
		 t.setType("update");
		if(StringUtils.isNull(rcid)){
			 rcid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			 t.setType("save");
		}
		List<String[]> jcblList = new ArrayList<String[]>();
		List<String[]> jcmxList = new ArrayList<String[]>();
		List<String> jjlxList = new ArrayList<String>();
		if(StringUtils.isNotNull(t.getWsjc())){
			jjlxList.add(t.getWsjc());
		}
		if(StringUtils.isNotNull(t.getAqjc())){
			jjlxList.add(t.getAqjc());
		}
		if(StringUtils.isNotNull(t.getJljc())){
			jjlxList.add(t.getJljc());
		}
		for (int i = 0; i < xydms.length; i++) {
			if(StringUtils.isNotNull(jcbls[i])){
				jcblList.add(new String[]{rcid,xydms[i],jcbls[i]});
			}
		}
		List<HashMap<String, String>> sjsList = dao.produceSjqsList(t);
		//如果没有产生任何抽查数据，返回提示。
		if( sjsList == null || sjsList.size() == 0){
			throw new SystemException(MessageKey.XG_GYJC_JCRC_NO_DATA);
		}
		for (int i = 0; i < sjsList.size(); i++) {
			for (int j = 0; j < jjlxList.size(); j++) {
				jcmxList.add(new String[]{rcid,sjsList.get(i).get("xydm"),sjsList.get(i).get("lddm"),sjsList.get(i).get("qsh"),jjlxList.get(j)});
			}
		}
		t.setGuid(rcid);
		return this.saveJcrcInDb(jcblList, jcmxList, t);
	}
	
	/**
	 * 
	 * @描述: 保存数据至数据库
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-18 下午03:27:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcblList
	 * @param jcmxList
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJcrcInDb(List<String[]> jcblList,List<String[]> jcmxList,JcrcForm t) throws Exception{
		boolean rs = true;
		rs = dao.saveJcrc(t);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		rs = dao.saveJcbl(jcblList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		rs = dao.saveJcmx(jcmxList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述: 修改时获取jcbl明细
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-18 下午03:41:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getUpdatejcrcmx(String rcid) throws Exception{
		return dao.getUpdatejcrcmx(rcid);
	}
	
	/**
	 * 
	 * @描述: 验证是否有提交数据,为了后续可能存在批量删除的情况
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-18 下午03:53:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIfExistTj(String rcid ){
		return dao.checkIfExistTj(rcid);
	}
	
	/**
	 * 
	 * @描述: 验证当前是否为最新检查日程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-18 下午04:10:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIfLastNew(String rcid,String js,String xydm){
		return dao.checkIfLastNew(rcid, js,xydm);
	}
	
	/**
	 * 
	 * @描述: 验证日期是否有交集
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-18 下午04:58:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ccrq
	 * @param jzrq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIfRqIntersection(String ccrq,String jzrq,String rcid,String js,String xydm){
		return dao.checkIfRqIntersection(ccrq, jzrq, rcid, js, xydm);
	}
	
	/**
	 * 
	 * @描述: 删除检查日程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-18 下午05:54:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJcrc(String rcid,boolean flag) throws Exception{
		boolean rs = true;
		if(flag){
			 rs = dao.delJcrcB(new String[]{rcid});
			if(!rs){
				throw new SystemException(MessageKey.SYS_DEL_FAIL);
			}
		}
		rs = dao.delJcblB(new String[]{rcid});
		if(!rs){
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		rs = dao.delJcmxBz(rcid);
		if(!rs){
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		
		rs = dao.delJcmxB(new String[]{rcid});
		if(!rs){
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return rs;
	
    }

	public boolean delJcrcTran(String rcid,boolean flag) throws Exception{
		return this.delJcrc(rcid,flag);
	}
	
	/**
	 * 
	 * @描述: 获取组合List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-19 上午09:21:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcmxList
	 * @param jcblList
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
    public List<HashMap<String, Object>> getZhList(List<HashMap<String, String>> jcmxList,List<HashMap<String, String>> jcblList,List<HashMap<String, String>> ldxxList){
    	List<HashMap<String, Object>> zhList = new ArrayList<HashMap<String,Object>>();
		for (HashMap<String, String> jcmx : jcmxList) {
			String jcblTemp = "";
			for (HashMap<String, String> jcbl : jcblList) {
				if(jcmx.get("xydm").equals(jcbl.get("xydm"))){
					jcblTemp = jcbl.get("jcbl");
					break;
				}
			}
			List<HashMap<String, String>> tempLdList = new ArrayList<HashMap<String,String>>();
			for (HashMap<String, String> ld : ldxxList) {
				if(jcmx.get("xydm").equals(ld.get("xydm"))){
					tempLdList.add(ld);
				}
			}
			HashMap<String,Object> tempMap = new HashMap<String, Object>();
			tempMap.putAll(jcmx);
			tempMap.put("jcblinput", jcblTemp);
			tempMap.put("ld",tempLdList);
			zhList.add(tempMap);
		}
		return zhList;
	}
    
    /**
	 * 
	 * @描述: 获取选择楼栋信息List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-5-23 下午05:37:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSelLdList(JcrcForm t){
		return dao.getSelLdList(t);
	}
	
	/**
	 * 
	 * @描述: 修改时获取本次日程抽了多少个楼栋，多少个寝室
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-5-24 上午11:22:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdxxList(String rcid){
		return dao.getLdxxList(rcid);
	}
    
}
