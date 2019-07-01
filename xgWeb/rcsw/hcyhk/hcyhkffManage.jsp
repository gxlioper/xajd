<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript">
		var DCCLBH = "xszz_hcyhkff.do";//dcclbh,导出功能编号          
		// 导出配置 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出的函数
			customExport(DCCLBH, exportData);
		}

		// 导出方法
		function exportData() {
			var url = "hcyhkff.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			//url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alertInfo('请选择要修改的数据行！');
				return false;
			}
		}
		function ff(){
			var pkV=document.getElementsByName("pkV");
			var sfff=document.getElementsByName("sfffArr");
			var url="hcyhkff.do?method=hcyhkffUpdate";
			var check=0;
			var select=0;
			for(var i=0;i<pkV.length;i++){
				if(pkV[i].checked){
					select=i;
					check++;
				}
			}
			if(check>1){
				alertInfo('不能选择多个学生进行发放！');
				return false;
			}else if(check<=0){
				showTopWin(url,800,600);
			}else{
				var guid=pkV[select].value;
				if(sfff[select].value=="是"){
					alertInfo("不能对已发放的学生再进行发放！");
					return false;
				}
				showTopWin(url + '&xh='+guid,800,600);
			}
		}
		function view(url){
			
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);

		}
		
		function checkPlff(){
			
			if($("qdz") && $("qdz").value==""){
				alertInfo("起点站不能为空,请确认!");
				return false;
			}
			
			if($("zdz") && $("zdz").value==""){
				alertInfo("终点站不能为空,请确认!");
				return false;
			}
			dataBatch('hcyhkff.do?method=hcyhkffManage&doType=plff')
		}
		
		
		function checkDel(){
			
			if($("xn").value==$("dqxn").value
				&& $("xq").value==$("dqxq").value
				&& $("nd").value==$("dqnd").value){
				var pkV=document.getElementsByName("pkV");
				var sfff=document.getElementsByName("sfffArr");
				for(var i=0;i<pkV.length;i++){
				if(pkV[i].checked && sfff[i].value!="是"){
					alertInfo("勾选的记录中存在未发放的记录,无法取消发放,请确认！");
					return false;
				}else if(pkV[i].checked){
					flag=true;
				}
			}
			}else {
				alertInfo("取消注册时必须选择当前学年、学期、年度!");
				return false;
			}
			dataBatch('hcyhkff.do?method=hcyhkffManage&doType=del');
		}
		
		function plff(){
			var flag=false;
			if($("xn").value==$("dqxn").value
				&& $("xq").value==$("dqxq").value
				&& $("nd").value==$("dqnd").value){
				var pkV=document.getElementsByName("pkV");
				var sfff=document.getElementsByName("sfffArr");
				for(var i=0;i<pkV.length;i++){
					if(pkV[i].checked && sfff[i].value!="否"){
						alertInfo("勾选的记录中存在已发放的记录,无法重复发放,请确认！");
						return false;
					}else if(pkV[i].checked){
						flag=true;
					}
				}
			}else {
				alertInfo("注册时必须选择当前学年、学期、年度!");
				return false;
			}
			
			if(!flag){
				alertInfo("请勾选需要发放学生证的记录！");
				return false;
			}
			
			tipsWindown("系统提示","id:div_hcyhkff","450","300","true","","true","id");
		}
		
		function checkUpdate(){
		
			if(curr_row){
				var sfff=curr_row.getElementsByTagName("input")[1];
				if(sfff.value=="否"){
					alertInfo("选中的记录尚未发放,无法进行修改操作！");
					return false;
				}else {
					return true;
				}
			}else {
				return true;
			}
		}
		
		function checkView(){
		
			if(curr_row){
				var sfzc=curr_row.getElementsByTagName("input")[1];
				if(sfzc.value=="否"){
					alertInfo("选中的记录尚未发放,无法进行查看操作！");
					return false;
				}else {
					return true;
				}
			}else {
				return true;
			}
		}
		
		function checkSearch(){
			var xn=$("xn").value;
			var xq=$("xq").value;
			if(xn==null || xn==""){
				alertInfo("查询时必须选择一个学年!");
				return false;
			}
			if(xq==null || xq==""){
				alertInfo("查询时必须选择一个学期!");
				return false;
			}
			allNotEmpThenGo('hcyhkff.do?method=hcyhkffManage')
		}
		</script>
	</head>
	<body>

		<html:form action="/hcyhkff" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="realTable" id="realTable"
				value="xszffb" />
			<input type="hidden" name="tableName" id="tableName"
				value="view_xszff" />
			<input type="hidden" name="viewName" id="viewName"
				value="view_xszff" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userDep" id="userDep" value="${userDep}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<input type="hidden" name="pk" id="pk" value='xh||ffsj' />
			<input type="hidden" name="dqxn" id="dqxn" value='${dqxn}' />
			<input type="hidden" name="dqxq" id="dqxq" value='${dqxq}' />
			<input type="hidden" name="dqnd" id="dqnd" value='${dqnd}' />
			<input type="hidden" id="act" name="act"
						value="studentPaperPut" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#"
								onclick="ff()"
								class="btn_zj"> 优惠卡发放 </a>
						</li>
						<li>
							<a href="#"
								onclick="if(checkUpdate()){modi('hcyhkff.do?method=hcyhkffOne&doType=update')}"
								class="btn_xg"> 修改 </a>
						</li>
						<li>
							<a href="#" onclick="checkDel()" class="btn_sc"> 取消发放 </a>
						</li>
						<li>
							<a href="#" onclick="exportConfig()" class="btn_qx">自定义导出</a>
						</li>
						<li>
							<a href="#" onclick="exportData()" class="btn_dc">导出</a>
						</li>
						</logic:equal>
						<%--<li>
							<a href="#" onclick="plff()" class="btn_ccg"> 批量发放 </a>
						</li>
					--%></ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="checkSearch()">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
									<html:text property="xh" styleId="xh" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" />
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:150px"
										onchange="initZyList();initBjList();">
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
										<html:select property="queryequals_xydm" styleId="xy"
											disabled="true" value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList();">
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
									<html:select property="zydm" styleId="zy" style="width:150px"
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
									<html:select property="bjdm" styleId="bj" style="width:150px">
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
									<html:select property="xn" styleId="xn" style="width:150px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" styleId="xq" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" styleId="nd" style="width:150px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								
							</tr>
							<tr>
								<th>
									是否发放
								</th>
								<td>
									<html:select property="sfff" styleId="sfff" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								<td colspan="4">
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							<font color="blue">提示：单击表头可以排序;双击一条记录可以查看详细信息。</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									&nbsp;
									<input type="checkbox" />
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
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="if(checkView()){view('hcyhkff.do?method=hcyhkffOne&doType=view')}">
										<td>
											&nbsp;
											<input type="checkbox" name="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="8" >
										
											<td>
												<bean:write name="v" />
											</td>
										
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="9" length="1" >
										
											<td>
												<bean:write name="v" />
												<input type="hidden" name="sfffArr" id="sfff_${index }" value="${v}"/>
											</td>
										
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!-- 补空行 -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- 补空行 end-->
						</tbody>
					</table>
				</div>
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
				
			</div>
			<div id="div_hcyhkff" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>火车优惠卡发放</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_clr">
								<th width="30%">
									<font color="red">*</font>起点站
								</th>
								<td>
									<html:text property="qdz" styleId="qdz" maxlength="10"/>
								</td>
							</tr>
							<tr id="tr_clr">
								<th width="30%">
									<font color="red">*</font>终点站
								</th>
								<td>
									<html:text property="zdz" styleId="zdz" maxlength="10"/>
								</td>
							</tr>
							<tr id="tr_tsnr">
								<th width="30%">
									备注<br/>
									<font color="red"><限500字></font>
								</th>
								<td>
									<textarea name="bz" id="bz" rows="5" cols="" 
										style="word-break:break-all;width:96%" onblur="chLeng(this,500)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										带<font color="red">"*"</font>字段必须填写
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="checkPlff()">
											保 存
										</button>
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
