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
	<html:form action="/gwfb.do?method=gwxx" method="post">
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
					<th>У��</th>
					<td>
						${rs.xqmc}
					</td>
					<th>��λ����</th>
					<td>
						${rs.gwdm}
					</td>
				</tr>
				<tr>
					<th>��λ����</th>
					<td>
						${rs.gwxzmc}
					</td>
					<th>���뵥λ</th>
					<td>
						${rs.yrdwmc}
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
					<th>��������</th>
					<td height="22" align="left">
						${rs.xyrs}
					</td>
					<th>����ʹ������</th>
					<td>
						${rs.sqsyrs}
					</td>
				</tr>
				<tr>
					<th>ʹ����������</th>
					<td>
						${rs.syknss}
					</td>
					<th>����ʹ����������</th>
					<td>
						${rs.sqsyknss}
					</td>					
				</tr>
				<tr>
					<th>�Ƴ귽ʽ</th>
					<td>
						${rs.jcfsmc}
					</td>
					<th>���鱨���׼</th>
					<td>
						${rs.jybcbz}
						 Ԫ(${rs.jcfsmc})
					</td>
				</tr>
				<tr>
					<th>��ϵ�绰</th>
					<td>
						${rs.lxdh}
					</td>
					<th>���������׼</th>
					<td>
						${rs.spbcbz}
						 Ԫ(${rs.jcfsmc})
					</td>
				</tr>
				<tr>
					<th>����ʱ��</th>
					<td colspan="3">
						${rs.gzsj}						
						<span id="gzsjDw"></span>
					</td>							
											
				</tr>
				<tr>
					<th>��������</th>
					<td colspan="3">
						${rs.gznr}
					</td>
				</tr>		
				<tr>
					<th>��λҪ��</th>
					<td colspan="3">
						${rs.gwtsyq}
					</td>
				</tr>
				<tr>
					<th>��ԱҪ��</th>
					<td colspan="3">
						${rs.ryyq}
					</td>
				</tr>
				<tr>
					<th>���뱨��</th>
					<td colspan="3">
						${rs.sqbg}
					</td>
				</tr>
				<tr>
					<th>���뵥λ���</th>
					<td colspan="3">
						${rs.sqdwyj}
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
