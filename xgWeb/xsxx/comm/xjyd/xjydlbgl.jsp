<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function saveSqrfw(){
				var sqrfw = "";
				var sqr = jQuery('input[type=checkbox]:checked[name=sqr]');
				
				if (sqr.length==5){
					sqrfw = "全体";
				} else {
					for(var i = 0 ; i < sqr.length ; i++){
						sqrfw+=sqr[i].value;
						
						if (i != sqr.length-1){
							sqrfw+=',';
						}
					}
				}
				refreshForm('xsxxXjyd.do?method=plszSqrfw&save_sqrfw='+sqrfw);
			}
			
			//检测可否删除
			function checkDel(){
				jQuery.ajaxSetup({async:false});
				var num = jQuery('input[name=primarykey_cbv]').length;
				var pk = new Array();
				var n = 0;
				for(var i=0;i<num;i++){
					var obj = jQuery('input[name=primarykey_cbv]')[i];
					if(obj.checked){
						pk[n]=escape(obj.value);
						//alert(pk[n]);
						n++;
					}
				}
				
				//其他数据
			 	var parameter = {
					"pk":pk.join("!!@@!!")
				};
				
				var url="xsxxXjyd.do?method=checkDel";
				
				//showWaitingDiv("10000");
				jQuery.post(url,parameter,function(result){
					if(result != ""){
						alertInfo(result);
					}else{
						//alert("未使用");
						batchData('primarykey_cbv','xsxxXjyd.do?method=ydlbDel','del');
					}
				});
				jQuery.ajaxSetup({async:true});
			}

			//检测可否可以修改
			function checkUpdate(){
				jQuery.ajaxSetup({async:false});
				var num = jQuery('input[name=primarykey_cbv]').length;
				var pk = new Array();
				var n = 0;
				for(var i=0;i<num;i++){
					var obj = jQuery('input[name=primarykey_cbv]')[i];
					if(obj.checked){
						pk[n]=escape(obj.value);
						//alert(pk[n]);
						n++;
					}
				}
				
				//其他数据
			 	var parameter = {
					"pk":pk.join("!!@@!!")
				};
				
				var url="xsxxXjyd.do?method=checkUpdate";
				
				jQuery.post(url,parameter,function(result){
					if(result != ""){
						alertInfo(result);
					}else{
						//alert("未使用");
						showUpdateWindow('primarykey_cbv','xsxxXjyd.do?method=ydlbView','550','350');
					}
				});
				jQuery.ajaxSetup({async:true});
			}
			//检测可否可以设置申请人范围
			function checkSqrfw(){
				jQuery.ajaxSetup({async:false});
				var num = jQuery('input[name=primarykey_cbv]').length;
				var pk = new Array();
				var n = 0;
				for(var i=0;i<num;i++){
					var obj = jQuery('input[name=primarykey_cbv]')[i];
					if(obj.checked){
						pk[n]=escape(obj.value);
						//alert(pk[n]);
						n++;
					}
				}
				
				//其他数据
			 	var parameter = {
					"pk":pk.join("!!@@!!")
				};
				
				var url="xsxxXjyd.do?method=checkSqrfw";
				
				jQuery.post(url,parameter,function(result){
					if(result != ""){
						alertInfo(result);
					}else{
						//alert("未使用");
						if(isChecked("primarykey_cbv")){tipsWindown("系统提示","id:plszDiv","350","150","true","","true","id")};
					}
				});
				jQuery.ajaxSetup({async:true});
			}
			
		</script>
		
	</head>
	<body>

		<html:form action="/xsxxXjyd" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" id="isFdy"     name="isFdy"  	 value="${isFdy }" />
			<input type="hidden" id="userName"  name="userName"  value="${userName }" />
			<input type="hidden" id="userType"  name="userType"  value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			
			<!-- 大师的权限用的 -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview" value="${ purview }" /> 
				<input type="hidden" id=" operateBound " name="operateBound" value="${ operateBound }" />
			</logic:present>
			

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									id = "btn_zj"
									onclick="showTopWin('xsxxXjyd.do?method=ydlbAdd','550','350')"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#"
									id = "btn_xg"
									onclick="checkUpdate();return false;"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#"
									id="btn_sc"
									onclick="checkDel();return false;"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#"
									id="btn_sz"
									onclick='checkSqrfw();return false;'
									class="btn_sz">设置申请人范围</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						
						<tbody>
							<tr>
								<th>
									异动类别
								</th>
								<td>
									<html:hidden property="save_shlcid" styleId="shlcid"/>
									<html:select property="ydlbm" styleId="ydlbm" style="width:180px" >
										<html:option value=""></html:option>
										<html:options collection="ydlbAllList" property="ydlbm" labelProperty="ydlbmc"/>
									</html:select>
								</td>
								<th>
									学籍状态
								</th>
								<td>
									<html:select property="xjzt">
										<html:option value=""></html:option>
										<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
									</html:select>
								</td>
								<th>
									是否在校
								</th>
								<td>
									<html:select property="sfzx">
										<html:option value=""></html:option>
										<html:options collection="sfzxList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
								<td>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xsxxXjyd.do?method=xjydlbgl&doType=query')">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> 
					</span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
										</td>
										<logic:iterate id="v" name="s" offset="0">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxXjydForm"></jsp:include>
				<!--分页显示-->
			</div>
			
			<!-- 批量设置申请人范围 -->
			<div id="plszDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请选择申请人范围</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									申请人范围
								</th>
								<td>
									<input type="checkbox" value="学生" checked="true" name="sqr"/>学生
									<input type="checkbox" value="辅导员" checked="true" name="sqr"/>辅导员
									<br/>
									<input type="checkbox" value="班主任" checked="true" name="sqr"/>班主任
									<input type="checkbox" value="<bean:message key="lable.xb" />" checked="true" name="sqr"/><bean:message key="lable.xb" />
									<input type="checkbox" value="学工处" checked="true" name="sqr"/>学工处
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
										<button type="button" name="保存" onclick="saveSqrfw()">
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
			<script language="javascript">
         		alert("${message}");
         	</script>
		</logic:present>
	</body>
</html>
