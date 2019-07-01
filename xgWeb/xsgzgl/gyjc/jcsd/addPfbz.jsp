<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcsd/js/pfbz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#fjid").change(function(){
					if(this.value == "top"){
						jQuery("#tid").text("项目");
					}else{
						jQuery("#tid").text("要求");
					}
				})
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gyjc_pfbz" method="post" styleId="PfbzForm">
			<html:hidden property="js" name="PfbzForm" styleId="js"/>
			<html:hidden property="jjlx" name="PfbzForm" styleId="jjlx"/>
			<html:hidden property="xydm" name="PfbzForm" styleId="xydm"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>增加项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>项目</th>
							<td>
								<html:select property="fjid" styleId="fjid" style="width:90%">
									<html:option value="top">顶级</html:option>
									<html:options collection="xmList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
								<th><font color="red">*</font>序号</th>
								<td>
									<html:text property="xh" styleId="xh" onkeyup="checkInput(this)" maxlength="10" style="width:90%" />
								</td>
						</tr>
						<tr>
							<th><font color="red">*</font><font id=“tit">项目</font></th>
							<td>
								<html:textarea property="wsqkyq" styleId="wsqkyq" style="width:90%" rows="5" onblur="checkLen(this,50)"   />
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="savePfbz();">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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