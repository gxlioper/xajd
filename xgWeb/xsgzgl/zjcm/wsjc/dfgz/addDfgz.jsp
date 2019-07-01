<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/dfgz/js/dfgz.js"></script>
		<script language="javascript">
			jQuery(function() {
				changeNy();
			});
		</script>
	</head>
	<body>
		<html:form action="/cjWsfDfgz" method="post" styleId="DfgzForm">
			<input type="hidden" name="xn" value="${xn}" id="xn"/>
			<input type="hidden" name="xq" value="${xq}" id="xq"/>
			<input type="hidden" id="pfzJson" name="pfzJson"/>
			<div style='tab;width:100%;overflow:hidden;'>
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>时间设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">
								学年
							</th>
							<td width="32%">
								${xn}
							</td>
							<th width="18%">
								学期
							</th>
							<td width="32%">
								${xqmc}
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>抽查年月</th>
					    	<td>
								<html:select property="ccny" styleId="ccny" onchange="changeNy()">
									<html:options name="nyList" />
								</html:select>
							</td>
							<th><span class="red">*</span>开放维护时间</th>
					    	<td>
								<html:text property="wwsj" styleId="wwsj" style="width:70px;" onfocus="showCalendar('wwsj','y-mm-dd',true,'wwzzsj');" />
								  至  
								<html:text property="wwzzsj" styleId="wwzzsj" style="width:70px;" onfocus="showCalendar('wwzzsj','y-mm-dd',false,'wwsj');" /> 
							</td>	
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">
								"
								<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" type="button"
									onclick="saveForm('save');return false;">
									保 存
								</button>
								<button type="button" type="button"
									onclick="iFClose();return false;">
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

