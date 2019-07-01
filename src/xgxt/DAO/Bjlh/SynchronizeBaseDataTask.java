
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
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

	public void synchronize() throws Exception{
		//Ҫ�ж�glsjb���ÿ�����������Ǹ��ԵĿ�ʼʱ��ͼ������ͬ��
		Calendar date = Calendar.getInstance();
		String now = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
		ArrayList<String[]> doSynchronize = new ArrayList<String[]>();
		for(String[] temp : rs){
			String[] jgsj = temp[3].split("!@");
			long jgsjMilliSeconds = (Long.parseLong(jgsj[0])*24*60+Long.parseLong(jgsj[1])*60+Long.parseLong(jgsj[2]))*60*1000;
			Calendar kssj = Calendar.getInstance();
			if(temp != null && temp[2] != null && temp[3] != null){//�ڿ�ʼʱ��ͼ��ʱ�䶼���ڵ������½���ͬ������
				kssj.set(getYear(temp[2]), getMonth(temp[2])-1, getDay(temp[2]), getHour(temp[2]), getMinute(temp[2]), getSecond(temp[2]));
				//��������Ѿ������ÿ�ʼʱ��֮����Ҫ�ж��Ƿ�ﵽʱ���������ʱ������趨��ʱ�����ı�������ִ�б��ݲ���
				if(date.after(kssj)){
					long nowMillis = date.getTimeInMillis();
					long kssjMillis = kssj.getTimeInMillis();
					if(((nowMillis-kssjMillis)%jgsjMilliSeconds == 0) && ((nowMillis-kssjMillis)/jgsjMilliSeconds >= Integer.parseInt(temp[4]))){//�ܹ�����,���Ҽ���������Ѿ����µĴ�����������
						doSynchronize.add(temp);
					}
				} else if(Long.parseLong(now) == Long.parseLong(temp[2])){//ʱ��պ����
					//BjlhWebServiceDao synDao = new BjlhWebServiceDao(Base.getBjlhBaseDataEndPointUri(),"getData",new String[]{"username","password","dataServerId","params","start","count"});
					doSynchronize.add(temp);
				}
			}
		}
		//��Ҫ���и��µı��¼�������ݸ��²��� start
		BjlhWebServiceDao webServiceDao = new BjlhWebServiceDao(Base.getBjlhBaseDataEndPointUri(),"getDate",new String[]{"tableName"},"xgsj","00000000");
		for(String[] temp : doSynchronize){//ÿ��temp����localtablename,remotetablename,kssj,jgsj,cs�ļ�
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
