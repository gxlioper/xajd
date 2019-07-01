<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="bjDisabled('nj-xy-zy-bj');myxyDisabled('xy');removeXnXq();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/bottomButton.js"></script>		
		<script type="text/javascript">
		function myxyDisabled(ele) {
		    var userT = document.getElementById("userType").value;
			if (userT == "xy"||userT =="stu") {
				var tmp = ele.split("-");
				for (i = 0; i < tmp.length; i++) {
					document.getElementById(tmp[i]).disabled = true;
				}
			}
		}
		function hdxfhz(){
		    	     var url = "/xgxt/csmz_sztz.do?method=hdxfhz";	        
			         document.forms[0].action = url;
			         document.forms[0].target = "_blank";
			         document.forms[0].submit();
			         document.forms[0].target = "_self";
		}
		function tzcjhz(){
    	     var url = "/xgxt/csmz_sztz.do?method=tjlgTzcj&pkValue=";
    	     if(curr_row != null){
	    	     url+=curr_row.getElementsByTagName('input')[0].value;       
		         document.forms[0].action = url;
		         document.forms[0].target = "_blank";
		         document.forms[0].submit();
		         document.forms[0].target = "_self";
	         }else{
	         	alert("请先选择一条记录!");
	         }
		}
		</script>		
    <html:form action="/csmz_sztz.do?method=data_search" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
	        <input type="hidden" name="userType" id="userType" 
		        value="<bean:write name="userType" scope="request"/>">	
		     <input type="hidden" name="userName" id="userName" 
		        value="<bean:write name="userName" scope="session"/>">		
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips}</a>
				</p>
			</div>

			<div class="toolbox">
				 <!-- 按钮 -->
				 <div class="buttonbox">
				    <ul>
				    <logic:equal value="yes" name="writeAble" scope="request">
					<li> <a href="#" onclick="MyMoreDo('add')" class="btn_zj"> 增加 </a> </li>
				    <li> <a href="#" onclick="MyMoreDo('modi')" class="btn_xg"> 修改 </a> </li>
					<li> <a href="#" onclick="MyMoreDo('del')" class="btn_sc"> 删除 </a> </li>
					</logic:equal>
					<li> <a href="#" onclick="dataExport()" class="btn_dc"> 导出数据 </a> </li>
				    <logic:equal value="12104" name="xxdm">
						 <!-- 柳州职业技术<bean:message key="lable.xsgzyxpzxy" />学校代码 -->
						   <li> <a href="#" onclick="hdxfhz()" class="btn_tj"> 第二课堂汇总表 </a> </li>
					</logic:equal>
					<!-- 天津理工 中环学院 学生拓展成绩汇总 2011.3.24 qlj -->
					<logic:equal name="xxdm" value="13897">
						   <li> <a href="#" onclick="tzcjhz()" class="btn_tj"> 拓展成绩统计 </a> </li>
					</logic:equal>
				    </ul>
				 </div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
					      <tfoot>
					        <tr>
					          <td colspan="6">
					            <div class="btn">
					               <input type="hidden" name="go" value="a" />
					              <button class="btn_cx" id="search_go" 
					              	onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
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
										<html:select property="nj" style="width:80px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										学号
									</th>
									<td>
										<html:text property="xh" style="width:85px"></html:text>
									</td>
									<th>
										姓名
									</th>
									<td>
										<html:text property="xm" style="width:85px"></html:text>
									</td>
								</tr>
								<tr>
									<th align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										专业
									</th>
									<td>
										<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									<th>
										班级
									</th>
									<td>
										<html:select property="bjdm" style="width:180px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>	
								<logic:equal value="no" name="ptcx">
								<tr>
									<th>
										学年
									</th>
									<td>
										<html:select property="xn" style="width:100px" styleId="xn"
											onchange="genNdList(this)">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
									</th>
									<td>
									</td>
							</logic:equal>						
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
										<!-- 柳州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
										<logic:equal name="xxdm" value="12104">
											<logic:iterate id="tit" name="topTr" offset="2" length="11">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" >
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
											<td>
											本学年活动总分
											</td>
										</logic:equal>
										<!-- 非柳州职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
										<logic:notEqual name="xxdm" value="12104">
											<logic:iterate id="tit" name="topTr" offset="2">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" >
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:notEqual>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;"
											ondblclick="MyMoreDo('view')">
											<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td >
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>		 
							<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
						    	<script type="text/javascript">
														$('choose').className="hide";
								</script>
				</logic:notEmpty>
			</div>
	</html:form>
    </body>
</html>	
