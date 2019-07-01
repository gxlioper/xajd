<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function modi(){
			if($("pjyj").value==""){
				alert("评价意见不能为空!");
				return false;
			}
			
			if($("fcmy").value=="" && $("bjmy").value=="" && $("my").value=="" && $("bmy").value=="" ){
				alert("满意程度不能为空!");
				return false;
			}
			refreshForm("/xgxt/zjxyRcsw.do?method=swffOnePjManage&doType=pj");
		}
		
		function checkRadio(){
			if($("radioValue").value==""){
				$("fcmy").checked=true;
			}
		}
	</script>
	</head>
	<body>

		<html:form action="/zjxyRcsw" method="post">
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name="xxdm" />" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
						<thead>
							<tr align="center" style="cursor:hand">
								
								<logic:iterate id="tit" name="topTr"  >
									<td id="<bean:write name="tit" property="en"/>">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">

								<logic:iterate id="v" name="s">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</tbody>
					
				</table>
			</div>
			
		</html:form>
	</body>
</html>
