<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.*" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="xyDisabled('xy');">		
		<html:form action="/xjyd.do?method=xjydcx" method="post">
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="xyV" name="xyV" value=""/>
			<input type="hidden" id="zyV" name="zyV" value=""/>
			<input type="hidden" id="bjV" name="bjV" value=""/>		

			<!--用户信息-->
			<%@ include file="/xsxx/yhxx.jsp"%>
		
			<logic:notEqual value="true" name="isFdy" scope="session">
				<logic:equal value="xy" name="userType" scope="session">
					<input type="hidden" name="queryequals_yhqxydm" value="${userDep}"/>
				</logic:equal>
			</logic:notEqual>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
						  <logic:empty name="yhInfo">
								<li> <a href="#" onclick="showTopWin('xjydsq.do',800,600)" class="btn_zj">增加</a> </li>
								<li> <a href="#" onclick="showDetailWindow('xjyd.do?method=xjydsq&oper=modi',800,600)" class="btn_xg">修改</a> </li>
								<li> <a href="#" onclick="bachAction('xjyd.do?method=xjydcx&doType=del','primarykey_cbv','您确定删除选择的数据吗？')" class="btn_sc">删除</a> </li>
								<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>				    
								<li> <a href="#" onclick="showExportDIV('expData.do');" class="btn_dc">导出数据</a> </li>
						  </logic:empty>
					  </logic:equal>	
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>		  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/xjyd.do?method=xjydcx&doType=query')">
										查 询
									</button>
									<button type="button"
										onclick="searchReset();return false;">
										重 置
									</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>					  
					  <tr>
						<th>异动前年级</th>
						<td>
							<html:select property="ydqnj" onchange="initZyList();initBjList()" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th>学号</th>
						<td>	
							<html:text property="xh" maxlength="20" style="width:80px"></html:text>	
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" maxlength="20" style="width:80px"></html:text>	
						</td>
					  </tr>
					  <tr>
						<th>异动前<bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="ydqxydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>异动前专业</th>
						<td>
							<html:select property="ydqzydm" onchange="initBjList()"  styleId="zy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>		
						</td>
						<th>异动前班级</th>
						<td>
							<html:select property="ydqbjdm"  styleId="bj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>

					  <tr>
						<th>异动类别</th>
						<td>
                            <html:select property="ydlbdm" styleId="ydlbdm" style="width:160px" onchange="refreshForm('xjyd.do?method=xjydcx&doType=query')">
								<html:options collection="ydlbList" property="dm"
									labelProperty="mc" />
							</html:select>
                        </td>
						<th>异动日期</th>
						<td>
                            <html:text property="querygreaterequal_ydrq"  styleId="ydrqks"
							           style="width:70px" 
							           onclick="return showCalendar('ydrqks','y-mm-dd');" 
							           onblur="dateFormatChg(this)"
							           ></html:text>
								-
							<html:text property="querylessequal_ydrq"  styleId="ydrqjs"
							           style="width:70px" 
							           onclick="return showCalendar('ydrqjs','y-mm-dd');" 
							           onblur="dateFormatChg(this)"
							           ></html:text>
                        </td>
						<th>异动截止日期</th>
						<td>
                            <html:text property="querygreaterequal_ydjzrq" styleId="ydjzrqks"
							           style="width:65px"
							           onclick="return showCalendar('ydjzrqks','y-mm-dd');" 
							           onblur="dateFormatChg(this)"
							           ></html:text>
								-
							<html:text property="querylessequal_ydjzrq" styleId="ydjzrqjs"
							           style="width:65px"
							           onclick="return showCalendar('ydjzrqjs','y-mm-dd');" 
							           onblur="dateFormatChg(this)"
							           ></html:text>
                        </td>
						</tr>
						<!--审核条件页面-->
						<%@ include file="/xsxx/xsxx_shtjym.jsp"%>

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
							<font color="blue">提示：双击一行可以查看详细；单击表头可以排序！</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			       <td>
						<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px" />
					</td>
					<logic:iterate id="tit" name="topTr" offset="3" scope="request">
						<td id="${tit.en}"
							onclick="tableSort(this)">
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" ondblclick="showDetailWindow('xjyd.do?method=xjydShOne&doType=view&xtgwid='+radioValue('xtgwid'),800,600)"
							style="cursor:hand;background-color:
							    <logic:iterate id="v" name="s" offset="1" length="1">
							    ${v}
							    </logic:iterate>
							     ">

							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">								
									<input type="hidden" name="check"  value="${v}"/>
									<input type="checkbox" name="primarykey_cbv" value="${v}" alt="<logic:iterate id="v" name="s" offset="0" length="1"><logic:equal value="是" name="v">disabled</logic:equal></logic:iterate>" id="cbv" />
								</logic:iterate>
						    </td>
							<td>
								<logic:iterate id="v" name="s" offset="1" length="1">							
									<a href="#" onclick="showTopWin('stu_info_details.do?xh=${v}',800,600)">${v}</a>
								</logic:iterate>
						    </td>
							<logic:iterate id="v" name="s" offset="2">
								<td>
									${v}
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxXjydForm"></jsp:include>
				<!--分页显示-->
				
				</logic:notEmpty>
				</div>
		</html:form>
		 
		 <logic:present name="result">
			<script>
				alert(''+$('message').value);
				//document.getElementById('search_go').click();
			</script>		
		</logic:present>
	</body>
</html>
