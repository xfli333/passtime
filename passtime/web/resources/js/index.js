function initChromeFont(){
    if (!$.browser.msie) {
        $('#site_title>h1').css("font-family", "xs");
        $('.title_a').css("font-size", "48px");
        
        $('.stripNav>ul>li').css("font-family", "xs");
        $('.stripNav>ul>li>a').css("font-size", "22px");

        $('.wrapper>h2').css("font-family", "xs");
        $('.wrapper>h2').css("font-size", "33px");
        
//        $('.service_list>li').css("font-family", "xs");
//        $('.service_list>li').css("font-size", "20px");
    }
}
