<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				url+='&pkValue='+curr_row.getElementsByTagName('input')[0].value;
				url+='&write='+curr_row.getElementsByTagName('input')[1].value;
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
	</script>
	</head>
	<body >
		
		<html:form action="jsspZds" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="realTable" id="realTable" value="rcsw_jssp_zds" />
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
			    <logic:equal name="writeAble" value="yes">
				<li> <a href="#" onclick="showOpenWindow('/xgxt/jsspZds.do?method=zdsSq&doType=add',700,500)" class="btn_zj"> 增加 </a> </li>
			    <li> <a href="#" onclick="modi('/xgxt/jsspZds.do?method=zdsOne&doType=modi')" class="btn_xg"> 修改 </a> </li>
				<li> <a href="#" onclick="dataBatch('/xgxt/jsspZds.do?method=zdsCx&doType=del')" class="btn_sc"> 删除 </a> </li>
				<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a> </li>
				</logic:equal>
				<li> <a href="#" onclick="expData('/xgxt/jsspZds.do?method=expDate')" class="btn_dc"> 导出 </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button type="button" class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('/xgxt/jsspZds.do?method=zdsCx&doType=query')">
			              	查 询
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
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
								学号
							</th>
							<td>
								<logic:notEqual name="userType" value="stu">
									<html:text property="querylike_xh" styleId="xh"/>
								</logic:notEqual>
								<logic:equal name="userType" value="stu">
									<input type="text" name="xh" value="${userName }" readonly="true"/>
									<html:hidden property="querylike_xh" styleId="xh" value="${userName }"/>
								</logic:equal>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="querylike_xm" styleId="xh"/>
							</td>
							<th>
								年级
							</th>
							<td>
								<html:select property="queryequals_nj" styleId="nj" style="width:150px"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
								labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
						<th >
							系
						</th>
						<td>
						<logic:equal name="userType" value="xy">
							<html:select property="xydm" styleId="xy" value="${userDep}" disabled="true" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
							</html:select>
							<html:hidden property="queryequals_xydm" value="${userDep}"/>
						</logic:equal>
						<logic:notEqual name="userType" value="xy">
							<html:select property="queryequals_xydm" styleId="xy" style="width:150px"
								onchange="initZyList();initBjList()">
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
						<html:select property="queryequals_zydm" styleId="zy"   style="width:150px"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
							</html:select>
						</td>
						<th>
							班级
						</th>
						<td>
						<html:select property="queryequals_bjdm" styleId="bj"   style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
							</html:select>
						</td>
						</tr>
						<tr>
							<th>
								申请时间
							</th>
							<td>
								 <html:text property="kssj" styleId="kssj" style="width:90px"
									onclick="return showCalendar('kssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" /> --
								<html:text property="jssj" styleId="jssj" 
									onclick="return showCalendar('jssj','y-mm-dd');"   style="width:90px"
									onblur="dateFormatChg(this)" />
							</td>
							<logic:equal name="userType" value="xy">
							<th>
								<bean:message key="lable.xb" />审核
							</th>
							<td>
								<html:select property="queryequals_xysh" styleId="xysh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
							<th>
								学校审核
							</th>
							<td>
								<html:select property="queryequals_xxsh" styleId="xxsh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
							</logic:equal>
							<logic:equal name="userType" value="xx">
								<th>
								学校审核
								</th>
								<td>
									<html:select property="queryequals_xxsh" styleId="xxsh">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
								<th></th>
								<td></td>
							</logic:equal>
							<logic:equal name="userType" value="admin">
								<th>
								学校审核
								</th>
								<td>
									<html:select property="queryequals_xxsh" styleId="xxsh">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
								<th></th>
								<td></td>
							</logic:equal>
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
			 		 	<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
	
			<logic:notEmpty name="rs">
					<div class="con_overlfow">
					 <table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()"/>
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="2" indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('/xgxt/jsspZds.do?method=zdsOne&doType=view')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>/>
									<input type="hidden" name="write" value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"/>
									</logic:iterate>
									
								</td>
								<logic:iterate id="v" name="s" offset="2">
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
						page="/sjcz/turnpage.jsp?form=jsspZdsForm"></jsp:include>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>	
  </body>
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
</html>
