<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkWinType();">
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ա��Ϣά��</a>
			</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<logic:notEqual name="xxdm" value="10402">
				<input type="hidden" id="url" name="url" value="/member_modi.do" />
				</logic:notEqual>
				<logic:equal name="xxdm" value="10402">
				<input type="hidden" id="url" name="url" value="/szdw/member_One.jsp" />
				</logic:equal>
			
				
				<fieldset>
					
					<div class="tab">
					<table width="100%" class="formlist" border="0">
						<thead>
							<tr><th colspan="4"><span>��Ա��Ϣά��</span></th></tr>
						</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					         	 <button type="button" onclick="szsxDataDoSave('member_modi.do?doType=save','rtsj-xh');return false;"
									 id="buttonSave">
									�� ��
								</button>
								<button type="button" onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
					          </div></td>
					      </tr>
					    </tfoot>
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="true"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<logic:equal name="xxdm" value="10402">
								<logic:equal name="doType" value="add">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do?jq=ty',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
								</button>
								</logic:equal>
								</logic:equal>
							</td>
							<th>
								����
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb"  readonly="true" />
							</td>
							<th>
								�꼶
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj"  readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />��
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy"  readonly="true" />
							</td>
							<th>
								רҵ
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy"  readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj"  readonly="true" />
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td align="left">
								<html:text name='rs' property="rtrq" styleId="rtrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rtrq','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								���ŵص�
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="rtdd" styleId="rtdd"  maxlength="100" style="width:100%"/>
							</td>
						</tr>
						<tr align="left">
							<th>
								��ע
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' onblur="chLeng(this,'100')"/>
							</td>
						</tr>
					</tbody>
					</table>
					</div>
				</fieldset>
				<div class="buttontool">
					
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
    dialogArgumentsQueryChick();
	Close();
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
