package com.dreawer.favorite.domain;

import java.sql.Timestamp;

import com.dreawer.domain.BaseDomain;

/**
 * <CODE>Favorites</CODE> 收藏夹实体类。
 *
 * @author zengzhijie
 * @version 1.0
 * @since Favorite 1.0
 */
public class Favorites extends BaseDomain {
    private static final long serialVersionUID = 7406372133507991038L;



    private String collectorId;   //收藏者

    private String name = null; // 收藏夹名称

    private Timestamp createTime = null; // 收藏时间

    private Timestamp updateTime = null; // 更新时间

    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public Favorites() {
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
     * 获取属性 <TT>name</TT>（名称）的值。
     *
     * @return <TT>name</TT> 名称。
     */
    public String getName() {
        return name;
    }

    /**
     * 设置属性 <TT>name</TT>（名称）的值。
     *
     * @param name 名称。
     */
    public void setName(String name) {
        this.name = name;
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
