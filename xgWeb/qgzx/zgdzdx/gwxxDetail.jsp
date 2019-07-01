<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/String.js"></script>
</head>
	<body>
		<html:form action="/qgzxZgdzdx.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>校外勤工助学 - 岗位信息查询 - 岗位详细信息</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>

			<logic:notEmpty name="rs">		
				<div class="tab">
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>岗位名称</th>
						<td>
							<bean:write name="rs" property="gwmc"/>						
						</td>
						<th>单位名称</th>
						<td>
							<bean:write name="rs" property="sqdwmc"/>	
						</td>
					</tr>
					<tr>
						<th>单位地址</th>
						<td colspan="3">
							<bean:write name="rs" property="sqdwdz"/>	
						</td>
					</tr>
					<tr>
						<th>学年</th>
						<td>
							<bean:write name="rs" property="xn"/>	
						</td>
						<th>年度</th>
						<td>
							<bean:write name="rs" property="nd"/>							
						</td>
					</tr>
					<tr>
						<th>学期</th>
						<td>
							<bean:write name="rs" property="xq"/>	
						</td>
						<th>联系电话</th>
						<td>
							<bean:write name="rs" property="lxdh"/>	
						</td>
					</tr>
					<tr>
						<th>工作开始日期</th>
						<td>
							<bean:write name="rs" property="gzkssj"/>	
						</td>
						<th>工作结束日期</th>
						<td>
							<bean:write name="rs" property="gzjssj"/>	
						</td>
					</tr>
					<tr>
						<th>需求人数</th>
						<td><bean:write name="rs" property="xyrs"/></th>
						<th>使用困难生数</th>
						<td>
							<bean:write name="rs" property="xyknsrs"/>	
						</td>
					</tr>
					<tr>
						<th>计酬方式</th>
						<td>
							<bean:write name="rs" property="jcfsmc"/>	
						</td>
						<th>建议报酬标准</th>
						<td>
							<bean:write name="rs" property="jcbz"/>	
						</td>
					</tr>	
					<tr>
						<th><font color="red">审核通过人数</font></th>
						<td>
							<font color="red"><bean:write name="rs" property="shtgrs"/>	</font>
						</td>
						<th><font color="red">未审核人数</font></th>
						<td>
							<font color="red"><bean:write name="rs" property="wshrs"/></font>	
						</td>
					</tr>		
					<tr>
					  <th>工作时间</th>
					  <td colspan="3">
					  	<bean:write name="rs" property="gzsj"/>	
					  </td>
				    </tr>
					<tr>
					  <th>工作内容</th>
					  <td colspan="3">
					  	<bean:write name="rs" property="gznr"/>	
					  </td>
				  	</tr>		
				    <tr>
					  <th>工作要求</th>
					  <td colspan="3">
					  	<bean:write name="rs" property="gzyq"/>	
					  </td>
				    </tr>			
					<tr>
						<th>备注</th>
						<td colspan="3">
							<bean:write name="rs" property="bz"/>	
						</td>
					</tr>
					</tbody>
				    <tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
				            <button type="button" class="button2"
								onclick="Close();return false;"
								style="width:80px" id="buttonSave">
								关 闭
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
				</div>
			</logic:notEmpty>			
		</html:form>
	</body>
</html>
