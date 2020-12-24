var data = [{pname: '联想',price: 5299}, {pname: '华为',price: 8299},{pname: '联想',price: 4499}, {pname: '惠普',price: 3999}, {pname: '华为',price: 5398}, {pname: '苹果',price: 6299}, {pname: '苹果',price: 4299}, {pname: '惠普',price: 7299},
{pname: '联想',price: 5299}, {pname: '华为',price: 8299},{pname: '联想',price: 4499}, {pname: '惠普',price: 3999}, {pname: '华为',price: 5398}, {pname: '苹果',price: 6299}, {pname: '苹果',price: 4299}, {pname: '惠普',price: 7299}, ];
        // 1. 获取相应的元素
        var tbody = document.querySelector('tbody');
        var start = document.querySelector('.start');
        var end = document.querySelector('.end');
        var product = document.querySelector('.product');
        var search_pro = document.querySelector('.search-pro');
        var asc = document.querySelector('.asc');
        var desc = document.querySelector('.desc');
        var oPage = document.getElementById("page");
        var oPageCount = oPage.children;
        var oPagenum = document.getElementById("pagenum");
        var newData=[];
        var countNum = 5;
        var pages;
        var p;
        var pageIndex = 0;
        function updata(up){
            oPagenum.innerHTML="";
            pages = Math.ceil(up.length/countNum);
            for (var i =0;i<pages;i++){
                var op = document.createElement("p");
                op.innerHTML=i+1;
                oPagenum.appendChild(op);
            }
            p=oPagenum.children;
            for(var j=0;j<p.length;j++){
                p[j].index = j;
                //点击页码显示不同页码的内容
                //p[0].className="countIndex";
                p[j].addEventListener('click',function(){
                pageIndex = this.index;
                if(newData!=''){
                sDate(pageIndex,newData);
                }
                else{
                sDate(pageIndex,data);
                }
                p[pageIndex].className="countIndex";
                })
            }
        }
        updata(data);
        // 2. 把数据渲染到页面中
        sDate(pageIndex,data);
        p[0].className="countIndex";
        //首页
        oPageCount[0].onclick=function(){
            pageIndex=0;
            if(newData!=''){
                sDate(pageIndex,newData);
                p[0].className="countIndex";
            }
            else{
                sDate(pageIndex,data);
                p[0].className="countIndex";
            }
        }
        //上一页
        oPageCount[1].addEventListener('click',function(){
            pageIndex--;
            if(pageIndex<0){
                pageIndex=p.length-1;
            }
            if(newData!=''){
                sDate(pageIndex,newData);
                p[pageIndex].className="countIndex";
            }
            else{
                sDate(pageIndex,data);
                p[pageIndex].className="countIndex";
            }
         });
        //下一页
        oPageCount[oPageCount.length-2].addEventListener('click',function(){
            pageIndex++;
            if(pageIndex>pages-1){
                pageIndex=0;
            }
            if(newData!=''){
                sDate(pageIndex,newData);
                p[pageIndex].className="countIndex";
            }
            else{
                sDate(pageIndex,data);
                p[pageIndex].className="countIndex";
            }
        });
        //尾页
        oPageCount[oPageCount.length-1].addEventListener('click',function(){
            pageIndex=p.length-1;
            if(newData!=''){
                sDate(pageIndex,newData);
                p[p.length-1].className="countIndex";
            }
            else{
                sDate(pageIndex,data);
                p[p.length-1].className="countIndex";
            }
            
        });
        //分页操作
        function sDate(mydata,ndata) {
            // 先清空原来tbody 里面的数据
            tbody.innerHTML = '';
            updata(ndata);//更新页数
            for (var n=0;n<p.length;n++){
                p[n].className="";
            }
            for(var i= mydata*countNum;i<Math.min((mydata+1)*countNum,ndata.length);i++){
                var tr = document.createElement('tr');
                tr.innerHTML = '<td>' + ndata[i].pname + '</td><td>' + ndata[i].price + '</td>';
                tbody.appendChild(tr);
            }
        }
        //升序排列
        asc.addEventListener('click',function(){
            if(newData!=''){
                newData=newData.sort(complete);
                sDate(0,newData);
                p[0].className="countIndex";
            }
            else{
                data=data.sort(complete);
                sDate(pageIndex,data);
                p[pageIndex].className="countIndex";
            }
		    function complete(x,y){
			    if(x.price>y.price){
				    return 1;
			    }else if(x.price<y.price){
				    return -1;
			    }else{
				    return 0;
			    }
		    }
        });
        //降序排列
        desc.addEventListener('click',function(){
            if(newData!=''){
                newData=newData.sort(completes);
                sDate(0,newData);
                p[0].className="countIndex";
            }
            else{
                data=data.sort(completes);
                sDate(pageIndex,data);
                p[pageIndex].className="countIndex";
            }
		    function completes(x,y){
			    if(x.price>y.price){
				    return -1;
			    }else if(x.price<y.price){
				    return 1;
			    }else{
				    return 0;
			    }
		    }
        });
        
        // 当我们点击了按钮,就可以根据我们的商品价格或名称去筛选数组里面的对象
        search_pro.addEventListener('click', function() {
            if((start.value==''||end.value=='')&&product.value==''){
                    sDate(0,data);
                    p[0].className="countIndex";
                    newData="";
                    return;
            }//  根据价格查询商品
            if(start.value!='' && end.value!=''){
                newData = data.filter(function(value) {
                    return value.price >= start.value && value.price <= end.value;
                });
                if(product.value!=''){
                    newData=newData.filter(function(value) {
                        return value.pname === product.value; 
                    });
                }
            }// 根据商品名称查找商品
            else{
                newData=data.filter(function(value) {
                    return value.pname === product.value; 
                });
            }
            // 把筛选完之后的对象渲染到页面中
            sDate(0,newData);
            p[0].className="countIndex";
        })