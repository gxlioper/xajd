<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/syszDWR.js'></script>
		<script language="javascript">
			function checkMkList(text){
				var liArr = $('xmxslb').getElementsByTagName('li');
				
				for (var i = 0 ; i < liArr.length ; i++){
					if (liArr[i].id.indexOf(text)>=0){
						liArr[i].style.display="";
					} else {
						liArr[i].style.display="none";
					}
				}
			
			}
		
			
			function getXm(){
				var text="<span>";
				var xmdm=$("xmdm").value;
				var num=document.getElementsByName("sqxmdm").length;
				if($("mklx")){
					for(i=0;i<num;i++){	
						var dm=document.getElementsByName("sqxmdm")[i].value;
						var mc=document.getElementsByName("sqxmmc")[i].value;
						var lx=document.getElementsByName("sqxmlb")[i].value;
						if($("mklx").value==lx || $("mklx").value==""){
							if(xmdm==dm){
								text+="<li><a href=\"#\" style='background:#dce8f8;color:#0A63BF'  onclick=\"setXmdm('"+dm+"');checkXmdm('"+dm+"')\"><span class='ico'></span>"+mc+"</a></li>";
							}else{
								text+="<li><a href=\"#\"  onclick=\"setXmdm('"+dm+"');checkXmdm('"+dm+"')\"><span class='ico'></span>"+mc+"</a></li>";
							}
						}
					}
					text+="</span>"
					document.getElementById('xmxslb').innerHTML = text;
					
				}
			}
			
			//设置模块代码
			function setXmdm(xmdm){
				$("xmdm").value=xmdm;
			}
			
			function checkXmdm(){
					var xmdm=$("xmdm").value;
					 setXmdm(xmdm);
					allNotEmpThenGo("/xgxt/xtwhSysz.do?method=xssqInfo&doType=query");
			}
		</script>
	</head>
	<%--	getXm()--%>
	<body onload="">

		<html:form action="/xtwhSysz" method="post">
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType}" />
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="returnHomPage('')" class="btn_fh">返回</a>
						</li>
					</ul>
				</div>



				<div class="searchtab" style="width:100%">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" onclick="checkXmdm()">
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
								<th width="16%">
									模块类型
								</th>
								<td width="16%">
									<html:select property="mklx" styleId="mklx"
									  style="width:80px" onchange="">
										<html:options collection="mklxList" property="dm"
										labelProperty="mc" />
									</html:select>
								</td>
								<th width="16%">
									
								</th>
								<td width="16%">
									
								</td>
								<th width="16%">
									
								</th>
								<td width="16%">
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>



				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>

				<div class="con_overlfow" style="width:100%">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">

								<logic:iterate id="tit" name="topTr">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>

						<tbody>
							
							<logic:notEmpty name="rs">
								<!-- 非违纪处分模块 begin -->
								<logic:notEqual name="mklx" value="违纪处分">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</logic:notEqual>
								<!-- 非违纪处分模块 end -->
								
								<!-- 违纪处分模块 begin -->
								<logic:equal name="mklx" value="违纪处分">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0" length="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td nowrap>
												<a href='#' onclick="showTopWin('grwjcfxxview.do?pkValue=<logic:iterate id="vs" name="s" offset="3" length="1">${vs }</logic:iterate>',800,650)">${v }</a>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</logic:equal>
								<!-- 违纪处分模块 end -->
							</logic:notEmpty>
							<%
									ArrayList list = ((ArrayList) request.getAttribute("rs"));
									int rsNum = 0;
									if (list != null) {
										rsNum = list.size();
									}
									int pageSize = (Integer) request.getAttribute("pageSize");
									for (int i = 0; i < (pageSize - rsNum); i++) {
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xtwhSyszForm"></jsp:include>
				<script type="text/javascript">
									$('choose').className="hide";
									</script>
			</div>

			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alertInfo('操作成功！');
					</script>
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alertInfo('操作失败！');
					</script>
				</logic:notEqual>
			</logic:present>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
