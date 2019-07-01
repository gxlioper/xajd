<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/xswsjc/js/xswsjc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="form"	action="/gyglnew_xswsjc">
		<html:hidden property="jcrcid" />
		<html:hidden property="xh" />
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>床位信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								园区
							</th>
							<td width="30%">
								${cwxx.yqmc}
							</td>
							<th width="20%">
								楼栋
							</th>
							<td width="30%" >
								${cwxx.ldmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								寝室号
							</th>
							<td width="30%" >
								${cwxx.qsh}
							</td>
							<th width="20%">
								床位号
							</th>
							<td width="30%">
								${cwxx.cwh}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>学生卫生分</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>等级
							</th>
							<td colspan="3">
								<html:select property="fs">
									<html:options collection="wsfdjList" property="dj" labelProperty="dj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								等级备注
								<br></font>
							</th>
							<td colspan="3" >
								<html:textarea property="djbz" style="width:94%;height:100px"  styleId="djbz" onblur="chLengs(this,200);" ></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="save('gyglnew_xswsjc.do?method=xswsjcSave');" id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="iFClose();"  id="buttonClose">
										关 闭
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