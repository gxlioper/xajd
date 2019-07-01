<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type="text/javascript">
	function showlr(url,doType,w,h){
		if(doType == "update"){
			if(curr_row == null){
				alert('请选择要录入的数据！');
				return false;
			}
		
		var pk = curr_row.getElementsByTagName('input')[0].value;
		url+="&doType="+doType;
		url+="&pk="+pk;
		showTopWin(url,w,h);
	}
	}
	
	function initCxf(){
		var xn = $('xn').value;
		var xq = $('xq').value;
		var xqmc = DWRUtil.getText('xq');
		if('' != xn && '' != xq){
			var message = "您要初始化的周期为\n学年："+xn+",学期:"+xqmc+"\n您确定要这么做吗？";
			if(confirm(message)){
				refreshForm('zjjtPjpy.do?method=initCxf&xn='+xn+'&xq='+xq);
			}
		}
	}
	
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjjtPjpy" method="post">
			<%@ include file="/pjpy/hiddenValue.jsp"%>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" />
					</a>
				</p>
			</div>

			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:equal name="mklx" value="lr">
								<li>
									<a href="#" class="btn_csh" title="初始化学生（未录入操行分记录的学生）的操行分为默认值"
										onclick='tipsWindown("系统提示","id:cshDiv","350","165","true","","true","id")'>初始化操行分</a>
								</li>
								<logic:equal name="userType" value="xx">
									<li>
										<a href="#" class="btn_xg"
											onclick="showTopWin('/xgxt/zjjtPjpy.do?method=szManage','400','250')">设置</a>
									</li>
								</logic:equal>
								<logic:equal name="userType" value="admin">
									<li>
										<a href="#" class="btn_xg"
											onclick="showTopWin('/xgxt/zjjtPjpy.do?method=szManage','400','250')">设置</a>
									</li>
								</logic:equal>
								<logic:equal name="bzrQx" value="false">
									<li>
										<a href="#" class="btn_zj"
											onclick="showlr('/xgxt/zjjtPjpy.do?method=cxfwhUpdate&mklx='+$('mklx').value,'update','1000','600')">录入</a>
									</li>
								</logic:equal>
							</logic:equal>
							<logic:equal name="mklx" value="cx">
								<li>
									<a href="#" class="btn_sc"
										onclick="showInfo('/xgxt/zjjtPjpy.do?method=cxfwhUpdate&mklx='+$('mklx').value,'update','1000','600')">删除</a>
								</li>
								<li>
									<a href="#" class="btn_dr" onclick="impAndChkData()">导入</a>
								</li>
								<li>
									<a href="#" class="btn_dc" onclick="dataExport()">导出</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</div>
			</logic:equal>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
					<div class="searchtab">
						<table width="100%" class="" border="0">
							<tbody>
								<!-- 录入 -->
								<logic:equal name="mklx" value="cx">
									<tr>
										<th>
											学年
										</th>
										<td>
											<html:select property="xn" style="" onchange="">
												<html:options collection="xnList" property="xn"
													labelProperty="xn" />
											</html:select>
										</td>
										<th>
											学期
										</th>
										<td>
											<html:select property="xq" style="" onchange="">
												<html:option value=""></html:option>
												<html:options collection="xqList" property="xqdm"
													labelProperty="xqmc" />
											</html:select>
										</td>
										<th></th>
										<td></td>
										<th></th>
										<td></td>
									</tr>
								</logic:equal>
								<!-- 录入 end-->
								<tr>
									<th>
										年级
									</th>
									<td>
										<html:select property="nj" style=""
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
										<html:text property="xh" styleId="xh" style="" maxlength="20" />
									</td>
									<th>
										姓名
									</th>
									<td colspan="3">
										<html:text property="xm" styleId="xm" style="" maxlength="20" />
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<html:select property="xydm" style="width:170px" styleId="xy"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										专业
									</th>
									<td>
										<html:select property="zydm" style="width:170px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										班级
									</th>
									<td colspan="3">
										<html:select property="bjdm" style="width:170px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										校区
									</th>
									<td>
										<html:select property="xqdm" style="" styleId="xqdm"
											onchange="setLdList()">
											<html:options collection="xqdmList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										楼栋
									</th>
									<td>
										<html:select property="lddm" style="" styleId="lddm"
											onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										所属层数
									</th>
									<td>
										<html:select property="cs" style="" styleId="cs"
											onchange="setQsList();">
											<html:options collection="csList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										寝室号
									</th>
									<td>
										<html:select property="qsh" style="" styleId="qsh" onchange="">
											<html:options collection="qsList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<logic:equal name="mklx" value="cx">
								<tr>
									<th>
										开始日期
									</th>
									<td colspan="2">
										<html:text readonly="readonly"
										property="ksrq" styleId="ksrq"
										onclick="return showCalendar('ksrq','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
									</td>
									<th>
										结束日期
									</th>
									<td colspan="2">
										<html:text readonly="readonly"
										property="jsrq" styleId="jsrq"
										onclick="return showCalendar('jsrq','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
									</td>
									<th>
										
									</th>
									<td>
										
									</td>
								</tr>
								</logic:equal>
							</tbody>

							<tfoot>
								<tr>
									<td colspan="8">
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											<!-- 录入 -->
											<logic:equal name="mklx" value="lr">
												<button id="search_go"
													onclick="allNotEmpThenGo('/xgxt/zjjtPjpy.do?method=cxfwhLr');">
													查询
												</button>
											</logic:equal>
											<!-- 查询 -->
											<logic:equal name="mklx" value="cx">
												<button id="search_go"
													onclick="allNotEmpThenGo('/xgxt/zjjtPjpy.do?method=cxfwhCx');">
													查询
												</button>
											</logic:equal>
											<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												重 置
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>


					<div class="formbox">
						<logic:empty name="rs">
							<h3 class="datetitle_01">
								<span> 查询结果&nbsp;&nbsp; <font color="red">未找到任何记录！</font>
								</span>
							</h3>
						</logic:empty>
						<logic:notEmpty name="rs">
							<h3 class="datetitle_01">
								<span> 查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font>
								</span>
							</h3>
							<div class="con_overlfow">
								<table width="100%" id="rsTable" class="dateline tablenowrap">
									<thead>
										<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="topTr" offset="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="showInfo('/xgxt/zjjtPjpy.do?method=cxfwhUpdate&mklx='+$('mklx').value,'view','1000','600')">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<td align="center">
													<bean:write name="v" />
													<input type="checkbox" id="checkVal" name="checkVal"
														style="display : none"
														value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
												</td>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</div>
							<!--分页显示-->
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
							<!--分页显示-->
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
						</logic:notEmpty>
					</div>
			</logic:empty>

			<div id="cshDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请选择初始化学年、学期</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>学年
								</th>
								<td>
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>学期
								</th>
								<td>
									<html:select property="xq" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
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
										<button name="确定" onclick="initCxf()">
											确 定
										</button>
										<button name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>


	<div style="height:200px"></div>

		</html:form>


		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
	</body>
</html>
