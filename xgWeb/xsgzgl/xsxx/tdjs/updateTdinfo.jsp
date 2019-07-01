<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/tdjs/js/tdgl.js"></script>
		
		<script type="text/javascript">
			function saveForm(){
				var url = "tdjs.do?method=updateTdinfo&type=save";
				if (checkNotNull("nj-xy-zy-bjmc-zgh")){
					ajaxSubFormWithFun("tdjsForm",url,function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
					});
				} else{
					showAlert("�뽫��������д������");
				}
			}

		</script>
	</head>
	<body >
		<html:form action="/tdjs" method="post" styleId="tdjsForm">
			<input type="hidden" id="xyV" name="xyV" value=""/>
			<input type="hidden" id="zyV" name="zyV" value=""/>
			<input type="hidden" id="bjV" name="bjV" value=""/>	
			<html:hidden property="bjdm"/>
		
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�޸��Ŷ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th>
								<font color="red">*</font>�꼶
							</th>
							<td>
								<html:select property="nj" onchange="initZyList();" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font><bean:message key="lable.xb" />
							</th>
							<td width="65%" >
								<html:select property="bmdm" onchange="initZyArray('xy','zy');"  styleId="xy" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>רҵ
							</th>
							<td >
								<html:select property="zydm"  styleId="zy" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>	
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�Ŷ�
							</th>
							<td >
								<html:text property="bjmc" maxlength="32" styleId="bjmc"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>ָ����ʦ����
							</th>
							<td >
								<html:text property="zgh" styleId="zgh" 
										   style="width:60px;" onchange="loadZdls();"
										   maxlength="20" styleClass="text_nor" />
										   
								<font id="xm">
									<bean:write name="tdjsForm" property="zdls"/>
								</font>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<span class="button" onclick="saveForm();">
										�� ��
									</span>
									<span class="button" onclick="iFClose();">
										�� ��
									</span>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

