<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript">
		function toStuViewMores(url){
	      var xh = document.getElementById("xh").value;
	      var cfwh = curr_row.cells[2].innerText.replace(" ","").replace(" ","");
	      var cfsj = curr_row.cells[5].innerText.replace(" ","");
	      var pkValue = xh+cfwh+cfsj;	     
	      url += pkValue;
	      showTopWin(url, 750,600); 
}

		
		</script>

	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 学生申诉申请 - 查询</a>
			</p>
		</div>


		<html:form action="/data_search" method="post">
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name="userName" scope="session"/>" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<td align="left">
								学号：
								<bean:write name="userName" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								姓名：
								<bean:write name="userNameReal" scope="session" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								违纪处分申诉申请次数：
								<bean:write name="num" scope="request" />
							</td>
						</tr>
					</thead>
				</table>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
						</logic:empty> </span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="toStuViewMores('/xgxt/wjcf_viewmoreinfoanddel.do?pkValue=')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
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

			<logic:equal value="yes" name="writeAble" scope="request">
				<input type="hidden" name="xh" id="xh" value=" " />
			</logic:equal>

		</html:form>
		</body>
</html>
