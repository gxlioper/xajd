package com.zfsoft.xgxt.xsxx.fbgl.comm;

/**
 * @�๦������: ������key�ظ��쳣
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-18 ����03:45:22
 * @�汾�� V1.0
 */
public class ProgressUniqueKeyException extends RuntimeException {
	private static final long serialVersionUID = 2031587107229609990L;

	public ProgressUniqueKeyException() {
		super("������key��ռ��,�����.");
	}

	public ProgressUniqueKeyException(String message) {
		super(message);
	}
	@Override
	public String getMessage() {
		return "������key����Ψһ!"+super.getMessage();
	}
}
