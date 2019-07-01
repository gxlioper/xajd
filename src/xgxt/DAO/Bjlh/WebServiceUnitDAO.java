
package xgxt.DAO.Bjlh;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import com.zfsoft.webService.zfjw.BjInfo;
import com.zfsoft.webService.zfjw.BjInfoDTO;
import com.zfsoft.webService.zfjw.CheckPasswordSoapStub;
import com.zfsoft.webService.zfjw.CjInfo;
import com.zfsoft.webService.zfjw.CjInofDTO;
import com.zfsoft.webService.zfjw.XjydInfo;
import com.zfsoft.webService.zfjw.XjydInfoDTO;
import com.zfsoft.webService.zfjw.XsInfo;
import com.zfsoft.webService.zfjw.XsInfoDTO;
import com.zfsoft.webService.zfjw.XyInfo;
import com.zfsoft.webService.zfjw.XyInfoDTO;
import com.zfsoft.webService.zfjw.ZyInfo;
import com.zfsoft.webService.zfjw.ZyInfoDTO;
import com.zfsoft.webService.zjcm.Ws_xgxtLocator;
import com.zfsoft.webService.zjcm.Ws_xgxtSoap12Stub;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.WSForCjJd.WebserviceClient;

public class WebServiceUnitDAO extends WebserviceClient{
	
	/**
	 * 同步学院信息
	 * @param tableName
	 * @return
	 */
	
	public boolean synchronizeXyData(String tableName){
		
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		CheckPasswordSoapStub myStub;
		Boolean updata = false;
		
		try {
			DAO dao = DAO.getInstance();
			myStub = new CheckPasswordSoapStub(new URL(Base.getInitProperties().get("zfJwWebService")),remoteWebserviceLocator);
			String passWord = getJwWebPassWord();
			XyInfoDTO xyInfoDTO= myStub.getXyxx(passWord);
			
			XyInfo[] xyInfoList =  xyInfoDTO.getXyInfo();
			String[] insertSql = new String[xyInfoList.length];
			
			Calendar date = Calendar.getInstance();
			String bakeupFlag = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
			
			//	创建备份表
			String backUpSql = "create table zxbz_xxbmdm"+bakeupFlag+" as select * from zxbz_xxbmdm";
			updata = dao.runUpdate(backUpSql, new String[]{});
			//	清除原表里的内容
			String delSql = "delete from zxbz_xxbmdm";
			if(updata){
				updata = dao.runUpdate(delSql, new String[]{});
			}
			
			String xgbdm = dao.getOneRs("select xgbdm from xtszb where rownum = 1", new String[]{}, "xgbdm");
			//清除成功后插入数据
			if(updata){
				for(int i = 0;i<xyInfoList.length;i++){
				
					//取数据
					XyInfo xyInfo = xyInfoList[i];
					String bmdm = xyInfo.getBMDM();
			        String bmmc = xyInfo.getBMMC();
			        String bmpyjc = xyInfo.getBMPYJC();
			        String bmfdm = xyInfo.getBMFDM();
			        String bmjb = xyInfo.getBMJB();
			        String bmlb = xyInfo.getBMLB();
			        String bz =  xyInfo.getBZ();
			        
			        if(bmlb.equalsIgnoreCase(xgbdm)){
			        	bmlb = "1";
			        }else{
			        	bmlb = "5";
			        }
			        //形成sql
			        StringBuffer insertBuf = new StringBuffer("insert into zxbz_xxbmdm (bmdm,bmmc,bmpyjc,bmfdm,bmjb,bmlb,bz) values ('");
			        insertBuf.append(bmdm);
			        insertBuf.append("','");
			        insertBuf.append(bmmc);
			        insertBuf.append("','");
			        insertBuf.append(bmpyjc);
			        insertBuf.append("','");
			        insertBuf.append(bmfdm);
			        insertBuf.append("','1','");
			        insertBuf.append(bmlb);
			        insertBuf.append("','");
			        insertBuf.append(bz);
			        insertBuf.append("')");
			        
			        insertSql[i] = insertBuf.toString(); 
				}
				
				//插入数据
				int[] res = dao.runBatch(insertSql);

				for (int i = 0; i < res.length; i++) {
					updata = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!updata)
						break;
				}
				return updata;
			}
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return updata;
	}

