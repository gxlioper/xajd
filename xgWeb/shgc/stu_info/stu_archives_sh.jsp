<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<html:form action="/data_search" method="post">
		<input type="hidden" name="realTable" id="realTable" value="stu_archives_auditing"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>����ѧ������ - ת������ - �������</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
		    <thead>
		    	<tr>
		        	<th colspan="4"><span>�������</span></th>
		        </tr>
		    </thead>
		    <tbody>
		      <tr>
					<th>ѧ��</th>
					<td>
						${rs.xh}
						<input type="hidden" value="${rs.xh}" name="xh" id="xh"/>
					</td>
					<th>���</th>
					<td>
						${rs.nd}
						<input type="hidden" value="${rs.nd}" name="nd" id="nd"/>
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>
						${rs.xm}
					</td>
					<th>������������</th>
					<td>
						${rs.hkssqx}
					</td>
				</tr>				
				<tr>
					<th>�꼶</th>
					<td>
						${rs.nj}
					</td>
					<th>���������ֵ�</th>
					<td>
						${rs.hkssjd}
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc}
					</td>
					<th>������ϸ��ַ</th>
					<td>
						${rs.hkxxdz}
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						${rs.zymc}
					</td>
					<th>ת����λ����</th>
					<td>
						${rs.zddwmc}
					</td>
					
				</tr>
				<tr>
					<th>�༶</th>
					<td>
						${rs.bjmc}
					</td>
					<th>ת����λ��ַ</th>
					<td>
						${rs.zddwdz}
					</td>	
				</tr>
				<tr>
					<th>��������</th>
					<td>
						${rs.sqrq}
					</td>
					<th>��������</th>
					<td>
						${rs.sqly}
					</td>	
				</tr>
				<tr>
					<th>���</th>
					<td colspan="3">
					<html:select property="yesNo" name="rs">
							<html:option value="δ���">δ���</html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
						</html:select>
					</td>
				</tr>	
				<tr>					
					<th>��ע</th>
					<td colspan="3">
						<html:textarea property="bz" name="rs" cols="66" style="width:680px" rows="4"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz"></div>
			          <div class="btn">
						<button class="btn_bc"
							onclick="viewAdd('/xgxt/abroad_query.do?act=txsq_sh&doType=save','add');"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						<button class="btn_gb" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				if(window.dialogArguments){
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:equal>
	</body>
</html>
