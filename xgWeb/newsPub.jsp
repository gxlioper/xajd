<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
//		function chgEditer(obj){
//			var url = "eWebEditor/eWebEditor.jsp?color=" + obj.value;
//			eWebEditor1.location = url;
//		}
function pubNews(){
	if(document.getElementById("newsTitle").value.replace(/(\s*)/g, "") == ""){
		alert("����д���ű��⣡");
			document.getElementById("newsTitle").focus();
		return false;
	}
	if(document.getElementById("mxdx").value == ""){
		alert("��ѡ������ģ�飡");
			document.getElementById("mxdx").focus();
		return false;
	}	
	if(FCKeditorAPI.GetInstance("content1").EditorDocument.body.innerText == ""){
		alert("����д�������ģ�");
		return false;
	}
	refreshForm('saveNews.do');
}

	function cha(url){
			refreshForm(url);
		}
	</script>
	</head>
	<body>
		<!-- �������� ������ת -->
		<logic:equal value="11417" name="xxdm">
			<jsp:forward page="bjlh_newsPub.jsp"></jsp:forward>
		</logic:equal>



		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������</a>
			</p>
		</div>
		<html:form method="post" action="/newsPub.do">
			<div style="display:none">
				<input type="text"
					onclick="refreshForm('newsPub.do?tagId='+tagId.value)"
					id="search_go" />
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> </span>
				</h3>


				<logic:notEqual name="showbjlh" value="showbjlh">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td onclick="tableSort(this)" width="20px">
									���ű���
								</td>
								<td width="100" onclick="tableSort(this)">
									ģ�����
								</td>
								<td width="80" onclick="tableSort(this)">
									����
								</td>
								<%--��ɳ����--%>
								<logic:equal value="10827" name="xxdm">
									<td width="140" onclick="tableSort(this)">
										��������
									</td>
								</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
									<td width="140" onclick="tableSort(this)">
										����ʱ��
									</td>
								</logic:notEqual>
								<td width="80" onclick="tableSort(this)">
									������
								</td>
								<logic:present name="showNewsType">
									<td width="80" onclick="tableSort(this)">
										����
									</td>
								</logic:present>
								<td width="80" onclick="tableSort(this)">
									����
								</td>
							</tr>
						</thead>
						<tbody class="breakword">
							<logic:present name="rs">
								<logic:notEmpty name="rs">
									<logic:iterate id="list" name="rs">
										<tr onclick="rowOnClick(this)" style="cursor:hand;">
											<td>
												<!-- 2010/4/26 luojw -->
												<a
													href="newsContent.do?newsId=<logic:iterate id='v' name='list' offset='0' length='1'>${v}</logic:iterate>"
													target="_blank"> <logic:iterate id='v' name='list'
														offset='1' length='1'>${v}</logic:iterate> </a>
											</td>
											<logic:iterate id="v" name="list" offset="2" length="1">
												<td align="center">
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<logic:present name="showzgdzdx">
												<td align="center">
													<logic:iterate id='v' name='list' offset='3' length='1'>
														<logic:equal value="1" name="v">
											ȫ��
										</logic:equal>
														<logic:equal value="2" name="v">
											ѧУ
										</logic:equal>
														<logic:equal value="3" name="v">
															<bean:message key="lable.xsgzyxpzxy" />
														</logic:equal>
														<logic:equal value="4" name="v">
											ѧ��
										</logic:equal>
														<logic:equal value="5" name="v">
											���˵�λ
										</logic:equal>
														<logic:equal value="" name="v">
											ȫ��
										</logic:equal>
													</logic:iterate>
												</td>
												<logic:iterate id="v" name="list" offset="4" length="2">
													<td align="center">
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<td align="center">
													<a href="#"
														onclick="location='newsPub.do?doType=update&newsId=<logic:iterate id='v' name='list' offset='0' length='1'>${v}</logic:iterate>&tagId='+tagId.value;">�޸�</a>/
													<a href="#"
														onclick="if(confirm('ȷʵҪɾ����ǰ������'))location='saveNews.do?act=del&newsId=<logic:iterate id='v' name='list' offset='0' length='1'>${v}</logic:iterate>&tagId='+tagId.value;">ɾ��</a>
												</td>
											</logic:present>
											<logic:notPresent name="showzgdzdx">

												<td align="center">
													<logic:iterate id='v' name='list' offset='3' length='1'>
														<logic:equal value="1" name="v">
											ȫ��
										</logic:equal>
														<logic:equal value="2" name="v">
											��ʦ
										</logic:equal>
														<logic:equal value="3" name="v">
											ѧ��
										</logic:equal>
														<logic:equal value="" name="v">
											ȫ��
										</logic:equal>
													</logic:iterate>
												</td>
												<logic:iterate id="v" name="list" offset="4">
													<td align="center">
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<td align="center">
													<a href="#"
														onclick="location='newsPub.do?doType=update&newsId=<logic:iterate id='v' name='list' offset='0' length='1'>${v}</logic:iterate>&tagId='+tagId.value;">�޸�</a>/
													<a href="#"
														onclick="if(confirm('ȷʵҪɾ����ǰ������'))location='saveNews.do?act=del&newsId=<logic:iterate id='v' name='list' offset='0' length='1'>${v}</logic:iterate>&tagId='+tagId.value;">ɾ��</a>
												</td>
											</logic:notPresent>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</logic:present>

						</tbody>
					</table>
					<logic:notEmpty name="rs">
						<!--��ҳ��ʾ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						<script type="text/javascript">
								$('choose').className="hide";
							</script>
						<!--��ҳ��ʾ-->
					</logic:notEmpty>
				</logic:notEqual>
			</div>


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button name="�ύ" onclick="pubNews();return false;">
										�� ��
									</button>
									<button name="����" type="reset">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								����ģ��
							</th>
							<td width="84%">
								<logic:equal name="showbjlh" value="showbjlh">
									<html:select name="commanForm" property="xmdm" styleId="mxdx"
										onchange="cha('newsPub.do?tagId='+this.value);">
										<html:options collection="modList" property="gnmkdm"
											labelProperty="gnmkmc" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="showbjlh" value="showbjlh">
									<html:select name="commanForm" property="xmdm" styleId="mxdx"
										disabled="true" onchange="tagId.value=this.value">
										<html:options collection="modList" property="gnmkdm"
											labelProperty="gnmkmc" />
									</html:select>
								</logic:notEqual>

								<logic:present name="showNewsType">
							 &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;���ͣ�
							 <logic:equal name="update" value="update">
										<html:select name="map" property="newsType" styleId="newsType">
											<html:options collection="NewsTypeList" property="en"
												labelProperty="cn" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="update" value="update">
										<html:select name="commanForm" property="newsType"
											styleId="newsType">
											<html:options collection="NewsTypeList" property="en"
												labelProperty="cn" />
										</html:select>
									</logic:notEqual>
								</logic:present>


								<html:hidden name="commanForm" property="xmdm" />
								<input type="hidden" name="tagId" id="tagId"
									value="<bean:write name="commanForm" property="xmdm" />" />
								<input type="hidden" name="isModi" id="isModi"
									value="<bean:write name="isModi" />" />
								<input type="hidden" name="newsId" id="newsId"
									value="<bean:write name="newsId" />" />
								<logic:present name="showshgc">

									<logic:equal name="update" value="update">
							    &nbsp;&nbsp;&nbsp;  
							    
							    &nbsp;&nbsp;&nbsp;
						<html:radio name="map" property="towho" value="1">&nbsp;ȫ��ɼ�</html:radio>
						&nbsp;&nbsp;
						<html:radio name="map" property="towho" value="2">&nbsp;��ʦ�ɼ�</html:radio>
					    &nbsp;&nbsp;
						<html:radio name="map" property="towho" value="3">&nbsp;ѧ���ɼ�</html:radio>

									</logic:equal>
									<logic:notEqual name="update" value="update">							   
							   &nbsp;&nbsp;&nbsp;     
        	<input type="radio" name="towho" value="1" checked="true" />&nbsp;ȫ��ɼ�
        	&nbsp;&nbsp;&nbsp; 
        	<input type="radio" name="towho" value="2" />&nbsp;��ʦ�ɼ�
        	&nbsp;&nbsp;&nbsp; 
        	<input type="radio" name="towho" value="3" />&nbsp;ѧ���ɼ�   							   
							   </logic:notEqual>
								</logic:present>

								<logic:present name="showzgdzdx">

									<logic:equal name="update" value="update">
							    &nbsp;&nbsp;&nbsp;  
							    
							    &nbsp;&nbsp;&nbsp;
						<html:radio name="map" property="towho" value="1">&nbsp;ȫ��ɼ�</html:radio>
						&nbsp;&nbsp;
						<html:radio name="map" property="towho" value="2">&nbsp;ѧУ�ɼ�</html:radio>
					    &nbsp;&nbsp;
						<html:radio name="map" property="towho" value="3">&nbsp;<bean:message
												key="lable.xsgzyxpzxy" />�ɼ�</html:radio>
						&nbsp;&nbsp;
						<html:radio name="map" property="towho" value="4">&nbsp;ѧ���ɼ�</html:radio>
										<logic:equal value="N06" name="commanForm" property="xmdm">
						&nbsp;&nbsp;
						<html:radio name="map" property="towho" value="5">&nbsp;���˵�λ�ɼ�</html:radio>
										</logic:equal>

									</logic:equal>
									<logic:notEqual name="update" value="update">							   
							   &nbsp;&nbsp;&nbsp;     
        	<input type="radio" name="towho" value="1" checked="true" />&nbsp;ȫ��ɼ�
        	&nbsp;&nbsp;&nbsp; 
        	<input type="radio" name="towho" value="2" />&nbsp;ѧУ�ɼ�
        	&nbsp;&nbsp;&nbsp; 
        	<input type="radio" name="towho" value="3" />&nbsp;<bean:message
											key="lable.xsgzyxpzxy" />�ɼ�
        	&nbsp;&nbsp;&nbsp; 
        	<input type="radio" name="towho" value="4" />&nbsp;ѧ���ɼ�
