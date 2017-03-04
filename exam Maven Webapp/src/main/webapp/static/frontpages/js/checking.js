$(function(){
	/*解析下拉点击出现隐藏*/
	var $xiala = $(".xiala");
    $xiala.bind("click",function () {
    	$(this).hide();
    	var index = $xiala.index(this);
        $(".jiexi").eq(index).hide();
    	$(".analysis").eq(index).show(500);
    });
    var $jiexi = $(".jiexi");
    $jiexi.bind("click",function () {
        $(this).hide();
        var index = $jiexi.index(this);
        $(".xiala").eq(index).hide();
        $(".analysis").eq(index).show(500);
    });
    /*解析上拉点击出现隐藏*/
	var $upla = $(".upla");
    $upla.bind("click",function () {
    	var index = $upla.index(this);
        $(".analysis").eq(index).hide(500);
        $(".xiala").eq(index).show();
        $(".jiexi").eq(index).show();
    });
    /*为每个按钮增加导航*/
    function addHref(){
        var buttons = document.getElementsByClassName("buttons");
        for(var i=0;i < buttons.length;i++){
            var index = buttons[i].getAttribute("index");
            var href = "#que" + index;
            buttons[i].setAttribute("href",href);
        }
    }
    addHref();
    /*为每道题div增加id属性*/
    function addId(){
        var ques = document.getElementsByClassName("ques");
        for(var i=0;i < ques.length;i++){
            var index = ques[i].getAttribute("index");
            var id = "que" + index;
            ques[i].setAttribute("id",id);
        }
    }
    addId();

    /*调整高度*/
    var winHeight = $(document).height();
    var leftHeight = winHeight - 226;
    $("#left-maincontent").height(leftHeight);
    $("#right-bottom").height(leftHeight + 90);
    $("#button").css("top",winHeight-60);
    /*调整宽度*/
    var winWidth = $(document).width();
    var leftWidth = winWidth * 0.6;
    $("#sign").css("margin-left",leftWidth);

    /*为选中的添加样式*/
    $(":radio:checked").parent("label").addClass("selected");
    $(":checkbox:checked").parent("label").addClass("selected");
    /*正确错误添加类 对应的按钮也作出相应的变化*/
    function isCorrect(){
        var ques = document.getElementsByClassName("ques");
        var buttons = document.getElementsByClassName("buttons");
        for(var i=0;i < ques.length;i++){
            var isCorrect = ques[i].getAttribute("isCorrect");
            if(isCorrect == 1){
                ques[i].setAttribute("class","ques choice correct");
                buttons[i].setAttribute("class","buttons correctbtn")
            }else if(isCorrect == 0){
                ques[i].setAttribute("class","ques choice wrong");
                buttons[i].setAttribute("class","buttons wrongbtn");
            }
        }
    }
    isCorrect();
});