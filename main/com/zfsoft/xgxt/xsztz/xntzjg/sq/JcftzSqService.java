/**
 * @部门:学工产品事业部
 * @日期：2016-3-29 上午09:04:00 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-29 上午09:04:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcftzSqService extends SuperServiceImpl<JcftzSqForm, JcftzSqDao>{
	private JcftzSqDao dao = new JcftzSqDao();
	private XmsbjgDao xmsbjgDao = new XmsbjgDao();
	
	/** 
	 * @描述:得到认定学生列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午02:27:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListForRenDing(JcftzSqForm t, User user) throws Exception{
		return dao.getPageListForRenDing(t, user);
	}
	
	public HashMap<String, String> getXmxx(String xmdm){
		return dao.getXmxx(xmdm);
	}
	
	public HashMap<String, String> getXmxxByjgid(String jgid){
		return dao.getXmxxByjgid(jgid);
	}
	
	/** 
	 * @描述:实时保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午02:27:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveJcftzSq(JcftzSqForm t) throws Exception{
		return dao.saveJcftzSq(t);
	}
	
	/** 
	 * @描述:是否可提交
	 * @作者：柳俊[工号：1282]
	 * @修改者：yxy，加入团体项目判断
	 * @日期：2016-3-29 下午04:33:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public void checkIsCanSubmit(JcftzSqForm form) throws Exception {
		String[] ids = form.getXmdms();
		//参赛模式
		String[] csms = form.getCsms();
		boolean result = true;
		for(int i = 0;i<ids.length;i++){
			List<HashMap<String, String>> rymap = null;
			String Csms = csms[i];
			if("1".equals(Csms)){
				rymap = dao.getJcfTzRyxx(ids[i]);
			}else if("2".equals(Csms)){
				rymap = dao.getJcfTzTtxx(ids[i]);
			}
			for(int j=0;j<rymap.size();j++){
				String jgid = rymap.get(j).get("jgid");
				if("1".equals(Csms)){
					result = dao.checkFz(jgid);
				}else{
					result = dao.checkTtFz(jgid);
				}
				form.setJgid(jgid);
				if (!result) {
					HashMap<String,String> map = getXmxx(ids[i]);
					form.setTzhjcf((map.get("jcxf")));
					if("1".equals(Csms)){
						dao.saveJcftzSq(form);
					}else{
						dao.saveJcftzTtSq(form);
					}
					
				}
				if("1".equals(Csms)){
					result = dao.checkQq(jgid);
				}else{
					result = dao.checkTtQq(jgid);
				}
				
				if (!result) {
					form.setSfqq("0");
					if("1".equals(Csms)){
						dao.saveJcftzSq(form);
					}else{
						dao.saveJcftzTtSq(form);
					}
				}
			}
			
		}
	}
	
	/** 
	 * @描述:提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午04:46:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String tj(JcftzSqForm form, User user) throws Exception{
		List<String> failXms = new ArrayList<String>();
		String[] ids = form.getIds();
		String splc = dao.getShlc();
		form.setRdsplc(splc);
		form.setXfrdsqzt(Constants.YW_SHZ);
		for(int i = 0;i<ids.length;i++){
			String sqid = UniqID.getInstance().getUniqIDHash();
			form.setSqid(sqid);
			boolean result = dao.tj(ids[i], user, form);
			if (!result) {
				failXms.add(ids[i]);
			}
			ShlcInterface shlc = new CommShlcImpl();
			XmsbjgForm model = xmsbjgDao.getModel(ids[i]);
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getXmdm(), "sztz_jcftz_sh.do", "sztz_jcftz_sq.do");
			}
		}
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
		}
		
	} 
	
	public String getFs(String jxdm){
		return dao.getFs(jxdm);
	}
	
	/**
	 * 
	 * @描述:团体项目上报查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-4 下午01:42:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForTtRenDing(JcftzSqForm t, User user)
	throws Exception {
		return dao.getPageListForTtRenDing(t, user);
   }
	
	/** 
	 * @描述:团体项目实时保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午02:27:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveJcftzTtSq(JcftzSqForm t) throws Exception{
		return dao.saveJcftzTtSq(t);	
	}
}
