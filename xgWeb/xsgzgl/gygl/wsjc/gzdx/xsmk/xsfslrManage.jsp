<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/wsjc.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
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
		
		function modi(url){
			if($("jczc")){
				url+= "&jczc="+$("jczc").value;
			}
			if($("jcsj")){
				url+= "&jcsj="+$("jcsj").value;
			}
			
			if(curr_row != null){
				showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('请选择要操作的数据行！');
				return false;
			}
		}
		
		</script>
	</head>
	<body onload="showWsdj()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commWsjc">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>	
			<input type="hidden" id="djNum" value="${djNum}"/>
			<input type="hidden" id="rsNum" value="${rsNum}"/>
			<input type="hidden" id="lrxs" value="${lrxs}"/>
			<!-- 卫生分等级非空 -->
			<logic:notEqual name="djNum" value="0">
				<logic:iterate name="wsfdjList" id="djnr" indexId="num">
					<input type="hidden" id="xx${num }" value="${djnr.wsfxx }"/>
					<input type="hidden" id="sx${num }" value="${djnr.wsfsx }"/>
					<input type="hidden" id="dj${num }" value="${djnr.wsfdj }"/>
				</logic:iterate>
			</logic:notEqual>	
			
			<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_xg" onclick="modi('/xgxt/commWsjc.do?method=xsfslrUpdate&doType=modi');">录入</a></li>
				</ul>
			</div>
			</div>
		</logic:equal>
			<div class="toolbox">
				
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>学号</th>
								<td><html:text property="xh"></html:text></td>
								<th>姓名</th>
								<td><html:text property="xm"></html:text></td>
								<th>
									校区
								</th>
								<td>
									<html:select property="xqdm" style="width: 150px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>													
								</td>
							</tr>							
							<tr>
								<th>
									楼栋
								</th>
								<td>
									<html:select property="lddm" style="width: 150px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									所属层数
								</th>
								<td>
									<html:select property="cs" style="width: 150px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh" style="width: 150px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
								<th></th>
								<td></td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
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
										<html:select property="xydm" onchange="" styleId="xy" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
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
										<html:select property="jczc" style="width: 150px" styleId="jczc" onchange="$('search_go').click()">
											<html:options collection="zcList" property="dm" labelProperty="mc" />
										</html:select>	
									</logic:equal>
									<logic:equal name="jczq" value="日">
										<html:text property="jcsj" styleId="jcsj" onchange="$('search_go').click()"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('jcsj','y-mm-dd');"
										/>
									</logic:equal>
								</td>
								<th></th><td></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="if($('jcsj')&&$('jcsj').value==''){alert('检查时间不能为空，请确认！')}else{allNotEmpThenGo('/xgxt/commWsjc.do?method=xsfslrManage')};">
											查询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="searchReset();return false;">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<!-- 过滤条件 end-->
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序；若<bean:message key="lable.xb" />名称为空，请检查该寝室是否已在宿舍分配模块被分配。</font> 
						</span>
					</h3>
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rsArrList" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('/xgxt/commWsjc.do?method=xsfslrUpdate&doType=view');"
								align="left"
								style="cursor:hand">
					
								<logic:iterate id="v" name="s" offset="0">
									<td>
										<input type="hidden" value="${v }"/>
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--分页end-->
						
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>