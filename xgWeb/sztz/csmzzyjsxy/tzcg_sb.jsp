<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<logic:equal value="1" name="sqsjTag">
		<body>
	</logic:equal>
	<logic:notEqual value="1" name="sqsjTag">
	<body onload="numChekPrint()">
	</logic:notEqual>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<html:form action="/csmz_sztz.do?method=tzcg_sb" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
	
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:equal value="1" name="sqsjTag">
			   <br />
				<br />
				<p align="center">
					<font color="red">�����趨ʱ�䷶Χ��,�ݲ������걨!</font>
				</p>
			</logic:equal>   
			<logic:notEqual value="1" name="sqsjTag">			
			<logic:notEmpty name="rs">
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/csmz_sztz.do?method=tzcg_sb" />
				<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue" scope="request"/>"/>
				<input type="hidden" id="jxsb" name="jxsb" value="${jxsb}"/>
				<div class="tab">
			  <table width="100%"  border="0" class="formlist" id="rsT">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>��д�����</span></th>
			        </tr>
			    </thead>
				 <tfoot>
				 <logic:notEqual value="view" name="doType">
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			        	
						<button  id="buttonSave"
							onclick="tzcgSbSave()">
							�ύ����
						</button>
						<button  onclick="expAppTab('rsT','ѧ�ָ����걨��','')">
							��ӡ����
						</button>
						
					</div></td>
			      </tr>
			      </logic:notEqual>
			    </tfoot>

						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th align="right" width="10%">
									<font color="red">*</font>ѧ��
								</th>
								<td align="left" width="40%">
								<html:text name='rs' property="xh" styleId="xh"  readonly="true"
									 onkeypress="if(event.keyCode == 13) return false;" />
									 <logic:notEqual value="modi" name="doType">
						
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);" class="btn_01"
											 id="buttonFindStu">
											ѡ��
										</button>
							
									</logic:notEqual>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th align="right" width="10%">
									<font color="red">*</font>ѧ��
								</th>
								<td align="left" width="40%">
									<input type="text" id="xh" name="xh"  value="<bean:write name='rs' property="xh" />" readonly="readonly" />
								</td>
							</logic:equal>
							<th align="right" width="18%">
							    <font color="red">*</font>
							    <logic:equal value="12104" name="xxdm">
							       �(��Ŀ)
							    </logic:equal>
							    <logic:notEqual value="12104" name="xxdm">
							        ��չ�(��Ŀ)
							    </logic:notEqual>
						    </th>
						    <td align="left">
						        <input type="hidden" name="xmdm" value="<bean:write name="xmdm" scope="request"/>">
						        <html:text name='rs' property="xmmc" styleId="xmmc" readonly="true" style="width:200px"/>
							<button onclick="tzxmInfoTo()"  class="btn_01">
										ѡ��
							</button>					       
						    </td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								����
							</th>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<th align="right">
							    ѧ��
							</th>
							<td align="left">
								<bean:write name='rs' property="xn" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								<bean:write name='rs' property="xb" />
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								<bean:write name='rs' property="xq" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								�꼶
							</th>
							<td align="left">							
								<bean:write name='rs' property="nj" />
							</td>							
							<th align="right">
								������Ŀ
							</th>
							<td align="left">
								<bean:write name='rs' property="kmmc" />						
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<th align="right">
								��Ŀ����<bean:message key="lable.xsgzyxpzxy" />(����)
							</th>
							<td>
								<bean:write name='rs' property="bmmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								רҵ
							</th>
							<td align="left">
								<bean:write name='rs' property="zymc" />
							</td>
							<th align="right">
							    <logic:equal value="12104" name="xxdm">
							       �(��Ŀ)����
							    </logic:equal>
							    <logic:notEqual value="12104" name="xxdm">
							        �(��Ŀ)���� 
							    </logic:notEqual>
							</th>
							<td align="left">
								<bean:write name='rs' property="xmjb" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								�༶
							</th>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>							
							<th align="right">								
							        ������(�걨��) 							   
							</th>
							<td align="left">						
							  	<bean:write name='rs' property="xmsbr" />			    							
							</td>
						</tr>						
						<tr align="left">
							<th align="right">
								�����ɫ
							</th>
							<td align="left">
								<html:select name="rs" property="cyjs" style="width:90px;background-color:#FFFFFF"
								styleId="cyjs">
								<html:option value="����">����</html:option>
								<html:options collection="cjsfList" property="en"
									labelProperty="cn" />
							    </html:select>
							</td>						
							<th align="right">
								<logic:equal value="12104" name="xxdm">
							       ָ����ʦ
							    </logic:equal>
							    <logic:notEqual value="12104" name="xxdm">
							       ���쵥λ
							    </logic:notEqual>
							</th>
							<td align="left">
								<bean:write name='rs' property="zzdw" />
							</td>
						</tr>
						<tr align="left">				
							<th align="right">
								����ʱ��
							</th>
							<td align="left">
								<bean:write name='rs' property="zbsj" />
							</td>
							<logic:equal name="jxsb" value="xssb">
							<th align="right">
								<font color="red">*</font>�񽱽���
							</th>
							<td align="left">
								<html:select name="rs" property="jxlb" style="background-color:#FFFFFF"
								styleId="jxlb">
								<html:option value=""></html:option>
								<html:options collection="jxjbList" property="jxid"
									labelProperty="jxm" />
							    </html:select>
							</td>
							</logic:equal>
							<logic:equal name="jxsb" value="lssb">
								<th align="right">
								
								</th>
								<td align="left">
									<input type="hidden" name="jxlb" id="jxlb" value="9527"/>
								</td>
							</logic:equal>
						</tr>		
					  <logic:present name="showdybz">
					  <tr align="left">
							<th align="right">
								
							</th>
							<td align="left">
								
							</td>						
							<th align="right">
								�Ƿ���֤���ϴ�ӡ
							</th>
							<td align="left">
								<html:select name="rs" property="sfdy" style="width:90px;background-color:#FFFFFF"
								styleId="sfdy" onchange="numChekPrint()">
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>																
							    </html:select>
							</td>
						</tr>							
						</logic:present>	
						<tr align="left">
							<th align="right">
								��Ŀ����
							</th>
							<td colspan="3">
								 <html:textarea name='rs' property='xmms' styleId="xmms"  
								    rows='8'  style="word-break:break-all;width:85%"/>								
							</td>
						</tr>							
						<tr align="left" style="width:500px">
							<th align="right">
								�ɹ�����
								<br><font color="red">(��300����)</font>
							</th>
							<td colspan="3">
								 <html:textarea name='rs' property='cgms' styleId="cgms"  
								    rows='8'  style="word-break:break-all;width:85%"/>
							</td>
						</tr>				
					</table>
			</logic:notEmpty>
			</logic:notEqual>		
			<logic:equal value="yes" name="done">
			  <script type="text/javascript">
			      alert('�걨�ɹ���');
					if(window.dialogArguments){
				 		window.dialogArguments.document.getElementById('search_go').onclick();
				 	}
				  Close();
			  </script>
			</logic:equal>
		</html:form>
	</body>
