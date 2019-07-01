<%@ page import="com.zfsoft.xgxt.xlzx.yysq.xstxxx.XstxxxForm" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="xsgzgl/xlzx/yysq/js/yyzxDetail.js"></script>

		<script type="text/javascript">
            jQuery(function(){
				var sfdszn = "${xstxxx.sfdszn}";
				if(sfdszn == "0"){
                    jQuery("#div_zmr").show();
				}else{
                    jQuery("#div_zmr").hide();
				}

                jQuery("input[type=radio][name=sfdszn]").click(function(){
                    var sfdszn = jQuery("input[type=radio][name=sfdszn]:checked").val();
                    if(sfdszn == '1'){
                        jQuery("#div_zmr").hide();
                    }else{
                        jQuery("#div_zmr").show();
                    }
                });
            });
		</script>
	</head>
	<body onload="init();">
		<html:form action="/xlzx_yysq" method="post" styleId="form">
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="yyid" id="yyid" value="${yyzxInfo.id}" />
			<input type="hidden" name="yyzxrq" id="yyzxrq" value="${yyzxInfo.yyzxrq}"/>	
			<input type="hidden" name="status" id="status" value="${yyzxInfo.status}" />

			<div style='width:100%;height:460px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr >
						<th colspan="4">
							<span>ѧ����д��Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody id="xstxxx_tb">
					<input type="hidden" name="xh" id="xh" value="${xstxxx.xh}" />
					<%
						XstxxxForm xstxxxForm = (XstxxxForm)request.getAttribute("xstxxx");
					%>
					<tr>
						<th>
							<span class="red">*</span>���
						</th>
						<td >
							<label><input type="radio" name="hf" value="1"
									<% if("1".equals(xstxxxForm.getHf())){%> checked="checked" <% } %> />��</label>
							<label><input type="radio" name="hf" value="0"
									<% if("0".equals(xstxxxForm.getHf())){%> checked="checked" <% } %> />��</label>
						</td>
						<th>
							<span class="red">*</span>�Ƿ�����Ů
						</th>
						<td  >
							<label><input type="radio" name="sfyzn" value="1"
									<% if("1".equals(xstxxxForm.getSfyzn())){%> checked="checked" <% } %>/>��</label>
							<label><input type="radio" name="sfyzn" value="0"
									<% if("0".equals(xstxxxForm.getSfyzn())){%> checked="checked" <% } %>/>��</label>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>������Ů
						</th>
						<td  >
							<label><input type="radio" name="sfdszn" value="1"
									<% if("1".equals(xstxxxForm.getSfdszn())){%> checked="checked" <% } %>/>��</label>
							<label><input type="radio" name="sfdszn" value="0"
									<% if("0".equals(xstxxxForm.getSfdszn())){%> checked="checked" <% } %>/>��</label>
							<div id="div_zmr" style="margin-left: 10px;float: right" >
								����ˣ�<input type="text" name="zmr" style="width: 80px;"
										   id="zmr" onblur="checkLen(this,2)" value="${xstxxx.zmr}">
							</div>
						</td>
						<th>
							<span class="red">*</span>��������
						</th>
						<td  >
							<input type="text" name="dzyx" id="dzyx" value="${xstxxx.dzyx}"
								   maxlength="30"  onblur="checkEmail(this)" />
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>����ְҵ
						</th>
						<td >
							<input type="text" name="fqzy" id="fqzy"  value="${xstxxx.fqzy}" onblur="checkLen(this,50)"/>
						</td>
						<th>
							<span class="red">*</span>����ѧ��
						</th>
						<td  >
							<input type="text" name="fxl" id="fxl" value="${xstxxx.fxl}" onblur="checkLen(this,50)"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>ĸ��ְҵ
						</th>
						<td >
							<input type="text" name="mqzy" id="mqzy" value="${xstxxx.mqzy}" onblur="checkLen(this,50)"/>
						</td>
						<th>
							<span class="red">*</span>ĸ��ѧ��
						</th>
						<td  >
							<input type="text" name="mxl" id="mxl" value="${xstxxx.mxl}" onblur="checkLen(this,50)"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>��ͥ��ַ
						</th>
						<td >
							<input type="text" name="jtdz" id="jtdz" value="${xstxxx.jtdz}" onblur="checkLen(this,50)"/>
						</td>
						<th>
							<span class="red">*</span>��Դ��
						</th>
						<td>
							<label><input type="radio" name="syd" value="cs"
									<% if("cs".equals(xstxxxForm.getSyd())){%> checked="checked" <% } %>/>����</label>
							<label><input type="radio" name="syd" value="xc"
									<% if("xc".equals(xstxxxForm.getSyd())){%> checked="checked" <% } %> />�س�</label>
							<label><input type="radio" name="syd" value="nc"
									<% if("nc".equals(xstxxxForm.getSyd())){%> checked="checked" <% } %> />����ũ��</label>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>��ѯĿ��
							<br />
							<font color="red">(������500����)</font>
						</th>
						<td colspan="3">
							<textarea name="zxmd" id="zxmd" rows="4" cols=""
									  style="width: 98%" onblur="checkLen(this,500)">${xstxxx.zxmd}</textarea>
						</td>
					</tr>
					</tbody>
				</table>
				<logic:equal value="1" name="sfscsq">
					<table width="100%" border="0" class="formlist" id="yytxxx_tb">
						<thead>
						<tr>
							<th colspan="2">
								<span>ԤԼ��д��Ϣ</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th rowspan="3" width="20%">һ�ܵ�����״̬</th>
							<td width="80%">
								<span class="red">*</span>���壺
								<label><html:radio property="qxztzt" value="hh">�ܺ�</html:radio></label>
								<label><html:radio property="qxztzt" value="jh">�Ϻ�</html:radio></label>
								<label><html:radio property="qxztzt" value="yb">һ��</html:radio></label>
								<label><html:radio property="qxztzt" value="jc">�ϲ�</html:radio></label>
								<label><html:radio property="qxztzt" value="hc">�ܲ�</html:radio></label>
							</td>
						</tr>
						<tr>
							<td>
								<span class="red">*</span>���ǣ�
								<label><html:radio property="qxztjl" value="yz">����</html:radio></label>
								<label><html:radio property="qxztjl" value="jz">����</html:radio></label>
								<label><html:radio property="qxztjl" value="y">�� &nbsp;&nbsp;</html:radio></label>
								<label><html:radio property="qxztjl" value="qw">��΢</html:radio></label>
								<label><html:radio property="qxztjl" value="w">��</html:radio></label>
							</td>
						</tr>
						<tr>
							<td>
								<span class="red">*</span>������
								<label><html:radio property="qxztyy" value="yz">����</html:radio></label>
								<label><html:radio property="qxztyy" value="jz">����</html:radio></label>
								<label><html:radio property="qxztyy" value="y">�� &nbsp;&nbsp;</html:radio></label>
								<label><html:radio property="qxztyy" value="qw">��΢</html:radio></label>
								<label><html:radio property="qxztyy" value="w">��</html:radio></label>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>�ϴ���ѯ��ĸı�</th>
							<td>
								<label><html:radio property="sczxhgb" value="hmx">������</html:radio></label>
								<label><html:radio property="sczxhgb" value="jmx">������</html:radio></label>
								<label><html:radio property="sczxhgb" value="yb">һ��</html:radio></label>
								<label><html:radio property="sczxhgb" value="jbmx">�ϲ�����</html:radio></label>
								<label><html:radio property="sczxhgb" value="bmx">������</html:radio></label>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>���Լ������״̬</th>
							<td>
								<label><html:radio property="zjzt" value="hmy">������</html:radio></label>
								<label><html:radio property="zjzt" value="jmy">������</html:radio></label>
								<label><html:radio property="zjzt" value="yb">һ��</html:radio></label>
								<label><html:radio property="zjzt" value="jbmc">�ϲ�����</html:radio></label>
								<label><html:radio property="zjzt" value="bmy">������</html:radio></label>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>������ѯ������
								<br />
								<font color="red">(������500����)</font>
							</th>
							<td>
								<html:textarea property="bczxwt" styleId="bczxwt" style="width:98%" rows="4" onblur="checkLen(this,500)" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�ϴ���ѯ�����������״̬����Ṧ��
								<br />
								<font color="red">(������500����)</font>
							</th>
							<td>
								<html:textarea property="zxhzt" styleId="zxhzt" style="width:98%" rows="4" onblur="checkLen(this,500)" />
							</td>
						</tr>
						</tbody>
					</table>
				</logic:equal>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>ԤԼ��ѯʦ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfoList">
							<tr style="height:10px">
								<th  width="16%">
									����
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxsxm}
								</td>
							</tr>
							<tr style="height:10px">
								<th  width="16%">
									�Ա�
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxb }
								</td>
								<th width="16%">
									����
								</th>
								<td  width="34%">
									${yyzxInfo.zxsage}
								</td>
							</tr>
							<tr style="height:10px">
								<th width="16%">
									��ϵ�绰
								</th>
								<td  width="34%">
									${yyzxInfo.lxdh }
									
								</td>
								<th width="16%">
									���ڲ���
								</th>
								<td  width="34%">
									${yyzxInfo.bmmc }
								</td>
							</tr>
							<logic:equal name="xxdm" value="10026">
								<tr style="height:10px">
									<th width="16%">
										У��
									</th>
									<td   colspan="3">
										${yyzxInfo.xxxq}
									</td>
								</tr>
							</logic:equal>
							<tr style="height:10px">
								<th width="16%">
									��ѯ��ַ
								</th>
								<td   colspan="3">
									${yyzxInfo.address}
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									��ְ����<br/>
								</th>
								<td colspan="3">
									${yyzxInfo.zxszg }
								</td>
							</tr>
							<tr style="height:30px">
								<th>
									���<br/>
								</th>
								<td colspan="3">
									${yyzxInfo.zxsjj}
								</td>
							</tr>
							
					</tbody>
					
					 <thead>
						<tr >
							<th colspan="4">
								<span>ԤԼ������Ϣ</span>
							</th>
						</tr>
					</thead>
						<tbody id="yyzxInfo"> 
						<tr style="height:10px">
							<th>
								ԤԼ��ѯ����
							</th>
							<td >
								<span class="red"><B>${yyzxInfo.yyzxrq}</B></span>
							</td>
							<th width="16%">
								<logic:notEqual name ="doType" value="view"><logic:equal value="2" name="pbfs"><span class="red">*</span></logic:equal></logic:notEqual>ԤԼ��ѯʱ��
							</th>
							<td width="34%">
							<logic:equal name ="doType" value="view">
								<logic:notEqual value="2" name="pbfs">
										${ yyzxInfo.yyqssj}&nbsp;
									<logic:notEqual  name="yyzxInfo" property="yyjssj" value="">
										��&nbsp;${yyzxInfo.yyjssj}
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal value="2" name="pbfs">
									${yyzxInfo.sjdmc}
								</logic:equal>
							</logic:equal>
							<logic:notEqual name ="doType" value="view">
								<logic:notEqual value="2" name="pbfs">
									<html:text property="qssj" styleId="qssj"   style="width:30%"  value="${ yyzxInfo.yyqssj}" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\\'jssj\\')}'})" readonly="true" />&nbsp;��&nbsp;
									<html:text property="jssj" styleId="jssj"   style="width:30%"  value="${ yyzxInfo.yyjssj}" onfocus="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\\'qssj\\')}'})" readonly="true" />
								</logic:notEqual>
								<logic:equal value="2" name="pbfs">
									<html:select styleId="sjddm" property="sjddm" value="${yyzxInfo.sjddm}">
										<html:option value=""></html:option>
										<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
									</html:select>
								</logic:equal>
							</logic:notEqual>
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								<logic:notEqual name ="doType" value="view"><span class="red">*</span></logic:notEqual>Ԥ����ϵ����
							</th>
							<td>
							<logic:equal name ="doType" value="view">
									${yyzxInfo.xstell}
								</logic:equal>
								<logic:notEqual name ="doType" value="view">
									<html:text property="xstell" styleId="xstell"   maxlength="11"  value="${ yyzxInfo.xstell}" readonly="fasle" onblur="checkInputData(this);"/>
								</logic:notEqual>
							</td>
							<th width="16%">
								ѧ��ѧ��
							</th>
							<td width="34%">
								${currxn}&nbsp;&nbsp;${currxq}
							</td>
						</tr>
						<tr  style="height:10px">
							<th>
								<logic:notEqual name ="doType" value="view"><span class="red">*</span></logic:notEqual>��ѯ����
							</th>
							<td colspan="3">
								<logic:equal name ="doType" value="view">
									${yyzxInfo.yyzxzt}
								</logic:equal>
								<logic:notEqual name ="doType" value="view">
									<html:text property="yyzxzt" styleId="yyzxzt" style="width:98%"   maxlength="100"  value="${ yyzxInfo.yyzxzt}" />
								</logic:notEqual>
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								��ѯ��Ҫ
								<logic:notEqual name ="doType" value="view"><br/>
								<font color="red"><B>(��500��)</B></font></logic:notEqual>
							</th>
							<td colspan="3">
								<logic:notEqual name ="doType" value="view">
									<html:textarea  property='yyzxxq' styleId="yyzxxq" style="word-break:break-all;width:99%" value="${ yyzxInfo.yyzxxq}" onblur="chLengs(this,500);" rows='4' />
								</logic:notEqual>
								<logic:equal name ="doType" value="view">
									${ yyzxInfo.yyzxxq}
								</logic:equal>
							</td>
						</tr>
					</tbody>
						
					<thead name ="yyqkInfo">
						<tr>
							<th colspan="4">
								<span>ԤԼ�������</span>
							</th>
						</tr>
					</thead>
					<tbody id="yyInfo">
							<tr style="height:10px">
								<th  width="16%">
									ԤԼ״̬
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.statusmc}
								</td>
							</tr>
							<tr style="height:10px" name ="yyqkInfo">
								<th  width="16%">
									��ѯ��������
								</th>
								<td  width="34%" >
									<span class="red"><B>${yyzxInfo.zxrq}</B></span>
								</td>
								<th width="16%">
									��ѯʱ��
								</th>
								<td width="34%">
									<logic:notEqual value="2" name="pbfs">
										${yyzxInfo.zxqssj}&nbsp;
										<logic:notEqual  name="yyzxInfo" property="zxjssj" value="">
											��&nbsp;${yyzxInfo.zxjssj}
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="2" name="pbfs">
										${yyzxInfo.sjdmczx}
									</logic:equal>
								</td>
							</tr>
							<tr style="height:10px" name ="yyqkInfo">
								<th  width="16%">
									��ѯ�绰
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxtell}
								</td>
							</tr>
							<tr style="height:10px" name ="yyqkInfo" >
								<th  width="16%">
									��ѯ��ַ
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxdz}
								</td>
							</tr>
							<tr style="height:10px" name="yysbyytr">
								<th  width="16%">
									ԤԼʧ��ԭ��<br/>
								<logic:notEqual name="doType" value="view"><font color="red"><B>(��500��)</B></font></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
								<logic:equal name ="doType" value="view">
									${yyzxInfo.yysbyy}
								</logic:equal>
								<logic:notEqual name ="doType" value="view">
									<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLengs(this,500);" rows='4' />
								</logic:notEqual>
								</td>
							</tr>
					</tbody>
						
					<thead name="zxxgInfo">
						<tr >
							<th colspan="4">
								<span>��ѯ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxInfo" name="zxxgInfo">
							<tr style="height:10px">
								<th  width="16%">
									��ѯ���
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxztmc}
								</td>
							</tr>
							<tr style="height:40px">
								<th  width="16%">
									��ѯ����
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.xspj}
								</td>
							</tr>
					</tbody>
					</table>
				</div>
			  <table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz" id="btx">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveyyzxInfo();return false;">
										�� ��
									</button>
									<button onclick="Close();return false;">
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

