package com.dreawer.favorite.domain;

import java.sql.Timestamp;


import com.dreawer.domain.BaseDomain;
import com.dreawer.favorite.lang.ContentStatus;

/**
 * <CODE>Favorite</CODE> 收藏实体类。
 *
 * @author zengzhijie
 * @version 1.0
 * @since Favorite 1.0
 */
public class Favorite extends BaseDomain {
    private static final long serialVersionUID = 7406372133507991038L;



    private String collectorId;      //收藏者Id；

    private String contentId;   //收藏内容Id;

    private String contentCategory;  //被收藏内容类别

    private Timestamp createTime = null; // 收藏时间

    private ContentStatus status = null; //状态

    private Timestamp updateTime = null; // 更新时间
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public Favorite() {
        super();
    }

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------




    /**
     * 获取属性 <TT>collectorId</TT>（收藏者）的值。
     *
     * @return <TT>collectorId</TT> 收藏者。
     */
    public String getCollectorId() {
        return collectorId;
    }

    /**
     * 设置属性 <TT>collector</TT>（收藏者）的值。
     *
     * @param collectorId 收藏者。
     */
    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    /**
     * 获取属性 <TT>content</TT>内容类别的值。
     *
     * @return <TT>content</TT> 内容内别。
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * 设置属性 <TT>collector</TT>（收藏内容）的值。
     *
     * @param contentId 收藏内容。
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * 获取属性 <TT>getContentCategory</TT>（收藏内容）的值。
     *
     * @return <TT>getContentCategory</TT> 收藏内容。
     */
    public String getContentCategory() {
        return contentCategory;
    }

    /**
     * 设置属性 <TT>collector</TT>（收藏者）的值。
     *
     * @param contentCategory 收藏者类别。
     */
    public void setContentCategory(String contentCategory) {
        this.contentCategory = contentCategory;
    }



    /**
     * 获取属性 <TT>createTime</TT>（收藏时间）的值。
     *
     * @return <TT>createTime</TT> 收藏时间。
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 设置属性 <TT>createTime</TT>（收藏时间）的值。
     *
     * @param createTime 收藏时间。
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取属性 <TT>status</TT>（状态）的值。
     *
     * @return <TT>status</TT> 状态。
     */
    public ContentStatus getStatus() {
        return status;
    }

    /**
     * 设置属性 <TT>status</TT>（状态）的值。
     *
     * @param status 状态。
     */
    public void setStatus(ContentStatus status) {
        this.status = status;
    }

    /**
     * 获取属性 <TT>updateTime</TT>（更新时间）的值。
     *
     * @return <TT>updateTime</TT> 更新时间。
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置属性 <TT>updateTime</TT>（更新时间）的值。
     *
     * @param updateTime 更新时间。
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
