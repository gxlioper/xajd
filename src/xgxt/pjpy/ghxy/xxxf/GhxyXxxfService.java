package xgxt.pjpy.ghxy.xxxf;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.pjpy.ghxy.stxf.GhxyStxfForm;
import xgxt.pjpy.ghxy.stxf.GhxyStxfModel;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;

public class GhxyXxxfService {
	/**
	 * ѡ��ѧ��ά��
	 * method xxxfWh
	 * @param request
	 * @param ghxyStxfForm
	 * @throws Exception
	 */
	public void xxxfWh(HttpServletRequest request,
			GhxyXxxfForm ghxyXxxfForm) throws Exception{
		
		GhxyNjzrwhService service =new GhxyNjzrwhService();
		
		String xn=Base.currXn;
		String xq=Base.currXq;
		//�������ݲ��� �ı���
		String realTable ="ghxy_xxxfb";
		
		//�����ѯ����������Ϣ��XH����
		String[] zgh = ghxyXxxfForm.getPrimarykey_cbv();
		//������ѡ�����Ϣ������
		String[] checkVal = ghxyXxxfForm.getCheckVal();
		
		String[] pkValue=new String[checkVal.length];
		for(int i=0;i<checkVal.length;i++){
			pkValue[i]=checkVal[i]+xn+xq;
		}
		
		
		String[] zf= ghxyXxxfForm.getZf();
		//�жϲ����Ƿ�ɹ�
		boolean reslut = false;
		
		//�������Ӽ�ȡ��
		if (checkVal != null && checkVal.length > 0) {
			String pk = "xh || xn || xq";
			String[] arrzd = new String[] {"xh","xn","xq","zf"};
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
			String[] zfs=new String[m];
			int f = 0;
			for(int i=0; i<sxh.length;i++){
				if(!Base.isNull(sxh[i])){
				xhArr[f] = sxh[i];
				zfs[f]=zf[i];
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
			
			GhxyXxxfModel model=new GhxyXxxfModel();
			model.setXh(xhArr);
			model.setXn(xns);
			model.setXq(xqs);
			model.setZf(zfs);
			reslut = service.saveTyxx(saveForm, model);
			request.setAttribute("result", reslut);
		}
	}
}
