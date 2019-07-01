<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwsh.js" defer="defer"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
		<script type="text/javascript">
		jQuery(document).ready(function(){
			if("10351"==jQuery("#xxdm").val()){
				changeYxssz();
			}
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.gwdm}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splcid}&shid=${rs.shid}");
		});
		</script>
	</head>
	<body>
		<html:form action="/qgzx_gwglnew" styleId="gwshform" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="gwxzmc" id="gwxzmc" value="${rs.gwxzmc }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xn" id ="xn" value ="${rs.xn}"/>
			<input type="hidden" name="yrdwdm" id ="yrdwdm" value ="${rs.yrdwdm}"/>
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<input type="hidden"  id="xqrs" value="${rs.xqrs }"/>
			<input type="hidden"  id="xxdm" value="${xxdm}"/>
			<input type="hidden"  id="qgzq" name="qgzq" value="${cs.qgzq}" />
			<input type="hidden" id="splcid" name="splcid" value="${rs.splcid}" />
			<input type="hidden" id="gwdm" name="gwdm" value="${rs.gwdm}" />
			<input type="hidden" id="sqr" name="sqr" value="${rs.sqrzgh}" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>��λ������Ϣ</span>
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
							<td width="34%" colspan="3" >
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
							
							<td width="34%" colspan="3" >
								<html:hidden name="rs" property="yrdwdm" styleId="yrbm"/>
								${rs.yrdwmc}
							</td>
						</tr>
						
						
						
						
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th width="16%">
								��λ����
							</th>
							<td width="34%" colspan="3" >
								${rs.gwmc }
							</td>
							<th width="16%">
								��λ����
							</th>
							<td width="34%" colspan="3" >
								<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:200px" >
									<html:option value="">---------��ѡ��---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%" colspan="3" >
								${rs.xqrs }(��)
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%" colspan="3">
								<html:text name="rs" property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)"></html:text>  (��)
							</td>
						</tr>
						<tr>
							<th>��Чʱ����</th>
							<td colspan="3">
								<logic:present name="yxsszList">
									<logic:iterate id="yxsszMap" name="yxsszList" >
										<html:radio name="rs" property="yxssz" onclick="changeYxssz();" value="${yxsszMap.dm}">${yxsszMap.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
								<%-- ${rs.yxsszmc} --%>
							</td>
							<th>�Ƿ��ܸ�λ����������</th>
							<td colspan="3">
								<logic:present name="isnotList">
									<logic:iterate id="o" name="isnotList" >
										<html:radio name="rs" property="sfsgwsqsxz" onclick="" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
							<logic:equal name="xxdm" value="10351">
								<tr>
									<th>��ϵ��</th>
									<td colspan="3" >
										${rs.lxr}
									</td>
									<th>��ϵ�绰</th>
									<td colspan="3" >
										${rs.lxdh}
									</td>

								</tr>
							</logic:equal>
						<tr>
							<th><span class="red">*</span>��λ��ʼʱ��</th>
							<td colspan="3">
								<html:text name="rs" property="gwkssj" styleId="gwkssj" size="10" readonly="true"></html:text>
							</td>
							<th id="gwjssj_th"></th>
							<td id="gwjssj_td" colspan="3">
								<html:text name="rs" property="gwjssj" styleId="gwjssj" size="10" readonly="true"></html:text>
							</td>
						</tr>
						</logic:equal>
						
						
						<logic:notEqual value="10351" name="xxdm">
						<tr>
							<th width="16%">
								��λ����
							</th>
							<td width="34%" colspan="3" >
								${rs.gwmc }
							</td>
							<th width="16%">
								��λ����
							</th>
							<td width="34%" colspan="3" >
								${rs.gwxzmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%" colspan="3" >
								${rs.xqrs }(��)
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%" colspan="3">
								${rs.knsrs }(��)
							</td>
						</tr>
					<logic:equal value="10511" name="xxdm">
						<tr>
						   <th width="16%">���˵�λ���</th>
							<td width="34%" colspan="3" >
								${rs.zxdwlbmc}
							</td>
							<th width="16%">�ʽ���Դ</th>
							<td width="34%" colspan="3">
								${rs.zjlymc}
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>��Чʱ����</th>
							<td colspan="3">
								${rs.yxsszmc}
							</td>
							<th>�Ƿ��ܸ�λ����������</th>
							<td colspan="3">
								${rs.sfsgwsqsxzmc}
							</td>
						</tr>
						<logic:equal value="11488" name="xxdm">
							<tr>
								<th>��λ����</th>
								<td colspan="3">
									${rs.gwlx}
								</td>
								<th>��ѧ������Ա</th>
					            <td colspan="3">
					            	${rs.fzls}
					            </td>
							</tr>
							<tr>
								<th>��ϵ�绰</th>
								<td colspan="3">${rs.lxdh}</td>
								<th></th>
								<td colspan="3"></td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
						<tr>
							<th>��������</th>
							<td colspan="3">
								<html:hidden name="rs" property="ssbm" styleId="ssbm"/>
								${rs.ssbmmc}
							</td>
							<th>������ʦ</th>
				            <td colspan="3">
				            	${rs.fzlsxm}
				            </td>
						</tr>
						<tr>
							<th>��ϵ�绰</th>
							<td colspan="3">${rs.lxdh}</td>
							<th>������ʦ�칫��</th>
				            <td colspan="3">
				            	${rs.lsbgs}
				            </td>
						</tr>
						</logic:equal>
						<tr>
							<th>��λ��Чʱ</th>
							<td colspan="7">
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
						</logic:notEqual>
						<logic:equal value="12309" name="xxdm">
						<tr>
						<th><span class="red">*</span>��λ����׼</th>
							<td colspan="3">
								<html:text name="rs" property="gwcjbz" readonly ="true" styleId="gwcjbz" style="width:80px"></html:text> Ԫ/Сʱ
							</td>
							<th><span class="red">*</span>���ѻ���</th>
							<td colspan="3">
								<html:text name="rs" property="jfhb" styleId="jfhb" style="width:80px" onblur="checkJe(this)"></html:text> Ԫ/Сʱ
							</td>
						</tr>
						<tr>
							<th>�Գ�</th>
							<td colspan="6">
								<html:text  name="rs" property="zc" styleId="zc" style="width:80px"  onblur="checkJe(this)"></html:text> Ԫ/Сʱ
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
							<td colspan="7">
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
								<td width="34%" colspan="7">
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
						<tr>
							<th width="16%">
								������
							</th>
							<td width="34%" colspan="3" >
								${rs.sqr }
							</td>
							<th width="16%">
								����ʱ��
							</th>
							
							<td width="34%" colspan="3">
								${rs.sqsj }
							</td>
						</tr>
						<logic:equal value="10344" name="xxdm">
							<tr>
								<th align="right" >
									����У��
								</th>
								<td colspan="7" >
									${rs.ssxqmc }
								</td>
							</tr>
							<tr>
								<th align="right" >
									�����ص�
								</th>
								<td colspan="7" >
									${rs.gzdd }
								</td>
							</tr>
							<tr>
								<th align="right" >
									����ʱ��
								</th>
								<td colspan="7" >
									${rs.gzsj }
								</td>
							</tr>
							<tr>
								<th align="right" >
									��������
								</th>
								<td colspan="7" >
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
						<tr style="height:40px">
							<th align="right" >
								<logic:equal name="xxdm" value="10344">
								   �Ͷ�ǿ�ȼ�����Ҫ��
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								  ��λ��ԱҪ��
							    </logic:notEqual>
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwryyq }
							</td>
							
						</tr>
						<tr style="height:40px">
							<th align="right" >
								��λԤ����ԱЧ��
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwyqryxg }
							</td>
							
						</tr>
						<tr style="height:40px">
							<th align="right" >
								<logic:equal name="xxdm" value="10344">
								�ù�����
							</logic:equal>
							<logic:notEqual name="xxdm" value="10344">
								��ע
							</logic:notEqual>
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="8">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8" id="shlccx">
							
							</td>
						</tr>
				
					</tbody>
				<thead>
					<tr>
						<th colspan="8">
							<span>�����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th >
						��˽��
					</th>
					<td id="shjgSpan" colspan="7">
						
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> ������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="7">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cdgl&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
				</table>
			</div>
			<div style="height: 30px">
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="gwxxshBc();return false;">
										����
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

