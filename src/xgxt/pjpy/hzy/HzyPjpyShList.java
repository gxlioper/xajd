
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
	
	public void update(Observable o, Object shList) {//如果subject关键字段发生了变化，就触发修改subject的相应属性
		ShListModel shListSubject = (ShListModel)o;
		String xxdm = shListSubject.getXxdm();
		HzyPjpyDAO dao = new HzyPjpyDAO();
		if("12872".equals(xxdm)){//如果是杭职院，则进行相应的操作
			shListSubject.setShList(dao.hzyShList());
		}
	}

}
