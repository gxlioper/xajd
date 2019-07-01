<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			
		<script type="text/javascript">
			function sendInfo(){
				if(window.dialogArguments.document.getElementById('bjmc')){
					window.dialogArguments.document.getElementById('xymc').value = curr_row.getElementsByTagName('input')[6].value;
					window.dialogArguments.document.getElementById('xydm').value = curr_row.getElementsByTagName('input')[5].value;
					window.dialogArguments.document.getElementById('zymc').value = curr_row.getElementsByTagName('input')[4].value;
					window.dialogArguments.document.getElementById('zydm').value = curr_row.getElementsByTagName('input')[3].value;
					window.dialogArguments.document.getElementById('bjmc').value = curr_row.getElementsByTagName('input')[2].value;
					window.dialogArguments.document.getElementById('bjdm').value = curr_row.getElementsByTagName('input')[1].value;
					window.dialogArguments.document.getElementById('nj').value = curr_row.getElementsByTagName('input')[0].value;
					Close();
				}
			}

			function validate(){
				if(window.dialogArguments.document.getElementById('nj')){
					var nj = window.dialogArguments.document.getElementById('nj').value;
					var xydm = window.dialogArguments.document.getElementById('xydm').value;
					var zydm = window.dialogArguments.document.getElementById('zydm').value;
					
<%--					if(nj != ""){--%>
<%--						$('nj').disabled = "disabled";--%>
<%--						var tmp = document.createElement("input");--%>
<%--						tmp.type = "hidden";--%>
<%--						tmp.name = "nj";--%>
<%--						tmp.value = nj;--%>
<%--						document.forms[0].appendChild(tmp);--%>
<%--					}--%>
<%----%>
<%--					if(xydm != ""){--%>
<%--						$('xymc').disabled = "disabled";--%>
<%--						var tmp = document.createElement("input");--%>
<%--						tmp.type = "hidden";--%>
<%--						tmp.name = "xydm";--%>
<%--						tmp.value = xydm;--%>
<%--						document.forms[0].appendChild(tmp);--%>
<%--					}--%>
<%----%>
<%--					if(zydm != ""){--%>
<%--						$('zymc').disabled = "disabled";--%>
<%--						var tmp = document.createElement("input");--%>
<%--						tmp.type = "hidden";--%>
<%--						tmp.name = "zydm";--%>
<%--						tmp.value = zydm;--%>
<%--						document.forms[0].appendChild(tmp);--%>
<%--					}--%>
				}
			}
		</script>
	</head>
	<body>
		<center>
			<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>班级选择</a>
				</p>
			</div>
			<html:form action="/commXgInfo.do" method="post">
				
				<div class="searchtab">
					<table width="90%" class="" border="0">
						<tbody>
							<tr>
								<th>年级</th>
								<td>
									<html:select property="nj" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select>
								</td>
								<th><bean:message key="lable.xb" />名称</th>
								<td>
								<html:text property="xymc" value="${xymc }"/>
								</td>
								<th>专业名称</th>
								<td>
								<html:text property="zymc" value="${zymc }"/>
								</td>
							</tr>
							<tr>
								<th>班级代码</th>
								<td>
									<html:text property="bjdm" styleId="bjdm"></html:text>
								</td>
								<th>班级名称</th>
								<td>
									<html:text property="bjmc" styleId="bjmc"></html:text>
								</td>
								<th colspan="2"></th>
							</tr>
						</tbody>
						
						<tfoot>
			        		<tr>
			          			<td colspan="6">
			            		<div class="btn">
			              		<input type="hidden" name="go" value="a" />
			              		<button type="button" id="search_go" 
			              		onclick="document.forms[0].go.value='go';refreshForm('/xgxt/commXgInfo.do?method=choiceBj');this.disabled=true;">
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
								查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序</font> 
							</span>
						</h3>
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									
									<td onclick="tableSort(this)">
										年级
									</td>
									<td onclick="tableSort(this)">
										班级代码
									</td>
									<td onclick="tableSort(this)">
										班级名称
									</td>
									<td onclick="tableSort(this)">
										专业代码
									</td>
									<td onclick="tableSort(this)">
										专业
									</td>
									<td onclick="tableSort(this)">
										<bean:message key="lable.xb" />代码
									</td>
									<td onclick="tableSort(this)">
										<bean:message key="lable.xb" />
									</td>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="sendInfo();">
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											<input type="hidden" value="${v }"/>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
						
						<!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commForm"></jsp:include>
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
