<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwfpxxcx&doType=query');
		}
		
	
		
		//取消分配分配
		function qxfpcw(){
			
			var pkvArr = document.getElementsByName("pkvArr");
			
			var xh = new Array();
			
			var blog=false;
			for(var i=0;i<pkvArr.length;i++){
				var obj =pkvArr[i];
				if(obj.checked){
					blog=true;
				}
			}
			
			if(!blog){
				alert("请选择需要取消分配的床位信息!");
				return false;
			}
			
			saveUpdate('/xgxt/gyglCwgl.do?method=cwfpxxcx&doType=qxfp','');
			
		}
		
		function goCwxxtj(){
			refreshForm("gygl_cwgl_cwfp.do");
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 床位管理 - 床位分配信息查询 </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/gyglCwgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_qx"
								onclick="qxfpcw();return false;"
								class="btn_qx">
								取消分配
							</a>
						</li>
						<li>
							<a href="#" id="btn_fh" onclick="goCwxxtj();return false;"
								class="btn_fh"> 返回 </a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->

			</div>
			<!-- 多功能操作区 end -->

			<!-- 查询结果-->
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果 <logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue"></font>
						</logic:notEmpty> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<!-- 表头 -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td width="5px">
									<input type="checkbox" id="selall" name="selall"
										onclick="selAll()" />
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
						<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td align="center" width="5px">
										<input type="checkbox" id="pk_${index }" name="pkvArr"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</td>
									<!-- 显示信息 -->
									<logic:iterate id="v" name="s" offset="1" length="${showNum }">
										<td align="left">
											${v }
										</td>
									</logic:iterate>

									<!-- 需分配床位数 -->
									<logic:iterate id="v" name="s" offset="${xfprs }" length="1">
										<td style="display:none">
											<input type="hidden" id="xfprs_${index }" value="${v }" />
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
					page="/sjcz/turnpage.jsp?form=gyglCwglForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->

			
			<logic:present name="result">
				<logic:equal name="result" value="true">
					<script>
						alert("操作成功！");
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败！");
					</script>
				</logic:equal>
			</logic:present>
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>
