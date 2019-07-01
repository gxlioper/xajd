<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gywp/js/gywp.js"></script>

	</head>
	<body>
		<html:form action="gygl_qywpxx.do?method=viewGywpxx" styleId="gywpxxForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="id"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>公寓物品信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width: 20%">物品名称</th>
							<td style="width: 30%">
								<bean:write name="gywpxxForm" property="wpmc"/>
							</td>
							<th style="width: 20%">所属大类</th>
							<td style="width: 30%">
								<bean:write name="gywpxxForm" property="wpdlmc"/>
							</td>
						</tr>
						<tr>
							<th>物品类别</th>
							<td>
								<bean:write name="gywpxxForm" property="wplbmc"/>
							</td>
							<th>数量</th>
							<td>
								<bean:write name="gywpxxForm" property="sl"/>
							</td>
						</tr>
						<tr>
							<th>是否在合格期</th>
							<td>
								<bean:write name="gywpxxForm" property="sfzhgq"/>
							</td>
							<th>是否完好</th>
							<td>
								<bean:write name="gywpxxForm" property="sfwh"/>
							</td>
						</tr>
						<tr>
							<th>所属楼栋</th>
							<td><bean:write name="gywpxxForm" property="ldmc"/></td>
							<th>所属寝室</th>
							<td><bean:write name="gywpxxForm" property="qsh"/></td>
						</tr>
						<tr>
							<th>
								毁坏原因
								<br/>
							</th>
							<td colspan="3">
								<html:textarea readonly="true"  property="hhyy" styleId="hhyy" rows="4" style="width:98%"></html:textarea>
							</td>
						</tr>
						<tr>
							<th rowspan="4">
								备注
								<br/>
							</th>
							<td rowspan="4" colspan="3">
								<html:textarea readonly="true" property="bz" styleId="bz" rows="4" style="width:98%" ></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParent2();">
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