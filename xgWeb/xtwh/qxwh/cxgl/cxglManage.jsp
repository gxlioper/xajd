<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	
	<body>
		<html:form action="/qxcxManage">
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>系统维护-权限维护-权限相关查询</a>
				</p>
			</div>
			   
			<div>
			 	<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>查询选择</span>
							</th>
						</tr>
					</thead>
					<tbody align="left">
						<tr>
							<td colspan="4" style="padding-left: 300px"><html:radio property="mk" value="yhjscx"/>查询角色拥有的用户</td>
						</tr>
						<tr>
							<td colspan="4" style="padding-left: 300px"><html:radio property="mk" value="gnjscx"/>查询功能下所拥有的角色</td>
						</tr>
						<tr>
							<td colspan="4" style="padding-left: 300px"><html:radio property="mk" value="jsqxcx"/>查询角色拥有的权限</td>
						</tr>
						<tr>
							<td colspan="4" style="padding-left: 300px"><html:radio property="mk" value="yhqxcx"/>查询用户所拥有的功能模块</td>
						</tr>
					</tbody>
					
					<tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
							  <button type="button" name="" onclick="refreshForm('qxcxManage.do?method=cxglManage&doType=view');">下一步</button>
				          </div></td>
				      </tr>
				    </tfoot>
				</table>
			</div>	
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
