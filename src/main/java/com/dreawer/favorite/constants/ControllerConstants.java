package com.dreawer.favorite.constants;


/**
 * <CODE>RequestConstants</CODE> 控制器层常量类。
 * 该类用于定义“请求链接”和“页面地址”的代码规范性常量，以统一工程中有关于对象、属性名称的代码规范。
 *
 * @author zengzhijie
 * @version 1.0
 * @since Favorite 1.0
 */
public final class ControllerConstants {

	int i =1;
    /**
     * 私有构造器。
     */
    private ControllerConstants() {
    }

    // --------------------------------------------------------------------------------
    // 控制器
    // --------------------------------------------------------------------------------

    /**
     * 收藏服务控制器
     */
    public static final String FAVORITE_CONTROLLER = "favoriteController";

    // --------------------------------------------------------------------------------
    // 请求地址
    // --------------------------------------------------------------------------------

    /**
     * 请求“收藏”
     */
    public static final String REQ_FAVORITE = "/addFavorite";

    /**
     * 请求“添加收藏夹”
     */
    public static final String REQ_FAVORITES_ADD = "/favorites/addFavorites";

    /**
     * 请求“删除收藏夹”
     */
    public static final String REQ_FAVORITES_DELETE = "/favorites/deleteFavorites";

    /**
     * 请求“更新收藏夹”
     */
    public static final String REQ_FAVORITES_EDIT = "/favorites/editFavorites";

    /**
     * 请求“查询收藏夹列表”
     */
    public static final String REQ_FAVORITES_LIST = "/favorites/getFavoritesList";

    /**
     * 请求“取消收藏”
     */
    public static final String REQ_UNFAVORITE = "/unfavorite";

    /**
     * 请求“获得用户收藏”
     */
    public static final String REQ_GET_USERFAVORITES = "/getUserFavorites";

    /**
     * 请求“更新收藏”
     */
    public static final String REQ_UPDATE_FAVORITE = "/updateFavorite";

    /**
     * 基本地址（静态页面资源引用）
     */
    public static final String BASE_URL = "baseurl";

    /**
     * 错误信息
     */
    public static final String ERRORS = "errors";
}
