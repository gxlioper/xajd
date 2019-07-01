<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript"  src="xsgzgl/xlzx/zxswh/js/zxspbDetail.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			creareSjdPbHtml();
			sfqyCopyPb();
		})
	</script>
	<style type="text/css">
	</style>
	</head>
	<body >
	
		<html:form action="/xlzx_zxspb" method="post" styleId="zxspbForm">
		<!-- <input type="hidden" id="url" name="url" value="xlzx_zxspb.do?method=zxspbDetail" /> -->
			<input type="hidden" name="pbid" id="pbid" value="${zxspbList.id}" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pbdate" id="date" value="${date}" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<div id="sjddmdiv">
			</div>
			<div style='tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;width:100%'>
				<table width="100%" border="0" class="formlist">
					<tbody id="tbody_jbxx">
						<tr style="height:35px">
							<th width="16%">
								咨询日期
							</th>
							<td colspan="3">${date} &nbsp;&nbsp;${weekday}
							</td>
						</tr>
						<logic:equal name="doType" value="add">
						<tr style="height:35px">
							<th width="16%">
								每<span style="color:blue">周${week}</span>安排<br />相同咨询师
							</th>
							<td >
								<input type="radio"  id="sfCopyPb" name="sfCopyPb"  value="1"  onclick="sfqyCopyPb();"/>是
								<input type="radio"  id="sfCopyPb" name="sfCopyPb"  value="2"  onclick="sfqyCopyPb();" checked="true"/>否
							</td>
							<th name="copyQzrqName">起止时间
							</th>
							<td name="copyQzrqName">
								<input type="hidden" name="startDate" id="pbqssj" style="width:100px"  value="${date}" />${date}&nbsp;至&nbsp;
								<span class="red">*</span><html:text property="endDate" styleId="pbjssj" style="width:30%"  value="${afterMonthDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${date}'})" />
							</td>
						</tr>
						</logic:equal>
						<tr style="height:35px">
							<th>
								预约说明<br/>
								<logic:notEqual name="doType" value="view"><font color="red"><B>(限500字)</B></font></logic:notEqual>
							</th>
							<td colspan="3">
								<logic:notEqual name="doType" value="view">
									<html:textarea  property='bz' styleId="bz" style="width:90%" value="${zxspbList.bz }" onblur="chLeng(this,500);"
										rows='4' />
								</logic:notEqual>
								<logic:equal name="doType" value="view">
									${zxspbList.bz }
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%">
									<thead>
									<logic:equal value="10026" name="xxdm" >
										<tr>
											<th width="10%" style="text-align: left">咨询师</th>
											<th width="20%" style="text-align: left">所属部门</th>
											<th width="10%" style="text-align: left">校区</th>
											<th width="60%" style="text-align: left">时间段</th>
										</tr>
									</logic:equal>
									<logic:notEqual value="10026" name="xxdm"  >
										<tr>
											<th width="10%" style="text-align: left">咨询师</th>
											<th width="30%" style="text-align: left">所属部门</th>
											<th width="60%" style="text-align: left">时间段</th>
										</tr>
									</logic:notEqual>
									</thead>
									<tbody id="databody">
										
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;">
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td>
								<logic:notEqual name="doType" value="view">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
								</logic:notEqual>
								<div class="btn">
									<logic:notEqual name="doType" value="view">
									<button id="buttonSave" onclick="savePbxxSjd();return false;">
										保 存
									</button>
									</logic:notEqual>
									<button onclick="Close();return false;">
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

