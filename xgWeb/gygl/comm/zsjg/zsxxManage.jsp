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
			allNotEmpThenGo('/xgxt/gyglZsjg.do?method=zsxxManage&doType=query');
		}
		
		//删除历史信息
		function delLsxx(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				var mklx=$("mklx").value;
				var url="/xgxt/gyglZsjg.do?method=zsxxManage";
				url+="&doType=xsts";
				refreshForm(url);
				hiddenMessage(true,true);
				BatAlert.showTips('正在操作，请稍等...');
			}else{
				alert("请选择需要“退宿”的学生记录!");
				return false;
			}
		
			
		}
		
		//删除历史信息
		function pldelLsxx(){
		
			if(confirm("“批量退宿”操作是对满足查询条件的学生进行整体退宿，\n如没有选择任何条件，点击“批量退宿”按钮会将所有学生进行\n退宿，确认要继续操作吗？"))	{	
				setSearchTj();
				var url="/xgxt/gyglZsjg.do?method=zsxxManage";
				url+="&doType=plxsts";
				refreshForm(url);
				hiddenMessage(true,true);
				BatAlert.showTips('正在操作，请稍等...');
			}else {
				return false;
			}
		}
		
		function goZsxxtj(){
			refreshForm("gygl_zsjg_zstj.do");
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 住宿结果 - 住宿信息查看</a>
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
				本功能模块展示学生入住情况。勾选学生住宿信息后，点击"勾选退宿"按钮，可以对选中学生进行退宿操作。<br/>	
				选择相应的查询条件，点击"批量退宿"按钮可以对满足条件的学生进行整体退宿操作。<font color="blue"><B>在没有选择任何条件<br/>
				的情况下，点击"批量退宿"按钮会将所有学生进行退宿，请谨慎操作。</B></font>
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<html:form action="/gyglZsjg">
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
							<a href="#" id="btn_dc"
								onclick="delLsxx();return false;"
								class="btn_dc">
								勾选退宿
							</a>
						</li>
						<li>
							<a href="#" id="btn_up"
								onclick="pldelLsxx();return false;"
								class="btn_up">
								批量退宿
							</a>
						</li>
						<li>
							<a href="#" id="btn_fh"
								onclick="goZsxxtj();return false;"
								class="btn_fh">
								返回
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
							&nbsp;&nbsp;<font color="blue"></font> 
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
							<logic:iterate id="tit" name="topTr" offset="1">
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
					<logic:notEmpty name="rsArrList" >
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- 显示信息 -->
								<logic:iterate id="v" name="s" offset="1" >
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglZsjgForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->

			<logic:present name="result">
				<logic:equal name="result" value="true">
				<script>
					alert("操作成功!");
				</script>
				</logic:equal>
				<logic:equal name="result" value="false">
				<script>
					alert("操作失败!");
				</script>
				</logic:equal>
			</logic:present>
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>