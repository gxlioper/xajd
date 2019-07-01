<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
	
	function jyzxZxsUpdate(){
	 var name = document.getElementById("name").value;
     var email = document.getElementById("email").value;
     var userid = document.getElementById("userid").value;
     var lxdh = document.getElementById("lxdh").value;
     
     var zxsjj = document.getElementById("zxsjj").value;
		if(zxsjj.length>300){
			alert("��ѯʦ����������ܳ���300��");
			return false;
		}
     if(name==""){
     alert("��������Ϊ�գ�");
     return false;
     }
     if(isNumber(name)){
     alert("��������Ϊ���֣�");
     return false;
     }
     if(userid==""){
     alert("�û�������Ϊ�գ�");
     return false;
     }  
     if(lxdh!=""&&!isNumber(lxdh)){
     alert("��ϵ�绰ӦΪ���֣�");
     return false;
     }  
     if((email != null) && (email!= "") && (!isEmail(email))){
     alert("�������䲻�Ϸ���");
     return false;
     }
		 document.forms[0].action = "/xgxt/jyzxZxsUpdate.do?doType=update";
		 document.forms[0].submit();       
    }
    
    //exclude left and right space; 
	function trim(s){
 		return rtrim(ltrim(s)); 
	}
	//exclude left space; 
	function ltrim(s){
 		return s.replace( /^\s*/, ""); 
	} 
	//exclude right space; 
	function rtrim(s){ 
 		return s.replace( /\s*$/, ""); 
	}
    
    function isEmail(s){
	s = trim(s); 
 	var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	return p.test(s);
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    } 
	
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ѯʦ��Ϣ�޸�</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯʦ��Ϣ�޸�</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="jyzxZxsUpdate();return false;" id="saveButton">
										�� ��
									</button>
									<button onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								��ѯʦ���
							</th>
							<td width="30%">
								<html:text name="rs" property="num" 
									maxlength="3" 
									onkeyup="value=value.replace(/[^\d]/g,'')"
									readonly="true" />
							</td>
							<th width="16%">
								��ѯʦ����
							</th>
							<td width="30%">
								<html:text name="rs" property="name" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѯʦ����
							</th>
							<td>
								<div class="pos" style="z-index:3">
									<html:text name="rs" property="age" 
											  maxlength="3"
											  onkeyup="checkAge(this)" />
									<div id="ageErrow" class="hide">
							            <p>������1-120������</p>
							        </div>
							    </div>
							</td>
							<th>
								��ѯʦ�Ա�
							</th>
							<td>
								<html:select name="rs" property="xb">
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��½��
							</th>
							<td>
								<html:text name="rs" property="userid" maxlength="10" />
							</td>
							<th>
								��ϵ�绰
							</th>
							<td>
								<div class="pos" style="z-index:2">
									<html:text name="rs" property="lxdh"  
										onkeyup="checkPhoneV4(this)"
										maxlength="11"
									/>
									<div id="phoneErrow" class="hide">
											<p>
												�绰��ʽ����ȷ
											</p>
									</div>
								</div>	
							</td>
							<th style="display: none">
								��½����
							</th>
							<td style="display: none">
								<html:text name="rs" property="password"  />
							</td>
						</tr>
						<tr>
							<th>
								��ѯʦ�ʸ�
							</th>
							<td>
								<html:text name="rs" property="zxszg"  maxlength="15" />
							</td>
							<th>
								��������
							</th>
							<td>
							<div class="pos" style="z-index:1">
								<html:text name="rs" property="email" 
									maxlength="30"
									onkeyup="checkEmaile(this)"
								/>
								<div id="emaliErrow" class="hide">
										<p>
											�����ʽ����ȷ
										</p>
								</div>
							</div>
							</td>
						</tr>
						<tr>
							<th>
								��ѯʦ���
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxsjj" rows="6"
									onblur="checkLen(this,300)"
									style="width:90%" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("�޸ĳɹ�!");
                      if (window.dialogArguments) {
							Close();
							dialogArgumentsQueryChick();
					  }
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("�޸�ʧ��");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
