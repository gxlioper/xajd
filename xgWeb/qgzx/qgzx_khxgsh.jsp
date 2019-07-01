<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body onload="xyDisabled('xy')">
		<input id="userType" name="userType" value="${userType }" type="hidden"/>
		<html:form action="/qgzx_kh_modicheck" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="tips" scope="request" /></a>
				</p>
			</div>
			<div class="searchtab">		
				<table width="100%" class="tbstyle">
					<tbody>
						<tr>
							<th>年级</th>
							<td><html:select property="nj" style="width:90px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" style="width:230px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
							<th>审核情况</th>
							<td><html:select property="sfsh" style="width:230px" styleId="sfsh">
									<html:option value=""></html:option>
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="refreshForm('/xgxt/qgzx_kh_modicheck.do?go=go')">
								查询
							</button>
							 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								重置
							 </button>
							</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
					<table width="100%" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:
    						<logic:iterate id="v" name="s" offset="0" length="1">
    						<bean:write name="v"/>
    						</logic:iterate>
    "  ondblclick="chkPriseOne('/xgxt/khModiCheckOne.do?pkvalue=', 700, 500)">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
			</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
	</body>
</html>
                                                                                                   
