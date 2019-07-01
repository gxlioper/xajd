<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<thead>
	<tr>
		<th colspan="5" style="cursor:hand">
			<span>基本信息</span>
		</th>
	</tr>
</thead>
<tbody id="jbxx">
	<tr>
		<th width="16%">
			职工号
			<html:hidden property="zgh" value="${rs.zgh}" />
		</th>
		<td width="24%">
			${rs.zgh }
		</td>
		<th width="15%">
			姓名
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
				onclick='tipsWindown("系统提示","id:addPic","380","130","true","","true","id");'
				style="width:100px" class="btn_01">
				上传照片
			</button>
		</td>
	</tr>
	<tr>
		<th>
			性别
		</th>
		<td>
			<html:select property="xb" style="width:80px" value="${rs.xb }">
				<html:option value=""></html:option>
				<html:option value="1">男</html:option>
				<html:option value="2">女</html:option>
			</html:select>
		</td>
		<th>
			民族
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
			籍贯
		</th>
		<td>
			<html:select property="jg" value="${rs.jg }">
				<html:option value=""></html:option>
				<html:options collection="jgList" property="qxdm"
					labelProperty="qxmc" />
			</html:select>
		</td>
		<th>
			政治面貌
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
			出生日期
		</th>
		<td>
			<html:text property="csrq" readonly="true" value="${rs.csrq}" styleId="csrq"
					   onclick="showCalendar(this.id,'y-mm-dd')" 
			></html:text>
		</td>
		<th>
			身份证号
		</th>
		<td>
			<html:text property="sfzh" value="${rs.sfzh}" maxlength="18"></html:text>
		</td>
	</tr>
	<tr>
		<th>
			学位
		</th>
		<td>
			<html:text property="xw" maxlength="15" value="${rs.xw}"></html:text>
		</td>
		<th>
			学历
		</th>
		<td>
			<html:select property="xl" value="${rs.xl }">
				<html:option value=""></html:option>
				<html:option value="大专">大专</html:option>
				<html:option value="本科">本科</html:option>
				<html:option value="硕士">硕士</html:option>
				<html:option value="博士">博士</html:option>
				<html:option value="博士后">博士后</html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<th>
			毕业院校
		</th>
		<td>
			<html:text property="byyx" maxlength="15" value="${rs.byyx}"></html:text>
		</td>
		<th>
			所学专业
		</th>
		<td>
			<html:text property="sxzy" maxlength="15" value="${rs.sxzy}"></html:text>
		</td>
	</tr>
</tbody>
