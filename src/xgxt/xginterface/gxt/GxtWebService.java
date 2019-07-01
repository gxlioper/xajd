package xgxt.xginterface.gxt;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;



import xgxt.action.Base;

public class GxtWebService {
	
	
//	public static void main(String[] args) {
//		try {
////			String wsdlUrl = "http://221.131.216.59/sms/services/GaoxtSMSInterfaceForSchools?wsdl";
////			String nameSpaceUri = "http://221.131.216.59/sms/services/GaoxtSMSInterfaceForSchools";
////			// 创建调用对象
////			Service service = new Service();
////			Call call = (Call)service.createCall();
////			QName qn = new QName("http:// 221.131.216.59/sms/services/GaoxtSMSInterfaceForSchools","moitem");
////			call.registerTypeMapping(MOItem.class, qn, new BeanSerializerFactory(MOItem.class,qn), new BeanDeserializerFactory(MOItem.class,qn));
////			// 调用getMessage
////			call.setOperationName(new QName(nameSpaceUri, "sendSMS"));   
////			call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));  
////			//int ret = (Integer)call.invoke(new Object[] {});
////			String loginName = "zhengfang";
////			String passWord = "gaoxt.com";
//////			String schoolCode = Base.xxdm;
////			String schoolCode = "13275";
////			//发送信息内容
////			String smsMsg = "测试啊测试";
////			String sendTime = "2011-3-14 11:14:00";
////		
////			String mps = "201020150218";
////			long userDefId = 1;
////			//MOItem[] MOItems = (MOItem[]) call.invoke(new Object[]{ 
////					//loginName,passWord,schoolCode}); 
////			Object ret = (Object)call.invoke(new Object[] { 
////					loginName,passWord,schoolCode,smsMsg,sendTime,mps,userDefId});
////			/*
////			if (MOItems != null){
////				for (int i = 0;i<MOItems.length;i++){
////					MOItem r = new MOItem();
////					r = MOItems[i];
////					System.out.println("return value is " + r.getMobile()); 
////					System.out.println("return value is " + r.getMoTime());
////					System.out.println("return value is " + r.getMsg());
////					System.out.println("=================================");
////				}
////			} else {
////				System.out.println("return value is"+MOItems);
////			}
////			*/
////			System.out.println("return value is"+ret.toString());
//			
//			String wsdlUrl= "http://221.131.216.59/sms/services/GaoxtSMSInterfaceForSchools?wsdl";
//			String nameSpaceUri = "http://221.131.216.59/sms/services/GaoxtSMSInterfaceForSchools";
//			// 创建调用对象
//			Service service = new Service();
//			Call call = (Call)service.createCall();
//			QName qn = new QName("http://221.131.216.59/sms/services/GaoxtSMSInterfaceForSchools","rptitem");
//			call.registerTypeMapping(RPTItem.class, qn, new BeanSerializerFactory(RPTItem.class,qn), new BeanDeserializerFactory(RPTItem.class,qn));
//			// 调用getMessage
//			call.setOperationName(new QName(nameSpaceUri, "receiveRPT"));   
//			call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));  
//			//int ret = (Integer)call.invoke(new Object[] {});
//			String loginName = "zhengfang";
//			String passWord = "5ccc2613eaf720a6c3d105a383f29f19";
//			String schoolCode = "13275";
//			long userDefId = 1;
//			RPTItem[] RPTItems = (RPTItem[]) call.invoke(new Object[]{ 
//					loginName,passWord,schoolCode,userDefId}); 
//			//Object ret = (Object)call.invoke(new Object[] { 
//					//loginName,passWord,schoolCode,userDefId});
//			for (int i = 0;i<RPTItems.length;i++){
//				RPTItem r = new RPTItem();
//				r = RPTItems[i];
//				System.out.println("return value is " + r.getMobile()); 
//				System.out.println("return value is " + r.getRptTime());
//				System.out.println("return value is " + r.getUserdefid());
//				System.out.println("return value is " + r.getStatus());
//				System.out.println("=====================================");
//			}
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		
//	}
	
