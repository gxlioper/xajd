<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		
		function addQssj(){
			var url="/xgxt/gyglGywh.do?method=qsxxwh&doType=add";
			showTopWin(url,800,600);
		}
	
		
		//修改
		function modiQssj(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			
			if(n>1){
				viewTempDiv("寝室信息设置","plUpdate",410,245);
			}else if(n==1 || curr_row){
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				var url = '/xgxt/gyglGywh.do?method=qsxxwh&doType=update&pkValue='+pkValue;
				showTopWin(url,800,600);
				
			}else if (null == curr_row) {
					alert('请选择一行');
					return false;
			}
		}
		
		function updateQsxx(){
			
			var url = '/xgxt/gyglGywh.do?method=qswhManage&doType=plsz';
			var pkValue=document.getElementsByName("checkVal");
			var rzqkArr=document.getElementsByName("rzqkArr");
			var xbxdArr=document.getElementsByName("xbxdArr");
			
			var xbxdHidArr=new Array();
			var rzqkHidArr=new Array();
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					rzqkHidArr[n]=rzqkArr[i];
					xbxdHidArr[n]=xbxdArr[i];
					n++;
				}
			}
			
			for(i=0;i<rzqkHidArr.length;i++){
			
				var rzqk=document.createElement("input");
				rzqk.type="hidden";
				rzqk.name="rzqkHidArr";
				rzqk.value=rzqkHidArr[i].value;
				
				var xbxd=document.createElement("input");
				xbxd.type="hidden";
				xbxd.name="xbxdHidArr";
				xbxd.value=xbxdHidArr[i].value;
				
				document.forms[0].appendChild(rzqk);
				document.forms[0].appendChild(xbxd);
			}
			
			if($("xbHid").value!="不修改" || $("kfhzHid").value!="不修改" || $("sfxgHid").value!="不修改" || $("fpbjHid").value!="不修改"){
				refreshForm(url);
			}else{
				hiddenMessage(true,true);
			}
		}
		
		function showQsxx(pkValue){
			var url = '/xgxt/gyglGywh.do?method=qsxxwh&doType=view&pkValue='+pkValue;
			showTopWin(url,800,600);
		}
		
		function delQssj(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				if(confirm("确定要删除选中的记录吗?")){
					var mklx=$("mklx").value;
					var url="/xgxt/gyglGywh.do?method=qswhManage";
					url+="&doType=del&mklx="+mklx;
					refreshForm(url);
					hiddenMessage(true,true);
					BatAlert.showTips('正在操作，请稍等...');
				}else {
					return false;
				}
			}else{
				alert("没有需要保存的记录!");
				return false;
			}
		
			
		}
		
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglGywh.do?method=qswhManage');
		}
		
		//下一步操作
		function nextCz(){
			var next = $("next_cz").value;
		
			if(next == "gb"){//关闭
				hiddenMessage(true,true);
			}else {
				refreshForm(next);
			}
		}
		
		//显示加载页面
		function showLoadPage(){
			$("div_help").style.display='none';
			//多功能操作
			$("dgncz").style.display="none";
			//查询结果
			$("cxjg").style.display="none";
			//显示
			$("page_loading").style.display="";
			//设置提示信息
			$("prompt").innerHTML="正在进行床位生成操作,请稍候!";
		}
		
		function createCw(){
			if(confirm("床位自动生成功能用于在导入寝室信息后，在床位信息表\n中为寝室自动生成床位。使用本系统正常流程增加寝室无\n需进行本操作。是否进行床位自动生成?")){
				showLoadPage();
				refreshForm('/xgxt/gygl_gywh_qswh.do?doType=cwsc');
			}
		}
		
		function changeDisabled(blog){
			$("sfbz_id").disabled=blog;
		}
		
		function changeValue(obj,bool){
			$('sfxgHid').value=obj.value;
			changeDisabled(bool);
		}
		</script>
	</head>
	<body>

		<html:form action="/gyglGywh" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					本功能模块，用于完成对寝室的增加、修改和查看功能。点击"增加"按钮可以增加一个新的寝室。选中一条寝室记录后点<br/>	
					击修改"按钮"可以对已有的寝室信息进行修改。勾选多条记录后，点击修改按钮可进行批量修改操作。批量修改操作只针<br/>
					对未住人寝室进行修改，在进行寝室性别修改时只能修改楼栋性别限制为“混合”的寝室。勾选一个或多个寝室后，点击<br/>
					"删除"按钮可以删除一条或多条寝室记录。如果需要大批量的增加寝室，可以点击"寝室自动生成"按钮生成寝室。点击"查<br/>	
					看"可以查看寝室详细信息。“床位生成”功能用于在导入寝室信息后，对寝室进行自动床位的生成。
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="path" value="gygl_gywh_qswh.do"/>
			<input type="hidden" id="mklx" name="mklx" value="${mklx}"/>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
