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
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ���ɲ�ְ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ְ������</th>
							<td>
								${zwmc.zwmc}
							</td>
							<th>��ְʱ��</th>
							<td>
								${rzsj}
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td >
								${khjg.xn}
							</td>
							<th>ѧ��</th>
							<td>
								${xqmc}
							</td>
						</tr>
						<tr>
							<th id ="grsz"  id="grsz"></font>������ְ</th>
							 <td colspan="3" >${khjg.grsz}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������</th>
							<td >
									${khjg.grzp}
							</td>
							<th>���ܵ�λ�������</th>
							<td >
									${khjg.zgdwyj}
							</td>
						</tr>
						<tr>
							<th id ="qdyj"  id="qdyj"></font>�������</th>
							 <td colspan="3" >${khjg.qdyj}</td>
						</tr>
						<tr>
							<th id ="szdwyj"  id="szdwyj"></font>���ڵ�λ���</th>
							 <td colspan="3" >${khjg.szdwyj}</td>
						</tr>
						<tr>
							<th id ="ddyj"  id="ddyj"></font>������</th>
							 <td colspan="3" >${khjg.ddyj}</td>
						</tr>
						<tr>
							<th id ="xsgzcyj"  id="xsgzcyj"></font>ѧ�����������</th>
							 <td colspan="3" >${khjg.xsgzcyj}</td>
						</tr>
						<tr>
							<th id ="bz"  id="bz"></font>��ע</th>
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
										�ر�
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