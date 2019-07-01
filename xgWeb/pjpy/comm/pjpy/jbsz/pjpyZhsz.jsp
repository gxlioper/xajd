<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<style> 
		.liucheng_xg_pj {
			background:url(<%=stylePath%>images/xg/liucheng_xg_pj.gif) no-repeat;
			height:393px;
			width:740px;
			margin:5px auto;
			position:relative
		}
	  </style>
		<script type="text/javascript">
      	//��ҳ�����в���,�ý��õĽ��á�
      	jQuery(function(){
      		jQuery.ajaxSetup({async:false});
      		jQuery.post('pjpyJbsz.do?method=getPjzqSfsz',{},function(data){
      			 if (data == '1'){
      			 	//�������ý���
      			 	plszDis(['pjpy_zqsz'],true);
      			 	setQtzc();
      			 } else {
      			 //����������������õģ���ô�����������ã�����ȥ���������ã�
      			 	setNotZqszDisabled();
      			 }
      		});
      		jQuery.ajaxSetup({async:true});
      	});
      
      	function setQtzc(){
      		var xmsz = ['pjpy_sjsz','pjpy_tzsz','pjpy_jdsz','pjpy_tjsz','pjpy_rssz'];
      		jQuery.ajaxSetup({async:false});
      		//������Ա����ô��ʼ����
      		jQuery.post('pjpyJbsz.do?method=getPjrySfwh',{},function(data){
      			//������Աô�г�ʼ����Ҫ���¼���������
      			if (data == '0'){
      				plszDis(['pjpy_ryqd','pjpy_zcsz','pjpy_xmwh'],true);
      				plszDis(xmsz,true);
      			} else {
      				//������Ա�г�ʼ�����������۲����ò�������Щ��Ҫ����
      				jQuery.post('pjpyJbsz.do?method=getPjzcSfsz',{},function(data){
						if (data != '0') {
							//���۲����������ݣ�������Ա���ý���
							plszDis(['pjpy_ryqd','pjpy_rycsh'],true);
							//����Ȩ���ݸ�������Ŀά��
							jQuery.post('pjpyJbsz.do?method=getPjxmSfwh',{},function(data){
				    			if (data != '0') {
									//ά���˵�ǰ���ڵ�������Ŀ���۲����ý���
									plszDis(['pjpy_zcsz'],true);
								} else {
									//��ǰ����û��ά��������Ŀ����ô���أ���Ŀ���ý���
									plszDis(xmsz,true);
								}
				    		});
						} else {
							//�۲�����û������Ŀά������Ŀ���ý���
							plszDis(['pjpy_xmwh'],true);
							plszDis(xmsz,true);
						}
		    		});
      			}
      		})
      		jQuery.ajaxSetup({async:true});
      	}
      	
        //��ʼ������
      	function startPjpy(obj){
      		var flg = jQuery('input',jQuery(obj)).val();
      	
      		if (flg == 'true') {
      			confirmInfo('��ȷ��Ҫ�����µ�����������?',function(t){
      				if (t=='ok'){
      					jQuery.post('pjpyJbsz.do?method=startPjpy',{},function(data){
			      			if ('true' == data){
			      				plszDis(['pjpy_zqsz'],false);
			      				setNotZqszDisabled();
			      			}
			      		})
      				}
      			})
      		}
      	}
      
      	//�������������������
      	function setNotZqszDisabled(){
      		var pjszx = jQuery('a[id!=pjpy_zqsz][id!=pjpy_kspj]',jQuery('#pjsz'));
      				
			for (var i = 0 ; i < pjszx.length ; i++){
				var a = jQuery(pjszx[i]);
				var img = jQuery('img',a);
				var src = img.attr('src').replace('48-1','48-2');
				
				//��ԭ����ͼƬ�ڵ�ɾ��������div��ǩ�ǽ��IE6��ͼƬɾ������
				jQuery('div',a).remove();
				//�����µ�ͼƬ�ڵ�
				var html = '<div><img src="'+src+'"></div>';
				//��ͼƬ�ڵ���뵽a��ǩ�ڵ���ǰ��
				jQuery(html).prependTo(a);
				jQuery('input',a).val('false');
			}
      	}
      	
      	function plszDis(arr,dis){
      		for (var i = 0 ; i < arr.length ; i++){
      			var a = jQuery('#'+arr[i]);
				var img = jQuery('img',a);
				var src = img.attr('src');
				//��ԭ����ͼƬ�ڵ�ɾ��������div��ǩ�ǽ��IE6��ͼƬɾ������
				jQuery('div',a).remove();
				
				if(dis){
					src = src.replace('48-1','48-2');
					//���̻�����Ϊ������
					jQuery('input',a).val('false');
				} else {
					src = src.replace('48-2','48-1');
					//���̻�����Ϊ����
					jQuery('input',a).val('true');
				}
				
				//�����µ�ͼƬ�ڵ�
				var html = '<div><img src="'+src+'"></div>';
				//��ͼƬ�ڵ���뵽a��ǩ�ڵ���ǰ��
				jQuery(html).prependTo(a);
				
			}
      	}
      	
      	//����������Ա��ʼ��
      	function pjpyRycsh(obj){
      		var flg = jQuery('input',jQuery(obj)).val();
      		
      		if (flg == 'true'){
	      		jQuery.post('pjpyRyqd.do?method=pjpyRycsh',{},function(data){
	      			if ("true" == data){
	      				alertInfo('��Ա��ʼ���ɹ�!');
	      				plszDis(['pjpy_zcsz','pjpy_ryqd'],false);
	      			} else {
	      				alertInfo('��Ա��ʼ��ʧ��!');
	      			}
	      		});
      		} else {
      			alertInfo('����������������!');
      		}
      	}
      	
      	function pjlcLink(obj,src){
      		var flg = jQuery('input[type=hidden]',jQuery(obj)).val();
      		if (flg == 'true'){
      			document.location.href = src;
      		}
      	}
      	
      	
      	function displayDiv(obj,divId){
      		jQuery(obj).attr('style','z-index:999;position:relative');
      		//jQuery('#'+divId).attr('style','display:block');
      		$(divId).style.display='block';
      	}
      	
      	function hiddenDiv(obj,divId){
      		jQuery(obj).attr('style','z-index:998;position:');
      		//jQuery('#'+divId).attr('style','display:none');
      		$(divId).style.display='none';
      	}
      	
      </script>
	</head>

	<body>
		
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
				1.�¿�ʼ����������£����ȵ��<font color="blue">��ʼ������</font>��</br>
				2.ֻ�������<font color="blue">ǰһ��</font>�������ſ���ִ��<font color="blue">��һ��</font>������</br>
				3.������Ѿ�ִ�е�ĳһ��������֮ǰ����������<font color="blue">�޸�</font>�Ļ�������<font color="blue">���</font>������������������ݣ�<font color="blue">���˻�</font>��</br>
				4.��������޸�<font color="blue">��������</font>�Ļ�����ֱ�ӵ��<font color="blue">��ʼ������</font>��
				</span>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->		

		<div class="liucheng_xg_pj" id="pjsz">
			<div onmouseover="displayDiv(this,'helpcon1')"
				 onmouseout="hiddenDiv(this,'helpcon1')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:12px; top:60px;">
					<a href="#" onclick="startPjpy(this);" id="pjpy_kspj"> 
						<div><img src="<%=stylePath%>images/blue/48-1/Function85.png" /></div>
						<p>
							��ʼ������
							<input type="hidden" value="true" />
						</p> 
					</a>
					<div class="explain_left" id="helpcon1" style="display:none;">
						<div class="explain_con">
							<h3>
								��ʼ������
							</h3>
							<ul>
								<li>
									��ѧ����������ɣ������µ���������
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div onmouseover="displayDiv(this,'helpcon2')"
				onmouseout="hiddenDiv(this,'helpcon2')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:182px; top:60px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_jbsz_jbsz.do');return false;"
						id="pjpy_zqsz"> <div><img
							src="<%=stylePath%>images/blue/48-1/Function37.png" /></div>
						<p>
							������������
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_left" id="helpcon2" style="display:none;left:83px">
						<div class="explain_con">
							<h3>
								������������
							</h3>
							<ul>
								<li>
									����ѧ��:${pjxtszModel.pjxn }
								</li>
								<li>
									����ѧ��:${pjxtszModel.pjxqmc }
								</li>
								<li>
									�������:${pjxtszModel.pjnd }
								</li>
								<li>
									��ʾ�����Ʊ����������ڵ�ʱ�䣬��������
								</li>
								<li>
									���δ���õĻ�����ȡϵͳ���õĵ�ǰʱ��Ϊ׼
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>


			<div onmouseover="displayDiv(this,'helpcon3')"
				onmouseout="hiddenDiv(this,'helpcon3')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute; left:606px; top:60px;">
					<a href="#" onclick="pjlcLink(this,'zhcp_jbsz_jbsz.do');return false;"
						id="pjpy_zcsz"><div> <img
							src="<%=stylePath%>images/blue/48-1/Function19.png" /></div>
						<p>
							�۲��������
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon3" style="display:none;right:84px">
						<div class="explain_con">
							<h3>
								�۲��������
							</h3>
							<ul>
								<li>
									���ñ��������ڵ��ۺ����ʲ����ֵ���ɽṹ��<br />
									�����ø�������ı�������ʼ��ѧ���۲���Ϣ��<br />
									ע�����۲��������һ����δ�����仯�Ļ���������ģ��󣬲���������Ŀ���ɡ�
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div onmouseover="displayDiv(this,'helpcon4')"
				onmouseout="hiddenDiv(this,'helpcon4')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute; left:385px; top:8px;">
					<a href="#"  id="pjpy_rycsh" onclick="return false;"> 
						<div>
						<img
							src="<%=stylePath%>images/blue/48-1/Function39.png" />
						</div>
						<p>
							������Ա���ʼ��
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon4" style="display:none;right:97px;">
						<div class="explain_con">
							<h3>
								������Ա���ʼ��
							</h3>
							<ul>
								<li>
									��ʼ������ִ�к󣬽���ͬ��ʵʱ�Ĳ�����Ϣ��ѧ����Ϣ������
									<br />
									����Ա�⡣Ȼ����Զ�����е�������������ڲ���Ҫ���е���
									<br />
									��ִ�г�ʼ����������ô������Ŀ���ü��ɡ�
								</li>
								<p class="btn" onclick="pjpyRycsh(jQuery('#pjpy_rycsh'));return false;">
					              <button type="button">��ʼ��</button>
					            </p>	
							</ul>
						</div>
					</div>
				</div>
			</div>




			<div onmouseover="displayDiv(this,'helpcon5')"
				onmouseout="hiddenDiv(this,'helpcon5')" style="z-index:998;">

				<div class="liucheng_font"
					style="position:absolute; left:395px; top:95px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_jbsz_ryqd.do');return false;"
						id="pjpy_ryqd"> 
						<div><img src="<%=stylePath%>images/blue/48-1/Function55.png" /></div>
						<p>
							������Ա��ȷ��
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon5" style="display:none;right:90px;">
						<div class="explain_con">
							<h3>
								������Ա��ȷ��
							</h3>
							<ul>
								<li>
									����ѧ����ǰ���ڰ༶����������༶��һ��ʱ���е�����
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>




			<div onmouseover="displayDiv(this,'helpcon6')"
				onmouseout="hiddenDiv(this,'helpcon6')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:604px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_jbsz_xmsz.do');return false;"
						id="pjpy_xmwh"> 
						<div>
							<img src="<%=stylePath%>images/blue/48-1/Function47.png" />
						</div>
						<p>
							��Ŀά��
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon6" style="display:none;right:68px">
						<div class="explain_con">
							<h3>
								��Ŀά��
							</h3>
							<ul>
								<li>
									ά����ǰ�������ڵ�������Ŀ��
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>



			<div onmouseover="displayDiv(this,'helpcon7')"
				onmouseout="hiddenDiv(this,'helpcon7')" style="z-index:998;">

				<div class="liucheng_font"
					style="position:absolute;left:16px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_sjsz.do');return false;" id="pjpy_sjsz">
						<div><img src="<%=stylePath%>images/blue/48-1/Function60.png" /></div>
						<p>
							ʱ������
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_left" id="helpcon7" style="display:none;left:68px">
						<div class="explain_con">
							<h3>
								ʱ������
							</h3>
							<ul>
								<li>
									��������������Ŀ���������˿��ء�
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>


			<div onmouseover="displayDiv(this,'helpcon8')"
				onmouseout="hiddenDiv(this,'helpcon8')" style="z-index:998;">

				<div class="liucheng_font"
					style="position:absolute;left:121px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_xmsz_tzfwsz.do');return false;"
						id="pjpy_tzsz"> <div><img
							src="<%=stylePath%>images/blue/48-1/Function24.png" /></div>
						<p>
							��������
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_left" id="helpcon8" style="display:none;left:68px">
						<div class="explain_con">
							<h3>
								��������
							</h3>
							<ul>
								<li>
									�������ô����������Ŀ�����磺ĳѧ�����ϻ������"��ĿA",
									<br />
									���Ǹ���Ŀ�������ͨ�������ﵽ����������,��ʱ��Ϳ��԰�
									<br/>
									��ѧ�����������������Ŀ��
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>



			<div onmouseover="displayDiv(this,'helpcon9')"
				onmouseout="hiddenDiv(this,'helpcon9')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:224px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_xmsz_jdsz.do');return false;"
						id="pjpy_jdsz"> <div><img
							src="<%=stylePath%>images/blue/48-1/Function71.png" /></div>
						<p>
							�������
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_left" id="helpcon9" style="display:none;left:68px">
						<div class="explain_con">
							<h3>
								�������
							</h3>
							<ul>
								<li>
									��������������Ŀ֮��ļ����������磺����"��ĿA"��"��ĿB"���ɼ�ã�
									<br />
									�����ǰ��������ѧ�����������"��ĿA"�Ͳ����ٻ��"��ĿB"��
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div onmouseover="displayDiv(this,'helpcon10')"
				onmouseout="hiddenDiv(this,'helpcon10')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:327px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_xmsz_tjsz.do');return false;"
						id="pjpy_tjsz"><div><img
							src="<%=stylePath%>images/blue/48-1/Function56.png" /></div>
						<p>
							��������
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon10" style="display:none;right:68px">
						<div class="explain_con">
							<h3>
								��������
							</h3>
							<ul>
								<li>
									��������ѧ������������Ŀ��Ҫ�����������
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div onmouseover="displayDiv(this,'helpcon11')"
				onmouseout="hiddenDiv(this,'helpcon11')" style="z-index:998;">
				<div class="liucheng_font"
					style="position:absolute;left:420px; top:293px;">
					<a href="#" onclick="pjlcLink(this,'pjpy_xmsz_rssz.do');return false;"
						id="pjpy_rssz"> <div><img
							src="<%=stylePath%>images/blue/48-1/Function25.png" /></div>
						<p>
							��������
							<input type="hidden" value="true" />
						</p> </a>
					<div class="explain_right" id="helpcon11" style="display:none;right:68px">
						<div class="explain_con">
							<h3>
								��������
							</h3>
							<ul>
								<li>
									�������ø�<bean:message key="lable.xb" />���Ի��ĳ������Ŀ��������
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
