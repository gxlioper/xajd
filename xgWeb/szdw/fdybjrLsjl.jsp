<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/szdwfzjy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" name="userName" id="userName" value="${userName }"/>
			<input type="hidden" name="userType" id="userType" value="${userType }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable }"/>
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
			<div class="toolbox">
			 <!-- 按钮 -->
			  <logic:equal value="yes" name="writeAble">
				 <div class="buttonbox">
				    <ul>
					<li> <a href="#" onclick="wjcfDataExport('/xgxt/szdwfzjy.do?method=szdwExp')" class="btn_dc"> 导出数据 </a> </li>
				    </ul>
				 </div>
				</logic:equal>			 
			 <div class="searchtab">			
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="" />
			              <button type="button" class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('/xgxt/szdwfzjy.do?method=szdwBbLy');">
			              	查 询
			              </button>
			              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
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
								<html:select property="queryequals_xn" style="" onchange="">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>
								年级
							</th>
							<td>
								<html:select property="queryequals_nj" style="" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
							</td>
							<th>
								<html:radio property="tableName" value="view_fdybbjls">辅导员历史信息</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;
      						</th>
      						<td>
      							<html:radio property="tableName" value="view_bzrbbjls">班主任历史信息</html:radio>
      						</td>
							</tr>
							<tr>
								<th>
										<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
										<html:select property="queryequals_xydm" style="width:150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
										<html:select property="queryequals_zydm" style="width:150px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm" labelProperty="zymc" />
										</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
										<html:select property="queryequals_bjdm" style="width:150px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										职工号
									</th>
									<td>
										<logic:equal name= "fdyQx" value="true" scope="session" >
										<html:text property="querylike_zgh" styleId="zgh" style="" value="${userName}" maxlength="20" readonly="true"/>
										</logic:equal>
										<logic:notEqual name="fdyQx" value="true" scope="session" >
										<html:text property="querylike_zgh" styleId="zgh" style="" maxlength="20"/>
										</logic:notEqual>
									</td>
									<th>
										辅导员姓名
									</th>
									<td>
										<html:text property="querylike_xm" styleId="xm" style="" maxlength="20"/>
									</td>
									<td colspan="2">
								</tr>
							</tbody>
						</table>
						</div>
					  </div>

					<div class="formbox">
					    <h3 class="datetitle_01">
					    <span>
					    	查询结果&nbsp;&nbsp;
					    	<logic:empty name="rs">
								<font color="red">未找到任何记录！</font> 
					 		 </logic:empty>
					 		 <logic:notEmpty name="rs">
					 		 	记录数：
								<bean:write name="rsNum" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：单击表头可以排序</font>
					 		 </logic:notEmpty>
					    </span>
					    </h3>				
						<logic:notEmpty name="rs">
							<div class="con_overlfow">
							 <table summary="" class="dateline tablenowrap" width="100%">
								<thead>	
									<tr style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>

								<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" >
										<logic:iterate id="v" name="s" offset="0">
										<td>
											<bean:write name="v" />	
										</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
							</div>
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=szdwForm"></jsp:include>
							 <script type="text/javascript">
								$('choose').className="hide";
							</script>							
					</logic:notEmpty>
				</div>
			</logic:empty>
		</html:form>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
				<script>
					if($("message") && $("message").value != ""){
						alert($("message").value);
						$("message").value = "";
					}
				</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