<%--						<li><a href="#" onclick="delJcsj();return false;" class="btn_ccg"> 保存 </a></li>--%>
<%--						<li><a href="#" onclick="delJcsj();return false;" class="btn_sz"> 设置 </a></li>--%>
						<li><a href="#" onclick="addQssj();return false;" class="btn_zj"> 增加 </a></li>
						<li><a href="#" onclick="modiQssj();return false;" class="btn_xg"> 修改 </a></li>
						<li><a href="#" onclick="delQssj();return false;" class="btn_sc"> 删除 </a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入数据</a></li>		
						<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">导出数据</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出字段确认</a></li>
						<li><a href="#" class="btn_csh" onclick="refreshForm('/xgxt/gygl_gywh_zdsc.do?tzPath=qswh');return false;">寝室自动生成</a></li>
						<li><a href="#" class="btn_cs" onclick="createCw();return false;">床位生成</a></li>
					</ul>
				</div>
				
				
				
<%--			<!-- new 版本 -->--%>
			     <logic:equal name="superSearch" value="yes">
			     	<%@ include file="/comm/search/superSearchArea.jsp"%>
			     </logic:equal>

			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
						<font color="blue"></font>	
						</logic:notEmpty>
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
								<logic:iterate id="tit" name="topTr"   indexId="index" offset="1" length="11">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td>查看寝室信息</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<input type="checkbox" name="checkVal" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										/>
										<input type="hidden" name="rzqkArr" value="<logic:iterate id="v" name="s" offset="12" length="1">${v}</logic:iterate>"/>
										<input type="hidden" name="xbxdArr" value="<logic:iterate id="v" name="s" offset="13" length="1">${v}</logic:iterate>"/>
									</td>
									<logic:iterate id="v" name="s" offset="2" length="11">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<td>
										<a href="#" onclick="showQsxx('<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>')"><font color="blue">查看</font></a>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglGywhForm"></jsp:include>
				</logic:notEmpty>
			</div>
			<div id="plUpdate" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th colspan="2">
										<span>
										寝室信息设置(只有当寝室入住情况为空时才可修改)
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>
										性别
									</th>
									<td>
										<input type="hidden" name="xbHid" id="xbHid" value="不修改"/>
										<html:radio property="xb"  value="不修改"  onclick="$('xbHid').value=this.value"/>不修改
										<html:radio property="xb"  value="男"  onclick="$('xbHid').value=this.value"/>男
										<html:radio property="xb"  value="女"  onclick="$('xbHid').value=this.value"/>女
									</td>
								</tr>
								<tr>
									<th>
										可否混住
									</th>
									<td>
										<input type="hidden" name="kfhzHid" id="kfhzHid" value="不修改"/>
										<html:radio property="kfhz"  value="不修改"  onclick="$('kfhzHid').value=this.value"/>不修改
										<html:radio property="kfhz"  value="可以"   onclick="$('kfhzHid').value=this.value"/>可以
										<html:radio property="kfhz"  value="不可"   onclick="$('kfhzHid').value=this.value"/>不可
									</td>
								</tr>
								<tr>
									<th>
										收费标准
									</th>
									<td>
										<input type="hidden" name="sfxgHid" id="sfxgHid" value="不修改"/>
										<input type="radio" name="sfxg"  value="不修改" checked onclick="changeValue(this,'true')"/>不修改
										<input type="radio" name="sfxg" value="修改" onclick="changeValue(this,'');"/>修改
										<html:text property="sfbz" styleId="sfbz_id"  disabled="true"/>
									</td>
								</tr>
								<tr>
									<th>
										分配标记
									</th>
									<td>
										
										<input type="hidden" name="fpbjHid" id="fpbjHid" value="不修改"/>
										<html:radio property="fpbj" value="不修改" onclick="$('fpbjHid').value=this.value"/>不修改
										<html:radio property="fpbj" value="一般"  onclick="$('fpbjHid').value=this.value"/>一般
										<html:radio property="fpbj" value="保留"  onclick="$('fpbjHid').value=this.value"/>保留
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<!-- 保存 -->
										<button onclick="updateQsxx()">
											保 存
										</button>
										<!-- 关闭 -->
										<button onclick="hiddenMessage(true,true);">
											关 闭
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
		</html:form>
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
		<logic:notPresent name="delMessage">
				<logic:present name="message">
				<script>
					alert($("message").value);	
					$("message").value = "";
					$("doType").value = "";	
				</script>
				</logic:present>
		</logic:notPresent>	
	</body>
</html>
