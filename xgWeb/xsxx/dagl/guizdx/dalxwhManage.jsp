<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
			function modi(url){
				var url="/xgxt/guizdxDagl.do?method=dalxwhUpdate&act=update";
				var pkValue=document.getElementsByName("pkValue");
				n=0;
				var pkV="";
				for(i=0;i<pkValue.length;i++){
					if(pkValue[i].checked){
						n++;
						pkV=pkValue[i].value;
					}
				}
				if(n!=1){
					alertInfo("请选择一条记录进行修改！");
				}else{
					showTopWin(url + '&pkValue='+pkV,300,300);
					return true;
				}
			
			}
			
			function add(){
				showTopWin("/xgxt/guizdxDagl.do?method=dalxwhUpdate&act=add",300,300);
			}
			
			function del(){
				var url="/xgxt/guizdxDagl.do?method=dalxwhManage&doType=del";
				var pkValue=document.getElementsByName("pkValue");
				n=0;
				var pkV="";
				for(i=0;i<pkValue.length;i++){
					if(pkValue[i].checked){
						n++;
						break;
					}
				}
				if(n!=1){
					alertInfo("请选择需要删除的记录！");
					return false;
				}else{
					refreshForm(url);
					return true;
				}
			
			}
		</script>
	</head>
	<body>

		<html:form action="/guizdxDagl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
			<input type="hidden" name="realTable" id="realTable"
				value="mdqr_xmnrb" />
			<input type="hidden" name="tabName" id="tabName" value="mdqr_xmnrb" />
			<input type="hidden" name="viewName" id="viewName"
				value="view_mdqr_xmnrb" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="add()" class="btn_zj"> 增加 </a>
						</li>
						<li>
							<a href="#" onclick="modi()" class="btn_xg"> 修改 </a>
						</li>
						<li>
							<a href="#"
								onclick="del()"
								class="btn_sc"> 删除 </a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/guizdx_dagl_dalxwh.do')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<th>
									名称
								</th>
								<td>
									<html:text property="mc" styleId="mc" />
								</td>
								<th>
									类型
								</th>
								<td>
									<html:select property="lx" styleId="lx">
										<html:option value=""></html:option>
										<html:options collection="lxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td width="15px">
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2" indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s">
									<tr onclick="rowMoreClick(this,'',event);" ondblclick=""
										style="cursor:hand">
										<td width="15px">
											<input type="checkbox" name="pkValue" id="pkV"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"
												<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate> />
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<%@ include file="/comm/noRows.jsp"%>

						</tbody>
					</table>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xsxxAhjzgyxyForm"></jsp:include>
					<%--					<script type="text/javascript">--%>
					<%--							$('choose').className="hide";--%>
					<%--					</script>--%>
				</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:present name="result">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>
