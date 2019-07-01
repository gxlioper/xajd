<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript">
		
		//整体撤销宿舍分配
		function ztcx_ss(){
		
			var nj = $("nj").value;
			var xydm = $("xy").value;
			var xqdm = $("xqdm").value;
			var lddm = $("lddm").value;
			var cs = $("cs").value;
			var qsh = $("qsh").value;
			
			var msg = "";
			
			//所有条件都没选
			if(nj == "" && xydm == "" && xqdm == "" && lddm == "" && cs == "" && qsh == "")	{
				msg = "未选择任何条件，撤销全部宿舍分配\n";
				msg+="确认请按'确定'按钮，否则请取消";
			}else{
				msg = "根据您所选择的条件，现在撤销符合如下条件的宿舍：\n";
				
				//年级
				if(nj != ""){
					msg+="年级："+ nj + "\n";
				}
				
				//学院
				if(xydm != ""){
				
					var xy = $("xy");
					var xymc = "";
					
					for(var i=0;i<xy.options.length;i++){
						if(xy.options[i].selected){
							xymc=xy.options[i].text;
						}
					}
					msg+="<bean:message key="lable.xb" />："+ xymc + "\n";
				}
				
				//校区
				if(xqdm != ""){
				
					var xq = $("xqdm");
					var xqmc = "";
					
					for(var i=0;i<xq.options.length;i++){
						if(xq.options[i].selected){
							xqmc=xq.options[i].text;
						}
					}
					msg+="校区："+ xqmc + "\n";
				}
				
				//楼栋
				if(lddm != ""){
				
					var ld = $("lddm");
					var ldmc = "";
					
					for(var i=0;i<ld.options.length;i++){
						if(ld.options[i].selected){
							ldmc=ld.options[i].text;
						}
					}
					msg+="楼栋："+ ldmc + "\n";
				}
				
				//层数
				if(cs != ""){
					msg+="层数："+ cs + "\n";
				}
				
				//寝室
				if(qsh != ""){
					msg+="寝室："+ qsh + "\n";
				}
				
				msg+="确认请按'确定'按钮，否则请取消";
				
			}
			
			if(confirm(msg)){
				var url = "/xgxt/commGygl.do?method=cxfpSsManage&doType=plcx"
		    	saveUpdate(url,'');
		   	}
		}
		</script>
	</head>

	<body onload="">
		<html:form action="/commGygl">
			<!-- 标题 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<!-- 标题 end-->
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->		
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/commGygl.do?method=cxfpSsManage','del')"
									class="btn_fh">撤销分配</a>
							</li>
							<li>
								<a href="#"
									onclick="ztcx_ss()"
									class="btn_sy">整体撤销</a>
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
										<button style="" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commGygl.do?method=cxfpSsManage');">
											查询
										</button>
										<button style="" id="cz"
											onclick="czSearchCond('nj-xy-xqdm-lddm-cs-qsh');return false;">
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
									<html:select property="queryequals_nj" style="" styleId="nj" onchange="">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>												
								</td>
								<th>
									院系
								</th>
								<td>
									<html:select property="queryequals_xydm" style="" styleId="xy" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									校区
								</th>
								<td>
									<html:select property="queryequals_xqdm" style="" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									楼栋										
								</th>
								<td>
									<html:select property="queryequals_lddm" style="" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>											
								</td>
								<th>
									所属层数
								</th>
								<td>
									<html:select property="queryequals_cs" style="" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="queryequals_qsh" style="" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td width="5%">
										<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" 
									style="cursor:hand" 
									ondblclick="">
									<!-- checkbox -->								
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center">
											<input type="checkbox" id="checkVal"  name="primarykey_checkVal"  value="${v }"/>	
										</td>
									</logic:iterate>
									<!-- 项目信息 -->		
									<logic:iterate id="v" name="s" offset="1" >
										<td align="left">
											${v }
										</td>
									</logic:iterate>
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
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		<!-- 提示信息 end-->
	</body>
</html>