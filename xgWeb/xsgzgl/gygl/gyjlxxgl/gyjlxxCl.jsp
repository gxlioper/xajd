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
			allNotEmpThenGo('gyglnew_gyjlgl_gyjlcl.do');
		}
		function view(url,h,w){
			var xh = curr_row.getElementsByTagName('input')[0].value;
			var wjsj = curr_row.getElementsByTagName('input')[1].value;
			showTopWin(url + '&xh='+xh+'&wjsj='+wjsj,h,w);
		}

		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		//����
		function gyjlcl(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var xh=jQuery("[name=div_pkValue]:checked").val();
				
				var jlsj=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();

				var url="gyglnew_gyjlgl_gyjlclwh.do";
				
				url+="?xh="+xh+"&jlsj="+jlsj;
				 
				showTopWin(url,800,600);
			} else if (len > 1) {
				tipsWindown("","id:shDiv","450","250","true","","true","id");
				sfclCh();
			} else{
				
				alertInfo("�빴ѡһ����Ҫ����ļ�¼��");
				
				return false;
			}
		}

		
		function sfclCh(){
			var obj=$("sfcl");
			if(obj.value=='2'){
				jQuery("#cljgtsRs").css("display","");
				//jQuery("#cljgtsRs").css("display","");
			}else{
				jQuery("#cljgtsRs").css("display","none");
				//jQuery("#cljgtsRs").css("display","none");
			}
		}

		//����
		function saveShzt(){
			var obj=$("sfcl");
			if (obj.value=='2') {
				if (jQuery("#dcqk").val()==null ||jQuery("#dcqk").val()=='') {
					alertInfo("����д���������",function(){return false;});
					return false;
				}
			}
				confirmInfo("ȷ��Ҫ�����ѹ�ѡ�ļ�¼��?",function(tag){
	
					if(tag=="ok"){
						var array = document.getElementsByName("div_pkValue");
						var pk = "";
						for (var i=0;i<array.length;i++) {
							if (array[i].checked) {
								pk+= array[i].value;
								pk+=jQuery(array[i]).parent().find("input[name='wjsj']").val();
								pk+="!!@@";
							}
						}
						jQuery('#pkStr').val(pk);
						allNotEmpThenGo('gyglnew_gyjlgl_gyjlcl.do?doType=cl');
					}else {
						return false;
					}
				});
			}

		</script>
	</head>
	<body>
		<html:form action="/gyglnew_gyjlgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>
			<input type="hidden" id="num" value="<bean:write name="num"/>"/>
			<input type="hidden" id="pkStr" name="pkStr" />
			<input type="hidden" id="xxdm" name="xxdm" value='${xxdm}'/>
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
						<li><a href="#" onclick="gyjlcl();return false;" class="btn_sh"> ���� </a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<%@ include file="/xsgzgl/gygl/gyjlxxgl/gyglGjcx.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox" >
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫����¼�ɲ鿴;</font>
						</span>
					</h3>
					<div class="con_overlfow">
					<%@ include file="/comm/loading.jsp"%>
					<table width="99%" id="rsTable" class="dateline" >
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" >
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
								<logic:iterate id="tit" name="topTr" offset="0">
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
									<logic:iterate id="v" name="s" offset="2" length="1">
										<td align="center" width="5px">
											<input type="checkbox" id="pk_${index }"
											name="div_pkValue" value="${v }" <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>/>
											<input type="hidden" id="wjsj" name="wjsj"
											value="<logic:iterate id="v" name="s" offset="6" length="1">${v }</logic:iterate>"/>
											<input type="hidden" id="xh" name="xh"
											value="${v }"
											/>
										</td>
									</logic:iterate>
									<!-- ��ʾ��Ϣ -->
									<logic:iterate id="v" name="s" offset="2">
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
								<logic:iterate id="tit" name="topTr" offset="0">
									<td nowrap="nowrap">
										&nbsp;
									</td>
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
			</div>
			<div id="shDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
										<logic:equal value="11799" name="xxdm">
											��������Ԣ������Ϣ
										</logic:equal>
										<logic:notEqual value="11799" name="xxdm">
											��������Ԣ������Ϣ
										</logic:notEqual>										
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
								<th width="30%">
									<font color="red">*</font>�Ƿ���
								</th>
								<td width="70%">
									<html:select styleId="sfcl" property="sfcl" onchange="sfclCh()" style="width:150px" >
										<html:option value="2">�Ѵ���</html:option>
										<html:option value="3">������</html:option>
									</html:select>
								</td>
								
								</tr>
								<tr id="cljgtsRs" style="display: none;">
								<th width="30%">
									������
								</th>
								<td width="70%">
									
										<html:select property="cljg" style="width:150px" styleId="cljg">
											<html:options collection="cflbList" property="gyjlcfdm"
												labelProperty="gyjlcfmc" />
										</html:select>
									
								</td>
								</tr>
								<logic:equal value="13033" name="xxdm">
								<tr>
									<th width="20%">
										�⳥���
									</th>
									<td align="left" width="30%" colspan="3">
										<html:text property="ylzd1" styleId="ylzd1" style="width:150px" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"></html:text>&nbsp;&nbsp;Ԫ&nbsp;&nbsp;
									</td>
								</tr>
								</logic:equal>
							<tr>
								<th width="30%">
									�������<br/>(<font color="blue">��¼��500��</font>)
								</th>
								<td width="70%" >
									<html:textarea property="dcqk" rows="4" styleId="dcqk" style="word-break:break-all;width:97%" onblur="chLeng(this,500);">
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
										<button type="button" name="ȷ��" onclick="saveShzt();return false;">
											����
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
	</body>
</html>
