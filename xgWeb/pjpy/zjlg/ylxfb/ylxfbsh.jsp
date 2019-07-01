<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	<script language="javascript">	
		function exportHzdata() {
			if ($('xn'.value == '' || $('xy').value=='')) {
				alert("请选择要汇总的学年和<bean:message key="lable.xsgzyxpzxy" />!");
				return false;
			} else {
				if (confirm('确认要根据所选择的学年和<bean:message key="lable.xsgzyxpzxy" />进行优良学风班级评比汇总吗?')) {
					wjcfDataExport('pjpy_zjlg_ylxfbhz.do?xymc=' + $('xy').options[$('xy').selectedIndex].text);
				}
			}
		}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjlgPjpyylxfb" method="post">
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="userName" name="userName" value="${userName }"/>
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="message" name="message" value="${message }" />
			<input type="hidden" id="pt" name="pt" value="${message }" />
		
			<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 审核 - 优良学风班级审核</a>
			</p>
			</div>
			<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_shtg" onclick="shformdata('pjpy_zjlg_ylxfbsh.do?act=sh&jg=tg')">审核通过</a></li>
							<li><a href="#" class="btn_shbtg" onclick="shformdata('pjpy_zjlg_ylxfbsh.do?act=sh&jg=btg')">审核不通过</a></li>
							<li><a href="#" class="btn_sh" onclick="modiAndDel('pjpy_zjlg_ylxfbdgsh.do?pkValue=','modi',800,600)">单个审核</a></li>
							<li><a href="#" class="btn_tj" onclick="exportHzdata()">优良学风班级评比汇总</a></li>
						</ul>
					</div>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" class="tbstyle">
						<tbody>
							<tr>
								<th>学年</th>
								<td><html:select property="queryequals_xn" style="width:100px" disabled="true" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select></td>
								<th>年级</th>
								<td><html:select property="queryequals_nj" style="width:80px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select></td>	
								<th>审核</th>
								<td><logic:equal value="xy" name="userType">
										<html:select property="queryequals_xysh" styleId="sh">
											<html:option value=""></html:option>
											<html:option value="未审核">未审核</html:option>
											<html:option value="通过">通过</html:option>
											<html:option value="不通过">不通过</html:option>
										</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<html:select property="queryequals_xxsh" styleId="sh">
											<html:option value=""></html:option>
											<html:option value="未审核">未审核</html:option>
											<html:option value="通过">通过</html:option>
											<html:option value="不通过">不通过</html:option>
										</html:select>
										<input type="hidden" name="queryequals_xysh" value="通过"/>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><logic:equal value="xy" name="userType">
										<input type="hidden" name="queryequals_xydm" value="${userDep }"/>
										<html:select property="xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<html:select property="queryequals_xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:notEqual>
									</td>
									<th>专业</th>
									<td><html:select property="queryequals_zydm" styleId="zy" style="width:150px"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									</td>
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
								<button type="button" id="search_go" onclick="allNotEmpThenGo('pjpy_zjlg_ylxfbsh.do?act=qry')">
									查 询
								</button>
								 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									重 置
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
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px"/>
										</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
		
								<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpy_zjlg_ylxfbdgsh.do?act=view&pkValue=','modi',760,600)">
								<td align="center">
									<input type="checkbox" id="cbv" name="primarykey_cbv" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
							</logic:iterate>
						</table>
						<!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=ylxfbActionForm"></jsp:include>
						<!--分页显示-->
				</logic:notEmpty>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:notEmpty name="result">
			<script>
				alert("" + $('message').value);
				document.getElementById('search_go').onclick();
			</script>
		</logic:notEmpty>
	</body>
