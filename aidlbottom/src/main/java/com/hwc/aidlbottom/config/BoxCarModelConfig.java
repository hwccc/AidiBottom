package com.hwc.aidlbottom.config;

/**
 * 车SN标记
 */
public interface BoxCarModelConfig {
    int BOX_MODEL_TYPE_DEFAULT = 88;//通用（全系通用
    int BOX_MODEL_TYPE_AO_DE = 0;//奥迪
    int BOX_MODEL_TYPE_BAO_MA = 11;//宝马
    int BOX_MODEL_TYPE_BEN_CHE = 12;//奔驰
    int BOX_MODEL_TYPE_BAO_SHI_JIE = 13;//保时捷
    int BOX_MODEL_TYPE_JIE_BAO = 14;//捷豹
    int BOX_MODEL_TYPE_LU_HU = 15;//路虎
    int BOX_MODEL_TYPE_WO_ER_WO = 16;//沃尔沃
    int BOX_MODEL_TYPE_BIE_KE = 17;//别克
    int BOX_MODEL_TYPE_KAI_DE_LA_KE = 18;//凯迪拉克
    int BOX_MODEL_TYPE_FU_TE = 19;//福特
    int BOX_MODEL_TYPE_DONG_FENG_BEN_TIAN = 20;//东风本田
    int BOX_MODEL_TYPE_BEI_JING_XIAN_DAI = 21;//北京现代
    int BOX_MODEL_TYPE_QI_YA = 22;//起亚
    int BOX_MODEL_TYPE_GUANG_QI_FEI_KE_JEEP = 23;//广汽菲克Jeep
    int BOX_MODEL_TYPE_LEI_KE_SHA_SI = 24;//雷克萨斯
    int BOX_MODEL_TYPE_DONG_FENG_RE_CHAN = 25;//东风日产
    int BOX_MODEL_TYPE_YI_QI_FENG_TIAN = 26;//一汽丰田
    int BOX_MODEL_TYPE_YI_QI_DA_ZHONG = 27;//一汽大众
    int BOX_MODEL_TYPE_BIAO_ZHI = 28;//标致
    int BOX_MODEL_TYPE_XUE_TIE_LONG = 29;//雪铁龙
    int BOX_MODEL_TYPE_GUANG_QI_BEN_TIAN = 30;//广汽本田
    int BOX_MODEL_TYPE_GUANG_QI_FENG_TIAN = 31;//广汽丰田
    int BOX_MODEL_TYPE_SHANG_QI_DA_ZHONG = 32;//上汽大众
    int BOX_MODEL_TYPE_JIN_KO_DA_ZHONG = 33;//进口大众
    int BOX_MODEL_TYPE_XUE_FO_LAN = 34;//雪佛兰
    int BOX_MODEL_TYPE_YI_QI_MA_ZI_DA = 35;//一汽马自达
    int BOX_MODEL_TYPE_CHANG_AN_MA_ZI_DA = 36;//长安马自达
    int BOX_MODEL_TYPE_DS = 37;//DS
    int BOX_MODEL_TYPE_SI_KE_DA = 38;//斯柯达
    int BOX_MODEL_TYPE_LING_KE = 39;//领克
    int BOX_MODEL_TYPE_OUGE = 43;//讴歌
    int BOX_MODEL_TYPE_BINLI = 01;//宾利
    int BOX_MODEL_TYPE_LINKEN = 02;//林肯


    int BOX_MODEL_TYPE_FOREIGN_STYLE_ONE = 40;//国外样式一（通用）
    int BOX_MODEL_TYPE_FOREIGN_STYLE_TWO = 41;//国外样式二（奔驰为主，奔驰、奥迪、保时捷（海外））
    int BOX_MODEL_TYPE_FOREIGN_STYLE_THREE = 42;//国外样式三（大众为主， 大众+其它（排除41））
    int BOX_MODEL_TYPE_FOREIGN_STYLE_FOUR = 44;//国外样式四（丰田为主，丰田/本田/日产/大众/福特/标致/雪佛兰/别克/现代/斯柯达）
    int BOX_MODEL_TYPE_FOREIGN_STYLE_FIVES = 45;//国外样式五（奔驰为主，奔驰/奥迪/保时捷/VOLVO/凯迪拉克）
    int BOX_MODEL_TYPE_FOREIGN_STYLE_SIX = 81;//国外样式六（宝马为基准，排除奔驰、奥迪、大众、保时捷）


    int BOX_MODEL_TYPE_DOMESTIC_STYLE_ONE = 46;//国内样式一（宝马为基准，排除奔驰、奥迪、大众、保时捷）,跟国外样式六一样

    int BOX_MODEL_TYPE_SUNPLUS = 47; //卓依达解码器专用


}
