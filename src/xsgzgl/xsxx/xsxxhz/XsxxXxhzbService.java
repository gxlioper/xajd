package xsgzgl.xsxx.xsxxhz;

import xsgzgl.comm.BasicService;
import jxl.write.WritableWorkbook;

public abstract class XsxxXxhzbService  extends BasicService{
	
	
	XsxxXxhzbDAO dao=new XsxxXxhzbDAO();
	/**
	 * У�����ҵ����ӡ
	 * @param wwb
	 * @param map
	 */
	public abstract void printXsxx(WritableWorkbook wwb,String xh);
}
