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
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>公寓纪律信息查看</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td align="left" colspan="4"><span>学生信息</span></td>
			</tr>
			</tbody>
			<tbody>
			<tr>
				<th align="right" width="16%">
					学号
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					${rs.xh }
				</td>
				
				<th  align="right" width="16%">
					姓名			
				</th>
				<td  width="34%">
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
				<th>住宿寝室</th>
				<td>${rs.zsqs }</td>
				<th>寝室电话</th>
				<td>${rs.qsdh }</td>
			</tr>
			</tbody>
			<tbody>
				<tr>
					<td align="left" colspan="4">公寓纪律信息</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td align="center">违纪时间</td>
					<td align="center">违纪类别</td>
					<td align="center" colspan="2">备注</td>
				</tr>
				<logic:empty name="rsArrList">
					<tr><td align="center" colspan="4">该学生无违纪记录！</td></tr>
				</logic:empty>
				<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s">
								<tr>
									<!-- 显示信息 -->
									<logic:iterate id="v" name="s" offset="0" length="2">
										<td align="center">
											${v }
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<td align="center" colspan="2">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
</body>
</html>
