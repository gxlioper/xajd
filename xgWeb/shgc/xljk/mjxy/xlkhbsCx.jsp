<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function checkAjax(){
			if($("lc").value=="null"){
				$("lc").value="";
			}
			if($("qsh").value=="null"){
				$("qsh").value="";
			}
			refreshForm('/xgxt/nbtyWmqs.do?method=resultWmqs&doType=qry');
		}
	</script>
	</head>
	<body >
		
		<html:form action="/xljkMjxyXlztbs" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="realTable" id="realTable" value="xljk_mjxy_xlkhbs" />
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
				<li> <a href="#" onclick="showOpenWindow('/xgxt/xljkMjxyXlkhbs.do?method=xlkhbsWh&doType=add',700,500)" class="btn_zj"> 增加 </a> </li>
			    <li> <a href="#" onclick="modi('/xgxt/xljkMjxyXlkhbs.do?method=xlkhbsOne&doType=modi')" class="btn_xg"> 修改 </a> </li>
				<li> <a href="#" onclick="dataBatch('xljkMjxyXlkhbs.do?method=xlkhbsCx&doType=del')" class="btn_sc"> 删除 </a> </li>
				<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a> </li>
				</logic:equal>
				<li> <a href="#" onclick="expData('/xgxt/xljkMjxyXlkhbs.do?method=expDate')" class="btn_dc"> 导出 </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('/xgxt/xljkMjxyXlkhbs.do?method=xlkhbsCx&doType=qry')">
			              	查 询
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	重 置
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>

					<tbody>
						<tr>
						<th >
							<bean:message key="lable.xb" />
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
							<html:select property="queryequals_xydm" styleId="xy"  style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</logic:notEqual>
						</td>
						<th>
							心理状态
						</th>
						<td>
							<html:select property="queryequals_xlzt" styleId="xlzt"  style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="xlztList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>
							报送部门
						</th>
						<td>
							<html:select property="queryequals_bsbm" styleId="bsbm"  style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bsbmList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						</tr>
						<tr>
							<th>
								负责人
							</th>
							<td>
								<html:text property="querylike_fzr" styleId="fzr"/>
							</td>
							<th>
								报送人
							</th>
							<td>
								<html:text property="querylike_bsr" styleId="bsr"/>
							</td>
							<th>
								报送时间
							</th>
							<td>
								 <html:text property="kssj" styleId="kssj" style="width:90px"
									onclick="return showCalendar('kssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />--
								<html:text property="jssj" styleId="jssj" 
									onclick="return showCalendar('jssj','y-mm-dd');"   style="width:90px"
									onblur="dateFormatChg(this)" readonly="true" />
							</td>
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
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()">
									
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
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('/xgxt/xljkMjxyXlkhbs.do?method=xlkhbsOne&doType=view')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
									</logic:iterate>
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
						page="/sjcz/turnpage.jsp?form=xljkMjxyXlztbsForm"></jsp:include>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>	
  </body>
</html>
