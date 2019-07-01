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
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	<html:form action="ntzy_pypx.do" method="post">
	<input type="hidden" name="pkValue" value="${param.pkValue }"/>

		<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>优秀团支部单个审核</a>
					</p>
		</div>
		
		<div class="tab">
		<table class="formlist" border="0" width="100%">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>单个审核信息</b>
					</td>
				</tr>
			</thead>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:notEqual value="view" name="doType">
						<button type="button" class="button2" onclick="saveDataShowTips('ntzy_pypx.do?method=yxtzbshone&doType=save','tzbmc','正在处理数据，请稍候！');">
							保	存
						</button>
					  </logic:notEqual>
			          	<button type="button" class="button2" onclick="window.close();return false;">
							关	闭
						</button>
			          </div></td>
			      </tr>
			   </tfoot>
			
			<tbody>
			<tr>
				<th>
					<div>
						<font color="red">*</font>团支部名称：
					</div>
				</th>
				<td width="30%" align="left">
					<html:hidden property="save_tzbmc" styleId="tzbmc" value="${rs.tzbmc }"/>
					${rs.tzbmc }
				</td>
				
				<th>
					<div >
						学年：
					</div>
				</th>
				<td>
					<html:hidden property="save_xn" styleId="xn" value="${rs.xn }"/>
					${rs.xn }
					</td>
				</tr>
				<tr>
				<th>
					<div>
						申请人：
					</div>
				</th>
				<td width="30%" align="left">
					${rs.sqr }
				</td>
				
				<th>
					<div>
						所在院系团总支：
					</div>
				</th>
				<td colspan="3">
					${rs.szyxtzz }
				</td>
			</tr>
			<tr>
				<logic:equal value="xy" name="userType">
					<th>
						<div >
							<bean:message key="lable.xb" />审核：
						</div>
					</th>
					<td width="30%">
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
					<td width="30%">
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
								主要工作：
								<br/>
							<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_zygz' style="width:99%" value="${rs.zygz}" readonly="true"
									onblur="checkLen(this,800)" rows='8'/>
							</td>
			</tr>
			</tbody>
		</table>
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
