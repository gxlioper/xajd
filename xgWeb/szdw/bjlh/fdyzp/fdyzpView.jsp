<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>	
		<script type="text/javascript">	
		</script>
	</head>
	<body>
		<html:form action="/bjlh_fdyzp" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>辅导员自评信息查看</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tr>
						<td align="left" colspan="4">
						学年：<input type="text" value="${rs.xn }" readonly="true"/>
						所在部门：<input type="text" value="${rs.bmmc }" readonly="true"/>
						辅导员姓名：<input type="text" value="${rs.xm }" readonly="true"/>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th>
								考核项目
							</th>
							<th>
								项目类型
							</th>
							<th>
								完成情况
							</th>
							<th>
								备注
							</th>
						</tr>
					</thead>
					<tbody id="sljxTab">
						<logic:present name="xmList">
							<logic:iterate id="j" name="xmList">
								<tr>
									<td>
										${j.khxm}
									</td>
									<td>
										${j.xmlx}
									</td>
									<td>
										${j.wcqk}
									</td>
									<td>
										${j.bz}
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="message">
				<script defer>
					alertInfo('${message}',function(){
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					});
				</script>
			</logic:present>
			<logic:present name="error">
				<script defer>
					alert('${error}');
				</script>
			</logic:present>
			
		</html:form>
		
	</body>
</html>
