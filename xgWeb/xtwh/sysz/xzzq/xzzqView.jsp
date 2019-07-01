<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��������
		function clickLeftTj(value){
			var num = document.getElementsByName("left_lx").length;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("left_lx")[i];
				var obj_id = obj.id;
				var obj_value = obj.value;
				
				if(obj.checked){
					if(obj_value == "filess"){
						$("ssdm").value = value;
						$("lxdm").value = "";
					}else{
						$("lxdm").value = value;
						$("ssdm").value = "";
					}
				}
			}
			
			clickLeftMenu();
		}
		
		//��������
		function clickLeftMenu(){
			
			dwr.engine.setAsync(false);
			
			var divHtml = "<div id=\"dy_area\" style=\"height: 450px;overflow:scroll;overflow-x:hidden\">"
				divHtml+= "<span class=\"formbox\">";
				divHtml+= "<table class=\"dateline\" width=\"100%\" id=\"showDiary\">";
				
				//-------------����-------------
				divHtml+= "<thead><tr>";
				
				var tableName = $("tableName").value;
				var colList =["filemc","ss","lx"];
				var path = $("path").value;
				
				getOtherData.getTopTr(tableName,colList,path,function(data){
					if(data != null && data.length > 0){
						for(var i=0;i<data.length;i++){
							if(i == 0){
								divHtml+= "<td width='30%'>";
							}else{
								divHtml+= "<td width='20%'>";
							}
							divHtml+= data[i].cn;
							divHtml+= "</td>";
						}
					}
					divHtml+= "<td width='20%'>����</td>";
				});
				divHtml+= "</tr></thead>";					
				//------------���� end-----------
				
				//------------����---------------
				
				colList = ["filepath","filesm","filemc","ss","lx"];
				var pk = "";
				var pkValue = "";
				var userType = $("userType").value;
				var query = "and (xzdx = 'ȫ��' or xzdx = '";
				
				var dataSize = "0";
				var pageSize = $("pageRsSize").value;
				var currentPage =  $("currentPage").value;
				var maxRecord = "0";
				var maxPage = "0";
				
				var pages = [pageSize,currentPage];
				
				//�жϺ��ֲ�ѯ��ʽ
				if($("ssdm").value != ""){
					pk = "filess";
					pkValue = $("ssdm").value;
				}else{
					pk = "filelx";
					pkValue = $("lxdm").value;
				}
				
				//��½�û�Ϊѧ��
				if(userType == "stu"){
					query += "ѧ��";
				}else{
					query += "��ʦ";
				}
				query += "')";
				
				divHtml+= "<tbody>";
				getOtherData.getRsListForPage(tableName,colList,pk,pkValue,query,pages,function(data){
					if(data != null && data.length > 0){
						maxRecord = data[0][0];
						for(var i=1;i<data.length;i++){
							
							if(dataSize > pageSize){
								break;
							}
							
							var zdz = data[i];
							divHtml+= "<tr onclick=\"rowOnClick(this)\">";
							divHtml+= "<td title=\""+zdz[1]+"\">"+zdz[2]+"</td>"
							divHtml+= "<td>"+zdz[3]+"</td>"
							divHtml+= "<td>"+zdz[4]+"</td>"
							divHtml+= "<td><a href=\"czxxDtjsDyxx.do?method=downLoadFile&dir="+zdz[0]+"\" target=\"_blank\" style=\"color: blue\">���ظ���</a></td>"
							divHtml+= "</tr>";
							
							dataSize++;
						}
					}
				});
				divHtml+= "</tbody>";
				//------------���� end-----------
				
				//������	
				for(var i=0;i<pageSize - dataSize;i++){
					divHtml+= "<tr>";
					divHtml+= "<td>&nbsp;</td>"
					divHtml+= "<td>&nbsp;</td>"
					divHtml+= "<td>&nbsp;</td>"
					divHtml+= "<td>&nbsp;</td>"
					divHtml+= "</tr>";
				}
				
				divHtml+= "</table>";
				
				//��ҳ��ʾ
				maxPage = parseInt(maxRecord/pageSize) +1;
				$("maxPage").value = maxPage;
				
				divHtml+= "<div class=\"pagination\">";
					divHtml+= "<div class=\"pageleft\">";
						divHtml+= "<div class=\"pagenum\">";
						divHtml+= "<p class=\"pagenum\">��<span class=\"red\">";		
						divHtml+= "<input type=\"text\" class=\"text_nor\" id=\"pageNo\" value=\""+$("pageNum").value+"\"";
						divHtml+= "onkeydown=\"if(event.keyCode==13) {return submitNoPage()}\" size='2'";
						divHtml+= "name='pageno'  style=\"width:20px\"/></span> ҳ/ ��";
						divHtml+= "<span class=\"red\">"+maxPage+"</span>ҳ��ÿҳ��ʾ";
						divHtml+= "<input type=\"text\" value=\""+pageSize+"\" onkeydown=\"if(event.keyCode==13) {updatePageSize()}\"";
						divHtml+= "size=\"2\" style=\"width:20px\" class=\"text_nor\" id=\"showNum\" />";
						divHtml+= "��/��<span class=\"red\">"+maxRecord+"</span>����¼</p>";
						divHtml+= "</div>";
					divHtml+= "</div>";
					
					divHtml+= "<div class=\"pageright\">";
						divHtml+= "<div id=\"pagediv\" class=\"paging\">";
							divHtml+= "<span id=\"pagelist\" class=\"pagelist\"></span>";
							divHtml+= "<a id=\"first\" href=\"javascript:firstPage()\" class=\"first\" title=\"��ҳ\">�� ҳ</a>&nbsp;&nbsp;";
							divHtml+= "<a id=\"pre\" href=\"javascript:prePage()\" class=\"prev\" title=\"��һҳ\">��һҳ</a>&nbsp;&nbsp;";
							divHtml+= "<a id=\"next\" href=\"javascript:nextPage()\" class=\"next\" title=\"��һҳ\">��һҳ</a>&nbsp;&nbsp;";
							divHtml+= "<a id=\"last\" href=\"javascript:lastPage()\" class=\"last\" title=\"ĩҳ\">ĩ ҳ</a>&nbsp;&nbsp;";
						divHtml+= "</div>";
					divHtml+= "</div>";
					
				divHtml+= "</div>";
				
			dwr.engine.setAsync(true);
				
			$("dy_area").outerHTML=divHtml;
		}
		
		//����������������
		function chlckLeftLx(leftLx){
			var ul = document.getElementById("left_ul");
			var num = document.getElementsByName("left_a").length;

			for(var i=num-1;i>=0;i--){
		
				var obj = document.getElementsByName("left_a")[i];
				var li_id = "li_"+obj.id.replace("left_a_","");

				if($(li_id)){
					ul.removeChild($(li_id));
				}
			}
			
			dwr.engine.setAsync(false);
				
			var tableName = "";
			var colList = ["dm","mc"];
			var pk = "";
			var pkValue = "";
			var query = " order by dm ";
			
			if(leftLx == "filess"){
				tableName = "xg_xtwh_szzqssb";
			}else{
				tableName = "xg_xtwh_szzqlxb";
			}
				
			getOtherData.getTableListInfo(tableName,colList,pk,pkValue,query,function(data){
				if(data != null && data.length > 0){
				
					var li = document.createElement("li");  
						  
				    var value = "<a href=\"#\" name=\"left_a\"";
				    	value+= "id=\"left_a_0\"";
						value+= "onclick=\"clickLeftTj('');return false;\" style=\"\">";
						value+= "<span class=\"ico\"></span>";
						value+= "ȫ��";
				    	value+= "</a>";
				    		
				    li.innerHTML = value;
				    li.id = "li_0";
				    ul.appendChild(li); 
				        
					for(var i=0;i<data.length;i++){
					
						li = document.createElement("li");  	  
				    	value = "<a href=\"#\" name=\"left_a\"";
				    	value+= "id=\"left_a_"+parseInt(i+1)+"\"";
						value+= "onclick=\"clickLeftTj('"+data[i].dm+"');return false;\" style=\"\">";
						value+= "<span class=\"ico\"></span>";
						value+= data[i].mc;
				    	value+= "</a>";
				    		
				    	li.innerHTML = value;
				    	li.id = "li_"+parseInt(i+1);
				        ul.appendChild(li); 
					}
				}
			});
			
			dwr.engine.setAsync(true);
		}
		
		//��ѯ���
		function searchRs(){
			allNotEmpThenGo("/xgxt/xtwhSysz.do?method=xzzqView");
		}
		
		function setLiClick(num){
		
			var li_num = $("li_num").value;
			
			for(var i=0;i<li_num;i++){
				var id = "li_"+i;
				$(id).style.background="";
			}
			
			if(num != ""){
				var id = "li_"+num;
				$(id).style.background="#dce8f8";
			}else{
				if($("li_0")){
					$("li_0").style.background="#dce8f8";
				}
			}
		}
		</script> 	
	</head>
	<body onload="setLiClick('')" >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>����ר��</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/xtwhSysz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<a href="xtwhSysz.do?method=xzzqView" target="framecon" id="abq"></a>
			<input type="hidden" name="filess" id="filess" value="${filess }"/>
			<input type="hidden" id="pageRsSize" name="pageRsSize" value="${pagesInfo.pageSize }"/>
			<input type="hidden" id="currentPage" name="currentPage" value="${pagesInfo.currentPage }"/>
			<input type="hidden" id="maxPage" name="maxPage" value="${pagesInfo.maxPage }"/>
			<input type="hidden" id="maxRecord" name="maxRecord" value="${pagesInfo.maxRecord }"/>
			<input type="hidden" id="pageNum" name="pageNum" value="1"/>

			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="returnHomPage('');return false;"
								class="btn_fh">����</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th width="10%">
			              			�ļ���
			              		</th>
			         			<td>
			         				<input type="text" name="searchModel.input_mhcx" id="input_mhcx" value="${searchTj.input_mhcx }" 
										style="width: 60%" onkeypress="if(pressEnter(event)){searchRs();return false;}"
										onfocus="displayMsgDiv('input_filemc_msg')" 
										onblur="hideMsgDiv('input_filemc_msg')"/>
										
									<div id="input_filemc_msg" class="hide" style="left: 90px;top: 80px;">
										<div class="prompcon" style="width: 250px">
											<p>����¼�����ļ������԰�ǿո�����</p>	
										</div>
									</div>
									
									<input type="hidden" name="go" value="a"/>
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
										�� ѯ
									</button>
			         			</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end-->
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<table align="center" width="100%">
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox">
								<input type="radio" style="cursor: hand" name="left_lx"
									onfocus="this.blur();" checked="checked"
									onclick="chlckLeftLx(this.value);" value="filess"/>�ļ�����									
								<input type="radio" style="cursor: hand" name="left_lx"
									onfocus="this.blur();"
									onclick="chlckLeftLx(this.value);" value="filelx"/>�ļ�����
									
								<div class="titlelist" style="height: 400px;">
									<ul id="left_ul">
										<%int count = 0; %>
										<logic:iterate id="ss" name="sslxList" indexId="index">
											<li id="li_${index}" class="Child">
												<logic:equal name="index" value="0">
													<a href="#" name="left_a" id="left_a_${index}" onclick="setLiClick('');clickLeftTj('');return false;" style=""><span class="ico"></span>ȫ��</a>
												</logic:equal>
												<logic:notEqual name="index" value="0">
													<a href="#" name="left_a" id="left_a_${index}" onclick="setLiClick('${index}');clickLeftTj('${ss.dm }');return false;" style=""><span class="ico"></span>${ss.mc}</a>
												</logic:notEqual>
											</li>
											<% count++; %>
										</logic:iterate>
									</ul>
									<input type="hidden" id="ssdm" name="ssdm" value=""/>
									<input type="hidden" id="lxdm" name="lxdm" value=""/>
									<input type="hidden" id="li_num" name="li_num" value="<%=count %>"/>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="80%" valign="top" style="position: relative;">
							<div id="dy_area">
								<span class="formbox">
									<table class="dateline" width="100%" id="showDiary">
										<!-- ���� -->
								    	<thead>
											<tr>
												<logic:iterate id="tit" name="topTr" offset="2" length="1">
													<td width="30%">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												<logic:iterate id="tit" name="topTr" offset="3">
													<td width="20%">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												<td width="20%">
													����
												</td>
								      		</tr>
										</thead>
										<!-- ���� end-->
										<!-- ���� -->
										<logic:equal name="hadRs" value="yes">
								    	<tbody>
									    	<logic:iterate name="rsArrList" id="rs" indexId="index">
									    		<tr onclick="rowOnClick(this);">
									    			<!-- �ļ����� -->
									    			<logic:iterate id="v" name="rs" offset="2" length="1">
														<td align="left"
															title="<logic:iterate id="sm" name="rs" offset="1" length="1">${sm }</logic:iterate>">
															${v }
														</td>
													</logic:iterate>
									    			<logic:iterate id="v" name="rs" offset="3">
														<td align="left">
															${v }
														</td>
													</logic:iterate>
													<!-- ���ز��� -->
													<logic:iterate id="v" name="rs" offset="0" length="1">
														<td width="20%">
															<logic:notEmpty name="v">
																<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${v }" target="_blank" style="color: blue">���ظ���</a>
															</logic:notEmpty>
														</td>
													</logic:iterate>	
												</tr>
									    	</logic:iterate>
										</tbody>
										</logic:equal>
										<!-- ������ -->
										<%@ include file="/comm/noRows.jsp"%>
										<!-- ������ end-->
									</table>
									<!--��ҳ��ʾ-->
									<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xtwhSyszForm"></jsp:include>
									<script type="text/javascript">
										$('choose').className="hide";
									</script>
									<!--��ҳ��ʾ-->
								</span>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<!-- ������ʾ����ʼ end-->
			
			
