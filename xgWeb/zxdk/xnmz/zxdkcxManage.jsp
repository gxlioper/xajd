<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglCwgl.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('/xgxt/zxdk_xnmz.do?method=zxdkcxManage&doType=query');
		}
		
		function addZxdk(){
			showTopWin("/xgxt/zxdk_xnmz.do?method=zxdkSq",800,600);
		}
		
		function modi(){
			var url='/xgxt/zxdk_xnmz.do?method=zxdkModi&doType=update';
			if(curr_row != null){
				showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function view(obj){
			var url='/xgxt/zxdk_xnmz.do?method=zxdkModi&doType=view';
			if(curr_row != null){
				showTopWin(url + '&xh='+obj.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function zxdkShlcgz(){
			var url='/xgxt/zxdk_xnmz.do?method=zxdkShlcgz';
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,500,300);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		
		//查询页面进行批量操作
		function del(){
		  	var pkValue=document.getElementsByName("checkVal");
		  
			var bool=false;
			for (i=0; i<pkValue.length; i++){
		    	if(pkValue[i].checked){
		    		bool=true;
		    	}
			}
			
			if(!bool){
				alertError("请勾选需要删除的数据!");
				return false;
			}
			
			confirmInfo('确定要删除已勾选的数据吗？',function(t){
				if (t=='ok'){
					refreshForm("/xgxt/zxdk_xnmz.do?method=zxdkcxManage&doType=del");
				}
			})	
						
		}
		
		function zhgjzxdkb(dyblx){
		
			if(curr_row){
				var pkValue= curr_row.getElementsByTagName('input')[0].value;
				window.open("/xgxt/zxdk_xnmz.do?method=zxdkbbdy&pkValue="+pkValue+"&dyblx="+dyblx,800,600);
			}else {
				alertInfo("请先选择一条记录!");
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		
		<!-- 标题 end-->
		<html:form action="/zxdk_xnmz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<!-- 确定 -->
						<li>
							<a href="#" id="btn_shtg" onclick="addZxdk();return false;"
								class="btn_zj" > 增加 </a>
						</li>
						<!-- 手动分配 -->
						<li>
							<a href="#" id="btn_xg"
								onclick="modi();return false;"
								class="btn_xg"> 修改 </a>
						</li>
						<!-- 手动分配 -->
						<li>
							<a href="#" id="btn_sh"
								onclick="del();return false;"
								class="btn_sc"> 删除 </a>
						</li>
						</logic:equal>
						<li>
							<a href="#" id="btn_sh"
								onclick="zxdkShlcgz();return false;"
								class="btn_csh"> 流程跟踪 </a>
						</li>
						<li>
							<a href="#" id="btn_sh"
								onclick="zhgjzxdkb('zhgjzxdk');return false;"
								class="btn_dy"> 中行国家助学贷款 </a>
						</li>
						<li>
							<a href="#" id="btn_sh"
								onclick="zhgjzxdkb('gjzxdkxsxx');return false;"
								class="btn_tj"> 学生国家助学贷款信息 </a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->

				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->

			</div>
			<!-- 多功能操作区 end -->

			<!-- 查询结果-->
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> 查询结果 <logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue">双击一条记录可以查看详细信息；</font>
						</logic:notEmpty>
					</span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<!-- 表头 -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- 表头 end-->
						<!--内容 -->
						<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="view(this)">
									<td>
										<div align="center">
										<input type="checkbox" name="checkVal" id="pkV"
											<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" />
										</div>
									</td>
									<!-- 显示信息 -->
									<logic:iterate id="v" name="s" offset="2">
										<td align="left" nowrap="nowrap">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<!--内容 end-->
						<!-- 补空行 -->
						<%@ include file="/comm/noRows.jsp"%>
						<!-- 补空行 end-->
					</table>
				</div>
				<!--分页-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=zxdkForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->


			<div id="fpxx" style="display: none" style="width:650px">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th>
									<span> 分配信息 </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<p id="Bmfpxx" class="tab"
										style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- 确定 -->
										<button onclick="zdfpcw();">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 提示信息end -->

			<!-- 请等待 -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- 请等待 end-->
			
		</html:form>
	</body>
</html>
