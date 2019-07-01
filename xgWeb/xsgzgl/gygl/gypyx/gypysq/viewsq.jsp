<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${GypySqForm.sqid}&tt="+new Date().getTime());
			})
		</script>
	</head>
	<body>
		<html:form action="/gypynew_gypysq" method="post" styleId="GypySqForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">楼栋</th>
							<td width="35%">
								${qsxx.ldmc}
								<!-- 
								<html:select property="lddm" styleId="lddm" style="width:150px;">
								
								</html:select>	 -->							
							</td>
							<th width="15%">层号</th>
							<td width="35%">
								${qsxx.ch}层
								<!-- <html:select property="ch" styleId="ch" style="width:150px;">
									
								</html:select> -->
							</td>
						</tr>
						<tr>
							<th>寝室号</th>
							<td>
								${qsxx.qsh}
							<!-- 
								<html:select   property="qsh" styleId="qsh"  style="width:150px;">
								</html:select>-->
							</td>
							<th>申请星级</th>
							<td>
								${GypySqForm.sqxj}
							</td>
						</tr>
						<tr>
							<th>是否为再次挂星</th>
							<td>
								${GypySqForm.sfzcgx}
							</td>
							<th>申请时间</th>
							<td>${GypySqForm.sqsj} </td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								${GypySqForm.sqly}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审批信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
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
								<div class="btn">
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