<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function chgEditer(obj){
			var url = "eWebEditor/eWebEditor.jsp?color=" + obj.value;
			eWebEditor1.location = url;
		}
		function pubInfo(){
			if(document.getElementById("infoTitle").value == ""){
				alert("����д���⣡");
					document.getElementById("infoTitle").focus();
				return false;
			}
			document.getElementById('content1').value = frames('eWebEditor1').getHTML();
			if(document.getElementById("content1").value == ""){
				alert("����дҪ��������Ϣ��");
				return false;
			}
			refreshForm('qgzxLogic.do?method=savePubInfo');
		}
	</script>
	</head>
	<body>
		<form method="post" id="myform"
			action="/xgxt/qgzxLogic.do?method=savePubInfo">
			<input type="hidden" id="gnmkdm" name="gnmkdm" value="${gnmkdm}"/>
			<div class="tab_cur" id="jd">
				<p class="location"  id="title_m">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				 <thead>
    				<tr>
        				<th colspan="4"><span>�����Ϣ</span></th>
        			</tr>
   				 </thead>
   				 <tfoot>
   				 	<tr>
						<td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
						          <div class="btn">
						          	<button type="button"  onclick="pubInfo()">
										�� ��
									</button>
									<button type="button" type="reset" name=b2 value="�� ��" >
										�� ��
									</button>		           
						          </div>
						 </td>
					</tr>
   				 </tfoot>
				<tbody>
				<tr>
					<th >
						<span class="red">*</span>����
					</th>
					<td colspan="3">
						<select name="infoTitle" id="infoTitle" style="width:20%">
							<logic:empty name="gnmkdm">
							<option value=""></option>
							<option value="�ڹ���ѧ���">
								�ڹ���ѧ���
							</option>
							<option value="�ڹ���ѧ��֪">
								�ڹ���ѧ��֪
							</option>
							<option value="��λְ����Ϣ">
								��λְ����Ϣ
							</option>
							<option value="��н���ڹ�ʾ">
								��н���ڹ�ʾ
							</option>
							</logic:empty>
							<!--ѧ����Ϣ-->
							<logic:equal value="N11" name="gnmkdm">
							<option value="ת����֪">
								ת����֪
							</option>
							</logic:equal>
						</select>
					</td>
				</tr>
				<tr>
					<th align=right width="100">
						<span class="red">*</span>�༭����
					</th>
					<td align=center colspan="3">
						<INPUT type="hidden" name="content1" value="">
						<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
							scrolling="no" width="100%" height="350"></IFRAME>
					</td>
				</tr>
			</TABLE>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>	
	  		alert("�����ɹ�!");
	  	</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>	
	  		alert("����ʧ��!");
	  	</script>
				</logic:equal>
			</logic:present>
		</FORM>
	</body>
</html>
