<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function loadQs(){
			var api = frameElement.api,W = api.opener;
			//��ѡ��ѡ��ѧ��
			var pks = W.document.getElementsByName("primarykey_cbv");
			//�ɳ�ʼ��������
			var num = W.document.getElementById("kcshqss").value;
			//��ѯ�����ݼ��Ĳ�ѯ����
			var searchTjstr = W.document.getElementById("searchTjstr").value;

			var RowsStr="";
			var count =0;
			for (i=0; i<pks.length; i++){
	 			if(pks[i].checked){
	 				RowsStr+=pks[i].value+"!!splitOne!!";
	 				count++;
	 			}
			}
			if(count==0){//δѡ��ѧ��
				jQuery('#searchTjstr').val(searchTjstr);
				jQuery('#xhtd').html("��ǰ��ѯ����й���<font color='red'>"+num+"</font>���ɳ�ʼ�������ң���ִ������������ʼ������");
				if(num == 0){
					jQuery('#buttonSave').attr({disabled:'disabled'});
				}
			}else{//ѡ��ѧ��
				jQuery('#qsStr').val(RowsStr);
				jQuery('#xhtd').html("��ǰ��ѡ��<font color='red'>"+count+"</font>�����ң���ִ������������ʼ������");

			}
		}
		
		function saveTsxx(){
			saveData('gyglnew_qsgl.do?method=qsglQsssInit&doType=init','');
		}
	</script>
</head>
<body onload="loadQs()">
	<html:form action="/gyglnew_cwgl" method="post">	
		<input type="hidden" id="qsStr" name="qsStr" value=""/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" id="count" value=""/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ����</a>
			</p>
		</div>		
		--%>
		<div class="prompt" id="span_qsh" >
	       <h3><span>��ʾ��</span></h3>
	       <p>��ʼ����������ʱ�����Ƿ��ʼ�����Ҵ�λ������ѡ���ǡ�����ͬʱ��ʼ�������д�λ������</p>
	   	</div>
		
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="2" width="23%">
						<span>��ʼ����������</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="23%">
						��ʼ����������				
					</th>
					<td id="xhtd">
						
					</td>
				</tr>
				<tr>
					<th width="23%">
						<font color="red">*</font>�Ƿ��ʼ�����Ҵ�λ����				
					</th>
					<td>
						<input type="radio" name="sfqccwss" value="��" checked="checked"/>��
						<input type="radio" name="sfqccwss" value="��"/>��
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="2">
<%--			        <div class="bz">"<span class="red">*</span>"Ϊ������</div>--%>
			          <div class="btn">
			          	<button type="button"  name="�ύ" id="buttonSave" onclick="saveTsxx();">����</button>
			            <button type="button"  name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
		 	showAlert("����ɹ�",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
			
		</script>
	</logic:present>
</body>
</html>
