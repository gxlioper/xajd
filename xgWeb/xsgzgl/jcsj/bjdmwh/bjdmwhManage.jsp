<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.jcsj.bjdmwh.BjdmwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//班级代码div操作 增加、修改
		var doType;
		function bjdmDiv(type,topMsg){
			$("bjdm").readOnly="";
			$("bjdm").value="";
			$("bjmc").value="";
			$("ssbmdm").value="";
			$("sszydm").value="";
			$("nj").value="";
			doType=type;
			if(type!="add"){
				if(curr_row != null){
					bjdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				$("bjdm").value=curr_row.cells[1].innerHTML;
				$("bjmc").value=curr_row.cells[2].innerHTML;
				$("nj").value=curr_row.cells[5].innerHTML;
				$("select_ssbmdm").value=curr_row.getElementsByTagName('input')[2].value;
				changeZySelect(curr_row.getElementsByTagName('input')[1].value,"select_ssbmdm","select_sszydm");
				$("bjdm").readOnly="true";
			}
			tipsWindown(topMsg,"id:tempDiv","350","250","true","","true","id");
		}
		function bjdmDivSave(){
			if(doType!="update"){
				if($("bjdm").value==""){
					alertInfo("请输入班级代码！");
					return false;
				}
			}
			if($("bjmc").value.trim()==""){
				alertInfo("请输入班级名称！");
				return false;
			}
			if($("select_sszydm").value.trim()==""){
				alertInfo("请选择所属专业！");
				return false;
			}
			allNotEmpThenGo('jcsj_bjdmwh_bjdmwh.do?doType='+doType);
		}
			
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('jcsj_bjdmwh_bjdmwh.do');
			}
			

			//删除
			function delBjdm(doType){
				var bjdm;
				if(curr_row != null){
					bjdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				confirmInfo("确定删除吗？",function(data){
					if("ok"==data){
						allNotEmpThenGo('jcsj_bjdmwh_bjdmwh.do?doType=del&bjdm='+bjdm);
					}
				});
			}

			//改变专业下拉框
			function changeZySelect(zydm,bmdm_id,zydm_id){
				
				jQuery.getJSON('jcsj_bjdmwh.do?method=getZydmList',{bmdm:jQuery('#'+bmdm_id).val()},function(data){
					jQuery('#'+zydm_id).empty();
					jQuery('#'+zydm_id).append("<option value=''>--请选择--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
							jQuery('#'+zydm_id).append(option);
						}
						if(zydm&&zydm!=""){
							jQuery('#'+zydm_id).val(zydm);
						}
					}
				});
			}

			//检测异常数据
			function checkExcData(){
				allNotEmpThenGo('jcsj_bjdmwh_bjdmwh.do?doType=checkExcData');
			}
			function newChgCode(obj){
				allNotEmpThenGo(obj.id+".do");
			}
		</script>
	</head>
	<body onload="">
		<html:form action="/jcsj_bjdmwh" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
<%--						<logic:equal value="yes" name="writeAble">--%>
						<li><a href="#" onclick="bjdmDiv('add','增加班级');return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="bjdmDiv('update','修改班级');return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="delBjdm();return false;" class="btn_sc">删除</a></li>
						<li><a href="#" onclick="checkExcData();return false;" class="btn_cs">异常检测</a></li>
<%--						</logic:equal>--%>
					</ul>
				</div>
				<!-- 过滤条件 start-->				
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>年级</th>
								<td>
									<html:select property="query_nj" styleId="query_nj" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
								<th>所属部门</th>
								<td>
									<html:select property="query_ssbmdm" styleId="query_ssbmdm" style="width:150px;" onchange="changeZySelect('','query_ssbmdm','query_sszydm')">
										<html:option value=""></html:option>
										<html:options collection="bmdmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</td>
								<th>所属专业</th>
								<td>
									<html:select property="query_sszydm" styleId="query_sszydm" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="zydmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>是否异常数据</th>
								<td>
									<html:select property="query_sfycsj" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								<th>班级名称(代码)</th>
								<td>
									<html:text property="query_text"></html:text>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('jcsj_bjdmwh_bjdmwh.do');">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>					
				</div>
				<!-- 过滤条件 end-->
			</div>
<%--				<logic:equal name="superSearch" value="yes">--%>
<%--			     	<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
<%--			    </logic:equal>--%>
			    
			    <div class="compTab" id="card">
				<div class="comp_title"><ul>
									<li><a href="#" onclick="newChgCode(this)" id="jcsj_bmdmwh_bmdmwh"><span>部门代码</span> </a>
									<li><a href="#" onclick="newChgCode(this)" id="jcsj_zydmwh_zydmwh"><span>专业代码</span> </a>
									<li class="ha"><a href="#" onclick="newChgCode(this)" id="jcsj_bjdmwh_bjdmwh"><span>班级代码</span></a>
									<li><a href="#" onclick="newChgCode(this)" id="jcsj_xsxxwh_xsxxwh"><span>学生信息</span> </a>
				</div>
			    
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td style="display: none;">
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort_hc(this,1)">
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
								<td style="display: none;">
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
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none;">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>" />
										
									</td>
									<logic:iterate id="v" name="s" offset="3" length="8">
										<td><bean:write name="v" format="ture"/></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							BjdmwhForm form = (BjdmwhForm)request.getAttribute("jcsjBjdmwhForm");
							int pageSize = form.getPages().getPageSize();
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td style="display: none;">
									<input type="checkbox" value="" disabled="disabled"></input>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jcsjBjdmwhForm"></jsp:include>
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
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_select_xn">
								<th>
									<span class="red">*</span>班级代码
								</th>
								<td>
									<input type="text" id="bjdm" name="bjdm" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>班级名称
								</th>
								<td>
									<input type="text" id="bjmc" name="bjmc" maxlength="100"/>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>所属部门
								</th>
								<td>
									<html:select property="ssbmdm" styleId="select_ssbmdm" style="width:150px;" onchange="changeZySelect('','select_ssbmdm','select_sszydm')">
										<html:option value=""></html:option>
										<html:options collection="bmdmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>所属专业
								</th>
								<td>
									<html:select property="sszydm" styleId="select_sszydm" style="width:150px;">
										<html:option value=""></html:option>
									</html:select>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>年级
								</th>
								<td>
									<html:select property="nj" styleId="select_nj" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="bjdmDivSave();return false;">
											保 存
										</button>
										<button type="button" name="取消" onclick="closeWindown();return false;">
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
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