<%--        	<html:text property="xmdm" name="commanForm"></html:text>--%>
										<logic:equal value="N06" name="commanForm" property="xmdm">
        	&nbsp;&nbsp;&nbsp; 
        	<input type="radio" name="towho" value="5" />&nbsp;���˵�λ�ɼ�   
        	</logic:equal>
									</logic:notEqual>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���ű���
							</th>
							<td>
								<input type="text" name="newsTitle" id="newsTitle"
									style="width:100%" value="<bean:write name="newstit"/>"
									maxlength="100" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�༭����
							</th>
							<td>
								<%--
								<logic:equal name="normal" value="normal">
									<input type="hidden" name="content1" value="" />
								</logic:equal>

								<logic:equal name="update" value="update">
									<input type="hidden" name="content1"
										value="<bean:write name="content1" />" />
								</logic:equal>

								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
									scrolling="no" width="100%" height="350"></iframe>				
								--%>
								<FCK:editor instanceName="content1" toolbarSet="Default" inputName="content1"
									width="100%" height="400px" >
									<jsp:attribute name="value">
										<logic:equal name="update" value="update">
										<bean:write name="content1" filter="false"/>
										</logic:equal>
									</jsp:attribute>
								</FCK:editor>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert("${message}");
			</script>
		</logic:present>
	</body>
</html>
