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
								alertInfo("��ѡ��"+stmc[i].value);
								return false;
							}
						}else if(stlx[i].value=="2"){//�����
							if(temp[0].value.trim()==""){
								alertInfo("����д"+stmc[i].value);
								return false;
							}
						}
					}else{
						//���Ϊ�������쳣
					}
				}
			}

			//////////////////////���˱���֤start
			var df = document.getElementsByName("df");
			for(var i=0;i< df.length;i++){
				if(df[i].value==""){
					alertInfo("�÷����Ϊ�գ�");
					return false;
				}
			}
			/////////////////////���˱���֤end
			confirmInfo("ȷ���ύ��",function(data){
				if("ok"==data){
					var fdyid=document.getElementById("fdyid").value;
					saveData('bjlh_fdycpwj.do?method=cpwjglWjlrSingle&fdyid='+fdyid+'&doType=save','');
				}
			});
		}

		function checkValue(obj,ind){
			checkInputNum(obj);
			var fzqjGet = jQuery("#fzqj_"+ind);
			if(fzqjGet.length>0){
				var fzqj = fzqjGet.val();
				var fzqjarr = fzqj.split('-');
				var srfz = jQuery(obj).val();
				if(fzqjarr.length == 1){
					if(srfz !="" &&( eval(srfz)<0 || eval(srfz)>eval(fzqjarr[0]))){
						jQuery(obj).val("");
						alertInfo("�������ֵ�����ڷ�ֵ�����ڣ�",function(){
							jQuery(obj).focus();
						});
						return false;
					}
				}else if(fzqjarr.length == 2){
					var fzDown = fzqjarr[0];
					var fzUp = fzqjarr[1];
					if(srfz !="" &&( eval(srfz)<0 ||  eval(srfz)>fzUp ||  eval(srfz)<fzDown)){
						jQuery(obj).val("");
						alertInfo("�������ֵ�����ڷ�ֵ�����ڣ�",function(){
							jQuery(obj).focus();
						});
						return false;
					}
				}
			}
		}
		function fh(){
			var url='bjlh_fdycpwj.do?method=cpwjglWjlr';
			refreshForm(url);
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdycpwj" method="post">
		<html:hidden property="wjid" name="rs" styleId="wjid"/>		
		<input type="hidden" name="fdyid" id="fdyid" value="${fdyid }"/>
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<logic:equal value="fh" name="fh">
		<div class="toolbox">
			<div class="buttonbox">
				<ul>
						<li><a href="javascript:void(0);" onclick="fh();" class="btn_fh">����</a></li>
				</ul>
			</div>
		</div>
		</logic:equal>
		<div class="tab">
			<table class="formlist" width="95%">
				<thead>
					<tr>
						<th><span>����Ա�����ʾ�222��${rs.wjmc}</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							ѧ�꣺${xn}
							&nbsp;&nbsp;
						          ���ڲ���:${fdyInfo.bmmc }
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
<%--				<tfoot>--%>
<%--					<tr>--%>
<%--					  	<td>--%>
<%--					  		<div class="bz">"<span class="red">*</span>"Ϊ������</div>--%>
<%--						    <div class="btn">--%>
<%--								<logic:equal value="false" name="xswjstsfzd">--%>
<%--				            		<button type="button" name="����" id="buttonSave" onclick="cpwjDaSave();">����</button>--%>
<%--			          			</logic:equal>--%>
<%--								<button type="button" name="����" type="reset">�� ��</button>	--%>
<%--						    </div>--%>
<%--					    </td>--%>
<%--					</tr>--%>
<%--			    </tfoot>--%>
			</table>
		</div>
		<logic:equal value="11417" name="xxdm">
		<div class="tab">
		<table class="formlist" width="95%">		
			<thead>
				<tr>
					<th colspan="5"><span>����Ա������${rsKhb.khbmc}</span></th>
				</tr>
			</thead>
			<tbody>	
			<tr>
				<td colspan="5">				
					 ѧ�꣺${rsKhb.xn }
					&nbsp;&nbsp;
					 ���ڲ��ţ�${rs1.bmmc }
					&nbsp;&nbsp;
					 ����Ա������${rs1.xm }
					<html:hidden property="khbid" name="rs" styleId="khbid"/>
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th>һ��ָ��</th>
				<th>����ָ��</th>
				<th>��������</th>
				<th>��ֵ</th>
				<th>�÷�</th>
			</tr>
			</thead>
			<tbody>	
			<logic:present name="xmList">
			<logic:iterate id="xm" name="xmList" indexId="ind"> 
				<tr>
					<logic:notEmpty name="xm" property="yjzbRowNum">
						<td width="150px" style="word-break:break-all"  rowspan="${xm.yjzbRowNum}">${xm.yjzb }</td>
					</logic:notEmpty>
					
					<td width="150px" style="word-break:break-all" >${xm.ejzb }</td>
					<td width="300px" style="word-break:break-all" >${xm.khnr }</td>
					<td width="25px"><font value="${xm.fzqj}">${xm.fzqj}</font>
						<input type="hidden" id="fzqj_${ind}" value="${xm.fzqj }"/></td>
					<td width="50px">
						<input type="hidden" name="xmid" value="${xm.xmid }"/>
						<input type="text" id="df" name="df" maxlength="5" style="width: 15px"
							onblur="checkValue(this,${ind})" value="${xm.df}"/>
						<font class="red">*</font>
					</td>
				</tr>
			</logic:iterate>
			</logic:present>
			</tbody>
			<%--<tfoot>
			      <tr>
			        <td colspan="5">
			        	<div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				          	<logic:equal value="false" name="khbsfzd">
				            	<button type="button" name="����" id="buttonSave" onclick="cpwjDaSave();return false;">����</button>
								<button type="button" name="����" type="reset">�� ��</button>
							</logic:equal>	
				          </div>
				    </td>
			      </tr>
			    </tfoot>--%>
		</table>
		</div>
		</logic:equal>
		<div class="tab">
			<table  class="formlist" width="95%">
				<tfoot>
			      <tr>
			        <td colspan="5">
			        	<div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				          	<logic:equal value="false" name="khbsfzd">
				            	<button type="button" name="����" id="buttonSave" onclick="cpwjDaSave();return false;">����</button>
							</logic:equal>	
				          </div>
				    </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		
	</html:form>
	<logic:present name="back">
		<script type="text/javascript">
			alertInfo("${back}");
		</script>
	</logic:present>
</body>
</html>
