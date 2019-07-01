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
		//页签处理
		function checkLi(liId){
			if(liId == "qsA"){
				$("qsLi").className = 'ha';
				$("xsLi").className = '';
				$("lx").value="qs"
				$("xgId").style.display = "";
				$("scId").style.display = "";
				$("dcId").style.display = "";
			}else{
				$("qsLi").className = '';
				$("xsLi").className = 'ha';
				$("lx").value="xs"
				$("xgId").style.display = "none";
				$("scId").style.display = "none";
				$("dcId").style.display = "none";
			}
		}
		
		//查询
		function searchRs(){
			allNotEmpThenGo('/xgxt/commWsjc.do?method=fscxManage');
		}
		
		function setLiClass(){
			var lx = $("lx").value;
			if(lx == "qs"){
				$("qsLi").className = 'ha';
				$("xsLi").className = '';
				$("xgId").style.display = "";
				$("scId").style.display = "";
				$("dcId").style.display = "";
			}else{
				$("qsLi").className = '';
				$("xsLi").className = 'ha';
				$("xgId").style.display = "none";
				$("scId").style.display = "none";
				$("dcId").style.display = "none";
			}
		}
		
		//显示详细
		function showXxInfo(type){
			var lx = $("lx").value 
			var url = "/xgxt/commWsjc.do?method=fscxUpdate";
				url+= "&lx="+lx;

			showInfo(url,type,'800','600');
		}
		
		//导出
		function wsjcExp(){
		
			var lx = $("lx").value 
			var url = "/xgxt/commWsjc.do?method=fscxManage&doType=exp";
				url+= "&lx="+lx;

			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		
		</script>
	</head>
	<body onload="setLiClass();showGdtj('Three-Four-Five-Six')">
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
			<!-- 页签 -->
			<div class="compTab" id="card">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li id="qsLi">
							<a href="#"
								onclick="checkLi(this.id);searchRs()"
							 	id="qsA">
								<span>寝室查询</span>
							</a>
						</li>
						<li id="xsLi">
							<a href="#" 
								onclick="checkLi(this.id);searchRs()"
							 	id="xsA">
								<span>学生查询</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- 页签 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li id="xgId">
								<a href="#"
									onclick="showXxInfo('update')"
									class="btn_xg">修改</a>
							</li>
							<li id="scId">
								<a href="#"
									onclick="sumitInfo('/xgxt/commWsjc.do?method=fscxManage','del');"
									class="btn_sc">删除</a>
							</li>
							<li id="dcId">
								<a href="#"
									onclick="impAndChkData();"
									class="btn_dr">导入</a>
							</li>
							<li>
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
										<html:checkbox property="gdtj" styleId="gdtj"  onclick="showGdtj('Three-Four-Five-Six')"/>更多条件
									</div>
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="searchRs();return false;">
											查询
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
									检查部门
								</th>
								<td>
									<html:select property="jcbm" styleId="jcbm" style="width:150px" onmouseover="">
										<html:options collection="jcbmList" property="dm" labelProperty="mc" />
									</html:select>	
								</td>
							</tr>
							<!-- 第五行 -->
							<tr id="trFive" style="display: none">
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" style="" maxlength="20"/>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="" maxlength="20"/>
								</td>
								<th>
									政治面貌
								</th>
								<td>
									<html:select property="zzmm" styleId="zzmm" style="width:150px" onmouseover="">
										<html:option value=""></html:option>
										<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
									</html:select>	
								</td>
							</tr>
							<!-- 第六行 -->
							<tr id="trSix" style="display: none">
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
								<logic:equal name="lrxs" value="分数">
									<th>
										卫生分
									</th>
									<td>
										<html:text property="fsxx" styleId="fsxx" 
											maxlength="3" onblur="chMax(this,100);"
											onkeydown="return onlyNum(this,3)"
										 	onmousedown="return onlyNum(this,3)"
											style="width: 70px;ime-mode:disabled"/>
										—
										<html:text property="fssx" styleId="fssx" 
											maxlength="3" onblur="chMax(this,100);"
											onkeydown="return onlyNum(this,3)"
										 	onmousedown="return onlyNum(this,3)"
											style="width: 70px;ime-mode:disabled"/>
									</td>
									<th>
										<logic:equal name="gldj" value="是">
											卫生分等级
										</logic:equal>
									</th>
									<td>
										<logic:equal name="gldj" value="是">
											<html:select property="dj" style="width: 150px" styleId="dj" onchange="">
												<html:option value=""></html:option>
												<html:options collection="wsfdjList" property="wsfdj" labelProperty="wsfdj" />
											</html:select>	
										</logic:equal>
									</td>
								</logic:equal>
								<logic:equal name="lrxs" value="等级">
									<th>
										卫生分等级
									</th>
									<td>
										<html:select property="dj" style="width: 150px" styleId="dj" onchange="">
											<html:option value=""></html:option>
											<html:options collection="wsfdjList" property="wsfdj" labelProperty="wsfdj" />
										</html:select>	
									</td>
									<th>
										<logic:equal name="glfs" value="是">
											卫生分
										</logic:equal>
									</th>
									<td>
										<logic:equal name="glfs" value="是">
											<html:text property="fsxx" styleId="fsxx" 
												maxlength="3" onblur="chMax(this,100);"
												onkeydown="return onlyNum(this,3)"
											 	onmousedown="return onlyNum(this,3)"
												style="width: 70px;ime-mode:disabled"/>
											—
											<html:text property="fssx" styleId="fssx" 
												maxlength="3" onblur="chMax(this,100);"
												onkeydown="return onlyNum(this,3)"
											 	onmousedown="return onlyNum(this,3)"
												style="width: 70px;ime-mode:disabled"/>
										</logic:equal>
									</td>
								</logic:equal>
							</tr>
						</tbody>
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序。</font> 
						</span>
					</h3>
					<div class="con_overlfow">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td width="1%">
										<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
									</td>
									<logic:equal name="lx" value="qs">
										<td onclick="tableSort(this)" nowrap>学年</td>
										<td onclick="tableSort(this)" nowrap>学期</td>
										<td onclick="tableSort(this)" nowrap>年度</td>
										<td onclick="tableSort(this)" nowrap>校区</td>
										<td onclick="tableSort(this)" nowrap>楼栋</td>
										<td onclick="tableSort(this)" nowrap>层数</td>
										<td onclick="tableSort(this)" nowrap>寝室号</td>
										<td onclick="tableSort(this)" nowrap>检查时间</td>
										<td onclick="tableSort(this)" nowrap>检查部门</td>
										<logic:equal name="lrxs" value="分数">
											<td onclick="tableSort(this)" nowrap>卫生分</td>
											<logic:equal name="gldj" value="是">
												<td onclick="tableSort(this)" nowrap>卫生等级</td>
											</logic:equal>
										</logic:equal>
										<logic:equal name="lrxs" value="等级">
											<td onclick="tableSort(this)" nowrap>卫生等级</td>
											<logic:equal name="glfs" value="是">
												<td onclick="tableSort(this)" nowrap>卫生分</td>
											</logic:equal>
										</logic:equal>
									</logic:equal>
									<logic:equal name="lx" value="xs">
										<td onclick="tableSort(this)" nowrap>学号</td>
										<td onclick="tableSort(this)" nowrap>姓名</td>
										<td onclick="tableSort(this)" nowrap>学年</td>
										<td onclick="tableSort(this)" nowrap>学期</td>
										<td onclick="tableSort(this)" nowrap>年度</td>
										<td onclick="tableSort(this)" nowrap>校区</td>
										<td onclick="tableSort(this)" nowrap>楼栋</td>
										<td onclick="tableSort(this)" nowrap>层数</td>
										<td onclick="tableSort(this)" nowrap>寝室号</td>
										<td onclick="tableSort(this)" nowrap>检查时间</td>
										<td onclick="tableSort(this)" nowrap>检查部门</td>
										<logic:equal name="lrxs" value="分数">
											<td onclick="tableSort(this)" nowrap>卫生分</td>
											<logic:equal name="gldj" value="是">
												<td onclick="tableSort(this)" nowrap>卫生等级</td>
											</logic:equal>
										</logic:equal>
										<logic:equal name="lrxs" value="等级">
											<td onclick="tableSort(this)" nowrap>卫生等级</td>
											<logic:equal name="glfs" value="是">
												<td onclick="tableSort(this)" nowrap>卫生分</td>
											</logic:equal>
										</logic:equal>
									</logic:equal>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rsArrList" id="rs" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="showXxInfo('view')">
									<td align="center">
										<input type="checkbox" id="checkVal" 
											name="primarykey_checkVal"  
											value="<logic:iterate id="v" name="rs" offset="0" length="1">${v }</logic:iterate>"/>
									</td>
									<!-- 检查寝室 -->
									<logic:equal name="lx" value="qs">
										<logic:iterate id="v" name="rs" offset="1" length="9">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
										<logic:equal name="lrxs" value="分数">
											<td align="left">
												<logic:iterate id="v" name="rs" offset="10" length="1">${v }</logic:iterate>
												<input type="hidden" name="wsffs" id="wsf${index }" 										
												    value="<logic:iterate id="v" name="rs" offset="10" length="1">${v }</logic:iterate>"/>
											</td>
											<logic:equal name="gldj" value="是">
												<td width="100px">
													<logic:iterate id="v" name="rs" offset="12" length="1">${v }</logic:iterate>
												</td>
											</logic:equal>
										</logic:equal>
										<logic:equal name="lrxs" value="等级">
											<td align="left">
												<logic:iterate id="v" name="rs" offset="11" length="1">${v }</logic:iterate>
											</td>
											<logic:equal name="glfs" value="是">
												<td width="50px">
													<logic:iterate id="v" name="rs" offset="12" length="1">${v }</logic:iterate>
												</td>
											</logic:equal>
										</logic:equal>
									</logic:equal>
									<!-- 检查学生 -->
									<logic:equal name="lx" value="xs">
										<logic:iterate id="v" name="rs" offset="1" length="11">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
										<logic:equal name="lrxs" value="分数">
											<td align="left">
												<logic:iterate id="v" name="rs" offset="12" length="1">${v }</logic:iterate>
												<input type="hidden" name="wsffs" id="wsf${index }" 										
												    value="<logic:iterate id="v" name="rs" offset="12" length="1">${v }</logic:iterate>"/>
											</td>
											<logic:equal name="gldj" value="是">
												<td width="100px">
													<logic:iterate id="v" name="rs" offset="14" length="1">${v }</logic:iterate>
												</td>
											</logic:equal>
										</logic:equal>
										<logic:equal name="lrxs" value="等级">
											<td align="left">
												<logic:iterate id="v" name="rs" offset="13" length="1">${v }</logic:iterate>
											</td>
											<logic:equal name="glfs" value="是">
												<td width="50px">
													<logic:iterate id="v" name="rs" offset="14" length="1">${v }</logic:iterate>
												</td>
											</logic:equal>
										</logic:equal>
									</logic:equal>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>