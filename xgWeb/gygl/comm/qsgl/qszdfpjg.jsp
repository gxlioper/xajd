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
			allNotEmpThenGo('/xgxt/gyglQsgl.do?method=qszdfpjg');
		}
		
		//取消分配
		function qxfp(){
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					flag = true;
					break;
				}
			}
			
			if(flag){
				if (confirm("将要取消所勾选寝室的分配情况，请确认！")) {
					saveUpdate('/xgxt/gyglQsgl.do?method=qszdfpjg&doType=del','');
				}
			}else{
				alert("请勾选需要取消分配的寝室！");
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理-寝室管理-寝室自动分配结果</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/gyglQsgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 确定 -->
						<li>
							<a href="#" id="btn_fh"
								onclick="refreshForm('gygl_qsgl_zdfp.do');return false;"
								class="btn_fh">
								<bean:message key="lable.btn_fh" />
							</a>
						</li>
						
						<!-- 取消分配 -->
						<li>
							<a href="#" id="btn_qx"
								onclick="qxfp();return false;"
								class="btn_qx">
								<bean:message key="lable.btn_qx" />
							</a>
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
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;
							<font color="blue">查询结果仅为分配对象设置为</font> 
							<font color="red">
							<logic:equal name="fpdx" value="xy"><bean:message key="lable.xb" /></logic:equal>
							<logic:equal name="fpdx" value="njxy">年级+<bean:message key="lable.xb" /></logic:equal>
							<logic:equal name="fpdx" value="njzy">年级+专业</logic:equal>
							<logic:equal name="fpdx" value="bj">班级</logic:equal>
							</font>
							<font color="blue">的数据</font> 
							
							</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- 显示信息 -->
								<logic:iterate id="v" name="s" offset="0">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--内容 end-->
					<!-- 补空行 -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- 补空行 end-->
				</table>
				</div>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglQsglForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>