<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/ArmyCalendarArrange" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>军训管理 - 信息维护 - 军训日程安排</a>
				</p>
			</div>
		<div class="toolbox">
			 <div class="buttonbox">
			    <ul>
			    	<logic:equal value="yes" name="writeAble" scope="request">
						<li> <a href="#" onclick="showDataFrame('viewArmyCalendar.do','add',550,350)" class="btn_zj"> 增加 </a> </li>
					      <li> <a href="#"  onclick="showDataFrame('viewArmyCalendar.do','modi',550,350)" class="btn_xg"> 修改 </a> </li>
						<li> <a href="#" onclick="showDataFrame('viewArmyCalendar.do','del',550,350)" class="btn_sc"> 删除 </a> </li>
					</logic:equal>
				<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a> </li>
				<li> <a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a> </li>
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
			              	onclick="allNotEmpThenGo('/xgxt/ArmyCalendarArrange.do')">
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
					<input type="hidden" name="njV" id="njV" />
					<input type="hidden" name="ndV" id="ndV" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
						
						
						
		
						<tbody>
							<tr>
								<th align="left">
									年级
								</th>
								<td>
									<html:select property="nj" style="width:80px" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" style="width:90px" styleId="nd">
										<html:options collection="ndList" property="nd" labelProperty="nd" />
									</html:select>
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
				 		 </logic:notEmpty>
				    </span>
				    </h3>
			
				<logic:notEmpty name="rs">
						 <table summary="" class="datelist" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="showDataFrame('viewArmyCalendar.do','modi',550,350)">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
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
				</logic:notEmpty>
			</div>
				<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>