<%--			<div class="toolbox" style="float:right;width: <%=width%>%">--%>
<%--			--%>
<%--				<!-- ��ť -->--%>
<%--				<div class="buttonbox">--%>
<%--					<ul>--%>
<%--						<li>--%>
<%--							<a href="#"--%>
<%--								onclick="returnHomPage('');return false;"--%>
<%--								class="btn_fh">����</a>--%>
<%--						</li>--%>
<%--					</ul>--%>
<%--				</div>--%>
<%--				<!-- ��ť end-->	--%>
<%--				--%>
<%--				<!-- �������� -->--%>
<%--				<div class="searchtab">--%>
<%--					<table width="100%" border="0">--%>
<%--						<tfoot>--%>
<%--							<tr>--%>
<%--								<td colspan="10">--%>
<%--									<div class="btn">--%>
<%--										<input type="hidden" name="go" value="a"/>--%>
<%--										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">--%>
<%--											�� ѯ--%>
<%--										</button>--%>
<%--										<button type="button" class="btn_cz" id="btn_cz" --%>
<%--											onclick="czSearchCond('filelx-filemc');">--%>
<%--											�� ��--%>
<%--										</button>--%>
<%--									</div>--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</tfoot>--%>
<%--						<tbody>--%>
<%--							<!-- ��һ�� -->--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									�ļ�����--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:select property="filelx" style="" styleId="filelx">--%>
<%--										<html:options collection="filelxList" property="dm" labelProperty="mc" />--%>
<%--									</html:select>								--%>
<%--								</td>--%>
<%--								<th>--%>
<%--									�ļ���--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:text property="filemc" style="" maxlength="20" styleId="filemc"--%>
<%--										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>--%>
<%--								</td>--%>
<%--								<th>--%>
<%--								--%>
<%--								</th>--%>
<%--								<td>--%>
<%----%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</tbody>--%>
<%--					</table>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--			<!-- �������� end-->--%>
<%--			--%>
<%--			<!-- �����-->--%>
<%--			<div class="menulist">--%>
<%--				<div class="menutitle">--%>
<%--    				<h3>--%>
<%--    					<span class="title">�ļ�����</span>	--%>
<%--      				</h3>--%>
<%--					<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px">--%>
<%--						<ul>--%>
<%--							<logic:iterate id="ss" name="sslxList" indexId="index">--%>
<%--								<li id="li${ss.dm}">--%>
<%--									<span>--%>
<%--										<logic:equal name="index" value="0">--%>
<%--											<a href="#" onclick="clickSs('');return false;" style="">ȫ��</a>--%>
<%--										</logic:equal>--%>
<%--										<logic:notEqual name="index" value="0">--%>
<%--											<a href="#" onclick="clickSs('${ss.dm }');return false;" style="">${ss.mc}</a>--%>
<%--										</logic:notEqual>--%>
<%--									</span>--%>
<%--								</li>--%>
<%--							</logic:iterate>--%>
<%--						</ul>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--			<!-- ����� end-->--%>
<%--			--%>
<%--			<div class="formbox"  style="float:right;width: <%=width%>%">--%>
<%--				<h3 class="datetitle_01">--%>
<%--			    <span>--%>
<%--			    	��ʾ&nbsp;&nbsp;--%>
<%--			    	<logic:empty name="rsArrList">--%>
<%--						<font color="red">δ�ҵ��κμ�¼��</font> --%>
<%--			 		</logic:empty>--%>
<%--			 		<logic:notEmpty name="rsArrList">--%>
<%--			 		 	<font color ="blue">��������ļ����ƴ��ɲ鿴�ļ�˵��</font>--%>
<%--			 		</logic:notEmpty>--%>
<%--			    </span>--%>
<%--			    </h3>--%>
<%--				<!-- ����� -->--%>
<%--				<table summary="" class="dateline tablenowrap" width="100%">--%>
<%--					<!-- ���� -->--%>
<%--			    	<thead>--%>
<%--						<tr>--%>
<%--							<logic:iterate id="tit" name="topTr" offset="2">--%>
<%--								<td>--%>
<%--									<bean:write name="tit" property="cn" />--%>
<%--								</td>--%>
<%--							</logic:iterate>--%>
<%--							<td>--%>
<%--								����--%>
<%--							</td>--%>
<%--			      		</tr>--%>
<%--					</thead>--%>
<%--					<!-- ���� -->--%>
<%--					<logic:equal name="hadRs" value="yes">--%>
<%--			    	<tbody>--%>
<%--				    	<logic:iterate name="rsArrList" id="rs" indexId="index">--%>
<%--				    		<tr onclick="rowOnClick(this);">--%>
<%--				    			<!-- �ļ����� -->--%>
<%--				    			<logic:iterate id="v" name="rs" offset="2" length="1">--%>
<%--									<td align="left" title="<logic:iterate id="sm" name="rs" offset="1" length="1">${sm }</logic:iterate>">--%>
<%--										${v }--%>
<%--									</td>--%>
<%--								</logic:iterate>--%>
<%--				    			<logic:iterate id="v" name="rs" offset="3">--%>
<%--									<td align="left">--%>
<%--										${v }--%>
<%--									</td>--%>
<%--								</logic:iterate>--%>
<%--								<!-- ���ز��� -->--%>
<%--								<logic:iterate id="v" name="rs" offset="0" length="1">--%>
<%--									<td>--%>
<%--										<logic:notEmpty name="v">--%>
<%--											<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${v }" target="_blank" style="color: blue">���ظ���</a>--%>
<%--										</logic:notEmpty>--%>
<%--									</td>--%>
<%--								</logic:iterate>	--%>
<%--							</tr>--%>
<%--				    	</logic:iterate>--%>
<%--					</tbody>--%>
<%--					</logic:equal>--%>
<%--					<!-- ������ -->--%>
<%--					<%@ include file="/comm/noRows.jsp"%>--%>
<%--					<!-- ������ end-->--%>
<%--				</table>--%>
<%--				<!--��ҳ��ʾ-->--%>
<%--				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xtwhSyszForm"></jsp:include>--%>
<%--				<script type="text/javascript">--%>
<%--					$('choose').className="hide";--%>
<%--				</script>--%>
<%--				<!--��ҳ��ʾ-->--%>
<%--			</div>--%>
			<!-- �߼���ѯ -->
			<%@ include file="/comm/search/searchInfo.jsp"%>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>