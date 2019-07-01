<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript">
	function sendXx(cs){
		var url = "/xgxt/zjxyRcsw.do?method=swffXmffManage&doType=view";
		if(cs!="ff"){
			url+="&cs=tj";
			url+="&pkValue="+cs;
		}else{
			url+="&cs=ff";
			if(curr_row){
				url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
			}else {
				alert("请选择要发放的项目！");
				return false;
			}
		}
		refreshForm(url);
	}
	</script>
	</head>

	<body >
		<html:form action="/zjxyRcsw" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			
			<!-- 通用 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">
			<logic:equal name="writeAble" value="yes">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="sendXx('ff');return false;" class="btn_fs"> 预选办理名单 </a>
							</li>
						</ul>
					</div>
			</logic:equal>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">	
<!--==================================================================================================-->
					<div class="searchtab">
						<table width="100%" border="0">
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" class="btn_cx" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffXmxz&doType=query','800','480');">
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
										学年
									</th>
									<td>
										<html:select property="queryequals_xn" style="" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="queryequals_xq" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										年度
									</th>
									<td>
										<html:select property="queryequals_nd" style="" onchange="">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										项目类型
									</th>
									<td>
										<html:select property="queryequals_xmlx" style=""
											styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										项目名称
									</th>
									<td>
										<html:text property="querylike_xmmc" style="" styleId="xmmc"/>
									</td>
									<th>
										办理开始时间
									</th>
									<td>
										<html:text property="querygreaterequal_ffsj"
											styleId="querygreaterequal_ffsj" onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('querygreaterequal_ffsj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
										--
										<html:text property="querylessequal_ffsj"
											styleId="querylessequal_ffsj" onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('querylessequal_ffsj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
									</td>

								</tr>
								<tr>
									<th>
										办理结束时间
									</th>
									<td colspan="5">
										<html:text property="kssj"  styleId="kssj"
											onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('kssj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
										--
										<html:text property="jssj"  styleId="jssj"
											 onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('jssj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
									</td>
									
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
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序</font> 
						</span>
					</h3>
					  <table summary="" class="dateline" width="100%">
					    <thead>
					      <tr>
							<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
							</logic:iterate>
					      </tr>
					    </thead>
					    <tbody>
					      <logic:iterate name="rs" id="s" indexId="index">
						      <tr onclick="rowOnClick(this);" style="cursor:hand" 
								>
								<td style="display:none">
									<input type="hidden" id="checkVal" name="checkVal" 
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="7">
									<td align="left">
									<bean:write name="v" />
									</td>
								</logic:iterate>
								
									<td align="left">
									<a href="#" onclick="sendXx('<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>');return false;">
										<font color="blue"><logic:iterate id="v" name="s" offset="8" length="1">
											<bean:write name="v" />
										</logic:iterate></font>
									</a>
									</td>
							
						    </tr>
					      </logic:iterate>
					    </tbody>
					  </table>
					  <!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
					     <script type="text/javascript">
							$('choose').className="hide";
						 </script>
					  <!--分页显示-->
					  </logic:notEmpty>
		</div></div>	
		
		</logic:empty></html:form>
<!--==================================================================================================-->						
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
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
