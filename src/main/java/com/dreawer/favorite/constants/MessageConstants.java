package com.dreawer.favorite.constants;


/**
 * <CODE>ResourceConstants</CODE> 国际化资源常量类。<br/>
 * 该类用于定义“国际化（本地化）资源”的代码规范性常量，以统一工程中有关于对象、属性名称的代码规范。
 *
 * @author David Dai
 * @version 1.0
 * @since Favorite 1.0
 */
public final class MessageConstants {

    /**
     * 私有构造器。
     */
    private MessageConstants() {
    }

    /**
     * 资源文件名
     */
    public static final String FILE_MESSAGE_RESOURCE = "messageResource";

    // --------------------------------------------------------------------------------
    // 交互信息
    // --------------------------------------------------------------------------------

    /**
     * 交互信息（其他异常）
     */
    public static final String MSG_SYSTEM_BUSY = "其他系统异常！";


    /**
     * 对象ID号为空
     */
    public static final String VAL_OBJECT_ID_NOT_EMPTY = "未指定对象ID号，请检查并重试";

    /**
     * 对象ID号长度太短
     */
    public static final String VAL_OBJECT_ID_LENGTH_MIN = VAL_OBJECT_ID_NOT_EMPTY;

    /**
     * 对象ID号为空
     */
    public static final String VAL_OBJECT_CATEGORY_NOT_EMPTY = "未指定对象分类，请检查并重试";



    /**
     * 系统异常
     */
    public static final String MSG_SYS_BUSY = "系统异常，请稍后再试";

    /**
     * 应用不存在
     */
    public static final String MSG_APP_NULL = "该应用不存在";




    // --------------------------------------------------------------------------------
    // 警告信息
    // --------------------------------------------------------------------------------

    /**
     * 警告信息（专题未找到）
     */
    public static final String WARN_TOPIC_NOTFOUND = "warn.topic.notfound";

    /**
     * 警告信息（服务范围未找到）
     */
    public static final String WARN_CATEGORY_NOTFOUND = "warn.category.notfound";

    /**
     * 警告信息（应用未找到）
     */
    public static final String WARN_APP_NOTFOUND = "warn.app.notfound";

    /**
     * 警告信息（应用重复）
     */
    public static final String WARN_APP_NAME_DUPLUN = "warn.app.name.duplun";

    // --------------------------------------------------------------------------------
    // 错误信息
    // --------------------------------------------------------------------------------

    /**
     * 错误信息（其他异常）
     */
    public static final String ERR_OTHER = "err.other";

    /**
     * 收藏夹不存在
     */
    public static final String VAL__FAVORITES_NOTFOUND = "该收藏夹不存在";

    /**
     * 收藏已经存在
     */
    public static final String VAL__FAVORITES_EXISTS = "该收藏夹已存在";

    /**
     * 收藏夹名称不符合要求
     */
    public static final String VAL_FAVORITES_NAME_LENGTH = "收藏夹名称为1~20字符！";

    /**
     * 收藏夹名称为空
     */
    public static final String VAL_FAVORITES_NAME_NOT_EMPTY = VAL_FAVORITES_NAME_LENGTH;

    /**
     * 收藏夹ID为空
     */
    public static final String VAL_FAVORITES_ID_NOT_EMPTY = "id不能为空！";

}
