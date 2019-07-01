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
				var xn = jQuery("#xn").val();
				var yf = jQuery("#yf").val();
				var xymc = jQuery("#xymc").val();
				var url = "/xgxt/gyglnew_qszf_ajax.do?method=xqcsTj";
				url +="&xn="+xn;
				url +="&yf="+yf;
				url +="&xymc="+xymc;
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
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:98%;height:320px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="8">
								<span>�߷ô���ͳ��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								ͳ�����
							</th>
							
							<td width="34%" colspan="3" >
								<html:select  property="xn" styleId="xn" value="${rs.xn }">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								ͳ���·�
							</th>
							
							<td width="34%" colspan="3" >
								<select id="yf">
									<option value=""></option>
									<option value="01">1��</option>
									<option value="02">2��</option>
									<option value="03">3��</option>
									<option value="04">4��</option>
									<option value="05">5��</option>
									<option value="06">6��</option>
									<option value="07">7��</option>
									<option value="08">8��</option>
									<option value="09">9��</option>
									<option value="10">10��</option>
									<option value="11">11��</option>
									<option value="12">12��</option>
								</select>
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								ͳ��<bean:message key="lable.xb" />
							</th>
							<td width="34%" colspan="3" >
								<html:select  property="xymc" styleId="xymc" value="${rs.xymc }">
									<option value=""></option>
									<html:options collection="xyList" property="xymc" labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button type="button" onclick="expKbbb();Close();">
										ȷ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
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