<script type="text/javascript">
function tzxmInfoTo(){
    if($('xh').value==""){
        alert("ѧ�Ų���Ϊ�գ�");
        return false;
    }else{
        var url = "/xgxt/csmz_sztz.do?method=tzxm_xxcx&xh="+$('xh').value+"&url="+$('url').value;        
        showTopWin(url,800,550);            
    }
}
function tzcgSbSave(){ 

  var notEmpty="xh-xmmc-jxlb";
  
  if($("jxsb").value=="lssb"){
  	notEmpty="xh-xmmc";
  }
         
  if(IsNoEmpty(notEmpty)){ 
  	
     if($("cgms").value.length>300){
         alert("�ɹ������������ܴ���300��");
         return false;
     }    
     var xh = $("xh").value;
     var xmdm = $("xmdm").value;
     var cyjs = $("cyjs").value;
     var jxlb ="";
     if($("jxsb").value=="lssb"){
	  	jxlb="9527";
	 }else{
	 	jxlb = $("jxlb").value;
	 }
     
     pkV = xh+xmdm+cyjs+jxlb;
     getSztzData.getInfoEx("csmz_tzcgb","xh||xmid||cyjs||jxlb",pkV," 1=1",function(str){
		     if(str){		         	
		        if(confirm("��������չ�ɹ����걨,\n�����������,�Ƿ��ٴ��ύ��")){
		           refreshForm('/xgxt/csmz_sztz.do?method=tzcg_sb&act=save');
                   $("buttonSave").disabled=true;
		        }	          			  
		     }else{
                refreshForm('/xgxt/csmz_sztz.do?method=tzcg_sb&act=save');
                $("buttonSave").disabled=true;
 		     }
    }); 
  }    
}
function numChekPrint(){
     var sfdy = "";
     var xh = "";
     var xmid = "";
     sfdy = ($("sfdy"))?$("sfdy").value:"";
     xh = $("xh").value;
     xmid = $("xmdm").value;
     if(sfdy=="��"){
        getSztzData.getTzxmDyCout(xh,xmid,function(str){
              if(parseInt(str)>=8){
                 alert("���걨�ĳɹ�����ѧ�ꡢ��Ŀ�У���ӡ���Ѵﵽ8����������ѡ���ӡ\n\n������\"(�ƻ���Ŀ)�ɹ���֤ - ��ӡ�ɹ�����\"�����н��е������ٽ����걨��")
                if($("buttonSave")){ $("buttonSave").disabled=true;}
              }
        });
     }else{        
        if($("buttonSave")&&$("buttonSave").disabled){
           $("buttonSave").disabled = false;
        }
     }
}
</script>
</html>
