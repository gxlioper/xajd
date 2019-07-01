<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxjbsz/cssz/js/cssz.js"></script>
		<script type="text/javascript" >
			jQuery(function(){
				//����Ŀ����radio��ֵ
				jQuery("input[name='xmlx'][value='${xmlx}']").attr("checked",true);
				//����ҳ�津��onclick�¼�
				changeZcxm();
			})
		</script>
	</head>
	<body>
		<html:form action="/xpjpy_cssz" method="post" styleId="zjdxCsszForm" >
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="xmdm" styleId="xmdm"/>
			<input type="hidden" id="objStr" name="objStr"/>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th><span class="red">*</span>�۲���Ŀ</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" style="width:155px;" maxlength="10" styleClass="text_nor"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>������Ŀ</th>
							<td>
				    			<html:select property="fjdm" styleId="fjdm" style="width:155px">
				    				<html:option value="">��ѡ��</html:option>
				    				<html:option value="top">����</html:option>
				    				<html:options collection="yjxmlist" property="dm" labelProperty="mc" />
				    			</html:select>
				    		</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��Ŀ����</th>
							<td>
								<input type="radio" id="xmlx" name="xmlx" value="0" onclick="changeZcxm();"/>�ȼ�
								<input type="radio" id="xmlx" name="xmlx" value="1" onclick="changeZcxm();"/>��ֵ
							</td>
						</tr>
						<tr id="zxfzTr">
							<th><span class="red">*</span>��С��ֵ</th>
							<td>
								<html:text property="zxfz" styleId="zxfz"  maxlength="4" onkeyup="checkInput(this)"></html:text>
							</td>
						</tr>
						<tr id="zdfzTr">
							<th><span class="red">*</span>����ֵ</th>
							<td>
								<html:text property="zdfz" styleId="zdfz"  maxlength="4" onkeyup="checkInput(this)"></html:text>
							</td>
						</tr>
						<tr id="pxTr">
							<th>����</th>
							<td>
								<html:text property="px" styleId="px"  maxlength="3" onkeyup="checkInput(this)"></html:text>
							</td>
						</tr>
				</table>
				<div style="height:1px"></div>
				<table width="100%" border="0" class="formlist" id="xmdjTable">
					<thead>
						<tr>
							<th colspan="3">
								<span>��Ŀ���͵ȼ�����</span>
								<img src="xsxx/fbgl/images/add-icons-1.gif" alt="����" onclick="addRow();return false;">
								<img src="xsxx/fbgl/images/delete-icons-2.gif" alt="ɾ��" onclick="delRow();return false;">
							</th>
						</tr>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td><font color="red">*</font>����</td>
							<td>����</td>
						</tr>
					</thead>
					<tbody id="tbody_zcxmLx">
						<logic:empty name="zcxmDjList">
							<tr>
								<td>
									<input type="checkbox" />
								</td>
								<td>
									<input type="text" name="mc" maxlength="10"/>
								</td>
								<td >
								    <input type="text" name="px" maxlength="3" onkeyup="checkInput(this)"/>
								</td>																			
							</tr>
						</logic:empty>
						<logic:iterate id="i" name="zcxmDjList" indexId="index01">
							<tr>
								<td><input type="checkbox" id="checkbox_${index01}"/></td>
								<td>
									<html:text property="mc" styleId="mc" maxlength="10" value="${i.mc}"></html:text>
								</td>
								<td>
									<html:text property="px" styleId="px" maxlength="3" onkeyup="checkInput(this)" value="${i.px}"></html:text>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForUpdate();">
										ȷ ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
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