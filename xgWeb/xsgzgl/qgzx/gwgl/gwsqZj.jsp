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
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/qgzx/gwgl/gwsq.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function(){
				initData();
				var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
				//�������� ���õ�н������
				var gwzgcjsx=jQuery("#gwzgcjsx").val();
				var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
				//��λ���ó������
				var gwcjsx=jQuery("#gwcjsx").val();
				//����˸�λδ����
				if(gwcjsx==""){
					jQuery("#gwcjsx").val(gwzgcjsx);
					jQuery("#gwcjsxh").text(gwzgcjsx);
				}
				if("no"==sfsdgwcjsx){
					jQuery("#gwcjsxTr").hide();
				}else{
					jQuery("#gwcjsxTr").show();
					if("no"==sfkgggwcjsx){
						jQuery("#gwcjsx").hide();
						jQuery("#gwcjsxh").show();
						jQuery("#sxcolor").hide();
					}else{
						jQuery("#gwcjsx").show();
						jQuery("#gwcjsxh").hide();
						jQuery("#sxcolor").show();
					}
				}
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzx_gwgl" method="post">
		<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
		<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
		<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--%><div style='tab��width:100%;overflow-x:hidden;overflow-y:auto;height:555px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							
							<td width="34%">
								<input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
								${rs.xn }
							</td>
							<th width="16%">
								���˲���
							</th>
							
							<td width="34%" >
								<input type="hidden" id="yrbm" name="yrbm" value="${rs.yrbm }"/>
								${rs.yrdwmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��λ����
							</th>
							
							<td width="34%">
								
								<html:text property="gwmc" styleId="gwmc" maxlength="50" style="width:200px" ></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>��λ����
							</th>
							
							<td width="34%">
								<html:select property="gwxzdm" styleId="gwxzdm" style="width:200px" >
									<html:option value="">---------��ѡ��---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��������
							</th>
							
							<td width="34%">
								<html:text property="xqrs" styleId="xqrs" size="10" maxlength="3" onblur="checkXqrs(this)"></html:text>  (��)
							</td>
							<th width="16%">
								<font color="red">*</font>��������
							</th>
							<td width="34%">
								<html:text property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)" value="0"></html:text>  (��)
							</td>
						</tr>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>��λ�������
							</th>
							<td width="34%" colspan="3">
								<html:text property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh"></span>
								<span>Ԫ/��  &nbsp;&nbsp;(�ø�λÿ��ÿ�³������)</span>
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<font color="red">*</font>��λ����<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='gwms' styleId="gwms" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<font color="red">*</font>��λ��ԱҪ��<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								��λԤ����ԱЧ��<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='gwyqryxg' styleId="gwyqryxg" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								��ע<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style='height: 30px'>
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="zjBcGwsq()">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

