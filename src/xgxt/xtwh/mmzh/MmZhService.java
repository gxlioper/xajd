/**
 * @部门:学工产品事业部
 * @日期：2015-7-27 下午04:08:08 
 */  
package xgxt.xtwh.mmzh;

import com.zfsoft.ms.MessageBody;
import com.zfsoft.ms.MessageOutputProvider;
import com.zfsoft.ms.MessageType;
import com.zfsoft.ms.mail.EmailMessageOutputProvider;
import com.zfsoft.ms.sms.SMSMessageOutputProvider;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.DAO.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-27 下午04:08:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class MmZhService extends SuperServiceImpl<MmZhForm, MmZhDao> {

	DAO d = DAO.getInstance();

	//所有密码问题选项list,供用户密码问题设置下拉框选择
	public List<HashMap<String, String>> getMbList(){
		return dao.getMbList();
	}
	
	//获取登陆用户已设置的密码问题
	public  HashMap<String, String> getmbjl(String yhm){
		return dao.getmbjl(yhm);
	}
	
	//验证用户表中是否存在该记录
	public boolean checkYhbExits(String yhm){
		boolean flag = false;
		String result = dao.checkYhbExits(yhm);
		if((!result.trim().equals("")) && (result != null)){
			flag = true;
		}
		return flag;
	}
	
	//验证学生密码表中是否存在该记录
	public boolean checkXsmmbExits(String xh){
		boolean flag = false;
		String result = dao.checkXsmmbExits(xh);
		if((!result.trim().equals("")) && (result != null)){
			flag = true;
		}
		return flag;
	}
	
	//验证密码找回表中是否存在该记录
	public boolean checkMbwtExits(String yhm){
		boolean flag = false;
		String result = dao.checkMbwtExits(yhm);
		if((!result.trim().equals("")) && (result != null)){
			flag = true;
		}
		return flag;
	}
	
	public HashMap<String, String> getMbmap(String yhm){
		return dao.getMbmap(yhm);
	}

	/**
	 * 更新找回方式的启用状态
	 * @param mmZhForm
	 * @return
	 * @throws SQLException
	 */
	public boolean updateZhfs(MmZhForm mmZhForm) throws SQLException {

		String s = "delete from XG_MMWH_CSSZ where zhfs = ?";
		List<String[]> list = new ArrayList<String[]>();
		String[] x1 = {mmZhForm.getEmail()};
		String[] x2 = {mmZhForm.getPhone()};
		String[] x3 = {mmZhForm.getMb()};
		list.add(x1);
		list.add(x2);
		list.add(x3);
		int[] i = d.runBatch(s,list);

		String sql = "insert into XG_MMWH_CSSZ(zhfs,qyzt) VALUES(?,?)";
		List<String[]> list2 = new ArrayList<String[]>();
		String[] s1 = {mmZhForm.getEmail(),mmZhForm.getEmail_qyzt()};
		String[] s2 = {mmZhForm.getPhone(),mmZhForm.getPhone_qyzt()};
		String[] s3 = {mmZhForm.getMb(),mmZhForm.getMb_qyzt()};
		list2.add(s1);
		list2.add(s2);
		list2.add(s3);
		int[] j = d.runBatch(sql,list2);
		return j.length == 3;
	}

	/**
	 * 获取找回方式
	 * @return
	 */
	public List<HashMap<String,String>> getzt(){
		String sql = "select * from XG_MMWH_CSSZ";
		return d.getListNotOut(sql,new String[]{});
	}

	/**
	 * 获取启用的找回方式
	 * @return
	 */
	public List<HashMap<String,String>> getzhfs(){
		String sql = "select * from XG_MMWH_CSSZ where qyzt = 1";
		return d.getListNotOut(sql,new String[]{});
	}

	/**
	 * 获取密码找回方式
	 */
	public String getZhfs(String yhm,String zhfs){
		String zhfsResult;
		String sql = "select dzyx,lxdh from xsxxb where xh = ?";
		String[] result = d.getOneRs(sql,new String[]{yhm},new String[]{"dzyx","lxdh"});
		if(result == null){
			//老师用户
			String s = "select dzyx,lxdh from FDYXXB where zgh = ? ";
			result = d.getOneRs(s,new String[]{yhm},new String[]{"dzyx","lxdh"});
		}

		if("email".equals(zhfs)){
			zhfsResult = result[0];
		} else {//电话
			zhfsResult = result[1];
		}
		return zhfsResult;
	}

	/**
	 * 发送验证码给用户
	 * @return
	 */
	public boolean sendMessage(String yzm,String zh,String zhfs) throws Exception {

		MessageBody message = new MessageBody(MessageType.EMAIL);
//		// 设置收件人的邮箱
		message.getBody().put("sendTo", zh);
//		// 设置邮件标题
		message.getBody().put("subject", "正方学工系统密码找回" );
		message.getBody().put("content", yzm);
		MessageOutputProvider provider;
		if("email".equals(zhfs)){
			provider = new EmailMessageOutputProvider();
		} else {
			provider = new SMSMessageOutputProvider();
		}
		boolean flag = provider.output(message);
		return flag;
	}
}
