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
				value="wspyxjgrSq" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��չ����-��չ�������-ѧ���걨��Ŀ</a>
				</p>
			</div>
			<logic:empty name="rs">
				<!-- ��ѧ�� -->
				<logic:notEqual name="userType" value="stu">
					<div align="center"><font color="red"><bean:write name = "message" /></font></div>
				</logic:notEqual>
			</logic:empty>
			<logic:equal name="userType" value="stu">
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
				<ul>
					<li id="001m" >
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xssqYcjy')"><span>
						&nbsp;Ӣ�Ź�������&nbsp;</span></a>
					</li>
					<li id="002m" >
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xssqJzfdy')" id=""><span>
						&nbsp;��ְ����Ա&nbsp;</span></a>
					</li>
					<li id="003m" class='ha'>
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xssqWspyxjgr')"><span>
						&nbsp;���������Ƚ�����&nbsp;</span></a>
					</li>
					<li id="004m">
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xssqXsjlsbgr')"><span>
						&nbsp;ѧ�������걨&nbsp;	</span></a>
					</li>
				</ul>
  				</div>
  			</div>
  			</logic:equal>
  			<logic:empty name="rs">
				<!-- ��ѧ�� -->
				<logic:equal name="userType" value="stu">
					<div align="center"><font color="red"><bean:write name = "message" /></font></div>
				</logic:equal>
			</logic:empty>
			
  			<logic:notEmpty name="rs">
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name='rs' property='xh' />" />
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
			         <button type="button" name="�ύ"onclick="szsxDataDoSave('szdwfzjy.do?method=saveWspyxjgrsq','xh-sbjx');"  id="buttonSave">�ꡡ��</button>
			         </div>
			         </td>    
			      </tr>
			    </tfoot>
			    
				<tbody>
						<tr>
							<th align="right">
								�걨����:
							</th>
							<td colspan="3">
								<html:text name = "rs" property="sbjx" style="width:99%"  styleId="sbjx"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>ѧ�ţ�
							</th>
							<td align="left">
								<bean:write name='rs' property="xh" />
							</td>
							<th align="right">
								������
							</th>
							<td align="left">
								<bean:write name='rs' property="xm"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								<bean:write name='rs' property="xb"/>
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
								�༶��
							</th>
							<td align="left">
								<bean:write name='rs' property="bjmc"/>
							</td>
							<th align="right">
								ע���:
							</th>
							<td align="left">
								<html:text name='rs' property="zch" />
							</td>
						</tr>
						<tr>
							<th align="right">
								ѧ�ּ��㣺
							</th>
							<td align="left">
								<html:text name='rs' property="xfjj" />
							</td>
							<th align="right">
								�ۺϲ�������:
							</th>
							<td align="left">
								<html:text name='rs' property="zhcppm" />
							</td>
						</tr>
						<tr>
							<th align="right">
								Ӣ��ɼ���
							</th>
							<td align="left">
								<html:text name='rs' property="yycj" />
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th align="right">
								���������ְ��:
							</th>
							<td colspan="3">
								<html:text name = "rs" property="cdrshzw" style="width:99%"  />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								��ע
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
    dialogArgumentsQueryChick();
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
