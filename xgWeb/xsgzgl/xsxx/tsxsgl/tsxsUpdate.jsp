<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/tsxsgl/js/tsxsglManage.js"></script>
		<script type='text/javascript'>
		
		</script>
		<style type="text/css">	
			.demo_data2 {
			 	/*border: 1px solid #DEDEDE;*/
				display: inline;
			    float: left;
			    height: 15px;
			    margin: 0px 0px 0;
			    padding: 0px;
			    width: 160px;
			}
		</style>
		
	</head>
	<body onload="init();">
		<html:form action="/tsxs_tsxswh" method="post" styleId="TsxsglForm" onsubmit="return false;">
			<html:hidden property="xslxdm" styleId="xslxdm" value="${model.xslxdm}"/>
			<html:hidden property="xslxmc" styleId="xslxmc" value="${model.xslxmc}"/>
			<html:hidden property="xh" styleId="xh"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<tbody id="tbody_jbxx">
						<tr>
						<th width="16%">
						<span class="red">*</span>��������
					    </th>
						<td width="84%" colspan="3">
						<logic:notEmpty name="xslxList">
							<logic:iterate id="s" name="xslxList">
								<div class='demo_data2'>
								<input type="checkbox" name="xslxBoxList" id="xslx<bean:write name="s" property="xslxdm"/>" value="${s.xslxdm}"/>
								<label style="cursor:pointer;" for="xslx<bean:write name="s" property="xslxdm"/>">${s.xslxmc}</label>
								</div>
							</logic:iterate>
						</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��ע״̬
							</th>
							<td colspan="3">
								
								<logic:notEqual name="doType" value="view">
									<html:select property="gzzt" styleId="gzzt"  style="width:100px">
										<html:option value="1">��ע</html:option>
										<html:option value="2">ȡ����ע</html:option>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
						    <th>
								<span class="red">*</span>�����ʩ</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="clcs" styleId="clcs" cols="50" rows="4"
									style="width:100%" onblur="chLeng(this,500)"></html:textarea>
							</td>
						</tr>
						<tr>
						    <th>
								��ע</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" cols="50" rows="4"
									style="width:100%" onblur="chLeng(this,500)"></html:textarea>
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
								<button type="button" onclick="save()">
									�� ��
								</button>
								<button type="button" onclick="Close();return false;">
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

