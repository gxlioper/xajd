<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdblsq.js"></script>
	<script type="text/javascript">
	
	jQuery(function(){
        var hdxs = jQuery("#hdxs").val();
        if("�γ�" == hdxs){
            jQuery("#jzlxTr").show();
            jQuery("tr[name='zjrxx_tr']").hide();
        }else if("����" == hdxs){

            jQuery("tr[name='zjrxx_tr']").show();
            jQuery("#lx_span").html("��������");
            jQuery("#con_span").html("��������");

            jQuery("#jzlxTr").hide();
        }else{
            jQuery("tr[name='zjrxx_tr']").hide();
            jQuery("#jzlxTr").hide();
        }
        kcjbChange();

		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splc}&shid=${rs.shid}");

	});
	function saveSh(){
        var hdxs = jQuery("#hdxs").val();
        var jzlx = jQuery("#jzlx").val();
		if ((!!jQuery("#shjg").val()) == false || (!!jQuery("#hdlx").val() == false) || (!!jQuery("#hdxf").val() == false)){
			showAlert("�뽫��������д������");
			return false;
		}
        var ids = "xsxxlx-hdxs-hdlx-zbf";
        if("����" == hdxs){
            ids += "-zjrxm-zjrdw-zjrzc-zjrzw-jzjb";
        }
        if(!check(ids)){
            showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
            return false;
        }
        var nlbqLen = jQuery("[name='nlbqs']:checked").length;
        if(nlbqLen > 3){
            showAlert("������ǩ���ֻ��ѡ��������ȷ�ϣ�");
            return false;
        }
        if("�γ�" == hdxs){
            if(jzlx == null || jzlx == ''){
                showAlert("��ѡ��γ̼���");
                return false;
            } else {
                var zxkclx = jQuery("#zxkclx").val();
                if(zxkclx == ""){
                    showAlert("��ѡ����ѡ�γ����ͣ�");
                    return false;
                }
            }
        }
		var url = "hdgl_hdblsh.do?method=sbDgsh&type=save";
		ajaxSubFormWithFun("hdblsqshForm",url,function(data){
			 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			});
	}	
	</script>
