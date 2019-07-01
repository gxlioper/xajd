
package xgxt.pjpy.hzy;

import java.util.Observable;
import java.util.Observer;

import xgxt.pjpy.shList.ShListModel;

public class HzyPjpyShList implements Observer{
	
	public HzyPjpyShList(){
		
	}
	
	public HzyPjpyShList(Observable obs){
		obs.addObserver(this);
	}
	
	public void update(Observable o, Object shList) {//���subject�ؼ��ֶη����˱仯���ʹ����޸�subject����Ӧ����
		ShListModel shListSubject = (ShListModel)o;
		String xxdm = shListSubject.getXxdm();
		HzyPjpyDAO dao = new HzyPjpyDAO();
		if("12872".equals(xxdm)){//����Ǻ�ְԺ���������Ӧ�Ĳ���
			shListSubject.setShList(dao.hzyShList());
		}
	}

}
