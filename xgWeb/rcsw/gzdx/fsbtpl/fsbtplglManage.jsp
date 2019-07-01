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
				jQuery("#search_go").click(function(){searchGo('/xgxt/rcsw_gzdx_fsbtplgl.do?method=fsbtplglManage&doType=go','nd-yf-btdm')});
				
				//����
				jQuery("#btn_cz").click(function(){searchReset();});
				
				//���
				jQuery(".btn_sc").bind("click",function(event){
					event.preventDefault();
					batchData('pkValues','/xgxt/rcsw_gzdx_fsbtplgl.do?method=fsbtplglManage&doType=qk','qk');
				});

				jQuery(".btn_dr").bind("click",function(event){
					event.preventDefault();
					impAndChkData();
				});
				
				jQuery(".btn_dc").bind("click",function(event){
					event.preventDefault();
					document.forms[0].action = "rcsw_gzdx_lxgl.do?method=lxcxManage&doType=export";
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";
				});
				
				//����
				jQuery(".btn_xg").bind("click",function(event){
					event.preventDefault();
					var nd = document.getElementById("nd").value;
					var yf = document.getElementById("yf").value;
					var btdm = document.getElementById("btdm").value;
					var querry = "&nd="+nd+"&yf="+yf+"&btdm="+btdm;
					var flag = false;
					var pkV=document.getElementsByName("pkValues");
					for(i=0;i<pkV.length;i++){
						if(pkV[i].checked && !pkV[i].disabled){
							flag=true;
						}
					}
					if(flag){
						showTopWin('rcsw_gzdx_fsbtplgl.do?method=fsbtplglUpdate'+querry,700,360);
						return false;
					}else{
						if(confirm('��ǰû�й�ѡ��¼���Ƿ��Ѳ�ѯ����Ϊ���ݷ��Ų�����')){
							showTopWin('rcsw_gzdx_fsbtplgl.do?method=fsbtplglUpdate'+querry,700,360);
						}
						return false;
					}
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
			function searchGo(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("��ȣ��·ݣ�������ĿΪ��ѡ�ֶΣ���ѡ����д������");
						return false;
					}
				}
				allNotEmpThenGo('/xgxt/rcsw_gzdx_fsbtplgl.do?method=fsbtplglManage&doType=go');
			}
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
		<html:form action="/rcsw_gzdx_fsbtplgl" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" name="qx" value="${qx }"/>
			<input type="hidden" id="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" value="${tableName }" />
			<input type="hidden" id="userType" value="${user.userStatus }" />
			<input type="hidden" name="isFdy" value="${isFdy }"/>
			<input type="hidden" name="isBzr" value="${isBzr }" />
			<input type="hidden" name="userName" value="${user.userName }"/>
			<input type="hidden" id="num" value="<bean:write name="num"/>"/>
			<input type="hidden" id="searchTjstr" value="${searchTjstr }"/>
			
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>			
			
			<logic:equal value="yes" name="writeAble">
				<!-- ��ѧ������ begin -->
				<logic:notEqual name="userType" value="stu">
				<div class="toolbox" id="dgncz">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							
							<li><a href="#" class="btn_xg">��������</a></li>
							<li><a href="#" class="btn_sc">���</a></li>
							<li><a href="#" class="btn_dr">����</a></li>	
							
						</ul>
					</div>
				</div>
				</logic:notEqual>
				<!-- ��ѧ������ end -->
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
							<th>���</th>
							<td>
								<html:select property="nd" style="nd" styleId="nd">
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							<th>�·�</th>
							<td>
								<html:select property="yf">
									<html:options collection="yfList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>������Ŀ</th>
							<td>
								<html:select property="btdm">
									<html:options collection="fsbtList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<logic:notEqual name="userType" value="stu">
						<tr>
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
							<th>�༶</th>
							<td>
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td><html:text property="xueh" styleId="xueh"></html:text></td>
							<th>����</th>
							<td><html:text property="xm" styleId="xm"></html:text></td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						</logic:notEqual>
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
								<logic:notEqual name="userType" value="stu">
								<td><input type="checkbox" name="checAll" disabled="disabled"/></td>
								</logic:notEqual>
								<logic:iterate id="tit" name="topTr" offset="1">
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
								<logic:notEqual name="userType" value="stu">
								<td>
									<input type="checkbox" disabled="disabled"></input>
								</td>
								</logic:notEqual>
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
								
									
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="modi('/xgxt/rcsw_gzdx_fsbtplgl.do?method=fsbtplglView&doType=view',700,400)">
									<!-- ѧ������Ҫ��ѡ��ť -->
									<logic:notEqual name="userType" value="stu">
									<td> 
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkValues" value="${v }"/>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" name="xh" value="${v }"/>
										</logic:iterate>
									</td>
									</logic:notEqual>
									
									<logic:equal name="userType" value="stu">
									<td  style="display:none">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkValues" value="${v }" style="display:none" />
										</logic:iterate>
									</td>
									</logic:equal>
									
									<logic:iterate id="v" name="s" offset="1">
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
								<logic:notEqual name="userType" value="stu">
								<td><input type="checkbox" disabled="disabled"/></td>
								</logic:notEqual>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=fsbtplglForm"></jsp:include>
				<!--��ҳ��ʾ-->
				<logic:equal name="userType" value="stu">
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				</logic:equal>
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
