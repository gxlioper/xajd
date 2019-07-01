<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function xssqSh(){
			var pkValue=document.getElementsByName('primarykey_checkVal');
			var n=0;
			var pk=""
			for(var i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					pk=pkValue[i].value;
					n++;
				}
			}
			var url="";
			if(n==0){
				alertInfo("请勾选需要审核的数据!");
				return false;
			}else if(n==1){//单条记录审核 
				 url="/xgxt/cdtyXsst.do?method=xsstDgsh&pkValue="+pk;
				 showTopWin(url,800,600);
			}else{//多条记录审核 
				 //url="/xgxt/cdtyXsst.do?method=xsstsqSh&doType=plsh";
				 
				 viewTempDiv('请选择审核岗位','div_plsh',350,200);
			}
			
		
		}
		
		function plsh(shzt){
				refreshForm("/xgxt/cdtyXsst.do?method=xsstsqSh&doType=plsh&shzt="+shzt);
		}
		</script>
	</head>
	<body onload="">

		<html:form action="/cdtyXsst" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
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
							<a href="#" onclick="xssqSh()" class="btn_sh"> 审核 </a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/cdtyXsst.do?method=xsstManage')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
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
									<html:select property="nj" styleId="nj" style="width:150px"
										onchange="initZyList();initBjList();">
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
										<html:select property="queryequals_xydm" styleId="xy"
											disabled="true" value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList();">
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
									<html:select property="zydm" styleId="zy" style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									审核状态
								</th>
								<td>
									<html:select property="shzt" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
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
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" indexId="index">

										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
										<td>
											<input type="checkbox" name="primarykey_checkVal"
												id="checkVal"
												value="<logic:iterate id="v" name="s" offset="0" length='1'><bean:write name="v" /></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=cdtyXsstForm"></jsp:include>

					</div>
				</logic:notEmpty>
				<div id='div_plsh' style='display:none'>
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span> 批量审核 </span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<textarea name='shyj' id="shyj" rows='4'
											style="word-break:break-all;width:98%"
											onblur="chLeng(this,500);"></textarea>

									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td>
										<div class="btn">
											<!-- 确定 -->
											<button onclick="plsh('通过')">
												通 过
											</button>
											<button onclick="plsh('不通过')">
												不通过
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
		</html:form>

	</body>
</html>
