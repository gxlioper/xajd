package xsgzgl.xtwh.general.mobilemessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 短信发送
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-7-29 上午11:40:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class MobileMessageService  extends SuperServiceImpl<MobileMessageForm, MobileMessageDao> {
	
	
	//中医药短信参数
	public static final String sendMsg="http://60.191.116.236:8080/gxtMsg/SendMsgServer";
	public static final String loginName="0571026";
	public static final String loginPWD="12345678";



	/**
	 * 实现短信群发方法
	 * @throws Exception 
	 */
	public boolean sendGroup(String[] lxdhs,MobileMessageForm model) throws Exception {
		StringBuffer failSxr = new StringBuffer();
		List<HashMap<String,String>> sxrList = dao.getSxrList(model);
		boolean result=false;
	if(null==sxrList||sxrList.size()==0){
		String[] sxrs= model.getSxr().split(";");
		
		//过滤没有手机号码的用户
		for (int i = 0; i < sxrs.length; i++) {
			if(StringUtils.isNull(lxdhs[i])){
				if(i!=0){
					failSxr.append(",");
				}
					failSxr.append(sxrs[i]);
			}
		}
		
		for (int i = 0; i < lxdhs.length; i++) {
			//调用单发方法发送
			if(StringUtils.isNotNull(lxdhs[i])){
				result=sendSingle(lxdhs[i],model.getFsnr());
				if(!result){
					if(failSxr.length()!=0){
						failSxr.append(",");
					}
						failSxr.append(sxrs[i]);
					
				}
			}
		}
	}else{
		for (int i = 0; i < sxrList.size(); i++) {
			//调用单发方法发送
				result=sendSingle(sxrList.get(i).get("lxdh"),model.getFsnr());
				if(!result){
					if(failSxr.length()!=0){
						failSxr.append(",");
					}
						failSxr.append(sxrList.get(i).get("xm"));
					
				}
			}
		
	}
		model.setFailsxr(failSxr.toString());
		//重置发送完成后，发送记录存储至学工数据库
			return dao.runInsert(model);
	}
	
   /**
    * 实现短信单发方法
    */
	public boolean sendSingle(String lxdh,String fsnr) {
		String result = sendMsg(lxdh, fsnr);
		return "00000".equals(result)?true:false;
	}
	
	/**
	 * 
	 * @描述:短信发送具体接口调用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-7-28 上午11:25:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mobiles
	 * @param content
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String sendMsg(String mobiles,String content) {
		String serviceAddress=sendMsg;
		String loginname=loginName;
		String loginpwd=md5(loginPWD);
		String flag=""; 		
		try {
			URL postUrl = new URL(serviceAddress);
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setChunkedStreamingMode(5);
			connection.connect();

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "GBK"));
			String param = "loginName="+loginname
					+ "&loginPWD="+loginpwd
					+ "&mobiles=" + mobiles
					+ "&content=" + content;
			out.write(param);
			out.flush();
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				flag=line.trim();
			}
			reader.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
 /**
  * 
  * @描述:MD5加密
  * @作者：xiaxia[工号：1104]
  * @日期：2016-7-29 上午11:41:46
  * @修改记录: 修改者名字-修改日期-修改内容
  * @param loginPWD
  * @return
  * String 返回类型 
  * @throws
  */
	public String md5(String loginPWD){
		String backString="";
    	Date date=new Date();
    	SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
    	String day=format.format(date);
    	String checkString=String.valueOf(((Integer.parseInt(day))&(Integer.parseInt(loginPWD))));
    	if(checkString.length()<8){
    		for(int i=0;i<8-checkString.length();i++){
    			checkString="0"+checkString;
    		}
    	}
		try {
				  char hexDigits[] = {       // 用来将字节转换成 16 进制表示的字符
				     '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'}; 
				    java.security.MessageDigest md = java.security.MessageDigest.getInstance( "MD5" );
				    md.update( checkString.substring(2).getBytes());
				    byte tmp[] = md.digest();          
				    char str[] = new char[16 * 2];   
				    int k = 0;                               
				    for (int i = 0; i < 16; i++) {          
				    byte byte0 = tmp[i];                 
				    str[k++] = hexDigits[byte0 >>> 4 & 0xf];   
				    str[k++] = hexDigits[byte0 & 0xf];            
				    } 
				    backString = new String(str);                                
		} catch (Exception e) {
			e.printStackTrace();
		}
		return backString;
	}
	
	public static void main(String[] args){
		MobileMessageService zj = new MobileMessageService();
	String result=zj.sendMsg("18767106890", "短信测试");
	
	System.out.println(result);
}
	
}
