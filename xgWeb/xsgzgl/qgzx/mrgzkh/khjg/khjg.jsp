<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/mrgzkh/khjg/khjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/mrgzkhKhjg" method="post" styleId="GzkhKhjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:457px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��д��Ϣ</span>
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
								<html:text property="gs" styleId="gs" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"></html:text>��Сʱ��
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��λ</th>
							<td>
								<html:select  property="gwdm" styleId="gwdm" style="width:200px">
									<html:options collection="gwList" property="gwdm" labelProperty="gwmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>�����ص�</th>
							<td>
								<html:text property="gzdd" styleId="gzdd" maxlength="50" ></html:text>
								
							</td>
						</tr>
						<tr>
						<th><font color="red">*</font>�������ڼ�ʱ���</th>
							<td colspan="3">
								<html:text property="gzrq" styleId="gzrq" onfocus="showCalendar('gzrq','y-mm-dd');" />
								<html:select  property="gzkssj" styleId="gzkssj" >
									<html:options collection="gzsjList" property="dm" labelProperty="mc" />
								</html:select>
								��
								<html:select  property="gzjssj" styleId="gzjssj" >
									<html:options collection="gzsjList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
						
							<th><span class="red">*</span>��������
								</br><font color="red">(��1000��)</font></th>
							<td colspan="3">
								<html:textarea property="gznr" styleId="gznr" 
											   onkeypress="checkLen(this,100);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveKhjg('save');">
										��    ��
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