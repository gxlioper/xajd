<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<%@ include file="/syscommon/pagehead_xg.ini"%>	
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxxtjDWR.js'></script>			
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>		
	<script language="javascript" src="js/webPrint.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>	
	<script>
		function jtxxDr(){
			$('realTable').value='xsfzxxb';
			impAndChkData();
			$('realTable').value='xsxxb';
			return false;
		}
	
	
		function ljsdaUpdate(url,w,h){	
			var pk="";	
			if(curr_row == null ){
					alert("请选择一行记录！\n单击一行即可!");
					return false;
			} 	
					
			pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
			url+=pk;
			//判断是否在校库
			url+="&sfzxk="+$("sfzxk").value;
			if(w==""||w==null){
				w = 800;
			}
			if(h==""||h==null){
				h = 600;
			}
			showTopWin(url,w,h);		
		}
		
		function addInfo(){
			if(curr_row == null){
				alert("此操作需要有选中的行，请点击要添加备注的行！");
				return false;
			}
			showTopWin("addStuInfo.do?xh=" + curr_row.cells[1].innerText,400,300,false);
		}
	
		function chec(){
	      for(i=0;i<document.getElementsByName("pk").length;i++){
	      	if(!document.getElementsByName("pk")[i].disabled){
	      		document.getElementsByName("pk")[i].checked=document.getElementsByName("xsxx")[0].checked;
	      	}
	      }
	    }
	   
	       
		function batch(url,oper){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	}
			}
			document.forms[0].delPk.value = RowsStr;
			
			if (RowsStr=="!!"){
				alert("请选择要批量操作的记录！");
				return false;
			}
			
			if (!confirm("确定要操作所选记录？")){
				return false;
			}
			if(oper=="del"){
				refreshForm(url);
			}else{
				url += "&pk=";
				url += RowsStr;
				showTopWin(url,400,300);
			}
		}
	
		function batchOper(url){
			var RowsStr="!!";		
				for (i=0; i<document.getElementsByName("pk").length; i++){
			    	if(document.getElementsByName("pk")[i].checked){
			    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
			    	}
				}
				document.forms[0].delPk.value = RowsStr;
				
				if (RowsStr=="!!"){
					alert("请选择要批量操作的记录！");
					return false;
				}
				
				if (!confirm("确定要操作所选记录？")){
					return false;
				}
				
				url += "&pk=";
				url += RowsStr;
				refreshForm(url);	
		}
	
		function check_user(){
		
			var user=document.all['userType'].value;
			
			if($("xydm")){
				if("xy"==user){
					document.getElementById('xydm').disabled=true;
				}else if("xx"==user){
					document.getElementById('xydm').disabled=false;
				}
			}
		}
		
		function xy_dataExport1(){
			var url='/xgxt/expData.do?tableName=view_xsjbxx';
			var xydm=document.getElementById('xydm').value;
			url=url+'&xydm='+xydm;
			dataExport1(url);
		}
		
		function showTr(){
			var xxdm = document.getElementById("xxdm").value;
			var length = 12;
			for(var i=0; i<length; i++){
				if(ele('temDiv'+(i+1))){
					ele('temDiv'+(i+1)).style.display=(ele('temDiv'+(i+1)).style.display =='none')?'':'none';
				}
			}
		}		
		
		function getValue(){
			if(document.getElementById("jgshen").value!=""){
				var shenV = document.getElementById("jgshen").options[document.getElementById("jgshen").selectedIndex].text;
				document.getElementById("jgz").value='%' + shenV + '%';
			}
			if(document.getElementById("jgshi").value!=""){
				var shiV = document.getElementById("jgshi").options[document.getElementById("jgshi").selectedIndex].text;
				document.getElementById("jgz").value += '%' + shiV + '%';
			}
			if(document.getElementById("jgxian").value!=""){
				var xianV = document.getElementById("jgxian").options[document.getElementById("jgxian").selectedIndex].text;
				document.getElementById("jgz").value += '%' + xianV + '%';
			}
		}
		
		function writeCondition(){			
			var xxdm = document.getElementById("xxdm").value;
			ele = 'nj-xh-xm-xy-zy-bj-xz-xjztm-sfzx-sfyby-byny-pycc-xb-sfzh-ssbh-csrq-sjhm-jtcyxm-mz-zzmm-jg-syd-ksh-jgshen-jgshi-jgxian-sdyshen-sydshi-sydxian-sfgat-sfssmz-sfzxk';
			var strs = ele.split('-');						
			var tmp = "";
			for(var i=0; i<strs.length; i++){
				if(document.getElementById(strs[i]) && document.getElementById(strs[i]).value!= undefined){
					if(document.getElementById(strs[i]).value != ''){						
						if(strs[i] == 'xy'){
							tmp += "@xydm!!" + document.getElementById(strs[i]).value;
						}else if(strs[i] == 'zy'){
							tmp += "@zydm!!" + document.getElementById(strs[i]).value;
						}else if(strs[i] == 'bj'){
							tmp += "@bjdm!!" + document.getElementById(strs[i]).value;
						}else{
							tmp += "@" + strs[i]+ "!!" + document.getElementById(strs[i]).value;
						}
					}
				}
			}
			
			document.getElementById('condition').value = tmp;
		}
		
		
		function zjjd_stuTable(type){
		    var bjdm = "";
		    if($("bjdm")){
		       bjdm = $("bjdm").value;
		    }
		    if(bjdm==""){
		       alert("请选择要统计班级！");
		       return false;
		    }
		   	var url = "/xgxt/stu_info_add.do?method=zjjd_zjjd_stuTable&tableType="+type;
		   	document.forms[0].action = url;
		   	document.forms[0].target = "_blank";
		   	document.forms[0].submit();
		  	 document.forms[0].target = "_self";
		}
	   
	   //杭职院打印成绩报告单
	   function hzy_cjbgdprint(){
	      	var RowsStr="";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
			 if(document.getElementsByName("pk")[i].checked){
			    RowsStr+=document.getElementsByName("pk")[i].value+"!!";
			 }
		}
		document.forms[0].delPk.value = RowsStr;				
		if (RowsStr==""){
			alert("请选择要打印的记录！");
			return false;
		}
		if(RowsStr.split("!!").length-1>2){
			alert("一次只能打印两条记录！");
			return false;
		}
		var url = "/xgxt/cjbgdPrint.do?method=hzy_cjbgdprint&pkValue="+$("delPk").value;
		showOpenWindow(url,800,600);		
	   }
	   
	   //打印成绩单
	   function printCjd(){
	   	//判断是否选择了数据
	   	var RowsStr="";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
			 if(document.getElementsByName("pk")[i].checked){
			    RowsStr+=document.getElementsByName("pk")[i].value+"!!";
			 }
		}
		if (RowsStr==""){
			alert("请选择要打印的记录！");
			return false;
		}
		var url = "xsxxgl.do?method=czxxCjdPrint&pkV=" + RowsStr;
		showOpenWindow(url,800,600);
	   }
	   
	   function init(){
	   	if(val('ctypeV') != null && val('ctypeV') != ''){
	   		ele('ctype').checked=true;
	   		showTr();
	   	}
	   }
	   
	   function printXjqrd(){
	   
	   		var checkbox = jQuery('input[type=checkbox][name=pk]:checked');
	   		
	   		if (checkbox.length > 0){
	   		
	   			var url = "moralCard.do?method=xjqrdPrint";
			   	document.forms[0].action = url;
			   	document.forms[0].target = "_blank";
			   	document.forms[0].submit();
		  	 	document.forms[0].target = "_self";
	   		
	   		} else {
	   			alert('请选择您要打印学籍确认单的学生!');
	   			return false;
	   		}
	   }
	   
	   //打印学生信息卡西西
	   function printXsxxk(){
	   		
	   		var len=jQuery("[name=pk]:checked").length;
	   		if(len!=1){
	   			alertInfo("请勾选一条学生信息，再进行该操作！");
	   			return false;
		  	}else{
		   		var xh=jQuery("[name=pk]:checked").eq(0).val();
		  		var url = "xsxx_gdjs_xsxxk.do?method=printXsxxExcel&xh="+xh;
			   	document.forms[0].action = url;
			   	document.forms[0].target = "_blank";
			   	document.forms[0].submit();
		  	 	document.forms[0].target = "_self";
		  	}
	   }
	   
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo&isChecked=false');
		}
	</script>

