<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self"/>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<title><bean:message key="lable.title" /></title>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	<html:form action="ntzy_pypx.do" method="post">
	<input type="hidden" name="pkValue" value="${param.pkValue }"/>
	
	<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>优秀团支部单个修改查看</a>
					</p>
	</div>
		
		<div class="tab">
		<table class="formlist" border="0" width="100%">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>申请信息</b>
					</td>
				</tr>
			</thead>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			        <logic:notEqual value="view" name="doType">
						<button type="button" class="button2" onclick="saveDataShowTips('ntzy_pypx.do?method=yxtzbView&doType=save','tzbmc','正在处理数据，请稍候！');">
						保	存
						</button>
					</logic:notEqual>
			            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
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
					<html:text property="save_tzbmc" styleId="tzbmc" value="${rs.tzbmc }" readonly="true"/>
				</td>
				
				<th>
					<div >
						学年：
					</div>
				</th>
				<td>
					<html:text property="save_xn" styleId="xn" value="${rs.xn }" readonly="true"/>
				</td>
				</tr>
				<tr>
				<th>
					<div>
						申请人：
					</div>
				</th>
				<td width="30%" align="left">
					<input type="text" readonly="readonly" value="${rs.sqr }"/>
				</td>
				
				<th>
					<div>
						所在院系团总支：
					</div>
				</th>
				<td colspan="3">
					<html:text property="save_szyxtzz" value="${rs.szyxtzz }"/>
				</td>
			</tr>	
			<tr align="left" style="height:22px">
							<th>
								主要工作：
								<br/>
							<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="3">
								<html:textarea property='save_zygz' style="width:99%" value="${rs.zygz}"
									onblur="checkLen(this,800)" rows='8'/>
							</td>
			</tr>
			</tbody>
		</table>
		</div>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
		
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
