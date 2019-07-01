<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_gyglry.do?method=gyglryFp');
		}
		//管理人员分配
		function glryfp(){
			if(curr_row != null){
					var param=curr_row.getElementsByTagName('input')[0].value; 
					tipsWindownNew("保存提示","id:tempDiv",400,280,"xh:"+param);
					return false;
			}else{
				alertInfo('请选择要操作的数据行');
				return false;
			}
		}
		function glryfp_submit(){
			$("xh").value=curr_row.getElementsByTagName('input')[0].value;
			allNotEmpThenGo('gyglnew_gyglry.do?method=gyglryFp&doType=fp');
		}

		function getQsh(){
			jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm',{lddm:jQuery('#lddm').val(), ch:jQuery('#ch').val()},function(data){
				jQuery('#qsh').empty();
				jQuery('#qsh').append("<option value=''>--请选择--</option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].qsh + "\">" + data[i].qsh + "</option>";
						jQuery('#qsh').append(option);
					}
				}
			});
		}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_gyglry" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="pkValue"/>
			<html:hidden property="lddm" styleId="lddm"/>
			<html:hidden property="fp_ch"/>
			<html:hidden property="fp_qsh"/>
			<html:hidden property="old_xh"/>
			<input type="hidden" name="xh" id="xh" value="" />
			<!-- 隐藏域 -->
			
			<!-- 提示信息 end-->
			<!-- div class="prompt" id="promptTs">
				<h3>
					<span>系统提示：</span>
				</h3>
				<p>
					“入住”时系统会根据学生所属自动更新床位所属；“对调”仅可操作2个相同性别且至少其中1个已入住的床位；
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div-->
			<!-- 提示信息 end-->	
			
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="glryfp();return false;" class="btn_sh"> 分配 </a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('gyglnew_gyglry.do?method=gyglryFp&query=y')">
											查询
										</button>
										<button type="button" class="btn_cz" type="reset">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>层号</th>
								<td>
								<logic:notEmpty name="rsp" property="fp_qsh">
								<html:select property="ch" styleId="ch" style="width:120px;"  disabled="true">
									<html:option value=""></html:option>
									<html:optionsCollection name="chList" label="chmc" value="ch"/>
								</html:select>
								</logic:notEmpty>
								<logic:empty name="rsp" property="fp_qsh">
								<html:select property="ch" styleId="ch" style="width:120px;" onchange="getQsh()">
									<html:option value=""></html:option>
									<html:optionsCollection name="chList" label="chmc" value="ch"/>
								</html:select>
								</logic:empty>
								 </td>
								<th>寝室号</th>
								<td>
								<logic:notEqual value="" name="rsp" property="fp_qsh">
								<html:select property="qsh" styleId="qsh" style="width:120px;"  disabled="true">
									<html:option value=""></html:option>
									<html:optionsCollection name="qshList" label="qsh" value="qsh"/>
								</html:select> 
								</logic:notEqual>
								<logic:equal value="" name="rsp" property="fp_qsh">
								<html:select property="qsh" styleId="qsh" style="width:120px;">
									<html:option value=""></html:option>
									<html:optionsCollection name="qshList" label="qsh" value="qsh"/>
								</html:select> 
								</logic:equal>
								</td>
								<th>学号（或姓名）</th>
								<td>
									<html:text property="xm" styleId="xm"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td style="display: none" >
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="pk" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="">
									<td style="display: none" >								
										<input type="checkbox" name="primarykey_checkVal"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)(request.getAttribute("pageSize"));
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewGyglryForm"></jsp:include>
		   	 	<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>管理人员分配</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:equal value="false" name="gyglryfpczfs">
							<tr>
								<th>
									操作方式
								</th>
								<td>
									<input type="radio" name="czfs" value="gx" checked="checked"/>更新
									<input type="radio" name="czfs" value="zj"/>追加
								</td>
							</tr>
							</logic:equal>
							<tr>
								<th>
									<font color="red">*</font>任职时间
								</th>
								<td>
									<html:text property="rzksrq" styleId="rzksrq" onkeypress="onlyBackSpace(this,event);" 
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"></html:text>
								</td>
							</tr>
							<%--
							<tr>
								<th>
									手机
								</th>
								<td>
									<html:text property="lxdh" styleId="lxdh" maxlength="15" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
								</td>
							</tr>
							<tr>
							--%>
								<th>
									备注
								</th>
								<td>
									<html:text property="bz" styleId="bz"  maxlength="100"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="保存"  onclick="szdwjr();">
											保存 
										</button>
										<button type="button" name="取消"  onclick="iFClose();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
		</html:form>
		<logic:present name="message">
			<script>
				
				 showAlert('${message}',{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
			</script>
		</logic:present>
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
