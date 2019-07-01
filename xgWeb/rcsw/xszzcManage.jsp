<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function modi(url){
			
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function view(url){
			
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);

		}
		
		function checkDel(){
			
			if($("xn").value==$("dqxn").value
				&& $("xq").value==$("dqxq").value){
				var pkV=document.getElementsByName("pkV");
				var sfzc=document.getElementsByName("sfzcArr");
				for(var i=0;i<pkV.length;i++){
				if(pkV[i].checked && sfzc[i].value!="是"){
					alertInfo("勾选的记录中存在未注册的记录,无法进行取消操作！");
					return false;
				}else if(pkV[i].checked){
					flag=true;
				}
			}
			}else {
				alertInfo("取消注册时必须选择当前学年、学期!");
				return false;
			}
			dataBatch('rcsw_xszgl.do?method=xszzcManage&doType=del')
		}
		
		function checkPlff(){
			
			
			dataBatch('rcsw_xszgl.do?method=xszblManage&doType=plff')
		}
		
		function showPlzcInfo(){
			if($("xn").value==$("dqxn").value
				&& $("xq").value==$("dqxq").value){
				var pkV=document.getElementsByName("pkV");
				var sfzc=document.getElementsByName("sfzcArr");
				for(var i=0;i<pkV.length;i++){
				if(pkV[i].checked && sfzc[i].value!="否"){
					alertInfo("勾选的记录中存在已注册的记录,无法重复注册操作！");
					return false;
				}else if(pkV[i].checked){
					flag=true;
				}
			}
			}else {
				alertInfo("注册时必须选择当前学年、学期!");
				return false;
			}
			tipsWindown("系统提示","id:div_xszzc","450","300","true","","true","id");
		}
		
		
		function plzc(){
		
			if($("jbr") && $("jbr").value==""){
				alertInfo("经办人不能为空!");
				return false;
			}
			dataBatch('rcsw_xszgl.do?method=xszzcManage&doType=plzc');
		}
		
		function checkUpdate(){
		
			if(curr_row){
				var sfff=curr_row.getElementsByTagName("input")[2];
				if(sfff.value=="否"){
					alertInfo("选中的记录尚未注册,无法进行修改操作！");
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
					alertInfo("选中的记录尚未注册,无法进行查看操作！");
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
			allNotEmpThenGo('rcsw_xszgl.do?method=xszzcManage')
		}
		</script>
	</head>
	<body>

		<html:form action="/rcsw_xszgl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="realTable" id="realTable"
				value="xszffb" />
			<input type="hidden" name="tableName" id="tableName"
				value="view_xszff" />
			<input type="hidden" name="viewName" id="viewName"
				value="view_xszff" />
			<input type="hidden" name="userName" id="userName"
				value="${userName }" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="userDep" id="userDep"
				value="${userDep }" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<input type="hidden" name="pk" id="pk" value='xh||ffsj' />
			<input type="hidden" name="dqxn" id="dqxn" value='${dqxn}' />
			<input type="hidden" name="dqxq" id="dqxq" value='${dqxq}' />
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
						<li>
							<a href="#"
								onclick="showTopWin('rcsw_xszgl.do?method=xszzcUpdate',800,600)"
								class="btn_zj"> 单个学生注册 </a>
						</li>
						<li>
							<a href="#" onclick="checkDel()" class="btn_sc"> 取消注册 </a>
						</li>
						<li>
							<a href="#" onclick="showPlzcInfo()" class="btn_ccg"> 批量注册 </a>
						</li>
					</ul>
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
									是否注册
								</th>
								<td>
									<html:select property="sfzc" styleId="sfzc" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								
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
										ondblclick="if(checkView()){view('rcsw_xszgl.do?method=xszzcOne')}">
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
												<input type="hidden" name="sfzcArr" id="sfzc_${index }" value="${v}"/>
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
			
			<div id="div_xszzc" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>学生证注册</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_clr">
								<th width="30%">
									<font color="red">*</font>经办人
								</th>
								<td>
									<html:select  property="select_jbr"  style="width:90px"
										styleId="jbr">
										<html:option value=""></html:option>
										<html:options collection="jbrList" property="jbrgh"
											labelProperty="jbrxm" />
									</html:select>
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
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="plzc()">
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
