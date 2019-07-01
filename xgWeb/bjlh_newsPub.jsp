<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
			function pubNews(){
				if(jQuery('#newsTitle').val().trim() == ''){
					alert("����д���ű��⣡");
					jQuery("#newsTitle").focus();
					return false;
				}
				
				//jQuery('#content1').val(frames('eWebEditor1').getHTML());
				
				//if(jQuery("#content1").val() == ''){
				//	alert("����д�������ģ�");
				//	return false;
				//}
				if(FCKeditorAPI.GetInstance("content1").EditorDocument.body.innerText == ""){
					alert("����д�������ģ�");
					return false;
				}
				refreshForm('saveNews.do');
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������</a>
			</p>
		</div>
		<html:form method="post" action="/newsPub.do">
			<logic:notEmpty name="rs">
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> �����б�</span>
					</h3>
					<table class="dateline" width="100%">
						<thead>
							<tr>
								<td width="20px">
									���ű���
								</td>
								<td>
									ģ�����
								</td>
								<td>
									����
								</td>
								<td>
									������
								</td>
								<td width="80">
									����
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:present name="rs">
							<logic:notEmpty name="rs">
							<logic:iterate id="list" name="rs">
								<tr onclick="rowOnClick(this)" style="cursor:hand;">
									<td>
										<a href="newsContent.do?newsId=<logic:iterate id='v' name='list' offset='0' length='1'>${v}</logic:iterate>"
										   target="_blank"> 
										   <logic:iterate id='v' name='list' offset='1' length='1'>${v}</logic:iterate>
										</a>
									</td>
									<td>
										<logic:iterate id="v" name="list" offset="2" length="1">
											${v }
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id='v' name='list' offset='3' length='1'>
											<logic:equal value="1" name="v">ȫ��</logic:equal>
											<logic:equal value="2" name="v">��ʦ</logic:equal>
											<logic:equal value="3" name="v">ѧ��</logic:equal>
											<logic:equal value="4" name="v">ȫԺ</logic:equal>
											<logic:equal value="5" name="v">ȫԺ��ʦ</logic:equal>
											<logic:equal value="6" name="v">ȫԺѧ��</logic:equal>
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="list" offset="5" length="1">
										<td>
											${v }
										</td>
									</logic:iterate>
									<td>
										<a href="#"
											onclick="location='newsPub.do?doType=update&newsId=<logic:iterate id='v' name='list' offset='0' length='1'>${v}</logic:iterate>&tagId='+tagId.value;">�޸�</a>/
										<a href="#"
											onclick="if(confirm('ȷʵҪɾ����ǰ������'))location='saveNews.do?act=del&newsId=<logic:iterate id='v' name='list' offset='0' length='1'>${v}</logic:iterate>&tagId='+tagId.value;">ɾ��</a>
									</td>
								</tr>
							</logic:iterate>
							</logic:notEmpty>
							</logic:present>
						</tbody>
					</table>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
					<!--��ҳ��ʾ-->
				</div>
			</logic:notEmpty>


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
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
							<th width="15%">
								����ģ��
							</th>
							<td width="83%">
								<html:hidden name="commanForm" property="xmdm" />
								<input type="hidden" name="tagId"  id="tagId"  value="${commanForm.xmdm }" />
								<input type="hidden" name="isModi" id="isModi" value="${isModi }" />
								<input type="hidden" name="newsId" id="newsId" value="${newsId }" />
							
								<html:select name="commanForm" 
											 property="xmdm" 
											 styleId="mxdx" 
											 disabled="true">
									<html:options collection="modList" property="gnmkdm" labelProperty="gnmkmc" />
								</html:select>
								
								<logic:notEqual value="xy" name="userType">
									<input type="radio" name="towho" value="1" checked="true"/>ȫ��
									<input type="radio" name="towho" value="2"/>��ʦ
									<input type="radio" name="towho" value="3"/>ѧ��
								</logic:notEqual>
								
								<logic:equal value="xy" name="userType">
									<input type="radio" name="towho" value="4" checked="true"/>ȫԺ
									<input type="radio" name="towho" value="5"/>ȫԺ��ʦ
									<input type="radio" name="towho" value="6"/>ȫԺѧ��
								</logic:equal>
								
								<script defer>
									jQuery('input[name="towho"][value=${map.towho}]').attr('checked',true);
								</script>
							</td>
						</tr>
						<tr>
							<th>���ʷ�ʽ</th>
							<td>
								<input type="radio" name="fwfs" value="1"/>�����ɼ�
								<input type="radio" name="fwfs" value="2"/>�����ɼ�
								<input type="radio" name="fwfs" value="3" checked/>�������ɼ�
								<script defer>
									jQuery('input[name=fwfs][value=${map.fwfs}]').attr('checked',true);
								</script>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���ű���
							</th>
							<td>
								<input  type="text" name="newsTitle" 
										id="newsTitle"
										style="width:98%" value="${newstit }"
										maxlength="100" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�༭����
							</th>
							<td>
<%--								<input type="hidden" name="content1" value="<logic:present name="content1"><bean:write name="content1"/></logic:present>" id="content1"/>--%>
<%--								<iframe id="eWebEditor1" --%>
<%--										src="BatEditor" --%>
<%--										frameborder="0"--%>
<%--										scrolling="no" --%>
<%--										width="98%" height="350"></iframe>--%>
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
			<logic:present name="message">
				<script>
					alert('${message}')
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
