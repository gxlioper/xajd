		//��ʼ��
		function onShow_cssz(){
			showZcTr();
			showGlTr();
			showGlTb();
			showWsdjList();
		}
		
		//��ʾ���ص��ܴ�tr
		function showZcTr(){
			var zq = $("zq").value;
			if(zq == "��"){
				$("zgzcTr").style.display = "";
				$("qsrqTr").style.display = "";
			}else{
				$("zgzcTr").style.display = "none";
				$("qsrqTr").style.display = "none";
			}
		}
		
		//��ʾ���صĹ���tr
		function showGlTr(){
			var xs = $("xs").value;
			if(xs == "����"){
				$("gldjTr").style.display = "";
				$("glfsTr").style.display = "none";
			}else{
				$("gldjTr").style.display = "none";
				$("glfsTr").style.display = "";
			}
			showGlTb();
		}
		
		//��ʾ���صķ��������ȼ�tbody
		function showGlTb(){
			var dj = $("dj").value;
			var fs = $("fs").value;
			var xs = $("xs").value;
			
			$("nrDel").value=max;
			trDelAllTr("nr","nrDel");

			if(xs == "����" && dj == "��"){
				$("nrTb").style.display = "";
				
				$("gldjTh").style.display = "";
				$("glfsTh").style.display = "none";
				$("bglfsTh").style.display = "none";
				
				$("gldjTopTr").style.display = "";
				$("glfsTopTr").style.display = "none";
				$("bglfsTopTr").style.display = "none";
				
			}else if(xs == "�ȼ�" && fs == "��"){
				$("nrTb").style.display = "";
				
				$("glfsTh").style.display = "";
				$("gldjTh").style.display = "none";
				$("bglfsTh").style.display = "none";
				
				$("glfsTopTr").style.display = "";
				$("gldjTopTr").style.display = "none";
				$("bglfsTopTr").style.display = "none";
				
			}else if(xs == "�ȼ�" && fs == "��"){
				$("nrTb").style.display = "";
				
				$("bglfsTh").style.display = "";
				$("gldjTh").style.display = "none";
				$("glfsTh").style.display = "none";
				
				$("bglfsTopTr").style.display = "";
				$("gldjTopTr").style.display = "none";
				$("glfsTopTr").style.display = "none";
				
			}else{
				$("nrTb").style.display = "none";
				
				$("gldjTh").style.display = "none";
				$("glfsTh").style.display = "none";
				$("bglfsTh").style.display = "none";
				
				$("gldjTopTr").style.display = "none";
				$("glfsTopTr").style.display = "none";
				$("bglfsTopTr").style.display = "none";
			}
		}
		
		//���ӵȼ�
		function addDj(type){
			
			var dj = $("dj").value;
			var fs = $("fs").value;
			var xs = $("xs").value;
			
			if(xs == "����" && dj == "��"){
				trAdd('nr',type,'nrAdd','gygl_wsjc_fsgldj');
			}
			
			if(xs == "�ȼ�" && fs == "��"){
				trAdd('nr',type,'nrAdd','gygl_wsjc_djglfs');
			}
			
			if(xs == "�ȼ�" && fs == "��"){
				trAdd('nr',type,'nrAdd','gygl_wsjc_djbglfs');
			}
		}
		
		//��ʾ�����ȼ��б�
		function showWsdjList(){
			
			var tableName = "gygl_wsjc_wsfdjb";
			var pk = "1";
			var pkValue = "1";
			var query = " order by to_number(djpx) ";
			var tbId = "nr";
			var btnId = "nrAdd";
			var lx = "";
			
						var dj = $("dj").value;
			var fs = $("fs").value;
			var xs = $("xs").value;
			
			if(xs == "����" && dj == "��"){
				lx='gygl_wsjc_fsgldj';
			}
			
			if(xs == "�ȼ�" && fs == "��"){
				lx='gygl_wsjc_djglfs';
			}
			
			if(xs == "�ȼ�" && fs == "��"){
				lx='gygl_wsjc_djbglfs';
			}
			
			if(lx != ""){
				setNr(tableName,pk,pkValue,query,tbId,btnId,lx);
			}
			
				
		}
		
		//�����������Ƿ����Ҫ��
		function checkWsf(num){
			var xxId = "wsfxx"+num;
			var sxId = "wsfsx"+num;
			if($(xxId) && $(sxId) && $(xxId).value !="" && $(sxId).value!=""){
				if(parseInt($(xxId).value) > parseInt($(sxId).value)){
					alert("�������޷ֲ��ܴ������޷�!");
					$(xxId).value = "";
					$(sxId).value = "";
					$(xxId).focus();
					return false;
				}
			}
		}
		
		//�����������
		function saveCssz(){
			var zq = $("zq").value;
			var url = "/xgxt/commWsjc.do?method=csszManage&doType=save";
			var pk = "";
			if(zq == "��"){
				pk = "zgzc-qsrq";
			}
			
			var lrxs = $("xs").value;
			var gldj = $("dj").value;
			
			var num = 0;
			
			for(var i=0;i<=max;i++){
			
				var xxId = "wsfxx"+i;
				var sxId = "wsfsx"+i;
				var djId = "wsfdj"+i;
				
				if($(xxId)){
					if($(xxId).value ==""){
						alert("��ȷ�Ϸ�������Ϊ�գ�");
						$(xxId).focus();
						return false;
					}else{
						num++;
					}
				}
				
				if($(sxId)){
					if($(sxId).value ==""){
						alert("��ȷ�Ϸ�������Ϊ�գ�");
						$(sxId).focus();
						return false;
					}else{
						num++;
					}
				}
				
				if($(djId)){
					if($(djId).value ==""){
						alert("��ȷ�ϵȼ�����Ϊ�գ�");
						$(djId).focus();
						return false;
					}else{
						num++;
					}
				}
			}
			
			if(lrxs == "����" && gldj == "��" ){
			
			}else if(num == 0){
				alert("������ά��һ�������ֵȼ���");
				return false;
			}
			
			saveUpdate(url,pk);
		}