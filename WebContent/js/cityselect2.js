/* *
 * 全局空间 Vcity
 * */
var Vcity = {};

/* *
 * 静态方法集
 * @name _m
 * */
Vcity._m = {
    /* 选择元素 */
    $:function (arg, context) {
        var tagAll, n, eles = [], i, sub = arg.substring(1);
        context = context || document;
        if (typeof arg == 'string') {
            switch (arg.charAt(0)) {
                case '#':
                    return document.getElementById(sub);
                    break;
                case '.':
                    if (context.getElementsByClassName) return context.getElementsByClassName(sub);
                    tagAll = Vcity._m.$('*', context);
                    n = tagAll.length;
                    for (i = 0; i < n; i++) {
                        if (tagAll[i].className.indexOf(sub) > -1) eles.push(tagAll[i]);
                    }
                    return eles;
                    break;
                default:
                    return context.getElementsByTagName(arg);
                    break;
            }
        }
    },

    /* 绑定事件 */
    on:function (node, type, handler) {
        node.addEventListener ? node.addEventListener(type, handler, false) : node.attachEvent('on' + type, handler);
    },

    /* 获取事件 */
    getEvent:function(event){
        return event || window.event;
    },

    /* 获取事件目标 */
    getTarget:function(event){
        return event.target || event.srcElement;
    },

    /* 获取元素位置 */
    getPos:function (node) {
        var scrollx = document.documentElement.scrollLeft || document.body.scrollLeft,
                scrollt = document.documentElement.scrollTop || document.body.scrollTop;
        var pos = node.getBoundingClientRect();
        return {top:pos.top + scrollt, right:pos.right + scrollx, bottom:pos.bottom + scrollt, left:pos.left + scrollx }
    },

    /* 添加样式名 */
    addClass:function (c, node) {
        if(!node)return;
        node.className = Vcity._m.hasClass(c,node) ? node.className : node.className + ' ' + c ;
    },

    /* 移除样式名 */
    removeClass:function (c, node) {
        var reg = new RegExp("(^|\\s+)" + c + "(\\s+|$)", "g");
        if(!Vcity._m.hasClass(c,node))return;
        node.className = reg.test(node.className) ? node.className.replace(reg, '') : node.className;
    },

    /* 是否含有CLASS */
    hasClass:function (c, node) {
        if(!node || !node.className)return false;
        return node.className.indexOf(c)>-1;
    },

    /* 阻止冒泡 */
    stopPropagation:function (event) {
        event = event || window.event;
        event.stopPropagation ? event.stopPropagation() : event.cancelBubble = true;
    },
    /* 去除两端空格 */
    trim:function (str) {
        return str.replace(/^\s+|\s+$/g,'');
    }
};

/* 所有城市数据,可以按照格式自行添加（北京|beijing|bj），前16条为热门城市 */

