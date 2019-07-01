<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<input type="hidden" id="method" name="method"
				value="wspyxjjtSq" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��չ����-��չ�������-�����걨��Ŀ</a>
				</p>
			</div>
			<logic:empty name="rs">
				<logic:empty name="szbjdm" >
					<div align="center"><font color="red"><bean:write name = "message" /></font></div>
				</logic:empty>
			</logic:empty>
			
			<logic:notEmpty name="szbjdm" >
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
				<ul>
					<li id="003m" class="ha">
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=wspyjtsb')"><span>
						&nbsp;���������Ƚ�����&nbsp;</span></a>
					</li>
					<li id="004m" >
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xsjljtsb')" ><span>
						&nbsp;ѧ�����������걨&nbsp;</span></a>
					</li>
				</ul>
  			</div>
  			</div>
  			</logic:notEmpty>
  			
  			<logic:notEmpty name="szbjdm" >
				<div align="center"><font color="red">${message }</font></div>
			</logic:notEmpty>
			
  			<logic:notEmpty name="rs">
			<input type="hidden" id="bjdm" name="bjdm"
				value="<bean:write name='rs' property='bjdm'/>" />
			
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>���������Ƚ�����</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			         <button type="button" name="����" onclick="szsxDataDoSave('szdwfzjy.do?method=saveWspyxjjtsq','xh-sbjx');"  id="buttonSave">�ꡡ��</button>
			         </div>
			         </td>    
			      </tr>
			    </tfoot>
				<tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>�걨���
							</th>
							<td colspan="3">
								<html:text name = "rs" property="sbjx" style="width:99%"  styleId="sbjx"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								�༶���ƣ�
							</th>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>
							<th align="right">
								�꼶��
							</th>
							<td align="left">
								<bean:write name='rs' property="nj"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</th>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<th align="right">
								רҵ��
							</th>
							<td align="left">
								<bean:write  name='rs' property="zymc"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								�����ɼ��ϸ��ʣ�
							</th>
							<td align="left">
								<html:text name='rs' property="cjcjhgl" />
							</td>
							<th align="right">
								�����ɼ������ʣ�
							</th>
							<td align="left">
								<html:text name='rs' property="cjcjyxl" />
							</td>
						</tr>
						<tr>
							<th align="right">
								�＾�ɼ��ϸ��ʣ�
							</th>
							<td align="left">
								<html:text name='rs' property="qjcjhgl " />
							</td>
							<th align="right">
								�＾�ɼ������ʣ�
							</th>
							<td align="left">
								<html:text name='rs' property="cjcjyxl" />
							</td>
						</tr>
						<tr>
							<th align="right">
								Ӣ��4��ͨ���ʣ�
							</th>
							<td align="left">
								<html:text name='rs' property="yysjhgl" />
							</td>
							<th align="right">
								Ӣ��4�������ʣ�
							</th>
							<td align="left">
								<html:text name='rs' property="yysjyxl" />
							</td>
						</tr>
						<tr>
							<th align="right">
								Ӣ��6��ͨ���ʣ�
							</th>
							<td align="left">
								<html:text name='rs' property="yyljhgl" />
							</td>
							<th align="right">
								Ӣ��6�������ʣ�
							</th>
							<td align="left">
								<html:text name='rs' property="yyljyxl" />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								��ע��
							</th>
							<td colspan="3">
								<html:textarea name = "rs" property="bz" style="width:99%" rows="5" />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
