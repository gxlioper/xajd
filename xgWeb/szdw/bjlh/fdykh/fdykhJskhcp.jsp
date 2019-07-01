<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		
		//辅导员评分
		function fdykhPf(url){
			if(curr_row != null){
					url+="&fdyid="+curr_row.getElementsByTagName('input')[0].value;
					url+="&xm="+curr_row.getElementsByTagName('input')[1].value;
					url+="&bmmc="+curr_row.getElementsByTagName('input')[2].value;
					url+="&khbid="+curr_row.getElementsByTagName('input')[4].value;
					showTopWin(url,800,600);
					return true;
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}
		function fdykhpfView(url){
			url+="&fdyid="+curr_row.getElementsByTagName('input')[0].value;
			url+="&xm="+curr_row.getElementsByTagName('input')[1].value;
			url+="&bmmc="+curr_row.getElementsByTagName('input')[2].value;
			url+="&khbid="+curr_row.getElementsByTagName('input')[4].value;
			showTopWin(url,800,600);
			return false;
		}
		
		//高级查询 
		function searchRs(){
			allNotEmpThenGo('bjlh_fdykh_jskhcp.do');
		}
</script>

	</head>
	<body>
		<html:form action="/bjlh_fdykh" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<logic:equal name="writeAble" value="yes">
				<logic:equal value="false" name="sfxspfan">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
		     				 <li> <a href="#" disabled='true' onclick="" class="btn_xg"> 评分</a> </li>
		    			</ul>
					</div>
				</logic:equal>
				<logic:notEqual value="false" name="sfxspfan">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
		     				 <li> <a href="#" onclick="fdykhPf('/xgxt/bjlh_fdykh.do?method=fdykhJskhpf');return false;" class="btn_xg"> 评分</a> </li>
		    			</ul>
					</div>
				</logic:notEqual>
				</logic:equal>
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
									提示：双击一行可以查看详细信息；单击表头可以排序;</font><font color="red">${yhInfo }</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
			<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td style="display: none">
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort_hc(this,1)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="left"
								ondblclick="fdykhpfView('/xgxt/bjlh_fdykh.do?method=fdykhJskhpf&doType=view');return false;">
								<td align="center" style="display: none">
								<logic:iterate id="v" name="s" offset="1" length="4">
									<input type="hidden" value="<bean:write name="v"/>"/>
								</logic:iterate>
									<input type="hidden" value="<logic:iterate id="v" name="s" offset="6" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="0" length="6">
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
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdykhForm"></jsp:include>
			  	<!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>
	
	</body>
</html>