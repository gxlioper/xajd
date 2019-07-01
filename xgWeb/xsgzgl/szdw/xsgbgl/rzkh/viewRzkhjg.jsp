<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/rzkh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/szdw_xsgb_rzkhjg" method="post" styleId="rzkhjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:500px;margin-bottom: 0px;" >
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
								<span>学生干部职务信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>职务名称</th>
							<td>
								${zwmc.zwmc}
							</td>
							<th>任职时间</th>
							<td>
								${rzsj}
							</td>
						</tr>
						<tr>
							<th>学年</th>
							<td >
								${khjg.xn}
							</td>
							<th>学期</th>
							<td>
								${xqmc}
							</td>
						</tr>
						<tr>
							<th id ="grsz"  id="grsz"></font>个人述职</th>
							 <td colspan="3" >${khjg.grsz}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>考核意见</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>个人自评</th>
							<td >
									${khjg.grzp}
							</td>
							<th>主管单位评审意见</th>
							<td >
									${khjg.zgdwyj}
							</td>
						</tr>
						<tr>
							<th id ="qdyj"  id="qdyj"></font>区队意见</th>
							 <td colspan="3" >${khjg.qdyj}</td>
						</tr>
						<tr>
							<th id ="szdwyj"  id="szdwyj"></font>所在单位意见</th>
							 <td colspan="3" >${khjg.szdwyj}</td>
						</tr>
						<tr>
							<th id ="ddyj"  id="ddyj"></font>大队意见</th>
							 <td colspan="3" >${khjg.ddyj}</td>
						</tr>
						<tr>
							<th id ="xsgzcyj"  id="xsgzcyj"></font>学生工作处意见</th>
							 <td colspan="3" >${khjg.xsgzcyj}</td>
						</tr>
						<tr>
							<th id ="bz"  id="bz"></font>备注</th>
							 <td colspan="3" >${khjg.bz}</td>
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
								</div>
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