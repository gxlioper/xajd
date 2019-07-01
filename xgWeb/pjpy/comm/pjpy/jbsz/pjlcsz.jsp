<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			function modi(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('请选择要修改的数据行！');
					return false;
				}
			}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/pjpyLcsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/pjpyLcsz.do?method=pjlcszOne','600','480')"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="modi('/xgxt/pjpyLcsz.do?method=pjlcszOne')"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="batchData('primarykey_cbv','/xgxt/pjpyLcsz.do?method=pjlcsz&doType=del','del')"
									class="btn_sc">删除</a>
							</li>
<%--							<li>--%>
<%--								<a href="#"--%>
<%--									onclick=""--%>
<%--									class="btn_ccg">保存</a>--%>
<%--							</li>--%>
							<li>
								<a href="#"
									onclick=""
									class="btn_dc">批量设置</a>
							</li>
							<li>
								<a href="#"
									onclick=""
									class="btn_yl">预览流程</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->			
				<!-- 查询结果-->
				<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				 <logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序</font> 
						</span>
					</h3>
					<div class="con_overlfow">
						<table summary="" class="dateline" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="all" value="all" onclick="chec()" />
									</td>
									<logic:iterate id="tit" name="topTr">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" >
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">								
											<input type="checkbox" name="primarykey_cbv" id="pkV" value="${v}" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						</div>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>