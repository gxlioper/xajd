<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		
	</script>
</head>
<body>
	<html:form action="/gyglnew_tsgl" method="post">
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>退宿信息查看</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					学号
				</th>
				<td width="34%">
					<bean:write name="map" property="xh"/>
				</td>
				<th align="right" width="16%">
					姓名
				</th>
				<td width="34%">
					<bean:write name="map" property="xm"/>
				</td>
			</tr>
			<tr>
				<th align="right" width="16%">
					性别
				</th>
				<td width="34%">
					<bean:write name="map" property="xb"/>
				</td>
				<th align="right" width="16%">
					年级
				</th>
				<td width="34%">
					<bean:write name="map" property="nj"/>
				</td>
			</tr>
			<tr>
				<th align="right" width="16%">
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td width="34%">
					<bean:write name="map" property="xymc"/>
				</td>
				<th align="right" width="16%">
					专业
				</th>
				<td width="34%">
					<bean:write name="map" property="zymc"/>
				</td>
			</tr>
			
			<tr>
				<th>班级</th>
				<td>${map.bjmc }</td>
				<th align="right" width="16%">
					当前住宿寝室
				</th>
				<td align="left" width="34%">
					<font color="blue">
						<logic:notEmpty name="map" property="dqldmc">
							<bean:write name="map" property="dqldmc"/>_
							<bean:write name="map" property="dqqsh"/>_
							<bean:write name="map" property="dqcwh"/>
						</logic:notEmpty>					
						<logic:empty name="map" property="dqldmc">
							无
						</logic:empty>
					</font>
				</td>
			</tr>
			</tbody>			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>退宿信息</span>
					</th>
				</tr>
			</thead>
			
			<tbody>
			<tr>
				<th align="right" width="16%">
					退宿原因				
				</th>
				<td align="left" width="34%">
					<bean:write name="map" property="tsyy"/>
				</td>
				<th align="right" width="16%">
					退宿寝室
				</th>
				<td align="left" width="34%">
					<font color="blue">
						<bean:write name="map" property="ldmc"/>_
						<bean:write name="map" property="qsh"/>_
						<bean:write name="map" property="cwh"/>
					</font>
				</td>
			</tr>
			<tr>
				<th>入住时间</th>
				<td>${map.rzsj }</td>
				<th align="right" width="16%">
					退宿时间				
				</th>
				<td align="left" width="34%">
					<bean:write name="map" property="tssj"/>
				</td>
			</tr>
			<tr>
				<th align="right" width="16%">
					退宿操作人
				</th>
				<td align="left" width="34%">
					<bean:write name="map" property="tsczr"/>
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>
					备注
				</th>
				<td colspan="3">
					<bean:write name="map" property="bz"/>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz"></div>
			          <div class="btn">
			            <button type="button"  name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
</body>
</html>