Vcity.allCity = ['北京市|beijing|bj','上海市|shanghai|sh','广州市|guangzhou|gz','深圳市|shenzhen|sz','南京市|nanjing|nj','杭州市|hangzhou|hz','天津市|tianjin|tj','重庆市|chongqing|cq','成都市|chengdu|cd','青岛市|qingdao|qd','苏州市|shuzhou|sz','无锡市|wuxi|wx','常州市|changzhou|cz','温州市|wenzhou|wz','武汉市|wuhan|wh','长沙市|changsha|cs','石家庄市|shijiazhuang|sjz','南昌市|nanchang|nc','三亚市|sanya|sy','合肥市|hefei|hf','郑州市|zhengzhou|zz','保定市|baoding|bd','唐山市|tangshan|ts','秦皇岛市|qinhuangdao|qhd','邯郸市|handan|hd','邢台市|xingtai|xt','张家口市|zhangjiakou|zjk','承德市|chengde|cd','衡水市|hengshui|hs','廊坊市|langfang|lf','沧州市|cangzhou|cz','太原市|taiyuan|ty','大同市|datong|dt','阳泉市|yangquan|yq','长治市|changzhi|cz','晋城市|jincheng|jc','朔州市|shuozhou|sz','晋中市|jinzhong|jz','运城市|yuncheng|yc','忻州市|xinzhou|xz','临汾市|linfen|lf','吕梁市|lvliang|ll','呼和浩特市|huhehaote|hhht','包头市|baotou|bt','乌海市|wuhai|wh','赤峰市|chifeng|cf','通辽市|tongliao|tl','鄂尔多斯市|eerduosi|eeds','呼伦贝尔市|hulunbeier|hlbe','巴彦淖尔市|bayannaoer|byne','乌兰察布市|wulanchabu|wlcb','兴安盟|xinganmeng|xam','锡林郭勒盟|xilinguolemeng|xlglm','阿拉善盟|alashanmeng|alsm','沈阳市|shenyang|sy','大连市|dalian|dl','鞍山市|anshan|as','抚顺市|fushun|fs','本溪市|benxi|bx','丹东市|dandong|dd','锦州市|jinzhou|jz','营口市|yingkou|yk','阜新市|fuxin|fx','辽阳市|liaoyang|ly','盘锦市|panjin|pj','铁岭市|tieling|tl','朝阳市|chaoyang|cy','葫芦岛市|huludao|hld','长春市|changchun|cc','吉林市|jilin|jl','四平市|siping|sp','辽源市|liaoyuan|ly','通化市|tonghua|th','白山市|baishan|bs','松原市|songyuan|sy','白城市|baicheng|bc','延边朝鲜族自治州|ybcxzzzz|ybcxzzzz','哈尔滨市|haerbin|heb','齐齐哈尔市|qiqihaer|qqhe','鸡西市|jixi|jx','鹤岗市|hegang|hg','双鸭山市|shuangyashan|sys','大庆市|daqing|dq','伊春市|yichun|yc','佳木斯市|jiamusi|jms','七台河市|qitaihe|qth','牡丹江市|mudanjiang|mdj','黑河市|heihe|hh','绥化市|suihua|sh','大兴安岭地区|daxinganling|dxaldq','徐州市|xuzhou|xz','南通市|nantong|nt','连云港市|lianyungang|lyg','淮安市|huaian|ha','盐城市|yancheng|yc','扬州市|yangzhou|yz','镇江市|zhenjiang|zj','泰州市|taizhou|tz','宿迁市|suqian|sq','宁波市|ningbo|nb','嘉兴市|jiaxing|jx','湖州市|huzhou|hz','绍兴市|shaoxing|sx','舟山市|zhoushan|zs','衢州市|quzhou|qz','金华市|jinhua|jh','台州市|taizhou|tz','丽水市|lishui|ls','芜湖市|wuhu|wh','蚌埠市|bengbu|bb','淮南市|huainan|hn','马鞍山市|maanshan|mas','淮北市|huaibei|hb','铜陵市|tongling|tl','安庆市|anqing|aq','黄山市|huangshan|hs','滁州市|chuzhou|cz','阜阳市|fuyang|fy','宿州市|suzhou|sz','巢湖市|chaohu|ch','六安市|luan|la','亳州市|bozhou|bz','池州市|chizhou|cz','宣城市|xuancheng|xc','福州市|fuzhou|fz','厦门市|xiamen|xm','莆田市|putian|pt','三明市|sanming|sm','泉州市|quanzhou|qz','漳州市|zhangzhou|zz','南平市|nanping|np','龙岩市|longyan|ly','宁德市|ningde|nd','景德镇市|jingdezhen|jdz','萍乡市|pingxiang|px','九江市|jiujiang|jj','新余市|xinyu|xy','鹰潭市|yingtan|yt','赣州市|ganzhou|gz','吉安市|jian|ja','宜春市|yichun|yc','抚州市|fuzhou|fz','上饶市|shangrao|sr','济南市|jinan|jn','淄博市|zibo|zb','枣庄市|zaozhuang|zz','东营市|dongying|dy','烟台市|yantai|yt','潍坊市|weifang|wf','济宁市|jining|jn','泰安市|taian|ta','威海市|weihai|wh','日照市|rizhao|rz','莱芜市|laiwu|lw','临沂市|linyi|ly','德州市|dezhou|dz','聊城市|liaocheng|lc','滨州市|binzhou|bz','菏泽市|heze|hz','开封市|kaifeng|kf','洛阳市|luoyang|ly','平顶山市|pingdingshan|pds','安阳市|anyang|ay','鹤壁市|hebi|hb','新乡市|xinxiang|xx','焦作市|jiaozuo|jz','濮阳市|puyang|py','许昌市|xuchang|xc','漯河市|luohe|lh','三门峡市|sanmenxia|smx','南阳市|nanyang|ny','商丘市|shangqiu|sq','信阳市|xinyang|xy','周口市|zhoukou|zk','驻马店市|zhumadian|zmd','济源市|jiyuan|jiyuan','黄石市|huangshi|hs','十堰市|shiyan|sy','宜昌市|yichang|yc','襄樊市|xiangfan|xf','鄂州市|ezhou|ez','荆门市|jingmen|jm','孝感市|xiaogan|xg','荆州市|jingzhou|jz','黄冈市|huanggang|hg','咸宁市|xianning|xn','随州市|suizhou|sz','恩施土家族苗族自治州|estjzmzzzz|estjzmzzzz','仙桃市|xiantao|xt','潜江市|qianjiang|qj','天门市|tianmen|tm','神农架林区|shennongjia|snjlq','株洲市|zhuzhou|zz','湘潭市|xiangtan|xt','衡阳市|hengyang|hy','邵阳市|shaoyang|sy','岳阳市|yueyang|yy','常德市|changde|cd','张家界市|zhangjiajie|zjj','益阳市|yiyang|yy','郴州市|chenzhou|cz','永州市|yongzhou|yz','怀化市|huaihua|hh','娄底市|loudi|ld','湘西土家族苗族自治州|xxtjzmzzzz|xxtjzmzzzz','韶关市|shaoguan|sg','珠海市|zhuhai|zh','汕头市|shantou|st','佛山市|foushan|fs','江门市|jiangmen|jm','湛江市|zhanjiang|jz','茂名市|maoming|mm','肇庆市|zhaoqing|zq','惠州市|huizhou|hz','梅州市|meizhou|mz','汕尾市|shanwei|sw','河源市|heyuan|hy','阳江市|yangjiang|yj','清远市|qingyuan|qy','东莞市|dongguan|dg','中山市|zhongshan|zs','潮州市|chaozhou|cz','揭阳市|jieyang|jy','云浮市|yunfu|yf','南宁市|nanning|nn','柳州市|liuzhou|lz','桂林市|guilin|gl','梧州市|wuzhou|wz','北海市|beihai|bh','防城港市|fangchenggang|fcg','钦州市|qinzhou|qz','贵港市|guigang|gg','玉林市|yulin|yl','百色市|baise|bs','贺州市|hezhou|hz','河池市|hechi|hc','来宾市|laibin|lb','崇左市|chongzuo|cz','海口市|haikou|hk','三亚市|sanya|sy','五指山市|wuzhishan|wzs','琼海市|qionghai|qh','儋州市|danzhou|dz','文昌市|wenchang|wc','万宁市|wanning|wn','东方市|dongfang|df','定安县|dingan|da','屯昌县|tunchang|tc','澄迈县|chengmai|cm','临高县|lingao|lg','白沙黎族自治县|bsnzzzx|bsnzzzx','昌江黎族自治县|cjlzzzx|cjlzzzx','乐东黎族自治县|ldlzzzx|ldlzzzx','陵水黎族自治县|lingshui|ls','保亭黎族苗族自治县|btlzmzzzx|btlzmzzzx','琼中黎族苗族自治县|qzlzmzzzx|qzlzmzzzx','西沙群岛|xishaqundao|xsqd','南沙群岛|nanshaqundao|nsqd','中沙群岛的岛礁及其海域|zhongshaqundao|zsqd','自贡市|zigong|zg','攀枝花市|panzhihua|pzh','泸州市|luzhou|lz','德阳市|deyang|dy','绵阳市|mianyang|my','广元市|guangyuan|gy','遂宁市|suining|sn','内江市|neijiang|nj','乐山市|leshan|ls','南充市|nanchong|nc','眉山市|meishan|ms','宜宾市|yibin|yb','广安市|guangan|ga','达州市|dazhou|dz','雅安市|yaan|ya','巴中市|bazhong|bz','资阳市|ziyang|zy','阿坝藏族羌族自治州|abzzqzzzz|abzzqzzzz','甘孜藏族自治州|gzzzzzz|gzzzzzz','凉山彝族自治州|lsyzzzz|lsyzzzz','贵阳市|guiyang|gy','六盘水市|liupanshui|lps','遵义市|zunyi|zy','安顺市|anshun|as','铜仁地区|tongren|tr','黔西南布依族苗族自治州|qxnbyzmzzzz|qxnbyzmzzzz','毕节地区|bijie|bj','黔东南苗族侗族自治州|qdnmzdzzzz|qdnmzdzzzz','黔南布依族苗族自治州|qnbyzmzzzz|qnbyzmzzzz','昆明市|kunming|km','曲靖市|qujing|qj','玉溪市|yuxi|yx','保山市|baoshan|bs','昭通市|zhaotong|zt','丽江市|lijiang|lj','思茅市|simao|sm','临沧市|lincang|lc','楚雄彝族自治州|cxyzzzz|cxyzzzz','红河哈尼族彝族自治州|hhhnzyzzzz|hhhnzyzzzz','文山壮族苗族自治州|wszzmzzzz|wszzmzzzz','西双版纳傣族自治州|xsbndzzzz|xsbndzzzz','大理白族自治州|dlbzzzz|dlbzzzz','德宏傣族景颇族自治州|dhdzjpzzzz|dhdzjpzzzz','怒江傈僳族自治州|njlszzzz|njlszzzz','迪庆藏族自治州|dqzzzzz|dqzzzzz','拉萨市|lasa|ls','昌都地区|changdudiqu|cd','山南地区|shannandiqu|sndq','日喀则地区|rikazediqu|rkzdq','那曲地区|naqudiqu|nqdq','阿里地区|alidiqu|aldq','林芝地区|linzhidiqu|lzdq','西安市|xian|xa','铜川市|tongchuan|tc','宝鸡市|baoji|bj','咸阳市|xianyang|xy','渭南市|weinan|wn','延安市|yanan|ya','汉中市|hanzhong|hz','榆林市|yulin|yl','安康市|ankang|ak','商洛市|shangluo|sl','兰州市|lanzhou|lz','嘉峪关市|jiayuguan|jyg','金昌市|jinchang|jc','白银市|baiyin|by','天水市|tianshui|ts','武威市|wuwei|ww','张掖市|zhangye|zy','平凉市|pingliang|pl','酒泉市|jiuquan|jq','庆阳市|qingyang|qy','定西市|dingxi|dx','陇南市|longnan|ln','临夏回族自治州|lxhzzzz|lxhzzzz','甘南藏族自治州|gnzzzzz|gnzzzzz','西宁市|xining|xn','海东地区|haidongdiqu|hddq','海北藏族自治州|hbzzzzz|hbzzzzz','黄南藏族自治州|hnzzzzz|hnzzzzz','海南藏族自治州|hnzzzzz|hnzzzzz','果洛藏族自治州|glzzzzz|hlzzzzz','玉树藏族自治州|yszzzzz|yszzzzz','海西蒙古族藏族自治州|hxmgzzzzzz|hxmgzzzzzz','银川市|yinchuan|yc','石嘴山市|shizuishan|szs','吴忠市|wuzhong|wz','固原市|guyuan|gy','中卫市|zhongwei|zw','乌鲁木齐市|wulumuqi|wlmq','克拉玛依市|kelamayi|klmy','吐鲁番地区|tulufandiqu|tlfdq','哈密地区|hamidiqu|hmdq','昌吉回族自治州|cjhzzzz|cjhzzzz','博尔塔拉蒙古自治州|betlmgzzz|betlmgzzz','巴音郭楞蒙古自治州|byglmgzzz|byglmgzzz','阿克苏地区|akesudiqu|aksdq','克孜勒苏柯尔克孜自治州|kzlskekzzzz|kzlskekzzzz','喀什地区|kashidiqu|ksdq','和田地区|hetian|ht','伊犁哈萨克自治州|ylhskzzz|ylhskzzz','塔城地区|tachengdiqu|tcdq','阿勒泰地区|aletaidiqu|altdq','石河子市|shihezi|shz','阿拉尔市|alaer|ale','图木舒克市|tumushuke|tmsk','五家渠市|wujiaqu|wjq','台北市|taibei|tb','高雄市|gaoxiong|gx','基隆市|jilong|jl','台中市|taizhong|tz','台南市|tainan|tn','新竹市|xinzhu|xz','嘉义市|jiayi|jy','台北县|taibeixian|tbx','宜兰县|yilanxian|ylx','桃园县|taoyuanxian|tyx','新竹县|xinzhuxian|xzx','苗栗县|miaolixian|mlx','台中县|taizhongxian|tzx','彰化县|zhanghuaxian|zhx','南投县|nantouxian|ntx','云林县|yunlinxian|ylx','嘉义县|jiayixian|jyx','台南县|tainanxian|tnx','高雄县|gaoxiongxian|gxx','屏东县|pingdongxian|pdx','澎湖县|penghuxian|phx','台东县|taidongxian|tdx','花莲县|hualianxian|hlx','中西区|zhongxiqu|zxq','东区|dongqu|dq','九龙城区|jiulongchengqu|jlcq','观塘区|guantangqu|gtq','南区|nanqu|nq','深水埗区|shenshuibuqu|ssbq','黄大仙区|huangdaxianqu|hdxq','湾仔区|wanzaiqu|wzq','油尖旺区|youjianwangqu|yjwq','离岛区|lidaoqu|ldq','葵青区|kuiqingqu|kqq','北区|beiqu|bq','西贡区|xigongqu|xgq','沙田区|shatianqu|stq','屯门区|tunmenqu|tmq','大埔区|dabuqu|dbq','荃湾区|quanwanqu|qwq','元朗区|yuanlangqu|ylq','花地玛堂区|huadimatangqu|hdmtq','圣安多尼堂区|shenganduonitangqu|sadntq','大堂区|datangqu|dtq','望德堂区|wangdetangqu|wdtq','风顺堂区|fengshuntangqu|fstq','嘉模堂区|jiamotangqu|jmtq','圣方济各堂区|shengfangjigetangqu|sfjgtq','阿城站|acheng|ac','阿尔山站|aershan|aes','阿贵图站|aguitu|agt','阿金站|ajin|aj','阿克苏站|akesu|aks','阿克陶站|aketao|akt','阿拉山口站|alashankou|alsk','阿里河站|alihe|alh','阿龙山站|alongshan|als','阿木尔站|amuer|ame','阿南庄站|ananzhuang|anz','阿图什站|atushi|ats','阿乌尼站|awuni|awn','阿寨站|azhai|az','埃岱站|aizuo|az','艾不盖站|aibugai|abg','艾河站|aihe|ah','艾家村站|aijiacun|ajc','爱河站|aihe|ah','安达站|anda|ad','安德站|ande|ad','安多站|anduo|ad','安富镇站|anfuzhen|afz','安广站|anguang|ag','安化站|anhua|ah','安家站|anjia|aj','安家庄站|anjiazhuang|ajz','安康站|ankang|ak','安口窑站|ankouyao|aky','安龙站|anlong|al','安陆站|anlu|al','安平站|anping|ap','安庆沟站|anqinggou|aqg','安庆西站|anqingxizhan|aqxz','安庆站|anqingzhan|aqz','安仁站|anren|ar','安顺站|anshun|as','安塘站|antang|at','安亭北站|antingbeizhan|atbz','安图站|antu|at','安阳站|anyang|ay','安阳东站|anyangdong|ayd','鞍山站|anshan|as','鞍山西站|anshanxi|asx','昂昂溪站|angangxi|aax','昂乃站|angnai|an','敖汉站|aohan|ah','敖来站|aolai|al','敖力布告站|aolibugao|albg','敖头站|aotou|at','鳌江站|zuojiangzhan|zjz','八村站|bacun|bc','八达岭站|badalingzhan|bdlz','八方站|bafang|bf','八虎力站|bahuli|bhl','八家子站|bajiazi|bjz','八角台站|bajiaotai|bjt','八面城站|bamiancheng|bmc','八面通站|bamiantong|bmt','八苏木站|basumu|bsm','八仙筒站|baxiantong|bxt','巴楚站|bachu|bc','巴东站|badong|bd','巴林站|balin|bl','巴山站|bashan|bs','巴彦高勒站|bayangaole|bygl','巴彦郭勒站|bayanguole|bygl','巴中站|bazhong|bz','坝梁站|baliang|bl','鲅鱼圈站|zuoyuquan|zyq','霸州站|bazhouzhan|bzz','白壁关站|baibiguan|bbg','白城站|baicheng|bc','白果站|baiguo|bg','白海站|baihai|bh','白合站|baihe|bh','白河站|baihe|bh','白河东站|baihedong|bhd','白河县站|baihexian|bhx','白桦排站|baizuopai|bzp','白芨沟站|baizuogou|bzg','白鸡坡站|baijipo|bjp','白涧站|baijianzhan|bjz','白奎堡站|baikuibao|bkb','白狼站|bailang|bl','白马站|baima|bm','白旗站|baiqi|bq','白泉站|baiquan|bq','白沙站|baisha|bs','白沙坡站|baishapo|bsp','白沙沱站|baishazuo|bsz','白山市站|baishanshi|bss','白山乡站|baishanxiang|bsx','白石山站|baishishan|bss','白石岩站|baishiyan|bsy','白水站|baishui|bs','白水江站|baishuijiang|bsj','白水镇站|baishuizhen|bsz','白涛站|baitao|bt','白彦花站|baiyanhua|byh','白音察干站|baiyinchagan|bycg','白音胡硕站|baiyinhushuo|byhs','白音他拉站|baiyintala|bytl','白银哈尔站|baiyinhaer|byhe','白银市站|baiyinshi|bys','白银西站|baiyinxi|byx','白云鄂博站|baiyunebo|byeb','百里峡站|bailixiazhan|blxz','百色站|baise|bs','柏村站|baicun|bc','柏果站|baiguo|bg','柏林站|bailin|bl','班猫箐站|banmaozuo|bmz','坂尾站|zuowei|zw','板城站|bancheng|bc','板石河站|banshihe|bsh','半截河站|banjiehe|bjh','蚌埠南站|bangbunanzhan|bbnz','蚌埠站|bangbuzhan|bbz','包头站|baotou|bt','包头东站|baotoudong|btd','宝坻站|baozuozhan|bzz','宝东站|baodong|bd','宝华山站|baohuashanzhan|bhsz','宝鸡站|baoji|bj','宝鸡南站|baojinan|bjn','宝拉格站|baolage|blg','宝老山站|baolaoshan|bls','宝林站|baolin|bl','宝龙山站|baolongshan|bls','宝木吐站|baomutu|bmt','宝泉岭站|baoquanling|bql','宝山站|baoshan|bs','保定东站|baodingdongzhan|bddz','保定站|baodingzhan|bdz','保家楼站|baojialou|bjl','保健站|baojian|bj','保康站|baokang|bk','葆园站|zuoyuan|zy','堡子湾站|baoziwan|bzw','北安站|beian|ba','北白站|beibai|bb','北板桥站|beibanqiao|bbq','北碚站|beizuo|bz','北仓站|beicang|bc','北戴河站|beidaihezhan|bdhz','北磴站|beizuo|bz','北岗站|beigang|bg','北海站|beihai|bh','北滘站|beijiao|bj','北京北站|beijingbeizhan|bjbz','北京东站|beijingdongzhan|bjdz','北京南站|beijingnanzhan|bjnz','北京西站|beijingxizhan|bjxz','北京站|beijingzhan|bjz','北井子站|beijingzi|bjz','北林站|beilin|bl','北流站|beiliu|bl','北牟站|beimou|bm','北票南站|beipiaonan|bpn','北台站|beitai|bt','北台子站|beitaizi|btz','北头河站|beitouhe|bth','北屯站|beitun|bt','北屯市站|beitunshi|bts','北兴安站|beixingan|bxa','北义井站|beiyijing|byj','北宅站|beizhai|bz','背荫河站|beiyinhe|byh','本井站|benjing|bj','本溪站|benxi|bx','本溪湖站|benxihu|bxh','笔架山站|bijiashan|bjs','毕克齐站|bikeqi|bkq','碧江站|bijiang|bj','碧水站|bishui|bs','碧州站|bizhou|bz','边沟站|biangou|bg','别山站|bieshan|bs','彬江站|binjiang|bj','滨海北站|binhaibeizhan|bhbz','滨海站|binhaizhan|bhz','滨江站|binjiang|bj','栟茶站|bingchazhan|bcz','丙谷站|binggu|bg','播明站|boming|bm','泊头站|botouzhan|btz','勃利站|boli|bl','亳州站|zuozhouzhan|zzz','博鳌站|bozuo|bz','博克图站|boketu|bkt','博乐站|bole|bl','博兴站|boxing|bx','渤海站|bohai|bh','布敦化站|budunhua|bdh','布海站|buhai|bh','布列开站|buliekai|blk','布苏里站|busuli|bsl','采石场站|caishichang|csc','蔡家沟站|caijiagou|cjg','蔡家坡站|caijiapo|cjp','蚕厂屯站|canchangtun|cct','苍南站|cangnanzhan|cnz','苍坪站|cangping|cp','苍石站|cangshi|cs','沧州西站|cangzhouxizhan|czxz','沧州站|cangzhouzhan|czz','曹家营子站|caojiayingzi|cjyz','曹县站|caoxian|cx','曹子里站|caozili|czl','草海站|caohai|ch','草河口站|caohekou|chk','草市站|caoshi|cs','册亨站|ceheng|ch','岑溪站|zuoxi|zx','曾家坪子站|zengjiapingzi|zjpz','茶陵站|chaling|cl','茶陵南站|chalingnan|cln','茶条沟站|chatiaogou|ctg','茶镇站|chazhen|cz','查布嘎站|chabuga|cbg','查干哈达站|chaganhada|cghd','查干芒和站|chaganmanghe|cgmh','查干特格站|chagantege|cgtg','察尔汗站|chaerhan|ceh','察素齐站|chasuqi|csq','岔河站|chahe|ch','岔江站|chajiang|cj','柴岗站|chaigang|cg','柴河站|chaihe|ch','昌乐站|changle|cl','昌黎站|changlizhan|clz','昌平北站|changpingbeizhan|cpbz','昌平站|changpingzhan|cpz','昌图站|changtu|ct','昌图西站|changtuxi|ctx','常德站|changde|cd','常平站|changping|cp','常兴站|changxing|cx','常州北站|changzhoubeizhan|czbz','常州站|changzhouzhan|czz','超梁沟站|chaolianggou|clg','巢湖东站|chaohudongzhan|chdz','巢湖站|chaohuzhan|chz','朝格温多尔站|chaogewenduoer|cgwde','朝天站|chaotian|ct','朝阳站|chaoyang|cy','朝阳川站|chaoyangchuan|cyc','朝阳村站|chaoyangcun|cyc','朝阳地站|chaoyangdi|cyd','朝阳镇站|chaoyangzhen|cyz','朝中站|chaozhong|cz','潮汕站|chaoshan|cs','潮水站|chaoshui|cs','潮阳站|chaoyang|cy','潮州站|chaozhou|cz','车转湾站|chezhuanwan|czw','郴州站|chenzhou|cz','郴州西站|chenzhouxi|czx','辰清站|chenqing|cq','辰溪站|chenxi|cx','陈官营站|chenguanying|cgy','陈家湾站|chenjiawan|cjw','陈相屯站|chenxiangtun|cxt','晨明站|chenming|cm','成都站|chengdu|cd','成都东站|chengdudong|cdd','成都南站|chengdunan|cdn','成高子站|chenggaozi|cgz','成吉思汗站|chengjisihan|cjsh','承德东站|chengdedong|cdd','承德站|chengdezhan|cdz','城固站|chenggu|cg','城子坦站|chengzitan|czt','程家站|chengjia|cj','池河站|chihe|ch','池州站|chizhouzhan|czz','赤壁站|chibi|cb','赤壁北站|chibibei|cbb','赤峰站|chifeng|cf','赤峰东站|chifengdong|cfd','赤峰西站|chifengxi|cfx','崇信站|chongxin|cx','崇左站|chongzuo|cz','滁州北站|chuzhoubeizhan|czbz','滁州站|chuzhouzhan|czz','楚鲁图站|chulutu|clt','楚山站|chushan|cs','楚雄站|chuxiong|cx','褚家湾站|zuojiawan|zjw','串子沟站|chuanzigou|czg','创业村站|chuangyecun|cyc','春亭阁站|chuntingge|ctg','春阳站|chunyang|cy','茨坝站|ciba|cb','茨冲站|cichong|cc','慈利站|cili|cl','磁山站|cishanzhan|csz','磁县站|cixianzhan|cxz','磁窑站|ciyao|cy','崔黄口站|cuihuangkou|chk','翠峰站|cuifeng|cf','翠岗站|cuigang|cg','嵯岗站|zuogang|zg','达布逊站|dabuxun|dbx','达家沟站|dajiagou|djg','达拉滨站|dalabin|dlb','达日其嘎站|dariqiga|drqg','达州站|dazhou|dz','打柴沟站|dachaigou|dcg','打羊站|dayang|dy','大安站|daan|da','大安北站|daanbei|dab','大巴站|daba|db','大坝站|daba|db','大板站|daban|db','大堡站|dabao|db','大厂县站|dachangxian|dcx','大成站|dacheng|dc','大甸站|dadian|dd','大东站|dadong|dd','大关站|daguan|dg','大观岭站|daguanling|dgl','大官屯站|daguantun|dgt','大罕站|dahan|dh','大河坝站|daheba|dhb','大红旗站|dahongqi|dhq','大虎山站|dahushan|dhs','大荒地站|dahuangdi|dhd','大灰厂站|dahuichang|dhc','大涧站|dajian|dj','大苴站|dazuo|dz','大口钦站|dakouqin|dkq','大口屯站|dakoutun|dkt','大理站|dali|dl','大荔站|dali|dl','大栗子站|dalizi|dlz','大连站|dalian|dl','大连北站|dalianbei|dlb','大良站|daliang|dl','大林站|dalin|dl','大刘家站|daliujia|dlj','大陆号站|daluhao|dlh','大民屯站|damintun|dmt','大盘石站|dapanshi|dps','大平房站|dapingfang|dpf','大埔站|dapu|dp','大其拉哈站|daqilaha|dqlh','大桥站|daqiao|dq','大青沟站|daqinggou|dqg','大庆站|daqing|dq','大庆西站|daqingxi|dqx','大山铺站|dashanpu|dsp','大深站|dashen|ds','大石桥站|dashiqiao|dsq','大石头站|dashitou|dst','大石寨站|dashizhai|dsz','大滩站|datan|dt','大田边站|datianbian|dtb','大同站|datong|dt','大同东站|datongdong|dtd','大屯站|datun|dt','大湾子站|dawanzi|dwz','大乌苏站|dawusu|dws','大武口站|dawukou|dwk','大西站|daxi|dx','大辛庄站|daxinzhuang|dxz','大兴站|daxing|dx','大兴沟站|daxinggou|dxg','大兴镇站|daxingzhen|dxz','大徐屯站|daxutun|dxt','大雅河站|dayahe|dyh','大雁站|dayan|dy','大扬气站|dayangqi|dyq','大杨树站|dayangshu|dys','大杨树东站|dayangshudong|dysd','大冶北站|dayebei|dyb','大英东站|dayingdong|dyd','大营站|daying|dy','大营镇站|dayingzhenzhan|dyzz','大营子站|dayingzi|dyz','大战场站|dazhanchang|dzc','大杖子站|dazhangzi|dzz','大竹园站|dazhuyuan|dzy','大足站|dazu|dz','代马沟站|daimagou|dmg','代湾站|daiwan|dw','代县站|daixian|dx','岱岳站|zuoyue|zy','带岭站|dailing|dl','待王站|daiwang|dw','丹东站|dandong|dd','丹凤站|danfeng|df','丹徒站|dantuzhan|dtz','丹阳北站|danyangbeizhan|dybz','丹阳站|danyangzhan|dyz','当雄站|dangxiong|dx','当阳站|dangyang|dy','砀山站|zuoshanzhan|zsz','刀尔登站|daoerdeng|ded','到保站|daobao|db','道德站|daode|dd','道口站|daokou|dk','道仑郭勒站|daolunguole|dlgl','道镇站|daozhen|dz','道州站|daozhou|dz','得耳布尔站|deerbuer|debe','得胜台站|deshengtai|dst','德安站|dean|da','德伯斯站|debosi|dbs','德昌站|dechang|dc','德惠站|dehui|dh','德惠西站|dehuixi|dhx','德令哈站|delingha|dlh','德清西站|deqingxizhan|dqxz','德清站|deqingzhan|dqz','德日斯图站|derisitu|drst','德阳站|deyang|dy','德州东站|dezhoudongzhan|dzdz','德州站|dezhouzhan|dzz','灯塔站|dengta|dt','登沙河站|dengshahe|dsh','登瀛崖站|dengzuoya|dzy','邓家湾站|dengjiawan|djw','邓州站|dengzhou|dz','低窝铺站|diwopu|dwp','低庄站|dizhuang|dz','滴道站|didao|dd','甸心站|dianxin|dx','丁家园站|dingjiayuan|djy','定边站|dingbian|db','定南站|dingnan|dn','定陶站|dingtao|dt','定西站|dingxi|dx','定襄站|dingxiang|dx','定远站|dingyuanzhan|dyz','定州东站|dingzhoudongzhan|dzdz','定州站|dingzhouzhan|dzz','东安东站|dongandong|dad','东边井站|dongbianjing|dbj','东大坝站|dongdaba|ddb','东二道河站|dongerdaohe|dedh','东方红站|dongfanghong|dfh','东丰站|dongfeng|df','东富站|dongfu|df','东宫站|donggong|dg','东沟门站|donggoumen|dgm','东莞站|dongzuo|dz','东莞东站|dongzuodong|dzd','东光站|dongguangzhan|dgz','东海站|donghai|dh','东海县站|donghaixianzhan|dhxz','东壕站|donghao|dh','东湖站|donghu|dh','东佳木斯站|dongjiamusi|djms','东津站|dongjin|dj','东京城站|dongjingcheng|djc','东来站|donglai|dl','东门站|dongmen|dm','东明村站|dongmingcun|dmc','东明县站|dongmingxian|dmx','东南营子站|dongnanyingzi|dnyz','东坡站|dongpo|dp','东升站|dongsheng|ds','东升坝站|dongshengba|dsb','东胜西站|dongshengxi|dsx','东台站|dongtaizhan|dtz','东田良站|dongtianliang|dtl','东通化站|dongtonghua|dth','东湾站|dongwan|dw','东乡站|dongxiang|dx','东辛庄站|dongxinzhuang|dxz','东兴站|dongxing|dx','东营站|dongying|dy','东营子站|dongyingzi|dyz','东元庆站|dongyuanqing|dyq','东镇站|dongzhen|dz','东至站|dongzhizhan|dzz','氡泉站|zuoquan|zq','洞庙河站|dongmiaohe|dmh','洞子崖站|dongziya|dzy','都格站|duge|dg','都江堰站|dujiangyan|djy','都伦站|dulun|dl','都匀站|duyun|dy','斗沟子站|dougouzi|dgz','豆罗站|douluo|dl','豆沙关站|doushaguan|dsg','豆庄站|douzhuang|dz','独立屯站|dulitun|dlt','独山站|dushan|ds','杜尔基站|duerji|dej','杜家站|dujia|dj','杜赵站|duzhao|dz','渡市站|dushi|ds','段家站|duanjia|dj','段甲岭站|duanjialing|djl','对青山站|duiqingshan|dqs','敦化站|dunhua|dh','敦煌站|dunhuang|dh','峨边站|ebian|eb','峨眉站|emei|em','额济纳站|ejina|ejn','鄂州站|ezhou|ez','鄂州东站|ezhoudong|ezd','恩施站|enshi|es','尔赛河站|ersaihe|esh','二岔站|ercha|ec','二道岗站|erdaogang|edg','二道沟门站|erdaogoumen|edgm','二道河站|erdaohe|edh','二道桥站|erdaoqiao|edq','二道湾站|erdaowan|edw','二道岩站|erdaoyan|edy','二井站|erjing|ej','二龙站|erlong|el','二龙山屯站|erlongshantun|elst','二密河站|ermihe|emh','二营站|erying|ey','二营子站|eryingzi|eyz','发耳站|faer|fe','繁峙站|fanzhi|fz','范家屯站|fanjiatun|fjt','范镇站|fanzhen|fz','防城港北站|fangchenggangbei|fcgb','肥东站|feidongzhan|fdz','费县站|feixian|fx','分宜站|fenyi|fy','汾河站|fenhe|fh','汾阳站|fenyang|fy','丰城站|fengcheng|fc','丰城南站|fengchengnan|fcn','丰都站|fengdu|fd','丰广站|fengguang|fg','丰乐镇站|fenglezhen|flz','丰水村站|fengshuicun|fsc','丰顺站|fengshun|fs','丰镇站|fengzhen|fz','风陵渡站|fenglingdu|fld','风水沟站|fengshuigou|fsg','封丘站|fengqiu|fq','峰高铺站|fenggaopu|fgp','冯家山站|fengjiashan|fjs','冯屯站|fengtun|ft','凤凰城站|fenghuangcheng|fhc','凤县站|fengxian|fx','凤翔站|fengxiang|fx','凤阳站|fengyangzhan|fyz','凤州站|fengzhou|fz','奉化站|fenghuazhan|fhz','佛岭站|foling|fl','佛山站|foshan|fs','扶绥站|fusui|fs','扶余站|fuyu|fy','扶余北站|fuyubei|fyb','浮图峪站|futuyu|fty','涪陵站|fuling|fl','涪陵北站|fulingbei|flb','福安站|fuan|fa','福德站|fude|fd','福鼎站|fuding|fd','福海站|fuhai|fh','福巨站|fuju|fj','福利屯站|fulitun|flt','福临堡站|fulinbao|flb','福清站|fuqing|fq','福泉站|fuquan|fq','福山口站|fushankou|fsk','福山寺站|fushansi|fss','福生庄站|fushengzhuang|fsz','福兴地站|fuxingdi|fxd','福州站|fuzhou|fz','福州南站|fuzhounan|fzn','抚宁站|funingzhan|fnz','抚顺站|fushun|fs','抚顺北站|fushunbei|fsb','抚远站|fuyuan|fy','抚州站|fuzhou|fz','阜南站|funanzhan|fnz','阜宁站|funingzhan|fnz','阜新站|fuxin|fx','阜阳站|fuyangzhan|fyz','富川站|fuchuan|fc','富海站|fuhai|fh','富锦站|fujin|fj','富拉尔基站|fulaerji|flej','富县站|fuxian|fx','富县东站|fuxiandong|fxd','富裕站|fuyu|fy','富源站|fuyuan|fy','富庄子站|fuzhuangzi|fzz','嘎拉德斯汰站|galadesitai|gldst','嘎什甸子站|gashidianzi|gsdz','盖州站|gaizhou|gz','盖州西站|gaizhouxi|gzx','甘草店站|gancaodian|gcd','甘谷站|gangu|gg','甘河站|ganhe|gh','甘洛站|ganluo|gl','甘旗卡站|ganqika|gqk','甘泉站|ganquan|gq','甘泉北站|ganquanbei|gqb','甘棠站|gantang|gt','泔溪站|zuoxi|zx','赶水站|ganshui|gs','感德站|gande|gd','干沟站|gangou|gg','干塘站|gantang|gt','干溪沟站|ganxigou|gxg','赣州站|ganzhou|gz','刚察站|gangcha|gc','皋兰站|gaolan|gl','高碑店东站|gaobeidiandongzhan|gbddz','高碑店站|gaobeidianzhan|gbdz','高村站|gaocun|gc','高峰站|gaofeng|gf','高峰站|gaofeng|gf','高各庄站|gaogezhuang|ggz','高谷站|gaogu|gg','高家站|gaojia|gj','高家村站|gaojiacun|gjc','高家岁站|gaojiasui|gjs','高老站|gaolao|gl','高梁铺站|gaoliangpu|glp','高林屯站|gaolintun|glt','高岭站|gaoling|gl','高密站|gaomi|gm','高平站|gaoping|gp','高坪铺站|gaopingpu|gpp','高桥镇站|gaoqiaozhen|gqz','高山子站|gaoshanzi|gsz','高寺台站|gaositai|gst','高台站|gaotai|gt','高台子站|gaotaizi|gtz','高滩站|gaotan|gt','高潭子站|gaotanzi|gtz','高头站|gaotou|gt','高兴站|gaoxing|gx','高邑西站|gaoyixizhan|gyxz','高邑站|gaoyizhan|gyz','高州站|gaozhou|gz','藁城站|zuochengzhan|zcz','鸽子洞站|gezidong|gzd','革居站|geju|gj','革镇堡站|gezhenbao|gzb','格尔木站|geermu|gem','格口站|gekou|gk','葛店南站|gediannan|gdn','葛根庙站|gegenmiao|ggm','根河站|genhe|gh','工农湖站|gongnonghu|gnh','公积坂站|gongjizuo|gjz','公庙子站|gongmiaozi|gmz','公兴站|gongxing|gx','公营子站|gongyingzi|gyz','公主埂站|gongzhugeng|gzg','公主岭站|gongzhuling|gzl','公主岭南站|gongzhulingnan|gzln','巩义站|gongyi|gy','巩义南站|gongyinan|gyn','共和站|gonghe|gh','共青城站|gongqingcheng|gqc','沟帮子站|goubangzi|gbz','沟口站|goukou|gk','孤店子站|gudianzi|gdz','孤家子站|gujiazi|gjz','孤山站|gushan|gs','孤山口站|gushankou|gsk','孤山子站|gushanzi|gsz','姑家堡站|gujiabao|gjb','古城站|gucheng|gc','古城镇站|guchengzhen|gcz','古城子站|guchengzi|gcz','古东站|gudong|gd','古家沱站|gujiazuo|gjz','古交站|gujiao|gj','古浪站|gulang|gl','古里金站|gulijin|glj','古莲站|gulian|gl','古田站|gutian|gt','古源站|guyuan|gy','古镇站|guzhen|gz','谷城站|gucheng|gc','固始站|gushi|gs','固原站|guyuan|gy','固镇站|guzhenzhan|gzz','瓜州站|guazhou|gz','关村坝站|guancunba|gcb','关林站|guanlin|gl','关寨站|guanzhai|gz','官高站|guangao|gg','官家站|guanjia|gj','官厅西站|guantingxi|gtx','官厅站|guantingzhan|gtz','官字井站|guanzijing|gzj','冠豸山站|guanzuoshan|gzs','灌水站|guanshui|gs','光辉站|guanghui|gh','光明站|guangming|gm','光明城站|guangmingcheng|gmc','光泽站|guangze|gz','广安站|guangan|ga','广安南站|guangannan|gan','广德号站|guangdehao|gdh','广德站|guangdezhan|gdz','广汉站|guanghan|gh','广南卫站|guangnanwei|gnw','广宁寺站|guangningsi|gns','广盛站|guangsheng|gs','广水站|guangshui|gs','广顺场站|guangshunchang|gsc','广通北站|guangtongbei|gtb','广元站|guangyuan|gy','广元东站|guangyuandong|gyd','广元南站|guangyuannan|gyn','广州站|guangzhou|gz','广州北站|guangzhoubei|gzb','广州东站|guangzhoudong|gzd','广州南站|guangzhounan|gzn','归流河站|guiliuhe|glh','贵定站|guiding|gd','贵定南站|guidingnan|gdn','贵港站|guigang|gg','贵溪站|guixi|gx','贵阳站|guiyang|gy','桂林站|guilin|gl','桂平站|guiping|gp','郭尔奔敖包站|guoerbenaobao|gebab','郭家店站|guojiadian|gjd','郭家屯站|guojiatun|gjt','虢镇站|zuozhen|zz','哈川站|hachuan|hc','哈达站|hada|hd','哈达阳站|hadayang|hdy','哈尔巴岭站|haerbaling|hebl','哈尔滨站|haerbin|heb','哈尔滨东站|haerbindong|hebd','哈尔滨西站|haerbinxi|hebx','哈尔盖站|haergai|heg','哈福站|hafu|hf','哈克站|hake|hk','哈拉沟站|halagou|hlg','哈拉海站|halahai|hlh','哈拉黑站|halahei|hlh','哈拉苏站|halasu|hls','哈力图站|halitu|hlt','哈密站|hami|hm','哈日努拉站|harinula|hrnl','哈业胡同站|hayehutong|hyht','蛤蟆塘站|gezuotang|gzt','海安县站|haianxianzhan|haxz','海北站|haibei|hb','海城站|haicheng|hc','海城西站|haichengxi|hcx','海口站|haikou|hk','海口东站|haikoudong|hkd','海拉尔站|hailaer|hle','海浪站|hailang|hl','海林站|hailin|hl','海龙站|hailong|hl','海伦站|hailun|hl','海宁西站|hainingxizhan|hnxz','海宁站|hainingzhan|hnz','海石湾站|haishiwan|hsw','海坨子站|haizuozi|hzz','海湾站|haiwanzhan|hwz','海晏站|haizuo|hz','海阳站|haiyang|hy','邯郸东站|handandongzhan|hddz','邯郸站|handanzhan|hdz','涵江站|hanjiang|hj','韩城站|hancheng|hc','韩府湾站|hanfuwan|hfw','韩家园站|hanjiayuan|hjy','韩麻营站|hanmaying|hmy','寒葱沟站|hanconggou|hcg','寒岭站|hanling|hl','汉沟镇站|hangouzhen|hgz','汉阴站|hanyin|hy','汉源站|hanyuan|hy','汉中站|hanzhong|hz','汗苏鲁站|hansulu|hsl','杭锦旗站|hangjinqi|hjq','杭州东站|hangzhoudongzhan|hzdz','杭州站|hangzhouzhan|hzz','好鲁库站|haoluku|hlk','浩良河站|haolianghe|hlh','合川站|hechuan|hc','合肥北城站|hefeibeichengzhan|hfbcz','合肥南站|hefeinanzhan|hfnz','合肥站|hefeizhan|hfz','合浦站|hepu|hp','合阳站|heyang|hy','何三家站|hesanjia|hsj','和村站|hecun|hc','和龙站|helong|hl','和平站|heping|hp','和什托洛盖站|heshituoluogai|hstlg','和硕站|heshuo|hs','和田站|hetian|ht','河边站|hebian|hb','河唇站|hechun|hc','河津站|hejin|hj','河口南站|hekounan|hkn','河洛营站|heluoying|hly','河南站|henan|hn','河汤沟站|hetanggou|htg','河源站|heyuan|hy','核桃园站|hetaoyuan|hty','菏泽站|heze|hz','贺家店站|hejiadian|hjd','贺家河站|hejiahe|hjh','贺日斯台站|herisitai|hrst','贺胜桥东站|heshengqiaodong|hsqd','贺州站|hezhou|hz','赫尔洪得站|heerhongde|hehd','鹤北站|hebei|hb','鹤壁站|hebi|hb','鹤壁东站|hebidong|hbd','鹤岗站|hegang|hg','鹤立站|heli|hl','鹤庆站|heqing|hq','鹤山站|heshan|hs','黑冲滩站|heichongtan|hct','黑河站|heihe|hh','黑井站|heijing|hj','黑沙土站|heishatu|hst','黑山头站|heishantou|hst','黑石木站|heishimu|hsm','黑水站|heishui|hs','黑台站|heitai|ht','黑旺站|heiwang|hw','恒地营站|hengdiying|hdy','横道河子站|hengdaohezi|hdhz','横峰站|hengfeng|hf','横岗站|henggang|hg','横沟桥东站|henggouqiaodong|hgqd','横江站|hengjiang|hj','衡山站|hengshan|hs','衡山西站|hengshanxi|hsx','衡水站|hengshuizhan|hsz','衡阳站|hengyang|hy','衡阳东站|hengyangdong|hyd','红安西站|honganxi|hax','红房子站|hongfangzi|hfz','红峰站|hongfeng|hf','红光镇站|hongguangzhen|hgz','红果站|hongguo|hg','红花沟站|honghuagou|hhg','红花塘站|honghuatang|hht','红花园站|honghuayuan|hhy','红江站|hongjiang|hj','红柳河站|hongliuhe|hlh','红旗站|hongqi|hq','红旗营站|hongqiying|hqy','红砂坝站|hongshaba|hsb','红砂岘站|hongshazuo|hsz','红山站|hongshan|hs','红石站|hongshi|hs','红寺堡站|hongsibao|hsb','红岘台站|hongzuotai|hzt','红星站|hongxing|hx','红兴隆站|hongxinglong|hxl','红彦站|hongyan|hy','宏庆站|hongqing|hq','宏图站|hongtu|ht','洪安乡站|honganxiang|hax','洪洞站|hongdong|hd','洪洞西站|hongdongxi|hdx','洪河站|honghe|hh','侯家坪站|houjiaping|hjp','侯马站|houma|hm','侯马西站|houmaxi|hmx','侯杖子站|houzhangzi|hzz','猴山站|houshan|hs','后寨站|houzhai|hz','呼兰站|hulan|hl','呼源站|huyuan|hy','呼中站|huzhong|hz','葫芦岛站|huludao|hld','葫芦岛北站|huludaobei|hldb','湖北站|hubei|hb','湖潮站|huchao|hc','湖口站|hukou|hk','湖南站|hunan|hn','湖州站|huzhouzhan|hzz','虎尔虎拉站|huerhula|hehl','虎峰站|hufeng|hf','虎林站|hulin|hl','虎门站|humen|hm','虎山站|hushan|hs','虎什哈站|hushiha|hsh','虎石台站|hushitai|hst','互助站|huzhu|hz','花湖站|huahu|hh','花家庄站|huajiazhuang|hjz','花楼坝站|hualouba|hlb','花棚子站|huapengzi|hpz','花桥站|huaqiaozhan|hqz','花山站|huashan|hs','花山南站|huashannan|hsn','花土坡站|huatupo|htp','花园站|huayuan|hy','花庄站|huazhuang|hz','华安站|huaan|ha','华城站|huacheng|hc','华家站|huajia|hj','华容站|huarong|hr','华容东站|huarongdong|hrd','华容南站|huarongnan|hrn','华山站|huashan|hs','华山北站|huashanbei|hsb','华蓥站|huazuo|hz','滑县南站|huaxiannan|hxn','化处站|huachu|hc','化州站|huazhou|hz','画桥站|huaqiao|hq','桦林站|zuolin|zl','桦南站|zuonan|zn','桦皮厂站|zuopichang|zpc','怀化站|huaihua|hh','怀仁站|huairen|hr','怀仁东站|huairendong|hrd','怀柔北站|huairoubeizhan|hrbz','怀柔站|huairouzhan|hrz','淮安站|huaianzhan|haz','淮北站|huaibeizhan|hbz','淮滨站|huaibin|hb','淮南东站|huainandongzhan|hndz','淮南站|huainanzhan|hnz','桓龙湖站|huanlonghu|hlh','换新天站|huanxintian|hxt','荒沟西站|huanggouxi|hgx','皇德站|huangde|hd','黄白茨站|huangbaici|hbc','黄村站|huangcunzhan|hcz','黄冈站|huanggang|hg','黄冈东站|huanggangdong|hgd','黄冈西站|huanggangxi|hgx','黄瓜园站|huangguayuan|hgy','黄花筒站|huanghuatong|hht','黄家坝站|huangjiaba|hjb','黄家店站|huangjiadian|hjd','黄甲屯站|huangjiatun|hjt','黄口站|huangkouzhan|hkz','黄联关站|huanglianguan|hlg','黄磏站|huangchu|hc','黄陵站|huangling|hl','黄梅站|huangmeizhan|hmz','黄泥河站|huangnihe|hnh','黄泥崴子站|huangnizuozi|hnzz','黄平站|huangping|hp','黄山北站|huangshanbeizhan|hsbz','黄山站|huangshanzhan|hsz','黄石站|huangshi|hs','黄石北站|huangshibei|hsb','黄水塘站|huangshuitang|hst','黄丝站|huangsi|hs','黄松甸站|huangsongdian|hsd','黄桶站|huangtong|ht','黄土坎站|huangtukan|htk','黄羊滩站|huangyangtan|hyt','黄羊镇站|huangyangzhen|hyz','黄洋站|huangyang|hy','黄州站|huangzhou|hz','湟源站|zuoyuan|zy','潢川站|zuochuan|zc','辉南站|huinan|hn','徽县站|huixian|hx','汇流河站|huiliuhe|hlh','汇塘河站|huitanghe|hth','会同站|huitong|ht','惠东站|huidong|hd','惠农站|huinong|hn','惠山站|huishanzhan|hsz','惠州站|huizhou|hz','惠州南站|huizhounan|hzn','惠州西站|huizhouxi|hzx','火炬沟站|huojugou|hjg','火连寨站|huolianzhai|hlz','火烧寨站|huoshaozhai|hsz','获嘉站|huojia|hj','霍尔果斯站|huoerguosi|hegs','霍林郭勒站|huolinguole|hlgl','霍邱站|huoqiuzhan|hqz','霍州站|huozhou|hz','霍州东站|huozhoudong|hzd','芨岭站|zuoling|zl','鸡东站|jidong|jd','鸡冠山站|jiguanshan|jgs','鸡西站|jixi|jx','吉安站|jian|ja','吉林站|jilin|jl','吉首站|jishou|js','吉舒站|jishu|js','吉文站|jiwen|jw','吉新河站|jixinhe|jxh','集安站|jian|ja','集北站|jibei|jb','集宁南站|jiningnan|jnn','纪家沟站|jijiagou|jjg','济南站|jinan|jn','济南东站|jinandong|jnd','济南西站|jinanxi|jnx','济宁站|jining|jn','济源站|jiyuan|jy','绩溪北站|jixibeizhan|jxbz','绩溪县站|jixixianzhan|jxxz','蓟县南站|jixiannan|jxn','蓟县站|jixianzhan|jxz','稷山站|zuoshan|zs','加格达奇站|jiagedaqi|jgdq','加劳站|jialao|jl','加南站|jianan|jn','佳木斯站|jiamusi|jms','嘉川站|jiachuan|jc','嘉峰站|jiafeng|jf','嘉善南站|jiashannanzhan|jsnz','嘉善站|jiashanzhan|jsz','嘉祥站|jiaxiang|jx','嘉兴南站|jiaxingnanzhan|jxnz','嘉兴站|jiaxingzhan|jxz','嘉峪关站|jiayuguan|jyg','夹心子站|jiaxinzi|jxz','甲山站|jiashan|js','简阳站|jianyang|jy','碱柜站|jiangui|jg','建昌站|jianchang|jc','建湖站|jianhuzhan|jhz','建宁县北站|jianningxianbei|jnxb','建瓯站|jianzuo|jz','建三江站|jiansanjiang|jsj','建设站|jianshe|js','建始站|jianshi|js','建水站|jianshui|js','建阳站|jianyang|jy','涧池铺站|jianchipu|jcp','江边村站|jiangbiancun|jbc','江都站|jiangduzhan|jdz','江华站|jianghua|jh','江家岭站|jiangjialing|jjl','江津站|jiangjin|jj','江门站|jiangmen|jm','江密峰站|jiangmifeng|jmf','江宁站|jiangningzhan|jnz','江桥站|jiangqiao|jq','江山站|jiangshanzhan|jsz','江所田站|jiangsuotian|jst','江西村站|jiangxicun|jxc','江永站|jiangyong|jy','江油站|jiangyou|jy','将乐站|jiangle|jl','姜家站|jiangjia|jj','姜堰站|jiangyanzhan|jyz','蒋村站|jiangcun|jc','绛帐站|zuozhang|zz','交城站|jiaocheng|jc','胶州站|jiaozhou|jz','胶州北站|jiaozhoubei|jzb','蛟河站|zuohe|zh','焦作站|jiaozuo|jz','蕉溪站|jiaoxi|jx','角美站|jiaomei|jm','揭阳站|jieyang|jy','街基站|jieji|jj','界山站|jieshan|js','金宝屯站|jinbaotun|jbt','金昌站|jinchang|jc','金城站|jincheng|jc','金城江站|jinchengjiang|jcj','金刚沱站|jingangzuo|jgz','金沟屯站|jingoutun|jgt','金河站|jinhe|jh','金华南站|jinhuananzhan|jhnz','金华站|jinhuazhan|jhz','金鸡村站|jinjicun|jjc','金家店站|jinjiadian|jjd','金坑站|jinkeng|jk','金口河站|jinkouhe|jkh','金林站|jinlin|jl','金马村站|jinmacun|jmc','金沙岗站|jinshagang|jsg','金沙滩站|jinshatan|jst','金沙湾站|jinshawan|jsw','金山北站|jinshanbeizhan|jsbz','金山屯站|jinshantun|jst','金山湾站|jinshanwan|jsw','金银川站|jinyinchuan|jyc','金寨站|jinzhaizhan|jzz','金杖子站|jinzhangzi|jzz','金州站|jinzhou|jz','锦和站|jinhe|jh','锦河站|jinhe|jh','锦州站|jinzhou|jz','锦州南站|jinzhounan|jzn','进贤站|jinxian|jx','劲松站|jinsong|js','晋城站|jincheng|jc','晋城北站|jinchengbei|jcb','晋江站|jinjiang|jj','晋中站|jinzhong|jz','晋州站|jinzhouzhan|jzz','缙云站|zuoyunzhan|zyz','京山站|jingshan|js','泾县站|zuoxianzhan|zxz','经久站|jingjiu|jj','经棚站|jingpeng|jp','荆门站|jingmen|jm','荆州站|jingzhou|jz','旌德站|zuodezhan|zdz','精河站|jinghe|jh','精河南站|jinghenan|jhn','井店站|jingdianzhan|jdz','井冈山站|jinggangshan|jgs','井陉站|jingzuozhan|jzz','井南站|jingnanzhan|jnz','景德镇站|jingdezhen|jdz','景泰站|jingtai|jt','敬梓场站|jingzuochang|jzc','靖边站|jingbian|jb','靖远站|jingyuan|jy','靖远西站|jingyuanxi|jyx','靖州站|jingzhou|jz','静海站|jinghaizhan|jhz','镜铁山站|jingtieshan|jts','九江站|jiujiang|jj','九龙塘站|jiulongtang|jlt','九三站|jiusan|js','九台站|jiutai|jt','九台南站|jiutainan|jtn','九营站|jiuying|jy','酒泉站|jiuquan|jq','旧庄窝站|jiuzhuangwo|jzw','旧庄窝东站|jiuzhuangwodong|jzwd','莒南站|zuonan|zn','莒县站|zuoxian|zx','巨宝站|jubao|jb','巨亭站|juting|jt','巨野站|juye|jy','句容西站|jurongxizhan|jrxz','鄄城站|zuocheng|zc','军粮城站|junliangcheng|jlc','军粮城北站|junliangchengbeizhan|jlcbz','峻德站|junde|jd','喀喇其站|kalaqi|klq','喀什站|kashi|ks','卡路屯站|kalutun|klt','卡伦站|kalun|kl','开安站|kaian|ka','开道站|kaidao|kd','开封站|kaifeng|kf','开江站|kaijiang|kj','开鲁站|kailu|kl','开通站|kaitong|kt','开原站|kaiyuan|ky','开原西站|kaiyuanxi|kyx','凯北站|kaibei|kb','凯里站|kaili|kl','康城站|kangcheng|kc','康金井站|kangjinjing|kjj','康庄站|kangzhuangzhan|kzz','柯柯站|keke|kk','柯坪站|keping|kp','岢岚站|zuozuo|zz','克东站|kedong|kd','克拉玛依站|kelamayi|klmy','克山站|keshan|ks','克一河站|keyihe|kyh','孔家沟站|kongjiagou|kjg','孔滩站|kongtan|kt','孔庄站|kongzhuang|kz','口前站|kouqian|kq','库车站|kuche|kc','库都尔站|kuduer|kde','库尔勒站|kuerle|kel','库伦站|kulun|kl','宽甸站|kuandian|kd','奎山站|kuishan|ks','奎屯站|kuitun|kt','葵潭站|kuitan|kt','昆都庙站|kundumiao|kdm','昆明站|kunming|km','昆明西站|kunmingxi|kmx','昆山南站|kunshannanzhan|ksnz','昆山站|kunshanzhan|ksz','昆阳站|kunyang|ky','拉白站|labai|lb','拉古站|lagu|lg','拉哈站|laha|lh','拉拉屯站|lalatun|llt','拉林站|lalin|ll','拉萨站|lasa|ls','拉鲊站|lazuo|lz','砬子河站|zuozihe|zzh','喇嘛甸站|lamadian|lmd','喇嘛山站|lamashan|lms','来宾站|laibin|lb','来宾北站|laibinbei|lbb','来福站|laifu|lf','来舟站|laizhou|lz','莱芜东站|laiwudong|lwd','莱芜西站|laiwuxi|lwx','莱西站|laixi|lx','莱阳站|laiyang|ly','涞源站|zuoyuanzhan|zyz','濑湍站|zuotuan|zt','兰岗站|langang|lg','兰家站|lanjia|lj','兰家屯站|lanjiatun|ljt','兰考站|lankao|lk','兰棱站|lanleng|ll','兰岭站|lanling|ll','兰溪站|lanxizhan|lxz','兰州站|lanzhou|lz','兰州东站|lanzhoudong|lzd','兰州西站|lanzhouxi|lzx','蓝村站|lancun|lc','滥坝站|lanba|lb','狼尾山站|langweishan|lws','狼窝铺站|langwopu|lwp','廊坊北站|langfangbeizhan|lfbz','廊坊站|langfangzhan|lfz','朗乡站|langxiang|lx','劳动屯站|laodongtun|ldt','老边站|laobian|lb','老府站|laofu|lf','老古沟站|laogugou|lgg','老莱站|laolai|ll','老岭站|laoling|ll','老罗堡站|laoluobao|llb','老王洞站|laowangdong|lwd','老羊壕站|laoyanghao|lyh','老爷岭站|laoyeling|lyl','老营站|laoying|ly','乐昌站|lechang|lc','乐都站|ledu|ld','乐平市站|lepingshi|lps','乐清站|leqingzhan|lqz','乐山站|leshan|ls','乐善村站|leshancun|lsc','乐素河站|lesuhe|lsh','乐武站|lewu|lw','乐园站|leyuan|ly','乐跃站|leyue|ly','了墩站|liaodun|ld','雷州站|leizhou|lz','耒阳站|zuoyang|zy','耒阳西站|zuoyangxi|zyx','冷水站|lengshui|ls','冷水江东站|lengshuijiangdong|lsjd','离堆公园站|liduigongyuan|ldgy','梨树湾站|lishuwan|lsw','梨树镇站|lishuzhen|lsz','黎城站|lichengzhan|lcz','黎塘站|litang|lt','礼州站|lizhou|lz','李家站|lijia|lj','李家坪站|lijiaping|ljp','李家屯站|lijiatun|ljt','李石寨站|lishizhai|lsz','李市镇站|lishizhen|lsz','李旺站|liwang|lw','里木店站|limudian|lmd','澧县站|zuoxian|zx','醴陵站|zuoling|zl','立志站|lizhi|lz','丽江站|lijiang|lj','丽水站|lishuizhan|lsz','励家站|lijia|lj','利川站|lichuan|lc','溧水站|zuoshuizhan|zsz','溧阳站|zuoyangzhan|zyz','连江站|lianjiang|lj','连山关站|lianshanguan|lsg','连云港站|lianyungangzhan|lygz','连珠山站|lianzhushan|lzs','莲东站|liandong|ld','莲花山站|lianhuashan|lhs','莲江口站|lianjiangkou|ljk','莲塘站|liantang|lt','涟源站|lianyuan|ly','联合乡站|lianhexiang|lhx','廉江站|lianjiang|lj','良村站|liangcun|lc','良各庄站|lianggezhuang|lgz','凉风垭站|liangfengzuo|lfz','凉红站|lianghong|lh','梁底下站|liangdixia|ldx','梁平站|liangping|lp','梁山站|liangshan|ls','两当站|liangdang|ld','两家站|liangjia|lj','两所屯站|liangsuotun|lst','亮兵台站|liangbingtai|lbt','亮甲店站|liangjiadian|ljd','辽阳站|liaoyang|ly','辽源站|liaoyuan|ly','辽中站|liaozhong|lz','聊城站|liaocheng|lc','林东站|lindong|ld','林海站|linhai|lh','林家台站|linjiatai|ljt','林口站|linkou|lk','林盛堡站|linshengbao|lsb','林西站|linxi|lx','林源站|linyuan|ly','林子头站|linzitou|lzt','临巴溪站|linbaxi|lbx','临汾站|linfen|lf','临汾西站|linfenxi|lfx','临海站|linhaizhan|lhz','临河站|linhe|lh','临江场站|linjiangchang|ljc','临江寺站|linjiangsi|ljs','临江溪站|linjiangxi|ljx','临清站|linqing|lq','临水站|linshui|ls','临湘站|linxiang|lx','临沂站|linyi|ly','临沂北站|linyibei|lyb','临颍站|linzuo|lz','临泽站|linze|lz','临淄站|linzi|lz','蔺家楼站|zuojialou|zjl','灵宝站|lingbao|lb','灵宝西站|lingbaoxi|lbx','灵璧站|lingzuozhan|lzz','灵丘站|lingqiu|lq','灵山站|lingshan|ls','灵石东站|lingshidong|lsd','灵武站|lingwu|lw','灵仙庙站|lingxianmiao|lxm','凌海站|linghai|lh','凌源站|lingyuan|ly','凌源东站|lingyuandong|lyd','陵丘站|lingqiu|lq','陵水站|lingshui|ls','零陵站|lingling|ll','岭北站|lingbei|lb','岭南站|lingnan|ln','刘沟站|liugou|lg','刘家村站|liujiacun|ljc','刘家店站|liujiadian|ljd','刘家沟站|liujiagou|ljg','刘家河站|liujiahe|ljh','刘屯站|liutun|lt','刘征站|liuzheng|lz','留庄站|liuzhuang|lz','流水沟站|liushuigou|lsg','柳沟站|liugou|lg','柳河站|liuhe|lh','柳林南站|liulinnan|lln','柳毛站|liumao|lm','柳毛沟站|liumaogou|lmg','柳树站|liushu|ls','柳树泉站|liushuquan|lsq','柳树屯站|liushutun|lst','柳园站|liuyuan|ly','柳园南站|liuyuannan|lyn','柳州站|liuzhou|lz','六安站|liuanzhan|laz','六道河站|liudaohe|ldh','六道河子站|liudaohezi|ldhz','六地沟站|liudigou|ldg','六个鸡站|liugeji|lgj','六合镇站|liuhezhen|lhz','六盘山站|liupanshan|lps','六盘水站|liupanshui|lps','六枝站|liuzhi|lz','龙伯屯站|longbotun|lbt','龙池站|longchi|lc','龙川站|longchuan|lc','龙船站|longchuan|lc','龙沟站|longgou|lg','龙骨甸站|longgudian|lgd','龙华站|longhuazhan|lhz','龙家堡站|longjiabao|ljb','龙嘉站|longjia|lj','龙江站|longjiang|lj','龙井站|longjing|lj','龙里站|longli|ll','龙门河站|longmenhe|lmh','龙南站|longnan|ln','龙泉寺站|longquansi|lqs','龙山站|longshan|ls','龙山镇站|longshanzhen|lsz','龙市站|longshi|ls','龙塘坝站|longtangba|ltb','龙头站|longtou|lt','龙岩站|longyan|ly','龙游站|longyouzhan|lyz','龙爪沟站|longzhuagou|lzg','龙镇站|longzhen|lz','隆昌站|longchang|lc','隆化站|longhuazhan|lhz','陇西站|longxi|lx','陇县站|longxian|lx','娄底站|loudi|ld','娄山关站|loushanguan|lsg','卢龙站|lulongzhan|llz','芦潮港站|luchaogangzhan|lcgz','芦沟站|lugou|lg','芦家村站|lujiacun|ljc','芦家庄站|lujiazhuang|ljz','芦溪站|luxi|lx','庐江站|lujiangzhan|ljz','庐山站|lushan|ls','炉桥站|luqiaozhan|lqz','泸阳站|zuoyang|zy','胪滨站|zuobin|zb','鲁番站|lufan|lf','鲁河站|luhe|lh','鲁山站|lushan|ls','陆川站|luchuan|lc','陆丰站|lufeng|lf','陆良站|luliang|ll','鹿道站|ludao|ld','鹿寨站|luzhai|lz','禄丰南站|lufengnan|lfn','路口铺站|lukoupu|lkp','潞城站|luchengzhan|lcz','露水河站|lushuihe|lsh','滦河沿站|luanheyan|lhy','滦河站|luanhezhan|lhz','滦平站|luanpingzhan|lpz','滦县站|luanxianzhan|lxz','罗家站|luojia|lj','罗江站|luojiang|lj','罗平站|luoping|lp','罗山站|luoshan|ls','罗源站|luoyuan|ly','螺山站|luoshan|ls','洛川站|luochuan|lc','洛门站|luomen|lm','洛阳站|luoyang|ly','洛阳东站|luoyangdong|lyd','骆驼巷站|luotuoxiang|ltx','珞璜站|zuozuo|zz','落坡岭站|luopoling|lpl','漯河站|zuohe|zh','漯河西站|zuohexi|zhx','吕梁站|lvliang|ll','绿化站|lvhua|lh','绿潭站|lvtan|lt','略阳站|lueyang|ly','麻城站|macheng|mc','麻城北站|machengbei|mcb','麻江站|majiang|mj','麻栗站|mali|ml','麻柳站|maliu|ml','麻山站|mashan|ms','麻尾站|mawei|mw','麻阳站|mayang|my','马鞍山站|maanshanzhan|masz','马村站|macun|mc','马嘎站|maga|mg','马盖图站|magaitu|mgt','马架子站|majiazi|mjz','马街站|majie|mj','马兰站|malan|ml','马莲河站|malianhe|mlh','马林站|malin|ml','马柳站|maliu|ml','马龙站|malong|ml','马桥河站|maqiaohe|mqh','马三家站|masanjia|msj','马蹄湾站|matiwan|mtw','马田墟站|matianxu|mtx','玛纳斯站|manasi|mns','麦园站|maiyuan|my','满归站|mangui|mg','满洲里站|manzhouli|mzl','漫水湾站|manshuiwan|msw','毛坝站|maoba|mb','毛坝关站|maobaguan|mbg','毛告吐站|maogaotu|mgt','茅草坪站|maocaoping|mcp','茂林站|maolin|ml','茂名站|maoming|mm','茂名东站|maomingdong|mmd','茂舍祖站|maoshezu|msz','帽儿山站|maoershan|mes','么铺站|mepu|mp','眉山站|meishan|ms','梅河口站|meihekou|mhk','梅花山站|meihuashan|mhs','梅江站|meijiang|mj','梅州站|meizhou|mz','梅子铺站|meizipu|mzp','煤田站|meitian|mt','美岱召站|meizuozhao|mzz','美兰站|meilan|ml','美溪站|meixi|mx','门达站|menda|md','蒙渡站|mengdu|md','蒙克山站|mengkeshan|mks','蒙自北站|mengzibei|mzb','猛洞河站|mengdonghe|mdh','孟家岗站|mengjiagang|mjg','孟津站|mengjin|mj','弥渡站|midu|md','米沙子站|mishazi|msz','米易站|miyi|my','米脂站|mizhi|mz','汨罗站|zuoluo|zl','汨罗东站|zuoluodong|zld','密山站|mishan|ms','密山西站|mishanxi|msx','密云站|miyunzhan|myz','绵阳站|mianyang|my','免渡河站|mianduhe|mdh','勉县站|mianxian|mx','冕宁站|mianning|mn','冕山站|mianshan|ms','渑池站|zuochi|zc','渑池南站|zuochinan|zcn','庙城站|miaocheng|mc','庙宫站|miaogong|mg','庙岭站|miaoling|ml','庙山站|miaoshan|ms','庙台子站|miaotaizi|mtz','庙阳站|miaoyang|my','庙庄站|miaozhuang|mz','庙子沟站|miaozigou|mzg','民福寺站|minfusi|mfs','民权站|minquan|mq','民族站|minzu|mz','闽清站|minqing|mq','明安站|mingan|ma','明城站|mingcheng|mc','明港东站|minggangdong|mgd','明光站|mingguangzhan|mgz','明珠站|mingzhu|mz','磨刀石站|modaoshi|mds','磨盘山站|mopanshan|mps','磨滩站|motan|mt','磨溪站|moxi|mx','磨心坡站|moxinpo|mxp','莫尔道嘎站|moerdaoga|medg','漠河站|mohe|mh','墨池坝站|mochiba|mcb','墨冲站|mochong|mc','墨玉站|moyu|my','牡丹江站|mudanjiang|mdj','木里图站|mulitu|mlt','木竹河站|muzhuhe|mzh','沐滂站|zuozuo|zz','牧原站|muyuan|my','穆棱站|muleng|ml','那曲站|naqu|nq','那玉站|nayu|ny','乃林站|nailin|nl','奈曼站|naiman|nm','南岸坝站|nananba|nab','南博山站|nanboshan|nbs','南岔站|nancha|nc','南昌站|nanchang|nc','南昌西站|nanchangxi|ncx','南陈铺站|nanchenpu|ncp','南城站|nancheng|nc','南城司站|nanchengsi|ncs','南充站|nanchong|nc','南充东站|nanchongdong|ncd','南仇站|nanchou|nc','南大庙站|nandamiao|ndm','南丹站|nandan|nd','南尔岗站|nanergang|neg','南芬站|nanfen|nf','南丰站|nanfeng|nf','南宫东站|nangongdongzhan|ngdz','南沟站|nangou|ng','南关岭站|nanguanling|ngl','南观村站|nanguancun|ngc','南河川站|nanhechuan|nhc','南华站|nanhua|nh','南峧站|nanjiao|nj','南京南站|nanjingnanzhan|njnz','南京站|nanjingzhan|njz','南靖站|nanjing|nj','南口前站|nankouqian|nkq','南口站|nankouzhan|nkz','南朗站|nanlang|nl','南陵站|nanlingzhan|nlz','南木站|nanmu|nm','南宁站|nanning|nn','南票站|nanpiao|np','南平站|nanping|np','南平南站|nanpingnan|npn','南桥站|nanqiao|nq','南山站|nanshan|ns','南台站|nantai|nt','南汤站|nantang|nt','南通站|nantongzhan|ntz','南头站|nantou|nt','南洼站|nanwa|nw','南湾子站|nanwanzi|nwz','南翔北站|nanxiangbeizhan|nxbz','南兴安站|nanxingan|nxa','南阳站|nanyang|ny','南峪站|nanyuzhan|nyz','南杂木站|nanzamu|nzm','南张村站|nanzhangcun|nzc','南召站|nanzhao|nz','闹海营站|naohaiying|nhy','讷尔克气站|zuoerkeqi|zekq','讷河站|zuohe|zh','内江站|neijiang|nj','内江南站|neijiangnan|njn','内乡站|neixiang|nx','嫩江站|nenjiang|nj','能家站|nengjia|nj','尼波站|nibo|nb','尼勒克站|nileke|nlk','尼木站|nimu|nm','尼日站|niri|nr','泥河子站|nihezi|nhz','鲇鱼山站|zuoyushan|zys','碾子山站|nianzishan|nzs','娘娘庙站|niangniangmiao|nnm','娘子关站|niangziguanzhan|nzgz','捏掌站|niezhang|nz','聂河站|niehe|nh','宁安站|ningan|na','宁波东站|ningbodongzhan|nbdz','宁波站|ningbozhan|nbz','宁德站|ningde|nd','宁国站|ningguozhan|ngz','宁海站|ninghaizhan|nhz','宁家站|ningjia|nj','宁陵县站|ninglingxian|nlx','宁明站|ningming|nm','宁武站|ningwu|nw','牛耳河站|niuerhe|neh','牛汾台站|niufentai|nft','牛家站|niujia|nj','牛家营子站|niujiayingzi|njyz','牛角山站|niujiaoshan|njs','牛坪子站|niupingzi|npz','牛心台站|niuxintai|nxt','牛庄站|niuzhuang|nz','农安站|nongan|na','暖泉站|nuanquan|nq','女儿河站|nverhe|neh','欧里站|ouli|ol','徘徊北站|paihuaibei|phb','潘家店站|panjiadian|pjd','攀枝花站|panzhihua|pzh','盘古站|pangu|pg','盘古寺站|pangusi|pgs','盘关站|panguan|pg','盘锦站|panjin|pj','盘锦北站|panjinbei|pjb','磐安镇站|pananzhen|paz','磐石站|panshi|ps','泡子站|paozi|pz','裴德站|peide|pd','彭水站|pengshui|ps','彭阳站|pengyang|py','彭泽站|pengze|pz','彭州站|pengzhou|pz','蓬安站|pengan|pa','蓬溪站|pengxi|px','邳州站|zuozhouzhan|zzz','皮口站|pikou|pk','皮山站|pishan|ps','郫县站|zuoxian|zx','郫县东站|zuoxiandong|zxd','郫县西站|zuoxianxi|zxx','椑木镇站|beimuzhen|bmz','偏店站|piandian|pd','偏岭站|pianling|pl','瓢儿屯站|piaoertun|pet','平安站|pingan|pa','平安驿站|pinganzuo|paz','平坝站|pingba|pb','平等站|pingdeng|pd','平顶庙站|pingdingmiao|pdm','平顶山站|pingdingshan|pds','平顶山西站|pingdingshanxi|pdsx','平坊站|pingfang|pf','平房站|pingfang|pf','平岗站|pinggang|pg','平关站|pingguan|pg','平果站|pingguo|pg','平河口站|pinghekou|phk','平凉站|pingliang|pl','平凉南站|pingliangnan|pln','平罗站|pingluo|pl','平南南站|pingnannan|pnn','平泉站|pingquanzhan|pqz','平山站|pingshan|ps','平社站|pingshe|ps','平台站|pingtai|pt','平田站|pingtian|pt','平旺站|pingwang|pw','平型关站|pingxingguan|pxg','平洋站|pingyang|py','平遥古城站|pingyaogucheng|pygc','平邑站|pingyi|py','平峪站|pingyu|py','平原站|pingyuan|py','平原堡站|pingyuanbao|pyb','平庄站|pingzhuang|pz','平庄南站|pingzhuangnan|pzn','坪石站|pingshi|ps','凭祥站|pingxiang|px','萍乡站|pingxiang|px','坡底村站|podicun|pdc','坡底下站|podixia|pdx','莆田站|putian|pt','蒲坝站|puba|pb','蒲城东站|puchengdong|pcd','蒲家站|pujia|pj','普安站|puan|pa','普定站|puding|pd','普洱渡站|puerdu|ped','普济站|puji|pj','普兰店站|pulandian|pld','普宁站|puning|pn','普湾站|puwan|pw','普雄站|puxiong|px','七甸站|qidian|qd','七家子站|qijiazi|qjz','七间房站|qijianfang|qjf','七里河站|qilihe|qlh','七龙星站|qilongxing|qlx','七泉湖站|qiquanhu|qqh','七苏木站|qisumu|qsm','七台河站|qitaihe|qth','七营站|qiying|qy','戚墅堰站|qishuyanzhan|qsyz','齐哈日格图站|qiharigetu|qhrgt','齐齐哈尔站|qiqihaer|qqhe','祁东站|qidong|qd','祁家堡站|qijiabao|qjb','祁门站|qimenzhan|qmz','祁县东站|qixiandong|qxd','祁阳站|qiyang|qy','岐山站|zuoshan|zs','奇峰塔站|qifengta|qft','棋盘站|qipan|qp','綦江站|zuojiang|zj','旗下营站|qixiaying|qxy','蕲春站|zuochun|zc','千河站|qianhe|qh','千阳站|qianyang|qy','迁安站|qiananzhan|qaz','前锋站|qianfeng|qf','前进镇站|qianjinzhen|qjz','前磨头站|qianmotouzhan|qmtz','前山站|qianshan|qs','前苇塘站|qianweitang|qwt','前窑站|qianyao|qy','钱家店站|qianjiadian|qjd','乾安站|qianan|qa','潜江站|qianjiang|qj','黔江站|qianjiang|qj','桥北站|qiaobei|qb','桥头站|qiaotou|qt','桥湾站|qiaowan|qw','桥西站|qiaoxi|qx','茄子溪站|qiezixi|qzx','钦州站|qinzhou|qz','钦州东站|qinzhoudong|qzd','秦家站|qinjia|qj','秦家庄站|qinjiazhuang|qjz','秦岭站|qinling|ql','沁河北站|qinhebei|qhb','沁县站|qinxian|qx','沁阳站|qinyang|qy','青城山站|qingchengshan|qcs','青岛站|qingdao|qd','青堆子站|qingduizi|qdz','青沟子站|qinggouzi|qgz','青花站|qinghua|qh','青岭子站|qinglingzi|qlz','青龙站|qinglong|ql','青龙场站|qinglongchang|qlc','青龙山站|qinglongshanzhan|qlsz','青山站|qingshan|qs','青水山站|qingshuishan|qss','青田站|qingtianzhan|qtz','青铜峡站|qingtongxia|qtx','青溪站|qingxi|qx','青县站|qingxianzhan|qxz','青州市站|qingzhoushi|qzs','清河门站|qinghemen|qhm','清河站|qinghezhan|qhz','清华园站|qinghuayuanzhan|qhyz','清涧县站|qingjianxian|qjx','清水站|qingshui|qs','清水河站|qingshuihe|qsh','清徐站|qingxu|qx','清原站|qingyuan|qy','清远站|qingyuan|qy','庆安站|qingan|qa','庆丰站|qingfeng|qf','庆盛站|qingsheng|qs','庆阳山站|qingyangshan|qys','琼海站|qionghai|qh','秋梨沟站|qiuligou|qlg','秋木庄站|qiumuzhuang|qmz','渠旧站|qujiu|qj','渠黎站|quli|ql','渠县站|quxian|qx','衢州站|zuozhouzhan|zzz','曲阜站|qufu|qf','曲阜东站|qufudong|qfd','曲家店站|qujiadian|qjd','曲靖站|qujing|qj','曲水站|qushui|qs','曲水站|qushui|qs','全椒站|quanjiaozhan|qjz','全胜站|quansheng|qs','全州南站|quanzhounan|qzn','泉沟站|quangou|qg','泉江站|quanjiang|qj','泉水站|quanshui|qs','泉州站|quanzhou|qz','确山站|queshan|qs','群力站|qunli|ql','饶平站|raoping|rp','饶阳站|raoyangzhan|ryz','绕阳河站|raoyanghe|ryh','热水站|reshui|rs','仁布站|renbu|rb','任家店站|renjiadian|rjd','任丘站|renqiuzhan|rqz','日喀则站|rikaze|rkz','日照站|rizhao|rz','荣昌站|rongchang|rc','容桂站|ronggui|rg','容县站|rongxian|rx','融安站|rongan|ra','融水站|rongshui|rs','如东站|rudongzhan|rdz','如皋站|rugaozhan|rgz','汝箕沟站|rujigou|rjg','汝阳站|ruyang|ry','汝州站|ruzhou|rz','乳山站|rushan|rs','瑞安站|ruianzhan|raz','瑞昌站|ruichang|rc','瑞金站|ruijin|rj','萨拉齐站|salaqi|slq','赛汉塔拉站|saihantala|shtl','赛乌苏站|saiwusu|sws','三把火站|sanbahuo|sbh','三道营站|sandaoying|sdy','三堆子站|sanduizi|sdz','三队站|sandui|sd','三关口站|sanguankou|sgk','三合庄站|sanhezhuang|shz','三河县站|sanhexianzhan|shxz','三花石站|sanhuashi|shs','三汇坝站|sanhuiba|shb','三汇镇站|sanhuizhen|shz','三家店站|sanjiadianzhan|sjdz','三家寨站|sanjiazhai|sjz','三家子站|sanjiazi|sjz','三间房站|sanjianfang|sjf','三江站|sanjiang|sj','三江口站|sanjiangkou|sjk','三江县站|sanjiangxian|sjx','三门峡站|sanmenxia|smx','三门峡南站|sanmenxianan|smxn','三门峡西站|sanmenxiaxi|smxx','三门县站|sanmenxianzhan|smxz','三明站|sanming|sm','三明北站|sanmingbei|smb','三平站|sanping|sp','三十家站|sanshijia|ssj','三十里堡站|sanshilibao|sslb','三水站|sanshui|ss','三台站|santai|st','三堂集站|santangjizhan|stjz','三亚站|sanya|sy','三阳川站|sanyangchuan|syc','三义井站|sanyijing|syj','三营站|sanying|sy','三元坝站|sanyuanba|syb','三原站|sanyuan|sy','三源浦站|sanyuanpu|syp','桑根达来站|sanggendalai|sgdl','桑园子站|sangyuanzi|syz','沙坝站|shaba|sb','沙城站|shachengzhan|scz','沙海站|shahai|sh','沙河口站|shahekou|shk','沙河市站|shaheshizhan|shsz','沙后所站|shahousuo|shs','沙力站|shali|sl','沙岭子站|shalingzizhan|slzz','沙马拉达站|shamalada|smld','沙坡头站|shapotou|spt','沙日乃站|sharinai|srn','沙沙坡站|shashapo|ssp','沙坨子站|shazuozi|szz','沙沱站|shazuo|sz','沙湾站|shawan|sw','沙湾县站|shawanxian|swx','沙园站|shayuan|sy','莎车站|shache|sc','厦门站|xiamen|xm','厦门北站|xiamenbei|xmb','山城镇站|shanchengzhen|scz','山丹站|shandan|sd','山海关站|shanhaiguan|shg','山河屯站|shanhetun|sht','山口站|shankou|sk','山坡东站|shanpodong|spd','山市站|shanshi|ss','山湾子站|shanwanzi|swz','山阴站|shanyin|sy','杉松岗站|shansonggang|ssg','汕头站|shantou|st','汕尾站|shanwei|sw','鄯善站|zuoshan|zs','商城站|shangcheng|sc','商洛站|shangluo|sl','商南站|shangnan|sn','商丘站|shangqiu|sq','商丘南站|shangqiunan|sqn','上安站|shangan|sa','上板城南站|shangbanchengnanzhan|sbcnz','上板城站|shangbanchengzhan|sbcz','上仓站|shangcang|sc','上店站|shangdian|sd','上高镇站|shanggaozhen|sgz','上谷站|shanggu|sg','上海虹桥站|shanghaihongqiaozhan|shhqz','上海南站|shanghainanzhan|shnz','上海西站|shanghaixizhan|shxz','上海站|shanghaizhan|shz','上杭站|shanghang|sh','上街站|shangjie|sj','上普雄站|shangpuxiong|spx','上饶站|shangrao|sr','上万站|shangwan|sw','上西铺站|shangxipu|sxp','上腰墩站|shangyaodun|syd','上虞北站|shangyubeizhan|sybz','上虞站|shangyuzhan|syz','上园站|shangyuan|sy','尚家站|shangjia|sj','尚武站|shangwu|sw','尚志站|shangzhi|sz','韶关站|shaoguan|sg','韶关东站|shaoguandong|sgd','韶山站|shaoshan|ss','邵东站|shaodong|sd','邵家堂站|shaojiatang|sjt','邵武站|shaowu|sw','邵阳站|shaoyang|sy','绍兴北站|shaoxingbeizhan|sxbz','绍兴站|shaoxingzhan|sxz','舍伯吐站|shebotu|sbt','舍力虎站|shelihu|slh','涉县站|shexianzhan|sxz','申家店站|shenjiadian|sjd','绅坊站|shenfangzhan|sfz','深井子站|shenjingzi|sjz','深圳站|shenzuo|sz','深圳北站|shenzuobei|szb','深圳东站|shenzuodong|szd','深圳坪山站|shenzuopingshan|szps','深圳西站|shenzuoxi|szx','深州站|shenzhouzhan|szz','什里店站|shilidian|sld','神池站|shenchi|sc','神木站|shenmu|sm','神树站|shenshu|ss','神头站|shentou|st','神峪河站|shenyuhe|syh','神州站|shenzhou|sz','沈家站|shenjia|sj','沈家河站|shenjiahe|sjh','沈丘站|shenqiu|sq','沈阳站|shenyang|sy','沈阳北站|shenyangbei|syb','沈阳西站|shenyangxi|syx','升昌站|shengchang|sc','圣浪站|shenglang|sl','师庄站|shizhuang|sz','师宗站|shizong|sz','狮子营站|shiziying|szy','施秉站|shibing|sb','施家嘴站|shijiazui|sjz','十八台站|shibatai|sbt','十渡站|shiduzhan|sdz','十家子站|shijiazi|sjz','十里坪站|shiliping|slp','十堰站|shiyan|sy','石坝站|shiba|sb','石板滩站|shibantan|sbt','石场站|shichang|sc','石城站|shicheng|sc','石河子站|shihezi|shz','石湖站|shihu|sh','石家站|shijia|sj','石家庄北站|shijiazhuangbeizhan|sjzbz','石家庄站|shijiazhuangzhan|sjzz','石郎庄站|shilangzhuang|slz','石林站|shilin|sl','石林南站|shilinnan|sln','石磷站|shilin|sl','石岭站|shiling|sl','石门村站|shimencun|smc','石门坎站|shimenkan|smk','石门县站|shimenxian|smx','石门县北站|shimenxianbei|smxb','石门子站|shimenzi|smz','石庙沟站|shimiaogou|smg','石脑站|shinao|sn','石桥镇站|shiqiaozhen|sqz','石桥子站|shiqiaozi|sqz','石泉县站|shiquanxian|sqx','石人城站|shirencheng|src','石山站|shishan|ss','石头站|shitou|st','石峡子站|shixiazi|sxz','石岘站|shizuo|sz','石长站|shichang|sc','石柱槽站|shizhucao|szc','石柱县站|shizhuxian|szx','石嘴山站|shizuishan|szs','史家铺站|shijiapu|sjp','寿阳站|shouyang|sy','舒城站|shuchengzhan|scz','舒兰站|shulan|sl','疏勒站|shule|sl','疏勒河站|shulehe|slh','蜀河站|shuhe|sh','沭阳站|zuoyangzhan|zyz','双安屯站|shuangantun|sat','双城堡站|shuangchengbao|scb','双丰站|shuangfeng|sf','双峰寺站|shuangfengsi|sfs','双凤驿站|shuangfengzuo|sfz','双福站|shuangfu|sf','双河镇站|shuanghezhen|shz','双龙山站|shuanglongshan|sls','双牌站|shuangpai|sp','双泡子站|shuangpaozi|spz','双石桥站|shuangshiqiao|ssq','双水站|shuangshui|ss','双鸭山站|shuangyashan|sys','双羊店站|shuangyangdian|syd','双子河站|shuangzihe|szh','水车湾站|shuichewan|scw','水城站|shuicheng|sc','水地站|shuidi|sd','水富站|shuifu|sf','水沟站|shuigou|sg','水花站|shuihua|sh','水家湖站|shuijiahuzhan|sjhz','水礼站|shuili|sl','水泉站|shuiquan|sq','水洋站|shuiyang|sy','水源站|shuiyuan|sy','顺昌站|shunchang|sc','顺德站|shunde|sd','顺德学院站|shundexueyuan|sdxy','顺河场站|shunhechang|shc','顺义站|shunyizhan|syz','朔州站|shuozhou|sz','司家岭站|sijialing|sjl','四道沟站|sidaogou|sdg','四道湾站|sidaowan|sdw','四方台站|sifangtai|sft','四分地站|sifendi|sfd','四分滩站|sifentan|sft','四合站|sihe|sh','四合永站|siheyong|shy','四家子站|sijiazi|sjz','四马架站|simajia|smj','四平站|siping|sp','四平东站|sipingdong|spd','四台子站|sitaizi|stz','泗洪站|zuohongzhan|zhz','泗水站|zuoshui|zs','泗县站|zuoxianzhan|zxz','泗阳站|zuoyangzhan|zyz','松坝站|songba|sb','松河站|songhe|sh','松江河站|songjianghe|sjh','松江南站|songjiangnanzhan|sjnz','松江站|songjiangzhan|sjz','松江镇站|songjiangzhen|sjz','松坎站|songkan|sk','松岭站|songling|sl','松青站|songqing|sq','松树站|songshu|ss','松树林站|songshulin|ssl','松树台站|songshutai|sst','松树镇站|songshuzhen|ssz','松桃站|songtao|st','松原站|songyuan|sy','松滋站|songzi|sz','宋站|song|s','苏坂站|suzuo|sz','苏北站|subei|sb','苏集站|suji|sj','苏家坡站|sujiapo|sjp','苏家屯站|sujiatun|sjt','苏雄站|suxiong|sx','苏州北站|suzhoubeizhan|szbz','苏州新区站|suzhouxinquzhan|szxqz','苏州园区站|suzhouyuanquzhan|szyqz','苏州站|suzhouzhan|szz','肃宁站|suningzhan|snz','算王庄站|suanwangzhuang|swz','绥德站|suide|sd','绥芬河站|suifenhe|sfh','绥化站|suihua|sh','绥棱站|suileng|sl','绥西站|suixi|sx','绥阳站|suiyang|sy','绥中站|suizhong|sz','绥中北站|suizhongbei|szb','随州站|suizhou|sz','遂宁站|suining|sn','遂平站|suiping|sp','遂溪站|suixi|sx','孙家站|sunjia|sj','孙吴站|sunwu|sw','孙镇站|sunzhen|sz','索伦站|suolun|sl','索图罕站|suotuhan|sth','塔尔根站|taergen|teg','塔尔气站|taerqi|teq','塔哈站|taha|th','塔河站|tahe|th','塔虎城站|tahucheng|thc','塔前站|taqian|tq','塔山站|tashan|ts','塔崖驿站|tayazuo|tyz','塔源站|tayuan|ty','台安站|taian|ta','台集屯站|taijitun|tjt','台前站|taiqian|tq','台州站|taizhouzhan|tzz','苔青站|taiqing|tq','太白站|taibai|tb','太谷西站|taiguxi|tgx','太和站|taihe|th','太湖站|taihuzhan|thz','太姥山站|tailaoshan|tls','太岭站|tailing|tl','太平川站|taipingchuan|tpc','太平岭站|taipingling|tpl','太平镇站|taipingzhen|tpz','太平庄站|taipingzhuang|tpz','太阳沟站|taiyanggou|tyg','太阳山站|taiyangshan|tys','太阳升站|taiyangsheng|tys','太原站|taiyuan|ty','太原北站|taiyuanbei|tyb','太原东站|taiyuandong|tyd','太原南站|taiyuannan|tyn','太子河站|taizihe|tzh','泰安站|taian|ta','泰和站|taihe|th','泰康站|taikang|tk','泰来站|tailai|tl','泰宁站|taining|tn','泰山站|taishan|ts','泰州站|taizhouzhan|tzz','滩头站|tantou|tt','郯城站|zuocheng|zc','覃家坝站|zuojiaba|zjb','谭家井站|tanjiajing|tjj','坦甸站|tandian|td','汤池站|tangchi|tc','汤河站|tanghe|th','汤山城站|tangshancheng|tsc','汤头沟站|tangtougou|ttg','汤旺河站|tangwanghe|twh','汤逊湖站|tangxunhu|txh','汤阴站|tangyin|ty','汤原站|tangyuan|ty','唐官屯站|tangguantun|tgt','唐河站|tanghe|th','唐家站|tangjia|tj','唐家湾站|tangjiawan|tjw','唐山北站|tangshanbeizhan|tsbz','唐山站|tangshanzhan|tsz','塘豹站|tangbao|tb','塘沽站|tangguzhan|tgz','洮南站|zuonan|zn','桃村站|taocun|tc','桃山站|taoshan|ts','桃映站|taoying|ty','桃园站|taoyuan|ty','陶卜齐站|taoboqi|tbq','陶家沟站|taojiagou|tjg','陶家屯站|taojiatun|tjt','陶赖昭站|taolaizhao|tlz','陶思浩站|taosihao|tsh','滕州站|zuozhou|zz','滕州东站|zuozhoudong|zzd','藤县站|tengxian|tx','天德泉站|tiandequan|tdq','天岗站|tiangang|tg','天津南站|tianjinnanzhan|tjnz','天津西站|tianjinxizhan|tjxz','天津站|tianjinzhan|tjz','天龙站|tianlong|tl','天门站|tianmen|tm','天门南站|tianmennan|tmn','天桥站|tianqiao|tq','天桥沟站|tianqiaogou|tqg','天桥岭站|tianqiaoling|tql','天水站|tianshui|ts','天西站|tianxi|tx','天义站|tianyi|ty','天柱山站|tianzhushanzhan|tzsz','天祝站|tianzhu|tz','田东站|tiandong|td','田家沟站|tianjiagou|tjg','田梁子站|tianliangzi|tlz','田林站|tianlin|tl','田师府站|tianshifu|tsf','田阳站|tianyang|ty','铁佛寺站|tiefosi|tfs','铁口站|tiekou|tk','铁力站|tieli|tl','铁岭站|tieling|tl','铁岭西站|tielingxi|tlx','铁西站|tiexi|tx','亭亮站|tingliang|tl','通安驿站|tonganzuo|taz','通北站|tongbei|tb','通道站|tongdao|td','通沟站|tonggou|tg','通海站|tonghai|th','通化站|tonghua|th','通化县站|tonghuaxian|thx','通辽站|tongliao|tl','通辽北站|tongliaobei|tlb','通天屯站|tongtiantun|ttt','通途站|tongtu|tt','通远堡站|tongyuanbao|tyb','通州西站|tongzhouxizhan|tzxz','同心站|tongxin|tx','佟家站|zuojia|zj','桐柏站|tongbai|tb','桐城站|tongchengzhan|tcz','桐木寨站|tongmuzhai|tmz','桐乡站|tongxiangzhan|txz','桐子林站|tongzilin|tzl','桐梓站|tongzuo|tz','铜佛寺站|tongfosi|tfs','铜鼓溪站|tongguxi|tgx','铜罐驿站|tongguanzuo|tgz','铜陵北站|tonglingbeizhan|tlbz','铜陵站|tonglingzhan|tlz','铜仁站|tongren|tr','潼关站|zuoguan|zg','潼南站|zuonan|zn','统军庄站|tongjunzhuang|tjz','头道桥站|toudaoqiao|tdq','图里河站|tulihe|tlh','图们站|tumen|tm','图强站|tuqiang|tq','土地堂东站|tuditangdong|tdtd','土贵乌拉站|tuguiwula|tgwl','土门子站|tumenzi|tmz','土们岭站|tumenling|tml','土牧尔台站|tumuertai|tmet','土桥子站|tuqiaozi|tqz','土溪站|tuxi|tx','吐哈站|tuha|th','吐列毛杜站|tuliemaodu|tlmd','吐鲁番站|tulufan|tlf','吐鲁番北站|tulufanbei|tlfb','团结站|tuanjie|tj','团结村站|tuanjiecun|tjc','团林站|tuanlin|tl','团山站|tuanshan|ts','驼腰岭站|tuoyaoling|tyl','瓦房站|wafang|wf','瓦房店站|wafangdian|wfd','瓦房店西站|wafangdianxi|wfdx','瓦拉干站|walagan|wlg','瓦屋山站|wawushanzhan|wwsz','瓦窑坝站|wayaoba|wyb','瓦窑田站|wayaotian|wyt','瓦祖站|wazu|wz','歪头山站|waitoushan|wts','完工站|wangong|wg','万发屯站|wanfatun|wft','万家屯站|wanjiatun|wjt','万乐站|wanle|wl','万隆站|wanlong|wl','万年站|wannian|wn','万宁站|wanning|wn','万源站|wanyuan|wy','万州站|wanzhou|wz','汪清站|wangqing|wq','王安镇站|wanganzhen|waz','王场站|wangchang|wc','王府站|wangfu|wf','王岗站|wanggang|wg','王家湾站|wangjiawan|wjw','王家营西站|wangjiayingxi|wjyx','王团庄站|wangtuanzhuang|wtz','王杨站|wangyang|wy','王兆屯站|wangzhaotun|wzt','王庄站|wangzhuang|wz','网户站|wanghu|wh','旺苍站|wangcang|wc','望江站|wangjiang|wj','威海站|weihai|wh','威岭站|weiling|wl','威宁站|weining|wn','威箐站|weizuo|wz','威舍站|weishe|ws','微子镇站|weizizhen|wzz','韦庄站|weizhuang|wz','潍坊站|weifang|wf','苇河站|weihe|wh','苇子沟站|weizigou|wzg','卫东站|weidong|wd','卫辉站|weihui|wh','卫星站|weixing|wx','渭津站|weijin|wj','渭南站|weinan|wn','渭南北站|weinanbei|wnb','渭南南站|weinannan|wnn','渭南镇站|weinanzhen|wnz','魏杖子站|weizhangzi|wzz','温春站|wenchun|wc','温都和硕站|wenduheshuo|wdhs','温岭站|wenlingzhan|wlz','温泉寺站|wenquansi|wqs','温州南站|wenzhounanzhan|wznz','温州站|wenzhouzhan|wzz','文昌站|wenchang|wc','文登站|wendeng|wd','文地站|wendi|wd','文水站|wenshui|ws','文庄村站|wenzhuangcun|wzc','闻喜站|wenxi|wx','闻喜西站|wenxixi|wxx','倭肯站|zuoken|zk','涡阳站|woyangzhan|wyz','沃皮站|wopi|wp','卧里屯站|wolitun|wlt','卧牛河站|woniuhe|wnh','乌尔旗汗站|wuerqihan|weqh','乌固诺尔站|wugunuoer|wgne','乌海北站|wuhaibei|whb','乌拉山站|wulashan|wls','乌拉特前旗站|wulateqianqi|wltqq','乌兰站|wulan|wl','乌兰哈达站|wulanhada|wlhd','乌兰浩特站|wulanhaote|wlht','乌兰胡同站|wulanhutong|wlht','乌兰花站|wulanhua|wlh','乌兰丘站|wulanqiu|wlq','乌龙泉南站|wulongquannan|wlqn','乌鲁布铁站|wulubutie|wlbt','乌鲁木齐站|wulumuqi|wlmq','乌鲁木齐南站|wulumuqinan|wlmqn','乌奴耳站|wunuer|wne','乌斯土站|wusitu|wst','乌西站|wuxi|wx','乌伊岭站|wuyiling|wyl','无为站|wuweizhan|wwz','无锡东站|wuxidongzhan|wxdz','无锡新区站|wuxixinquzhan|wxxqz','无锡站|wuxizhan|wxz','芜湖站|wuhuzhan|whz','吴堡站|wubao|wb','吴场站|wuchang|wc','吴官田站|wuguantian|wgt','吴家川站|wujiachuan|wjc','吴家屯站|wujiatun|wjt','吴桥站|wuqiaozhan|wqz','梧州站|wuzhou|wz','梧州南站|wuzhounan|wzn','五叉沟站|wuchagou|wcg','五常站|wuchang|wc','五大连池站|wudalianchi|wdlc','五道沟站|wudaogou|wdg','五道河站|wudaohe|wdh','五凤溪站|wufengxi|wfx','五家站|wujia|wj','五棵树站|wukeshu|wks','五莲站|wulian|wl','五林站|wulin|wl','五龙站|wulong|wl','五龙背站|wulongbei|wlb','五女山站|wunvshan|wns','五十家子站|wushijiazi|wsjz','五台山站|wutaishan|wts','五五站|wuwu|ww','五星站|wuxing|wx','五营站|wuying|wy','五寨站|wuzhai|wz','午汲站|wuji|wj','武安站|wuanzhan|waz','武昌站|wuchang|wc','武当山站|wudangshan|wds','武功站|wugong|wg','武汉站|wuhan|wh','武隆站|wulong|wl','武清站|wuqingzhan|wqz','武山站|wushan|ws','武威站|wuwei|ww','武威南站|wuweinan|wwn','武乡站|wuxiang|wx','武穴站|wuxue|wx','武夷山站|wuyishan|wys','武义站|wuyizhan|wyz','西安站|xian|xa','西安南站|xiannan|xan','西昌站|xichang|xc','西昌北站|xichangbei|xcb','西昌南站|xichangnan|xcn','西大庙站|xidamiao|xdm','西斗铺站|xidoupu|xdp','西丰站|xifeng|xf','西岗子站|xigangzi|xgz','西沟站|xigou|xg','西沟站|xigou|xg','西固城站|xigucheng|xgc','西胡尔清站|xihuerqing|xheq','西江站|xijiang|xj','西街口站|xijiekou|xjk','西口站|xikou|xk','西里站|xili|xl','西林站|xilin|xl','西岭口站|xilingkou|xlk','西刘站|xiliu|xl','西柳站|xiliu|xl','西六方站|xiliufang|xlf','西麻山站|ximashan|xms','西宁站|xining|xn','西宁西站|xiningxi|xnx','西平站|xiping|xp','西桐古站|xitonggu|xtg','西武匠站|xiwujiang|xwj','西峡站|xixia|xx','西乡站|xixiang|xx','西阳岔站|xiyangcha|xyc','西阳村站|xiyangcun|xyc','西哲里木站|xizhelimu|xzlm','汐子站|xizi|xz','息烽站|xifeng|xf','息县站|xixian|xx','浠水站|zuoshui|zs','犀浦站|xipu|xp','犀浦东站|xipudong|xpd','锡林浩特站|xilinhaote|xlht','锡林呼都嘎站|xilinhuduga|xlhdg','锡铁山站|xitieshan|xts','溪洞站|xidong|xd','歙县北站|zuoxianbeizhan|zxbz','歙县站|zuoxianzhan|zxz','喜德站|xide|xd','峡江站|xiajiang|xj','霞浦站|xiapu|xp','下板城站|xiabanchengzhan|xbcz','下仓站|xiacang|xc','下城子站|xiachengzi|xcz','下花园站|xiahuayuanzhan|xhyz','下坑子站|xiakengzi|xkz','下马塘站|xiamatang|xmt','下普雄站|xiapuxiong|xpx','下社站|xiashe|xs','下台子站|xiataizizhan|xtzz','夏坝站|xiaba|xb','夏官营站|xiaguanying|xgy','夏家河站|xiajiahe|xjh','夏拉哈马站|xialahama|xlhm','夏石站|xiashi|xs','夏邑县站|xiayixianzhan|xyxz','仙林站|xianlinzhan|xlz','仙人桥站|xianrenqiao|xrq','仙桃西站|xiantaoxi|xtx','鲜滩站|xiantan|xt','咸宁站|xianning|xn','咸宁北站|xianningbei|xnb','咸宁东站|xianningdong|xnd','咸宁南站|xianningnan|xnn','咸阳站|xianyang|xy','咸阳秦都站|xianyangqindu|xyqd','香坊站|xiangfang|xf','香兰站|xianglan|xl','湘潭站|xiangtan|xt','湘乡站|xiangxiang|xx','襄汾站|xiangfen|xf','襄汾西站|xiangfenxi|xfx','襄河站|xianghe|xh','襄阳站|xiangyang|xy','襄阳东站|xiangyangdong|xyd','襄垣站|xiangyuan|xy','祥云站|xiangyun|xy','向塘站|xiangtang|xt','向阳站|xiangyang|xy','向阳川站|xiangyangchuan|xyc','向阳村站|xiangyangcun|xyc','项城站|xiangcheng|xc','小白站|xiaobai|xb','小北站|xiaobei|xb','小村站|xiaocun|xc','小得江站|xiaodejiang|xdj','小东站|xiaodong|xd','小儿坪站|xiaoerping|xep','小高站|xiaogao|xg','小姑家站|xiaogujia|xgj','小关溪站|xiaoguanxi|xgx','小合隆站|xiaohelong|xhl','小河沿站|xiaoheyan|xhy','小河镇站|xiaohezhen|xhz','小榄站|xiaozuo|xz','小岭站|xiaoling|xl','小南海站|xiaonanhai|xnh','小南垭站|xiaonanzuo|xnz','小平房站|xiaopingfang|xpf','小山站|xiaoshan|xs','小哨站|xiaoshao|xs','小市站|xiaoshi|xs','小寺沟站|xiaosigouzhan|xsgz','小宋站|xiaosong|xs','小西庄站|xiaoxizhuang|xxz','小新街站|xiaoxinjie|xxj','小扬气站|xiaoyangqi|xyq','小榆树站|xiaoyushu|xys','小雨谷站|xiaoyugu|xyg','小月旧站|xiaoyuejiu|xyj','小舟站|xiaozhou|xz','孝感站|xiaogan|xg','孝感北站|xiaoganbei|xgb','协荣站|xierong|xr','斜河涧站|xiehejian|xhj','谢家镇站|xiejiazhen|xjz','辛集站|xinjizhan|xjz','忻州站|xinzhou|xz','新安站|xinan|xa','新安县站|xinanxian|xax','新安庄站|xinanzhuang|xaz','新场站|xinchang|xc','新绰源站|xinchuoyuan|xcy','新城子站|xinchengzi|xcz','新干站|xingan|xg','新高峰站|xingaofeng|xgf','新固镇站|xinguzhen|xgz','新寒岭站|xinhanling|xhl','新和站|xinhe|xh','新华站|xinhua|xh','新华屯站|xinhuatun|xht','新化站|xinhua|xh','新晃站|xinhuang|xh','新会站|xinhui|xh','新家站|xinjia|xj','新江站|xinjiang|xj','新绛站|xinzuo|xz','新津站|xinjin|xj','新开站|xinkai|xk','新李站|xinli|xl','新立屯站|xinlitun|xlt','新立镇站|xinlizhen|xlz','新凉站|xinliang|xl','新林站|xinlin|xl','新岭站|xinling|xl','新民站|xinmin|xm','新平坝站|xinpingba|xpb','新坪田站|xinpingtian|xpt','新坡站|xinpo|xp','新桥站|xinqiaozhan|xqz','新青站|xinqing|xq','新邱站|xinqiu|xq','新松浦站|xinsongpu|xsp','新天站|xintian|xt','新铁村站|xintiecun|xtc','新窝铺站|xinwopu|xwp','新县站|xinxian|xx','新乡站|xinxiang|xx','新乡东站|xinxiangdong|xxd','新香坊站|xinxiangfang|xxf','新彦站|xinyan|xy','新阳镇站|xinyangzhen|xyz','新沂站|xinyizhan|xyz','新友谊站|xinyouyi|xyy','新余站|xinyu|xy','新杖子站|xinzhangzizhan|xzzz','新帐房站|xinzhangfang|xzf','新肇站|xinzhao|xz','信丰站|xinfeng|xf','信阳站|xinyang|xy','信阳东站|xinyangdong|xyd','信宜站|xinyi|xy','星朗站|xinglang|xl','星耀站|xingyao|xy','邢台东站|xingtaidongzhan|xtdz','邢台站|xingtaizhan|xtz','兴安站|xingan|xa','兴安北站|xinganbei|xab','兴安岭站|xinganling|xal','兴城站|xingcheng|xc','兴福站|xingfu|xf','兴国站|xingguo|xg','兴和站|xinghe|xh','兴和西站|xinghexi|xhx','兴凯站|xingkai|xk','兴莲站|xinglian|xl','兴隆店站|xinglongdian|xld','兴隆山站|xinglongshan|xls','兴隆县站|xinglongxianzhan|xlxz','兴隆镇站|xinglongzhen|xlz','兴宁站|xingning|xn','兴平站|xingping|xp','兴泉堡站|xingquanbao|xqb','兴无站|xingwu|xw','兴业站|xingye|xy','兴义站|xingyi|xy','杏树站|xingshu|xs','杏树屯站|xingshutun|xst','熊岳城站|xiongyuecheng|xyc','修武站|xiuwu|xw','宿松站|susongzhan|ssz','宿州东站|suzhoudongzhan|szdz','宿州站|suzhouzhan|szz','秀山站|xiushan|xs','绣峰站|xiufeng|xf','徐家站|xujia|xj','徐家坪站|xujiaping|xjp','徐水站|xushuizhan|xsz','徐闻站|xuwen|xw','徐州东站|xuzhoudongzhan|xzdz','徐州站|xuzhouzhan|xzz','许昌站|xuchang|xc','许昌东站|xuchangdong|xcd','许家台站|xujiatai|xjt','许家屯站|xujiatun|xjt','许三湾站|xusanwan|xsw','溆浦站|zuopu|zp','轩岗站|xuangang|xg','宣城站|xuanchengzhan|xcz','宣汉站|xuanhan|xh','宣和站|xuanhe|xh','宣化站|xuanhuazhan|xhz','宣家沟站|xuanjiagou|xjg','宣威站|xuanwei|xw','悬钟站|xuanzhong|xz','薛家站|xuejia|xj','学庄站|xuezhuang|xz','旬阳站|xunyang|xy','旬阳北站|xunyangbei|xyb','牙克石站|yakeshi|yks','牙拉盖图站|yalagaitu|ylgt','牙屯堡站|yatunbao|ytb','衙门庙站|yamenmiao|ymm','雅河站|yahe|yh','雅鲁站|yalu|yl','亚布力站|yabuli|ybl','亚布力南站|yabulinan|ybln','亚复站|yafu|yf','亚沟站|yagou|yg','亚河站|yahe|yh','亚龙湾站|yalongwan|ylw','烟台站|yantai|yt','烟筒山站|yantongshan|yts','烟筒屯站|yantongtun|ytt','焉耆站|yanzuo|yz','延安站|yanan|ya','延吉站|yanji|yj','延津站|yanjin|yj','延庆站|yanqingzhan|yqz','岩会站|yanhuizhan|yhz','岩山站|yanshan|ys','炎陵站|yanling|yl','沿河城站|yanhecheng|yhc','盐城站|yanchengzhan|ycz','盐池站|yanchi|yc','盐津站|yanjin|yj','盐津北站|yanjinbei|yjb','阎家站|yanjia|yj','阎良站|yanliang|yl','兖州站|zuozhou|zz','偃师站|zuoshi|zs','砚川站|yanchuan|yc','晏城站|zuocheng|zc','晏家坝站|zuojiaba|zjb','雁翅站|yanchi|yc','雁荡山站|yandangshanzhan|ydsz','雁石站|yanshi|ys','燕岗站|yangang|yg','燕家庄站|yanjiazhuang|yjz','燕郊站|yanjiaozhan|yjz','燕山站|yanshanzhan|ysz','燕子砭站|yanzizuo|yzz','秧草地站|yangcaodi|ycd','扬州站|yangzhouzhan|yzz','羊堡站|yangbao|yb','羊草站|yangcao|yc','羊场站|yangchang|yc','羊臼河站|yangjiuhe|yjh','羊坪站|yangping|yp','羊圈子站|yangquanzi|yqz','羊尾哨站|yangweishao|yws','羊者窝站|yangzhewo|yzw','阳城站|yangcheng|yc','阳澄湖站|yangchenghuzhan|ychz','阳春站|yangchun|yc','阳方口站|yangfangkou|yfk','阳谷站|yanggu|yg','阳平关站|yangpingguan|ypg','阳曲站|yangqu|yq','阳泉北站|yangquanbeizhan|yqbz','阳泉曲站|yangquanqu|yqq','阳泉站|yangquanzhan|yqz','阳新站|yangxin|yx','阳邑站|yangyizhan|yyz','杨岗站|yanggang|yg','杨各庄站|yanggezhuang|ygz','杨家站|yangjia|yj','杨家店站|yangjiadian|yjd','杨家营站|yangjiaying|yjy','杨林站|yanglin|yl','杨陵站|yangling|yl','杨陵南站|yanglingnan|yln','杨柳青站|yangliuqingzhan|ylqz','杨木站|yangmu|ym','杨木山站|yangmushan|yms','杨树岭站|yangshuling|ysl','杨树湾站|yangshuwan|ysw','杨漩站|yangzuo|yz','杨杖子站|yangzhangzi|yzz','洋河站|yanghezhan|yhz','养马河站|yangmahe|ymh','腰栈站|yaozhan|yz','姚安站|yaoan|ya','姚家站|yaojia|yj','姚千户屯站|yaoqianhutun|yqht','窑上站|yaoshang|ys','遥林站|yaolin|yl','野三坡站|yesanpozhan|yspz','叶柏寿站|yebaishou|ybs','叶城站|yecheng|yc','一步滩站|yibutan|ybt','一间堡站|yijianbao|yjb','一面坡站|yimianpo|ymp','一面山站|yimianshan|yms','伊春站|yichun|yc','伊尔施站|yiershi|yes','伊拉哈站|yilaha|ylh','伊林站|yilin|yl','伊宁站|yining|yn','伊宁东站|yiningdong|ynd','伊图里河站|yitulihe|ytlh','依安站|yian|ya','沂南站|yinan|yn','沂水站|yishui|ys','宜宾站|yibin|yb','宜宾南站|yibinnan|ybn','宜昌东站|yichangdong|ycd','宜城站|yicheng|yc','宜良北站|yiliangbei|ylb','宜耐站|yinai|yn','宜兴站|yixingzhan|yxz','宜州站|yizhou|yz','彝良站|yiliang|yl','彝良南站|yiliangnan|yln','迤那站|zuona|zn','迤资站|zuozi|zz','弋阳站|zuoyang|zy','义马站|yima|ym','义县站|yixian|yx','益阳站|yiyang|yy','银川站|yinchuan|yc','银浪站|yinlang|yl','银镇站|yinzhen|yz','尹地站|yindi|yd','饮马峡站|yinmaxia|ymx','英德站|yingde|yd','英德西站|yingdexi|ydx','英额门站|yingemen|yem','英吉沙站|yingjisha|yjs','鹰手营子站|yingshouyingzizhan|ysyzz','鹰潭站|yingtan|yt','迎宾路站|yingbinlu|ybl','迎春站|yingchun|yc','迎水桥站|yingshuiqiao|ysq','迎祥街站|yingxiangjie|yxj','营北站|yingbei|yb','营城站|yingcheng|yc','营城子站|yingchengzi|ycz','营东站|yingdong|yd','营街站|yingjie|yj','营口站|yingkou|yk','营口东站|yingkoudong|ykd','营盘水站|yingpanshui|yps','营盘湾站|yingpanwan|ypw','营山站|yingshan|ys','影壁山站|yingbishan|ybs','应城站|yingcheng|yc','应县站|yingxian|yx','硬石岭站|yingshiling|ysl','永安站|yongan|ya','永安乡站|yonganxiang|yax','永川站|yongchuan|yc','永登站|yongdeng|yd','永甸站|yongdian|yd','永定站|yongding|yd','永丰营站|yongfengying|yfy','永福南站|yongfunan|yfn','永和站|yonghe|yh','永济站|yongji|yj','永济北站|yongjibei|yjb','永嘉堡站|yongjiabao|yjb','永嘉站|yongjiazhan|yjz','永康站|yongkangzhan|ykz','永郎站|yonglang|yl','永乐店站|yongledian|yld','永宁站|yongning|yn','永胜站|yongsheng|ys','永泰站|yongtai|yt','永修站|yongxiu|yx','永州站|yongzhou|yz','攸县站|zuoxian|zx','尤溪站|youxi|yx','油溪站|youxi|yx','友好站|youhao|yh','酉阳站|youyang|yy','于都站|yudu|yd','余杭站|yuhangzhan|yhz','余江站|yujiang|yj','余粮堡站|yuliangbao|ylb','余姚北站|yuyaobeizhan|yybz','余姚站|yuyaozhan|yyz','鱼儿沟站|yuergou|yeg','鱼泉站|yuquan|yq','俞冲站|yuchong|yc','榆次站|yuci|yc','榆林站|yulin|yl','榆社站|yushe|ys','榆树站|yushu|ys','榆树川站|yushuchuan|ysc','榆树沟站|yushugou|ysg','榆树屯站|yushutun|yst','虞城县站|yuchengxianzhan|ycxz','宇宙地站|yuzhoudi|yzd','雨格站|yuge|yg','禹城站|yucheng|yc','玉林站|yulin|yl','玉门站|yumen|ym','玉屏站|yuping|yp','玉泉站|yuquan|yq','玉山站|yushan|ys','玉舍站|yushe|ys','玉石站|yushi|ys','玉田县站|yutianxianzhan|ytxz','玉溪站|yuxi|yx','郁山站|yushan|ys','育英站|yuying|yy','鸳鸯镇站|yuanyangzhen|yyz','元坝站|yuanba|yb','元宝山站|yuanbaoshan|ybs','元龙站|yuanlong|yl','元谋站|yuanmou|ym','元氏站|yuanshizhan|ysz','元田坝站|yuantianba|ytb','园墩站|yuandun|yd','袁家堡站|yuanjiabao|yjb','原林站|yuanlin|yl','原平站|yuanping|yp','源迁站|yuanqian|yq','源潭站|yuantan|yt','月华站|yuehua|yh','月亮田站|yueliangtian|ylt','月山站|yueshan|ys','岳池站|yuechi|yc','岳家井站|yuejiajing|yjj','岳阳站|yueyang|yy','岳阳东站|yueyangdong|yyd','越西站|yuexi|yx','云彩岭站|yuncailing|ycl','云端站|yunduan|yd','云居寺站|yunjusi|yjs','云梦站|yunmeng|ym','云田乡站|yuntianxiang|ytx','云霄站|yunxiao|yx','运城站|yuncheng|yc','运城北站|yunchengbei|ycb','郓城站|zuocheng|zc','枣强站|zaoqiangzhan|zqz','枣阳站|zaoyang|zy','枣庄站|zaozhuang|zz','枣庄西站|zaozhuangxi|zzx','枣子林站|zaozilin|zzl','泽普站|zepu|zp','缯溪河站|zuoxihe|zxh','扎赉诺尔站|zhazuonuoer|zzne','扎赉诺尔西站|zhazuonuoerxi|zznex','扎兰屯站|zhalantun|zlt','扎鲁特站|zhalute|zlt','扎罗木得站|zhaluomude|zlmd','扎音河站|zhayinhe|zyh','柞水站|zuoshui|zs','湛江站|zhanjiang|zj','湛江西站|zhanjiangxi|zjx','张百湾站|zhangbaiwan|zbw','张家堡站|zhangjiabao|zjb','张家船站|zhangjiachuan|zjc','张家祠站|zhangjiazuo|zjz','张家界站|zhangjiajie|zjj','张家口南站|zhangjiakounanzhan|zjknz','张家口站|zhangjiakouzhan|zjkz','张桥站|zhangqiao|zq','张三营站|zhangsanying|zsy','张台子站|zhangtaizi|ztz','张维屯站|zhangweitun|zwt','张辛站|zhangxin|zx','张掖站|zhangye|zy','章党站|zhangdang|zd','章古台站|zhanggutai|zgt','章丘站|zhangqiu|zq','彰武站|zhangwu|zw','漳平站|zhangping|zp','漳浦站|zhangpu|zp','漳州站|zhangzhou|zz','漳州东站|zhangzhoudong|zzd','樟木头站|zhangmutou|zmt','樟树站|zhangshu|zs','樟树东站|zhangshudong|zsd','长城站|changcheng|cc','长冲站|changchong|cc','长春站|changchun|cc','长春东站|changchundong|ccd','长春南站|changchunnan|ccn','长春西站|changchunxi|ccx','长甸站|changdian|cd','长发屯站|changfatun|cft','长葛站|changge|cg','长河坝站|changheba|chb','长河碥站|changhezuo|chz','长临河站|changlinhezhan|clhz','长岭子站|changlingzi|clz','长农站|changnong|cn','长坡岭站|changpoling|cpl','长沙站|changsha|cs','长沙埂站|changshageng|csg','长沙南站|changshanan|csn','长山堡站|changshanbao|csb','长山屯站|changshantun|cst','长寿站|changshou|cs','长寿北站|changshoubei|csb','长潭沟站|changtangou|ctg','长汀站|changting|ct','长汀镇站|changtingzhen|ctz','长兴南站|changxingnanzhan|cxnz','长兴站|changxingzhan|cxz','长阳站|changyang|cy','长缨站|changying|cy','长垣站|changyuan|cy','长征站|changzheng|cz','长治站|changzhi|cz','长治北站|changzhibei|czb','长子站|changzi|cz','招柏站|zhaobai|zb','昭通站|zhaotong|zt','昭通北站|zhaotongbei|ztb','昭通南站|zhaotongnan|ztn','诏安站|zuoan|za','赵城站|zhaocheng|zc','赵光站|zhaoguang|zg','赵家站|zhaojia|zj','赵庄站|zhaozhuang|zz','照福铺站|zhaofupu|zfp','肇东站|zhaodong|zd','肇庆站|zhaoqing|zq','哲里木站|zhelimu|zlm','枕头峰站|zhentoufeng|ztf','轸溪站|zuoxi|zx','镇安站|zhenan|za','镇城底站|zhenchengdi|zcd','镇江南站|zhenjiangnanzhan|zjnz','镇江站|zhenjiangzhan|zjz','镇赉站|zhenzuo|zz','镇平站|zhenping|zp','镇远站|zhenyuan|zy','镇紫街站|zhenzijie|zzj','正定机场站|zhengdingjichangzhan|zdjcz','正镶白旗站|zhengxiangbaiqi|zxbq','郑家堡站|zhengjiabao|zjb','郑家屯站|zhengjiatun|zjt','郑州站|zhengzhou|zz','郑州东站|zhengzhoudong|zzd','枝城站|zhicheng|zc','枝江北站|zhijiangbei|zjb','织金站|zhijin|zj','纸坊东站|zhifangdong|zfd','轵城站|zuocheng|zc','治安站|zhian|za','治山站|zhishan|zs','中德站|zhongde|zd','中和站|zhonghe|zh','中宁站|zhongning|zn','中宁东站|zhongningdong|znd','中宁南站|zhongningnan|znn','中山站|zhongshan|zs','中山北站|zhongshanbei|zsb','中台子站|zhongtaizi|ztz','中卫站|zhongwei|zw','中寨站|zhongzhai|zz','中嘴站|zhongzui|zz','忠工屯站|zhonggongtun|zgt','钟家村站|zhongjiacun|zjc','钟祥站|zhongxiang|zx','钟杖子站|zhongzhangzi|zzz','重庆站|zhongqing|zq','重庆北站|zhongqingbei|zqb','重庆东站|zhongqingdong|zqd','重庆南站|zhongqingnan|zqn','重庆西站|zhongqingxi|zqx','周家站|zhoujia|zj','周家屯站|zhoujiatun|zjt','周口站|zhoukou|zk','周士庄站|zhoushizhuang|zsz','周水子站|zhoushuizi|zsz','朱家沟站|zhujiagou|zjg','朱家窑站|zhujiayao|zjy','朱日和站|zhurihe|zrh','朱石寨站|zhushizhai|zsz','朱杨溪站|zhuyangxi|zyx','珠海站|zhuhai|zh','珠海北站|zhuhaibei|zhb','珠斯花站|zhusihua|zsh','珠窝站|zhuwo|zw','株洲站|zhuzhou|zz','株洲西站|zhuzhouxi|zzx','诸城站|zhucheng|zc','诸暨站|zhuzuozhan|zzz','竹园坝站|zhuyuanba|zyb','驻马店站|zhumadian|zmd','驻马店西站|zhumadianxi|zmdx','庄桥站|zhuangqiaozhan|zqz','壮志站|zhuangzhi|zz','准沙日乌苏站|zhunshariwusu|zsrws','涿州东站|zuozhoudongzhan|zzdz','涿州站|zuozhouzhan|zzz','卓资东站|zhuozidong|zzd','卓资山站|zhuozishan|zzs','资溪站|zixi|zx','资阳站|ziyang|zy','资中站|zizhong|zz','淄博站|zibo|zb','子长站|zichang|zc','子洲站|zizhou|zz','紫荆关站|zijingguan|zjg','紫阳站|ziyang|zy','自贡站|zigong|zg','棕溪站|zongxi|zx','邹城站|zoucheng|zc','遵义站|zunyi|zy','左家站|zuojia|zj','左岭站|zuoling|zl'];
Vcity.regEx = /^([\u4E00-\u9FA5\uf900-\ufa2d]+)\|(\w+)\|(\w)\w*$/i;
Vcity.regExChiese = /([\u4E00-\u9FA5\uf900-\ufa2d]+)/;

