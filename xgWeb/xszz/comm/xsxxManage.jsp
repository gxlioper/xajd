<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
			function sendXx(){
				
				if(window.opener == undefined){					 				
					var url = window.dialogArguments.document.forms[0].url.value;
					url+="&xmdm="+$("xmdm").value;
					url+="&xh="+curr_row.getElementsByTagName('input')[0].value;
					window.dialogArguments.document.forms[0].action = url;
					window.dialogArguments.document.forms[0].submit();
				}else{
					var url = window.opener.document.forms[0].url.value;
					url+="&xmdm="+$("xmdm").value;
					url+="&xh="+curr_row.getElementsByTagName('input')[0].value;
					window.opener.document.forms[0].action = url;
					window.opener.document.forms[0].submit();
				}
				window.close();
			}
			
			jQuery(function(){
				xyDisabled('xy');
			})						
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>学生资助 - 学生信息</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXszz" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }"/>
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }"/>
			<!-- 隐藏域 end-->
			<div class="toolbox">
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commXszz.do?method=xsxxManage');return false;">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('nj-xy-zy-bj-xh-xm');return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
								<th>
									院系
								</th>
								<td>
									<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="" styleId="zy" onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>						
									<html:select property="bjdm" style="" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>		
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" style="width:85px" maxlength="20"/>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" style="width:85px" maxlength="20"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rsList">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rsList">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
								<logic:iterate name="rsList" id="rs" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="sendXx()">
										<td>
											<input type="hidden" value="${rs.xh }" />
											${rs.xh }
										</td>
										<td>
											${rs.xm }
										</td>
										<td>
											${rs.xb }
										</td>
										<td>
											${rs.nj }
										</td>
										<td>
											${rs.xymc }
										</td>
										<td>
											${rs.zymc }
										</td>
										<td>
											${rs.bjmc }
										</td>
									</tr>
								</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
						<!--分页end-->
						
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
		</html:form>
	</body>
</html>
