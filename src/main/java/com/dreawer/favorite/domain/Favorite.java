package com.dreawer.favorite.domain;

import java.sql.Timestamp;


import com.dreawer.domain.BaseDomain;
//import com.dreawer.dream.domain.BaseDomain;
import com.dreawer.dream.domain.Content;
import com.dreawer.favorite.lang.ContentStatus;
import com.dreawer.sso.domain.User;

/**
 * <CODE>Favorite</CODE> 收藏实体类。
 *
 * @author David Dai
 * @version 1.0
 * @since Favorite 1.0
 */
public class Favorite extends BaseDomain {
    private static final long serialVersionUID = 7406372133507991038L;

    private User collector = null; // 收藏者

    private Content content = null; // 被收藏内容

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
     * 获取属性 <TT>collector</TT>（收藏者）的值。
     *
     * @return <TT>collector</TT> 收藏者。
     */
    public User getCollector() {
        return collector;
    }

    /**
     * 设置属性 <TT>collector</TT>（收藏者）的值。
     *
     * @param collector 收藏者。
     */
    public void setCollector(User collector) {
        this.collector = collector;
    }

    /**
     * 获取属性 <TT>content</TT>（被收藏内容）的值。
     *
     * @return <TT>content</TT> 被收藏内容。
     */
    public Content getContent() {
        return content;
    }

    /**
     * 设置属性 <TT>content</TT>（被收藏内容）的值。
     *
     * @param content 被收藏内容。
     */
    public void setContent(Content content) {
        this.content = content;
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
