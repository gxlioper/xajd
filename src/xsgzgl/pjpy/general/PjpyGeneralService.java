package xsgzgl.pjpy.general;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.inter.PjpyDjbgInterface;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;
import xsgzgl.pjpy.general.inter.PjpyTjcxInterface;
import xsgzgl.pjpy.general.inter.PjpyWdpjInterface;
import xsgzgl.pjpy.general.inter.PjpyXmszInterface;
import xsgzgl.pjpy.general.inter.PjpyYwjkInterface;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszBjdlInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszCpxzInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszZcxmInterface;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjjehzInterface;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjmdhzInterface;
import xsgzgl.pjpy.general.inter.tjcx.TjcxShmddcInterface;
import xsgzgl.pjpy.general.inter.tjcx.TjcxTjmdhzInterface;
import xsgzgl.pjpy.general.inter.tjcx.TjcxZcbjmdInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjLssbInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszPjtjInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszRsszInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszSjszInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmjdInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmsyInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyGeneralService extends BasicService {

	/**
	 * �ܷ����
	 * 
	 * @author ΰ�����
	 */
	public String getOperation(String lcdm) {

		PjpyGeneralDAO dao = new PjpyGeneralDAO();

		// �������Ƿ��ύ
		String operation = dao.getLcdmSftj(lcdm);

		if ("yes".equalsIgnoreCase(operation)) {
			return "later";
		}

		// �������Ƿ�ɲ���
		operation = dao.getLcdmNfcz(lcdm);
		if ("no".equalsIgnoreCase(operation)) {
			return "early";
		}

		return operation;
	}

	// ===============����Service=============================

	// ===================��������ҳ�� begin=====================

	/**
	 * ����������ҳService
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjpyIndexInterface getPjpyIndexService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc + ".index.PjpyIndexService";

		PjpyIndexInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc + ".index.PjpyIndexService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjpyIndexInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	// ===================��������ҳ�� end=====================

	// ===================���������á� begin=====================

	/**
	 * ������������_��Ա����Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjszPjryInterface getPjszPjryService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".pjsz.pjry.PjszPjryService";

		PjszPjryInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".pjsz.pjry.PjszPjryService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjszPjryInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ������������_����С��Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjszCpxzInterface getPjszCpxzService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".pjsz.cpxz.PjszCpxzService";

		PjszCpxzInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".pjsz.cpxz.PjszCpxzService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjszCpxzInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ������������_�༶����Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjszBjdlInterface getPjszBjdlService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".pjsz.bjdl.PjszBjdlService";

		PjszBjdlInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".pjsz.bjdl.PjszBjdlService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjszBjdlInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * ������������_�۲���ĿService
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjszZcxmInterface getPjszZcxmService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".pjsz.zcxm.PjszZcxmService";

		PjszZcxmInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".pjsz.zcxm.PjszZcxmService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjszZcxmInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	/**
	 * ������������_������ĿService
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjszPjxmInterface getPjszPjxmService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".pjsz.pjxm.PjszPjxmService";

		PjszPjxmInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".pjsz.pjxm.PjszPjxmService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjszPjxmInterface) interFaceClass.getConstructor(null)
					.newInstance(null);

			return service;
		}
	}

	// ===================���������á� end=====================

	// ===================���ۺϲ����� begin=====================

	/**
	 * �����ۺϲ���_�ۺϲ���Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjpyZhcpInterface getPjpyZhcpService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc + ".zhcp.PjpyZhcpService";

		PjpyZhcpInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc + ".zhcp.PjpyZhcpService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjpyZhcpInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	// ===================���ۺϲ����� end=====================

	// ===================����Ŀ���á� begin=====================

	/**
	 * ������������_��Ŀ����Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjpyXmszInterface getPjpyXmszService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc + ".xmsz.PjpyXmszService";

		PjpyXmszInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc + ".xmsz.PjpyXmszService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjpyXmszInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ������������_��������Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XmszPjtjInterface getXmszPjtjService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".xmsz.pjtj.XmszPjtjService";

		XmszPjtjInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc
						+ ".xmsz.pjtj.XmszPjtjService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XmszPjtjInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ������������_��Ŀ���Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XmszXmjdInterface getXmszXmjdService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".xmsz.xmjd.XmszXmjdService";

		XmszXmjdInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc
						+ ".xmsz.xmjd.XmszXmjdService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XmszXmjdInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ������������_��������Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XmszRsszInterface getXmszRsszService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".xmsz.rssz.XmszRsszService";

		XmszRsszInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc
						+ ".xmsz.rssz.XmszRsszService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XmszRsszInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ������������_��Ŀ˳��Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public XmszXmsyInterface getXmszXmsyService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".xmsz.xmsy.XmszXmsyService";

		Class interFaceClass = Class.forName(className);

		XmszXmsyInterface service = (XmszXmsyInterface) interFaceClass
				.getConstructor(null).newInstance(null);

		return service;
	}

	/**
	 * ������������_ʱ������Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public XmszSjszInterface getXmszSjszService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".xmsz.sjsz.XmszSjszService";

		XmszSjszInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc
						+ ".xmsz.sjsz.XmszSjszService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (XmszSjszInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	// ===================����Ŀ���á� end=====================

	// ===================���ҵ������� begin=====================

	/**
	 * �����ҵ�����_��������Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public WdpjPjtjInterface getWdpjPjtjService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".wdpj.pjtj.WdpjPjtjService";

		WdpjPjtjInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc
						+ ".wdpj.pjtj.WdpjPjtjService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (WdpjPjtjInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * �����ҵ�����_�ҵ�����Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjpyWdpjInterface getPjpyWdpjService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc + ".wdpj.PjpyWdpjService";

		PjpyWdpjInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc + ".wdpj.PjpyWdpjService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjpyWdpjInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * �����ҵ�����_ѧ������Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public WdpjXssqInterface getWdpjXssqService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".wdpj.xssq.WdpjXssqService";

		WdpjXssqInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc
						+ ".wdpj.xssq.WdpjXssqService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (WdpjXssqInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * �����ҵ�����_��ʦ�ϱ�Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "finally", "unchecked" })
	public WdpjLssbInterface getWdpjLssbService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".wdpj.lssb.WdpjLssbService";

		WdpjLssbInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc
						+ ".wdpj.lssb.WdpjLssbService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (WdpjLssbInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * �����ҵ�����_��Ŀ���Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "finally", "unchecked" })
	public WdpjXmshInterface getWdpjXmshService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".wdpj.xmsh.WdpjXmshService";

		WdpjXmshInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc
						+ ".wdpj.xmsh.WdpjXmshService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (WdpjXmshInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * �����ҵ�����_�����ѯService
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public WdpjJgcxInterface getWdpjJgcxService(PjpyGeneralForm model)
			throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".wdpj.jgcx.WdpjJgcxService";

		WdpjJgcxInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				model.setXxpymc(xxpymc);
				className = "xsgzgl.pjpy." + xxpymc
						+ ".wdpj.jgcx.WdpjJgcxService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (WdpjJgcxInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	// ===================���ҵ������� end=====================

	// ===================���ǼǱ�� begin===================
	/**
	 * ����ǼǱ��Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjpyDjbgInterface getPjpyDjbgService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc + ".djbg.PjpyDjbgService";

		PjpyDjbgInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc + ".djbg.PjpyDjbgService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjpyDjbgInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	// ===================���ǼǱ�� end===================

	// ===================���I�սӿڡ� begin=====================
	/**
	 * ����ǼǱ��Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjpyYwjkInterface getPjpyYwjkService()
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);

		String className = "xsgzgl.pjpy." + xxpymc + ".ywjk.PjpyYwjkService";

		PjpyYwjkInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc + ".ywjk.PjpyYwjkService";
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjpyYwjkInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	// ===================���I�սӿڡ� end=====================

	// ===================��ͳ�Ʋ�ѯ�� begin===================
	/**
	 * ����ͳ�Ʋ�ѯService
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public PjpyTjcxInterface getPjpyTjcxService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc + ".tjcx.PjpyTjcxService";

		PjpyTjcxInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc + ".tjcx.PjpyTjcxService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (PjpyTjcxInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ����ͳ�Ʋ�ѯ_�۲�༶����Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxZcbjmdInterface getTjcxZcbjmdService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".tjcx.zcbjmd.TjcxZcbjmdService";

		TjcxZcbjmdInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".tjcx.zcbjmd.TjcxZcbjmdService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (TjcxZcbjmdInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ����ͳ�Ʋ�ѯ_����������Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxHjmdhzInterface getTjcxHjmdhzService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".tjcx.hjmdhz.TjcxHjmdhzService";

		TjcxHjmdhzInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".tjcx.hjmdhz.TjcxHjmdhzService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (TjcxHjmdhzInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ����ͳ�Ʋ�ѯ_�񽱽�����Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxHjjehzInterface getTjcxHjjehzService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".tjcx.hjjehz.TjcxHjjehzService";

		TjcxHjjehzInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".tjcx.hjjehz.TjcxHjjehzService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (TjcxHjjehzInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ����ͳ�Ʋ�ѯ_�Ƽ���������_Service
	 * 
	 * @author qlj
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxTjmdhzInterface getTjcxTjmdhzService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".tjcx.tjmdhz.TjcxTjmdhzService";

		TjcxTjmdhzInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".tjcx.tjmdhz.TjcxTjmdhzService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (TjcxTjmdhzInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ����ͳ�Ʋ�ѯ_�Ƽ���������_Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxTjmdhzInterface getTjcxKnsTjmdhzService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".tjcx.knstjmdhz.TjcxTjmdhzService";

		TjcxTjmdhzInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".tjcx.knstjmdhz.TjcxTjmdhzService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (TjcxTjmdhzInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	/**
	 * ����ͳ�Ʋ�ѯ_����������Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxShmddcInterface getTjcxShmddcService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".tjcx.shmddc.TjcxShmddcService";

		TjcxShmddcInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".tjcx.shmddc.TjcxShmddcService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (TjcxShmddcInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}

	// ===================��ͳ�Ʋ�ѯ�� end=====================

	// ===============����Service end=============================

	/**
	 * �����ۺϲ���Service
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public PjpyZhcpInterface getZhcpService(PjpyGeneralForm model)
			throws Exception {

		// ѧУƴ������
		String xxpymc = model.getXxpymc();

		String className = "xsgzgl.pjpy." + xxpymc + ".zhcp.PjpyZhcpService";

		Class interFaceClass = Class.forName(className);

		PjpyZhcpInterface service = (PjpyZhcpInterface) interFaceClass
				.getConstructor(null).newInstance(null);

		return service;

	}

	/**
	 * ��ó�ʼ����Ŀ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCshXmList(PjpyGeneralForm model,
			User user) throws Exception {

		PjpyZhcpInterface service = getZhcpService(model);

		List<HashMap<String, String>> xmList = null;

		return xmList;
	}

	/**
	 * ����ۺϲ���ά����ͷ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhcpMaintainTop(
			PjpyGeneralForm model, User user) throws Exception {

		PjpyZhcpInterface service = getZhcpService(model);
		List<HashMap<String, String>> topTr = service.getZhcpMaintainTop(model,
				user);

		return topTr;
	}

	/**
	 * ��ý����(�ۺϲ���ά��)
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public ArrayList<String[]> getZhcpMaintainInfo(PjpyGeneralForm model,
			User user) throws Exception {

		PjpyZhcpInterface service = getZhcpService(model);
		ArrayList<String[]> list = service.getZhcpMaintainInfo(model, user);

		return list;
	}

	/**
	 * ���������(�ۺϲ���ά��)
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String createZhcpMaintainRs(SearchRsModel rsModel,
			PjpyGeneralForm model, ArrayList<String[]> rsArrList, User user)
			throws Exception {

		PjpyZhcpInterface service = getZhcpService(model);
		String html = service.createZhcpMaintainRs(rsModel, model, rsArrList,
				user);

		return html;
	}
	
	/**
	 * ��ý����������Service
	 * 
	 * @author ��ǿ
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "finally" })
	public TjcxHjjehzInterface getCmhjmdhzService(PjpyGeneralForm model)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);

		String className = "xsgzgl.pjpy." + xxpymc
				+ ".tjcx.hjjehz.TjcxHjjehzService";

		TjcxHjjehzInterface service = null;
		Class interFaceClass = null;

		boolean flag = false;

		try {
			interFaceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			flag = true;
		} finally {
			if (flag) {
				xxpymc = "general";
				className = "xsgzgl.pjpy." + xxpymc
						+ ".tjcx.hjjehz.TjcxHjjehzService";
				model.setXxpymc(xxpymc);
				try {
					interFaceClass = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
			service = (TjcxHjjehzInterface) interFaceClass.getConstructor(null)
					.newInstance(null);
			return service;
		}
	}
	
}
