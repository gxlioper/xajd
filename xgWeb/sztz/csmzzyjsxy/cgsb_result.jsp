<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/sztz_applyResult" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="view_csmz_cgsbxx" />
			<input type="hidden" id="act" name="act"
				value="cgsb" />
		
			<input type="hidden" id="userType" name="userType"
				value="stu" />
			<input type="hidden" id="realTable" name="realTable"
				value="csmz_tzcgb" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<div class="searchtab">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							学号：
							<bean:write name="userName" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							姓名：
							<bean:write name="userNameReal" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							拓展活动参加申报次数：
							<bean:write name="num" scope="request" />
						</td>
					</tr>
				</thead>
			</table>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
			 		 		<font color="blue">提示：单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
						<div class="tab">
							<table width="100%"  border="0" class="formlist">
							<thead>
								<tr align="center" style="cursor:hand">
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
									>
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
<%--					<div id="toolTipLayer"--%>
<%--						style="position:absolute; visibility: hidden" /></div>--%>
<%--					<logic:equal value="yes" name="writeAble" scope="request">--%>
<%--					<input type="hidden" name="xh" id="xh" value=" ">--%>
<%--						<div id="button">--%>
<%--							<button class="button2" onclick="viewMore('add')"--%>
<%--								style="width:80px">--%>
<%--								增 加--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<button class="button2" onclick="viewMore('modi');document.forms[0].xh.value=curr_row.cells[3].value"--%>
<%--								style="width:80px">--%>
<%--								修 改--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<button class="button2" onclick="viewMore('del')"--%>
<%--								style="width:80px">--%>
<%--								删 除--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<button class="button2"--%>
<%--								onclick="impAndChkData();"--%>
<%--								style="width:80px">--%>
<%--								导入数据--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<button class="button2" onclick="dataExport()" style="width:80px">--%>
<%--								导出数据--%>
<%--							</button>--%>
<%--						</div>--%>
<%--					</logic:equal>--%>
				</div>
			</div>
		</html:form>
		<logic:notEmpty name="result" >
			<logic:equal name="result" value="true">
				<script >
					alert("操作成功！");
				</script>	
			</logic:equal>
			<logic:notEqual name="result" value="true">
				<script >
					alert("操作失败！");
				</script>
			</logic:notEqual>
		</logic:notEmpty>
	</body>
</html>
