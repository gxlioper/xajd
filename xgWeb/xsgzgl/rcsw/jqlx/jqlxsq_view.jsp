<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

	</head>
	<body>

		<html:form action="/rcsw_xszbb_bbshgl" method="post" styleId="jqlxModel">
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<html:hidden property="sqid" styleId="sqid" name="model" />

			<div style="tab;overflow-x:hidden;overflow-y:auto;height:347px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<logic:equal value="10277" name="xxdm">
						<tr>
							<th>�Ƿ�ƶ����</th>
							<td colspan="3">
									${sfkns }
							</td>
						</tr>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">ѧ��</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">ѧ��</th>
							<td width="30%">
								${rs.xqmc}
							</td>
					    </tr>
					    <logic:equal value="10344" name="xxdm">
					    	<tr>
					    	<%--
					    		<th width="20%">
									ԭס��԰��
								</th>
								<td>
									${rs.yqmc}
								</td>
								<th width="20%">
									ԭ����¥��
								</th>
								<td>
									${rs.ldmc}
								</td>
					    	</tr>
					    	<tr>
					    		<th width="20%">
									ԭ���Һ�
								</th>
								<td>
									${rs.qsh}
								</td>
							--%>
								<th width="20%">
									ԭס����
								</th>
								<td>
									${rs.yzqs}
								</td>
								<th>
									�ֻ�����
								</th>
								<td>
									${rs.dwlxdh}
								</td>
					    	</tr>
					    	<tr>
								<th>
									��ͥ��ַ
								</th>
								<td>
									${rs.lxdw}
								</td>
								<th>
									��ͥ��ϵ�˺��ֻ�
								</th>
								<td>
									${rs.bz}
								</td>
							</tr>
							<tr>
								<th>
									����԰��
								</th>
								<td>
									${rs.lxxqmc}
								</td>
								<th>
									��������¥��
								</th>
								<td>
									${rs.lxldmc}
								</td>
							</tr>
							<tr>
								<th>
									�������Һ�
								</th>
								<td>
									${rs.lxqs}
								</td>
							</tr>
					    </logic:equal>
					    <tr>
							<th>��У��ʼʱ��</th>
							<td>
								${rs.lxkssj}
							</td>
							<th>��У��ֹʱ��</th>
							<td>
								${rs.lxjzsj}
							</td>
					    </tr>
					    <logic:notEqual value="12309" name="xxdm">
					    <tr>
					    	<th>��У��ʼʱ���</th>
							<td>
								${rs.lxkssj2}
							</td>
							<th>��У��ֹʱ���</th>
							<td>
								${rs.lxjzsj2}
							</td>
						</tr>
						<logic:equal value="10344" name="xxdm">
							<tr>
						    	<th>�Ƿ���У������</th>
								<td>
									${rs.sfgcj}
								</td>
								<th>������У����</th>
								<td>
									${rs.sqlxtj}
								</td>
							</tr>
						</logic:equal>
						</logic:notEqual>
						<!-- ����ũҵ��ѧ��Уԭ����Ի�-->
						<logic:equal value="10364" name="xxdm">
							<tr>
								<th>��Уԭ��</th>
								<td colspan="3">
									${rs.lxyymc}
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="11488" name="xxdm">
						<tr>
							<th>��Уԭ��</th>
							<td>
								${rs.lxyymc}
							</td>
							<th><logic:equal value="3" name="rs" property="lxyy">
								��ְ��λ
							</logic:equal>
							<logic:equal value="7" name="rs" property="lxyy">
								��ͥ
							</logic:equal>��ϵ��</th>
							<td>
								${rs.dwlxr}
							</td>
						</tr>
						<tr>
							<th>��ϵ�绰</th>
							<td>
								${rs.dwlxdh}
							</td>
							<th>ԭס����</th>
							<td>
								${rs.yzqs}
							</td>
						</tr>
					    </logic:equal>
					    <logic:equal value="10530" name="xxdm">
							<tr>
								<th width="20%">
									��ϵ�绰
								</th>
								<td>
									${rs.jzlxdh}	
								</td>
								<logic:equal value="����" name = "jqlxV">
									<th width="20%">
										�Ƿ���У������
									</th>
									<td>
										${rs.sfgcj }
									</td>
								</logic:equal>
							</tr>
						</logic:equal>
					     <logic:equal value="11458" name="xxdm">
					    	<logic:equal value="����" name = "jqlxV">
							<tr>
								<th>
									�Ƿ����ҹ��
								</th>
								<td colspan="3">
									${rs.sfcnyf }
								</td>
				      		</tr>
							</logic:equal>
						</logic:equal>
						<logic:equal value="10277" name="xxdm">
					    	<logic:equal value="����" name = "jqlxV">
							<tr>
								<th>
									�Ƿ����ҹ��
								</th>
								<td colspan="3">
									${rs.sfcnyf }
								</td>
				      		</tr>
							</logic:equal>
						</logic:equal>
					    <logic:equal value="10351" name="xxdm">
					    	<logic:equal value="����" name = "jqlxV">
							<tr>
								<th>
									�Ƿ���У����
								</th>
								<td colspan="3">
									${rs.sflxgn }
								</td>
				      		</tr>
							<tr>
								<th>
									��ע
								</th>
								<td colspan="3">
									${rs.bz }
								</td>
				      		</tr>
							</logic:equal>
						</logic:equal>
						<% if("12861".equals(xxdm) || "10351".equals(xxdm)){ %>
							<tr>
								<th>
									�ҳ�����
								</th>
								<td>
									${rs.jzxm }
								</td>
								<th>
									�ҳ���ϵ�绰
								</th>
								<td>
									${rs.jzlxdh }
								</td>
				      		</tr>
						<% } %>
						<logic:equal value="10718" name="xxdm">
						<tr>
								<th>
									�Ƿ�ʳ������ʳ��
								</th>
								<td colspan="3">
									${rs.sfsyqzsw }
								</td>
				      		</tr>
						</logic:equal>
						<logic:equal value="10704" name="xxdm">
						<tr>
								<th>
									�Ƿ�ʳ������ʳ��
								</th>
								<td colspan="3">
									${rs.sfsyqzsw }
								</td>
				      		</tr>
						</logic:equal>
						<!-- �㽭��ýѧԺ���Ի���������У���ֶ�begin -->
						<logic:equal value="11647" name = "xxdm">
							<tr>
							<th>
								����У��
							</th>
							<td colspan="3">
								${rs.xxxqmc }
							</td>
			      		</tr>
						</logic:equal>
						<!-- �㽭��ýѧԺ���Ի���������У���ֶ�end -->
						<!-- ������ҽҩ���Ի�begin -->
						<logic:equal value="10026" name = "xxdm">
							<tr>
								<th>
									��УУ��
								</th>
								<td>
										${rs.xxxqmc }
								</td>
								<th>
									¥��
								</th>
								<td>
										${rs.ldmc }
								</td>
							</tr>
							<tr>
								<th>
									���Һ�
								</th>
								<td>
										${rs.qsh }
								</td>
							</tr>
						</logic:equal>
						<!-- ������ҽҩ���Ի�end -->
						<logic:notEqual name="xxdm" value="11488">
						<logic:notEqual name="xxdm" value="10026">
						<logic:notEqual name="xxdm" value="10344">
						<tr>
							<th>
								ԭס����
							</th>
							<td colspan="3">
								${rs.yzqs }
							</td>
			      		</tr>
			      		</logic:notEqual>
			      		</logic:notEqual>
						</logic:notEqual>
			      		<logic:equal value="3" name="rs" property="lxyy">
			      		<tr>
							<th>
								��ְ��λ
							</th>
							<td colspan="3">
								${rs.lxdw }
							</td>
			      		</tr>
			      		</logic:equal>
						<logic:notEqual name="xxdm" value="10026">
						<logic:notEqual name="xxdm" value="10344">
					    <tr>
							<th>��У����</th>
							</th>
							<td colspan="3">
								${rs.rzdz}
							</td>
					    </tr>
					    </logic:notEqual>
						</logic:notEqual>
							
						<logic:equal value="10351" name = "xxdm">
							<tr>
								<th>��������</th>
								<td colspan="3">
									${rs.lxsqlxmc}
								</td>
							</tr>		
						</logic:equal>
						<logic:notEqual name="xxdm" value="11488">
						<logic:notEqual name="xxdm" value="10364">
					    <tr>
							<th>��Уԭ��</th>
							</th>
							<td colspan="3">
								${rs.sqly}
							</td>
					    </tr>
					    </logic:notEqual>
					    </logic:notEqual>
					    <tr>
							<th align="right" style="width: 10%">
								������Ϣ
							</th>
							<td colspan="3">
									<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
									<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											var gid = "${rs.fjxx}";
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
									</script>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>

