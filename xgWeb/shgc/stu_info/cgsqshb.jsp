<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
</head>
	<body onload="checkWinType();">		
		<html:form action="/data_search" method="post">
		<input type="hidden" name="realTable" id="realTable" value="xscgsqsh"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 学生出国退学审核　-　单个审核</a>
			</p>
		</div>
		<div class="tab">	
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>单个审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>学号</th>
					<td>
						<bean:write name="rs" property="xh" />
						<input type="hidden" value="<bean:write name="rs" property="xh" />" name="xh" id="xh"/>
					</td>
					<th>年度</th>
					<td>
						<bean:write name="rs" property="nd" />
						<input type="hidden" value="<bean:write name="rs" property="nd" />" name="nd" id="nd"/>
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
					<th>政治面貌</th>
					<td>
						<bean:write name="rs" property="zzmm" />
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<th>家庭住址</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<th>联系电话</th>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<th>申请时间</th>
					<td>
						<bean:write name="rs" property="sqrq"/>
					</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>去何国家（学校）</th>
					<td>
						<bean:write name="rs" property="gj"/>
					</td>
				</tr>
				<tr>
					<th>班级</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>					
					<th>出国原因</th>
					<td>
						<bean:write name="rs" property="cgyy" />
					</td>	
				</tr>
				<tr>
					<th>经济担保情况</th>
					<td>
						<bean:write name="rs" property="jjdbqk" />
					</td>
					<th>审核</th>
					<td>
						<logic:equal value="xx" name="userType" scope="request">
						<html:select name="rs" styleId="yesNo" property="xxsh">
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
						</logic:equal>
						<logic:equal value="xy" name="userType" scope="request">
						<html:select name="rs" styleId="yesNo" property="xysh">
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" />意见</th>
					<td colspan="3">
						<logic:equal value="xy" name="userType" scope="request">
						<html:textarea property="xyyj" name="rs" style="height:80;width:100%"/>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="request">						
						<html:textarea property="xyyj" name="rs" style="height:80;width:100%;" readonly="true"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>学生处意见</th>
					<td colspan="3">
						<logic:equal value="xx" name="userType">
						<html:textarea name="rs" property="xscyj" style="height:80;width:100%"/>
						</logic:equal>
						<logic:notEqual value="xx" name="userType">
						<html:textarea name="rs" property="xscyj" style="height:80;width:100%" readonly="true"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>校领导意见</th>
					<td colspan="3">
						<logic:equal value="xx" name="userType" scope="request">
						<html:textarea property="xxyj" name="rs" style="height:80;width:100%"/>
						</logic:equal>
						<logic:notEqual value="xx" name="userType" scope="request">						
						<html:textarea property="xxyj" name="rs" style="height:80;width:100%;" readonly="true"/>
						</logic:notEqual>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			           <button
							onclick="viewAdd('/xgxt/abroad_query.do?act=cgsq_sh&doType=save','add');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						<button onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>				
			</table>
		</div>
		</html:form>

		<logic:equal value="true" name="result">
			<script>
				alert("操作成功");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
