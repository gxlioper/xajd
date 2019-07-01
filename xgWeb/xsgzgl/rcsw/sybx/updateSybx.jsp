<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/sybx/js/updateSybx.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("th",jQuery("#sybxForm")).css("width","17%");
			});
		</script>
	</head>
	<body style="width:710px">
		
		<html:form action="/rcsw_sybx" method="post" styleId="sybxForm">
			<html:hidden property="guid"/>
			
			<div style='tab'>
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
								<span>��ҵ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:155px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>�������ҽ��<br/>���ս��</th>
							<td>
								<html:text property="czjmylbxje" styleId="czjmylbxje" maxlength="8" onkeyup="checkInputNum(this);changeBxje();" />
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>�������ҽ�Ʊ��ղα���ʼ����</th>
							<td>
								<html:text property="czjmylbxcbqsrq" styleId="czjmylbxcbqsrq"  value="${mkxxForm.czjmylbxcbqsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
							<th>�������ҽ�Ʊ��ղα���������</th>
							<td>
								<html:text property="czjmylbxcbjsrq" styleId="czjmylbxcbjsrq"  value="${mkxxForm.czjmylbxcbjsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>��ҵ���ս��</th>
							<td>
								<html:text property="sybxje" styleId="sybxje" maxlength="8" onkeyup="checkInputNum(this);changeBxje();" />
							</td>
							<th><span class="red">*</span>��ҵ���ղα�<br/>��ʼ����</th>
							<td>
								<html:text property="sybxcbqsrq" styleId="sybxcbqsrq"  value="${mkxxForm.sybxcbqsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
					    </tr>
					    <tr>
							<th>��ҵ���ղα�<br/>��������</th>
							<td>
								<html:text property="sybxcbjsrq" styleId="sybxcbjsrq"  value="${mkxxForm.sybxcbjsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
							<th>�����ܽ��<input type="hidden" name="bxje" value="${mkxxForm.bxje }" id="bxje"/></th>
							<td id="bxjeTd">
								${mkxxForm.bxje }
							</td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>����ԭ��</th>
							<td>
								<html:select property="zjyy" styleId="zjyy"  style="width: 152px" value="${mkxxForm.zjyy}">
									<html:options collection="zjyyList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th><span class="red">*</span>�α���Ա���</th>
							<td>
								<html:select property="cbrylb" styleId="cbrylb"  style="width: 152px" value="${mkxxForm.cbrylb}">
									<html:options collection="cbrylbList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>�ɷ���Ա���</th>
							<td>
								<html:select property="jfrylb" styleId="jfrylb"  style="width: 152px" value="${mkxxForm.jfrylb}">
									<html:options collection="jfrylbList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th><span class="red">*</span>���֤ǩ������</th>
							<td>
								<html:text property="sfzqfjg" styleId="sfzqfjg" maxlength="100" style=""/>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>���֤��Ч������ʼ����</th>
							<td>
								<html:text property="sfzyxqxqsrq" styleId="sfzyxqxqsrq"  value="${mkxxForm.sfzyxqxqsrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
							<th><span class="red">*</span>���֤��Ч���޽�ֹ����</th>
							<td>
								<html:text property="sfzyxqxjzrq" styleId="sfzyxqxjzrq"  value="${mkxxForm.sfzyxqxjzrq }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
							</td>
					    </tr>
					    <tr>
							<th>�໤������</th>
							<td>
								<html:text property="jhrxm" styleId="jhrxm" maxlength="20" />
							</td>
							<th width="18%">�໤�����֤��</th>
							<td>
								<html:text property="jhrsfzh" styleId="jhrsfzh" maxlength="18" onblur="if(!checkSfzh(this)){this.focus();}"/>
							</td>
					    </tr>
					    <tr>
							<th>
								<span class="red">*</span>ͨѶ��ַ
							</th>
							<td colspan="4">
								<html:text property="txdz" styleId="txdz" maxlength="50" style="width:550px" />
							</td>
			     		 </tr>
					    <tr>
							<th>
								��ע
								<br /><font color="red">(������500����)</font>
							</th>
							<td colspan="4">
								<html:textarea property='bz' style="width:95%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			     		 </tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
										�� ��
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