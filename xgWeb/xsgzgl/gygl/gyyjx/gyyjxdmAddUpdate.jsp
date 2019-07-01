<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyyjx/js/gyyjx.js"></script>
	</head>
	<body>
		<html:form action="/gygl_gyyjxdmwh" method="post" styleId="gyyjxForm">
			
			<html:hidden property="yjfldm"/>
			<div style=''>
				<table width="98%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>增加意见分类</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th >
								<font color="red">* </font> 意见分类：
							</th>
							<td>
								<html:text property="yjflmc" styleId="yjflmc"  maxlength="25" onkeypress="if(pressEnter(event)){return false;}"></html:text>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEmpty name="actionType" >
										<logic:equal value="add" name="actionType">
											<button id="save_button" type="button"onclick="submitAction_dmwh('add');">保存</button>
										</logic:equal>
										<logic:equal value="update" name="actionType">
											<button id="save_button" type="button"onclick="submitAction_dmwh('update');">保存</button>
										</logic:equal>
									</logic:notEmpty>
									<button type="button" name="关 闭" onclick="iFClose();">
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

