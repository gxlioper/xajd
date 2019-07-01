<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/syszDWR.js'></script>
		<script type="text/javascript">
			function pjpySh(xmdm,gwxx,jbxx){
				location.href="pjpy_pjlc_xmsh.do?fwfs=homepage&xmshdm="+xmdm+"&gwxx="+gwxx+"&jbxx="+jbxx+"&go=go";
			}

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
				var num=document.getElementsByName("dbdm").length;
				if($("mklx")){
					if($("mklx").value=="文件管理"){
						$("xmtj").style.display="none";
						$("gwtj").style.display="";
					}else{
						$("xmtj").style.display="";
						$("gwtj").style.display="none";
					}
					for(i=0;i<num;i++){	
						var dm=document.getElementsByName("dbdm")[i].value;
						var mc=document.getElementsByName("dbmc")[i].value;
						var lx=document.getElementsByName("dblb")[i].value;
						if($("mklx").value==lx || $("mklx").value==""){
							if(xmdm==dm){
								text+="<li style='background:#dce8f8'><a href=\"#\"    onclick=\"setXmdm('"+dm+"');checkXmdm('"+dm+"');return false;\">"+mc+"</a></li>";
							}else{
								text+="<li><a href=\"#\"  onclick=\"setXmdm('"+dm+"');checkXmdm('"+dm+"');return false;\">"+mc+"</a></li>";
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
					allNotEmpThenGo("/xgxt/xtwhSysz.do?method=dbsxInfo&doType=query");
			}
		</script>
	</head>
	<body onload="getXm()">

		<html:form action="/xtwhSysz" method="post">
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}" />
			<input type="hidden" name="userDep" id="userDep" value="${userDep}" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType}" />
			<input type="hidden" name="fdyQx" id="fdyQx" value="${fdyQx}" />
			<input type="hidden" name="bzrQx" id="bzrQx" value="${bzrQx}" />
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="returnHomPage('');return false;"
								class="btn_fh">返回</a>
						</li>
					</ul>
				</div>
			</div>

			<!-- 多功能操作区 -->
			<div class="leftframe04">
				<div class="menulist">
				    <!--层start-->
				    <div class="menutitle">
				      <h3><span class="title">项目</span></h3>
				      <!--CNLTreeMenu Start:-->
				      <div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height:440px; overflow:auto;">
						
						<p class="choose_select"><label>
							项目类别
						</label>
						<html:select property="mklx" styleId="mklx" style="width:80px"
							onchange="getXm()">
							<html:options collection="mklxList" property="dm"
								labelProperty="mc" />
						</html:select></p>
						<ul id="xmxslb">
								
						</ul>
						<logic:iterate id="xmlist" name="xmList">
							<input type="hidden" id=dbdm name="dbdm"
								value="${xmlist.dm}" />
							<input type="hidden" id="dbmc" name="dbmc"
								value="${xmlist.mc}" />
							<input type="hidden" id="dblb" name="dblb"
								value="${xmlist.lb}" />
						</logic:iterate>
					 </div>
					
				    </div>
				    <!--层end-->
				  </div>
				</div>		

			<div class="rightframe04" style="width:81.5%">
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
							<!-- 项目条件 -->
							<tr id="xmtj">
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:100px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" styleId="xq" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" styleId="nd" style="width:100px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<!-- 公文问题 -->
							<tr id="gwtj">
								<th>
									文件号
								</th>
								<td>
									<html:text property="wjh" styleId="wjh"/>
								</td>
								<th>
									文件名
								</th>
								<td>
									<html:text property="wjm" styleId="wjm"/>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>

				<div class="con_overlfow" style="width:100%">
						<table width="100%" class="dateline tablenowrap">
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
								<logic:iterate name="rs" id="s">
									
								
								
									<!-- 非文件管理  -->
									
									<logic:equal name="mklx" value="评奖评优">
										<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">

											<td nowrap>
												<a
													href="#"><logic:iterate
														id="v" name="s" offset="0" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</a>
											</td>
											<td nowrap>
												<logic:iterate id="v" name="s" offset="1" length="1">
													${v }
												</logic:iterate>
											</td>
										</tr>
									</logic:equal>
									<logic:equal name="mklx" value="学生资助">
										<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">

											<td nowrap>
												<a
													href="<logic:iterate id="v" name="s" offset="2" length="1">
																<bean:write name="v" />
																</logic:iterate>"><logic:iterate
														id="v" name="s" offset="0" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</a>
											</td>
											<td nowrap>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
										</tr>
									</logic:equal>
									<!-- 非文件管理 end -->

									<!-- 文件管理 特殊显示方式 -->
									<logic:equal name="mklx" value="文件管理">
										<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
											<td nowrap>
												<a
													href="fileView.do?wjh=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>&yd=yes"
													target="_blank"> <logic:iterate id="v" name="s"
														offset="0" length="1">${v }</logic:iterate> </a>
											</td>
											<td nowrap>
												<a
													href="fileView.do?wjh=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>&yd=yes"
													target="_blank"> <logic:iterate id="v" name="s"
														offset="1" length="1">${v }</logic:iterate> </a>
											</td>
											<td>
												<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>
											</td>
										</tr>
									</logic:equal>
									<!-- 文件管理 特殊显示方式 end -->
								</logic:iterate>
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
				<logic:present name="result">
					<logic:equal value="true" name="result">
						<script>
						alert('操作成功！');
					</script>
					</logic:equal>
					<logic:notEqual value="true" name="result">
						<script>
						alert('操作失败！');
					</script>
					</logic:notEqual>
				</logic:present>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
