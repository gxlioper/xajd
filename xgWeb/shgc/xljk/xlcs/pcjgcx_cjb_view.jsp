<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xljk_xlcs_pcjgcx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ���߲��� - �ղ�����ѯ - �ɼ���¼��Ϣ</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>
			        		<logic:empty name="rs">
				              δ�ҵ��κμ�¼��
				            </logic:empty>
				            <logic:notEmpty name="rs">
			        		�ɼ���¼��Ϣ
			        		</logic:notEmpty>
			        		</span></th>
			        </tr>
			    </thead>
			 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button name="�ر�" onclick="Close();return false;"  id="buttonClose">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			<logic:notEmpty name="rs">
				<tbody>
						<tr>
							<th align="right">
								�Ծ���ˮ�ţ�
							</th>
							<td align="left">
								<html:text name='rs' property="sjlsh" style="width: 120px"
									styleId="sjlsh" readonly="true"/>
							</td>
							<th align="right"></th>
							<td align="left"></td>
						</tr>
						<tr>
							<th align="right">
								ѧ�ţ�
							</th>
							<td align="left">
									<html:text name="rs" property="xh" style="width: 120px"
									styleId="xh" readonly="true"/>
							</td>
							<th align="right">
								������
							</th>
							<td align="left">
								<html:text name='rs' property="xm" style="width: 120px"
									styleId="xm" onblur="" readonly="true"/>
							</td>
						</tr>					
						<tr>
							<th align="right">
								���ڰ༶��
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" style="width: 120px"
									styleId="bjmc" readonly="true"/>
							</td>
							<th align="right">
								����<bean:message key="lable.xsgzyxpzxy" />��
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" style="width: 120px"
									styleId="xymc" readonly="true"/>
							</td>
						</tr>			
						<tr>
							<th align="right">
								����ʱ�䣺
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="dtsj" style="width:100%"
									styleId="stda" readonly="true"/>
							</td>
						</tr>	
						<tr align="left">
							<th align="right">
								������ʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="dtys" style="width: 120px"
									styleId="dtys" readonly="true"/>
							</td>
							<th align="right">
								�ɼ���
							</th>
							<td colspan="3">
								<html:text name='rs' property="cj" style="width: 120px"
									styleId="cj" readonly="true"/>
							</td>
						</tr>		
						<tr>
							<th align="right">
								��ע����ʦ�����
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:95%"
									rows='5' readonly="true"/>
							</td>
						</tr>
					
				</tbody>
			</logic:notEmpty>
			</table>
			</div>
		</html:form>
	</body>
</html>
