<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/qxwhDWR.js'></script>
	<script language="javascript">
		function jssq(url){
			if(curr_row != null){
				refreshForm(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
				return true;
			}else{
				alert('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		
		function modi(url,h,w){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alert('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		
		//��ʾ�û�������Ϣ
		function showYhxx(jsdm,type){
		
			var html="";
			html+="<div class='tab' style='width:100%;height:100%;overflow-y:auto;overflow-x:hidden'><table  class='formlist'><thead><tr><th colspan='3'><span>";
			html+="�����û�";
			html+="</span></th></tr></thead>";		
			dwr.engine.setAsync(false);
			qxwhDWR.getYhForJs(jsdm,type,function(data){
				var len=data.length;
				html+="<tbody><tr>";
				var n=0;
				for(i=1;i<=data.length;i++){
					html+="<td width='33%'>"+data[i-1].yhm+"("+data[i-1].bmmc+")</td>";
					if(i%3==0){
						html+="</tr><tr>";
						n++;
					}
				}
				
				if(len%3!=0){
					for(i=1;i<=3-(len%3);i++){
						html+="<td></td>"
					}
				}
				
				if(n<3){
					for(i=1;i<3-n;i++){
						html+="<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
					}
				}
				
				html+="</tr></tbody><tfoot>";
				html+="<tr>	<td colspan='3' align='right'>"
				html+="<button  onclick='hiddenMessage(true,true);'>�ر�</button>"
				html+="</td></tr></tfoot></table></div>";
			});
			dwr.engine.setAsync(true);
			viewTempDiv('�û�������Ϣ','jswhDiv',600,220);
			$("jswhDiv").innerHTML=html;
		}
		
	</script>
	</head>
	<body>
		<html:form action="/jswhManage" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ϵͳά��-Ȩ��ά��-��ɫά��</a>
				</p>
			</div>
			
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_zj" id="btn_zj" onclick="showTopWin('jswhManage.do?method=jswhUpdate',700,500);return false;">����</a></li>
					<li><a href="#" class="btn_xg" id="btn_xg" onclick="modi('jswhManage.do?method=jswhUpdate',700,500);return false;">�޸�</a></li>
					<li><a href="#" class="btn_xy" id="btn_xy" onclick="jssq('jswhManage.do?method=jssqManage');return false;">ָ����ɫ���书��</a></li>
					<li><a href="#" class="btn_sc" id="btn_sc" onclick="dataBatch('jswhManage.do?method=jswhDel');return false;">ɾ ��</a></li>			
				</ul>
			</div>
			</div>
			
			<div class="searchtab">
				<table width="100%" class="">
					<tbody>
						<tr>
							<th>��ɫ������Χ</th>
							<td><html:select property="jscmdm" style="width:150px" >
									<html:option value=""></html:option>
									<html:options collection="jsczfwList" property="jscmdm"
										labelProperty="jscmmc" />
								</html:select>
						   </td>
							<th>��ɫ����</th>
							<td><html:select property="jslxdm" style="width:150px" >
									<html:option value=""></html:option>
									<html:options collection="jslxList" property="jslxdm"
										labelProperty="jslxmc" />
								</html:select>
						   </td>
						</tr>
						<tr>
						<th>�Ƿ�����</th>
							<td><html:select property="sfqy" style="width:150px" >
									<html:option value=""></html:option>
									<html:options collection="yesNoList" property="en"
										labelProperty="cn" />
								</html:select>
						   </td>
						<th>��ɫ��</th>
							<td><html:text property="jsmc" styleId="jsmc" style="width:150px"></html:text></td>
						</tr>
					</tbody>
					
					<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="go" />
		              		<button type="button" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/jswhManage.do?method=jswh&go=go')">
		              		�� ѯ
		              		</button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			�� ��
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     		</tfoot>
				</table>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ�������������û��Ľ�ɫ����ɾ����</font>
					</span>
				</h3>
				<table width="99%" id="rsTable" class="dateline">
					<thead>
						<tr align="center" style="cursor:hand">
							<td>
								<input type="checkbox" name="all" value="all" onclick="chec()"/>
							</td>
							<logic:iterate id="tit" name="topTr" offset="1">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)" align="center">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:empty name="rs">
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
					 
					 <logic:notEmpty name="rs">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand">
								<td>
									<input type="checkbox" name="checkPkValue" id="pkV"
										<logic:iterate id="sm" name="s" offset="6" length="1">
											<logic:notEqual name="sm" value="0">
												disabled="disabled"
											</logic:notEqual>
										</logic:iterate>
										value="<logic:iterate id="vk" name="s" offset="0" length="1">${vk }</logic:iterate>" />
								</td>
							
								<logic:iterate id="v" name="s" offset="1" length="5">
									<td>${v }</td>
								</logic:iterate>
									<logic:iterate id="v" name="s" offset="6" length="1">
									<td><a href="#" onclick="showYhxx('${vk}','all');return false;"><u><font color="blue">
									${v }</font></u></a></td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="7" length="1">
									<td><a href="#" onclick="showYhxx('${vk}','gl');return false;"><u><font color="blue">
									${v }</font></u></a></td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = 11;
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
						<%}}%>
					</logic:notEmpty>
				</table>
				<!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qxwhForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>
		<div id="jswhDiv" style="display: none">
					
			</div>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
		<!-- ��ʾ��Ϣ -->
	</body>
</html>
