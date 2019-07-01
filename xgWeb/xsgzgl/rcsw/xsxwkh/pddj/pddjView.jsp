<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	</script>
</head>
<body>
	<html:form action="/xsxwkhDjpd" method="post" styleId="pddjForm">
		<html:hidden name="rs" property="xh" styleId="xh"/>
		<html:hidden name="rs" property="id" styleId="id"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>${XsxwKhxxMap}学年学生行为考核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								班主任辅导员测评等级
							</th>
							<td width="30%">
								${rs.bzrcpdj}
							</td>
							</td>
							<th>班级学生测评等级</th>
							<td>
								${rs.xscpdj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								奖励分
							</th>
							<td width="30%">
								${rs.jlf}
							</td>
							<th width="20%">
								处罚分
							</th>
							<td width="30%">
								${rs.cff}
							</td>
						</tr>
						<tr>
							
							<th width="20%">
								附加分
							</th>
							<td width="30%">
								${rs.fjf}
							</td>
						   <th width="20%">总分</th>
							<td colspan="3">
								${rs.zf}
							</td>
					</tr>
					</tbody>
			<thead>
					<tr>
						<th colspan="4">
							<span>奖励分明细</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4">
					<table  width="100%" class="formlist" >
						<tr>
							<th width="8%" align="center"><div align="center">项目名称</div></th>
							<th width="10%" align="center"><div align="center">分值</div></th>
							<th width="10%" align="center"><div align="center">奖励时间</div></th>
						</tr>
						<logic:empty name="jlfList">
							<tr>
								<td colspan="3" align="center">暂时没有数据</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="jlfList">
							<logic:iterate id="jlf" name="jlfList" >
								<tr>
									<td align="center">${jlf.xmmc}</td>
									<td align="center">${jlf.fz}</td>
									<td align="center">${jlf.fssj}</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
			</tbody>
			<thead>
					<tr>
						<th colspan="4">
							<span>处罚分明细</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4">
					<table  width="100%" class="formlist" >
						<tr>
							<th width="8%" align="center"><div align="center">项目名称</div></th>
							<th width="10%" align="center"><div align="center">分值</div></th>
							<th width="10%" align="center"><div align="center">处罚时间</div></th>
						</tr>
						<logic:empty name="cffList">
							<tr>
								<td colspan="3" align="center">暂时没有数据</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="cffList">
							<logic:iterate id="cff" name="cffList" >
								<tr>
									<td align="center">${cff.xmmc}</td>
									<td align="center">-${cff.fz}</td>
									<td align="center">${cff.fssj}</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
			</tbody>
			<thead>
					<tr>
						<th colspan="4">
							<span>附加分明细</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4">
					<table  width="100%" class="formlist" >
						<tr>
							<th width="8%" align="center"><div align="center">分值</div></th>
							<th width="10%" align="center"><div align="center">服务时间</div></th>
							<th width="10%" align="center"><div align="center">备注</div></th>
						</tr>
						<logic:empty name="fjfList">
							<tr>
								<td colspan="3" align="center">暂时没有数据</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="fjfList">
							<logic:iterate id="fjf" name="fjfList" >
								<tr>
									<td align="center">${fjf.fz}</td>
									<td align="center">${fjf.fwsj}</td>
									<td align="center">${fjf.bz}</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
