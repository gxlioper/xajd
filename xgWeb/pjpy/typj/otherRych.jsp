<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
  <body>
            <tr >
			<th align="right" width="16%">
					���˼�����
					<br/>
				<font color="red">(������400����)</font>
			</th>
			<td  align="left"  colspan="3">
			<logic:empty name="rs">
				<html:textarea rows="5" style="width:98%" property="brjl" styleId="a" />
			</logic:empty>
			<logic:notEmpty name="rs">
				<html:textarea rows="5" style="width:98%"
				name="rs" property="brjl" styleId="a" />
			</logic:notEmpty>
			</td>
			</tr>
			<tr >
			<th align="right">
					��Ҫ�¼���
					<br/>
					<font color="red">(������400����)</font>
			</th>
			<td  align="left"  colspan="3">
			<logic:empty name="rs" >
					<html:textarea rows="5"  style="width:98%"
					 property="save_zysj" styleId="zysj" />
					</logic:empty>
			<logic:notEmpty name="rs" >
				<html:textarea rows="5"  style="width:98%" name="rs" property="save_zysj" styleId="zysj" />
			</logic:notEmpty>
			</td>
			</tr>
			<tr >
			<th align="right">
					�������
					<br/>
				<font color="red">(������400����)</font>
			</th>
			<td  align="left" colspan="3">
			<logic:empty name="rs" >
				<html:textarea rows="5"
				 style="width:98%"  property="save_jcqk" styleId="jcqk" />
			</logic:empty>
			<logic:notEmpty name="rs" >
			<html:textarea rows="5"
				 style="width:98%" name="rs" property="save_jcqk" styleId="jcqk" />
				 </logic:notEmpty>
			</td>
		    </tr>
  </body>