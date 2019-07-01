<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
			
			jQuery.ajaxSetup({async:false});

			var xmdm = jQuery("#xmdm").val();
			
			//·��
			var url = "general_xmsz_pjtj_ajax.do?method=defaultPjtjSetting";
			//����
		 	var parameter = {
				"xmdm":xmdm
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery("#div_tjsz").load(
				url,
				parameter,
				function(){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";

					if($("hidden_num")){
						jQuery("#num").val($("hidden_num").value);
					}
				}
			);

			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.�������Ϊ����Ŀ��������������<font color="blue">��������</font>��<br/>
				2.�������Ϊ����Ŀɾ����������<font color="blue">��ѡ</font>��Ӧ�������������<font color="blue">ɾ������</font>��<br/>
				3.ά��������ʱ����������ϵ������ֵ����Χ��<font color="blue">����Ϊ��</font>��<br/>
				4.���÷�ΧĬ��Ϊ<font color="blue">ȫ��</font>����ȫУ����ѧ�����������ά�����༶����Ļ����ڴ˴�����ѡ������<font color="blue">�༶����</font>��<br/>
				5.��ͬ��Χ����������ͬһ���������ǲ����з�Χ������һ�µ�������<br/>
				6.����ĳ��Ŀ��<font color="blue">�ۺϷ�(ȫ��)����80���ۺϷ�(��ư�)����70</font>������ư��ѧ��ֻ��Ҫ�ۺϷ�<font color="blue">����70</font>�����������Ŀ��<br/>
				7.��������������<font color="blue">����������</font>�����<font color="blue">û������Ҫ������</font>������ϵ<font color="blue">����Ա</font>��<br/>
				8.�������Ŀ�Ѿ���<font color="blue">ѧ���������</font>����˴����е������������ٽ���<font color="blue">�޸Ļ�ɾ��</font>��<br/>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="str_xmdm" id="xmdm" value="${xmdm }"/>
			<input type="hidden" name="num" id="num" value="0"/>
			<!-- �������� -->
			<select id="select_pjtj" style="display:none">
				<option value=""></option>
				<logic:iterate name="pjtjList" id="pjtj_rs">
					<option value="${pjtj_rs.tjdm }">${pjtj_rs.tjmc }</option>
				</logic:iterate>
			</select>
			<!-- ��Χ�ȼ� -->
			<select id="select_tjfw" style="display:none">
				<option value="all">ȫ��</option>
				<logic:iterate name="bjdlList" id="bjdl_rs">
					<option value="${bjdl_rs.dm }">${bjdl_rs.mc }</option>
				</logic:iterate>
			</select>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<td>
							<span>������������</span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<logic:equal name="checkXssq" value="true">
							<button type="button"  onclick="addTjsz();">��������</button>
							<button type="button"  onclick="delTjsz();">ɾ������</button>
							</logic:equal>
							<logic:equal name="checkXssq" value="false" >
							<button type="button"  onclick="addTjsz();" disabled="true">��������</button>
							<button type="button"  onclick="delTjsz();" disabled="true">ɾ������</button>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td>
							<div id="div_tjsz_top">
								<table width="100%" border="0">
									<tr>
										<td width="5%">
											<input type="checkbox" name="selall" onclick="selAll()"/>
										</td>
										<td width="40%">
											����
										</td>
										<td width="10%">
											��ϵ
										</td>
										<td width="10%">
											����ֵ
										</td>
										<td>
											���÷�Χ
										</td>
									</tr>
								</table>
							</div>
							<div id="div_tjsz">
								
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn">
								<logic:equal name="checkXssq" value="true">
								<button type="button"  name="����" onclick="checkSavePjtj();return false;">�� ��</button>
								</logic:equal>
								<logic:equal name="checkXssq" value="false">
								<button type="button"  name="����" onclick="checkSavePjtj();return false;" disabled="true">�� ��</button>
								</logic:equal>
								<button type="button"  name="�ر�" onclick="Close();return false;">�� ��</button>
							</div>
						</td>
					</tr>
			    </tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>