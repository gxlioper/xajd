<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	</head>
	
	<body onload="">
	<html:form action="/zjlgPjpy" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName" value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable" value="${realTable }" />
		<input type="hidden" id="isFdy" name="isFdy" value="${isFdy }"/>
		<input type="hidden" id="userName" name="userName" value="${userName }"/>
		<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 信息维护 - 智育成绩维护</a>
			</p>
		</div>
		<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_dc" onclick="wjcfDataExport('zjlgPjpy.do?method=zycpfwh&act=export')">导出</a></li>								
				</ul>
			</div>
			</div>
		</logic:equal>		
			<div class="searchtab">	
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th>学年</th>
							<td><html:select property="querylike_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select></td>
							<th>年级</th>
							<td><html:select property="queryequals_nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select></td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>学号</th>
							<td><html:text property="querylike_xh" style="width:100px" styleId="xh" maxlength="20"></html:text></td>
							<th>姓名</th>
							<td><html:text property="querylike_xm" styleId="xm" style="width:80px" maxlength="20"></html:text>
							</td>
							<th></th><td></td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<logic:equal name="userType" value="xy">
								<td>
									<html:hidden property="queryequals_xydm"/>
									<html:select property="queryequals_xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList()" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</logic:equal>
							<logic:notEqual name="userType" value="xy">
								<td>
									<html:select property="queryequals_xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</logic:notEqual>
							
							<th>专业</th>
							<td><html:select property="queryequals_zydm" styleId="zy" style="width:150px"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
							<th>班级</th>
							<td><html:select property="queryequals_bjdm" styleId="bj" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="refreshForm('zjlgPjpy.do?method=zycpfwh&act=qry');">
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
							记录数： ${rsNum } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：单击表头可以排序;</font>
							<font color="red">请先在参数设置 - 评价方式功能中设置年级评价方式;</font>
						</span>
					</h3>
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
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;">
								<logic:iterate id="v" name="s">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<!--分页显示-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgPjpyForm"></jsp:include>
					<!--分页显示-->
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
			</logic:notEmpty>
		</div>
	</html:form>
</body>
</html>
