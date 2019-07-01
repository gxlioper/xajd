<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss"/>

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">	
	
	function updateHdsh(type){
		var checkBoxArr = document.getElementsByName("checkVal");
		var selall = document.getElementById('selall');
		var flag = false;
			
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		
		if(type == "tg" || type =="wtg" || type == "del"){
			if(flag){
				var msg;
				if(type=="tg"){
					msg="已通过";
				}else{
					msg="未通过";
				}
				if(type != "del"){
					if (confirm("将所勾选申请的审核状态改为"+msg+"。\n点击\"确定\"，审核完成。\n点击\"取消\"，将放弃审核！")) {
						refreshForm('/xgxt/zgddShgzHdgl.do?method=hdglManage&doType='+type)
					}
				}else{
					if (confirm("确认要删除所选申请吗？\n点击\"确定\"，删除申请。\n点击\"取消\"，将放弃删除！")) {
						refreshForm('/xgxt/zgddShgzHdgl.do?method=hdglManage&doType='+type)
					}
				}
			}else{
				alert("请勾选欲处理信息");
				return false;
			}
		}else if(type == "sh" || type == "view" ){
			if(curr_row == null){
				alert('请选择要处理的信息!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				showTopWin('/xgxt/zgddShgzHdgl.do?method=hdglSh&pk='+pk+"&doType="+type,600,480)
			}
		}
	}
	
	function disabled() {
        if($("userType")){
            var ele="";
	        if ($("userType").value == "xy") {
	             ele ="xy";
	             var tmp = ele.split("-");
		         for (i = 0; i < tmp.length; i++) {
		 	       document.getElementById(tmp[i]).disabled = true;
		         }		      
	        }else if($("userType").value == "stu"){
	             ele ="xy-zy-bj-xh-xm";
	             var tmp = ele.split("-");
		         for (i = 0; i < tmp.length; i++) {
		 	       document.getElementById(tmp[i]).disabled = true;
		         }	        
	        }

        }
    }
	</script>
	<body onload="disabled()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getZbrDAO.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/zgddShgzHdgl" method="post">
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<logic:equal name="doType" value="jg">
								<li>
									<a href="#" class="btn_ck" onclick="updateHdsh('view')"> 查看
										</button>
								</li>
								<li>
									<a href="#" class="btn_dr" onclick="impAndChkData()"> 导入数据 </a>
								</li>
								<li>
									<a href="#" class="btn_dc" onclick="dataExport()"> 导出数据 </a>
								</li>
							</logic:equal>
							<logic:notEqual name="doType" value="jg">
								<logic:equal name="writeAble" value="yes">
									<logic:notEqual name="userType" value="xy">
										<li>
											<a href="#" class="btn_sh" onclick="updateHdsh('tg')"> 审核通过 </a>
										</li>
										<li>
											<a href="#" class="btn_sh" onclick="updateHdsh('wtg')"> 审核不通过 </a>
										</li>
										<li>
											<a href="#" class="btn_sc" onclick="updateHdsh('del')"> 删除 </a>
										</li>
									</logic:notEqual>
									<logic:equal name="userType" value="xy">
										<logic:equal name="needXy" value="yes">
											<li>
												<a href="#" class="btn_sh" onclick="updateHdsh('tg')"> 审核通过 </a>
											</li>
											<li>
												<a href="#" class="btn_sh" onclick="updateHdsh('wtg')"> 审核不通过 </a>
											</li>
										</logic:equal>
									</logic:equal>
								</logic:equal>
							</logic:notEqual>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<logic:equal name="doType" value="jg">
											<button class="" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/zgddShgzHdgl.do?method=hdglHdjg')">
												查询
											</button>
										</logic:equal>
										<logic:notEqual name="doType" value="jg">
											<button class="" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/zgddShgzHdgl.do?method=hdglManage')">
												查询
											</button>
										</logic:notEqual>
										<input type="hidden" name="go" value="a" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									活动名称
								</th>
								<td>
									<html:select property="hddm" onchange="">
										<html:option value=""></html:option>
										<html:options collection="hdList" property="hddm"
											labelProperty="hdmc" />
									</html:select>
								</td>
								<logic:notEqual name="doType" value="jg">
									<th>
										学年
									</th>
									<td>
										<html:select property="xn" style="" disabled="true">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="xq" style="" disabled="true">
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
								</logic:notEqual>
								<logic:equal name="doType" value="jg">
									<th>
										学年
									</th>
									<td>
										<html:select property="xn" style="">
											<html:option value=""></html:option>
											<html:options collection="xnVList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
								</logic:equal>
							</tr>
							<tr>
							<th>
									学号
								</th>
								<td>
									<html:text property="xh" style="width:85px"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" style="width:85px"></html:text>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:160px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:160px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:160px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
			 		记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:equal name="doType" value="jg">
								<font color="blue">提示：单击表头可以排序,双击查看详细</font>
							</logic:equal>
							<logic:notEqual name="doType" value="jg">
								<font color="blue">提示：单击表头可以排序,双击查看详细，单条审核</font>
							</logic:notEqual>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align="center"
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:equal name="doType" value="jg">
										<logic:iterate id="tit" name="topTr" offset="1" length="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</logic:equal>
									<logic:notEqual name="doType" value="jg">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" >
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" length="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<logic:equal name="userType" value="xy">
											<logic:iterate id="tit" name="topTr" offset="2" length="9">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:equal>
										<logic:notEqual name="userType" value="xy">
											<logic:iterate id="tit" name="topTr" offset="2" length="10">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:notEqual>
									</logic:notEqual>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<logic:equal name="doType" value="jg">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="updateHdsh('view')">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="hidden" value="<bean:write name="v" />" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="2">
												<td align="left">
													<bean:write name="v" filter="false" />
												</td>
											</logic:iterate>
										</tr>
									</logic:equal>
									<logic:notEqual name="doType" value="jg">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="updateHdsh('sh')">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
											</td>
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="hidden" value="<bean:write name="v" />" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="2" length="8">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="10" length="1">
												<td align="left">
													<bean:write name="v" filter="false" />
												</td>
											</logic:iterate>
											<logic:notEqual name="userType" value="xy">
												<logic:iterate id="v" name="s" offset="11" length="1">
													<td align="left">
														<bean:write name="v" filter="false" />
													</td>
												</logic:iterate>
											</logic:notEqual>
										</tr>
									</logic:notEqual>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=zgddShgzForm"></jsp:include>
					<!--分页显示-->
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				//alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				//alert("操作失败");
			</script>
		</logic:equal>
	</body>
</html>
