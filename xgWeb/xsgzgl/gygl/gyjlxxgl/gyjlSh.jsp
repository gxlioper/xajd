<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_gyjlgl_gyjlsh.do');
		}
		function view(url,h,w){
			var xh = curr_row.getElementsByTagName('input')[0].value;
			xh = xh.split("!!shen!!")[1];
			var wjsj = curr_row.getElementsByTagName('input')[1].value;
			showTopWin(url + '&xh='+xh+'&wjsj='+wjsj,h,w);
		}

		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		//���
		function gyjlSh(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pk_value=jQuery("[name=div_pkValue]:checked").eq(0).val();

				var xh=pk_value.split("!!shen!!")[1];

				var jlsj=pk_value.split("!!shen!!")[0];
					
				var url="gyglnew_gyjlgl_gyjlclsh.do";
				
				url+="?xh="+xh+"&jlsj="+jlsj;

				showTopWin(url,830,610);
			}else if(len>1){

				tipsWindown("","id:shDiv","420","210","true","","true","id");

			}else{
				
				alertInfo("�빴ѡ��Ҫ��˵ļ�¼��");
				
				return false;
			}
		}

		function saveShzt(act){
			
			confirmInfo("ȷ��Ҫ����ѹ�ѡ�ļ�¼��?",function(tag){

				if(tag=="ok"){
					allNotEmpThenGo('gyglnew_gyjlgl_gyjlsh.do?doType=sh&act='+act);
				}else {
					return false;
				}
			});
		}


<%--		jQuery(function(){--%>
<%--			$("page_loading").style.display="";--%>
<%--			$("rsTable").style.display="none";--%>
<%--			setTimeout("c()","3000");--%>
<%--		});--%>
<%----%>
<%--		function c(){--%>
<%--			$("page_loading").style.display="none";--%>
<%--			$("rsTable").style.display="";--%>
<%--		}--%>
		</script>
	</head>
	<body >
		<html:form action="/gyglnew_gyjlgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
<%--			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>--%>
			<input type="hidden" id="num" value="<bean:write name="num"/>"/>
			<!-- ������ -->
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="gyjlSh();return false;" class="btn_sh"> ��� </a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<%@ include file="/xsgzgl/gygl/gyjlxxgl/gyglGjcx.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫����¼�ɲ鿴;</font>
						</span>
					</h3>
					<div class="con_overlfow">
					<%@ include file="/comm/loading.jsp"%>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)"
										nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rsArrList">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="pk" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s">
								<logic:iterate name="s" id="dis" offset="1" length="1"></logic:iterate>
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
								ondblclick="view('gyglnew_gyjlgl.do?method=gyjlcxView&act=clview',800,600);">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center" width="5px">
											<input type="checkbox" id="pk_${index }"
											name="div_pkValue" value="${v }"/>
											<input type="hidden" id="wjsj" name="wjsj"
											value="<logic:iterate id="v" name="s" offset="5" length="1">${v }</logic:iterate>"/>
											<input type="hidden" id="xh" name="xh"
											value="${v }"/>
										</td>
									</logic:iterate>
									
									<!-- ��ʾ��Ϣ -->
									<logic:iterate id="v" name="s" offset="1">
										<td align="left" nowrap="nowrap">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rsArrList")).size();
							int pageSize = (Integer)(request.getAttribute("pageSize"));
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" indexId="index_id">
									<logic:notEqual value="0" name="index_id">
									<td nowrap="nowrap">
										&nbsp;
									</td>
									</logic:notEqual>
								</logic:iterate>
						 	</tr>
							<%}}%>
						</logic:notEmpty>
					</table>
					</div>
					<!--��ҳ��ʾ-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyjlxxglForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</div>
			
			<div id="shDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��˹�Ԣ������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									������<br/>(<font color="blue">��¼��500��</font>)
								</th>
								<td width="70%">
									<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gyjlxx&id=shyj" />
									<html:textarea property="shyj" rows="4" styleId="shyj" style="word-break:break-all;width:99%;margin-top:5px " onblur="chLeng(this,500);">
									</html:textarea>
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
										<button type="button" name="ȷ��" onclick="saveShzt('tg');return false;">
											ͨ��
										</button>
										<button type="button" name="ȷ��" onclick="saveShzt('btg');return false;">
											��ͨ��
										</button>
										<button type="button" name="ȡ��" onclick="closeWindown();return false;">
											ȡ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