	private String getJwWebPassWord() {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xsxxcx from wsmmb", new String[]{}, "xsxxcx");
	}

	/**
	 * 同步专业信息
	 * @param tableName
	 * @return
	 */
	
	public boolean synchronizeZyData(String tableName){
		
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		CheckPasswordSoapStub myStub;
		Boolean updata = false;
		
		try {
			DAO dao = DAO.getInstance();
			myStub = new CheckPasswordSoapStub(new URL(Base.getInitProperties().get("zfJwWebService")),remoteWebserviceLocator);
			String passWord = getJwWebPassWord();
			ZyInfoDTO zyInfoDTO= myStub.getZyxx(passWord);
			
			ZyInfo[] zyInfoList =  zyInfoDTO.getZyInfo();
			String[] insertSql = new String[zyInfoList.length];
			
			Calendar date = Calendar.getInstance();
			String bakeupFlag = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
			
			//	创建备份表
			String backUpSql = "create table bks_zydm"+bakeupFlag+" as select * from bks_zydm";
			updata = dao.runUpdate(backUpSql, new String[]{});
			//	清除原表里的内容
			String delSql = "delete from bks_zydm";
			if(updata){
				updata = dao.runUpdate(delSql, new String[]{});
			}
			
			//清除成功后插入数据
			if(updata){
				for(int i = 0;i<zyInfoList.length;i++){
				
					//取数据
					ZyInfo zyInfo = zyInfoList[i];
					String zydm = zyInfo.getZYDM();
					String bmdm = zyInfo.getBMDM();
					String xkmldm = zyInfo.getXKMLDM();
					String zymc = zyInfo.getZYMC();
					String zyywmc = zyInfo.getZYYWMC();
					String jlny = zyInfo.getJLNY();
					String gjzydm = zyInfo.getGJZYDM();
					String ksdm = zyInfo.getKSDM();
					String zyjc = zyInfo.getZYJC();
			        
			        //形成sql
			        StringBuffer insertBuf = new StringBuffer("insert into bks_zydm (bmdm,gjzydm,jlny,ksdm,xkmldm,zydm,zyjc,zymc,zyywmc) values ('");
			        insertBuf.append(bmdm);
			        insertBuf.append("','");
			        insertBuf.append(gjzydm);
			        insertBuf.append("','");
			        insertBuf.append(jlny);
			        insertBuf.append("','");
			        insertBuf.append(ksdm);
			        insertBuf.append("','-8','");
			        insertBuf.append(zydm);
			        insertBuf.append("','");
			        insertBuf.append(zyjc);
			        insertBuf.append("','");
			        insertBuf.append(zymc);
			        insertBuf.append("','");
			        insertBuf.append(zyywmc);
			        insertBuf.append("')");
			        
			        insertSql[i] = insertBuf.toString(); 
				}
				
				//插入数据
				int[] res = dao.runBatch(insertSql);

				for (int i = 0; i < res.length; i++) {
					updata = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!updata)
						break;
				}
				return updata;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return updata;
	}
	
	/**
	 * 同步专业信息
	 * @param tableName
	 * @return
	 */
	
	public boolean synchronizeXjydxxData(){
		
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		CheckPasswordSoapStub myStub;
		Boolean updata = false;
		
		try {
			DAO dao = DAO.getInstance();
			myStub = new CheckPasswordSoapStub(new URL(Base.getInitProperties().get("zfJwWebService")),remoteWebserviceLocator);
			String passWord = getJwWebPassWord();
			XjydInfoDTO xjydInfoDTO= myStub.getXjyd(passWord);
			
			XjydInfo[] xjydInfo = xjydInfoDTO.getXjydInfo();
			String[] insertSql = new String[xjydInfo.length];
			
			Calendar date = Calendar.getInstance();
			String bakeupFlag = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
			
			//	创建备份表
			String backUpSql = "create table bks_xjydxx"+bakeupFlag+" as select * from bks_xjydxx";
			updata = dao.runUpdate(backUpSql, new String[]{});
			//	清除原表里的内容
			String delSql = "delete from bks_xjydxx";
			if(updata){
				updata = dao.runUpdate(delSql, new String[]{});
			}
			
			//清除成功后插入数据
			if(updata){
				for(int i = 0;i<xjydInfo.length;i++){
				
					//取数据
					XjydInfo ydInfo = xjydInfo[i];
					String ydxh = ydInfo.getYDXH();
					String clwh = ydInfo.getCLWH();
					String czrq = ydInfo.getCZRQ();
					String ydsj = ydInfo.getYDSJ();
					String ydyy = ydInfo.getYDYY();
					String ydqbjdm = ydInfo.getYDQBJDM();
					String ydhbjdm = ydInfo.getYDHBJDM();
					String ydhxzb = ydInfo.getYDHXZB();
					String ydqxzb = ydInfo.getYDQXZB();
					String ydlb = ydInfo.getYDLB();
					String xh = ydInfo.getXH();
					
			        //形成sql
			        StringBuffer insertBuf = new StringBuffer("insert into bks_xjydxx (ydxh,clwh,ydsj,ydyy,YDqBJMC,YDHBJMC,ydqbdm,ydhbdm,ydlbm,xh) values ('");
			        insertBuf.append(ydxh);
			        insertBuf.append("','");
			        insertBuf.append(clwh);
			        insertBuf.append("','");
			        insertBuf.append(ydsj);
			        insertBuf.append("','");
			        insertBuf.append(ydyy);
			        insertBuf.append("','");
			        insertBuf.append(ydqxzb);
			        insertBuf.append("','");
			        insertBuf.append(ydhxzb);
			        insertBuf.append("','");
			        insertBuf.append(ydqbjdm);
			        insertBuf.append("','");
			        insertBuf.append(ydhbjdm);
			        insertBuf.append("','");
			        insertBuf.append(ydlb);
			        insertBuf.append("','");
			        insertBuf.append(xh);
			        insertBuf.append("')");
			        
			        insertSql[i] = insertBuf.toString(); 
				}
				
				//插入数据
				int[] res = dao.runBatch(insertSql);

				for (int i = 0; i < res.length; i++) {
					updata = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!updata)
						break;
				}
				return updata;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return updata;
	}
	
	/**
	 * 同步班级信息
	 * @param tableName
	 * @return
	 */
	
	public boolean synchronizeBjData(String tableName){
		
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		CheckPasswordSoapStub myStub;
		Boolean updata = false;
		
		try {
			DAO dao = DAO.getInstance();
			myStub = new CheckPasswordSoapStub(new URL(Base.getInitProperties().get("zfJwWebService")),remoteWebserviceLocator);
			String passWord = getJwWebPassWord();
			BjInfoDTO bjInfoDTO= myStub.getBjxx(passWord);
			
			BjInfo[] bjInfoList =  bjInfoDTO.getBjInfo();
			String[] insertSql = new String[bjInfoList.length];
			
			Calendar date = Calendar.getInstance();
			String bakeupFlag = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
			
			//	创建备份表
			String backUpSql = "create table bks_bjdm"+bakeupFlag+" as select * from bks_bjdm";
			updata = dao.runUpdate(backUpSql, new String[]{});
			//	清除原表里的内容
			String delSql = "delete from bks_bjdm";
			if(updata){
				updata = dao.runUpdate(delSql, new String[]{});
			}
			
			//清除成功后插入数据
			if(updata){
				for(int i = 0;i<bjInfoList.length;i++){
				
					//取数据
					BjInfo bjInfo = bjInfoList[i];
					
			        String bjdm = bjInfo.getBJDM();
					String zydm = bjInfo.getZYDM();
					String jzgh = bjInfo.getJZGH();
					String bmdm = bjInfo.getBMDM();
					String bjmc = bjInfo.getBJMC();
					String nj = bjInfo.getNJ();
			        
			        //形成sql
			        StringBuffer insertBuf = new StringBuffer("insert into bks_bjdm (bjdm,zydm,jzgh,bmdm,bjmc,nj) values ('");
			        insertBuf.append(bjdm);
			        insertBuf.append("','");
			        insertBuf.append(zydm);
			        insertBuf.append("','");
			        insertBuf.append(jzgh);
			        insertBuf.append("','");
			        insertBuf.append(bmdm);
			        insertBuf.append("','");
			        insertBuf.append(bjmc);
			        insertBuf.append("','");
			        insertBuf.append(nj);
			        insertBuf.append("')");
			        
			        insertSql[i] = insertBuf.toString(); 
				}
				
				//插入数据
				int[] res = dao.runBatch(insertSql);

				for (int i = 0; i < res.length; i++) {
					updata = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!updata)
						break;
				}
				return updata;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return updata;
	}

	public boolean synchronizeXsxxData() {
		
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		CheckPasswordSoapStub myStub;
		Boolean updata = false;
		
		try {
			DAO dao = DAO.getInstance();
			myStub = new CheckPasswordSoapStub(new URL(Base.getInitProperties().get("zfJwWebService")),remoteWebserviceLocator);
			String passWord = getJwWebPassWord();
			int stuCount =  myStub.getXsInfoAmount(passWord);
			//			创建备份表
			Calendar date = Calendar.getInstance();
			String bakeupFlag = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
			String backUpSql = "create table bks_xsjbxx"+bakeupFlag+" as select * from bks_xsjbxx";
			updata = dao.runUpdate(backUpSql, new String[]{});
			//	清除原表里的内容
			String delSql = "delete from bks_xsjbxx";
			if(updata){
				updata = dao.runUpdate(delSql, new String[]{});
			}
			String tableName = "bks_xsjbxx";
			//清除成功后插入数据
			if(updata){
				for(int j = 1;j<stuCount+1;j+=1000){
					XsInfoDTO xsinfodto = null;
					if(j+1000<stuCount+1){
						xsinfodto= myStub.getXsxx(Integer.toString(j),Integer.toString(j+1000-1),passWord);
					}else{
						xsinfodto= myStub.getXsxx(Integer.toString(j),Integer.toString(stuCount),passWord);
					}
					XsInfo[] xsinfolist =  xsinfodto.getXsInfo();
					String[] insertSql = new String[xsinfolist.length];
					for(int i = 0;i<xsinfolist.length;i++){
				
						//取数据
						XsInfo xsInfo = xsinfolist[i];
						//通过反射形成sql
						String[] colList = new String[]{"bjdm","bmdm","bz","cym","eje","gatqm","jgm","jzgh","ksh","lqh","mm","nj","pyfs","pyfx","rxfsm","rxny","sfzh","xbm","xfzbz","xh","xjztm","xkmldm","xm","xmpy","xsdl","xslbm","xxnx","xz","ywxtbh","yxxsbz","yydj","zhgxsj","zjlbm","zydm","zyfx"}; 
						//形成sql
						insertSql[i] = getInsertSqlForModel(xsInfo, tableName, colList);
					}
					//插入数据
					int[] res = dao.runBatch(insertSql);

					for (int i = 0; i < res.length; i++) {
						updata = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
						if (!updata)
						break;
					}
				}
				return updata;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return updata;
	}
	
public boolean synchronizeXsqtxxData() {
		
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		CheckPasswordSoapStub myStub;
		Boolean updata = false;
		
		try {
			DAO dao = DAO.getInstance();
			myStub = new CheckPasswordSoapStub(new URL(Base.getInitProperties().get("zfJwWebService")),remoteWebserviceLocator);
			String passWord = getJwWebPassWord();
			int stuCount =  myStub.getXsInfoAmount(passWord);
			//			创建备份表
			Calendar date = Calendar.getInstance();
			String bakeupFlag = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
			String backUpSql = "create table bks_xsqtxx"+bakeupFlag+" as select * from bks_xsqtxx";
			updata = dao.runUpdate(backUpSql, new String[]{});
			//	清除原表里的内容
			String delSql = "delete from bks_xsqtxx";
			if(updata){
				updata = dao.runUpdate(delSql, new String[]{});
			}
			String tableName = "bks_xsqtxx";
			//清除成功后插入数据
			if(updata){
				for(int j = 1;j<stuCount+1;j+=1000){
					XsInfoDTO xsinfodto = null;
					if(j+1000<stuCount+1){
						xsinfodto= myStub.getXsxx(Integer.toString(j),Integer.toString(j+1000-1),passWord);
					}else{
						xsinfodto= myStub.getXsxx(Integer.toString(j),Integer.toString(stuCount),passWord);
					}
					XsInfo[] xsinfolist =  xsinfodto.getXsInfo();
					String[] insertSql = new String[xsinfolist.length];
					for(int i = 0;i<xsinfolist.length;i++){
				
						//取数据
						XsInfo xsInfo = xsinfolist[i];
						//通过反射形成sql
						String[] colList = new String[]{"bbldbz","bsldbz","bybz","byzx","bz","csdm","csrq","cwrdjjfzrq","dpsbz","exwbz","gbm","gfsbz","hkszd","hkxzm","hyzkm","jhsbz","jkzkm","jrny","lxsbz","lydq","mzdm","pksbz","rdjjfz","tc","xh","xxm","ybybz","ybynf","ywxtbh","zhgxsj","zjxy","zzmmm"}; 
						//形成sql
						insertSql[i] = getInsertSqlForModel(xsInfo, tableName, colList);
					}
					//插入数据
					int[] res = dao.runBatch(insertSql);

					for (int i = 0; i < res.length; i++) {
						updata = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
						if (!updata)
						break;
					}
				}
			
				return updata;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return updata;
	}

	public boolean synchronizeXscjxxData() {
	
	Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
	CheckPasswordSoapStub myStub;
	Boolean updata = false;
	
	try {
		DAO dao = DAO.getInstance();
		myStub = new CheckPasswordSoapStub(new URL(Base.getInitProperties().get("zfJwWebService")),remoteWebserviceLocator);
		String passWord = getJwWebPassWord();
		int stuCount =  myStub.getCjInfoAmount(passWord);
		//			创建备份表
		Calendar date = Calendar.getInstance();
		String bakeupFlag = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
		String backUpSql = "create table cjb"+bakeupFlag+" as select * from cjb";
		updata = dao.runUpdate(backUpSql, new String[]{});
		//	清除原表里的内容
		String delSql = "delete from cjb";
		if(updata){
			updata = dao.runUpdate(delSql, new String[]{});
		}
		String tableName = "cjb";
		//清除成功后插入数据
		if(updata){
			for(int j = 1;j<stuCount+1;j+=1000){
				CjInofDTO cjInfodto = null;
				if(j+1000<stuCount+1){
					cjInfodto= myStub.getCjxx(Integer.toString(j),Integer.toString(j+1000-1),passWord);
				}else{
					cjInfodto= myStub.getCjxx(Integer.toString(j),Integer.toString(stuCount),passWord);
				}
				CjInfo[] cjinfolist =  cjInfodto.getCjInfo();
				String[] insertSql = new String[cjinfolist.length];
				for(int i = 0;i<cjinfolist.length;i++){
			
					//取数据
					CjInfo cjInfo = cjinfolist[i];
					//通过反射形成sql
					String[] colList = new String[]{"bkcj","bz","cj","cxbj","cxcj","jd","kcdm","kclx","kcmc","kcsbm","kcxz","khfs","nd","xf","xh","xkkh","xn","xq","xqbz","zhxs","zpcj1","zpcj2"}; 
					//形成sql
					insertSql[i] = getInsertSqlForModel(cjInfo, tableName, colList);
				}
				//插入数据
				int[] res = dao.runBatch(insertSql);

				for (int i = 0; i < res.length; i++) {
					updata = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!updata)
					break;
				}
			}
		
			return updata;
		}
		
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (RemoteException e) {
		// TODO 自动生成 catch 块
		e.printStackTrace();
	} catch (Exception e) {
		// TODO 自动生成 catch 块
		e.printStackTrace();
	}
	return updata;
	}

	private String getInsertSqlForModel(Object model, String tableName, String[] colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		StringBuilder sqlBuilder = new StringBuilder();
		Class myClass = model.getClass();
		if (colList != null && colList.length > 0) {
			sqlBuilder.append("insert into " + tableName + " (");
				if (colList != null && colList.length > 0) {
					for (int i = 0; i < colList.length-1; i++) {
							sqlBuilder.append(colList[i]);
							sqlBuilder.append(",");
					}
					sqlBuilder.append(colList[colList.length-1]);
					sqlBuilder.append(") values (");
					for (int i = 0; i < colList.length-1; i++) {
						String methodName = "get"+ colList[i].toUpperCase();
						String inputValues = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
						sqlBuilder.append("'");
						sqlBuilder.append(inputValues);
						sqlBuilder.append("',");
					}
					String methodName = "get"+ colList[colList.length-1].toUpperCase();
					String inputValues = (String) myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null);
					sqlBuilder.append("'");
					sqlBuilder.append(inputValues);
					sqlBuilder.append("')");
				}
				
			}
		return sqlBuilder.toString();
	}
	
	
}

