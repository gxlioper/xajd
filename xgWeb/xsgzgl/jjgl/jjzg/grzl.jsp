<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
  </head>
  
  <body>
  		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/jjgl_jjzg" method="post" styleId="zgsqForm">
			<html:hidden property="id" />
			<html:hidden property="xh" value="${userName }"/>
			<html:hidden property="shzt"/>
		
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr style="height: 45px;">
							<th width="15%">学号</th>
							<td width="25%">${jbxx.xh }</td>
							<th width="15%">姓名</th>
							<td width="25%">${jbxx.xm }</td>
							<td rowspan="3" align="center">
								<img src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh }" width="100" height="120" >
							</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>性别</th>
					    	<td>${jbxx.xb }</td>
					    	<th>年级</th>
					    	<td>${jbxx.nj }</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>学院</th>
					    	<td>${jbxx.xymc }</td>
					    	<th>班级</th>
					    	<td>${jbxx.bjmc }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>家教信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height: 45px;">
					    	<th>擅长科目</th>
					    	<td colspan="4">
					    		${jjzgForm.xkmca } 、${jjzgForm.xkmcb } 、${jjzgForm.xkmcc }
					    	</td>
					    </tr>
						<tr style="height: 45px;">
					    	<th>针对年级</th>
					    	<td>
					    		${jjzgForm.jjnjmc }
					    	</td>
					    	<th>联系电话</th>
					    	<td colspan="2">
					    		${jjzgForm.lxdh }
					    	</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>备注</th>
					    	<td colspan="4">
					    		${jjzgForm.bz }
					    	</td>
					    </tr>
					</tbody>
				</table>
			</div>
		</html:form>
  </body>
</html>
