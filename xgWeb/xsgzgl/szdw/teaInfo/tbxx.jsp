<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<div class="open_win01">
	<table align="center" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>同步教师到用户信息库</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>
					用户组
				</th>
				<td>
					<html:select property="zdm" styleId="zdm"
						style="width:120px;">
						<html:option value=""></html:option>
						<html:options collection="yhzList" property="zdm"
							labelProperty="zmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					所属单位
				</th>
				<td>
					<html:select property="dwdm" styleId="dwdm"
						style="width:120px;">
						<html:option value=""></html:option>
						<html:options collection="dwList" property="dwdm"
							labelProperty="dwmc" />
					</html:select>
				</td>
			</tr>
		</tbody>

		<tfoot>
			<tr>
				<td colspan='2'>
					<div class='btn'>
						<font color="red">注：在用户库已存在该用户的情况下不会覆盖原有用户</font>
						<button type="button" onclick="tbTeaInfo()">
							确定
						</button>
						<button type="button" onclick="closeWindown();return false;">
							关闭
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
