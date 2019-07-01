<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//统计报修信息
		function bxtj(){
			var tjfs = $("tjfs").value;
			var tjfw = $("tjfw").value;
			
			if(tjfs != "" && tjfw != ""){
				allNotEmpThenGo('/xgxt/zjcmGygl.do?method=bxtjManage');
			}else{
				alert("请确定统计范围以及统计方式！");
			}
		}
		
		//输出excel
		function expInfo(){
			var tjfs = $("tjfs").value;
			var tjfw = $("tjfw").value;
		
			if(tjfs != "" && tjfw != ""){
				wjcfDataExport('/xgxt/zjcmGygl.do?method=bxtjManage&doType=exp');
			}else{
				alert("请确定统计范围以及统计方式！");
			}
		}
		//修改统计方式
		function chTjfs(){
		
			dwr.engine.setAsync(false);
			
			var id = "tjfw";
			var tjfs = $("tjfs").value;
			var fw = $("fw").value;
			
			if(tjfs != ""){	
				
				getGyglDAO.getSelectList(tjfs,function(data) {
					
					DWRUtil.removeAllOptions(id);
					DWRUtil.addOptions(id,data,"en","cn");
					$(id).value = "";
				});
				
			}else{
				DWRUtil.removeAllOptions(id);
			}
			
			if(tjfs == "bxtjfw_cl"){
				$("clId").style.display = "";
			}else{
				$("clId").style.display = "none";
			}
			
			if(fw != ""){
				$(id).value = fw;
			}
			dwr.engine.setAsync(true);
		}
		</script>
	</head>

	<body onload="chTjfs()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjcmGygl" >
		
			<!-- 隐藏域 -->
			<%@ include file="/gygl/hiddenValue.jsp"%>
			<input type="hidden" id="fw" value="${fw }"/>
			<!-- 隐藏域 end-->
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="expInfo()" 
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
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="bxtj()">
											统计
										</button>
										<button class="button2" style="" id="cz"
											onclick="czSearchCond('nj-xn-xq-nd-xy-zy-bj-xh-xm-xqdm-lddm-cs-qsh-querygreaterequal_bxsj-querylessequal_bxsj-sfsf-sfwg-tjcllx-tjclmc-tjfs-tjfw')">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj" style="" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>												
								</td>
								<th>
									学号
								</th>
								<td>
									<!-- 学生用户 -->
									<logic:equal name="userType" value="stu">
										<html:text property="querylike_xh" styleId="xh" style="width: 100px" maxlength="20" readonly="true"/>
									</logic:equal>
									<!-- 非学生用户 -->
									<logic:notEqual name="userType" value="stu">
										<html:text property="querylike_xh" styleId="xh" style="width: 100px" maxlength="20"/>
									</logic:notEqual>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" styleId="xm" style="width: 100px" maxlength="20"/>	
								</td>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" style="" styleId="xb">
										<html:option value=""></html:option>
										<html:options collection="xbList" property="en" labelProperty="cn" />
									</html:select>	
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<!-- 是学院用户 -->
									<logic:equal name="isxy" value="true">
										<html:hidden property="queryequals_xydm"/>
										<html:select property="xydm" style="" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>	
									</logic:equal>
									<!-- 是学院用户 end -->
									<!-- 非学院用户 -->
									<logic:equal name="isxy" value="false">
										<html:select property="queryequals_xydm" style="width: 100px" styleId="xy" 
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>	
									</logic:equal>
									<!-- 非学院用户 end-->											
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm" style="width: 100px" styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" style="width: 100px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									是否完工
								</th>
								<td>
									<html:select property="sfwg" style="width: 100px" styleId="sfwg">
										<html:option value="">----请选择----</html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									校区
								</th>
								<td>
									<html:select property="queryequals_xqdm" style="width: 100px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>								
								</td>
								<th>
									楼栋
								</th>
								<td>
									<html:select property="queryequals_lddm" style="width: 100px" styleId="lddm" 
										onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									所属层数
								</th>
								<td>
									<html:select property="queryequals_cs" style="width: 100px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="queryequals_qsh" style="width: 100px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>				
								</td>
							</tr>
							<!-- 第四行 -->
							<tr>
								<th>
									<font color="red">*</font>统计方式
								</th>
								<td>
									<html:select property="tjfs" styleId="tjfs" style="width: 100px" onchange="chTjfs()">
										<html:option value="">----请选择----</html:option>
										<html:options collection="tjfsList" property="en" labelProperty="cn" />
									</html:select>				
								</td>
								<th>
									是否收费
								</th>
								<td>
									<html:select property="queryequals_sfsf" style="width: 100px" styleId="sfsf">
										<html:option value="">----请选择----</html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									报修时间
								</th>
								<td colspan="3">
									<html:text property="querygreaterequal_bxsj" styleId="querygreaterequal_bxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:100px"
										onclick="return showCalendar('querygreaterequal_bxsj','y-mm-dd');"/>	
									—
									<html:text property="querylessequal_bxsj" styleId="querylessequal_bxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:100px"
										onclick="return showCalendar('querylessequal_bxsj','y-mm-dd');"/>	
								</td>
							</tr>
							<!-- 第五行 -->
							<tr>
								<th>
									<font color="red">*</font>统计范围
								</th>
								<td>
									<html:select property="tjfw" styleId="tjfw" style="" onchange="">
										<html:option value="">----请选择----</html:option>
									</html:select>
								</td>
								<td colspan="6">
									<span id="clId" style="display : none">
									&nbsp;&nbsp;&nbsp;&nbsp;材料类型
									<html:select property="tjcllx" styleId="tjcllx" style="width: 100px" onchange="">
										<html:options collection="cllxList" property="dm" labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									材料名称&nbsp;&nbsp;
									<html:text property="tjclmc" styleId="tjclmc" style="width: 100px" maxlength="25"/>		
									</span>
								</td>
								
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
						<table summary="" class="dateline tablenowrap" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rsArrList" id="rs" indexId="index">
								<!-- 不算总计 -->
								<logic:notEqual name="index" value="${rsNum }">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<logic:iterate id="v" name="rs">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:notEqual>
								<!-- 总计 -->
								<logic:equal name="index" value="${rsNum }">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<logic:iterate id="v" name="rs" offset="0" length="1">
											<td align="left" colspan="${topNum }">
												${v }
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="rs" offset="${topNum }">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:equal>
							</logic:iterate>
							<!--内容 end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>