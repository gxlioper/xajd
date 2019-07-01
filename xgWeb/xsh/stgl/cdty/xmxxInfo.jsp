<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript">	
		function sendXx(){			
			if(window.opener == undefined){					 				
				var url = window.dialogArguments.document.forms[0].url.value;
				url+="&stdm="+curr_row.getElementsByTagName('input')[0].value;
				window.dialogArguments.document.forms[0].action = url;
				window.dialogArguments.document.forms[0].submit();
			}else{
				var url = window.opener.document.forms[0].url.value;
				url+="&stdm="+curr_row.getElementsByTagName('input')[0].value;
				window.opener.document.forms[0].action = url;
				window.opener.document.forms[0].submit();
			}
			window.close();
		}			
		jQuery(function(){
			xyDisabled('xy');
		})	
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>信息维护 - ${lx }信息</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXgInfo" method="post">	
		<!-- 隐藏域 -->
		<%@ include file="/comm/hiddenValue.jsp"%>
		<!-- 隐藏域 end-->
			<input type="hidden" id="lx" name="lx" value="${lx }" />
			<input type="hidden" id="zd" name="zd" value="${zd }" />
			<input type="hidden" id="va" name="va" value="${va }" />
			
			<div class="toolbox">
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commXgInfo.do?method=xsxxManage');">
											查询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
				<logic:empty name="stxxInfo">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="stxxInfo">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">提示：单击表头可以排序；双击选定学生信息.</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="stxxInfo" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="sendXx()">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=cdtyXsstForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
		</html:form>
	</body>
</html>
