<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/wjsc.js"></script>
		<script language="javascript">
		function initJd(){
			if($("jd")){
				$("jd").focus();
			}
		}
		</script>
	</head>
	<body  onload="initPage();viewyh('first','');initJd()">
		<logic:equal value="ok" name="inserted">
				<script type="text/javascript">
					alert('�����ɹ�!');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script type="text/javascript">
					alert('${message}!');
				</script>
			</logic:equal>
			<logic:equal value="reinsert" name="inserted">
				<script type="text/javascript">
					alert('�ļ��ϴ�ʧ�ܣ������ļ����Ƿ��ظ�');
				</script>
			</logic:equal>
			<logic:equal name="alert" value="cannot">
				<script type="text/javascript">
					alert('�ļ��ϴ�ʧ�ܣ��ļ���СӦ������10M����!');
				</script>
			</logic:equal>
	
	
		<html:form action="/wjffManage" method="post"
			enctype="multipart/form-data">
				<input type="hidden" value="" id="yczdm"/>
				<input type="hidden" value="" id="yhms" name="yhms"/>
				<input type="hidden" value="" id="zdms" name="zdms"/>
				<input type="hidden" value="" id="zmcs" name="zmcs"/>
				<input type="hidden" id="pk" name="pkValue" value="${pkValue }" />

				<div class="tab_cur" id="jd">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>�ļ�����-����-�ļ�����</a>
					</p>
				</div>


				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					<thead>
				    	<tr>
				        	<th colspan="3"><span>�ļ�����</span></th>
				        </tr>
			   		</thead>
			   		 <tfoot>
				      <tr>
				        <td colspan="3"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				          	<input type="hidden" name="act" value="save" />
				            <button id="buttonSave" onclick="getyhs();if(IsNoEmpty('wjh-wjm-wjscsm-yhms-kk')){showTips('���ݱ����У����Ժ�...');refreshForm('fileSave.do')};">����</button>
				            <button onclick="reset();"> ����</button>
				          </div>
				          </td>
				      </tr>
				    </tfoot>
			   		
					<tr>
						<th width="10%" align="center">
							<font color="red">*</font>�ļ���:
						</th>
						<td colspan="2">
							<input type="text" name="wjh" id="wjh" size="50" maxlength="20" value="${rs.wjh }"/>
							<font color="red">(�����������20��������)</font>
						</td>
					</tr>
					<tr>
						<th align="center">
							<font color="red">*</font>�ļ���:
						</th>
						<td colspan="2">
							<input type="text" name="wjm" id="wjm" size="50" maxlength="50" value="${rs.wjm }"/>
							<font color="red">(�����������50��������)</font>
						</td>
					</tr>
					<tr>
						<th align="center" valign="top">
							<font color="red">*</font>�ļ�˵��:
							<p>
								<font color="red">(��800��)</font>
							</p>
						</th>
						<td colspan="2">
							<textarea rows="10"  name="wjscsm" id="wjscsm"
								onblur="chLeng(this,800)" style="width:95%"
								type="_moz">${rs.wjscsm }</textarea>
						</td>
					</tr>
					<tr>
						<th valign="middle" rowspan="2" align="center">
							<font color="red">*</font>�������:
							
						</th>
						<th align="center">
							<div align="center">
							<font color="red">������ѡ��</font>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--							<input type="checkbox" value="" onclick="lock(this)" id="alluser" name="alluser"/>&nbsp;�����û�--%>
							</div>
						</th>
						<th >
							<div align="center">
							<font color="red">������</font>
							</div>
						</th>
						<logic:present name="jsrlist">
							<html:select property="jsr" styleId="wjjsr" style="display:none">
								<html:options collection="jsrlist" property="yhm" labelProperty="xm"/>
							</html:select>
						</logic:present>
					</tr>
				
					<tr>
						<td>	
							<table >
								<tr>
									<td style="cursor:hand;">
										<div style="overflow: auto;height:250px;width:200px;" id="div1">					
											<table  id="yhzinfo">
												<thead align="center" >
													<td style="width:140px">�� �� ��</td>
												</thead>
												<tr>
													<td>
														<logic:iterate id="s" name="yhzList">
															<input type="checkbox" value="<bean:write name="s" property="zdm"/>" onclick="selectall(this)"/>&nbsp;
															<span  style="font-size: 13;color: #003366" onclick="viewyh(this,'<bean:write name="s" property="zdm"/>');">
															<bean:write name="s" property="zmc"/>
															</span><br>
														</logic:iterate>
													</td>
												</tr>
											</table>
										</div>
									</td>
									<td width="15px"></td>
									<td>
										<div style="overflow: auto;height:250px" id="div2">					
											<table id="yhinfo">										
												<thead align="center">
													<td style="width:140px">�� ��</td>
												</thead>
												<tr id="yhlist">
													<logic:iterate id="s" name="allyhlist">
													<td id = "<bean:write name="s" property="zdm"/>" style="display: none">
														<logic:iterate id="s" name="s" property="yhzyhmap">
															
															<input type="checkbox" value="<bean:write name="s" property="yhm"/>" onclick="selectjsr(this)"/>&nbsp;<span id="xm"><bean:write name="s" property="xm" /></span><br>
														</logic:iterate>
													</td>
													</logic:iterate>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>					
						<td >
							<textarea name="jsrxm" rows="16" cols="30" readonly="true"  id="jsrxm" type="_moz">${jsrxm}</textarea>
						</td>
					</tr>				
					<tr>
						<th align="center">
							<font color="red">*</font>��ѡ���ļ���
						</th>
						<td align="left" colspan="2">
							<div>	
							<input type="file" name="uploadFile" style="width:60%" id="kk" contenteditable="false" class="text_nor"/>
							&nbsp;&nbsp;
							<font color="red">(�ļ���С&lt;10M&gt;)</font>
							</div>
						</td>
					</tr>
				</table>
				</div>
				<logic:present name="inserted">
					<script defer="defer">
					    if (window.dialogArguments) {
							window.close();
							dialogArgumentsQueryChick();
						}
					 </script>
				</logic:present>
		</html:form>
	</body>
</html>