	public String sendSMS(SendMess sendMess) { 
		try {
			
			String nameSpaceUri = Base.getInitProperties().get("gxtWebService");
			String wsdlUrl = nameSpaceUri+"?wsdl";
			// 创建调用对象
			Service service = new Service();
			Call call = (Call)service.createCall();
			QName qn = new QName(nameSpaceUri,"moitem");
			call.registerTypeMapping(MOItem.class, qn, new BeanSerializerFactory(MOItem.class,qn), new BeanDeserializerFactory(MOItem.class,qn));
			// 调用getMessage
			call.setOperationName(new QName(nameSpaceUri, "sendSMS"));   
			call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));  
			//int ret = (Integer)call.invoke(new Object[] {});
			String loginName = sendMess.getLoginName();
			String passWord = sendMess.getPassWord();
			String schoolCode = sendMess.getSchoolCode();
			//发送信息内容
			String smsMsg = sendMess.getSmsMsg();
			String sendTime = sendMess.getSendTime();
		
			String mps = sendMess.getMps();
			long userDefId = sendMess.userDefId;
			//MOItem[] MOItems = (MOItem[]) call.invoke(new Object[]{ 
					//loginName,passWord,schoolCode}); 
			Object ret = (Object)call.invoke(new Object[] { 
					loginName,passWord,schoolCode,smsMsg,sendTime,mps,userDefId});
			/*
			if (MOItems != null){
				for (int i = 0;i<MOItems.length;i++){
					MOItem r = new MOItem();
					r = MOItems[i];
					System.out.println("return value is " + r.getMobile()); 
					System.out.println("return value is " + r.getMoTime());
					System.out.println("return value is " + r.getMsg());
					System.out.println("=================================");
				}
			} else {
				System.out.println("return value is"+MOItems);
			}
			*/
//			System.out.println("return value is"+ret.toString());
			return ret.toString();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
//	上行实例 
    public static void mainreceiveSM(SendMess sendMess) {
		try {
			String nameSpaceUri = Base.getInitProperties().get("gxtWebService");
			String wsdlUrl = nameSpaceUri+"?wsdl";
			// 创建调用对象
			Service service = new Service();
			Call call = (Call)service.createCall();
			QName qn = new QName(nameSpaceUri,"moitem");
			call.registerTypeMapping(MOItem.class, qn, new BeanSerializerFactory(MOItem.class,qn), new BeanDeserializerFactory(MOItem.class,qn));
			// 调用getMessage
			call.setOperationName(new QName(nameSpaceUri, "receiveSM"));   
			call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));  
			//int ret = (Integer)call.invoke(new Object[] {});
			String loginName = sendMess.getLoginName();
			String passWord = sendMess.getPassWord();
			String schoolCode = sendMess.schoolCode;
			//String smsMsg = "短信接口测试1622";
			//String sendTime = "2010-12-17 15:44:39";
			//String mps = "13757149910";
			//long userDefId = 1;
			MOItem[] MOItems = (MOItem[]) call.invoke(new Object[]{ 
					loginName,passWord,schoolCode}); 
			//Object ret = (Object)call.invoke(new Object[] { 
					//loginName,passWord,schoolCode,userDefId});
			if (MOItems != null){
				for (int i = 0;i<MOItems.length;i++){
					MOItem r = new MOItem();
					r = MOItems[i];
					System.out.println("return value is " + r.getMobile()); 
					System.out.println("return value is " + r.getMoTime());
					System.out.println("return value is " + r.getMsg());
					System.out.println("==================================================");
				}
			} else {
				System.out.println("return value is"+MOItems);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

//回执短信


    public int receiveRPT(SendMess sendMess) {
			try {
				String nameSpaceUri = Base.getInitProperties().get("gxtWebService");
				String wsdlUrl = nameSpaceUri+"?wsdl";
				// 创建调用对象
				Service service = new Service();
				Call call = (Call)service.createCall();
				QName qn = new QName(nameSpaceUri,"rptitem");
				call.registerTypeMapping(RPTItem.class, qn, new BeanSerializerFactory(RPTItem.class,qn), new BeanDeserializerFactory(RPTItem.class,qn));
				// 调用getMessage
				call.setOperationName(new QName(nameSpaceUri, "receiveRPT"));   
				call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));  
				//int ret = (Integer)call.invoke(new Object[] {});
				String loginName = sendMess.getLoginName();
				String passWord = sendMess.getPassWord();
				String schoolCode = sendMess.getSchoolCode();
				long userDefId = sendMess.getUserDefId();
				RPTItem[] RPTItems = (RPTItem[]) call.invoke(new Object[]{ 
						loginName,passWord,schoolCode,userDefId}); 
				//Object ret = (Object)call.invoke(new Object[] { 
						//loginName,passWord,schoolCode,userDefId});
				if(RPTItems!=null&&RPTItems.length==1){
					return RPTItems[0].getStatus();
				}else{
					return 8888;
				}
		}catch(Exception ex){
			ex.printStackTrace();
			//
			return 8888;
		}
}

}
