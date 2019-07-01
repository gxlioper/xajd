<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/kqgl/js/kqgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<script type="text/javascript">
		   jQuery(function(){
			   var kqlb = jQuery("#kqlbmc").text();
			   if("病假"==trim(kqlb)){
				   jQuery("#noneOne").css({"display":""});
				   jQuery("#noneTwo").css({"display":""});
			   }else{
				   jQuery("#noneOne").css({"display":"none"});
				   jQuery("#noneTwo").css({"display":"none"});
			   }
		   });
		</script>
	</head>
		
	<body>
		
		<html:form action="/rcsw_kqgl_kqgljg" method="post" styleId="KqgljgForm" onsubmit="return false;">
			
			<div style='tab;width:100%;height:410px;overflow-x:hidden;overflow-y:auto;' >
			    <html:hidden property="id" styleId="id"/>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>学生基本信息</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>考勤信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>考勤日期</th>
							<td>
							  <html:hidden  property="kqrq" styleId="kqrq"></html:hidden>
							  <span>${KqgljgForm.kqrq}</span>
							</td>
							<th>缺勤类别</th>
							<td>
							    <html:hidden  property="kqlbdm" styleId="kqlbdm"></html:hidden>
								<span id="kqlbmc">${KqgljgForm.kqlbmc}</span>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>缺勤天数(天)</th>
							<td>
								<html:text property="qqts" styleId="qqts"  maxlength="5"
									onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')">
								</html:text>
							</td>
							<th><span class="red">*</span>缺课节数</th>
							<td>
								<html:text property="kkjs" styleId="kkjs"  maxlength="5"
									onblur="if(value != '') {value=parseInt(value,10)}" onkeyup="checkInputData(this);return false;">
								</html:text>
							</td>
						</tr>
					    <tr id="noneOne" style="display:none;">
							<th><span class="red">*</span>缺课疾病类别</th>
							<td>
								<html:select property="qkjblbdm" styleId="qkjblbdm">
									<html:option value="">请选择</html:option>
									<html:options collection="qkjblblist" property="dm" labelProperty="mc" />
								</html:select>	
							</td>
							<th><span class="red">*</span>因病缺课疾病名称</th>
							<td>
								<html:select property="ybqkjbdm" styleId="ybqkjbdm">
									<html:option value="">请选择</html:option>
									<html:options collection="ybqkjblist" property="dm" labelProperty="mc" />
								</html:select>	
							</td>
						</tr>
						<tr id="noneTwo" style="display:none;">
							<th><span class="red">*</span>目前状况</th>
							<td>
								<html:select property="dqztdm" styleId="dqztdm">
									<html:option value="">请选择</html:option>
									<html:options collection="dqztlist" property="dm" labelProperty="mc" />
								</html:select>	
							</td>
							<th></th>
							<td>
							</td>
						</tr>
					    <tr>
							<th>
								备注
								<br /><font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='4' onblur="checkLen(this,500);return false;"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="updateForm('updateKqjg','update');">
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