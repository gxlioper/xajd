<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	</head>
<body>
	<html:form action="/pjpyzjcmwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${rs.id }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - ��Ϣά�� - �ۺ����ʲ��� - ��������</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr style="height:23px">
					<th colspan="8" >
						��������
					</th>
				</tr>
			</thead>
			<tbody>
			<tr style="height:23px">
			<logic:equal value="1049701" name="xxdm">
				<th align="center" colspan="3">
					�����ɼ�<br/>����
				</th>
				<td align="center">
					<html:text property="zybl" styleId="zybl" onblur="ckinpdata(this)" style="width:60px" maxlength="5"></html:text><font color="red">(%)</font>
				</td>
				<th align="center" colspan="3">
					�����ɼ�<br/>����
				</th>
				<td align="center">
					<html:text property="tybl" styleId="tybl" onblur="ckinpdata(this)" style="width:60px" maxlength="5"></html:text><font color="red">(%)</font>
				</td>
			</logic:equal>
			<logic:notEqual value="1049701" name="xxdm">
				<logic:equal value="10628#" name="xxdm">
					<th align="right" width="30%" colspan="2">
						ƽ���ɼ���ռ����
					</th>
					<td align="right" width="20%" colspan="2">
						<html:text property="zybl" styleId="zybl" style="width:50px" maxlength="5" onblur="ckinpdata(this)"></html:text><font color="red">%</font>
					</td>
					<th align="right" width="30%" colspan="2">
						���з���ռ����
					</th>
					<td align="right" width="20%" colspan="2">
					<html:text property="cxbl" styleId="cxbl" style="width:50px" maxlength="5" onblur="ckinpdata(this)"></html:text><font color="red">%</font>
					</td>
				</logic:equal>
				<logic:notEqual value="10628#" name="xxdm">
				<th align="center">
					<logic:equal value="12682" name="xxdm">
					��Ϊ�淶<br/>��ռ����
					</logic:equal>
					<logic:notEqual value="12682" name="xxdm">
					�������ʷ�<br/>��ռ����
					</logic:notEqual>
				</th>
				<td align="center">
					<html:text property="dybl" styleId="dybl" maxlength="5" style="width:60px" onblur="ckinpdata(this)"></html:text><font color="red">(%)</font>
				</td>
				<th align="center">
				<logic:equal value="12682" name="xxdm">
					�Ļ��ɼ�<br/>��ռ����
					</logic:equal>
					<logic:notEqual value="12682" name="xxdm">
					�����ɼ�<br/>��ռ����
					</logic:notEqual>
					
				</th>
				<td align="center">
					<html:text property="zybl" styleId="zybl" onblur="ckinpdata(this)" style="width:60px" maxlength="5"></html:text><font color="red">(%)</font>
				</td>
				<th align="center">
					�����ɼ�<br/>��ռ����
				</th>
				<td align="center">
					<html:text property="tybl" styleId="tybl" onblur="ckinpdata(this)" style="width:60px" maxlength="5"></html:text><font color="red">(%)</font>
				</td>
				<logic:equal value="11647" name="xxdm">
				<th align="center">
					ʵ�����³ɼ�<br/>��ռ����
				</th>
				<td align="center">
					<html:text property="sjcxbl" styleId="sjcxbl" onblur="ckinpdata(this)" style="width:60px" maxlength="5"></html:text><font color="red">(%)</font>
				</td>
				</logic:equal>
				</logic:notEqual>
			</logic:notEqual>
				
			</tr>
			</tbody>
			
			<tfoot>
			      <tr>
			        <td colspan="6">
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button type="button" name="�ύ" id="btn_save" onclick="saveinfo('pjpy_zjcm_zhszcpblsz.do?act=save','dybl-zybl-tybl-sjcxbl')">�� ��</button>
			          </logic:notEqual>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
		
		
		
				<!-- ������ʾ��Ϣ -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>