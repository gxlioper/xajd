<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript">
		//照片导出层
		jQuery(function(){
			
			jQuery('#btn_dc').click(function(){
				showDialog("", 500, 200, 'xtwhZpgl.do?method=zpdc');
				//tipsWindown("系统提示","id:zpdcDiv","500","200","true","","true","id");
			});
			
		})
		
		//照片导出
		function zpdc(){
			var photoNameType=$("photoNameType").value;
			
			document.forms[0].action = 'xtwhZpgl.do?method=xszpdc&photoNameType='+photoNameType;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			
		}
		</script>
	</head>
	<body>
		<html:form action="/xtwhZpgl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
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
							<a href="#"
								onclick="showDialog('', 800, 400, 'xtwhZpgl.do?method=xszpdrManage');"
								class="btn_dr"> 招生照片导入 </a>
						</li>
						<li>
							<a href="#"
								onclick="showDialog('',800,400,'xtwhZpgl.do?method=xszpdrManage&zpType=xszp');"
								class="btn_dr"> 学生照片导入 </a>
						</li>
						<li>
							<a href="#" id="btn_dc"
								class="btn_dc"> 导出 </a>
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
											onclick="allNotEmpThenGo('/xgxt/xtwhZpgl.do?method=zpglManage')">
											查询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" />
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										style="width:150px" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="queryequals_xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy"
											style="width:150px" onchange="initZyList();initBjList();">
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
									<html:select property="zydm" styleId="zy"
										style="width:150px" onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									考生号
								</th>
								<td>
									<html:text property="ksh" styleId="ksh" />
								</td>
								<th>
									身份证号
								</th>
								<td>
									<html:text property="sfzh" styleId="sfzh" />
								</td>
								<th>
<%--									是否导入照片--%>
									招生照片状态
								</th>
								<td>
									<html:select property="sfdrzp" styleId="sfdrzp"
										style="width:150px">
										<html:option value=""></html:option>
										<html:option value="已导入">已导入</html:option>
										<html:option value="未导入">未导入</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									新生照片状态
								</th>
								<td>
									<html:select property="sfdrxszp" styleId="sfdrxszp"
										style="width:150px">
										<html:option value=""></html:option>
										<html:option value="已导入">已导入</html:option>
										<html:option value="未导入">未导入</html:option>
									</html:select>
								</td>
								<th>
									
								</th>
								<td>
									
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
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rsArrList">
					<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rsArrList" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						</div>
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xtwhZpglForm"></jsp:include>
						<script type="text/javascript">
							$('choose').className="hide";
					</script>
					
				</logic:notEmpty>
				<div id="zpdcDiv" style="display:none">
					
					<div class="open_win01">
						<table align="center" class="formlist">
							<div id="xxPrompt" class="prompt">
								<h3>
									<span>提示：</span>
								</h3>
								<p >
									若学生信息中所选字段内容为空，则照片的导出命名方式默认为<br/>
									“姓名+学号”。 
								</p>
								<a class="close" title="隐藏"
									onclick="this.parentNode.style.display='none';"></a>
							</div>
							<tbody>
								<tr>
									<th>
										导出照片的命名方式
									</th>
									<td>
										<html:select property="photoNameType" styleId="photoNameType" style="width:120px">
											<html:option value="xh">学号</html:option>
										 	<html:option value="sfzh">身份证号</html:option>
										 	<html:option value="ksh">考生号</html:option>
										</html:select>
									</td>
								</tr>
<%--								<tr>--%>
<%--									<th>--%>
<%--										部门类型--%>
<%--									</th>--%>
<%--									<td>--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="xy"/>学院--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="zy"/>专业--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="bj"/>班级--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="wxz"/>无限制--%>
<%--									</td>--%>
<%--								</tr>--%>
								<tr>
									<th>
										导出类别
									</th>
									<td>
										<input type="radio" name="zpType" value="xs_zs" checked="checked"/>新生&招生照片
										<input type="radio" name="zpType" value="xszp"/>新生照片
										<input type="radio" name="zpType" value="zszp"/>招生照片
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2">
										<div class="bz">
											"
											<span class="red">*</span>"为必填项
										</div>
										<div class="btn">
											<button type="button" name="导出"
												onclick="zpdc()">
												导 出
											</button>
											<button type="button" name="取消" onclick="closeWindown();return false;">
												取 消
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
