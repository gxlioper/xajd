<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<%@ include file="/syscommon/head.ini"%>
	<%@ include file="/syscommon/autocomplete.ini"%>
	<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script language="javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="xsgzgl/xpjpy/zjdxjbsz/cssz/js/cssz.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#zxfzTr").hide();
			jQuery("#zdfzTr").hide();
			jQuery("#pxTr").hide();
			jQuery("#xmdjTable").hide();
		});
	</script>
  </head>
  <body>
		<html:form action="/xpjpy_cssz" method="post" styleId="zjdxCsszForm" >
			<input type="hidden" id="objStr" name="objStr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
									<button type="button" type="button" onclick="saveForAdd();">
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