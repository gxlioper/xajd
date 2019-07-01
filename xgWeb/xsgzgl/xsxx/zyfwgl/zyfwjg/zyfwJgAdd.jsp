<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/zyfwgl/zyfwjg/js/zyfwJg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type='text/javascript'>
			jQuery(function(){
				proviceCiyyLocalMain({type:"add",id:"fwddssx",flag:"yxxdz"});
			})
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xsxx_zyfwgl_jg" method="post" styleId="zyfwJgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>志愿服务结果信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>学年
							</th>
							<td width="30%">
								<html:select  property="xn" styleId="xn" style="width:100px" >
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>学期</th>
							<td>
								<html:select  property="xq" styleId="xq" >
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>服务开始时间
							</th>
							<td width="30%">
								<html:text property="fwkssj"
									onclick="return showCalendar('fwkssj','yyyy-MM-dd HH:mm',true,'fwjssj');" styleId="fwkssj" ></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>服务结束时间
							</th>
							<td width="30%">
								<html:text property="fwjssj"
									onclick="return showCalendar('fwjssj','yyyy-MM-dd HH:mm',false,'fwkssj');" styleId="fwjssj" ></html:text>
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								<font color="red">*</font>服务地点
							</th>
							<td colspan="3">
								<html:hidden  property="fwddssx" styleId="fwddssx"/>
							
								<html:text property="fwdd" styleId="fwdd" maxlength="100"></html:text>
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>见证人
							</th>
							<td width="30%">
								<html:text property="jzr" styleId="jzr" maxlength="10"></html:text>
							</td>
							<th><span class="red">*</span>服务小时数</th>
							<td>
								<html:text property="fwxss" styleId="fwxss" maxlength="5"
									onkeyup="checkTimeNumForKeyup(this)"
									 onblur="checkTimeNumForBlur(this)"></html:text>
							</td>
						</tr>
						<tr><th width="20%"><font color="red">*</font>服务内容
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="fwnr" styleId="fwnr" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
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
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="zyfwJgSaveForAdd();">
									保存
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

