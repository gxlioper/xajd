<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.jcsj.xsxxwh.XsxxwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//���Ŵ���div���� ���ӡ��޸�
		var doType;
		function ljsdaUpdate(url,w,h){	
			var pk="";	
			if(curr_row == null ){
					alert("��ѡ��һ�м�¼��\n����һ�м���!");
					return false;
			} 	
					
			pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
			url+=pk;
			//�ж��Ƿ���У��
			//url+="&sfzxk="+$("sfzxk").value;
			if(w==""||w==null){
				w = 800;
			}
			if(h==""||h==null){
				h = 600;
			}
			showTopWin(url,w,h);		
		}
			
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('jcsj_xsxxwh_xsxxwh.do');
			}
			

			//ɾ��
			function delXsxx(doType){
				var xsxx;
				if(curr_row != null){
					xh=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				confirmInfo("ȷ��ɾ����",function(data){
					if("ok"==data){
						allNotEmpThenGo('jcsj_xsxxwh_xsxxwh.do?doType=del&xh='+xh);
					}
				});
			}

			//����쳣����
			function checkExcData(){
				allNotEmpThenGo('jcsj_xsxxwh_xsxxwh.do?doType=checkExcData');
			}

			function newChgCode(obj){
				allNotEmpThenGo(obj.id+".do");
			}

			//�ı�רҵ������
			function changeZySelect(bmdm_id,zydm_id){
				jQuery.getJSON('jcsj_xsxxwh.do?method=getZydmList',{bmdm:jQuery('#'+bmdm_id).val()},function(data){
					jQuery('#'+zydm_id).empty();
					jQuery('#'+zydm_id).append("<option value=''>--��ѡ��--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
							jQuery('#'+zydm_id).append(option);
						}
					}
					jQuery('#bjdm').empty();
				});
			}

			//�ı�༶������
			function changeBjSelect(zydm_id,bjdm_id){
				
				jQuery.getJSON('jcsj_xsxxwh.do?method=getBjdmList',{zydm:jQuery('#'+zydm_id).val()},function(data){
					jQuery('#'+bjdm_id).empty();
					jQuery('#'+bjdm_id).append("<option value=''>--��ѡ��--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
							jQuery('#'+bjdm_id).append(option);
						}
					}
				});
			}
		</script>
	</head>
	<body onload="">
		<html:form action="/jcsj_xsxxwh" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="refurbish" id="refurbish" value=""/>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<!-- ��ʾ��Ϣ end-->	
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
<%--						<logic:equal value="yes" name="writeAble">--%>
						<li><a href="#" onclick="$('refurbish').value='yes';showTopWin('stu_info_add.do?method=showStuInfo&oper=add',800,600,false);return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="$('refurbish').value='yes';ljsdaUpdate('stu_info_add.do?method=showStuInfo&oper=update&xh=',800,600);return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="delXsxx();return false;" class="btn_sc">ɾ��</a></li>
						<li><a href="#" onclick="checkExcData();return false;" class="btn_cs">�쳣���</a></li>
<%--						</logic:equal>--%>
					</ul>
				</div>
				<!-- �������� start-->				
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>�꼶</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
								<th>����</th>
								<td>
									<html:select property="bmdm" styleId="bmdm" style="width:150px;" onchange="changeZySelect('bmdm','zydm')">
										<html:option value=""></html:option>
										<html:options collection="bmdmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</td>
								<th>רҵ</th>
								<td>
									<html:select property="zydm" styleId="zydm" style="width:150px;" onchange="changeBjSelect('zydm','bjdm')">
										<html:option value=""></html:option>
										<html:options collection="zydmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>�༶</th>
								<td>
									<html:select property="bjdm" styleId="bjdm" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="bjdmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>�Ƿ��쳣����</th>
								<td>
									<html:select property="query_sfycsj" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th>ѧ��(���������֤��)</th>
								<td>
									<html:text property="query_text"></html:text>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('jcsj_xsxxwh_xsxxwh.do');return false;">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>					
				</div>
				<!-- �������� end-->
			</div>
<%--				<logic:equal name="superSearch" value="yes">--%>
<%--			     	<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
<%--			    </logic:equal>--%>
			    
			<div class="compTab" id="card">
			<div class="comp_title"><ul>
								<li><a href="#" onclick="newChgCode(this)" id="jcsj_bmdmwh_bmdmwh"><span>���Ŵ���</span> </a>
								<li><a href="#" onclick="newChgCode(this)" id="jcsj_zydmwh_zydmwh"><span>רҵ����</span> </a>
								<li><a href="#" onclick="newChgCode(this)" id="jcsj_bjdmwh_bjdmwh"><span>�༶����</span> </a>
								<li class="ha"><a href="#" onclick="newChgCode(this)" id="jcsj_xsxxwh_xsxxwh"><span>ѧ����Ϣ</span> </a>
			</div>
			    
			    
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
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td style="display: none;">
									<input type="checkbox" name="pk" value="" disabled="disabled"></input>
								</td>
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
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										
									</td>
									<logic:iterate id="v" name="s" offset="1" length="9">
										<td><bean:write name="v" format="ture"/></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							XsxxwhForm form = (XsxxwhForm)request.getAttribute("jcsjXsxxwhForm");
							int pageSize = form.getPages().getPageSize();
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td style="display: none;">
									<input type="checkbox" value="" disabled="disabled"></input>
								</td>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jcsjXsxxwhForm"></jsp:include>
		   	 	<script type="text/javascript">
					$('choose').className="hide";
				</script>
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
