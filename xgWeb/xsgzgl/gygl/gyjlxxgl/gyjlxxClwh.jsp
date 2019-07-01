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
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	<script type="text/javascript">
	function changeDcqk(){
		var sfcl = jQuery("#sfcl").val();
		if(sfcl=="��"){
			jQuery("#div_dcqk").show();
		}else if(sfcl=="��"){
			jQuery("#div_dcqk").hide();
		}

	}

	function changeTqs(obj){
		if(obj.checked){
			jQuery('input[type=checkbox][name=xhs]').attr('checked',jQuery(obj).attr('checked'));
			jQuery('input[type=checkbox][name=th]').attr('checked',jQuery(obj).attr('checked'));
			jQuery("#xs_body").css("display","");
		}else{
			jQuery("#xs_body").css("display","none");	
			}
		}

	function saveCljg(){
		var pk_value=jQuery("#gyjl_div_pkValue").val();

		var xh=pk_value.split("!!shen!!")[1];

		var jlsj=pk_value.split("!!shen!!")[0];
		
		var gyjllbdm=pk_value.split("!!shen!!")[2];
		var url='gyglnew_gyjlgl_gyjlclwh.do?doType=cl';
		url+="&xh="+xh+"&jlsj="+jlsj+"&gyjllbdm="+gyjllbdm;
		var dcqk = jQuery('#dcqk').val();
		var cljg=jQuery("#select_cljg").val();
		if (cljg == null || cljg == "") {
			alertInfo("��ѡ������!",function(){return false;});
			return false;
		} 
		if (dcqk == null || dcqk == "") {
			alertInfo("�����������Ϊ��!",function(){return false;});
			return false;
		} 
		
		confirmInfo("ȷ��Ҫ����ü�¼��?",function(tag){
			if(tag=="ok"){
				allNotEmpThenGo(url);
			}else {
				return false;
			}
		});
	}
	</script>
</head>
<body>
	<html:form action="/gyglnew_gyjlgl" method="post">
		<div class="tab" style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
		<%@ include file="/comm/hiddenValue.jsp"%>			
		<input type="hidden"  name="div_pkValue" id="gyjl_div_pkValue" value="${div_pkValue}"/>
		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					ѧ��
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					${rs.xh }
				</td>
				
				<th  align="right" width="20%">
					����			
				</th>
				<td  width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				<th>רҵ</th>
				<td>${rs.zymc }</td>
			</tr>
			<tr>
				<th>�༶</th>
				<td>${rs.bjmc }</td>
				<th>�꼶</th>
				<td>${rs.nj }</td>
			</tr>
			<tr>
				<th>ס������</th>
				<td>${rs.zsqs }</td>
				<th>���ҵ绰</th>
				<td>${rs.qsdh }</td>
			</tr>
			</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>
						<logic:equal value="11799" name="xxdm">
							��Ԣ������Ϣ
						</logic:equal>
						<logic:notEqual value="11799" name="xxdm">
							��Ԣ������Ϣ
						</logic:notEqual>
						</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					Υ��ѧ��
				</th>
				<td align="left" width="30%">
					${rs.wjxn}
				</td>
				<th align="right" width="20%">
					Υ��ѧ��
				</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
						<logic:equal value="11799" name="xxdm">
							���ʹ���
						</logic:equal>
						<logic:notEqual value="11799" name="xxdm">
							���ɴ���
						</logic:notEqual>				
				</th>
				<td align="left" width="30%">
					${rs.jldl}
				</td>
				<th align="right" width="20%">
						<logic:equal value="11799" name="xxdm">
							���ʹ���
						</logic:equal>
						<logic:notEqual value="11799" name="xxdm">
							�������				
						</logic:notEqual>
				</th>
				<td align="left" width="30%" >
					${rs.jldb}
				</td>
			</tr>
			<tr>
					<th width="20%">
						Υ��ʱ��				
					</th>
					<td align="left" width="30%">
						${rs.wjsj}
					</td>
					<th width="20%">
					</th>
					<td align="left" width="30%">
					</td>
			</tr>
			<logic:equal name="xxdm" value="70002">
				<tr>
					<th align="right" width="20%">
								������Ϣ
						</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${rs.fileid}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
				</tr>
			</logic:equal>
			<tr>	
					<th>
						��ע
					</th>
					<td colspan="3" style="word-break:break-all;" >
						${rs.bz }
					</td>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*</font>������
				</th>
				<td align="left" width="30%" colspan="3">
					<html:select name="rs" property="cljg" style="width:150px" styleId="select_cljg">
						<html:option value="">--��ѡ��--</html:option>
						<html:option value="th">�˻�</html:option>
						<html:options collection="cflbList" property="gyjlcfdm" labelProperty="gyjlcfmc" />
					</html:select>
				</td>
			</tr>
			<logic:equal value="13033" name="xxdm">
			<tr>
				<th width="20%">
					�⳥���
				</th>
				<td align="left" width="30%" colspan="3">
					<html:text property="ylzd1" styleId="ylzd1" style="width:150px" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')" value="${rs.ylzd1}"></html:text>&nbsp;&nbsp;Ԫ&nbsp;&nbsp;
				</td>
			</tr>
			</logic:equal>
			<tr>
					<th>
						<font color="red">*</font>�������
						<br /><font color="red">(������500����)</font>
					</th>
					<td colspan="3" >
						<textarea style="width:600px;" id="dcqk" name="dcqk" rows='3' onblur="chLeng(this,500);">${rs.dcqk}</textarea>
					</td>
			</tr>
			<tr>
					<th>
						�����Ϣ
						
					</th>
					<td colspan="3" >
						${rs.shyj}
					</td>
			</tr>
			</tbody>
			</table>
			
			<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>
							<logic:equal value="11799" name="xxdm">
								�ѻ񽱳���Ϣ
							</logic:equal>
							<logic:notEqual value="11799" name="xxdm">
								�ѻ������Ϣ
							</logic:notEqual>
							
						</span>
					</th>
				</tr>
			</thead>
			
				<tr>
				<td colspan="4">
					<div style="height:100px;overflow-x:hidden;overflow-y:auto;">
					<table class="formList" width="100%">
						<thead>
							<tr align="left">
								<td align="center">Υ��ѧ��</td>
								<td align="center">Υ��ѧ��</td>
								<td align="center">
									<logic:equal value="11799" name="xxdm">
										�������
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										�������
									</logic:notEqual>
								</td>
								<td align="center">Υ��ʱ��</td>
								<td align="center">������</td>
							</tr>
						</thead>
			<logic:empty name="rsArrList">
				<tr><td align="left" colspan="5">��ѧ������ʷΥ�ͼ�¼��</td></tr>
			</logic:empty>
			<logic:notEmpty name="rsArrList">
				<logic:iterate name="rsArrList" id="s">
					<tr>
						<!-- ��ʾ��Ϣ -->
						<logic:iterate id="v" name="s" offset="0" length="5">
							<td align="center">
								${v }
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
		</div>
		</td>
		</tr>
		</table>
		</div>
		<table width="100%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����" id="buttonSave" onclick="saveCljg();return false;">
									�� ��
								</button>
								&nbsp;&nbsp;
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
