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
</head>
<body>
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;" >
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					学号
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					${rs.xh }
				</td>
				
				<th  align="right" width="20%">
					姓名			
				</th>
				<td  width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				<th>专业</th>
				<td>${rs.zymc }</td>
			</tr>
			<tr>
				<th>班级</th>
				<td>${rs.bjmc }</td>
				<th>年级</th>
				<td>${rs.nj }</td>
			</tr>
			<tr>
				<th>楼栋名称</th>
				<td>${rs.ldmc }</td>
				<th>寝室号</th>
				<td>${rs.qsh }</td>
			</tr>
			<tr>
				<th>床位号</th>
				<td>${rs.cwh }</td>
				<th>寝室电话</th>
				<td>${rs.qsdh }</td>
			</tr>
			</tbody>
			</table>
			<table width="95%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>公寓纪律信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					违纪学年
				</th>
				<td align="left" width="30%">
					${rs.wjxn}
				</td>
					<th align="right" width="20%">
						违纪学期
					</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					纪律大类				
				</th>
				<td align="left" width="30%">
					${rs.gyjllbdlmc}
				</td>
				<th align="right" width="20%">
					纪律类别				
				</th>
				<td align="left" width="30%" >
					${rs.gyjllbmc}
				</td>
			</tr>
			<tr>
					<th width="20%">
						违纪时间				
					</th>
					<td align="left" width="30%" colspan="3">
						${rs.wjsj}
					</td>
			</tr>
			<tr>
					<th>
						备注
					</th>
					<td colspan="3" style="word-wrap: break-word!important; word-break: break-all!important" >
						${rs.bz }
					</td>
			</tr>
			</tbody>
			</table>
			
			<table width="80%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>历史纪律信息</span>
					</th>
				</tr>
			</thead>
			
				<tr>
				<td colspan="4">
					<div style="overflow-x:hidden;overflow-y:auto;">
					<table class="formList" width="100%">
						<thead>
							<tr align="left">
								<td align="center">违纪学年</td>
								<td align="center">违纪学期</td>
								<td align="center">纪律类别</td>
								<td align="center">违纪时间</td>
							</tr>
						</thead>
			<logic:empty name="rsArrList">
				<tr><td align="center" colspan="4">该学生无历史违纪记录！</td></tr>
			</logic:empty>
			<logic:notEmpty name="rsArrList">
				<logic:iterate name="rsArrList" id="s">
					<tr>
						<td align="center">
							${s.wjxn}
						</td>
						<td align="center">
						${s.xqmc}
						</td>
						<td align="center">
						${s.wjlb}
						</td>
						<td align="center">
						${s.wjsj}
						</td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
		</div>
		</td>
		</tr>
		</table>
		</div>
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
</body>
</html>
