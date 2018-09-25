package com.dreawer.favorite.view;


/**
 * <CODE>ViewFavorite</CODE> 收藏内容视图实例类，用于展示收藏内容信息到客户端。
 *
 * @author David Dai
 * @version 1.0
 * @since Param 1.0
 */
public class ViewFavoriteContent {

    private String contentId = null; // 内容id

    private String category = null; // 分类

    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public ViewFavoriteContent() {
    }

    // --------------------------------------------------------------------------------
    // get和set方法
    // --------------------------------------------------------------------------------

    /**
     * 获取属性 <TT>contentId</TT>（内容id）的值。
     *
     * @return <TT>contentId</TT> 内容id。
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * 设置属性 <TT>id</TT>（内容id）的值。
     *
     * @param contentId 内容id。
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * 获取属性 <TT>category</TT>（分类）的值。
     *
     * @return <TT>category</TT> 分类。
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置属性 <TT>category</TT>（分类）的值。
     *
     * @param category 分类。
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
