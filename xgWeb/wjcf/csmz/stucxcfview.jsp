<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript">
			function cxcfopen(url){
				showTopWin(url+=curr_row.getElementsByTagName("input")[0].value,'580','480' );
			}
		</script>
	</head>
	<body>
		<html:form action="/wjcfjgsdxwh" method="post">
		    <input type="hidden" id="xh" name="xh" 
				   value="<bean:write name="userName" scope="session"/>" />
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>违纪处分 - 解除处分管理 - 解除结果</a>
				</p>
			</div>
			
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>&nbsp;&nbsp; 
						学号：
							${userName }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							姓名：
							<bean:write name="userNameReal" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							违纪处分撤消申请次数：
							 ${num}
					</span>
				</h3>
				<logic:notEmpty name="rs">
					<div class="con_overlfow" >
						<table summary="" class="dateline" align="" width="100%">
						<thead>
								<tr align="center" style="cursor:hand">
						
									<logic:iterate id="tit" name="topTr" offset="1">
									
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap >
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" 
									<logic:equal value="10827" name="xxdm">ondblclick="cxcfopen('stucxcfviews.do?pkValue=')"</logic:equal>>
								
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										
									
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap >
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
						</table>
					</div>
				</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>
