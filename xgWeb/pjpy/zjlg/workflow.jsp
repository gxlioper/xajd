<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxjRs.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxjRstg.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
/*
全部选中
*/    
 </script>
</head>

<body>
	<html:form action="/zjlgPjpy" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>流程追踪</a>
			</p>
		</div>
		<div class="formbox">
			<logic:notEmpty name="rs1">
				<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs1" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;">
								<logic:iterate id="v" name="s">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</table>
				</logic:notEmpty >
				<logic:empty name="rs1">
				      <table width="100%" id="rsTable" class="dateline">
				            <tr><td>目前尚无此流程的信息	</td></tr>
                       </table>                                          			
				</logic:empty>
				<div id="tmpdiv"></div>
			</div>
	</html:form>
</body>
</html>
