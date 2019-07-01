<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xgxt.szdw.bjlh.fdycpwj.BjlhFdycpwjForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		function cpwjStwh(type){
			var xmid;
			if(type=="update"){
				if(curr_row != null){
					xmid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
			}
			var khbid=$("khbid").value;
			var url="bjlh_fdykh.do?method=fdykhXmwhOne&doType="+type+"&khbid="+khbid+"&xmid="+xmid;
			showTopWin(url,650,400);
		}
			
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('bjlh_fdykh.do?method=fdykhXmwh');
			}
			

			//删除
			function delCpwjst(doType){
				var xmid;
				if(curr_row != null){
					xmid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				confirmInfo("确定删除吗？",function(data){
					if("ok"==data){
						allNotEmpThenGo('bjlh_fdykh.do?method=fdykhXmwh&doType=del&xmid='+xmid);
					}
				});
			}

			function goback(){
				allNotEmpThenGo('bjlh_fdykh_khcpbgl.do?doType=');
			}
		</script>
	</head>
	<body onload="">
		<html:form action="/bjlh_fdykh" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="khbid" styleId="khbid"/>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>思政队伍-辅导员考核-考核项目维护</a>
				</p>
			</div>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="cpwjStwh('add');return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="cpwjStwh('update');return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="delCpwjst();return false;" class="btn_sc">删除</a></li>
						</logic:equal>
						<li><a href="#" onclick="goback();return false;" class="btn_fh">返回</a></li>
					</ul>
				</div>
			</div>
				<span style="display: none;">
					<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">查询</button>
				</span>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td style="display: none;">
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort_hc(this,1)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:notEmpty name="xmxxList">
							<logic:iterate name="xmxxList" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none;">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="${s.xmid }" />
										
									</td>
									<td><bean:write name="s" property="yjzb" format="true"/></td>
									<td><bean:write name="s" property="ejzb" format="true"/></td>
									<td><bean:write name="s" property="khnr" format="true"/></td>
									<td>${s.fzqj }</td>
									<td>${s.pflx }</td>
									<td>${s.xssx }</td>
								</tr>
							</logic:iterate>
					</logic:notEmpty>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
