<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		//选择页签
		function changePage(obj){
			var url="/xgxt/gyglGywh.do?method=jcsjwhManage";
			var mklx=obj.id;
			url+="&mklx="+mklx;
			refreshForm(url);
		}
		//加载页签信息
		function loadPages(){
			if($("mklx")){
				var mklx=$("mklx").value;
				if(mklx!=""){
					if($("czxq").value=="是"){
						$("xq_li").className="";
					}else if(mklx=="xq"){
						mklx="yq";
					}
					if($("czyq").value=="是"){
						$("yq_li").className="";
					}else if(mklx=="yq"){
						mklx="ld";
					}
<%--					$("ld_li").className="";--%>
<%--					$("qs_li").className="";--%>
<%--					$("cw_li").className="";--%>
					
					$(mklx+"_li").className="ha";
					$("mklx").value=mklx;
				}	
			}
		}
		//增加校区
		function addXqxx(){
			var url="/xgxt/gyglGywh.do?method=xqxxwh&doType=add";
			showTopWin(url,400,200);
		}
		//修改校区信息
		function modiXqxx(){
			if (null == curr_row) {
				alert('请选择一行');
			} else {
				var xqdm = curr_row.getElementsByTagName('input')[0].value;
				var url = '/xgxt/gyglGywh.do?method=xqxxwh&doType=update&xqdm='+xqdm;
				showTopWin(url,400,200);
			}
		}
		
		//增加园区
		function addYqxx(){
			var url="/xgxt/gyglGywh.do?method=yqxxwh&doType=add";
			showTopWin(url,400,200);
		}
		
		//修改园区
		function modiYqxx(){
			if (null == curr_row) {
				alert('请选择一行');
			} else {
				var yqdm = curr_row.getElementsByTagName('input')[0].value;
				var url = '/xgxt/gyglGywh.do?method=yqxxwh&doType=update&yqdm='+yqdm;
				showTopWin(url,400,200);
			}
		}
		
		//删除基础数据
		function delJcsj(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				var mklx=$("mklx").value;
				var url="/xgxt/gyglGywh.do?method=jcsjwhManage";
				url+="&doType=del&mklx="+mklx;
				if(confirm("确定要删除选中的记录吗?")){
					refreshForm(url);
					hiddenMessage(true,true);
					BatAlert.showTips('正在操作，请稍等...');
				}else {
					return false;
				}
			}else{
				alert("请勾选希望删除的记录!");
				return false;
			}
		
			
		}
		
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglGywh.do?method=jcsjwhManage&mklx=${mklx }');
		}
		
		function setPath(){
			$("path").value="gygl_gywh_jcsj.do&searchType=${mklx}";
		}
		
		function loadTitle(){
			$("input_mhcx_msg").style.left='130px';
			$("input_mhcx_msg").style.top='117px';
		}
		
		//下一步操作
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next==""){
				next=$("next_1").value;
			}
			
			if(next == "gb"){//关闭
				hiddenMessage(true,true);
			}else {
				next+="&doType=scjc";
				refreshForm(next);
			}
		}
		
<%--		function createSearch(){--%>
<%--			var node = $("searchTjDiv");--%>
<%--			var delArr=document.getElementsByName("delArr");--%>
<%--			alert(delArr.length);--%>
<%--			--%>
<%--			for(i=0;i<delArr.length;i++){--%>
<%--				if(delArr[i].value!=""){--%>
<%--					var tmp = document.createElement("input");--%>
<%--					tmp.type = "text";--%>
<%--					alert(delArr[i].id);--%>
<%--					tmp.name = "searchModel.search_tj_"+delArr[i].id;--%>
<%--					tmp.value = delArr[i].value;--%>
<%--					node.appendChild(tmp);--%>
<%--				}--%>
<%--			}--%>
<%--		}--%>
		</script>
	</head>
	<body onload="loadPages();loadTitle()">

		<html:form action="/gyglGywh" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			
			<!-- 模块类型 -->
			<input type="hidden" id="mklx" name="mklx" value="${mklx}"/>
			<input type="hidden" id="czxq" name="czxq" value="${czxq}"/><!-- 存在校区 -->
			<input type="hidden" id="czyq" name="czyq" value="${czyq}"/><!-- 存在园区 -->
			<input type="hidden" id="xqdm" name="xqdm" value="${xqdm}"/><!-- 校区代码 -->
			<input type="hidden" id="yqdm" name="yqdm" value="${yqdm}"/><!-- 园区代码 -->
			<input type="hidden" id="lddm" name="lddm" value="${lddm}"/><!-- 楼栋代码 -->
			<input type="hidden" id="cs" name="cs" value="${cs}"/><!-- 层数 -->
			<input type="hidden" id="qsh" name="qsh" value="${qsh}"/><!-- 寝室号 -->
			<input type="hidden" id="dm" name="dm" value="${dm}"/><!-- 存在园区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="mklx" value="xq">
							<li><a href="#" onclick="addXqxx();return false;" class="btn_zj">增加</a></li>
							<li><a href="#" onclick="modiXqxx();return false;" class="btn_xg">修改 </a></li>
						</logic:equal>
						<logic:equal name="mklx" value="yq">
							<li><a href="#" onclick="addYqxx();return false;" class="btn_zj">增加</a></li>
							<li><a href="#" onclick="modiYqxx();return false;" class="btn_xg">修改 </a></li>
						</logic:equal>
						<li><a href="#" onclick="delJcsj();return false;" class="btn_sc">删除</a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入数据</a></li>		
						<li><a href="#" class="btn_dc" onclick="setPath();setSearchTj();configureExportData();return false;">导出数据</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出字段确认</a></li>
					</ul>
				</div>
				<div class="compTab" id="card" style="position:relative">
					<div class="comp_title" id="div1">
						<ul id="ul1">		
							<logic:iterate name="pageCard" id="pcard">				
							<li id="${pcard.dm}_li" class="ha">
								<a href="#" onclick="changePage(this);return false;" id="${pcard.dm}">
									<logic:equal name="pcard" property="mcxs" value="lable">	
										<span><bean:message key="${pcard.mc}" />信息</span>
									</logic:equal>
									<logic:notEqual name="pcard" property="mcxs" value="lable">	
										<span>${pcard.mc}</span>
									</logic:notEqual>
								</a>
							</li>
							</logic:iterate>
						</ul>	
					</div>
				</div>
							
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
						<font color="blue"></font>	
						</logic:notEmpty>
						<font color="blue">
						注：非本功能模块录入的数据，请前往具体模块(如楼栋信息维护，寝室信息维护等)进行删除操作
						</font>
					</span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1"  indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<input type="checkbox" name="checkVal" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>
											/>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglGywhForm"></jsp:include>
				</logic:notEmpty>
			</div>
		</html:form>
		<%@ include file="/comm/delMessage.jsp"%>
		<logic:equal name="result" value="true">
		<script>
			alert("操作成功!");	
		</script>
		</logic:equal>
	</body>
</html>
