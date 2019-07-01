<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">
		function expPageData() {
			document.forms[0].action = "jygl.do?method=tjbSh&doType=expPageData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/jygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" name="tableName" value="${tableName }" />
			<input type="hidden" name="realTable" value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="shzd" id="shzd" value="xysh" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" id="btn_shtg"
									onclick="shformdata('jygl.do?method=tjbSh&shjg=通过&doType=sh');return false;"
									class="btn_shtg"> 通过 </a>
							</li>

							<li>
								<a href="#" id="btn_xg"
									onclick="shformdata('jygl.do?method=tjbSh&shjg=不通过&doType=sh');return false;"
									class="btn_shbtg"> 不通过 </a>
							</li>

						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
									<button class="btn_cx" id="search_go"
										onclick="allNotEmpThenGo('jygl.do?method=tjbSh&doType=query')">
										查 询
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:select property="queryequals_nj" style="width:150px"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								学号
							</th>
							<td>
								<html:text property="querylike_xh" maxlength="20" style="width:150px"></html:text>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="querylike_xm" maxlength="20" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								补办原因
							</th>
							<td>
								<html:select property="queryequals_bbyy" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="yyList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								所需材料
							</th>
							<td>
								<html:select styleId="sxcl" property="queryequals_sxcl" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="clList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								协议书编号
							</th>
							<td>
								<html:text property="querylike_xysbh" maxlength="20" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="queryequals_xydm" 
									onchange="initZyList();initBjList()" style="width:150px"
									styleId="xy" value="${userDep }" onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="queryequals_zydm" onchange="initBjList()"
									style="width:150px" styleId="zy" onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="queryequals_bjdm" style="width:150px"
									styleId="bj" onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					</table>
					
			</div>
			<div class="formbox" id="result">
				
 				<h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
	
				<logic:notEmpty name="rs">
				<table summary="" class="dateline" align="" width="100%">
					<thead>
						<tr align="center" style="cursor:hand">
							<td nowrap>
								<input type="checkbox" name="cb" onclick="selectAll()"
									style="height:17.5px">
							</td>
							<logic:iterate id="tit" name="topTr" offset="1" scope="request">
								<td id="${tit.en}" onclick="tableSort(this)" nowrap>
									${tit.cn}
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								ondblclick="showInfo('jygl.do?method=showTjb','view','800','600');"
								style="cursor:hand;">
								<td align=center>
									<input type="checkbox" id="cbv" name="primarykey_cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									<input type="hidden" value="<bean:write name="v" />" />
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td align=center>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</tbody>
				</table>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>

				</logic:notEmpty>

			</div>
		</html:form>
		
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("操作失败！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
