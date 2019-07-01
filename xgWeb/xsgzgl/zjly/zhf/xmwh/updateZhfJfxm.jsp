<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/xmwh.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/comm/comm.js"></script>
		<script type="text/javascript">
	
		</script>
	</head>
	<body>
		<html:form action="/zhf_jfxmwh" styleId="myForm" method="post">
		<html:hidden property="jfxmdm"/>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>计分项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>
								所属模块
							</th>
							<td>
								<html:select property="xmmkdm" styleId="xmmkdm" >
									<html:option value="">---请选择---</html:option>
									<html:options collection="xmmkList" property="xmmkdm" labelProperty="xmmkmc"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>
								计分项目
							</th>
							<td>
								<html:text property="jfxmmc" styleId="jfxmmc" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								考核要点
							</th>
							<td>
								<html:text property="khyd" styleId="khyd" maxlength="50" />
							</td>
							<th>
								<font color="red">*</font>
								得分
							</th>
							<td>
								<html:text property="fs" styleId="fs" maxlength="3"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								是否限分					
							</th>
							<td>
								<html:radio property="sfxf" value="1" onchange="xs(this);return false;">是</html:radio>
								<html:radio property="sfxf" value="0" onchange="xs(this);return false;">否</html:radio>
							</td>
							<th>
								<font color="red">*</font>
								是否必修					
							</th>
							<td>
								<html:radio property="sfbt" value="1" >是</html:radio>
								<html:radio property="sfbt" value="0" >否</html:radio>
							</td>
						</tr>
						<logic:present name="xdfs">
							<tr id="xdfstr">
								<th>限定分数</th>
								<td>
									<html:text property="xdfs" styleId="xdfs" maxlength="3"/>
								</td>
							</tr>
						</logic:present>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<font color="red">*</font>"为必填项
								</div>
								<div class="btn">
									<button class="button2" id="btn_bc" type="button" onclick="saveJfxm('update')">
										保 存
									</button>
									<button class="button2" type="button" onclick="Close()">
										关 闭
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
