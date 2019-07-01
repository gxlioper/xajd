<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/wsjc.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		function searchRs(){
			allNotEmpThenGo('/xgxt/commWsjc.do?method=fstjManage');
		}
		
		//导出
		function wsjcExp(){
		
			var url = "/xgxt/commWsjc.do?method=fstjManage&doType=exp";

			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		</script>
	</head>
	<body onload="showGdtj('Three-Four-Five')">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commWsjc">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="lx" styleId="lx"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li id="xgId">
								<a href="#"
									onclick="wsjcExp()"
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
									<div class="bz">
										<html:checkbox property="gdtj" styleId="gdtj"  onclick="showGdtj('Three-Four-Five')"/>更多条件
									</div>
									<div class="btn">
										<input type="hidden" name="go" value="a"/>
										<button class="btn_cx" id="search_go"
											onclick="showTips('处理统计中，请等待......');searchRs()">
											统 计
										</button>
										<button class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('xn-xq-nd-xqdm-lddm-cs-qsh-xy-sfdf-jcbm-nj-xy-zy-bj-xh-xm-zzmm-dj-fssx-fsxx');return false;">
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
									年度
								</th>
								<td>
									<html:select property="nd" style="width: 150px" onchange="">
										<html:options collection="ndList" property="nd" labelProperty="nd" />
									</html:select>												
								</td>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="width: 150px" onchange="">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>		
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" style="width: 150px" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
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
							<!-- 第三行 -->
							<tr id="trThree" style="display: none">
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh" style="width: 150px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
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
										<html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList()" onmouseover="">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<!-- 第四行 -->
							<tr id="trFour" style="display: none">
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()" styleId="zy" style="width:150px" onmouseover="">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px" onmouseover="">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									检查时间
								</th>
								<td>
									<logic:equal name="jczq" value="周">
										<html:select property="kszc" style="width: 70px" styleId="kszc" onchange="">
											<html:options collection="zcList" property="dm" labelProperty="mc" />
										</html:select>	
										—
										<html:select property="jszc" style="width: 70px" styleId="jszc" onchange="">
											<html:options collection="zcList" property="dm" labelProperty="mc" />
										</html:select>	
									</logic:equal>
									<logic:equal name="jczq" value="日">
										<html:text property="kssj" styleId="kssj"
											onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
											onclick="return showCalendar('kssj','y-mm-dd');"
										/>
										—
										<html:text property="jssj" styleId="jssj"
											onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
											onclick="return showCalendar('jssj','y-mm-dd');"
										/>
									</logic:equal>
								</td>
							</tr>
							<!-- 第五行 -->
							<tr id="trFive" style="display: none">
								<th>
									统计对象
								</th>
								<td>
									<html:select property="tjfs" style="width: 150px" styleId="tjfs" onchange="">
										<html:options collection="tjfsList" property="en" labelProperty="cn" />
									</html:select>	
								</td>
								<th>
									统计范围
								</th>
								<td>
									<html:select property="tjfw" style="width: 150px" styleId="tjfw" onchange="">
										<html:options collection="tjfwList" property="en" labelProperty="cn" />
									</html:select>	
								</td>
								<th>
									<logic:notEmpty name="wsfdj">
										等级情况
									</logic:notEmpty>
								</th>
								<td>
									<logic:notEmpty name="wsfdj">
										<html:select property="dj" style="width: 150px" styleId="dj" onchange="">
											<html:option value=""></html:option>
											<html:options collection="wsfdjList" property="wsfdj" labelProperty="wsfdj" />
										</html:select>	
									</logic:notEmpty>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
			</div>
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
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序</font> 
						</span>
					</h3>
					<div class="con_overlfow">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rsArrList" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" >
									<logic:iterate id="v" name="s" offset="1" length="${num-2 }">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="${num }">
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
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						  <script type="text/javascript">
					      $('choose').className="hide";
					     </script>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->

			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>