</head>
	<body onload="check_user();initXnList();init();">		
			<html:form action="/stu_info_query.do?method=stuInfo" method="post">
				<div id="tmpdiv"></div>
				<div style="display:none">
					<select id='xn' name='xn' style="display:none"></select>
					<html:select property="xq" style="display:none" styleId="xq">
						<html:option value="">--请选择--</html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
				</div>

				<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
				<input type="hidden" id="userType" name="userType" value="${userSpecType }" />
				<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
				<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
				<input type="hidden" id="userName" name="userName" value="${userName }" />
				<input type="hidden" id="ctypeV" value="${ctype}"/>

				<logic:equal value="yes" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="xsxxb"/>
				</logic:equal>
				<logic:equal value="no" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="bks_xsjbxx"/>
				</logic:equal>
				<input type="hidden" id="tableName" name="tableName" value="view_xsxxb"/>  
				<input type="hidden" id="delPk" name="delPk" value="pk" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />
				<input type="hidden" name="sfzxk" id="sfzxk" value="${sfzxk}" />
				<input type="hidden" name="jgz" value="" />
				<input type="hidden" name="mes" value="${mes}" />
				<input type="hidden" name="condition" value="" id='condition' />
				<input type="hidden" name="path" value="${path }" />
				
				<!-- 高级查询 必须 -->
				<input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
				<logic:notEmpty name="sfzxk">
					<input type="hidden" id="path" name="searchModel.path" value="stu_info_query.do?method=stuInfo&sfzxk=no"/>
				</logic:notEmpty>
				<logic:empty name="sfzxk">
					<input type="hidden" id="path" name="searchModel.path" value="stu_info_query.do?method=stuInfo"/>
				</logic:empty>
				
		        <div class="tab_cur">
		          <p class="location"> <em>您的当前位置:</em>
			          <a>
			          <logic:notEmpty name="title">${title}</logic:notEmpty>
			          <logic:empty name="title">学生信息-学生信息-历史学生信息</logic:empty>
			          </a>
		          </p>
		        </div>

				<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
					  <!--学生信息可维护-->
					  <logic:notEmpty name="userOper">
					  <logic:equal name="userType" value="admin" >
						<li><a href="#" onclick="showTopWin('stu_info_add.do?method=showStuInfo&oper=add',800,600,false);return false;" class="btn_zj">增 加</a></li>
						<li><a href="#" onclick="batch('stu_info_add.do?method=stuInfoDelete','del');return false;" class="btn_sc">删 除</a></li>
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入基本信息</a></li>	
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a></li>						  
					  </logic:equal>
					  <logic:equal name="userType" value="xx">
						<li><a href="#" onclick="showTopWin('stu_info_add.do?method=showStuInfo&oper=add',800,600,false);return false;" class="btn_zj">增 加</a></li>											
						<li><a href="#" onclick="batch('stu_info_add.do?method=stuInfoDelete','del');return false;" class="btn_sc">删 除</a></li>
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入基本信息</a></li>	
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a></li>					  
					  </logic:equal>
					  <logic:notEqual name="userType" value="admin">
					  <logic:notEqual name="userType" value="xx">
					  	<li><a href="#" onclick="ljsdaUpdate('stu_info_add.do?method=showStuInfo&oper=update&xh=',800,600);return false;" class="btn_xg">修 改</a></li>
					  	<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>	
					  </logic:notEqual>
					  </logic:notEqual>
					  </logic:notEmpty>
					  <!--end学生信息可维护-->
					  </logic:equal>
					  <!--end读写权-->
		
				    </ul>
				  </div>				  
				  <!-- end按钮 -->
				</div>

				<!-- 内容显示区开始 -->
				<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
					
					<!-- new 版本 -->
					<logic:equal name="superSearch" value="yes">
						<%@ include file="/comm/search/superSearchArea.jsp"%>
					</logic:equal>
					
					<!-- old 版本 -->
					<logic:equal name="superSearch" value="no">
					<!-- From内容 -->
