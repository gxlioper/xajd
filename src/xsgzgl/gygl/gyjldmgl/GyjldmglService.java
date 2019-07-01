package xsgzgl.gygl.gyjldmgl;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xsgzgl.jcsj.comm.JcsjService;

public class GyjldmglService extends JcsjService{
	
	private GyjldmglDao dao=new GyjldmglDao();
	
	/**
	 * ��ñ�ͷ
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] str = null;
		if(type.equals("jldl")){
			//���칤�̴�ѧ���Ի�
			if("11799".equals(Base.xxdm)){
				str = new String[]{"���ʹ������","���ʹ�������"}; 
			}else{				
				str = new String[]{"���ɴ������","���ɴ�������"};
			}
		}else if(type.equals("jllb")){
			//���칤�̴�ѧ���Ի�
			if("11799".equals(Base.xxdm)){
				str = new String[]{"����������","�����������","�������۷�","���ʹ�������"};
			}else{				
				str = new String[]{"����������","�����������","�������۷�","���ɴ�������"};
			}
		}else if (type.equals("cflb")){
			str = new String[]{"����������","�����������"};
		}
		return str;
	}
	
	/**
	 * ��ü��ɴ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJldlList(GyjldmglForm model) throws Exception{
		return dao.getJldlList(model);
	}
	
	/**
	 * ������ɴ�����Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String saveJldlInfo(GyjldmglForm model,String type) throws Exception{
		return dao.saveJldlInfo(model, type);
	}
	
	/**
	 * ɾ�����ɴ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteJldlInfo(GyjldmglForm model) throws Exception{
		String b=dao.deleteJldlInfo(model);
		return "exists".equalsIgnoreCase(b )? "�������Ѿ�ʹ�ã�����ɾ����" : ("yes".equalsIgnoreCase(b) ? "ɾ���ɹ���":"ɾ��ʧ�ܣ�");
	}
	/**
	 * ��ü��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJllbList(GyjldmglForm model) throws Exception{
		return dao.getJllbList(model);
	}
	/**
	 * ������������Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String saveJllbInfo(GyjldmglForm model,String type) throws Exception{
		return dao.saveJllbInfo(model, type);
	}
	
	/**
	 * ɾ�����������Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteJllbInfo(GyjldmglForm model) throws Exception{
		String b=dao.deleteJllbInfo(model);
		return "exists".equalsIgnoreCase(b )? "�������Ѿ�ʹ�ã�����ɾ����" : ("yes".equalsIgnoreCase(b) ? "ɾ���ɹ���":"ɾ��ʧ�ܣ�");
	}
	/**
	 * ��ü������List<HashMap<String,String>>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getJllbListMap(GyjldmglForm model) throws Exception{
		return dao.getJllbListMap(model);
	}
	
	/**
	 * ���洦���������
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String saveCflbInfo(GyjldmglForm model, String type)	throws Exception { 
		return dao.saveCflbInfo(model, type);
	}
	
	/**
	 * ��ô��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCflbList(GyjldmglForm model) throws Exception{
		return dao.getCflbList(model);
	}
}
