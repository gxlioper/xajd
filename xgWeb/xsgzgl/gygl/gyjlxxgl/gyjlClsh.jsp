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

	function sfshCh(obj){
		if(obj.value=='tg'){
			jQuery("#cljgRs").css("display","");
		}else{
			jQuery("#cljgRs").css("display","none");
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

	function saveShzt(){
		var xh=jQuery("#xh").val();
		var wjsj=jQuery("#wjsj").val();
		var shzt=jQuery("#shzt").val();
		var gyjllbdm=jQuery("#gyjllbdm").val();
		jQuery("#gyjl_div_pkValue").val(wjsj+"!!shen!!"+xh+"!!shen!!"+gyjllbdm);
		
		confirmInfo("ȷ��Ҫ��˸ü�¼��?",function(tag){

			if(tag=="ok"){
				allNotEmpThenGo('gyglnew_gyjlgl_gyjlclsh.do?doType=sh&act='+shzt);
			}else {
				return false;
			}
		});
	}
	
	</script>
</head>
<body>
	<html:form action="/gyglnew_gyjlgl" method="post">
		
		<div class="tab"  style="width:100%;overflow-x:hidden;overflow-y:auto;">
		<html:hidden property="div_pkValue" styleId="gyjl_div_pkValue"/>
		<%@ include file="/comm/hiddenValue.jsp"%>			
		<table class="formlist" width="95%">
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
					<html:hidden name="rs" property="xh" styleId="xh"/>
					<html:hidden name="rs" property="wjsj" styleId="wjsj"/>
					<html:hidden name="rs" property="gyjllbdm" styleId="gyjllbdm"/>
					<input type="hidden" name="jlsj" id="jlsj" value="${rs.wjsj }"/>
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
			<table width="100%" class="formlist">
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
				<th >
					<logic:equal value="11799" name="xxdm">
						���ʹ���
					</logic:equal>
					<logic:notEqual value="11799" name="xxdm">						
						���ɴ���
					</logic:notEqual>
									
				</th>
				<td >
					${rs.jldl}
				</td>
				<th >
					<logic:equal value="11799" name="xxdm">
						�������
					</logic:equal>
					<logic:notEqual value="11799" name="xxdm">						
						�������
					</logic:notEqual>									
				</th>
				<td  >
					${rs.jldb}
				</td>
			</tr>
			<tr>
					<th >
						Υ��ʱ��				
					</th>
					<td >
						${rs.wjsj}
					</td>
					<th >
					</th>
					<td >
					</td>
			</tr>
			<logic:equal name="xxdm" value="70002">
				<tr>
					<th align="right" >
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
					<td colspan="3" style="word-break:break-all;" width="650px">
						${rs.bz }
					</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					������
				</th>
				<td align="left" width="30%" id="cljg" >
					${rs.cljgmc}
				</td>
				<%--�����ʵ��ѧ���Ի� --%>
				<logic:equal value="13627" name="xxdm">
					<th align="right" width="20%">
						����ʱ��
					</th>
					<td align="left" width="30%" id="cljg" >
						${rs.ylzd3}
					</td>
				</logic:equal>
			</tr>
			<logic:equal value="13033" name="xxdm">
				<tr>
					<th width="20%">
						�⳥���
					</th>
					<td align="left" width="30%" colspan="3">
						${rs.ylzd1}&nbsp;&nbsp;Ԫ&nbsp;&nbsp;
					</td>
				</tr>
			</logic:equal>
			<tr>
					<th>
						�������
						<br />
					</th>
					<td colspan="3" style="word-break:break-all;" width="650px">
						${rs.dcqk}
					</td>
			</tr>
			<tr>
				<th width="20%">
					���״̬
				</th>
				<td align="left" width="30%" >
					<html:select name="rs" property="shzt" style="width:150px" styleId="shzt">
<%--						<html:option value="wsh">δ���</html:option>--%>
						<html:option value="tg">ͨ��</html:option>
						<html:option value="btg">��ͨ��</html:option>
						<html:option value="th">�˻�</html:option>
					</html:select>
				</td>
				<th>���ʱ��</th>
				<td>${rs.shsj }</td>
			</tr>
			<tr>
				<th width="20%">
					������
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gyjlxx&id=shyj" />
					<textarea id="shyj" rows="3" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="chLeng(this,500);">${rs.shyj}</textarea>
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
					<div style="height:70px;overflow-x:hidden;overflow-y:auto;">
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
								<button type="button" name="����" id="buttonClose" onclick="saveShzt();return false;">
									����
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
