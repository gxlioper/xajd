<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	</head>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
		<script language="javascript">
			function selectLb(){
	
		var lbid = "jbdm";
		var xxid = "zwdm";
		
		var tableName = "sxjy_bjgbzlb"; 
		var dm = "bjgbdm"; 
		var mc = "bjgbmc";
		var msg = "";
		var pk = "GBZLJB";
		var pkValue = $(lbid).value;
		
		if(pkValue == ""){
			pk = "";
		}
		
		getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(xxid);
				DWRUtil.addOptions(xxid,data,"dm","mc");
				$(xxid).options[0].value = "";
			}
			});
		}
			
		function bjxsgbdwExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			//showOpenWindow('configureExportData.do?method=choiceExportFields&tableName='+tableName,1000,600);
			customExport("show_bjxsgbdw_list.do", bjxsgbdwExportData);
			}
				
			
				
		// 导出方法
		function bjxsgbdwExportData() {
			//setSearchTj();//设置高级查询条件
			var url = "show_xsgbdwExport_list.do?dcclbh=" + "show_bjxsgbdw_list.do";//dcclbh,导出功能编号
			//url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}	
		</script>
		<html:form action="/show_classStudentCadre_list.do" method="post">
		<input type="hidden" name="xyV" id="xyV" value="" />
<input type="hidden" name="zyV" id="zyV" value="" />
<input type="hidden" name="bjV" id="bjV" value="" />
<input type="hidden" name="njV" id="njV" value="" />
<input type="hidden" name="xhV" id="xhV" value="" />
<input type="hidden" name="xmV" id="xmV" value="" />
<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
<input type="hidden" name="mklx" id="mklx" value="${mklx }"/>
<input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
<input type="hidden" name="userType" id="userType" value="${userType }"/>
<input type="hidden" name="pk" id="pk" value="${pk }"/>
<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
<input type="hidden" name="doType" id="doType" value="${doType }"/>
<input type="hidden" name="userName" id="userName" value="${userName }"/>
<input type="hidden" name="userStatus" id="userStatus" value="${userStatus }"/>
<input type="hidden" name="tableName" id="tableName" value="${tableName }"/>
<input type="hidden" name="message" id="message" value="${message }"/>

<input type="hidden" name="searchType" id="searchType" value="${searchType }"/>

<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>"/>
<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
<input type="hidden" id="gyglyQx" name="gyglyQx" value="${gyglyQx}" />

<input type="hidden" id="path" name="searchModel.path" value="${path }"/>

<!-- 排序 -->
<input type="hidden" id="arrange" name="arrange" value=""/>
<input type="hidden" id="fashion" name="fashion" value="asc"/>

<!-- 分页相关 -->
<input type="hidden" id="editPageSize" name="editPageSize" value=""/>

<input type="hidden" name="ryfw" id="ryfw" value="${ryfw }"/>

<!-- 学生信息加载按钮 -->
<button type="button" id="btn_setXsxx" onclick="" style="display:none">刷新</button>
			<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
							value="sxjy_bjgbxxb" />
					
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>思政队伍 - 信息维护 - 班级学生干部队伍</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox" id="btn">
			    <ul>
			    <logic:equal name="writeAble" value="yes">
				<li> <a href="#" onclick="showDialog('', 660, 500, 'classDuty_Cadre_updata.do?act=bjzw&doType=add');" class="btn_zj"> 增加 </a> </li>
			      <li> <a href="#" onclick="sxjyReportupdata('bjzw')" class="btn_xg"> 修改 </a> </li>
				<li> <a href="#" onclick="sxjyReportDel('bjzw')" class="btn_sc"> 删除 </a> </li>
				<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a> </li>
				</logic:equal>
				 <li> <a href="#" onclick="bjxsgbdwExportConfig()" class="btn_dc"> 自定义导出 </a> </li>
				<!--<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
				<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出</a></li> -->
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="a" />
			              <button type="button" class="btn_cx" id="search_go" 
			              	onclick="szdwBjMust('show_classStudentCadre_list.do?act=bjzw&part=N170302')">
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
									<html:text property="xh" style="width:150px"/>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" style="width:150px"/>
								</td>
								<th>
									年级
								</th>
								<td>
									<input type="hidden" name="njV" value=""/>
									<html:select property="nj" styleId="nj" style="width:150px" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal name="userType" value="xy" scope="session">
									<input type="hidden" name="xyV" value=""/>
									<html:select property="xy" styleId="xy" style="width:150px" onchange="initZyList();initBjList()" >
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:equal>
									
									<logic:notEqual name="userType" value="xy" scope="session">
									<input type="hidden" name="xyV" value=""/>
									<html:select property="xy" styleId="xy" style="width:150px" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:notEqual>
								</td>
									<th align="left" nowrap>
								    专业
								</th>
								<td>
									<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
									<html:select property="zy" styleId="zy" style="width:150px" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
									<html:select property="bj" styleId="bj" style="width:150px" >
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>	
									职务
								</th>
								<td>
									<html:select property="zwdm" style="width:150px" styleId="zwdm">
									<html:option value=""></html:option>
									<html:options collection="bjzwList" property="bjgbdm" labelProperty="bjgbmc" />
									</html:select>
								</td>
								<th>
									职务级别
								</th>
								<td>
									<html:select property="jbdm" style="width:150px" styleId="jbdm" onchange="selectLb()">
									<html:option value=""></html:option>
									<html:options collection="bjzwTypeList" property="jbdm" labelProperty="jbmc" />
									</html:select>
								</td>
								<logic:equal name="xxdm" value="11647">
									<th>
									任职情况
									</th>
									<td>
									<html:select property="rzqk" style="" styleId="zwdm">
									<html:option value=""></html:option>
									<html:option value="no">曾任</html:option>
									<html:option value="yes">现任</html:option>
									</html:select>
									</td>
								</logic:equal>
								<logic:notEqual name="xxdm" value="11647">
									<th></th><td></td>
								</logic:notEqual>
								</tr>
						</tbody>
					</table>
				</div>
				</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rsList">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rsList">
			 		 	<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
	
			<logic:notEmpty name="rsList">
				 <div class="con_overlfow">
					 <table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rsList" id="s">
							<tr onclick="rowOnClick(this)" ondblclick="sxjyReportupdata('bjzwview')" style="cursor:hand">
								<td>
									<input type="checkbox" value="<logic:iterate id="v" name="s" offset="0" length="1">
										<bean:write name="v"/>
									</logic:iterate>" />
									
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
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
					 <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=SxjyForm"></jsp:include>
			</logic:notEmpty>
			</div>
		</html:form>
		<logic:equal value="ok" name="ok">
			<script type="text/javascript">
				alert("操作成功！");
			</script>
		</logic:equal>
	</body>
</html>

