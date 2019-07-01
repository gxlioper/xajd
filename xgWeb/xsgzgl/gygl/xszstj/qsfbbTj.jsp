<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		
		<script	type="text/javascript">
			function expKbbb(){
				var xymc = jQuery("#xymc").val();
				var url = "/xgxt/gyglnew_xszstj.do?method=qsfbDc";
				url +="&xymc="+escape(xymc);
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}
		</script>
	</head>
	<body onload="">
	
		<html:form action="/gyglnew_qszf" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<!--
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			-->
			<div style='width:98%;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="8">
								<span>寝室分布统计</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								统计<bean:message key="lable.xb" />
							</th>
							<td width="34%" colspan="3" >
								<html:select  property="xymc" styleId="xymc" value="${rs.xymc }">
									<html:options collection="xyList" property="xymc" labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button type="button" onclick="expKbbb()">
										确定
									</button>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