<!--							<div style="float:left;">-->
<!--								<div class="listbox" style="width:155px;float:left">-->
<!--									<div class="menulist">-->
<!--									层start-->
<!--									<div class="menutitle">-->
<!--									    <h4 style="height:30px;line-height:30px;padding-left:40px;">部门列表</h4>-->
<!--									<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px;">-->
<!--									<ul>-->
<!--									  <li class="Opened" id="xxid"><span onclick="clickBm(this,'xxid')">${xxmc}</span></li>-->
<!--									</ul>-->
<!--									</div>-->
<!--									<script type="text/javascript">-->
<!--									-->
<!--									var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");-->
<!--									//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif","menutitle");-->
<!--			 						-->
<!--									</script>-->
<!--									</div>-->
<!--									层end-->
<!--									</div>-->
<!--								</div>-->
<!--							</div>-->
<!--							<div style="float:right;width:630px;">-->
							  <div class="searchtab">
								<table width="100%" border="0">
							      <tfoot>
							        <tr>
									  <th nowrap="nowrap">
										<input type="checkbox" name="ctype" value="checked" onclick="showTr()" id="moreTj"/>						
										更多条件
									  </th>
							          <td colspan="5">
							            <div class="btn">
										  <input type="hidden" name="go" value="a" />
							              <button type="button" class="btn_cx" title="" id="search_go" onclick="allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo&isChecked='+$('moreTj').checked);">
											查 询
										  </button>
										  <button type="button" id="cz"
												onclick="czSearchCond('nj-xh-xm-xy-zy-bj-xz-xjztm-sfzx-sfyby-byny-pycc-xb-sfzh-ssbh-csrq-sjhm-jtcyxm-jtdzshen-jtdzshi-jtdzxian-qqhm-mz-zzmm-jg-jgshen-jgshi-jgxian-sfb-jfqk-hkszd-sydshen-sydshi-sydxian-ksh-syd-sfgat-sfssmz');return false;">
												重 置
										  </button>
							            </div>
							          </td>
							        </tr>
							      </tfoot>
								  <tbody>
								  
							      <tr id="gdDiv1">
									<th width="15%">年级</th>
									<td width="15%">
										<%--杭州职业技术学院--%>
										<logic:equal value="12872" name="xxdm">
											<%--非在校库--%>
											<logic:notEmpty name="sfzxk">
												<logic:equal value="no" name="sfzxk">
													<html:select property="nj" styleId="nj"
														onchange="initZyList();initBj('by');" style="width:80px">
														<html:option value=""></html:option>
														<html:options collection="njList" property="nj"
															labelProperty="nj" />
													</html:select>
												</logic:equal>
											</logic:notEmpty>
											<%--end非在校库--%>

											<logic:empty name="sfzxk">
												<html:select property="nj" styleId="nj"
													onchange="initZyList();initBj('zx');"  style="width:80px">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
											</logic:empty>
										</logic:equal>
										<%--end杭州职业技术学院--%>
			
										<logic:notEqual value="12872" name="xxdm">
											<html:select property="nj" styleId="nj"
												onchange="initZyList();initBjList();"  style="width:80px">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</logic:notEqual>									
									</td>
									<th width="15%">
										学号
									</th>
									<td width="15%">
										<html:text style="width:150px"
											property="xh"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId='xh' />
									</td>
									<th width="15%">
										姓名
									</th>
									<td width="">
										<html:text style="width:150px"
											property="xm"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId='xm' />
									</td>
								</tr>					
								<tr id="gdDiv2">
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<%--杭州职业技术学院--%>
										<logic:equal value="12872" name="xxdm">
											<logic:notEmpty name="sfzxk">
												<logic:equal value="no" name="sfzxk">
													<html:select property="xydm" 
														styleId="xy" onchange="initZyList();initBj('by');" style="width:150px">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
												</logic:equal>
											</logic:notEmpty>
											<logic:empty name="sfzxk">
												<html:select property="xydm"  styleId="xy"
													onchange="initZyList();initBj('zx');" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</logic:empty>
										</logic:equal>
										<%--end杭州职业技术学院--%>
			
										<logic:notEqual value="12872" name="xxdm">
											<html:select property="xydm"  styleId="xy"
												onchange="initZyList();initBjList();" style="width:150px">
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
									<%--杭州职业技术学院--%>
									<logic:equal value="12872" name="xxdm">
										<logic:notEmpty name="sfzxk">
											<logic:equal value="no" name="sfzxk">
												<html:select property="zydm" 
													styleId="zy" onchange="initBj('by');" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="zyList" property="zydm"
														labelProperty="zymc" />
												</html:select>
											</logic:equal>
										</logic:notEmpty>
										<logic:empty name="sfzxk">
											<html:select property="zydm" styleId="zy" style="width:150px"
												onchange="initBj('zx');">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:empty>
									</logic:equal>
									<%--end杭州职业技术学院--%>
			
									<logic:notEqual value="12872" name="xxdm">
										<html:select property="zydm"  styleId="zy" style="width:150px"
											onchange="initBjList();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</logic:notEqual>
									</td>
									
									<th>
										班级
									</th>
									<td>
										<html:select property="bjdm" styleId="bj" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr id="temDiv3" style="display:none">
									<th>
										学制
									</th>
									<td>							
										<html:select property="xz" styleId="xz" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xzList" property="xz" labelProperty="xz"/>
										</html:select>
									</td>
									<th>
										学籍状态
									</th>
									<td>
										<html:select property="xjzt" styleId="xjztm" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
										</html:select>
									</td>
									<th>
										是否在校
									</th>
									<td>
										<%--非西北民族大学--%>
										<logic:notEqual value="10742" name="xxdm">
										<html:select property="sfzx" styleId="sfzx" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="在校">在校</html:option>
											<html:option value="不在校">不在校</html:option>
										</html:select>
										</logic:notEqual>
										<%--end非西北民族大学--%>
			
										<%--西北民族大学--%>
										<logic:equal value="10742" name="xxdm">
											<html:select property="sfzx" style="width:80px">
												<html:option value=""></html:option>
												<html:option value="1">在校</html:option>
												<html:option value="0">不在校</html:option>
											</html:select>
										</logic:equal>
										<%--end西北民族大学--%>
									</td>				
								</tr>
								<tr id="temDiv4" style="display:none">
									<th>
										是否毕业
									</th>
									<td>
										<html:select property="sfyby" styleId="sfyby" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="是">是</html:option>
											<html:option value="否">否</html:option>
										</html:select>			
									</td>
									<th>
										毕业时间
									</th>
									<td>
										<html:text property="byny" styleId="byny" style="width:150px"
										onclick="return showCalendar('byny','y-mm-dd');" />
									</td>
									<th>
										培养层次
									</th>
									<td>
										<html:text property="pycc" 
												styleId="pycc"
												style="width:150px"
												onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');" />
									</td>
								</tr>
								<tr id="temDiv5" style="display:none">
									<th>
										性别
									</th>
									<td>
										<html:select property="xb" styleId="xb" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="男">男</html:option>
											<html:option value="女">女</html:option>
										</html:select>
									</td>
									<th>
										身份证号
									</th>
									<td>
										<html:text property="sfzh" style="width:150px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId="sfzh"  onkeyup="value=value.replace(/[^\d|X|x]/g,'')" />
									</td>
									<th>
										宿舍编号
									</th>
									<td>
										<html:text property="ssbh" style="width:150px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId='ssbh' />
									</td>						
								</tr>
								
								<tr id="temDiv6" style="display:none">
									<th>
										出生日期
									</th>
									<td>
										<html:text property="csrq" style="width:150px"
												onclick="return showCalendar('csrq','y-mm-dd');"
												styleId="csrq" onclick="return showCalendar('csrq','y-mm-dd');"/>
									</td>
									<th>
										手机号码
									</th>
									<td>
										<html:text property="sjhm" style="width:150px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId="sjhm"  onkeyup="value=value.replace(/[^\d]/g,'')"/>
									</td>
									<th>
										家庭成员姓名
									</th>
									<td>
										<html:text style="width:150px"
											property="jtcyxm"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');" />
									</td>
								</tr>
								<%--乐山师范学院显示省市区全国统一编码下拉菜单家庭详细地址--%>
								<logic:equal value="10649" name="xxdm">
								<tr id="temDiv7" style="display:none">
									<th>
										家庭详细地址
									</th>
									<td colspan="3">
										<html:select 
										property="jtdzshen" styleId="jtdzshen"
										onchange="loadShi('jtdzshen','jtdzshi','jtdzxian')">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
										</html:select>
										<html:select  property="jtdzshi" styleId="jtdzshi"
											onchange="loadXian('jtdzshi','jtdzxian')">
											<html:options collection="shiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
										<html:select  property="jtdzxian" styleId="jtdzxian">
											<html:options collection="xianList" property="xiandm"
												labelProperty="xianmc" />
										</html:select>
									</td>
									<th>
										QQ号码	
									</th>
									<td>
										<html:text property="qqhm"
											style="width:150px" maxlength="20"
											onkeyup="value=value.replace(/[^\d]/g,'')"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId='qqhm' />
									</td> 
								</tr>    
								</logic:equal>
								<%--end乐山师范学院显示省市区全国统一编码下拉菜单家庭详细地址乐山师范--%>
								<tr id="temDiv8" style="display:none">
									<th>
										民族
									</th>
									<td>
										<html:select property="mzdm"  styleId="mz" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="mzList" property="mzdm"
												labelProperty="mzmc" />
										</html:select>
												
									</td>
									<th>
										政治面貌	
									</th>
									<td>
										<html:select property="zzmmdm"  styleId="zzmm" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="zzmmList" property="zzmmdm"
												labelProperty="zzmmmc" />
										</html:select>
									</td>
									<logic:present name="showLys">
										<th>
											来源省
										</th>
										<td>
											<html:select property="jgshen" styleId="jgshen">
												<html:option value=""></html:option>
												<html:options collection="ssList" property="ssdm"
													labelProperty="ssmc" />
											</html:select>
										</td>
									</logic:present>
									<logic:notPresent name="showLys">	
										<logic:notPresent name="showSheng">	
										<th>
											籍贯
										</th>
										<td colspan="">							
											
												<html:text property="jg" style="width:150px"
													onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
													styleId='jg' />							
										</td>		
										</logic:notPresent>		
										<logic:present name="showSheng">
										<th>
											
										</th>
										<td colspan="">			
										</td>	
										</logic:present>		
									</logic:notPresent>	
								</tr>
								<%--显示省市区全国统一编码下拉菜单籍贯--%>
								<logic:present name="showSheng">
								<tr style="display:none">
									<th>籍贯</th>
									<td colspan="5">
										<html:select  property="jgshen" styleId="jgshen"
											onchange="loadShi('jgshen','jgshi','jgxian')">
											<html:option value="">--请选择--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
										<html:select  property="jgshi" styleId="jgshi"
											onchange="loadXian('jgshi','jgxian')">
											<html:options collection="shiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
										<html:select  property="jgxian" styleId="jgxian">
											<html:options collection="xianList" property="xiandm"
												labelProperty="xianmc" />
										</html:select>
									</td>
								</tr>
								</logic:present>
								<%--end显示省市区全国统一编码下拉菜单--%>
								
								<!--北京 理工大学珠海学院 -->
								<logic:equal value="13675" name="xxdm">
								<tr id="temDiv10" style="display:none">
									<th>
										是否报到
									</th>
									<td colspan="2">
										<html:select property="sfbd" styleId="sfbd" style="width:80px">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
										</html:select>
									</td>
									<th>
										缴费情况
									</th>
									<td colspan="2">
										<html:select property="jfqk" style="width:80px">
										<html:option value=""></html:option>
										<html:option value="已缴费">已缴费</html:option>
										<html:option value="未缴费">未缴费</html:option>
										</html:select>
									</td>
								</tr>
								</logic:equal>
								<!--end北京 理工大学珠海学院 -->
								<%--北京联合大学--%>
								<logic:equal value="11417" name="xxdm">
								<tr id="temDiv11" style="display:none">
									<th>
										户口所在地
									</th>
									<td colspan="5">
										<html:text property="hkszd" style="width:150px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId="hkszd" />
									</td>
								</tr>
								</logic:equal>
								<%--end北京联合大学--%>
								<!-- 南宁职业技术学院-->
								<logic:equal value="11355" name="xxdm">
								<%--显示省市区全国统一编码下拉菜单--%>
								<logic:present name="showSheng">
								<tr id="temDiv12" style="display:none">
									<th>
										生源地
									</th>
									<td colspan="5">
										<html:select  property="sydshen" styleId="sydshen"
											onchange="loadShi('sydshen','sydshi','sydxian')">
											<html:option value="">--请选择--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
										<html:select  property="sydshi" styleId="sydshi"
											onchange="loadXian('sydshi','sydxian')">
											<html:options collection="shiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
										<html:select  property="sydxian" styleId="sydxian">
											<html:options collection="xianList" property="xiandm"
												labelProperty="xianmc" />
										</html:select>
									</td>
								</tr>
								<tr id="temDiv13" style="display:none">
									<th>
										考生号
									</th>
									<td colspan="2">
										<html:text property="ksh" styleId="ksh" style="width:150px"/>
									</td>
									<th>
										
									</th>
									<td colspan="2">
										
									</td>						
								</tr>
								</logic:present>
								<%--end显示省市区全国统一编码下拉菜单--%>
								<logic:notPresent name="showSheng">
								<tr id="temDiv12" style="display:none">
									<th>
										生源地
									</th>
									<td>
										<html:text property="syd" styleId="syd" style="width:150px"/>
									</td>
									<th>
										考生号
									</th>
									<td colspan="3">
										<html:text property="ksh" styleId="ksh" style="width:150px"/>
									</td>
								</tr>
								</logic:notPresent>		
								</logic:equal>
								<!-- end南宁职业技术学院 -->	
								<%--福建工程学院--%>
								<%@ include file="/xsxx/fjgcxy/xsxxcx_fjgcxy.jsp"%>			
							</tbody>
							</table>
							</div>
							</logic:equal>

							<div class="formbox" >	
								<h3 class="datetitle_01">
							    <span>
							    	查询结果&nbsp;&nbsp;<font color="blue">双击查看学生相关信息；单击表头可以排序</font>
							    	
							    </span>
							    </h3>
								<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
								<thead>
							      <tr>
							        <td>
										<logic:equal value="yes" name="userOper">
