
package xgxt.DAO.Bjlh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class SynchronizeBaseDataTask extends TimerTask{
	private DAO dao = null;
	private ArrayList<String[]> rs = null;
	public SynchronizeBaseDataTask(){
		dao = DAO.getInstance();
		rs = dao.rsToVator3("select localtablename,remotetablename,kssj,jgsj,NVL(cs,'0') cs from glsjb", new String[]{}, new String[]{"localtablename","remotetablename","kssj","jgsj","cs"});
	}
	public void run() {
		try {
			synchronize();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	public void synchronize() throws Exception{
		//要判断glsjb里的每个表，根据它们各自的开始时间和间隔进行同步
		Calendar date = Calendar.getInstance();
		String now = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
		ArrayList<String[]> doSynchronize = new ArrayList<String[]>();
		for(String[] temp : rs){
			String[] jgsj = temp[3].split("!@");
			long jgsjMilliSeconds = (Long.parseLong(jgsj[0])*24*60+Long.parseLong(jgsj[1])*60+Long.parseLong(jgsj[2]))*60*1000;
			Calendar kssj = Calendar.getInstance();
			if(temp != null && temp[2] != null && temp[3] != null){//在开始时间和间隔时间都存在的条件下进行同步操作
				kssj.set(getYear(temp[2]), getMonth(temp[2])-1, getDay(temp[2]), getHour(temp[2]), getMinute(temp[2]), getSecond(temp[2]));
				//如果现在已经在设置开始时间之后，那要判断是否达到时间间隔，如果时间差是设定的时间间隔的倍数，就执行备份操作
				if(date.after(kssj)){
					long nowMillis = date.getTimeInMillis();
					long kssjMillis = kssj.getTimeInMillis();
					if(((nowMillis-kssjMillis)%jgsjMilliSeconds == 0) && ((nowMillis-kssjMillis)/jgsjMilliSeconds >= Integer.parseInt(temp[4]))){//能够整除,并且间隔倍数比已经更新的次数多的情况下
						doSynchronize.add(temp);
					}
				} else if(Long.parseLong(now) == Long.parseLong(temp[2])){//时间刚好相等
					//BjlhWebServiceDao synDao = new BjlhWebServiceDao(Base.getBjlhBaseDataEndPointUri(),"getData",new String[]{"username","password","dataServerId","params","start","count"});
					doSynchronize.add(temp);
				}
			}
		}
		//对要进行更新的表记录进行数据更新操作 start
		BjlhWebServiceDao webServiceDao = new BjlhWebServiceDao(Base.getBjlhBaseDataEndPointUri(),"getDate",new String[]{"tableName"},"xgsj","00000000");
		for(String[] temp : doSynchronize){//每个temp都是localtablename,remotetablename,kssj,jgsj,cs的集
			webServiceDao.synchronizeTableData(temp[0].toLowerCase(), temp[1].toLowerCase());
		}
	}

	private int getYear(String timeStr){
		return Integer.parseInt(timeStr.substring(0,4));
	}
	private int getMonth(String timeStr){
		return Integer.parseInt(timeStr.substring(4,6));
	}
	private int getDay(String timeStr){
		return Integer.parseInt(timeStr.substring(6,8));
	}
	private int getHour(String timeStr){
		return Integer.parseInt(timeStr.substring(8,10));
	}
	private int getMinute(String timeStr){
		return Integer.parseInt(timeStr.substring(10,12));
	}
	private int getSecond(String timeStr){
		return Integer.parseInt(timeStr.substring(12));
	}
}
