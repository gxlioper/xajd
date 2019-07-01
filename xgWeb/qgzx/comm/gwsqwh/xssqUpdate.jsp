<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/systemFunction.js'></script>
		<script type="text/javascript">

		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}

		//����
		function save(){
			var xh = document.getElementById('save_xh').value;
			systemFunction.checkExists("qgzx_zgdzdx_hmdb", "xh", xh, function(data){
				if(data == true){
					alert("�������е�ѧ�����������λ��");
					return false;
				}else{
					var sqsyrs = document.getElementById('sqsyrs').value; //����ʹ������
					var syknss = document.getElementById('syknss').value; //ʹ����������
					var save_sfpks = document.getElementById('save_sfpks').value; //�Ƿ�ƶ����
					if(sqsyrs=='' || sqsyrs==null || sqsyrs==0){
						alert('�ø�λĿǰ�����ˣ��������룡');
						return false;
					}else if(sqsyrs==syknss && save_sfpks=='��'){
						alert('�������������޷�����ø�λ��');
						return false;
					}else{
						saveDataShowTips('qgzx_gwsqwh.do?method=xssqUpdate&doType=save','save_xh-save_kcjqgzxsj-save_xssq','���ڱ������Ժ�');
					}
				}
			});
		}
	</script>
</head>
<body>
	<html:form action="/qgzx_gwsqwh" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" id="url" name="url" value="/xgxt/qgzx_gwsqwh.do?method=xssqUpdate"/>
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		
		<div class="tab"/>
		<table class="formlist" width="93%">
			<%@include file="gwxx.jsp" %>
			<%@include file="xsxx.jsp" %>
			<!-- �鿴���޸ķֱ���ز�ͬ����չʾ������Ϣ -->
			<logic:notEqual value="yes" name="stuck" scope="request">
			<logic:equal value="0" name="shcount" scope="request">
				<%@include file="sqxx.jsp" %>
			</logic:equal>
			<logic:notEqual value="0" name="shcount" scope="request">
				<%@include file="sqxxview.jsp" %>
			</logic:notEqual>
			</logic:notEqual>
			<logic:equal value="yes" name="stuck" scope="request">
				<%@include file="sqxxview.jsp" %>
			</logic:equal>
			
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		          <logic:notEqual value="yes" name="stuck" scope="request">
		          	<logic:equal value="0" name="shcount" scope="request">
		          			<button type="button" name="�ύ" id="buttonSave" onclick="save();return false;">�� ��</button>
		          	</logic:equal>
		          	<logic:notEqual value="0" name="shcount" scope="request">
		          	 	<font color="red">�Ѿ���ʼ��ˣ����ܽ����޸ģ�</font>
		          	</logic:notEqual>
		          </logic:notEqual>
		            <!-- 
		            <button type="button" name="�ر�" onclick="window.close();return false;">�� ��</button>
		             -->
		             <logic:equal value="yes" name="gb">
		            	 <button type="button" name="�ر�" onclick="window.close();return false;">�� ��</button>
		             </logic:equal>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<script type="text/javascript">
			alertInfo('${message }');
			if (window.dialogArguments && window.dialogArguments.document.getElementById('search_go')) {
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
