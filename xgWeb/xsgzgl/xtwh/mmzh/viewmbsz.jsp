<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="xsgzgl/xtwh/mmzh/js/mmzh.js"></script>
	<script type="text/javascript">
	jQuery(function() {
		//document.getElementById("wtda").value = '${wtda}';
		document.getElementById("wtid").value = '${wtid}';
	});
	</script>
	<style>
	</style>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - ����ά�� - �ܱ������޸�</a>
			</p>
		</div>
	
		<html:form action="/mmzhgl_mmzh" method="post" styleId="MmZhForm" onsubmit="return false;">
		<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<font color="red">�����·�����������ѡ���ܱ����⣬����д�ܱ��𰸣���������������ܱ���������ã������ÿ��԰������һ��Լ�������</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>		
		<div class="tab">
		  <table width="100%"  border="0" class="formlist">
		    <thead>
		    	<tr>
		        	<th colspan="4"><span>�ܱ������޸�</span></th>
		        </tr>
		    </thead>
		    <tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		            <button type="button" name="�޸�" onclick="mbsz()">����</button>
		          </div></td>
		      </tr>
		    </tfoot>
		    <tbody>
		      <tr>
		        <th ><span class="red">*</span>�ܱ�����</th>
		        <td >
		        	<html:select   property="wtid" styleId="wtid" style="width:145px;">
						    <html:options collection="mbList" property="wtid" labelProperty="mbwt" />
					 </html:select>	
		        </td>
		      </tr>
		      <tr>
		        <th><span class="red">*</span>�ܱ���</th>
		        <td>
					  <input name = "wtda" id = "wtda" maxlength="50" style="width:145px;">
		        </td>
		      </tr>
		    </tbody>
		  </table>
		  </div>
		</html:form>
	</body>
</html>
