<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<!--������ʾҳ-->
		<div class="page_error">
			<div class="con_error">
				<h3>
					ҳ������ˣ����ż�������������ԭ����ɵģ�
				</h3>
				<span>�����������㣬�������Ǹ�⡣������������ϵ����Ա!</span>
				
				<logic:notPresent name="errMsg">
					<logic:equal value="400" name="No" scope="request">
						<p>
							1.���������
						</p>
					</logic:equal>
					<logic:equal value="404" name="No" scope="request">
						<p>
							1.ҳ��δ�ҵ���ɾ��
						</p>
						<p>
							2.�޷���������Ķ˿��Ϸ���Webվ��
						</p>
						<p>
							3.Web������չ����������ֹ������
						</p>
						<p>
							4.MIMEӳ�������ֹ������
						</p>
					</logic:equal>
					<logic:equal value="500" name="No" scope="request">
						<p>
							1.�ڲ�����������
						</p>
						<p>
							2.Web������̫æ
						</p>
						<p>
							3.Ӧ�ó�����æ����Web����������������
						</p>
						<p>
							4.UNC��Ȩƾ�ݲ���ȷ
						</p>
						<p>
							5.URL��Ȩ�洢���ܴ�
						</p>
						<p>
							6.�ڲ�JSP����
						</p>
					</logic:equal>	
				</logic:notPresent>
				<logic:present name="errMsg">
					<logic:notEmpty name="errMsg">
						<p>${errMsg }</p>
					</logic:notEmpty>
				</logic:present>
			</div>
		</div>
	</body>
</html>
