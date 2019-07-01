<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<title><bean:message key="lable.title" /></title>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self"/>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
</head>
<body>
	
	<html:form action="/ntzy_pypx.do?method=tytgbsq" method="post">
	<!-- 评选项目-->
	<input type="hidden" name="mk" value="${mk }"/>
	<input type="hidden" name="save_mk" value="${mk }"/>
	<input type="hidden" name="save_xh" value="${rs.xh }"/>
	<input type="hidden" name="pkValue" value="${param.pkValue }" />
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>
				<logic:equal value="yxty" name="mk">优秀共青团员单个审核</logic:equal>
				<logic:equal value="yxtgb" name="mk">优秀共青团干部单个审核</logic:equal>
			</a>
			</p>
		</div>
			
		<div class="tab">
		<table class="formlist" border="0" width="100%">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>填写申请表</b>
					</td>
				</tr>
			</thead>
			
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		       	  	<button type="button" class="button2" onclick="saveDataShowTips('ntzy_pypx.do?method=tytgbshone&doType=save','xh');">
						保	存
					</button>
		            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
		          </div></td>
		      </tr>
		    </tfoot>
			
			<tbody>
			<tr>
				<logic:notEqual name="userOnLine" value="student" scope="session">
					<th>
						学号：
					</th>
					<td>
						${rs.xh }
					</td>
				</logic:notEqual>
				
				<th>
					<div >
						学年：
					</div>
				</th>
				<td >
					<input type="hidden" name="save_xn" value="${rs.xn }"/>
					${rs.xn }
				</td>
			</tr>
			<tr>
				<th>
					<div >
						性别：
					</div>
				</th>
				<td width="30%">
					${rs.xb }
				</td>
				<th>
					<div >
						出生年月：
					</div>
				</th>
				<td>
					<input type="hidden" name="save_xn" value="${xn }"/>
					${rs.csrq }
				</td>
			</tr>
			<tr>
				<th>
					<div >
						学历：
					</div>
				</th>
				<td>
					${rs.whcd }
				</td>
				<th>
					<div >
						入团时间：
					</div>
				</th>
				<td>
					${rs.rtrq }
				</td>
			</tr>
			
			<tr>
				<th>
					<div >
						院系：
					</div>
				</th>
				<td>
					${rs.xymc }
				</td>
				<th>
					<div >
						班级：
					</div>
				</th>
				<td>
					${rs.bjmc }
				</td>
			</tr>
			
			<tr>
			<logic:equal value="yxtgb" name="mk">
				<th>
					<div >
						职务：
					</div>
				</th>
				<td>
					${zw }
				</td>
			</logic:equal>
				<logic:equal value="xy" name="userType">
					<th>
						<div >
							<bean:message key="lable.xb" />审核：
						</div>
					</th>
					<td>
							<html:select property="save_xysh" value="${rs.xysh}">
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
					</td>
				</logic:equal>
				
				<logic:equal value="xx" name="userType">
					<th>
						<div >
							学校审核：
						</div>
					</th>
					<td>
							<html:select property="save_xxsh" value="${rs.xxsh}">
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
						
					</td>
				</logic:equal>
			</tr>	
		
			
			<tr align="left" style="height:22px">
							<th>
								个人简历：
								<br />
							<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="3">
								<html:textarea property='save_grjl' style="width:99%"
									onblur="checkLen(this,800)" rows='8' value="${rs.grjl}" readonly="true"/>
							</td>
			</tr>
			<tr align="left" style="height:22px">
							<th>
								获奖情况：
								<br />
								<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="3">
								<html:textarea property='save_hjqk' style="width:99%"
									rows='8' onblur="checkLen(this,800)" value="${rs.hjqk}" readonly="true"/>
							</td>
						</tr>
						
			<tr align="left" style="height:22px">
							<th>
								事迹说明：
								<br />
								<font color="red">(限制在1000字内)</font>
							</th>
							<td colspan="3">
								<html:textarea property='save_sqsm' style="width:99%"
									rows='8' onblur="checkLen(this,800)" value="${rs.sqsm}" readonly="true"/>
							</td>
						</tr>
					</tbody>
		</table>		
		</div>
			<div class="buttontool" id="btn" style="position: absolute;width:93%">
				
			</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
		</script>
	</logic:present>
</body>
</html>
