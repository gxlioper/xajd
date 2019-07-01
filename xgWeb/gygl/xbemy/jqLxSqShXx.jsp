<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 头文件 -->
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body onload="xyDisabled('xy');">

		<html:form action="/XsgyglDispatch.do?method=jqLxSqShXx" method="post">
		    <input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
		    <input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />			
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userType" id="userType"
			       value="<bean:write name="userType"/>"/>																			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>公寓管理 - 审核 - 假期留校审核</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox" id="btn">
			    <ul>
			     <logic:notEqual name="xxdm" value="10690">
				<li> <a href="#" onclick="spbPrint() " class="btn_dy"> 审核表打印 </a> </li>
			     </logic:notEqual>
			      <li> <a href="#" onclick="dataExport()" class="btn_dc"> 导出数据 </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="go" />
			              <button class="btn_cx" id="search_go" 
			              	onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsgyglDispatch.do?method=jqLxSqShXx');">
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
							<th>
							    年级
							 </th>
							 <td>
									<html:select property="nj" style="width:90px" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
							</td>
							<th>
								学年
							</th>
							<td>
								<html:select  property="xn" style="width:90px" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>
								学期
							</th>
							<td>
								<html:select  property="xq" style="width:90px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>	
							</td>
						</tr>
						<tr>
							<th>								
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" style="width:180px" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>		
						<tr>
							<th>
								学号
							</th>
							<td>
									<html:text  property="xh" style="width:80px" styleId="xh"/>
							</td>
							<th>
								审核状态
							</th>
							<td colspan="3"> 
								<html:select property="yesNo" styleId="yesNo">
								    <html:option value=""></html:option>
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
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
			 		 	记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息，并可修改其审核状态；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">
					  <table summary="" class="datelist" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
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
								style="cursor:hand;background-color:
	    					<logic:iterate id="v" name="s" offset="0" length="1">
						    	<bean:write name="v"/>
						    </logic:iterate>" ondblclick="CheckDo()">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
			</div>
		</html:form>
		
  </body>
 
  <script type="text/javascript">
  function CheckDo(){
    var	w = 630;
	var	h = 500;	
	url = "/xgxt/XsgyglDispatch.do?method=jqLxSqSh";	
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
  }
  
   function spbPrint() {
     if(curr_row!=null){
	    document.forms[0].action = "/xgxt/XsgyglDispatch.do?method=jQlXsPb&pkValue="+curr_row.cells[0].getElementsByTagName("input")[0].value;
 	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
     }else{
        alert("请选择要打印的记录！\n（单击相应的行）");
        return false;
     }
   }
  </script>
</html>

