<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_cwfpgl_cwfp.do');
		}
		
		//设置查询提示层位置
		function setSearchMsgWz(left,top){
			$("input_mhcx_msg").style.left=left;
			$("input_mhcx_msg").style.top=top;
		}		

		//床位手动分配
		function goQssdfpByHand(act){
			
			if(!curr_row){
				alertInfo('请选择一行');
				return false;
			}
			var pkValue = curr_row.getElementsByTagName('input')[0].value;
			$("primarykey_checkVal").value=pkValue;
			
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&act="+act;
			refreshForm(url);
			BatAlert.showTips('正在操作，请稍等...');
		}	
		</script>
	</head>
	<body onload="setSearchMsgWz('130px','145px')">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理-住宿管理-床位分配管理</a>
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
				查询结果中的“总床位数”指分配给该年级<bean:message key="lable.xsgzyxpzxy" />该性别的床位总数；<br/>
				总人数=已入住人数+未入住人数；总床位数=已入住床位数+未入住床位数；
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/gyglnew_cwfpgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="primarykey_checkVal" id="primarykey_checkVal" />
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<!-- 手动分配 -->
						<li>
							<a href="#" id="btn_xg"
								onclick="goQssdfpByHand('to_fp');return false;"
								class="btn_sh">
								分配
							</a>
						</li>
						<!-- 取消分配 -->
						<li>
							<a href="#" id="btn_xg"
								onclick="goQssdfpByHand('to_qxfp');return false;"
								class="btn_qxsh">
								取消分配
							</a>
						</li>	
						</logic:equal>	
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
							<font color="blue">
							
							</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px" style="display: none;">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort_hc(this,1)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:empty name="rsArrList">
					  	<%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					 	</tr>
						<%}%>
					 </logic:empty>
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" style="cursor:hand">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px" style="display: none;">
									<input type="checkbox" id="pk_${index }"
									 value="${v }"/>
								</td>
							</logic:iterate>
							<!-- 显示信息 -->
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<!--内容 end-->
					<!-- 补空行 -->
					<%--<%@ include file="/comm/noRows.jsp"%>
					--%><!-- 补空行 end-->
				</table>
				</div>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewCwfpglForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
		</html:form>
	</body>
</html>