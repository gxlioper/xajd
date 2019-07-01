<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>

		<div class="formbox">
			<h3 class="datetitle_01">
				<span> 查询结果&nbsp;&nbsp; <logic:empty name="rswj">
						<font color="red"> 暂无材料或证明附件</font>
					</logic:empty> </span>
			</h3>

			<logic:notEmpty name="rswj">
				<table summary="" class="dateline" align="" width="100%">
					<tbody>
						<logic:iterate id="list" name="rswj">
							<tr onmouseover="rowOnClick(this)">
								<td>
									<a
										href="downloadfile.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj"/>">下载</a>
								</td>
								<td>
									<bean:write name="list" property="cfwh" />
								</td>
								<td>
									<bean:write name="list" property="cflbmc" />
								</td>
								<td>
									<bean:write name="list" property="cfyymc" />
								</td>
								<td>
									<bean:write name="list" property="sssj" />
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</logic:notEmpty>
		</div>
		<div class="tab">
			<table width="100%" border="0" class="formlist" id="rsTable">
				<thead>
					<tr>
						<th colspan="5">
							<span>违纪处分申诉申请详细信息</span>
						</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="btn">
								<button type="button" onclick="window.close();return false;">
									关闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>

				<tbody>
					<tr>
						<th width="15%">
							学号
						</th>
						<td width="28%">
							<bean:write name="rs" property="xh" />
						</td>
						<th width="18%">
							处分文件号
						</td>
						<td width="24%">
							<bean:write name="rs" property="cfwh" />
						</td>
						<td width="15%" rowspan="5">
							<img
								src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
								align="absmiddle" style="width:100px;height:120px" />

						</td>
					</tr>
					<tr>
						<th>
							姓名
						</th>
						<td>
							<bean:write name="rs" property="xm" />
						</td>
						<th>
							年度
						</th>
						<td >
							<bean:write name="rs" property="nd" />
						</td>
					</tr>
					<tr>
						<th>
							性别
						</th>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
						<th>
							学年
						</th>
						<td >
							<bean:write name="rs" property="xn" />
						</td>
					</tr>
					<tr>
						<th>
							年级
						</th>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
						<th>
							学期
						</th>
						<td>
							<bean:write name="rs" property="xq" />
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<th>
							处分时间
						</th>
						<td>
							<bean:write name="rs" property="cfsj" />
						</td>
					</tr>
					<tr>
						<th>
							专业
						</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<th>
							处分类别
						</th>
						<td colspan="2">
							<bean:write name="rs" property="cflbmc" />
						</td>
					</tr>
					<tr>
						<th>
							班级
						</th>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<th>
							处分事由
						</th>
						<td colspan="2">
							<bean:write name="rs" property="cfyymc" />
						</td>
					</tr>
					<tr>
						<th>
							联系地址
						</th>
						<td>
							<bean:write name="rs" property="dz" />
						</td>
						<th>
							申诉时间
						</th>
						<td colspan="2">
							<bean:write name="rs" property="sssj" />
						</td>
					</tr>
					<tr>
						<th>
							改变<bean:message key="lable.xsgzyxpzxy" />处分要求
						</th>
						<td colspan="4" style="word-break:break-all;">
							<bean:write name="rs" property="yq" />
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<td colspan="5">
							<span>学校处理情况</span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />受理情况
						</th>
						<td>
							<bean:write name="rs" property="jd" />
						</td>
						<th>
							原处分类别
						</th>
						<td colspan="2">
							${rs.cflbmc }
						</td>
					</tr>
					<tr>
						<th>
							(解除/更改)文号
						</th>
						<td>
							<bean:write name="rs" property="jdwh" />
						</td>
						<th>
							(解除/更改)时间
						</th>
						<td colspan="2">
							<bean:write name="rs" property="jdsj" />
						</td>
					</tr>
					<tr>
						<th>
							决定结果、&nbsp;&nbsp;&nbsp;
							<br>
							内容或理由
						</th>
						<td colspan="4">
							<bean:write name="rs" property="jdly" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
