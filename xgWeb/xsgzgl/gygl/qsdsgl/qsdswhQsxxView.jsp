<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript">
		
		/*function searchRs(){
			allNotEmpThenGo('gyglnew_ldgl_ldgl.do');
		}*/

		</script>
	</head>
	<body>

		<html:form action="/gyglnew_ldgl" method="post">
			<!-- “˛≤ÿ”Ú -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="text" id="nouse" style="display: none"/>
			<!-- “˛≤ÿ”Ú -->
			<!-- ƒ£øÈ¿‡–Õ -->
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:289px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>«ﬁ “œÍ«È</span>
							</th>
						</tr>
					</thead>
				</table>
				<logic:notEmpty name="rsList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr"   indexId="index">
									<td>
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rsList" id="s" indexId="index">	
								<tr>
									<logic:iterate id="v" name="s">
										<td nowrap>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									πÿ ±’
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>
