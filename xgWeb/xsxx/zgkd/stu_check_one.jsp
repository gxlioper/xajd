<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>	
</head>
	<body>
		<html:form action="/xsxx_zgkd.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>		
			<div class="tab">	
			<table class="formlist" id="rsTable">
			<thead>			
			<tr>
				<th colspan="6">
					<span>������Ϣ�޸�(ԭʼֵ  - ��ֵ)</span>
				</th>
			</tr>
			<tr>
				<td>
					�ֶ���
				</td>
				<td>
					��ֵ
				</td>
				<td>
					ԭʼֵ
				</td>
				<td>
					�ֶ���
				</td>
				<td>
					��ֵ
				</td>
				<td>
					ԭʼֵ
				</td>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th>ѧ��</th>
				<td>
					<bean:write name="newValue" property="xh"/>		 
					<html:hidden property="xh" name="oldValue"/>							
				</td>
				<td>
					<bean:write name="oldValue" property="xh"/>
				</td>
				<th>����</th>
				<td>
					<bean:write name="newValue" property="xm"/>
				</td>
				<td>
					<bean:write name="oldValue" property="xm"/>
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
				<bean:write name="newValue" property="xymc"/>
	<%--				<html:select property="xydm" name="newValue" disabled="true">--%>
	<%--					<html:option value=""></html:option>--%>
	<%--					<html:options collection="xyList" property="xydm" labelProperty="xymc"/>--%>
	<%--				</html:select>--%>
				</td>
				<td>
				<bean:write name="oldValue" property="xymc"/>
	<%--				<html:select property="xydm" name="oldValue" disabled="true">--%>
	<%--					<html:option value=""></html:option>--%>
	<%--					<html:options collection="xyList" property="xydm" labelProperty="xymc"/>--%>
	<%--				</html:select>--%>
				</td>
				<th>רҵ</th>
				<td>
				<bean:write name="newValue" property="zymc"/>
	<%--				<html:select property="zydm" name="newValue" disabled="true" styleId="zydm">--%>
	<%--					<html:option value=""></html:option>--%>
	<%--					<html:options collection="zyList" property="zydm" labelProperty="zymc"/>--%>
	<%--				</html:select>--%>
				</td>
				<td>
				<bean:write name="oldValue" property="zymc"/>
	<%--				<html:select property="zydm" name="oldValue" disabled="true" styleId="zydm">--%>
	<%--					<html:option value=""></html:option>--%>
	<%--					<html:options collection="zyList" property="zydm" labelProperty="zymc"/>--%>
	<%--				</html:select>--%>
				</td>
			</tr>
			
			<tr>
			<th>�꼶</th>
			<td>
				<bean:write name="newValue" property="nj"/>
<%--				<html:select property="nj" name="newValue" disabled="true" styleId="nj">--%>
<%--					<html:option value=""></html:option>--%>
<%--					<html:options collection="njList" property="nj" labelProperty="nj"/>--%>
<%--				</html:select>--%>
			</td>			
			<td>
				<bean:write name="oldValue" property="nj"/>
			
<%--				<html:select property="nj" name="oldValue" disabled="true" styleId="nj">--%>
<%--					<html:option value=""></html:option>--%>
<%--					<html:options collection="njList" property="nj" labelProperty="nj"/>--%>
<%--				</html:select>--%>
			</td>
			<th>�༶</th>
			<td>
			<bean:write name="newValue" property="bjmc"/>
