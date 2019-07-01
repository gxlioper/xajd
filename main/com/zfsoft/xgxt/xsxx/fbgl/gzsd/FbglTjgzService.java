/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午09:43:29 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjDao;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午09:43:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglTjgzService extends
		SuperServiceImpl<FbglTjgzForm, FbglTjgzDao> {
	FbglTjgzDao dao = new FbglTjgzDao();

	public FbglTjgzService() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @描述: 修改规则配置
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-18 上午11:31:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param json
	 * @param fgt
	 * @return
	 * boolean 返回类型 
	 */
	
	public boolean updataGzpz(JSONObject json){
		String gzpzid=json.get("gzpzid").toString();
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		FbglGzpzTjForm fgt=new FbglGzpzTjForm();
		try {
			fgt=fgts.getModel(gzpzid);
			FbglGzpzTjDao fgtd=new FbglGzpzTjDao();
			if(fgtd.delGzpzXxtj(gzpzid)>0){
				return saveGzpz(json,fgt);
			}
		} catch (Exception e) {
			throw new RuntimeException("获取源数据失败！");
		}
		return false;
	}
	/**
	 * 
	 * @描述:验证规则名称是否已存在
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-9-15 下午02:07:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param gzmc
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHave(String pzgzid,String gzmc){
		return dao.isHave(pzgzid,gzmc);
	}
	/**
	 * 
	 * @描述: 保存规则配置
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-18 上午11:31:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param json
	 * @param fgt
	 * @return
	 * boolean 返回类型 
	 */
	public boolean saveGzpz(JSONObject json ,FbglGzpzTjForm fgt) {
		boolean isok = true;
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		if(fgt==null){
			fgt=new FbglGzpzTjForm();
			fgt.setGzdm(json.get("gzdm").toString());
			fgt.setPzgzmc(json.get("pzgzmc").toString());
			fgt.setGxsj(GetTime.getNowTime4());
			// 保存条件规则
			fgt.setQyzt(json.get("qyzt").toString());
			String zbid = UniqID.getInstance().getUniqIDHash();
			fgt.setPzgzid(zbid);
			isok = fgts.save(fgt);
		}else{
			try {
				fgt.setGzdm(json.get("gzdm").toString());
				fgt.setPzgzmc(json.get("pzgzmc").toString());
				fgt.setGxsj(GetTime.getNowTime4());
				fgt.setQyzt(json.getString("qyzt").toString());
				isok=fgts.runUpdate(fgt);
			} catch (Exception e) {
				throw new RuntimeException("修改错误!");
			}
		}
		// 保存条件规则详细配置
		JSONArray array = json.getJSONArray("gztjObject");
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		for (int i = 0; i < array.length(); i++) {
			JSONObject jb = array.getJSONObject(i);
			FbglGzpzTjXxForm fgtx = new FbglGzpzTjXxForm();
			fgtx.setPzgzid(fgt.getPzgzid());
			fgtx.setTjgzid(jb.get("tjgzid").toString());
			fgtx.setTjszzd(jb.get("tjszzd").toString());
			fgtx.setSx(jb.get("sx").toString());
			String tjnr = jb.get("tjnr").toString();
			String[] tjnrs = tjnr.split(",");
			tjnrs=formatParam(tjnrs);
			// var defalut="0";// 默认
			// var text="1";// 文本框
			// var select="2";// 下拉框
			// var qzw="3";// 区间
			int j=0;
			if("3".equals(tjnrs[0])){
				fgtx.setXxz(tjnrs[1] +"~"+tjnrs[2]);
				j++;
			}else{
				fgtx.setXxz(tjnrs[1]);
			}
			fgtx.setSfkxg(tjnrs[j+2]);
			fgtx.setWsbl(tjnrs[j+3]);
			String ylz=tjnrs[j+4];
			String qsz=tjnrs[tjnrs.length-1];
			if(null==ylz||"null".equals(ylz)){
				ylz="";
			}
			if(null==qsz||"null".equals(qsz)){
				qsz="";
			}
			fgtx.setQsz(qsz);
			fgtx.setYlz(ylz);
			isok = fgtxs.save(fgtx);
		}
		// {"pzgzmc":"c","gztjObject":[{"tjgzid":"BBGZ_BJDM","tjszzd":"TJ_ZDY","sx":0,"tjnr":"1_c_1_1_"},{"tjgzid":"BBGZ_BJMC","tjszzd":"TJ_ZDY","sx":1,"tjnr":"0_0_1_"}]}
		return isok;
	}
	/**
	 * @描述: 格式化数据，由于页面传递空值接受不到，故控制以-1来处理，</br>
	 * 		  这里格式化回空数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-20 下午03:38:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjnrs
	 * @return
	 * String[] 返回类型 
	 * @throws
	 */
	private String[] formatParam(String[] tjnrs ){
		String[] newnrs=new String[tjnrs.length];
		int i=0;
		for(String str:tjnrs){
			//-1认为是空值
			if("null"==str||str.equals("-1")){
				newnrs[i]=null;
			}else{
				newnrs[i]=str;
			}
			i++;
		}
		return newnrs;
	}
	/**
	 * 
	 * @描述: 获取条件类型
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-27 下午04:46:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTjlx(String tjgzid) {
		if (StringUtils.isNull(tjgzid)) {
			return null;
		}
		return dao.getTjlx(tjgzid.split(","));
	}

	/**
	 * 
	 * @描述: 条件配置详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-27 下午05:06:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTjpzXx(String tjgzid) {
		return dao.getTjpzXx(tjgzid);
	}

	/**
	 * 
	 * @描述:获取条件配置内容信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-10 下午02:14:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 * @param tjszzd
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public HashMap<String, String> getTjNrpzXx(String tjgzid, String tjszzd) {
		return dao.getTjNrpzXx(tjgzid, tjszzd);
	}
	/**
	 * 
	 * @描述:获取默认预览值
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-9-14 下午04:26:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ylz
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getMrylz(String ylz) {
		String[] tableMsg = ylz.split("-");
		return dao.getMrylz(tableMsg);
	}
}
