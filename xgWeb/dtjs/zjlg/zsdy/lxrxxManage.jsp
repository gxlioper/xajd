<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script language="javascript">
	function sendDy(){
		var blxr = $("blxr").value;
		var url = "/xgxt"+window.dialogArguments.document.forms[0].url.value;
		var doType = window.dialogArguments.document.forms[0].doType.value;
		url+="&blxr="+blxr;
		url+="&lxr="+curr_row.getElementsByTagName('input')[0].value;
		url=url.replace("add",doType);
		window.dialogArguments.document.forms[0].action = url;
		window.dialogArguments.document.forms[0].submit();
		window.close();
	}
	
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
	</script>
	</head>
	<body>
		<center>
			<html:form action="/zjlgDtjsZsdy" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm" scope="session"/>" />	
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="blxr" id="blxr" value="${blxr }" />
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>学生信息 - 信息查询 - 党员(预备)信息</a>
					</p>
				</div>
				
				<div class="searchtab">		
					<table width="100%" class="tbstyle">
						<tbody>
							<tr>
								<th>年级</th>
								<td><html:select property="nj" 
										onchange="setDzbZyList();setDzbBjList();" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select></td>
								<th>学号</th>
								<td><html:text property="xh" /></td>
								<th>姓名</th>
								<td><html:text property="xm"/></td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:select property="xydm" onchange="setDzbZyList();setDzbBjList();getZbList();" styleId="xy" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
									</html:select></td>
								<th>专业</th>
								<td><html:select property="zydm"  styleId="zy" style="width:150px"
										onchange="setDzbBjList();">
										<logic:equal name="isZbfzr" value="false">
										<html:option value=""></html:option>
										</logic:equal>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>班级</th>
								<td><html:select property="bjdm"  styleId="bj" style="width: 150px">
										<logic:notEqual value="yes" name="isBzr">
										<html:option value=""></html:option>
										</logic:notEqual>
										<logic:equal name="isZbfzr" value="false">
											<html:option value=""></html:option>
										</logic:equal>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select></td>
									
							</tr>
							<tr>
								<th>政治面貌</th>
								<td><html:select property="zzmm"  styleId="zzmm">
										<html:option value=""></html:option>
										<html:option value="党员">党员</html:option>
										<html:option value="预备党员">预备党员</html:option>
									</html:select></td>
								<th>所在支部</th>
								<td><html:select property="zbdm" onchange="" styleClass="select" style="" styleId="zbdm">
											<html:options collection="zbList" property="zbdm"
												labelProperty="zbmc" />
											<html:option value="无所属">无所属</html:option>
									</html:select>
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" id="go" name="go" value="a" />
								 <button type="button" id="search_go" onclick="document.forms[0].go.value='go';refreshForm('/xgxt/zjlgDtjsZsdy.do?method=lxrxxManage');this.disabled=true;">
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
									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="sendDy()">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center">
											<bean:write name="v" />									
											<input type="hidden" value="<bean:write name="v" />" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						<!--分页显示-->
				    	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgDtjsForm"></jsp:include>
						<!--分页显示-->
						
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					</logic:notEmpty>
				</div>
			</html:form>
		</center>
	</body>
</html>
