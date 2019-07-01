package xgxt.dtjs.zjcm.kkcs;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.pjpy.zjcm.cprs.ZjcmCprsForm;
import xgxt.pjpy.zjcm.cprs.ZjcmCprsModel;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;

public class ZjcmKkcsService {
	/**
	 * �㽭��ý ���δ���ά��
	 * method xxxfWh
	 * @param request
	 * @param ghxyStxfForm
	 * @throws Exception
	 */
	public void kkcsWh(HttpServletRequest request,
			ZjcmKkcsForm zjcmKkcsForm) throws Exception{
		
		GhxyNjzrwhService service =new GhxyNjzrwhService();
		
		String xn=Base.getJxjsqxn();
		String xq=Base.getJxjsqxq();
	    //�������ݲ��� �ı���
		String realTable ="zjcm_dtjs_kkcs";
		
		//�����ѯ����������Ϣ��XH����
		String[] zgh = zjcmKkcsForm.getPrimarykey_cbv();
		//������ѡ�����Ϣ������
		String[] checkVal = zjcmKkcsForm.getCheckVal();
		
		String[] pkValue=new String[checkVal.length];
		for(int i=0;i<checkVal.length;i++){
			pkValue[i]=checkVal[i]+xn+xq;
		}
		
		
		String[] kkcs= zjcmKkcsForm.getKkcs();
		//�жϲ����Ƿ�ɹ�
		boolean reslut = false;
		
		//�������Ӽ�ȡ��
		if (checkVal != null && checkVal.length > 0) {
			String pk = "xh || xn || xq";
			String[] arrzd = new String[] {"xh","xn","xq","kkcs"};
			String[] sxh = new String[checkVal.length];
			int m = 0;
			for (int i = 0; i < checkVal.length; i++) {
				if (zgh != null && zgh.length > 0) {
					for (int j = 0; j < zgh.length; j++) {
						if (zgh[j].equalsIgnoreCase(checkVal[i])) {
							sxh[i] = checkVal[i];
							m++;
							break;
						}else{
							sxh[i]="";
						}
					}
				}
			}
			
			String[] xhArr = new String[m];
			String[] xns=new String[m];
			String[] xqs=new String[m];
			String[] kkcss=new String[m];
			int f = 0;
			for(int i=0; i<sxh.length;i++){
				if(!Base.isNull(sxh[i])){
				xhArr[f] = sxh[i];
				kkcss[f]=kkcs[i];
				xns[f]=xn;
				xqs[f]=xq;
				f++;
				}
			}
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);
			
			ZjcmKkcsModel model=new ZjcmKkcsModel();
			model.setXh(xhArr);
			model.setXn(xns);
			model.setXq(xqs);
			model.setKkcs(kkcss);
			reslut = service.saveTyxx(saveForm, model);
			request.setAttribute("result", reslut);
		}
	}
}
