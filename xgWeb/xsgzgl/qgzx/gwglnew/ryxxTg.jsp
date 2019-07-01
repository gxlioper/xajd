<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
	</head>
	<body>
		<html:form action="/qgzx_gwglnew" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="gwxzmc" id="gwxzmc" value="${rs.gwxzmc }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" name="xhs" id="xhs" value="" />
			<input type="hidden" name="sgsjs" id="sgsjs" value="" />
			<input type="hidden" name="sqbhs" id="sqbhs" value="" />
			<input type="hidden" name="xhTal" id="xhTal" value="" />
			<input type="hidden"  id="xxdm" value="${xxdm}"/>			
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--%><div style="height:450px;overflow-x:hidden;overflow-y:auto;">
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
									<input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
									<input type="hidden" id="xq" name="xq" value=""/>
									${rs.xn }
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									<input type="hidden" id="xq" name="xq" value="${rs.xq }"/>
									<input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
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
								${rs.xqrs}(�ڸ�������<font class="red" >${rs.zgrs}</font>)
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
						<tr>
							<th>��λ��Чʱ</th>
							<td colspan="6">
								${rs.gwyxs}
							</td>
						</tr>
						<logic:equal value="12867" name="xxdm">
						<tr>
							<th>��λ�����</th>
							<td colspan="3">
								${rs.gwshr}
							</td>
							<th>��λ���������</th>
							<td colspan="3">
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
				<div style="height:240px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="zgryHtml">
						<tbody>
							<tr>
								<td>�ø�λ���ڸ���Ա�������˸ڣ�</td>
							</tr>
						</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="zgryHtml">
					<thead>
						<tr>
							<td ><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td >ѧ��</td>
							<td >����</td>
							<td >�༶</td>
							<td >�Ƿ�������</td>
							<td >�Ƿ���У</td>
							<td >Ŀǰ�ڹ���λ��</td>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.zgryHtml }
					</tbody>
					</logic:notEmpty>
				</table>
				</div>
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<logic:notEmpty name="rs" property="zgryHtml">
									<button type="button" onclick="tgRyxx()">
										�˸�
									</button>
									</logic:notEmpty>
									<button type="button" onclick="refreshParentTg();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="xszgxxDiv" style="display: none;">
				<%@ include file="/xsgzgl/qgzx/gwglnew/ryxxCk.jsp"%>
			</div>
		</html:form>
	</body>
</html>

