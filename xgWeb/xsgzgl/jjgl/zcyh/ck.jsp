<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
  </head>
  
  <body>
		<html:form action="/jjgl_zcyhgl" method="post" styleId="jjglZcyhForm">
			<div class='tab' style='width:100%;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>用户基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">家长编号</th>
					    	<td width="30%">${zcyhxxMap.yhm }</td>
					    	<th width="20%">姓名</th>
					    	<td width="30%">${zcyhxxMap.xm }</td>
					    </tr>
					    <tr>
					    	<th>身份证号</th>
					    	<td>${zcyhxxMap.sfzh }</td>
					    	<th>联系电话</th>
					    	<td>${zcyhxxMap.lxdh }</td>
					    </tr>
					    <tr>
					    	<th>家庭住址</th>
					    	<td colspan="3">${zcyhxxMap.jtzz }</td>
					    </tr>
					    <tr>
					    	<th>工作单位</th>
					    	<td colspan="3">${zcyhxxMap.gzdw }</td>
					    </tr>
					    <tr>
					    	<th>登记时间</th>
					    	<td colspan="3">${zcyhxxMap.zcsj }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>子女信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%">
					    			<thead>
										<tr>
											<td>
												姓名
											</td>
											<td>
												性别
											</td>
											<td>
												出生日期
											</td>
											<td>
												年级
											</td>
											<td>
												在读学校
											</td>
										</tr>
									</thead>
					    			<tbody>
					    				<logic:empty name="znxxMapList">
					    					<tr>
					    						<td colspan="5" style="text-align:center;">
					    							暂无!
					    						</td>
					    					</tr>
					    				</logic:empty>
										<logic:notEmpty name="znxxMapList">
											<logic:iterate id="znxx" name="znxxMapList">
											<tr>
					    						<td>${znxx.xm}</td>
					    						<td>${znxx.xb}</td>
					    						<td>${znxx.csrq}</td>
					    						<td>${znxx.nj}</td>
					    						<td>${znxx.zdxx}</td>
					    						</tr>
					    					</logic:iterate>
										</logic:notEmpty>
					    			</tbody>
					    		</table>
							</td>
						</tr>
					 </tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
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
			</div>
		</html:form>
  </body>
</html>
