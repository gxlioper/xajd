<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"prefix="logic"%>
<div class="open_win01" style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;" >
	<table align="center" class="formlist">
		<thead>
			<tr>
				<th>
					<span>���ò�ѯ��</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<table>
						<tbody>
							<tr>
								<logic:iterate id="col" name="cols" indexId="i">
									<%
										if (i % 5 == 0 && i != 0){
									 %>
									</tr>
									<tr>
									<%
										}
									 %>
									<td>
										<html:checkbox property="colList" value="${col.column_name}" >${col.comments}</html:checkbox>
									</td>
								</logic:iterate>
							</tr>
						</tbody>
					</table>
					
					
					<logic:present name="colList">
						<logic:iterate id="c" name="colList">
							<script defer>
								jQuery('input[name=colList][value=${c}]').attr('checked',true);							
							</script>
						</logic:iterate>
					</logic:present>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<div class="open_win01" style="width:100%;" >
	<table align="center" class="formlist">
		<tfoot>
			<tr>
				<td>
					<div class="bz">
						
					</div>
					<div class="btn">
						<button type="button" name="�� ��" onclick="jQuery('#search_go').click();">
							��&nbsp;&nbsp;��
						</button>
						<button type="button" name="ȡ��" onclick="closeWindown();return false;">
							ȡ&nbsp;&nbsp;��
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
