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
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xjjt/wmqs/js/wmqs.js"></script>
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/xpjpy_wmqs" method="post" styleId="wmqsForm">
			<input type="hidden" id="type" value="${type}"/>
			<input type="hidden" id="guid" value="${wmqsForm.guid}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						
					    <tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select property="xn" styleId="xn"  onchange="changeXmmc();" style="width:160px">
									<html:options collection="xnList" labelProperty="xn"
										property="xn" />
								</html:select>
							</td>
							<th><span class="red">*</span>校区</th>
							<td>
								<html:select property="xqmc" styleId="xqmc" style="width:160px">
									<html:options collection="xqmcList" labelProperty="xqmc"
										property="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
				    		<th><span class="red">*</span>楼栋</th>
							<td>
								<html:text   property="ldmc" styleId="ldmc" styleClass="text_nor" style="width:155px"></html:text>
							</td>
								
							<th><span class="red">*</span>寝室号</th>
							<td>
								<html:text   property="qsh" styleId="qsh"  styleClass="text_nor" style="width:155px"></html:text>
							</td>	
					    </tr>
						<tr>
							<th>
								备注&nbsp;&nbsp;&nbsp;<br/>
								<font color="red">&lt;限200字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="bz" styleId="bz" style="width:100%;" rows="6" onblur="checkLen(this,200);"></html:textarea>
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
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>