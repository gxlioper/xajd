<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body onload="xyDisabled('xy');initPage()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/party_allStatistic.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="showTopWin('classDuty_config_updata.do?act=bjgbsz',600,450)" class="btn_zj"> 增加 </a> </li>
			    <li> <a href="#" onclick="sxjyReportupdata('bjgbsz')" class="btn_xg"> 修改 </a> </li>
				<li> <a href="#" onclick="sxjyReportDel('bjgbsz')" class="btn_sc"> 删除 </a> </li>
			    </ul>
			 </div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	学生干部设置&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未设置任何职务！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">	
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font><br>
						<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;注：请不要随意删除职务，删掉职务同时各<bean:message key="lable.xsgzyxpzxy" />设定的该职务内容也会被删除</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">
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
							<tr onclick="rowOnClick(this)" ondblclick="sxjyReportupdata('bjgbsz')" style="cursor:hand">
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
			</div>
		</html:form>
	</body>
</html>

