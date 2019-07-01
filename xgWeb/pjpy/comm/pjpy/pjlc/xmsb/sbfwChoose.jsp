<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript" defer="defer">
	
	//��ʼ���б�
	function onShow(){
		//��ʼ������
		//defaultBmOption();
		//��ʼ����Ŀ
		//setXmList();
	}
	
	//������Ŀ�б�
	function setXmList(){
		jQuery(function(){	
			var xmdm = jQuery('#xmdm');
			
			var url = "pjpyXmsb.do?method=setXmOption";
				
			xmdm.combobox({
				valueField:'dm',
				textField:'mc',
				url:url,
				panelHeight:"auto"
			});
		});
	}
	
	//�ύ�ϱ���Χ
	function sbfwCheck(){
		var xmdm = jQuery('#xmdm').val();//.combobox('getValue');
		var bjdm = jQuery('#bj').val();//combobox('getValue');
		
		//�ж���Ŀ�Ƿ�Ϊ��
		if(xmdm == ""){
			alertError("��Ŀ���Ʋ���Ϊ��,��ȷ��");
			return false;
		}
	
		//�жϰ༶�Ƿ�Ϊ��
		if(bjdm == ""){
			alertError("�༶����Ϊ��,��ȷ��");
			return false;
		}
			
		//�ж���Ŀ�Ƿ����
<%--		var existsMessage = checkIsExists();--%>
<%--		if(existsMessage != ""){--%>
<%--			alertError(existsMessage);--%>
<%--			return false;--%>
<%--		}else{--%>
			confirmInfo('��ȷ����Ҫ�ϱ��Ĳ��ż���Ŀ',sbfwSubmit);
		//}
	}
	
	//�ж��Ƿ����
	function checkIsExists(){
	
		var existsMessage = "";
		
		if(existsMessage == ""){
			//�����Ŀ
			checkXm();
			existsMessage = $("em_xmdm").innerHTML;
		}
			
		if(existsMessage == ""){
			//���༶
			checkBj();
			existsMessage = $('em_bjdm').innerHTML;
		}

		return existsMessage;
	}
	
	//���������Ŀ
	function checkXm(){
		var url = "xsxx_ajax.do?method=checkIsExists";
		var xmdm = jQuery('#xmdm').combobox('getValue');
		var pk = ['xmdm||pjxn||pjxq||pjnd||sfqy||sqfs','xmmc||pjxn||pjxq||pjnd||sfqy||sqfs']
		var pkValue = escape(xmdm+$("pjxn").value+$("pjxq").value+$("pjnd").value+"��"+"lssb");
		var errMsg = escape("������Ŀ�����ڣ�");
		
		var message = "";
		
		//����
	 	var parameter = {
	 		"pk":pk.join("!!@@!!"),
	 		"pkValue":pkValue,
	 		"tableName":"xg_pjpy_pjxmwhb",
			"errMsg":errMsg
		};

		jQuery.ajax({
			type:'post',
			url:url,
			data:parameter,
			async: false,
			success:function(result){
				showErrMessage(result,"xmdm");
			}
		});
	}
		
	//���༶
	function checkBj(){
		var url = "xsxx_ajax.do?method=checkIsExists";
		var bjdm = jQuery('#bjdm').combobox('getValue');
		var pk = ['bjdm','bjmc']
		var pkValue = escape(bjdm);
		var errMsg = escape("����༶�����ڣ�");
		
		var message = "";
		
		//����
	 	var parameter = {
	 		"pk":pk.join("!!@@!!"),
	 		"pkValue":pkValue,
	 		"tableName":"view_njxyzybj",
			"errMsg":errMsg
		};
			
		jQuery.ajax({
			type:'post',
			url:url,
			data:parameter,
			async: false,
			success:function(result){
				showErrMessage(result,"bjdm");
			}
		});
	}
	
	//�ύ�ϱ���Χ
	function sbfwSubmit(tag){
	
		if(tag == "ok"){
			var xmdm = jQuery('#xmdm').val()//.combobox('getValue');
			var bjdm = jQuery('#bj').val()//.combobox('getValue');
			
			window.dialogArguments.document.getElementById("xmdm").value = xmdm;
			window.dialogArguments.document.getElementById("bjdm").value = bjdm;
			
			if(window.dialogArguments.document.getElementById("forward")){
				window.dialogArguments.document.getElementById("forward").click();
			}else if(window.dialogArguments.document.getElementById("search_go")){
				window.dialogArguments.document.getElementById("search_go").click();
			}
			
			window.close();
		}
	}
		
		jQuery(function(){
			onShow();
		})
	</script>
</head>

<body>	
	
	<!-- ��ʾ��Ϣ end-->
	<div class="prompt">
		<h3>
			<span>�������ڣ�</span>
		</h3>
		<p>
			����ѧ��(${pjxn })&nbsp;&nbsp;
			����ѧ��(${pjxqmc })&nbsp;&nbsp;
			�������(${pjnd })&nbsp;&nbsp;		
		</p>
	</div>
	<!-- ��ʾ��Ϣ end-->	
		
    <html:form action="/pjpyXmsb">
    
		<!-- ������ -->
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="pjxn" value="${pjxn }"/>
		<input type="hidden" id="pjxq" value="${pjxq }"/>
		<input type="hidden" id="pjnd" value="${pjnd }"/>
		<!-- ������ end-->
		
		<div class="open_win">
			<table class="formlist" border="0" align="center" style="width: 100%;" >	
				<thead>
					<tr>
						<td colspan="2"><span>����ѡ��</span></td>
					</tr>
				</thead>
				<tbody>	
					<tr>
						<th width="30%"><font color="red">*</font>��Ŀ����</th>
						<td>
							<html:select property="xmdm" styleId="xmdm" style="width: 200px">
								<html:option value=""></html:option>
								<html:options collection="xmdmList" property="dm" labelProperty="mc" />
							</html:select>
							<!-- ������Ϣ -->
							<span id="sp_xmdm" class="msg_prompt2" style="display: none">
				              	<em class="prompcon" id="em_xmdm">
				                	
				                </em>
			                </span>
			                <!-- ������Ϣ end-->
						</td>
					</tr>
				</tbody>
				
				<thead>
					<tr>
						<td colspan="2"><span>�ϱ�����ѡ��</span></td>
					</tr>
				</thead>
				<tbody>	
					<tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" style="width: 200px" onchange="initZyList();initBjList()" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width: 200px" styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" style="width: 200px" styleId="zy" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>�༶</th>
						<td>
							<html:select property="bjdm" style="width:200px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
				
				<tfoot>
			    	<tr>
				        <td colspan="2">
				          <div class="btn">
				          	<button type="button" id="btn_save" onclick="sbfwCheck();">
								ȷ ��
							</button>
							
				            <button type="button" id="btn_clo" onclick="Close();return false;">
								�� ��
							</button>
				          </div>
				        </td>
			    	</tr>
			    </tfoot>
			</table>
		</div>
		<!--��ע-->
		<div class="readme">
			<h2>ģ������</h2>
<%--			<h3>��������ı�</h3>--%>
			<div class="readcon">
				<ul>
					<li>��Ŀ���г����뷽ʽΪ��ʦ�ϱ�������������ʱ�䷶Χ�ڵ���Ŀ��</li>
					<li>�������¼�룬��������</li>
					<li>�������б���û��������Ҫ�Ĳ��ţ�����ѡ�����ϼ����ţ�����С��Χ��</li>
					<li>�������б���û��������Ҫ�Ĳ��ţ�����ֱ��¼�룬ϵͳ����������롣</li>
					<li>��Ŀ���ƣ��༶����Ϊ�ա�</li>
				</ul>  
			</div>
		</div>	
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
    </html:form>
  </body>
</html>
