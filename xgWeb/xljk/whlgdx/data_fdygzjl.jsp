<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 头文件 -->
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetFdyList.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript">
		function del(url){
			var RowsStr="";	
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
		    	}
			}
			document.forms[0].cbVal.value = RowsStr;	
			if (RowsStr==""){
				alert("请选择要批量删除的记录！");
				return false;
			}else if(confirm("确定要将选中的信息删除吗？")){
				document.forms[0].action=url;
		    	document.forms[0].submit();
			}
		}
		
		function add(url){
			return showTopWin(url,700,500);
		}
		
		/**view*/
		function chkAssisOne(url) {
			if (curr_row == null) {
				alert("请选择要选择的行数据\n点击双击");
				return false;
			} 
			if($("userType")){
				var userType = $("userType").value;
				if(userType != "stu" && userType != "fdy"){
					//把url抹掉
					url += "&checkfdy=yes";   //将checkfdy蔓延
					if($("seeFdyInfo")){
						url += "&pkVal=";
						url += curr_row.getElementsByTagName("input")[0].value;
						showTopWin(url,700,500);
					}else{
						url = "whlgdx_xljk.do?method=fdygzjlwh&go=go&doType=search";
						url += "&checkfdy=yes";
						url += "&pkVal=";
						url += curr_row.getElementsByTagName("input")[0].value;
						refreshForm(url);
					}	
				}else{
					url += "&pkVal=";
					url += curr_row.getElementsByTagName("input")[0].value;
					showTopWin(url,700,500);
				}
			}
		}	
				
		function modi(url){
			if((curr_row == null) || (curr_row == "")){
				alert("请选择要修改的记录！");
				return false;
			}
			url += "&pkVal=";
			url += curr_row.getElementsByTagName("input")[0].value;
			return showTopWin(url,700,500);
		}
		
		function dataExport2() {
			document.forms[0].action = "/xgxt/whlgdx_xljk.do?method=common_exp";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function disableSelect(){
			var userType = $("userType").value;
			var ele = "";
			if (userType == "xy") {
				ele = "xy";
			}else if(userType == "fdy"){
				ele = "xy-zgh";
			}
			var tmp = ele.split("-");
			for (i = 0; i < tmp.length; i++) {
				if($(tmp[i])){
					document.getElementById(tmp[i]).disabled = true;
				}	
			}
		}
	</script>
	</head>
	<body onload="initXnList();initXyList();disableSelect();">
		<html:form action="whlgdx_xljk.do?method=xlzxjyhdwh" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>心理健康 - <bean:message key="lable.xsgzyxpzxy" />心理健康教育活动 - 辅导员工作记录 
					</a>
					</p>
				</div>
			<%--
			xx //xy //fdy //stu
			--%><input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<logic:present name="seeFdyInfo" scope="request">
				<input type="hidden" id="seeFdyInfo" name="seeFdyInfo" 
					value="<bean:write name="seeFdyInfo" scope="request"/>" />	
			</logic:present>	
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="xnV" value="<bean:write property="xn" name="form"/>" id="xnV"/>
			<input type="hidden" name="xyV" value="<bean:write property="xydm" name="form"/>" id="xyV"/>
			<input type="hidden" name="zghV" value="<bean:write property="zgh" name="form"/>" id="zghV"/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox">
			    <ul>
				    <logic:equal value="yes" name="writeAble" scope="request">
				    		 <li> <a href="#"  onclick="del('whlgdx_xljk.do?method=fdygzjlwh&doType=del');" class="btn_sc"> 删除 </a> </li>
				     		 <li> <a href="#" onclick="dataExport2()" class="btn_dc"> 导出 </a> </li>
				    </logic:equal>
				 
					 <logic:equal value="fdy" name="userType" scope="request">
						<li> <a href="#"  onclick="add('whlgdx_xljk.do?method=fdygzjlpre');" class="btn_zj"> 增加工作记录 </a> </li>
			            <li> <a href="#"  onclick="del('whlgdx_xljk.do?method=fdygzjlwh&doType=del');" class="btn_sc"> 选中删除记录 </a> </li>
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
							<button  style="height:20px" id="search_go"
								onclick="allNotEmpThenGo('/xgxt/whlgdx_xljk.do?method=fdygzjlwh&go=go&doType=search')">
								查 询
							</button>
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	重 置
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
			 
				<tbody>
						<tr>
							<th align="left">
								学年
							</th>
							<td>
								<html:select property="xn" name="form"
								styleId="xn" style="width:90px;padding-left:80px">
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" style="width:180px" styleId="xy" 
									onchange="initFdyList();">
								</html:select>
							</td>
							<th>
								辅导员
							</th>
							<td>	
								<html:select property="zgh" name="form"
								styleId="zgh" style="width:90px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="fdyList" property="zgh"
										labelProperty="xm"/>
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
			 		 	<font color="blue">提示：双击一行可以查看详细信息，并可以修改信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
					<logic:notEmpty name="rs">
						 <table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr>
									    <td>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="${tit.en}" onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/whlgdx_xljk.do?method=fdygzjlpre&doType=view')">
										<td><input type="checkbox" id="pk" name="pk" 
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
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
					<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="ok" scope="request">
			<logic:match value="ok" name="ok">
				<script language="javascript">
		   			alert("删除成功！");  	
		        </script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
		        	alert("删除失败！");
		        </script>
			</logic:match>
		</logic:present>		 
	</body>
</html>
