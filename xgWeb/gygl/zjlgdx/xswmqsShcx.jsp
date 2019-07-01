<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/js/function.js"></script>
		<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	</head>
	
	<body>
		<center>
			<html:form action="/zjlg_gygl" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>公寓管理 - 文明寝室管理 - 审核结果查询 - 文明寝室审核结果查询</a>
					</p>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
			 		 		显示记录数：${rsNum }
								&nbsp;
								<font color="blue"> 
									提示：单击表头可以排序;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
						<div class="con_overlfow">
						    <table summary="" id="rsTable" class="dateline" width="100%">
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
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									>
									<td align="center">
										<input type="hidden" name="pkV"
											value="<bean:write name="s" property="pk"/>">
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<bean:write name="s" property="xqmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="ssbh" />
									</td>
									<td align="center">
										<bean:write name="s" property="ldmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="lc" />
									</td>
									<td align="center">
										<bean:write name="s" property="qsh" />
									</td>
									<td align="center">
										<bean:write name="s" property="lxdh" />
									</td>
									<td align="center">
										<bean:write name="s" property="sqsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xysh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xxsh" />
									</td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</fieldset>
					<br />
					<br />
				</logic:notEmpty>
				</div>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
</html>
