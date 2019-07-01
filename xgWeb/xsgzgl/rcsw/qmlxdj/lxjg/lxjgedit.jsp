<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/qmlxdj/lxjg/js/lxjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type="text/javascript">
			jQuery(function(){
				proviceCiyyLocalMain({type:"edit",id:"mddssx",flag:"wxxdz"});
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/qmlxjg" method="post" styleId="LxjgForm">
				<html:hidden property="jgid" styleId="jgid" />
				<html:hidden property="xh" styleId="xh" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>离校趋向信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								<html:hidden property="xn" styleId="xn" value="${xn}"/>
								${xn}
							</td>
							<th>学期</th>
							<td>
								<html:hidden property="xq" styleId="xq" value="${xq}"/>
								${xqmc}
							</td>
						</tr>
						<tr>
							<th>是否离校</th>
							<td>
								<select name="sflxdm" id="sflxdm">
									<option value="是" <logic:equal value="是" name="sflxdm"> selected</logic:equal>>是</option>
									<option value="否" <logic:equal value="否" name="sflxdm"> selected</logic:equal>>否</option>
								</select>
							</td>
							<th><span class="red">*</span>离校类型</th>
							<td>
								<html:select property="lxlx" styleId="lxlx" style="width:90%" >
									<option value=""></option>
									<html:options collection="lxlxList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>监护人姓名</th>
							<td>
								<html:text property="jhrxm" styleId="jhrxm" style="width:90%" maxlength="10" />						
							</td>
							<th><font color="red">*</font>监护人联系方式</th>
							<td>
								<html:text property="jhrlxfs" styleId="jhrlxfs" style="width:90%" maxlength="12" onkeyup="checkInputLxfx(this)"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>是否与监护人联系</th>
							<td>
								<html:radio property="sflx" value="是">是</html:radio>
								<html:radio property="sflx" value="否">否</html:radio>
							</td>
							<th><font color="red">*</font>离校时间</th>
							<td>
								<html:text property="lxsj" styleId="lxsj" style="width:90%" maxlength="10" onclick="return showCalendar('lxsj','y-mm-dd',true,'fxsj');" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>离校交通工具</th>
							<td>
								<html:select property="lxjtgjdm" styleId="lxjtgjdm" style="width:90%" >
									<html:options collection="dmList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th><font color="red">*</font>离校车次/航班</th>
							<td>
								
								<html:text property="lxcchb" styleId="lxcchb" style="width:90%"  maxlength="25" />
								
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>目的地</th>
							<td colspan="3">
								<html:hidden  property="mddssx" styleId="mddssx"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>返校时间</th>
							<td>
								<html:text property="fxsj" styleId="fxsj" maxlength="10" style="width:90%" onclick="return showCalendar('fxsj','y-mm-dd',false,'lxsj');" />
							</td>
							<th><font color="red">*</font>返校交通工具</th>
							<td>
								<html:select property="fxjtgjdm" styleId="fxjtgjdm" style="width:90%" >
									<html:options collection="dmList" labelProperty="mc" property="dm"/>
								</html:select>
								
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>返校车次/航班</th>
							<td>
								<html:text property="fxcchb" styleId="fxcchb" style="width:90%"  maxlength="25" />
							</td>
						</tr>
						<tr>
							<th>备注
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,500);"
											   style="width:90%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveJg();">
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