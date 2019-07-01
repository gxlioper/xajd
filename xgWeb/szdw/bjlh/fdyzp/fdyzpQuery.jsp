<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		function getFdyzpView(url){
				url+="&zpbid="+curr_row.cells[0].getElementsByTagName('input')[0].value;
				url+="&yhm="+curr_row.cells[0].getElementsByTagName('input')[2].value;
				showTopWin(url,800,600);
			}		
		function searchRs(){
			refreshForm('bjlh_fdykh_fdyzpcx.do');
		}
		//打印统计报表
		function getFdyzpPrint(url){
			if(curr_row!=null){
				url+="&zpbid="+curr_row.cells[0].getElementsByTagName('input')[0].value;
				url+="&yhm="+curr_row.cells[0].getElementsByTagName('input')[2].value;
				window.open(url);
			}else{
				alert("请选中打印行!");
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/bjlh_fdyzp" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
			<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="11417" name="xxdm" scope="request">
							<li><a href="#" class="btn_dy" onclick="getFdyzpPrint('/xgxt/bjlh_fdyzp.do?method=fdyzpPrint');return false;">打印报表</a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
								<font color="blue"> 
									提示：双击一行可以查看详细信息；单击表头可以排序;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">
			<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
			    	<thead>
							<tr align="center" style="cursor:hand">
								<td  style="display: none">
									<input type="hidden" id="selall" name="selall" onclick="selAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" indexId="index">
									<td onclick="tableSort_hc(this,1)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="left"
								ondblclick="getFdyzpView('/xgxt/bjlh_fdyzp.do?method=fdyzpView');return false;">
								<td align="center" style="display: none">
									<logic:iterate id="v" name="s" offset="0" length="3">
									<input type="hidden" value="<bean:write name="v"/>"/>
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="7">
									<td>
											<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdyzpForm"></jsp:include>
			  	<!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>
		
	</body>
</html>
