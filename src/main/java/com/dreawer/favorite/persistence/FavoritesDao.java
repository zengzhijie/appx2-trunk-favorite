package com.dreawer.favorite.persistence;

import static com.dreawer.favorite.constants.DomainConstants.*;
import static com.dreawer.favorite.constants.DAOConstants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dreawer.persistence.mybatis.MyBatisBaseDao;
import org.springframework.stereotype.Repository;

import com.dreawer.favorite.domain.Favorites;
//import com.dreawer.sso.domain.User;

/**
 * <CODE>FavoritesDao</CODE> 收藏夹 DAO 类，负责对收藏夹实例数据进行访问和操作。
 *
 * @author zengzhijie
 * @version 1.0
 * @since Favorite 1.0
 */
@Repository(FAVORITES_DAO)
public class FavoritesDao extends MyBatisBaseDao<Favorites> {

    /**
     * 保存收藏夹信息。
     *
     * @param favorites 收藏夹信息。
     * @return 成功保存的记录数。
     * @author zengzhijie
     * @since 1.0
     */
    public int save(Favorites favorites) {
        return insert("save", favorites);
    }

    /**
     * 删除收藏夹信息。
     *
     * @param id 收藏夹信息。
     * @return 成功删除的记录数。
     * @author zengzhijie
     * @since 1.0
     */
    public int delete(String id) {
        return delete("delete", id);
    }

    /**
     * 更新收藏夹信息。
     *
     * @param favorites 收藏夹信息。
     * @return 成功更新的记录数。
     * @author zengzhijie
     * @since 1.0
     */
    public int update(Favorites favorites) {
        return update("updateFavorites", favorites);
    }

    /**
     * 查询指定收藏者所对应的收藏夹信息。
     *
     * @param collectorId 收藏者。
     * @return 收藏夹信息。如果存在返回收藏夹信息，否则返回null。
     * @author zengzhijie
     * @since 1.0
     */
    public List<Favorites> findList(String collectorId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(COLLECTOR, collectorId);
        return selectList("findFavoritesByCollector", params);
    }

    /**
     * 查询指定收藏者所对应的收藏夹信息。
     *
     * @param name 收藏夹名称。
     * @return 收藏夹信息。如果存在返回收藏夹信息，否则返回null。
     * @author zengzhijie
     * @since 1.0
     */
    public Favorites findByName(String name, String collectorId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(NAME, name);
        params.put(COLLECTOR, collectorId);
        return selectOne("findFavoritesByName", params);
    }

    /**
     * 查询指定收藏者所对应的收藏夹信息。
     *
     * @param id 收藏夹id。
     * @return 收藏夹信息。如果存在返回收藏夹信息，否则返回null。
     * @author zengzhijie
     * @since 1.0
     */
    public Favorites findById(String id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(ID, id);
        return selectOne("findFavoritesById", params);
    }

}
