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
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
				
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('bjlh_fdykh_cpwjcx.do');
			}
			
			//�����ʾ�Ԥ��
			function cpwjglTjOne(){
				var pk;
				if(curr_row != null){
					pk=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				var pkv=pk.split("!!one!!");
				var url="bjlh_fdycpwj.do?method=cpwjglTj&xn="+pkv[0]+"&wjid="+pkv[1]+"&fdyid="+pkv[2];
				showTopWin(url,800,800);
			}
		</script>
	</head>
	<body onload="">
		<html:form action="/bjlh_fdycpwj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="cpwjglTjOne();return false;" class="btn_yl">ͳ��</a></li>
					</ul>
				</div>
			</div>
				<logic:equal name="superSearch" value="yes">
			     	<%@ include file="/comm/search/superSearchArea.jsp"%>
			    </logic:equal>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none;">
										<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							BjlhFdycpwjForm form = (BjlhFdycpwjForm)request.getAttribute("bjlhFdycpwjForm");
							int pageSize = form.getPages().getPageSize();
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--��ҳ��ʾ-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdycpwjForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ѡ��ȡ����ס����ť�ɲ���ʱ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>��ʼʱ��
								</th>
								<td>
									<input type="text" id="kssj" name="kssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>����ʱ��
								</th>
								<td>
									<input type="text" id="jssj" name="jssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="saveData('gyglnew_cssz.do?method=sjszManage&sfqy=yes&doType=xg','yes');return false;">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
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
