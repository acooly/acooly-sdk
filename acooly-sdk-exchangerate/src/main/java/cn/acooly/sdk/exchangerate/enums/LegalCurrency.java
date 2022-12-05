/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-27 23:39
 */
package cn.acooly.sdk.exchangerate.enums;
/**
 * 常用法币定义
 *
 * @author zhangpu
 * @date 2021-05-27 23:39
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum LegalCurrency implements Messageable {
    CNY("CNY", "人民币", "￥", "156", 2),
    USD("USD", "美元", "$", "840", 2),
    EUR("EUR", "欧元", "€", "978", 2),
    GBP("GBP", "英镑", "￡", "826", 2),
    HKD("HKD", "港元", "", "344", 2),
    MOP("MOP", "澳门元", "", "446", 2),
    TWD("TWD", "新台币", "", "901", 2),
    NZD("NZD", "新西兰元", "", "554", 2),
    JPY("JPY", "日元", "¥", "392", 0),
    KRW("KRW", "韩元", "₩", "410", 0),
    VND("VND", "越南盾", "₫", "704", 2),
    AUD("AUD", "澳元", "","036", 2),
    CAD("CAD", "加元", "", "124", 2),
    TRY("TRY", "土耳其里拉", "", "949", 2),
    SEK("SEK", "瑞典克朗", "", "752", 2),
    INR("INR", "印度卢比", "", "710", 2),
    ZAR("ZAR", "南非兰特", "", "901", 2),
    THB("THB", "泰铢", "", "554", 2),
    SGD("SGD", "新加坡元", "", "784", 2),
    AED("AED", "阿联酋迪拉姆", "", "971", 2),
    AFN("AFN", "阿富汗尼", "", "008", 2),
    ALL("ALL", "阿尔巴尼列克", "", "051", 2),
    AMD("AMD", "亚美尼亚德拉姆", "", "532", 2),
    ANG("ANG", "荷兰盾", "", "973", 2),
    AOA("AOA", "安哥拉宽扎", "", "032", 2),
    ARS("ARS", "阿根廷比索", "", "533", 2),
    AWG("AWG", "阿鲁巴弗罗林", "", "944", 2),
    AZN("AZN", "阿塞拜疆马纳特", "", "977", 2),
    BAM("BAM", "波黑可兑换马克", "", "052", 2),
    BBD("BBD", "巴巴多斯元", "", "050", 2),
    BDT("BDT", "孟加拉国塔卡", "", "975", 2),
    BGN("BGN", "保加利亚列弗", "", "048", 2),
    BHD("BHD", "巴林第纳尔", "", "108", 2),
    BIF("BIF", "布隆迪法郎", "", "060", 2),
    BMD("BMD", "百慕大元", "", "096", 2),
    BND("BND", "文莱元", "", "068", 2),
    BOB("BOB", "玻利维亚诺", "", "986", 2),
    BRL("BRL", "巴西雷亚尔", "", "044", 2),
    BSD("BSD", "巴哈马元", "", "064", 2),
    BTN("BTN", "不丹努扎姆", "", "072", 2),
    BWP("BWP", "博茨瓦纳普拉", "", "974", 2),
    BYR("BYR", "白俄罗斯卢布", "", "084", 2),
    BZD("BZD", "伯利兹元", "", "976", 2),
    CDF("CDF", "刚果法郎", "", "756", 2),
    CHF("CHF", "瑞士法郎", "", "990", 2),
    CLF("CLF", "智利比索(基金)", "", "152", 2),
    CLP("CLP", "智利比索", "", "170", 2),
    COP("COP", "哥伦比亚比索", "", "188", 2),
    CRC("CRC", "哥斯达黎加科朗", "", "192", 2),
    CUP("CUP", "古巴比索", "", "132", 2),
    CVE("CVE", "佛得角埃斯库多", "", "196", 2),
    CYP("CYP", "塞普路斯镑", "", "203", 2),
    CZK("CZK", "捷克克朗", "", "280", 2),
    DEM("DEM", "德国马克", "", "262", 2),
    DJF("DJF", "吉布提法郎", "", "208", 2),
    DKK("DKK", "丹麦克朗", "", "214", 2),
    DOP("DOP", "多米尼加比索", "", "012", 2),
    DZD("DZD", "阿尔及利亚第纳尔", "", "218", 2),
    ECS("ECS", "厄瓜多尔苏克雷", "", "818", 2),
    EGP("EGP", "埃及镑", "", "232", 2),
    ERN("ERN", "厄立特里亚纳克法", "", "230", 2),
    ETB("ETB", "埃塞俄比亚比尔", "", "242", 2),
    FJD("FJD", "斐济元", "", "238", 2),
    FKP("FKP", "福克兰群岛镑", "", "250", 2),
    FRF("FRF", "法国法郎", "", "981", 2),
    GEL("GEL", "格鲁吉亚拉里", "", "936", 2),
    GHS("GHS", "加纳塞地", "", "292", 2),
    GIP("GIP", "直布罗陀镑", "", "270", 2),
    GMD("GMD", "冈比亚达拉西", "", "324", 2),
    GNF("GNF", "几内亚法郎", "", "320", 2),
    GTQ("GTQ", "危地马拉格查尔", "", "328", 2),
    GYD("GYD", "圭亚那元", "", "340", 2),
    HNL("HNL", "洪都拉斯伦皮拉", "", "191", 2),
    HRK("HRK", "克罗地亚库纳", "", "332", 2),
    HTG("HTG", "海地古德", "", "348", 2),
    HUF("HUF", "匈牙利福林", "", "360", 2),
    IDR("IDR", "印度尼西亚卢比", "", "372", 2),
    IEP("IEP", "爱尔兰镑", "", "376", 2),
    ILS("ILS", "以色列新谢克尔", "", "368", 2),
    IQD("IQD", "伊拉克第纳尔", "", "364", 2),
    IRR("IRR", "伊朗里亚尔", "", "352", 2),
    ISK("ISK", "冰岛克郎", "", "380", 2),
    ITL("ITL", "意大利里拉", "", "388", 2),
    JMD("JMD", "牙买加元", "", "400", 2),
    JOD("JOD", "约旦第纳尔", "", "404", 2),
    KES("KES", "肯尼亚先令", "", "417", 2),
    KGS("KGS", "吉尔吉斯斯坦索姆", "", "116", 2),
    KHR("KHR", "柬埔寨瑞尔", "", "174", 2),
    KMF("KMF", "科摩罗法郎", "", "408", 2),
    KPW("KPW", "朝鲜元", "", "414", 2),
    KWD("KWD", "科威特第纳尔", "", "136", 2),
    KYD("KYD", "开曼群岛元", "", "398", 2),
    KZT("KZT", "哈萨克斯坦坚戈", "", "418", 2),
    LAK("LAK", "老挝基普", "", "422", 2),
    LBP("LBP", "黎巴嫩镑", "", "144", 2),
    LKR("LKR", "斯里兰卡卢比", "", "430", 2),
    LRD("LRD", "利比里亚元", "", "426", 2),
    LSL("LSL", "莱索托洛蒂", "", "440", 2),
    LTL("LTL", "立陶宛立特", "", "428", 2),
    LVL("LVL", "拉脱维亚拉特", "", "434", 2),
    LYD("LYD", "利比亚第纳尔", "", "504", 2),
    MAD("MAD", "摩洛哥迪拉姆", "", "498", 2),
    MDL("MDL", "摩尔多瓦列伊", "", "969", 2),
    MGA("MGA", "马达加斯加阿里亚里", "", "807", 2),
    MKD("MKD", "马其顿代纳尔", "", "104", 2),
    MMK("MMK", "缅甸元", "", "496", 2),
    MNT("MNT", "蒙古图格里克", "", "478", 2),
    MRO("MRO", "毛里塔尼亚乌吉亚", "", "480", 2),
    MUR("MUR", "毛里求斯卢比", "", "462", 2),
    MVR("MVR", "马尔代夫拉菲亚", "", "454", 2),
    MWK("MWK", "马拉维克瓦查", "", "484", 2),
    MXN("MXN", "墨西哥比索", "", "979", 2),
    MXV("MXV", "墨西哥(资金)", "", "458", 2),
    MYR("MYR", "林吉特", "", "943", 2),
    MZN("MZN", "莫桑比克新梅蒂卡尔", "", "516", 2),
    NAD("NAD", "纳米比亚元", "", "566", 2),
    NGN("NGN", "尼日利亚奈拉", "", "558", 2),
    NIO("NIO", "尼加拉瓜新科多巴", "", "578", 2),
    NOK("NOK", "挪威克朗", "", "524", 2),
    NPR("NPR", "尼泊尔卢比", "", "512", 2),
    OMR("OMR", "阿曼里亚尔", "", "590", 2),
    PAB("PAB", "巴拿马巴波亚", "", "604", 2),
    PEN("PEN", "秘鲁新索尔", "", "598", 2),
    PGK("PGK", "巴布亚新几内亚基那", "", "608", 2),
    PHP("PHP", "菲律宾比索", "", "586", 2),
    PKR("PKR", "巴基斯坦卢比", "", "985", 2),
    PLN("PLN", "波兰兹罗提", "", "600", 2),
    PYG("PYG", "巴拉圭瓜拉尼", "", "634", 2),
    QAR("QAR", "卡塔尔里亚尔", "", "946", 2),
    RON("RON", "罗马尼亚列伊", "", "941", 2),
    RSD("RSD", "塞尔维亚第纳尔", "", "643", 2),
    RUB("RUB", "俄罗斯卢布", "", "646", 2),
    RWF("RWF", "卢旺达法郎", "", "682", 2),
    SAR("SAR", "沙特里亚尔", "", "090", 2),
    SBD("SBD", "所罗门群岛元", "", "690", 2),
    SCR("SCR", "塞舌尔卢比", "", "938", 2),
    SDG("SDG", "苏丹镑", "", "752", 2),
    SHP("SHP", "圣赫勒拿镑", "", "705", 2),
    SIT("SIT", "斯洛文尼亚托拉尔", "", "694", 2),
    SLL("SLL", "塞拉利昂利昂", "", "706", 2),
    SOS("SOS", "索马里先令", "", "968", 2),
    SRD("SRD", "苏里南元", "", "678", 2),
    STD("STD", "圣多美多布拉", "", "222", 2),
    SVC("SVC", "萨尔瓦多科朗", "", "760", 2),
    SYP("SYP", "叙利亚镑", "", "748", 2),
    SZL("SZL", "斯威士兰里兰吉尼", "", "972", 2),
    TJS("TJS", "塔吉克斯坦索莫尼", "", "934", 2),
    TMT("TMT", "土库曼斯坦马纳特", "", "788", 2),
    TND("TND", "突尼斯第纳尔", "", "776", 2),
    TOP("TOP", "汤加潘加", "", "949", 2),
    TTD("TTD", "特立尼达多巴哥元", "", "834", 2),
    TZS("TZS", "坦桑尼亚先令", "", "980", 2),
    UAH("UAH", "乌克兰格里夫纳", "", "800", 2),
    UGX("UGX", "乌干达先令", "", "858", 2),
    UYU("UYU", "乌拉圭比索", "", "860", 2),
    UZS("UZS", "乌兹别克斯坦苏姆", "", "937", 2),
    VEF("VEF", "委内瑞拉玻利瓦尔", "", "704", 2),
    VUV("VUV", "瓦努阿图瓦图", "", "882", 2),
    WST("WST", "萨摩亚塔拉", "", "886", 2),
    YER("YER", "也门里亚尔", "", "967", 2),
    ZMW("ZMW", "赞比亚克瓦查", "", "716", 2),
    ZWL("ZWL", "津巴布韦元", "", "950", 2),
    XAF("XAF", "中非法郎", "", "951", 2),
    XCD("XCD", "东加勒比元", "", "960", 2),
    XDR("XDR", "IMF特别提款权", "", "952", 2),
    XOF("XOF", "西非法郎", "", "953", 2),
    XPF("XPF", "太平洋法郎", "", "961", 2),
    XAG("XAG", "银价盎司", "", "959", 2),
    XAU("XAU", "金价盎司", "", "964", 2),
    XPD("XPD", "钯价盎司", "", "962", 2),
    ;

    private final String code;
    private final String message;
    private final String symbol;
    private final String numericCode;
    private final int scale;

    LegalCurrency(String code, String message, String symbol, String numericCode, int scale) {
        this.code = code;
        this.message = message;
        this.symbol = symbol;
        this.numericCode = numericCode;
        this.scale = scale;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (LegalCurrency type : values()) {
            map.put(type.getCode(), type.getMessage());
        }
        return map;
    }

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 Status 。
     */
    public static LegalCurrency find(String code) {
        for (LegalCurrency status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<LegalCurrency> getAll() {
        List<LegalCurrency> list = new ArrayList<LegalCurrency>();
        for (LegalCurrency status : values()) {
            list.add(status);
        }
        return list;
    }

    /**
     * 获取全部枚举值码。
     *
     * @return 全部枚举值码。
     */
    public static List<String> getAllCode() {
        List<String> list = new ArrayList<String>();
        for (LegalCurrency status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
