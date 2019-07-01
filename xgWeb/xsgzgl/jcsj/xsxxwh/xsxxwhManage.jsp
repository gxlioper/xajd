<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.jcsj.xsxxwh.XsxxwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//部门代码div操作 增加、修改
		var doType;
		function ljsdaUpdate(url,w,h){	
			var pk="";	
			if(curr_row == null ){
					alert("请选择一行记录！\n单击一行即可!");
					return false;
			} 	
					
			pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
			url+=pk;
			//判断是否在校库
			//url+="&sfzxk="+$("sfzxk").value;
			if(w==""||w==null){
				w = 800;
			}
			if(h==""||h==null){
				h = 600;
			}
			showTopWin(url,w,h);		
		}
			
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('jcsj_xsxxwh_xsxxwh.do');
			}
			

			//删除
			function delXsxx(doType){
				var xsxx;
				if(curr_row != null){
					xh=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				confirmInfo("确定删除吗？",function(data){
					if("ok"==data){
						allNotEmpThenGo('jcsj_xsxxwh_xsxxwh.do?doType=del&xh='+xh);
					}
				});
			}

			//检测异常数据
			function checkExcData(){
				allNotEmpThenGo('jcsj_xsxxwh_xsxxwh.do?doType=checkExcData');
			}

			function newChgCode(obj){
				allNotEmpThenGo(obj.id+".do");
			}

			//改变专业下拉框
			function changeZySelect(bmdm_id,zydm_id){
				jQuery.getJSON('jcsj_xsxxwh.do?method=getZydmList',{bmdm:jQuery('#'+bmdm_id).val()},function(data){
					jQuery('#'+zydm_id).empty();
					jQuery('#'+zydm_id).append("<option value=''>--请选择--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
							jQuery('#'+zydm_id).append(option);
						}
					}
					jQuery('#bjdm').empty();
				});
			}

			//改变班级下拉框
			function changeBjSelect(zydm_id,bjdm_id){
				
				jQuery.getJSON('jcsj_xsxxwh.do?method=getBjdmList',{zydm:jQuery('#'+zydm_id).val()},function(data){
					jQuery('#'+bjdm_id).empty();
					jQuery('#'+bjdm_id).append("<option value=''>--请选择--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
							jQuery('#'+bjdm_id).append(option);
						}
					}
				});
			}
		</script>
	</head>
	<body onload="">
		<html:form action="/jcsj_xsxxwh" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="refurbish" id="refurbish" value=""/>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 提示信息 end-->
			<!-- 提示信息 end-->	
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
<%--						<logic:equal value="yes" name="writeAble">--%>
						<li><a href="#" onclick="$('refurbish').value='yes';showTopWin('stu_info_add.do?method=showStuInfo&oper=add',800,600,false);return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="$('refurbish').value='yes';ljsdaUpdate('stu_info_add.do?method=showStuInfo&oper=update&xh=',800,600);return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="delXsxx();return false;" class="btn_sc">删除</a></li>
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
									<html:select property="nj" styleId="nj" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
								<th>部门</th>
								<td>
									<html:select property="bmdm" styleId="bmdm" style="width:150px;" onchange="changeZySelect('bmdm','zydm')">
										<html:option value=""></html:option>
										<html:options collection="bmdmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</td>
								<th>专业</th>
								<td>
									<html:select property="zydm" styleId="zydm" style="width:150px;" onchange="changeBjSelect('zydm','bjdm')">
										<html:option value=""></html:option>
										<html:options collection="zydmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>班级</th>
								<td>
									<html:select property="bjdm" styleId="bjdm" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="bjdmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>是否异常数据</th>
								<td>
									<html:select property="query_sfycsj" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								<th>学号(姓名或身份证号)</th>
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
											onclick="allNotEmpThenGo('jcsj_xsxxwh_xsxxwh.do');return false;">
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
								<li><a href="#" onclick="newChgCode(this)" id="jcsj_bjdmwh_bjdmwh"><span>班级代码</span> </a>
								<li class="ha"><a href="#" onclick="newChgCode(this)" id="jcsj_xsxxwh_xsxxwh"><span>学生信息</span> </a>
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
										
									</td>
									<logic:iterate id="v" name="s" offset="1" length="9">
										<td><bean:write name="v" format="ture"/></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							XsxxwhForm form = (XsxxwhForm)request.getAttribute("jcsjXsxxwhForm");
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jcsjXsxxwhForm"></jsp:include>
		   	 	<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--分页显示-->
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
