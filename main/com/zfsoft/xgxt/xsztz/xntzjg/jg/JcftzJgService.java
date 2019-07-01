/**
 * @部门:学工产品事业部
 * @日期：2016-3-31 上午11:19:44 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.xntzjg.sh.JcftzShDao;
import com.zfsoft.xgxt.xsztz.xntzjg.sh.JcftzShService;
import com.zfsoft.xgxt.xsztz.xntzjg.sq.JcftzSqDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-31 上午11:19:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcftzJgService extends SuperServiceImpl<JcftzJgForm, JcftzJgDao>{
	private JcftzJgDao dao = new JcftzJgDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	/** 
	 * @描述:是否有结果记录
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-31 上午11:35:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecord(JcftzJgForm t){
		return dao.isHaveRecord(t);
	} 
	
	/** 
	 * @描述:审核流通过删除原有记录
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-31 上午11:40:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delForSq(JcftzJgForm t) throws Exception{
		return dao.delForSq(t);
	}
	
	/** 
	 * @描述:更改认定状态 
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-31 上午11:51:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateRenDing(JcftzJgForm t) throws Exception {
		return dao.updateRenDing(t);
	}
	
	/** 
	 * @描述:得到项目列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-5 上午10:24:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmSelectList(JcftzJgForm t,User user){
       return dao.getXmSelectList(user,t);
	}
	
	/** 
	 * @描述:得到需要认定的学生列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-5 下午02:17:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsListForRenDing(JcftzJgForm t,User user) throws Exception{
		return dao.getXsListForRenDing(t, user);
	}
	
	/** 
	 * @描述:保存
	 * @作者：保存[工号：1282]
	 * @日期：2016-4-5 下午06:37:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveJg(JcftzJgForm t) throws Exception{
		boolean result = false;
		String csms = t.getCsms();
		
		String[] bz1s=t.getBz1s();
		String[] bz2s=t.getBz2s();
		String[] bz3s=t.getBz3s();
		String[] bz4s=t.getBz4s();
		String[] bz5s=t.getBz5s();
		
		if("save".equalsIgnoreCase(t.getType())){
			String[] xhs = t.getXhs();
			String[] jxdms = t.getJxdms();
			String[] tzhjcfs = t.getTzhjcfs();
			String[] sfqqs = t.getSfqqs();
			if("1".equals(csms)){
				for(int i=0;i<xhs.length;i++){				
					t.setJgid(null);				
					t.setXh(xhs[i]);
					if(jxdms == null || jxdms.length == 0){
						t.setJxdm("");
					}
					else if("0".equalsIgnoreCase(jxdms[i])){
						t.setJxdm("");
					}else{   
						t.setJxdm(jxdms[i]);
					}			
					t.setTzhjcf(tzhjcfs[i]);
					t.setSfqq(sfqqs[i]);
					
					t.setBz1(bz1s[i]);
					t.setBz2(bz2s[i]);
					t.setBz3(bz3s[i]);
					t.setBz4(bz4s[i]);
					t.setBz5(bz5s[i]);
					
					t.setXfrdjgzt(Constants.TG);
					result = dao.updateRenDing(t);
						result = dao.runInsert(t);
					
				}
			}else if("2".equals(csms)){
				t.setXfrdjgzt(Constants.TG);
				this.updateTtxx(t);
				result = dao.updateRenDing(t);
			}else{
				return false;
			}
			
		}else{
			String[] jgids = t.getJgids();
			String[] xhs = t.getXhs();
			String[] jxdms = t.getJxdms();
			String[] tzhjcfs = t.getTzhjcfs();
			String[] sfqqs = t.getSfqqs();
			for(int i=0;i<xhs.length;i++){				
				t.setJgid(jgids[i]);				
				t.setXh(xhs[i]);
				if(jxdms == null || jxdms.length == 0){
					t.setJxdm("");
				}
				else if("0".equalsIgnoreCase(jxdms[i])){
					t.setJxdm("");
				}else{
					t.setJxdm(jxdms[i]);
				}			
				t.setTzhjcf(tzhjcfs[i]);
				t.setSfqq(sfqqs[i]);
				t.setXfrdjgzt(Constants.TG);
				t.setBz1(bz1s[i]);
				t.setBz2(bz2s[i]);
				t.setBz3(bz3s[i]);
				t.setBz4(bz4s[i]);
				t.setBz5(bz5s[i]);
				result = dao.runUpdate(t);
			}
		}
		
		
		
		return result;
	}
	
	/** 
	 * @描述:结果删除（专用于结果）
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-8 下午01:54:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delForJg(JcftzJgForm t) throws Exception{
		return dao.delForJg(t);
	}
	
	/** 
	 * @描述:得到需要修改认定的学生 
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-8 下午03:47:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsListForUpdate(JcftzJgForm t, User user) throws Exception{
		return dao.getXsListForUpdate(t, user);
	}
	
	/** 
	 * @描述:得到项目信息
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-8 下午04:17:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXmxx(JcftzJgForm t){
		return dao.getXmxx(t);
	}
	
	/**
	 * 
	 * @描述: 得到要认定的团体
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-5 上午11:24:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtListForRenDing(JcftzJgForm t, User user) throws Exception {
	   return dao.getTtListForRenDing(t, user);
	}
		
	/**
	 * 
	 * @描述: 团体项目认定数据插入结果库
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-4 下午05:27:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcftzJgForm
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void updateTtxx(JcftzJgForm t) throws Exception{
		List<HashMap<String,String>> list = new JcftzSqDao().getJcfTzTtCyxx(t.getXmdm());
		String[] jgids = t.getJgids();
		String[] jxdms = t.getJxdms();
		String[] tzhjcfs = t.getTzhjcfs();
		String[] sfqqs = t.getSfqqs();
		
		String[] bz1s=t.getBz1s();
		String[] bz2s=t.getBz2s();
		String[] bz3s=t.getBz3s();
		String[] bz4s=t.getBz4s();
		String[] bz5s=t.getBz5s();
		
		ArrayList<String[]> paralist = new ArrayList<String[]>();
		for(HashMap<String,String> map :list){
			String jgid = map.get("jgid");
			for (int i = 0; i < jgids.length; i++) {
				if(jgids[i].equals(jgid)){
					String jxdm = "";
					if(jxdms == null || jxdms.length == 0 || StringUtils.isNull(jxdms[0])){
						jxdm = "";
					}else if("0".equalsIgnoreCase(jxdms[i])){
						jxdm = "";
					}else{   
						jxdm = jxdms[i];
					}			
					paralist.add(new String[]{map.get("xmdm"),map.get("xh"),tzhjcfs[i],jxdm,sfqqs[i],t.getLylcywid(),"1",bz1s[i],bz2s[i],bz3s[i],bz4s[i],bz5s[i]});
					break;
				}
			}
		}
		new JcftzShDao().updateTtxx(t, paralist);
	}
}
