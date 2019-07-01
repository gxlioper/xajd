<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		
		function save(){
			if(trim($("xmmc").value)==""){
				alertInfo("��Ŀ���Ʋ���Ϊ�գ�");
				return false;
			}
			if(trim($("xmnr").value)==""){
				alertInfo("��Ŀ���ݲ���Ϊ�գ�");
				return false;
			}
			if(!$("xsfs").value){
				alertInfo("��ʾ��ʽ����Ϊ�գ�");
				return false;
			}
			if(!$("sfxs").value){
				alertInfo("�Ƿ���ʾ����Ϊ�գ�");
				return false;
			}
			refreshForm("/xgxt/xtwhSysz.do?method=xsxmUpdate&doType=save");
		}
		function update(){
			if(!$("xmmc").value){
				alertInfo("��Ŀ���Ʋ���Ϊ�գ�");
				return false;
			}
			if(!$("xmnr").value){
				alertInfo("��Ŀ���ݲ���Ϊ�գ�");
				return false;
			}
			if(!$("xsfs").value){
				alertInfo("��ʾ��ʽ����Ϊ�գ�");
				return false;
			}
			if(!$("sfxs").value){
				alertInfo("�Ƿ���ʾ����Ϊ�գ�");
				return false;
			}
			refreshForm("/xgxt/xtwhSysz.do?method=xsxmUpdate&doType=modi");
		}
	</script>
	</head>
	<body>

		<html:form action="/xtwhSysz" method="post">
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name="xxdm" />" />
			<%--<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title} </a>
				</p>
			</div>--%>
			<!-- ��ʾ��Ϣ begin-->
<%--			<div id="xxPrompt" class="prompt">--%>
<%--				<h3>--%>
<%--					<span>ϵͳ��ʾ��</span>--%>
<%--				</h3>--%>
<%--				<p>--%>
<%--					��"��������"��ʽ��ʾ����Ϣ������д��Ŀ������Ϣ<br/>ʱ��������д�������磺http://www.xuegong.com��<br/>--%>
<%--					�����޷��������ӡ�--%>
<%--				</p>--%>
<%--				<a class="close" title="����"--%>
<%--					onclick="this.parentNode.style.display='none';"></a>--%>
<%--			</div>--%>
			<!-- ��ʾ��Ϣ end-->
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>��Ŀ��ʾ����</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
							<logic:empty name="rs">
								<input type="text" name="xmdm" value="${xmdm }" disabled="true"/>
								<html:hidden property="save_xmdm" value="${xmdm }" />
							</logic:empty>
							<logic:notEmpty name="rs">
								<input type="text" name="xmdm" value="${rs.xmdm }" disabled="true"/>
								<html:hidden property="save_xmdm" value="${rs.xmdm }" />
							</td>
							</logic:notEmpty>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
								<html:text property="save_xmmc" styleId="xmmc" value="${rs.xmmc }"  maxlength="20" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td colspan="3">
								<html:text property="save_xmnr"  styleId="xmnr" value="${rs.xmnr }"  maxlength="50" />
							</td>
						</tr>
						<tr style="display:none;">
							<th align="right">
								<font color="red">*</font>��ʾ��ʽ
							</th>
							<td>
								<html:select property="save_xsfs" styleId="xsfs"
									style="width:100px" value="${rs.xsfs }">
									<html:options collection="xsfsList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�Ƿ���ʾ
							</th>
							<td>
								<html:select property="save_sfxs" styleId="sfxs"
									style="width:100px" value="${rs.sfxs }">
									<html:option value=""></html:option>
									<html:options collection="sfxsList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								��ʾ˳��
							</th>
							<td>
								<html:text property="save_xssx" value="${rs.xssx }" onkeyup="value=value.replace(/[^\d]/g,'') "  maxlength="2" />
							</td>
						</tr>



					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:equal name="doType" value="add">
									<button type="button" onclick="save();return false;">
										�� ��
									</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
									<button type="button" onclick="update();return false;">
										�� ��
									</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
					alertInfo('�����ɹ���',function(tag){
						if(tag=="ok"){
							if (parent.window){
		    					refershParent();
		    				}
							//window.close();
							//window.dialogArguments.document.getElementById('search_go').click();
					
						}
					});
				
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alertInfo('����ʧ�ܣ�',function(tag){
						if(tag=="ok"){
							if (parent.window){
		    					refershParent();
		    				}
							//window.close();
							//window.dialogArguments.document.getElementById('search_go').click();
					
						}
					});
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
