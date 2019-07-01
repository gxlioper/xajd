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
			var stid;
			if(type=="update"){
				if(curr_row != null){
					stid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
			}
			var wjid=$("wjid").value;
			var url="bjlh_fdycpwj.do?method=cpwjglStwhOne&doType="+type+"&wjid="+wjid+"&stid="+stid;
			showTopWin(url,650,400);
		}
			
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('bjlh_fdycpwj.do?method=cpwjglStwh');
			}
			

			//ɾ��
			function delCpwjst(doType){
				var stid;
				if(curr_row != null){
					stid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				confirmInfo("ȷ��ɾ����",function(data){
					if("ok"==data){
						allNotEmpThenGo('bjlh_fdycpwj.do?method=cpwjglStwh&doType=del&stid='+stid);
					}
				});
			}
			function goback(){
				allNotEmpThenGo('bjlh_fdykh_cpwjgl.do?doType=');
			}
		</script>
	</head>
	<body onload="">
		<html:form action="/bjlh_fdycpwj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="wjid" styleId="wjid"/>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>˼������-����Ա����-����Ա�����ʾ�������� </a>
				</p>
			</div>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="cpwjStwh('add');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="cpwjStwh('update');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="delCpwjst();return false;" class="btn_sc">ɾ��</a></li>
						<li><a href="#" onclick="goback();return false;" class="btn_fh">����</a></li>
					</ul>
				</div>
			</div>
				<span style="display: none;">
					<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">��ѯ</button>
				</span>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;
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
						<logic:notEmpty name="stxxList">
							<logic:iterate name="stxxList" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none;">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="${s.stid }" />
										
									</td>
									<td><bean:write name="s" property="stmc" format="true"/></td>
									<td>${s.stlxmc }</td>
								</tr>
							</logic:iterate>
					</logic:notEmpty>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
