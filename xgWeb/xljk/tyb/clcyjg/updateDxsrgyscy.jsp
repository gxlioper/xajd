<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>
</head>
<body onload="checkWinType();">
	<html:form action="/xljkXlcyjg" method="post">
    	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������ - ���������ά�� - �˸����������ʾ�ά��</a>
			</p>
		</div>
    	<div class="searchtab">
    	<div class="tab">
			  <table width="90%"  border="0" class="formlist">
		 		<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:notEqual value="view" name="writable">
				        <button name="����" id="btn_Save" onclick="saveinfo('xljk_tyb_updateDxsrgyscy.do?act=save','xh-cssj');">�� ��</button>
				       </logic:notEqual>
			            <button name="�ر�"onclick="Close();return false;" id="buttonClose">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>

    	
				<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
				 <thead>
			    	<tr>
			        	<th colspan="4"><span>�����޸�</span></th>
			        </tr>
			    </thead>
				<tr style="width:22px">
					<th align="right">
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
				</td>
					<th align="right">
						<font color="red">*</font>����ʱ��
					</th>
					<td align="left">
						<html:text property="cssj" styleId="cssj" readonly="true" onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cssj','y-mm-dd');"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							����
						</th>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<th align="right">
						����֢״���Է�ֵ
					</th>
					<td align="left">
						<html:text property="qtzz" styleId="qtzz" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
					
				</tr>
				<tr style="width:22px">
					<th align="right">
							�Ա�
						</th>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
					<th align="right">
						������Ѳ��Է�ֵ
					</th>
					<td align="left">
						<html:text property="jsfl" styleId="jsfl" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							��������
						</th>
						<td align="left">
							<html:text name='rs' property="csrq" styleId="csrq" readonly="true"/>
						</td>
					<th align="right">
						����֢���Է�ֵ
					</th>
					<td align="left">
						<html:text property="yyz" styleId="yyz" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							�꼶
						</th>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
					<th align="right">
						��֢���Է�ֵ
					</th>
					<td align="left">
						<html:text property="sjz" styleId="sjz" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
					<th align="right">
						�ܷ�
					</th>
					<td align="left">
						<html:text property="zf" styleId="zf" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th align="right">
							רҵ
						</th>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
					<td align="right">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr>
						<th align="right">
							�༶
						</th>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
				</tr>
				
				<tr>
					<td colspan="4" align="center">
						  <table width="90%"  border="0" class="formlist">
							<thead>
								<tr >
									<th colspan="6">
										<div  align="center">
										��ѧ���˸������ʾ�����
										</div>
										<div align="left">
										<logic:notEmpty name="yzmc">
											<font color="red" align="left">��ʾ����ϵͳά�� - ������ - ��ѧ���˸�����������������������δ���ã�${yzmc }�����ӵ����������Ϣ!</font>
										</logic:notEmpty>
										</div>
									</th>
								</tr>
							</thead>
							<tr>
							<th>
									˳��
								</th>
								<th>
									����
								</th>
								<th>
									��������
								</th>
								<th>
									��׼��
								</th>
								<th>
									����
								</th>
								<th>
									�ȼ�
								</th>
							</tr>
							<logic:empty name="pyList">
								<tr><td colspan="6" align="center">���޼�¼!</td></tr>
							</logic:empty>
							<logic:notEmpty name="pyList">
								<logic:iterate name="pyList" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center">
										<logic:iterate id="v" name="s">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
						</logic:iterate>
							</logic:notEmpty>
						</table>
					</td>
				</tr>
			</table>
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>