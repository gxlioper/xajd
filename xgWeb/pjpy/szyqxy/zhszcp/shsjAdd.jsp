<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
		<script type="text/javascript">
		    var count = 1;
		    function trAdd(the_tab,type){
		    var len = document.getElementById(the_tab).rows.length+1;
		    var num = $("numAdd").value;
		    count=len;     
			var cellfu =[
			function(data){
				return count+ "<input type='hidden' style='width:25px'  name='_xuh' value='"+count+"'>";
			},
			function(data){	
			    var htmltext = "<textarea  name='hdnr' style='width:100px' rows='2' id='hdnr" + count + "' />";	  		
				return htmltext;
		    },   	
		    function(data){
		    	var htmltext = "<input type='text' style='width:100px'  name='hddd' id='hddd" + count + "' maxlength='50' />";   	
		    	return htmltext;
		    }, 
		    function(data){
		    	var htmltext = "<input type='text' style='width:100px'  name='hdrq' id='hdrq" + count + "' readonly='true' onblur='dateFormatChg(this)' style='cursor:hand;' onclick='time(this.id)' />";   	
		    	return htmltext;
		    },
		    function(data){
		    	var htmltext = "<input type='text' style='width:40px'  name='hdsj' id='hdsj" + count + "' maxlength='50'/>";   	
		    	return htmltext;
		    },       
			function(data){	    
				var htmltext = "<select style='width:50px'  name='jjf' id='jjf" + count + "'>";
			  		htmltext+= "<option value='�ӷ�'>�ӷ�</option>";
			  		htmltext+= "<option value='����'>����</option></select>";
				return htmltext;
		    },
		    function(data){
		    	var htmltext = "<input type='text' style='width:25px'  name='shfz' id='shfz" + count + "' maxlength='5'";
		    		htmltext += "onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)'/>";
		    	return htmltext;
		    },       
			function(data){	    
				var htmltext = "<input type='text' style='width:50px'  name='shType' id='shType" + count + "' maxlength='50' value='δ���' readonly=true />";	  		
				return htmltext;
		    }
			];	
			if(type=='add'){
		       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		    }else{
		       if(num==""||num==null){	
		           return false;
		       }
		       for(i=count;i<=num;i++){          
		          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		          count++;
		       }
		       $("numAdd").value = "";
		    }        
		}
		function time(id){
			return showCalendar(id,'y-mm-dd');
		}
		function trDel(the_tab){
		    var tabobj = document.getElementById(the_tab);
		    var length = tabobj.rows.length;   
		    if(length==0){
		       return false;
		    }
		    if(confirm("ȷ��Ҫɾ����"+(length)+"�У�")){
		       if($("shType"+length).value!="δ���"){
		         alert("�ü�¼�Ѿ�����ز�����ˣ����ܽ���ɾ��������");   
		         return false;
		       }else{
		         tabobj.deleteRow(tabobj.rows.length-1);    
		       }       
		    }
		}
		function trDelAll(the_tab){
		    var tabobj = document.getElementById(the_tab);
		    var length = tabobj.rows.length;
		    var num = $("numDel").value; 
		    if(length==0){
		       $("numDel").value = "";
		       return false;     
		    }
		    if(num==""||num==null){	
		       return false;
		    }
		    if(num>length){
		      num = length;
		    }
		    if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){ 
		         for(i=1;i<=num;i++){           
		            if($("shType"+length).value!="δ���"){
		              alert("���Ѿ�����ز�����˵ļ�¼�����ܽ���ɾ��������");   
		              return false;
		            }
		            length--;
		         }
		       for(i=1;i<=num;i++){
		          length--;
		          tabobj.deleteRow(tabobj.rows.length-1);
		       }
		    }
		    $("numDel").value = "";
		}	
		function fillValue(obj){
				var xm= "xm" + obj.id.replace("xh","");
				var bj= "bj" + obj.id.replace("xh","");
		  		if(obj.value==""){
		  		  return false;
		  		}	
				getSztzData.getXsInfo(obj.value,function(data){
				if(data!=null){
					$(xm).value=data[0];
					$(bj).value=data[1];
				}else{
					alert("��ѧ�Ų����ڣ���������ȷѧ�ţ�");
					obj.value="";
					obj.focus();
					}
				});
		
		}
		function shsjSave(the_tab){
			var tabobj = document.getElementById(the_tab);
			var rowLen = tabobj.rows.length;
			var nullCout = 0;
			var xh = $("xh").value;
			var xn = $("xnV").value;
			var xq = $("xqV").value;
		
			if(xh == ""){
				alert("��ȷ���������ѧ��");
				return false;
			}
			if(rowLen==0){
			   alert("�������Ϊ�գ�");
			   return false;
			}
			for(var i=1;i<=rowLen;i++){
			    if($("hdnr"+i)){
					if($("hdnr"+i).value == ""){
						alert("��"+i+"����ݲ���Ϊ�գ���ȷ��");
						return false;
					}
				}
				if($("hdnr"+i)){
					if($("hdnr"+i).value.length > 500){
						alert("��"+i+"���������������500���ڣ���ȷ��");
						return false;
					}
				}
				if($("hddd"+i)){
					if($("hddd"+i).value == ""){
						alert("��"+i+"��ص㲻��Ϊ�գ���ȷ��");
						return false;
					}
				}
				if($("hdrq"+i)){
					if($("hdrq"+i).value == ""){
						alert("��"+i+"����ڲ���Ϊ�գ���ȷ��");
						return false;
					}
				}		
				if($("shfz"+i)){
					if($("shfz"+i).value == ""){
						alert("��"+i+"�з�ֵΪ�գ���ȷ��");
						return false;
					}
				}
			}
		   showTips('���������У���ȴ�......');
		   refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_shsjAdd&doType=save&xn='+xn+'&xq='+xq);
		   $("buttonSave").disabled=true;
		}
		
		function onShow(){
			var pk = $("pk").value;
			if(pk != ""){
				dwr.engine.setAsync(false);
				getSzPjpyDAO.dao_getShsj(pk,function(data){
					if(data !=null && data.length>0){
						$("numAdd").value=data.length;
						trAdd('flag','madd');    
						for(var i=1;i<=data.length;i++){
							if($("hdnr"+i)){
								$("hdnr"+i).value = data[i-1].hdnr == null ? "" : data[i-1].hdnr;
							}
							if($("hddd"+i)){
								$("hddd"+i).value = data[i-1].hddd;
							}
							if($("hdrq"+i)){
								$("hdrq"+i).value = data[i-1].hdrq;
							}
							if($("hdsj"+i)){
								$("hdsj"+i).value = data[i-1].hdsj;
							}
							if($("jjf"+i)){
								$("jjf"+i).value = data[i-1].jjf;
							}
							if($("shfz"+i)){
								$("shfz"+i).value = data[i-1].shfz;
							}
							if($("shType"+i)){
								$("shType"+i).value = data[i-1].xxsh;
							}
						}
					}		
				});
				dwr.engine.setAsync(true);
			}
		}
		
	
		jQuery(function(){
			onShow();
		})
	</script>
	</head>
	<body >
		<html:form action="/pjpyszyqwh">
			<input type="hidden" id="url" name="url" value="/pjpyszyqwh.do?method=szyc_shsjAdd"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
			<input type="hidden" id="pk" name="pk" value="${pk}"/>
			<input type="hidden" id="xnV" name="xnV" value="${xnV}"/>
			<input type="hidden" id="xqV" name="xqV" value="${xqV}"/>
			
			<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ۺ�����-ѧ���ۺ��������ɿ�-���ʵ����ά��</a>
			</p>
		</div>
			
			<table class="formlist" border="0" id="rsTable" style="width: 100%">
				<tr style="height: 23px">
					<th>
						ѧ��
					</th>
					<td>
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					
					<th>
						����
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
							<html:text name='rs' property="xq" styleId="xq" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						�Ա�
					</th>
					<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						רҵ
					</th>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<th>
						�༶
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
				</tr>
			</table>
			<p>
				<!-- ��ѯ�õ�����������ʾ���� -->
				<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
				<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
				<input type="text" name="numAdd" id="numAdd" style="width: 20px"
					onblur="trAdd('flag','madd')"/>
				&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
				<input type="text" name="numDel" id="numDel" style="width: 20px"
					onblur="trDelAll('flag')"/>
				&nbsp;��
			</p>
			<table id="rsT" class="formlist">
				<!-- ��ӡʱ��һ�в���ʾ- -->
				<thead style="height: 23px">
					<tr>
						<th>
							���
						</th>
						<th>
							�����<font color="red">(��500����)</font>
						</th>											
						<th>
							��ص�
						</th>
						<th>
							�����
						</th>
						<th>
							�ʱ��(Сʱ)
						</th>											
						<th>
							�Ӽ���
						</th>
						<th>
							�����ֵ
						</th>	
						<th>
							���״̬
						</th>																						
					</tr>
				</thead>
				<tbody class="tbstyle" id="flag">
				
				</tbody>
			</table>
							
			<div  align="right">
				<span class="openbutton">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" id="buttonSave" onclick="shsjSave('flag');">
						�� ��
					</button>
					</logic:notEqual>
					<button type="button" class="button2" id="buttonClose" 
					onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();" id="buttonClose">
						�� ��
					</button>
				</span>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
