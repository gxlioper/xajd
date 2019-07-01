<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
	function expStationDis(){
		document.forms[0].target = "_blank";
		refreshForm('xsqgzx.do?method=expStation');
		document.forms[0].target = "_self";
	}
	function add(){
		var xxdm = document.getElementById("xxdm").value;
		if(xxdm == "10395"){//闽江学院
			refreshForm('qgzxgwfb.do');
			return true;
		}else{
			refreshForm('comm_pub.do?doType=add&tableName='+document.forms[0].tableName.value,800,600);
		}
	}
	function modi(){
		var xxdm = document.getElementById("xxdm").value;
		if(curr_row != null){
			var pkValue = curr_row.getElementsByTagName('input')[0].value;
			if(xxdm=="10497"){//武汉理工大学
				if(curr_row.cells[10].innerText.trim()=="通过"){
					alert("审核已经通过，已不能再进行修改！");			
					return false;
				}
			}
			if(xxdm=="13275"){//浙江工业大学之江学院
				if(curr_row.cells[12].innerText.trim()=="通过"){
					alert("审核已经通过，已不能再进行修改！");			
					return false;
				}
			}
			if(xxdm == "10395"){//闽江学院
				refreshForm('gwfb.do?method=gwfbModi&pkValue=' + pkValue);
				return true;
			}else{
				refreshForm('/xgxt/comm_pub.do?doType=modi&pkValue=' + pkValue);
				return true;
			}
		}else{
			alert('请选择要修改的行！');
			return false;
		}
	}
	
	function applyGw(url){
		if(curr_row == null ){
				alert("请选要申请的岗位！\n单击一行记录即可");
				return false;
			} 
		var pkValue = (curr_row.cells[0].getElementsByTagName("input"))[0].value;		 
		//判断岗位时间是否过期
		cqkjFunc.checkGwsj(pkValue,function(data){
			if(data == true){
				var xxdm = document.getElementById("xxdm").value;
				//浙江工业大学之江学院
				if(xxdm == "13275"){
					cqkjFunc.checkGwsqsj(pkValue,function(data){
						if(data == true){
							//浙江工业大学之江学院的学生岗位申请跳转至新的页面处理，不使用通用url
							url = "qgzx_gwsqwh.do?method=xssqUpdate";
							url += "&gwdmsbsj="+pkValue;
							refreshForm(url);
						}else{
							alert("该岗位目前还不能申请，请关注该岗位申请开始时间与结束时间！");
						}
					});
				}else{
					refreshForm(url+pkValue,600,480);
				}
			}else{
				alert("该岗位还未审核通过或工作时间已过期，现在不能申请！");
				return false;
			}
		});	

	}
	
	//显示岗位详细信息
	function showDetails(){
		var xxdm = val('xxdm');
		var pkValue = curr_row.cells[1].getElementsByTagName('input')[0].value;
		if(xxdm == '10395'){//闽江学院
			url = 'gwfb.do?method=gwfbDetails&pkValue=';
			url += pkValue;
		}else{
			url = 'qgzxLogic.do?method=queryGwxxxx&pk=';
			url += pkValue;
		}
		showTopWin(url, 650, 550);
	}

	//显示人数相关详细信息
	function showRsxxDetail(lx){
		var pkValue = curr_row.cells[1].getElementsByTagName('input')[0].value;
		url = "qgzxLogic.do?method=showRsxxDetail&pk=";
		url += pkValue;
		url += "&lx=";
		url += lx;
		showTopWin(url,650,550);
	}
	
	function print1(){
		var pkValue = '';
		if (curr_row != null) {
			pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
		}
		window.open('gzdx_scgwxxbPrint.do?pkValue=' + pkValue);
	} 
	
	function print(){
		var nd = val('nd');
		window.open('qgzxGwgl.do?method=printGwhzb&nd='+nd);	
	}
	
	function change(id,disId,loadFlag){	
		var xxdm = val('xxdm');
		if(xxdm == "11078"){//广州大学
			if(id== ""){
				id = "gwfbr";
				disId = "qtfbr";
			}	
			
			ele("field"+id).style.display = "";
			ele("field"+disId).style.display = "none";
			ele("field"+id).disabled = false;
			ele("field"+disId).disabled = true;
			
			setVal('divId',id);
			setVal('disDivId',disId);
			
			ele(id + "l").className = "xxk_on_l";
			ele(id + "m").className = "xxk_on_m";
			ele(id + "r").className = "xxk_on_r";
			
			ele(disId + "l").className = "xxk_off_l";
			ele(disId + "m").className = "xxk_off_m";
			ele(disId + "r").className = "xxk_off_r";
			if(id == "qtfbr"){
				ele('gwfbr').disabled=true;
			}else{
				ele('gwfbr').disabled=false;
			}
			if(!loadFlag){
				refreshForm('data_search2.do?act=work');
			}					
		}
	}
	
	
	function printQggwInfo(){
		$("zzxn").value=$("qsxn").value;
		document.forms[0].target = "_blank";
		refreshForm('qgzxGxls.do?method=printQggwInfo');
		document.forms[0].target = "_self";
		closeWindown();
	}
	
	function showXnChecked(){
			tipsWindown("系统提示","id:div_xn","350","200","true","","true","id");
		}
		
	</script>
