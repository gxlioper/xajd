<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/mrgzkh/khsq/khsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/mrgzkhKhsq" method="post" styleId="GzkhKhsqForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��д</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>���˵�λ</th>
							<td>
							
								<html:select property="yrdw" styleId="yrdw" onclick="checkXh();" onchange="getGwxx();" style="width:200px">
										<html:options collection="yrdwList" property="bmdm" labelProperty="bmmc" />
									</html:select>
							</td>
							<th><font color="red">*</font>��ʱ</th>
							<td>
								<html:text property="gs" styleId="gs" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"
                                ></html:text>��Сʱ��
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��λ</th>
							<td>
								<html:select  property="gwdm" styleId="gwdm" onclick="checkXh();" style="width:200px">
									<html:options collection="gwList" property="gwdm" labelProperty="gwmc" />
								</html:select>
							</td>
							<th>��������</th>
							<td colspan="3">
								<input name="gzrq" id="gzrq" value="${rq}" readonly onfocus="showCalendar('gzrq','yyyy-MM-dd');"/>
							</td>
							<%--<th><font color="red">*</font>�����ص�</th>
							<td>
								<html:text property="gzdd" styleId="gzdd" maxlength="100" ></html:text>
								
							</td>--%>
						</tr>
						<tr>
							<th><font color="red">*</font>����ʱ���</th>
							<td colspan="3">
								<html:text  property="gzkssj" styleId="gzkssj" onfocus="showCalendar('gzkssj','HH:mm');" />
								-
								<html:text  property="gzjssj" styleId="gzjssj" onfocus="showCalendar('gzjssj','HH:mm');" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 40px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								<span style="font-size: larger; color: orchid">������ʾ��</span>
								<br>
								<span>1.ѧ��ÿ����ɹ�������뵱����ϵͳ��ά��������ʱ������δ��ʱά��������ϵ���Ź���Ա��ʦ��ά����</span>
								<br>
								<span>2.ÿ�칤��ʱ��<=8Сʱ��ÿ�¹���ʱ��<=40Сʱ��������Ź���ʱ�䡣</span>
							</div>
						</td>
					</tr>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:equal value="stu" name="userType">
									<button type="button" onclick="saveKhsq('save');">
										����ݸ�
									</button>
									</logic:equal>
									<button type="button" onclick="saveKhsq('submit');">
										�ύ����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>