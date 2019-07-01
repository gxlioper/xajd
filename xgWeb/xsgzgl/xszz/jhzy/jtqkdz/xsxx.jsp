<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
	</head>

	<body>
		<table width="100%" border="0" class="formlist">
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
							<font color="red">*</font>学号
						</th>
						<td align="left" width="30%">
							
							${rs.xh }
						</td>
						<th align="right" width="20%">
							姓名
						</th>
						<td align="left" width="30%">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							性别
						</th>
							<td align="left">
							${rs.xb}
						</td>
						<th align="right">
							年级
						</th>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left">
							${rs.xymc}
						</td>
						<th align="right">
							专业
						</th>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							班级
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
						<th align="right">
							学制
						</th>
							<td align="left">
							${rs.xz}
						</td>
						
					</tr>
					<tr>
						<th align="right">
							身份证号
						</th>
						<td align="left">
							${rs.sfzh}
						</td>
						<th align="right">
							出生日期
						</th>
							<td align="left">
							${rs.csrq}
						</td>
						
					</tr>
					<tr>
						<th align="right">
							民族
						</th>
						<td align="left">
							${rs.mzmc}
						</td>
						<th align="right">
							入学日期
						</th>
							<td align="left">
							${rs.rxrq}
						</td>
						
					</tr>
					<tr>
						<th align="right">
							籍贯
						</th>
						<td align="left">
							${rs.jgmc}
						</td>
						<th align="right">
							政治面貌
						</th>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					<tr>
						<th align="right">
							毕业学校
						</th>
						<td align="left">
							${rs.byxx }
						</td>
						<th align="right">
							手机号码
						</th>
							<td align="left">
							${rs.sjhm }
						</td>
						
					</tr>
					<tr>
						<th align="right">
							特长
						</th>
						<td align="left" colspan="3">
							${rs.tc }
						</td>
						
					</tr>
					</tbody>
					</table>
					
	</body>

	</html>