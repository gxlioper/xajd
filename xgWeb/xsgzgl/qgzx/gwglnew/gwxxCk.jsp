<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script>
		jQuery(document).ready(function(){
			var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
			// �������� ���õ�н������
			var gwzgcjsx=jQuery("#gwzgcjsx").val();
			var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
			// ��λ���ó������
			var gwcjsx=jQuery("#gwcjsx").val();
			// ����˸�λδ����
			if(gwcjsx==""){
				jQuery("#gwcjsx").val(gwzgcjsx);
				jQuery("#gwcjsxh").text(gwzgcjsx);
			}
			if("no"==sfsdgwcjsx){
				jQuery("#gwcjsxTr").hide();
			}else{
				jQuery("#gwcjsxTr").show();
				jQuery("#gwcjsx").hide();
				jQuery("#gwcjsxh").show();
				jQuery("#sxcolor").hide();
			}
			if("10704"==jQuery("#xxdm").val()&&'${rs.gwxzmc}'=="�̶���"){
			jQuery("#gwcjsxTr").hide();
			jQuery("#gdgcjbzTr").show();
		}
		});
		//��ʾ�˸���Ϣ
		function showTgxsxx(obj){
			jQuery(obj).parent().parent().hide();
			jQuery("#div_tgxsxx").show();
		}

		//�°�鿴������
		function zxsxxView(xh) {
			showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
		}
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwglnew" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden"  id="xxdm" value="${xxdm}"/>
						
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<div style="height:450px;overflow-x:hidden;overflow-y:auto;padding-right:3px;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<logic:equal name="cs" property="qgzq" value="xn">
								         ѧ��
                                </logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									ѧ��ѧ��
								</logic:equal>
							</th>
							<td width="34%">
								<logic:equal name="cs" property="qgzq" value="xn">
									${rs.xn }
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									${rs.xn }&nbsp;&nbsp;&nbsp;${rs.xqmc }
								</logic:equal>
							</td>
							<th width="16%">
								���˲���
							</th>
							
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%">
								${rs.gwmc}
							</td>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%">
								${rs.gwxzmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${rs.xqrs}(�ڸ�������<font color="red" >${rs.zgrs}</font>)
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${rs.knsrs}(������<font class="red" >${rs.zgknsrs}</font>)
							</td>
						</tr>
						<logic:equal value="10511" name="xxdm">
						<tr>
						   <th>���˵�λ���</th>
							<td >
								${rs.zxdwlbmc}
							</td>
							<th>�ʽ���Դ</th>
							<td >
								${rs.zjlymc}
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>��Чʱ����</th>
							<td>
								${rs.yxsszmc}
							</td>
							<th>�Ƿ��ܸ�λ����������</th>
							<td>
								${rs.sfsgwsqsxzmc}
							</td>
						</tr>
						<logic:equal name="xxdm" value="11488">
							<tr>
								<th>��λ����</th>
								<td >
									${rs.gwlx}
								</td>
								<th>��ѧ������Ա</th>
					            <td >
					            	${rs.fzls}
					            </td>
							</tr>
							<tr>
								<th>��ϵ�绰</th>
								<td >${rs.lxdh}</td>
								<th></th>
								<td ></td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
						<tr>
							<th>��������</th>
							<td >
								${rs.ssbmmc}
							</td>
							<th>������ʦ</th>
				            <td >
				               ${rs.fzlsxm}
				            </td>
						</tr>
						<tr>
							<th>��ϵ�绰</th>
							<td >
								${rs.lxdh}
							</td>
							<th>������ʦ�칫��</th>
				            <td >
				            	${rs.lsbgs}
				            </td>
						</tr>
						
						</logic:equal>
						<logic:equal name="xxdm" value="10351">
							<tr>
								<th>��ϵ��</th>
								<td>
									${rs.lxr}
								</td>
								<th>��ϵ�绰</th>
								<td>${rs.lxdh}</td>

							</tr>
						</logic:equal>
						<tr>
							<th>��λ��Чʱ</th>
							<td colspan="6">
								${rs.gwyxs}
							</td>
						</tr>
						<logic:equal value="12867" name="xxdm">
						<tr>
							<th>��λ�����</th>
							<td>
								${rs.gwshr}
							</td>
							<th>��λ���������</th>
							<td>
								${rs.gwshrxm}
							</td>
						</tr>	
						</logic:equal>
						<logic:equal value="10052" name="xxdm">
						<tr>
							<th width="16%">
								��λ���
							</th>
							<td width="34%" colspan="6">
								${rs.gwxh}
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="12309" name="xxdm">
						<tr>
						<th>��λ����׼</th>
							<td >
								${rs.gwcjbz} Ԫ/Сʱ
							</td>
							<th>���ѻ���</th>
							<td >
								${rs.jfhb} Ԫ/Сʱ
							</td>
						</tr>
						<tr>
							<th>�Գ�</th>
							<td colspan="6">
								${rs.zc} Ԫ/Сʱ
							</td>
						</tr>
						</logic:equal>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>
								<logic:equal name="xxdm" value="10344">
								 �±���Ԥ��
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								��λ�������
							    </logic:notEqual>
							</th>
							<td width="34%" colspan="6">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>Ԫ/��  &nbsp;&nbsp;(�ø�λÿ��ÿ�³������)</span>
							</td>
						</tr>
						<logic:equal value="10704" name="xxdm">
							<tr id="gdgcjbzTr" style="display:none">
								<th width="16%">
									�̶��ڳ���׼
								</th>
								<td width="34%" colspan="6">
									<span id="gdgcjbzSpan">${jcpz.gdgcjbz}</span>
									<span>Ԫ/��  &nbsp;&nbsp;(�̶���ÿ�³���׼)</span>
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="10351" name="xxdm">
							<tr>
								<th>
									��λ���뿪��ѧԺ
								</th>							
								<td colspan="7">
									${rs.sqxyms}
								</td>						
							</tr>
						</logic:equal>
						
								<logic:equal value="10344" name="xxdm">
							<tr>
								<th align="right" >
									����У��
								</th>
								<td colspan="3" >
									${rs.ssxqmc }
								</td>
							</tr>
							<tr>
								<th align="right" >
									�����ص�
								</th>
								<td colspan="3" >
									${rs.gzdd }
								</td>
							</tr>
							<tr>
								<th align="right" >
									����ʱ��
								</th>
								<td colspan="3" >
									${rs.gzsj }
								</td>
							</tr>
							<tr>
								<th align="right" >
									��������
								</th>
								<td colspan="3" >
									${rs.gznr }
								</td>
							</tr>
							
						</logic:equal>
						<logic:notEqual value="10344" name="xxdm">
							<tr >
								<th align="right" >
									��λ����
								</th>
								<td colspan="7" style="word-break:break-all;width:97%">
									${rs.gwms }
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th align="right" >
								<logic:equal name="xxdm" value="10344">
								   �Ͷ�ǿ�ȼ�����Ҫ��
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								  ��λ��ԱҪ��
							    </logic:notEqual>
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwryyq}
							</td>
							
						</tr>
						<tr>
							<th align="right" >
								��λԤ����ԱЧ��
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwyqryxg}
							</td>
						</tr>
						<tr>
							<th align="right" >
								<logic:equal name="xxdm" value="10344">
									�ù�����
								</logic:equal>
								<logic:notEqual name="xxdm" value="10344">
									��ע
								</logic:notEqual>
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.bz}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ڸ�ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
				</table>
				<!-- <div style="height:250px;overflow-y:auto;"> -->
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="zgryHtml">
					<tbody>
						<tr>
							<td>�ø�λ���ڸ���Ա��</td>
						</tr>
						<tr>
							<td>
								<a href="#" onclick="showTgxsxx(this);return false;">�鿴�˸�ѧ����Ϣ...</a>
							</td>
						</tr>
					</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="zgryHtml">
					<thead>
						<tr>
							<td width='10px'>�к�</td>
							<td width='15%'>ѧ��</td>
							<td width='15%'>����</td>
							<td width='25%'>�༶</td>
							<td width='12%'>�Ƿ�������</td>
							<td width='10%'>�Ƿ���У</td>
							<logic:equal value="12036" name='xxdm'>
							<td width='10%'>�ϸ��·�</td>
							</logic:equal>
							<logic:notEqual value="12036" name='xxdm'>
							<td width='10%'>�ϸ�����</td>
							</logic:notEqual>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.zgryHtml }
						<tr>
							<td colspan="7">
								<a href="#" onclick="showTgxsxx(this);return false;">�鿴�˸�ѧ����Ϣ...</a>
							</td>
						</tr>
					</tbody>
					</logic:notEmpty>
				</table>
				<div id="div_tgxsxx" style="display: none;">
					<table border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�˸�ѧ����Ϣ</span>
								</th>
							</tr>
						</thead>
					</table>
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<logic:empty name="rs" property="tgryHtml">
						<tbody>
							<tr>
								<td colspan="4">�ø�λ���˸���Ա��</td>
							</tr>
						</tbody>
						</logic:empty>
						<logic:notEmpty name="rs" property="tgryHtml">
						<thead>
							<tr>
								<td width='10px'>�к�</td>
								<td width='15%'>ѧ��</td>
								<td width='15%'>����</td>
								<td width='25%'>�༶</td>
								<td width='15%'>�Ƿ�������</td>
								<td width='15%'>�Ƿ���У</td>
								<td width='15%'>�ϸ�����</td>
								<td width='15%'>�˸�����</td>
							</tr>
						</thead>
						<tbody id="tbody_tgryxx">
							${rs.tgryHtml }
						</tbody>
						</logic:notEmpty>
					</table>
					</div>
				<!--</div>-->
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="8">
							
								<div class="btn">
									<button type="button" onclick="iFClose();">
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