</head>
<body>
	<html:form action="/hdgl_hdblsh" method="post" styleId="hdblsqshForm">
		<html:hidden name="rs" property="sqid" styleId="sqid"/>
		<html:hidden name="rs" property="splc" styleId="splc"/>
		<html:hidden name="rs" property="shid" styleId="shid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				 </table>
		
			<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>�����ʷ</span>
					</th>
				</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>			
			</tbody>	
			<thead>
				<tr>
					<th colspan="4">
						<span>���Ϣ</span>
					</th>
				</tr>
			</thead>
				<tbody>
				<tr>
					<th width="15%">
						ѧ��
					</th>
					<td width="35%">
							${rs.xn}
					</td>
					<th>ѧ��</th>
					<td>
							${rs.xqmc}
					</td>
				</tr>
				<tr>
					<th width="15%">
						�����
					</th>
					<td width="35%">
							${rs.hdmc}
					</td>
					<th>�ʱ��</th>
					<td>
							${rs.hdsj}
					</td>
				</tr>
					<tr>
						<th>
							<font color="red">*</font>���췽
						</th>
						<td colspan="3">
							<html:text property="zbf" styleId="zbf" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th width="15%">
							<span><font color="red">*</font>����or���»</span>
						</th>
						<td width="35%">
							<html:select property="xsxxlx" styleId="xsxxlx"  style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="����">���ϻ</html:option>
								<html:option value="����">���»</html:option>
							</html:select>
						</td>
						<th width="15%">
							<span><font color="red">*</font>���ʽ</span>
						</th>
						<td width="35%">
							<html:select property="hdkclx" styleId="hdkclx"  style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="������">�����ࣨ��׶Σ�</html:option>
								<html:option value="������">�����ࣨ���׶Σ�</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th width="15%">
							<span><font color="red">*</font>�����</span>
						</th>
						<td width="35%">
							<html:select property="hdxs" styleId="hdxs" onchange="changeHdxs()" style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="�">�</html:option>
								<html:option value="�γ�">�γ�</html:option>
								<html:option value="����">����</html:option>
							</html:select>
						</td>
						<th>
							<font color="red">*</font><span id="lx_span">�����</span>
						</th>
						<td>
							<html:select property="hdlx" styleId="hdlx" style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="hdlxList" labelProperty="hdlxmc" property="hdlxdm"/>
							</html:select>
						</td>
					</tr>

					<tr name="zjrxx_tr">
						<th>
							<font color="red">*</font>����������
						</th>
						<td>
							<html:text property="zjrxm" styleId="zjrxm" maxlength="10"/>
						</td>
						<th >
							<font color="red">*</font>�����˵�λ
						</th>
						<td >
							<html:text property="zjrdw" styleId="zjrdw" maxlength="20"/>
						</td>
					</tr>
					<tr name="zjrxx_tr">
						<th>
							<font color="red">*</font>������ְ��
						</th>
						<td>
							<html:select property="zjrzc" styleId="zjrzc" style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="zjrzcList" labelProperty="mc" property="dm"/>
							</html:select>
						</td>
						<th >
							<font color="red">*</font>������ְ��
						</th>
						<td >
							<html:text property="zjrzw" styleId="zjrzw" maxlength="10"/>
						</td>
					</tr>
					<tr >
						<th>
							<font color="red">*</font>�����

						</th>
						<td colspan="3">
							<html:select property="jzjb" styleId="jzjb" style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="У���">У���</html:option>
								<html:option value="Ժ���">Ժ���</html:option>
								<html:option value="��ѡ�">��ѡ�</html:option>
							</html:select>
						</td>

					</tr>
					<tr name="zjrxx_tr">
						<th>
							�����˽���
							<br><font color="red">(��100��)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="2" property="zjrjs" styleId="zjrjs"
										   style="width:95%" onblur="checkLen(this,100);"/>
						</td>

					</tr>

					<tr id="jzlxTr">
						<th>
							<font color="red">*</font>�γ̼���
						</th>
						<td>
							<html:select property="jzlx" styleId="jzlx" style="width:173px" onchange="kcjbChange()">
								<html:option value="">---&nbsp;��ѡ��γ̼���&nbsp;---</html:option>
								<html:options collection ="jzlxList" property="jzlxdm" labelProperty="jzlxmc" />
							</html:select>
						</td>
						<th id="zxkclxTh" style="display: none;">
							<font color="red">*</font>��ѡ�γ�����
						</th>
						<td id="zxkclxTd" style="display: none;">
							<html:select property="zxkclx" styleId="zxkclx" style="width:173px">
								<html:option value="">---&nbsp;��ѡ����ѡ�γ�����&nbsp;---</html:option>
								<html:options collection ="zxckclxList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							���ǩ
						</th>
						<td colspan="3">
							<logic:iterate name="activityLabelList" id="bq">
								<%--<html:checkbox property="hdbqs" value="${bq.dm}">${bq.mc}</html:checkbox>--%>
								<label><html:multibox property="hdbqs" value="${bq.hdbqdm}"/>${bq.hdbqmc}</label>
							</logic:iterate>
						</td>
					</tr>
					<tr>
						<th>
							������ǩ
						</th>
						<td colspan="3">
							<logic:iterate name="abilityLabelList" id="bq">
								<label><html:multibox property="nlbqs" value="${bq.nlbqdm}"/>${bq.nlbqmc}</label>
							</logic:iterate>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>���ѧ��
						</th>
						<td colspan="3">
							<html:text property="hdxf" styleId="hdxf" maxlength="8" onblur="clearNoNum(this);return false;"/>
						</td>
					</tr>
				</tbody>
				<thead>
				<tr>
					<th colspan="4">
						<span>�����Ϣ</span>
					</th>
				</tr>
				</thead>
				<tbody>
					<tr>
						<th >
							<font color="red">*</font>��˽��
						</th>
						<td id="shjgSpan" colspan="3">
							
						</td>
					</tr>
					<tr>
						<th width="20%">
							<font color="red">*&nbsp;</font> ������
							<br />
							<font color="red">(��200��)</font>
						</th>
						<td colspan="3">
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=hdblsh&id=shyj" />
							<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveSh();return false;">
									�� ��
								</button>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
