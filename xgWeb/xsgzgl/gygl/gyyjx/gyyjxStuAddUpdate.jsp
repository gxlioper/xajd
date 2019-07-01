<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyyjx/js/gyyjx.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){

				
				
			});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_gyyjxstu" method="post" styleId="gyyjxForm">
			<logic:notEmpty name="actionType" >
				<logic:equal value="update" name="actionType">
					<html:hidden property="gyyjid"/>
				</logic:equal>
			</logic:notEmpty>
			
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<logic:notEmpty name="actionType" >
						<logic:equal value="update" name="actionType">
							<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
						</logic:equal>
						<logic:equal value="add" name="actionType">
							<%@ include file="/xsgzgl/gygl/comm/selectStudent.jsp" %>				
						</logic:equal>
					</logic:notEmpty>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>ס�޴�λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>¥������</th>
							<td>${cwxx.ldmc}</td>
							<th>���Һ�</th>
							<td>${cwxx.qsh}</td>
						</tr>
						<tr>
							<th>��λ��</th>
							<td>${cwxx.cwh}</td>
							<th>���ҵ绰</th>
							<td>${cwxx.qsdh}</td>
						</tr>
						<tr>
							<th>�շѱ�׼</th>
							<td>${cwxx.sfbz}</td>
							<th>�����꼶</th>
							<td>${cwxx.qsnj}</td>
						</tr>
						<tr>
							<th>����<bean:message key="lable.xb" /></th>
							<td>${cwxx.qsxymc}</td>
							<th>�����༶</th>
							<td>${cwxx.bjmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>�������
							</th>
							<td colspan="3">
								<html:select property="yjfldm" styleId="yjfldm"  >
									<html:option value="" >--��ѡ���������--</html:option>
									<html:options collection="yjflList" property="yjfldm" labelProperty="yjflmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							
							<th>
								<span class="red">*</span>�������
								<br/>
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="yjms" styleId="yjms" style="width:95%;" rows="5" ></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									
									<logic:notEmpty name="actionType" >
										<logic:equal value="add" name="actionType">
											<button id="save_button" type="button"  onclick="submitAction_stu('add');">����</button>
										</logic:equal>
										<logic:equal value="update" name="actionType">
											<button id="save_button" type="button"  onclick="submitAction_stu('update');">����</button>
										</logic:equal>
									</logic:notEmpty>
									<button type="button" name="�� ��" onclick="iFClose();">
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

