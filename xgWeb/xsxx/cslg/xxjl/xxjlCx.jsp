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
	<script language="javascript" src="js/xgutil.js"></script>
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
	</script>
	</head>
	<body>
		<html:form action="xsxxCslgXsjl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="xsxx_cslg_xxjlb" />		
			<input type="hidden" name="primarykey_xh" id="primarykey_xh"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox">
			    <ul>
			    <logic:equal name="writeAble" value="yes">
			    <logic:notEqual name="userOnLine" value="student">
					<li> <a href="#" onclick="showOpenWindow('xsxxCslgXsjl.do?method=xxjlWh&doType=add',700,500)" class="btn_zj"> 增加 </a> </li>
			    </logic:notEqual>
			    <li> <a href="#" onclick="modi('xsxxCslgXsjl.do?method=xxjlUpdate&doType=modi')" class="btn_xg"> 修改 </a> </li>
				<logic:notEqual name="userOnLine" value="student">
					<li> <a href="#" onclick="dataBatch('xsxxCslgXsjl.do?method=xxjlCx&doType=del')" class="btn_sc"> 删除 </a> </li>
					<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> 导入数据 </a> </li>
				</logic:notEqual>
				</logic:equal>
				<li> <a href="#" onclick="wjcfDataExport('xsxxCslgXsjl.do?method=expDate')"  class="btn_dc"> 导出数据 </a> </li>
			    </ul>
			 </div>
				<div class="searchtab">
				<table width="100%" border="0">
				      <tfoot>
				        <tr>
				          <td colspan="6">
				            <div class="btn">
				              <button type="button" class="btn_cx" id="search_go" 
				              	onclick="allNotEmpThenGo('xsxxCslgXsjl.do?method=xxjlCx&doType=qry')" >
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
								<logic:equal name="userOnLine" value="student">
									<input type="text" value="${userName }" readonly="true"/>
									<html:hidden property="querylike_xh" styleId="xh"  value="${userName }"/>
								</logic:equal>
								<logic:notEqual name="userOnLine" value="student">
									<html:text property="querylike_xh" styleId="xh" />
								</logic:notEqual>
								
							</td>
							<th>	
								姓名
							</th>
							<td>
								<html:text property="querylike_xm" styleId="xm"/>
							</td>
							<th >年级</th>
							<td>
								<html:select property="queryequals_nj" style="width:90px;padding-left:80px" styleId="nj"
									onchange="initZyList();initBjList()">
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
									<html:select disabled="true" property="xydm" styleId="xydm" style="width:150px" value="${userDep }">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<html:hidden property="queryequals_xydm" value="${userDep }"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
									<html:select  property="queryequals_xydm" styleId="xy" style="width:150px"
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
								<html:select  property="queryequals_zydm" styleId="zy" style="width:150px"
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
								<html:select  property="queryequals_bjdm" styleId="bj" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
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
						<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" style="display:none" name="all" value="all" onclick="chec()"/>
									
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
								ondblclick="modi('xsxxCslgXsjl.do?method=xxjlOne&doType=view')" style="cursor:hand">
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
								page="/sjcz/turnpage.jsp?form=xsxxCslgXsjlForm"></jsp:include>
				</logic:notEmpty>
			</div>
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
		</html:form>
	</body>
</html>
