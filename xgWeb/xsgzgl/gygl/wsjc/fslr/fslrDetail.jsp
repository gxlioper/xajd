<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript' src="xsgzgl/gygl/wsjc/js/kflr.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/gyglnew_kslr" method="post" styleId="kflrForm" onsubmit="return false;">
		<html:hidden property="lddm" styleId="lddm" value="${lddm}"/>
		<html:hidden property="qsh" styleId="qsh" value="${qsh}"/>
		<html:hidden property="jcrcid" styleId="jcrcid" value="${jcrcid}"/>
		<input type="hidden" id="sfbc" value="${bcfs}" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th width="10%">模块</th>
							<th width="10%">代号</th>
							<th width="65%">内容</th>
							<th width="10%">评分</th>
							<th width="5%">扣分</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="mxList">
							<logic:iterate id="mx" name="mxList">
								<tr>
									<td>
										${mx.mk}
									</td>
									<td>
										${mx.dh}
									</td>
									<td>
										${mx.nr}
									</td>
									<td>
										${mx.pf}
									</td>
									<td>
										<input type="text" value="${mx.kf}" onblur="szFs(this)" name="kfs" style="width: 50px" maxlength="6"/>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>						
					</tbody>
				 </table>			
			</div>
			<div style="height: 30px">
			
			</div> 
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
										<button type="button" onclick="bc();">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

