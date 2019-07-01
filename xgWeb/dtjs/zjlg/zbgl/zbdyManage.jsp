<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">	
	function getZbList(){
		var xydm = $("xydm").value;
		dwr.engine.setAsync(false);
		zjlgZbglDAO.getZbWssList(xydm,function(data){
			if (data != null && typeof data == 'object') {
				var objId = "zbdm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"zbdm","zbmc");			
				}
				$(objId).options[0].value = "";
			}
		});
		dwr.engine.setAsync(true);
	}
	
	function showDyInfo(){
	
		var lx = curr_row.cells[9].innerText;
		var pk = curr_row.getElementsByTagName('input')[0].value;
		var pkValue= pk.split("!!@@!!");
		var url = "";

		if( lx == "党员 "){
			pk = pkValue[1];
			url ="/xgxt/zjlgDtjsZsdy.do?method=zsdyUpdate&doType=view&pk="+pk;
		}else if( lx == "预备党员 "){
			pk = pkValue[1];
			url ="/xgxt/zjlgDtjsYbdy.do?method=ybdyUpdate&doType=view&pk="+pk;
		}
		showTopWin(url,800,600);
	}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjlgDtjsZbgl" method="post">

			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_sc" onclick="sumitInfo('/xgxt/zjlgDtjsZbgl.do?method=zbdyManage','del')" >删除</a></li>
							<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>								
						</ul>
					</div>
					</div>
				</logic:equal>
				<div class="searchtab">
						<table width="100%" class="" border="0">
							<tbody>
								<tr>
									<th>年级</th>
									<td><html:select property="nj" style="" onchange="setDzbZyList();setDzbBjList();">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select></td>
									<th>学号</th>
									<td><html:text property="xh" style="width:85px" maxlength="20"></html:text></td>
									<th>姓名</th>
									<td><html:text property="xm" style="width:85px" maxlength="20"></html:text></td>
								</tr>
								<tr>
									<th><bean:message key="lable.xsgzyxpzxy" /></th>
									<td><html:select property="xydm" style="width: 150px" styleId="xy"
											onchange="setDzbZyList();setDzbBjList();getZbList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select></td>
									<th>专业</th>
									<td><html:select property="zydm" style="width: 150px" styleId="zy"
											onchange="setDzbBjList();">
											<logic:equal name="isZbfzr" value="false">
												<html:option value=""></html:option>
											</logic:equal>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select></td>
									<th>班级</th>
									<td><html:select property="bjdm" style="width: 150px" styleId="bj">
											<logic:equal name="isZbfzr" value="false">
												<html:option value=""></html:option>
											</logic:equal>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>所在支部</th>
									<td><html:select property="zbdm" onchange=""
										styleClass="select" style="" styleId="zbdm">
											<html:options collection="zbList" property="zbdm"
												labelProperty="zbmc" />
											<html:option value="无所属">无所属</html:option>
										</html:select></td>
									<th>类型</th>
									<td><html:select property="lx" onchange="" styleClass="select" style="" styleId="lx">
											<html:option value="">-----请选择-----</html:option>
											<html:options collection="dylxList" property="en" labelProperty="cn" />	
										</html:select></td>
									<th></th><td></td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="6">
									<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/zjlgDtjsZbgl.do?method=zbdyManage');">
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
							<table width="100%" id="rsTable" class="dateline">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()"/>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" ondblclick="showDyInfo()" style="cursor:hand">
										<td align="center">
											<input type="checkbox" id="checkVal" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v" />" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						<!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgDtjsForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
			</logic:empty>
			<div id="tmpdiv1"></div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
