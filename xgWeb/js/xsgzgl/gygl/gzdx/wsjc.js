		//初始化
		function onShow_cssz(){
			showZcTr();
			showGlTr();
			showGlTb();
			showWsdjList();
		}
		
		//显示隐藏的周次tr
		function showZcTr(){
			var zq = $("zq").value;
			if(zq == "周"){
				$("zgzcTr").style.display = "";
				$("qsrqTr").style.display = "";
			}else{
				$("zgzcTr").style.display = "none";
				$("qsrqTr").style.display = "none";
			}
		}
		
		//显示隐藏的关联tr
		function showGlTr(){
			var xs = $("xs").value;
			if(xs == "分数"){
				$("gldjTr").style.display = "";
				$("glfsTr").style.display = "none";
			}else{
				$("gldjTr").style.display = "none";
				$("glfsTr").style.display = "";
			}
			showGlTb();
		}
		
		//显示隐藏的分数关联等级tbody
		function showGlTb(){
			var dj = $("dj").value;
			var fs = $("fs").value;
			var xs = $("xs").value;
			
			$("nrDel").value=max;
			trDelAllTr("nr","nrDel");

			if(xs == "分数" && dj == "是"){
				$("nrTb").style.display = "";
				
				$("gldjTh").style.display = "";
				$("glfsTh").style.display = "none";
				$("bglfsTh").style.display = "none";
				
				$("gldjTopTr").style.display = "";
				$("glfsTopTr").style.display = "none";
				$("bglfsTopTr").style.display = "none";
				
			}else if(xs == "等级" && fs == "是"){
				$("nrTb").style.display = "";
				
				$("glfsTh").style.display = "";
				$("gldjTh").style.display = "none";
				$("bglfsTh").style.display = "none";
				
				$("glfsTopTr").style.display = "";
				$("gldjTopTr").style.display = "none";
				$("bglfsTopTr").style.display = "none";
				
			}else if(xs == "等级" && fs == "否"){
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
		
		//增加等级
		function addDj(type){
			
			var dj = $("dj").value;
			var fs = $("fs").value;
			var xs = $("xs").value;
			
			if(xs == "分数" && dj == "是"){
				trAdd('nr',type,'nrAdd','gygl_wsjc_fsgldj');
			}
			
			if(xs == "等级" && fs == "是"){
				trAdd('nr',type,'nrAdd','gygl_wsjc_djglfs');
			}
			
			if(xs == "等级" && fs == "否"){
				trAdd('nr',type,'nrAdd','gygl_wsjc_djbglfs');
			}
		}
		
		//显示卫生等级列表
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
			
			if(xs == "分数" && dj == "是"){
				lx='gygl_wsjc_fsgldj';
			}
			
			if(xs == "等级" && fs == "是"){
				lx='gygl_wsjc_djglfs';
			}
			
			if(xs == "等级" && fs == "否"){
				lx='gygl_wsjc_djbglfs';
			}
			
			if(lx != ""){
				setNr(tableName,pk,pkValue,query,tbId,btnId,lx);
			}
			
				
		}
		
		//检验卫生分是否符合要求
		function checkWsf(num){
			var xxId = "wsfxx"+num;
			var sxId = "wsfsx"+num;
			if($(xxId) && $(sxId) && $(xxId).value !="" && $(sxId).value!=""){
				if(parseInt($(xxId).value) > parseInt($(sxId).value)){
					alert("卫生下限分不能大于上限分!");
					$(xxId).value = "";
					$(sxId).value = "";
					$(xxId).focus();
					return false;
				}
			}
		}
		
		//保存参数设置
		function saveCssz(){
			var zq = $("zq").value;
			var url = "/xgxt/gzdxWsjc.do?method=csszManage&doType=save";
			var pk = "";
			if(zq == "周"){
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
						alert("请确认分数不能为空！");
						$(xxId).focus();
						return false;
					}else{
						num++;
					}
				}
				
				if($(sxId)){
					if($(sxId).value ==""){
						alert("请确认分数不能为空！");
						$(sxId).focus();
						return false;
					}else{
						num++;
					}
				}
				
				if($(djId)){
					if($(djId).value ==""){
						alert("请确认等级不能为空！");
						$(djId).focus();
						return false;
					}else{
						num++;
					}
				}
			}
			
			if(lrxs == "分数" && gldj == "否" ){
			
			}else if(num == 0){
				alert("请至少维护一项卫生分等级！");
				return false;
			}
			
			saveUpdate(url,pk);
		}