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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ���ŷ���
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-7-29 ����11:40:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class MobileMessageService  extends SuperServiceImpl<MobileMessageForm, MobileMessageDao> {
	
	
	//��ҽҩ���Ų���
	public static final String sendMsg="http://60.191.116.236:8080/gxtMsg/SendMsgServer";
	public static final String loginName="0571026";
	public static final String loginPWD="12345678";



	/**
	 * ʵ�ֶ���Ⱥ������
	 * @throws Exception 
	 */
	public boolean sendGroup(String[] lxdhs,MobileMessageForm model) throws Exception {
		StringBuffer failSxr = new StringBuffer();
		List<HashMap<String,String>> sxrList = dao.getSxrList(model);
		boolean result=false;
	if(null==sxrList||sxrList.size()==0){
		String[] sxrs= model.getSxr().split(";");
		
		//����û���ֻ�������û�
		for (int i = 0; i < sxrs.length; i++) {
			if(StringUtils.isNull(lxdhs[i])){
				if(i!=0){
					failSxr.append(",");
				}
					failSxr.append(sxrs[i]);
			}
		}
		
		for (int i = 0; i < lxdhs.length; i++) {
			//���õ�����������
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
			//���õ�����������
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
		//���÷�����ɺ󣬷��ͼ�¼�洢��ѧ�����ݿ�
			return dao.runInsert(model);
	}
	
   /**
    * ʵ�ֶ��ŵ�������
    */
	public boolean sendSingle(String lxdh,String fsnr) {
		String result = sendMsg(lxdh, fsnr);
		return "00000".equals(result)?true:false;
	}
	
	/**
	 * 
	 * @����:���ŷ��;���ӿڵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-7-28 ����11:25:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mobiles
	 * @param content
	 * @return
	 * String �������� 
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
  * @����:MD5����
  * @���ߣ�xiaxia[���ţ�1104]
  * @���ڣ�2016-7-29 ����11:41:46
  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
  * @param loginPWD
  * @return
  * String �������� 
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
				  char hexDigits[] = {       // �������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�
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
	String result=zj.sendMsg("18767106890", "���Ų���");
	
	System.out.println(result);
}
	
}
