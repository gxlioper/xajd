<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<thead onclick="hiddenMk('qtxx')">
	<tr><th colspan="5" style="cursor:hand"><span>������Ϣ</span></th></tr>
</thead>
<tbody id="qtxx" style="display:none">
	<tr>
		<th><span class="red">&lt;��2000��&gt;</span><br/>��Ҫְ��</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="zyzz" style="width:90%" rows="5" value="${rs.zyzz}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;��2000��&gt;</span><br/>��������</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="fblw" style="width:90%" rows="5" value="${rs.fblw}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;��2000��&gt;</span><br/>��������</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="gzjl" style="width:90%" rows="5" value="${rs.gzjl}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;��2000��&gt;</span><br/>���˻����</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="grhjqk" style="width:90%" rows="5" value="${rs.grhjqk}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;��2000��&gt;</span><br/>���о���</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="kyjl" style="width:90%" rows="5" value="${rs.kyjl}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;��300��&gt;</span><br/>��ѵ���</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="pxqk" style="width:90%" rows="5" value="${rs.pxqk}" onblur='chLeng(this,300)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;��2000��&gt;</span><br/>��ע</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="bz" style="width:90%" rows="5" value="${rs.bz}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
</tbody>