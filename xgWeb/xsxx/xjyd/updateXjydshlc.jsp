<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script type="text/javascript" src="js/xgutil.js"></script>
<script>
	function save(){
		//�����ֶ��Ƿ���д
		if(filedNotNull(['shlcid'])){
			//�ύ
			refreshForm('xjyd.do?method=updateXjydshlc&doType=save');
		}else{
			alert('�뽫��*�ŵ���Ŀ��д������');
			return false;
		}	
	}
	
</script>
</head>

<body>
	<html:form action="/xjyd.do?method=updateXjydshlc" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>

		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4"> 
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="15%">�춯������</th>
						<td>
							${rs.ydlbm}
							<input type="hidden" name="save_ydlbm" value="${rs.ydlbm}"/>						
						</td>
						<th width="15%">�춯�������</th>
						<td width="35%">
							${rs.ydlbmc}
						</td>
					</tr>
					<tr>
						<th>ѧ��״̬</th>
						<td>							
							${rs.xjzt}						
						</td>
						<th>�Ƿ���У</th>
						<td>
							${rs.sfzx}
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4"> 
								<span>���������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th><font color="red">*</font>�������</th>
						<td>
							<html:hidden property="save_shlcid" name="rs" styleId="shlcid"/>
							<input type="text" name="shlcmc" id="shlcmc" value="${rs.shlcmc}" readonly="readonly"/>
							<logic:notEqual value="view" name="doType">
								<button type="button" class="btn_01" onclick="showTopWin('commXgInfo.do?method=choiceLc',650,500);" id="button_shlc" style="display: ">ѡ��</button>
							</logic:notEqual>
						</td>
						<th>ͬ������뷽ʽ</th>
						<td>
							<input type="text" name="tjbcyfs" id="tjbcyfs" value="${rs.tjbcyfs}" readonly="readonly"/>
						</td>
					</tr>								
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						<div class="buttontool">
			            <logic:notEqual value="view" name="doType">
							<button type="button" class="" onclick="save();return false;" >
								��&nbsp;&nbsp;��
							</button>
			            </logic:notEqual>
						<button type="button" class="" onclick="Close();return false;">
								��&nbsp;&nbsp;��
						</button>
						</div>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>		
		</div>	

		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</html:form>
</body>
</html>