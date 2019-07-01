<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/zjsy/js/zjsykq.js"></script>
		<script type="text/javascript">
		function qqxszj(html){
			jQuery("#tbody_qqryxx").append(html);	
			}
		</script>
	</head>		
	<body>	
		<html:form action="/zjsy_kqgl" method="post" styleId="ZjsyKqForm" onsubmit="return false;">
		<html:hidden property="id" styleId="id"/>
		<html:hidden property="xn" styleId="xn"/>
		<html:hidden property="xq" styleId="xq"/>
		<html:hidden property="yf" styleId="yf"/>
		<html:hidden property="zc" styleId="zc"/>
		<html:hidden property="bjdm" styleId="bjdm"/>
		<input type="hidden" id="objStr" name="objStr"/>
		<div style='tab;width:100%;height:410px;overflow-x:hidden;overflow-y:auto;' >
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
						<span>考勤信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					    <tr>
							<th width="20%">学年学期</th>
							<td>
								<span>${ZjsyKqForm.xn}</span><span>${ZjsyKqForm.xqmc}</span>
							</td>
						  <th>月份</th>
							<td>
								<span>${ZjsyKqForm.toyf}</span>
							</td>
					    </tr>
					    <tr>
					        <th>周次</th>
							<td>
								<span>${ZjsyKqForm.tozc}</span>
							</td>
					        <th>
								班级
							</th>
							<td>
								<span>${ZjsyKqForm.bjmc}</span>
							</td>
							
						</tr>
					    <tr>
					   		 <th><span class="red">*</span>应出勤人数</th>
								<td colspan="3">
									<span>${ZjsyKqForm.cqrs}</span>
								</td>
						</tr>
			      		  <tr>
							<th>
								备注
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='4' disabled="true"
								/>
							</td>
			      		</tr>
					</tbody>
				</table>
			   <table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width='25%'>学号</td>
							<td width='20%'>姓名</td>
							<td width='20%'><font color="red">*</font>病假次数</td>
							<td width='20%'><font color="red">*</font>事假次数</td>
							<td width='20%'><font color="red">*</font>旷课节数</td>
						</tr>
					</thead>
					<tbody id="tbody_qqryxx">
					  <logic:iterate id="i" name="kqinfoList" indexId="index01">
						<tr>
							<td name="xh">${i.xh}</td>
							<td>${i.xm}</td>
							<td>${i.bjcs}</td>
							<td>${i.sjcs}</td>
							<td>${i.kkjs}</td>
						</tr>
					</logic:iterate>
				    </tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz"></div>
								<div class="btn">
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