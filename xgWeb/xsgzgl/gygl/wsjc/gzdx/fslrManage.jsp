<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/gygl/gzdx/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/xsgzgl/gygl/gzdx/wsjc.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gzdx_gygl_wsjc_fslr.do');
		}

		//根据卫生分取卫生等级
		function setWsfdj(num){
			var wsfId = "wsf"+num;
			var djId = "wsfdj"+num;	
			var djNum = $("djNum").value;
			
			var wsfdj = "未维护";
			
			if($(wsfId)&&$(wsfId).value!=""){
				var wsf = $(wsfId).value;
				for(var i=0;i<djNum;i++){
					var xx = $("xx"+i).value;
					var sx = $("sx"+i).value;
					var dj = $("dj"+i).value;
					
					if(parseInt(wsf)>=xx && parseInt(wsf)<=sx){
						wsfdj = dj;
						break;
					}
				}
			}
			
			if($(djId)){
				$(djId).value = wsfdj;
			}
		}
		
		//根据卫生分等级取卫生分数
		function setWsffs(num){
			var wsfId = "wsf"+num;
			var djId = "djOption"+num;	
			var tempDjId = "tempDj"+num;	
			var djNum = $("djNum").value;
			
			var wsf = "未维护";
			
			if($(djId)&&$(djId).value!=""){
				var djV = $(djId).value;
				for(var i=0;i<djNum;i++){
					var xx = $("xx"+i).value;
					var sx = $("sx"+i).value;
					var dj = $("dj"+i).value;
					
					if(dj == djV){
						wsf = sx;
					}
				}
				
				$(tempDjId).value=djV;
			}
			
			if($(wsfId)){
				$(wsfId).value = wsf;
			}
		}
		
		//显示卫生等级
		function showWsdj(){
			var num = $("rsNum").value;
			var lrxs = $("lrxs").value;
			for(var i=0;i<num;i++){
			
				var tempDjId = "tempDj"+i;
				var djId = "djOption"+i;	
				
				if($(tempDjId) && $(tempDjId).value != "" && $(djId)){
					$(djId).value = $(tempDjId).value;
				}
				
				if(lrxs == "分数"){
					setWsfdj(i);
				}else{
					setWsffs(i);
				}
					
			}
		}
		
		//显示保存DIV
		function showSaveDiv(){
		
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var flag = false;
			
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			if(!flag){
				alert('请勾选需要保存的数据！');
			}else{
				viewTempDiv('保存其他信息','showDiv',280,300)
			}
		}
		
		//显示寝室信息
		function showQsInfo(){
			var url = "/xgxt/gzdxWsjc.do?method=showQsInfo";
			if($("jczc")){
				url+= "&jczc="+$("jczc").value;
			}
			if($("jcsj")){
				url+= "&jcsj="+$("jcsj").value;
			}
			showInfo(url,'view','800','600');
		}
		
		//保存卫生分
		function saveWsf(){
		
			var rsNum = $("rsNum").value;
			var lrxs = $("lrxs").value;
			var flag = true;

			for(var i=0;i<rsNum;i++){
				if(lrxs == "分数"){
					var id = "wsf"+i;
					if($(id) && $(id).value != ""){
						flag = false;
						break;
					}
				}else{
					var id = "djOption"+i;
					if($(id) && $(id).value != ""){
						flag = false;
						break;
					}
				}
			}
			
			if(flag){
				alert("请先录入分数，再保存！");
				return false;
			}else{
				saveUpdate('/xgxt/gzdxWsjc.do?method=fslrManage&doType=save','')
			}
		}
		</script>
	</head>
	<body onload="showWsdj()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				录入卫生分时，请注意查询条件中的“检查时间”，进入页面时，检查时间默认为当前时间，若你要录入的卫生分非当前时间，请先设置查询条件中的“检查时间”。若<bean:message key="lable.xb" />名称为空，请至【公寓管理人员】中为寝室维护寝室长。
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		<html:form action="/gzdxWsjc">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>	
			<input type="hidden" id="djNum" value="${djNum}"/>
			<input type="hidden" id="rsNum" value="${rsNum}"/>
			<input type="hidden" id="lrxs" value="${lrxs}"/>
			<input type="hidden" id="jcsj" value="${jcsj}"/>
			<!-- 卫生分等级非空 -->
			<logic:notEqual name="djNum" value="0">
				<logic:iterate name="wsfdjList" id="djnr" indexId="num">
					<input type="hidden" id="xx${num }" value="${djnr.wsfxx }"/>
					<input type="hidden" id="sx${num }" value="${djnr.wsfsx }"/>
					<input type="hidden" id="dj${num }" value="${djnr.wsfdj }"/>
				</logic:iterate>
			</logic:notEqual>	
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
							<li>
								<a href="#"
									onclick="saveWsf()"
									class="btn_ccg">保存</a>
							</li>
							<li>
								<a href="#"
									onclick="showSaveDiv();"
									class="btn_xg">保存其他信息</a>
							</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<%--<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="if($('jcsj')&&$('jcsj').value==''){alert('检查时间不能为空，请确认！')}else{allNotEmpThenGo('/xgxt/gzdxWsjc.do?method=fslrManage')};">
											查询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('xqdm-lddm-cs-qsh-xy-sfdf');return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									校区
								</th>
								<td>
									<html:select property="xqdm" style="width: 150px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>													
								</td>
								<th>
									楼栋
								</th>
								<td>
									<!-- 公寓老版本 -->
									<logic:equal name="gyglEdition" value="old">
										<html:select property="lddm" style="width: 150px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm" labelProperty="mc" />
										</html:select>
									</logic:equal>
									<!-- 公寓新版本 -->
									<logic:equal name="gyglEdition" value="new">
										<html:select property="lddm" style="width: 150px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:option value="">----请选择----</html:option>
											<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
										</html:select>
									</logic:equal>
								</td>
								<th>
									所属层数
								</th>
								<td>
									<html:select property="cs" style="width: 150px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh" style="width: 150px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
								<th>
									所属<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal value="true" name="isxy">
										<html:hidden property="xydm"/>
										<html:select property="xydm" style="width: 150px" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="true" name="isxy">
										<html:select property="xydm" onchange="" styleId="xy" style="width:150px" onmouseover="">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									是否已打分
								</th>
								<td>
									<html:select property="sfdf" styleId="sfdf" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									<logic:equal name="jczq" value="周">
										<font color="red">*</font>检查周次
									</logic:equal>
									<logic:equal name="jczq" value="日">
										<font color="red">*</font>检查时间
									</logic:equal>
								</th>
								<td>
									<logic:equal name="jczq" value="周">
										<html:select property="jczc" style="width: 150px" styleId="jczc" onchange="">
											<html:options collection="zcList" property="dm" labelProperty="mc" />
										</html:select>	
									</logic:equal>
									<logic:equal name="jczq" value="日">
										<html:text property="jcsj" styleId="jcsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('jcsj','y-mm-dd');"
										/>
									</logic:equal>
								</td>
								<th>
									检查部门
								</th>
								<td>
									<html:select property="jcbm" styleId="jcbm" style="width:150px">
										<html:options collection="jcbmList" property="dm" labelProperty="mc" />
									</html:select>	
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				--%><!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
				<logic:empty name="rsArrList">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rsArrList">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序；</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td width="1%">
										<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<!-- 分数是否关联等级 -->
									<logic:equal name="lrxs" value="分数">
										<logic:equal name="gldj" value="是">
											<td>
												卫生分等级
											</td>
										</logic:equal>
									</logic:equal>
									<!-- 等级是否关联分数 -->
									<logic:equal name="lrxs" value="等级">
										<logic:equal name="glfs" value="是">
											<td>
												卫生分分数
											</td>
										</logic:equal>
									</logic:equal>
									<td onclick="tableSort(this)" nowrap>
										检查人员
									</td>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rsArrList" id="rs" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="showQsInfo()">
									<td align="center">
										<input type="checkbox" id="checkVal" 
											name="primarykey_checkVal"  
											value="<logic:iterate id="v" name="rs" offset="0" length="1">${v }</logic:iterate>"/>
										<input type="hidden" name="jcld" value="<logic:iterate id="v" name="rs" offset="6" length="1">${v }</logic:iterate>"/>	
										<input type="hidden" name="jccs" value="<logic:iterate id="v" name="rs" offset="4" length="1">${v }</logic:iterate>"/>	
										<input type="hidden" name="jcqs" value="<logic:iterate id="v" name="rs" offset="5" length="1">${v }</logic:iterate>"/>					
									</td>
									<logic:iterate id="v" name="rs" offset="1" length="5">
										<td align="left">
											${v }
										</td>
									</logic:iterate>
									<logic:equal name="jczq" value="周">
										<td align="left">
											第${jczc }周
										</td>
									</logic:equal>
									<!-- 录入形式为分数 -->
									<logic:equal name="lrxs" value="分数">
										<td align="left">
											<input type="text" name="wsffs" id="wsf${index }" 
												onkeydown="return onlyNum(this,3)"
											    onmousedown="return onlyNum(this,3)"
											    maxlength="3" style="width: 50px;ime-mode:disabled"
											    onblur="setWsfdj(${index });chMax(this,100);"
											    value="<logic:iterate id="v" name="rs" offset="7" length="1">${v }</logic:iterate>"/>
										</td>
										<logic:equal name="gldj" value="是">
											<td width="100px">
												<input type="text" name="wsfdj" id="wsfdj${index }" value="" readonly="readonly"
													style="width:70px;"/>
											</td>
										</logic:equal>
									</logic:equal>
									<!-- 录入形式为等级 -->
									<logic:equal name="lrxs" value="等级">
										<td align="left">
											<select id="djOption${index }" style="width: 50px;" onchange="setWsffs(${index });">
												<option value=""></option>
												<logic:iterate name="wsfdjList" id="dj">
													<option value="${dj.wsfdj }">${dj.wsfdj }</option>
												</logic:iterate>
											</select>
											<input type="hidden" name="wsfdj" id="tempDj${index }" 
												value="<logic:iterate id="v" name="rs" offset="7" length="1">${v }</logic:iterate>"/>
										</td>
										<logic:equal name="glfs" value="是">
											<td width="50px">
												<input type="text" name="wsffs" id="wsf${index }" value="" readonly="readonly"
													style="width:50px;"/>
											</td>
										</logic:equal>
									</logic:equal>
									<td>
										<input type="hidden" name="arr_jcbm" value="<logic:iterate id="v" name="rs" offset="9" length="1">${v }</logic:iterate>"/>
										<input type="hidden" name="jcbz" value="<logic:iterate id="v" name="rs" offset="10" length="1">${v }</logic:iterate>"/>
