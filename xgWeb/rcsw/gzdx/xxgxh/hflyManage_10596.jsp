<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script type="text/javascript">	
	
	//showidArr 要显示元素的id集合；hiddenidArr 要隐藏元素的id集合
		function changeDisplay(showidArr,hiddenidArr){
			for(var i=0; i<showidArr.length; i++){
				if($(showidArr[i])){
					$(showidArr[i]).style.display = "";
				}
			}
			for(var i=0; i<hiddenidArr.length; i++){
				if($(hiddenidArr[i])){
					$(hiddenidArr[i]).style.display = "none";
				}
			}
		}
	
	//改变查询条件（类型）
	function chLx(){
	
		var lx = $("lx").value;
		
		var showEle = [];
		var hiddenEle = [];
			
		if(lx == "学生"){
			showEle = ["stuTr"];
			hiddenEle = ["teaTr","admTr"];				
		}else if(lx == "老师"){
			showEle = ["teaTr"];
			hiddenEle = ["stuTr","admTr"];	
		}else if(lx == "管理者"){
			showEle = ["admTr"];
			hiddenEle = ["stuTr","teaTr"];	
		}else{
			showEle = ["stuTr"];
			hiddenEle = ["teaTr","admTr"];		
		}
		changeDisplay(showEle,hiddenEle);
	}
		
	//置顶留言
	function zdLy(pkValue,flag){
	
		var msg = "";
		
		if("true" == flag){
			msg = "置顶本条信息？";
		}else{
			msg = "取消置顶本条信息？";
		}
		
		if(confirm(msg)){
			var url = "/xgxt/gzdxRcsw.do?method=hflyManage&doType=zd";
				url+="&flag="+flag;
			if($("pkValue")){
				$("pkValue").value = pkValue;
			}
			showTips('处理数据中，请等待......');
			saveUpdate(url,"");
		}
	}
	
	</script>
	</head>
	<body onload="chLx();">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
	
	
		<html:form action="/gzdxRcsw">
			<%@ include file="hiddenValue.jsp"%>
			
			
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="isAdmin" value="true">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/gzdxRcsw.do?method=hflyManage','del')"
									class="btn_sc"> 删除 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<logic:equal name="userType" value="xx">
						<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/gzdxRcsw.do?method=hflyManage','del')"
									class="btn_sc"> 删除 </a>
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
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/gzdxRcsw.do?method=hflyManage&doType=go');">
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
									留言类型
								</th>
								<td>
									<html:select property="lylx" style="" onchange="" >
											<html:options collection="lylxList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th>
									身份
								</th>
								<td>
									<html:select property="lx" style="" onchange="chLx()" >
											<html:option value=""></html:option>
											<html:options collection="lxList" property="en" labelProperty="cn" />
										</html:select>
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
											<!-- 教师用户 -->
											<logic:equal name="isTeacher" value="true">
												<html:select property="xydm" style="width:200px" styleId="xy" onchange="initZyList();initBjList();">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm" labelProperty="xymc" />
												</html:select>
											</logic:equal>
											<!-- 非教师用户 -->
											<logic:equal name="isTeacher" value="false">
												<html:hidden property="xydm"/>
												<html:select property="xydm" style="width:200px" styleId="xy" disabled="true">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</logic:equal>
										</logic:equal>
										<!-- 非<bean:message key="lable.xsgzyxpzxy" />用户 -->
										<logic:notEqual name="userType" value="xy">
											<html:select property="xydm" style="width:200px" styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:200px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:200px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
								</td>
								
							</tr>
								<tr>
									<th>学号(工号)</th>
									<td>
										<html:text property="lyr" style="" maxlength="20"/>
									</td>
									<th>姓名</th>
									<td><html:text property="xm" style="" maxlength="20"/></td>
									<th></th>
									<td></td>
								</tr>
								<tr style="display:none" id="teaTr">
									<th>所在<bean:message key="lable.xsgzyxpzxy" /></th>
									<td><logic:equal name="userType" value="xy">
											<html:hidden property="bmdm"/>
											<html:select property="bmdm" style="width:200px" styleId="bmdm" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
											</html:select>
										</logic:equal>
										<!-- 非<bean:message key="lable.xsgzyxpzxy" />用户 -->
										<logic:notEqual name="userType" value="xy">
											<html:select property="xydm" style="width:200px" styleId="xy" onchange="">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual></td>
										<th></th><td></td>
										<th></th><td></td>
								</tr>
								<tr style="display:none" id="admTr">
									<th>所属部门</th>
									<td align="left">
										<!-- <bean:message key="lable.xsgzyxpzxy" />用户 -->
										<logic:equal name="userType" value="xy">
											<html:hidden property="bmdm"/>
											<html:select property="bmdm" style="width:200px" styleId="bmdm" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
											</html:select>
										</logic:equal>
										<!-- 非<bean:message key="lable.xsgzyxpzxy" />用户 -->
										<logic:notEqual name="userType" value="xy">
											<html:select property="bmdm" style="width:200px" styleId="bmdm" onchange="">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm"
													labelProperty="bmmc" />
											</html:select>
										</logic:notEqual>
									</td>
								</tr>
							</tbody>
						</table>
						</div>
						
						
						<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
										<!-- 管理员权限 -->
										<logic:equal name="isAdmin" value="true">
											<td align="center">
												<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
											</td>
										</logic:equal>
										<td>
											置顶
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" length="6">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<td>
											执行操作
										</td>
									</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" >
										<!-- 管理员权限 -->
										<logic:equal name="isAdmin" value="true">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal" 
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
											</td>
										</logic:equal>
										<logic:iterate id="v" name="s" offset="8" length="1">
											<td align="center">
												<!-- 是否有置顶 0：无 非0：有 -->
												<logic:notEqual name="v" value="0"><font color="red">顶</font></logic:notEqual>
												<logic:equal name="v" value="0"></logic:equal>
											</td>
										</logic:iterate>	
										<logic:iterate id="v" name="s" offset="1" length="6">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									<td align="left">
										<logic:iterate id="v" name="s" offset="0" length="1">
										<a href="#" 
											onclick="showTopWin('/xgxt/gzdxRcsw.do?method=xslyUpdate&doType=view&pk=<bean:write name='v'/>','800','600')">
											查看
										</a>
										</logic:iterate>
										<logic:notEqual name="userType" value="stu">
											<logic:iterate id="v" name="s" offset="3" length="1">
												<logic:equal name="v" value="学生">
													<a href="#" 
														onclick="showTopWin('/xgxt/gzdxRcsw.do?method=xslyUpdate&doType=update&pk=<logic:iterate id="x" name="s" offset="0" length="1"><bean:write name='x'/></logic:iterate>','800','600')">
														回复
													</a>
												</logic:equal>
											</logic:iterate>
										</logic:notEqual>
										<logic:equal name="isAdmin" value="true">
										<logic:iterate id="v" name="s" offset="8" length="1">
											<!-- 是否有置顶 0：无 非0：有 -->
											<logic:notEqual name="v" value="0">
												<logic:iterate id="x" name="s" offset="0" length="1">
													<a href="#" onclick="zdLy('<bean:write name='x'/>','false')">
														取消置顶
													</a>
												</logic:iterate>
											</logic:notEqual>
											<logic:equal name="v" value="0">
												<logic:iterate id="x" name="s" offset="0" length="1">
													<a href="#" onclick="zdLy('<bean:write name='x'/>','true')">
														置顶
													</a>
												</logic:iterate>
											</logic:equal>
										</logic:iterate>						
										</logic:equal>
									</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
					<!-- 管理员权限 -->
				</div>
			</logic:empty>
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
	</body>
</html>
