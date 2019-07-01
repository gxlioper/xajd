<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
				<script type="text/javascript" src="xsgzgl/gygl/gypynew/yxxs/js/yxxs.js"></script>
				<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script>
		</script>
	</head>
	<body>
		<html:form action="/gypy" method="post" styleId="form">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>查看优秀学生</a>
			</p>
		</div>
		<html:hidden property="pylx" value="3"/>
		<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx"/>	
		<div style='tab'>
			<table class="formlist"  width="95%">
			<tbody>
				<tr>
					<th width="20%" align="right">学号：</th>
					<td width="30%">
					${data.xh}
					</td>
					<th width="20%">姓名：</th>
					<td width="30%">
						${data.xm}
					</td>
				</tr>
				<tr>
					<th>性别：</th>
					<td>
						${data.xb}
					</td>
					<th><bean:message key="lable.xb" />：</th>
					<td>
						${data.xymc}
					</td>
				</tr>
				<tr>
					<th>班级：</th>
					<td>
						${data.bjmc}
					</td>
					<th>专业：</th>
					<td>
						${data.zymc}
					</td>
				</tr>
				<tr>
					<th>楼栋：</th>
					<td>
						${data.ldmc}
					</td>
					<th>寝室号：</th>
					<td>
						${data.qsh}
					</td>
				</tr>
				<tr>
					<th>学年：</th>
					<td>
						${data.xn}
					</td>
					<th>学期:</th>
					<td>
						${data.xqmc}
					</td>
				</tr>
				<tr>
					<th>评选理由：</th>
					<td colspan="3">
						${data.pyly}
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			   </tfoot>
			</table>
		</div>
		</html:form>
	</body>

</html>