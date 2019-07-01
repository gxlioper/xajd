<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>	
			<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript">

function dataView(act){  
    if(curr_row==null){
       alert('请选择要操作的记录!\n(单击一行即可)');
       return false;
    }
    var url = "/xgxt/jhzy_gygl.do?method=qszModi&act="+act+"&pkValue=";
    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
    url += pk;
    showTopWin(url,"600","400");
} 
function del(){          
           var url = "/xgxt/jhzy_gygl.do?method=qszInfoDel&go=go"; 
		   var RowsStr="";		  		 
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }	    	  
		   }		   
		   if (RowsStr==""){
			   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   		   
		   if (confirm("确定要删除所选记录？")){
		      refreshForm(url);		          	
		   }	                  
 }
</script>
	</head>
	<body onload="xyDisabled('xy');">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 寝室长信息</a>
			</p>
		</div>
		<!-- 标题 end-->
			<html:form action="/jhzy_gygl" method="post">
				<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
				<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
				<input type="hidden" id="userType" name="userType" value="${userType}" />
				<input type="hidden" id="userName" name="userName" value="${userName}" />
				<input type="hidden" id="xyV" name="xyV"  value="" />
				<input type="hidden" id="zyV" name="zyV"  value="" />
				<input type="hidden" id="bjV" name="bjV"  value="" />
				<input type="hidden" id="pkVStr" name="pkVStr"  value="" />
		
				<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showTopWin('jhzy_gygl.do?method=qszAdd',600,400)"
									class="btn_xg">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="dataView('modi')"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="del()"
									class="btn_sc">删除</a>
							</li>		
						</logic:equal>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">导出</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="refreshForm('/xgxt/jhzy_gygl.do?method=qszManage&go=go');"">
											查询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级	
								</th>
								<td>
									<html:select property="nj" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>												
								</td>
								<th>
									学号	
								</th>
								<td>
									<html:text property="xh"  style="width:80px"/>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm"  style="width:80px"/>		
								</td>
								<th>
									
								</th>
								<td>
											
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									院系
								</th>
								<td>
									<html:select property="xydm" styleId="xy" style="width: 180px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>										
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width: 180px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>	
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width: 180px"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									
								</th>
								<td>
											
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									楼栋	
								</th>
								<td>
									<html:select property="lddm" styleId="lddm"
										onchange="getLcList()" >
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>									
								</td>
								<th>
									楼层
								</th>
								<td>
									<html:select property="lc" styleId="lc"
										onchange="getQshList2()" >
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									寝室
								</th>
								<td>
									<html:select property="qsh" styleId="qsh" >
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									是否在职
								</th>
								<td>
									<html:select property="sfzz" styleId="sfzz"
										onchange="">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>				
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center">
										<input type="checkbox" name="all" value="all" onclick="chec()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="dataView('view')">
										<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />"/>
											<input type="checkbox" name="pkV" value="<bean:write name="v"/>"/>
										</logic:iterate>										
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=jhzy_gyglForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
			</html:form>
			<div id="tmpdiv"></div>

	</body>
</html>