/* *
 * 格式化城市数组为对象oCity，按照a-h,i-p,q-z,hot热门城市分组：
 * {HOT:{hot:[]},ABCDEFGH:{a:[1,2,3],b:[1,2,3]},IJKLMNOP:{i:[1.2.3],j:[1,2,3]},QRSTUVWXYZ:{}}
 * */
/*
 * /^[a-b]是首字母为ab的，/i是不区分大小写
 * 
 * */
(function () {
    var citys = Vcity.allCity, match, letter,
            regEx = Vcity.regEx,
            reg2 = /^[a-b]$/i, reg3 = /^[c-d]$/i, reg4 = /^[e-g]$/i,reg5 = /^[h]$/i,reg6 = /^[j]$/i,reg7 = /^[k-l]$/i,reg8 =  /^[m-p]$/i,reg9 =  /^[q-r]$/i,reg10 =  /^[s]$/i,reg11 =  /^[t]$/i,reg12 =  /^[w]$/i,reg13 =  /^[x]$/i,reg14 =  /^[y]$/i,reg15 =  /^[z]$/i;
//    console.log(""+citys.length);
    if (!Vcity.oCity) {
        Vcity.oCity = {hot:{},AB:{},CD:{},EFG:{},H:{},J:{},KL:{},MNOP:{},QR:{},S:{},T:{},W:{},X:{},Y:{},Z:{}};
//        console.log(citys.length);
       // alert(citys.length);
//        for (var i = 0, n = citys.length; i < n; i++) {
        for (var i = 0, n = citys.length; i < n; i++) {
         
//            alert(match);
            try{
            match = regEx.exec(citys[i]);
            letter = match[3].toUpperCase();
            }catch (e) {
				alert(citys[i]);
			}
//            alert(letter);
            if (reg2.test(letter)) {
                if (!Vcity.oCity.AB[letter]) Vcity.oCity.AB[letter] = [];
                Vcity.oCity.AB[letter].push(match[1]);
//                alert(match[1]);
            } else if (reg3.test(letter)) {
                if (!Vcity.oCity.CD[letter]) Vcity.oCity.CD[letter] = [];
                Vcity.oCity.CD[letter].push(match[1]);
//                alert(match[1]);
            } else if (reg4.test(letter)) {
                if (!Vcity.oCity.EFG[letter]) Vcity.oCity.EFG[letter] = [];
                Vcity.oCity.EFG[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg5.test(letter)) {
                if (!Vcity.oCity.H[letter]) Vcity.oCity.H[letter] = [];
                Vcity.oCity.H[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg6.test(letter)) {
                if (!Vcity.oCity.J[letter]) Vcity.oCity.J[letter] = [];
                Vcity.oCity.J[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg7.test(letter)) {
                if (!Vcity.oCity.KL[letter]) Vcity.oCity.KL[letter] = [];
                Vcity.oCity.KL[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg8.test(letter)) {
                if (!Vcity.oCity.MNOP[letter]) Vcity.oCity.MNOP[letter] = [];
                Vcity.oCity.MNOP[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg9.test(letter)) {
                if (!Vcity.oCity.QR[letter]) Vcity.oCity.QR[letter] = [];
                Vcity.oCity.QR[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg10.test(letter)) {
                if (!Vcity.oCity.S[letter]) Vcity.oCity.S[letter] = [];
                Vcity.oCity.S[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg11.test(letter)) {
                if (!Vcity.oCity.T[letter]) Vcity.oCity.T[letter] = [];
                Vcity.oCity.T[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg12.test(letter)) {
                if (!Vcity.oCity.W[letter]) Vcity.oCity.W[letter] = [];
                Vcity.oCity.W[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg13.test(letter)) {
                if (!Vcity.oCity.X[letter]) Vcity.oCity.X[letter] = [];
                Vcity.oCity.X[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg14.test(letter)) {
                if (!Vcity.oCity.Y[letter]) Vcity.oCity.Y[letter] = [];
                Vcity.oCity.Y[letter].push(match[1]);
//                alert(match[1]);
            }else if (reg15.test(letter)) {
                if (!Vcity.oCity.Z[letter]) Vcity.oCity.Z[letter] = [];
                Vcity.oCity.Z[letter].push(match[1]);
//                alert(match[1]);
            }

            /* 热门城市 前16条 */
            if(i<20){
                if(!Vcity.oCity.hot['hot']) Vcity.oCity.hot['hot'] = [];
                Vcity.oCity.hot['hot'].push(match[1]);
            }
        }
    }
})();


/* 城市HTML模板 */
Vcity._template = [
    '<p class="tip">直接输入可搜索城市(支持汉字/拼音)</p>',
    '<ul>',
    '<li class="on">热门城市</li>',
    '<li>AB</li>',
    '<li>CD</li>',
    '<li>EFG</li>',
    '<li>H</li>',
    '<li>J</li>',
    '<li>KL</li>',
    '<li>MNP</li>',
    '<li>QR</li>',
    '<li>S</li>',
    '<li>T</li>',
    '<li>W</li>',
    '<li>X</li>',
    '<li>Y</li>',
    '<li>Z</li>',
    '</ul>'
];

/* *
 * 城市控件构造函数
 * @CitySelector
 * */

Vcity.CitySelector = function () {
    this.initialize.apply(this, arguments);
};

Vcity.CitySelector.prototype = {

    constructor:Vcity.CitySelector,

    /* 初始化 */

    initialize :function (options) {
        var input = options.input;
        this.input = Vcity._m.$('#'+ input);
        this.inputEvent();
    },

    /* *
        

    /* *
     * @createWarp
     * 创建城市BOX HTML 框架
     * */

    createWarp:function(){
        var inputPos = Vcity._m.getPos(this.input);
        var div = this.rootDiv = document.createElement('div');
        var that = this;

        // 设置DIV阻止冒泡
        Vcity._m.on(this.rootDiv,'click',function(event){
            Vcity._m.stopPropagation(event);
        });

        // 设置点击文档隐藏弹出的城市选择框
        Vcity._m.on(document, 'click', function (event) {
            event = Vcity._m.getEvent(event);
            var target = Vcity._m.getTarget(event);
            if(target == that.input) return false;
            //console.log(target.className);
            if (that.cityBox)Vcity._m.addClass('hide', that.cityBox);
            if (that.ul)Vcity._m.addClass('hide', that.ul);
            if(that.myIframe)Vcity._m.addClass('hide',that.myIframe);
        });
        div.className = 'citySelector';
        div.style.position = 'absolute';
        div.style.left = inputPos.left + 'px';
        div.style.top = inputPos.bottom + 5 + 'px';
        div.style.zIndex = 999999;

        // 判断是否IE6，如果是IE6需要添加iframe才能遮住SELECT框
        var isIe = (document.all) ? true : false;
        var isIE6 = this.isIE6 = isIe && !window.XMLHttpRequest;
        if(isIE6){
            var myIframe = this.myIframe =  document.createElement('iframe');
            myIframe.frameborder = '0';
            myIframe.src = 'about:blank';
            myIframe.style.position = 'absolute';
            myIframe.style.zIndex = '-1';
            this.rootDiv.appendChild(this.myIframe);
        }

        var childdiv = this.cityBox = document.createElement('div');
        childdiv.className = 'cityBox';
        childdiv.id = 'cityBox';
        childdiv.innerHTML = Vcity._template.join('');
        var hotCity = this.hotCity =  document.createElement('div');
        hotCity.className = 'hotCity';
        childdiv.appendChild(hotCity);
        div.appendChild(childdiv);
        this.createHotCity();
    },

    /* *
     * @createHotCity
     * TAB下面DIV：hot,a-h,i-p,q-z 分类HTML生成，DOM操作
     * {HOT:{hot:[]},ABCDEFGH:{a:[1,2,3],b:[1,2,3]},IJKLMNOP:{},QRSTUVWXYZ:{}}
     **/

    createHotCity:function(){
        var odiv,odl,odt,odd,odda=[],str,key,ckey,sortKey,regEx = Vcity.regEx,
                oCity = Vcity.oCity;
        for(key in oCity){
            odiv = this[key] = document.createElement('div');
            // 先设置全部隐藏hide
            odiv.className = key + ' ' + 'cityTab hide';
            sortKey=[];
            for(ckey in oCity[key]){
                sortKey.push(ckey);
                // ckey按照ABCDEDG顺序排序
                sortKey.sort();
            }
            for(var j=0,k = sortKey.length;j<k;j++){
                odl = document.createElement('dl');
                odt = document.createElement('dt');
                odd = document.createElement('dd');
                odt.innerHTML = sortKey[j] == 'hot'?'&nbsp;':sortKey[j];
                odda = [];
                for(var i=0,n=oCity[key][sortKey[j]].length;i<n;i++){
                    str = '<a href="#">' + oCity[key][sortKey[j]][i] + '</a>';
                    odda.push(str);
                }
                odd.innerHTML = odda.join('');
                odl.appendChild(odt);
                odl.appendChild(odd);
                odiv.appendChild(odl);
            }

            // 移除热门城市的隐藏CSS
            Vcity._m.removeClass('hide',this.hot);
            this.hotCity.appendChild(odiv);
        }
        document.body.appendChild(this.rootDiv);
        /* IE6 */
        this.changeIframe();

        this.tabChange();
        this.linkEvent();
    },

    /* *
     *  tab按字母顺序切换
     *  @ tabChange
     * */

    tabChange:function(){
        var lis = Vcity._m.$('li',this.cityBox);
        var divs = Vcity._m.$('div',this.hotCity);
        var that = this;
        for(var i=0,n=lis.length;i<n;i++){
            lis[i].index = i;
            lis[i].onclick = function(){
                for(var j=0;j<n;j++){
                    Vcity._m.removeClass('on',lis[j]);
                    Vcity._m.addClass('hide',divs[j]);
                }
                Vcity._m.addClass('on',this);
                Vcity._m.removeClass('hide',divs[this.index]);
                /* IE6 改变TAB的时候 改变Iframe 大小*/
                that.changeIframe();
            };
        }
    },

    /* *
     * 城市LINK事件
     *  @linkEvent
     * */

    linkEvent:function(){
        var links = Vcity._m.$('a',this.hotCity);
        var that = this;
        for(var i=0,n=links.length;i<n;i++){
            links[i].onclick = function(){
                that.input.value = this.innerHTML;
                Vcity._m.addClass('hide',that.cityBox);
                /* 点击城市名的时候隐藏myIframe */
                Vcity._m.addClass('hide',that.myIframe);
            }
        }
    },

    /* *
     * INPUT城市输入框事件
     * @inputEvent
     * */

    inputEvent:function(){
        var that = this;
        Vcity._m.on(this.input,'click',function(event){
            event = event || window.event;
            if(!that.cityBox){
                that.createWarp();
            }else if(!!that.cityBox && Vcity._m.hasClass('hide',that.cityBox)){
                // slideul 不存在或者 slideul存在但是是隐藏的时候 两者不能共存
                if(!that.ul || (that.ul && Vcity._m.hasClass('hide',that.ul))){
                    Vcity._m.removeClass('hide',that.cityBox);

                    /* IE6 移除iframe 的hide 样式 */
                    //alert('click');
                    Vcity._m.removeClass('hide',that.myIframe);
                    that.changeIframe();
                }
            }
        });
        // Vcity._m.on(this.input,'focus',function(){
        //     that.input.select();
        //     if(that.input.value == '城市名') that.input.value = '';
        // });
        Vcity._m.on(this.input,'blur',function(){
            // if(that.input.value == '') that.input.value = '城市名';
            
            var value = Vcity._m.trim(that.input.value);
            if(value != ''){
                var reg = new RegExp("^" + value + "|\\|" + value, 'gi');
                var flag=0;
                for (var i = 0, n = Vcity.allCity.length; i < n; i++) {
                    if (reg.test(Vcity.allCity[i])) {
                        flag++;
                    }
                }
                if(flag==0){
                    that.input.value= '';
                }else{
                    var lis = Vcity._m.$('li',that.ul);
                    if(typeof lis == 'object' && lis['length'] > 0){
                        var li = lis[0];
                        var bs = li.children;
                        if(bs && bs['length'] > 1){
                            that.input.value = bs[0].innerHTML;
                        }
                    }else{
                        that.input.value = '';
                    }
                }
            }

        });
        Vcity._m.on(this.input,'keyup',function(event){
            event = event || window.event;
            var keycode = event.keyCode;
            Vcity._m.addClass('hide',that.cityBox);
            that.createUl();

            /* 移除iframe 的hide 样式 */
            Vcity._m.removeClass('hide',that.myIframe);

            // 下拉菜单显示的时候捕捉按键事件
            if(that.ul && !Vcity._m.hasClass('hide',that.ul) && !that.isEmpty){
                that.KeyboardEvent(event,keycode);
            }
        });
    },

    /* *
     * 生成下拉选择列表
     * @ createUl
     * */

    createUl:function () {
        //console.log('createUL');
        var str;
        var value = Vcity._m.trim(this.input.value);
        // 当value不等于空的时候执行
        if (value !== '') {
            var reg = new RegExp("^" + value + "|\\|" + value, 'gi');
            // 此处需设置中文输入法也可用onpropertychange
            var searchResult = [];
            for (var i = 0, n = Vcity.allCity.length; i < n; i++) {
                if (reg.test(Vcity.allCity[i])) {
                    var match = Vcity.regEx.exec(Vcity.allCity[i]);
                    if (searchResult.length !== 0) {
                        str = '<li><b class="cityname">' + match[1] + '</b><b class="cityspell">' + match[2] + '</b></li>';
                    } else {
                        str = '<li class="on"><b class="cityname">' + match[1] + '</b><b class="cityspell">' + match[2] + '</b></li>';
                    }
                    searchResult.push(str);
                }
            }
            this.isEmpty = false;
            // 如果搜索数据为空
            if (searchResult.length == 0) {
                this.isEmpty = true;
                str = '<li class="empty">对不起，没有找到 "<em>' + value + '</em>"</li>';
                searchResult.push(str);
            }
            // 如果slideul不存在则添加ul
            if (!this.ul) {
                var ul = this.ul = document.createElement('ul');
                ul.className = 'cityslide mCustomScrollbar';
                this.rootDiv && this.rootDiv.appendChild(ul);
                // 记录按键次数，方向键
                this.count = 0;
            } else if (this.ul && Vcity._m.hasClass('hide', this.ul)) {
                this.count = 0;
                Vcity._m.removeClass('hide', this.ul);
            }
            this.ul.innerHTML = searchResult.join('');

            /* IE6 */
            this.changeIframe();

            // 绑定Li事件
            this.liEvent();
        }else{
            Vcity._m.addClass('hide',this.ul);
            Vcity._m.removeClass('hide',this.cityBox);

            Vcity._m.removeClass('hide',this.myIframe);

            this.changeIframe();
        }
    },

    /* IE6的改变遮罩SELECT 的 IFRAME尺寸大小 */
    changeIframe:function(){
        if(!this.isIE6)return;
        this.myIframe.style.width = this.rootDiv.offsetWidth + 'px';
        this.myIframe.style.height = this.rootDiv.offsetHeight + 'px';
    },

    /* *
     * 特定键盘事件，上、下、Enter键
     * @ KeyboardEvent
     * */

    KeyboardEvent:function(event,keycode){
        var lis = Vcity._m.$('li',this.ul);
        var len = lis.length;
        switch(keycode){
            case 40: //向下箭头↓
                this.count++;
                if(this.count > len-1) this.count = 0;
                for(var i=0;i<len;i++){
                    Vcity._m.removeClass('on',lis[i]);
                }
                Vcity._m.addClass('on',lis[this.count]);
                break;
            case 38: //向上箭头↑
                this.count--;
                if(this.count<0) this.count = len-1;
                for(i=0;i<len;i++){
                    Vcity._m.removeClass('on',lis[i]);
                }
                Vcity._m.addClass('on',lis[this.count]);
                break;
            case 13: // enter键
                this.input.value = Vcity.regExChiese.exec(lis[this.count].innerHTML)[0];
                Vcity._m.addClass('hide',this.ul);
                Vcity._m.addClass('hide',this.ul);
                /* IE6 */
                Vcity._m.addClass('hide',this.myIframe);
                break;
            default:
                break;
        }
    },

    /* *
     * 下拉列表的li事件
     * @ liEvent
     * */

    liEvent:function(){
        var that = this;
        var lis = Vcity._m.$('li',this.ul);
        for(var i = 0,n = lis.length;i < n;i++){
            Vcity._m.on(lis[i],'click',function(event){ 
                event = Vcity._m.getEvent(event);
                var target = Vcity._m.getTarget(event);
                that.input.value = Vcity.regExChiese.exec(target.innerHTML)[0];
                Vcity._m.addClass('hide',that.ul);
                /* IE6 下拉菜单点击事件 */
                Vcity._m.addClass('hide',that.myIframe);
            });
            Vcity._m.on(lis[i],'mouseover',function(event){
                event = Vcity._m.getEvent(event);
                var target = Vcity._m.getTarget(event);
                Vcity._m.addClass('on',target);
            });
            Vcity._m.on(lis[i],'mouseout',function(event){
                event = Vcity._m.getEvent(event);
                var target = Vcity._m.getTarget(event);
                Vcity._m.removeClass('on',target);
            })
        }
    }
};