<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<thead onclick="hiddenMk('qtxx')">
	<tr><th colspan="5" style="cursor:hand"><span>其它信息</span></th></tr>
</thead>
<tbody id="qtxx" style="display:none">
	<tr>
		<th><span class="red">&lt;限2000字&gt;</span><br/>主要职责</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="zyzz" style="width:90%" rows="5" value="${rs.zyzz}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;限2000字&gt;</span><br/>发表论文</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="fblw" style="width:90%" rows="5" value="${rs.fblw}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;限2000字&gt;</span><br/>工作经历</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="gzjl" style="width:90%" rows="5" value="${rs.gzjl}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;限2000字&gt;</span><br/>个人获奖情况</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="grhjqk" style="width:90%" rows="5" value="${rs.grhjqk}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;限2000字&gt;</span><br/>科研经历</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="kyjl" style="width:90%" rows="5" value="${rs.kyjl}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;限300字&gt;</span><br/>培训情况</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="pxqk" style="width:90%" rows="5" value="${rs.pxqk}" onblur='chLeng(this,300)'></html:textarea>
		</td>
	</tr>
	
	<tr>
		<th><span class="red">&lt;限2000字&gt;</span><br/>备注</th>
		<td colspan="4" style="word-break:break-all;">
			<html:textarea property="bz" style="width:90%" rows="5" value="${rs.bz}" onblur='chLeng(this,2000)'></html:textarea>
		</td>
	</tr>
	
</tbody>