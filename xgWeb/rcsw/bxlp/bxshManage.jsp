<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/xtwh/bdsz.js"></script>
		<script type="text/javascript" >
		function showPlsh(){
			
			tipsWindown("系统提示","id:div_plsh","350","250","true","","true","id");
		}
		
		function plsh(){
			
			confirmInfo("确定操作勾选的记录吗?",function(t){
				if(t=="ok"){
					refreshForm('/xgxt/rcsw_bxlp.do?method=bxshManage&doType=plsh');
				}
			});
			
		}
		
		function dgsh(pk){
			showTopWin('/xgxt/rcsw_bxlp.do?method=bxshSave&pk='+pk,800,600);
		}
		
		function bxsh(){
			var n=0;
			var checkVal= document.getElementsByName("checkVal");
			var pkV="";
			for(var i=0;i<checkVal.length;i++){
				if(checkVal[i].checked){
					pkV=checkVal[i].value;
					n++;
				}
			}
			
			if(n==0){
				alertInfo("请选择需要审核的记录!");
				return false;
			}
			
			if(n==1){
				dgsh(pkV);
				return false;
			}else{
				showPlsh();
			}
		
		}
		</script>
	</head>

	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/rcsw_bxlp" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx }" />
			<input type="hidden" name="isBzr" id="isBzr" value="${bzrQx }" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			<input type="hidden" name="likeCol" value="xh!!xm" />
			<input type="hidden" name="queryCol" value="nj!!xydm!!zydm!!bjdm!!sfby" />

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="bxsh()"
									class="btn_sh"> 审核 </a>
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
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/rcsw_bxlp.do?method=bxshManage&go=go')">
											查 询
										</button>
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
									学年
								</th>
								<td>
									<html:select property="xn" style="width:160px">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" style="width:160px">
										<html:options collection="ndList" property="nd" labelProperty="nd"/>
									</html:select>
								</td>
								<th>
									是否毕业
								</th>
								<td>
									<html:select property="sfby" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="isNot" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								
							</tr>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" onchange="initZyList();initBjList()" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<logic:equal value="stu" name="userType" scope="session">
										<html:text property="xh" styleId="xh" styleClass="inputtext" style="width:160px" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<html:text property="xh" styleId="xh" styleClass="inputtext" style="width:160px"></html:text>
									</logic:notEqual>
								
								
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext" style="width:160px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy"/>
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<input type="hidden" name="xydm" value="${userDep }"/>
									</logic:equal>
									<html:select property="xydm" style="width:160px"
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()"
										style="width:160px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<logic:equal name="fdyQx" value="true">
									<th>
										辅导员审核
									</th>
									<td>
										<html:select property="fdysh" style="width:160px"
											 styleId="fdysh">
											<html:option value=""></html:option>
											<html:option value="未审核">未审核</html:option>
											<html:option value="通过">通过</html:option>
											<html:option value="不通过">不通过</html:option>
										</html:select>
									</td>
								</logic:equal>
								<logic:equal name="fdyQx" value="true">
									<th>
										<bean:message key="lable.xb" />审核
									</th>
									<td>
										<html:select property="xysh" style="width:160px"
											 styleId="xysh">
											<html:option value=""></html:option>
											<html:option value="未审核">未审核</html:option>
											<html:option value="通过">通过</html:option>
											<html:option value="不通过">不通过</html:option>
										</html:select>
									</td>
								</logic:equal>
								<logic:notEqual name="fdyQx" value="true">
								<logic:equal name="userType" value="xy">
									<th>
										<bean:message key="lable.xb" />审核
									</th>
									<td>
										<html:select property="xysh" style="width:160px"
											 styleId="xysh">
											<html:option value=""></html:option>
											<html:option value="未审核">未审核</html:option>
											<html:option value="通过">通过</html:option>
											<html:option value="不通过">不通过</html:option>
										</html:select>
									</td>
								</logic:equal>
								</logic:notEqual>
								<th>
									学校审核
								</th>
								<td>
									<html:select property="xxsh" style="width:160px"
											 styleId="xxsh">
											<html:option value=""></html:option>
											<html:option value="未审核">未审核</html:option>
											<html:option value="通过">通过</html:option>
											<html:option value="不通过">不通过</html:option>
										</html:select>
								</td>
								<logic:notEqual name="fdyQx" value="true">
									<logic:equal name="userType" value="xy">
										<th>
											
										</th>
										<td>
										
										</td>
									</logic:equal>
								</logic:notEqual>
								<logic:equal name="userType" value="xx">
									<th>
										
									</th>
									<td>
									
									</td>
									<th>
										
									</th>
									<td>
									
									</td>
								</logic:equal>
								<logic:equal name="userType" value="admin">
									<th>
										
									</th>
									<td>
									
									</td>
									<th>
										
									</th>
									<td>
									
									</td>
								</logic:equal>
							</tr>
						</tbody>
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
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showInfo('/xgxt/rcsw_bxlp.do?method=bxwhSave','view','650','500');"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="pk" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" 
												<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>/>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						</div>
						<!--分页显示-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bxlpForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
			</div>
			
				<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>审核</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_clr">
								<th width="30%">
									<font color="red">*</font>审核
								</th>
								<td>
									<html:select  property="shzt"  style="width:90px"
										styleId="shzt">
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
							</tr>
							<tr id="tr_tsnr">
								<th width="30%">
									审核意见<br/>
									<font color="red"><限500字></font>
								</th>
								<td>
									<textarea name="shyj" id="shyj" rows="5" cols="" 
										style="word-break:break-all;width:96%" onblur="chLeng(this,500)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="plsh()">
											保 存
										</button>
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alertInfo("审核成功！");
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alertInfo("审核失败！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