</head>
<body onload="change(val('divId'),val('disDivId'),true);">
	<html:form action="/data_search2" method="post">
		<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
		<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
		<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
		<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
		<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
		<input type="hidden" id="userName" name="userName" value="${userName}" />
		<input type="hidden" id="divId" name="divId" value="${divId}" />
		<input type="hidden" id="disDivId" name="disDivId" value="${disDivId}" />
		<input type="hidden" id="shFlag" name="shFlag" value="false" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		
		<!--广州大学-->
		<logic:equal value="11078" name="xxdm">
		<html:hidden property="gwfbr" value="${userName}"></html:hidden>
		<div class="xxk" id="xxkDiv" style="width:97%">
			<ul id="gwfbrul">
				<li id="gwfbrl" class="xxk_on_l"></li>
				<li id="gwfbrm" onclick="change('gwfbr','qtfbr',false);" class="xxk_on_m">
					&nbsp;
					本人发布岗位
					&nbsp;
				</li>
				<li id="gwfbrr"class="xxk_on_r"></li>
			</ul>
			<ul id="qtfbrul">
				<li id="qtfbrl" class="xxk_off_l"></li>
				<li id="qtfbrm" onclick="change('qtfbr','gwfbr',false);" class="xxk_off_m">
					&nbsp;
					其它用户发布岗位
					&nbsp;
				</li>
				<li id="qtfbrr"class="xxk_off_r"></li>
			</ul>
		</div>		
		<fieldset id="fieldqtfbr" style="display:none">
			<legend>
				查询条件
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							年度：
							<html:select property="nd" style="width:90px" onchange="loadFgwfbrGwmcxx('gwdm','xn','nd','xq')">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
							&nbsp;&nbsp;学年：
							<html:select property="xn" style="width:120px" onchange="loadFgwfbrGwmcxx('gwdm','xn','nd','xq')">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;学期：
							<html:select property="xq" style="width:90px" onchange="loadFgwfbrGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
							&nbsp;&nbsp;用人单位：
							<html:select property="yrdwdm" style="width:90px" onchange="loadFgwfbrGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
							
						</td>
						<td width="10" align="center" valign="middle" rowspan="2">
							<input type="hidden" name="go" value="go" />
							<button type="button" class="button2" id="search_go" style="height:40px"
								onclick="allNotEmpThenGo('/xgxt/data_search2.do?act='+document.getElementById('act').value)">
								查 询
							</button>
						</td>
					</tr>
					<logic:equal value="view_gwxx" name="tableName">
						<tr>
							<td>
								<logic:equal value="view_gwxx" name="tableName">
								岗位名称：
								<html:select property="gwdm">
										<html:option value=""></html:option>
										<html:options collection="gwList" property="gwmc"
											labelProperty="gwmc" />
									</html:select>
								</logic:equal>	
							</td>
						</tr>
					</logic:equal>
				</thead>
			</table>
		</fieldset>

		<fieldset id="fieldgwfbr" style="">
			<legend>
				查询条件
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							年度：
							<html:select property="nd" style="width:90px" styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
							&nbsp;&nbsp;学年：
							<html:select property="xn" style="width:120px" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;学期：
							<html:select property="xq" style="width:90px" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>								
						</td>
						<td width="10" align="center" valign="middle" rowspan="2">
							<input type="hidden" name="go" value="go" />
							<button type="button" class="button2" id="search_go" style="height:40px"
								onclick="allNotEmpThenGo('/xgxt/data_search2.do?act='+document.getElementById('act').value)">
								查 询
							</button>
						</td>
					</tr>
					<logic:equal value="view_gwxx" name="tableName">
						<tr>
							<td>
								<logic:equal value="view_gwxx" name="tableName">
								岗位名称：
								<html:select property="gwdm">
										<html:option value=""></html:option>
										<html:options collection="brfbgwList" property="gwmc"
											labelProperty="gwmc" />
									</html:select>
								</logic:equal>									
							</td>
						</tr>
					</logic:equal>
				</thead>
			</table>
		</fieldset>	
		</logic:equal>
		<!--end广州大学-->

		<!--非广州大学-->
		<logic:notEqual value="11078" name="xxdm">
			<div class="toolbox">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<logic:equal value="yes" name="writeAble">					
					<!-- 广州大学 -->
					<logic:notEqual value="11078" name="xxdm">
						<li> <a href="#" onclick="applyGw('/xgxt/post_stu_apply.do?gwValue=')" class="btn_sq">岗位申请</a> </li>
					</logic:notEqual>	
					<logic:notEqual value="stu" name="userType">
					<logic:notEmpty name="gwcxview">
						<li> <a href="#" onclick="viewMore('modi','yes')" class="btn_xg">修改</a> </li>
						<li> <a href="#" onclick="dataDel('qgzxLogic.do?method=delPost')" class="btn_sc">删 除</a> </li>
					</logic:notEmpty>	
					<logic:empty name="gwcxview">	
						<li> <a href="#" onclick="add()" class="btn_zj">增 加</a> </li>				
						<li> <a href="#" onclick="modi()" class="btn_xg">修 改</a> </li>
						<li> <a href="#" onclick="dataDel('qgzxLogic.do?method=delPost')" class="btn_sc">删 除</a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>					
						<li> <a href="#" onclick="showExportDIV('/xgxt/expData.do');" class="btn_dc">导出数据</a> </li>
						<%--湖南工业大学--%>
						<logic:equal value="11535" name="xxdm">
							<logic:equal value="view_gwxx" name="tableName">								
								<%--勤工助学--%>
								<li> <a href="#" onclick="expStationDis()" class="btn_ck">岗位分布</a> </li>
							</logic:equal>
						</logic:equal>
						<%--end湖南工业大学--%>
						<!--广州大学-->
						<logic:equal value="11078" name="xxdm">
							<li> <a href="#" onclick="print1()" class="btn_dy">岗位申请表打印</a> </li>
						</logic:equal>
						<!--end广州大学-->					
						<!--宁波天一职业技术学院-->
						<logic:equal value="13742" name="xxdm">
							<li> <a href="#" onclick="print()" class="btn_dy">岗位汇总表打印</a> </li>						
						</logic:equal>
						<!--end宁波天一职业技术学院-->
						<!--浙江科技学院-->
						<logic:equal value="11057" name="xxdm">
							<li> <a href="#" onclick="printYrqkb()" class="btn_dy">用人情况表打印</a> </li>
						</logic:equal>
						
						<li> <a href="#" onclick="showXnChecked()" class="btn_dy">用人情况表打印</a> </li>
						<!--end浙江科技学院-->	
					</logic:empty>
					</logic:notEqual>
					</logic:equal>	
			    </ul>
			  </div>
			  <!--查询条件-->
			  <div class="searchtab">
			    <table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="a" />
						  <button type="button" class="btn_cx" id="search_go"
								onclick="allNotEmpThenGo('/xgxt/data_search2.do?act='+document.getElementById('act').value)">
								查询
						  </button>
						  <button type="button" id="cz"
							onclick="czSearchCond('nd-xn-xq-yrdwdm-gwxzdm-jcfs-xxyj-gwdm-gwflag-sfgq');return false;">
							重置
					    	</button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
				  <tbody>
			      	<tr>
			      		<th>年度</th>
						<td>
							<html:select property="nd" style="width:180px" styleId="nd" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th>学年</th>
						<td>
							<html:select property="xn" style="width:180px" styleId="xn" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" style="width:180px" styleId="xq" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>	
						</td>
					</tr>
					<tr>
						<th>用人单位</th>
						<td>
							<html:select property="yrdwdm" style="width:180px" styleId="yrdwdm">
									<html:option value=""></html:option>
									<html:options collection="yrdwList" property="yrdwdm"
										labelProperty="yrdwmc" />
							</html:select>
						</td>
			      		<th>岗位性质</th>
						<td>
							<html:select property="gwxz" style="width:180px" styleId="gwxzdm" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
						<th>计酬方式</th>
						<td>
							<html:select property="jcfs" style="width:180px" styleId="jcfs">
								<html:option value=""></html:option>
								<html:options collection="jcfsList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>						
					</tr>
					<tr>
						<th>审核结果</th>
						<td>
							<html:select property="xxyj" style="width:180px" styleId="xxyj">
									<html:option value=""></html:option>
									<html:options collection="chkList" property="en" labelProperty="cn" />
							</html:select>
						</td>	
						<th>岗位名称</th>
						<td>
							<html:text property="gwdm" style="width:180px" styleId="gwdm"></html:text>
						</td>
						<logic:notEqual name="userType" value="stu">
							<th>是否有效</th>
							<td>
									<html:radio property="sfgq" value="yx" styleId="sfgq">有效</html:radio>	&nbsp;  
								    <html:radio property="sfgq" value="gq" styleId="sfgq">无效</html:radio>&nbsp;
								    <html:radio property="sfgq" value="" styleId="sfgq">全部</html:radio>
							</td>			      							
						 </logic:notEqual>
						 <logic:equal name="userType" value="stu">
						    	<input type="hidden" name="sfgq" value="yx"/>
					    	<th>是否空闲岗位</th>
							<td>
								<html:radio property="sfkx" value="kx" styleId="sfkx">空闲</html:radio>	&nbsp;  
							    <html:radio property="sfkx" value="bkx" styleId="sfbkx">非空闲</html:radio>&nbsp;
							    <html:radio property="sfkx" value="qb" styleId="">全部</html:radio>&nbsp;
							</td>		
					    </logic:equal>
					</tr>
					<!--闽江学院-->
					<logic:equal value="10395" name="xxdm">
					<tr>
			      		<th>岗位类别</th>
						<td>
							<html:select property="gwflag" style="width:180px" styleId="gwflag">
								<html:option value=""></html:option>
								<html:option value="xngw">校内岗位</html:option>
								<html:option value="xwgw">校外岗位</html:option>
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</logic:equal>
				</tbody>
			</table>
			</div>		
			</div>
		</logic:notEqual>
		<!--end非广州大学-->
		<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
					<logic:notEmpty name="rs">
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序！</font>
					</logic:notEmpty>
			    </span>
			    </h3>
				   
			  <logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
					<td>
						<input type="checkbox" name="all" value="all" onclick="chec()"/>
					</td>
					<logic:equal name="xxdm" value="11078">
						<logic:iterate id="tit" name="topTr" offset="2">
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)">
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
					</logic:equal>
					<logic:notEqual name="xxdm" value="11078">
						<logic:iterate id="tit" name="topTr" offset="1">
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)">
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
					</logic:notEqual>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<!--武汉理工-->
						<logic:equal value="10497" name="xxdm">
							<tr onclick="rowMoreClick(this,'',event);" ondblclick="viewMore('modi','yes')" style="cursor:hand">
						</logic:equal>
						<!--end武汉理工-->

						<!--湖南工业-->
						<logic:equal value="11535" name="xxdm">
							<tr onclick="rowMoreClick(this,'',event);" ondblclick="viewMore('modi','yes')" style="cursor:hand">
						</logic:equal>
						<!--end湖南工业-->
						
						<!--公共-->
						<logic:notEqual value="10497" name="xxdm">
						<logic:notEqual value="11535" name="xxdm">
							<tr onclick="rowMoreClick(this,'',event);" align="center" ondblclick="showDetails()" style="cursor:hand">
						</logic:notEqual>
						</logic:notEqual>
						<!--end公共-->
							<td>
								<input type="checkbox" name="pkV" <logic:equal name="xxdm" value="11078">
								<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>
								</logic:equal>
								value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
							</td>
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>"/>
								</logic:iterate>
								<!-- 广州大学第二个是DISABLED -->
								<logic:equal name="xxdm" value="11078">
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</logic:equal>
								<!-- 非广州大学 -->
								<logic:notEqual name="xxdm" value="11078">
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</logic:notEqual>
							</td>
							<!-- 广州大学第二个是DISABLED -->
							<logic:equal name="xxdm" value="11078">
							<logic:iterate id="v" name="s" offset="3">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
							</logic:equal>
							<logic:notEqual name="xxdm" value="11078">
								<!-- 浙江工业大学之江学院 -->
								<logic:equal value="13275" name="xxdm">
									<logic:iterate id="v" name="s" offset="2" length="6">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="8" length="1">
										<td>
											<logic:equal value="0" name="v">
												<bean:write name="v" />
											</logic:equal>
											<logic:notEqual value="0" name="v">
												<a href="javascript:showRsxxDetail('sqrs');" style="color: blue;">
													<bean:write name="v" />
												</a>
											</logic:notEqual>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="9" length="1">
										<td>
											<logic:equal value="0" name="v">
												<bean:write name="v" />
											</logic:equal>
											<logic:notEqual value="0" name="v">
												<a href="javascript:showRsxxDetail('lyrs');" style="color: blue;">
													<bean:write name="v" />
												</a>
											</logic:notEqual>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="10">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</logic:equal>
								<!-- 通用学校 -->
								<logic:notEqual value="13275" name="xxdm">
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</logic:notEqual>
							</logic:notEqual>
						</tr>
					</logic:iterate>
			    </tbody>
			</table>
			</div>
			<!--分页显示-->
			   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			<!--分页显示-->			
			</logic:notEmpty>
			</div>	
			
			
		<!-- 岗位统计起始、结束学年选择 -->
		<div id="div_xn" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>总分计算</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="qsxn" styleId="qsxn" style="width:150px"
										>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									<html:hidden property="zzxn" styleId="zzxn"/>
								</td>
							</tr>
							
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="计 算" onclick="printQggwInfo()">
											确 认
										</button>
										<button type="button" name="取 消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
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
