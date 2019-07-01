<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
			jQuery(function(){
				//��ѯ
				jQuery("#search_go").click(function(){allNotEmpThenGo('/xgxt/cdty_rcsw_qjgl.do?method=qjcxManage')});
				
				//����
				jQuery("#btn_cz").click(function(){searchReset();});
				
				//ɾ��
				jQuery(".btn_sc").bind("click",function(event){
					event.preventDefault();
					batchData('pkValues','/xgxt/cdty_rcsw_qjgl.do?method=qjcxManage&doType=del','del');
				});
				
				//��ӡ
				jQuery(".btn_dy").bind("click",function(event){
					event.preventDefault();
					print('/xgxt/cdty_rcsw_qjgl.do?method=qjxgUpdate&doType=print');
				});
				
				//�޸�
				jQuery(".btn_xg").bind("click",function(event){
					event.preventDefault();
					var dis = curr_row.cells[0].getElementsByTagName("input")[1].value;
					
					if("" == dis){
						modi('/xgxt/cdty_rcsw_qjgl.do?method=qjxgUpdate&doType=view',800,600);
					}else{
						alertInfo("�ü�¼��������У�����ϵ����Ա�޸ģ�");
					}
				});
				
				//����
				jQuery(".btn_dr").bind("click",function(event){
					event.preventDefault();
					impAndChkData();
				});
				
				//����
				jQuery(".btn_dc").bind("click",function(event){
					event.preventDefault();
					dataExport();
				});
				
				//ѧԺ
				jQuery("#xy").change(function(){
					initZyList();
					initBjList();
				});
				
				//רҵ
				jQuery("#zy").change(function(){
					initBjList();
				});
				
				disXy();
			});
			
			function print(url,w,h){
				if (curr_row == null) {
					alertInfo("��ѡ��Ҫ�������У�");
					return false;
				} else {
					var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
					url += "&pkValue=";
					url += pk;
					window.open(url);
				}	
			}
			
		</script>
		
	</head>
	<body>
		<html:form action="/cdty_rcsw_qjgl" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			
			<input type="hidden" id="userType" value="${user.userStatus }" />
			<input type="hidden" name="isFdy" value="${isFdy }"/>
			<input type="hidden" name="isBzr" value="${isBzr }" />
			<input type="hidden" name="userName" value="${user.userName }"/>
			
			<input type="hidden" id="userType" value="${user.userStatus }" />
			
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>			
			
			<logic:equal value="yes" name="writeAble">
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_xg">�޸�</a></li>
						<li><a href="#" class="btn_sc">ɾ��</a></li>
						<li><a href="#" class="btn_dy">��ӡ����</a></li>	
						<li><a href="#" class="btn_dr">����</a></li>
						<li><a href="#" class="btn_dc">����</a></li>		
					</ul>
				</div>
			</div>
			</logic:equal>
			<div class="searchtab">
				<table>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="btn_cx" id="search_go">
										��ѯ
									</button>
									<button type="button" class="btn_cz" id="btn_cz">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								<html:select property="xn" style="width:100px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>ѧ��</th>
							<td>
								<html:select property="xq" style="width:90px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							<th>ѧ��</th>
							<td><html:text property="xh" style="width:85px"></html:text></td>
						</tr>
						<tr>
							<th>����</th>
							<td><html:text property="xm" style="width:85px"></html:text></td>
							<th>
								Ժϵ
							</th>
							<td>
								<html:select property="xydm" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
							</td>
							<th>רҵ</th>
							<td>
								<html:select property="zydm" styleId="zy" style="width:180px" >
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�༶</th>
							<td>
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<th colspan="4"></th>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫����¼�鿴��ϸ��Ϣ</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td><input type="checkbox" name="checAll" disabled="disabled"/></td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td onclick="tableSort(this)">
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
								<td>
									<input type="checkbox" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="modi('/xgxt/cdty_rcsw_qjgl.do?method=qjshUpdate&doType=view',800,600)">
									<td> 
										<logic:iterate id="dis" name="s" offset="0" length="1"></logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkValues" value="${v }" ${dis }/>
										</logic:iterate>
										
										<input type="hidden" value="${dis }"/>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)request.getAttribute("pageSize");
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td><input type="checkbox" disabled="disabled"/></td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--��ҳ��ʾ-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=cdtyQjglForm"></jsp:include>
				<!--��ҳ��ʾ-->
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
