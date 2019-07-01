<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js">
</script>
<script type="text/javascript" >
<!--
function printFun(){
	var requestPath = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint&';
	var bjdm = document.getElementById("bjdm").value;
	requestPath += "bjdm="+bjdm;
	var titName = document.getElementById("titName");
	requestPath += "&titName="+titName;
	var pk = '';
	if (curr_row=='' || curr_row==null) {
		pk='';
	} else {
		pk = curr_row.getElementsByTagName("input")[0].value;
	}
	requestPath+='&pkValue='+pk;
	window.open(requestPath);
}
//-->
</script>
</head>

<body onload="xyDisabled('xy');">
	<html:form action="/pjpyynyswh" method="post">
    	
    	<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:message bundle="pjpyynys" key="pjpy_ynys_xjbjsqqry" /></a>
				</p>
			</div>
			
				<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">	
							<ul>
								<li><a href="#" class="btn_zj" onclick="refreshForm('pjpy_hzy_xjbjandwmbj.do?method=xjbjAndWmbjSq')">增加</a></li>
								<li><a href="#" class="btn_xg" onclick="modiAndDel('ynys_xjbjmodi.do?pkValue=','modi','650','550');">修改</a></li>
								<li><a href="#" class="btn_sc" onclick="deldata('ynys_xjbjdel.do')">删除</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData();">导入数据</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">导出数据</a></li>	
								<logic:equal value="10863" name="xxdm">
									<li><a href="#" class="btn_dy" onclick="window.open('pjpy_nbzy_xjbjprint.do?pkValue=');">打印报表</a></li>
<%--									<li><a href="xlsDown/ynys_xjbjb.xls" target="_blank" class="btn_down" >下载模板</a></li>	--%>
								</logic:equal>
							
								
								
								<logic:notEqual value="10863" name="xxdm">
									<logic:equal value="10690" name="xxdm">
										<li><a href="#" class="btn_dy" onclick="bbPrint('ynys_printxjbj.do?pkValue=')">打印报表</a></li>
									</logic:equal>
									
									<logic:notEqual value="10690" name="xxdm">
										<li><a href="#" class="btn_dy" onclick="printFun()">打印报表</a></li>
									</logic:notEqual>
									
<%--									<logic:notEqual value="11417" name="xxdm">--%>
<%--										<li><a href="xlsDown/pjpy_xjbjb.xls" target="_blank" class="btn_down" >下载模板</a></li>	--%>
<%--									</logic:notEqual>--%>
									
								</logic:notEqual>					
							</ul>
						</div>
						</div>
					</logic:equal>
			
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="titName" name="titName" value="${titName}"/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!-- 批量删除信息提示 -->
    	<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
						<th align="left">
							年级</th>
						<td><html:select property="nj" styleId="nj" onchange="initZyList();initBjList()"
							 styleClass="select" style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select></td>
						<th>学年</th>
						<td><html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="left"><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>专业</th>
							<td><html:select property="zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
							<th>班级</th>
							<td><html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
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
						<button type="button" id="search_go" onclick="refreshForm('ynys_xjbjsqqryres.do');">
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
							<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)">
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;" ondblclick="modiAndDel('ynys_xjbjmodi.do?act=view&pkValue=','modi','650','550');">
										<td align=center><input type="checkbox" id="cbv" name="cbv"
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
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
							</div>
					</logic:notEmpty>
					</div>
					<div id="tmpdiv"></div>
			
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <logic:present name="deleted">
	 	<logic:equal value="yes" name="deleted">
	 		<script>
	 			alert('操作成功！');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="deleted">
	 		<script>
	 			alert('' + document.getElementById('failInfo').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 </logic:present> 
</body>