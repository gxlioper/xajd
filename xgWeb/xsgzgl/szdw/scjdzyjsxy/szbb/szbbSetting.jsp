<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/szbb/szbb.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript">
		function closeWindown() {
			jQuery.ajaxSetup({async:false});
			
			jQuery("#windownbg").remove();
			jQuery("#windown-box").fadeOut("slow",function(){jQuery(this).remove();});
			jQuery.ajaxSetup({async:true});
		}
		
			
		
		function resetFdytj(){
			
			jQuery("input,select",jQuery("#tab_fdy")).each(function(){
				
				if(jQuery(this).attr("disabled")!=true
					&& jQuery(this).css("display")!="none"){
					jQuery(this).val("");
				}
			});
		}
		
		function resetBbtj(){
			
			jQuery("input,select",jQuery("#tab_bbxx")).each(function(){
				
				if(jQuery(this).attr("disabled")!=true
					&& jQuery(this).css("display")!="none"){
					jQuery(this).val("");
				}
			});
		}
						
		jQuery(function(){
			onShow();
		})
		</script>
	<body >
		
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
<%--			<p class="help">--%>
<%--				<a href="#" onclick="return false;" onmouseover="showHelpDiv()"--%>
<%--					onmouseout="showHelpDiv()"> 帮助中心</a>--%>
<%--				<img src="<%=stylePath%>/images/ico_new02.gif" />--%>
<%--			</p>--%>
		</div>
		<!-- 标题 end-->

		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					1.本功能默认展示的是<font color="blue">本评奖周期</font>的信息。 <br/>
					2.<font color="blue">计算功能</font>仅提供<font color="blue">学校(管理员)级别</font>用户进行操作。 <br/>
					3.计算功能仅能计算<font color="blue">本评奖周期</font>的综测分及相关排名。<br/>
					4.如果结果查询中显示的列不满足您的需要，请点击<font color="blue">列选</font>。<br/>
				</span>
			</p>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/szdw_szbb" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden"  name="zypm" id="zypm" value="${zypm }" />
			<input type="hidden" name="zcpm" id="zcpm" value="${zcpm }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="checks" name="checks" value="yes" />
			<input type="hidden" id="bcpjzq" name="bcpjzq" value="${zczq }" />
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
			<input type="hidden" name="hid_fplx" id="hid_fplx" value="${fplx}" />
			
			<!-- 多功能操作区 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goHome();return false;" class="btn_fh">
								返回
							</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				
			</div>
			
			<div>
			<table align="center" class="searchtab" width="100%">
			<tr>
				<td  rowspan="2" style="vertical-align: top">
				<!-- 左边 年级、学院、专业、班级列表   begin -->
				<table align="center" style="vertical-align: top" class="formlist" width="250px" height="560px" id="tab_fdy">
						<tbody>
							<tr>
								<td align="right" style="padding-right: 15px">
									年级
									<html:select property="nj" styleId="nj" style="width:120px"
										onchange="initZyList();initBjListBySzbb();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right"  style="padding-right: 15px">
								<bean:message key="lable.xb" />
								<logic:equal name="userType" value="xy">
									<html:select property="xydm" styleId="xy" disabled="true"
										value="${userDep }" style="width:120px"
										onchange="initZyList();initBjListBySzbb();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" value="${userDep }" style="display:none" />
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
									<html:select property="xydm" styleId="xy" style="width:120px"
										onchange="initZyList();initBjListBySzbb();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td align="right" style="padding-right: 15px">
								专业
								<html:select property="zydm" styleId="zy" style="width:120px"
									onchange="initBjListBySzbb();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								</td>
							</tr>
							<tr>
								<td align="right"  style="padding-right: 15px">
								带班老师
								<select name="fbqk" id="sbqk"  style="width:120px" onchange="initBjListBySzbb();">
									<option value=""></option>
									<option value="已分配">已分配</option>
									<option value="未分配">未分配</option>
								</select>
								</td>
							</tr>
							<tr>
								<td >
									
									<div class="btn">
										<button type="button"   name="查询" onclick="loadYfpInfo();searchRs();initBjListBySzbb();">
											查 询
										</button>
										
										<button type="button"  name="查询" onclick="resetFdytj();initBjListBySzbb();">
											重 置
										</button>
									</div>
								</td>
							</tr>
							<tr>
								<td >
									<html:select property="bjdm" styleId="bj" style="width:200px" size="33"
										onchange="loadYfpInfo();searchRs();">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc"/>
									</html:select>
								</td>
							</tr>
							
						</tbody>
				</table>
				<!-- 左边 年级、学院、专业、班级列表   end -->
				</td>
				
				<td width="95%" style="vertical-align:  top" height="100px">
						<!-- 已分班 辅导员、班主任 begin -->
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th>
										<span>已分配<font color="blue">
											<logic:equal name="fplx" value="fdy">	
												辅导员
											</logic:equal>
											<logic:equal name="fplx" value="bzr">
												班主任
											</logic:equal>
										</font>
										</span>
									</th>
								</tr>
							</thead>
						</table>
						<div style="height:100px;width:100%;overflow-x:hidden;overflow-y:auto;vertical-align:  top">
						<table  align="center" class="dateline" style="width:99%" >
							<thead>
								<tr >
									<th style="text-align: center;">职工号</th>
									<th style="text-align: center;">姓名</th>
									<th style="text-align: center;">性别</th>
									<th style="text-align: center;">所属部门</th>
									<th style="text-align: center;">联系电话</th>
									<th style="text-align: center;">操作</th>
								</tr>
							</thead>
							<tbody id="yfp_tbody" >
								<tr>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
								</tr>
								<tr>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
								</tr>
								<tr>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
									<td style="text-align: center;">&nbsp;</td>
								</tr>
							</tbody>
						</table>
						<!-- 已分班 辅导员、班主任 end -->
						</div>
					</td>
				</tr>
				
				<tr>
					<td >
						<div  id="tbody_search_query">
						<!-- 可分班 辅导员、班主任 begin -->
						<table width="100%" border="0" class="formlist">
							<thead>
								<%--<tr>
									<td>
										<hr color="black" size="0.25"/>
									</td>
								</tr>
								--%><tr>
									<th>
										<span>可分配<font color="blue">
											<logic:equal name="fplx" value="fdy">	
												辅导员
											</logic:equal>
											<logic:equal name="fplx" value="bzr">
												班主任
											</logic:equal>
										</font>
										</span>
									</th>
								</tr>
							</thead>
						</table>
						<table  align="center" class="searchtab" id="tab_bbxx">
							<thead>
								<tr>
									<th>职工号</th>
									<td><input type="text" name="zgh" id="zgh" style="width:120px" /></td>
									<th>姓名</th>
									<td><input type="text" name="xm" id="xm"  style="width:120px" /></td>
									
									<th>性别</th>
									<td>
										<select  name="xb" id="xb"  style="width:120px">
											<option value=""></option>
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</td>
									
								</tr>
								<tr>
								<th>部门类别</th>
									<td>
									<logic:equal name="userType" value="xy"  >
										<select name="bmlb" id="bmlb"  style="width:120px"  disabled="true" >
										<option value=""></option>
										<option value="1">学校</option>
										<option value="5" selected="selected"><bean:message key="lable.xb" /></option>
										</select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy">	
										<select name="bmlb" id="bmlb"  style="width:120px" onchange="initBmList();">
										<option value=""></option>
										<option value="1">学校</option>
										<option value="5"><bean:message key="lable.xb" /></option>
										</select>
									</logic:notEqual>
									</td>
									
									<th>所属部门</th>
									<td>
									<logic:equal name="userType" value="xy">
										<select name="bmdm" id="bmdm" style="width:120px" disabled="true" >
											<logic:notEmpty name="yjbmList">
												<option value=""></option>
												<logic:iterate name="yjbmList" id="bm">
													<logic:equal name="bm" property="bmdm" value="${userDep}">
														<option value="${bm.bmdm}" title="${bm.bmmc }" selected="selected">${bm.bmjc }</option>
													</logic:equal>
													<logic:notEqual name="bm" property="bmdm" value="${userDep}">
														<option value="${bm.bmdm}" title="${bm.bmmc}">${bm.bmjc }</option>
													</logic:notEqual>
												</logic:iterate>
											</logic:notEmpty>
										</select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy">	
										<select name="bmdm" id="bmdm" style="width:120px">
											<logic:notEmpty name="yjbmList">
												<option value=""></option>
												<logic:iterate name="yjbmList" id="bm">
													<option value="${bm.bmdm }" title="${bm.bmmc }">${bm.bmjc }</option>
												</logic:iterate>
											</logic:notEmpty>
										</select>
									</logic:notEqual>
									</td>
									<th>带班情况</th>
									<td><select  name="sfdb" id="sfdb"  style="width:120px">
											<option value=""></option>
											<option value="已带班" >已带班</option>
											<option value="未带班">未带班</option>
										</select>
									</td>
									<th></th>
									<td></td>
									
								</tr>
								<tr>
									<td width="width:22%;" colspan="6">
										<div class="btn">
						              		<button id="search_go"  type="button"
						              		onclick="searchRs()">
						              		查 询
						              		</button>
						              		<button class="btn_cz" id="btn_cz"  type="button"
											onclick="resetBbtj();">
											重 置
											</button>
						            	</div>
									</td>
								</tr>
							</thead>
						</table>
						<!-- 可分班 辅导员、班主任 end -->
						</div>
						
						<!-- 结果集 begin -->
						<div id="div_rs"
							style="width:100%;overflow-x:auto;overflow-y:hidden;">
						</div>
						<!-- 结果集 end -->
						
						<!--分页显示-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=generalSzdwGeneralForm"></jsp:include>
							<script type="text/javascript">
								$('choose').className="hide";
						</script>
					</td>
				</tr>
				</div>
				<!-- 班级信息Div begin -->
				<div id="div_bjxx" style="display:none">
					
				</div>
		</html:form>
	</body>
	<%@ include file="/comm/loading.jsp"%>
	<%@ include file="/comm/other/tsxx.jsp"%>
</html>
