<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		</script>
	</head>
	<body>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>个人简历详细信息</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" id="grjl" class="formlist">
				<thead>
					<tr>
						<th colspan="5"><span>个人资料</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							<th width="15%">身份证号</th>
							<td width="30%"><html:text name="rs" property="id" readonly="true"/>
								<html:checkbox name="rs" property="idsee" value="no" />
								(保密) &nbsp;&nbsp; 
							</td>
						</logic:notEqual>
						<th width="15%">发布时间</th>
						<td width="25%"><html:text name="rs" property="fbsj" style="width:130px" readonly="true" /></td>
						<td rowspan="5" align="center" width="15%">
							照片
						</td>
					</tr>
					<tr>
						<th>所属<bean:message key="lable.xsgzyxpzxy" /></th>
						<td><html:text name="rs" property="xymc" readonly="true" />
						</td>
						<th>学号</th>
						<td><html:text name="rs" property="xsxh" readonly="true"/></td>
					</tr>
					<tr>
						<th>
							姓名
						</th>
						<td>
							<bean:write name="rs" property="name" />
						</td>
						<th>
							性别
						</th>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
					</tr>
					<tr>
						<th>
							出生年月
						</th>
						<td>
							<bean:write name="rs" property="csny" />
						</td>
						<th>
							民族
						</th>
						<td>
							<bean:write name="rs" property="mz"/>
						</td>
					</tr>
					<tr>
						<th>
							学历
						</th>
						<td>
							<bean:write name="rs" property="xl" />
						</td>
						<th>
							政治面貌
						</th>
						<td>
							<bean:write name="rs" property="zzmm" />
						</td>
					</tr>
					<tr>
						<th>
							专业名称
						</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<th>
							辅修专业
						</th>
						<td colspan="2">
							<bean:write name="rs" property="fxzymc" />
						</td>
					</tr>
				</tbody>
				<thead>
					<tr><th colspan="5"><span>联系方式</span></th></tr>
				</thead>	
				<tbody>
					<tr>
						<th>
							联系地址
						</th>
						<td>
							<bean:write name="rs" property="lxdz" />
						</td>
						<th>
							联系电话
						</th>
						<td colspan="2">
							<bean:write name="rs" property="lxdh" />
						</td>
					</tr>
					<tr>
						<th>
							邮政编码
						</th>
						<td>
							<bean:write name="rs" property="yzbm" />
						</td>
						<th>
							电子邮箱
						</th>
						<td colspan="2">
							<bean:write name="rs" property="email" />
						</td>
					</tr>
					<tr>
						<th>
							生源地区
						</th>
						<td>
							<bean:write name="rs" property="sydq" />
						</td>
						<th></th>
						<td colspan="2"></td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="5"><span>学生综合情况</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							获奖情况
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="hjqk" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							学习情况
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="xxqk" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							校级以上奖励
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="xjysjl" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							社会实践情况
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="shsjqk" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							工作经历
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="gzjl" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							个人特长
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="grtc" rows="4"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							自我推荐
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="zwtj" rows="6"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							学校推荐
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="xxtj" rows="8"
								style="width: 95%;word-break:break-all;" readonly="true" />
						</td>
					</tr>
					</tbody>
					<thead>
						<tr><th colspan="5"><span>学校审核</span></th></tr>
					</thead>
					<tbody>
					<tr>
						<th>
							学校审核
						</th>
						<td>
							<html:text name="rs" property="xxsh" readonly="true"/>
						</td>
						<th>
							审核人
						</th>
						<td colspan="2">
							<html:text name="rs" property="shperson" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							审核时间
						</th>
						<td>
							<html:text name="rs" property="shsj" readonly="true"/>
						</td>
						<th>
							不通过原因及修改意见
						</th>
						<td colspan="2">
							<html:textarea name="rs" property="btgyy" rows="4" readonly="true" style="width: 95%;word-break:break-all;"/>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