<%--				<html:select property="bjdm" name="newValue" disabled="true" styleId="bjdm">--%>
<%--					<html:option value=""></html:option>--%>
<%--					<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>--%>
<%--				</html:select>--%>
			</td>
			<td>
				<bean:write name="oldValue" property="bjmc"/>
			</td>
			</tr>
			
			<tr>
			<th>�Ա�</th>
			<td>
				<bean:write name="newValue" property="xb"/>
			</td>
			<td>
				<bean:write name="oldValue" property="xb"/>
			</td>
			<th>ѧ��</th>
			<td>
				<bean:write name="newValue" property="xz"/>
			</td>
			<td>
				<bean:write name="oldValue" property="xz"/>
			</td>
			</tr>
			
			<tr>
			<th>����</th>
			<td>
				<html:select property="mz" name="newValue" disabled="true" styleId="mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="mz" name="oldValue" disabled="true" styleId="mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<th>������ò</th>
			<td>
				<html:select property="zzmm" name="newValue" disabled="true" styleId="zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="zzmm" name="oldValue" disabled="true" styleId="zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<th>ѧ��״̬</th>
			<td>
				<bean:write name="newValue" property="xjztm"/>
			</td> 
			<td>
				<bean:write name="oldValue" property="xjztm"/>
			</td>
			<th>��������</th>
			<td>
				<bean:write name="newValue" property="csrq"/>
			</td>
			<td>
				<bean:write name="oldValue" property="csrq"/>
			</td>
			</tr>
			
			<tr>
			<th>����ƴ��</th>
			<td>
				<bean:write name="newValue" property="xmpy"/>				
			</td>			
			<td>
				<bean:write name="oldValue" property="xmpy"/>
			</td>
			<th>������</th>
			<td>
				<bean:write name="newValue" property="cym"/>
			</td>
			<td>
				<bean:write name="oldValue" property="cym"/>
			</td>
			</tr>
			
			<tr>
			<th>���</th>
			<td>
				<bean:write name="newValue" property="sg"/>
			</td>
			<td>
				<bean:write name="oldValue" property="sg"/>
			</td>
			<th>����</th>
			<td>
				<bean:write name="newValue" property="tz"/>
			</td>
			<td>
				<bean:write name="oldValue" property="tz"/>
			</td>
			</tr>
			
			<tr>
			<th>���֤��</th>
			<td>
				<bean:write name="newValue" property="sfzh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="sfzh"/>
			</td>
			<th>�س�</th>
			<td>
				<bean:write name="newValue" property="tc"/>
			</td>
			<td>
				<bean:write name="oldValue" property="tc"/>
			</td>
			</tr>
			
			<tr>
			<th>
				������ʽ
			</th>
			<td>
				<bean:write name="newValue" property="pyfs"/>
			</td>
			<td>
				<bean:write name="oldValue" property="pyfs"/>
			</td>
			<th>
				�������
			</th>
			<td>
				<bean:write name="newValue" property="pycc"/>
			</td>
			<td>
				<bean:write name="oldValue" property="pycc"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��ѧ��ʽ
			</th>
			<td>
				<bean:write name="newValue" property="rxfs"/>
			</td>
			<td>
				<bean:write name="oldValue" property="rxfs"/>
			</td>
			<th>
				�������
			</th>
			<td>
				<bean:write name="newValue" property="kslb"/>
			</td>
			<td>
				<bean:write name="oldValue" property="kslb"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��Դ����
			</th>
			<td>
				<bean:write name="newValue" property="syd"/>
			</td>
			<td>
				<bean:write name="oldValue" property="syd"/>
			</td>
			<th>
				����
			</th>			
			<td>
				<bean:write name="newValue" property="jg"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jg"/>
			</td>
			</tr>
			<tr>
			<th>
				������
			</th>
			<td>
				<bean:write name="newValue" property="ssbh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="ssbh"/>
			</td>
			<th>		
			    ���ҵ绰	
			</th>
			<td>	
				<bean:write name="newValue" property="qsdh"/>			
			</td>
			<td>	
				<bean:write name="oldValue" property="qsdh"/>			
			</td>
			</tr>
			<tr>
			<th>
				��������
			</th>
			<td>
				<bean:write name="newValue" property="dzyx"/>
			</td>
			<td>
				<bean:write name="oldValue" property="dzyx"/>
			</td>
			<th>
				��ϵ�绰
			</th>
			<td>
				<bean:write name="newValue" property="lxdh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="lxdh"/>
			</td>
			</tr>
			
			<tr>
			<th>
				�ֻ�����
			</th>			
			<td>
				<bean:write name="newValue" property="sjhm"/>
			</td>
			<td>
				<bean:write name="oldValue" property="sjhm"/>
			</td>
			<th>
			</th>
			<td>
			</td>
			<td>
			</td>
			</tr>
			<%@ include file="/xsxx/bjqnzzxy/xsxx_audit_bjqnzzxy.jsp"%>			
			<tr>
			<th>
				��ͥ�ʱ�
			</th>
			<td>
				<bean:write name="newValue" property="yb"/>
			</td>
			<td>
				<bean:write name="oldValue" property="yb"/>
			</td>
			<th>
				��ͥ�������
			</th>
			<td>
				<bean:write name="newValue" property="jjzk"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jjzk"/>
			</td>
			</tr>
			<tr>
				<th>
				��ͥ��ַ
				</th>
				<td>
					<bean:write name="newValue" property="jtszd"/>
				</td>
				<td>
					<bean:write name="oldValue" property="jtszd"/>
				</td>
				<th>
				</th>
				<td>
				</td>
				<td>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr title="���չ��������">
					<td colspan="6" style="cursor:hand" align="center"
						onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
							<span>ѧ����ͥ��Ա��Ϣ</span>
					</td>
				</tr>				
			</thead>
			<tbody>
			<tr id="jt1" style="display: none">
			<td colspan="6">					
			<table width="100%" class="tbstyle">
			<thead>
			<tr>
			<td>
			�ֶ���
			</td>
			<td>
			��ֵ
			</td>
			<td>
			ԭʼֵ
			</td>
			<td>
			�ֶ���
			</td>
			<td>
			��ֵ
			</td>
			<td>
			ԭʼֵ
			</td>
			</tr>
			</thead>
			<tr>
			<th>
				��ͥ��Ա1����
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_xm"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_xm"/>
			</td>
			<th>
				�������ͥ��Ա1��ϵ
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_gx"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_gx"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա1��������
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_nl"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_nl"/>
			</td>
			<th>
				��ͥ��Ա1���֤��
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_sfzh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_sfzh"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա1����
			</th>
			<td>
				<html:select property="jtcy1_mz" name="newValue" disabled="true" styleId="jtcy1_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy1_mz" name="oldValue" disabled="true" styleId="jtcy1_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<th>
				��ͥ��Ա1������ò
			</th>
			<td>
				<html:select property="jtcy1_zzmm" name="newValue" disabled="true" styleId="jtcy1_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy1_zzmm" name="oldValue" disabled="true" styleId="jtcy1_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա1ְҵ
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_zy"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_zy"/>
			</td>
			<th>
				��ͥ��Ա1ְ��
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_zw"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_zw"/>
			</td>
			</tr>
				
			<tr>
			<th>
				��ͥ��Ա1������λ�绰
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_lxdh1"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_lxdh1"/>
			</td>
			<th>
				��ͥ��Ա1���˵绰
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_lxdh2"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_lxdh2"/>
			</td>			
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա1������ַ
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_gzdz"/>
			</td>	
			<td>
				<bean:write name="oldValue" property="jtcy1_gzdz"/>
			</td>	
			<td>		
			</td>	
			<td>		
			</td>	
			<td>		
			</td>
			</tr>	
					
			<tr>
			<th>
				��ͥ��Ա2����
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_xm"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_xm"/>
			</td>
			<th>
				�������ͥ��Ա2��ϵ
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_gx"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_gx"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա2��������
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_nl"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_nl"/>
			</td>
			<th>
				��ͥ��Ա2���֤��
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_sfzh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_sfzh"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա2����
			</th>
			<td>
				<html:select property="jtcy2_mz" name="newValue" disabled="true" styleId="jtcy2_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy2_mz" name="oldValue" disabled="true" styleId="jtcy2_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<th>
				��ͥ��Ա2������ò
			</th>
			<td>
				<html:select property="jtcy2_zzmm" name="newValue" disabled="true" styleId="jtcy2_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy2_zzmm" name="oldValue" disabled="true" styleId="jtcy2_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա2ְҵ
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_zy"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_zy"/>
			</td>
			<th>
				��ͥ��Ա2ְ��
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_zw"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_zw"/>
			</td>
			</tr>
				
			<tr>
			<th>
				��ͥ��Ա2������λ�绰
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_lxdh1"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_lxdh1"/>
			</td>
			<th>
				��ͥ��Ա2���˵绰
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_lxdh2"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_lxdh2"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա2������ַ
			</th>	
			<td>
				<bean:write name="newValue" property="jtcy2_gzdz"/>
			</td>		
			<td>
				<bean:write name="oldValue" property="jtcy2_gzdz"/>
			</td>	
			<td>		
			</td>
			<td>
			</td>
			<td>
			</td>
			</tr>
										
			<tr>
			<th>
				��ͥ��Ա3����
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_xm"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_xm"/>
			</td>
			<th>
				�������ͥ��Ա3��ϵ
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_gx"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_gx"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա3��������
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_nl"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_nl"/>
			</td>
			<th>
				��ͥ��Ա3���֤��
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_sfzh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_sfzh"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա3����
			</th>
			<td>
				<html:select property="jtcy3_mz" name="newValue" disabled="true" styleId="jtcy3_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy3_mz" name="oldValue" disabled="true" styleId="jtcy3_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<th>
				��ͥ��Ա3������ò
			</th>
			<td>
				<html:select property="jtcy3_zzmm" name="newValue" disabled="true" styleId="jtcy3_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy3_zzmm" name="oldValue" disabled="true" styleId="jtcy3_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա3ְҵ
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_zy"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_zy"/>
			</td>
			<th>
				��ͥ��Ա3ְ��
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_zw"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_zw"/>
			</td>			
			</tr>
				
			<tr>
			<th>
			    ��ͥ��Ա3������λ�绰
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_lxdh1"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_lxdh1"/>
			</td>
			<th>
				��ͥ��Ա3���˵绰
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_lxdh2"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_lxdh2"/>
			</td>
			</tr>
			
			<tr>
			<th>
				��ͥ��Ա3������ַ
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_gzdz"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_gzdz"/>
			</td>	
			<td>		
			</td>
			<td>
			</td>
			<td>
			</td>
			</tr>	
			</table>
			</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="6">
		          <div class="btn">
		            <logic:equal value="yes" name="writeAble">
					<button type="button"						
						onclick="refreshForm('xsxx_zgkd.do?method=saveAutidting&yesNo=ͨ��')">
						ͨ �� 
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button"						
						onclick="refreshForm('xsxx_zgkd.do?method=saveAutidting&yesNo=��ͨ��')">
						��ͨ�� 
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button"						
						onclick="Close();return false;">
						�� �� 
					</button>
					</logic:equal>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
			</table>
		</div>	
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");		
					Close();		
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");				
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
