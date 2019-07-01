<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<thead>
	<tr>
		<th colspan="5" style="cursor:hand">
			<span>������Ϣ</span>
		</th>
	</tr>
</thead>
<tbody id="jbxx">
	<tr>
		<th width="16%">
			ְ����
			<html:hidden property="zgh" value="${rs.zgh}" />
		</th>
		<td width="24%">
			${rs.zgh }
		</td>
		<th width="15%">
			����
		</th>
		<td width="25%">
			<input type="text" name="xm" value="${rs.xm }" maxlength="25" />
		</td>
		<td rowspan="6">
			<div id="teaImg">
				<img style="width:120px;height:160px" 
					src="<%=request.getContextPath()%>/teaPic.jsp?zgh=${rs.zgh }"
					border="0">
			</div>
			<br />
			<button type="button"
				onclick='tipsWindown("ϵͳ��ʾ","id:addPic","380","130","true","","true","id");'
				style="width:100px" class="btn_01">
				�ϴ���Ƭ
			</button>
		</td>
	</tr>
	<tr>
		<th>
			�Ա�
		</th>
		<td>
			<html:select property="xb" style="width:80px" value="${rs.xb }">
				<html:option value=""></html:option>
				<html:option value="1">��</html:option>
				<html:option value="2">Ů</html:option>
			</html:select>
		</td>
		<th>
			����
		</th>
		<td>
			<html:select property="mz" value="${rs.mz }">
				<html:option value=""></html:option>
				<html:options collection="mzList" property="mzdm"
					labelProperty="mzmc" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th>
			����
		</th>
		<td>
			<html:select property="jg" value="${rs.jg }">
				<html:option value=""></html:option>
				<html:options collection="jgList" property="qxdm"
					labelProperty="qxmc" />
			</html:select>
		</td>
		<th>
			������ò
		</th>
		<td>
			<html:select property="zzmm" value="${rs.zzmm }">
				<html:option value=""></html:option>
				<html:options collection="zzmmList" property="zzmmdm"
					labelProperty="zzmmmc" />
			</html:select>
		</td>
	</tr>
	<tr>
		<th>
			��������
		</th>
		<td>
			<html:text property="csrq" readonly="true" value="${rs.csrq}" styleId="csrq"
					   onclick="showCalendar(this.id,'y-mm-dd')" 
			></html:text>
		</td>
		<th>
			���֤��
		</th>
		<td>
			<html:text property="sfzh" value="${rs.sfzh}" maxlength="18"></html:text>
		</td>
	</tr>
	<tr>
		<th>
			ѧλ
		</th>
		<td>
			<html:text property="xw" maxlength="15" value="${rs.xw}"></html:text>
		</td>
		<th>
			ѧ��
		</th>
		<td>
			<html:select property="xl" value="${rs.xl }">
				<html:option value=""></html:option>
				<html:option value="��ר">��ר</html:option>
				<html:option value="����">����</html:option>
				<html:option value="˶ʿ">˶ʿ</html:option>
				<html:option value="��ʿ">��ʿ</html:option>
				<html:option value="��ʿ��">��ʿ��</html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<th>
			��ҵԺУ
		</th>
		<td>
			<html:text property="byyx" maxlength="15" value="${rs.byyx}"></html:text>
		</td>
		<th>
			��ѧרҵ
		</th>
		<td>
			<html:text property="sxzy" maxlength="15" value="${rs.sxzy}"></html:text>
		</td>
	</tr>
</tbody>
