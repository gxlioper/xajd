<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
</head>
<body>
	<html:form action="/gwfb.do?method=xwgwfb" method="post">
		<input type="hidden" id="path" name="path" value="${path}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" class="formlist" id="rsT">
				<thead>
					<tr>
						<th colspan="4">
							<span>��λ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>��Ƹ��˾</th>
					<td>
						${rs.yrdwmc}
					</td>
					<th>��λ����</th>
					<td>
						${rs.gwdm}
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						${rs.xn}
					</td>
					<th>���</th>
					<td>
						${rs.nd}
					</td>
				</tr>
				<tr>
					<th>������ʼ����</th>
					<td>
						${rs.gzksrq}
					</td>
					<th>������������</th>
					<td>
						${rs.gzjsrq}
					</td>
				</tr>
				<tr>
					<th>��Ƹ����</th>
					<td height="22" align="left">
						${rs.xyrs}
					</td>
					<th>��ϵ�绰</th>
					<td>
						${rs.lxdh}
					</td>	
				</tr>
				<tr>
					<th>�Ƴ귽ʽ</th>
					<td>
						${rs.jcfsmc}
					</td>
					<th>�����׼</th>
					<td>
						${rs.jybcbz}Ԫ(${rs.jcfsmc})
					</td>
				</tr>
				<tr>
					<th>����ʱ��</th>
					<td>
						${rs.mssj}
					</td>
					<th>���Եص�</th>
					<td>
						${rs.msdd}
					</td>
				</tr>
				<tr>
					<th>����ʱ��</th>
					<td>
						${rs.gzsj}
					</td>							
					<th>�����ص�</th>
					<td>
						${rs.gzdd}
					</td>						
				</tr>
				<tr>
					<th>��������</th>
					<td colspan="3">
						${rs.gznr}
					</td>
				</tr>		
				<tr>
					<th>��ƸҪ��</th>
					<td colspan="3">
						${rs.gwtsyq}
					</td>
				</tr>
								
				<tr>
					<th>��ע</th>
					<td colspan="3">
						${rs.bz}
					</td>
				</tr>				
				</tbody>
				<!--��ʾ�μӸ�λ��ѧ���б�-->
				<%@ include file="/qgzx/gwxsxx.jsp"%>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
							<button type="button"  id="buttonClose" onclick="Close();return false;">
								�ر�
							</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
	</html:form>
</body>
</html>
