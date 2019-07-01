<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
		<html:form action="/zgdzdxXxwh" method="post" enctype="multipart/form-data">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><span>�������ĵ���ά��</span></a>
				</p>
			</div>
			
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
					
					<div class="tab">
					<table width="100%" class="formlist">
						<thead>
						<tr><th colspan="4"><span>��������</span></th></tr>
						</thead>
						
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>����
							</th>
							<td align="left">
								<html:select name = "rs" property="bmdm" style="width:180px" styleId="xy" onchange="getFdyList();">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>����
							</th>
							<td align="left">
								<html:select name = "rs" property="zgh"  styleId="zgh" style="width:150px" >
										<html:option value=""></html:option>
										<html:options collection="fdyList" property="zgh"
											labelProperty="xm" />
								</html:select>
							<input type="hidden" name="zghV" value="<bean:write name="rs" property="zgh" scope="request"/>"/>
							</td>
						</tr>
						<tr>
						<logic:equal name="rs" property="pk" value="">
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								ϵͳ����
							</td>
						</logic:equal>
						<logic:notEqual name="rs" property="pk" value="">
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								<bean:write name="rs" property="xh"/>
							</td>
						</logic:notEqual>
							<th>
								����ʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="fbsj" styleId="fbsj" readonly="true"
									onblur="dateFormatChg(this)" style="width:180px;cursor:hand;"
									onclick="return showCalendar('fbsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td align="left">
									<html:select name= "rs" property="lwlbdm" style="width:180px" styleId="lwlbdm" >
										<html:option value=""></html:option>
										<html:options collection="fblwlbList" property="lwlbdm"
											labelProperty="lwlbmc" />
									</html:select>
							</td>
							<th>
								�����ڿ�����
							</th>
							<td align="left">
								<html:text name='rs' property="fbqkmc" styleId="fbqkmc" maxlength="100" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>������Ŀ
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="lwtm" styleId="lwtm" maxlength="50" style="width:90%"/>
							</td>
						</tr>
						<tr align="left" id="zy">
							<th>
								ժҪ
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zy' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<logic:notPresent name = "type">
						<tr>
							<th>
								�����ϴ�
							</th>
							<td align=left colspan="3"> 
							<input type="file" name="uploadFile" style="width:60%" id="kk"/>
							&nbsp;&nbsp;
							<font color="red">(�ļ���СС��&lt;10M&gt;)</font>
							</td>
						</tr>
						</logic:notPresent>
						<logic:notEmpty name = "rs" property='xzlj'>
						<tr>
							<th>
								��������
							</th>
							<td align=left colspan="3"> 
							<a href="zgdzdxXxwh.do?method=downLoadFile&dir=<bean:write name = "rs" property='xzlj'/>" target="_blank" >���ظ���</a>
							</td>
						</tr>
						</logic:notEmpty>
						</tbody>
						
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					         <logic:notPresent name = "type">
								<button type="button" onclick="dtjsCommonSave('zgdzdxXxwh.do?method=fblwSave','zy','500','zgh-bmdm-lwlbdm-lwtm');" 
									id="buttonSave">
									�� ��
								</button>
							</logic:notPresent>
					            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
					</div>
			</logic:notEmpty>
			<logic:present name="update">
				<logic:equal name="update" value="yes">
					<script>
    alert("�ύ�ɹ���");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
