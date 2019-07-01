/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-5-31 ����11:04:37 
 */  
package xsgzgl.gygl.wsjc.jcrcgl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��Ԣ�������ճ�service(�㽭��ҵ��ʦ���Ի�) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-5-31 ����11:04:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyglJcrcglForZjSyJsService extends SuperServiceImpl<GyglJcrcglForm, GyglJcrcglForZjSyJsDao>{
	private GyglJcrcglForZjSyJsDao dao = new GyglJcrcglForZjSyJsDao();
	
	/** 
	 * @����:�������ճ�(�㽭��ҵ��ʦѧԺ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-31 ����01:41:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws ParseException
	 * @throws SQLException
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertJcrc(GyglJcrcglForm form) throws Exception{
		List<String> dates = getDates(form);
		return dao.plInsertJc(form, dates.toArray(new String[]{}));		
	}
	
	/** 
	 * @����:��ȡ��ֹʱ������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-31 ����01:42:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws ParseException
	 * List<String> �������� 
	 * @throws 
	 */
	private List<String> getDates(GyglJcrcglForm form) throws ParseException{
		String kssj = form.getKssj();
		String jssj = form.getJssj();
		List<String> dates = new ArrayList<String>();
		dates.add(form.getKssj());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date begin = sdf.parse(kssj);
		Date end = sdf.parse(jssj);		
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(begin);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		while(endCalendar.after(beginCalendar)){
			beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
			dates.add(sdf.format(beginCalendar.getTime()));
		}
		return dates;
	}
}
