<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<div class="open_win01">
	<table align="center" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>ͬ����ʦ���û���Ϣ��</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>
					�û���
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
					������λ
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
						<font color="red">ע�����û����Ѵ��ڸ��û�������²��Ḳ��ԭ���û�</font>
						<button type="button" onclick="tbTeaInfo()">
							ȷ��
						</button>
						<button type="button" onclick="closeWindown();return false;">
							�ر�
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
