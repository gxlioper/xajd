<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/jjgl/jjlsjg/js/jjlsjg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
	</head>
	<body style="width: 100%">
		<html:form action="/jjgl_jjlsjggl" method="post" styleId="jjlsjgForm" onsubmit="return false;">
			<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>�ҽ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>�ó���Ŀ
							</th>
							<td width="80%" colspan="3">
								<logic:iterate id="jjxk" name="jjxkList">
									<input type="checkbox" name="sckm" value="${jjxk.jjxkmc}"/> ${jjxk.jjxkmc}
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>�ҽ��꼶
							</th>
							<td width="30%">
								<html:select  property="jjnj" styleId="jjnj" style="width:100px" >
									<html:options collection="jjnjList" labelProperty="jjnjmc" property="jjnjdm"/>
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>��ϵ�绰
							</th>
							<td width="30%">
								<html:text property="lxdh" styleId="lxdh" ></html:text>
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								��ѧ����<br/>
								<font color="red">��������150���ڣ�</font>
							</th>
							<td colspan="3" width="80%">
								<html:textarea property="jxxy" styleId="jxxy"
											   onkeypress="checkLen(this,150);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								�����Ա
							</th>
							<td width="30%">
								<input type="checkbox" name="yxjy" value="1"/>
							</td>
							<th>�ö���ʾ</th>
							<td>
								<input type="checkbox" name="zdxs" value="1"/>
							</td>
						</tr>
					</tbody>
				 </table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="jjlsjgSaveForAdd();">
									����
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

