<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<%--<script type='text/javascript' src='js/calendar/calendar-zh.js'></script>--%>
		<%--<script type='text/javascript' src='js/calendar/calendar.js'></script>--%>
		<script type="text/javascript" src="xsgzgl/xsxx/general/xsxxcj/js/xsxxcj.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>

	</head>
	<body>
		<html:form action="/xsxx_gygl_xsxxcj" method="post" styleId="xsxxcjForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xh" styleId="xh"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">ѧ��</th>
							<td style="width:35%">
								<a class="name" href="#" onclick="showDialog('ѧ����ϸ��Ϣ',700,500,'stu_info_details.do?xh=${xsxxcjForm.xh }')" style="margin-left: 1px;">${xsxxcjForm.xh }</a>
							</td>
							<th style="width:15%">����</th>
							<td style="width:35%">
								<bean:write name="jbxx" property="xm"/>
							</td>
						</tr>
						
						<tr>
							<th>�꼶</th>
							<td>
								<bean:write name="jbxx" property="nj"/>
							</td>
							<th><bean:message key="lable.xb" /></th>
							<td>
								<bean:write name="jbxx" property="xymc"/>
							</td>
						</tr>
						<tr>
							<th>רҵ</th>
							<td>
								<bean:write name="jbxx" property="zymc"/>
							</td>
							<th>�༶</th>
							<td>
								<bean:write name="jbxx" property="bjmc"/>
							</td>
						</tr>
						<tr>
							<th>��ͥ��ϵ��ʽ</th>
							<td colspan="3">${xsxxcjForm.jtlxfs }</td>
						</tr>
						<tr>
							<th style="width:15%"> �������ڵ�</th>
							<td colspan="3">
								${xsxxcjForm.hkszdmc }
							</td>							
						</tr>
						<tr>
							<th style="width:15%">��ͥסַ</th>
							<td colspan="3">
								${xsxxcjForm.jtdzmc }
							</td>							
						</tr>
						<tr>
							<th style="width:15%">��Դ��</th>
							<td colspan="3">
								${xsxxcjForm.sydmc }
							</td>							
						</tr>
						<tr>
							<th style="width:15%">����</th>
							<td colspan="3">
								${xsxxcjForm.jgmc }
							</td>							
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ���ɼ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th >�����Ƿ�Ǩ��ѧУ</th>
							<td >
								${xsxxcjForm.hksfjrxx }
							</td>
							<th >�Ƿ�סУ</th>
							<td >
								${xsxxcjForm.sfzx }
							</td>
						</tr>
						<tr>
							<th >�Ƿ������뵳</th>
							<td >
								${xsxxcjForm.sfsqrd }
							</td>
							<th>�Ƿ񶥸�ʵϰ</th>
							<td >
								${xsxxcjForm.sfdgsx }
							</td>
						</tr>
						<tr id="sfsqrdcs" <logic:equal value="��" name="xsxxcjForm" property="sfsqrd"> style="display: none;" </logic:equal>bgcolor="#f1f1f1">
							<th>�ݽ�������ʱ��</th>
							<td>
								${xsxxcjForm.djsqssj }
							</td>
							<th>�뵳ʱ��</th>
							<td>
								${xsxxcjForm.rdsj }
							</td>
						</tr>
						<tr id="sfsqrdcs2" <logic:equal value="��" name="xsxxcjForm" property="sfsqrd"> style="display: none;" </logic:equal> bgcolor="#f1f1f1">
							<th>ת��ʱ��</th>
							<td colspan="3" >
								${xsxxcjForm.zzsj }
							</td>
						</tr>
						<tr>
							<th >�Ƿ���������</th>
							<td >
								${xsxxcjForm.sfssmz }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfssmzcs" <logic:equal value="��" name="xsxxcjForm" property="sfssmz">style="display: none;"</logic:equal> >
							<th>������������</th>
							<td colspan="3" bgcolor="#f1f1f1">
								${xsxxcjForm.mzmc }
							</td>
						</tr>
						<tr>
							<th >�Ƿ���ʱ��ѵ</th>
							<td >
								${xsxxcjForm.sflspx }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sflspxcs" <logic:equal value="��" name="xsxxcjForm" property="sflspx">style="display: none;"</logic:equal>>
							<th>��ѵ����</th>
							<td colspan="3" bgcolor="#f1f1f1">
								${xsxxcjForm.pxmc }
							</td>
						</tr>
						<tr>
							<th >�Ƿ��ڽ�����</th>
							<td >
								${xsxxcjForm.sfzjxy }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfzjxycs" <logic:equal value="��" name="xsxxcjForm" property="sfzjxy">style="display: none;"</logic:equal>>
							<th>�����ڽ�����</th>
							<td  bgcolor="#f1f1f1">
								${xsxxcjForm.xyzjmc }
							</td>
							<th>�μ��ڽ�ʱ��</th>
							<td  bgcolor="#f1f1f1">
								${xsxxcjForm.cjzjsj }
							</td>
						</tr>
						<tr>
							<th >�Ƿ񾭼�����</th>
							<td >
								${xsxxcjForm.sfjjkn }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfjjkncs" <logic:equal value="��" name="xsxxcjForm" property="sfjjkn">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>��������ԭ��</th>
							<td colspan="3">
								${xsxxcjForm.jjknyy }
							</td>
						</tr>
						<tr>
							<th >�����Ƿ񼲲�</th>
							<td >
								${xsxxcjForm.stsfcj }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="stsfcjcs" <logic:equal value="��" name="xsxxcjForm" property="stsfcj">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>���弲��ԭ��</th>
							<td colspan="3">
								${xsxxcjForm.stcjyy }
							</td>
						</tr>
						
						<tr>
							<th >�Ƿ�ѧϰ����</th>
							<td >
								${xsxxcjForm.sfxxkn }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfxxkncs" <logic:equal value="��" name="xsxxcjForm" property="sfxxkn">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>ѧϰ����ԭ��</th>
							<td colspan="3">
								${xsxxcjForm.xxknyy }
							</td>
						</tr>
						
						<tr>
							<th >�Ƿ���������</th>
							<td >
								${xsxxcjForm.sfxlkr }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfxlkrcs" <logic:equal value="��" name="xsxxcjForm" property="sfxlkr">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>��������ԭ��</th>
							<td colspan="3">
								${xsxxcjForm.xlkryy }
							</td>
						</tr>
						
						<tr>
							<th >�Ƿ��ͥ����</th>
							<td >
								${xsxxcjForm.sfjtkr }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfjtkrcs" <logic:equal value="��" name="xsxxcjForm" property="sfjtkr">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>��ͥ����ԭ��</th>
							<td colspan="3">
								${xsxxcjForm.jtkryy }
							</td>
						</tr>
						
						<tr>
							<th >�Ƿ�����������</th>
							<td >
								${xsxxcjForm.sfyqtkr }
							</td>
							<td colspan="2"></td>
						</tr>
						<tr id="sfyqtkrcs" <logic:equal value="��" name="xsxxcjForm" property="sfyqtkr">style="display: none;"</logic:equal> bgcolor="#f1f1f1">
							<th>��������ԭ��</th>
							<td colspan="3">
								${xsxxcjForm.qtkryy }
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParent2();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>	
		</html:form>
	</body>
</html>