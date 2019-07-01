<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getWjcfInfo.js"></script>
		<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" src="js/check.js"></script>
		
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<logic:equal value="11049" name="xxdm">
					<em>���ĵ�ǰλ��:</em>
					<a>Υ�ʹ��� - ѧ������ - ����</a>
				</logic:equal>
				<logic:notEqual value="11049" name="xxdm">
					<em>���ĵ�ǰλ��:</em>
					<a> Υ�ʹ��� - ѧ���������� - ����</a>
				</logic:notEqual>
			</p>
		</div>

		<html:form action="/wjcf_xsshssq" method="post"
			enctype="multipart/form-data">
			<logic:empty name="rs">
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    alert("�������ѧ����Ч!");
				    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/wjcf_xsshssq.do" />
				<input type="hidden" id="cfid" name="cfid" value="${cfid }" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="tabType" name="tabType" value="wjcf_xsssb" />
				<input type="hidden" name="flag" id="flag" value="${flag }" />
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
				<input type="hidden" name="pk" id="pk" value="${pk }" />
				<input type="hidden" name="xxsh" id="xxsh" value="${rs.jd }"/>
 



					<div class="formbox">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rswj">
									<font color="red"> ���޲��ϻ�֤������</font>
								</logic:empty> </span>
						</h3>

						<logic:notEmpty name="rswj">
							<table summary="" class="dateline" align="" width="100%">
								<tbody>
									<logic:iterate id="list" name="rswj">
										<tr onmouseover="rowOnClick(this)">
											<td>
												<a
													href="downloadfilewj.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj"/>"
													target="_black">���� <input type="hidden" name="lj"
														id="lj"
														value="<bean:write name="list" property="wjsclj"/>" /> </a>
											</td>
											<td>
												<bean:write name="list" property="cfwh" />
											</td>
											<td>
												<bean:write name="list" property="cflbmc" />
											</td>
											<td>
												<bean:write name="list" property="cfyymc" />
											</td>
											<td>
												<bean:write name="list" property="sssj" />
											</td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</logic:notEmpty>
					</div>

				<div class="tab">
					<table width="100%" border="0" class="formlist" id="rsTable">
						<thead>
							<tr>
								<th colspan="5">
									<span><bean:write name="tit" scope="request" /> </span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="5">
									<div class="btn">
									<logic:equal value="δ����" name="rs" property="jd">
										<button type="button" id="btn_save"
											onclick="addShsApply('xh-cfwh','');">
											�ύ
										</button>
									</logic:equal>
									<logic:notEqual value="δ����" name="rs" property="jd">
										<logic:notEqual value="modi" name="doType">
										<button type="button" id="btn_save"
											onclick="addShsApply('xh-cfwh','');">
											�ύ
										</button>
										</logic:notEqual>
									</logic:notEqual>
										<logic:equal value="modi" name="doType">
										<button type="button" onclick="Close();return false;">
										�ر�
									</button>
										</logic:equal>
										<logic:notEqual value="modi" name="doType">
										<button type="button" type="reset">
										����
									</button>
										</logic:notEqual>
									</div>
								</td>
							</tr>
						</tfoot>
						<thead>
							
							<logic:equal value="11078" name="xxdm">
								<tr>
									<td colspan="5" align="center">
										<font color="red">
											��ʾ��1���������ϵ���ύ��ҳ���ݡ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
											2�����ӡ�����������ϣ����ɱ�����дǩ����ݽ�ѧУѧ������ίԱ�ᣨ��ϵ�绰������ص㿴��������ʾ���� </font>
									</td>
								</tr>
							</logic:equal>
						</thead>
						<tbody>
							<tr align="center" style="cursor:hand">
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<th width="10%">
										<font color="red">*</font>ѧ��
									</th>
									<td align="left" width="25%">
										
											
											<logic:equal value="modi" name="doType">
											<html:text name='rs' property="xh" styleId="xh"
											maxlength="20" readonly="true"/>
											</logic:equal>
											<logic:notEqual value="modi" name="doType">
											<html:text name='rs' property="xh" styleId="xh"
											onkeypress="autoFillStuInfo(event.keyCode,this);"
											maxlength="20" />
											</logic:notEqual>
											
										<logic:notEqual value="domodi" name="modi">
											<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu">
												ѡ��
											</button>
										</logic:notEqual>
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<th width="15%">
										<font color="red">*</font>ѧ��
									</th>
									<td align="left" width="25%">
										<input type="text" id="xh" name="xh"
											value="<bean:write name='rs' property="xh"  />"
											readonly="readonly" />
									</td>
								</logic:equal>
								<th width="20%">
									<font color="red">*</font> �����ĺ�
								</th>
								<td align="left" width="25%">
									<html:text name='rs' property="cfwh" styleId="cfwh"
										readonly="true" />
									<button type="button" onclick="wjcfInfoTo()" class="btn_01"
										id="buttonFindWjcf">
										ѡ��
									</button>
								</td>
								<td align="left" width="15%" rowspan="5">
									<div class="open_img">
									<img
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
										border="0" align="absmiddle" width="96" height="128" />
									</div>
								</td>
							</tr>
							<tr>
								<th>
									����
								</th>
								<td>
									<html:text name='rs' property="xm" readonly="true" />
								</td>
								<th>
									���
								</th>
								<td align="left">
									<html:text name='rs' property="nd" readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									�Ա�
								</th>
								<td align="left">
									<html:text name='rs' property="xb" readonly="true" />
								</td>
								<th>
									ѧ��
								</th>
								<td align="left">
									<html:text name='rs' property="xn" readonly="true" />
								</td>
							</tr>
							<tr align="center" style="cursor:hand">
								<th>
									�꼶
								</th>
								<td align="left">
									<html:text name='rs' property="nj" readonly="true" />
								</td>
								<th>
									ѧ��
								</th>
								<td align="left">
								
									<html:hidden name='rs' property="xq"  />
									
									
									<html:select property="xq" name="rs" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />

								</th>
								<td align="left">
									<html:text name='rs' property="xymc" readonly="true" />
								</td>
								<th>
									����ʱ��
								</th>
								<td align="left">
									<html:text name='rs' property="cfsj" styleId="cfsj"
										readonly="true" />
								</td>
							</tr>

							<tr>
								<th>
									רҵ
								</th>
								<td align="left">
									<html:text name='rs' property="zymc" readonly="true" />
								</td>
								<th>
									�������
								</th>
								<td align="left" colspan="2">
									<html:text name='rs' property="cflbmc" readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td align="left">
									<html:text name='rs' property="bjmc" readonly="true" />
								</td>
								<th>
									��������
								</th>
								<td align="left" colspan="2">
									<html:text name='rs' property="cfyymc" readonly="true" />
									<input type="hidden" id="cfyy" name="cfyy"
										value="<bean:write name="rs" property="cfyy"/>" />
								</td>
							</tr>
							<tr>
								<th>
									��ϵ��ַ
								</th>
								<td align="left">
									<html:text name='rs' property="dz" maxlength="50" />
								</td>
								<th>
									<logic:present name="isZJJDZYJSXY">
					    ����/�������ʱ��
					    </logic:present>
									<logic:notPresent name="isZJJDZYJSXY">
					       ����ʱ��
					     </logic:notPresent>
								</th>
								<td align="left" colspan="2">
									<html:text name="rs" property="sssj" readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									��������
								</th>
								<td align="left">
									<html:text name='rs' property="yb" maxlength="6"
										onkeyup="chkIsNum(this)" />
								</td>
								<th>
									��ϵ�绰
								</th>
								<td align="left" colspan="2">
									<html:text name='rs' property="lxdh" maxlength="14"
										onblur="checkPhone(this)" />
								</td>
							</tr>
							<logic:present name="isZJJDZYJSXY">
								<tr style="height:30px">
									<th>
										���ָ�Ϊ
									</th>
									<td align="left" colspan="4">
										<html:text name='rs' property="cfxg" maxlength="80"
											style="width:300px" />
									</td>
								</tr>
							</logic:present>
							<tr style="height:30px">
								<th>
									<logic:present name="isZJJDZYJSXY">
					    						����/���<br />
												�������� 
											<br />
									</logic:present>
									<logic:notPresent name="isZJJDZYJSXY">
										<logic:equal value="11078" name="xxdm">
							���߳�������<br />
										</logic:equal>
										<logic:notEqual value="11078" name="xxdm">
										<logic:equal value="10653" name="xxdm">
										��������
										</logic:equal>
										<logic:notEqual value="10653" name="xxdm">
											��ʵ���ֻ�<br/>�ı�<bean:message key="lable.xsgzyxpzxy" />����Ҫ��<br />
										</logic:notEqual>
										</logic:notEqual>

									</logic:notPresent>
									<font color="red">(������2000������)</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<td align="left" colspan="4">
									<html:textarea rows="5" name='rs' style="width:600px"
										property="yq" styleId="yq" onkeyup="checkLen(this,2000)" />
								</td>
							</tr>
							<tr>
								<th>
									���ϻ�&nbsp;&nbsp;&nbsp;
									<br />
									֤������
								</th>
								<td align="left" colspan="4">
									<input type="file" name="uploadFile" id="cfwj"
										contentEditable="false" style="width:60%" />
									<logic:equal name="xxdm" value="10628#">
      				 	&nbsp;&nbsp;<a
											href="downloadfiletemplate.do?filename=����<bean:message key="lable.xsgzyxpzxy" />ѧ��У��������.doc"
											target="_blank">����<bean:message key="lable.xsgzyxpzxy" />ѧ��У������������</a>
										<font color="red">(��������д���ϴ�)</font>
									</logic:equal>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
                      alert("�ύ�ɹ���");
                     if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    					window.dialogArguments.document.all("search_go").click();
    					
    				}   
    				Close();             
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
                      alert("�ύʧ��!");                   
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="upFail">
					<script>
                      alert("�ύʧ��!");                   
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		</body>
</html>
