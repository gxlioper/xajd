<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 数据维护 - 处分降级历史信息单个查看</a>
			</p>
		</div>
		
		<html:form action="wjcf_bjqn.do" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue}"/>
		<input type="hidden" name="xh" value="${rs.xh }"/>
		<input type="hidden" name="cfsj" value="${rs.cfsj }"/>
		<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4"><span>学生处分信息</span></th>
					</tr>
				</thead>
			
				<tbody>
					<tr>
						<th width="15%">学号</th>
						<td width="35%">${rs.xh }</td>
						<th width="15%">年度</th>
						<td width="35%">${rs.nd }</td>
					</tr>
					<tr>
						<th>姓名</th>
						<td>${rs.xm }</td>
						<th>学年</th>
						<td>${rs.xn }</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>${rs.xb }</td>
						<th>年级</th>
						<td>${rs.nj }</td>
					</tr>
					<tr>
						<th>政治面貌</th>
						<td>${rs.zzmmmc }</td>
						<th>学期</th>
						<td>${rs.xqmc }</td>
					</tr>
					<tr>
						<th>年级</th>
						<td>${rs.nj }</td>
						<th>处分类别</th>
						<td>${rs.cflbmc }</td>
					</tr>
					<tr>
						<th>系</th>
						<td>${rs.xymc }</td>
						<th>处分事由</th>
						<td>${rs.cfyymc}</td>
					</tr>
					<tr>
						<th>专业名称</th>
						<td>${rs.zymc}</td>
						<th>处分时间</th>
						<td>${rs.cfsj}</td>
					</tr>
					<tr>
						<th>班级名称</th>
						<td>${rs.bjmc }</td>
						<th>处分文号</th>
						<td>${rs.cfwh }</td>
					</tr>
					<tr>
						<th>处分降级时间</th>
						<td>${rs.jjsj }</td>
					</tr>
					
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" name="message" value="${message }"/>
			<script>
				alert($('message').value);
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();	
				}
			</script>
		</logic:present>
	</body>
</html>