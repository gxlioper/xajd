<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/sdftj/js/sdftj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				lddmChange();
				chChange();
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gygl_sdftj" method="post" styleId="sdfTjForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>水电费统计</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>年度</th>
							<td>
								<html:select property="nd" styleId="nd" style="width:98%">
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>								
							</td>
							<th><font color="red">*</font>季度</th>
							<td>
								<html:select property="jd" styleId="jd" style="width:98%">
									<html:option value=""></html:option>
									<html:option value="第一季度">第一季度</html:option>																						
									<html:option value="第二季度">第二季度</html:option>																						
									<html:option value="第三季度">第三季度</html:option>																						
									<html:option value="第四季度">第四季度</html:option>																						
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>楼栋名称</th>
							<td>
								<html:select   property="lddm" styleId="lddm"  style="width:98%">
									<html:option value=""></html:option>
									<html:options collection="LddmList" labelProperty="ldmc" property="lddm" />
								</html:select>
							</td>
							<th><font color="red">*</font>层号</th>
							<td>
								<select id="ch" name="ch" style="width:98%">
								</select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>寝室号</th>
							<td>
								<select   name="qsh" id="qsh"  style="width:98%">
								</select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>水费合计(元)</th>
							<td>
								
								<html:text property="sf" styleId="sf"  maxlength="8" onkeyup="checkMoneyBykeyUp(this)" style="width:98%"/>
								
							</td>
							<th><font color="red">*</font>电费合计(元)</th>
							<td>
								
								<html:text property="df" styleId="df"  maxlength="8" onkeyup="checkMoneyBykeyUp(this)" style="width:98%"/>
								
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
									<button type="button" onclick="saveData('save');">
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