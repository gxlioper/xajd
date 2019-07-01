<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script language="javascript">
		function searchRs(){
		
			if($('jxjdm').value == ''){
				alert('请确认设置的奖学金名称');
				return false;
			}
			
			if($('nj').value == ''){
				alert('请确认所要设置的年级');
				return false;
			}
			
			allNotEmpThenGo('/xgxt/guizdxPjpy.do?method=rsszXyManage');
		}
		
		//保存剩余评奖人数
		function setSyPjrs(){
			var xyrs = $("xyrs").value;
			var syrs = "0";
			var szrs = document.getElementsByName("szrs");
			for(var i=0;i<szrs.length;i++){
				if(szrs[i].value !="" && szrs[i].value !=null){
					var zyrs = szrs[i].value
					syrs = parseInt(syrs) + parseInt(zyrs);
				}
			}
			syrs = parseInt(xyrs) - parseInt(syrs);
			
			if(syrs < 0){
				if(!$("btn_bc").disabled){
					alert("剩余人数小于0，请重新确认");
				}
				if($('btn_bc')){$("btn_bc").disabled = true};
			}else{
				if($('btn_bc')){$("btn_bc").disabled = false};
			}
			
			
			$("syrs").value = syrs;
		}
		
		function expRs(){
		
			wjcfDataExport('guizdxPjpy.do?method=rsszXyManage&doType=exp');
		}
		</script>
	</head>
	<body onload="setSyPjrs()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/guizdxPjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<logic:equal value="yes" name="writeAble">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" class="btn_bc"
										onclick="refreshForm('/xgxt/guizdxPjpy.do?method=rsszXyManage&doType=save')"
										id="btn_bc">保存</a>
								</li>
								<li>
									<a href="#" class="btn_dr" onclick="impAndChkData()"
										id="btn_dr">导入</a>
								</li>
								<li>
									<a href="#" class="btn_dc" onclick="expRs()" id="btn_dc">导出</a>
								</li>
							</ul>
						</div>
					</logic:equal>


					<div class="searchtab">
						<table width="100%" border="0" id="searchTab">
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											<button class="btn_cx" id="search_go" onclick="searchRs();return false;">
												查 询
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<th>
										年级
									</th>
									<td>
										<html:select property="nj" style="" onchange="initZyList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<html:hidden property="xydm" />
										<html:select property="xydm" style="" styleId="xy" onchange=""
											disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										评奖学年
									</th>
									<td>
										<html:hidden property="xn" />
										<html:select property="xn" style="" onchange=""
											disabled="true">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										类别
									</th>
									<td>
										<html:select property="lx" style="" styleId="lx"
											disabled="true">
											<html:options collection="lxList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<th>
										名称
									</th>
									<td>
										<html:select property="jxjdm" style="" styleId="jxjdm">
											<html:options collection="jxjList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th></th>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
								<font color="blue">记录数： <bean:write name="rsNum" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 注：${nj }级 ${xymc } ${jxjmc
									} 评奖剩余分配人数为 <input type="hidden" name="xyrs" id="xyrs"
										value="${xyrs}" /> <input type="text" name="syrs" id="syrs"
										value="" readonly="readonly" style="width:7%" /> 人 </font>
							</logic:notEmpty> </span>
					</h3>
					<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table class="dateline tablenowrap" width="100%">
							<thead>
								<tr>
									<logic:iterate id="tit" name="topTr" offset="0" length="7">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="5">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td align="left">
											<!-- 设置人数 -->
											<input type="text" name="szrs"
												value="<logic:iterate id="v" name="s" offset="6" length="1"><bean:write name="v" /></logic:iterate>"
												onkeypress="return onlyNum(this,5)" maxlength="5"
												style="width:50%;ime-mode:disabled" onblur="setSyPjrs()" />
											人
											<input type="text" name="szbm" style="display : none"
												value="<logic:iterate id="v" name="s" offset="7" length="1"><bean:write name="v" /></logic:iterate>" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					</logic:notEmpty>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				$("doType").value = "";
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
				$("doType").value = "";
			</script>
		</logic:equal>
		<logic:present name="message">
			<script>
				alert('${message}');
			</script>
		</logic:present>
	</body>
</html>