<!--										<input type="checkbox" style="height:17.5px"></input>-->
										<input type="checkbox" style="height:17.5px" name="xsxx" value="all" onclick="chec()"></input>
										</logic:equal>
									</td>
									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="${tit.en}" onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
				
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="${tit.en}" onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
									<%--淮海工学院--%>
									<logic:equal value="11641" name="xxdm">
										<td>
											学生课表 
										</td>
									</logic:equal>
									<%--end淮海工学院--%>
							      </tr>
							    </thead>
								<logic:empty name="rs">
									<%
									for(int i=0; i<11; i++){
									%>
									<tr>
										<td align="center">
											<input type="checkbox" name="pk" value="" disabled="disabled"></input>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
										<%--淮海工学院--%>
										<logic:equal value="11641" name="xxdm">
											<td>
												&nbsp;
											</td>
										</logic:equal>
										<%--end淮海工学院--%>
								 	</tr>

									<%}%>
							 	</logic:empty>		 

								<logic:notEmpty name="rs">	
								<tbody>
							      <logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="stuInfoWin(this)">
										<logic:equal value="yes" name="userOper">
											<td align="center">
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="checkbox" name="pk" value="${v}"></input>
												</logic:iterate>
											</td>
										</logic:equal>
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												${v}
											</logic:iterate>
											<input type="hidden" value="${v}" />
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="2" length="1">
												${v}
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td align="left">
												${v}
											</td>
										</logic:iterate>
										<%--淮海工学院--%>
										<logic:equal value="11641" name="xxdm">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<font color="blue">
														<a onclick="turnSearch('${xskbUrl}','${v}','${time}');"> 查看</a> 
				                                    </font>
												</logic:iterate>
											</td>
										</logic:equal>
										<%--end淮海工学院--%>
									</tr>
								</logic:iterate>
								<logic:lessEqual value="${pageSize}" name="rsNum">
									<%
									int rsNum = ((List)request.getAttribute("rs")).size();
									int pageSize = (Integer) request.getAttribute("pageSize");
									for(int i=0; i<(pageSize-rsNum); i++){
									%>
									<tr>
										<td align="center">
											<input type="checkbox" name="pk" value="" disabled="disabled"></input>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
										<%--淮海工学院--%>
										<logic:equal value="11641" name="xxdm">
											<td>
												&nbsp;
											</td>
										</logic:equal>
										<%--end淮海工学院--%>
								 	</tr>
									<%}%>
								</logic:lessEqual>
							    </tbody>
								</logic:notEmpty>
								</table>
								<!--分页显示-->
							      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							 	<!--分页显示-->
