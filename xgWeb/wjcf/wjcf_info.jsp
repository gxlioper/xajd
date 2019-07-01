<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/wjcfFuction.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/interface/getWjcfInfo.js"></script>
	</head>
	<body onload="xyDisabled('xy')">
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a><bean:write name="tips" scope="request" /></a>
			</p>
		</div>
		
		<html:form action="/wjcf_info" method="post">
			<input type="hidden" id="userType" name="userType"
				value="" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="isCSMZ" name="isCSMZ" value="${isCSMZ }"/>
				<input type="hidden" id="isJGSDX" value="${param1 }"/>
				<input type="hidden" id="xxdm" value="${xxdm }"/>
				<input type="hidden" id="tabType" value="${tabType }" />
            <input type="hidden" id="url" name="url" value="/wjcf_info.do" />
            
            
            <div class="formbox">
					<h3 class="datetitle_01">
						<span> 违纪处分信息&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> </span>
					</h3>

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
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="sendWjcfInfo();">
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
			</div>
            <div id="tmpdiv"></div>
		</html:form>
	</body>
</html>