<%--										<logic:iterate id="v" name="rs" offset="8" length="1">${v }</logic:iterate>--%>
										<logic:iterate id="v" name="rs" offset="11" length="1">${v}</logic:iterate>
									</td>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gzdxGyglTyForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
			<div id="showDiv" style="display:none;height: 500px" align="center">
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<th colspan="4">
								<span>保存其他信息</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<td align='right' width='25%'>
								检查人员
							</td>
							<td align='left'>
								<input type="text" id="jcry" name="jcry" value="${jcry}" maxlength="10"/>
<%--								<html:select property="bmdm" styleId="select_jcbm">--%>
<%--									<html:options collection="jcbmList" property="dm" labelProperty="mc" />--%>
<%--								</html:select>--%>
							</td>
						</tr>
						<tr>
							<td align='right' width='' height="100px">
								备注
								</br>
								<font color="red">(限录入250字)</font>
							</td>
							<td align='left'>
								<html:textarea property="bz" styleId="bz" style="height: 100px" onblur="chLeng(this,250)"/>
							</td>
						</tr>
						<tr>
							<td align='left' width='' colspan="2">
								<font color="blue">注：只对执行过卫生分保存的寝室进行操作。</font>
							</td>
						</tr>
					</tbody>		
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<button type="button" onclick="saveUpdate('/xgxt/gzdxWsjc.do?method=fslrManage&doType=upDate','')">
										提交
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick='hiddenMessage(true,true)'>
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>