<!--								<script type="text/javascript">-->
<!--									$('choose').className="hide";-->
<!--								</script>								-->
							</div>
						</div>

				<logic:notEmpty name="result">
					<logic:equal value="true" name="result">
						<logic:notEmpty name="mes">
							<script>
								alert(document.getElementById('mes').value);
							</script>
						</logic:notEmpty>
						<logic:empty name="mes">
							<script>
									alert("操作成功！");
								</script>
						</logic:empty>
						<script>
						Close();
						document.getElementById('search_go').click();						
					</script>
					</logic:equal>
					<logic:equal name="result" value="false">
						<logic:present name="mes">
							<logic:notEmpty name="mes">
								<script>
									alert(document.getElementById('mes').value);
								</script>
							</logic:notEmpty>
							<logic:empty name="mes">
								<script>
									alert("操作失败!");
								</script>
							</logic:empty>
						</logic:present>
						<logic:notPresent name="mes">
							<script>
								alert("操作失败!");
							</script>
						</logic:notPresent>
						<script>
								document.getElementById('search_go').click();
						</script>
					</logic:equal>
				</logic:notEmpty>

				<div id="tmpdiv"></div>
				<div id='bycfDiv' style="display:none">
				<table class='formlist'>
				<tbody>
				<tr>
					<th>
						请选择修改方式：
					</th>
					<td>
						<input type='radio' name='configtype' value='2' checked="checked"/>&nbsp;按条件修改(您在查询栏中选择的学籍条件)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
						</br>
						<input type='radio' name='configtype' value='1'/>&nbsp;修改选中的数据(您在查询结果列表中勾选的数据)
					</td>
				</tr>
				</tbody>
				<tbody>		
				<thead>
					<tr>
						<th colspan='2'>
							<span>学生毕业相关信息</span>
						</th>
					</tr>
				</thead>
					
				<tr>
				<th>
					学籍状态：
				</th>
				<td>
					<html:select property="xjzt" styleId="select_newxjztm" style="width:180px">
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
					</html:select>
				</td>
				</tr>
				<!--中国地质大学-->
				<logic:equal value="10491" name="xxdm">
					
					<tr>
						<th>
							能否毕业：
						</th>
						<td>
							<select id='select_newnfby' name='nfby'><option></option><option value='是'>是</option><option value='否'>否</option></select>
						</td>
					</tr>
					
				</logic:equal>
				<tr>
					<th>
						是否在校：
					</th>
					<td>
						<select id='select_newsfzx' name='sfzx'><option></option><option value='在校'>在校</option><option value='不在校'>不在校</option></select>
					</td>
				</tr>
				<tr>
						<th>
							是否已毕业：
						</th>
						<td>
							<select id='select_newsfyby' name='sfyby'><option></option><option value='是'>是</option><option value='否'>否</option></select>
						</td>
					</tr>	
				<tr>
					<th>
						是否应届毕业生：
					</th>
					<td>
						<select id='select_newsfbys' name='sfbys'><option></option><option value='是'>是</option><option value='否'>否</option></select>
					</td>
				</tr>
				<tr>
					<th>
						毕业时间：
					</th>
					<td>
						<input type='text' id='newbyny' name='byny' onclick="return showCalendar('newbyny','y-mm-dd');"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
				<tr>
					<td colspan='2'> 
						<div class='btn'>
							<span class='red'>您输入的与学生毕业相关的信息,请慎重操作！</span>
							<button type="button" onclick='setSearchTj();byxxConfig()'>确定</button>
							<button type="button" onclick="hiddenMessage(true,true);">关闭</button>
						</div>
					</td>
				</tr>
				</tfoot>
				</table>
				</div>
			</html:form>			
	</body>
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/stu_info_query.do?method=stuInfo&isChecked="+$("moreTj").checked;-->
<!--</script>-->
</html>

