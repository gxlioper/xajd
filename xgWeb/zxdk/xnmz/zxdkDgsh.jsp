<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function dgsh(shzt){
			refreshForm("/xgxt/zxdk_xnmz.do?method=zxdkDgsh&doType=save&shzt="+shzt);
		}
		
		function displayTbody(id){
			if($(id).style.display=="none"){
				$(id).style.display="";
			}else{
				$(id).style.display="none"
			}
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/zxdk_xnmz" method="post">
			<!-- ������ -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/zxdk_xnmz.do?method=zxdkSq" />
			<input type="hidden" name="guid" id="guid" value="${guid }" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<html:hidden property="shgw" styleId="shgw" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">

									<button class="button2" id="btn_bc" onclick="dgsh('ͨ��')">
										ͨ ��
									</button>
									<button class="button2" id="btn_bc" onclick="dgsh('��ͨ��')">
										��ͨ��
									</button>
									<button class="button2" id="btn_bc" onclick="dgsh('�˻�')">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead onclick="displayTbody('tbody_jbxx')">
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>

					<tbody id="tbody_jbxx">
						<tr>
							<th style="width:16%">
								ѧ��
							</th>
							<td style="width:34%">
								${rs.xh }
								<html:hidden name="rs" property="xh" styleId="xh" />
							</td>
							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�Ա�
							</th>
							<td style="width:34%">
								${rs.xb }
							</td>
							<th style="width:16%">
								�꼶
							</th>
							<td style="width:34%">
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td id="" style="width:34%">
								${rs.xymc }
							</td>

							<th>
								רҵ
							</th>
							<td id="" style="width:34%">
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�༶
							</th>
							<td style="width:34%">
								${rs.zymc }
							</td>
							<th style="width:16%">
								���֤��
							</th>
							<td style="width:34%">
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.mzmc }
							</td>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								${rs.csrq }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.nl }
							</td>
							<th style="width:16%">
								ѧ��
							</th>
							<td style="width:34%">
								${rs.xz }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								��ҵ����
							</th>
							<td style="width:34%">
								${rs.byny }
							</td>
							<th style="width:16%">
								��ϵ�绰
							</th>
							<td style="width:34%">
								${rs.lxdh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								OICQ��
							</th>
							<td style="width:34%">
								${rs.qqhm }
							</td>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								${rs.dzyx }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ѧ����ͥ��ַ
							</th>
							<td style="width:34%">
								${rs.jtdz }
							</td>
							<th style="width:16%">
								ѧ����ͥ��ϵ�绰
							</th>
							<td style="width:34%">
								${rs.jtdh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ѧ����ͥ��������
							</th>
							<td style="width:34%">
								${rs.jtyb }
							</td>
							<th style="width:16%">
								��ͥ�˾�������
							</th>
							<td style="width:34%">
								${rs.jtysr}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								${rs.hkxz }
							</td>
							<th style="width:16%">

							</th>
							<td style="width:34%">

							</td>
						</tr>

					</tbody>
					<thead onclick="displayTbody('tbody_jtxx')">
						<tr>
							<th colspan="4">
								<span>��ͥ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jtxx">
						<tr>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								${rs.fqxm }
							</td>
							<th style="width:16%">
								�������֤��
							</th>
							<td style="width:34%">
								${rs.fqsfzh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								���׹�����λ
							</th>
							<td style="width:34%">
								${rs.fqgzdw }
							</td>
							<th style="width:16%">
								������ϵ��ʽ
							</th>
							<td style="width:34%">
								${rs.fqlxfs }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ĸ������
							</th>
							<td style="width:34%">
								${rs.mqxm }
							</td>
							<th style="width:16%">
								ĸ�����֤��
							</th>
							<td style="width:34%">
								${rs.mqsfzh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ĸ�׹�����λ
							</th>
							<td style="width:34%">
								${rs.mqgzdw }
							</td>
							<th style="width:16%">
								ĸ����ϵ��ʽ
							</th>
							<td style="width:34%">
								${rs.mqlxfs }
							</td>
						</tr>
					</tbody>
					<thead onclick="displayTbody('tbody_lxrxx')">
						<tr>
							<th colspan="4">
								<span>��ϵ��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_lxrxx">
						<tr>
							<th style="width:16%">
								��ϵ������
							</th>
							<td style="width:34%">
								${rs.lxrxm }
							</td>
							<th style="width:16%">
								��ϵ�˼�ͥ��ַ
							</th>
							<td style="width:34%">
								${rs.lxrdz }
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�˹�����λ
							</th>
							<td>
								${rs.lxrgzdw }
							</td>
							<th>
								��ϵ�˹̶��绰
							</th>
							<td>
								${rs.lxrgddh }
							</td>
						</tr>
						<tr>
							<th>
								��ϵ���ƶ��绰
							</th>
							<td>
								${rs.lxryddh }
							</td>
							<th>

							</th>
							<td>
							</td>
						</tr>
					</tbody>
					<thead onclick="displayTbody('tbody_jzrxx')">
						<tr>
							<th colspan="4">
								<span>��֤��</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jzrxx">
						<tr>
							<th style="width:16%">
								��֤������
							</th>
							<td style="width:34%">
								${rs.jzrxm }
							</td>
							<th style="width:16%">
								��֤��֤����
							</th>
							<td style="width:34%">
								${rs.jzrzjh }
							</td>
						</tr>
						<tr>
							<th>
								��֤��֤�����ʹ���
							</th>
							<td>
								${rs.jzrzjlxdm }
							</td>
							<th>
								��֤�˼�ͥ��ַ
							</th>
							<td>
								${rs.jzrdz }
							</td>
						</tr>
						<tr>
							<th>
								��֤���������˹�ϵ
							</th>
							<td>
								${rs.jzrgx }
							</td>
							<th>
								��֤���ʱ�
							</th>
							<td>
								${rs.jzryb }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								��֤�˵绰
							</th>
							<td style="width:34%">
								${rs.jzrdh }
							</td>
							<th style="width:16%">

							</th>
							<td style="width:34%">
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								��֤�����
							</th>
							<td colspan="3" style="width:34%">
								<html:textarea name='rs' property='jzryj' styleId="jzryj"
									style="word-break:break-all;width:99%" 
									onblur="chLeng(this,250);" rows='4' />
							</td>
						</tr>

					</tbody>
					<thead onclick="displayTbody('tbody_dkxx')">
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_dkxx">
						<tr>
							<th style="width:16%">
								��һѧ��ѧ�Ѵ���
							</th>
							<td style="width:34%">
								${rs.onexnxfje }
							</td>
							<th style="width:16%">
								��һѧ�����ҷѴ���
							</th>
							<td style="width:34%">
								${rs.onexnqsfje }
							</td>

						</tr>
						<tr>
							<th>
								�ڶ�ѧ��ѧ�Ѵ���
							</th>
							<td>
								${rs.twoxnxfje }
							</td>
							<th>
								�ڶ�ѧ�����ҷѴ���
							</th>
							<td>
								${rs.twoxnqsfje }
							</td>
						</tr>
						<tr>
							<th>
								����ѧ��ѧ�Ѵ���
							</th>
							<td>
								${rs.threexnxfje }
							</td>
							<th>
								����ѧ�����ҷѴ���
							</th>
							<td>
								${rs.threexnqsfje }
							</td>

						</tr>
						<tr>
							<th>
								����ѧ��ѧ�Ѵ���
							</th>
							<td>
								${rs.fourxnxfje }
							</td>
							<th>
								����ѧ�����ҷѴ���
							</th>
							<td>
								${rs.fourxnqsfje }
							</td>
						</tr>
						<tr>
							<th>
								����ѧ��ѧ�Ѵ���
							</th>
							<td>
								${rs.fivexnxfje }
							</td>
							<th>
								����ѧ�����ҷѴ���
							</th>
							<td>
								${rs.fivexnqsfje }
							</td>

						</tr>
						<tr>

							<th>
								�����ܽ��
							</th>
							<td>
								${rs.jzryb}
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>

					</tbody>
					<thead>

					</thead>
					<tbody>
						<logic:iterate name="shxxList" id="shxx">
							<logic:notEmpty name="shxx" property="xtgwid">
								<tr>
									<th>
										��˸�λ
									</th>
									<td>
										${shxx.mc }
									</td>
									<th>
										��˽��
									</th>
									<td>
										${shxx.shzt }
									</td>
								</tr>
								<tr>
									<th>
										������
									</th>
									<td colspan="3">
										<logic:notEqual name="shxx" property="xtgwid" value="${shgw}">
											<html:textarea name='shxx' property='shyj' styleId="shyj"
												style="word-break:break-all;width:99%" disabled="true"
												onblur="chLeng(this,250);" rows='4' />
										</logic:notEqual>
									
										<logic:equal name="shxx" property="xtgwid" value="${shgw}">
											<html:textarea name='shxx' property='shyj' styleId="shyj"
												style="word-break:break-all;width:99%" 
												onblur="chLeng(this,250);" rows='4' />
										</logic:equal>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:iterate>
					</tbody>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
