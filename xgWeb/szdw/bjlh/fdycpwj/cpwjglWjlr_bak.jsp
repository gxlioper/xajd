<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function cpwjDaSave(){
			var stid=document.getElementsByName("hidden_stid");//����id
			var stmc=document.getElementsByName("hidden_stmc");//��������
			var stlx=document.getElementsByName("hidden_stlx");//��������

			var temp;//��ʱ�û���ȡ
			if(stid&&stid.length>0){
				for(var i=0;i<stid.length;i++){
					temp=document.getElementsByName("st_"+stid[i].value);
					
					if(temp&&temp.length>0){
						if(stlx[i].value=="1"){//��ѡ��
							var b=false;
							for(var j=0;j<temp.length;j++){
								if(temp[j].checked){
									b=true;
								}
							}
							if(!b){
								alert("��ѡ��"+stmc[i].value);
								return false;
							}
						}else if(stlx[i].value=="2"){//�����
							if(temp[0].value.trim()==""){
								alert("����д"+stmc[i].value);
								return false;
							}
						}
					}else{
						//���Ϊ�������쳣
					}
				}
			}
			confirmInfo("�ʾ��ύ�󽫲������޸ģ�ȷ���ύ��",function(data){
				if("ok"==data){
					saveData('bjlh_fdycpwj.do?method=cpwjglWjlr&doType=save','');
				}
			});
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdycpwj" method="post">
		<html:hidden property="wjid" name="rs" styleId="wjid"/>		
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
			<table class="formlist" width="95%">
				<thead>
					<tr>
						<th><span>����Ա�����ʾ�${rs.wjmc}</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							ѧ�꣺${xn}
							&nbsp;&nbsp;
						          ���ڲ���:${fdyInfo.xymc }
						    &nbsp;&nbsp;
						          ����Ա����:${fdyInfo.xm }
						    &nbsp;&nbsp;
						</td>
					</tr>
				<logic:present name="stList">
					<logic:iterate id="st" name="stList">
					<tr>
						<td>
							<input type="hidden" name="hidden_stid" value="${st.stid}" />
							<input type="hidden" name="hidden_stmc" value="${st.stmc}" />
							<input type="hidden" name="hidden_stlx" value="${st.stlx}" />
							${st.r}��<bean:write name="st" property="stmc" format="true"/>
						<br/><br/>
							${st.xxHtml }
						</td>
					</tr>
					</logic:iterate>
				</logic:present>
				</tbody>
				<tfoot>
					<tr>
					  	<td>
					  		<div class="bz">"<span class="red">*</span>"Ϊ������</div>
						    <div class="btn">
								<logic:equal value="false" name="xswjstsfzd">
				            		<button type="button" name="����" id="buttonSave" onclick="cpwjDaSave();return false;">����</button>
			          			</logic:equal>
			          			<logic:equal value="true" name="xswjstsfzd">
<%--				            	<button type="button" name="����" id="buttonSave" onclick="alertInfo('�ʾ������𣬲�����������');return false;">����</button>--%>
			          			</logic:equal>
								<button type="button" name="����" type="reset">�� ��</button>	
						    </div>
					    </td>
					</tr>
			    </tfoot>
			</table>
		</div>
	</html:form>
	<logic:present name="back">
		<script type="text/javascript">
			alertInfo("${back}",function(){
				if(window.dialogArguments){
					if(window.dialogArguments.document.getElementById("search_go")){
						dialogArgumentsQueryChick();
					}
					window.close();
				}
			});
		</script>
	</logic:present>
</body>
</html>
