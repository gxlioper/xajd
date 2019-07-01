<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getWjcfInfo.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="javascript">
		function print() {
			var url = 'wjcf_nblg_cxprint.do';
			var pk = '';	
			if (curr_row != null) {
				pk =curr_row.cells[0].getElementsByTagName("input")[0].value;			
			}
			url += '?pkValue=';
			url += pk;
			window.open(url);
		}
		function printb() {
			var url = 'wjcf_gzdx_ssprint.do';
			var pk = '';	
			if (curr_row != null) {
				pk =curr_row.cells[0].getElementsByTagName("input")[0].value;			
			}
			url += '?pkValue=';
			url += pk;
			window.open(url);
		}
		//显示详细信息
function viewwjMoreinfo(doType,w,h){
var sty = "toolbar=no,location=no,directories=no,status=yes";
				sty += ",menubar=no,scrollbars=yes,resizable=yes,width=800,height=700,top=100";
				sty += ",left=200";
	    if(chkRow()){
			var url ="/xgxt/wjcf_viewmoreinfoanddel.do?doType=";
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			var realtab = document.getElementById("realTable").value;
			var xh = curr_row.cells[1].innerText.replace(" ","");
			var act = document.getElementById("act").value;
			var w = "0";
			var h = "0";
			if (doType == "view"){		    
		    	url += doType;
		    	url += "&pkValue="
		    	url += pkValue;
		    	url += "&realTable=";
		    	url += realtab;
		    	url += "&xh=";
		    	url += xh;
		    	url += "&act=";
		    	url += act;
		   	 	w = "700";
		    	h = "600";
		    	if(act == "discuss"||act == "decide"){
		    		url = url.replace("wjcf_viewmoreinfoanddel.do","wjcf_shscheck.do");		    		
		    		w = "800";
		    		h = "650";
		    	}
		    			    	
		    	//window.open(url,'', sty);
		    	showTopWin(url,w,h);
	       }
	    	if(doType == "modi"){
	    		   	  	           
        	     	getWjcfInfo.getInfoEx("wjcf_xsssb","xh||cfwh||cfsj",pkValue," sh='成立' ",function(data){
		         		if(data){
		            		alert("该处分申诉已申请,并经相关部门讨论已成立，不能再操作！");
			        		return false;
		         		}else{
		           			url = url.replace("wjcf_viewmoreinfoanddel.do","wjcf_xsshssq.do");
	    	       			url += doType;
	    	       			url += "&pkValue="
		           			url += pkValue;
		           			url += "&xh=";
		           			url += xh; 
	    	       			w = "880";
	    	       			h = "680";	    	       
		           			window.open(url,'',sty);		        		            	
		         		}		        
		         	}); 	    	       	    	       	           
            	
	    	}	
	  	} 	         
}
		function shcfdata() {
			if (curr_row == null) {
				alert('请选择要审核的记录，选择一行即可！');
				return false;
			}
			viewwjMoreinfo('view');
		}
	</script>
	</head>
	<body onload="xyDisabled('xy')" class="breakword">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a><bean:write name="tips" scope="request" /> </a>
			</p>
		</div>

		<html:form action="/wjcf_shscheck.do" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			
            <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">

				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					

						
							<logic:equal value="cx" name="act">
							<div class="buttonbox">
							<ul>
								<li>
									<a href="#"
										onclick="showTopWin('/xgxt/wjcf_xsshssq.do',850,600)"
										class="btn_zj"> 增加 </a>
								</li>


								<logic:equal value="yes" name="isSHGC">
									<li>
										<a href="#" onclick="viewMoreinfoshgc('modi',850,600)" class="btn_xg">
											修改 </a>
									</li>

								</logic:equal>
								<logic:notPresent name="isSHGC">
									<li>
										<a href="#" onclick="viewwjMoreinfo('modi',850,600)" class="btn_xg">
											修改 </a>
									</li>

								</logic:notPresent>


								<li>
									<a href="#"
										onclick="dataDel('/xgxt/wjcf_viewmoreinfoanddel.do?doType=del');"
										class="btn_sc"> 删除 </a>
								</li>
<%--								<li>--%>
<%--									<a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a>--%>
<%--								</li>--%>
								<li>
									<a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a>
								</li>
								<logic:notEqual value="11641" name="xxdm">
<%--									<li>--%>
<%--										<a href="#" onclick="printb()" class="btn_dy">打印/预览</a>--%>
<%--									</li>--%>
								</logic:notEqual>
								</ul>
								</div>
							</logic:equal>
							<logic:notEqual value="cx" name="act">
								<logic:equal value="11641" name="xxdm">
								<div class="buttonbox">
								<ul>
									<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" />申诉处理结果告知书打印 -->
									<li>
										<a href="#" onclick="jgprint()" class="btn_dy">打印/预览</a>
									</li>
									</ul>
									</div>
								</logic:equal>
								<logic:notEqual value="11641" name="xxdm">
								<div class="buttonbox">
								<ul>
									<!-- 淮海工<bean:message key="lable.xsgzyxpzxy" />申诉处理结果告知书打印 -->
									<li>
										<a href="#" onclick="shcfdata()" class="btn_sh">审核</a>
									</li>
									</ul>
									</div>
									</logic:notEqual>
							</logic:notEqual>
					
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/wjcf_shsdatasearch.do');">
											查询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
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
									<html:select property="nj"
										style="width:90px;background-color:#FFFFFF"
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
									
									<logic:equal value="stu" name="userType">
									<html:text property="xh" style="width:120px;" value="${userName }" readonly="true"/>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
									<html:text property="xh" style="width:120px;" />
									</logic:notEqual>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" style="width:120px;" />
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" styleId="xy" style="width:200px"
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
									<html:select property="zydm" style="width:200px" styleId="zy"
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
									<html:select property="bjdm" styleId="bj" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
								学年
								</th>
								<td>
									<html:select property="xn" style="width:90px;background-color:#FFFFFF">
									
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
									</html:select>
								</td>
								
								<th>
								年度
								</th>
								<td>
								<html:select property="nd" style="width:90px;background-color:#FFFFFF">
										
										<html:options collection="ndList" property="nd" labelProperty="nd"/>
									</html:select>
								</td>
								<th>
										审核结果
									</th>
									<td>
										<html:select property="sh" style="" styleId="sh">
											<html:option value=""></html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> <logic:notEmpty name="rs">
								记录数：${rsNum }
								<font color="blue"><bean:write name="cues"
										scope="request" /> </font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en" />"
											onclick="tableSort(this)" >
											<bean:write name="tit" property="cn" />
											<br />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;"
										ondblclick="viewwjMoreinfo('view')">
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
						</div>
					</logic:notEmpty>
				</div>
			</div>
		</html:form>
	</body>
</html>
