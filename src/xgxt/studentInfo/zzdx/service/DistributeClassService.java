package xgxt.studentInfo.zzdx.service;

import xgxt.action.Base;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ְ�Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-08</p>
 */
public class DistributeClassService {
	/**
	 * ���¼���İ༶��Ϣ���뵽Base�е�map�У�ʹ�����������������ɲ�ѯ���µİ༶
	 * */
	public void addBjInfoToBase(){
		Base.initialBj init = new Base.initialBj();
		new Thread(init).start();
	}
}
