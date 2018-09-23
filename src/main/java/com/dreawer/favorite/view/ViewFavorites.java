package com.dreawer.favorite.view;

import java.util.List;

import com.dreawer.favorite.domain.Favorite;

/**
 * <CODE>ViewFavorite</CODE> 收藏内容视图实例类，用于展示收藏内容信息到客户端。
 *
 * @author David Dai
 * @version 1.0
 * @since Param 1.0
 */
public class ViewFavorites  {

    private String id = null; // 内容id

    private String name = null; // 名称

    private List<Favorite> favoriteList = null; // 收藏集合

    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public ViewFavorites() {
    }

    // --------------------------------------------------------------------------------
    // get和set方法
    // --------------------------------------------------------------------------------


    /**
     * 获取属性 <TT>id</TT>（id）的值。
     *
     * @return <TT>id</TT> id。
     */
    public String getId() {
        return id;
    }

    /**
     * 设置属性 <TT>id</TT>（id）的值。
     *
     * @param id id。
     */
    public void setId(String id) {
        this.id = id;
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
     * 获取属性 <TT>favoriteList</TT>（收藏列表）的值。
     *
     * @return <TT>favoriteList</TT> 收藏列表。
     */
    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    /**
     * 设置属性 <TT>favoriteList</TT>（收藏列表）的值。
     *
     * @param favoriteList 收藏列表。
     */
    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

}
